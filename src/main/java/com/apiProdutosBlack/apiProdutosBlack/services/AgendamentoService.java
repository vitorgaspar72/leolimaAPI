package com.apiProdutosBlack.apiProdutosBlack.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apiProdutosBlack.apiProdutosBlack.models.Agendamento;
import com.apiProdutosBlack.apiProdutosBlack.repositories.AgendamentoRepository;

@Service
public class AgendamentoService {
	
	final AgendamentoRepository repository;

	public AgendamentoService(AgendamentoRepository repository) {

		this.repository = repository;
	}
	
	@Transactional
	public Agendamento salvar(Agendamento agendamento) {
		return repository.save(agendamento);
	}
	
	public Page<Agendamento> agendamentos(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public boolean verificarExistenciaAgendamento(Integer idagendamento) {
		return repository.existsById(idagendamento);
	}
	
	
	public Optional<Agendamento> buscarAgendamento(Integer idagendamento) {
		return repository.findById(idagendamento);
	}
	
	@Transactional
	public void deletarAgendamento(Integer idagendamento) {
		repository.deleteById(idagendamento);
	}
}
