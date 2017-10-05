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
    public boolean canWatch(String movieId) throws MovieService.TitleNotFoundException {
        int movieLevelOrdinal = 0;
        boolean canWatchResult = false;

        try
        {
            ParentalControlLevel movieLevel =
                    ParentalControlLevel.valueOf(
                            movieService.getParentalControlLevel(movieId));
            movieLevelOrdinal = movieLevel.ordinal();
            canWatchResult = ( preference.ordinal() >= movieLevelOrdinal );
        }
        catch (MovieService.TechnicalFailureException tfe)
        {
            // Do nothing, simply return false
        }

        return canWatchResult;
    }

    public void setMovieService(MovieService movieService)
    {
        this.movieService = movieService;
    }
}