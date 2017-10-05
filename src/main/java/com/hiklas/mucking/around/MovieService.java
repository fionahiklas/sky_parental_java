package com.hiklas.mucking.around;

/**
 * @author Sky Digital
 * @since 2017-10-05
 */
public interface MovieService {

    /**
     * Exception indicating the movie wasn't found
     */
    class TitleNotFoundException extends Exception
    {

    }

    /**
     * Internal failure for the service
     */
    class TechnicalFailureException extends Exception
    {

    }


    /**
     * Return the parental control level for a given movie
     *
     * @param movieId
     * @return String representation of parental control
     * @throws TitleNotFoundException
     * @throws TechnicalFailureException
     */
    String getParentalControlLevel(String movieId)
            throws TitleNotFoundException,
            TechnicalFailureException;
}
