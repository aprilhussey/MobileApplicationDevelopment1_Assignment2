package com.example.localshopecommerceapplication.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localshopecommerceapplication.db.DatabaseConnect;
import com.example.localshopecommerceapplication.models.ItemModel;
import com.example.localshopecommerceapplication.R;
import com.example.localshopecommerceapplication.fragments.ItemPageFragment;

import java.io.File;
import java.util.ArrayList;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {
    Context context;
    private final ArrayList<ItemModel> itemsInBasket;
    DatabaseConnect dbConnect;

    // Constructor
    public BasketAdapter(Context context, ArrayList<ItemModel> itemsInBasket) {
        this.context = context;
        this.itemsInBasket = itemsInBasket;
        this.dbConnect = new DatabaseConnect(context.getApplicationContext());
    }

    @NonNull
    @Override
    public BasketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // To inflate the layout for each item of recycler view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_basket, parent, false);
        return new BasketAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BasketAdapter.ViewHolder holder, int position) {
        // To set data to textview and imageview of each card layout

        ItemModel model = itemsInBasket.get(position);

        holder.txtItemName.setText(model.getName());
        holder.txtItemPrice.setText(model.getPrice());
        holder.imgItem.setImageURI(Uri.fromFile(new File(model.getImageFilePath()))); //String set via database not image resource
        holder.txtItemVer.setText(model.getVersion());
        holder.txtItemSet.setText(model.getSet());
        if (model.getInStock() == 1){
            holder.txtItemStock.setText("In Stock");
        } else {
            holder.txtItemStock.setText("Out of Stock");
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemPageFragment itemPageFragment = new ItemPageFragment();
                Bundle bundle = new Bundle();

                bundle.putString("itemSelected", holder.getItemName());
                //bundle.putInt("verSelected", holder.spnItemVer.getSelectedItemPosition());
                //bundle.putInt("setSelected", holder.spnItemSet.getSelectedItemPosition());

                itemPageFragment.setArguments(bundle);

                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.flFragment, itemPageFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Works on the assumption that the itemsInBasket ArrayList is the same order
                // RecyclerView
                itemsInBasket.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        // This method is used for showing number of card items in recycler view
        return itemsInBasket.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtItemName;
        TextView txtItemPrice;
        TextView txtItemVer;
        TextView txtItemSet;
        ImageView imgItem;
        TextView txtItemStock;
        ImageButton removeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtItemPrice = itemView.findViewById(R.id.txtItemPrice);
            txtItemVer = itemView.findViewById(R.id.txtItemVerTxt);
            txtItemSet = itemView.findViewById(R.id.txtItemSetTxt);
            imgItem = itemView.findViewById(R.id.imgItem);
            txtItemStock = itemView.findViewById(R.id.txtItemStock);
            removeButton = itemView.findViewById(R.id.btnRemoveItem);
        }

        public String getItemName() {
            return txtItemName.getText().toString();
        }
    }
}
