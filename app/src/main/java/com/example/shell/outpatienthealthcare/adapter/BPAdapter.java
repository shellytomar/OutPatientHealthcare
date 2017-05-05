package com.example.shell.outpatienthealthcare.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shell.outpatienthealthcare.R;
import com.example.shell.outpatienthealthcare.model.BloodPressure;
import com.example.shell.outpatienthealthcare.model.User;

import java.util.ArrayList;

import static android.R.id.message;

/**
 * Created by Shelly Tomar on 4/25/2017.
 */

public class BPAdapter extends RecyclerView.Adapter<BPAdapter.ViewHolder> {

    private ArrayList<BloodPressure> bp;
    User user;
    public BPAdapter(ArrayList<BloodPressure> bp) {
        this.bp = bp;
    }

    @Override
    public BPAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bp_card_row,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BPAdapter.ViewHolder viewHolder, int i) {
        int systolicBP = bp.get(i).getSystolicBP();
        int diastolicBP = bp.get(i).getDiastolicBP();
        viewHolder.tvDate.setText(String.valueOf(bp.get(i).getDate()));
        viewHolder.tvSystolicValue.setText(Integer.toString(systolicBP));
        viewHolder.tvDiastolicValue.setText(Integer.toString(diastolicBP));
        viewHolder.tvMessage.setText(bpMessage(diastolicBP, systolicBP));
    }

    @Override
    public int getItemCount() {
        return (bp == null) ? 0 :bp.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvDate,tvSystolicValue,tvDiastolicValue,tvMessage;

        public ViewHolder(View view) {
            super(view);

            tvDate = (TextView)view.findViewById(R.id.tvDate);
            tvSystolicValue = (TextView)view.findViewById(R.id.tvSystolicValue);
            tvDiastolicValue = (TextView)view.findViewById(R.id.tvDiastolicValue);
            tvMessage = (TextView)view.findViewById(R.id.tvMessage);

        }
    }

    public String bpMessage(int systolic, int diastolic) {
        String message ="";
        if(systolic<120 && diastolic<80) {
            message= "Normal Blood Pressure";
        } else if (systolic>=120 && systolic<=139 && diastolic>=80 && diastolic<106){
            message="Prehypertension";
        }else if (systolic>=140 && systolic<159 && diastolic>=106 && diastolic<108){
            message="High Blood Pressure Stage 1";
        }else if (systolic>=160 && systolic<180 && diastolic>=108 && diastolic<110){
            message="High Blood Pressure Stage 2";
        }else if (systolic>180 && diastolic>110) {
            message = "Hypertensive Crisis";
        }else
            message = "Normal Blood Pressure";
        return message;
    }
}