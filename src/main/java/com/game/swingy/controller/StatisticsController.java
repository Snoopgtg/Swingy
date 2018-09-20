package com.game.swingy.controller;

import com.game.swingy.core.Unit.Hero.Hero;
import com.game.swingy.core.Map.Map;
import com.game.swingy.view.gui.HeroStatisticsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticsController {

    private HeroStatisticsView heroStatisticsView;

    public StatisticsController() {

        heroStatisticsView = new HeroStatisticsView();
        initBtnOk();
    }

    public void setHeroFields() {

        Hero hero = (Hero)Map.getMap().getObservers().get(0);
        String name = hero.getName();
        String heroClass = hero.getHeroClass();
        int level = hero.getLevel();
        int experience = hero.getExperience();
        int attack = hero.getAttack();
        int defense = hero.getDefense();
        int hitPoints = hero.getHitPoints();
        int weapon = hero.getArtefacts().getWeapon();
        int armor = hero.getArtefacts().getArmor();

        heroStatisticsView.getLabelHeroName2().setText(name);
        heroStatisticsView.getLabelHeroCass2().setText(heroClass);
        heroStatisticsView.getLabelHeroLevel2().setText(Integer.toString(level));
        heroStatisticsView.getLabelHeroExp2().setText(Integer.toString(experience));
        heroStatisticsView.getLabelAttack2().setText(
                Integer.toString(attack) + " + " + Integer.toString(weapon)
        );
        heroStatisticsView.getLabelDefense2().setText(
                Integer.toString(defense) + " + " + Integer.toString(armor)
        );
        heroStatisticsView.getLabelHealth2().setText(Integer.toString(hitPoints));
        heroStatisticsView.getLabelWeapon2().setText(Integer.toString(weapon));
        heroStatisticsView.getLabelArmor2().setText(Integer.toString(armor));
    }

    private void initBtnOk() {

        heroStatisticsView.getBtnOk().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                heroStatisticsView.getJf().dispose();
            }
        });
    }
}
