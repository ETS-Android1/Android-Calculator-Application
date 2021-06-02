package com.example.calculatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    public static String itemSelected = "com.example.listview.MAINACTIVITY";
    public static String index = "ahmed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        setTitle("History");

        final ArrayList<String> list = getIntent().getStringArrayListExtra(MainActivity.HISTORY_EXTRA);
        final ArrayList<String> value = getIntent().getStringArrayListExtra(MainActivity.HISTORY_ITEM_VALUE);

        TextView noContent = findViewById(R.id.noContent);
        noContent.setVisibility(list == null || list.isEmpty() ? View.VISIBLE : View.INVISIBLE);

        ListView listView = findViewById(R.id.list_view);
        listView.setVisibility(list == null || list.isEmpty() ? View.INVISIBLE : View.VISIBLE);



        if(list != null && !list.isEmpty()){
            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_view_item_layout, list.toArray(new String[list.size()]));

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                    //String item = value.get(i);
                    int ind = i - value.size();

                    Intent intent = new Intent(History.this, MainActivity.class);
                    //intent.putExtra(index, i);
                    //intent.putStringArrayListExtra(itemSelected, value);
                    //intent.putExtra(itemSelected, value.get(i));
                    Bundle bun = new Bundle();
                    bun.putString(index, String.valueOf(i));
                    bun.putStringArrayList(itemSelected, value);
                    intent.putExtras(bun);
                    ind = 0;

                    startActivity(intent);

                    Log.d("history", "SELECTED: "+value);

                }
            });

        }

    }
}
