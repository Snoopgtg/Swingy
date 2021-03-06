package com.game.swingy.view.gui;

import com.game.swingy.controller.MapController;
import com.game.swingy.core.Map.EmptyButtonListener;
import com.game.swingy.core.Map.Map;
import com.game.swingy.view.MainMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MapGuiView implements MainMap{

    private JFrame jf;
    private JPanel panel;
    private Container content;
    private JButton btnUnits[][];
    private JButton btnChangeMode;
    private int mapSize;
    private MapController mapController;

    public MapGuiView(MapController mapController) {

        this.mapController = mapController;
        this.mapSize = this.mapController.getMapSize();
        btnUnits = new JButton[mapSize][mapSize];
        btnChangeMode = new JButton("Change mode");
        jf = new JFrame("Swingy");
        jf.setLayout(new BorderLayout());
        content = jf.getContentPane();
        panel = new JPanel(new GridLayout(mapSize,mapSize));
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++){
                btnUnits[i][j] = new JButton();
                panel.add(btnUnits[i][j]);
            }
        }
        content.add(panel, BorderLayout.CENTER);
        content.add(btnChangeMode, BorderLayout.NORTH);
        jf.setSize(mapSize * 95, mapSize * 95);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);

        setHeroIcon(Map.getMap().getObservers().get(0).getCoordinates().getX(),
                Map.getMap().getObservers().get(0).getCoordinates().getY());

        deAndActivatedbtnUnits();
        initMoveHero();
        changeModeListener();
        initCloseListener();
        mapController.setMapJframe(jf);
        mapController.setMainMap(this);
    }

    private void deAndActivatedbtnUnits() {

        int x = Map.getMap().getObservers().get(0).getCoordinates().getX();
        int y = Map.getMap().getObservers().get(0).getCoordinates().getY();
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++){
                if (i == (x - 1) && j == y && (x - 1) < mapSize && (x - 1) >= 0)//рухаємся в гору
                    btnUnitsActivated(i, j);
                else if (i == (x + 1) && j == (y) && (x + 1) < mapSize && (x + 1) >= 0)
                    btnUnitsActivated(i, j);
                else if (i == x && j == (y - 1) && (y - 1) < mapSize && (y - 1) >= 0)//рухаємся в ліво
                    btnUnitsActivated(i, j);
                else if (i == x && j == (y + 1) && (y + 1) < mapSize && (y + 1) >= 0)//рухаємся в право
                    btnUnitsActivated(i, j);
                else if (i == x && j == y)
                    btnUnitsActivated(i, j);
                else
                    btnUnitsDeActivated(i,j);
            }
        }
    }

    public void initMoveHero() {

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                getBtnUnits()[i][j].addActionListener(new EmptyButtonListener(i, j, this) {
                    public void actionPerformed(ActionEvent e) {
                        mapController.onClickButton(this.getCoordinateX(), this.getCoordinateY(),
                                this.getMainMap());
                    }
                });
            }
        }
    }

    private void changeModeListener() {
        this.btnChangeMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                mapController.saveHero();
                mapController.changeGameMode();

            }
        });
    }


    public void initCloseListener() {

        this.jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to close current game with save?",
                        "Close Swingy Message Box",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == 0) {
                    mapController.saveHero();

                }
                else {
                    mapController.deleteHeroAndVillainFromListOfUnit();
                }
                getJf().dispose();
                mapController.visibleStartFrame();
            }
        });
    }

    public void onClickVillainsButton(int x, int y) {

        jf.setVisible(false);
        mapController.onClickVillainsButton(x, y);
    }


    public void onClickEmptyButton(int x, int y) {

        changeHeroPosition(x, y);

    }

    public void btnUnitsDeActivated(int x, int y) {

        btnUnits[x][y].setEnabled(false);
    }

    public void btnUnitsActivated(int x, int y) {

        btnUnits[x][y].setEnabled(true);
    }

    public JButton[][] getBtnUnits() {
        return btnUnits;
    }

    public void setHeroIcon(int x, int y) {

        try {
            Image img = ImageIO.read(getClass().getResource("/superman.png"));
            Image newimg = img.getScaledInstance(btnUnits[x][y].getWidth(),
                    btnUnits[mapSize / 2][mapSize / 2].getHeight(), Image.SCALE_DEFAULT);
            btnUnits[x][y].setIcon(new ImageIcon(newimg));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    public void changeHeroPosition(int toX, int toY) {

        int x = Map.getMap().getObservers().get(0).getCoordinates().getX();
        int y = Map.getMap().getObservers().get(0).getCoordinates().getY();
        setEmptyIcon(x, y);
        setHeroIcon(toX, toY);
        mapController.changeHeroPosition(toX, toY);
        deAndActivatedbtnUnits();
        if (!mapController.isMissionCompleted() && jf != null) {
            jf.setVisible(true);
        }
    }

    public void setVillainsIcon(int x, int y) {

        try {
            Image img = ImageIO.read(getClass().getResource("/villian.jpg"));
            Image newImg = img.getScaledInstance(btnUnits[x][y].getWidth(),
                    btnUnits[x][y].getHeight(), Image.SCALE_DEFAULT);
            btnUnits[x][y].setIcon(new ImageIcon(newImg));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    public void showMissionCompletedView() {

        JOptionPane.showMessageDialog(null, "Mission completed");
        this.jf.dispose();
    }

    public void setEmptyIcon(int x, int y) {

        btnUnits[x][y].setIcon(null);
    }

    private JFrame getJf() {
        return jf;
    }
}
