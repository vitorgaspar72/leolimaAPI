package com.apiProdutosBlack.apiProdutosBlack.models;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@SuppressWarnings("unused")
@Getter
@Setter
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


	
	
	

}
