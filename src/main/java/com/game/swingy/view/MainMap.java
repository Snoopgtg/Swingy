package com.game.swingy.view;

public interface MainMap {
    void initMoveHero();
    void initCloseLisener();
    void setHeroIcon(int x, int y);
    void setVilliansIcon(int x, int y);
    void setEmptyIcon(int x, int y);
    void showMissionCompletedView();
    void onClickVillainsButton(int x, int y);
    void onClickEmptyButton(int x, int y);
    void changeHeroPosition(int toX, int toY);


}