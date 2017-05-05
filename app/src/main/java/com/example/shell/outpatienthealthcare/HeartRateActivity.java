package com.example.shell.outpatienthealthcare;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.shell.outpatienthealthcare.adapter.HRAdapter;
import com.example.shell.outpatienthealthcare.model.HeartBeat;
import com.example.shell.outpatienthealthcare.rest.RestAPIClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HeartRateActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private HRAdapter adapter;
    ArrayList<HeartBeat> heartBeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initViews();
    }

    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        getHeartBeatValues();
    }

    private void getHeartBeatValues(){
        Call<ArrayList<HeartBeat>> call = RestAPIClient.get().getAllHeartBeat();
        call.enqueue(new Callback <ArrayList<HeartBeat>>() {
            @Override
            public void onResponse(Call<ArrayList<HeartBeat>> call, Response<ArrayList<HeartBeat>> response) {
                heartBeat = response.body();
                adapter = new HRAdapter(heartBeat);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<HeartBeat>> call, Throwable t) {
                Log.i("Error ", "Cannot get Heartbeat data");

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.i("id is:", "" + id);
        System.out.print(id);
        System.out.print(R.id.action_activity);
        if (id == R.id.action_logout) {
            Intent i = new Intent(HeartRateActivity.this, SignInActivity.class);
            startActivity(i);
        } else if (id == R.id.home) {
            Intent i = new Intent(HeartRateActivity.this, DashboardActivity.class);
            startActivity(i);
        } else if (id == R.id.action_activity) {
            Intent i = new Intent(HeartRateActivity.this, StepCountActivity.class);
            startActivity(i);
        } else if (id == R.id.action_bp) {
            Intent i = new Intent(HeartRateActivity.this, BloodPressureActivity.class);
            startActivity(i);
        } else if (id == R.id.action_hr) {
            Intent i = new Intent(HeartRateActivity.this, HeartRateActivity.class);
            startActivity(i);
        }else if (id == R.id.action_sync) {
            Intent i = new Intent(HeartRateActivity.this,SyncActivity.class);
            startActivity(i);
        } else if (id == R.id.action_reports) {
            Intent i = new Intent(HeartRateActivity.this, ReportActivity.class);
            startActivity(i);
        } else if (id == R.id.exit) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
