package br.com.ifrn.portal.sm.models.services.definitions;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.ifrn.portal.sm.models.entities.Product;
import lombok.Getter;
import lombok.Setter;

/**
 * @author erikv
 * @version 1.0
 * @system DAOProduto.java
 * @date 15:46:47 16 de jan. de 2023
 * 
 */

@Getter
@Setter
public class Service<T> {

	private static ValidatorFactory factory;
	
	private Validator validator;
	
	private Set<ConstraintViolation<T>> violetions;
	
	static {
		try {
			factory = Validation.buildDefaultValidatorFactory();
		} catch (Exception e) {
			throw new RuntimeException("Não foi possivel criar o validador");
		}
	}
	
	public Service() {
		validator = factory.getValidator();
	}
	
}
