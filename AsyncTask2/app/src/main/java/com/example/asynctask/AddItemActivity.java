package com.example.asynctask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class AddItemActivity extends AppCompatActivity {
    private EditText productNameInput;
    private EditText priceInput;
    private EditText descriptionInput;
    private Button addItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        productNameInput = findViewById(R.id.product_name_input);
        priceInput = findViewById(R.id.price_input);
        descriptionInput = findViewById(R.id.description_input);
        addItemButton = findViewById(R.id.add_item_button);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = productNameInput.getText().toString().trim();
                String priceText = priceInput.getText().toString().trim();
                String description = descriptionInput.getText().toString().trim();

                Toolbar toolbar = findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);

                if (name.isEmpty() || priceText.isEmpty()) {
                    Toast.makeText(AddItemActivity.this, "Please enter name and price", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double price = Double.parseDouble(priceText);

                    Item newItem = new Item(name, price, description);

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("new_item", newItem);
                    setResult(RESULT_OK, resultIntent);
                    finish();

                } catch (NumberFormatException e) {
                    Toast.makeText(AddItemActivity.this, "Invalid price format", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
