package com.hiklas.mucking.around;

/**
 * @author Fiona Bianchi
 * @since 2017-10-05
 */
public class SkyParentalControl implements ParentalControlService
{
    /**
     * Instance of the movie service to call for ratings
     */
    private MovieService movieService;

    /**
     * Current preference
     */
    private ParentalControlLevel preference;

    @Override
    public void setPreference(ParentalControlLevel level) {
        preference = level;
    }

    @Override
    public ParentalControlLevel getPreference() {
        return preference;
    }

    @Override
    public boolean canWatch(String movieId) {
        int movieLevelOrdinal = 0;

        try
        {
            ParentalControlLevel movieLevel =
                    ParentalControlLevel.valueOf(
                            movieService.getParentalControlLevel(movieId));
            movieLevelOrdinal = movieLevel.ordinal();
        }
        catch (Exception ee)
        {
            // Temporary for testing purposes
            throw new RuntimeException(ee);
        }

        return ( preference.ordinal() >= movieLevelOrdinal );
    }

    public void setMovieService(MovieService movieService)
    {
        this.movieService = movieService;
    }
}