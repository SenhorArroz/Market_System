package br.com.ifrn.portal.sm.models.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
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
 * @system Product.java
 * @date 20:51:58 19 de jan. de 2023 2023
 * @system_unity_description Classe modelo do estoque que contém seus atributos,
 *  regras de validações, mapeamento do objeto para o  banco de dados e o 
 *  relacionamento entre as entidades.
 * 
 */

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class StockProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(nullable = false, unique = true)
	private Product product;
	
	@NonNull
	@Min(value = 0, message = "Quantidade de produtos inválida")
	@Max(value = 20000, message = "Quantidade máxima de produtos ultrapassada")
	private Integer quantity;
	
	@NonNull
	@DecimalMin(value = "0.0", message = "Valor de unitário inválido")
	@DecimalMax(value = "10000.00", message = "Valor de unitário máximo ultrapassado")
	private Double unitValue;
	
	@NonNull
	@DecimalMin(value = "0.0", message = "Valor de porcentagem inválida")
	@DecimalMax(value = "1.0", message = "A porcentagem de venda deve ser de 0 a 100%")
	private Double percentageSale;

	@NonNull
	@DecimalMin(value = "0.0", message = "Valor de porcentagem inválida")
	@DecimalMax(value = "1.0", message = "O desconto deve ser de 0 a 100%")
	private Double discount;
	
}
