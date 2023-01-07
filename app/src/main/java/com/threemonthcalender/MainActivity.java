package com.threemonthcalender;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.threemonthcalender.databinding.ActivityMainBinding;
import com.threemonthcalender.limitedCalender.LimitedCalenderHandler;
import com.threemonthcalender.limitedCalender.SetOnCalenderClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ActivityMainBinding mainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        List<String> list = new ArrayList<>();
        list.add("2023-01-15");
        list.add("2023-01-26");
        list.add("2023-01-16");
        list.add("2023-02-13");
        list.add("2023-02-25");
        list.add("2023-03-02");
        list.add("2023-03-20");

        LimitedCalenderHandler limitedCalenderHandler = LimitedCalenderHandler
                .getInstance(this, findViewById(R.id.list_of_month))
                .setOnCalenderClickListener(new SetOnCalenderClickListener() {
                    @Override
                    public void onDateClick(Calendar date) {
                        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd-MMM-yyyy HH:mm a", Locale.getDefault());
                        Log.e(TAG, "onDateClick: " + sdf.format(date.getTime()));
                    }
                });
        limitedCalenderHandler.build();

        limitedCalenderHandler.setDisabledDateList(list);

    }




}