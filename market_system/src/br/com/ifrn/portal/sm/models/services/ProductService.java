package br.com.ifrn.portal.sm.models.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.ifrn.portal.sm.models.entities.Product;
import br.com.ifrn.portal.sm.models.exceptions.InvalidDataException;
import br.com.ifrn.portal.sm.models.infrastructure.DAOProduto;
import br.com.ifrn.portal.sm.models.validations.SimpleConstraintViolations;

/**
 * @author erikv
 * @version 1.0
 * @system ProductService.java
 * @date 12:06:53 8 de jan. de 2023
 * @system_unity_description Classe responsável por implementar os serviços da entidade Produto,
 * evitando assim, o acoplamento entre regras de negócios e a classe modelo.
 */

public class ProductService implements EntityService<Product>{
	
	private DAOProduto daoProduto;
	
	private static ValidatorFactory factory;
	
	private Validator validator;
	
	private Set<ConstraintViolation<Product>> violetions;
	
	static {
		try {
			factory = Validation.buildDefaultValidatorFactory();
		} catch (Exception e) {
			throw new RuntimeException("Não foi possivel criar o validador");
		}
	}
	
	public ProductService() {
		daoProduto = new DAOProduto();
		validator = factory.getValidator();
	}
	
	@Override
	public boolean insert(Product entity) {
		entity.setValor_venda((entity.getPorcentagem_venda() + 1) * entity.getValor_custo());
		
		boolean isValid = validate(entity);
		
		if (isValid) {
			daoProduto.insertAtomic(entity);
			return true;
			
		}else {
			List<SimpleConstraintViolations> listViolations = getListViolations();
			throw new InvalidDataException(listViolations);
		}
	}

	@Override
	public boolean findById(Long id) {
		return false;
	}

	@Override
	public boolean update(Product entity) {
		return false;
	}

	@Override
	public boolean delete(Product entity) {
		return false;
	}

	@Override
	public boolean validate(Product entity) {
		violetions = validator.validate(entity);
		
		if (violetions.size() == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public List<SimpleConstraintViolations> getListViolations(){
		List<SimpleConstraintViolations> listViolations = new ArrayList<>();
		
		violetions.stream().toList().forEach(v -> 
			listViolations.add(new SimpleConstraintViolations(v.getMessage(), v.getInvalidValue())));
		
		return listViolations;
	}
	
}
