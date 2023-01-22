package br.com.ifrn.portal.sm.models.services.implementation;

import java.util.ArrayList;
import java.util.List;

import br.com.ifrn.portal.sm.models.entities.UnitMeasurement;
import br.com.ifrn.portal.sm.models.exceptions.InvalidDataException;
import br.com.ifrn.portal.sm.models.infrastructure.DAOUnitMeasurement;
import br.com.ifrn.portal.sm.models.services.definitions.EntityAnonymService;
import br.com.ifrn.portal.sm.models.services.definitions.EntityService;
import br.com.ifrn.portal.sm.models.services.definitions.Service;
import br.com.ifrn.portal.sm.models.services.utilities.PagedEntity;
import br.com.ifrn.portal.sm.models.services.utilities.Pagination;
import br.com.ifrn.portal.sm.models.services.utilities.PaginationInfo;
import br.com.ifrn.portal.sm.models.validations.SimpleConstraintViolations;

/**
 * @version 1.0
 * @system CadastroController.java
 * @author erikv
 * @date 09:48:36 19 de jan. de 2023
 * @system_unity_description Classe responsável por implementar os serviços da entidade unidade de medida,
 * contendo os métodos herdados do DAO Generic e implementando os métodos específico de busca por meio da
 * descrição e sigla da unidade de medida.
 * 
 */

public class UnitMeasurementService extends Service<UnitMeasurement> implements EntityAnonymService<UnitMeasurement>, EntityService<UnitMeasurement>{

	private DAOUnitMeasurement daoUnitMeasurement;

	public UnitMeasurementService() {
		daoUnitMeasurement = new DAOUnitMeasurement();
	}
	
	/**
	 * Insert.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
	@Override
	public boolean insert(UnitMeasurement entity) {
		
		boolean isValid = validate(entity);
		if(isValid) {
			daoUnitMeasurement.insertAtomic(entity);
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
	public UnitMeasurement findById(Long id) {
		try {
			UnitMeasurement unitMeasurement = daoUnitMeasurement.findById(id);
			return unitMeasurement;
			
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
	public PagedEntity<UnitMeasurement> findByName(String name) {
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
	public PagedEntity<UnitMeasurement> findByName(String name, int numberPage) {
		if(!name.isBlank() && numberPage > 0) {
			
			PaginationInfo paginationInfo = calculatePaginationWithFilterName(name, numberPage);
			PagedEntity<UnitMeasurement> pagedEntity = getPagedEntityByFindName(name, paginationInfo);
			
			return pagedEntity;
		}else {
			throw new IllegalArgumentException("Nome do produto ou página inválida");
		}
	}
	
	public PagedEntity<UnitMeasurement> findByAcronym(String acronym, int numberPage) {
		if(!acronym.isBlank() && numberPage > 0) {
			
			PaginationInfo paginationInfo = calculatePaginationWithFilterAcronym(acronym, numberPage);
			PagedEntity<UnitMeasurement> pagedEntity = getPagedEntityByFindAcronym(acronym, paginationInfo);
			
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
	public PagedEntity<UnitMeasurement> findAll() {
		return findAll(1);
	}


	/**
	 * Find all.
	 *
	 * @param numberPage the number page
	 * @return the paged entity
	 */
	@Override
	public PagedEntity<UnitMeasurement> findAll(int numberPage) {
		if(numberPage <= 0) {
			throw new IllegalArgumentException("Número da página inválido");
		}
		
		PaginationInfo paginationInfo = calculatePagination(numberPage);
		
		List<UnitMeasurement> unitMeasurements = daoUnitMeasurement.findAll(
				paginationInfo.getEntitiesPerPage(), paginationInfo.getStart());
		
		PagedEntity<UnitMeasurement> pagedEntity = new PagedEntity<UnitMeasurement>(unitMeasurements, paginationInfo);
		 
		return pagedEntity;
	}

	/**
	 * Update.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
	@Override
	public boolean update(UnitMeasurement entity) {
		boolean isValid = validate(entity);
		
		if (isValid) {
			daoUnitMeasurement.updateAtomic(entity);
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
	public boolean delete(UnitMeasurement entity) {
		try {
			daoUnitMeasurement.deleteAtomic(entity);
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
	public boolean validate(UnitMeasurement entity) {
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
	private PagedEntity<UnitMeasurement> getPagedEntityByFindName(String name, PaginationInfo paginationInfo) {
		List<UnitMeasurement> brands = daoUnitMeasurement.findByName(name, paginationInfo.getEntitiesPerPage(), paginationInfo.getStart());
		
		return convertToPagedEntity(brands, paginationInfo);
	}
	
	private PagedEntity<UnitMeasurement> getPagedEntityByFindAcronym(String acronym, PaginationInfo paginationInfo) {
		List<UnitMeasurement> brands = daoUnitMeasurement.findByAcronym(acronym, paginationInfo.getEntitiesPerPage(), paginationInfo.getStart());
		
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
		
		int quantity = Integer.parseInt(daoUnitMeasurement.getEntityQuantity().toString());
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
		
		int quantity = daoUnitMeasurement.getQuantityMeasurementPerFilterName(searchValue);
		PaginationInfo infoPagination =  pagination.getPagination(quantity, numberPage);
		
		return infoPagination;
	}
	
	public PaginationInfo calculatePaginationWithFilterAcronym(String acronym, int numberPage) {
		Pagination pagination = new Pagination();
		
		int quantity = daoUnitMeasurement.getQuantityMeasurementPerFilterAcronym(acronym);
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
	public PagedEntity<UnitMeasurement> convertToPagedEntity(List<UnitMeasurement> entities, PaginationInfo paginationInfo) {
		return new PagedEntity<UnitMeasurement>(entities, paginationInfo);
	}
	
}
