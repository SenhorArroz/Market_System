package br.com.ifrn.portal.sm.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author erikv
 * @version 1.0
 * @system Product.java
 * @date 12:03:25 8 de jan. de 2023 2023
 * @system_unity_description Classe modelo do produto quem contém seus atributos,
 *  regras de validações, mapeamento do objeto para o  banco de dados e o 
 *  relacionamento entre as entidades.
 */

@Data
@NoArgsConstructor
@RequiredArgsConstructor()
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@Size(min = 5, message = "O tamanho minimo da descrição não foi atingido" )
	@NotBlank(message = "A descrição precisa estar preenchida")
	private String descricao;
	
	@NonNull  
	@DecimalMin(value = "0.0", message = "Valor de custo inválido")
	@DecimalMax(value = "1000000.0", message = "Valor máximo ultrapassado")
	private Double valor_custo;
	
	@DecimalMin(value = "0.0", message = "Valor de custo inválido")
	@DecimalMax(value = "2000000.0", message = "Valor máximo ultrapassado")
	private Double valor_venda;
	
	@NonNull
	@DecimalMin(value = "0.0", message = "Porcentagem de lucro inválida")
	@DecimalMax(value = "1.0", message = "Porcentagem máxima de lucro ultrapassada")
	private Double porcentagem_venda;
	
	@NonNull
	@DecimalMin(value = "0.0", message = "Desconto inválido")
	@DecimalMax(value = "1.0", message = "Desconto máximo ultrapassado")
	private Double desconto;
	
}
