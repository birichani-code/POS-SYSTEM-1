package com.devIT.dao;

import java.util.List;

import com.devIT.entity.Product;

public interface ProductDAO {
	
	 List<Product> getProducts();

	public void saveProduct(Product theProduct);

	public Product getProduct(int theId);

	public void deleteProduct(int theId);
	
	public Product getProduct(String name);

}
