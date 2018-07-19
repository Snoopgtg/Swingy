package com.game.swingy.core.Map;

import com.game.swingy.view.MainMap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmptyButtonListener implements ActionListener {

    private int coordinateX;
    private int coordinateY;
    private MainMap mainMap;

    protected EmptyButtonListener(int x, int y, MainMap mainMap) {
        this.coordinateX = x;
        this.coordinateY = y;
        this.mainMap = mainMap;
    }

    public void actionPerformed(ActionEvent e) {

    }

    protected int getCoordinateX() {
        return coordinateX;
    }

    protected int getCoordinateY() {
        return coordinateY;
    }

    public MainMap getMainMap() {
        return mainMap;
    }
}
