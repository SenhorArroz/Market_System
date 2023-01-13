package br.com.ifrn.portal.sm.views;
	
import java.io.IOException;

import org.controlsfx.control.Notifications;

import br.com.ifrn.portal.sm.models.entities.Product;
import br.com.ifrn.portal.sm.models.exceptions.InvalidDataException;
import br.com.ifrn.portal.sm.models.services.ProductService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author erikv
 * @version 1.0
 * @system App.java
 * @date 9:20:55 7 de jan. de 2023 2023
 */

public class App extends Application {
	
	private static Stage janela;
	private static Scene cenaInicio;
	private static Scene cenaCadastro;
	
	@Override
	public void start(Stage primaryStage) {
		try {
//			
//			try {
//				Product produto = new Product("", 0.0, 1.2, 0.0);
//				ProductService produtoService = new ProductService();
//				produtoService.insert(produto);
//			} catch (InvalidDataException e) {
//				e.printStackTrace();
//				e.getListContraintViolations().stream().forEach(v -> System.out.println(v.toString()));
//			}
//			
			janela = primaryStage;
			
			//Exemplos
			Parent parent1 = getParent("/br/com/ifrn/portal/sm/views/ScreenHome.fxml");
			Parent parent2 = getParent("/br/com/ifrn/portal/sm/views/ScreenRegistration.fxml");
			/*OBS: aqui est� sendo carregando duas telas do sistemas, mas isso n�o poder� ocorrer
			 * com todas as telas pois ir� afetar no desempenho do software. Sendo assim, o carregamento 
			 * dever� ser feito por demanda, isto �, quando o usu�rios quiser usar as funcionalidades 
			 * de determinada tela */
			
			cenaInicio = new Scene(parent1);
			cenaCadastro = new Scene(parent2);
			
			App.janela.setScene(cenaInicio);
			App.janela.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Parent getParent(String absolutePath) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setClassLoader(getClass().getClassLoader()); // definir o carregador de classe 
		loader.setLocation(getClass().getResource(absolutePath));
		return loader.load();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void setCenaCadastro() {
		janela.setScene(cenaCadastro);
	}
	
	public static void setCenaInicio() {
		janela.setScene(cenaInicio);
	}
}
