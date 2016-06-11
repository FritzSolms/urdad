package org.urdad.services.mocking;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import org.urdad.services.utilities.MethodOverrideChecker;
import org.urdad.services.utilities.MethodOverrideCheckerBean;
import org.urdad.services.utilities.ServiceUtilities;
import org.urdad.services.utilities.ServiceUtilities.NotAServiceException;
import org.urdad.services.utilities.ServiceUtilitiesBean;

/**
 * A basic implementation of a call logger which maintains a list of call descriptors.
 * 
 * @author fritz@solms.co.za
 */
public class SimpleCallLogger implements CallLogger 
{

	public SimpleCallLogger(BaseMock mock)
	{
		this.mock = mock;
	}
	
	@Override
	public void logCall(CallDescriptor callDescriptor) 
	{
		callLog.add(callDescriptor); 
	}

	@Override
	public List<CallDescriptor> getCallLog() { return callLog; }

	@Override
	public int getInvocationCount(Method method) 
	{
		int numCalls = 0;
		for (CallDescriptor callDescriptor : callLog)
		{
			if (methodOverrideChecker.overrides(method, callDescriptor.getMethod()))
				numCalls++;
		}
		return numCalls;
	}

	@Override
	public BaseMock getMockObject() { return mock; }
	
	@Override
	public int getInvocationCount(String serviceName) throws NotAServiceException
	{
		Method method = serviceUtilities.getService(mock, serviceName);
		return getInvocationCount(method);
	}

	@Override
	public void clearLog() { callLog.clear(); }

	private BaseMock mock;
	private ServiceUtilities serviceUtilities = new ServiceUtilitiesBean();
	private MethodOverrideChecker methodOverrideChecker = new MethodOverrideCheckerBean();
	private List<CallDescriptor> callLog = new LinkedList<>();

}
