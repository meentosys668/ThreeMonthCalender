package com.limitedcalender;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MonthAdapter extends RecyclerView.Adapter<MonthViewHolder> {

    Context context;
    List<Date> dateList;
    SetOnCalenderClickListener setOnCalenderClickListener;
    boolean isLast;
    List<Date> disabledDateList;

    public MonthAdapter(Context context, List<Date> dateList, SetOnCalenderClickListener setOnCalenderClickListener, boolean isLast) {
        this.context = context;
        this.dateList = dateList;
        this.setOnCalenderClickListener = setOnCalenderClickListener;
        this.isLast = isLast;
    }

    @NonNull
    @Override
    public MonthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MonthViewHolder(LayoutInflater.from(context).inflate(R.layout.date_view, parent, false), setOnCalenderClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthViewHolder holder, int position) {
        Date date = dateList.get(position);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        Calendar calendar1 = Calendar.getInstance();
        int currentDay = calendar1.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar1.get(Calendar.MONTH);
        if (currentMonth == month) {
            if (currentDay > day) {
                holder.textView.setEnabled(false);
                holder.textView.setTextColor(context.getResources().getColor(R.color.grey));
            }
        } else {
            if (isLast) {
                if (currentDay < day) {
                    holder.textView.setEnabled(false);
                    holder.textView.setTextColor(context.getResources().getColor(R.color.grey));
                }
            }
        }
        holder.textView.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        holder.textView.setOnClickListener(v -> {

            if (setOnCalenderClickListener != null) {
                if (disabledDateList != null) {
                    if (disabledDateList.contains(date)) {
                        setOnCalenderClickListener.onDisabledDateClick(calendar);
                        return;
                    }
                }
                setOnCalenderClickListener.onDateClick(calendar);
            }

            ColorDrawable colorDrawable = (ColorDrawable) holder.linearLayout.getBackground();
            if (colorDrawable.getColor() == context.getResources().getColor(R.color.teal_200)) {
                holder.linearLayout.setBackgroundColor(Color.parseColor("#F3F1F1"));
            } else {
                holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.teal_200));
            }

        });

        if (disabledDateList != null) {
            if (disabledDateList.contains(date)) {
                holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.red));
                holder.textView.setTextColor(context.getResources().getColor(R.color.white));
            }
        }
    }

    public void setDisabledDateList(List<Date> disabledDateList) {
        this.disabledDateList = disabledDateList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }
}
