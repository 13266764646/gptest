package com.gwy;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Hello world!
 *
 */
@EnableZuulProxy
@SpringCloudApplication
public class GpApplication {

	public static void main(String[] args) {
		System.out.println("---test branches----");
		new SpringApplicationBuilder(GpApplication.class).web(true).run(args);
	}

}
