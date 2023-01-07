package com.threemonthcalender.limitedCalender;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.threemonthcalender.R;


public class CalenderViewHolder extends RecyclerView.ViewHolder {
    RecyclerView recyclerView;
    TextView textView;
    SetOnCalenderClickListener setOnCalenderClickListener;
    public CalenderViewHolder(@NonNull View itemView, SetOnCalenderClickListener setOnCalenderClickListener) {
        super(itemView);
        this.setOnCalenderClickListener = setOnCalenderClickListener;
        textView = itemView.findViewById(R.id.month);
        recyclerView = itemView.findViewById(R.id.list_of_date);
    }
}
