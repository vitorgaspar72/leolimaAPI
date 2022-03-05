package com.apiProdutosBlack.apiProdutosBlack.dtos;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;




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

	public Integer getIdmenu() {
		return idmenu;
	}

	public void setIdmenu(Integer idmenu) {
		this.idmenu = idmenu;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public Integer getExibir() {
		return exibir;
	}

	public void setExibir(Integer exibir) {
		this.exibir = exibir;
	}
	
	
}
