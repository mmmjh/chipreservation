
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
		//������Ӷ���
		Connection connection = DBUtil.getConnection();
		//׼��sql���
		String sql="select count(*) from login where username = ?";
		//������䴫�����
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			//���ս����
			resultSet=preparedStatement.executeQuery();
			//���������
			while(resultSet.next())
			{
				if(resultSet.getInt(1)>0)//>0˵�����ݿ����Ѵ��ڸ��û�
				{
					throw new UserException("�û��Ѵ���");
				}
			}
			sql="insert into login(username,passwd,name,usertype) value (?,?,?,?)";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPasswd());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setInt(4, user.getUsertype());

			preparedStatement.executeUpdate();//����
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
		//������Ӷ���
		Connection connection = DBUtil.getConnection();
		//׼��sql���
		String sql="update login set passwd =? , name =?  where username =?";
		//������䴫�����
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
		//������Ӷ���
		Connection connection = DBUtil.getConnection();
		//׼��sql���
		String sql="select * from login";
		//������䴫�����
		PreparedStatement preparedStatement=null;	
		ResultSet resultSet=null;
		//������ֻ�ܷ���user����
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
		//������Ӷ���
		Connection connection = DBUtil.getConnection();
		//׼��sql���
		String sql="select * from login where username = ?";
		//������䴫�����
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
					throw new UserException("�û���������");
				}
				if(!user.getPasswd().equals(passwd))
				{
					throw new UserException("���벻��ȷ");
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
		//������Ӷ���
		Connection connection = DBUtil.getConnection();
		//׼��sql���
		String sql="select * from login ";
		//������䴫�����
		PreparedStatement preparedStatement=null;	
		ResultSet resultSet=null;
		//������ֻ�ܷ���user����
		List<User>users=new ArrayList<User>();
		User user=null;
		try {
			System.out.println("------"+content);
			if(content == null || "".equals(content))
			{
				preparedStatement=connection.prepareStatement(sql);

			}else {
				sql += "where username like ? or name like ?";//ģ����ѯ
				preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1, "%"+ content +"%");//%��ʾn���ַ�,_��ʾ��ȷ��һ���ַ�
				preparedStatement.setString(2, "%"+ content +"%");//%��ʾn���ַ�,_��ʾ��ȷ��һ���ַ�

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
		//������Ӷ���
		Connection connection = DBUtil.getConnection();
		//׼��sql���
		String sql="select * from login where username = ?";
		//������䴫�����
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
					throw new UserException("�û���������");
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


