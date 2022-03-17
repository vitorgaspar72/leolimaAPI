package com.apiLeoLima.apiLeolima.models;


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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="usuario")
public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idusuario;
	
	@Column(nullable=false, length=64)
	private String nome;
	
	@Column(nullable=false, length=45)
	private String login;
	
	@Column(nullable=false, length=124)
	private String senha;
	
	@Column(nullable=false)
	private Integer status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idperfil")
	private Perfil perfil;
	
	@Column(nullable=false, length=45)
	private String cpf;
	
	@Column(nullable=false, length=45)
	private String endereco;
	
	@Column(nullable=false, length=45)
	private String telefone;
	
	@Column
	private Date datanascimento;

	
	@OneToMany
	private List<Agendamento> agendamentos;
	
	

	
	
}
