package com.lwl.dubbo;

import java.util.List;

import com.lwl.model.User;

public interface DubboService {
	public List<User> findAllUser();
}
