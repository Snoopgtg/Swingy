package com.game.swingy.view.console;

import com.game.swingy.controller.CreateHeroController;
import com.game.swingy.controller.StarterController;
import com.game.swingy.core.Map.HeroClassEnum;
import com.game.swingy.view.CreateHero;
import com.game.swingy.view.StartView;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class CreateHeroConsoleView implements CreateHero{

    private CreateHeroController createHeroController;

    public CreateHeroConsoleView(CreateHeroController createHeroController) {

        this.createHeroController = createHeroController;
        System.out.println("Write a name of hero:\n");
        Scanner sc = new Scanner(System.in); // object for scanner
        this.createHeroController.setNameHero(sc.next());
        //TODO check input
        System.out.println("Chose type of hero:\n" +
                "1 - Samnite\n" +
                "2 - Skissor\n" +
                "3 - Peltasts\n\n" +
                "0 - back to start game");
        //TODO перевірка вводу
        int heroClass = sc.nextInt();
        if (heroClass == 0) {
            StarterController starterController =
                    new StarterController();
            StartView startView = new StartConsoleView(starterController);
        }
        else {
            HeroClassEnum classEnum = HeroClassEnum.values()[heroClass];
            this.createHeroController.setSelectedHeroClass(classEnum.toString());
            System.out.println("Select a previously created hero:\n");
            createNewHero();
        }
    }

    @Override
    public void createNewHero() {

        this.createHeroController.onClickCreate();
    }
}
