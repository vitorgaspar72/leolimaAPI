package com.apiLeoLima.apiLeolima.controllers;

import java.util.ArrayList;
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

import com.apiLeoLima.apiLeolima.dtos.ServicoDTO;
import com.apiLeoLima.apiLeolima.models.Servico;
import com.apiLeoLima.apiLeolima.services.ServicoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/servicos")
public class ServicoController {
	final ServicoService service;

	public ServicoController(ServicoService service) {
		this.service = service;
	}

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> salvarServico(@RequestBody @Valid ArrayList<ServicoDTO> servicos,
			@AuthenticationPrincipal UserDetails userDetails) {
		ArrayList<Servico> servicosModel = new ArrayList<>();
		for (int i = 0; i < servicos.size(); i++) {

			for (int j = 0; j < servicos.size(); j++) {
				Servico servico = new Servico();
				servicosModel.add(servico);
			}

			servicosModel.get(i).setNome(servicos.get(i).getNome());
			servicosModel.get(i).setPreco(servicos.get(i).getPreco());
			servicosModel.get(i).setDescricao(servicos.get(i).getDescricao());
			servicosModel.get(i).setDuracao(servicos.get(i).getDuracao());

			service.salvarServico(servicosModel.get(i));

		}
		return ResponseEntity.status(HttpStatus.OK).body("Salvos com sucesso!");

	}

	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<Servico>> getAllServicos(
			@PageableDefault(page = 0, sort = "idservico", direction = Sort.Direction.ASC) Pageable pageable,
			@AuthenticationPrincipal UserDetails userDetails) {
		return ResponseEntity.status(HttpStatus.OK).body(service.servicos(pageable));
	}

	@GetMapping("/{idservico}")
	public ResponseEntity<Object> getServico(@PathVariable(value = "idservico") Integer idservico,
			@AuthenticationPrincipal UserDetails userDetails) {
		Optional<Servico> ServicoModelOptional = service.buscarServico(idservico);

		if (!ServicoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servico não encontrado!");
		}

		return ResponseEntity.status(HttpStatus.OK).body(ServicoModelOptional.get());
	}

	@DeleteMapping("/{idservico}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> deleteServico(@PathVariable(value = "idservico") Integer idservico,
			@AuthenticationPrincipal UserDetails userDetails) {
		Optional<Servico> Servico = service.buscarServico(idservico);
		if (!Servico.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servico não encontrado.");
		}
		service.deletarServico(idservico);
		return ResponseEntity.status(HttpStatus.OK).body("Servico deletado com sucesso!");
	}

	@PutMapping("/{idservico}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> updateServico(@PathVariable(value = "idservico") Integer idservico,
			@RequestBody @Valid ServicoDTO servicos, @AuthenticationPrincipal UserDetails userDetails) {
		Optional<Servico> servicoOptional = service.buscarServico(idservico);
		if (!servicoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servico não encontrado.");
		}

		var servico = new Servico();

		servico.setIdservico(servicos.getIdservico());
		servico.setNome(servicos.getNome());
		servico.setPreco(servicos.getPreco());
		servico.setDescricao(servicos.getDescricao());
		servico.setDuracao(servicos.getDuracao());

		return ResponseEntity.status(HttpStatus.OK).body(service.salvarServico(servico));
	}

}
