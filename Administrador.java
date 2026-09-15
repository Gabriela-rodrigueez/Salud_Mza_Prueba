package com.saludmza.app.models;

import jakarta.persistence.*;

@Entity
@Table(name = "administradores")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    private String nombreCompleto;

    @Column(unique = true) // Legajo único del administrador
    private String legajo;

    @Column(unique = true) // Usuario de red institucional
    private String usuarioRed;

    public Administrador() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getLegajo() { return legajo; }
    public void setLegajo(String legajo) { this.legajo = legajo; }

    public String getUsuarioRed() { return usuarioRed; }
    public void setUsuarioRed(String usuarioRed) { this.usuarioRed = usuarioRed; }
}