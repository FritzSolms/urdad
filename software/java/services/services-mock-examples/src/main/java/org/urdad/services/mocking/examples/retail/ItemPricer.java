package org.urdad.services.mocking.examples.retail;

import org.urdad.services.PreconditionViolation;
import org.urdad.services.Request;
import org.urdad.services.RequestNotValidException;
import org.urdad.services.Response;
import org.urdad.services.mocking.examples.Person;

public interface ItemPricer
{
	
	GetItemPriceResponse getItemPrice(GetItemPriceRequest request) throws RequestNotValidException,
		ItemNotAvailableException;
	
	class GetItemPriceResponse implements Response
	{
		public GetItemPriceResponse(double price)
		{
			this.price = price;
		}
		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		private double price;
	}

	class GetItemPriceRequest implements Request
	{

		public GetItemPriceRequest(Person person, String itemCode) {
			super();
			this.person = person;
			this.itemCode = itemCode;
		}

		public Person getPerson() {
			return person;
		}

		public void setPerson(Person person) {
			this.person = person;
		}

		public String getItemCode() {
			return itemCode;
		}

		public void setItemCode(String itemCode) {
			this.itemCode = itemCode;
		}

		private Person person;
		private String itemCode;
	}

	class ItemNotAvailableException extends PreconditionViolation
	{
		public ItemNotAvailableException()
		{
			super();
		}

		public ItemNotAvailableException(String message, Throwable cause) {
			super(message, cause);
		}

		public ItemNotAvailableException(String message) {
			super(message);
		}

		public ItemNotAvailableException(Throwable cause) {
			super(cause);
		}
	}

}