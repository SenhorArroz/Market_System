package br.com.ifrn.portal.sm.models.services;

import java.util.ArrayList;
import java.util.List;

import br.com.ifrn.portal.sm.models.entities.Brand;
import br.com.ifrn.portal.sm.models.entities.Product;
import br.com.ifrn.portal.sm.models.exceptions.InvalidDataException;
import br.com.ifrn.portal.sm.models.infrastructure.DAOBrand;
import br.com.ifrn.portal.sm.models.services.definitions.EntityService;
import br.com.ifrn.portal.sm.models.services.definitions.Service;
import br.com.ifrn.portal.sm.models.services.utilities.PagedEntity;
import br.com.ifrn.portal.sm.models.services.utilities.Pagination;
import br.com.ifrn.portal.sm.models.services.utilities.PaginationInfo;
import br.com.ifrn.portal.sm.models.services.utilities.ProductFindType;
import br.com.ifrn.portal.sm.models.validations.SimpleConstraintViolations;

public class BrandService  extends Service<Brand> implements EntityService<Brand>{

	private DAOBrand daoBrand;
	
	public BrandService() {
		daoBrand = new DAOBrand();
	}
	
	@Override
	public boolean insert(Brand entity) {
		
		boolean isValid = validate(entity);
		if(isValid) {
			daoBrand.insert(entity);
			return true;
		}else {
			List<SimpleConstraintViolations> listViolations = getListViolations();
			throw new InvalidDataException(listViolations);
		}
	}


	@Override
	public Brand findById(Long id) {
		try {
			Brand brand = daoBrand.findById(id);
			return brand;
			
		} catch (Exception e) {
			throw new IllegalArgumentException("Id inválido");
		}
	}


	@Override
	public PagedEntity<Brand> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PagedEntity<Brand> findByName(String name, int numberPage) {
		if(!name.isBlank() && numberPage > 0) {
			
			PaginationInfo paginationInfo = calculatePaginationWithFilterName(name, numberPage);
			PagedEntity<Brand> pagedEntity = getPagedEntityByFindName(name, paginationInfo);
			
			return pagedEntity;
		}else {
			throw new IllegalArgumentException("Nome de produto ou página inválida");
		}
	}


	@Override
	public PagedEntity<Brand> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PagedEntity<Brand> findAll(int numberPage) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean update(Brand entity) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean delete(Brand entity) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean validate(Brand entity) {
		// TODO Auto-generated method stub
		return false;
	}

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
	
	private PagedEntity<Brand> getPagedEntityByFindName(String name, PaginationInfo paginationInfo) {
		List<Brand> brands = daoBrand.findByName(name, paginationInfo.getEntitiesPerPage(), paginationInfo.getStart());
		
		return convertToPagedEntity(brands, paginationInfo);
	}

	@Override
	public PaginationInfo calculatePagination(int numberPage) {
		Pagination pagination = new Pagination();
		
		int quantity = Integer.parseInt(daoBrand.getEntityQuantity().toString());
		PaginationInfo infoPagination =  pagination.getPagination(quantity, numberPage);
		
		return infoPagination;
	}
	
	public PaginationInfo calculatePaginationWithFilterName(String searchValue, int numberPage) {
		Pagination pagination = new Pagination();
		
		int quantity = daoBrand.getQuantityBrandsPerFilterDescription(searchValue);
		PaginationInfo infoPagination =  pagination.getPagination(quantity, numberPage);
		
		return infoPagination;
	}


	@Override
	public PagedEntity<Brand> convertToPagedEntity(List<Brand> entities, PaginationInfo paginationInfo) {
		// TODO Auto-generated method stub
		return null;
	}

}
