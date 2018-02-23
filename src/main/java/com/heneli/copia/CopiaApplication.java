package com.heneli.copia;

import com.heneli.copia.db.PickupJdbcRepository;
import com.heneli.copia.db.RecipientJdbcRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(CopiaApplication.class, args);
		// Init DB
		// Parse CSV + Populate DB
		// Load db (HashMap<User> ?) vs LinkedHashMap
		// Run algorithm
		// CLI interface
		// Exit
	}

	@Override
	public void run(String... strings) throws Exception {
		List<Pickup> pickups = new ArrayList<>();
		pickupJdbcRepository.findAll()
				.forEach(pickups::add);

		List<Recipient> recipients = new ArrayList<>();
		recipientJdbcRepository.findAll()
				.forEach(recipients::add);

		Schedule schedule = new Schedule(pickups, recipients);

//		schedule.printMatches();

//		List<Match> matches = schedule.getMatchList();
//		List<Delivery> deliveries = Schedule.computeDeliveries(matches);

		// matchesJdbcRepository.insert(matches);
		// deliveryJdbcRepository.insert(deliveries);

		// csv-ify matches and deliveries
	}
}
