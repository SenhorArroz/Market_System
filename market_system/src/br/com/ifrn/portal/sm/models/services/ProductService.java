package br.com.ifrn.portal.sm.models.services;

import java.util.ArrayList;
import java.util.List;

import br.com.ifrn.portal.sm.models.entities.Product;
import br.com.ifrn.portal.sm.models.exceptions.InvalidDataException;
import br.com.ifrn.portal.sm.models.infrastructure.DAOProduct;
import br.com.ifrn.portal.sm.models.validations.SimpleConstraintViolations;

/**
 * @author erikv
 * @version 1.0
 * @system ProductService.java
 * @date 12:06:53 8 de jan. de 2023
 * @system_unity_description Classe responsável por implementar os serviços da entidade Produto,
 * evitando assim, o acoplamento entre regras de negócios e a classe modelo.
 * 
 */

public class ProductService extends Service implements EntityService<Product>{
	
	private DAOProduct daoProduct;
	
	public ProductService() {
		daoProduct = new DAOProduct();
	}
	
	@Override
	public boolean insert(Product entity) {
		
		boolean isValid = validate(entity);
		
		if (isValid) {
			daoProduct.insertAtomic(entity);
			return true;
			
		}else {
			List<SimpleConstraintViolations> listViolations = getListViolations();
			throw new InvalidDataException(listViolations);
		}
	}

	@Override
	public Product findById(Long id) { 
		try {
			Product product = daoProduct.findById(id);
			return product;
			
		} catch (Exception e) {
			throw new IllegalArgumentException("Id inválido");
		}
	}
	
	public Product findByBarCode(String barCode) {
		try {
			Product product = daoProduct.findByBarCode(barCode);
			return product;
			
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	@Override
	public List<Product> findByName(String name) {
		
		if(!name.isBlank()) {
			List<Product> products = daoProduct.findByName(name);
			
			return products;
		}else {
			throw new IllegalArgumentException("Nome de produto inválido");
		}
	}
	
	@Override
	public List<Product> findAll() {
		return findAll(10, 0);
	}
	
	@Override
	public List<Product> findAll(int limit, int skip) {
		
		if(limit > 50) {
			throw new IllegalArgumentException("Limite máximo de elementos ultrapassado");
		}
		
		List<Product> products = daoProduct.findAll(limit, skip);
		return products;
	}

	@Override
	public boolean update(Product entity) {
		
		boolean isValid = validate(entity);
		
		if (isValid) {
			daoProduct.updateAtomic(entity);
			return true;
			
		}else {
			List<SimpleConstraintViolations> listViolations = getListViolations();
			throw new InvalidDataException(listViolations);
		}
	}

	@Override
	public boolean delete(Product entity) {
		try {
			daoProduct.delete(entity);
			return true;
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível remover o produto");
		}
	}

	@Override
	public boolean validate(Product entity) {
		setVioletions(getValidator().validate(entity));
		
		if (getVioletions().size() == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public List<SimpleConstraintViolations> getListViolations(){
		if (!getVioletions().isEmpty()) {
			
			List<SimpleConstraintViolations> listViolations = new ArrayList<>();
			
			getVioletions().stream().toList().forEach(v -> 
			listViolations.add(new SimpleConstraintViolations(v.getMessage(), v.getInvalidValue())));
			
			return listViolations;
		}else {
			throw new RuntimeException("Nenhuma violação encontrada");
		}
	}
	
}
