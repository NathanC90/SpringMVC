package com.web.store.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.exception.ProductNotFoundException;
import com.web.store.model.BookBean;
import com.web.store.model.CompanyBean;
import com.web.store.repository.ProductRepository;
@Repository
public class HibernateProductRepository implements ProductRepository {
	@Autowired
	SessionFactory factory;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<BookBean> getAllProducts(){
		String hql = "FROM BookBean";
		Session session = null;
		List<BookBean> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public void updateStock(int productId, int newQuantity) {
		String hql = "UPDATE BookBean SET stock = :newQuantity WHERE bookId = :id";
		Session session = factory.getCurrentSession();
		int n = session.createQuery(hql).setParameter("newQuantity", newQuantity)
										.setParameter("id", productId)
										.executeUpdate();
	}

	@Override
	public List<String> getAllCategories() {
		String hql = "SELECT DISTINCT b.category FROM BookBean b";
		Session session = factory.getCurrentSession();
		List<String> list = new ArrayList<>();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public List<BookBean> getProductsByCategory(String category) {
		String hql = "FROM BookBean bb WHERE bb.category = :category";
		List<BookBean> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("category", category).getResultList();
		return list;
	}

	@Override
	public BookBean getProductById(int productId) {
		BookBean bb = null;
		Session session = factory.getCurrentSession();
		bb = session.get(BookBean.class, productId);
		if(bb==null) throw new ProductNotFoundException(productId);
		return bb;
	}

	@Override
	public void addProduct(BookBean product) {
		Session session = factory.getCurrentSession();
		CompanyBean cb = getCompanyById(product.getCompanyId());
		product.setCompanyBean(cb);
		session.save(product);
	}

	@Override
	public CompanyBean getCompanyById(int companyId) {
		CompanyBean cb = null;
		Session session = factory.getCurrentSession();
		cb = session.get(CompanyBean.class, companyId);
		return cb;
	}

	@Override
	public List<CompanyBean> getCompanyList() {
		String hql = "FROM CompanyBean";
		Session session = factory.getCurrentSession();
		List<CompanyBean> list = session.createQuery(hql).getResultList();
		return list;
	}

}
