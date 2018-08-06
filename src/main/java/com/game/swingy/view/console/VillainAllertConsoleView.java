package com.game.swingy.view.console;

import com.game.swingy.controller.VillainAllertController;
import com.game.swingy.core.Map.GameValidator;
import com.game.swingy.view.VillainAllert;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Random;
import java.util.Scanner;

public class VillainAllertConsoleView implements VillainAllert{

    @Pattern(regexp = "^\\d+$", message = "ERROR only digits allowed")
    @Min(value = 1, message = "ERROR You should choose one option 1 or 2")
    @Max(value = 2, message = "ERROR You should choose one option 1 or 2")
    private String yesNo;

    private Scanner sc;
    private VillainAllertController allertController;

    public VillainAllertConsoleView(VillainAllertController allertController) {

        this.allertController = allertController;
        this.sc = new Scanner(System.in);
        allertController.getTextOnBtnLabel(this);
        initBtn();
    }

    private void showYesNo() {

        System.out.println("1 - Fight\n" +
                           "2 - Run");
        Scanner sc = new Scanner(System.in);
        yesNo = sc.next();
        if (!GameValidator.getGameValidator().validate(this))
            showYesNo();
    }

    @Override
    public void initBtn() {

        showYesNo();
        int choose = Integer.parseInt(this.yesNo);
        if (choose == 1)
            showFightAllert();
        else
            showRunAllert();

    }

    @Override
    public void onClickRunYes() {

        Random random = new Random();
        if (random.nextBoolean()) {
            System.out.println("That's you don't lucky");
            allertController.onClickRunYes();
      }
        else {
            System.out.println("That's you lucky");
            allertController.getMapController().getMapJframe().setVisible(true);
        }
    }

    private void showRunAllertChoose() {

        System.out.println("You have 50% chance of returning to the previous position. " +
                "If the odds aren’t on your side, you must fight the " +
                "villian\nAre you sure that you want to run?\n" +
                "1 - yes\n" +
                "2 - no");
        yesNo = sc.next();
        if (!GameValidator.getGameValidator().validate(this))
            showRunAllertChoose();
    }

    @Override
    public void showRunAllert() {

        showRunAllertChoose();

        int choose = Integer.parseInt(this.yesNo);
        if (choose == 1)
            onClickRunYes();
        else
            initBtn();


    }

    private void showFightAllertChoose() {
        System.out.println("Are you sure that you want fight with villain?\n" +
                "1 - yes\n" +
                "2 - no\n");
        yesNo = sc.next();
        if (!GameValidator.getGameValidator().validate(this))
            showFightAllertChoose();
    }

    @Override
    public void showFightAllert() {

        showFightAllertChoose();
        int choose = Integer.parseInt(yesNo);
        if (choose == 1) {
            allertController.onClickRunYes();
        }
        else
            initBtn();
    }

    @Override
    public void setTextOnBtnLabel(int level, int attack, int weapon, int defense, int armor, int health) {
        System.out.format("Villain level ---> %s\n", level);
        System.out.format("Villain attack ---> %s + %s\n", attack, weapon);
        System.out.format("Villain defense ---> %s + %s\n", defense, armor);
        System.out.format("Points plus to attack ---> %s\n", weapon);
        System.out.format("Points plus to defense ---> %s\n", armor);
        System.out.format("Health ---> %s\n", health);
    }
}
