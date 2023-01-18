package br.com.ifrn.portal.sm.models.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import lombok.Getter;

/**
 * The Class DAOGeneric.
 *
 * @author erikv
 * @version 1.0
 * @param <T> the generic type to persistence
 * @system DAOGeneric.java
 * @date 11:42:21 8 de jan. de 2023 2023
 * @system_unity_description Classe genérica responsável por separar as regras de negócio do sistema
 * dos códigos de acesso ao banco de dados proporcionando assim uma maior organização, manutenibilidade
 *  e tarefas bem definidas para cada parte do sistema. 
 *  Essa classe disponibiliza métodos genéricos para a realização das operações basicas de um sistema e 
 *  que toda entidade tem, proporcionando assim um reuso de código via heraça pelas demais classes.
 */

/**
 * Gets the classe.
 *
 * @return the classe
 */
@Getter
public abstract class DAOGeneric<T> {
	
	/** The emf. */
	private static EntityManagerFactory emf;
	
	/** The em. */
	private EntityManager em;
	
	/** The classe. */
	private Class<T> classe;
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("market_system");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Instantiates a new DAO generic.
	 */
	public DAOGeneric() {
		this(null);
	}
	
	/**
	 * Instantiates a new DAO generic.
	 *
	 * @param classe the classe
	 */
	public DAOGeneric(Class<T> classe) {
		this.classe = classe;
		em = emf.createEntityManager();
	}

	/**
	 * Open transaction.
	 *
	 * @return the DAO generic
	 */
	public DAOGeneric<T> openTransaction(){
		em.getTransaction().begin();
		return this;
	}
	
	/**
	 * Close transaction.
	 *
	 * @return the DAO generic
	 */
	public DAOGeneric<T> closeTransaction(){
		em.getTransaction().commit();
		return this;
	}
	
	/**
	 * Insert.
	 *
	 * @param entity the entity
	 * @return the DAO generic
	 */
	public DAOGeneric<T> insert(T entity) {
		em.persist(entity);
		return this;
	}
	
	/**
	 * Insert atomic.
	 *
	 * @param entity the entity
	 * @return the DAO generic
	 */
	public DAOGeneric<T> insertAtomic(T entity) {
		return openTransaction().insert(entity).closeTransaction();
	}
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the t
	 */
	public T findById(Long id) {
		return em.find(classe, id);
	}
	
	/**
	 * Find all.
	 *
	 * @param limit the limit
	 * @param skip the skip
	 * @return the list
	 */
	public List<T> findAll(int limit, int skip){
		
		if(classe == null) {
			throw new UnsupportedOperationException("The class is null");
		}
		
		String jpql = "select e from " + classe.getName() +" e";
		
		TypedQuery<T> query = em.createQuery(jpql, classe);
		query.setFirstResult(skip);
		query.setMaxResults(limit);
		
		return query.getResultList();
	}
	
	/**
	 * Gets the entity quantity.
	 *
	 * @return the entity quantity
	 */
	public Long getEntityQuantity() {
		if(classe == null) {
			throw new UnsupportedOperationException("The class is null");
		}
		
		String jpql = "select count(e.id) from " + classe.getName() + " e";
		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		
		return query.getSingleResult();
	}
	
	/**
	 * Update.
	 *
	 * @param entity the entity
	 * @return the DAO generic
	 */
	public DAOGeneric<T> update(T entity){
		em.merge(entity);
		return this;
	}
	
	/**
	 * Update atomic.
	 *
	 * @param entity the entity
	 * @return the DAO generic
	 */
	public DAOGeneric<T> updateAtomic(T entity){
		return openTransaction().update(entity).closeTransaction();
	}
	
	/**
	 * Delete.
	 *
	 * @param entity the entity
	 * @return the DAO generic
	 */
	public DAOGeneric<T> delete(T entity){
		em.remove(entity);
		return this;
	}
	
	public DAOGeneric<T> deleteAtomic(T entity){
		return openTransaction().delete(entity).closeTransaction();
	}
	
	/**
	 * Detach.
	 *
	 * @param entity the entity
	 * @return the DAO generic
	 */
	public DAOGeneric<T> detach(T entity){
		em.detach(entity);
		return this;
	}
	
	/**
	 * Close em.
	 */
	public void closeEm(){
		em.clear();
	}
	
}
