package com.clinica.controller;

import com.clinica.dao.PacienteDAO;
import com.clinica.model.Paciente;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

import java.util.List;
import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class PacienteBean implements Serializable {

    private Paciente paciente = new Paciente();
    private List<Paciente> pacientes;
    private PacienteDAO pacienteDAO = new PacienteDAO();

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Paciente> getPacientes() {
        pacientes = pacienteDAO.listarTodos();
        return pacientes;

    }

    private List<Paciente> pacientesFiltrados;

    public List<Paciente> getPacientesFiltrados() {
        return pacientesFiltrados;
    }

    public void setPacientesFiltrados(List<Paciente> pacientesFiltrados) {
        this.pacientesFiltrados = pacientesFiltrados;
    }

    public String guardar() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        if (pacienteDAO.existe(paciente.getDpi())) {
            pacienteDAO.actualizar(paciente);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Paciente actualizado",
                    "Se actualizaron los datos correctamente."));

        } else {
            pacienteDAO.guardar(paciente);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Paciente guardado", "Se guardó el nuevo paciente."));
        }

        paciente = new Paciente(); // limpia el formulario
        return "listarPacientes?faces-redirect=true";
    }

    public String nuevo() {
        this.paciente = new Paciente(); // limpia el objeto
        return "registroPaciente?faces-redirect=true";
    }

    public String editar(Paciente p) {
        this.paciente = p; // carga el paciente seleccionado
        return "registroPaciente?faces-redirect=true";
    }

    public void eliminar(Paciente p) {
        pacienteDAO.eliminar(p.getDpi());
        pacientes = pacienteDAO.listarTodos(); // actualiza
    }
}
