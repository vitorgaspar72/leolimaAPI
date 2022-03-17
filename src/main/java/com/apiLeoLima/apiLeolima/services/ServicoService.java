package com.apiLeoLima.apiLeolima.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apiLeoLima.apiLeolima.models.Servico;
import com.apiLeoLima.apiLeolima.repositories.ServicoRepository;

@Service
public class ServicoService {
	
	final ServicoRepository repository;

	public ServicoService(ServicoRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public Servico salvarServico(Servico servico) {
		return repository.save(servico);
	}
	
	public Page<Servico> servicos(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public boolean verificarServicoExistente(Integer idservico) {
		return repository.existsById(idservico);
	}
	
	public Optional<Servico> buscarServico(Integer idservico) {
		return repository.findById(idservico);
	}
	
	public void deletarServico(Integer idservico) {
		repository.deleteById(idservico);
	}
}
