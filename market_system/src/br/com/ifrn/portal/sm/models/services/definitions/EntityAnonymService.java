package br.com.ifrn.portal.sm.models.services.definitions;

import java.util.List;

import br.com.ifrn.portal.sm.models.services.utilities.PagedEntity;
import br.com.ifrn.portal.sm.models.services.utilities.PaginationInfo;
import br.com.ifrn.portal.sm.models.validations.SimpleConstraintViolations;

/**
 * @author erikv
 * @version 1.0 9
 * @system EntityAnonymService.java
 * @date 17:52:47 9 de jan. de 2023 2023
 * @param <T> entity type to implement the service
 * @system_unity_description Interface que declara os métodos de serviços das classes modelos 
 * da aplicação que não têm uma identidade de busca como um nome ou uma descrição.
 * 
 */

public interface EntityAnonymService<T> {
	
	public abstract boolean insert(T entity);
	
	public abstract T findById(Long id);
	
	public abstract PagedEntity<T> findAll();
	
	public abstract PagedEntity<T> findAll(int numberPage);
	
	public abstract boolean update(T entity);
	
	public abstract boolean delete(T entity);
	
	public abstract boolean validate(T entity);
	
	public abstract PaginationInfo calculatePagination(int numberPage);
	
	public abstract PagedEntity<T> convertToPagedEntity(List<T> entities, PaginationInfo paginationInfo);
	
	public List<SimpleConstraintViolations> getListViolations();

}
