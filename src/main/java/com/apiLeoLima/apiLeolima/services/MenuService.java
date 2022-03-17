package com.apiLeoLima.apiLeolima.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apiLeoLima.apiLeolima.models.Menu;
import com.apiLeoLima.apiLeolima.repositories.MenuRepository;

@Service
public class MenuService {
	
	final MenuRepository repository;

	public MenuService(MenuRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public Menu salvarMenu(Menu menu) {
		return repository.save(menu);
	}
	
	public Page<Menu> menus(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public boolean verificarMenuExistente(Integer idmenu) {
		return repository.existsById(idmenu);
	}
	
	public Optional<Menu> verificarMenu(Integer idusuario){
		return repository.findById(idusuario);
	}
	
	public void deletarMenu(Integer idMenu) {
		repository.deleteById(idMenu);
	}
}
