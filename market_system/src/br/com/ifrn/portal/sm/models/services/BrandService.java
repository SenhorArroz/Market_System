package br.com.ifrn.portal.sm.models.services;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.ifrn.portal.sm.models.entities.Brand;
import br.com.ifrn.portal.sm.models.entities.Product;
import br.com.ifrn.portal.sm.models.infrastructure.DAOBrand;
import br.com.ifrn.portal.sm.models.validations.SimpleConstraintViolations;

public class BrandService  extends Service implements EntityService<Brand>{

	private DAOBrand daoBrand;
	
	@Override
	public boolean insert(Brand entity) {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return null;
	}

}
