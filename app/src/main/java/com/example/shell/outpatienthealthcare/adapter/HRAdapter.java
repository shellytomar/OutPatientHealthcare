package com.example.shell.outpatienthealthcare.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.shell.outpatienthealthcare.R;
import com.example.shell.outpatienthealthcare.model.HeartRate;

/**
 * Created by Shelly Tomar on 4/25/2017.
 */

public class HRAdapter extends RecyclerView.Adapter<HRAdapter.ViewHolder> {

    private ArrayList<HeartRate> hr;

    public HRAdapter(ArrayList<HeartRate> hr) {
        this.hr = hr;
    }

    @Override
    public HRAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hr_card_row, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HRAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tvDate.setText(hr.get(i).getDate());
        viewHolder.tvMaxValue.setText(hr.get(i).getMax());
        viewHolder.tvMinValue.setText(hr.get(i).getMin());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvDate,tvMaxValue,tvMinValue;

        public ViewHolder(View view) {
            super(view);

            tvDate = (TextView)view.findViewById(R.id.tvDate);
            tvMaxValue = (TextView)view.findViewById(R.id.tvMaxValue);
            tvMinValue = (TextView)view.findViewById(R.id.tvMinValue);

        }

    }
}
