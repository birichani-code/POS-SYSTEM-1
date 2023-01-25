package com.devIT.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devIT.dao.ProductDAO;
import com.devIT.dto.ProductDTO;
import com.devIT.entity.Product;
import com.devIT.service.ProductService;
import com.devIT.service.mapper.ProductMapper;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ProductMapper productMapper;

	
	public List<ProductDTO> getProducts() {
		
		List<Product> products= productDAO.getProducts();
		
		List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
		
		for(Product p :products) {
			productDTOList.add(productMapper.mapToDTO(p));
		}
		return productDTOList;
	}

	public void saveProduct(ProductDTO theProduct) {
		Product product = productMapper.mapToEntity(theProduct);
		productDAO.saveProduct(product);
		
		
	}

	

	@Override
	
	public void deleteProduct(int theId) {
		productDAO.deleteProduct(theId);

		
	}

	public ProductDTO getProducts(int theId) {
		Product product = productDAO.getProduct(theId);
		
		return productMapper.mapToDTO(product);
	}
	
	
}
