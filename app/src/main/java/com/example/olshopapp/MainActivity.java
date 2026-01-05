package com.example.olshopapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private List<Product> allProducts;
    private Spinner spinnerCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupToolbar();
        setupRecyclerView();
        loadProducts();
        setupCategoryFilter();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        spinnerCategory = findViewById(R.id.spinnerCategory);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("OlshopAPP");
        }
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);
    }

    private void loadProducts() {
        allProducts = new ArrayList<>();
        allProducts.add(new Product("Samsung Galaxy S24", 3500000, "Smartphone flagship dengan kamera 200MP dan chipset Snapdragon 8 Gen 3", R.drawable.product_samsung_s24, "Smartphone"));
        allProducts.add(new Product("iPhone 15 Pro", 4200000, "iPhone terbaru dengan chip A17 Pro dan kamera 48MP", R.drawable.product_iphone_15, "Smartphone"));
        allProducts.add(new Product("Xiaomi 14", 2800000, "Smartphone dengan Leica camera dan fast charging 90W", R.drawable.product_xiaomi_14, "Smartphone"));
        
        allProducts.add(new Product("Apple Watch Series 9", 2500000, "Smartwatch dengan health monitoring dan GPS akurat", R.drawable.product_apple_watch, "Jam"));
        allProducts.add(new Product("Samsung Galaxy Watch 6", 1800000, "Smartwatch Android dengan sleep tracking dan fitness", R.drawable.product_galaxy_watch, "Jam"));
        allProducts.add(new Product("Garmin Forerunner 965", 3200000, "Jam olahraga dengan GPS dan training metrics", R.drawable.product_garmin_watch, "Jam"));
        
        allProducts.add(new Product("ASUS ROG Strix", 12000000, "Laptop gaming dengan RTX 4060 dan Intel i7-13700H", R.drawable.product_asus_laptop, "Laptop"));
        allProducts.add(new Product("MacBook Air M2", 15000000, "Laptop tipis dengan chip M2 dan battery life 18 jam", R.drawable.product_macbook, "Laptop"));
        allProducts.add(new Product("Lenovo ThinkPad X1", 18000000, "Business laptop dengan security features dan durability", R.drawable.product_thinkpad, "Laptop"));
        
        allProducts.add(new Product("Sony WH-1000XM5", 750000, "Headphone wireless dengan noise cancelling terbaik", R.drawable.product_sony_headphone, "Audio"));
        allProducts.add(new Product("AirPods Pro 2", 950000, "Earbuds dengan spatial audio dan adaptive transparency", R.drawable.product_airpods, "Audio"));
        allProducts.add(new Product("JBL Charge 5", 450000, "Speaker bluetooth portable dengan bass powerful", R.drawable.product_jbl_speaker, "Audio"));
        
        productList.addAll(allProducts);
        productAdapter.notifyDataSetChanged();
    }

    private void setupCategoryFilter() {
        String[] categories = {"Semua", "Smartphone", "Jam", "Laptop", "Audio"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
        
        spinnerCategory.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                filterProducts(categories[position]);
            }
            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });
    }

    private void filterProducts(String category) {
        productList.clear();
        if (category.equals("Semua")) {
            productList.addAll(allProducts);
        } else {
            for (Product product : allProducts) {
                if (product.getCategory().equals(category)) {
                    productList.add(product);
                }
            }
        }
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_cart) {
            startActivity(new Intent(this, CartActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}