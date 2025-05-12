package com.gemini.scorm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**").allowedOrigins("http://localhost:4200") 
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") 
						.allowedHeaders("*") 
						.allowCredentials(true);

				registry.addMapping("/scorm-content/**").allowedOrigins("http://localhost:4200").allowedMethods("GET")
						.allowedHeaders("*").exposedHeaders("Content-Disposition"); // Ensure file downloads work
																					// correctly
			}

			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/scorm-content/**").addResourceLocations("file:///C:/scorm-content/")
						.setCachePeriod(0);
			}
		};
	}

}
