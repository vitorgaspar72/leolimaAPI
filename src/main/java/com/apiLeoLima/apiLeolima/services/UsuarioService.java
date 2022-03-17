package com.apiLeoLima.apiLeolima.services;

import java.util.Optional;

import javax.transaction.Transactional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.apiLeoLima.apiLeolima.models.Usuario;
import com.apiLeoLima.apiLeolima.repositories.UsuarioRepository;


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
