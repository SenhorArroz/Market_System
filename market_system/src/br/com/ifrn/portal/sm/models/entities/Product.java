package br.com.ifrn.portal.sm.models.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author erikv
 * @version 1.0
 * @system Product.java
 * @date 12:03:25 8 de jan. de 2023
 * @system_unity_description Classe modelo do produto que contém seus atributos,
 *  regras de validações, mapeamento do objeto para o  banco de dados e o 
 *  relacionamento entre as entidades.
 * 
 */

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Product {
	
	/*Ordem das annotations 
	 * 1 - lombok
	 * 2 - validation
	 * 3 - JPA*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@NotNull(message = "A categoria não pode ser nula")
	@OneToOne(cascade = {CascadeType.MERGE})
	private Category category;
	
	@NonNull
	@NotNull(message = "A unidade de medida não pode ser nula")
	@OneToOne(cascade = {CascadeType.MERGE})
	private UnitMeasurement unitMeasurement;
	
	@NonNull
	@NotNull(message = "A marca não pode ser nula")
	@OneToOne(cascade = {CascadeType.MERGE})
	private Brand brand;
	
	@NonNull
	@NotBlank(message = "O código de barras é obrigatório")
	@Column(nullable = false, length = 50)
	private String barCode;
	
	@NonNull
	@Size(min = 5, message = "O tamanho minimo da descrição não foi atingido" )
	@NotBlank(message = "A descrição é obrigatória")
	private String description;
	
	@DecimalMin(value = "0.0", message = "Valor de custo inválido")
	@DecimalMax(value = "10000.00", message = "Valor máximo ultrapassado")
	@Column(scale = 7, precision = 2)
	private Double saleValue;
	
	@NonNull
	@PastOrPresent(message = "A data de fabricação não pode ser posterior a hoje")
	@Temporal(TemporalType.DATE)
	private Calendar fabricationDate;

	@NonNull
	@Temporal(TemporalType.DATE)
	@Future(message = "Não pode ser cadastrado um produto já vencido")
	private Calendar dueDate;

	@NonNull
	@Column(length = 5)
	private Byte[] image;
	
}
