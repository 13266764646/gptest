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
package com.gwy.jdk.proxy;

public class BookFacadeImpl implements BookFacade {

	@Override
	public void addBook() {
		System.out.println("增加图书方法。。。");  
	}

}
