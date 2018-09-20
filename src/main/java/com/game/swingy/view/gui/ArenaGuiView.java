package com.game.swingy.view.gui;

import com.game.swingy.controller.ArenaController;
import com.game.swingy.view.Arena;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Random;

public class ArenaGuiView implements Arena{

    private ArenaController arenaController;
    private JFrame jf;
    private JLabel labelVillain;
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
    private JLabel turnLabel;
    private JPanel panelHero;
    private JPanel panelMain;
    private JPanel panelVillain;
    private JButton heroBtn;
    private JButton villainBtn;
    private JLabel helmLabel1;
    private JLabel helmLabel2;

    public ArenaGuiView(ArenaController arenaController) {

        this.arenaController = arenaController;
        arenaController.setArena(this);
        createTools();
        arenaController.setTextOnVillainLabel();
        arenaController.setTextOnHeroLabel();
    }

    public void initBtn() {

        heroBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onClickHero();
            }
        });
        villainBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onClickVillain();
            }
        });
    }

    public void onClickVillain() {

        arenaController.takeDamageVillain();
        turnLabel.setText("Villain turn");
        villainBtn.setEnabled(false);
        heroBtn.setEnabled(true);
        if (arenaController.isDied()) {
            showLoser();
            exitWindow();
        }
    }

    public void onClickHero() {

        Random random = new Random();
        if (random.nextInt(5) == 0)
            showMissAttack();
        else
            arenaController.takeDamageHero();

        turnLabel.setText("Your turn");
        heroBtn.setEnabled(false);
        villainBtn.setEnabled(true);

        if (arenaController.isDied()) {
            showLoser();
            System.exit(0);
        }

    }

    public void villainDie() {

        if (arenaController.isEndOfGame()) {
            showWinner();
            exitWindow();
        }

        arenaController.villainDie();
        showWinVillainView();
        closeWindow();
    }

    public void setTextOnVillainLabel(int level, int attack, int weapon, int defense,
                                      int armor, int helm, int health) {

        levelLabel2.setText(Integer.toString(level));
        attackLabel2.setText(Integer.toString(attack) +
                " + " + Integer.toString(weapon));
        defenseLabel2.setText(Integer.toString(defense) +
                " + " + Integer.toString(armor));
        weaponLabel2.setText(Integer.toString(weapon));
        armorLabel2.setText(Integer.toString(armor));
        helmLabel2.setText(Integer.toString(helm));
        healthLabel2.setText(Integer.toString(health));
    }

    public void setTextOnHeroLabel(String name, String heroClass, int level, int experience, int attack, int weapon,
                                   int defense, int armor, int health) {

        labelHeroName2.setText(name);
        labelHeroCass2.setText(heroClass);
        labelHeroLevel2.setText(Integer.toString(level));
        labelHeroExp2.setText(Integer.toString(experience));
        labelAttack2.setText(Integer.toString(attack) +
                " + " + Integer.toString(weapon));
        labelDefense2.setText(Integer.toString(defense) +
                " + " + Integer.toString(armor));
        labelHealth2.setText(Integer.toString(health));
        labelWeapon2.setText(Integer.toString(weapon));
        labelArmor2.setText(Integer.toString(armor));
    }

    private void createTools() {

        jf = new JFrame("Swingy Arena");
        jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        createHeroTools();
        createVillianTools();
        turnLabel = new JLabel("Your turn");
        turnLabel.setForeground(Color.RED);
        panelMain = new JPanel(new GridBagLayout());
        setHeroOnPanelHero();
        setVillainOnVillainPanel();
        setAllOnMainPanel();
        jf.setLayout(new FlowLayout());
        jf.add(panelMain);
        jf.setSize(580, 380);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        initBtn();
    }

    private void setAllOnMainPanel() {

        panelMain.add(labelYourHero, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(turnLabel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(labelVillain, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(heroBtn, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(villainBtn, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(panelHero, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelMain.add(panelVillain, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
    }

    private void setVillainOnVillainPanel() {

        panelVillain.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        panelVillain.add(levelLabel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelVillain.add(levelLabel2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelVillain.add(attackLabel1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelVillain.add(attackLabel2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelVillain.add(defenseLabel1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelVillain.add(defenseLabel2, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelVillain.add(artefactsLabel, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        artefactsLabel.setHorizontalAlignment(JLabel.CENTER);
        panelVillain.add(weaponLabel1, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelVillain.add(weaponLabel2, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelVillain.add(armorLabel1, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelVillain.add(armorLabel2, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelVillain.add(helmLabel1, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelVillain.add(helmLabel2, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelVillain.add(healthLabel1, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelVillain.add(healthLabel2, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
    }

    private void setHeroOnPanelHero() {

        panelHero.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        panelHero.add(labelYourHero, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.BOTH, new Insets(5,5,5,5),
                0,0));
        panelHero.add(labelHeroName1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelHero.add(labelHeroName2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelHero.add(labelHeroCass1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelHero.add(labelHeroCass2, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelHero.add(labelHeroLevel1, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelHero.add(labelHeroLevel2, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelHero.add(labelHeroExp1, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelHero.add(labelHeroExp2, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelHero.add(labelHealth1, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelHero.add(labelHealth2, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelHero.add(labelAttack1, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelHero.add(labelAttack2, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelHero.add(labelDefense1, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelHero.add(labelDefense2, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelHero.add(labelArtefacts, new GridBagConstraints(0, 8, 2, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelHero.add(labelWeapon1, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelHero.add(labelWeapon2, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelHero.add(labelArmor1, new GridBagConstraints(0, 10, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
        panelHero.add(labelArmor2, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5),
                0,0));
    }

    private void createHeroTools() {

        panelHero = new JPanel();
        heroBtn = new JButton("Hero");
        heroBtn.setEnabled(false);
        panelHero.setLayout(new GridBagLayout());
        labelYourHero = new JLabel("HERO");
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
    }

    private void createVillianTools() {

        panelVillain = new JPanel();
        panelVillain.setLayout(new GridBagLayout());
        villainBtn = new JButton("Villain");
        labelVillain = new JLabel("VILLAIN");
        labelVillain.setHorizontalAlignment(JLabel.CENTER);
        levelLabel1 = new JLabel("Villain level");
        levelLabel2 = new JLabel();
        attackLabel1 = new JLabel("Villain attack");
        attackLabel2 = new JLabel();
        defenseLabel1 = new JLabel("Villain defense");
        defenseLabel2 = new JLabel();
        artefactsLabel = new JLabel("Villain artefacts");
        weaponLabel1 = new JLabel("Points plus to attack");
        weaponLabel2 = new JLabel();
        armorLabel1 = new JLabel("Points plus to defense");
        armorLabel2 = new JLabel();
        helmLabel1 = new JLabel("Helm");
        helmLabel2 = new JLabel();
        healthLabel1 = new JLabel("Health");
        healthLabel2 = new JLabel();
    }

    public int showArtefacts() {

        Object[] options = {"weapon",
                "armor",
                "helm"};
        return JOptionPane.showOptionDialog(null,
                "select one of the three artifacts",
                "Dropped artefacts",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);
    }

    private void showLoser() {

        JOptionPane.showMessageDialog(null,
                "Good buy, LOSER!!!");
    }

    private void showWinner() {

        JOptionPane.showMessageDialog(null,
                "Perfectly!!!\nYou are winner");
    }

    private void showWinVillainView() {

        JOptionPane.showMessageDialog(null,
                "You win villain");
    }

    private void showMissAttack() {

        JOptionPane.showMessageDialog(null,
                "Something went wrong");
    }

    private void exitWindow() {

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WindowEvent windowEvent = new WindowEvent(jf, WindowEvent.WINDOW_CLOSING);
        jf.dispatchEvent(windowEvent);
    }

    private void closeWindow() {

        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        WindowEvent windowEvent = new WindowEvent(jf, WindowEvent.WINDOW_CLOSING);
        jf.dispatchEvent(windowEvent);
    }
}
