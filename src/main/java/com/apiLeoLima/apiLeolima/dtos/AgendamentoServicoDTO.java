package com.apiLeoLima.apiLeolima.dtos;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AgendamentoServicoDTO {
	
	private Integer idagendamentoservico;
	@NotNull
	private Integer idservico;
	
	@NotNull
	private Integer idagendamento;
	
	@NotBlank
	private String horario;
	
	@NotNull
	private Integer status;
	
	@NotNull
	private Integer idatendente;
	
	
	
	

}
