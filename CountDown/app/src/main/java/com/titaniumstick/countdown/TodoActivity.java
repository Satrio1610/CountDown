package com.titaniumstick.countdown;


import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class TodoActivity extends ActionBarActivity {
    final static String FIRST_TIME = "com.android.titaniumstick.countdownver2.first_time";
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    String firstTime = "tru";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        readItems();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);

        SharedPreferences sp = getSharedPreferences(FIRST_TIME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        firstTime = sp.getString(FIRST_TIME,"tru");
        if (firstTime.equals("tru")) {
            writeInitial("Welcome to the To-do List!");
            writeInitial("Add new items below by typing it below and clicking \"Add Item\"");
            writeInitial("Click and hold to delete");
            firstTime = new String("fals");
            editor.putString(FIRST_TIME,firstTime);
            editor.commit();
        }



        setupListViewListener();
    }


    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");
        writeItems();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                        //Remove the item within array at position
                        items.remove(pos);
                        //Refresh the adapter
                        itemsAdapter.notifyDataSetChanged();
                        writeItems();
                        //Return true consumes the long click event (marks it handled)
                        return true;
                    }
                }
        );
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File (filesDir, "todo.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeInitial(String text){
        itemsAdapter.add(text);
        writeItems();
    }

}