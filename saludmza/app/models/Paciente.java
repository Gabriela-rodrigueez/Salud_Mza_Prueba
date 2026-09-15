package com.saludmza.app.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación 1 a 1 con la cuenta de Usuario (seguridad)
    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    private String nombreCompleto;

    @Column(unique = true, nullable = false) // El DNI debe ser único para el inicio de sesión
    private String dni;

    private String fechaNacimiento;
    private String genero;
    private String direccion;
    private String telefono;

    // Datos de la Obra Social
    private Boolean tieneObraSocial;
    private String nombreObraSocial;
    private String legajoObraSocial;
    private String planObraSocial;

    // Constructor vacío obligatorio para JPA
    public Paciente() {
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Boolean getTieneObraSocial() { return tieneObraSocial; }
    public void setTieneObraSocial(Boolean tieneObraSocial) { this.tieneObraSocial = tieneObraSocial; }

    public String getNombreObraSocial() { return nombreObraSocial; }
    public void setNombreObraSocial(String nombreObraSocial) { this.nombreObraSocial = nombreObraSocial; }

    public String getLegajoObraSocial() { return legajoObraSocial; }
    public void setLegajoObraSocial(String legajoObraSocial) { this.legajoObraSocial = legajoObraSocial; }

    public String getPlanObraSocial() { return planObraSocial; }
    public void setPlanObraSocial(String planObraSocial) { this.planObraSocial = planObraSocial; }
}