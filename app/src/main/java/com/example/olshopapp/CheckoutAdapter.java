package com.example.olshopapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder> {
    private List<CartItem> items;

    public CheckoutAdapter(List<CartItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CheckoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkout, parent, false);
        return new CheckoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutViewHolder holder, int position) {
        CartItem item = items.get(position);
        holder.ivProduct.setImageResource(item.getImageResource());
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText("Rp " + String.format("%,.0f", item.getPrice()));
        holder.tvQuantity.setText("x" + item.getQuantity());
        holder.tvTotal.setText("Rp " + String.format("%,.0f", item.getTotalPrice()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class CheckoutViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProduct;
        TextView tvName, tvPrice, tvQuantity, tvTotal;

        public CheckoutViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.ivProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvTotal = itemView.findViewById(R.id.tvTotal);
        }
    }
}
