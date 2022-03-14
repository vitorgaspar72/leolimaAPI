package com.apiProdutosBlack.apiProdutosBlack.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PerfilDTO {
	private Integer idperfil;
	
	@NotBlank
	@Size(max = 64)
	private String nome;
	
	@NotBlank
	private String datacadastro;
	
	
}
