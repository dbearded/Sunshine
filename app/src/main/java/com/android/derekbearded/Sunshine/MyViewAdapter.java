package com.android.derekbearded.Sunshine;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sputnik on 3/9/2018.
 */

class MyViewAdapter extends RecyclerView.Adapter<MyViewAdapter.MyViewHolder>{


    private List<ForecastDay> forecastDays;
    private int expandedPosition = -1;
    private int prevExpandedPos = -1;
    private RecyclerView recyclerView;

    public MyViewAdapter(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void setForecastDays(List<ForecastDay> forecastDays) {
        this.forecastDays = forecastDays;
        notifyDataSetChanged();
    }

    public void clear() {
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        ForecastDay day = forecastDays.get(position);
        holder.high.setText(String.valueOf(day.high.fahrenheit) + "\u00B0");
        holder.low.setText(String.valueOf(day.low.fahrenheit) + "\u00B0");
        holder.condition.setText(day.conditions);
        holder.date.setText(day.date.toString());
        holder.humidity.setText(String.valueOf(day.avehumidity) + "%");
        holder.wind.setText(day.avewind.toString());

        Picasso.get().load(day.icon_url).into(holder.icon);

        final boolean isExpanded = position == expandedPosition;
        holder.detailView.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.itemView.setActivated(isExpanded);
        if (isExpanded){
            prevExpandedPos = position;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandedPosition = isExpanded ? -1: position;
//                TransitionManager.beginDelayedTransition(recyclerView);
                notifyItemChanged(prevExpandedPos);
                notifyItemChanged(expandedPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return forecastDays.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView high, low, condition, date, wind, humidity;
        ViewGroup detailView;

        public MyViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            high = itemView.findViewById(R.id.tvHigh);
            low = itemView.findViewById(R.id.tvLow);
            condition = itemView.findViewById(R.id.tvConditions);
            date = itemView.findViewById(R.id.tvDate);
            wind = itemView.findViewById(R.id.tvWindVal);
            humidity = itemView.findViewById(R.id.tvHumidityVal);
            detailView = itemView.findViewById(R.id.detailView);
        }
    }
}
