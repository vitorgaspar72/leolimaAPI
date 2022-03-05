package com.apiProdutosBlack.apiProdutosBlack.dtos;


import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;




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
	
	

	public Date getDatanascimento() {
		return datanascimento;
	}


	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}



	
	public Integer getIdusuario() {
		return idusuario;
	}


	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Integer getIdperfil() {
		return idperfil;
	}


	public void setIdperfil(Integer idperfil) {
		this.idperfil = idperfil;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	
	
	
	
}
