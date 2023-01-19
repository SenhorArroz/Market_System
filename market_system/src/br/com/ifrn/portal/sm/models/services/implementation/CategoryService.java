package br.com.ifrn.portal.sm.models.services.implementation;

import java.util.ArrayList;
import java.util.List;

import br.com.ifrn.portal.sm.models.entities.Brand;
import br.com.ifrn.portal.sm.models.entities.Category;
import br.com.ifrn.portal.sm.models.exceptions.InvalidDataException;
import br.com.ifrn.portal.sm.models.infrastructure.DAOCategory;
import br.com.ifrn.portal.sm.models.services.definitions.EntityService;
import br.com.ifrn.portal.sm.models.services.definitions.Service;
import br.com.ifrn.portal.sm.models.services.utilities.PagedEntity;
import br.com.ifrn.portal.sm.models.services.utilities.Pagination;
import br.com.ifrn.portal.sm.models.services.utilities.PaginationInfo;
import br.com.ifrn.portal.sm.models.validations.SimpleConstraintViolations;

/**
 * The Class CategoryService.
 *
 * @author erikv
 * @version 1.0
 * @system ProductService.java
 * @date 20:03:56 18 de jan. de 2023
 * @system_unity_description Classe responsável por implementar os serviços da entidade categoria.
 */

public class CategoryService extends Service<Category> implements EntityService<Category>{

	/** The dao category. */
	private DAOCategory daoCategory;
	
	/**
	 * Instantiates a new category service.
	 */
	public CategoryService() {
		daoCategory = new DAOCategory();
	}
	
	/**
	 * Insert.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
	@Override
	public boolean insert(Category entity) {
		boolean isValid = validate(entity);
		if(isValid) {
			daoCategory.insertAtomic(entity);
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
	 * @return the category
	 */
	@Override
	public Category findById(Long id) {
		try {
			Category category = daoCategory.findById(id);
			return category;
			
		} catch (Exception e) {
			throw new IllegalArgumentException("Id inválido");
		}
	}

	/**
	 * Find by name.
	 *
	 * @param description the description
	 * @return the paged entity
	 */
	@Override
	public PagedEntity<Category> findByName(String description) {
		return findByName(description, 1);
	}

	/**
	 * Find by name.
	 *
	 * @param description the description
	 * @param numberPage the number page
	 * @return the paged entity
	 */
	@Override
	public PagedEntity<Category> findByName(String description, int numberPage) {
		if(!description.isBlank() && numberPage > 0) {
			
			PaginationInfo paginationInfo = calculatePaginationWithFilterDescription(description, numberPage);
			PagedEntity<Category> pagedEntity = getPagedEntityByFindDescription(description, paginationInfo);
			
			return pagedEntity;
		}else {
			throw new IllegalArgumentException("Nome da categoria ou página inválida");
		}
	}

	/**
	 * Find all.
	 *
	 * @return the paged entity
	 */
	@Override
	public PagedEntity<Category> findAll() {
		return findAll(1);
	}

	/**
	 * Find all.
	 *
	 * @param numberPage the number page
	 * @return the paged entity
	 */
	@Override
	public PagedEntity<Category> findAll(int numberPage) {
		if(numberPage <= 0) {
			throw new IllegalArgumentException("Número da página inválido");
		}
		
		PaginationInfo paginationInfo = calculatePagination(numberPage);
		
		List<Category> categories = daoCategory.findAll(
				paginationInfo.getEntitiesPerPage(), paginationInfo.getStart());
		
		PagedEntity<Category> pagedEntity = new PagedEntity<Category>(categories, paginationInfo);
		 
		return pagedEntity;
	}

	/**
	 * Update.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
	@Override
	public boolean update(Category entity) {
		boolean isValid = validate(entity);
		
		if (isValid) {
			daoCategory.updateAtomic(entity);
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
	public boolean delete(Category entity) {
		try {
			daoCategory.deleteAtomic(entity);
			return true;
			
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível remover a Categoria");
		}
	}

	/**
	 * Validate.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
	@Override
	public boolean validate(Category entity) {
		setVioletions(getValidator().validate(entity));
		
		if (getVioletions().size() == 0) {
			return true;
		}else {
			return false;
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
		
		int quantity = Integer.parseInt(daoCategory.getEntityQuantity().toString());
		PaginationInfo infoPagination =  pagination.getPagination(quantity, numberPage);
		
		return infoPagination;
	}
	
	/**
	 * Calculate pagination with filter description.
	 *
	 * @param searchValue the search value
	 * @param numberPage the number page
	 * @return the pagination info
	 */
	public PaginationInfo calculatePaginationWithFilterDescription(String searchValue, int numberPage) {
		Pagination pagination = new Pagination();
		
		int quantity = daoCategory.getQuantityCategoriesPerFilterDescription(searchValue);
		PaginationInfo infoPagination =  pagination.getPagination(quantity, numberPage);
		
		return infoPagination;
	}

	/**
	 * Convert to paged entity.
	 *
	 * @param entities the entities
	 * @param paginationInfo the pagination info
	 * @return the paged entity
	 */
	@Override
	public PagedEntity<Category> convertToPagedEntity(List<Category> entities, PaginationInfo paginationInfo) {
		return new PagedEntity<Category>(entities, paginationInfo);
	}
	
	/**
	 * Gets the paged entity by find description.
	 *
	 * @param description the description
	 * @param paginationInfo the pagination info
	 * @return the paged entity by find description
	 */
	private PagedEntity<Category> getPagedEntityByFindDescription(String description, PaginationInfo paginationInfo) {
		List<Category> categories = daoCategory.findByDescription(description, paginationInfo.getEntitiesPerPage(), paginationInfo.getStart());
		
		return convertToPagedEntity(categories, paginationInfo);
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

}
