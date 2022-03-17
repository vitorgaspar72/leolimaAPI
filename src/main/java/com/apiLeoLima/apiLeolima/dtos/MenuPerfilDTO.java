package com.apiLeoLima.apiLeolima.dtos;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MenuPerfilDTO {
	private Integer idmenuperfil;
	
	@NotNull(message="Menu não pode ser ausente")
	private Integer idMenu;
	
	@NotNull(message="Perfil não pode ser ausente")
	private Integer idPerfil;
	
	

}
