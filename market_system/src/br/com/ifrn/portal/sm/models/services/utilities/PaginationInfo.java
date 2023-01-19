package br.com.ifrn.portal.sm.models.services.utilities;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author erikv
 * @version 1.0
 * @system ProductService.java
 * @date 09:14:17 18 de jan. de 2023 2023
 * @system_unity_description Classe responsável por repassar as informações de paginação da entidade.
 * 
 */

@RequiredArgsConstructor
@Getter
@ToString
public class PaginationInfo {
	
	@NonNull
	private Integer numberPage;
	
	@NonNull
	private Integer totalPages;
	
	@NonNull
	private Integer entitiesPerPage;
	
	@NonNull
	private Integer totalEntities;
	
	@NonNull
	private Integer start;
	
}
