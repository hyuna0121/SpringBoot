package com.example.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// 설정 파일 표시
public class FileMappingConfig implements WebMvcConfigurer { 
	
	@Value("${app.upload}")
	private String uploadPath; // file:///upload/
	
	@Value("${app.upload.url}")
	private String urlPath; // /file/**
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(urlPath).addResourceLocations(uploadPath);
	}

//	@Bean
//	FileManager getFileManager() {
//		return new FileManager(); // FileManager를 API로 가정
//	}
	
}
