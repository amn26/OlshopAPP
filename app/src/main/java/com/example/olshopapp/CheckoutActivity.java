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

public class CheckoutActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCheckout;
    private TextView tvTotalPayment;
    private Button btnPay;
    private SharedPreferences cartPrefs;
    private double totalPrice;
    private List<CartItem> selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Enable back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initViews();
        cartPrefs = getSharedPreferences("CartPrefs", MODE_PRIVATE);
        totalPrice = getIntent().getDoubleExtra("total_price", 0);
        
        loadSelectedItems();
        setupRecyclerView();

        btnPay.setOnClickListener(v -> processPayment());
    }

    private void initViews() {
        recyclerViewCheckout = findViewById(R.id.recyclerViewCheckout);
        tvTotalPayment = findViewById(R.id.tvTotalPayment);
        btnPay = findViewById(R.id.btnPay);
    }

    private void loadSelectedItems() {
        selectedItems = new ArrayList<>();
        // Get selected items from CartActivity (passed via Intent or SharedPreferences)
        // For now, we'll load from SharedPreferences and filter selected ones
        // In real implementation, you'd pass selected items via Intent
        
        double shippingCost = 15000;
        double finalTotal = totalPrice + shippingCost;
        tvTotalPayment.setText("Total Pembayaran: Rp " + String.format("%,.0f", finalTotal));
    }

    private void setupRecyclerView() {
        CheckoutAdapter adapter = new CheckoutAdapter(selectedItems);
        recyclerViewCheckout.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCheckout.setAdapter(adapter);
    }

    private void processPayment() {
        Toast.makeText(this, "Checkout Berhasil! Terima kasih atas pembelian Anda", Toast.LENGTH_LONG).show();
        
        // Navigate back to MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
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
