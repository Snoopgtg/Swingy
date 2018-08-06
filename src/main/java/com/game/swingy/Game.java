package com.game.swingy;

import com.game.swingy.controller.StarterController;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Size;
import com.game.swingy.core.Map.Map;
import com.game.swingy.core.Map.ModeEnum;
import com.game.swingy.view.StartView;
import com.game.swingy.view.console.StartConsoleView;
import com.game.swingy.view.gui.StartGuiView;

import java.util.Set;

public class Game {

    /*@Size(min = 1, max = 1, message = "Usage: [console | gui]")
    private String[] gameArgs;

    private void setArgs(String[] args) {
        this.gameArgs = args;
    }*/

    public static void main(String[] args) {


        if (args.length == 0 || args.length > 1) {
            System.out.println("Usage: [console | gui]");
            System.exit(2);
        }

        StarterController starterController = new StarterController();
        StartView startView;
        switch (args[0]) {
            case ("console"):
                Map.getMap().setMode(ModeEnum.CONSOLE);
                startView = new StartConsoleView(starterController);
                break;
            case ("gui") :
                Map.getMap().setMode(ModeEnum.GUI);
                startView = new StartGuiView(starterController);
                break;
            default:
                System.out.println("Usage: [console | gui]");
                System.exit(2);
        }
    }


    //starterController.initGame();
}