package com.clinica.dao;

import java.util.List;
import com.clinica.model.Paciente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PacienteDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicaPU");

    public void guardar(Paciente paciente) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(paciente); // guarda el paciente en la BD
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al guardar paciente: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    public List<Paciente> listarTodos() {
    EntityManager em = emf.createEntityManager();
    List<Paciente> lista = em.createQuery("SELECT p FROM Paciente p", Paciente.class).getResultList();
    em.close();
    return lista;
    
    
}
    
    public boolean existe(String dpi) {
    EntityManager em = emf.createEntityManager();
    boolean encontrado = em.find(Paciente.class, dpi) != null;
    em.close();
    return encontrado;
}

public void actualizar(Paciente paciente) {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        em.merge(paciente); // actualiza
        em.getTransaction().commit();
    } catch (Exception e) {
        em.getTransaction().rollback();
        System.out.println("Error al actualizar paciente: " + e.getMessage());
    } finally {
        em.close();
    }
}


public void eliminar(String dpi) {
    EntityManager em = emf.createEntityManager();
    try {
        Paciente paciente = em.find(Paciente.class, dpi);
        if (paciente != null) {
            em.getTransaction().begin();
            em.remove(paciente);
            em.getTransaction().commit();
        }
    } catch (Exception e) {
        em.getTransaction().rollback();
        System.out.println("Error al eliminar: " + e.getMessage());
    } finally {
        em.close();
    }
}
}
