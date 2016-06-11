package org.urdad.validation.beanvalidation.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.urdad.services.Request;
import org.urdad.services.RequestNotValidException;
import org.urdad.services.Response;
import org.urdad.validation.Validation;

/**
 * FIXME: Javadoc
 */
@Stateless
@Service
public class ServiceValidationUtilities
{

    /** FIXME: Javadoc */
    public <T extends Request> void validateRequest(Class<T> requestType, T request) throws RequestNotValidException
    {
        Validation.ValidateObjectResponse validateObjectResponse = validation.validateObject(new Validation
            .ValidateObjectRequest<>(requestType, request));

        if (!validateObjectResponse.getValid())
        {
            throw new RequestNotValidException(validateObjectResponse.getMessage());
        }
    }

    /** FIXME: Javadoc */
    public <T extends Response> void validateResponse(Class<T> responseType, T response) throws RequestNotValidException
    {
        Validation.ValidateObjectResponse validateObjectResponse = validation.validateObject(new Validation
            .ValidateObjectRequest<>(responseType, response));

        if (!validateObjectResponse.getValid())
        {
            throw new RequestNotValidException(validateObjectResponse.getMessage());
        }
    }

    @Inject
    private Validation.ValidationLocal validation;

}