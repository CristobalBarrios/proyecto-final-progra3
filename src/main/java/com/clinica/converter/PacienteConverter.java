package com.clinica.converter;

import com.clinica.model.Paciente;
import com.clinica.dao.PacienteDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "com.clinica.converter.PacienteConverter", forClass = Paciente.class)
public class PacienteConverter implements Converter<Paciente> {
    @Override
    public Paciente getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            int id = Integer.parseInt(value);
            PacienteDAO pacienteDAO = new PacienteDAO();
            return pacienteDAO.buscarPorId(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Paciente paciente) {
        if (paciente == null) {
            return "";
        }
        return String.valueOf(paciente.getIdPaciente());
    }
}
