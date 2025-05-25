package com.clinica.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCita")
    private int idCita;

    @ManyToOne
    @JoinColumn(name = "idPaciente", referencedColumnName = "idPaciente", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "idDoctor", referencedColumnName = "idDoctor", nullable = false)
    private Doctor doctor;

    @Column(name = "fechaHora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(length = 255)
    private String motivo;

    // Getters y setters
    public int getIdCita() { return idCita; }
    public void setIdCita(int idCita) { this.idCita = idCita; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}
