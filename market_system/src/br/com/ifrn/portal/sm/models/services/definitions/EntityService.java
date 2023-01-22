package br.com.ifrn.portal.sm.models.services.definitions;

import br.com.ifrn.portal.sm.models.services.utilities.PagedEntity;

/**
 * @author erikv
 * @version 1.0 9
 * @system EntityService.java
 * @date 11:39:22 20 de jan. de 2023 2023
 * @param <T> entity type to implement the service
 * @system_unity_description Interface que declara os m�todos de servi�os como busca da entidade pelo nome ou 
 * descri��o.
 * 
 */

public interface EntityService<T> {
	
	public abstract PagedEntity<T> findByName(String name);
	
	public abstract PagedEntity<T> findByName(String name, int numberPage);

}
