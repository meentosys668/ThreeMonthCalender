package com.limitedcalender;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MonthViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    LinearLayout linearLayout;
    SetOnCalenderClickListener setOnCalenderClickListener;
    public MonthViewHolder(@NonNull View itemView,SetOnCalenderClickListener setOnCalenderClickListener) {
        super(itemView);
        this.setOnCalenderClickListener = setOnCalenderClickListener;
        textView = itemView.findViewById(R.id.text_date);
        linearLayout = itemView.findViewById(R.id.layout);
    }
}
