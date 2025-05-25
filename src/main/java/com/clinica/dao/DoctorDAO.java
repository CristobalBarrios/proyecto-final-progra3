package com.clinica.dao;

import java.util.List;
import com.clinica.model.Doctor;
import com.clinica.model.Paciente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DoctorDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicaPU");

    public void guardar(Doctor doctor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(doctor); // Guarda el Doctor en la BD
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al guardar Doctor: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Doctor> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Doctor> lista = em.createQuery("SELECT p FROM Doctor p", Doctor.class).getResultList();
        em.close();
        return lista;

    }

    public boolean existe(String dpi) {
        EntityManager em = emf.createEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(p) FROM Doctor p WHERE p.dpi = :dpi", Long.class)
                .setParameter("dpi", dpi)
                .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    public void actualizar(Doctor doctor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(doctor); // actualiza
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al actualizar Doctor: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void eliminar(String dpi) {
        EntityManager em = emf.createEntityManager();
        try {
            Doctor doctor = em.find(Doctor.class, dpi);
            if (doctor != null) {
                em.getTransaction().begin();
                em.remove(doctor);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al eliminar: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Doctor buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Doctor.class, id);
        } finally {
            em.close();
        }
    }
}
