package com.wndz.application;


import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wndz.bean.Wndz;


@Controller
public class HelloController {
	@RequestMapping("/helloworld")
	public String hello() {
		//不能写返回的文字了，因为配置文件下配置了返回templates目录下的html文件
		return "wndz";
	}
	@RequestMapping("/helloworld2")
	public String hello2() {
		return "select";
	}
	@RequestMapping("/helloworld3")
	public String hello3(Map<String,String> map) {
		map.put("msg", "百度搜索");
		return "wndz";
	}
	
	@RequestMapping("/testjson")
	@ResponseBody
	public Wndz test() {
		Wndz wndz = new Wndz();
		wndz.wid=1;
		wndz.name="百度搜索";
		wndz.url="www.baidu.com";
		wndz.date=new Date();
		return wndz;
	}
}
