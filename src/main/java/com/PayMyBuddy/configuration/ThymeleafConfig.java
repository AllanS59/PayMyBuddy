//package com.PayMyBuddy.configuration;
//
//import javax.servlet.ServletContext;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Description;
//import org.springframework.context.support.ResourceBundleMessageSource;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.spring5.view.ThymeleafViewResolver;
//import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
//
//
//public class ThymeleafConfig {
//
//	@Bean
//	public ServletContextTemplateResolver templateResolver(ServletContext servletContext) {
//	  ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
//	    //templateResolver.setPrefix("/WEB-INF/templates/");
//	    templateResolver.setSuffix(".html");
//	    templateResolver.setTemplateMode("HTML5");
//
//	    return templateResolver;
//	}
//
//	@Bean
//	@Description("Thymeleaf Template Engine")
//	public SpringTemplateEngine templateEngine(ServletContext servletContext) {
//	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//	    templateEngine.setTemplateResolver(templateResolver(servletContext));
//	    templateEngine.setTemplateEngineMessageSource(messageSource());
//	    return templateEngine;
//	}
//	
//	@Bean
//	@Description("Thymeleaf View Resolver")
//	public ThymeleafViewResolver viewResolver(ServletContext servletContext) {
//	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//	    viewResolver.setTemplateEngine(templateEngine(servletContext));
//	    viewResolver.setOrder(1);
//	    return viewResolver;
//	}
//	
//	@Bean
//	@Description("Spring Message Resolver")
//	public ResourceBundleMessageSource messageSource() {
//	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//	    messageSource.setBasename("messages");
//	    return messageSource;
//	}
//	
//}
