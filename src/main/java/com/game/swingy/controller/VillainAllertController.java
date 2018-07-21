package com.game.swingy.controller;

import com.game.swingy.core.Unit.Unit;
import com.game.swingy.view.VillainAllert;

public class VillainAllertController {

    private MapController mapController;
    private Unit villain;

    public VillainAllertController(Unit villian, MapController mapController) {

        this.villain = villian;
        this.mapController = mapController;
    }

    public void onClickRunYes() {

        //TODO if Map.getMap.Mode
        ArenaController arenaController = new ArenaController(this.villain,
                this.mapController);

    }

    public void getTextOnBtnLabel(Unit villain, VillainAllert villainAllert) {

        int level = villain.getLevel();
        int attack = villain.getAttack();
        int defense = villain.getDefense();
        int weapon = villain.getArtefacts().getWeapon();
        int armor = villain.getArtefacts().getArmor();
        int health = villain.getHitPoints();

        villainAllert.setTextOnBtnLabel(level, attack, defense,
                weapon, armor, health);

    }

    public MapController getMapController() {
        return mapController;
    }

    public Unit getVillain() {
        return villain;
    }
}
