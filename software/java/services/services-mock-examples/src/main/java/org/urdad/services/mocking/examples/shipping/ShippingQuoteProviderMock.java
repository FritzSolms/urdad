package org.urdad.services.mocking.examples.shipping;

import javax.ejb.Stateless;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.urdad.services.RequestNotValidException;
import org.urdad.services.mocking.BaseMock;
import org.urdad.services.mocking.Mock;
import org.urdad.validation.beanvalidation.services.ServiceValidationUtilities;

/**
 * A mock ShippingQuoteProvider which 
 * <ul>
 *   <li>throws {@link RailwayOnStrikeException} if set in a railwayOnStrike state, </li>
 *   <li>throws {@link DoNotShipToAddressException} if the shipping city is Timbuktu,</li>
 *   <li>returns a zero quote if the city is Pofadder, and</li>
 *   <li>otherwise returns a fixed cost of 888.88.</li>
 * </ul>  
 * 
 * @author fritz@solms.co.za
 *
 */
@Stateless
@Service
public class ShippingQuoteProviderMock extends BaseMock implements ShippingQuoteProvider
{
	public ShippingQuoteProviderMock()
	{
		setState(State.externalRequirementsMet);
	}
	
	public GetQuoteResponse getQuote(GetQuoteRequest request) 
		throws RequestNotValidException, DoNotShipToAddressException, RailwayOnStrikeException
	{
        // Check pre-condition: Request must be valid.
        serviceValidationUtilities.validateRequest(GetQuoteRequest.class, request);
        
		if (getState() == State.railwayOnStrike)
			throw new RailwayOnStrikeException();
		
		if (request.getAddress().getCity().trim().toLowerCase().equals("timbuktu"))
			throw new DoNotShipToAddressException();
		
		if (request.getAddress().getCity().trim().toLowerCase().equals("pofadder"))
			return new GetQuoteResponse(0);
		else
			return new GetQuoteResponse(888.88);
		
	}
	
	public enum State implements Mock.State{externalRequirementsMet, railwayOnStrike}
	
    @Inject
    private ServiceValidationUtilities serviceValidationUtilities;

}