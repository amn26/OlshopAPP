package com.example.olshopapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashSet;
import java.util.Set;

public class DetailProductActivity extends AppCompatActivity {
    private ImageView ivProductDetail;
    private TextView tvProductName, tvProductPrice, tvProductDescription, tvQuantity;
    private Button btnMinus, btnPlus, btnAddToCart;
    private SharedPreferences cartPrefs;
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        // Enable back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initViews();
        cartPrefs = getSharedPreferences("CartPrefs", MODE_PRIVATE);
        loadProductData();
        updateQuantityDisplay();

        btnMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                updateQuantityDisplay();
            }
        });

        btnPlus.setOnClickListener(v -> {
            quantity++;
            updateQuantityDisplay();
        });

        btnAddToCart.setOnClickListener(v -> addToCart());
    }

    private void initViews() {
        ivProductDetail = findViewById(R.id.ivProductDetail);
        tvProductName = findViewById(R.id.tvProductName);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvProductDescription = findViewById(R.id.tvProductDescription);
        tvQuantity = findViewById(R.id.tvQuantity);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnAddToCart = findViewById(R.id.btnAddToCart);
    }

    private void loadProductData() {
        String name = getIntent().getStringExtra("product_name");
        double price = getIntent().getDoubleExtra("product_price", 0);
        String description = getIntent().getStringExtra("product_description");
        int imageResource = getIntent().getIntExtra("product_image", R.drawable.ic_launcher_foreground);

        tvProductName.setText(name);
        tvProductPrice.setText("Rp " + String.format("%,.0f", price));
        tvProductDescription.setText(description);
        ivProductDetail.setImageResource(imageResource);
    }

    private void updateQuantityDisplay() {
        tvQuantity.setText(String.valueOf(quantity));
    }

    private void addToCart() {
        String productName = getIntent().getStringExtra("product_name");
        double productPrice = getIntent().getDoubleExtra("product_price", 0);
        int imageResource = getIntent().getIntExtra("product_image", R.drawable.ic_launcher_foreground);

        Set<String> cartItems = cartPrefs.getStringSet("cart_items", new HashSet<>());
        Set<String> newCartItems = new HashSet<>(cartItems);
        
        String cartItem = productName + "|" + productPrice + "|" + quantity + "|" + imageResource;
        newCartItems.add(cartItem);

        SharedPreferences.Editor editor = cartPrefs.edit();
        editor.putStringSet("cart_items", newCartItems);
        editor.apply();

        Toast.makeText(this, quantity + " item ditambahkan ke keranjang", Toast.LENGTH_SHORT).show();
        quantity = 1;
        updateQuantityDisplay();
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
