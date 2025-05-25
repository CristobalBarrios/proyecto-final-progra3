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
            return em.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.username = :username AND u.password = :password", Long.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult() > 0;
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
