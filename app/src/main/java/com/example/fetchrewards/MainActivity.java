package com.example.fetchrewards;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<Item> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.RecyclerView);

        itemAdapter = new ItemAdapter(itemList, this);

        // connect the recyclerView to the adapter
        recyclerView.setAdapter(itemAdapter);
        /* show the recyclerview */
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // create a JSON object to store each item
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(loadJSONFromAsset());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonObject;
            try {
                jsonObject = jsonArray.getJSONObject(i);
                Item item = new Item(jsonObject.getInt("id"),
                        jsonObject.getInt("listId"),
                        jsonObject.getString("name")
                );
                // add that JSON object to the itemList
                itemList.add(0, item);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // sort by listId
        itemList.sort(Comparator.comparingInt(Item::getListId)
                .thenComparing(Item::getName));

        // filter out any items where "name" is blank or null
        itemList.removeIf(item -> item.getName().isEmpty() || item.getName().equals("null"));

        itemAdapter.notifyDataSetChanged();
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().open("hiring.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}