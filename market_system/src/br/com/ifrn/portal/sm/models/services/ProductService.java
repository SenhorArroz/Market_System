package br.com.ifrn.portal.sm.models.services;

import java.util.ArrayList;
import java.util.List;

import br.com.ifrn.portal.sm.models.entities.Category;
import br.com.ifrn.portal.sm.models.entities.Product;
import br.com.ifrn.portal.sm.models.exceptions.InvalidDataException;
import br.com.ifrn.portal.sm.models.infrastructure.DAOProduct;
import br.com.ifrn.portal.sm.models.services.utilities.PaginationInfo;
import br.com.ifrn.portal.sm.models.services.definitions.EntityService;
import br.com.ifrn.portal.sm.models.services.definitions.Service;
import br.com.ifrn.portal.sm.models.services.utilities.PagedEntity;
import br.com.ifrn.portal.sm.models.services.utilities.Pagination;
import br.com.ifrn.portal.sm.models.validations.SimpleConstraintViolations;

/**
 * The Class ProductService.
 *
 * @author erikv
 * @version 1.0
 * @system ProductService.java
 * @date 12:06:53 8 de jan. de 2023
 * @system_unity_description Classe responsável por implementar os serviços da entidade Produto,
 * evitando assim, o acoplamento entre regras de negócios e a classe modelo.
 */

public class ProductService extends Service<Product> implements EntityService<Product>{
	
	/** The dao product. */
	private DAOProduct daoProduct;
	
	/**
	 * Instantiates a new product service.
	 */
	public ProductService() {
		daoProduct = new DAOProduct();
	}
	
	/**
	 * Insert.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
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

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the product
	 */
	@Override
	public Product findById(Long id) { 
		try {
			Product product = daoProduct.findById(id);
			return product;
			
		} catch (Exception e) {
			throw new IllegalArgumentException("Id inválido");
		}
	}
	
	/**
	 * Find by bar code.
	 *
	 * @param barCode the bar code
	 * @return the product
	 */
	public Product findByBarCode(String barCode) {
		try {
			Product product = daoProduct.findByBarCode(barCode);
			return product;
			
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the paged entity
	 */
	@Override
	public PagedEntity<Product> findByName(String name) {
		return findByName(name, 1);
	}
	
	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @param numberPage the number page
	 * @return the paged entity
	 */
	@Override
	public PagedEntity<Product> findByName(String name, int numberPage) {
		if(!name.isBlank() && numberPage > 0) {
			PaginationInfo paginationInfo = calculatePaginationWithFilter(name, numberPage);
			
			List<Product> products = daoProduct.findByName(
					name, paginationInfo.getEntitiesPerPage(), paginationInfo.getStart());
			
			PagedEntity<Product> pagedEntity = new PagedEntity<Product>(products, paginationInfo);
			
			return pagedEntity;
			
		}else {
			throw new IllegalArgumentException("Nome de produto ou página inválida");
		}
	}
	
	public PagedEntity<Product> findByCategory(Category category, int numberPage) {
		if(category != null && numberPage > 0) {
			
			PaginationInfo paginationInfo = calculatePagination(numberPage);
			List<Product> products = daoProduct.findByCategory(
					category, paginationInfo.getEntitiesPerPage(), paginationInfo.getStart());
			
			PagedEntity<Product> pagedEntity = new PagedEntity<Product>(products, paginationInfo);
			
			return pagedEntity;
		}else {
			throw new IllegalArgumentException("Categoria ou página inválida");
		}
	}
	
	/**
	 * Find all.
	 *
	 * @return the paged entity
	 */
	@Override
	public PagedEntity<Product> findAll() {
		return findAll(1);
	}
	
	/**
	 * Find all.
	 *
	 * @param numberPage the number page
	 * @return the paged entity
	 */
	@Override
	public PagedEntity<Product> findAll(int numberPage) {
		
		if(numberPage <= 0) {
			throw new IllegalArgumentException("Número da página inválido");
		}
		
		PaginationInfo paginationInfo = calculatePagination(numberPage);
		
		List<Product> products = daoProduct.findAll(
				paginationInfo.getEntitiesPerPage(), paginationInfo.getStart());
		
		PagedEntity<Product> pagedEntity = new PagedEntity<Product>(products, paginationInfo);
		 
		return pagedEntity;
	}

	/**
	 * Update.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
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

	/**
	 * Delete.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
	@Override
	public boolean delete(Product entity) {
		try {
			daoProduct.delete(entity);
			return true;
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível remover o produto");
		}
	}

	/**
	 * Validate.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
	@Override
	public boolean validate(Product entity) {
		setVioletions(getValidator().validate(entity));
		
		if (getVioletions().size() == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Gets the list violations.
	 *
	 * @return the list violations
	 */
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
	
	/**
	 * Calculate pagination.
	 *
	 * @param numberPage the number page
	 * @return the pagination info
	 */
	@Override
	public PaginationInfo calculatePagination(int numberPage) {
		Pagination pagination = new Pagination();
		
		int quantity = Integer.parseInt(daoProduct.getEntityQuantity().toString());
		PaginationInfo infoPagination =  pagination.getPagination(quantity, numberPage);
		
		return infoPagination;
	}
	
	/**
	 * Calculate pagination with filter.
	 *
	 * @param searchValue the search value
	 * @param numberPage the number page
	 * @return the pagination info
	 */
	private PaginationInfo calculatePaginationWithFilter(String searchValue, int numberPage) {
		Pagination pagination = new Pagination();
		
		int quantity = daoProduct.getQuantityProductsPerFilterDescription(searchValue);
		PaginationInfo infoPagination =  pagination.getPagination(quantity, numberPage);
		
		return infoPagination;
	} 
	
}
