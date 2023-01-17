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
 * @date 16:28:37 13 de jan. de 2023
 * @system_unity_description Classe modelo de categorias de produtos
 *
 */

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@Size(min = 3, max = 100, message = "A descrição não está entre o tamanho especificado: min = 3 a max =100")
	@NotBlank(message = "A descrição é obrigatória")
	@Column(nullable = false, length = 100)
	private String description;

}
