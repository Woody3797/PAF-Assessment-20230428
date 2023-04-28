package ibf2022.assessment.paf.batch3.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonObject;

@Repository
public class OrderRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	// TODO: Task 5
	public JsonObject addNewOrder(JsonObject jo) {
		JsonObject inserted = mongoTemplate.insert(jo, "orders");
		return inserted;
	}
}
