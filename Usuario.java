package com.saludmza.app.models;

import jakarta.persistence.*;

@Entity // Indica a JPA que esta clase se mapea a una tabla de la BD
@Table(name = "usuarios") // Nombre de la tabla física en la base de datos
public class Usuario {

    @Id // Define la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Clave autonumérica (Auto-increment)
    private Long id; // Uso del tipo envoltorio Long según la teoría

    @Column(unique = true, nullable = false) // Restricción: el correo debe ser único y obligatorio
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String rol; // Valores posibles: "PACIENTE", "MEDICO", "ADMINISTRADOR"

    // Constructor vacío obligatorio requerido por JPA
    public Usuario() {
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}