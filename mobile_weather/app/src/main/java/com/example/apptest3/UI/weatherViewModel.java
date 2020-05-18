package com.example.apptest3.UI;

import android.location.Geocoder;
import android.os.StrictMode;
import android.os.TransactionTooLargeException;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apptest3.Model.weather;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class weatherViewModel extends ViewModel {
    private MutableLiveData<List<weather>> weatherMutableLiveData;
    private List<weather> weatherList = new ArrayList<>();

    public LiveData<List<weather>> getWeatherMutableLiveData() {
        weatherMutableLiveData =  new MutableLiveData<>();
        return weatherMutableLiveData;
    }

    public void api_key() {
        OkHttpClient client=new OkHttpClient();
        double Ln = 31.55;
        double Lg = -9.77;
       if (AllWeek.lng!=0 &&AllWeek.lnt!=0){
           Ln=AllWeek.lnt;
           Lg=AllWeek.lng;
       }
        String BaseUrl = "https://api.openweathermap.org/data/2.5/onecall?lat="+Ln+"&lon="+Lg+"&exclude=hourly,current&appid=a6f41d947e0542a26580bcd5c3fb90ef&units=metric";

        Request request=new Request.Builder()
                .url(BaseUrl)
                .get()
                .build();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData= response.body().string();
                try {
                    JSONObject json=new JSONObject(responseData);
                    JSONArray array=json.getJSONArray("daily");

                    for (int i = 0;i<array.length();i++) {
                        JSONObject object = array.getJSONObject(i);//0
                        JSONObject tt = object.getJSONObject("temp");//temp

                        JSONArray t3 = object.getJSONArray("weather");//weather
                        JSONObject object2 = t3.getJSONObject(0);

                        weather weather = new weather(tt.getString("day")+"°C",tt.getString("min")+"°C",tt.getString("max")+"°C",tt.getString("night")+"°C"
                        ,tt.getString("eve")+"°C",tt.getString("morn")+"°C",object2.getString("description"),object2.getString("icon")
                        ,object.getString("dt"));
                        weatherList.add(weather);
                    }
                    try{
                        weatherMutableLiveData.postValue(weatherList);
                    }catch (Throwable t){
                        Log.e("Mut",t.getLocalizedMessage());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
