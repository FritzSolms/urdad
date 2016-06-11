package org.urdad.services.mocking;

import java.time.LocalDateTime;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.urdad.services.Request;
import org.urdad.services.Response;

/**
 * Logging interceptor for Mock objects which intercepts all mock object calls and adds the call events to the call
 * history of the mock object.
 * 
 * @author fritz@solms.co.za
 *
 */
@Interceptor
@Mocking
public class CallLoggingInterceptor 
{
	/**
	 * The actual interceptor method which intercepts all calls to all
	 * services of mocking service providers, i.e. service provider classes
	 * which have been annotated as {@link Mocking}.
	 * 
	 * @param invocationContext the context of the invocation including information of what is intercepted and how the
	 * intercepted object was called.
	 * @return the return value of the intercepted method (or it will raise the exception raised by the intercepted
	 * method)
	 * @throws Exception the exception raised by the intercepted method
	 */
	@AroundInvoke
	public Object logCall(InvocationContext invocationContext) throws Exception
	{	
		System.out.println("Logging call");
		LocalDateTime requestTime = LocalDateTime.now();
		Request request = (Request)invocationContext.getParameters()[0];

		Object response;
		LocalDateTime responseTime;
		
		try
		{
		  	response = (Response) invocationContext.proceed();

		  	responseTime = LocalDateTime.now();

		  	((Mock)invocationContext.getTarget()).getCallLogger().logCall(new CallDescriptor(invocationContext
			  .getMethod(), requestTime, request, response, responseTime));

			return response;
		}
		catch (Exception e)
		{
			responseTime = LocalDateTime.now();

			response = e;

			((Mock)invocationContext.getTarget()).getCallLogger().logCall(new CallDescriptor(invocationContext
				.getMethod(), requestTime, request, response, responseTime));

			throw e;
		}
	}

}