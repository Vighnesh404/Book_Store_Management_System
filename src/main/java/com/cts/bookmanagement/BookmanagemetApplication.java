package com.cts.bookmanagement;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookmanagemetApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BookmanagemetApplication.class, args);
	}
	

    @Bean
    ModelMapper createModelMapperBean()
	{
		return new ModelMapper();
	}

}
