package com.apiProdutosBlack.apiProdutosBlack.dtos;

import javax.validation.constraints.NotNull;

public class MenuPerfilDTO {
	private Integer idmenuperfil;
	
	@NotNull(message="Menu não pode ser ausente")
	private Integer idMenu;
	
	@NotNull(message="Perfil não pode ser ausente")
	private Integer idPerfil;
	
	

	public Integer getIdmenuperfil() {
		return idmenuperfil;
	}

	public void setIdmenuperfil(Integer idmenuperfil) {
		this.idmenuperfil = idmenuperfil;
	}

	public Integer getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}
	
	
}
