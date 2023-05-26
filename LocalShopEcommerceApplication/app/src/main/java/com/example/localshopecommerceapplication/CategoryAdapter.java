package com.example.localshopecommerceapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    Context context;
    private final ArrayList<CategoryModel> categoryModels;

    // Constructor
    public CategoryAdapter(Context context, ArrayList<CategoryModel> categoryModels) {
        this.context = context;
        this.categoryModels = categoryModels;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // To inflate the layout for each item of recycler view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        // To set data to textview and imageview of each card layout
        CategoryModel model = categoryModels.get(position);
        holder.txtCategoryName.setText(model.getCategoryName());
    }

    @Override
    public int getItemCount() {
        // This method is used for showing number of card items in recycler view
        return categoryModels.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCategoryName;

        public ViewHolder(@NonNull View category) {
            super(category);
            txtCategoryName = itemView.findViewById(R.id.txtCategoryName);
        }
    }
}
