package com.clinica.validator;

import com.clinica.dao.PacienteDAO;
import com.clinica.model.Paciente;
import com.clinica.controller.PacienteBean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.FacesValidator;

@FacesValidator("dpiUnicoValidator")
public class DpiUnicoValidator implements Validator<String> {

    private PacienteDAO pacienteDAO = new PacienteDAO();

    @Override
    public void validate(FacesContext context, UIComponent component, String dpiIngresado) throws ValidatorException {

        // Obtener el bean para saber si se está editando o creando
        PacienteBean pacienteBean = context.getApplication()
                                           .evaluateExpressionGet(context, "#{pacienteBean}", PacienteBean.class);

        Paciente pacienteActual = pacienteBean.getPaciente();

        boolean esEdicion = pacienteDAO.existe(dpiIngresado) &&
                            pacienteActual != null &&
                            dpiIngresado.equals(pacienteActual.getDpi());

        if (pacienteDAO.existe(dpiIngresado) && !esEdicion) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "DPI duplicado", "Ya existe un paciente con ese DPI."));
        }
    }
}
