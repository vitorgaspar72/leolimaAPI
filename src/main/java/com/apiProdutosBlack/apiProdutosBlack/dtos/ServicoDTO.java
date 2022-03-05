package com.apiProdutosBlack.apiProdutosBlack.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



public class ServicoDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idservico;
	
	@NotBlank
	@Size(max = 64)
	private String nome;
	
	@NotBlank
	@Size(max = 64)
	private String descricao;
	
	
	private Double preco;
	
	
	public String getNome() {
		return nome;
	}
	
	

	public Integer getIdservico() {
		return idservico;
	}



	public void setIdservico(Integer idservico) {
		this.idservico = idservico;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	


	private int duracao;
}
