package br.com.ifrn.portal.sm.models.services.utilities;

/**
 * @author erikv
 * @version 1.0
 * @system ProductService.java
 * @date 09:01:37 18 de jan. de 2023 
 * @system_unity_description Classe responsável por calcular a paginacao das entidades.
 * 
 */

public class Pagination {

	private int numberPage;
	
	private int totalPages;
	
	private int entitiesPerPage = 5;
	
	private int totalEntities;
	
	private int start;
	
	public PaginationInfo getPagination(int totaEntities, int numberPage) {
		this.totalEntities = totaEntities;
		this.numberPage = numberPage;
		calculateStart();
		calculatePages();
		
		return getInfoPagination();
	}
	
	private void calculateStart() {
		start = (numberPage * entitiesPerPage) - entitiesPerPage;
	}
	
	private void calculatePages() {
		totalPages = (int) Math.ceil(totalEntities * 1.0 /  (entitiesPerPage * 1.0));
	}
	
	private PaginationInfo getInfoPagination() {
		return new PaginationInfo(numberPage, totalPages, entitiesPerPage, totalEntities, start);
	}
	
}
