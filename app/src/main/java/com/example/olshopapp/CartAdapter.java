package com.example.olshopapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<CartItem> cartItems;
    private OnItemChangeListener listener;

    public interface OnItemChangeListener {
        void onSelectionChanged();
    }

    public CartAdapter(List<CartItem> cartItems, OnItemChangeListener listener) {
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.ivProduct.setImageResource(item.getImageResource());
        holder.tvItemName.setText(item.getName());
        holder.tvItemPrice.setText("Rp " + String.format("%,.0f", item.getPrice()));
        holder.tvQuantity.setText("Qty: " + item.getQuantity());
        holder.tvTotalPrice.setText("Total: Rp " + String.format("%,.0f", item.getTotalPrice()));
        holder.cbSelect.setChecked(item.isSelected());
        
        holder.cbSelect.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setSelected(isChecked);
            if (listener != null) listener.onSelectionChanged();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        CheckBox cbSelect;
        ImageView ivProduct;
        TextView tvItemName, tvItemPrice, tvQuantity, tvTotalPrice;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cbSelect = itemView.findViewById(R.id.cbSelect);
            ivProduct = itemView.findViewById(R.id.ivProduct);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
        }
    }
}
