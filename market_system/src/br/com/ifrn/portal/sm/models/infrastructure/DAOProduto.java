package br.com.ifrn.portal.sm.models.infrastructure;

import br.com.ifrn.portal.sm.models.entities.Product;

/**
 * @author erikv
 * @version 1.0
 * @system DAOProduto.java
 * @date 15:42:36 9 de jan. de 2023 2023
 */

public class DAOProduto extends DAOGeneric<Product>{

	public DAOProduto() {
		super(Product.class);
	}

}
