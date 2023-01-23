package br.com.ifrn.portal.sm.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import br.com.ifrn.portal.sm.models.entities.Brand;
import br.com.ifrn.portal.sm.models.entities.Category;
import br.com.ifrn.portal.sm.models.entities.Product;
import br.com.ifrn.portal.sm.models.entities.StockProduct;
import br.com.ifrn.portal.sm.models.entities.UnitMeasurement;
import br.com.ifrn.portal.sm.models.services.implementation.BrandService;
import br.com.ifrn.portal.sm.models.services.implementation.CategoryService;
import br.com.ifrn.portal.sm.models.services.implementation.StockProductService;
import br.com.ifrn.portal.sm.models.services.implementation.UnitMeasurementService;

class TestStockProductService {

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		
		//Instancia os serviços
		BrandService brandService = new BrandService();
		CategoryService categoryService = new CategoryService();
		UnitMeasurementService unitMeasurementService = new UnitMeasurementService();
		
		//Busca no banco de dados a marca/categoria/unidade de medida a qual eu quero relacionar ao produto
		Brand brand = brandService.findById(8L);
		Category category = categoryService.findById(4L);
		UnitMeasurement measurement = unitMeasurementService.findById(4L);
		
		//definiçao das datas de fabricação e validade do produto
		LocalDate dateFabrication = LocalDate.parse("2020-12-20");
		LocalDate dateValid = LocalDate.parse("2025-02-03");
		
		//Chamando o metodo para buscar a imagem no computador do usuário
		byte[] imagen = getByteImage("C:/Users/erikv/Downloads/treloso.jpg");
		
		/*Criação do objeto produto passando os atributos dele
			é passado a categoria, unidade de medida e a marca que foram buscados no banco de dados,
			codigo de barras, descrição do produto, a data de fabricação e validade. O último e um 
			array de Bytes que corresponde a uma imagem 
		*/
		Product product = new Product(category, measurement, brand, "327632", "Biscoito treloso", dateFabrication, dateValid, imagen);
		
		/*Criando uma registro desse produto no estoque
		é passado a quantidade do produto, por padrão é 0, o valor unitário do produto, a 
		pocentagem de venda e o desconto do produto*/
		StockProduct stockProduct = new StockProduct(product, 0, 20.1, 0.4, 0.1);
		
		//Chamando a classe de servico do estoque
		StockProductService stockProductService = new StockProductService();
		
		//realizando uma requisição(Pedido) para poder inserir produto e os registros dele no estoque
		stockProductService.insert(stockProduct);
	}
	
	//método de busca
	private byte[] getByteImage(String patch) {
		try {
			return Files.readAllBytes(new File(patch).toPath());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
