package com.emincingoz.bookservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class BookServiceApplication {
	protected BookServiceApplication() {log.info("Application has been started.");}

	/**
	 * Application starting point
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

}
