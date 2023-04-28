package ibf2022.assessment.paf.batch3.services;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import ibf2022.assessment.paf.batch3.repositories.OrderRepository;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@Service
public class BeerService {

	@Autowired
	OrderRepository orderRepository;

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public String placeOrder(Integer breweryId, MultiValueMap<String, String> payload) {
		// TODO: Task 5 
		UUID id = UUID.randomUUID();
		String orderId = id.toString().substring(0, 8);
		LocalDate date = LocalDate.now();

		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

		for (String k : payload.keySet()) {
			objectBuilder.add("beerId", k)
			.add("quantity", payload.get(k).get(0));
		}

		JsonArray orders = arrayBuilder.add(0, objectBuilder).build();

		JsonObject jo = Json.createObjectBuilder()
		.add("orderId", orderId)
		.add("date", date.toString())
		.add("breweryId", Integer.toString(breweryId))
		.add("orders", orders)
		.build();

		orderRepository.addNewOrder(jo);

		return orderId;
	}
}
