package com.saludmza.app.dto;

public class RegistroPacienteDto {
	//datos del usuario
	private String email;
	private String password;
	
	//datos del formulario
	private String nombreCompleto;
	private Integer edad;
	private String cuil;
	private String domicilio;
	private String telefono;
	private Boolean tieneObraSocial;
	private String nombreObraSocial;
	private String numeroAfiliado;
	private String numeroTarjeta;
	
	//Getters y Setters
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Boolean getTieneObraSocial() {
		return tieneObraSocial;
	}
	public void setTieneObraSocial(Boolean tieneObraSocial) {
		this.tieneObraSocial = tieneObraSocial;
	}
	public String getNombreObraSocial() {
		return nombreObraSocial;
	}
	public void setNombreObraSocial(String nombreObraSocial) {
		this.nombreObraSocial = nombreObraSocial;
	}
	public String getNumeroAfiliado() {
		return numeroAfiliado;
	}
	public void setNumeroAfiliado(String numeroAfiliado) {
		this.numeroAfiliado = numeroAfiliado;
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String nuemeroTarjeta) {
		this.numeroTarjeta = nuemeroTarjeta;
	}
	
	
}
