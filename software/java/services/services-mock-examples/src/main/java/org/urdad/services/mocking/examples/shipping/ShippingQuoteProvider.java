package org.urdad.services.mocking.examples.shipping;

import javax.validation.constraints.NotNull;
import org.urdad.services.PreconditionViolation;
import org.urdad.services.Request;
import org.urdad.services.RequestNotValidException;
import org.urdad.services.Response;
import org.urdad.services.mocking.examples.Address;

public interface ShippingQuoteProvider 
{

	GetQuoteResponse getQuote(GetQuoteRequest request) throws RequestNotValidException, DoNotShipToAddressException,
		RailwayOnStrikeException;
	
	class GetQuoteRequest implements Request
	{
		
		public GetQuoteRequest(Address address) {
			super();
			this.address = address;
		}

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		@NotNull
		private Address address;
	}
	
	class GetQuoteResponse implements Response
	{
		
		public GetQuoteResponse(double price)
		{
			super();
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

	class RailwayOnStrikeException extends PreconditionViolation
	{

		public RailwayOnStrikeException(){}

		public RailwayOnStrikeException(String msg) {super(msg);}

		public RailwayOnStrikeException(Throwable cause) {super(cause);}

		public RailwayOnStrikeException(String msg, Throwable cause) {super(msg,cause);}

	}

	class DoNotShipToAddressException extends PreconditionViolation
	{

		public DoNotShipToAddressException() {super();}

		public DoNotShipToAddressException(String message, Throwable cause) 
		{
			super(message, cause);
		}

		public DoNotShipToAddressException(String message) {super(message);}

		public DoNotShipToAddressException(Throwable cause) {super(cause);}

	}

}