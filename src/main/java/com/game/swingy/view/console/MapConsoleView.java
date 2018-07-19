package com.game.swingy.view.console;

import com.game.swingy.controller.MapController;
import com.game.swingy.controller.StarterController;
import com.game.swingy.core.Map.HeroClassEnum;
import com.game.swingy.core.Map.Map;
import com.game.swingy.view.MainMap;
import com.game.swingy.view.StartView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class MapConsoleView implements MainMap{

    private MapController mapController;
    private int mapSize;
    private String[][] map;

    public MapConsoleView(MapController mapController) {
       this.mapController = mapController;
       this.mapSize = mapController.getMapSize();
       this.map = new String[mapSize][mapSize];
       int i = mapSize * 3;
       while (i-- > 0)
           System.out.print("-");

    }
    @Override
    public void initMoveHero() {

        System.out.println("Move your hero on 1 from 4 position: \n" +
                "1 - North\n" +
                "2 - East\n" +
                "3 - South\n" +
                "4 - West\n\n" +
                "0 - save");
        Scanner sc = new Scanner(System.in); // object for scanner
        int choose = sc.nextInt();
        if (choose == 0) {
            initCloseLisener();
            //TODO save the game
        }
        else {
            MoveHeroEnum moveHeroEnum = MoveHeroEnum.values()[choose];
            moveListener(moveHeroEnum);
        }
    }

    private void moveListener(MoveHeroEnum moveHeroEnum) {
        int x = Map.getMap().getObservers().get(0).getCoordinates().getX();
        int y = Map.getMap().getObservers().get(0).getCoordinates().getY();
        System.out.println("x = " + x + "\ny = " + y);
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (moveHeroEnum == MoveHeroEnum.NORTH && (i == (x - 1) && j == y && (x - 1) < mapSize
                        && (x - 1) >= 0))//рухаємся в гору
                    mapController.onClickButton(i, j, this);
                else if (moveHeroEnum == MoveHeroEnum.SOUTH && (i == (x + 1) && j == (y) && (x + 1) < mapSize
                        && (x + 1) >= 0))
                    mapController.onClickButton(i, j, this);
                else if (moveHeroEnum == MoveHeroEnum.WEST && (i == x && j == (y - 1) && (y - 1) < mapSize
                        && (y - 1) >= 0))//рухаємся в ліво
                    mapController.onClickButton(i, j, this);
                else if (moveHeroEnum == MoveHeroEnum.EAST && (i == x && j == (y + 1) && (y + 1) < mapSize
                        && (y + 1) >= 0))//рухаємся в право
                    mapController.onClickButton(i, j, this);
                else if (i == x && j == y)
                    mapController.onClickButton(i, j, this);
                else
                    System.out.println("Error in move hero. Not corrected coordinates");

            }
        }
    }
    @Override
    public void initCloseLisener() {

        System.out.println("Are you sure you want to close current game with save?\n" +
                "1 - yes\n" +
                "2 - no");
        Scanner sc = new Scanner(System.in); // object for scanner
        int choose = sc.nextInt();
        if (choose == 1) {
            mapController.saveHero();
            //TODO save the game
        }
        else {
            mapController.deleteHeroAndVillainFromListOfUnit();
        }
    }

    @Override
    public void setHeroIcon(int x, int y) {

        this.map[x][y] = "H";
    }

    @Override
    public void setVilliansIcon(int x, int y) {
        this.map[x][y] = "V";
    }

    @Override
    public void setEmptyIcon(int x, int y) {
        this.map[x][y] = " ";
    }

    @Override
    public void showMissionCompletedView() {

        System.out.println("Mission completed");
    }

    @Override
    public void onClickVillainsButton(int x, int y) {
        mapController.onClickVillainsButton(x, y);

    }

    @Override
    public void onClickEmptyButton(int x, int y) {
        changeHeroPosition(x, y);

    }

    @Override
    public void changeHeroPosition(int toX, int toY) {
        int x = Map.getMap().getObservers().get(0).getCoordinates().getX();
        int y = Map.getMap().getObservers().get(0).getCoordinates().getY();
        setEmptyIcon(x, y);
        setHeroIcon(toX, toY);
        mapController.changeHeroPosition(toX, toY);
        mapController.isCheckWinner(this);
    }
}
