package com.game.swingy.controller;

import com.game.swingy.core.DataBase.DbMySQL;
import com.game.swingy.core.Map.Map;
import com.game.swingy.core.Map.ModeEnum;
import com.game.swingy.core.Unit.Coordinates;
import com.game.swingy.core.Unit.Hero.Hero;
import com.game.swingy.core.Unit.Unit;
import com.game.swingy.view.MainMap;
import com.game.swingy.view.console.MapConsoleView;
import com.game.swingy.view.console.StartConsoleView;
import com.game.swingy.view.console.VillainAlertConsoleView;
import com.game.swingy.view.gui.MapGuiView;
import com.game.swingy.view.gui.StartGuiView;
import com.game.swingy.view.gui.VillainAlertGuiView;

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

    private void onClickHeroButton() {
        StatisticsController statisticsController = new StatisticsController();
        statisticsController.setHeroFields();
    }

    public void onClickVillainsButton(int x, int y) {
        Unit villain = getVillain(x, y);
        VillainAlertController villainAlertController =
                new VillainAlertController(villain, this);
        if (Map.getMap().getMode() == ModeEnum.CONSOLE)
            new VillainAlertConsoleView(villainAlertController);
        else
            new VillainAlertGuiView(villainAlertController);
    }

    public void changeHeroPosition(int toX, int toY) {

        Map.getMap().getObservers().get(0).getCoordinates().setX(toX);
        Map.getMap().getObservers().get(0).getCoordinates().setY(toY);
    }

    public void heroKilledVillain(Unit villain) {

        Map.getMap().unregister(villain);
        mainMap.changeHeroPosition(villain.getCoordinates().getX(), villain.getCoordinates().getY());

    }

    private Unit getVillain(int x, int y) {

        List<Unit> unit = Map.getMap().getObservers();
        for (Unit one: unit) {
            if (one.getCoordinates().getX() == x && one.getCoordinates().getY() == y)
                return one;
        }
        throw new Error("Not valid x and y coordinats");
    }

    private boolean checkXYInUnitList(int x, int y) {

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
        } else if (checkXYInUnitList(x, y)) {
            mainMap.onClickVillainsButton(x, y);
        } else {
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
                    mainMap.setVillainsIcon(x, y);
                }
            }
        }
    }

    public void setVillainIcon() {

        int length = Map.getMap().getObservers().size();
        List<Unit> units = Map.getMap().getObservers();
        for (int i = 1; i < length; i++) {
            mainMap.setVillainsIcon(units.get(i).getCoordinates().getX(),
                    units.get(i).getCoordinates().getY());
        }
    }

    public boolean isMissionCompleted() {

        int x = Map.getMap().getObservers().get(0).getCoordinates().getX();
        int y = Map.getMap().getObservers().get(0).getCoordinates().getY();

        if (x == 0 || y == 0 || x == getMapSize() - 1 || y == getMapSize() - 1) {
            mainMap.showMissionCompletedView();
            Map.getMap().deleteVillainFromListofUnit();
            Hero.class.cast(Map.getMap().getObservers().get(0)).setTwoCoordinatesXY();
            Map.getMap().fillListOfVillain();

            return true;
        }

        return false;
    }

    public void changeGameMode() {

        int id = Map.getMap().getDbMySQL().getLastId();

        if (Map.getMap().getMode() == ModeEnum.GUI)
            Map.getMap().setMode(ModeEnum.CONSOLE);
        else
            Map.getMap().setMode(ModeEnum.GUI);

        Map.getMap().loadUnits(Map.getMap().getDbMySQL().getSelectedHero(id));
        Map.getMap().loadUnits(Map.getMap().getDbMySQL().getSelectedVillain(id));
        Map.getMap().getDbMySQL().deleteRow(id);

        MapController mapController = new MapController();

        if (Map.getMap().getMode() == ModeEnum.CONSOLE) {
            mainMap = null;
            mainMap = new MapConsoleView(mapController);
        }
        else {
            mainMap = null;
            mainMap = new MapGuiView(mapController);
            mapController.setVillainIcon();
        }
    }

    public void visibleStartFrame() {

        JFrame startFrame = Map.getMap().getStartFrame();

        if (startFrame == null) {
            StarterController starterController = new StarterController();
            if (Map.getMap().getMode() == ModeEnum.GUI)
                new StartGuiView(starterController);
            else
                new StartConsoleView(starterController);
        } else {
            Map.getMap().getStartFrame().setVisible(true);
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