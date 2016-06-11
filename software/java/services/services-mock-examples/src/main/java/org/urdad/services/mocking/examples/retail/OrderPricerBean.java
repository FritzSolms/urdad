package org.urdad.services.mocking.examples.retail;

import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.urdad.services.RequestNotValidException;
import org.urdad.services.mocking.examples.Address;
import org.urdad.services.mocking.examples.shipping.ShippingQuoteProvider;
import org.urdad.validation.beanvalidation.services.ServiceValidationUtilities;

@Stateless
@Service
public class OrderPricerBean implements OrderPricer
{

	@Override
	public GetOrderCostResponse getOrderCost(GetOrderCostRequest request) throws RequestNotValidException,
        ShippingQuoteProvider.DoNotShipToAddressException, ItemPricer.ItemNotAvailableException, ShippingQuoteProvider.RailwayOnStrikeException
	{
        // Check pre-condition: Request must be valid.
        serviceValidationUtilities.validateRequest(GetOrderCostRequest.class, request);

        Map<String,Integer> orderItems = request.getOrder().getOrderItems();
        double total = 0;

        for (String itemCode : orderItems.keySet())
        {
        	ItemPricer.GetItemPriceRequest req = new ItemPricer.GetItemPriceRequest(request.getOrder().getBuyer(),
                itemCode);
        	total += itemPricer.getItemPrice(req).getPrice() * orderItems.get(itemCode);
        }
        
        Address shippingAddress = request.getOrder().getBuyer().getAddress();
    	total += shippingQuoteProvider.getQuote(new ShippingQuoteProvider.GetQuoteRequest(shippingAddress)).getPrice();
    	
		return new GetOrderCostResponse(total);
	}

    @Inject
    private ServiceValidationUtilities serviceValidationUtilities;
    @Inject
    private ItemPricer itemPricer;
    @Inject
    private ShippingQuoteProvider shippingQuoteProvider;

}