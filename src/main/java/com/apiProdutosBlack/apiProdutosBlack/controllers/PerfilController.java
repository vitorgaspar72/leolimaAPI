package com.apiProdutosBlack.apiProdutosBlack.controllers;

import java.sql.Date;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiProdutosBlack.apiProdutosBlack.dtos.PerfilDTO;
import com.apiProdutosBlack.apiProdutosBlack.models.Perfil;
import com.apiProdutosBlack.apiProdutosBlack.services.PerfilService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/perfis")
public class PerfilController {
	final PerfilService service;

	public PerfilController(PerfilService service) {
		this.service = service;
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> salvarPerfil(@RequestBody @Valid PerfilDTO perfis,@AuthenticationPrincipal UserDetails userDetails){
		
				Perfil perfil = new Perfil();
				Date dataCadastro = Date.valueOf(perfis.getDatacadastro());
				perfil.setDatacadastro(dataCadastro);
			
			
			BeanUtils.copyProperties(perfis, perfil);
			service.salvarPerfil(perfil);
			
		
			return ResponseEntity.status(HttpStatus.OK).body("Salvo com sucesso!");
			
	}
	

	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Perfil>> getAllPerfis(@PageableDefault(page = 0, sort = "idperfil", 
    direction = Sort.Direction.ASC) Pageable pageable ,@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.status(HttpStatus.OK).body(service.perfis(pageable));
    }
	
	@GetMapping("/{idperfil}")
    public ResponseEntity<Object> getUsuario(@PathVariable(value = "idperfil") Integer idperfil
    		,@AuthenticationPrincipal UserDetails userDetails){
        Optional<Perfil> perfilModelOptional = service.buscarPerfil(idperfil);
     
        if (!perfilModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado!");
        }
        
        
        return ResponseEntity.status(HttpStatus.OK).body(perfilModelOptional.get());
    }
	
	@DeleteMapping("/{idperfil}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "idperfil") Integer idperfil
    		,@AuthenticationPrincipal UserDetails userDetails){
        Optional<Perfil> perfil = service.buscarPerfil(idperfil);
        if (!perfil.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfi não encontrado.");
        }
        service.deletarPerfil(idperfil);
        return ResponseEntity.status(HttpStatus.OK).body("Perfil deletado com sucesso!");
    }
	
	@PutMapping("/{idperfil}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value = "idperfil") Integer idperfil,
                                                    @RequestBody @Valid PerfilDTO perfilDTO,@AuthenticationPrincipal UserDetails userDetails){
        Optional<Perfil> perfilModelOptional = service.buscarPerfil(perfilDTO.getIdperfil());
        if (!perfilModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado.");
        }
        
        Date dataCadastro = Date.valueOf(perfilDTO.getDatacadastro());
       	

       
        perfilModelOptional.get().setIdperfil(perfilDTO.getIdperfil());
        perfilModelOptional.get().setDatacadastro(dataCadastro);
        perfilModelOptional.get().setNome(perfilDTO.getNome());
        
        return ResponseEntity.status(HttpStatus.OK).body(service.salvarPerfil(perfilModelOptional.get()));
    }
}
