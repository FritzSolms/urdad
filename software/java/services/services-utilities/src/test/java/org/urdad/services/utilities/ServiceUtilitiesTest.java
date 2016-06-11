package org.urdad.services.utilities;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.urdad.services.Request;
import org.urdad.services.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceUtilitiesTestConfiguration.class})
/**
 * A unit test for ServiceUtilities implementations. 
 * 
 * @author fritz@solms.co.za
 */
public class ServiceUtilitiesTest 
{

	@Test
	public void testRequestClassForValidService() 
	{
		DummyServiceProvider dummyServiceProvider = new DummyServiceProviderBean();
		try
		{
			Class<? extends Object> requestClass 
				= serviceUtilities.getRequestClass(dummyServiceProvider, "service1");
			assertTrue(requestClass.getName().equals
				("org.urdad.services.utilities.ServiceUtilitiesTest$DummyServiceProvider$Service1Request"));
		}
		catch (Exception e)
		{
			fail("Could not retrieve valid service.");
		}
	}
	
	@Test
	public void testRequestClassForWhenNoMethodWithRequestedServiceName()
	{
		DummyServiceProvider dummyServiceProvider = new DummyServiceProviderBean();
		try
		{
			serviceUtilities.getRequestClass(dummyServiceProvider, "unknownService");
			fail("Found service which does not exist");
		}
		catch (Exception e) {}
	}

	@Test
	public void testRequestClassForWhenMethodNotComplyingToServiceRequirements()
	{
		DummyServiceProvider dummyServiceProvider = new DummyServiceProviderBean();
		try
		{
			serviceUtilities.getRequestClass(dummyServiceProvider, "service2");
			fail("Found service2 though does not comply to service requirements");

			serviceUtilities.getRequestClass(dummyServiceProvider, "service3");
			fail("Found service3 though does not comply to service requirements");
		}
		catch (Exception e) {}
	}

	@Test
	public void testResponseClassForValidService() 
	{
		DummyServiceProvider dummyServiceProvider = new DummyServiceProviderBean();
		try
		{
			Class<? extends Object> responseClass 
				= serviceUtilities.getResponseClass(dummyServiceProvider, "service1");
			assertTrue(responseClass.getName().equals
				("org.urdad.services.utilities.ServiceUtilitiesTest$DummyServiceProvider$Service1Response"));
		}
		catch (Exception e)
		{
			fail("Could not retrieve valid service.");
		}
	}

	@Inject
	private ServiceUtilities serviceUtilities;
	
	public interface DummyServiceProvider
	{
		Service1Response service1(Service1Request request);
		Service2Response service2(Service2Request request, int extraParam);
		int service3(int nonReqeustParam);
		
		class Service1Request implements Request{}
		class Service1Response implements Response{}
		class Service2Request implements Request{}
		class Service2Response implements Response{}
	}

	public class DummyServiceProviderBean implements DummyServiceProvider
	{
		@Override
		public Service1Response service1(Service1Request request) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Service2Response service2(Service2Request request, int extraParam) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int service3(int nonReqeustParam) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}

}