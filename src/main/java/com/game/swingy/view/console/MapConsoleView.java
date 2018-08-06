package com.game.swingy.view.console;

import com.game.swingy.controller.MapController;
import com.game.swingy.controller.StarterController;
import com.game.swingy.core.Map.GameValidator;
import com.game.swingy.core.Map.Map;
import com.game.swingy.view.MainMap;
import com.game.swingy.view.StartView;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Scanner;

public class MapConsoleView implements MainMap{

    @Pattern(regexp = "^\\d+$", message = "ERROR only digits allowed")
    @Min(value = 0, message = "ERROR You should choose one option 1, 2, 3, 4 or 0")
    @Max(value = 4, message = "ERROR You should choose one option 1, 2, 3, 4 or 0")
    private String choose;

    @Pattern(regexp = "^\\d+$", message = "ERROR only digits allowed")
    @Min(value = 1, message = "ERROR You should choose one option 1 or 2")
    @Max(value = 2, message = "ERROR You should choose one option 1 or 2")
    private String yesNo;

    private Scanner sc;
    private MapController mapController;
    private int mapSize;
    private Character[][] map;

    public MapConsoleView(MapController mapController) {
        this.mapController = mapController;
        this.mapSize = mapController.getMapSize();
        this.map = new Character[mapSize][mapSize];
        sc = new Scanner(System.in);
        mapController.setRandomCoordinates(this);
        mapController.setMainMap(this);
        mapController.setVillainIcon();
        setEmptyChar();

        setHeroIcon(Map.getMap().getObservers().get(0).getCoordinates().getX(),
                Map.getMap().getObservers().get(0).getCoordinates().getY());

        showMap();
        initMoveHero();
    }

    private void showMap() {

        outputHorizontalLine();
        for (int i = 0; i < mapSize; i++) {
            System.out.print("\n");
            for (int j = 0; j < mapSize; j++) {
                if (j == 0)
                    System.out.print("|");
                System.out.format("%c|", map[i][j]);
            }
            System.out.print("\n");
            outputHorizontalLine();
        }
        System.out.print("\n");
    }

    private void setEmptyChar() {

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (map[i][j] == null)
                    setEmptyIcon(i, j);
            }

        }
    }

    private void outputHorizontalLine() {

        int k = mapSize * 2 + 1;
        while (k-- > 0)
            System.out.print("-");
    }

    private void showMovePosition() {

        System.out.println("Move your hero on 1 from 4 position: \n" +
                "1 - North\n" +
                "2 - East\n" +
                "3 - South\n" +
                "4 - West\n\n" +
                "0 - save");
        this.choose = sc.next();
        if (!GameValidator.getGameValidator().validate(this))
            showMovePosition();

    }

    @Override
    public void initMoveHero() {

        showMovePosition();
        int choose = Integer.parseInt(this.choose) - 1;
        if (choose == -1) {
            initCloseListener();
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
        if (moveHeroEnum == MoveHeroEnum.NORTH && (x - 1) >= 0 )//рухаємся в гору
            mapController.onClickButton(x - 1, y, this);
        else if (moveHeroEnum == MoveHeroEnum.SOUTH && ((x + 1) < mapSize))
            mapController.onClickButton(x + 1, y, this);
        else if (moveHeroEnum == MoveHeroEnum.WEST && (y - 1) >= 0)//рухаємся в ліво
            mapController.onClickButton(x, y - 1, this);
        else if (moveHeroEnum == MoveHeroEnum.EAST && (y + 1) < mapSize)//рухаємся в право
            mapController.onClickButton(x, y + 1, this);
        else
            System.out.println("Error in move hero. Not corrected coordinates");
    }

    private void showYesNo() {

        System.out.println("Are you sure you want to close current game with save?\n" +
                "1 - yes\n" +
                "2 - no");
        yesNo = sc.next();
        if (!GameValidator.getGameValidator().validate(this))
            showYesNo();
    }

    @Override
    public void initCloseListener() {

        showYesNo();
        int choose = Integer.parseInt(this.yesNo);
        if (choose == 1) {
            mapController.saveHero();
        }
        else {
            mapController.deleteHeroAndVillainFromListOfUnit();
        }
        StarterController starterController = new StarterController();
        StartView startView = new StartConsoleView(starterController);
    }

    @Override
    public void setHeroIcon(int x, int y) {
        this.map[x][y] = 'H';
    }

    @Override
    public void setVilliansIcon(int x, int y) {
        this.map[x][y] = 'V';
    }

    @Override
    public void setEmptyIcon(int x, int y) {
        this.map[x][y] = ' ';
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
        showMap();
        initMoveHero();

    }
}
