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

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	
	
}
