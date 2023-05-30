package com.example.localshopecommerceapplication.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localshopecommerceapplication.models.CategoryModel;
import com.example.localshopecommerceapplication.R;
import com.example.localshopecommerceapplication.fragments.ShopFragment;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    Context context;
    private ArrayList<CategoryModel> categoryModels;

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopFragment shopFragment = new ShopFragment();
                Bundle bundle = new Bundle();

                bundle.putString("categorySelected", holder.getCategoryName());
                shopFragment.setArguments(bundle);

                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.flFragment, shopFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        // This method is used for showing number of card items in recycler view
        return categoryModels.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCategoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCategoryName = itemView.findViewById(R.id.txtCategoryName);
        }

        public String getCategoryName() {
            return txtCategoryName.getText().toString();
        }
    }
}
