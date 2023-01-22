package br.com.ifrn.portal.sm.models.services.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.ifrn.portal.sm.models.entities.Brand;
import br.com.ifrn.portal.sm.models.entities.Category;
import br.com.ifrn.portal.sm.models.entities.Product;
import br.com.ifrn.portal.sm.models.entities.UnitMeasurement;
import br.com.ifrn.portal.sm.models.exceptions.InvalidDataException;
import br.com.ifrn.portal.sm.models.infrastructure.DAOProduct;
import br.com.ifrn.portal.sm.models.services.definitions.EntityAnonymService;
import br.com.ifrn.portal.sm.models.services.definitions.EntityService;
import br.com.ifrn.portal.sm.models.services.definitions.Service;
import br.com.ifrn.portal.sm.models.services.utilities.PagedEntity;
import br.com.ifrn.portal.sm.models.services.utilities.Pagination;
import br.com.ifrn.portal.sm.models.services.utilities.PaginationInfo;
import br.com.ifrn.portal.sm.models.services.utilities.enums.ProductFindType;
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

public class ProductService extends Service<Product> implements EntityAnonymService<Product>, EntityService<Product>{
	
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
			try {
				daoProduct.insertAtomic(entity);
				return true;
			} catch (Exception e) {
				SimpleConstraintViolations violation = new SimpleConstraintViolations(e.getMessage(), e.getCause().getMessage());
				List<SimpleConstraintViolations> listViolations = new ArrayList<>(Arrays.asList(violation));
				throw new InvalidDataException(listViolations);
			}
			
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
			
			PaginationInfo paginationInfo = calculatePaginationWithFilter(ProductFindType.BY_DESCRIPTION, name, numberPage);
			PagedEntity<Product> pagedEntity = getPagedEntityByFindType(ProductFindType.BY_DESCRIPTION, paginationInfo ,name);
			
