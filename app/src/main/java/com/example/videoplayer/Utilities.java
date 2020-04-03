package com.example.videoplayer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
    public static String getFormattedTime(long time){
        return new SimpleDateFormat("HH:mm:ss")
                .format(new Date(time));
    }
}
