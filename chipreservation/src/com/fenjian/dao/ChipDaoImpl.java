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
		//������Ӷ���
				Connection connection = DBUtil.getConnection();
				//׼��sql���
				String sql="select count(*) from chips where type = ?";
				//������䴫�����
				PreparedStatement preparedStatement=null;
				ResultSet resultSet=null;
				try {
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, chip.getType());
					//���ս����
					resultSet=preparedStatement.executeQuery();
					//���������
					while(resultSet.next())
					{
						if(resultSet.getInt(1)>0)//>0˵�����ݿ����Ѵ��ڸ��û�
						{
							throw new ChipException("��оƬ�ͺ��Ѵ���");
						}
					}
					sql="insert into chips(type,amount) value (?,?)";
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, chip.getType());
					preparedStatement.setInt(2, chip.getAmount());
					
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
		//������Ӷ���
				Connection connection = DBUtil.getConnection();
				//׼��sql���
				String sql="update chips set amount =?  where type =?";
				//������䴫�����
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
		//������Ӷ���
				Connection connection = DBUtil.getConnection();
				//׼��sql���
				String sql="select * from chips";
				//������䴫�����
				PreparedStatement preparedStatement=null;	
				ResultSet resultSet=null;
				//������ֻ�ܷ���user����
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
		//������Ӷ���
				Connection connection = DBUtil.getConnection();
				//׼��sql���
				String sql="select * from chips ";
				//������䴫�����
				PreparedStatement preparedStatement=null;	
				ResultSet resultSet=null;
				//������ֻ�ܷ���user����
				List<Chip>chips=new ArrayList<Chip>();
				Chip chip = null;
				try {
					System.out.println("------"+content);
					if(content == null || "".equals(content))
					{
						preparedStatement=connection.prepareStatement(sql);

					}else {
						sql += "where type like ? ";//ģ����ѯ
						preparedStatement=connection.prepareStatement(sql);
						preparedStatement.setString(1, "%"+ content +"%");//%��ʾn���ַ�,_��ʾ��ȷ��һ���ַ�
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
		//������Ӷ���
				Connection connection = DBUtil.getConnection();
				//׼��sql���
				String sql="select * from chips where type = ?";
				//������䴫�����
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
							throw new ChipException("���ͺŲ�����");
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
		//������Ӷ���
				Connection connection = DBUtil.getConnection();
				//׼��sql���
				String sql="update chips set amount =?  where type =?";
				//������䴫�����
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
