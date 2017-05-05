package com.example.shell.outpatienthealthcare.model;

import java.io.Serializable;

/**
 * Created by Shelly Tomar on 4/25/2017.
 */

public class BloodPressure implements Serializable {
    private int id;
    private int systolicBP;
    private int diastolicBP;
    private String date;

    public void setId(int id) {
        this.id = id;
    }

    public void setSystolicBP(int systolicBP) {
        this.systolicBP = systolicBP;
    }

    public void setDiastolicBP(int diastolicBP) {
        this.diastolicBP = diastolicBP;
    }

    public void setDateTime(String date) {
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public int getSystolicBP() {
        return systolicBP;
    }

    public int getDiastolicBP() {
        return diastolicBP;
    }

    public String getDate() {
        return date;
    }

}
