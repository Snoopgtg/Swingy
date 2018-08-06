package com.game.swingy.controller;

import com.game.swingy.core.Map.Map;
import com.game.swingy.core.Map.ModeEnum;
import com.game.swingy.view.MainMap;
import com.game.swingy.view.console.MapConsoleView;
import com.game.swingy.view.gui.MapGuiView;

public class PreviousHeroController {

    private String[][] rowValue;

    PreviousHeroController() {

        this.rowValue = Map.getMap().getDbMySQL().getData();
    }

    public void delete(int id) {

        Map.getMap().getDbMySQL().deleteRow(id);
    }

    public void load(int selectedId) {

        Map.getMap().loadUnits(Map.getMap().getDbMySQL().getSelectedHero(selectedId));
        Map.getMap().loadUnits(Map.getMap().getDbMySQL().getSelectedVillain(selectedId));
        com.game.swingy.controller.MapController mapController = new com.game.swingy.controller.MapController();
        MainMap mainMap;
        if (Map.getMap().getMode() == ModeEnum.CONSOLE)
            mainMap = new MapConsoleView(mapController);
        else {
            mainMap = new MapGuiView(mapController);

            mapController.setVillainIcon();
        }
    }

    public String[][] getRowValue() {
        return rowValue;
    }
}
