package com.game.swingy.view.console;

import com.game.swingy.controller.CreateHeroController;
import com.game.swingy.controller.StarterController;
import com.game.swingy.core.Map.GameValidator;
import com.game.swingy.core.Map.HeroClassEnum;
import com.game.swingy.view.CreateHero;
import com.game.swingy.view.StartView;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.*;
import java.util.Scanner;

public class CreateHeroConsoleView implements CreateHero{

    @NotBlank(message="ERROR: name must be not empty")
    @NotNull(message="ERROR: name must be not empty")
    @Size(max = 10, message="Max length of name is 10")
    private String name;

    @Pattern(regexp = "^\\d+$", message = "ERROR only digits allowed")
    @Min(value = 0, message = "ERROR You should choose one option 1, 2, 3 or 0")
    @Max(value = 3, message = "ERROR You should choose one option 1, 2, 3 or 0")
    private String heroClass;

    private CreateHeroController createHeroController;
    private Scanner sc;

    public CreateHeroConsoleView(CreateHeroController createHeroController) {

        this.createHeroController = createHeroController;
        sc = new Scanner(System.in);
        showWriteNameMessage();
        this.createHeroController.setNameHero(name);
        showChooseType();
        int heroClass = Integer.parseInt(this.heroClass) - 1;
        if (heroClass == -1) {
            StarterController starterController = new StarterController();
            new StartConsoleView(starterController);
        }
        else {
            HeroClassEnum classEnum = HeroClassEnum.values()[heroClass];
            this.createHeroController.setSelectedHeroClass(classEnum.toString());
            System.out.println("Select a previously created hero:\n");
            createNewHero();
        }

    }
    private void showWriteNameMessage() {

        System.out.println("Write a name of hero:\n");
        this.name = sc.nextLine();
        if (!GameValidator.getGameValidator().validate(this)) {
            showWriteNameMessage();
        }
    }

    private void showChooseType() {

        System.out.println("Chose type of hero:\n" +
                "1 - Samnite\n" +
                "2 - Skissor\n" +
                "3 - Peltasts\n\n" +
                "0 - back to start game");
        heroClass = sc.nextLine();
        if (!GameValidator.getGameValidator().validate(this)) {
            showChooseType();
        }
    }

    @Override
    public void createNewHero() {

        this.createHeroController.onClickCreate();
    }
}
