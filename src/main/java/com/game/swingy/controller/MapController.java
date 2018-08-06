package com.game.swingy.controller;

import com.game.swingy.core.DataBase.DbMySQL;
import com.game.swingy.core.Map.Map;
import com.game.swingy.core.Map.ModeEnum;
import com.game.swingy.core.Unit.Coordinates;
import com.game.swingy.core.Unit.Unit;
import com.game.swingy.view.MainMap;
import com.game.swingy.view.VillainAllert;
import com.game.swingy.view.console.VillainAllertConsoleView;
import com.game.swingy.view.gui.VillainAllertGuiView;

import javax.swing.*;
import java.util.List;
import java.util.Random;

public class MapController {

    private int mapSize;
    private JFrame mapJframe;
    private MainMap mainMap;
    private DbMySQL dbMySQL;


    public MapController() {

        int level = Map.getMap().getObservers().get(0).getLevel();

        mapSize = getMapSize(level);
        dbMySQL = Map.getMap().getDbMySQL();
    }

    static private int getMapSize(int heroLevel){

        return (heroLevel - 1) * 5 + 10 - (heroLevel % 2);
    }

    public void saveHero() {

        fillDataBase();
        deleteHeroAndVillainFromListOfUnit();
    }

    public void deleteHeroAndVillainFromListOfUnit() {
        Map.getMap().deleteVillainFromListofUnit();
        Map.getMap().deleteHeroFromListOfUnit();
    }

    private void fillDataBase() {

        List<Unit> unit = Map.getMap().getObservers();
        for (Unit one: unit) {
            dbMySQL.fillUnitTable(one);
        }
    }

    public void onClickHeroButton() {
        //TODO if(Map.mode ==?)
        //TODO hero stat in console mode
        StatisticsController statisticsController = new StatisticsController();
        statisticsController.setHeroFields();
    }

    public void onClickVillainsButton(int x, int y) {
        Unit villain = getVillian(x, y);
        VillainAllert villainAllert;
        VillainAllertController villainAllertController =
                new VillainAllertController(villain, this);
        if (Map.getMap().getMode() == ModeEnum.CONSOLE)
            villainAllert = new VillainAllertConsoleView(villainAllertController);
        else
            villainAllert = new VillainAllertGuiView(villainAllertController);
    }

    public void changeHeroPosition(int toX, int toY) {

        Map.getMap().getObservers().get(0).getCoordinates().setX(toX);
        Map.getMap().getObservers().get(0).getCoordinates().setY(toY);
    }

    public void heroKilledVillain(Unit villain) {

        mainMap.changeHeroPosition(villain.getCoordinates().getX(), villain.getCoordinates().getY());
        Map.getMap().unregister(villain);
    }

    public Unit getVillian(int x, int y) {

        List<Unit> unit = Map.getMap().getObservers();
        for (Unit one: unit) {
            if (one.getCoordinates().getX() == x && one.getCoordinates().getY() == y)
                return one;
        }
        throw new Error("Not valid x and y coordinats");
    }
    public boolean checkXYInUnitList(int x, int y) {

        List<Unit> unit = Map.getMap().getObservers();
        for (Unit one: unit) {
            if (one.getCoordinates().getX() == x && one.getCoordinates().getY() == y)
                return true;
        }
        return false;
    }

    public void onClickButton(int x, int y, MainMap mainMap) {

        int heroX = Map.getMap().getObservers().get(0).getCoordinates().getX();
        int heroY = Map.getMap().getObservers().get(0).getCoordinates().getY();

        if (heroX == x && heroY == y) {
            onClickHeroButton();
        }
        else if (checkXYInUnitList(x, y)) {
            mainMap.onClickVillainsButton(x, y);
        }
        else {
            mainMap.onClickEmptyButton(x, y);
        }

    }

    public void setRandomCoordinates(MainMap mainMap) {

        Random random = new Random();
        int length = Map.getMap().getObservers().size();
        List<Unit> units = Map.getMap().getObservers();
        for (int i = 1; i < length; i++) {
            if (units.get(i).getCoordinates().getX() == -1 && units.get(i).getCoordinates().getY() == -1) {
                int x = random.nextInt(mapSize);
                int y = random.nextInt(mapSize);
                if (checkXYInUnitList(x, y)) {
                    this.setRandomCoordinates(mainMap);
                } else {
                    Coordinates coordinates = new Coordinates(x, y);
                    units.get(i).setCoordinates(coordinates);
                    mainMap.setVilliansIcon(x, y);
                }
            }
        }
    }

    public void setVillainIcon() {

        int length = Map.getMap().getObservers().size();
        List<Unit> units = Map.getMap().getObservers();
        for (int i = 1; i < length; i++) {
            mainMap.setVilliansIcon(units.get(i).getCoordinates().getX(),
                    units.get(i).getCoordinates().getY());
        }
    }

    public void isCheckWinner(MainMap mainMap) {

        int x = Map.getMap().getObservers().get(0).getCoordinates().getX();
        int y = Map.getMap().getObservers().get(0).getCoordinates().getY();

        if (x == 0 || y == 0 || x == getMapSize() - 1 ||
                y == getMapSize() - 1) {
            mainMap.showMissionCompletedView();
            Map.getMap().deleteVillainFromListofUnit();
            Map.getMap().fillListOfVillain();
        }
    }

    public int getMapSize() {
        return mapSize;
    }

    public JFrame getMapJframe() {
        return mapJframe;
    }

    public void setMapJframe(JFrame mapJframe) {
        this.mapJframe = mapJframe;
    }

    public void setMainMap(MainMap mainMap) {
        this.mainMap = mainMap;
    }
}