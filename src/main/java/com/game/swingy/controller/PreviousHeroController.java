package com.game.swingy.controller;

import com.game.swingy.core.Map.TableActionListener;
import com.game.swingy.core.Map.Map;
import com.game.swingy.view.gui.PreviousHeroView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreviousHeroController {

    private PreviousHeroView previousHeroView;

    public PreviousHeroController() {

        previousHeroView = new PreviousHeroView();
        String[][] rowValue = Map.getMap().getDbMySQL().getData();
        previousHeroView.setDeleteAction(deleteActionGui());
        previousHeroView.setLoadAction(loadActionGui());
        previousHeroView.getHeroTableModel().addDate(rowValue);

    }

    private ActionListener deleteActionGui() {
        return new TableActionListener(
                previousHeroView.getHeroTable(),
                previousHeroView.getHeroTableModel()
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
                    Map.getMap().getDbMySQL().deleteRow(Integer.parseInt(id));
                }
            }
        };
    }


    private ActionListener loadActionGui() {
        return new TableActionListener(
                previousHeroView.getHeroTable(),
                previousHeroView.getHeroTableModel(),
                previousHeroView.getFrame()
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
                    Map.getMap().loadUnits(Map.getMap().getDbMySQL().getSelectedHero(Integer.parseInt(id)));
                    Map.getMap().loadUnits(Map.getMap().getDbMySQL().getSelectedVillain(Integer.parseInt(id)));
                    MapController mapController = new MapController();
                    //mapController.setVillainIcon();//TODO закоментував щоб запустилося
                    this.getjFrame().dispose();
                }
            }
        };
    }
}
