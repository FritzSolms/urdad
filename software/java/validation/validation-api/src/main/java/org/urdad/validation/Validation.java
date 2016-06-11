package org.urdad.validation;

import javax.validation.constraints.NotNull;
import org.urdad.services.Request;
import org.urdad.services.RequestNotValidException;
import org.urdad.services.Response;

/** Service contracts that are associated with validation. */
public interface Validation
{

    /** Validates the specified object. */
    ValidateObjectResponse validateObject(ValidateObjectRequest validateObjectRequest) throws
        RequestNotValidException;

    /** FIXME: Javadoc */
    class ValidateObjectRequest<T> implements Request
    {

        /** Default constructor. */
        public ValidateObjectRequest(){}

        /** Convenience constructor. */
        public ValidateObjectRequest(Class<T> objectType, T object)
        {
            this.objectType = objectType;
            this.object = object;
        }

        /** FIXME: Javadoc */
        public Class<T> getObjectType()
        {
            return objectType;
        }

        public void setObjectType(Class<T> objectType)
        {
            this.objectType = objectType;
        }

        /** FIXME: Javadoc */
        public T getObject()
        {
            return object;
        }

        public void setObject(T object)
        {
            this.object = object;
        }

        @NotNull(message= "An object type must be specified.")
        private Class<T> objectType;
        private T object;

    }

    /** FIXME: Javadoc */
    class ValidateObjectResponse implements Response
    {

        /** Default constructor. */
        public ValidateObjectResponse(){}

        /** Convenience constructor. */
        public ValidateObjectResponse(Boolean valid)
        {
            this.valid = valid;
        }

        /** Convenience constructor. */
        public ValidateObjectResponse(Boolean valid, String message)
        {
            this.valid = valid;
            this.message = message;
        }

        /** FIXME: Javadoc */
        public Boolean getValid()
        {
            return valid;
        }

        public void setValid(Boolean valid)
        {
            this.valid = valid;
        }

        /** FIXME: Javadoc */
        public String getMessage()
        {
            return message;
        }

        public void setMessage(String message)
        {
            this.message = message;
        }

        @NotNull
        private Boolean valid;
        private String message;

    }

    /** FIXME: Javadoc. */
    interface ValidationLocal extends Validation{}

    /**
     * FIXME: Javadoc. Mention anti-pattern and why local/remote is an architectural decision. Should not
     * be enforced by the framework
     */
    interface ValidationRemote extends Validation{}

}