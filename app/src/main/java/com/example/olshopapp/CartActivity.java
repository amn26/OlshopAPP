package com.example.olshopapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnItemChangeListener {
    private RecyclerView recyclerView;
    private TextView tvTotalPrice;
    private Button btnCheckout;
    private SharedPreferences cartPrefs;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItems;
    private double totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Enable back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initViews();
        cartPrefs = getSharedPreferences("CartPrefs", MODE_PRIVATE);
        
        cartItems = new ArrayList<>();
        cartAdapter = new CartAdapter(cartItems, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);
        
        loadCartItems();

        btnCheckout.setOnClickListener(v -> {
            List<CartItem> selectedItems = new ArrayList<>();
            for (CartItem item : cartItems) {
                if (item.isSelected()) {
                    selectedItems.add(item);
                }
            }
            
            if (selectedItems.isEmpty()) {
                Toast.makeText(this, "Pilih item yang ingin di-checkout", Toast.LENGTH_SHORT).show();
                return;
            }
            
            double selectedTotal = 0;
            for (CartItem item : selectedItems) {
                selectedTotal += item.getTotalPrice();
            }
            
            // Remove selected items from cart
            removeSelectedItems();
            
            Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
            intent.putExtra("total_price", selectedTotal);
            startActivity(intent);
        });
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerViewCart);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnCheckout = findViewById(R.id.btnCheckout);
    }

    private void loadCartItems() {
        Set<String> cartItemsSet = cartPrefs.getStringSet("cart_items", new HashSet<>());
        cartItems.clear();

        if (!cartItemsSet.isEmpty()) {
            for (String item : cartItemsSet) {
                String[] parts = item.split("\\|");
                if (parts.length == 4) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    int quantity = Integer.parseInt(parts[2]);
                    int imageResource = Integer.parseInt(parts[3]);
                    cartItems.add(new CartItem(name, price, quantity, imageResource));
                }
            }
        }

        cartAdapter.notifyDataSetChanged();
        updateTotalPrice();
    }

    private void removeSelectedItems() {
        Set<String> cartItemsSet = cartPrefs.getStringSet("cart_items", new HashSet<>());
        Set<String> newCartItems = new HashSet<>();
        
        for (CartItem item : cartItems) {
            if (!item.isSelected()) {
                String cartItem = item.getName() + "|" + item.getPrice() + "|" + item.getQuantity() + "|" + item.getImageResource();
                newCartItems.add(cartItem);
            }
        }
        
        SharedPreferences.Editor editor = cartPrefs.edit();
        editor.putStringSet("cart_items", newCartItems);
        editor.apply();
        
        loadCartItems();
    }

    @Override
    public void onSelectionChanged() {
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        totalPrice = 0;
        for (CartItem item : cartItems) {
            if (item.isSelected()) {
                totalPrice += item.getTotalPrice();
            }
        }
        tvTotalPrice.setText("Total Terpilih: Rp " + String.format("%,.0f", totalPrice));
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
