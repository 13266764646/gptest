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

public class PersonService {

	 public PersonService() {
	        System.out.println("PersonService构造");
	    }
	 
	    //该方法不能被子类覆盖
	     public final Person getPerson(String code) {
	        System.out.println("PersonService:getPerson>>"+code);
	        return null;
	    }

	    public void setPerson() {
	        System.out.println("PersonService:setPerson");
	    }

}
