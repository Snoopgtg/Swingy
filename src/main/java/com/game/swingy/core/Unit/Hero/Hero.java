package com.game.swingy.core.Unit.Hero;

import com.game.swingy.core.Map.UnitTypeFieldEnum;
import com.game.swingy.core.Unit.Artefacts;
import com.game.swingy.core.Unit.Coordinates;
import com.game.swingy.core.Unit.Unit;

import java.util.Collections;

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

         /*int nextExpa = (int) (this.level * 1000 + Math.pow((this.level - 1), 2) * 450);
         if (experience >= nextExpa) {*/
             this.level += 6/*1*/;
         //}
    }

    public void setTwoCoodinatesXY() {

        int mapSize = (level - 1) * 5 + 10 - (level % 2);
        Coordinates coordinates = new Coordinates();
        coordinates.setX(mapSize / 2);
        coordinates.setY(mapSize / 2);
        this.coordinates = coordinates;
    }

    public boolean isEndOfGame() {

        return level == 5;
    }

    public void experienceUp(int experience) {

        this.experience += experience;
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
