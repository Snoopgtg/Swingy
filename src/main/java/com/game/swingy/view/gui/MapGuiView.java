package com.game.swingy.view.gui;

import com.game.swingy.controller.MapController;
import com.game.swingy.core.Map.EmptyButtonListener;
import com.game.swingy.core.Map.Map;
import com.game.swingy.core.Unit.Unit;
import com.game.swingy.view.MainMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class MapGuiView implements MainMap{

    private JFrame jf;
    private JPanel panel;
    private Container content;
    private JButton btnUnits[][];
    private int mapSize;
    private MapController mapController;

    public MapGuiView(MapController mapController) {

        this.mapController = mapController;
        this.mapSize = this.mapController.getMapSize();
        btnUnits = new JButton[mapSize][mapSize];
        jf = new JFrame("Swingy");
        content = jf.getContentPane();
        panel = new JPanel(new GridLayout(mapSize,mapSize));
        panel.setSize(mapSize * 95,mapSize * 95);
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++){
                    btnUnits[i][j] = new JButton();
                panel.add(btnUnits[i][j]);
            }
        }
        content.add(panel);
        jf.setSize(mapSize * 95, mapSize * 95);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        setHeroIcon(Map.getMap().getObservers().get(0).getCoordinates().getX(),
                Map.getMap().getObservers().get(0).getCoordinates().getY());

        deAndActivatedbtnUnits();
        initMoveHero();
        mapController.setMapJframe(jf);

    }

    public JButton getBtnUnits(int x, int y) {

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++){
                if (i == x && j == y)
                    return btnUnits[i][j];
            }
        }
        throw new Error("Not valid x and y coordinats");
    }

    private void deAndActivatedbtnUnits() {

        int x = Map.getMap().getObservers().get(0).getCoordinates().getX();
        int y = Map.getMap().getObservers().get(0).getCoordinates().getY();
        System.out.println("x = " + x + "\ny = " + y);
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

    public void initCloseLisener() {

        this.jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to close current game with save?",
                        "Close Swingy Message Box",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == 0) {
                    /*fillDataBase();
                    Map.getMap().deleteVillainFromListofUnit();
                    Map.getMap().deleteHeroFromListOfUnit();*/
                    mapController.saveHero();
                    getJf().dispose();
                    //mapGuiView = null;
                }
                else {
                    /*Map.getMap().deleteVillainFromListofUnit();
                    Map.getMap().deleteHeroFromListOfUnit();*/
                    mapController.deleteHeroAndVillainFromListOfUnit();
                    getJf().dispose();
                }
            }
        });
    }

    public void setVillainIcon() {

        int length = Map.getMap().getObservers().size();
        List<Unit> units = Map.getMap().getObservers();
        for (int i = 1; i < length; i++) {
            setVilliansIcon(units.get(i).getCoordinates().getX(),
                    units.get(i).getCoordinates().getY());
        }
    }

    public void onClickVillainsButton(int x, int y) {

        jf.setVisible(false);
        mapController.onClickVillainsButton(x, y);
    }


    public void onClickEmptyButton(int x, int y) {

        System.out.println("Empty");
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
            System.out.println(ex);
        }
    }

    public void changeHeroPosition(int toX, int toY) {

        int x = Map.getMap().getObservers().get(0).getCoordinates().getX();
        int y = Map.getMap().getObservers().get(0).getCoordinates().getY();
        setEmptyIcon(x, y);
        setHeroIcon(toX, toY);
        mapController.changeHeroPosition(toX, toY);
        deAndActivatedbtnUnits();
        mapController.isCheckWinner(this);
    }

    /*private void isCheckWinner() {

            showMissionCompletedView();
            Map.getMap().deleteVillainFromListofUnit();
            //mapGuiView.closeWindow();
            //mapGuiView.getJf().setVisible(false);
            getJf().dispose();
            Map.getMap().fillListOfVillain();

    }*/

    public void setVilliansIcon(int x, int y) {

        try {
            Image img = ImageIO.read(getClass().getResource("/villian.jpg"));
            Image newimg = img.getScaledInstance(btnUnits[x][y].getWidth(),
                    btnUnits[x][y].getHeight(), Image.SCALE_DEFAULT);
            btnUnits[x][y].setIcon(new ImageIcon(newimg));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void showMissionCompletedView() {

        JOptionPane.showMessageDialog(null,
                "Mission completed");
        this.jf.dispose();
    }

    public void closeWindow() {

        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        WindowEvent windowEvent = new WindowEvent(jf, WindowEvent.WINDOW_CLOSING);
        jf.dispatchEvent(windowEvent);
    }

    public void setEmptyIcon(int x, int y) {

        btnUnits[x][y].setIcon(null);
    }

    public JFrame getJf() {
        return jf;
    }
}
