package com.apiProdutosBlack.apiProdutosBlack.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Entity
@Table(name="agendamento")
public class Agendamento implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idagendamento;
	
	@Column(nullable=false)
	private Date dataagendamento;
	
	@Column(nullable=false)
	private Double valortotal;
	
	@Column(nullable=false)
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;
	
	@ManyToMany
	@JoinTable(name="agendamento_servico",joinColumns = @JoinColumn(name="idagendamento"),
	inverseJoinColumns = @JoinColumn(name="idservico"))
	private List<Servico> servicos;

	public Integer getIdagendamento() {
		return idagendamento;
	}

	public void setIdagendamento(Integer idagendamento) {
		this.idagendamento = idagendamento;
	}

	public Date getDataagendamento() {
		return dataagendamento;
	}

	public void setDataagendamento(Date dataagendamento) {
		this.dataagendamento = dataagendamento;
	}

	public Double getValortotal() {
		return valortotal;
	}

	public void setValortotal(Double valortotal) {
		this.valortotal = valortotal;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
