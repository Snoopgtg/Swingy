package com.game.swingy.core.Map;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class GameValidator {

    private static GameValidator gameValidator;
    private Validator validator;


    private GameValidator(){

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public static GameValidator getGameValidator() {

        if(gameValidator == null)
            gameValidator = new GameValidator();
        return gameValidator;
    }

    public boolean validate(Object object) {

        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (violations.size() > 0) {
            System.out.println(violations.iterator().next().getMessage());
        }

        return violations.size() == 0;
    }
}
