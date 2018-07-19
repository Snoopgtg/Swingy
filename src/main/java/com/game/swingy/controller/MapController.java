package com.game.swingy.controller;

import com.game.swingy.core.DataBase.DbMySQL;
import com.game.swingy.core.Map.Map;
import com.game.swingy.core.Unit.Coordinates;
import com.game.swingy.core.Unit.Unit;
import com.game.swingy.view.MainMap;

import java.util.List;
import java.util.Random;

public class MapController {

    private int mapSize;
    //private MapGuiView mapGuiView;
    private DbMySQL dbMySQL;


    public MapController() {

        int level = Map.getMap().getObservers().get(0).getLevel();

        mapSize = getMapSize(level);
        //mapGuiView = new MapGuiView();
        dbMySQL = Map.getMap().getDbMySQL();
    }

    static private int getMapSize(int heroLevel){

        return (heroLevel - 1) * 5 + 10 - (heroLevel % 2);
    }

    /*private void deAndActivatedbtnUnits() {

        int x = Map.getMap().getObservers().get(0).getCoordinates().getX();
        int y = Map.getMap().getObservers().get(0).getCoordinates().getY();
        System.out.println("x = " + x + "\ny = " + y);
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++){
                if (i == (x - 1) && j == y && (x - 1) < mapSize && (x - 1) >= 0)//рухаємся в гору
                    mapGuiView.btnUnitsActivated(i, j);
                else if (i == (x + 1) && j == (y) && (x + 1) < mapSize && (x + 1) >= 0)
                    mapGuiView.btnUnitsActivated(i, j);
                else if (i == x && j == (y - 1) && (y - 1) < mapSize && (y - 1) >= 0)//рухаємся в ліво
                    mapGuiView.btnUnitsActivated(i, j);
                else if (i == x && j == (y + 1) && (y + 1) < mapSize && (y + 1) >= 0)//рухаємся в право
                    mapGuiView.btnUnitsActivated(i, j);
                else if (i == x && j == y)
                    mapGuiView.btnUnitsActivated(i, j);
                else
                    mapGuiView.btnUnitsDeActivated(i,j);
            }
        }
    }*/

    /*private void initMoveHero() {

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                mapGuiView.getBtnUnits()[i][j].addActionListener(new EmptyButtonListener(i, j) {
                    public void actionPerformed(ActionEvent e) {
                        onClickButton(this.getCoordinateX(), this.getCoordinateY());
                    }
                });
            }
        }
    }*/

    /*private void initCloseLisener() {

        mapGuiView.getJf().addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to close current game with save?",
                        "Close Swingy Message Box",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == 0) {
                    fillDataBase();
                    Map.getMap().deleteVillainFromListofUnit();
                    Map.getMap().deleteHeroFromListOfUnit();
                    mapGuiView.getJf().dispose();
                    //mapGuiView = null;
                }
                else {
                    Map.getMap().deleteVillainFromListofUnit();
                    Map.getMap().deleteHeroFromListOfUnit();
                    mapGuiView.getJf().dispose();
                }
            }
        });
    }*/

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

    /*public void onClickButton(int x, int y) {

        int heroX = Map.getMap().getObservers().get(0).getCoordinates().getX();
        int heroY = Map.getMap().getObservers().get(0).getCoordinates().getY();

        if (heroX == x && heroY == y) {
            onClickHeroButton();
        }
        else if (checkXYInUnitList(x, y)) {
            onClickVillainsButton(x, y);
        }
        else {
            onClickEmptyButton(x, y);
        }

    }*/
    public void onClickHeroButton() {
        System.out.println("On Hero");//TODO if(Map.mode ==?)
        StatisticsController statisticsController = new StatisticsController();
        statisticsController.setHeroFields();
    }

    public void onClickVillainsButton(int x, int y) {
        System.out.println("On Villain");
        Unit villain = getVillian(x, y);//TODO if(Map.mode ==?)
        VillianAllertController villianAllertController = new
                VillianAllertController(villain,this);
        System.out.println("yes on villain button");
    }


    /*public void onClickEmptyButton(int x, int y) {

        System.out.println("Empty");
        changeHeroPosition(x, y);

    }*/

    /*public void changeHeroPosition(int toX, int toY) {

        int x = Map.getMap().getObservers().get(0).getCoordinates().getX();
        int y = Map.getMap().getObservers().get(0).getCoordinates().getY();
        mapGuiView.setEmptyIcon(x, y);
        mapGuiView.setHeroIcon(toX, toY);
        Map.getMap().getObservers().get(0).getCoordinates().setX(toX);
        Map.getMap().getObservers().get(0).getCoordinates().setY(toY);
        deAndActivatedbtnUnits();
        isCheckWinner();
    }*/

    public void changeHeroPosition(int toX, int toY) {

        Map.getMap().getObservers().get(0).getCoordinates().setX(toX);
        Map.getMap().getObservers().get(0).getCoordinates().setY(toY);
    }

    public void heroKilledVillain(Unit villain) {

        changeHeroPosition(villain.getCoordinates().getX(), villain.getCoordinates().getY());
        Map.getMap().unregister(villain);
    }

    /*public void changeIconButton() {

        int x = Map.getMap().getObservers().get(0).getCoordinates().getX();
        int y = Map.getMap().getObservers().get(0).getCoordinates().getY();

        mapGuiView.changeIconButton(x, y);
    }*/

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

    /*public void setVillainIcon() {

        int length = Map.getMap().getObservers().size();
        List<Unit> units = Map.getMap().getObservers();
        for (int i = 1; i < length; i++) {
            mapGuiView.setVilliansIcon(units.get(i).getCoordinates().getX(),
                    units.get(i).getCoordinates().getY());
        }
    }*/

    public void isCheckWinner(MainMap mainMap) {

        int x = Map.getMap().getObservers().get(0).getCoordinates().getX();
        int y = Map.getMap().getObservers().get(0).getCoordinates().getY();

        if (x == 0 || y == 0 || x == getMapSize() - 1 ||
                y == getMapSize() - 1) {
            System.out.println("Mission completed");
            mainMap.showMissionCompletedView();
            Map.getMap().deleteVillainFromListofUnit();
            //mapGuiView.closeWindow();
            //mapGuiView.getJf().setVisible(false);
            Map.getMap().fillListOfVillain();
        }
    }

    public int getMapSize() {
        return mapSize;
    }

}