/*
 * Created: 2019年2月28日
 *
 * Shenzhen Yikuyi Co., Ltd. All rights reserved. 
 * Copyright (c) 2015-2019 
 * License, Version 1.0. Published by Yikuyi IT department.
 *
 * For the convenience of communicating and reusing of codes,
 * any java names, variables as well as comments should be written according to the regulations strictly.
 */
package com.gwy.cglib.proxy;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;

public class CglibProxyTest {

	public static void main(String[] args) {
		////代理类class文件存入本地磁盘
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(PersonService.class);
		enhancer.setCallback(new CglibProxyIntercepter());
		PersonService proxy = (PersonService)enhancer.create();
		proxy.setPerson();
		proxy.getPerson("1");
	}
}
