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

import com.example.shell.outpatienthealthcare.adapter.UserActivityAdapter;
import com.example.shell.outpatienthealthcare.model.UserActivity;
import com.example.shell.outpatienthealthcare.rest.RestAPIClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StepCountActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private UserActivityAdapter adapter;
    ArrayList<UserActivity> userActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_data);

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
        Log.i("Reached","Inside OnCreate");
        initViews();
    }

    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        Log.i("Reached","Inside initViews");
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        getUserActivityValues();
    }

    private void getUserActivityValues(){
        Call<ArrayList<UserActivity>> call = RestAPIClient.get().getAllActivity();
        Log.i("Reached","Inside getUserActivity");
        call.enqueue(new Callback<ArrayList<UserActivity>>() {
            @Override
            public void onResponse(Call<ArrayList<UserActivity>> call, Response<ArrayList<UserActivity>> response) {
                Log.i("Reached","Inside onResponse");
                userActivity = response.body();
                adapter = new UserActivityAdapter(userActivity);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<UserActivity>> call, Throwable t) {
                Log.i("Error ", "Cannot get User Activity data");
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
            Intent i = new Intent(StepCountActivity.this, SignInActivity.class);
            startActivity(i);
        } else if (id == R.id.home) {
            Intent i = new Intent(StepCountActivity.this, DashboardActivity.class);
            startActivity(i);
        } else if (id == R.id.action_activity) {
            Intent i = new Intent(StepCountActivity.this, StepCountActivity.class);
            startActivity(i);
        } else if (id == R.id.action_bp) {
            Intent i = new Intent(StepCountActivity.this, BloodPressureActivity.class);
            startActivity(i);
        } else if (id == R.id.action_hr) {
            Intent i = new Intent(StepCountActivity.this, HeartRateActivity.class);
            startActivity(i);
        }else if (id == R.id.action_sync) {
            Intent i = new Intent(StepCountActivity.this,SyncActivity.class);
            startActivity(i);
        } else if (id == R.id.action_reports) {
            Intent i = new Intent(StepCountActivity.this, ReportActivity.class);
            startActivity(i);
        } else if (id == R.id.exit) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
