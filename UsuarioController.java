package com.saludmza.app.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import com.saludmza.app.dto.LoginDto;
import com.saludmza.app.dto.RegistroPacienteDto;

import org.springframework.http.ResponseEntity;

import com.saludmza.app.models.Paciente;
import com.saludmza.app.models.Usuario;
import com.saludmza.app.repositories.PacienteRepository;
import com.saludmza.app.repositories.UsuarioRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/usuarios")
public class UsuarioController {
	//le decimos que nos traiga el repositorioque creamos antes
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	//Registro del paciente
	@PostMapping("/registro-paciente")
	
	public ResponseEntity<String> registrarPaciente(@RequestBody RegistroPacienteDto datos) {
		
		if (usuarioRepository.findByEmail(datos.getEmail()).isPresent()) {
			return ResponseEntity.status(400).body("ERROR: el email ya esta registrado" );
		}
		if (pacienteRepository.findByCuil(datos.getCuil()).isPresent()) {
			return ResponseEntity.status(400).body("ERROR: el cuil ya esta registrado" );
		}
		
		//Login del usuario
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setEmail(datos.getEmail());
		nuevoUsuario.setPassword(datos.getPassword());
		nuevoUsuario.setRol("Paciente");
		usuarioRepository.save(nuevoUsuario);//guardamos en la tabla Uusuarios
		
		//Login del paciente
		Paciente nuevoPaciente = new Paciente();
		nuevoPaciente.setUsuario(nuevoUsuario);
		nuevoPaciente.setNombreCompleto(datos.getNombreCompleto());
		nuevoPaciente.setEdad(datos.getEdad());
		nuevoPaciente.setCuil(datos.getCuil());
		nuevoPaciente.setDomicilio(datos.getDomicilio());
		nuevoPaciente.setTelefono(datos.getTelefono());
		nuevoPaciente.setTieneObraSocial(datos.getTieneObraSocial());
		nuevoPaciente.setNombreObraSocial(datos.getNombreObraSocial());
		nuevoPaciente.setNumeroAfiliado(datos.getNumeroAfiliado());
		nuevoPaciente.setNumeroTarjeta(datos.getNumeroTarjeta());
		
		
		pacienteRepository.save(nuevoPaciente);
		return ResponseEntity.ok("EXITO: Paciente registrado correctamente");
	}
	
	//---login email o cuil 
	
	@PostMapping("/login")
	public ResponseEntity<String> iniciarSesion(@RequestBody LoginDto datosLogin){
		
		String identificador = datosLogin.getEmail();
		Usuario usuarioLogueado = null;
		
		if (identificador.contains("@")) {
			//si tiene@ es un medico o un recepconista iniciando sesion 
			Optional<Usuario> userOpt = usuarioRepository.findByEmail(identificador);
			if (userOpt.isPresent()) {
				usuarioLogueado = userOpt.get();
			}
		}else {
			//si no tiene @ asumimos que es un paciente  iniciando sesion son cuil 
			Optional<Paciente> pacienteOpt = pacienteRepository.findByCuil(identificador);
			if (pacienteOpt.isPresent()) {
				usuarioLogueado = pacienteOpt.get().getUsuario();
			}
		}
		
		//verifica,os contraseña 
		if (usuarioLogueado != null && usuarioLogueado.getPassword().equals(datosLogin.getPassword())) {
			return ResponseEntity.ok("Login exitoso redirigiendo al panel de:" + usuarioLogueado.getRol());
			
		}else {
			return ResponseEntity.status(401).body("ERROR: credenciales incorrectas");
			
		}
		
		
	}
		
		
		
		
		
		
		
}
