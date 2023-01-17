package br.com.ifrn.portal.sm.models.infrastructure;

import br.com.ifrn.portal.sm.models.entities.Category;

/**
 * @author erikv
 * @version 1.0
 * @system DAOProduto.java
 * @date 15:34:55 16 de jan. de 2023 2023
 */

public class DAOCategory extends DAOGeneric<Category>{

	public DAOCategory() {
		super(Category.class);
	}
}
