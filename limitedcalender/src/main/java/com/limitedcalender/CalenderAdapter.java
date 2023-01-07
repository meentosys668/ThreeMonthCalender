package com.limitedcalender;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalenderAdapter extends RecyclerView.Adapter<CalenderViewHolder> {

    Context context;
    List<List<Date>> dateList;
    SetOnCalenderClickListener setOnCalenderClickListener;
    List<Date> disabledDateList;

    public CalenderAdapter(Context context, List<List<Date>> dateList, SetOnCalenderClickListener setOnCalenderClickListener) {
        this.context = context;
        this.dateList = dateList;
        this.setOnCalenderClickListener = setOnCalenderClickListener;
    }

    public void setDisabledDateList(List<Date> disabledDateList){
        this.disabledDateList = disabledDateList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CalenderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CalenderViewHolder(LayoutInflater.from(context).inflate(R.layout.month_view,parent,false),setOnCalenderClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalenderViewHolder holder, int position) {
        List<Date> date = dateList.get(position);
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context,7));
        MonthAdapter monthAdapter = new MonthAdapter(context,date,holder.setOnCalenderClickListener,(dateList.size()-1)==position);
        monthAdapter.setDisabledDateList(disabledDateList);
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM", Locale.getDefault());
        holder.textView.setText(sdf.format(date.get(0)));
        holder.recyclerView.setAdapter(monthAdapter);
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }
}
