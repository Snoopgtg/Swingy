package com.game.swingy.view;

import com.game.swingy.core.Unit.Unit;

public interface VillainAllert {

    void initBtn();
    void onClickRunYes();
    void showRunAllert();
    void showFightAllert();
    void setTextOnBtnLabel(int level, int attack, int weapon, int defense,
                           int armor, int health);
}