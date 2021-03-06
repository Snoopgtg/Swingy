package com.game.swingy;

import com.game.swingy.controller.StarterController;
import com.game.swingy.core.Map.Map;
import com.game.swingy.core.Map.ModeEnum;
import com.game.swingy.view.console.StartConsoleView;
import com.game.swingy.view.gui.StartGuiView;

public class Game {

    public static void main(String[] args) {

        if (args.length == 0 || args.length > 1) {
            System.out.println("Usage: [console | gui]");
            System.exit(2);
        }

        StarterController starterController = new StarterController();

        switch (args[0]) {
            case ("console"):
                Map.getMap().setMode(ModeEnum.CONSOLE);
                new StartConsoleView(starterController);
                break;
            case ("gui") :
                Map.getMap().setMode(ModeEnum.GUI);
                new StartGuiView(starterController);
                break;
            default:
                System.out.println("Usage: [console | gui]");
                System.exit(2);
        }
    }
}