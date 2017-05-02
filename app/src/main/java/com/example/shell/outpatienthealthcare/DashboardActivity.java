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
import com.example.shell.outpatienthealthcare.model.User;
import com.example.shell.outpatienthealthcare.model.UserActivity;
import com.example.shell.outpatienthealthcare.rest.RestAPIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    User user;
    UserActivity userActivity;
    TextView mStepCount, mCalories;

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

        mStepCount = (TextView) findViewById(R.id.stepValue);
        mCalories = (TextView) findViewById(R.id.caloriesValue);

        getCurrentValues();
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
        }else if (id == R.id.action_reports) {
            Intent i = new Intent(DashboardActivity.this, ReportActivity.class);
            startActivity(i);
        }else if (id == R.id.exit) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getCurrentValues() {
        Log.i("Today's Date is ", CommonUtils.getTodaysDate());
        Call<UserActivity> call = RestAPIClient.get().getCurrentStepCount(CommonUtils.getTodaysDate());
        Log.i("Value of call is",call.toString());
        call.enqueue(new Callback<UserActivity>() {
            @Override
            public void onResponse(Call<UserActivity> call, Response<UserActivity> response) {
                userActivity = response.body();
                showValues();
            }

            @Override
            public void onFailure(Call<UserActivity> call, Throwable t) {
                Log.i("Error ", "Cannot fetch user activity details");
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    private void showValues(){
        int stepCountValue = userActivity.getSteps();
        int caloriesValue = userActivity.getCalories();

        mStepCount.setText(Integer.toString(stepCountValue));
        mCalories.setText(Integer.toString(caloriesValue));
    }


}
