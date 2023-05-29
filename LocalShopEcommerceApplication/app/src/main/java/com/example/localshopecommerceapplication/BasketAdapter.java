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
    private final ArrayList<ItemModel> itemModels;

    // Constructor
    public BasketAdapter(Context context, ArrayList<ItemModel> itemModels) {
        this.context = context;
        this.itemModels = itemModels;
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
        ItemModel model = itemModels.get(position);
        holder.txtItemName.setText(model.getName());
        holder.txtItemPrice.setText(String.valueOf(model.getPrice()));
        holder.txtItemVer.setText(model.getVersion());
        holder.txtItemSet.setText(model.getSet());
        //holder.imgItem.setImageResource(model.getImage()); String set via database not image resource
        holder.txtItemDescription.setText(model.getDescription());
    }

    @Override
    public int getItemCount() {
        // This method is used for showing number of card items in recycler view
        return itemModels.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtItemName;
        TextView txtItemPrice;
        TextView txtItemVer;    // For wishlist and basket text view is fine, otherwise spinner
        TextView txtItemSet;    // For wishlist and basket text view is fine, otherwise spinner
        ImageView imgItem;
        TextView txtItemDescription;    // Only needed for item page

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtItemPrice = itemView.findViewById(R.id.txtItemPrice);
            txtItemVer = itemView.findViewById(R.id.txtItemVer);
            txtItemSet = itemView.findViewById(R.id.txtItemSet);
            imgItem = itemView.findViewById(R.id.imgItem);
            txtItemDescription = itemView.findViewById(R.id.txtItemDescription);
        }
    }
}
