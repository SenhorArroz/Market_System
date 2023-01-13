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
 * @system_unity_description Classe de exce��o personalizada para as lan�ar quando os 
 * atributos de uma entidades que vieram do front-ent estiverem inv�lidos que comt�m as 
 * viola��es ocorridas
 */

@RequiredArgsConstructor
@Getter
public class InvalidDataException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	@NonNull
	private List<SimpleConstraintViolations> listContraintViolations;
	
	@Override
	public String getMessage() {
		return "Os dados da entidade est�o inv�lidos!";
	}
}
