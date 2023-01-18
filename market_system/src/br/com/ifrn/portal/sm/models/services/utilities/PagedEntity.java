package br.com.ifrn.portal.sm.models.services.utilities;

import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author erikv
 * @version 1.0
 * @system ProductService.java
 * @date 09:33:54 18 de jan. de 2023
 * @system_unity_description Classe responsável por empacotar a lista de entidades paginadas
 *  e as informações referente à paginação como: numero da pagina, total de paginas, quantidade
 *  de entides, etc. 
 * 
 */

@RequiredArgsConstructor
@Getter
public class PagedEntity<E> {

	@NonNull
	private List<E> paginatedEntityList;
	
	@NonNull
	private PaginationInfo paginationInfo;
	
}
