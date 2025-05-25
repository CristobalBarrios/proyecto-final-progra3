package com.clinica.converter;

import com.clinica.model.Doctor;
import com.clinica.dao.DoctorDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "com.clinica.converter.DoctorConverter", forClass = Doctor.class)
public class DoctorConverter implements Converter<Doctor> {
    @Override
    public Doctor getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            int id = Integer.parseInt(value);
            DoctorDAO doctorDAO = new DoctorDAO();
            return doctorDAO.buscarPorId(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Doctor doctor) {
        if (doctor == null) {
            return "";
        }
        return String.valueOf(doctor.getIdDoctor());
    }
}
