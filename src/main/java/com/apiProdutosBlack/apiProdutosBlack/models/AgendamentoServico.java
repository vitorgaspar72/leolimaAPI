package com.apiProdutosBlack.apiProdutosBlack.models;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="agendamento_servico")
public class AgendamentoServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idagendamentoservico;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idservico")
	private Servico servico;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idagendamento")
	private Agendamento agendamento;
	
	@Column(nullable=false)
	private Time horario;
	
	@Column(nullable=false)
	private Integer status;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idatendente")
	private Usuario atendente;


	public Integer getIdagendamentoservico() {
		return idagendamentoservico;
	}


	public void setIdagendamentoservico(Integer idagendamentoservico) {
		this.idagendamentoservico = idagendamentoservico;
	}


	public Servico getServico() {
		return servico;
	}


	public void setServico(Servico servico) {
		this.servico = servico;
	}


	public Agendamento getAgendamento() {
		return agendamento;
	}


	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}


	public Time getHorario() {
		return horario;
	}


	public void setHorario(Time horario) {
		this.horario = horario;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Usuario getAtendente() {
		return atendente;
	}


	public void setAtendente(Usuario atendente) {
		this.atendente = atendente;
	}

	
	
	

}
