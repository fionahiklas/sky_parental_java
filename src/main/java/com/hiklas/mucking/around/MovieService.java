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
     *
     */
    class TechnicalFailureException extends Exception
    {

    }

    String getParentalControlLevel(String movieId)
            throws TitleNotFoundException,
            TechnicalFailureException;
}
