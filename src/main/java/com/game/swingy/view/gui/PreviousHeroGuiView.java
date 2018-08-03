package com.game.swingy.view.gui;

import com.game.swingy.controller.PreviousHeroController;
import com.game.swingy.core.Map.TableActionListener;
import com.game.swingy.view.PreviousHero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreviousHeroGuiView implements PreviousHero {

    private PreviousHeroController previousHeroController;
    private JFrame frame;
    private HeroTableModel htm;
    private JButton loadBtn;
    private JButton deleteBtn;
    private JTable heroTable;
    private JScrollPane heroTableScrollPane;

    public PreviousHeroGuiView(PreviousHeroController previousHeroController) {

        this.previousHeroController = previousHeroController;
        createTools();
        initPreviousHeroView();
        setDeleteAction();
        setLoadAction();
        htm.addDate(previousHeroController.getRowValue());


    }

    private void initPreviousHeroView() {


        frame.add(heroTableScrollPane, new GridBagConstraints(0, 0, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        frame.add(loadBtn, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        frame.add(deleteBtn, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        frame.setVisible(true);
        frame.pack();
    }

    private void createTools() {

        frame = new JFrame("Swingy Load");
        frame.setSize(new Dimension(850, 400));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        loadBtn = new JButton("Load hero");
        deleteBtn = new JButton("Delete hero");

        htm = new HeroTableModel();
        heroTable = new JTable(htm);
        heroTableScrollPane = new JScrollPane(heroTable);
        heroTableScrollPane.setPreferredSize(new Dimension(850, 200));
    }

    public void setLoadAction() {
        loadBtn.addActionListener(loadActionGui());
    }

    public void setDeleteAction() {
        deleteBtn.addActionListener(deleteActionGui());
    }

    private ActionListener deleteActionGui() {
        return new TableActionListener(
                this.heroTable,
                this.htm
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
                    previousHeroController.delete(Integer.parseInt(id));
                    previousHeroController.isEmptyHeroDB();
                }
            }
        };
    }

    private ActionListener loadActionGui() {
        return new TableActionListener(
                this.heroTable,
                this.htm,
                this.frame
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
                    previousHeroController.load(Integer.parseInt(id));
                    this.getjFrame().dispose();
                }
            }
        };
    }

    public JFrame getFrame() {
        return frame;
    }
    public HeroTableModel getHeroTableModel() {
        return htm;
    }
    public JTable getHeroTable() {
        return heroTable;
    }

}