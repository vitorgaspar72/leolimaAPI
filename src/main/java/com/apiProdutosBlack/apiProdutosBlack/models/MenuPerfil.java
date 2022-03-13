package com.apiProdutosBlack.apiProdutosBlack.models;


import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@SuppressWarnings("unused")
@Getter
@Setter
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

	
	
}
