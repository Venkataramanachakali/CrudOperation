package org.jsp.userdemo.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.userdemo.dto.Product;
import org.jsp.userdemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductDao {
	@Autowired
  private ProductRepository repository;
  public Product saveProduct(Product product) {
	   return repository.save(product);
  }
  public Product UpdateProduct(Product product) {
	   return repository.save(product);
 }
  public Optional<Product> findById(int id){
	  return repository.findById(id);
  }
  public boolean deleteProduct(int id) {
	  Optional<Product> recProduct=findById(id);
	  if(recProduct.isPresent()) {
		  repository.delete(recProduct.get());
		  return true;
	  }
	  return false;
  }
  public List<Product> findProductsByUserId(int user_id)
	{
	return repository.findProductsByUserId(user_id);
	}
  
  
}
