package com.lwl.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lwl.model.User;

@Mapper
public interface UserDao {  
    public User selectUserById(Integer userId);  
    public List<User> findAllUser();
} 
