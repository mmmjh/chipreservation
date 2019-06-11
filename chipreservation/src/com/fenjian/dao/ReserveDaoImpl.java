package com.fenjian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fenjian.bean.Chip;
import com.fenjian.bean.UsersStatus;
import com.fenjian.util.DBUtil;

public class ReserveDaoImpl {
	public void reserveAdd(String username,String type,int number) {
		UsersStatusDaoImpl statusDao = new UsersStatusDaoImpl();
		UsersStatus status = statusDao.load_if_exist(username, type);
		
		if(status != null) {//���и�����¼��ֱ���޸�ԤԼ������δ�黹����
			int reserveamount = status.getReservedamount() + number;
			int needreturn = status.getNeedreturn() + number;
			status.setReservedamount(reserveamount);
			status.setNeedreturn(needreturn);
			statusDao.update(status);
			
			
		}else { //��¼��û�У�����һ������
			UsersStatus statusNew = new UsersStatus();
			statusNew.setUsername(username);
			statusNew.setType(type);
			statusNew.setReservedamount(number);
			statusNew.setNeedreturn(number);
			statusDao.add(statusNew);
		}
		
		ChipDaoImpl chipDao = new ChipDaoImpl();
		Chip chip = chipDao.load_amount(type);
		chip.setAmount(chip.getAmount()-number);
		chipDao.update(chip);
	}
}
