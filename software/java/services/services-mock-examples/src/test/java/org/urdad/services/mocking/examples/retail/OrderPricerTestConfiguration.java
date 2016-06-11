package org.urdad.services.mocking.examples.retail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.urdad.services.mocking.CallLoggingAspect;
import org.urdad.services.mocking.examples.shipping.ShippingQuoteProvider;
import org.urdad.services.mocking.examples.shipping.ShippingQuoteProviderMock;
import org.urdad.services.utilities.ServiceUtilities;
import org.urdad.services.utilities.ServiceUtilitiesBean;
import org.urdad.validation.Validation;
import org.urdad.validation.beanvalidation.BeanValidationBean;
import org.urdad.validation.beanvalidation.services.ServiceValidationUtilities;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class OrderPricerTestConfiguration 
{

    @Bean
    public CallLoggingAspect callLoggingAspect()
    {
        return new CallLoggingAspect();
    }

    @Bean
    public OrderPricer orderPricer()
    {
        logger.trace("Configuring order pricer bean.");
        return new OrderPricerBean();
    }

    @Bean
    public ItemPricer itemPricerMock()
    {
        logger.trace("Configuring mock item pricer.");
        return new ItemPricerMock();
    }

    @Bean
    public ShippingQuoteProvider shippingQuoteProviderMock()
    {
        logger.trace("Configuring mock shipping quote provider.");
        return new ShippingQuoteProviderMock();
    }

    @Bean
    public javax.validation.Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public Validation.ValidationLocal validation() {
        return new BeanValidationBean();
    }

    @Bean
    public ServiceValidationUtilities serviceValidationUtilities() {
        return new ServiceValidationUtilities();
    }

    @Bean
    public ServiceUtilities serviceUtilities() {
        return new ServiceUtilitiesBean();
    }

    private static final Logger logger = (Logger)LoggerFactory.getLogger(OrderPricerTestConfiguration.class);

}