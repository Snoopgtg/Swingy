package com.game.swingy.core.Unit;

import com.game.swingy.core.Map.UnitTypeFieldEnum;

public interface Builder {

    void setKindOfUnit(UnitTypeFieldEnum kindOfUnit);
    void setName(String name);
    void setHeroClass(String heroClass);
    void setLevel(int level);
    void setExperience(int experience);
    void setAttack(int attack);
    void setDefense(int defense);
    void setHitPoints(int hitPoints);
    void setArtefacts(Artefacts artefacts);
    void setCoordinates(Coordinates coordinates);

}
