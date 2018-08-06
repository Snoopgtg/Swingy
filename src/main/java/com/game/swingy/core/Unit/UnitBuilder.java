package com.game.swingy.core.Unit;

import com.game.swingy.core.Unit.Hero.Hero;
import com.game.swingy.core.Map.UnitTypeFieldEnum;
import com.game.swingy.core.Unit.Villain.Villain;

public class UnitBuilder implements Builder {

    private UnitTypeFieldEnum kindOfUnit;
    private String name;
    private String heroClass;
    private int level;
    private int experience;
    private int attack;
    private int defense;
    private int hitPoints;
    private Artefacts artefacts;
    private Coordinates coordinates;

    @Override
    public void setKindOfUnit(UnitTypeFieldEnum kindOfUnit) {
        this.kindOfUnit = kindOfUnit;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public void setArtefacts(Artefacts artefacts) {
        this.artefacts = artefacts;
    }

    @Override
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Hero createHero() {
        return new Hero(kindOfUnit, name, heroClass, level, experience, attack, defense, hitPoints, artefacts, coordinates);

    }

    public Villain createVillian() {
        return new Villain(kindOfUnit, name, heroClass, level, attack, defense, hitPoints, artefacts, coordinates);

    }
}
