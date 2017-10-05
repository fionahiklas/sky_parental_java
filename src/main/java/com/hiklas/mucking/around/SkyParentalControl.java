package com.hiklas.mucking.around;

/**
 * @author Fiona Bianchi
 * @since 2017-10-05
 */
public class SkyParentalControl implements ParentalControlService
{
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
        return false;
    }
}