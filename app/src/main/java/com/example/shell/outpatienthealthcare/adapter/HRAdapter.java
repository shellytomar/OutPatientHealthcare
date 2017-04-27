package com.example.shell.outpatienthealthcare.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.shell.outpatienthealthcare.R;
import com.example.shell.outpatienthealthcare.model.HeartBeat;

/**
 * Created by Shelly Tomar on 4/25/2017.
 */

public class HRAdapter extends RecyclerView.Adapter<HRAdapter.ViewHolder> {

    private ArrayList<HeartBeat> heartBeat;

    public HRAdapter(ArrayList<HeartBeat> hr) {
        this.heartBeat = heartBeat;
    }

    @Override
    public HRAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hr_time_card_row, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HRAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tvDate.setText(heartBeat.get(i).getDate());
        viewHolder.tvMaxValue.setText(heartBeat.get(i).getMaxHeartRate());
        viewHolder.tvMaxTime.setText(heartBeat.get(i).getMaxTime());
        viewHolder.tvMinValue.setText(heartBeat.get(i).getMinHeartRate());
        viewHolder.tvMinTime.setText(heartBeat.get(i).getMinTime());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvDate,tvMaxValue,tvMinValue,tvMaxTime,tvMinTime;

        public ViewHolder(View view) {
            super(view);

            tvDate = (TextView)view.findViewById(R.id.tvDate);
            tvMaxValue = (TextView)view.findViewById(R.id.tvMaxValue);
            tvMaxTime = (TextView)view.findViewById(R.id.tvMaxTime);
            tvMinValue = (TextView)view.findViewById(R.id.tvMinValue);
            tvMinTime = (TextView)view.findViewById(R.id.tvMinTime);

        }

    }
}
