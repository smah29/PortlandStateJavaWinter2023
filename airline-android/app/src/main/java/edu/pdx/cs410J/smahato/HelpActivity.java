package edu.pdx.cs410J.smahato;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ListView listView = findViewById(R.id.helpListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getREADME());
        listView.setAdapter(arrayAdapter);
    }

    private List<String> getREADME() {
        List<String> list = new ArrayList<>();
        list.add("Application : Project 6 (Airline)");
        list.add("Developer : Shreyoshi Mahato");
        list.add("GitHub Handle : smah29");
        list.add("GitHub Email : shreyoshimahato@gmail.com");
        list.add("PSU Email : smahato@pdx.edu");
        StringBuilder sb = new StringBuilder();
        sb.append("\n")
                .append("Airline application has two functionalities : ")
                .append("\n")
                .append("\n")
                .append("1. Add Flights")
                .append("\n")
                .append("2. Search Flights")
                .append("\n")
                .append("\n")
                .append("To add a flight - airline name, flight number, source, departure time, destination and arrival time has to be provided. ")
                .append("If all information provided is valid then only `ADD FLIGHT` button is enabled otherwise the app doesn't allow wrong information to be added. ")
                .append("When the `ADD FLIGHT` button is clicked, it's text is changed to `Clicked!`, and the added flight information is shown in a short toast. ")
                .append("Multiple flights can be added from the same screen. Remember the Airline is case insensitive, so changing the name of airline does not create another airline, it basically maps to the first airline with the same name. ")
                .append("Source and Destination are three letter codes for airport. They are case insensitive as well. ")
                .append("\n")
                .append("For searching flights the only required field is Airline (case-insensitive), " +
                        "but if either of Source or Destination is provided then both are required. When all the above mentioned clause are meet then the `SEARCH FLIGHT` button is enabled. ")
                .append("If flights for the given information are not available, then error is shown in `Search Flights` screen, otherwise flights are shown in another screen. ")
                .append("\n");
        list.add(sb.toString());
        return list;
    }
}