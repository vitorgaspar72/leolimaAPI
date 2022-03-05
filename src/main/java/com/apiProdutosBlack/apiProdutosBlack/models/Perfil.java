package com.apiProdutosBlack.apiProdutosBlack.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="perfil")
public class Perfil implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idperfil;
	
	@Column(nullable=false, length=64)
	private String nome;
	
	@Column(nullable=false)
	private Date datacadastro;
	
	@OneToMany
	private List<Usuario> usuarios;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="menu_perfil",joinColumns = @JoinColumn(name="idperfil"),
	inverseJoinColumns= @JoinColumn(name="idmenu"))
	private List<Menu> menus;

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

	public Date getDatacadastro() {
		return datacadastro;
	}

	public void setDatacadastro(Date datacadastro) {
		this.datacadastro = datacadastro;
	}


	public List<Menu> getMenus() {
		return null;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
