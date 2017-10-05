package com.hiklas.mucking.around;

/**
 * @author Fiona Bianchi
 * @since 2017-10-05
 */
public class SkyParentalControl implements ParentalControlService
{

    @Override
    public void setPreference(ParentalControlLevel level) {

    }

    @Override
    public ParentalControlLevel getPreference() {
        return null;
    }

    @Override
    public boolean canWatch(String movieId) {
        return false;
    }
}