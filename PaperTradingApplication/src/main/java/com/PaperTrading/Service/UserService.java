package com.PaperTrading.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PaperTrading.Model.User;
import com.PaperTrading.Repository.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userrepo;

	public User saveUser(User user) {
		return userrepo.save(user);
	}

	public Optional<User> findById(int userId) {
		return userrepo.findById(userId);
	}

	public Optional<User> findByEmail(String email) {
		return userrepo.findAll().stream().filter(user -> user.getEmail().equals(email)).findFirst();
	}

	public Optional<User> authenticateUser(String email, String password) {
		Optional<User> userOptional = findByEmail(email);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			if( user.getPassword().equals(password))
				return Optional.of(user);	
			}
		return Optional.empty();
	}

	public User getName(int uid) {
		// TODO Auto-generated method stub
		User u=userrepo.findById(uid).orElse(null);
		if(u!=null)
		{
			return u;
		}
		else
			return  null;		
	}
	
	public String getEmail(int uid) {
		// TODO Auto-generated method stub
		User u=userrepo.findById(uid).orElse(null);
		if(u!=null)
		{
			return u.getEmail();
		}
		else
			return "NO USER ";
		
	}
}
