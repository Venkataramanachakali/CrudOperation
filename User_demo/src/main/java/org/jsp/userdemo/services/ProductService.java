package org.jsp.userdemo.services;

import java.util.List;
import java.util.Optional;

import org.jsp.userdemo.Exception.IdNotFoundException;
import org.jsp.userdemo.dao.ProductDao;
import org.jsp.userdemo.dao.UserDao;
import org.jsp.userdemo.dto.Product;
import org.jsp.userdemo.dto.ResponseStructure;
import org.jsp.userdemo.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProductService {
	@Autowired
 private ProductDao Dao;
	@Autowired
 private UserDao userDao;
	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product,int user_id)
	{
		Optional<User> recUser=userDao.findById(user_id);
		ResponseStructure<Product> structure=new ResponseStructure<>();
		if(recUser.isPresent())
		{
			User  u=recUser.get();
			 u.getProducts().add(product);
			
			product.setUser(recUser.get());
			userDao.updateUser(recUser.get());
			Dao.saveProduct(product);
			structure.setData(product);
			structure.setMessage("product added sucessfully");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product,int user_id)
	{
		Optional<User> recUser=userDao.findById(user_id);
		ResponseStructure<Product> structure=new ResponseStructure<>();
		if(recUser.isPresent())
		{
			User  u=recUser.get();
			((List<Product>) u.getProducts()).add(product);
			product.setUser(recUser.get());
			userDao.updateUser(recUser.get());
			Dao.UpdateProduct(product);
			structure.setData(product);
			structure.setMessage("product updated sucessfully");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}
	
	public	ResponseEntity<ResponseStructure<Product>> findById(int id)
	{
		ResponseStructure<Product> structure=new ResponseStructure<>();
		Optional<Product> recProduct=Dao.findById(id);
		if(recProduct.isPresent())
		{
			structure.setData(recProduct.get());
			structure.setMessage("Product found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteProduct(int id)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		Optional<Product> recProduct=Dao.findById(id);
		if(recProduct.isPresent())
		{
			Dao.deleteProduct(id);
			structure.setData("Product deleted");
			structure.setMessage("Product found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> findProductsByUserId(int user_id)
	{
		ResponseStructure<List<Product>> structure=new ResponseStructure<>();
		structure.setData(Dao.findProductsByUserId(user_id));
		structure.setMessage("products found for user id");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure,HttpStatus.OK);
	}
}
