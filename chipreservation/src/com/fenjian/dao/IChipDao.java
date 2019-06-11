package com.fenjian.dao;

import java.util.List;

import com.fenjian.bean.Chip;

public interface IChipDao {
	public void add(Chip chip);
	public void delete(String type);
	public void update(Chip chip);
	public List<Chip> load();
	public List<Chip> load(String content);
	public Chip load_amount(String type);
	public void updatereturn(Chip chip);
}
