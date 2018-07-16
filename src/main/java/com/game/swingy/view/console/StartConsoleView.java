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
                "2 - Select a previously created hero\n");

        Scanner sc = new Scanner(System.in); // object for scanner
        int no = sc.nextInt(); // similiarli Float,Double can be added to it as per the data type.
        if (no == 1)
            createHero();
        else if (no == 2)
            previouslyHeroes();
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