			return pagedEntity;
		}else {
			throw new IllegalArgumentException("Nome de produto ou página inválida");
		}
	}
	
	/**
	 * Find by category.
	 *
	 * @param category the category
	 * @param numberPage the number page
	 * @return the paged entity
	 */
	public PagedEntity<Product> findByCategory(Category category, int numberPage) {
		if(category != null && numberPage > 0) {
			
			PaginationInfo paginationInfo = calculatePaginationWithFilter(ProductFindType.BY_CATEGORY, category, numberPage);
			PagedEntity<Product> pagedEntity = getPagedEntityByFindType(ProductFindType.BY_CATEGORY, paginationInfo, category);
			
			return pagedEntity;
		}else {
			throw new IllegalArgumentException("Categoria ou página inválida");
		}
	}
	
	/**
	 * Find by brand.
	 *
	 * @param brand the brand
	 * @param numberPage the number page
	 * @return the paged entity
	 */
	public PagedEntity<Product> findByBrand(Brand brand, int numberPage) {
		if(brand != null && numberPage > 0) {
			
			PaginationInfo paginationInfo = calculatePaginationWithFilter(ProductFindType.BY_BRAND, brand, numberPage);
			PagedEntity<Product> pagedEntity = getPagedEntityByFindType(ProductFindType.BY_BRAND, paginationInfo, brand);
			
			return pagedEntity;
		}else {
			throw new IllegalArgumentException("Marca ou página inválida");
		}
	}
	
	/**
	 * Find by unit measurement.
	 *
	 * @param unitMeasurement the unit measurement
	 * @param numberPage the number page
	 * @return the paged entity
	 */
	public PagedEntity<Product> findByUnitMeasurement(UnitMeasurement unitMeasurement, int numberPage) {
		if(unitMeasurement != null && numberPage > 0) {
			
			PaginationInfo paginationInfo = calculatePaginationWithFilter(ProductFindType.BY_UNIT_MEASUREMENT, unitMeasurement, numberPage);
			PagedEntity<Product> pagedEntity = getPagedEntityByFindType(ProductFindType.BY_UNIT_MEASUREMENT, paginationInfo, unitMeasurement);
			
			return pagedEntity;
		}else {
			throw new IllegalArgumentException("Unidade de medida ou página inválida");
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
	 * @param findType the find type
	 * @param searchValue the search value
	 * @param numberPage the number page
	 * @return the pagination info
	 */
	private PaginationInfo calculatePaginationWithFilter(ProductFindType findType, Object searchValue, int numberPage) {
		
		if(searchValue == null || numberPage < 0) {
			throw new UnsupportedOperationException("Parametros inválido(s)!");
		}
		
		Pagination pagination = new Pagination();
		PaginationInfo paginationInfo = null;
		
		switch (findType) {
		
		case BY_DESCRIPTION: 
			String description = (String) searchValue; 
			
			int quantityDescription = daoProduct.getQuantityProductsPerFilterDescription(description);
			paginationInfo = pagination.getPagination(quantityDescription, numberPage);
			
			break;
			
		case BY_CATEGORY: 
			Category category = (Category) searchValue;			
			int quantityCategory = daoProduct.getQuantityProductsPerFilterCategory(category);
			paginationInfo = pagination.getPagination(quantityCategory, numberPage);
			
			break;
			
		case BY_BRAND: 
			Brand brand = (Brand) searchValue;
			
			int quantityBrand = daoProduct.getQuantityProductsPerFilterBrand(brand);
			paginationInfo = pagination.getPagination(quantityBrand, numberPage);
			
			break;
		
		case BY_UNIT_MEASUREMENT: 
			UnitMeasurement unitMeasurement = (UnitMeasurement) searchValue;
			
			int quantityUnitMeasurement = daoProduct.getQuantityProductsPerFilterUnitMeasurement(unitMeasurement);
			paginationInfo = pagination.getPagination(quantityUnitMeasurement, numberPage);
			
			break;	
			
		default:
			throw new UnsupportedOperationException("Tipo de busca não indentificado");
		}
		
		return paginationInfo;
	} 
	
	/**
	 * Gets the paged entity by find type.
	 *
	 * @param findType the find type
	 * @param paginationInfo the pagination info
	 * @param valueToFind the value to find
	 * @return the paged entity by find type
	 */
	private PagedEntity<Product> getPagedEntityByFindType(ProductFindType findType, 
			PaginationInfo paginationInfo,  Object valueToFind ) {
		
		List<Product> products = new ArrayList<>();
		
		switch (findType) {
		
		case BY_DESCRIPTION:
			String name = (String) valueToFind;
			products = daoProduct.findByDescription(
					name, paginationInfo.getEntitiesPerPage(), paginationInfo.getStart());
			break;
			
		case BY_CATEGORY:
			Category category = (Category) valueToFind;
			products = daoProduct.findByCategory(
					category, paginationInfo.getEntitiesPerPage(), paginationInfo.getStart());
			break;
			
		case BY_BRAND:
			Brand brand = (Brand) valueToFind;
			products = daoProduct.findByBrand(
					brand, paginationInfo.getEntitiesPerPage(), paginationInfo.getStart());
			break;
			
		case BY_UNIT_MEASUREMENT:
			UnitMeasurement unitMeasurement = (UnitMeasurement) valueToFind;
			products = daoProduct.findByUnitMeasurement(
					unitMeasurement, paginationInfo.getEntitiesPerPage(), paginationInfo.getStart());
			break;
			
		default:
			throw new UnsupportedOperationException("Tipo de busca não indentificado");
		}
		
		return convertToPagedEntity(products, paginationInfo);
	}
	
	/**
	 * Convert to paged entity.
	 *
	 * @param products the products
	 * @param paginationInfo the pagination info
	 * @return the paged entity
	 */
	@Override
	public PagedEntity<Product> convertToPagedEntity(List<Product> products, PaginationInfo paginationInfo){
		return new PagedEntity<Product>(products, paginationInfo);
	}
	
}
