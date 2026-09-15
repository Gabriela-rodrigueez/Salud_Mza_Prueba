package com.saludmza.app.models;

import jakarta.persistence.*;

@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    private String nombreCompleto;

    @Column(unique = true, nullable = false) // Matrícula profesional única para el login
    private String matriculaProfesional;

    public Medico() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getMatriculaProfesional() { return matriculaProfesional; }
    public void setMatriculaProfesional(String matriculaProfesional) { this.matriculaProfesional = matriculaProfesional; }
}