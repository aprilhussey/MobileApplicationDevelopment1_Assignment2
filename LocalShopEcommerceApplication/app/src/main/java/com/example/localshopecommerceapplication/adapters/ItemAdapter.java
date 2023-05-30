package com.example.localshopecommerceapplication.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
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

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    Context context;
    private final ArrayList<ItemModel> itemModels;
    DatabaseConnect dbConnect;

    // Constructor
    public ItemAdapter(Context context, ArrayList<ItemModel> itemModels) {
        this.context = context;
        this.itemModels = itemModels;
        this.dbConnect = new DatabaseConnect(context.getApplicationContext());
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // To inflate the layout for each item of recycler view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        // To set data to textview and imageview of each card layout
        ItemModel model = itemModels.get(position);
        holder.txtItemName.setText(model.getName());
        holder.txtItemPrice.setText(String.valueOf(model.getPrice()));

        ArrayAdapter<String> spnVerAdapter = new ArrayAdapter<>(holder.spnItemVer.getContext(), android.R.layout.simple_spinner_item, dbConnect.getVersionsOfItem(model.getName()));
        spnVerAdapter.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item));
        holder.spnItemVer.setAdapter(spnVerAdapter);

        ArrayAdapter<String> spnSetAdapter = new ArrayAdapter<>(holder.spnItemSet.getContext(), android.R.layout.simple_spinner_item, dbConnect.getSetsOfItem(model.getName()));
        spnVerAdapter.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item));
        holder.spnItemSet.setAdapter(spnSetAdapter);

        holder.imgItem.setImageURI(Uri.fromFile(new File(model.getImageFilePath()))); //String set via database not image resource

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemPageFragment itemPageFragment = new ItemPageFragment();
                Bundle bundle = new Bundle();

                bundle.putString("itemSelected", holder.getItemName());
                bundle.putInt("verSelected", holder.spnItemVer.getSelectedItemPosition());
                bundle.putInt("setSelected", holder.spnItemSet.getSelectedItemPosition());

                itemPageFragment.setArguments(bundle);

                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.flFragment, itemPageFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
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
        Spinner spnItemVer;
        Spinner spnItemSet;
        ImageView imgItem;
        TextView txtItemStock;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtItemPrice = itemView.findViewById(R.id.txtItemPrice);
            spnItemVer = itemView.findViewById(R.id.spnItemVer);
            spnItemSet = itemView.findViewById(R.id.spnItemSet);
            imgItem = itemView.findViewById(R.id.imgItem);
            txtItemStock = itemView.findViewById(R.id.txtItemStock);
        }

        public String getItemName() {
            return txtItemName.getText().toString();
        }
    }
}
