package com.example.shell.outpatienthealthcare.model;

import java.io.Serializable;

/**
 * Created by Shelly Tomar on 4/25/2017.
 */

public class BloodPressure implements Serializable {
    private int bpInfoID;
    private int systolicBP;
    private int diastolicBP;
    private String dateTime;

    public void setBpInfoID(int bpInfoID) {
        this.bpInfoID = bpInfoID;
    }

    public void setSystolicBP(int systolicBP) {
        this.systolicBP = systolicBP;
    }

    public void setDiastolicBP(int diastolicBP) {
        this.diastolicBP = diastolicBP;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }


    public int getBpInfoID() {
        return bpInfoID;
    }

    public int getSystolicBP() {
        return systolicBP;
    }

    public int getDiastolicBP() {
        return diastolicBP;
    }

    public String getDateTime() {
        return dateTime;
    }


}
