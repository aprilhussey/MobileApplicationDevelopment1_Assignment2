package com.example.localshopecommerceapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {
    Context context;
    private final ArrayList<BasketModel> basketModels;

    // Constructor
    public BasketAdapter(Context context, ArrayList<BasketModel> basketModels) {
        this.context = context;
        this.basketModels = basketModels;
    }

    @NonNull
    @Override
    public BasketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // To inflate the layout for each item of recycler view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_basket, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BasketAdapter.ViewHolder holder, int position) {
        // To set data to textview and imageview of each card layout
        BasketModel model = basketModels.get(position);
        holder.imgItem.setImageResource(model.getItemImage());
        holder.txtItemName.setText(model.getItemName());
        holder.txtItemPrice.setText(model.getItemPrice());
        holder.txtItemVer.setText(model.getItemVer());
    }

    @Override
    public int getItemCount() {
        // This method is used for showing number of card items in recycler view
        return basketModels.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgItem;
        TextView txtItemName;
        TextView txtItemPrice;
        TextView txtItemVer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.imgItem);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtItemPrice = itemView.findViewById(R.id.txtItemPrice);
            txtItemVer = itemView.findViewById(R.id.txtItemVer);
        }
    }
}
