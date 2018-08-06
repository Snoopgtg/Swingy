package com.game.swingy.view.console;

import com.game.swingy.controller.PreviousHeroController;
import com.game.swingy.controller.StarterController;
import com.game.swingy.view.PreviousHero;
import com.game.swingy.view.StartView;
import com.game.swingy.view.gui.HeroTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PreviousHeroConsoleView implements PreviousHero {

    private PreviousHeroController previousHeroController;
    private int columnCount = 11;
    private ArrayList<String []> dataArrayList;
    public PreviousHeroConsoleView(PreviousHeroController previousHeroController) {

        this.previousHeroController = previousHeroController;
        dataArrayList = new ArrayList<String []>();
        for (int i = 0; i < dataArrayList.size(); i++) {
            dataArrayList.add(new String[columnCount]);
        }

        addDate(previousHeroController.getRowValue());
        printTable();
        initChoose();
    }

    private void initChoose() {

        System.out.println("Choose the hero ID to load or delete (0 - back)");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        if (id == 0) {
            StarterController starterController = new StarterController();
            StartView startView = new StartConsoleView(starterController);
        }
        else {

            //TODO validate
            System.out.format("Selected id is %d%n1 - load%n2 - delete%n", id);
            int choose = sc.nextInt();
            String[] strings = (String[]) dataArrayList.get(id);
            int idForDB = Integer.parseInt(strings[0]);
            //TODO validate
            if (choose == 1)
                previousHeroController.load(idForDB);
            else {
                dataArrayList.remove(id);
                previousHeroController.delete(idForDB);
                setDeleteAction();
            }
        }
    }

    @Override
    public void setLoadAction() {

    }

    @Override
    public void setDeleteAction() {

        printTable();
        initChoose();
    }
    private void printTable() {

        String leftAlignFormat = "|%-5.5s |%-9.9s |%-8.8s |%-4.1s |%-5.2s |%-6.2s |%-8.3s |%-5.2s |%-4.2s |%-3.2s |%-9.4s |%n";
        System.out.format(" ==================================== Load/Delete Hero ================================= %n");
        System.out.format("+------+----------+---------+-----+------+-------+---------+------+-----+----+----------+%n");
        System.out.format("|№ ID  |name      |heroClass|level|attack|defense|hitPoints|weapon|armor|helm|experience|%n");
        System.out.format("+------+----------+---------+-----+------+-------+---------+------+-----+----+----------+%n");
        for (int i = 0; i < dataArrayList.size(); i++) {
            String[] strings = (String[]) dataArrayList.get(i);
            System.out.format(leftAlignFormat, i, strings[1], strings[2], strings[3], strings[4], strings[5],
                    strings[6], strings[7], strings[8], strings[9], strings[10]);
        }
        System.out.format("+------+----------+---------+-----+------+-------+---------+------+-----+----+----------+%n");

    }

    public void addDate(String [][]row) {

        Collections.addAll(dataArrayList, row);
    }
}