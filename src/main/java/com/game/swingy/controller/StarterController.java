package com.game.swingy.controller;

import com.game.swingy.core.Map.Map;
import com.game.swingy.core.Map.ModeEnum;
import com.game.swingy.view.console.CreateHeroConsoleView;
import com.game.swingy.view.console.PreviousHeroConsoleView;
import com.game.swingy.view.console.StartConsoleView;
import com.game.swingy.view.gui.CreateHeroGuiView;
import com.game.swingy.view.gui.PreviousHeroGuiView;

import javax.swing.*;

public class StarterController {

    private JFrame startFrame;
    public StarterController() {

    }

    public void onClickCreateHero() {
        CreateHeroController createHeroController = new CreateHeroController();

        if (Map.getMap().getMode() == ModeEnum.CONSOLE) {
            new CreateHeroConsoleView(createHeroController);
        }
        else {
            new CreateHeroGuiView(createHeroController);
            Map.getMap().setStartFrame(this.startFrame);
            if (startFrame != null)
                this.startFrame.setVisible(false);
        }
    }

    public void onClickPreviouslyHero(){

        PreviousHeroController previousHeroController = new
                PreviousHeroController();
        if (Map.getMap().getDbMySQL().isEmptyHeroTable()) {
            if (Map.getMap().getMode() == ModeEnum.CONSOLE)
                new PreviousHeroConsoleView(previousHeroController);
            else {
                new PreviousHeroGuiView(previousHeroController);
                Map.getMap().setStartFrame(this.startFrame);
                this.startFrame.setVisible(false);
            }
        }
        else {
            if (Map.getMap().getMode() == ModeEnum.CONSOLE) {
                System.out.println("At this time, you don't have saving hero");
                new StartConsoleView(this);
            }
            else
                JOptionPane.showMessageDialog(null,
                    "At this time, you don't have saving hero");
        }
    }

    public void setStartFrame(JFrame startFrame) {
        this.startFrame = startFrame;
    }
}
