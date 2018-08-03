package com.game.swingy.view.console;

import com.game.swingy.controller.StarterController;
import com.game.swingy.view.StartView;

import java.util.Scanner;

public class StartConsoleView implements StartView {

    private StarterController starterController;

    public StartConsoleView(StarterController starterController) {

        this.starterController = starterController;
        System.out.println("Console game started\n" +
                "Select one option:\n" +
                "1 - Create a new hero\n" +
                "2 - Select a previously created hero\n\n" +
                "0 - exit");

        Scanner sc = new Scanner(System.in); // object for scanner
        //TODO validate
        int choose = sc.nextInt(); // similiarli Float,Double can be added to it as per the data type.
        if (choose == 1)
            createHero();
        else if (choose == 2)
            previouslyHeroes();
        else
            System.exit(0);
    }

    @Override
    public void createHero() {
        starterController.onClickCreateHero();
    }

    @Override
    public void previouslyHeroes() {
        starterController.onClickPreviouslyHero();
    }
}
