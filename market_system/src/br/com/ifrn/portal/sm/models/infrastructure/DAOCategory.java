package br.com.ifrn.portal.sm.models.infrastructure;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.ifrn.portal.sm.models.entities.Category;

/**
 * @author erikv
 * @version 1.0
 * @system DAOProduto.java
 * @date 15:34:55 16 de jan. de 2023 2023
 */

public class DAOCategory extends DAOGeneric<Category>{

	public DAOCategory() {
		super(Category.class);
	}
	
	public List<Category> findByDescription(String description, int limit, int skip) {
		TypedQuery<Category> query = getEm().createNamedQuery("findCategoryByDescription", Category.class);
		query.setParameter("description", "%" + description + "%");
		query.setFirstResult(skip);
		query.setMaxResults(limit);
		
		return query.getResultList();
	}
	
	public int getQuantityCategoriesPerFilterDescription(String description) {
		TypedQuery<Long> query = getEm().createNamedQuery("numberCategoryPerFilterDescription", Long.class);
		query.setParameter("description", "%" + description + "%");
		
		long quantity = query.getSingleResult();
		
		return (int) quantity;
	}
}
