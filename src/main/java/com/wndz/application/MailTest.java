package com.wndz.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wndz.bean.Wndz;
import com.wndz.mapper.WndzMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {

	@Autowired
	private JavaMailComponent javaMailComponent;
	@Autowired
	private WndzMapper wndzMapper;
	
	@Test
	public void test() {
		this.javaMailComponent.sendMail("445847261@qq.com");
	}
	
	@Test
	public void test2() {
		Wndz wndz = new Wndz();
		wndz.name="测试";
		wndzMapper.insert(wndz);
	}
}
