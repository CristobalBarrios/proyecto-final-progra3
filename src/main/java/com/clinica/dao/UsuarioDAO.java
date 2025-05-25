package com.clinica.dao;

import com.clinica.model.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UsuarioDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicaPU");

    public Usuario buscarPorUsername(String username) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Usuario.class, username);
        } finally {
            em.close();
        }
    }

    public boolean validarLogin(String username, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            Usuario usuario = em.find(Usuario.class, username);
            return usuario != null && usuario.getPassword().equals(password);
        } finally {
            em.close();
        }
    }

    public void guardar(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
