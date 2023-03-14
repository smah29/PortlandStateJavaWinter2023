package edu.pdx.cs410J.smahato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void addFlightIntent(View view) {
        Intent intent = new Intent(this, AddFlightActivity.class);
        startActivity(intent);
    }

    public void searchFlightIntent(View view) {
        Intent intent = new Intent(this, SearchFlightActivity.class);
        startActivity(intent);
    }
}