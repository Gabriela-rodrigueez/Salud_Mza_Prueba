package com.saludmza.app.services;

import com.saludmza.app.dto.LoginRequestDto;
import com.saludmza.app.dto.RegistroPacienteDto;

// Contrato de interfaz para abstraer la lógica del controlador
public interface AuthService {
    void registrarPaciente(RegistroPacienteDto dto);
    String loginPaciente(LoginRequestDto dto);
    String loginMedico(LoginRequestDto dto);
    String loginAdministrador(LoginRequestDto dto);
}