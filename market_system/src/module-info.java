module sistema_mercado {
	//VALIDATION - Validation of actribucts
	requires java.validation;
	requires jakarta.el;
	
	opens br.com.ifrn.portal.sm.models.services;
	opens br.com.ifrn.portal.sm.models.infrastructure;
	
	//LOMBOK - Generation API getters, setters, contructors, equals e hashcode e toString
	requires lombok;
	
	//HIBERNATE package opening requirement for Reflection
	requires java.persistence;
	requires java.sql;
	requires org.hibernate.orm.core;
	
	opens br.com.ifrn.portal.sm.models.entities;
	
	//JAVAFX package opening requirement for Reflection
	requires javafx.controls;
	requires javafx.fxml;
	requires org.controlsfx.controls;
	
//	opens br.com.ifrn.portal.sm.views;
//	opens br.com.ifrn.portal.sm.controllers;
	opens br.com.ifrn.portal.sm.views to javafx.graphics, javafx.fxml;
	opens br.com.ifrn.portal.sm.controllers to javafx.graphics, javafx.fxml;
}