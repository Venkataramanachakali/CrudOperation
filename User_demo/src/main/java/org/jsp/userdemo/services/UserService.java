package org.jsp.userdemo.services;

import java.util.List;
import java.util.Optional;

import org.jsp.userdemo.dao.UserDao;
import org.jsp.userdemo.dto.ResponseStructure;
import org.jsp.userdemo.dto.User;
import org.jsp.userdemo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
	@Autowired
	private UserDao dao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User u) {
		ResponseStructure<User> structure = new ResponseStructure();
		structure.setData(dao.saveUser(u));
		structure.setMessage("User with id:" + u.getId());
		structure.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User u) {
		ResponseStructure<User> structure = new ResponseStructure();
		structure.setData(dao.saveUser(u));
		structure.setMessage("User Update");
		structure.setStatusCode(HttpStatus.ACCEPTED.value());

		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<User>> findById(int id) {
		ResponseStructure<User> structure = new ResponseStructure();
		Optional<User> recUser = dao.findById(id);
		if (recUser.isPresent()) {
			structure.setMessage("user found");
			structure.setData(recUser.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);

		} else {
			structure.setMessage("user not found");
			structure.setData(null);
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<String>> deleteUser(int id) {
		ResponseStructure<String> structure = new ResponseStructure();
		Optional<User> recUser = dao.findById(id);
		if (recUser.isPresent()) {
			structure.setMessage("user deleted");
			structure.setData("user found");
			structure.setStatusCode(HttpStatus.OK.value());
			dao.deleteUser(id);
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("user not found");
			structure.setData(null);
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<User>>> findAll() {
		ResponseStructure<List<User>> structure = new ResponseStructure();
		structure.setMessage("list of all users");
		structure.setData(dao.findAll());
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<User>> verifyUser(long phone, String password) {
		ResponseStructure<User> structure = new ResponseStructure();
		Optional<User> recUser = dao.verifyUser(phone, password);
		if (recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("user verified sucessesfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(HttpStatus.OK);
		} else {
			structure.setMessage("user not found");
			structure.setData(null);
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<User>> verifyUser(String email, String password) {
		ResponseStructure<User> structure = new ResponseStructure();
		Optional<User> recUser = dao.verifyUser(email, password);
		if (recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("user verified sucessesfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(HttpStatus.OK);
		} else {
			structure.setData(null);
			structure.setMessage("user not found");
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(HttpStatus.NOT_FOUND);
		}
	}

}
