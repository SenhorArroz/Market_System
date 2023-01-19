/**
 * 
 */
package br.com.ifrn.portal.sm.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
 *
 */

class TestProductService {

	@Test
	void testInsert() throws ParseException {
		
		DAOBrand daoBrand = new DAOBrand();
		DAOCategory daoCategory = new DAOCategory();
		DAOUnitMeasurement daoUnitMeasurement = new DAOUnitMeasurement();
		
		Brand brand = daoBrand.findById(8L);
		Category category = daoCategory.findById(4L);
		UnitMeasurement measurement = daoUnitMeasurement.findById(4L);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new SimpleDateFormat("dd-MM-yyy").parse("02-01-2021"));
		
		Calendar calendarV = Calendar.getInstance();
		calendarV.setTime(new SimpleDateFormat("dd-MM-yyy").parse("02-01-2024"));
		
		Product product = new Product(category, measurement, brand, "734647", "Guaraná antártica", calendar, calendarV, new Byte[] {1});
		
		ProductService productService = new ProductService();
		productService.insert(product);
		
	
		
	}

}
