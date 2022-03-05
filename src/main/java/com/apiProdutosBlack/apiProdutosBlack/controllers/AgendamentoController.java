package com.apiProdutosBlack.apiProdutosBlack.controllers;

import java.sql.Date;
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

import com.apiProdutosBlack.apiProdutosBlack.dtos.AgendamentoDTO;
import com.apiProdutosBlack.apiProdutosBlack.models.Agendamento;
import com.apiProdutosBlack.apiProdutosBlack.services.AgendamentoService;
import com.apiProdutosBlack.apiProdutosBlack.services.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/agendamentos")
public class AgendamentoController {
	final AgendamentoService service;
	final UsuarioService uservice;

	public AgendamentoController(AgendamentoService service, UsuarioService uservice) {
		this.service = service;
		this.uservice= uservice;
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> salvarAgendamento(@RequestBody @Valid AgendamentoDTO agendamentoDTO,@AuthenticationPrincipal UserDetails userDetails){
		
		
			Agendamento agendamento = new Agendamento();
	        Date dataAgendamento = Date.valueOf(agendamentoDTO.getDataagendamento());
			
			agendamento.setDataagendamento(dataAgendamento);
			agendamento.setStatus(agendamentoDTO.getStatus());
			agendamento.setValortotal(agendamentoDTO.getValortotal());
			agendamento.setUsuario(uservice.buscarUsuario(agendamentoDTO.getIdusuario()).get());
			service.salvar(agendamento);
				
	
			
		
			return ResponseEntity.status(HttpStatus.OK).body("Salvo com sucesso!");
			
	}
	

	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Agendamento>> getAllAgendamentos(@PageableDefault(page = 0, sort = "idagendamento",
    direction = Sort.Direction.ASC) Pageable pageable,@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.status(HttpStatus.OK).body(service.agendamentos(pageable));
    }
	
	@GetMapping("/{idagendamento}")
    public ResponseEntity<Object> getUsuario(@PathVariable(value = "idagendamento") Integer idagendamento,@AuthenticationPrincipal UserDetails userDetails){
        Optional<Agendamento> agendamento = service.buscarAgendamento(idagendamento);
     
        if (!agendamento.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado!");
        }
        
        
        return ResponseEntity.status(HttpStatus.OK).body(agendamento.get());
    }
	
	@DeleteMapping("/{idagendamento}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "idagendamento") Integer idagendamento
    		,@AuthenticationPrincipal UserDetails userDetails){
        Optional<Agendamento> agendamento = service.buscarAgendamento(idagendamento);
        if (!agendamento.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado.");
        }
        service.deletarAgendamento(idagendamento);
        return ResponseEntity.status(HttpStatus.OK).body("Agendamento deletado com sucesso!");
    }
	
	@PutMapping("/{idagendamento}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateAgendamento(@PathVariable(value = "idagendamento") Integer idagendamento,
                                                    @RequestBody @Valid AgendamentoDTO agendamentoDTO
                                                    ,@AuthenticationPrincipal UserDetails userDetails){
        Optional<Agendamento> agendamento = service.buscarAgendamento(agendamentoDTO.getIdagendamento());
        if (!agendamento.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado.");
        }
        
        Date dataCadastro = Date.valueOf(agendamentoDTO.getDataagendamento());
       

       
        agendamento.get().setIdagendamento(agendamentoDTO.getIdagendamento());
        agendamento.get().setDataagendamento(dataCadastro);
        agendamento.get().setStatus(agendamentoDTO.getStatus());
        agendamento.get().setValortotal(agendamentoDTO.getValortotal());
        agendamento.get().setUsuario(uservice.buscarUsuario(agendamentoDTO.getIdusuario()).get());
        
        return ResponseEntity.status(HttpStatus.OK).body(service.salvar(agendamento.get()));
    }
}
