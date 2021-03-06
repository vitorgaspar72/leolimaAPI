package com.apiLeoLima.apiLeolima.controllers;


import java.util.Optional;

import javax.validation.Valid;

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

import com.apiLeoLima.apiLeolima.dtos.MenuPerfilDTO;
import com.apiLeoLima.apiLeolima.models.MenuPerfil;
import com.apiLeoLima.apiLeolima.services.MenuPerfilService;
import com.apiLeoLima.apiLeolima.services.MenuService;
import com.apiLeoLima.apiLeolima.services.PerfilService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/menuperfil")
public class MenuPerfilController {
	final MenuPerfilService service;
	final MenuService menuservice;
	final PerfilService perfilservice;
	
	public MenuPerfilController(MenuPerfilService service,MenuService menuservice,PerfilService perfilservice ) {
		this.service = service;
		this.menuservice= menuservice;
		this.perfilservice= perfilservice;
	}
	
	@SuppressWarnings("null")
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> salvarServico(@RequestBody @Valid MenuPerfilDTO menuperfilDTO,@AuthenticationPrincipal UserDetails userDetails){
			
			var menuperfil= new MenuPerfil();
			menuperfil = service.buscarMenuPerfil(menuperfilDTO.getIdmenuperfil()).get();
			if(menuperfil != null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Associa????o j?? cadastrada");
			}else {
				menuperfil.setMenu(menuservice.verificarMenu(menuperfilDTO.getIdMenu()).get());
				menuperfil.setPerfil(perfilservice.buscarPerfil(menuperfilDTO.getIdPerfil()).get());
			
				service.salvarMenuPerfil(menuperfil);
				
			
				return ResponseEntity.status(HttpStatus.OK).body("Salvos com sucesso!");
			}
			
			
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<MenuPerfil>> getAllServicos(@PageableDefault(page = 0, sort = "idmenuperfil",
    direction = Sort.Direction.ASC) Pageable pageable,@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.status(HttpStatus.OK).body(service.menusPerfis(pageable));
    }
	
	@GetMapping("/{idmenuperfil}")
    public ResponseEntity<Object> getMenuPerfil(@PathVariable(value = "idmenuperfil") Integer idmenuperfil,@AuthenticationPrincipal UserDetails userDetails){
        Optional<MenuPerfil> menuperfil = service.buscarMenuPerfil(idmenuperfil);
     
        if (!menuperfil.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Associa????o n??o encontrada.");
        }
        
        
        return ResponseEntity.status(HttpStatus.OK).body(menuperfil.get());
    }
	
	 @DeleteMapping("/{idmenuperfil}")
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	    public ResponseEntity<Object> deleteServico(@PathVariable(value = "idmenuperfil") 
	    Integer idmenuperfil,@AuthenticationPrincipal UserDetails userDetails){
	        Optional<MenuPerfil> menuperfil = service.buscarMenuPerfil(idmenuperfil);
	        if (!menuperfil.isPresent()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Associa????o n??o encontrada.");
	        }
	        service.deletarMenuPerfil(idmenuperfil);
	        return ResponseEntity.status(HttpStatus.OK).body("Associa????o exclu??da com sucesso!");
	    }
	 
	 @PutMapping("/{idmenuperfil}")
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	    public ResponseEntity<Object> updateMenuPerfil(@PathVariable(value = "idmenuperfil") Integer idmenuperfil,
	                                                    @RequestBody @Valid MenuPerfilDTO menuperfilDTO,@AuthenticationPrincipal UserDetails userDetails){
	        Optional<MenuPerfil> menuperfil = service.buscarMenuPerfil(idmenuperfil);
	        if (!menuperfil.isPresent()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Associa????o menu/perfil n??o encontrada.");
	        }
	       
	        
	        
	        menuperfil.get().setIdmenuperfil(idmenuperfil);
	        menuperfil.get().setMenu(menuservice.verificarMenu(menuperfilDTO.getIdMenu()).get());
			menuperfil.get().setPerfil(perfilservice.buscarPerfil(menuperfilDTO.getIdPerfil()).get());
			
	    
	        
	        return ResponseEntity.status(HttpStatus.OK).body(service.salvarMenuPerfil(menuperfil.get()));
	    }
}
