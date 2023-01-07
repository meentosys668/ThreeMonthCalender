package com.limitedcalender;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LimitedCalenderHandler {

    private int count = 3;
    private final Context context;
    private final View recyclerViewId;
    private SetOnCalenderClickListener setOnCalenderClickListener;
    private List<Date> disabledDateList;
    private CalenderAdapter calenderAdapter;
    private String dateFormat;

    public static LimitedCalenderHandler getInstance(Context context, View recyclerViewId) {
        return new LimitedCalenderHandler(context, recyclerViewId);
    }

    public LimitedCalenderHandler setOnCalenderClickListener(SetOnCalenderClickListener setOnCalenderClickListener) {
        this.setOnCalenderClickListener = setOnCalenderClickListener;
        return this;
    }

    public LimitedCalenderHandler setNumberMonth(int count) {
        this.count = count;
        return this;
    }

    public LimitedCalenderHandler setDisabledDateList(List<String> disabledDateList) {
        this.disabledDateList = getListOfDate(disabledDateList);
        if (calenderAdapter!=null) {
            calenderAdapter.setDisabledDateList(this.disabledDateList);
        }
        return this;
    }

    public LimitedCalenderHandler setDisabledDateList(List<String> disabledDateList,String dateFormat) {
        this.dateFormat = dateFormat;
        this.disabledDateList = getListOfDate(disabledDateList);
        if (calenderAdapter!=null) {
            calenderAdapter.setDisabledDateList(this.disabledDateList);
        }
        return this;
    }

    private List<Date> getListOfDate(List<String> dateListInString) {
        List<Date> list = new ArrayList<>();
        for (String s : dateListInString) {
            if (getDateFromText(s) != null) {
                list.add(getDateFromText(s));
            }
        }
        return list;
    }

    private Date getDateFromText(String stringDate) {
        if (dateFormat==null){
            dateFormat = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
        try {
            return sdf.parse(stringDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private LimitedCalenderHandler(Context context, View recyclerViewId) {
        this.context = context;
        this.recyclerViewId = recyclerViewId;
    }

    public void build() {
        List<List<Date>> threeMonthDateList = new ArrayList<>(getListOfMonth());
        RecyclerView recyclerView = (RecyclerView) recyclerViewId;
        calenderAdapter = new CalenderAdapter(context, threeMonthDateList, setOnCalenderClickListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(calenderAdapter);
    }

    private List<List<Date>> getListOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        List<List<Date>> listOfMonth = new ArrayList<>();
        int limit;
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            limit = count;
        } else {
            limit = count + 1;
        }
        for (int i = 0; i < limit; i++) {
            listOfMonth.add(getDaysOfMonth(year, month));
            calendar.add(Calendar.MONTH, 1);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
        }

        return listOfMonth;
    }

    private List<Date> getDaysOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, month, 1);
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<Date> listOfDay = new ArrayList<>();

        for (int i = 0; i < daysInMonth; i++) {
            listOfDay.add(cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

//        int remain = listOfDay.size() % 7;
//        if (remain != 0) {
//            int size = listOfDay.size();
//        }
        return listOfDay;
    }


}
