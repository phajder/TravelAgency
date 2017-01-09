package com.hajder.travelagency.validator;

import com.hajder.travelagency.action.RegisterUserAction;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.sql.SQLException;

/**
 * Created by Piotr on 09.01.2017.
 * @author Piotr Hajder
 */
@FacesValidator("com.hajder.UsernameValidator")
public class UsernameValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        //TODO: Lokalizacja wiadomosci
        RegisterUserAction action = new RegisterUserAction();
        FacesMessage message = new FacesMessage();
        try {
            if(!action.isUsernameUnique(value.toString())) {
                message.setSummary("Invalid username.");
                message.setDetail("Username in use. To process registration it is required to change.");
                throw new ValidatorException(message);
            }
        } catch (SQLException e) {
            message.setSummary("Internal error!");
            message.setDetail("Internal server error. Unable to connect. Please try again later.");
            throw new ValidatorException(message);
        }
    }
}
