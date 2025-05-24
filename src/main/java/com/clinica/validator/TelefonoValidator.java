package com.clinica.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.FacesValidator;


@FacesValidator("telefonoValidator")
public class TelefonoValidator implements Validator<String> {

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if (!value.matches("\\d{10}")) {
            throw new ValidatorException(
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Numero inválido", "Telefono debe tener 10 dígitos numéricos"));
        }
    }
}
