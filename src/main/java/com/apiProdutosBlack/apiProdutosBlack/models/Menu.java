package com.apiProdutosBlack.apiProdutosBlack.models;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="menu")
public class Menu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idmenu;
	
	@Column(nullable=false, length=64)
	private String nome;
	
	@Column(nullable=false, length=124)
	private String link;
	
	@Column(nullable=false, length=45)
	private String icone;
	
	@Column(nullable=false)
	private Integer exibir;
	
	@ManyToMany // tabela associativa
	@JoinTable(name="menu_perfil",joinColumns = @JoinColumn(name="idperfil"),
	inverseJoinColumns= @JoinColumn(name="idmenu")) // a primeira chave vem invertida
	private List<Perfil> perfis;

	
	
}
