package com.game.swingy.core.Map;

import java.io.Serializable;

public enum HeroClassEnum implements Serializable {

    SAMNITE,SKISSOR,PELTASTS;

    public String toString(){
        switch(this){
            case SAMNITE :
                return "Samnite";
            case SKISSOR :
                return "Skissor";
            case PELTASTS :
                return "Peltasts";
        }
        return null;
    }
}

