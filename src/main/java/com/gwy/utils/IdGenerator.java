package com.gwy.utils;

import java.time.LocalDateTime;
import java.util.UUID;

public class IdGenerator {
	
	/**
	 * ID 产生的时间
	 * CreatePayIdEx中使用
	 */
	private static LocalDateTime ID_TIME = LocalDateTime.now();
	
	/**
	 * ID 的数字值
	 * CreatePayIdEx中使用
	 */
	private static int ID_NUMBER = 0;
	
	/**
	 * 创建PayId
	 * prefix+yyyyMMdd+4位随机数+HHmmss+SSS prefix不超过3位 总长不超过24位 
	 * @return
	 * @author Biggo 2017年6月15日
	 */
	public static String createPayId(String prefix){
		LocalDateTime time = LocalDateTime.now();
		StringBuffer payId = new StringBuffer(prefix);
		payId.append(time.toLocalDate().toString().replace("-", ""))
			.append((int) (Math.random() * 9000) + 1000)
			.append(time.toLocalTime().toString().replace(":", "").replace(".", ""));
		return payId.toString();
	}
	
	/**
	 * 创建PayId 线程安全 并保证不重复
	 * 改进了的CreatePayId算法
	 * prefix+yyyyMMdd+4位随机数+HHmmss+SSS prefix不超过3位 总长不超过24位 
	 * @return
	 * @author Biggo 2017年6月15日
	 */
	
	public static synchronized String createPayIdEx(String prefix) {
		LocalDateTime time = LocalDateTime.now();
		StringBuffer payId = new StringBuffer(prefix);
		
		int id = 0;
		if (!time.equals(ID_TIME)) {
			ID_TIME = time;
			ID_NUMBER = (int) (Math.random() * 7000) + 1000;
		} 
		ID_NUMBER ++;
		id = ID_NUMBER;
		
		payId.append(time.toLocalDate().toString().replace("-", ""))
			.append(id)
			.append(time.toLocalTime().toString().replace(":", "").replace(".", ""));
		return payId.toString();
	}

	/**
	 * 获取UUID去“-”字符的字符串
	 * @return
	 */
	public static String getUUIDStr() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
