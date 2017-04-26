package com.example.shell.outpatienthealthcare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.shell.outpatienthealthcare.adapter.BPAdapter;
import com.example.shell.outpatienthealthcare.model.BloodPressure;

import java.util.ArrayList;
import java.util.List;

public class BloodPressureActivity extends AppCompatActivity {

    BloodPressure bloodPressure;
    List<BloodPressure> bloodPressureList;
    private RecyclerView recyclerView;
    private ArrayList<BloodPressure> bloodPressureArrayList;
    private BPAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);
        initViews();
    }

    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }



}
