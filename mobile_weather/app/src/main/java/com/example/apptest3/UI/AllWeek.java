package com.example.apptest3.UI;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptest3.Model.weather;
import com.example.apptest3.R;
import com.example.apptest3.Adapter.WeatherAdapter;

import java.io.IOException;
import java.util.List;

public class AllWeek extends AppCompatActivity {
    RecyclerView weatherrecycle;
    WeatherAdapter weatherAdapter;
    weatherViewModel weatherViewModel;
    public static double lnt,lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_week_layout);

       weatherViewModel = ViewModelProviders.of(this).get(weatherViewModel.class);
       weatherViewModel.api_key();

        weatherAdapter =new WeatherAdapter(getApplicationContext());
        weatherrecycle =  findViewById(R.id.weatherrecycle);
        weatherrecycle.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        weatherrecycle.setHasFixedSize(true);
        weatherrecycle.setAdapter(weatherAdapter);

        try {
            final Geocoder geocoder = new Geocoder(getApplicationContext());

            final List<Address> list = geocoder.getFromLocationName(MainActivity.city_pass, 1);
            if (!(list == null || list.isEmpty())) {
                final Address adress = list.get(0);
                lnt  =  adress.getLatitude();lng=adress.getLongitude();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        weatherViewModel.getWeatherMutableLiveData().observe(this, weathers -> weatherAdapter.setList(weathers));

    }

}
