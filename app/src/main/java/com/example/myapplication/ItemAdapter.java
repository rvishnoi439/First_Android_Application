package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<String> data;
    private OnLongClickListener longClickLister;
    public interface OnLongClickListener{
        void onLongClick(int position);
    }

    public ItemAdapter(List<String> data, OnLongClickListener longClickLister) {
        this.data = data;
        this.longClickLister = longClickLister;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String toAdd = this.data.get(position);
        holder.bind(toAdd);
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    // Container that provides easy access to view that represents the rows
    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvItem = itemView.findViewById(android.R.id.text1);
        }


        public void bind(String toAdd) {
            this.tvItem.setText(toAdd);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    longClickLister.onLongClick(getAdapterPosition());

                    return false;
                }
            });
        }
    }
}
