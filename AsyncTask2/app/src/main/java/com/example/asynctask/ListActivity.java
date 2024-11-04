package com.example.asynctask;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private static final int REQUEST_ADD_ITEM = 1;
    private static final int REQUEST_EDIT_ITEM = 2;

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        listView = findViewById(R.id.item_list_view);
        items = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        new LoadDataTask(this).execute();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ListActivity.this, EditItemActivity.class);
            intent.putExtra("item_index", position);
            intent.putExtra("item", items.get(position));
            startActivityForResult(intent, REQUEST_EDIT_ITEM);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ADD_ITEM && resultCode == RESULT_OK && data != null) {
            Item newItem = (Item) data.getSerializableExtra("new_item");
            items.add(newItem);
            updateList(items);
        } else if (requestCode == REQUEST_EDIT_ITEM && data != null) {
            int index = data.getIntExtra("item_index", -1);
            if (resultCode == RESULT_OK) {
                Item updatedItem = (Item) data.getSerializableExtra("updated_item");
                if (index != -1 && updatedItem != null) {
                    items.set(index, updatedItem);
                    updateList(items);
                }
            } else if (resultCode == RESULT_FIRST_USER) {
                if (index != -1) {
                    items.remove(index);
                    updateList(items);
                }
            }
        }
    }

    public void updateList(List<Item> items) {
        adapter.clear();
        for (Item item : items) {
            adapter.add(item.getName() + " - " + item.getPrice());
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
