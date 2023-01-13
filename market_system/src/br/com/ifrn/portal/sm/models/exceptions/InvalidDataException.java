package br.com.ifrn.portal.sm.models.exceptions;

import java.util.List;

import br.com.ifrn.portal.sm.models.validations.SimpleConstraintViolations;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author erikv
 * @system InvalidDataException.java
 * @date 17:35:39 11 de jan. de 2023 2023
 * @system_unity_description Classe de exceção personalizada para as lançar quando os 
 * atributos de uma entidades que vieram do front-ent estiverem inválidos que comtém as 
 * violações ocorridas
 */

@RequiredArgsConstructor
@Getter
public class InvalidDataException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	@NonNull
	private List<SimpleConstraintViolations> listContraintViolations;
	
	@Override
	public String getMessage() {
		return "Os dados da entidade estão inválidos!";
	}
}
