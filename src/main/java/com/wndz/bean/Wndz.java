package com.wndz.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Wndz {
	public int wid;
	public String name;
	public String url;
	@JSONField(format="yyyy-MM-dd")
	public Date date;
	public String path;
}
