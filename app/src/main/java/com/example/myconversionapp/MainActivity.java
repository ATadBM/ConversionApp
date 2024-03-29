package com.example.myconversionapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText TextToConvert;
    TextView ConvertedText;
    Spinner SpinnerSource;
    Spinner SpinnerDestination;
    TextView ToText;

    double INCH_TO_CM = 2.54;
    double FOOT_TO_CM = 30.48;
    double YARD_TO_CM = 91.44;
    double MILE_TO_KM = 1.60934;
    double POUND_TO_KG = 0.453592;
    double OUNCE_TO_G = 28.3495;
    double TON_TO_KG = 907.185;
//    double CEL_TO_FAR = ;
//    double FAR_TO_CEL = ;
//    double CEL_TO_KEL = ;
//    double KEL_TO_CEL = ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextToConvert = findViewById(R.id.TextToConvert);
        ConvertedText = findViewById(R.id.ConvertedText);

        SpinnerSource = findViewById(R.id.SpinnerSource);
        SpinnerDestination = findViewById(R.id.SpinnerDestination);

        SpinnerSource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Selected Source Unit: " + item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        SpinnerDestination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Selected Destination Unit: " + item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<String> SourceUnitArray = new ArrayList<>();
        SourceUnitArray.add("Inch");
        SourceUnitArray.add("Foot");
        SourceUnitArray.add("Yard");
        SourceUnitArray.add("Mile");
        SourceUnitArray.add("Pound");
        SourceUnitArray.add("Ounce");
        SourceUnitArray.add("Ton");
        SourceUnitArray.add("Celsius");
        SourceUnitArray.add("Fahrenheit");
        SourceUnitArray.add("Kelvin");

        ArrayAdapter<String> SourceAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SourceUnitArray);
        SourceAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        SpinnerSource.setAdapter(SourceAdapter);

        ArrayList<String> DestinationUnitArray = new ArrayList<>();
        DestinationUnitArray.add("Centimeters");
        DestinationUnitArray.add("Kilometers");
        DestinationUnitArray.add("Grams");
        DestinationUnitArray.add("Kilograms");
        DestinationUnitArray.add("Celsius");
        DestinationUnitArray.add("Fahrenheit");
        DestinationUnitArray.add("Kelvin");

        ArrayAdapter<String> DestinationAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DestinationUnitArray);
        DestinationAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        SpinnerDestination.setAdapter(DestinationAdapter);
    }

    public void convertClick(View view)
    {
        //try/catch to deal with any input that isnt valid, show a toast message if so
        try
        {
            double TextForOperation = Double.parseDouble(TextToConvert.getText().toString());
            String SourceUnit = SpinnerSource.getSelectedItem().toString();
            String DestinationUnit = SpinnerDestination.getSelectedItem().toString();
            ConvertUnitsFunc(SourceUnit, DestinationUnit, TextForOperation);
        }
        catch (NumberFormatException e)
        {
            Toast.makeText(getApplicationContext(), "Input not accepted", Toast.LENGTH_SHORT).show();
        }

    }

    private void ConvertUnitsFunc(String SourceUnit, String DestinationUnit, double UnitToConvert)
    {
        //if source and destination are the same, print as such
        if (SourceUnit == DestinationUnit)
        {
            ConvertedText.setText("Source and Destination are the same");
            return;
        }

        double result;
        if (SourceUnit.equals("Inch") && DestinationUnit.equals("Centimeters"))
        {
            result = UnitToConvert * INCH_TO_CM;
            ConvertedText.setText(String.valueOf(result));
            return;
        }
        if (SourceUnit.equals("Foot") && DestinationUnit.equals("Centimeters"))
        {
            result = UnitToConvert * FOOT_TO_CM;
            ConvertedText.setText(String.valueOf(result));
            return;
        }
        if (SourceUnit.equals("Yard") && DestinationUnit.equals("Centimeters"))
        {
            result = UnitToConvert * YARD_TO_CM;
            ConvertedText.setText(String.valueOf(result));
            return;
        }
        if (SourceUnit.equals("Mile") && DestinationUnit.equals("Kilometers"))
        {
            result = UnitToConvert * MILE_TO_KM;
            ConvertedText.setText(String.valueOf(result));
            return;
        }

        if (SourceUnit.equals("Pound") && DestinationUnit.equals("Kilograms"))
        {
            result = UnitToConvert * POUND_TO_KG;
            ConvertedText.setText(String.valueOf(result));
            return;
        }
        if (SourceUnit.equals("Ounce") && DestinationUnit.equals("Grams"))
        {
            result = UnitToConvert * OUNCE_TO_G;
            ConvertedText.setText(String.valueOf(result));
            return;
        }
        if (SourceUnit.equals("Ton") && DestinationUnit.equals("Kilograms"))
        {
            result = UnitToConvert * TON_TO_KG;
            ConvertedText.setText(String.valueOf(result));
            return;
        }

        if (SourceUnit.equals("Celsius") && DestinationUnit.equals("Fahrenheit"))
        {
            result = (UnitToConvert * 1.8)+32;
            ConvertedText.setText(String.valueOf(result));
            return;
        }
        if (SourceUnit.equals("Fahrenheit") && DestinationUnit.equals("Celsius"))
        {
            result = (UnitToConvert - 32) / 1.8;
            ConvertedText.setText(String.valueOf(result));
            return;
        }
        if (SourceUnit.equals("Celsius") && DestinationUnit.equals("Kelvin"))
        {
            result = UnitToConvert + 273.15;
            ConvertedText.setText(String.valueOf(result));
            return;
        }
        if (SourceUnit.equals("Kelvin") && DestinationUnit.equals("Celsius"))
        {
            result = UnitToConvert - 273.15;
            ConvertedText.setText(String.valueOf(result));
            return;
        }

        //if unable to convert, print as such
        else
        {
            ConvertedText.setText("Unable to convert");
            return;
        }
    }
}