package br.com.ifrn.portal.sm.models.infrastructure;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.ifrn.portal.sm.models.entities.Brand;

/**
 * @author erikv
 * @version 1.0
 * @system DAOProduto.java
 * @date 15:34:45 16 de jan. de 2023 2023
 */

public class DAOBrand extends DAOGeneric<Brand>{

	public DAOBrand() {
		super(Brand.class);
	}
	
	public List<Brand> findByName(String name, int limit, int skip) {
		TypedQuery<Brand> query = getEm().createNamedQuery("findBrandByName", Brand.class);
		query.setParameter("name", "%" + name + "%");
		query.setFirstResult(skip);
		query.setMaxResults(limit);
		
		return query.getResultList();
	}
	
	public int getQuantityBrandsPerFilterName(String searchValue) {
		TypedQuery<Long> query = getEm().createNamedQuery("numberBrandsPerFilterName", Long.class);
		query.setParameter("searchValue", "%" + searchValue + "%");
		
		long quantity = query.getSingleResult();
		
		return (int) quantity;
	}

	
}
