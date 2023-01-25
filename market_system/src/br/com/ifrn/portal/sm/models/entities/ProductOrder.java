package br.com.ifrn.portal.sm.models.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.PastOrPresent;

import br.com.ifrn.portal.sm.models.entities.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author V1NI-Zatarash
 * @secondary_author erikv modified in: 16:04:54 22 de jan. de 2023
 * @version 1.0
 * @date 13:40:13 20 de jan. de 2023
 * @system_unity_description Classe modelo de item pedido de pedidos
 *
 */

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class ProductOrder implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "productOrder")
	private List<ItemOrder> itensOrder;
	
	@ManyToOne
	private Provider provider;
	
	@NonNull
	private Integer theAmount;
	
	@NonNull
	@DecimalMin(value = "0.0", message = "Valor inv�lido")
	@DecimalMax(value = "10.0", message = "Valor m�ximo ultrapassado de frete")
	@Column(scale = 7, precision = 2)
	private Double freightValue;
	
	@NonNull
	@PastOrPresent(message = "A data de pedido n�o pode ser posterior a hoje")
	private LocalDate orderDate;
	
	@NonNull
	@Future(message = "Essa é a previsão da entrega")
	private LocalDate deliveryForecast;
	
	@NonNull
	@Column(scale = 7, precision = 2)
	private Double totalValue;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

}
