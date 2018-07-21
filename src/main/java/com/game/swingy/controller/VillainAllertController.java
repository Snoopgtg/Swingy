package com.game.swingy.controller;

import com.game.swingy.core.Unit.Unit;
import com.game.swingy.view.gui.VillainAllertGuiView;

public class VillainAllertController {

    private MapController mapController;
    private Unit villain;

    public VillainAllertController(Unit villian, MapController mapController) {

        this.villain = villian;
        this.mapController = mapController;
    }

    /*private void initBtn(Unit villian) {

        villainAllertGuiView.getRunBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onClickRun();
            }
        });
        villainAllertGuiView.getFightBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onClickFight();
            }
        });
    }*/

   /* private void onClickRun() {

        if (villainAllertGuiView.showRunAllert() == 0)
            onClickRunYes();
        else
            System.out.println("no");
    }*/

    /*private void onClickRunYes() {

        Random random = new Random();
        if (random.nextBoolean()) {
            System.out.println("true");
            villainAllertGuiView.showDisLucky();
            villainAllertGuiView.closeWindow();
            ArenaController arenaController = new ArenaController(this.villain,
                    this.mapController);

        }
        else {
            System.out.println("false");
            villainAllertGuiView.showLucky();
            villainAllertGuiView.closeWindow();
            mapController.getMapViewFrame().setVisible(true); //TODO закоментував щоб запустилося
        }
    }*/

    public void onClickRunYes() {

        //TODO if Map.getMap.Mode
        ArenaController arenaController = new ArenaController(this.villain,
                this.mapController);

    }

    /*private void onClickFight() {

        System.out.println("Fight");
        if (villainAllertGuiView.showFightAllert() == 0) {
            System.out.println("yes on click fight");
            ArenaController arenaController = new ArenaController(this.villain,
                    this.mapController);
            villainAllertGuiView.closeWindow();
        }
    }*/

    /*private void setTextOnBtnLabel(Unit villain) {

        int level = villain.getLevel();
        int attack = villain.getAttack();
        int defense = villain.getDefense();
        int weapon = villain.getArtefacts().getWeapon();
        int armor = villain.getArtefacts().getArmor();
        int health = villain.getHitPoints();

        villainAllertGuiView.getLevellabel2().setText(Integer.toString(level));
        villainAllertGuiView.getAttackLabel2().setText(Integer.toString(attack) +
                " + " + Integer.toString(weapon));
        villainAllertGuiView.getDefenseLabel2().setText(Integer.toString(defense) +
                " + " + Integer.toString(armor));
        villainAllertGuiView.getWeaponLabel2().setText(Integer.toString(weapon));
        villainAllertGuiView.getArmorLabel2().setText(Integer.toString(armor));
        villainAllertGuiView.getHealthLabel2().setText(Integer.toString(health));

    }*/

    public void getTextOnBtnLabel(Unit villain, VillainAllertGuiView villainAllertGuiView) {

        int level = villain.getLevel();
        int attack = villain.getAttack();
        int defense = villain.getDefense();
        int weapon = villain.getArtefacts().getWeapon();
        int armor = villain.getArtefacts().getArmor();
        int health = villain.getHitPoints();

        villainAllertGuiView.setTextOnBtnLabel(level, attack, defense,
                weapon, armor, health);

    }

    public MapController getMapController() {
        return mapController;
    }

    public Unit getVillain() {
        return villain;
    }
}
