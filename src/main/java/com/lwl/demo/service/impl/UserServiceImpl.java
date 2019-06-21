package com.lwl.demo.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.lwl.demo.dao.UserDao;
import com.lwl.demo.service.UserService;
import com.lwl.model.User;

@Service
public class UserServiceImpl implements UserService{
    @Autowired  
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Override
    public User selectUserById(Integer userId) {
        return userDao.selectUserById(userId);  
    }

	@Override
	public List<User> findAllUser() {
		String key = "user";
        ValueOperations<String, List<User>> operations = redisTemplate.opsForValue();
        
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
        	List<User> users = operations.get(key);
        	System.out.println("从Redis中获取Users");
        	
        	return users;
        }
        
        List<User> users = userDao.findAllUser();
        operations.set(key, users, 10, TimeUnit.SECONDS);
        System.out.println("将users存入Redis缓存");
    	return users;
	}

}
