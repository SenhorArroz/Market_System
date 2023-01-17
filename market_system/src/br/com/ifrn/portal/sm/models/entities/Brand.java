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
 * @date 17:08:43 13 de jan. de 2023 2023
 * @system_unity_description Classe modelo da marca do produto
 *
 */

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class Brand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@Size(min = 2, max = 50, message = "Insira o nome da marca entre 2 e 50 caracteres")
	@NotBlank(message = "O nome da marca é obrigatório")
	@Column(nullable = false, length = 50)
	private String name;

}
