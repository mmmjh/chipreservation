package com.fenjian.dao;

import java.util.List;

import com.fenjian.bean.Chip;
import com.fenjian.bean.User;

public interface IUserDao {
	void add(User user);
	void delete(String username);
	public void update(User user);
	public List<User> load();
	public List<User> load(String content);
	public User load(String username,String passwd);
	public User load_edit(String username);
}
