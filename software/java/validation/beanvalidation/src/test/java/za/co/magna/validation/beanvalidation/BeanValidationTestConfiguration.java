package za.co.magna.validation.beanvalidation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.urdad.validation.Validation;
import org.urdad.validation.beanvalidation.BeanValidationBean;
import org.urdad.validation.beanvalidation.services.ServiceValidationUtilities;

@Configuration
public class BeanValidationTestConfiguration
{

    @Bean
    public javax.validation.Validator validator()
    {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public Validation.ValidationLocal validation()
    {
        return new BeanValidationBean();
    }

    @Bean
    public ServiceValidationUtilities serviceValidationUtilities()
    {
        return new ServiceValidationUtilities();
    }

}