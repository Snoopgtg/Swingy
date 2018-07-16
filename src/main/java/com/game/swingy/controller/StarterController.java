package com.game.swingy.controller;

import com.game.swingy.core.Map.Map;
import com.game.swingy.core.Map.ModeEnum;
import com.game.swingy.view.console.CreateHeroConsoleView;
import com.game.swingy.view.gui.CreateHeroGuiView;

import javax.swing.*;

public class StarterController {

    public StarterController() {

    }

    public void onClickCreateHero() {
        CreateHeroController createHeroController = new CreateHeroController();

        System.out.println("Отработало создание контроллера");
        if (Map.getMap().getMode() == ModeEnum.CONSOLE) {
            CreateHeroConsoleView createHeroConsoleView =
                    new CreateHeroConsoleView(createHeroController);
        }
        else {
            CreateHeroGuiView createHeroGuiView =
                    new CreateHeroGuiView(createHeroController);
        }

    }

    public void onClickPreviouslyHero(){

        System.out.println("Отработало предвудущие герои");
        if (Map.getMap().getDbMySQL().isEmptyHeroTable()) {
            PreviousHeroController previousHeroController = new
                    PreviousHeroController();
        }
        else {
            JOptionPane.showMessageDialog(null,
                    "At this time, you don't have saving hero");
        }
        //bc.setVisible(true);
        //bc.createAndShowGUI();
    }

    //TODO close starterView
}
