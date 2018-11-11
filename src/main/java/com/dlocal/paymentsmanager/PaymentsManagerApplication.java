package com.dlocal.paymentsmanager;

import com.dlocal.paymentsmanager.services.MasterSlaveService;
import org.springframework.beans.factory.annotation.Autowired;
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
		factory.setPort(
				Integer.parseInt(
						MasterSlaveService.isMaster() ?
						env.getProperty("server.master.port") :
						env.getProperty("server.worker.port")
				)
		);
		return factory;
	}

	public static void main(String[] args) {
		MasterSlaveService.setMaster(args.length > 0 && args[0].matches("--master"));
		System.out.println("Is cluster Master: " + MasterSlaveService.isMaster());

		new PaymentsManagerApplication().configure(new SpringApplicationBuilder(PaymentsManagerApplication.class)).run(args);
	}
}
