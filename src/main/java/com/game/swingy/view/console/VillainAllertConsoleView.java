package com.game.swingy.view.console;

import com.game.swingy.controller.VillainAllertController;
import com.game.swingy.view.VillainAllert;

import java.util.Random;
import java.util.Scanner;

public class VillainAllertConsoleView implements VillainAllert{

    private VillainAllertController allertController;

    public VillainAllertConsoleView(VillainAllertController allertController) {

        this.allertController = allertController;
        allertController.getTextOnBtnLabel(this);
        initBtn();
    }

    @Override
    public void initBtn() {
        System.out.println("1 - Fight\n" +
                           "2 - Run");
        Scanner sc = new Scanner(System.in);
        int choose = sc.nextInt();
        //TODO check validate
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

    @Override
    public void showRunAllert() {

        System.out.println("You have 50% chance of returning to the previous position. " +
                        "If the odds aren’t on your side, you must fight the " +
                        "villian\nAre you sure that you want to run?\n" +
                "1 - yes\n" +
                "2 - no");
        Scanner sc = new Scanner(System.in);
        int choose = sc.nextInt();
        //TODO check validate
        if (choose == 1)
            onClickRunYes();
        else
            initBtn();


    }

    @Override
    public void showFightAllert() {

        System.out.println("Are you sure that you want fight with villain?\n" +
                "1 - yes\n" +
                "2 - no\n");
        Scanner sc = new Scanner(System.in);
        int choose = sc.nextInt();
        //TODO check validate
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
