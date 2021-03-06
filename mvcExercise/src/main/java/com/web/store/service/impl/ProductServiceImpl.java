package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model.BookBean;
import com.web.store.model.CompanyBean;
import com.web.store.service.ProductService;
import com.web.store.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	@Override
	public List<BookBean> getAllProducts() {		
		return productRepository.getAllProducts();
	}
	
	@Transactional
	@Override
	public void updateAllStock() {
		List<BookBean> allProducts = productRepository.getAllProducts();
		for(BookBean bb : allProducts) {
			if(bb.getStock() != null && bb.getStock() <50 ) {
				productRepository.updateStock(bb.getBookId(), bb.getStock() + 50);
			}
		}
	}

	@Transactional
	@Override
	public List<String> getAllCategories() {
		return productRepository.getAllCategories();
	}

	@Transactional
	@Override
	public List<BookBean> getProductsByCategory(String category) {
		return productRepository.getProductsByCategory(category);
	}
	@Transactional
	@Override
	public BookBean getProductById(int productId) {
		return productRepository.getProductById(productId);
	}
	@Transactional
	@Override
	public void addProduct(BookBean product) {
		productRepository.addProduct(product);
		
	}
	@Transactional
	@Override
	public CompanyBean getCompanyById(int productId) {
		return productRepository.getCompanyById(productId);
	}
	@Transactional
	@Override
	public List<CompanyBean> getCompanyList() {
		return productRepository.getCompanyList();
	}

}
