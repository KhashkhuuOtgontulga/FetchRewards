package com.example.fetchrewards;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder>{
    private List<Item> itemList;
    private MainActivity mainAct;

    public ItemAdapter(List<Item> itemList, MainActivity mainActivity) {
        this.itemList = itemList;
        mainAct = mainActivity;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_entry, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item oneItem = itemList.get(position);
        holder.idField.setText(Integer.toString(oneItem.getId()));
        holder.listIdField.setText(Integer.toString(oneItem.getListId()));
        holder.nameField.setText(oneItem.getName());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
