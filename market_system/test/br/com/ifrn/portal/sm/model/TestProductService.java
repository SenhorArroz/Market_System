package br.com.ifrn.portal.sm.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.ifrn.portal.sm.models.entities.Brand;
import br.com.ifrn.portal.sm.models.entities.Category;
import br.com.ifrn.portal.sm.models.entities.Product;
import br.com.ifrn.portal.sm.models.entities.UnitMeasurement;
import br.com.ifrn.portal.sm.models.infrastructure.DAOBrand;
import br.com.ifrn.portal.sm.models.infrastructure.DAOCategory;
import br.com.ifrn.portal.sm.models.infrastructure.DAOUnitMeasurement;
import br.com.ifrn.portal.sm.models.services.implementation.ProductService;

/**
 * @author erikv
 * @version 1.0 9
 * @system EntityAnonymService.java
 * @date 16:14:43 20 de jan. de 2023 2023
 * @system_unity_description Classe de teste dos serviços da entidade produto.
 * 
 */
class TestProductService {

	//@Test
	void testInsertProductSucifly(){
		
		DAOBrand daoBrand = new DAOBrand();
		DAOCategory daoCategory = new DAOCategory();
		DAOUnitMeasurement daoUnitMeasurement = new DAOUnitMeasurement();
		
		Brand brand = daoBrand.findById(8L);
		Category category = daoCategory.findById(4L);
		UnitMeasurement measurement = daoUnitMeasurement.findById(4L);
		
		LocalDate dateFabrication = LocalDate.parse("2020-12-20");
		LocalDate dateValid = LocalDate.parse("2025-02-03");
		
		byte[] imagen = getByteImage("C:/Users/erikv/Downloads/treloso.jpg");
		
		Product product = new Product(category, measurement, brand, "327632", "Biscoito treloso", dateFabrication, dateValid, imagen);
		
		ProductService productService = new ProductService();
		assertTrue(productService.insert(product));
		
	}
	
	@Test
	void testInsertProductFailed(){
		
		DAOBrand daoBrand = new DAOBrand();
		DAOCategory daoCategory = new DAOCategory();
		DAOUnitMeasurement daoUnitMeasurement = new DAOUnitMeasurement();
		
		Brand brand = daoBrand.findById(8L);
		Category category = daoCategory.findById(4L);
		UnitMeasurement measurement = daoUnitMeasurement.findById(4L);
		
		LocalDate dateFabrication = LocalDate.parse("2020-12-20");
		LocalDate dateValid = LocalDate.parse("2025-02-03");
		
		byte[] imagen = getByteImage("C:/Users/erikv/Downloads/treloso.jpg");
		
		Product product = new Product(category, measurement, brand, "327632", "Biscoito treloso", dateFabrication, dateValid, imagen);
		
		ProductService productService = new ProductService();
		
		/*InvalidDataException exception = null;
		try {
			productService.insert(product);
		} catch (InvalidDataException e) {
			System.out.println(e.getListContraintViolations().get(0));
		}*/
		//assertThrows(exception.getClass() , () -> );
	}
	
	private byte[] getByteImage(String patch) {
		try {
			return Files.readAllBytes(new File(patch).toPath());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
