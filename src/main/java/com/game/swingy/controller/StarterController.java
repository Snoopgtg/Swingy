package com.game.swingy.controller;

import com.game.swingy.core.Map.Map;
import com.game.swingy.core.Map.ModeEnum;
import com.game.swingy.view.PreviousHero;
import com.game.swingy.view.StartView;
import com.game.swingy.view.console.CreateHeroConsoleView;
import com.game.swingy.view.console.PreviousHeroConsoleView;
import com.game.swingy.view.console.StartConsoleView;
import com.game.swingy.view.gui.CreateHeroGuiView;
import com.game.swingy.view.gui.PreviousHeroGuiView;

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

        PreviousHeroController previousHeroController = new
                PreviousHeroController();
        PreviousHero previousHero;
        if (Map.getMap().getDbMySQL().isEmptyHeroTable()) {
            if (Map.getMap().getMode() == ModeEnum.CONSOLE)
                previousHero = new PreviousHeroConsoleView(previousHeroController);
            else
                previousHero = new PreviousHeroGuiView(previousHeroController);
        }
        else {
            if (Map.getMap().getMode() == ModeEnum.CONSOLE) {
                System.out.println("At this time, you don't have saving hero");
                StartView startView = new StartConsoleView(this);
            }
            else
                JOptionPane.showMessageDialog(null,
                    "At this time, you don't have saving hero");
        }
        //bc.setVisible(true);
        //bc.createAndShowGUI();
    }

    //TODO close starterView
}
