package br.com.ifrn.portal.sm.models.services.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.ifrn.portal.sm.models.entities.Product;
import br.com.ifrn.portal.sm.models.entities.ProductOrder;
import br.com.ifrn.portal.sm.models.entities.SaleReturnItem;
import br.com.ifrn.portal.sm.models.entities.StockProduct;
import br.com.ifrn.portal.sm.models.entities.enums.OrderStatus;
import br.com.ifrn.portal.sm.models.exceptions.InvalidDataException;
import br.com.ifrn.portal.sm.models.infrastructure.DAOProduct;
import br.com.ifrn.portal.sm.models.infrastructure.DAOStockProduct;
import br.com.ifrn.portal.sm.models.services.definitions.EntityAnonymService;
import br.com.ifrn.portal.sm.models.services.definitions.Service;
import br.com.ifrn.portal.sm.models.services.utilities.PagedEntity;
import br.com.ifrn.portal.sm.models.services.utilities.Pagination;
import br.com.ifrn.portal.sm.models.services.utilities.PaginationInfo;
import br.com.ifrn.portal.sm.models.validations.SimpleConstraintViolations;

/**
 * 
 * @author erikv
 * @version 1.0
 * @system Product.java
 * @date 06:40:12 20 de jan. de 2023
 * @system_unity_description Classe de reposável por implementar os 
 * seviços da entidade estoque.s
 * 
 */

public class StockProductService extends Service<StockProduct> implements EntityAnonymService<StockProduct>{

	private DAOStockProduct daoStockProduct;
	
	public StockProductService() {
		daoStockProduct = new DAOStockProduct();
	}
	
	@Override
	public boolean insert(StockProduct entity) {

		boolean isValid = validate(entity);
		
		if (isValid) {
			DAOProduct daoProduct = new DAOProduct();
			daoStockProduct.insertAtomic(entity);
			
			Product productUpdated = setProductValueForSale(entity);
			daoProduct.updateAtomic(productUpdated);
			
			return true;
		}else {
			List<SimpleConstraintViolations> listViolations = getListViolations();
			throw new InvalidDataException(listViolations);
		}
	}
	
	public boolean insertProductOrder(ProductOrder order) {
		if(order.getOrderStatus() == OrderStatus.DELIVERED) {
			
			order.getItensOrder().stream().forEach(i -> {
				StockProduct stockProduct = findByProduct(i.getProduct());
				stockProduct.setQuantity(i.getQuantity());
				update(stockProduct);
			});
			
			return true;
		}else {
			SimpleConstraintViolations violation = new SimpleConstraintViolations(
					"Não pode ser inserido um pedido que foi cancelado ou que ainda não foi entregue ",
					order.getOrderStatus()
			);
			
			List<SimpleConstraintViolations> listViolations = 
					new ArrayList<>(Arrays.asList(violation));
			
			throw new InvalidDataException(listViolations);
		}
	}

	@Override
	public StockProduct findById(Long id) {
		try {
			StockProduct stockProduct = daoStockProduct.findById(id);
			return stockProduct;
			
		} catch (Exception e) {
			throw new IllegalArgumentException("Id inválido");
		}
	}
	
	public StockProduct findByProduct(Product product) {
		try {
			StockProduct stockProduct = daoStockProduct.findByProduct(product);
			return stockProduct;
			
		} catch (Exception e) {
			throw new IllegalArgumentException("Id inválido");
		}
	}

	public PagedEntity<StockProduct> findByQuantityLessThan(int quantity) {
		return findByQuantityLessThan(quantity);
	}

	/**
	 * Find by name.
	 *
	 * @param name       the name
	 * @param numberPage the number page
	 * @return the paged entity
	 */
	public PagedEntity<StockProduct> findByQuantityLessThan(int quantity, int numberPage) {
		if(quantity > 0 && numberPage > 0) {
			
			PaginationInfo paginationInfo = calculatePaginationWithFilterQuantityLessThan(quantity, numberPage);
			PagedEntity<StockProduct> pagedEntity = getPagedEntityByFindQuantityLessThan(quantity, paginationInfo);
			
			return pagedEntity;
		}else {
			throw new IllegalArgumentException("Quantidade de produto ou página inválida");
		}
	}
	
	public PagedEntity<StockProduct> findByQuantityGreaterThan(int quantity) {
		return findByQuantityGreaterThan(quantity);
	}
	
	/**
	 * Find by name.
	 *
	 * @param name       the name
	 * @param numberPage the number page
	 * @return the paged entity
	 */
	public PagedEntity<StockProduct> findByQuantityGreaterThan(int quantity, int numberPage) {
		if(quantity > 0 && numberPage > 0) {
			
			PaginationInfo paginationInfo = calculatePaginationWithFilterQuantityGreaterThan(quantity, numberPage);
			PagedEntity<StockProduct> pagedEntity = getPagedEntityByFindQuantityGreaterThan(quantity, paginationInfo);
			
			return pagedEntity;
		}else {
			throw new IllegalArgumentException("Quantidade de produto ou página inválida");
		}
	}

	/**
	 * Find all.
	 *
	 * @return the paged entity
	 */
	@Override
	public PagedEntity<StockProduct> findAll() {
		return findAll(1);
	}

	/**
	 * Find all.
	 *
	 * @param numberPage the number page
	 * @return the paged entity
	 */
	@Override
	public PagedEntity<StockProduct> findAll(int numberPage) {
		if(numberPage <= 0) {
			throw new IllegalArgumentException("Número da página inválido");
		}
		
		PaginationInfo paginationInfo = calculatePagination(numberPage);
		
		List<StockProduct> stockProducts = daoStockProduct.findAll(
				paginationInfo.getEntitiesPerPage(), paginationInfo.getStart());
		
		PagedEntity<StockProduct> pagedEntity = new PagedEntity<StockProduct>(stockProducts, paginationInfo);
		 
		return pagedEntity;
	}

