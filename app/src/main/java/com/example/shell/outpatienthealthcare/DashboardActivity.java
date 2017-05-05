package com.example.shell.outpatienthealthcare;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.shell.outpatienthealthcare.common.CommonUtils;
import com.example.shell.outpatienthealthcare.model.BloodPressure;
import com.example.shell.outpatienthealthcare.model.CurrentHeartBeat;
import com.example.shell.outpatienthealthcare.model.User;
import com.example.shell.outpatienthealthcare.model.UserActivity;
import com.example.shell.outpatienthealthcare.rest.RestAPIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    User user;
    UserActivity userActivity;
    CurrentHeartBeat currentHeartBeat;
    TextView mStepCount, mCalories, mHeartRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

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

        /*
        ProgressBar caloriesburntprogress = (ProgressBar) findViewById(R.id.calories_burnt_circle_progress_bar);
        caloriesburntprogress.setProgress(20);
        //getAllUsers();

        */

        if(savedInstanceState!=null){
            user = (User) savedInstanceState.getSerializable(SignUpActivity.USER);
        }else {
            user = (User) getIntent().getSerializableExtra(SignUpActivity.USER);
        }

        mStepCount = (TextView) findViewById(R.id.stepValue);
        mCalories = (TextView) findViewById(R.id.caloriesValue);
        mHeartRate = (TextView) findViewById(R.id.hrValue);

        getCurrentActivityValues();
        getCurrentHeartRateValues();
    }


    public void onClickHeartRate(View view){
        Intent intent = new Intent(DashboardActivity.this, HeartRateActivity.class);
        intent.putExtra(SignUpActivity.USER, user);
        startActivity(intent);
    }

    public void onClickBP(View view){
        Intent intent = new Intent(DashboardActivity.this, BloodPressureActivity.class);
        intent.putExtra(SignUpActivity.USER, user);
        startActivity(intent);
    }

    public void onClickActivity(View view){
        Intent intent = new Intent(DashboardActivity.this, StepCountActivity.class);
        intent.putExtra(SignUpActivity.USER, user);
        startActivity(intent);
    }

    public void onClickReport(View view){
        Intent intent = new Intent(DashboardActivity.this, ReportActivity.class);
        intent.putExtra(SignUpActivity.USER, user);
        startActivity(intent);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.i("id is:", "" + id);
        System.out.print(id);
        System.out.print(R.id.action_activity);
        if (id == R.id.action_logout) {
            Intent i = new Intent(DashboardActivity.this, SignInActivity.class);
            startActivity(i);
        } else if (id == R.id.action_activity) {
            Intent i = new Intent(DashboardActivity.this,StepCountActivity.class);
            startActivity(i);
        }else if (id == R.id.action_bp) {
            Intent i = new Intent(DashboardActivity.this,BloodPressureActivity.class);
            startActivity(i);
        }else if (id == R.id.action_hr) {
            Intent i = new Intent(DashboardActivity.this,HeartRateActivity.class);
            startActivity(i);
        }else if (id == R.id.action_sync) {
            Intent i = new Intent(DashboardActivity.this,SyncActivity.class);
            startActivity(i);
        }else if (id == R.id.action_reports) {
            Intent i = new Intent(DashboardActivity.this, ReportActivity.class);
            startActivity(i);
        }else if (id == R.id.exit) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getCurrentActivityValues() {
        Log.i("Today's Date is ", CommonUtils.getTodaysDate());
        Call<UserActivity> call = RestAPIClient.get().getCurrentStepCount(CommonUtils.getTodaysDate());
        //Call<UserActivity> call = RestAPIClient.get().getCurrentStepCount("2017-05-03");
        call.enqueue(new Callback<UserActivity>() {
            @Override
            public void onResponse(Call<UserActivity> call, Response<UserActivity> response) {
                userActivity = response.body();
                mStepCount.setText(Integer.toString(userActivity.getSteps()));
                mCalories.setText(Integer.toString(userActivity.getCalories()));
            }

            @Override
            public void onFailure(Call<UserActivity> call, Throwable t) {
                Log.i("Error ", "Cannot fetch user activity details");
            }
        });
    }

    private void getCurrentHeartRateValues() {
        Log.i("Today's Date is ", CommonUtils.getTodaysDate());
        Call<CurrentHeartBeat> call = RestAPIClient.get().getCurrentHeartBeat(CommonUtils.getTodaysDate());
        //Call<CurrentHeartBeat> call = RestAPIClient.get().getCurrentHeartBeat("2017-05-03");
        call.enqueue(new Callback<CurrentHeartBeat>() {
            @Override
            public void onResponse(Call<CurrentHeartBeat> call, Response<CurrentHeartBeat> response) {
                currentHeartBeat = response.body();
                mHeartRate.setText(Integer.toString(currentHeartBeat.getValue()));

            }

            @Override
            public void onFailure(Call<CurrentHeartBeat> call, Throwable t) {
                Log.i("Error ", "Cannot fetch current Heart Rate details");
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }



}
