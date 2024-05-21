package com.ssafy.ddudu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Value("${upload.folder.path}")
    private String uploadFolderPath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry
      .addResourceHandler("/resources/**") 
      .addResourceLocations("file:" + uploadFolderPath+"/");
	}
	
}
