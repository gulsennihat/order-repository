package com.java.migros.repository;

import java.time.ZonedDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.java.migros.entity.Basket;

//other services 
@Repository
public class CaseProjectRepository {

	@PersistenceContext
    private EntityManager entityManager;

	public Basket getCustomerBasket(Long id) {
	 Basket basket =  (Basket) entityManager.createNativeQuery("Select * from baskets b left join line_items li on b.id = li.basket_id where b.customer_id = ?", Basket.class)
			.setParameter(1, id)
	      .getSingleResult();
		return basket;
	}
	
	@Transactional
	public void initProject() {
	    entityManager.createNativeQuery("INSERT INTO brands(created_by, created_date, description, name)"
	    		+ " VALUES (?, ?, ?, ?);")
	      .setParameter(1, "admin")
	      .setParameter(2, ZonedDateTime.now())
	      .setParameter(3, "Yayla Ürünleri")
	      .setParameter(4, "Yayla")
	      .executeUpdate();
	    
	    entityManager.createNativeQuery("INSERT INTO categories(created_by, created_date, name, url_path)"
	    		+ " VALUES (?, ?, ?, ?);")
	      .setParameter(1, "admin")
	      .setParameter(2, ZonedDateTime.now())
	      .setParameter(3, "Kuru Gıda")
	      .setParameter(4, "kuru-gida")
	      .executeUpdate();
	    
	    entityManager.createNativeQuery("INSERT INTO customers(created_by, created_date, email, name, surname, password)"
	    		+ " VALUES (?, ?, ?, ?, ?, ?);")
	      .setParameter(1, "admin")
	      .setParameter(2, ZonedDateTime.now())
	      .setParameter(3, "gulsennihat@gmail.com")
	      .setParameter(4, "nihat")
	      .setParameter(5, "gulsen")
	      .setParameter(6, "123456")
	      .executeUpdate();
	    
	    entityManager.createNativeQuery("INSERT INTO products(created_by, created_date, active, img_url, name, price, stock_amount, brand_id, category_id)"
	    		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);")
	      .setParameter(1, "admin")
	      .setParameter(2, ZonedDateTime.now())
	      .setParameter(3, true)
	      .setParameter(4, "images/yayla_bulgur_1kg.png")
	      .setParameter(5, "Yayla Bulgur")
	      .setParameter(6, "10")
	      .setParameter(7, "1000")
	      .setParameter(8, 1)
	      .setParameter(9, 1)
	      .executeUpdate();
	    
	    entityManager.createNativeQuery("INSERT INTO products(created_by, created_date, active, img_url, name, price, stock_amount, brand_id, category_id)"
	    		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);")
	      .setParameter(1, "admin")
	      .setParameter(2, ZonedDateTime.now())
	      .setParameter(3, true)
	      .setParameter(4, "images/yayla_baldo_pirinc_2kg.png")
	      .setParameter(5, "Yayla Pirinç")
	      .setParameter(6, "20")
	      .setParameter(7, "1000")
	      .setParameter(8, 1)
	      .setParameter(9, 1)
	      .executeUpdate();
	    
	    entityManager.createNativeQuery("INSERT INTO baskets(created_by, created_date, customer_id, shipment_price)"
	    		+ " VALUES (?, ?, ?, ?);")
	      .setParameter(1, "admin")
	      .setParameter(2, ZonedDateTime.now())
	      .setParameter(3, 1)
	      .setParameter(4, 0)
	      .executeUpdate();
	    
	    entityManager.createNativeQuery("INSERT INTO line_items(created_by, created_date, amount, price, product_id, basket_id)"
	    		+ " VALUES (?, ?, ?, ?, ?, ?);")
	      .setParameter(1, "admin")
	      .setParameter(2, ZonedDateTime.now())
	      .setParameter(3, 3)
	      .setParameter(4, 30)
	      .setParameter(5, 1)
	      .setParameter(6, 1)
	      .executeUpdate();
	    
	    entityManager.createNativeQuery("INSERT INTO line_items(created_by, created_date, amount, price, product_id, basket_id)"
	    		+ " VALUES (?, ?, ?, ?, ?, ?);")
	      .setParameter(1, "admin")
	      .setParameter(2, ZonedDateTime.now())
	      .setParameter(3, 2)
	      .setParameter(4, 40)
	      .setParameter(5, 2)
	      .setParameter(6, 1)
	      .executeUpdate();
	    

	}
	
}
