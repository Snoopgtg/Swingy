package com.game.swingy.view.console;

import com.game.swingy.controller.PreviousHeroController;
import com.game.swingy.controller.StarterController;
import com.game.swingy.core.Map.GameValidator;
import com.game.swingy.view.PreviousHero;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PreviousHeroConsoleView implements PreviousHero {

    @Pattern(regexp = "^\\d+$", message = "ERROR only digits allowed")
    @Min(value = 0, message = "ERROR You should choose one option 1, 2 or 0")
    @Max(value = 2, message = "ERROR You should choose one option 1, 2 or 0")
    private String selectedLoadDel;

    @Pattern(regexp = "^\\d+$", message = "ERROR only digits allowed")
    private String id;

    private PreviousHeroController previousHeroController;
    private ArrayList<String []> dataArrayList;
    private Scanner sc;

    public PreviousHeroConsoleView(PreviousHeroController previousHeroController) {

        int columnCount = 11;

        this.previousHeroController = previousHeroController;
        dataArrayList = new ArrayList<>();
        sc = new Scanner(System.in);
        for (int i = 0; i < dataArrayList.size(); i++) {
            dataArrayList.add(new String[columnCount]);
        }

        addDate(previousHeroController.getRowValue());
        printTable();
        initChoose();
    }

    private void showChooseHeroIdMessage() {

        System.out.println("Choose the hero ID to load or delete (0 - back)");
        id = sc.nextLine();
        if (!GameValidator.getGameValidator().validate(this))
            showChooseHeroIdMessage();
        if (Integer.parseInt(id) == 0)
            return;
        int choose = Integer.parseInt(id);
        if (dataArrayList.size() < choose) {
            System.out.println("Select correct ID");
            showChooseHeroIdMessage();
        }
    }

    private void initChoose() {

        showChooseHeroIdMessage();
        int id = Integer.parseInt(this.id);
        if (id == 0) {
            StarterController starterController = new StarterController();
            new StartConsoleView(starterController);
        }
        else {

            System.out.format("Selected id is %d%n", id);
            showLoadDelete();
            int choose = Integer.parseInt(selectedLoadDel);
            String[] strings = dataArrayList.get(id - 1);
            int idForDB = Integer.parseInt(strings[0]);
            if (choose == 1)
                previousHeroController.load(idForDB);
            else if (choose == 2){
                dataArrayList.remove(id - 1);
                previousHeroController.delete(idForDB);
                setDeleteAction();
            }
            else {
                printTable();
                initChoose();
            }
        }
    }

    private void showLoadDelete() {

        System.out.println("1 - load\n" +
                           "2 - delete\n\n" +
                           "0 - back");
        this.selectedLoadDel = sc.nextLine();
        if (!GameValidator.getGameValidator().validate(this))
            showLoadDelete();

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
            System.out.format(leftAlignFormat, i + 1, strings[1], strings[2], strings[3], strings[4], strings[5],
                    strings[6], strings[7], strings[8], strings[9], strings[10]);
        }
        System.out.format("+------+----------+---------+-----+------+-------+---------+------+-----+----+----------+%n");

    }

    private void addDate(String [][]row) {

        Collections.addAll(dataArrayList, row);
    }
}