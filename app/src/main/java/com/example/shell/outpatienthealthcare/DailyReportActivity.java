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

import com.numetriclabz.numandroidcharts.ChartData;
import com.numetriclabz.numandroidcharts.MultiBarChart;

import java.util.ArrayList;
import java.util.List;

public class DailyReportActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_report);

        MultiBarChart multiBarChart = (MultiBarChart) findViewById(R.id.chart);
        List<ChartData> value1 = new ArrayList<>();
        List<ChartData> value2 = new ArrayList<>();

//        for (int i=0; i<bloodPressure.size(); i++) {

//        float sys = bloodPressure.get(0).getSystolicBP();

        value1.add(new ChartData(12691f, 1f)); //values.add(new ChartData(y,x));
        value1.add(new ChartData(5598f, 2f));
        value1.add(new ChartData(5789f, 3f));
        value1.add(new ChartData(10778f, 4f));
        value1.add(new ChartData(45f, 5f));
        value1.add(new ChartData(8245f, 6f));
        value1.add(new ChartData(240f, 7f));


//            float dia = bloodPressure.get(0).getDiastolicBP();

        value2.add(new ChartData(3064f, 1f)); //values.add(new ChartData(y,x));
        value2.add(new ChartData(2343f, 2f));
        value2.add(new ChartData(1881f, 3f));
        value2.add(new ChartData(2485f, 4f));
        value2.add(new ChartData(634f, 5f));
        value2.add(new ChartData(2387f, 6f));
        value2.add(new ChartData(802f, 7f));

//        }

        List<ChartData> value3 = new ArrayList<>();
        value3.add(new ChartData(value1));
        value3.add(new ChartData(value2));

        multiBarChart.setData(value3);


        List<String> legends = new ArrayList<>();
        legends.add("Steps");
        legends.add("Calories");
        multiBarChart.setLegends(legends);

        List<String> h_lables = new ArrayList<>();
        h_lables.add("0.0");
        h_lables.add("04/30");
        h_lables.add("05/01");
        h_lables.add("05/02");
        h_lables.add("05/03");
        h_lables.add("05/04");
        h_lables.add("05/05");
        h_lables.add("05/06");

        multiBarChart.setHorizontal_label(h_lables);

        multiBarChart.setGesture(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.i("id is:", "" + id);
        System.out.print(id);
        System.out.print(R.id.action_activity);
        if (id == R.id.action_logout) {
            Intent i = new Intent(DailyReportActivity.this, SignInActivity.class);
            startActivity(i);
        } else if (id == R.id.home) {
            Intent i = new Intent(DailyReportActivity.this, DashboardActivity.class);
            startActivity(i);
        } else if (id == R.id.action_activity) {
            Intent i = new Intent(DailyReportActivity.this, StepCountActivity.class);
            startActivity(i);
        } else if (id == R.id.action_bp) {
            Intent i = new Intent(DailyReportActivity.this, BloodPressureActivity.class);
            startActivity(i);
        } else if (id == R.id.action_hr) {
            Intent i = new Intent(DailyReportActivity.this, HeartRateActivity.class);
            startActivity(i);
        } else if (id == R.id.action_sync) {
            Intent i = new Intent(DailyReportActivity.this, SyncActivity.class);
            startActivity(i);
        } else if (id == R.id.action_reports) {
            Intent i = new Intent(DailyReportActivity.this, ReportActivity.class);
            startActivity(i);
        } else if (id == R.id.exit) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
