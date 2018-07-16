package com.game.swingy.view.gui;

import com.game.swingy.controller.CreateHeroController;
import com.game.swingy.view.CreateHero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class CreateHeroGuiView implements CreateHero{

    private JFrame jf;
    private JLabel labelName;
    private JLabel labelHeroType;
    private JPanel panel;
    private Container content;
    private JTextField nameHero;
    private JButton btnCreate;
    private String[] heroClass;
    private JComboBox<String> heroClassList;
    private CreateHeroController createHeroController;

    public CreateHeroGuiView(CreateHeroController createHeroController) {

        this.createHeroController = createHeroController;
        heroClass = new String[] {"Samnite", "Skissor", "Peltasts"};
        btnCreate = new JButton("Create the hero");
        jf = new JFrame("Swingy");
        content = jf.getContentPane();
        panel = new JPanel(new GridLayout(2,2));
        panel.setSize(100,50);
        labelName = new JLabel("Name: ");
        nameHero = new JTextField();
        heroClassList = new JComboBox<>(heroClass);
        labelHeroType = new JLabel("Type of hero:");
        labelName.setLabelFor(nameHero);
        labelHeroType.setLabelFor(heroClassList);

        panel.add(labelName);
        panel.add(nameHero);
        panel.add(labelHeroType);
        panel.add(heroClassList);

        content.add(panel, BorderLayout.NORTH);
        content.add(btnCreate, BorderLayout.SOUTH);
        jf.setSize(450, 105);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        createNewHero();

    }

    @Override
    public void createNewHero() {
        this.btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createHeroController.setNameHero(getNameHero().getText());
                createHeroController.setSelectedHeroClass((String)getHeroClassList().getSelectedItem());
                createHeroController.onClickCreate();
                jf.dispose();
            }
        });
    }

    public JTextField getNameHero() {
        return nameHero;
    }

    public JComboBox<String> getHeroClassList() {
        return heroClassList;
    }

    public JFrame getJf() {
        return jf;
    }
}
