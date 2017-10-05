package com.hiklas.mucking.around;

/**
 * @author Fiona Bianchi
 * @since 2017-10-05
 */
public interface ParentalControlService {

    /**
     * Set the level for this instance of the service
     *
     * @param level
     */
    void setPreference(ParentalControlLevel level);

    /**
     * Get the parental control level for this instance
     * @return
     */
    ParentalControlLevel getPreference();

    /**
     * Can the given movie be watched based on the
     * parental control level
     *
     * @param movieId
     * @return true if the movie can be watched, false if not or on error
     */
    boolean canWatch(String movieId);
}
