package com.game.swingy.view;

public interface VillainAlert {

    void initBtn();
    void onClickRunYes();
    void showRunAlert();
    void showFightAlert();
    void setTextOnBtnLabel(int level, int attack, int weapon, int defense,
                           int armor, int health);
}
