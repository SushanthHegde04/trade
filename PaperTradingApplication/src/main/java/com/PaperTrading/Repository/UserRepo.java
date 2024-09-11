package com.PaperTrading.Repository;

import java.util.Optional;

import org.apache.el.parser.AstInteger;
import org.springframework.data.jpa.repository.JpaRepository;

import com.PaperTrading.Model.User;

public interface UserRepo extends JpaRepository<User ,Integer>{
Optional<User> findById(int userId);
}
