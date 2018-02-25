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
import org.springframework.context.ConfigurableApplicationContext;

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

	@Autowired
	private ConfigurableApplicationContext context;


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

		System.out.println("\nNOTE: Please note that each of the following steps may take a few minutes:\n");

		System.out.print("Generating all recipient matches...");
		Schedule schedule = new Schedule(pickups, recipients);
		System.out.print("   Done.\n");

		System.out.print("Loading matches into database...");
		pickups.parallelStream().forEach(pickup -> {
					List<Match> matches = schedule.getMatches(pickup);
					if (matches != null) matches.forEach(match -> matchJdbcRepository.insert(match));
				});
		System.out.print("   Done.\n");

		/* == NOTE: To generate only a CSV of sorted matches, please change getSorted to true and getUnsorted to false. If both are true, you will get both sorted and unsorted CSVs == */
		boolean getUnsorted = true, getSorted = false;

		System.out.print("Writing matches to CSV...");
		if (getUnsorted) matchJdbcRepository.exportMatchesToCSV();
		if (getSorted) matchJdbcRepository.exportSortedMatchesToCSV();
		System.out.print("   Done.\n");

		StringBuilder outputFiles = new StringBuilder();
		if (getUnsorted && getSorted) outputFiles.append("matches.csv and sorted_matches.csv files.");
		else if (getUnsorted) outputFiles.append("matches.csv file.");
		else outputFiles.append("sorted_matches.csv file.");

		System.out.println("\nProgram complete! Results have been written to the "
				+ outputFiles.toString());

		/* == NOTE: To run queries in H2, change willInteractWithDatabase to true. Please note, you will have to manually shut down the server! == */
		boolean willInteractWithDatabase = false;

		if (willInteractWithDatabase) {
			System.out.println("You can run your own queries on the database at http://localhost:8080/h2");
			System.out.println("To stop the server, press control-c twice.");

		}
		else {
			SpringApplication.exit(context);
		}
	}

}
