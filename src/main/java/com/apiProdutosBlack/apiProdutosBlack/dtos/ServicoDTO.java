package com.apiProdutosBlack.apiProdutosBlack.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
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
	
	private int duracao;
	
}
