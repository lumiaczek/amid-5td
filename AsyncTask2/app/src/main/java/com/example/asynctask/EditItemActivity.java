package com.example.asynctask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class EditItemActivity extends AppCompatActivity {
    private EditText editProductName;
    private EditText editPrice;
    private EditText editDescription;
    private Button saveChangesButton;
    private Button deleteItemButton;
    private int itemIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        editProductName = findViewById(R.id.edit_product_name);
        editPrice = findViewById(R.id.edit_price);
        editDescription = findViewById(R.id.edit_description);
        saveChangesButton = findViewById(R.id.save_changes_button);
        deleteItemButton = findViewById(R.id.delete_item_button);

        Intent intent = getIntent();
        itemIndex = intent.getIntExtra("item_index", -1);
        Item item = (Item) intent.getSerializableExtra("item");

        if (item != null) {
            editProductName.setText(item.getName());
            editPrice.setText(String.valueOf(item.getPrice()));
            editDescription.setText(item.getDescription());
        }

        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editProductName.getText().toString().trim();
                String priceText = editPrice.getText().toString().trim();
                String description = editDescription.getText().toString().trim();

                if (name.isEmpty() || priceText.isEmpty()) {
                    Toast.makeText(EditItemActivity.this, "Please enter name and price", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double price = Double.parseDouble(priceText);

                    Item updatedItem = new Item(name, price, description);

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("updated_item", updatedItem);
                    resultIntent.putExtra("item_index", itemIndex);
                    setResult(RESULT_OK, resultIntent);
                    finish();

                } catch (NumberFormatException e) {
                    Toast.makeText(EditItemActivity.this, "Invalid price format", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("item_index", itemIndex);
                setResult(RESULT_FIRST_USER, resultIntent);
                finish();
            }
        });
    }

    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
