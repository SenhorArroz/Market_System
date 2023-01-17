package br.com.ifrn.portal.sm.models.infrastructure;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.ifrn.portal.sm.models.entities.Product;

/**
 * @author erikv
 * @version 1.0
 * @system DAOProduto.java
 * @date 15:42:36 9 de jan. de 2023
 */

public class DAOProduct extends DAOGeneric<Product>{

	public DAOProduct() {
		super(Product.class);
	}
	
	public Product findByBarCode(String barCode) {
		try {
			TypedQuery<Product> query = getEm().createNamedQuery("findByBarCode", Product.class);
			query.setParameter("code", barCode);
			return query.getSingleResult();
			
		} catch (Exception e) {
			throw new IllegalArgumentException("Código de barras inválido");
		}
	}
	
	public List<Product> findByName(String name) {
		TypedQuery<Product> query = getEm().createNamedQuery("findByName", Product.class);
		query.setParameter("searchName", "%" + name + "%");
		return query.getResultList();
	}

}
