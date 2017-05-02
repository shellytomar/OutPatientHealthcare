package com.example.shell.outpatienthealthcare.common;

import java.util.Calendar;


public class CommonUtils {

    public static String getTodaysDate(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR)+"-0"+String.valueOf(c.get(Calendar.MONTH)+1)+"-0"+c.get(Calendar.DAY_OF_MONTH);
    }
}
