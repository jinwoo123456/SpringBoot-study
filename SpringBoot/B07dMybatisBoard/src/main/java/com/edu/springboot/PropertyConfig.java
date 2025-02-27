package com.edu.springboot;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PropertyConfig {
	/*
	 * name 속성을 지정하였으므로 myprops라는 이름으로 빈이 생성된다.
	 * PropertiesFactoryBean 타입의 인스턴스를 생성하여 스프링 컨테이너에
	 * 보관한다.
	 * */
	@Bean(name="myprops")
	public PropertiesFactoryBean propertiesFactoryBean() {
		
		//프로퍼티 사용을 위한 인스턴스 생성
		PropertiesFactoryBean propertiesFactoryBean =
				new PropertiesFactoryBean();
		//사용자정의 프로퍼티 파일의 경로 지정
		ClassPathResource classPathResource = 
				new ClassPathResource("page.properties");
		//경로를 등록한 후 빈(인스턴스)를 반환한다.
		propertiesFactoryBean.setLocation(classPathResource);
		return propertiesFactoryBean;
	}
}
