package com.hiklas.mucking.around;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;



public class SkyParentalControlTest
{
    private SkyParentalControl skyParentalControlToTest;


    @Before
    public void setup()
    {
        skyParentalControlToTest = new SkyParentalControl();
    }

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