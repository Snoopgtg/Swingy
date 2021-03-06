package com.game.swingy.controller;

import com.game.swingy.core.Map.Map;
import com.game.swingy.core.Map.ModeEnum;
import com.game.swingy.core.Unit.Artefacts;
import com.game.swingy.core.Unit.Hero.Hero;
import com.game.swingy.core.Unit.Unit;
import com.game.swingy.view.Arena;

import java.util.Random;

public class ArenaController {

    private int villainHealth;
    private Arena arena;
    private MapController mapController;
    private Unit villain;
    private Hero hero;

    public ArenaController(Unit villain, MapController mapController) {

        this.villain = villain;
        this.mapController = mapController;
        this.hero = (Hero)Map.getMap().getObservers().get(0);
        this.villainHealth = this.villain.getHitPoints();

    }

    public void takeDamageHero() {

        int attack = villain.getAttack() + villain.getArtefacts().getWeapon();
        hero.takeDamage(attack);
        setTextOnHeroLabel();

    }

    public void takeDamageVillain() {
        int attack = hero.getAttack() + hero.getArtefacts().getWeapon();

        villain.takeDamage(attack);

        if (villain.isDied()) {
            arena.villainDie();
        } else {
            if (Map.getMap().getMode() == ModeEnum.CONSOLE) {
                setTextOnVillainLabel();
                setTextOnHeroLabel();
                arena.initBtn();
            }
            else {
                setTextOnVillainLabel();
            }
        }

    }


    public boolean isEndOfGame() {

        return hero.isWinLevel();
    }

    public void villainDie() {

        Random random = new Random();
        hero.experienceUp(this.villainHealth);
        hero.levelUp();
        if (random.nextInt(2) == 0)
            setArtefacts();
        mapController.heroKilledVillain(this.villain);

    }

    private void setArtefacts() {

        Artefacts artefacts;
        switch (arena.showArtefacts()) {
            case 0:
                artefacts = new Artefacts(villain.getArtefacts().getWeapon(),
                        hero.getArtefacts().getArmor(), hero.getArtefacts().getHelm());
                hero.setArtefacts(artefacts);
                break;
            case 1:
                artefacts = new Artefacts(hero.getArtefacts().getWeapon(),
                        villain.getArtefacts().getArmor(), hero.getArtefacts().getHelm());
                hero.setArtefacts(artefacts);
                break;
            case 2:
                artefacts = new Artefacts(hero.getArtefacts().getWeapon(),
                        hero.getArtefacts().getArmor(), villain.getArtefacts().getHelm());
                hero.setHitPoints(hero.getHitPoints() + villain.getArtefacts().getHelm());
                hero.setArtefacts(artefacts);
                break;
            default:
                break;
        }
    }

    public boolean isDied() {
        return hero.isDied();
    }

    public void setTextOnVillainLabel() {

        int level = villain.getLevel();
        int attack = villain.getAttack();
        int defense = villain.getDefense();
        int weapon = villain.getArtefacts().getWeapon();
        int armor = villain.getArtefacts().getArmor();
        int helm = villain.getArtefacts().getHelm();
        int health = villain.getHitPoints();

        arena.setTextOnVillainLabel(level, attack, weapon,
                defense, armor, helm, health);

    }

    public void setTextOnHeroLabel() {

        String name = hero.getName();
        String heroClass = hero.getHeroClass();
        int level = hero.getLevel();
        int experience = hero.getExperience();
        int attack = hero.getAttack();
        int defense = hero.getDefense();
        int health = hero.getHitPoints();
        int weapon = hero.getArtefacts().getWeapon();
        int armor = hero.getArtefacts().getArmor();

        arena.setTextOnHeroLabel(name, heroClass, level, experience, attack, weapon,
                defense, armor, health);

    }

    public Unit getVillain() {
        return villain;
    }

    public void setVillain(Unit villain) {
        this.villain = villain;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }
}