	/**
	 * Update.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
	@Override
	public boolean update(StockProduct entity) {
		boolean isValid = validate(entity);
		
		if (isValid) {
			DAOProduct daoProduct = new DAOProduct();
			daoStockProduct.updateAtomic(entity);
			
			Product productUpdated = setProductValueForSale(entity);
			daoProduct.updateAtomic(productUpdated);
			
			return true;
		}else {
			List<SimpleConstraintViolations> listViolations = getListViolations();
			throw new InvalidDataException(listViolations);
		}
	}
	
	public boolean decreaseTheQuantityOfProductPerSale(String sale) {
		if(!sale.isBlank()) {
			new ArrayList<Object>().stream().forEach(i -> {
				StockProduct stockProduct = findByProduct(new Product());
				stockProduct.setQuantity(stockProduct.getQuantity()); // - item.getQuantity
				
				update(stockProduct);
			});
			
			return true;
		}else {
			
			return false;
		}
		
	}
	
	public boolean returnProdutcs(List<SaleReturnItem> saleReturnItens) {
		
		saleReturnItens.stream().forEach(i -> {
				StockProduct stockProduct = findByProduct(new Product()); //item.getProduct()
				stockProduct.setQuantity(stockProduct.getQuantity() + i.getReturnQuantity());
				
				update(stockProduct);
		});
		
		return false;
	}

	/**
	 * Delete.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
	@Override
	public boolean delete(StockProduct entity) {
		try {
			daoStockProduct.deleteAtomic(entity);
			return true;
			
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível remover a marca");
		}
	}

	/**
	 * Validate.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
	@Override
	public boolean validate(StockProduct entity) {
		setVioletions(getValidator().validate(entity));
		
		if (getVioletions().size() == 0) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Gets the list violations.
	 *
	 * @return the list violations
	 */
	@Override
	public List<SimpleConstraintViolations> getListViolations() {
		if (!getVioletions().isEmpty()) {
			
			List<SimpleConstraintViolations> listViolations = new ArrayList<>();
			
			getVioletions().stream().toList().forEach(v -> 
			listViolations.add(new SimpleConstraintViolations(v.getMessage(), v.getInvalidValue())));
			
			return listViolations;
		}else {
			throw new RuntimeException("Nenhuma violação encontrada");
		}
	}
	
	/**
	 * Gets the paged entity by find name.
	 *
	 * @param name           the name
	 * @param paginationInfo the pagination info
	 * @return the paged entity by find name
	 */
	private PagedEntity<StockProduct> getPagedEntityByFindQuantityLessThan(int quantity, PaginationInfo paginationInfo) {
		List<StockProduct> brands = daoStockProduct.findByQuantityLessThan(quantity, paginationInfo.getEntitiesPerPage(), paginationInfo.getStart());
		
		return convertToPagedEntity(brands, paginationInfo);
	}
	
	private PagedEntity<StockProduct> getPagedEntityByFindQuantityGreaterThan(int quantity, PaginationInfo paginationInfo) {
		List<StockProduct> brands = daoStockProduct.findByQuantityGreaterThan(quantity, paginationInfo.getEntitiesPerPage(), paginationInfo.getStart());
		
		return convertToPagedEntity(brands, paginationInfo);
	}

	/**
	 * Calculate pagination.
	 *
	 * @param numberPage the number page
	 * @return the pagination info
	 */
	@Override
	public PaginationInfo calculatePagination(int numberPage) {
		Pagination pagination = new Pagination();
		
		int quantity = Integer.parseInt(daoStockProduct.getEntityQuantity().toString());
		PaginationInfo infoPagination =  pagination.getPagination(quantity, numberPage);
		
		return infoPagination;
	}
	
	/**
	 * Convert to paged entity.
	 *
	 * @param entities       the entities
	 * @param paginationInfo the pagination info
	 * @return the paged entity
	 */
	@Override
	public PagedEntity<StockProduct> convertToPagedEntity(List<StockProduct> entities, PaginationInfo paginationInfo) {
		return new PagedEntity<StockProduct>(entities, paginationInfo);
	}
	
	/**
	 * Calculate pagination with filter name.
	 *
	 * @param searchValue the search value
	 * @param numberPage  the number page
	 * @return the pagination info
	 */
	public PaginationInfo calculatePaginationWithFilterQuantityLessThan(int searchQuantity, int numberPage) {
		Pagination pagination = new Pagination();
		
		int quantity = daoStockProduct.getQuantityStockProductsPerFilterQuantityLessThan(searchQuantity);
		PaginationInfo infoPagination =  pagination.getPagination(quantity, numberPage);
		
		return infoPagination;
	}
	
	public PaginationInfo calculatePaginationWithFilterQuantityGreaterThan(int searchQuantity, int numberPage) {
		Pagination pagination = new Pagination();
		
		int quantity = daoStockProduct.getQuantityStockProductsPerFilterQuantityGreaterThan(searchQuantity);
		PaginationInfo infoPagination =  pagination.getPagination(quantity, numberPage);
		
		return infoPagination;
	}

	
	private Double calculateValueToSale(double unitValue, double percentage, double discount) {
		Double result = (unitValue * (1.0 + percentage)) * (1 - discount);
		
		return result;
	}
	
	private Product setProductValueForSale(StockProduct stockProduct) {
		Double saleValue = calculateValueToSale(stockProduct.getUnitValue(), 
				stockProduct.getPercentageSale(), stockProduct.getDiscount());
		
		Product product = stockProduct.getProduct();
		product.setSaleValue(saleValue);
		
		return product;
	}
}
