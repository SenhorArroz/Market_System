package br.com.ifrn.portal.sm.models.infrastructure;

import br.com.ifrn.portal.sm.models.entities.Category;
import br.com.ifrn.portal.sm.models.services.implementation.CategoryService;
import br.com.ifrn.portal.sm.models.services.utilities.PagedEntity;

public class Test {

	public static void main(String[] args) {
		
		/*DAOProduct daoProduct = new DAOProduct();
		UnitMeasurement measurement = new UnitMeasurement("pc", "pacote");
		Brand brand = new Brand("Maratá");
		Category category = new Category("consumivel");
		
		Calendar dataFabricacao = Calendar.getInstance();
		dataFabricacao.setTime(new Date("2021/03/23"));
		
		Calendar dataValidade = Calendar.getInstance();
		dataValidade.setTime(new Date("2024/03/23"));
		
		Product product = new Product(category, measurement, brand, "4353", "Açucar dumel 1 kg", dataFabricacao, dataValidade, new Byte[] {12});
		
		daoProduct.insertAtomic(product);*/
		/*ProductService productService = new ProductService();
		
		DAOUnitMeasurement  daoUnitMeasurement = new DAOUnitMeasurement();
		UnitMeasurement measurement = daoUnitMeasurement.findById(5L);
		//Category category = daoCategory.findById(5L);*/
		
		CategoryService categoryService = new CategoryService();	
		
		//brandService.insert(new Brand("Unilever"));
		
 		//Category category =  categoryService.findById(7L);
		
		PagedEntity<Category> paged =  categoryService.findByName("em", 0);
		paged.getPaginatedEntityList().stream().forEach(e -> System.out.println(e.getDescription()));
		System.out.println(paged.getPaginationInfo().toString());
		
		//daoProduct.findByBarCode(848374L);
	}

}
