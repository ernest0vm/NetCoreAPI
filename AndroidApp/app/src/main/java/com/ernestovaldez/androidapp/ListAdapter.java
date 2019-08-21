package com.ernestovaldez.androidapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private ArrayList<Student> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt1;
        TextView txt2;
        public MyViewHolder(View v) {
            super(v);
            txt1 = v.findViewById(R.id.txtName);
            txt2 = v.findViewById(R.id.txtAge);
        }

    }

    public ListAdapter(ArrayList<Student> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cellcomponent, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txt1.setText("Name: " + mDataset.get(position).Name);
        holder.txt2.setText("Age: " + mDataset.get(position).Age);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
