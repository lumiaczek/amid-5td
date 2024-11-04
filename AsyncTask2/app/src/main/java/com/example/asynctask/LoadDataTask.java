package com.example.asynctask;

import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.List;

public class LoadDataTask extends AsyncTask<Void, Void, List<Item>> {
    private ListActivity activity;

    public LoadDataTask(ListActivity activity) {
        this.activity = activity;
    }

    @Override
    protected List<Item> doInBackground(Void... voids) {
        // Simulate loading data
        List<Item> items = new ArrayList<>();
        // Add dummy data
        items.add(new Item("Product 1", 10.00, "Description 1"));
        items.add(new Item("Product 2", 20.00, "Description 2"));

        try {
            Thread.sleep(5000); // Simulate delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    protected void onPostExecute(List<Item> items) {
        super.onPostExecute(items);
        activity.updateList(items);
    }
}
