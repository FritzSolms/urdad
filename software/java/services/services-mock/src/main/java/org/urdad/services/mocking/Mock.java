package org.urdad.services.mocking;

/**
 * Specifies that any Mock object must be able to provide the call logger which is used to log the calls made to it in
 * order to query the call history to assert that pre-conditions and post-conditions of a contract have been addressed.
 *  
 * @author fritz@solms.co.za
 */
@Mocking
public interface Mock
{
	/**
	 * This method returns the call logger (if any) which was assigned to the mock object. It is used by the
	 * {@link CallLoggingAspect} to log call descriptions.
	 * 
	 * @return the call logger used by the mock object.
	 */
	CallLogger getCallLogger();
	
	/**
	 * Method to set the state of a mock object to some specific state in which it behaves in some particular way.
	 * 
	 * @param state the state identifier
	 * @throws InvalidStateException if the mock object does not support the specified state
	 */
	void setState(State state) throws InvalidStateException;
	
	/**
	 * @return the current state of the mock object
	 */
	State getState();
	
	interface State{}
	
	class InvalidStateException extends Exception{}

}