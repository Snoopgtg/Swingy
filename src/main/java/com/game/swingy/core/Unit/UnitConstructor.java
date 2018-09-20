package com.game.swingy.core.Unit;

import com.game.swingy.core.Map.UnitTypeFieldEnum;

public class UnitConstructor {

    public void constructSamnite(Builder builder, String name) {

        builder.setKindOfUnit(UnitTypeFieldEnum.HERO);
        builder.setName(name);
        builder.setHeroClass("Samnite");
        builder.setLevel(0);
        builder.setExperience(0);
        builder.setAttack(25);
        builder.setDefense(18);
        builder.setHitPoints(100);
        Artefacts artefacts = new Artefacts(5,5,2);
        builder.setCoordinates(new Coordinates(2,2));
        builder.setArtefacts(artefacts);

    }

    public void constructSkissor(Builder builder, String name) {

        builder.setKindOfUnit(UnitTypeFieldEnum.HERO);
        builder.setName(name);
        builder.setHeroClass("Skissor");
        builder.setLevel(0);
        builder.setExperience(0);
        builder.setAttack(20);
        builder.setDefense(10);
        builder.setHitPoints(100);
        Artefacts artefacts = new Artefacts(2,5,5);
        builder.setCoordinates(new Coordinates(2,2));
        builder.setArtefacts(artefacts);

    }

    public void constructPeltasts(Builder builder, String name) {

        builder.setKindOfUnit(UnitTypeFieldEnum.HERO);
        builder.setName(name);
        builder.setHeroClass("Peltasts");
        builder.setLevel(0);
        builder.setExperience(0);
        builder.setAttack(10);
        builder.setDefense(20);
        builder.setHitPoints(100);
        Artefacts artefacts = new Artefacts(5,2,5);
        builder.setCoordinates(new Coordinates(2,2));
        builder.setArtefacts(artefacts);
    }

    public void constructVillain(Builder builder, String name, int level) {

        builder.setKindOfUnit(UnitTypeFieldEnum.ENEMY);
        builder.setName(name);
        builder.setHeroClass("Villain");
        builder.setLevel(level);
        builder.setAttack(level * 10 + 17);
        builder.setDefense(level * 8 + 5);
        builder.setHitPoints(0/*level * 20 + 30*/);
        Artefacts artefacts = new Artefacts(level * 15 + 10, level * 12 + 5, level * 7 + 7);
        builder.setCoordinates(new Coordinates(-1, -1));
        builder.setArtefacts(artefacts);

    }
}
