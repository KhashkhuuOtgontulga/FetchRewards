package com.example.fetchrewards;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView idField;
    public TextView listIdField;
    public TextView nameField;

    public ItemViewHolder(View view) {
        super(view);
        idField = view.findViewById(R.id.idField);
        listIdField = view.findViewById(R.id.listIdField);
        nameField = view.findViewById(R.id.nameField);
    }
}
