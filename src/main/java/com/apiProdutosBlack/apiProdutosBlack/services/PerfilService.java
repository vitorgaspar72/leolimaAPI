package com.apiProdutosBlack.apiProdutosBlack.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apiProdutosBlack.apiProdutosBlack.models.Perfil;
import com.apiProdutosBlack.apiProdutosBlack.repositories.PerfilRepository;

@Service
public class PerfilService {
	
	final PerfilRepository repository;

	public PerfilService(PerfilRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public Perfil salvarPerfil(Perfil perfil) {
		return repository.save(perfil);
	}
	
	public boolean verificarPerfilExistente(Integer idperfil) {
		return repository.existsById(idperfil);
	}
	
	public Optional<Perfil> buscarPerfil(Integer idperfil) {
		return repository.findById(idperfil);
	}
	
	public Page<Perfil> perfis(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	@Transactional
	public void deletarPerfil(Integer idperfil) {
		 repository.deleteById(idperfil);
	}
	
}
