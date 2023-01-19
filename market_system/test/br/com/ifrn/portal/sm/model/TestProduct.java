package br.com.ifrn.portal.sm.model;



import org.junit.jupiter.api.Test;

import br.com.ifrn.portal.sm.models.entities.Product;
import br.com.ifrn.portal.sm.models.infrastructure.DAOProduct;

class TestProduct {

	@Test
	void createProduct() {	
		
		System.out.println("Funciona");
		//UnitMeasurement measurement = new UnitMeasurement("", null);
		Product product = new Product();
		
	}
	
	void findByBarCode() {
		DAOProduct daoProduct = new DAOProduct();
		
		daoProduct.findByBarCode(null);
	}
}
