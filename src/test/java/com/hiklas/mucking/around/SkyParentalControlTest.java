package com.hiklas.mucking.around;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class SkyParentalControlTest
{

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


}