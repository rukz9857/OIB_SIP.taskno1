package com.example.unit_convertor;

// MainActivity.java
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    private EditText input;
    private TextView result;
    private Spinner conversionSpinner;
    private Button btnConvert;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        input = findViewById(R.id.TextValue);
        conversionSpinner= findViewById(R.id.spinnerinput);
        btnConvert = findViewById(R.id.btnConvert);
        result = findViewById(R.id.textViewResult);

        // Populate the Spinner with conversion options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.conversion_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conversionSpinner.setAdapter(adapter);

        // Set a listener for item selection in the Spinner
        conversionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                // You can perform actions when a new item is selected if needed
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing here
            }
        });

        // Set a click listener for the convert button
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertUnits();
            }
        });
    }

    private void setSupportActionBar(Toolbar myToolbar) {
    }


    private void convertUnits() {
        try {
            double inputValue = Double.parseDouble(input.getText().toString());
            String selectedConversion = conversionSpinner.getSelectedItem().toString();
            double resultValue = 0;

            switch (selectedConversion) {
                case "Meters to Feet":
                    resultValue = inputValue * 3.28084;
                    break;
                case "Celsius to Fahrenheit":
                    resultValue = (inputValue * 9 / 5) + 32;
                    break;
                case "Grams to Kilograms":
                    resultValue = inputValue / 1000;
                    break;
                case "Grams to Pounds":
                    resultValue = inputValue * 0.00220462;
                    break;
                case "Centimeters to Meters":
                    resultValue = inputValue / 100;
                    break;
                // Add more cases for other conversions

                default:
                    result.setText("Select a valid conversion");
                    return;
            }

            result.setText(String.format("%.2f %s", resultValue, getTargetUnit(selectedConversion)));
        } catch (NumberFormatException e) {
            result.setText("Enter a valid number");
        }
    }

    private String getTargetUnit(String selectedConversion) {
        String[] parts = selectedConversion.split(" to ");
        return parts[1];
    }

}

