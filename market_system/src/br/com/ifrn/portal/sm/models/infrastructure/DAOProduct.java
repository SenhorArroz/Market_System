package br.com.ifrn.portal.sm.models.infrastructure;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.ifrn.portal.sm.models.entities.Brand;
import br.com.ifrn.portal.sm.models.entities.Category;
import br.com.ifrn.portal.sm.models.entities.Product;
import br.com.ifrn.portal.sm.models.entities.UnitMeasurement;

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
	
	public List<Product> findByDescription(String description, int limit, int skip) {
		TypedQuery<Product> query = getEm().createNamedQuery("findByDescription", Product.class);
		query.setParameter("searchDescription", "%" + description + "%");
		query.setFirstResult(skip);
		query.setMaxResults(limit);
		return query.getResultList();
	}
	
	public List<Product> findByCategory(Category category, int limit, int skip){
		TypedQuery<Product> query = getEm().createNamedQuery("findByCategory", Product.class);
		query.setParameter("category", category);
		query.setFirstResult(skip);
		query.setMaxResults(limit);
		
		return query.getResultList();
	}
	
	public List<Product> findByBrand(Brand brand, int limit, int skip){
		TypedQuery<Product> query = getEm().createNamedQuery("findByBrand", Product.class);
		query.setParameter("brand", brand);
		query.setFirstResult(skip);
		query.setMaxResults(limit);
		
		return query.getResultList();
	}
	
	public List<Product> findByUnitMeasurement(UnitMeasurement unitMeasurement, int limit, int skip){
		TypedQuery<Product> query = getEm().createNamedQuery("findByUnitMeasurement", Product.class);
		query.setParameter("unitMeasurement", unitMeasurement);
		query.setFirstResult(skip);
		query.setMaxResults(limit);
		
		return query.getResultList();
	}
	
	public int getQuantityProductsPerFilterDescription(String searchValue) {
		TypedQuery<Long> query = getEm().createNamedQuery("numberProductsPerFilterDescription", Long.class);
		query.setParameter("searchValue", "%" + searchValue + "%");
		
		long quantity = query.getSingleResult();
		
		return (int) quantity;
	}
	
	public int getQuantityProductsPerFilterCategory(Category category) {
		TypedQuery<Long> query = getEm().createNamedQuery("numberProductsPerFilterCategory", Long.class);
		query.setParameter("category", category);
		
		long quantity = query.getSingleResult();
		
		return (int) quantity;
	}
	
	public int getQuantityProductsPerFilterBrand(Brand brand) {
		TypedQuery<Long> query = getEm().createNamedQuery("numberProductsPerFilterBrand", Long.class);
		query.setParameter("brand", brand);
		
		long quantity = query.getSingleResult();
		
		return (int) quantity;
	}
	
	public int getQuantityProductsPerFilterUnitMeasurement(UnitMeasurement unitMeasurement) {
		TypedQuery<Long> query = getEm().createNamedQuery("numberProductsPerFilterUnitMeasurement", Long.class);
		query.setParameter("unitMeasurement", unitMeasurement);
		
		long quantity = query.getSingleResult();
		
		return (int) quantity;
	}

}
