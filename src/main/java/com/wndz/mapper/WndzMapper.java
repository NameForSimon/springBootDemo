package com.wndz.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.wndz.bean.Wndz;

@Mapper
public interface WndzMapper {
	public void insert(Wndz wndz);
}
