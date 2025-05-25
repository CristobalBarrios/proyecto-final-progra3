package com.clinica.dao;

import com.clinica.model.Cita;
import javax.persistence.*;
import java.util.List;

public class CitaDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicaPU");

    public void guardar(Cita cita) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (cita.getPaciente() != null) {
                cita.setPaciente(em.getReference(com.clinica.model.Paciente.class, cita.getPaciente().getIdPaciente()));
            }
            if (cita.getDoctor() != null) {
                cita.setDoctor(em.getReference(com.clinica.model.Doctor.class, cita.getDoctor().getIdDoctor()));
            }
            em.persist(cita);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al guardar cita: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Cita> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Cita> lista = em.createQuery("SELECT c FROM Cita c", Cita.class).getResultList();
        em.close();
        return lista;
    }
}
