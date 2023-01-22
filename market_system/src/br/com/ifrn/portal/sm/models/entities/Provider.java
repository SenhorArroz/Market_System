package br.com.ifrn.portal.sm.models.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.ifrn.portal.sm.models.entities.enums.ProviderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author V1NI-Zatarash
 * @version 1.0
 * @system Provider.java
 * @date 14:12:05 19 de jan. de 2023
 * @system_unity_description Classe modelo do fornecedor que cont�m seus atributos,
 *  regras de valida��es, mapeamento do objeto para o  banco de dados e o 
 *  relacionamento entre as entidades.
 * 
 */

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Provider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@Size(min = 3 ,max = 20, message = "O tamanho mínimo ou máximo do código não foi atingido")
	@NotNull(message = "O código não pode ser nulo")
	private String code;
	
	@NonNull
	@Size(min = 5, max = 45, message = "O tamanho mínimo ou máximo da razão social não foi atingido")
	@NotBlank(message = "A razão social não pode ser nula")
	private String corporateName;
	
	@NonNull
	@NotBlank(message = "O cnpj não pode ser nulo")
	private String cnpj;
	
	@NonNull
	@Email(regexp =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email;
	
	@NonNull
	@NotBlank(message = "O telefone não pode ser nulo")
	@Pattern(regexp = "")
	
	private String telephone;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private ProviderStatus providerStatus;

}
