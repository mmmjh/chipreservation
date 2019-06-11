package com.fenjian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fenjian.bean.Chip;
import com.fenjian.bean.Record;
import com.fenjian.bean.User;
import com.fenjian.bean.UsersStatus;
import com.fenjian.util.ChipException;
import com.fenjian.util.DBUtil;
import com.fenjian.util.UserException;

public class UsersStatusDaoImpl implements IUsersStatusDao{

	
	@Override
	public void add(UsersStatus status) {
		// TODO Auto-generated method stub
		//获得链接对象
		Connection connection = DBUtil.getConnection();
		//准备sql语句
		String sql="insert into users(username,type,reservedamount,needreturn) value (?,?,?,?)";
		//创建语句传输对象
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, status.getUsername());
			preparedStatement.setString(2, status.getType());
			preparedStatement.setInt(3, status.getReservedamount());
			preparedStatement.setInt(4, status.getNeedreturn());

			preparedStatement.executeUpdate();//更改
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	}
	
	@Override
	public void update(UsersStatus status) {
		// TODO Auto-generated method stub
		//获得链接对象
		Connection connection = DBUtil.getConnection();
		//准备sql语句
		String sql="update users set reservedamount =?,needreturn = ?  where username = ? and type =?";
		//创建语句传输对象
		PreparedStatement preparedStatement=null;
		try {			
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,status.getReservedamount());
			preparedStatement.setInt(2,status.getNeedreturn());
			preparedStatement.setString(3, status.getUsername());
			preparedStatement.setString(4, status.getType());

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
	public UsersStatus load_if_exist(String username, String type) {
		// TODO Auto-generated method stub
		//获得链接对象
				Connection connection = DBUtil.getConnection();
				//准备sql语句
				String sql="select * from users where username = ? and type = ?";
				//创建语句传输对象
				PreparedStatement preparedStatement=null;	
				ResultSet resultSet=null;
				UsersStatus status=null;
				try {
						preparedStatement=connection.prepareStatement(sql);
						preparedStatement.setString(1,username);
						preparedStatement.setString(2,type);
						resultSet=preparedStatement.executeQuery();
						while(resultSet.next()) {
							status=new UsersStatus();
							status.setUsername(resultSet.getString("username"));
							status.setType(resultSet.getString("type"));
							status.setReservedamount(resultSet.getInt("reservedamount"));
							status.setNeedreturn(resultSet.getInt("needreturn"));

						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						DBUtil.close(resultSet);
						DBUtil.close(preparedStatement);
						DBUtil.close(connection);
					}
		return status;
	}
	
	
	@Override
	public List< UsersStatus> load(String name, String content) {
		// TODO Auto-generated method stub
		//获得链接对象
				Connection connection = DBUtil.getConnection();
				//准备sql语句
				String sql="select * from users where username = ? and type like ? ";
				//创建语句传输对象
				PreparedStatement preparedStatement=null;	
				ResultSet resultSet=null;
				//集合中只能放入user对象
				List<UsersStatus>uses=new ArrayList<UsersStatus>();
				UsersStatus use=null;
				try {
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1,name);
					preparedStatement.setString(2, "%"+ content +"%");//%表示n个字符,_表示精确到一个字符

					resultSet=preparedStatement.executeQuery();
					while(resultSet.next()) {
						use=new UsersStatus();
						use.setType(resultSet.getString("type"));
						use.setReservedamount(resultSet.getInt("reservedamount"));
						use.setNeedreturn(resultSet.getInt("needreturn"));
						uses.add(use);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					DBUtil.close(resultSet);
					DBUtil.close(preparedStatement);
					DBUtil.close(connection);
				}		
				return uses;
	}
	
	
	@Override
	public List< UsersStatus> load_user_type(String user,String type) {  //管理员按用户名和芯片型号模糊查询使用状况
		// TODO Auto-generated method stub
		//获得链接对象
				Connection connection = DBUtil.getConnection();
				//准备sql语句
				String sql="select * from users where username like ? and type like ? ";
				//创建语句传输对象
				PreparedStatement preparedStatement=null;	
				ResultSet resultSet=null;
				//集合中只能放入user对象
				List<UsersStatus>uses=new ArrayList<UsersStatus>();
				UsersStatus use=null;
				try {
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, "%"+ user +"%");//%表示n个字符,_表示精确到一个字符
					preparedStatement.setString(2, "%"+ type +"%");//%表示n个字符,_表示精确到一个字符
					System.out.println(user+','+type);
					resultSet=preparedStatement.executeQuery();
					while(resultSet.next()) {
						use=new UsersStatus();
						use.setUsername(resultSet.getString("username"));
						use.setType(resultSet.getString("type"));
						use.setReservedamount(resultSet.getInt("reservedamount"));
						use.setNeedreturn(resultSet.getInt("needreturn"));
						uses.add(use);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					DBUtil.close(resultSet);
					DBUtil.close(preparedStatement);
					DBUtil.close(connection);
				}		
				return uses;
	}
	
	
	public void update(String type,int num,String username) {//用户归还部分芯片 进行数据库更新（两张表同时更新）
		Connection connection = DBUtil.getConnection();
		//准备sql语句
		String sql="select * from users where username = ? and type = ?";
		String sqlchips="select * from chips where type = '"+type+"'";
		//创建语句传输对象
		PreparedStatement preparedStatement=null;	
		ResultSet resultSet=null;
		UsersStatus statusfind=null;
		Chip chip=new Chip();
		try {
				preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1,username);
				preparedStatement.setString(2,type);
				resultSet=preparedStatement.executeQuery();
				while(resultSet.next()) {
					statusfind=new UsersStatus();
					statusfind.setUsername(resultSet.getString("username"));
					statusfind.setType(resultSet.getString("type"));
					statusfind.setReservedamount(resultSet.getInt("reservedamount"));
					statusfind.setNeedreturn(resultSet.getInt("needreturn"));
				}//查询第一张表
				preparedStatement=connection.prepareStatement(sqlchips);
				resultSet=preparedStatement.executeQuery();
				while(resultSet.next()) {
					chip.setAmount(resultSet.getInt("amount"));
				}//查询第二张表
				//PreparedStatement preparedStatement=null;
				String sqluseUp1="update users set reservedamount ="+(statusfind.getReservedamount()-num)
						+",needreturn ="+(statusfind.getNeedreturn()-num)+"  where username ='"+username+"' and type ='"+type+"'";			
				preparedStatement=connection.prepareStatement(sqluseUp1);
				preparedStatement.executeUpdate();
				System.out.println("第一张表更新完成"+sqluseUp1);
				String sqluseUp2="update chips set amount ="+(chip.getAmount()+num)
						+"  where type ='"+type+"'";			
				preparedStatement=connection.prepareStatement(sqluseUp2);
				System.out.println("第二张表更新完成"+sqluseUp2);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtil.close(resultSet);
				DBUtil.close(preparedStatement);
				DBUtil.close(connection);
			}
	}
	

}
