package br.com.ifrn.portal.sm.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author erikv
 * @version 1.0
 * @system Product.java
 * @date 15:43:02 22 de jan. de 2023 2023
 * @system_unity_description Classe de items de produtos
 * 
 */

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class ItemOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private ProductOrder productOrder;
	
	@NonNull
	@ManyToOne
	private Product product;
	
	@NonNull
	private Integer quantity;
	
	@NonNull
	@DecimalMin(value = "0.0", message = "Valor inválido")
	private Double amount;

}
