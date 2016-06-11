package org.urdad.services.mocking;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation which is assigned to any mock object to designate that it is mocking another object.
 * This annotation is, for example, used to designate that the object must be intercepted by a call logging 
 * interceptor.
 * 
 * @author fritz@solms.co.za
 */
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mocking{}