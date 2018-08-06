package com.game.swingy.view;

public interface Arena {

    void initBtn();
    void onClickVillain();
    void setTextOnVillainLable(int level, int attack, int weapon, int defense,
                               int armor, int helm, int health);
    void setTextOnHeroLabel(String name, String heroClass, int level, int experience, int attack, int weapon,
                            int defense, int armor, int health);
    void villainDie();
    int showArtefacts();
    void onClickHero();
}
