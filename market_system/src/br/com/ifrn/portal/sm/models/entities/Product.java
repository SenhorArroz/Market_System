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
 * @system_unity_description Classe modelo do produto quem cont�m seus atributos,
 *  regras de valida��es, mapeamento do objeto para o  banco de dados e o 
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
	@Size(min = 5, message = "O tamanho minimo da descri��o n�o foi atingido" )
	@NotBlank(message = "A descri��o precisa estar preenchida")
	private String descricao;
	
	@NonNull  
	@DecimalMin(value = "0.0", message = "Valor de custo inv�lido")
	@DecimalMax(value = "1000000.0", message = "Valor m�ximo ultrapassado")
	private Double valor_custo;
	
	@DecimalMin(value = "0.0", message = "Valor de custo inv�lido")
	@DecimalMax(value = "2000000.0", message = "Valor m�ximo ultrapassado")
	private Double valor_venda;
	
	@NonNull
	@DecimalMin(value = "0.0", message = "Porcentagem de lucro inv�lida")
	@DecimalMax(value = "1.0", message = "Porcentagem m�xima de lucro ultrapassada")
	private Double porcentagem_venda;
	
	@NonNull
	@DecimalMin(value = "0.0", message = "Desconto inv�lido")
	@DecimalMax(value = "1.0", message = "Desconto m�ximo ultrapassado")
	private Double desconto;
	
}
