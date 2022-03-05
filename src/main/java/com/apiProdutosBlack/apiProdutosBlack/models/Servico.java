package com.apiProdutosBlack.apiProdutosBlack.models;

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

	public int getIdservico() {
		return idservico;
	}

	public void setIdservico(int idservico) {
		this.idservico = idservico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}
	
	
}
