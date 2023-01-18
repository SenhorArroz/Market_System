package br.com.ifrn.portal.sm.models.services;

import java.util.ArrayList;
import java.util.List;

import br.com.ifrn.portal.sm.models.entities.Brand;
import br.com.ifrn.portal.sm.models.exceptions.InvalidDataException;
import br.com.ifrn.portal.sm.models.infrastructure.DAOBrand;
import br.com.ifrn.portal.sm.models.services.definitions.EntityService;
import br.com.ifrn.portal.sm.models.services.definitions.Service;
import br.com.ifrn.portal.sm.models.validations.SimpleConstraintViolations;

public class BrandService  extends Service<Brand> implements EntityService<Brand>{

	private DAOBrand daoBrand;
	
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
		// TODO Auto-generated method stub
		return daoBrand.findById(id);
	}
	
	@Override
	public List<Brand> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Brand> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Brand> findAll(int skip, int limit) {
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

}
