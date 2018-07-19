package com.game.swingy.view.console;

import java.io.Serializable;

public enum MoveHeroEnum implements Serializable {
    NORTH, EAST, SOUTH, WEST;

    public String toString(){
        switch(this){
            case NORTH :
                return "north";
            case EAST :
                return "east";
            case SOUTH :
                return "south";
            case WEST :
                return "west";
        }
        return null;
    }
}