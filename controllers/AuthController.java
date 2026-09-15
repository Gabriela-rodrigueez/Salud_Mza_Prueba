package com.saludmza.app.controllers;

import com.saludmza.app.dto.LoginRequestDto;
import com.saludmza.app.dto.RegistroPacienteDto;
import com.saludmza.app.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Define el controlador REST (anotación requerida por la teoría)
@CrossOrigin(origins = "*") // Permite solicitudes CORS desde cualquier frontend (ej. HTML local)
@RequestMapping("/api/auth") // Ruta base de las peticiones
public class AuthController {

    private final AuthService authService;

    // INYECCIÓN POR CONSTRUCTOR
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Endpoint 1: Registrar Paciente
    @PostMapping("/registro-paciente")
    public ResponseEntity<String> registrarPaciente(@RequestBody RegistroPacienteDto dto) {
        try {
            authService.registrarPaciente(dto);
            return ResponseEntity.ok("ÉXITO: Paciente registrado correctamente en la base de datos");
        } catch (IllegalArgumentException e) {
            // Retorna un HTTP 400 (Bad Request) con el mensaje de error de la validación
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint 2: Login Paciente (DNI + Contraseña)
    @PostMapping("/login/paciente")
    public ResponseEntity<String> loginPaciente(@RequestBody LoginRequestDto dto) {
        try {
            String respuesta = authService.loginPaciente(dto);
            return ResponseEntity.ok(respuesta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    // Endpoint 3: Login Médico (Matrícula/Usuario + Contraseña)
    @PostMapping("/login/medico")
    public ResponseEntity<String> loginMedico(@RequestBody LoginRequestDto dto) {
        try {
            String respuesta = authService.loginMedico(dto);
            return ResponseEntity.ok(respuesta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    // Endpoint 4: Login Administrador (Legajo/UsuarioRed + Contraseña)
    @PostMapping("/login/administrador")
    public ResponseEntity<String> loginAdministrador(@RequestBody LoginRequestDto dto) {
        try {
            String respuesta = authService.loginAdministrador(dto);
            return ResponseEntity.ok(respuesta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}