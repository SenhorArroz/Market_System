package br.com.ifrn.portal.sm.models.infrastructure;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.ifrn.portal.sm.models.entities.UnitMeasurement;

/**
 * 
 * @author erikv
 * @version 1.0
 * @system DAOProduto.java
 * @date 15:35:04 16 de jan. de 2023
 * 
 */

public class DAOUnitMeasurement extends DAOGeneric<UnitMeasurement>{

	public DAOUnitMeasurement() {
		super(UnitMeasurement.class);
	}
	
	public List<UnitMeasurement> findByAcronym(String acronym, int limit, int skip) {
		TypedQuery<UnitMeasurement> query = getEm().createNamedQuery("findUnitMeasurementByAcronym", UnitMeasurement.class);
		query.setParameter("description", "%" + acronym + "%");
		query.setFirstResult(skip);
		query.setMaxResults(limit);
		
		return query.getResultList();
	}
	
	public List<UnitMeasurement> findByName(String name, int limit, int skip) {
		TypedQuery<UnitMeasurement> query = getEm().createNamedQuery("findUnitMeasurementByName", UnitMeasurement.class);
		query.setParameter("description", "%" + name + "%");
		query.setFirstResult(skip);
		query.setMaxResults(limit);
		
		return query.getResultList();
	}
	
	public int getQuantityMeasurementPerFilterAcronym(String acronym) {
		TypedQuery<Long> query = getEm().createNamedQuery("numberUnitMeasurementPerFilterAcronym", Long.class);
		query.setParameter("description", "%" + acronym + "%");
		
		long quantity = query.getSingleResult();
		
		return (int) quantity;
	}
	
	public int getQuantityMeasurementPerFilterName(String name) {
		TypedQuery<Long> query = getEm().createNamedQuery("numberUnitMeasurementPerFilterName", Long.class);
		query.setParameter("description", "%" + name + "%");
		
		long quantity = query.getSingleResult();
		
		return (int) quantity;
	}
}
