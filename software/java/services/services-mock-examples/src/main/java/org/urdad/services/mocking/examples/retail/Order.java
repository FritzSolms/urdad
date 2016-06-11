package org.urdad.services.mocking.examples.retail;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.urdad.services.mocking.examples.Person;

public class Order
{

	public Order(Person buyer, Map<String, Integer> orderItems) {
		super();
		this.buyer = buyer;
		this.orderItems = orderItems;
	}
	
	public Person getBuyer() {
		return buyer;
	}

	public void setBuyer(Person buyer) {
		this.buyer = buyer;
	}

	public Map<String, Integer> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Map<String, Integer> orderItems) {
		this.orderItems = orderItems;
	}
	
	@NotNull
	private Person buyer;
	
	@NotNull
	@NotEmpty
	private Map<String,Integer> orderItems = new HashMap<>();

}