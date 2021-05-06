package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.Product;

@Repository
public class ProductDao {

	@Autowired
	private RedisTemplate redisTemplate;
	
	public Product save(Product product) {
		redisTemplate.opsForHash().put("Product", product.getId(), product);
		return product;		
	}
	public List<Product> findAll(){
		return redisTemplate.opsForHash().values("Product");
	}
	public Product findById(int id) {
		return (Product) redisTemplate.opsForHash().get("Product", id);
	}
	public String deleteProduct(int id) {
		redisTemplate.opsForHash().delete("Product", id);
		return "product deleted";
	}
}
