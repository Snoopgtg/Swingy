package com.game.swingy.controller;

import com.game.swingy.core.Map.Map;
import com.game.swingy.core.Map.ModeEnum;
import com.game.swingy.core.Unit.Unit;
import com.game.swingy.view.Arena;
import com.game.swingy.view.VillainAllert;
import com.game.swingy.view.console.ArenaConsoleView;
import com.game.swingy.view.gui.ArenaGuiView;

public class VillainAllertController {

    private MapController mapController;
    private Unit villain;

    public VillainAllertController(Unit villian, MapController mapController) {

        this.villain = villian;
        this.mapController = mapController;
    }

    public void onClickRunYes() {

        ArenaController arenaController = new ArenaController(this.villain,
                this.mapController);
        Arena arena;
        if (Map.getMap().getMode() == ModeEnum.CONSOLE)
            arena = new ArenaConsoleView(arenaController);

        else
            arena = new ArenaGuiView(arenaController);

    }

    public void getTextOnBtnLabel(VillainAllert villainAllert) {

        int level = villain.getLevel();
        int attack = villain.getAttack();
        int defense = villain.getDefense();
        int weapon = villain.getArtefacts().getWeapon();
        int armor = villain.getArtefacts().getArmor();
        int health = villain.getHitPoints();

        villainAllert.setTextOnBtnLabel(level, attack, weapon,
                defense, armor, health);

    }

    public MapController getMapController() {
        return mapController;
    }

    public Unit getVillain() {
        return villain;
    }
}
