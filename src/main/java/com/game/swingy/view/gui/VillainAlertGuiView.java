package com.game.swingy.view.gui;

import com.game.swingy.controller.VillainAlertController;
import com.game.swingy.view.VillainAlert;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class VillainAlertGuiView implements VillainAlert {

    private VillainAlertController alertController;
    private JFrame jf;
    private JLabel levelLabel1;
    private JLabel levelLabel2;
    private JLabel attackLabel1;
    private JLabel attackLabel2;
    private JLabel defenseLabel1;
    private JLabel defenseLabel2;
    private JLabel artefactsLabel;
    private JLabel weaponLabel1;
    private JLabel weaponLabel2;
    private JLabel armorLabel1;
    private JLabel armorLabel2;
    private JLabel healthLabel1;
    private JLabel healthLabel2;
    private JButton fightBtn;
    private JButton runBtn;
    private JPanel panelMain;
    private JPanel panelBtn;

    public VillainAlertGuiView(VillainAlertController villainAlertController) {

        this.alertController = villainAlertController;
        createTools();
        jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        panelMain.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        panelMain.add(levelLabel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(levelLabel2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(attackLabel1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(attackLabel2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(defenseLabel1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(defenseLabel2, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(artefactsLabel, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        artefactsLabel.setHorizontalAlignment(JLabel.CENTER);
        panelMain.add(weaponLabel1, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(weaponLabel2, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(armorLabel1, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(armorLabel2, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(healthLabel1, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(healthLabel2, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelBtn.add(fightBtn, BorderLayout.WEST);
        panelBtn.add(runBtn, BorderLayout.EAST);
        jf.add(panelMain);
        jf.add(panelBtn);
        villainAlertController.getTextOnBtnLabel(this);
        initBtn();

    }

    private void createTools() {

        jf = new JFrame("Swingy allert");
        panelMain = new JPanel();
        panelBtn = new JPanel();
        panelBtn.setLayout(new BorderLayout());
        panelMain.setLayout(new GridBagLayout());
        levelLabel1 = new JLabel("Villain level");
        levelLabel2 = new JLabel();
        attackLabel1 = new JLabel("Villain attack");
        attackLabel2 = new JLabel();
        defenseLabel1 = new JLabel("Villain degense");
        defenseLabel2 = new JLabel();
        artefactsLabel = new JLabel("Villain artefacts");
        weaponLabel1 = new JLabel("Points plus to attack");
        weaponLabel2 = new JLabel();
        armorLabel1 = new JLabel("Points plus to defense");
        armorLabel2 = new JLabel();
        healthLabel1 = new JLabel("Health");
        healthLabel2 = new JLabel();
        fightBtn = new JButton("FIGHT");
        runBtn = new JButton("RUN");
        jf.setLayout(new FlowLayout());
        jf.setSize(220, 250);
        jf.setResizable(false);

        jf.setVisible(true);
        jf.setLocationRelativeTo(null);



    }

    public void initBtn() {

        getRunBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRunAlert();
            }
        });
        getFightBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showFightAlert();
            }
        });
    }

    public void setTextOnBtnLabel(int level, int attack, int weapon, int defense,
                                   int armor, int health) {

        getLevelLabel2().setText(Integer.toString(level));
        getAttackLabel2().setText(Integer.toString(attack) +
                " + " + Integer.toString(weapon));
        getDefenseLabel2().setText(Integer.toString(defense) +
                " + " + Integer.toString(armor));
        getWeaponLabel2().setText(Integer.toString(weapon));
        getArmorLabel2().setText(Integer.toString(armor));
        getHealthLabel2().setText(Integer.toString(health));

    }

    public void onClickRunYes() {

        Random random = new Random();
        if (random.nextBoolean()) {
            showDisLucky();
            jf.dispose();
            alertController.onClickRunYes();

        }
        else {
            showLucky();
            jf.dispose();
            alertController.getMapController().getMapJframe().setVisible(true);
        }
    }

    private void showLucky() {

        JOptionPane.showMessageDialog(null,
                "That's you lucky");
    }

    private void showDisLucky() {

        JOptionPane.showMessageDialog(null,
                "That's you don't lucky");
    }

    public void showFightAlert() {

        int res = JOptionPane.showConfirmDialog(
                null,
                "Are you sure that you want fight with villain?",
                "Swingy allert Question",
                JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            jf.dispose();
            alertController.onClickRunYes();
        }

    }

    public void showRunAlert() {

        int res = JOptionPane.showConfirmDialog(
                null,
                "You have 50% chance of returning to the previous position. " +
                        "If the odds aren’t on your side, you must fight the " +
                        "villian\nAre you sure that you want to run?",
                "Swingy allert Question",
                JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION)
            onClickRunYes();
    }

    private JLabel getLevelLabel2() {
        return levelLabel2;
    }

    private JLabel getAttackLabel2() {
        return attackLabel2;
    }

    private JLabel getDefenseLabel2() {
        return defenseLabel2;
    }

    private JLabel getWeaponLabel2() {
        return weaponLabel2;
    }

    private JLabel getArmorLabel2() {
        return armorLabel2;
    }

    private JLabel getHealthLabel2() {
        return healthLabel2;
    }

    private JButton getFightBtn() {
        return fightBtn;
    }

    private JButton getRunBtn() {
        return runBtn;
    }

}
