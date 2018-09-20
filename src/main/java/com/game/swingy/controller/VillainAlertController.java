package com.game.swingy.controller;

import com.game.swingy.core.Map.Map;
import com.game.swingy.core.Map.ModeEnum;
import com.game.swingy.core.Unit.Unit;
import com.game.swingy.view.VillainAlert;
import com.game.swingy.view.console.ArenaConsoleView;
import com.game.swingy.view.gui.ArenaGuiView;

public class VillainAlertController {

    private MapController mapController;
    private Unit villain;

    public VillainAlertController(Unit villain, MapController mapController) {

        this.villain = villain;
        this.mapController = mapController;
    }

    public void onClickRunYes() {

        ArenaController arenaController = new ArenaController(this.villain, this.mapController);
        if (Map.getMap().getMode() == ModeEnum.CONSOLE)
            new ArenaConsoleView(arenaController);
        else
            new ArenaGuiView(arenaController);

    }

    public void getTextOnBtnLabel(VillainAlert villainAlert) {

        int level = villain.getLevel();
        int attack = villain.getAttack();
        int defense = villain.getDefense();
        int weapon = villain.getArtefacts().getWeapon();
        int armor = villain.getArtefacts().getArmor();
        int health = villain.getHitPoints();

        villainAlert.setTextOnBtnLabel(level, attack, weapon,
                defense, armor, health);

    }

    public MapController getMapController() {
        return mapController;
    }

    public Unit getVillain() {
        return villain;
    }
}
