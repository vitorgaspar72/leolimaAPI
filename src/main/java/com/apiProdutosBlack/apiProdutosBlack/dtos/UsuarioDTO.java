package com.apiProdutosBlack.apiProdutosBlack.dtos;


import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@AllArgsConstructor
@Getter
@Setter
public class UsuarioDTO {
	
	

	private Integer idusuario;
	
	@NotBlank
	@Size (max= 64)
	private String nome;
	
	@NotBlank
	@Size (max= 45)
	private String login;
	
	@NotBlank
	@Size (max= 124)
	private String senha;
	
	
	private Integer status;
	
	private Integer idperfil;
	
	@NotBlank
	@Size (max= 45)
	private String cpf;
	
	@NotBlank
	@Size (max= 45)
	private String endereco;
	
	@NotBlank
	@Size (max= 45)
	private String telefone;
	
	
	private Date datanascimento;
	
	

	
}
