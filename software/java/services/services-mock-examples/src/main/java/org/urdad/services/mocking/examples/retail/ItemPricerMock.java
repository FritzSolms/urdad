package org.urdad.services.mocking.examples.retail;

import javax.ejb.Stateless;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.urdad.services.RequestNotValidException;
import org.urdad.services.mocking.BaseMock;
import org.urdad.validation.beanvalidation.services.ServiceValidationUtilities;

/**
 * A mock pricer which 
 * <ul>
 *   <li>throws an {@link ItemNotAvailableException} if the item code is "notAvailable" and
 * returns a price 99.99 for any other item code.
 * 
 * @author fritz@solms.co.za
 *
 */
@Stateless
@Service
public class ItemPricerMock extends BaseMock implements ItemPricer
{
	@Override
	public GetItemPriceResponse getItemPrice(GetItemPriceRequest request) throws RequestNotValidException,
		ItemNotAvailableException
	{
        // Check pre-condition: Request must be valid.
        serviceValidationUtilities.validateRequest(GetItemPriceRequest.class, request);

		if (request.getItemCode().equals("notAvailable"))
		{
			throw new ItemNotAvailableException();
		}

		return new GetItemPriceResponse(99.99);
	}

	@Inject
    private ServiceValidationUtilities serviceValidationUtilities;

}