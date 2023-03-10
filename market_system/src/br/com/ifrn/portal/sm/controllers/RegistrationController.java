package br.com.ifrn.portal.sm.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.controlsfx.control.Notifications;

import br.com.ifrn.portal.sm.models.entities.Product;
import br.com.ifrn.portal.sm.models.exceptions.InvalidDataException;
import br.com.ifrn.portal.sm.models.services.implementation.ProductService;
import br.com.ifrn.portal.sm.views.App;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @version 1.0
 * @system CadastroController.java
 * @author erikv
 * @date 12:07:26 8 de jan. de 2023
 * 
 */

public class RegistrationController {

	@FXML
	private TextField fieldDescription;
	
	@FXML
	private TextField fieldCost;
	
	@FXML
	private TextField fieldPercentage;
	
	@FXML
	private TextField fieldDiscount;
	
	@FXML
	private Button buttonHome;
	
	@FXML
	private Button buttonSave;
	
	private Product product;
	
	private ProductService service;
	
	private List<TextField> listFields;
	
	public void save() {
		if(fieldValids()) {
			product = new Product();
			
			product.setDueDate(LocalDate.now());
			product.setDescription(fieldDescription.getText());
			
			service = new ProductService();
			
			try {
				service.insert(product);
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Informa??o");
				alert.setContentText("Cadastrado com sucesso!");
				alert.setHeaderText("Comfirma??o");
				alert.showAndWait();
				
			} catch (InvalidDataException e) {
				e.printStackTrace();
				e.getListContraintViolations().stream().forEach(v -> 
					Notifications.create()
						.position(Pos.CENTER)
						.text(v.toString())
						.title("Viola??o de regras")
						.showWarning()
				);
				
			}
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Informa??o");
			alert.setContentText("Campos em branco");
			alert.setHeaderText("Erro");
			alert.showAndWait();
		}
		
	}
	
	private boolean fieldValids() {
		fillList();
		
		//Verifica se existem algum campo em branco
		Predicate<TextField> isValid = f -> !f.getText().isBlank();
		boolean isValids = listFields.stream().allMatch(isValid);
		return isValids;
	}
	
	private void fillList() {
		if (listFields == null) {
			listFields = new ArrayList<>();
		}
		
		if(listFields.isEmpty()) {
			listFields.addAll(Arrays.asList(fieldDescription, fieldCost, fieldPercentage, fieldPercentage));
		}
	}
	
	public void setCenaInicio() {
		App.setCenaInicio();
	}
	
}
