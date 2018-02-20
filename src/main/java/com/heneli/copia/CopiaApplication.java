package com.heneli.copia;

import com.heneli.copia.model.Pickup;
import com.heneli.copia.model.Recipient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CopiaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CopiaApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		Pickup pickup = new Pickup();
		Recipient recipient = new Recipient();
	}
}
