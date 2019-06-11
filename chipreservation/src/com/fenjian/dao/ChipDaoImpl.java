package com.fenjian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fenjian.bean.Chip;
import com.fenjian.bean.User;
import com.fenjian.util.DBUtil;
import com.fenjian.util.UserException;
import com.fenjian.util.ChipException;

public class ChipDaoImpl implements IChipDao{

	@Override
	public void add(Chip chip) {
		// TODO Auto-generated method stub
		//获得链接对象
				Connection connection = DBUtil.getConnection();
				//准备sql语句
				String sql="select count(*) from chips where type = ?";
				//创建语句传输对象
				PreparedStatement preparedStatement=null;
				ResultSet resultSet=null;
				try {
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, chip.getType());
					//接收结果集
					resultSet=preparedStatement.executeQuery();
					//遍历结果集
					while(resultSet.next())
					{
						if(resultSet.getInt(1)>0)//>0说明数据库中已存在该用户
						{
							throw new ChipException("该芯片型号已存在");
						}
					}
					sql="insert into chips(type,amount) value (?,?)";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, chip.getType());
					preparedStatement.setInt(2, chip.getAmount());
					
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
	public void delete(String type) {
		// TODO Auto-generated method stub
		Connection connection=DBUtil.getConnection();
		String sql="delete from chips where type = ?";
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,type);
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
	public void update(Chip chip) {
		// TODO Auto-generated method stub
		//获得链接对象
				Connection connection = DBUtil.getConnection();
				//准备sql语句
				String sql="update chips set amount =?  where type =?";
				//创建语句传输对象
				PreparedStatement preparedStatement=null;
				try {			
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setInt(1,chip.getAmount());
					preparedStatement.setString(2, chip.getType());
					
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
	public List<Chip> load() {
		// TODO Auto-generated method stub
		//获得链接对象
				Connection connection = DBUtil.getConnection();
				//准备sql语句
				String sql="select * from chips";
				//创建语句传输对象
				PreparedStatement preparedStatement=null;	
				ResultSet resultSet=null;
				//集合中只能放入user对象
				List<Chip>chips=new ArrayList<Chip>();
				Chip chip=null;
				try {
					preparedStatement=connection.prepareStatement(sql);
					resultSet=preparedStatement.executeQuery();
					while(resultSet.next()) {
						chip=new Chip();
						chip.setType(resultSet.getString("type"));
						chip.setAmount(resultSet.getInt("amount"));
						chips.add(chip);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					DBUtil.close(resultSet);
					DBUtil.close(preparedStatement);
					DBUtil.close(connection);
				}		
				return chips;
	}

	@Override
	public List<Chip> load(String content) {
		// TODO Auto-generated method stub
		//获得链接对象
				Connection connection = DBUtil.getConnection();
				//准备sql语句
				String sql="select * from chips ";
				//创建语句传输对象
				PreparedStatement preparedStatement=null;	
				ResultSet resultSet=null;
				//集合中只能放入user对象
				List<Chip>chips=new ArrayList<Chip>();
				Chip chip = null;
				try {
					System.out.println("------"+content);
					if(content == null || "".equals(content))
					{
						preparedStatement=connection.prepareStatement(sql);

					}else {
						sql += "where type like ? ";//模糊查询
						preparedStatement=connection.prepareStatement(sql);
						preparedStatement.setString(1, "%"+ content +"%");//%表示n个字符,_表示精确到一个字符
					}
					resultSet = preparedStatement.executeQuery();
					while(resultSet.next()) {
						chip=new Chip();
						chip.setType(resultSet.getString("type"));
						chip.setAmount(resultSet.getInt("amount"));
						chips.add(chip);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					DBUtil.close(resultSet);
					DBUtil.close(preparedStatement);
					DBUtil.close(connection);
				}
						
				return chips;
	}

	@Override
	public Chip load_amount(String type) {
		// TODO Auto-generated method stub
		//获得链接对象
				Connection connection = DBUtil.getConnection();
				//准备sql语句
				String sql="select * from chips where type = ?";
				//创建语句传输对象
				PreparedStatement preparedStatement=null;	
				ResultSet resultSet=null;
				Chip chip=null;
				try {
						preparedStatement=connection.prepareStatement(sql);
						preparedStatement.setString(1,type);
						resultSet=preparedStatement.executeQuery();
						while(resultSet.next()) {
							chip=new Chip();
							chip.setType(resultSet.getString("type"));
							chip.setAmount(resultSet.getInt("amount"));
						
						}
						if(chip == null)
						{
							throw new ChipException("该型号不存在");
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						DBUtil.close(resultSet);
						DBUtil.close(preparedStatement);
						DBUtil.close(connection);
					}
						
					return chip;
	}
	
	@Override
	public void updatereturn(Chip chip) {
		// TODO Auto-generated method stub
		//获得链接对象
				Connection connection = DBUtil.getConnection();
				//准备sql语句
				String sql="update chips set amount =?  where type =?";
				//创建语句传输对象
				PreparedStatement preparedStatement=null;
				try {			
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setInt(1,chip.getAmount());
					preparedStatement.setString(2, chip.getType());
					
					preparedStatement.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					DBUtil.close(preparedStatement);
					DBUtil.close(connection);
				}
	}

}
