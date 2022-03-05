package com.apiProdutosBlack.apiProdutosBlack.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apiProdutosBlack.apiProdutosBlack.models.MenuPerfil;
import com.apiProdutosBlack.apiProdutosBlack.repositories.MenuPerfilRepository;

@Service
public class MenuPerfilService {
	final MenuPerfilRepository repository;

	public MenuPerfilService(MenuPerfilRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public MenuPerfil salvarMenuPerfil(MenuPerfil menuperfil) {
		return repository.save(menuperfil);
	}
	
	public boolean verificarExistenciaMenuPerfil(Integer idmenuperfil) {
		return repository.existsById(idmenuperfil);
	}
	
	public Page<MenuPerfil> menusPerfis(Pageable pageable){
		return repository.findAll(pageable);
	}

	public Optional<MenuPerfil> buscarMenuPerfil(Integer idMenuPerfil) {
		return repository.findById(idMenuPerfil);
	}
	
	@Transactional
	public void deletarMenuPerfil(Integer idMenuPerfil) {
		repository.deleteById(idMenuPerfil);
	}
}
