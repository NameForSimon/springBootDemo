package com.wndz.application;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schedule {

	@Scheduled(fixedRate = 2000000)
	public void task() {
		System.out.println("启动定时任务:" + new Date());
	}
}