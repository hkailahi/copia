package com.heneli.copia;

import com.heneli.copia.db.MatchJdbcRepository;
import com.heneli.copia.db.PickupJdbcRepository;
import com.heneli.copia.db.RecipientJdbcRepository;
import com.heneli.copia.model.Match;
import com.heneli.copia.model.Pickup;
import com.heneli.copia.model.Recipient;
import com.heneli.copia.schedule.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CopiaApplication implements CommandLineRunner {

	@Autowired
	PickupJdbcRepository pickupJdbcRepository;
	@Autowired
	RecipientJdbcRepository recipientJdbcRepository;
	@Autowired
	MatchJdbcRepository matchJdbcRepository;

	public static void main(String[] args) {
		SpringApplication.run(CopiaApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		System.out.println("Parsing CSVs...   Done.");

		List<Pickup> pickups = new ArrayList<>();
		List<Recipient> recipients = new ArrayList<>();

		System.out.print("Loading Pickups from database...");
		pickupJdbcRepository.findAll()
				.forEach(pickups::add);
		System.out.print("   Done.\n");

		System.out.print("Loading Recipients from database...");
		recipientJdbcRepository.findAll()
				.forEach(recipients::add);
		System.out.print("   Done.\n");

		Schedule schedule = new Schedule(pickups, recipients);

		System.out.print("Loading matches into database...");
		pickups.parallelStream().forEach(pickup -> {
					List<Match> matches = schedule.getMatches(pickup);
					if (matches != null) matches.forEach(match -> matchJdbcRepository.insert(match));
				});
		System.out.print("   Done.\n");

		System.out.print("Writing matches to CSV...");
		// TODO - write to csvs
		System.out.print("   Done.\n");

		System.out.println("All match making complete!\n");
		System.out.println("You can run your own queries on the database at http://localhost:8080/h2");
		System.out.println("To stop the server, press control-c twice.");
	}
}
