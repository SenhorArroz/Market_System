package br.com.ifrn.portal.sm.models.entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author erikv
 * @version 1.0
 * @system ProductService.java
 * @date 21:19:39 23 de jan. de 2023
 * @system_unity_description Classe responsável por armazenar o item de devolução
 *  e a quantidade a ser devolvida.
 * 
 */

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class SaleReturnItem {

	@NonNull
	private String itemSale;
	
	@NonNull
	@Min(value = 1, message = "A devolução é de no minino um produto")
	@Max(value = 54/*itemSale.getQuantity()*/, message = "Não poder ser devolvida uma quantidade maior do que foi comprada")
	private Integer returnQuantity;
	
}
