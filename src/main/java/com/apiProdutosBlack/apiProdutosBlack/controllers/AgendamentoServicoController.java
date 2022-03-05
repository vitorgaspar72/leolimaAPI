package com.apiProdutosBlack.apiProdutosBlack.controllers;

import java.sql.Time;
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

import com.apiProdutosBlack.apiProdutosBlack.dtos.AgendamentoServicoDTO;
import com.apiProdutosBlack.apiProdutosBlack.models.AgendamentoServico;
import com.apiProdutosBlack.apiProdutosBlack.services.AgendamentoService;
import com.apiProdutosBlack.apiProdutosBlack.services.AgendamentoServicoService;
import com.apiProdutosBlack.apiProdutosBlack.services.ServicoService;
import com.apiProdutosBlack.apiProdutosBlack.services.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/agendamentoservico")
public class AgendamentoServicoController {
	final AgendamentoServicoService service;
	final ServicoService servicoservice;
	final UsuarioService usuarioservice;
	final AgendamentoService agendamentoservice;
	
	public AgendamentoServicoController(ServicoService servicoservice, UsuarioService usuarioservice, AgendamentoServicoService service,AgendamentoService agendamentoservice) {
		this.servicoservice = servicoservice;
		this.usuarioservice = usuarioservice;
		this.service=service;
		this.agendamentoservice= agendamentoservice;
	}

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> salvarAgendamentoServico(@RequestBody @Valid AgendamentoServicoDTO agendamentoDTO
			,@AuthenticationPrincipal UserDetails userDetails){
			Time horarioAgendamento = Time.valueOf(agendamentoDTO.getHorario());
			AgendamentoServico agendamento = new AgendamentoServico();
			agendamento.setAgendamento(agendamentoservice.buscarAgendamento(agendamentoDTO.getIdagendamento()).get());
			agendamento.setHorario(horarioAgendamento);
			agendamento.setStatus(agendamentoDTO.getStatus());
			agendamento.setAtendente(usuarioservice.buscarUsuario(agendamentoDTO.getIdatendente()).get());
			agendamento.setServico(servicoservice.buscarServico(agendamentoDTO.getIdservico()).get());
			service.salvarAgendamentoServico(agendamento);
			return ResponseEntity.status(HttpStatus.OK).body("Salvo com sucesso!");
			
			
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<AgendamentoServico>> getAllAgendamentos(@PageableDefault(page = 0, sort = "idagendamentoservico",
    direction = Sort.Direction.ASC) Pageable pageable,@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.status(HttpStatus.OK).body(service.agendamentosServicos(pageable));
    }
	
	@GetMapping("/{idagendamentoservico}")
    public ResponseEntity<Object> getAgendamentoServico(@PathVariable(value = "idagendamentoservico") Integer idagendamentoservico
    		,@AuthenticationPrincipal UserDetails userDetails){
        Optional<AgendamentoServico> agendamento = service.buscarAgendamentoServico(idagendamentoservico);
     
        if (!agendamento.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado!");
        }
        
        
        return ResponseEntity.status(HttpStatus.OK).body(agendamento.get());
    }
	
	
	@DeleteMapping("/{idagendamentoservico}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> deletarAgendamentoServico(@PathVariable(value="idagendamentoservico") Integer idagendamentoservico
			,@AuthenticationPrincipal UserDetails userDetails){
		service.deletarAgendamentoServico(idagendamentoservico);
		return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso");
	}
	
	@PutMapping("/{idagendamentoservico}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> updateAgendamentoServico(@PathVariable(value="idagendamentoservico")Integer idagendamentoservico,
			@RequestBody @Valid AgendamentoServicoDTO agendamentoDTO,@AuthenticationPrincipal UserDetails userDetails){
		
		
		boolean verificacao = service.verificarExistenciaAgendamentoServico(idagendamentoservico);
		
		if(!verificacao) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado");
		}else {
			Optional<AgendamentoServico> agendamento = service.buscarAgendamentoServico(idagendamentoservico);
			Time horarioAgendamento = Time.valueOf(agendamentoDTO.getHorario());
			agendamento.get().setHorario(horarioAgendamento);
			agendamento.get().setStatus(agendamentoDTO.getStatus());
			agendamento.get().setAtendente(usuarioservice.buscarUsuario(agendamentoDTO.getIdatendente()).get());
			agendamento.get().setServico(servicoservice.buscarServico(agendamentoDTO.getIdservico()).get());
			agendamento.get().setIdagendamentoservico(idagendamentoservico);
			service.salvarAgendamentoServico(agendamento.get());
		
			return ResponseEntity.status(HttpStatus.OK).body("/nAlterado com sucesso!");
		}
		
	}
	
}
