package com.apiProdutosBlack.apiProdutosBlack.dtos;




import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class PerfilDTO {
	private Integer idperfil;
	
	@NotBlank
	@Size(max = 64)
	private String nome;
	
	@NotBlank
	private String datacadastro;

	public Integer getIdperfil() {
		return idperfil;
	}

	public void setIdperfil(Integer idperfil) {
		this.idperfil = idperfil;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDatacadastro() {
		return datacadastro;
	}

	public void setDatacadastro(String datacadastro) {
		
		this.datacadastro = datacadastro;
	}
	
	
}
