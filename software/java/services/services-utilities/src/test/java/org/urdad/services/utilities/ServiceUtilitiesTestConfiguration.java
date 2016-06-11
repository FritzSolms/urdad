package org.urdad.services.utilities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * The spring configuration for the {@link ServiceUtilitiesTest}.
 * 
 * @author fritz@solms.co.za
 */
@EnableAspectJAutoProxy
@Configuration
public class ServiceUtilitiesTestConfiguration 
{

	@Bean
	public ServiceUtilities serviceUtilities()
	{
		return new ServiceUtilitiesBean();
	}

}