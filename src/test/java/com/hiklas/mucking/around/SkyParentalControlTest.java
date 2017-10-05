package com.hiklas.mucking.around;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class SkyParentalControlTest
{
    public static final String EIGHTEEN_MOVIE = "Long kiss goodnight";

    public static final String UNKOWN_MOVIE = "Star Trek VII - The Search for more Willian Shatner";

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

}