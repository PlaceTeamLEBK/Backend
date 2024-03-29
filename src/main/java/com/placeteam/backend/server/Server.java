package com.placeteam.backend.server;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Server {
	private static final Server server = new Server();
	public static Server getInstance() {
		return server;
	}

	private ConfigurableApplicationContext ctx;

	private boolean isServerStarted;

	@Autowired
	private ServerProperties serverProperties;

	public Server() {
		isServerStarted = false;
	}

	public InetAddress getAdress() {
		return serverProperties.getAddress();
	}

	public Integer getPort() {
		return serverProperties.getPort();
	}

	public void start() {
		ctx = SpringApplication.run(this.getClass());
		isServerStarted = true;
	}

	public void stop() {
		ctx.stop();
		isServerStarted = false;
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		return args -> {
//
//			System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName);
//			}
//
//		};
//	}
}
