package com.dlocal.paymentsmanager.web;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.setInitParameter("contextConfigLocation", "com.dlocal.merchant.web");
    }
}
