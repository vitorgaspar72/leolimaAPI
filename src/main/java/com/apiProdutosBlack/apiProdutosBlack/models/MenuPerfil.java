package com.apiProdutosBlack.apiProdutosBlack.models;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="menu_perfil")
public class MenuPerfil {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idmenuperfil;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idmenu")
	private Menu menu;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idperfil")
	private Perfil perfil;

	public Integer getIdMenuPerfil() {
		return idmenuperfil;
	}

	public void setIdMenuPerfil(Integer idMenuPerfil) {
		this.idmenuperfil = idMenuPerfil;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
}
