package com.ivanz.socialnetworkbackend.content;

import org.springframework.boot.SpringApplication;

public class TestContentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ContentServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
