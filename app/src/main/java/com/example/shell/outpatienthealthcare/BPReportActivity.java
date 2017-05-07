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

public class BPReportActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bpreport);

        MultiBarChart multiBarChart = (MultiBarChart) findViewById(R.id.chart);
        List<ChartData> value1 = new ArrayList<>();
        List<ChartData> value2 = new ArrayList<>();



        value1.add(new ChartData(102f, 1f));
        value1.add(new ChartData(115f, 2f));
        value1.add(new ChartData(138f, 3f));
        value1.add(new ChartData(115f, 4f));
        value1.add(new ChartData(139f, 5f));
        value1.add(new ChartData(116f, 6f));
        value1.add(new ChartData(109f, 7f));
        value1.add(new ChartData(108f, 8f));
        value1.add(new ChartData(114f, 9f));
        value1.add(new ChartData(129f, 10f));
        value1.add(new ChartData(118f, 11f));


        value2.add(new ChartData(70f, 1f)); //values.add(new ChartData(y,x));
        value2.add(new ChartData(79f, 2f));
        value2.add(new ChartData(77f, 3f));
        value2.add(new ChartData(85f, 4f));
        value2.add(new ChartData(105f, 5f));
        value2.add(new ChartData(81f, 6f));
        value2.add(new ChartData(75f, 7f));
        value2.add(new ChartData(82f, 8f));
        value2.add(new ChartData(75f, 9f));
        value2.add(new ChartData(88f, 10f));
        value2.add(new ChartData(87f, 11f));



        List<ChartData> value3 = new ArrayList<>();
        value3.add(new ChartData(value1));
        value3.add(new ChartData(value2));

        multiBarChart.setData(value3);

        List<String> legends = new ArrayList<>();
        legends.add("Systolic");
        legends.add("Diastolic");
        multiBarChart.setLegends(legends);

        List<String> h_lables = new ArrayList<>();
        h_lables.add("0.0");
        h_lables.add("04/04");
        h_lables.add("04/06");
        h_lables.add("04/16");
        h_lables.add("04/27");
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
            Intent i = new Intent(BPReportActivity.this, SignInActivity.class);
            startActivity(i);
        } else if (id == R.id.home) {
            Intent i = new Intent(BPReportActivity.this, DashboardActivity.class);
            startActivity(i);
        } else if (id == R.id.action_activity) {
            Intent i = new Intent(BPReportActivity.this, StepCountActivity.class);
            startActivity(i);
        } else if (id == R.id.action_bp) {
            Intent i = new Intent(BPReportActivity.this, BloodPressureActivity.class);
            startActivity(i);
        } else if (id == R.id.action_hr) {
            Intent i = new Intent(BPReportActivity.this, HeartRateActivity.class);
            startActivity(i);
        } else if (id == R.id.action_sync) {
            Intent i = new Intent(BPReportActivity.this, SyncActivity.class);
            startActivity(i);
        } else if (id == R.id.action_reports) {
            Intent i = new Intent(BPReportActivity.this, ReportActivity.class);
            startActivity(i);
        } else if (id == R.id.exit) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
