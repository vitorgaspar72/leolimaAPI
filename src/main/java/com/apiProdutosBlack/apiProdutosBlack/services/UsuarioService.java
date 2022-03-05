package com.apiProdutosBlack.apiProdutosBlack.services;

import java.util.Optional;

import javax.transaction.Transactional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.apiProdutosBlack.apiProdutosBlack.models.Usuario;
import com.apiProdutosBlack.apiProdutosBlack.repositories.UsuarioRepository;


@Service
public class UsuarioService {
	
	final UsuarioRepository repository;

	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		return repository.save(usuario);
	}
	
	public boolean verificarUsuarioExistente(Integer idusuario) {
		return repository.existsById(idusuario);
	}
	
	public Page<Usuario> usuarios(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public Optional<Usuario> buscarUsuario(Integer idusuario) {
		return repository.findById(idusuario);
	}
	
	@Transactional
	public void excluirUsuario(Integer idusuario) {
		 repository.deleteById(idusuario);
	}

	
	
}	
