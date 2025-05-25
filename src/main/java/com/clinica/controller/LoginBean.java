package com.clinica.controller;

import com.clinica.dao.UsuarioDAO;
import com.clinica.model.Usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
    private String username;
    private String password;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private boolean loggedIn = false;

    public String login() {
        if (usuarioDAO.validarLogin(username, password)) {
            loggedIn = true;
            return "listarPacientes?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login fallido", "Usuario o contraseña incorrectos"));
            return null;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        loggedIn = false;
        return "login?faces-redirect=true";
    }

    // Getters y setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public boolean isLoggedIn() { return loggedIn; }
}
