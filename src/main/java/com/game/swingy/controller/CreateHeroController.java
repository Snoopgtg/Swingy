package com.game.swingy.controller;

import com.game.swingy.core.Map.Map;
import com.game.swingy.core.Unit.UnitBuilder;
import com.game.swingy.core.Unit.UnitConstructor;

public class CreateHeroController {

    private String nameHero;
    private String selectedHeroClass;

    CreateHeroController() {
    }

    public void onClickCreate() {

        UnitConstructor unitConstructor = new UnitConstructor();
        UnitBuilder unitBuilder = new UnitBuilder();

        switch (selectedHeroClass) {

            case ("Samnite") :
                unitConstructor.constructSamnite(unitBuilder, nameHero);
                break;
            case ("Skissor") :
                unitConstructor.constructSkissor(unitBuilder, nameHero);
                break;
            case ("Peltasts") :
                unitConstructor.constructPeltasts(unitBuilder, nameHero);
                break;
        }
        Map.getMap().register(unitBuilder.createHero());
        Map.getMap().fillListOfVillain();
    }

    public void visibleStartFrame() {
        Map.getMap().getStartFrame().setVisible(true);
    }

    public void setNameHero(String nameHero) {
        this.nameHero = nameHero;
    }

    public void setSelectedHeroClass(String selectedHeroClass) {
        this.selectedHeroClass = selectedHeroClass;
    }
}
