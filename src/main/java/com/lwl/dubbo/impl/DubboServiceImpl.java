package com.lwl.dubbo.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.alibaba.dubbo.config.annotation.Service;
import com.lwl.demo.dao.UserDao;
import com.lwl.dubbo.DubboService;
import com.lwl.model.User;

@Service(version = "1.0.0")
public class DubboServiceImpl implements DubboService {
	@Autowired
	private UserDao userDao;
	@Autowired
    private RedisTemplate redisTemplate;
	
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
