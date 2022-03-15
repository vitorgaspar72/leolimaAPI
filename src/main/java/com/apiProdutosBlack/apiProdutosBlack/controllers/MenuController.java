package com.apiProdutosBlack.apiProdutosBlack.controllers;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiProdutosBlack.apiProdutosBlack.dtos.MenuDTO;
import com.apiProdutosBlack.apiProdutosBlack.models.Menu;
import com.apiProdutosBlack.apiProdutosBlack.services.MenuService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/menus")
public class MenuController {
	final MenuService service;

	public MenuController(MenuService service) {

		this.service = service;
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> salvarMenu(@RequestBody @Valid ArrayList<MenuDTO> menus
			,@AuthenticationPrincipal UserDetails userDetails){
		ArrayList<Menu> menusModel = new ArrayList<>();
		for(int i=0;i<menus.size();i++) {
			
			
			for(int j = 0; j<menus.size();j++) {
				Menu menu = new Menu();
				menusModel.add(menu);
			}
			
			
			BeanUtils.copyProperties(menus.get(i), menusModel.get(i));
			service.salvarMenu(menusModel.get(i));
			
		}
			return ResponseEntity.status(HttpStatus.OK).body("Salvos com sucesso!");
			
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Menu>> getAllMenus(@PageableDefault(page = 0, sort = "idmenu", 
    direction = Sort.Direction.ASC) Pageable pageable,@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.status(HttpStatus.OK).body(service.menus(pageable));
    }
	
	@GetMapping("/{idmenu}")
	
    public ResponseEntity<Object> getMenu(@PathVariable(value = "idmenu") Integer idmenu,@AuthenticationPrincipal UserDetails userDetails){
        Optional<Menu> menuModelOptional = service.verificarMenu(idmenu);
     
        if (!menuModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menu não encontrado!");
        }
        
        
        return ResponseEntity.status(HttpStatus.OK).body(menuModelOptional.get());
    }
	
	 @DeleteMapping("/{idmenu}")
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	    public ResponseEntity<Object> deleteMenu(@PathVariable(value = "idmenu") 
	    Integer idmenu,@AuthenticationPrincipal UserDetails userDetails){
	        Optional<Menu> menu = service.verificarMenu(idmenu);
	        if (!menu.isPresent()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menu não encontrado.");
	        }
	        service.deletarMenu(idmenu);
	        return ResponseEntity.status(HttpStatus.OK).body("Menu deletado com sucesso!");
	    }
	 
	 @PutMapping("/{idmenu}")
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	    public ResponseEntity<Object> updateMenu(@PathVariable(value = "idmenu") Integer idmenu,
	                                                    @RequestBody @Valid MenuDTO menuDTO,@AuthenticationPrincipal UserDetails userDetails){
	        Optional<Menu> menu = service.verificarMenu(idmenu);
	        if (!menu.isPresent()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menu não encontrado.");
	        }
	       
	      
	       
	        menu.get().setIdmenu(menuDTO.getIdmenu());
	        menu.get().setNome(menuDTO.getNome());
	        menu.get().setLink(menuDTO.getLink());
	        menu.get().setIcone(menuDTO.getIcone());
	        
	        
	        
	        return ResponseEntity.status(HttpStatus.OK).body(service.salvarMenu(menu.get()));
	    }
}
