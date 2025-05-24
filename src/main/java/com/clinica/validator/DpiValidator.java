package com.clinica.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.FacesValidator;


@FacesValidator("dpiValidator")
public class DpiValidator implements Validator<String> {

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if (!value.matches("\\d{13}")) {
            throw new ValidatorException(
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "DPI inválido", " DPI Debe tener  13 dígitos numéricos"));
        }
    }
}
