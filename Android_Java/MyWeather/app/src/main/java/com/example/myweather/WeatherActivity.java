package com.example.myweather;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myweather.GetWeatherClass.JSONInterface;
import com.example.myweather.GetWeatherClass.WeatherGetter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {

    public static final String URL = "http://api.openweathermap.org/data/2.5/";
    public static final String APPID = "749561a315b14523a8f5f1ef95e45864";
    public static final String UNITS ="metric";
    public static final long UPDATE_TIME = 60  * 1000; // ms

    private String city = "";

    private TextView cityName;
    private TextView temp;
    private TextView tempMax;
    private TextView tempMin;
    private TextView pressure;
    private TextView humidity;
    private JSONInterface jsonInterface;
    private Retrofit retrofit;
    private Intent returnIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        returnIntent = getIntent();
        city = returnIntent.getStringExtra("CITY");

        cityName = findViewById(R.id.cityName);
        temp = findViewById(R.id.temp);
        pressure = findViewById(R.id.pressure);
        humidity = findViewById(R.id.humidity);
        tempMin = findViewById(R.id.tempMin);
        tempMax = findViewById(R.id.tempMax);


        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

       jsonInterface = retrofit.create(JSONInterface.class);
        getWeather(jsonInterface);

        new Thread(new Runnable() { // thread to update weather forecast

            @Override
            public void run() {

                while (true) {
                    getWeather(jsonInterface);
                    SystemClock.sleep(UPDATE_TIME); // update every UPDATE_TIME
                    jsonInterface = retrofit.create(JSONInterface.class);
                }

            }
        }).start();


    }

    private void getWeather(JSONInterface jsonInterface) {

        Call<WeatherGetter> call = jsonInterface.getWeather(city,
                APPID, UNITS);

        call.enqueue(new Callback<WeatherGetter>() {
            @Override
            public void onResponse(Call<WeatherGetter> call,
                                   Response<WeatherGetter> response) {

                if (!response.isSuccessful()) {
                    goBack();
                    return;
                }

                WeatherGetter weather = response.body();
                setValueOfFields(weather);
            }

            @Override
            public void onFailure(Call<WeatherGetter> call, Throwable t) {
                //TODO: go to main activity
                cityName.setText(t.getMessage());
                goBack();
            }
        });
    }

    private void setValueOfFields(WeatherGetter weather) {
        String str;
        str = weather.getName();
        cityName.setText(str);

        str = weather.getMain().getTemp() + " \u00B0C"; // degree sign
        temp.setText(str);

        str = weather.getMain().getPressure() + " hPa";
        pressure.setText(str);

        str = weather.getMain().getHumidity() + "%";
        humidity.setText(str);

        str = weather.getMain().getTempMin()  + " \u00B0C";
        tempMin.setText(str);

        str = weather.getMain().getTempMax()  + " \u00B0C";
        tempMax.setText(str);
    }

    private void goBack() {
        Intent failIntent = new Intent(this, MainActivity.class);
        startActivity(failIntent);
    }
}
