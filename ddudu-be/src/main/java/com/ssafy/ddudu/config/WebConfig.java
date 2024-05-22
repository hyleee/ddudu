package com.ssafy.ddudu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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
	
//	@Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOriginPatterns("*") // 와일드카드 대신 allowedOriginPatterns 사용
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("*")
//                .allowCredentials(true);
//    }

	
}
