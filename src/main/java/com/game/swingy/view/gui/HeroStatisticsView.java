package com.game.swingy.view.gui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class HeroStatisticsView {

    private JFrame jf;
    private JLabel labelYourHero;
    private JLabel labelHeroName1;
    private JLabel labelHeroName2;
    private JLabel labelHeroCass1;
    private JLabel labelHeroCass2;
    private JLabel labelHeroLevel1;
    private JLabel labelHeroLevel2;
    private JLabel labelHeroExp1;
    private JLabel labelHeroExp2;
    private JLabel labelAttack1;
    private JLabel labelAttack2;
    private JLabel labelDefense1;
    private JLabel labelDefense2;
    private JLabel labelArtefacts;
    private JLabel labelWeapon1;
    private JLabel labelWeapon2;
    private JLabel labelArmor1;
    private JLabel labelArmor2;
    private JLabel labelHealth1;
    private JLabel labelHealth2;
    private JPanel panelMain;
    private JPanel panelBtn;
    private JButton btnOk;

    public HeroStatisticsView() {


        createTools();
        panelMain.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        panelMain.add(labelYourHero, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.BOTH, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelHeroName1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelHeroName2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelHeroCass1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelHeroCass2, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelHeroLevel1, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelHeroLevel2, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelHeroExp1, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelHeroExp2, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelHealth1, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelHealth2, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelAttack1, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelAttack2, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelDefense1, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelDefense2, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelArtefacts, new GridBagConstraints(0, 8, 2, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelWeapon1, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelWeapon2, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelArmor1, new GridBagConstraints(0, 10, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelArmor2, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelBtn.add(btnOk, BorderLayout.NORTH);
        jf.add(panelMain);
        jf.add(panelBtn);
    }

    private void createTools() {

        jf = new JFrame("Hero Statistics");
        panelMain = new JPanel();
        panelBtn = new JPanel();
        panelBtn.setLayout(new BorderLayout());
        panelMain.setLayout(new GridBagLayout());
        labelYourHero = new JLabel("Your hero");
        labelYourHero.setHorizontalAlignment(JLabel.CENTER);
        labelHeroName1 = new JLabel("Name");
        labelHeroName2 = new JLabel();
        labelHeroCass1 = new JLabel("Class");
        labelHeroCass2 = new JLabel();
        labelHeroLevel1 = new JLabel("Level");
        labelHeroLevel2 = new JLabel();
        labelHeroExp1 = new JLabel("Experience");
        labelHeroExp2 = new JLabel();
        labelAttack1 = new JLabel("Hero attack");
        labelAttack2 = new JLabel();
        labelDefense1 = new JLabel("Hero defense");
        labelDefense2 = new JLabel();
        labelArtefacts = new JLabel("Artefacts");
        labelArtefacts.setHorizontalAlignment(JLabel.CENTER);
        labelWeapon1 = new JLabel("Points plus to attack");
        labelWeapon2 = new JLabel();
        labelArmor1 = new JLabel("Points plus to defense");
        labelArmor2 = new JLabel();
        labelHealth1 = new JLabel("Health");
        labelHealth2 = new JLabel();
        btnOk = new JButton("OK");
        jf.setLayout(new FlowLayout());
        jf.setSize(250, 350);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
    }

    public JLabel getLabelHeroName2() {
        return labelHeroName2;
    }

    public JLabel getLabelHeroCass2() {
        return labelHeroCass2;
    }

    public JLabel getLabelHeroLevel2() {
        return labelHeroLevel2;
    }

    public JLabel getLabelHeroExp2() {
        return labelHeroExp2;
    }

    public JLabel getLabelWeapon2() {
        return labelWeapon2;
    }

    public JLabel getLabelArmor2() {
        return labelArmor2;
    }

    public JLabel getLabelHealth2() {
        return labelHealth2;
    }

    public JLabel getLabelAttack2() {
        return labelAttack2;
    }

    public JLabel getLabelDefense2() {
        return labelDefense2;
    }

    public JFrame getJf() {
        return jf;
    }

    public JButton getBtnOk() {
        return btnOk;
    }
}
