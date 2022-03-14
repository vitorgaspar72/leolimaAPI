package com.apiProdutosBlack.apiProdutosBlack.dtos;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MenuDTO {
	
	private Integer idmenu;
	
	@NotBlank
	@Size(max = 64)
	private String nome;
	
	@NotBlank
	@Size(max = 124)
	private String link;
	
	@NotBlank
	@Size(max = 45)
	private String icone;
	

	private Integer exibir;

	
	
}
