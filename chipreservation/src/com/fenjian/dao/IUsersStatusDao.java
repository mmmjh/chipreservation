package com.fenjian.dao;

import java.util.List;

import com.fenjian.bean.UsersStatus;

public interface IUsersStatusDao {
	public void add(UsersStatus status);
	public void update(UsersStatus status);
	public UsersStatus load_if_exist(String username,String type);
	public List<UsersStatus> load(String name, String content);
	public List< UsersStatus> load_user_type(String user,String type); //���û�����оƬ�ͺ�ģ������

	public void update(String type,int num,String username);
}
