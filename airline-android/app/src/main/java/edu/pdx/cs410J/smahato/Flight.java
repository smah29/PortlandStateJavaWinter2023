package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.AbstractFlight;

public class Flight extends AbstractFlight {
    @Override
    public int getNumber() {
        return 123;
    }

    @Override
    public String getSource() {
        return "PDX";
    }

    @Override
    public String getDepartureString() {
        return "10/10/2012 10:12 am";
    }

    @Override
    public String getDestination() {
        return "LAX";
    }

    @Override
    public String getArrivalString() {
        return "10/10/2012 11:12 am";
    }
}
