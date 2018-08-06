package com.game.swingy.view.console;

import com.game.swingy.controller.StarterController;
import com.game.swingy.core.Map.GameValidator;
import com.game.swingy.view.StartView;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Scanner;

public class StartConsoleView implements StartView {

    @Min(value = 0, message = "ERROR You should choose one option 1,2 or 0")
    @Max(value = 2, message = "ERROR You should choose one option 1,2 or 0")
    @Pattern(regexp = "^\\d+$", message = "ERROR only digits allowed")
    private String  choose;

    private StarterController starterController;

    public StartConsoleView(StarterController starterController) {

        this.starterController = starterController;
        System.out.println("Console game started");
        this.showSelector();

    }

    private void showSelector() {

        System.out.println("Select one option:\n" +
                "1 - Create a new hero\n" +
                "2 - Select a previously created hero\n\n" +
                "0 - exit");

        Scanner sc = new Scanner(System.in); // object for scanner

        this.choose = sc.next();
        //TODO розібратися як закрити сканер щоб не сканував все що ввели

        if (GameValidator.getGameValidator().validate(this)) {
            int choose = Integer.parseInt(this.choose);
            if (choose == 1)
                createHero();
            else if (choose == 2)
                previouslyHeroes();
            else if (choose == 0)
                System.exit(0);
        } else {
            showSelector();
        }

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
