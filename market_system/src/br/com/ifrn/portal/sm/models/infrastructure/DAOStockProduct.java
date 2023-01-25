package br.com.ifrn.portal.sm.models.infrastructure;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.ifrn.portal.sm.models.entities.Product;
import br.com.ifrn.portal.sm.models.entities.StockProduct;

/**
 * The Class DAOStockProduct.
 *
 * @author erikv
 * @version 1.0
 * @system Product.java
 * @date 21:21:53 19 de jan. de 2023
 * @system_unity_description Classe de reposável por implementar os códigos de acesso a
 * dados da entidade estoque e as consultas especificas da entidade estoque como a busca 
 * por a quantidade de produtos abaixo e acima de um valor passado.
 */

public class DAOStockProduct extends DAOGeneric<StockProduct>{

	/**
	 * Instantiates a new DAO stock product.
	 */
	public DAOStockProduct() {
		super(StockProduct.class);
	}
	
	/**
	 * Find by product.
	 *
	 * @param product the product
	 * @return the stock product
	 */
	public StockProduct findByProduct(Product product) {
		TypedQuery<StockProduct> query = getEm().createNamedQuery("findByProduct", StockProduct.class);
		query.setParameter("searchProduct", product);
		
		return query.getSingleResult();
	}
	
	/**
	 * Find by quantity less than.
	 *
	 * @param quantity the quantity
	 * @param limit the limit
	 * @param skip the skip
	 * @return the list
	 */
	public List<StockProduct> findByQuantityLessThan(int quantity, int limit, int skip) {
		TypedQuery<StockProduct> query = getEm().createNamedQuery("findByQuantityLessThan", StockProduct.class);
		query.setParameter("searchDescription", quantity);
		query.setFirstResult(skip);
		query.setMaxResults(limit);
		
		return query.getResultList();
	}
	
	/**
	 * Find by quantity greater than.
	 *
	 * @param quantity the quantity
	 * @param limit the limit
	 * @param skip the skip
	 * @return the list
	 */
	public List<StockProduct> findByQuantityGreaterThan(int quantity, int limit, int skip) {
		TypedQuery<StockProduct> query = getEm().createNamedQuery("findByQuantityGreaterThan", StockProduct.class);
		query.setParameter("searchDescription", quantity);
		query.setFirstResult(skip);
		query.setMaxResults(limit);
		
		return query.getResultList();
	}
	
	/**
	 * Gets the quantity stock products per filter quantity less than.
	 *
	 * @param quantity the quantity
	 * @return the quantity stock products per filter quantity less than
	 */
	public int getQuantityStockProductsPerFilterQuantityLessThan(int quantity) {
		TypedQuery<Long> query = getEm().createNamedQuery("numberStockProductsPerFilterQuantityLessThan", Long.class);
		query.setParameter("searchQuantity", quantity);
		
		long value = query.getSingleResult();
		
		return (int) value;
	}
	
	/**
	 * Gets the quantity stock products per filter quantity greater than.
	 *
	 * @param quantity the quantity
	 * @return the quantity stock products per filter quantity greater than
	 */
	public int getQuantityStockProductsPerFilterQuantityGreaterThan(int quantity) {
		TypedQuery<Long> query = getEm().createNamedQuery("nnumberStockProductsPerFilterQuantityGreaterThan", Long.class);
		query.setParameter("searchQuantity", quantity);
		
		long value = query.getSingleResult();
		
		return (int) value;
	}

}
