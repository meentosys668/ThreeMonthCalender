package com.limitedcalender;

import java.util.Calendar;

public interface SetOnCalenderClickListener {
    void onDateClick(Calendar date);
    void onDisabledDateClick(Calendar disabledDate);
}
