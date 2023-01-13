package br.com.ifrn.portal.sm.controllers;

import br.com.ifrn.portal.sm.views.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * @author erikv
 * @version 1.0
 * @system TelaController.java
 * @date 12:07:50 8 de jan. de 2023 2023
 */

public class TelaController {
	
	@FXML
	private Label mensagem;
	
	@FXML
	private Button botaoCadastro;
	
	public void setMensagem() {
		mensagem.setText("Made with Scene Builder for JavaFx");
	}
	
	public void setCenaCadastro() {
		App.setCenaCadastro();
	}

}
