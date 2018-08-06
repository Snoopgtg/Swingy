package com.game.swingy.core.Unit.Hero;

import com.game.swingy.core.Map.UnitTypeFieldEnum;
import com.game.swingy.core.Unit.Artefacts;
import com.game.swingy.core.Unit.Coordinates;
import com.game.swingy.core.Unit.Unit;

public class Hero extends Unit {

    private int experience;

    public Hero(UnitTypeFieldEnum kindOfUnit,
                   String name,
                   String heroClass,
                   int level,
                   int experience,
                   int attack,
                   int defense,
                   int hitPoints,
                   Artefacts artefacts,
                   Coordinates coordinates) {
        super(kindOfUnit, name, heroClass, level, attack,
                defense, hitPoints, artefacts, coordinates);
        this.experience = experience;
    }

    public void levelUp() {

         int nextExpa = (this.level + 1) * 1000 + this.level ^ 2 * 450;
         if (experience >= nextExpa) {
             this.level += 1;
         }

        /*if (experience >= 1000 && experience < 2450)
            this.level = 1;
        else if (experience >= 2450 && experience < 4800)
            this.level = 2;
        else if (experience >= 4800 && experience < 8050)
            this.level = 3;
        else if (experience >= 8050 && experience < 12200)
            this.level = 4;
        else if (experience >= 12200)
            this.level = 5;*/
    }

    public boolean isEndOfGame() {

        //TODO Enum endOfGame
        if (level == 5)
            return true;
        return false;
    }

    public void experienceUp(int experience) {

        this.experience += experience;
    }

    public UnitTypeFieldEnum getKindOfUnit() {
        return kindOfUnit;
    }

    public void setKindOfUnit(UnitTypeFieldEnum kindOfUnit) {
        this.kindOfUnit = kindOfUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Coordinates getCoordinates() {
        return super.getCoordinates();
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
