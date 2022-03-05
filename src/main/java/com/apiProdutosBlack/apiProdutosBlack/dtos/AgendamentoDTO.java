package com.apiProdutosBlack.apiProdutosBlack.dtos;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;





public class AgendamentoDTO {
	
	private Integer idagendamento;
	
	@NotBlank(message = "Data de agendamento não pode ser nulo")
	private String dataagendamento;
	
	@NotNull(message = "Valor total não pode ser nulo")
	private Double valortotal;
	
	@NotNull(message = "Status não pode ser nulo")
	private Integer status;
	
	@NotNull(message = "Id do usuario não pode ser nulo") 
	private Integer idusuario;

	public Integer getIdagendamento() {
		return idagendamento;
	}

	public void setIdagendamento(Integer idagendamento) {
		this.idagendamento = idagendamento;
	}

	public String getDataagendamento() {
		return dataagendamento;
	}

	public void setDataagendamento(String dataagendamento) {
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

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}
	
	
}
