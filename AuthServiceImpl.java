package com.saludmza.app.services;

import com.saludmza.app.dto.LoginRequestDto;
import com.saludmza.app.dto.RegistroPacienteDto;
import com.saludmza.app.models.*;
import com.saludmza.app.repositories.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // Marca la clase como componente de capa de servicio gestionado por Spring
public class AuthServiceImpl implements AuthService {

    // Atributos marcados como final para garantizar la inmutabilidad
    private final UsuarioRepository usuarioRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final AdministradorRepository administradorRepository;

    // INYECCIÓN DE DEPENDENCIAS MEDIANTE CONSTRUCTOR (técnica recomendada en la teoría)
    public AuthServiceImpl(UsuarioRepository usuarioRepository,
                           PacienteRepository pacienteRepository,
                           MedicoRepository medicoRepository,
                           AdministradorRepository administradorRepository) {
        this.usuarioRepository = usuarioRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.administradorRepository = administradorRepository;
    }

    @Override
    public void registrarPaciente(RegistroPacienteDto dto) {
        // PASO 1: Validar que las contraseñas coincidan
        if (!dto.getPassword().equals(dto.getConfirmarPassword())) {
            throw new IllegalArgumentException("ERROR: Las contraseñas ingresadas no coinciden");
        }

        // PASO 2: Validar duplicados de Email en la BD
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("ERROR: El correo electrónico ya está registrado");
        }

        // PASO 3: Validar duplicados de DNI en la BD
        if (pacienteRepository.findByDni(dto.getDni()).isPresent()) {
            throw new IllegalArgumentException("ERROR: El DNI ya pertenece a un paciente registrado");
        }

        // PASO 4: Crear e instanciar la entidad Usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(dto.getEmail());
        nuevoUsuario.setPassword(dto.getPassword());
        nuevoUsuario.setRol("PACIENTE");
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        // PASO 5: Crear e instanciar la entidad Paciente vinculada al Usuario
        Paciente nuevoPaciente = new Paciente();
        nuevoPaciente.setUsuario(usuarioGuardado);
        nuevoPaciente.setNombreCompleto(dto.getNombreCompleto());
        nuevoPaciente.setDni(dto.getDni());
        nuevoPaciente.setFechaNacimiento(dto.getFechaNacimiento());
        nuevoPaciente.setGenero(dto.getGenero());
        nuevoPaciente.setDireccion(dto.getDireccion());
        nuevoPaciente.setTelefono(dto.getTelefono());
        nuevoPaciente.setTieneObraSocial(dto.getTieneObraSocial());

        // PASO 6: Si tiene obra social, mapear sus campos opcionales
        if (Boolean.TRUE.equals(dto.getTieneObraSocial())) {
            nuevoPaciente.setNombreObraSocial(dto.getNombreObraSocial());
            nuevoPaciente.setLegajoObraSocial(dto.getLegajoObraSocial());
            nuevoPaciente.setPlanObraSocial(dto.getPlanObraSocial());
        }

        // Guardar el registro completo en la base de datos
        pacienteRepository.save(nuevoPaciente);
    }

    @Override
    public String loginPaciente(LoginRequestDto dto) {
        // PASO 1: Buscar al paciente mediante su DNI
        Optional<Paciente> pacienteOpt = pacienteRepository.findByDni(dto.getIdentificador());

        // PASO 2: Validar la existencia y contraseña contra la BD
        if (pacienteOpt.isPresent()) {
            Usuario u = pacienteOpt.get().getUsuario();
            if (u.getPassword().equals(dto.getPassword())) {
                return "ÉXITO: Inicio de sesión validado en BD. Bienvenido Paciente: " + pacienteOpt.get().getNombreCompleto();
            }
        }
        throw new IllegalArgumentException("ERROR: DNI o contraseña incorrectos");
    }

    @Override
    public String loginMedico(LoginRequestDto dto) {
        // PASO 1: Intentar buscar al médico por su Matrícula Profesional
        Optional<Medico> medicoOpt = medicoRepository.findByMatriculaProfesional(dto.getIdentificador());

        if (medicoOpt.isPresent()) {
            Usuario u = medicoOpt.get().getUsuario();
            if (u.getPassword().equals(dto.getPassword())) {
                return "ÉXITO: Inicio de sesión validado en BD. Bienvenido Dr/a: " + medicoOpt.get().getNombreCompleto();
            }
        } else {
            // Alternativa: Si ingresó su correo electrónico en lugar de la matrícula
            Optional<Usuario> uOpt = usuarioRepository.findByEmail(dto.getIdentificador());
            if (uOpt.isPresent() && "MEDICO".equals(uOpt.get().getRol()) && uOpt.get().getPassword().equals(dto.getPassword())) {
                return "ÉXITO: Inicio de sesión validado en BD para Médico mediante Correo Electrónico";
            }
        }
        throw new IllegalArgumentException("ERROR: Matrícula/Usuario o contraseña de Médico incorrectos");
    }

    @Override
    public String loginAdministrador(LoginRequestDto dto) {
        // PASO 1: Buscar por Legajo
        Optional<Administrador> adminOpt = administradorRepository.findByLegajo(dto.getIdentificador());

        // PASO 2: Si no encuentra por legajo, buscar por Usuario de Red
        if (adminOpt.isEmpty()) {
            adminOpt = administradorRepository.findByUsuarioRed(dto.getIdentificador());
        }

        // PASO 3: Validar credencial contra la BD
        if (adminOpt.isPresent()) {
            Usuario u = adminOpt.get().getUsuario();
            if (u.getPassword().equals(dto.getPassword())) {
                return "ÉXITO: Inicio de sesión validado en BD. Bienvenido Administrador: " + adminOpt.get().getNombreCompleto();
            }
        }
        throw new IllegalArgumentException("ERROR: Legajo/Usuario de Red o contraseña de Administrador incorrectos");
    }
}