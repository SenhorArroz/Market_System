package br.com.ifrn.portal.sm.model;



import br.com.ifrn.portal.sm.models.entities.Product;
import br.com.ifrn.portal.sm.models.entities.UnitMeasurement;
import br.com.ifrn.portal.sm.models.infrastructure.DAOProduct;

class TestProduct {

	void createProduct() {	
		
		UnitMeasurement measurement = new UnitMeasurement("", null);
		Product product = new Product();
		
	}
	
	void findByBarCode() {
		DAOProduct daoProduct = new DAOProduct();
		
		daoProduct.findByBarCode(null);
	}
}
