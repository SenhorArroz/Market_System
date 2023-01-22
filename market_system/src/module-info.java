module sistema_mercado {
	
	/*--********| Java Validation  |********--*/
	
	requires java.validation;
	requires jakarta.el;
	
	opens br.com.ifrn.portal.sm.model;
	opens br.com.ifrn.portal.sm.models.services.implementation;
	opens br.com.ifrn.portal.sm.models.infrastructure;
	
	/*--********| Lombok  |********--*/
	
	requires lombok;
	
	/*--********| Java Persistence API  |********--*/
	
	requires java.persistence;
	requires java.sql;
	requires org.hibernate.orm.core;
	
	opens br.com.ifrn.portal.sm.models.entities;
	
	/*--********| JavaFX  |********--*/
	
	requires javafx.controls;
	requires javafx.fxml;
	requires org.controlsfx.controls;
	
	opens br.com.ifrn.portal.sm.views to javafx.graphics, javafx.fxml;
	opens br.com.ifrn.portal.sm.controllers to javafx.graphics, javafx.fxml;
	
	requires org.junit.jupiter.api;
	requires org.junit.platform.commons;
	requires javafx.graphics;
	
}