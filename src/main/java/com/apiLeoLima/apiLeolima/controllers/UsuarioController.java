package com.apiLeoLima.apiLeolima.controllers;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiLeoLima.apiLeolima.dtos.UsuarioDTO;
import com.apiLeoLima.apiLeolima.models.Perfil;
import com.apiLeoLima.apiLeolima.models.Usuario;
import com.apiLeoLima.apiLeolima.services.PerfilService;
import com.apiLeoLima.apiLeolima.services.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	final UsuarioService service;
	final PerfilService pservice;

	public UsuarioController(UsuarioService service, PerfilService pservice) {
		this.service = service;
		this.pservice= pservice;
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> salvarUsuario(@RequestBody @Valid ArrayList<UsuarioDTO> usuarios,@AuthenticationPrincipal UserDetails userDetails){
		ArrayList<Usuario> usuariosModel = new ArrayList<>();
		for(int i=0;i<usuarios.size();i++) {
			var perfil = pservice.buscarPerfil(usuarios.get(i).getIdperfil());
			
			for(int j = 0; j<usuarios.size();j++) {
				Usuario usuario = new Usuario();
				usuariosModel.add(usuario);
			}
			if(usuarios.get(i).getIdusuario() != null && service.verificarUsuarioExistente(usuarios.get(i).getIdusuario())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já existente!");
			}
			
	        if(!perfil.isPresent()) {
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado.");
	        }
			
			BeanUtils.copyProperties(usuarios.get(i), usuariosModel.get(i));
			usuariosModel.get(i).setPerfil(perfil.get());
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			usuariosModel.get(i).setSenha(encoder.encode(usuarios.get(i).getSenha()));
			service.salvarUsuario(usuariosModel.get(i));
			
		}
			return ResponseEntity.status(HttpStatus.OK).body("Salvos com sucesso!");
			
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Usuario>> getAllUsuarios(@PageableDefault(page = 0, sort = "idusuario", direction = Sort.Direction.ASC) Pageable pageable,@AuthenticationPrincipal UserDetails userDetails){
        
		
		return ResponseEntity.status(HttpStatus.OK).body(service.usuarios(pageable));
    }
	
	@GetMapping("/{idusuario}")
    public ResponseEntity<Object> getUsuario(@PathVariable(value = "idusuario") Integer idusuario, 
    		@AuthenticationPrincipal UserDetails userDetails){
		System.out.println(userDetails);
        Optional<Usuario> usuarioModelOptional = service.buscarUsuario(idusuario);
     
        if (!usuarioModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
        }
        
        
        return ResponseEntity.status(HttpStatus.OK).body(usuarioModelOptional.get());
    }
	
	 @DeleteMapping("/{idusuario}")
	 @PreAuthorize("hasRole('ROLE_ADMIN')") // verifica se o requisitante é administrador
	    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "idusuario") Integer idusuario,@AuthenticationPrincipal UserDetails userDetails){
	        Optional<Usuario> usuario = service.buscarUsuario(idusuario);
	        if (!usuario.isPresent()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
	        }
	        service.excluirUsuario(idusuario);
	        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso!");
	    }
	 
	 @PutMapping("/{idusuario}")
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	    public ResponseEntity<Object> updateUsuario(@PathVariable(value = "idusuario") Integer idusuario,
	                                                    @RequestBody @Valid UsuarioDTO usuarioDTO,@AuthenticationPrincipal UserDetails userDetails){
	        Optional<Usuario> usuarioModelOptional = service.buscarUsuario(idusuario);
	        if (!usuarioModelOptional.isPresent()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
	        }
	       	Optional<Perfil> perfil = pservice.buscarPerfil(usuarioDTO.getIdperfil());
	        if(!perfil.isPresent()) {
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado.");
	        }
	        
	        var usuario = new Usuario();
	        BeanUtils.copyProperties(usuarioDTO, usuario);
	        usuario.setIdusuario(usuarioDTO.getIdusuario());
	        usuario.setPerfil(perfil.get());
	        
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        usuario.setSenha(encoder.encode(usuarioDTO.getSenha()));
	        
	        return ResponseEntity.status(HttpStatus.OK).body(service.salvarUsuario(usuario));
	    }
}
