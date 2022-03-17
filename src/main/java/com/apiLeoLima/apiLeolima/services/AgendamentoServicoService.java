package com.apiLeoLima.apiLeolima.services;

import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apiLeoLima.apiLeolima.models.AgendamentoServico;
import com.apiLeoLima.apiLeolima.repositories.AgendamentoServicoRepository;


@Service
public class AgendamentoServicoService {
	
	final AgendamentoServicoRepository repository;

	public AgendamentoServicoService(AgendamentoServicoRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public AgendamentoServico salvarAgendamentoServico(AgendamentoServico agendamentoServico) {
		return repository.save(agendamentoServico);
	}
	
	public Page<AgendamentoServico> agendamentosServicos(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public boolean verificarExistenciaAgendamentoServico(Integer idagendamentoservico) {
		return repository.existsById(idagendamentoservico);
	}
	
	public Optional<AgendamentoServico> buscarAgendamentoServico(Integer idAgendamentoServico){
		return repository.findById(idAgendamentoServico);
	}
	
	public void deletarAgendamentoServico(Integer idAgendamentoServico) {
		repository.deleteById(idAgendamentoServico);
	}
}
