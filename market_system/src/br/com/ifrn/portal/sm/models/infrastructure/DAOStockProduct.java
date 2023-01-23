package br.com.ifrn.portal.sm.models.infrastructure;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.ifrn.portal.sm.models.entities.Product;
import br.com.ifrn.portal.sm.models.entities.StockProduct;

/**
 * 
 * @author erikv
 * @version 1.0
 * @system Product.java
 * @date 21:21:53 19 de jan. de 2023
 * @system_unity_description Classe de reposável por implementar os códigos de acesso a 
 * dados da entidade estoque
 * 
 */

public class DAOStockProduct extends DAOGeneric<StockProduct>{

	public DAOStockProduct() {
		super(StockProduct.class);
	}
	
	public StockProduct findByProduct(Product product) {
		TypedQuery<StockProduct> query = getEm().createNamedQuery("findByProduct", StockProduct.class);
		query.setParameter("searchProduct", product);
		
		return query.getSingleResult();
	}
	
	public List<StockProduct> findByQuantityLessThan(int quantity, int limit, int skip) {
		TypedQuery<StockProduct> query = getEm().createNamedQuery("findByQuantityLessThan", StockProduct.class);
		query.setParameter("searchDescription", quantity);
		query.setFirstResult(skip);
		query.setMaxResults(limit);
		
		return query.getResultList();
	}
	
	public List<StockProduct> findByQuantityGreaterThan(int quantity, int limit, int skip) {
		TypedQuery<StockProduct> query = getEm().createNamedQuery("findByQuantityGreaterThan", StockProduct.class);
		query.setParameter("searchDescription", quantity);
		query.setFirstResult(skip);
		query.setMaxResults(limit);
		
		return query.getResultList();
	}
	
	public int getQuantityStockProductsPerFilterQuantityLessThan(int quantity) {
		TypedQuery<Long> query = getEm().createNamedQuery("numberStockProductsPerFilterQuantityLessThan", Long.class);
		query.setParameter("searchQuantity", quantity);
		
		long value = query.getSingleResult();
		
		return (int) value;
	}
	
	public int getQuantityStockProductsPerFilterQuantityGreaterThan(int quantity) {
		TypedQuery<Long> query = getEm().createNamedQuery("nnumberStockProductsPerFilterQuantityGreaterThan", Long.class);
		query.setParameter("searchQuantity", quantity);
		
		long value = query.getSingleResult();
		
		return (int) value;
	}

}
