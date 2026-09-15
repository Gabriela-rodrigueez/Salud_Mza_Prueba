
package com.saludmza.app.dto;

// DTO genérico para las peticiones de inicio de sesión de Pacientes, Médicos y Admins
public class LoginRequestDto {

    // 'identificador' representará el DNI (Paciente), Matrícula/Correo (Médico) o Legajo/UsuarioRed (Admin)
    private String identificador;
    private String password;

    public LoginRequestDto() {
    }

    public String getIdentificador() { return identificador; }
    public void setIdentificador(String identificador) { this.identificador = identificador; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

