package edu.pdx.cs410J.smahato;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayMessage(View view) {
        //Flight flight = new Flight();
        //Toast.makeText(this, flight.toString(), Toast.LENGTH_LONG).show();
        view.setEnabled(false);
        ((Button) view).setText("Disabled!");
        int id;
        if (view.getId() == R.id.button1) {
            id = R.id.button2;
        } else {
            id = R.id.button1;
        }
        findViewById(id).setEnabled(true);
        ((Button) findViewById(id)).setText(R.string.click_me);
    }

    public void magic(View view) {
    }
}