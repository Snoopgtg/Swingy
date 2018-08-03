package com.game.swingy.view.console;

import com.game.swingy.controller.ArenaController;
import com.game.swingy.view.Arena;

import java.util.Random;
import java.util.Scanner;

public class ArenaConsoleView implements Arena{

    private ArenaController arenaController;
    private boolean turn = true;


    public ArenaConsoleView(ArenaController arenaController) {

        this.arenaController = arenaController;
        arenaController.setArena(this);
        arenaController.setTextOnVillainLable();
        arenaController.setTextOnHeroLabel();
        initBtn();

    }

    @Override
    public void initBtn() {
        if (turn) {
            System.out.println("Your turn");
            turn = false;
        }
        else {
            System.out.println("Villain turn");
            turn = true;
        }
        System.out.println("1 - make turn");
        Scanner cs = new Scanner(System.in);
        int choose = cs.nextInt();
        //TODO checker
        if (turn)
            onClickVillain();
        else
            onClickHero();
    }

    @Override
    public void onClickHero() {

        arenaController.takeDamageVillain();
        arenaController.setTextOnHeroLabel();
        initBtn();
    }

    @Override
    public void setTextOnVillainLable(int level, int attack, int weapon, int defense, int armor, int health) {
        System.out.println("======= VILLAIN =======");
        System.out.format("Villain level ---> %s\n", level);
        System.out.format("Villain attack ---> %s + %s\n", attack, weapon);
        System.out.format("Villain defense ---> %s + %s\n", defense, armor);
        System.out.format("Points plus to attack ---> %s\n", weapon);
        System.out.format("Points plus to defense ---> %s\n", armor);
        System.out.format("Health ---> %s\n", health);
    }

    @Override
    public void setTextOnHeroLabel(String name, String heroClass, int level, int experience, int attack, int weapon,
                                   int defense, int armor, int health) {

        System.out.println("======= HERO =======");
        System.out.format("Hero name ---> %s\n", name);
        System.out.format("Hero class ---> %s\n", heroClass);
        System.out.format("Hero level ---> %s\n", level);
        System.out.format("Hero attack ---> %s + %s\n", attack, weapon);
        System.out.format("Hero defense ---> %s + %s\n", defense, armor);
        System.out.format("Points plus to attack ---> %s\n", weapon);
        System.out.format("Points plus to defense ---> %s\n", armor);
        System.out.format("Health ---> %s\n", health);
    }

    @Override
    public void villainDie() {
        if (arenaController.isLevel5()) {
            System.out.println("\"Perfectly!!!\\nYou are winner\"");
            System.exit(0);
        }
        System.out.println("You win villain");
        arenaController.villainDie();
        // TODO closeWindow();

    }

    @Override
    public void onClickVillain() {

        Random random = new Random();
        if (random.nextInt(5) == 0) {
            System.out.println("== Something went wrong ==");
            initBtn();
            return;

        }
        else
            arenaController.takeDamageHero();
        arenaController.setTextOnVillainLable();
        if (!arenaController.isLife()) {
            System.out.println("Good buy, LOSER!!!");
            System.exit(0);
        }
        initBtn();
    }

    public int showArtefacts() {

        System.out.println("select one of the three artifacts\n" +
                "1 - weapon\n" +
                "2 - armor\n" +
                "3 - helm\n\n" +
                "0 - back");
        Scanner cs = new Scanner(System.in);
        //TODO validate
        int choose = cs.nextInt();
        return choose - 1;
    }
}
