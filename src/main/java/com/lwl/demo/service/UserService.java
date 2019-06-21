package com.lwl.demo.service;

import java.util.List;

import com.lwl.model.User;

public interface UserService {
   public User selectUserById(Integer userId); 
   public List<User> findAllUser();
}
