package br.com.ifrn.portal.sm.models.validations;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author erikv
 * @version 1.0
 * @system SimpleConstraintVioletions.java
 * @date 17:32:40 11 de jan. de 2023 2023
 * @system_unity_description Classe para simplificar as os dados das violações de 
 * regras das entidades para enviar ao controller 
 */

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class SimpleConstraintViolations {
	
	@NonNull
	private String message;
	
	@NonNull
	private Object invalidValue;

}
