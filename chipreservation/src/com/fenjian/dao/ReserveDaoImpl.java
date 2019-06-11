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
		
		if(status != null) {//已有该条记录，直接修改预约数量和未归还数量
			int reserveamount = status.getReservedamount() + number;
			int needreturn = status.getNeedreturn() + number;
			status.setReservedamount(reserveamount);
			status.setNeedreturn(needreturn);
			statusDao.update(status);
			
			
		}else { //记录里没有，新增一条数据
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
