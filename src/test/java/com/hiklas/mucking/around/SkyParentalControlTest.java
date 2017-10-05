package com.hiklas.mucking.around;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class SkyParentalControlTest
{
    public static final String EIGHTEEN_MOVIE = "Long kiss goodnight";

    public static final String UNIVERSAL_MOVIE = "Muppets Christmas Carol";

    public static final String UNKNOWN_MOVIE = "Star Trek VII - The Search for more Willian Shatner";

    public static final String FAULTY_MOVIE = "Short Circuit II";

    @Mock
    private MovieService mockMovieService;

    @InjectMocks
    private SkyParentalControl skyParentalControlToTest;


    @Test
    public void test_get_preference_returns_value_from_set()
    {
        ParentalControlLevel result = null;

        skyParentalControlToTest.setPreference(ParentalControlLevel.U);
        result = skyParentalControlToTest.getPreference();

        assertThat(result, equalTo(ParentalControlLevel.U));

        skyParentalControlToTest.setPreference(ParentalControlLevel.FIFTEEN);
        result = skyParentalControlToTest.getPreference();

        assertThat(result, equalTo(ParentalControlLevel.FIFTEEN));
    }

    @Test
    public void test_cannot_watch_eighteen_with_universal_preference() throws Exception
    {
        when(mockMovieService.getParentalControlLevel(EIGHTEEN_MOVIE))
                .thenReturn(ParentalControlLevel.EIGHTEEN.name());

        skyParentalControlToTest.setPreference(ParentalControlLevel.U);

        boolean result = skyParentalControlToTest.canWatch(EIGHTEEN_MOVIE);
        assertThat(result, equalTo(false));
    }

    @Test
    public void test_cannot_watch_eighteen_with_fifteen_preference() throws Exception
    {
        when(mockMovieService.getParentalControlLevel(EIGHTEEN_MOVIE))
                .thenReturn(ParentalControlLevel.EIGHTEEN.name());

        skyParentalControlToTest.setPreference(ParentalControlLevel.FIFTEEN);

        boolean result = skyParentalControlToTest.canWatch(EIGHTEEN_MOVIE);
        assertThat(result, equalTo(false));
    }

    @Test
    public void test_can_watch_universal_with_any_preference() throws Exception
    {
        when(mockMovieService.getParentalControlLevel(UNIVERSAL_MOVIE))
                .thenReturn(ParentalControlLevel.U.name());

        skyParentalControlToTest.setPreference(ParentalControlLevel.U);
        assertThat(skyParentalControlToTest.canWatch(UNIVERSAL_MOVIE), equalTo(true));

        skyParentalControlToTest.setPreference(ParentalControlLevel.PG);
        assertThat(skyParentalControlToTest.canWatch(UNIVERSAL_MOVIE), equalTo(true));

        skyParentalControlToTest.setPreference(ParentalControlLevel.TWELVE);
        assertThat(skyParentalControlToTest.canWatch(UNIVERSAL_MOVIE), equalTo(true));

        skyParentalControlToTest.setPreference(ParentalControlLevel.FIFTEEN);
        assertThat(skyParentalControlToTest.canWatch(UNIVERSAL_MOVIE), equalTo(true));

        skyParentalControlToTest.setPreference(ParentalControlLevel.EIGHTEEN);
        assertThat(skyParentalControlToTest.canWatch(UNIVERSAL_MOVIE), equalTo(true));
    }


    @Test
    public void test_can_watch_eighteen_with_eighteen_preference() throws Exception
    {
        when(mockMovieService.getParentalControlLevel(EIGHTEEN_MOVIE))
                .thenReturn(ParentalControlLevel.EIGHTEEN.name());

        skyParentalControlToTest.setPreference(ParentalControlLevel.EIGHTEEN);

        boolean result = skyParentalControlToTest.canWatch(EIGHTEEN_MOVIE);
        assertThat(result, equalTo(true));
    }

    @Test
    public void test_returns_false_if_movie_service_fails() throws Exception
    {
        when(mockMovieService.getParentalControlLevel(FAULTY_MOVIE))
                .thenThrow( new MovieService.TechnicalFailureException() );

        skyParentalControlToTest.setPreference(ParentalControlLevel.U);

        boolean result = skyParentalControlToTest.canWatch(FAULTY_MOVIE);
        assertThat(result, equalTo(false));
    }

    @Test( expected = MovieService.TitleNotFoundException.class )
    public void test_throws_exception_if_title_not_found() throws Exception
    {
        when(mockMovieService.getParentalControlLevel(UNKNOWN_MOVIE))
                .thenThrow( new MovieService.TitleNotFoundException() );

        skyParentalControlToTest.setPreference(ParentalControlLevel.U);

        boolean result = skyParentalControlToTest.canWatch(UNKNOWN_MOVIE);
        fail();
    }

}