package com.example.localshopecommerceapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localshopecommerceapplication.R;
import com.example.localshopecommerceapplication.db.DatabaseConnect;
import com.example.localshopecommerceapplication.models.ItemModel;
import com.example.localshopecommerceapplication.models.OrderModel;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    Context context;
    private final ArrayList<OrderModel> orderModels;
    DatabaseConnect dbConnect;

    // Constructor
    public OrderAdapter(Context context, ArrayList<OrderModel> orderModels) {
        this.context = context;
        this.orderModels = orderModels;
        this.dbConnect = new DatabaseConnect(context.getApplicationContext());
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // To inflate the layout for each item of recycler view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order, parent, false);
        return new OrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        // To set data to textview and imageview of each card layout
        OrderModel model = orderModels.get(position);
        holder.txtAddress.setText(model.getAddress());
        holder.txtItems.setText(model.getItems());
        holder.txtEmail.setText(model.getEmail());
    }

    @Override
    public int getItemCount() {
        // This method is used for showing number of card txtItems in recycler view
        return orderModels.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtAddress;
        TextView txtItems;
        TextView txtEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAddress = itemView.findViewById(R.id.txtAddressVal);
            txtItems = itemView.findViewById(R.id.txtItemsVal);
            txtEmail = itemView.findViewById(R.id.txtEmailVal);
        }
    }
}
