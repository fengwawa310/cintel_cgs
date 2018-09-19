package com.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

@Component
public class ContextConfig implements ApplicationContextAware, ServletContextAware {

//	@Value("${context.path}")
	private String context_path;


	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	}

	public void setServletContext(ServletContext servletContext) {
		servletContext.setAttribute("context_path", context_path);
	}
}
