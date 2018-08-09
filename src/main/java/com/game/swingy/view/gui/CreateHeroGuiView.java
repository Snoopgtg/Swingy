package com.game.swingy.view.gui;

import com.game.swingy.controller.CreateHeroController;
import com.game.swingy.core.Map.GameValidator;
import com.game.swingy.view.CreateHero;
import org.hibernate.validator.constraints.NotBlank;

import javax.swing.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateHeroGuiView implements CreateHero{

    @NotBlank(message="ERROR: name must be not empty and max length of name is 10")
    @NotNull(message="ERROR: name must be not empty and max length of name is 10")
    @Size(max = 10, message="ERROR: name must be not empty and max length of name is 10")
    private String name;
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

    public void validateName(String name) {

        this.name = name;
        if (GameValidator.getGameValidator().validate(this)) {
            createHeroController.setNameHero(name);
            createHeroController.setSelectedHeroClass((String)getHeroClassList().getSelectedItem());
            createHeroController.onClickCreate();
            jf.dispose();
        }
        else {
            JOptionPane.showMessageDialog(null,
                    "ERROR: name must be not empty");
            //TODO вивести повідомлення коли більше 10символів
        }
    }

    @Override
    public void createNewHero() {
        this.btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validateName(nameHero.getText());

            }
        });
    }

    public JComboBox<String> getHeroClassList() {
        return heroClassList;
    }
}
