package com.game.swingy.controller;

import com.game.swingy.core.Unit.Unit;
import com.game.swingy.view.gui.VillianAllertView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class VillianAllertController {

    private VillianAllertView villianAllertView;
    private MapController mapController;
    private Unit villain;

    public VillianAllertController(Unit villian, MapController mapController) {

        this.villain = villian;
        this.mapController = mapController;
        villianAllertView = new VillianAllertView();
        setTextOnBtnLabel(villian);
        initBtn(villian);
    }

    private void initBtn(Unit villian) {

        villianAllertView.getRunBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onClickRun();
            }
        });
        villianAllertView.getFightBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onClickFight();
            }
        });
    }

    private void onClickRun() {

        if (villianAllertView.showRunAllert() == 0)
            onClickRunYes();
        else
            System.out.println("no");
    }

    private void onClickRunYes() {

        Random random = new Random();
        if (random.nextBoolean()) {
            System.out.println("true");
            villianAllertView.showDisLucky();
            villianAllertView.closeWindow();
            ArenaController arenaController = new ArenaController(this.villain,
                    this.mapController);

        }
        else {
            System.out.println("false");
            villianAllertView.showLucky();
            villianAllertView.closeWindow();
            //mapController.getMapViewFrame().setVisible(true); //TODO закоментував щоб запустилося
        }
    }

    private void onClickFight() {

        System.out.println("Fight");
        if (villianAllertView.showFightAllert() == 0) {
            System.out.println("yes on click fight");
            ArenaController arenaController = new ArenaController(this.villain,
                    this.mapController);
            villianAllertView.closeWindow();
        }
    }

    private void setTextOnBtnLabel(Unit villain) {

        int level = villain.getLevel();
        int attack = villain.getAttack();
        int defense = villain.getDefense();
        int weapon = villain.getArtefacts().getWeapon();
        int armor = villain.getArtefacts().getArmor();
        int health = villain.getHitPoints();

        villianAllertView.getLevellabel2().setText(Integer.toString(level));
        villianAllertView.getAttackLabel2().setText(Integer.toString(attack) +
                " + " + Integer.toString(weapon));
        villianAllertView.getDefenseLabel2().setText(Integer.toString(defense) +
                " + " + Integer.toString(armor));
        villianAllertView.getWeaponLabel2().setText(Integer.toString(weapon));
        villianAllertView.getArmorLabel2().setText(Integer.toString(armor));
        villianAllertView.getHealthLabel2().setText(Integer.toString(health));

    }
}
