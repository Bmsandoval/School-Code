package com.example.sandman.grocery_list;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MyActivity extends ActionBarActivity {
        int del = -1;
        ArrayList<ListItems> itemList;
        ListView lstView;
        EditText userEntry;
        ArrayAdapter<ListItems> aAdapter;
//        {
//            itemList = new ArrayList<ListItems>();
//            userEntry = (EditText) findViewById(R.id.itemEntry);
//            lstView = (ListView) findViewById(R.id.itemView);
//            aAdapter = new ArrayAdapter<ListItems>(this, android.R.layout.simple_list_item_1, itemList);
//            lstView.setAdapter(aAdapter);
//        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        lstView = (ListView) findViewById(R.id.itemView);
        itemList = new ArrayList<ListItems>();
        userEntry = (EditText) findViewById(R.id.itemEntry);
        aAdapter = new ArrayAdapter<ListItems>(this, android.R.layout.simple_list_item_1, itemList);
        lstView.setAdapter(aAdapter);
/*        ListView lstView = (ListView)findViewById(R.id.itemView);
        final EditText userEntry = (EditText)findViewById(R.id.itemEntry);
        // create todo list
        final ArrayList<ListItems> itemList = new ArrayList<ListItems>();
        // create array adapter
        final ArrayAdapter<ListItems> aAdapter;
        aAdapter = new ArrayAdapter<ListItems>(this, android.R.layout.simple_list_item_1, itemList);
        lstView.setAdapter(aAdapter);
        */
        // Watch for enter key or center dpad
        userEntry.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) || (keyCode == KeyEvent.KEYCODE_ENTER)){
                        itemList.add(new ListItems(userEntry.getText().toString()));
                        aAdapter.notifyDataSetChanged();
                        userEntry.setText("");
                        return true;
                    }
                return false;
            }
        });

        /**
         * Listview item click listener
         * TrackListActivity will be lauched by passing album id
         * */
        lstView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int arg2,
                                    long arg3) {
                del = arg2;
                Intent i = new Intent(getApplicationContext(), Detail.class);
                String itemName = (itemList.get(arg2)).toString();
                i.putExtra("item_name", itemName);
                startActivityForResult(i, 1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        lstView.setAdapter(aAdapter);
        if (resultCode == Activity.RESULT_OK){
                itemList.remove(del);
                aAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
