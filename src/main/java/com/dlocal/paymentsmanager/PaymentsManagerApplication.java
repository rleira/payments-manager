package com.dlocal.paymentsmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class PaymentsManagerApplication extends SpringBootServletInitializer {

	@Autowired
	private Environment env;

	@Bean
	public ConfigurableServletWebServerFactory webServerFactory()
	{
		JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
		factory.setContextPath("/api");
		factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
		return factory;
	}

	public static void main(String[] args) {
		SpringApplication.run(PaymentsManagerApplication.class, args);
		new PaymentsManagerApplication().configure(new SpringApplicationBuilder(PaymentsManagerApplication.class)).run(args);
	}
}
