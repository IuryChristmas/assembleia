package com.cooperado.assembleia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.cooperado.assembleia.cors.config.AssembleiaApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(AssembleiaApiProperty.class)
public class AssembleiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssembleiaApplication.class, args);
	}

}
