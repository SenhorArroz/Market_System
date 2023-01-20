package br.com.ifrn.portal.sm.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author erikv
 * @version 1.0
 * @date 16:50:35 13 de jan. de 2023 2023
 * @system_unity_description Classe modelo de unidade de medida dos produtos
 *
 */

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
//@Table(name = "unit_measurement")
public class UnitMeasurement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@Size(min = 1, max = 3, message = "Insira uma sigla entre 1 e 3 caracteres")
	@NotBlank(message = "A sigla é obrigatória")
	@Column(nullable = false, length = 3)
	private String acronym;
	
	@NonNull
	@Size(min = 2, max = 50, message = "Insira uma nome entre 2 e 50 caracteres")
	@NotBlank(message = "O nome da unidade de medida é obrigatória")
	@Column(nullable = false, length = 50)
	private String name;
	
	
}
