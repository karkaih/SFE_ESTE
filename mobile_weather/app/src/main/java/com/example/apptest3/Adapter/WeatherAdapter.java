package com.example.apptest3.Adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptest3.Model.weather;
import com.example.apptest3.R;
import com.example.apptest3.UI.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherAdapterholder> {
    private List<weather> weatherList = new ArrayList<>();
    private Context context;

    public WeatherAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public WeatherAdapterholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeatherAdapterholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_weather, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapterholder holder, int position) {
        holder.textView.setText(weatherList.get(position).getDescription());
        holder.temp.setText(weatherList.get(position).getDay());
        holder.max.setText(weatherList.get(position).getMax());
        holder.min.setText(weatherList.get(position).getMin());
        holder.night.setText(weatherList.get(position).getNight());
        holder.eve.setText(weatherList.get(position).getEve());
        holder.mor.setText(weatherList.get(position).getMorn());
        holder.town.setText(MainActivity.city_pass);
        holder.datetest.setText(convertDate(weatherList.get(position).getDt()+"000","dd/MM/yyyy hh:mm:ss"));
        switch (weatherList.get(position).getIcon()){


            case "01d": holder.wheather_image.setImageDrawable(context.getResources().getDrawable(R.drawable.d01d));
                break;
            case "01n": holder.wheather_image.setImageDrawable(context.getResources().getDrawable(R.drawable.d01d));
                break;
            case "02d": holder.wheather_image.setImageDrawable(context.getResources().getDrawable(R.drawable.d02d));
                break;
            case "02n": holder.wheather_image.setImageDrawable(context.getResources().getDrawable(R.drawable.d02d));
                break;
            case "03d": holder.wheather_image.setImageDrawable(context.getResources().getDrawable(R.drawable.d03d));
                break;
            case "03n": holder.wheather_image.setImageDrawable(context.getResources().getDrawable(R.drawable.d03d));
                break;
            case "04d": holder.wheather_image.setImageDrawable(context.getResources().getDrawable(R.drawable.d04d));
                break;
            case "04n": holder.wheather_image.setImageDrawable(context.getResources().getDrawable(R.drawable.d04d));
                break;
            case "09d": holder.wheather_image.setImageDrawable(context.getResources().getDrawable(R.drawable.d09d));
                break;
            case "09n": holder.wheather_image.setImageDrawable(context.getResources().getDrawable(R.drawable.d09d));
                break;
            case "10d": holder.wheather_image.setImageDrawable(context.getResources().getDrawable(R.drawable.d10d));
                break;
            case "10n": holder.wheather_image.setImageDrawable(context.getResources().getDrawable(R.drawable.d10d));
                break;
            case "11d": holder.wheather_image.setImageDrawable(context.getResources().getDrawable(R.drawable.d11d));
                break;
            case "11n": holder.wheather_image.setImageDrawable(context.getResources().getDrawable(R.drawable.d11d));
                break;
            case "13d": holder.wheather_image.setImageDrawable(context.getResources().getDrawable(R.drawable.d13d));
                break;
            case "13n": holder.wheather_image.setImageDrawable(context.getResources().getDrawable(R.drawable.d13d));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public void setList(List<weather> moviesList) {
        this.weatherList = moviesList;
        notifyDataSetChanged();
    }

    public static String convertDate(String dateInMilliseconds,String dateFormat) {
        return DateFormat.format(dateFormat, Long.parseLong(dateInMilliseconds)).toString();
    }

    public class WeatherAdapterholder extends RecyclerView.ViewHolder {

        TextView textView,temp ,max,min,night,eve,mor,town,datetest;
        ImageView wheather_image;
        public WeatherAdapterholder(@NonNull View itemView) {

            super(itemView);
            textView =  itemView.findViewById(R.id.desc);
            temp =  itemView.findViewById(R.id.temp);
            max =  itemView.findViewById(R.id.max);
            min =  itemView.findViewById(R.id.min);
            night =  itemView.findViewById(R.id.night);
            eve=  itemView.findViewById(R.id.eve);
            mor =  itemView.findViewById(R.id.mor);
            town =  itemView.findViewById(R.id.town);
            datetest =  itemView.findViewById(R.id.datetest);
            wheather_image =  itemView.findViewById(R.id.wheather_image);
        }
    }
}