package br.com.ifrn.portal.sm.models.services.implementation;

import java.util.ArrayList;
import java.util.List;

import br.com.ifrn.portal.sm.models.entities.Brand;
import br.com.ifrn.portal.sm.models.exceptions.InvalidDataException;
import br.com.ifrn.portal.sm.models.infrastructure.DAOBrand;
import br.com.ifrn.portal.sm.models.services.definitions.EntityService;
import br.com.ifrn.portal.sm.models.services.definitions.Service;
import br.com.ifrn.portal.sm.models.services.utilities.PagedEntity;
import br.com.ifrn.portal.sm.models.services.utilities.Pagination;
import br.com.ifrn.portal.sm.models.services.utilities.PaginationInfo;
import br.com.ifrn.portal.sm.models.validations.SimpleConstraintViolations;

/**
 * @author erikv
 * @version 1.0
 * @system ProductService.java
 * @date 19:53:24 18 de jan. de 2023 
 * @system_unity_description Classe responsável por implemetar os serviços da entidade marca.
 * 
 */

public class BrandService  extends Service<Brand> implements EntityService<Brand>{

	/** The dao brand. */
	private DAOBrand daoBrand;
	
	/**
	 * Instantiates a new brand service.
	 */
	public BrandService() {
		daoBrand = new DAOBrand();
	}
	
	/**
	 * Insert.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
	@Override
	public boolean insert(Brand entity) {
		
		boolean isValid = validate(entity);
		if(isValid) {
			daoBrand.insertAtomic(entity);
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
	 * @return the brand
	 */
	@Override
	public Brand findById(Long id) {
		try {
			Brand brand = daoBrand.findById(id);
			return brand;
			
		} catch (Exception e) {
			throw new IllegalArgumentException("Id inválido");
		}
	}

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the paged entity
	 */
	@Override
	public PagedEntity<Brand> findByName(String name) {
		return findByName(name, 1);
	}


	/**
	 * Find by name.
	 *
	 * @param name       the name
	 * @param numberPage the number page
	 * @return the paged entity
	 */
	@Override
	public PagedEntity<Brand> findByName(String name, int numberPage) {
		if(!name.isBlank() && numberPage > 0) {
			
			PaginationInfo paginationInfo = calculatePaginationWithFilterName(name, numberPage);
			PagedEntity<Brand> pagedEntity = getPagedEntityByFindName(name, paginationInfo);
			
			return pagedEntity;
		}else {
			throw new IllegalArgumentException("Nome do produto ou página inválida");
		}
	}

	/**
	 * Find all.
	 *
	 * @return the paged entity
	 */
	@Override
	public PagedEntity<Brand> findAll() {
		return findAll(1);
	}


	/**
	 * Find all.
	 *
	 * @param numberPage the number page
	 * @return the paged entity
	 */
	@Override
	public PagedEntity<Brand> findAll(int numberPage) {
		if(numberPage <= 0) {
			throw new IllegalArgumentException("Número da página inválido");
		}
		
		PaginationInfo paginationInfo = calculatePagination(numberPage);
		
		List<Brand> brands = daoBrand.findAll(
				paginationInfo.getEntitiesPerPage(), paginationInfo.getStart());
		
		PagedEntity<Brand> pagedEntity = new PagedEntity<Brand>(brands, paginationInfo);
		 
		return pagedEntity;
	}

	/**
	 * Update.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
	@Override
	public boolean update(Brand entity) {
		boolean isValid = validate(entity);
		
		if (isValid) {
			daoBrand.updateAtomic(entity);
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
	public boolean delete(Brand entity) {
		try {
			daoBrand.deleteAtomic(entity);
			return true;
			
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível remover a marca");
		}
	}

	/**
	 * Validate.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
	@Override
	public boolean validate(Brand entity) {
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
	public List<SimpleConstraintViolations> getListViolations() {
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
	 * Gets the paged entity by find name.
	 *
	 * @param name           the name
	 * @param paginationInfo the pagination info
	 * @return the paged entity by find name
	 */
	private PagedEntity<Brand> getPagedEntityByFindName(String name, PaginationInfo paginationInfo) {
		List<Brand> brands = daoBrand.findByName(name, paginationInfo.getEntitiesPerPage(), paginationInfo.getStart());
		
		return convertToPagedEntity(brands, paginationInfo);
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
		
		int quantity = Integer.parseInt(daoBrand.getEntityQuantity().toString());
		PaginationInfo infoPagination =  pagination.getPagination(quantity, numberPage);
		
		return infoPagination;
	}
	
	/**
	 * Calculate pagination with filter name.
	 *
	 * @param searchValue the search value
	 * @param numberPage  the number page
	 * @return the pagination info
	 */
	public PaginationInfo calculatePaginationWithFilterName(String searchValue, int numberPage) {
		Pagination pagination = new Pagination();
		
		int quantity = daoBrand.getQuantityBrandsPerFilterName(searchValue);
		PaginationInfo infoPagination =  pagination.getPagination(quantity, numberPage);
		
		return infoPagination;
	}

	/**
	 * Convert to paged entity.
	 *
	 * @param entities       the entities
	 * @param paginationInfo the pagination info
	 * @return the paged entity
	 */
	@Override
	public PagedEntity<Brand> convertToPagedEntity(List<Brand> entities, PaginationInfo paginationInfo) {
		return new PagedEntity<Brand>(entities, paginationInfo);
	}

}
