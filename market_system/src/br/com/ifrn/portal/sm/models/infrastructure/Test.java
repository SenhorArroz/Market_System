package br.com.ifrn.portal.sm.models.infrastructure;

import br.com.ifrn.portal.sm.models.entities.Category;
import br.com.ifrn.portal.sm.models.entities.Product;
import br.com.ifrn.portal.sm.models.services.ProductService;
import br.com.ifrn.portal.sm.models.services.utilities.PagedEntity;

public class Test {

	public static void main(String[] args) {
		
		DAOProduct daoProduct = new DAOProduct();
	/*	UnitMeasurement measurement = new UnitMeasurement("KG", "quilograma");
		Brand brand = new Brand("Dumel");
		Category category = new Category("empacotado");
		
		Product product = new Product(category, measurement, brand, "4353", "Açucar dumel 1 kg", new Date("2022/01/03"), new Date("2024/03/23"),  new Byte[] {12});
		
		daoProduct.insertAtomic(product);*/
		ProductService productService = new ProductService();
		
		DAOCategory daoCategory = new DAOCategory();
		
		Category category = daoCategory.findById(4L);
		
		PagedEntity<Product> paged = productService.findByCategory(category, 0);
		paged.getPaginatedEntityList().stream().forEach(e -> System.out.println(e.getDescription()));
		System.out.println(paged.getPaginationInfo().toString());
		
		//daoProduct.findByBarCode(848374L);
	}

}
