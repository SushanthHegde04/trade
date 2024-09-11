package com.PaperTrading.Controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PaperTrading.Model.User;
import com.PaperTrading.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userservice;

	 @GetMapping("/hi")
	 public String msg()
	 {
		 return "Hiii for stock market";
	 }
	@PostMapping("/signup")
	public User createUser(@RequestBody User user) {
		 if (user.getBalance() == null) {
	            user.setBalance(BigDecimal.valueOf(10000));  // Set default value before saving
	        }
		return userservice.saveUser(user);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> loginUser(@RequestBody User loginUser) {
	    String email = loginUser.getEmail();
	    String password = loginUser.getPassword();
	    Optional<User> authenticatedUser = userservice.authenticateUser(email, password);

	    if (authenticatedUser.isPresent()) {
	        User user = authenticatedUser.get();
	        Map<String, String> response = new HashMap<>();
	       // response.put("message", "Login successful");
	        response.put("userId", String.valueOf(user.getUserId())); // Ensure userId is a valid string
	        return ResponseEntity.ok(response); // Return HTTP 200
	    } else {
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "Invalid Email or Password");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // Return HTTP 401
	    }
	}
     
	@GetMapping("/name/{uid}")
	public User getUserName(@PathVariable int uid)
	{
		return userservice.getName(uid);
	
	}
	@GetMapping("/email")
	public String getEmail(@PathVariable int uid)
	{
		return userservice.getEmail(uid);
	}

}
