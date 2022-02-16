package org.comtravo.travel.repository.base;

import java.sql.Timestamp;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

public class BaseRepository extends BaseDbRepository {
     
    private Validator Validator = null;

    protected Validator GetValidator() {

        if (Validator == null) {
            var factory = Validation.buildDefaultValidatorFactory();
            Validator = factory.getValidator();
        }

        return Validator;
    }

    protected Timestamp GetTimestamp() {
        java.util.Date date = new java.util.Date();
        return new Timestamp(date.getTime());  
    }

    public <T> boolean IsValid(T entity) {

        var validator = GetValidator();
        var violations = validator.validate(entity);
        
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }        

        return true;
    }

}
