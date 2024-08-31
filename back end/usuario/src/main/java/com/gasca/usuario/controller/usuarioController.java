package com.gasca.usuario.controller;


import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasca.usuario.interfacesService.I_usuarioService;
import com.gasca.usuario.models.usuario;
import com.gasca.usuario.service.emailService;




@RequestMapping("/api/v1/usuario")
@RestController
@CrossOrigin
@Validated
public class usuarioController {

    
    	@Autowired
	private I_usuarioService usuarioService;

	
    @Autowired
    private emailService emailService;
	
	@PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody usuario usuario) {
        usuario.setFecha_ultima_act_contrasena(new Date()); // Asigna la fecha de actualización actual
        usuarioService.save(usuario);
        emailService.enviarCorreoBienvenida(usuario.getCorreo_electronico()); // Uso correcto
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
        

	
	
	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
		var listaUsuario=usuarioService.findAll();
		return new ResponseEntity<>(listaUsuario,HttpStatus.OK);
	}
	

	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findOne(@PathVariable("id") String id){
		var usuario=usuarioService.findOne(id);
		return new ResponseEntity<>(usuario,HttpStatus.OK);
	}

	// @GetMapping("/busquedafiltro/{filtro}")
    // public ResponseEntity<Object> findFiltro(@PathVariable String filtro) {
    //     var listaUsuario = usuarioService.filtroUsuario(filtro);
    //     return new ResponseEntity<>(listaUsuario, HttpStatus.OK);
    // }

	
	// @GetMapping("/editarUsuario/{id}")
	// public String mostrarFormularioDeEditarUsuario(@PathVariable("id") String id, @ModelAttribute("usuario") usuario usuarioUpdate) {
	//     // Lógica para obtener el usuario por ID y agregarlo al modelo
	//     return "formularioEditarUsuario";  // El nombre de la página Thymeleaf para el formulario de edición
	// }

	// @PostMapping("/editarUsuario/{id}")
	// public String actualizarusuario(@PathVariable("id") String id, @ModelAttribute("usuario") usuario usuarioUpdate) {
	//     // Lógica para actualizar el usuario en la base de datos
	//     return "redirect:/listaUsuarios";  // Redirigir a la lista de usuarios después de la edición
	// }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") String id){
		usuarioService.delete(id);
		return new ResponseEntity<>("Registro Eliminado",HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody usuario usuarioUpdate){
		var usuario= usuarioService.findOne(id).get();
		
		if (usuario != null) {
			usuario.setTipo_documento(usuarioUpdate.getTipo_documento());
			usuario.setNumero_documento(usuarioUpdate.getNumero_documento());
			usuario.setFecha_nacimiento(usuarioUpdate.getFecha_nacimiento());
			usuario.setContrasena(usuarioUpdate.getContrasena());
            usuario.setFecha_ultima_act_contrasena(usuarioUpdate.getFecha_ultima_act_contrasena());
            usuario.setFecha_ultimo_inicio_sesion(usuarioUpdate.getFecha_ultimo_inicio_sesion());
			usuario.setCorreo_electronico(usuarioUpdate.getCorreo_electronico());
			usuario.setEstado(usuarioUpdate.isEstado());

		
			usuarioService.save(usuario);
			return new ResponseEntity<>("Guardado",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Error usuario no encontrado",HttpStatus.BAD_REQUEST);
		}
		
	}

}