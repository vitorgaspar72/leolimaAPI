package com.apiLeoLima.apiLeolima.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@SuppressWarnings("unused")
@Getter
@Setter
@Entity
@Table(name="servico")
public class Servico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idservico;
	
	@Column(nullable=false, length=45)
	private String nome;
	
	@Column(nullable=false, length=125)
	private String descricao;
	
	@Column(nullable=false)
	private Double preco;
	
	@Column(nullable=false)
	private int duracao;
	
	@ManyToMany
	@JoinTable(name="agendamento_servico",joinColumns = @JoinColumn(name="idagendamento"),
	inverseJoinColumns = @JoinColumn(name="idservico"))
	private List<Agendamento> agendamentos;

	
	
}
