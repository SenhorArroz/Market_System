package br.com.ifrn.portal.sm.models.services;

import java.util.List;

import br.com.ifrn.portal.sm.models.validations.SimpleConstraintViolations;

/**
 * @author erikv
 * @version 1.0 9
 * @system EntityService.java
 * @date 17:52:47 9 de jan. de 2023 2023
 * @param <T> entity type to implement the service
 * @system_unity_description Interface que declara os métodos de servicos das classes models da aplicação
 */

public interface EntityService<T> {
	
	public abstract boolean insert(T entity);
	
	public abstract T findById(Long id);
	
	public abstract List<T> findByName(String name);

	public abstract List<T> findAll();
	
	public abstract List<T> findAll(int limit, int skip);
	
	public abstract boolean update(T entity);
	
	public abstract boolean delete(T entity);
	
	public abstract boolean validate(T entity);
	
	public List<SimpleConstraintViolations> getListViolations();

}
