package com.teaonlinestore.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.teaonlinestore.model.Category;
import com.teaonlinestore.model.Product;
import com.teaonlinestore.utils.HibernateUtil;

public class ProductDaoHibernate extends GenericDaoHibernate<Product, Long> implements ProductDao {

}
