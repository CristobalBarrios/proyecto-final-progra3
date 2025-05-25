package com.clinica.controller;

import com.clinica.dao.CitaDAO;
import com.clinica.model.Cita;
import com.clinica.model.Paciente;
import com.clinica.model.Doctor;
import com.clinica.dao.PacienteDAO;
import com.clinica.dao.DoctorDAO;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class CitaBean implements Serializable {
    private Cita cita = new Cita();
    private List<Cita> citas;
    private CitaDAO citaDAO = new CitaDAO();
    private PacienteDAO pacienteDAO = new PacienteDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();
    private List<Paciente> pacientes;
    private List<Doctor> doctores;

    @PostConstruct
    public void init() {
        pacientes = pacienteDAO.listarTodos();
        doctores = doctorDAO.listarTodos();
        System.out.println("DEBUG: pacientes loaded: " + (pacientes != null ? pacientes.size() : "null"));
    }

    public Cita getCita() { return cita; }
    public void setCita(Cita cita) { this.cita = cita; }

    public List<Cita> getCitas() {
        citas = citaDAO.listarTodos();
        return citas;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }
    
    public List<Doctor> getDoctores() {
        return doctores;
    }

    public String guardar() {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("DEBUG: Paciente seleccionado: " + (cita.getPaciente() != null ? cita.getPaciente().getIdPaciente() : "null"));
        System.out.println("DEBUG: Doctor seleccionado: " + (cita.getDoctor() != null ? cita.getDoctor().getIdDoctor() : "null"));
        if (cita.getPaciente() == null || cita.getPaciente().getIdPaciente() <= 0) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Paciente inválido", "Debe seleccionar un paciente válido."));
            return null;
        }
        if (cita.getDoctor() == null || cita.getDoctor().getIdDoctor() <= 0) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Doctor inválido", "Debe seleccionar un doctor válido."));
            return null;
        }
        citaDAO.guardar(cita);
        cita = new Cita();
        return "listarCitas?faces-redirect=true";
    }

    public String nueva() {
        this.cita = new Cita();
        return "registroCita?faces-redirect=true";
    }
}
