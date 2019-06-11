
package com.fenjian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fenjian.util.DBUtil;
import com.fenjian.util.UserException;
import com.fenjian.bean.User;

public class UserDaoImpl implements IUserDao{

	@Override
	public void add(User user) {
		//获得链接对象
		Connection connection = DBUtil.getConnection();
		//准备sql语句
		String sql="select count(*) from login where username = ?";
		//创建语句传输对象
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			//接收结果集
			resultSet=preparedStatement.executeQuery();
			//遍历结果集
			while(resultSet.next())
			{
				if(resultSet.getInt(1)>0)//>0说明数据库中已存在该用户
				{
					throw new UserException("用户已存在");
				}
			}
			sql="insert into login(username,passwd,name,usertype) value (?,?,?,?)";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPasswd());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setInt(4, user.getUsertype());

			preparedStatement.executeUpdate();//更改
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		
	}

	@Override
	public void delete(String username) {
		Connection connection=DBUtil.getConnection();
		String sql="delete from login where username = ?";
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,username);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		
	}

	@Override
	public void update(User user) {
		//获得链接对象
		Connection connection = DBUtil.getConnection();
		//准备sql语句
		String sql="update login set passwd =? , name =?  where username =?";
		//创建语句传输对象
		PreparedStatement preparedStatement=null;
		try {			
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,user.getPasswd());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getUsername());
	
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	
	}

	

	@Override
	public List<User> load() {
		//获得链接对象
		Connection connection = DBUtil.getConnection();
		//准备sql语句
		String sql="select * from login";
		//创建语句传输对象
		PreparedStatement preparedStatement=null;	
		ResultSet resultSet=null;
		//集合中只能放入user对象
		List<User>users=new ArrayList<User>();
		User user=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				user=new User();
				user.setUsername(resultSet.getString("username"));
				user.setPasswd(resultSet.getString("passwd"));
				user.setName(resultSet.getString("name"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
				
		return users;
	}

	
	
	@Override
	public User load(String username, String passwd) {
		// TODO Auto-generated method stub
		//获得链接对象
		Connection connection = DBUtil.getConnection();
		//准备sql语句
		String sql="select * from login where username = ?";
		//创建语句传输对象
		PreparedStatement preparedStatement=null;	
		ResultSet resultSet=null;
		User user=null;
		try {
				preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1,username);
				resultSet=preparedStatement.executeQuery();
				while(resultSet.next()) {
					user=new User();
					user.setUsername(resultSet.getString("username"));
					user.setPasswd(resultSet.getString("passwd"));
					user.setName(resultSet.getString("name"));
					user.setUsertype(resultSet.getInt("usertype"));

				}
				if(user == null)
				{
					throw new UserException("用户名不存在");
				}
				if(!user.getPasswd().equals(passwd))
				{
					throw new UserException("密码不正确");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtil.close(resultSet);
				DBUtil.close(preparedStatement);
				DBUtil.close(connection);
			}
				
			return user;
	}

	@Override
	public List<User> load(String content) {
		// TODO Auto-generated method stub
		//获得链接对象
		Connection connection = DBUtil.getConnection();
		//准备sql语句
		String sql="select * from login ";
		//创建语句传输对象
		PreparedStatement preparedStatement=null;	
		ResultSet resultSet=null;
		//集合中只能放入user对象
		List<User>users=new ArrayList<User>();
		User user=null;
		try {
			System.out.println("------"+content);
			if(content == null || "".equals(content))
			{
				preparedStatement=connection.prepareStatement(sql);

			}else {
				sql += "where username like ? or name like ?";//模糊查询
				preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1, "%"+ content +"%");//%表示n个字符,_表示精确到一个字符
				preparedStatement.setString(2, "%"+ content +"%");//%表示n个字符,_表示精确到一个字符

			}
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				user=new User();
				user.setUsername(resultSet.getString("username"));
				user.setPasswd(resultSet.getString("passwd"));
				user.setName(resultSet.getString("name"));
				user.setUsertype(resultSet.getInt("usertype"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
				
		return users;
	}

	@Override
	public User load_edit(String username) {
		// TODO Auto-generated method stub
		//获得链接对象
		Connection connection = DBUtil.getConnection();
		//准备sql语句
		String sql="select * from login where username = ?";
		//创建语句传输对象
		PreparedStatement preparedStatement=null;	
		ResultSet resultSet=null;
		User user=null;
		try {
				preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1,username);
				resultSet=preparedStatement.executeQuery();
				while(resultSet.next()) {
					user=new User();
					user.setUsername(resultSet.getString("username"));
					user.setPasswd(resultSet.getString("passwd"));
					user.setName(resultSet.getString("name"));
					user.setUsertype(resultSet.getInt("usertype"));
				}
				if(user == null)
				{
					throw new UserException("用户名不存在");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtil.close(resultSet);
				DBUtil.close(preparedStatement);
				DBUtil.close(connection);
			}
				
			return user;
	}

}


