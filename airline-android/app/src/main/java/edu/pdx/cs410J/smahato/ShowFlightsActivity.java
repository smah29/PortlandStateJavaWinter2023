package edu.pdx.cs410J.smahato;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import edu.pdx.cs410J.AirportNames;
import edu.pdx.cs410J.smahato.utils.Airline;

public class ShowFlightsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_flights);
        Intent intent = getIntent();
        Airline airline = intent.getSerializableExtra(getString(R.string.airline), Airline.class);
        ListView listView = findViewById(R.id.prettyFlights);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, prettyPrint(airline));
        listView.setAdapter(arrayAdapter);
    }

    private List<String> prettyPrint(Airline airline) {
        List<String> list = new ArrayList<>();
        list.add("Flights for airline " + airline.getName());
        AtomicInteger count = new AtomicInteger();

        new ArrayList<>(airline.getFlights()).stream().sorted().forEach(flight -> {

            long durationInMinutes = Duration.between(flight.getDeparture().toInstant(),
                    flight.getArrival().toInstant()).toMinutes();

            String[] flightDetails = {
                    "# : " + count.incrementAndGet(),
                    "Flight : " + flight.getNumber(),
                    "Source : " + AirportNames.getName(flight.getSource()),
                    "Destination : " + AirportNames.getName(flight.getDestination()),
                    "Departure : " + flight.getDepartureString(),
                    "Arrival : " + flight.getArrivalString(),
                    "Duration(minutes) : " + durationInMinutes};

            list.add(Arrays.stream(flightDetails).collect(Collectors.joining("\n")));
        });
        return list;
    }
}