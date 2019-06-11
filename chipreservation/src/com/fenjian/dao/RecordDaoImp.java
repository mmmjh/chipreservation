package com.fenjian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.fenjian.bean.*;
import com.fenjian.bean.Chip;
import com.fenjian.bean.User;
import com.fenjian.bean.UsersStatus;
import com.fenjian.util.ChipException;
import com.fenjian.util.DBUtil;
import com.fenjian.util.UserException;

public class RecordDaoImp{


	public void add(Record record) {
		// TODO Auto-generated method stub
		//������Ӷ���
		Connection connection = DBUtil.getConnection();
		//׼��sql���
		String sql="insert into records(identifier,thedate,username,type,operation,number) value (?,?,?,?,?,?)";
		//������䴫�����
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, record.getIdentifier());
			preparedStatement.setString(2, record.getThedate());
			preparedStatement.setString(3, record.getUsername());
			preparedStatement.setString(4, record.getType());
			preparedStatement.setString(5, record.getOperate());
			preparedStatement.setInt(6, record.getNumber());
			System.out.println(sql);
			preparedStatement.executeUpdate();//����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	}
	
	
	public List<Record> load(String username,String content) {
		// TODO Auto-generated method stub
		//������Ӷ���
				Connection connection = DBUtil.getConnection();
				//׼��sql���
				String sql="select * from records where username = ? and type like ?";
				//������䴫�����
				PreparedStatement preparedStatement=null;	
				ResultSet resultSet=null;
				//������ֻ�ܷ���user����
				List<Record>uses=new ArrayList<Record>();
				Record use=null;
				try {
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, "%" + content + "%");

					resultSet=preparedStatement.executeQuery();
					while(resultSet.next()) {
						use=new Record();
						use.setIdentifier(resultSet.getString("identifier"));
						use.setThedate(resultSet.getString("thedate"));
						use.setUsername(resultSet.getString("username"));
						use.setType(resultSet.getString("type"));
						use.setOperate(resultSet.getString("operation"));
						use.setNumber(resultSet.getInt("number"));
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
	
	
	public List<Record> load_user_type(String user,String type) {  //����Ա���û�����оƬ�ͺ�ģ����ѯ��־
		// TODO Auto-generated method stub
		//������Ӷ���
				Connection connection = DBUtil.getConnection();
				//׼��sql���
				String sql="select * from records where username like ? and type like ?";
				//������䴫�����
				PreparedStatement preparedStatement=null;	
				ResultSet resultSet=null;
				//������ֻ�ܷ���user����
				List<Record>uses=new ArrayList<Record>();
				Record use=null;
				try {
					preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, "%" + user + "%");
					preparedStatement.setString(2, "%" + type + "%");

					resultSet=preparedStatement.executeQuery();
					while(resultSet.next()) {
						use=new Record();
						use.setIdentifier(resultSet.getString("identifier"));
						use.setThedate(resultSet.getString("thedate"));
						use.setUsername(resultSet.getString("username"));
						use.setType(resultSet.getString("type"));
						use.setOperate(resultSet.getString("operation"));
						use.setNumber(resultSet.getInt("number"));
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
	public Pager<Record> load(String username,String content, int pageIndex, int pageSize) {
		Pager<Record> pager = new  Pager<Record>();
		Connection connection = DBUtil.getConnection();
		//������䴫�����
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//׼��sql���
		String sql = "select * from records where username='"+username+"'  ";
		//����������
		String  sqlCount = "select count(*) from records  where username='"+username+"' ";
		//������ֻ�ܷ���user����
		List<Record> users = new ArrayList<Record>();
		Record user = null;
		try {
			if (pageIndex <= 0) {
				pageIndex = 1;
			}
			
			int start = (pageIndex-1)*pageSize;
			
			if (content == null || "".equals(content)) {
				sql += "";
			}else {
				sql += " AND type like '%" + content +"%'";
				sqlCount += " AND type like '%" + content +"%'";
				
			}
			
			preparedStatement = connection.prepareStatement(sqlCount);
			resultSet = preparedStatement.executeQuery();
			
			//�ܼ�¼��
			int totalRecord = 0;
			
			while(resultSet.next()) {
				totalRecord = resultSet.getInt(1);
			}	
			//��ҳ��
			int totalPage = totalRecord%pageSize == 0 ? totalRecord/pageSize : totalRecord/pageSize + 1;
			
			//�ӷ�ҳ
			sql += "limit ?,?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, pageSize);
			resultSet = preparedStatement.executeQuery();
			System.out.println(sql+"ģ����ѯ");
			while(resultSet.next()) {
				user = new Record();
				user.setIdentifier(resultSet.getString("identifier"));
				user.setThedate(resultSet.getString("thedate"));
				user.setUsername(resultSet.getString("username"));
				user.setType(resultSet.getString("type"));
				user.setOperate(resultSet.getString("operation"));
				user.setNumber(resultSet.getInt("number"));				
				users.add(user);
			}	
			//����ҳ����������������
			pager.setDatas(users);
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			pager.setTotalPage(totalPage);
			pager.setTotalRecord(totalRecord);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return pager;
	}
	
	public Pager<Record> adminload(String userContent,String typeContent, int pageIndex, int pageSize) {
		Pager<Record> pager = new  Pager<Record>();
		Connection connection = DBUtil.getConnection();
		//������䴫�����
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//׼��sql���
		String sql = "select * from records ";
		//����������
		String  sqlCount = "select count(*) from records";
		//������ֻ�ܷ���user����
		List<Record> users = new ArrayList<Record>();
		Record user = null;
		try {
			if (pageIndex <= 0) {
				pageIndex = 1;
			}
			
			int start = (pageIndex-1)*pageSize;
			
			if (userContent != null && !"".equals(userContent)) {
				sql += " where username like '%" +userContent+"%'";
				sqlCount += " where username like '%" +userContent+"%'";
			}
			if (typeContent != null && !"".equals(typeContent)) {
				sql += " AND type like '%" +typeContent+"%'";
				sqlCount += " AND type like '%" +  typeContent +"%'";
			}
			
			preparedStatement = connection.prepareStatement(sqlCount);
			resultSet = preparedStatement.executeQuery();
			
			//�ܼ�¼��
			int totalRecord = 0;
			
			while(resultSet.next()) {
				totalRecord = resultSet.getInt(1);
			}	
			//��ҳ��
			int totalPage = totalRecord%pageSize == 0 ? totalRecord/pageSize : totalRecord/pageSize + 1;
			
			//�ӷ�ҳ
			sql += "limit ?,?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, pageSize);
			resultSet = preparedStatement.executeQuery();
			System.out.println(sql+"ģ����ѯ");
			while(resultSet.next()) {
				user = new Record();
				user.setIdentifier(resultSet.getString("identifier"));
				user.setThedate(resultSet.getString("thedate"));
				user.setUsername(resultSet.getString("username"));
				user.setType(resultSet.getString("type"));
				user.setOperate(resultSet.getString("operation"));
				user.setNumber(resultSet.getInt("number"));				
				users.add(user);
			}	
			//����ҳ����������������
			pager.setDatas(users);
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			pager.setTotalPage(totalPage);
			pager.setTotalRecord(totalRecord);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return pager;
	}


}
