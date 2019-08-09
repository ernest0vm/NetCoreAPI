package com.ernestovaldez.androidapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private List<Student> mDataset;
    private int cont = 0;
    private ViewGroup _parent;
    View view;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View v) {
            super(v);
        }

    }

    public ListAdapter(List<Student> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        _parent = parent;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cellcomponent, _parent, false);

        TextView txt1 = view.findViewById(R.id.txtName);
        txt1.setText("Name: " + mDataset.get(cont).Name);

        TextView txt2 = view.findViewById(R.id.txtAge);
        txt2.setText("Age: " + mDataset.get(cont).Age);

        cont++;
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
