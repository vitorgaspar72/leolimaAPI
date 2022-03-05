package com.apiProdutosBlack.apiProdutosBlack.dtos;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;





public class AgendamentoServicoDTO {
	
	private Integer idagendamentoservico;
	@NotNull
	private Integer idservico;
	
	@NotNull
	private Integer idagendamento;
	
	@NotBlank
	private String horario;
	
	@NotNull
	private Integer status;
	
	@NotNull
	private Integer idatendente;

	public Integer getIdservico() {
		return idservico;
	}

	public void setIdservico(Integer idservico) {
		this.idservico = idservico;
	}

	public Integer getIdagendamento() {
		return idagendamento;
	}

	public void setIdagendamento(Integer idagendamento) {
		this.idagendamento = idagendamento;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIdatendente() {
		return idatendente;
	}

	public void setIdatendente(Integer idusuario) {
		this.idatendente = idusuario;
	}

	public Integer getIdagendamentoservice() {
		return idagendamentoservico;
	}

	public void setIdagendamentoservice(Integer idagendamentoservice) {
		this.idagendamentoservico = idagendamentoservice;
	}
	
	
	
	
	

}
