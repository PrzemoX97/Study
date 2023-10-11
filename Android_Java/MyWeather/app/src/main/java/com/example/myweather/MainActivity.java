package com.example.myweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void checkTheWeather(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);


        EditText city = findViewById(R.id.enterCity);
        String cityName = city.getText().toString();

        intent.putExtra("CITY", cityName);


        startActivity(intent);
    }


}
