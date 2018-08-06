package com.game.swingy.controller;

import com.game.swingy.core.Map.Map;
import com.game.swingy.core.Map.ModeEnum;
import com.game.swingy.view.MainMap;
import com.game.swingy.view.StartView;
import com.game.swingy.view.console.MapConsoleView;
import com.game.swingy.view.console.StartConsoleView;
import com.game.swingy.view.gui.MapGuiView;
import com.game.swingy.view.gui.PreviousHeroGuiView;

import javax.swing.*;

public class PreviousHeroController {

    private PreviousHeroGuiView previousHeroGuiView;
    private String[][] rowValue;

    public PreviousHeroController() {

//        previousHeroGuiView = new PreviousHeroGuiView();
        this.rowValue = Map.getMap().getDbMySQL().getData();
        /*previousHeroGuiView.setDeleteAction(deleteActionGui());
        previousHeroGuiView.setLoadAction(loadActionGui());
        previousHeroGuiView.getHeroTableModel().addDate(rowValue);*/

    }

    /*private ActionListener deleteActionGui() {
        return new TableActionListener(
                previousHeroGuiView.getHeroTable(),
                previousHeroGuiView.getHeroTableModel()
        ) {
            @Override
            public void actionPerformed(ActionEvent e) {

                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete hero?",
                        "Deleting Swingy Hero",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    int selectedRow = this.getHeroTable().getSelectedRow();
                    Object object = this.getHeroTable().getValueAt(selectedRow, 0);
                    String id = object.toString();
                    System.out.println(selectedRow);
                    this.getHtm().delRow(selectedRow);
                    this.getHtm().fireTableDataChanged();
                    Map.getGameValidator().getDbMySQL().deleteRow(Integer.parseInt(id));
                }
            }
        };
    }*/

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
//        Map.getGameValidator().setVillainIcon();
    }

    /*private ActionListener loadActionGui() {
        return new TableActionListener(
                previousHeroGuiView.getHeroTable(),
                previousHeroGuiView.getHeroTableModel(),
                previousHeroGuiView.getFrame()
        ) {
            @Override
            public void actionPerformed(ActionEvent e) {

                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to load hero?",
                        "Loading Swingy Hero",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    //TODO load hero
                    int selectedRow = this.getHeroTable().getSelectedRow();
                    Object object = this.getHeroTable().getValueAt(selectedRow, 0);
                    String id = object.toString();
                    Map.getGameValidator().loadUnits(Map.getGameValidator().getDbMySQL().getSelectedHero(Integer.parseInt(id)));
                    Map.getGameValidator().loadUnits(Map.getGameValidator().getDbMySQL().getSelectedVillain(Integer.parseInt(id)));
                    MapController mapController = new MapController();
                    //mapController.setVillainIcon();//TODO закоментував щоб запустилося
                    this.getjFrame().dispose();
                }
            }
        };
    }*/

    public String[][] getRowValue() {
        return rowValue;
    }
}
