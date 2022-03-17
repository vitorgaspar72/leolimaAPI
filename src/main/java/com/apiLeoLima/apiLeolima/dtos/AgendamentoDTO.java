package com.apiLeoLima.apiLeolima.dtos;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@AllArgsConstructor
@Getter
@Setter
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

	
}
