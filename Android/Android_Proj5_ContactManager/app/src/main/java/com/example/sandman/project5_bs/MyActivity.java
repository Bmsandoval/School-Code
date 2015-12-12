package com.example.sandman.project5_bs;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class MyActivity extends Activity {
    Spinner spinner;
    ListView listView;
    Button addBtn;

    ArrayAdapter<Contact> listAdapter;
    ArrayAdapter<String> spinAdapter;
    ArrayList<Contact> contacts;
    ArrayList<String> tableOptions = new ArrayList<String>();

    String tableAll = "All";
    String tableFriends = "Friends";
    String tableFamily = "Family";
    String tableCoWorkers = "CoWorkers";
    String tableOther = "Other";

    DatabaseHandler dbHandler;
    Contact clickedContact;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        spinner = (Spinner) findViewById(R.id.dropTable);
        listView = (ListView) findViewById(R.id.listview);
        addBtn = (Button) findViewById(R.id.btnAdd);

        dbHandler = new DatabaseHandler(getApplicationContext());
        contacts = dbHandler.getAllContacts();
        tableOptions.add(tableAll);
        tableOptions.add(tableFriends);
        tableOptions.add(tableFamily);
        tableOptions.add(tableCoWorkers);
        tableOptions.add(tableOther);
// Create an ArrayAdapter using the string array and a default spinner layout
        spinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tableOptions);
// Specify the layout to use when the list of choices appears
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(spinAdapter);
// Listview array adapter
        contacts = new ArrayList<Contact>();
        listAdapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, contacts);
        listView.setAdapter(listAdapter);
        PreLoad();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                UpdateList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
//                contacts = dbHandler.getAllContacts();
                UpdateList();
            }
        });

// If item in list is clicked, open detail view
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int arg2,
                                    long arg3) {
                Intent i = new Intent(getApplicationContext(), ContactDetails.class);
                clickedContact = contacts.get(arg2);
                i.putExtra("table", clickedContact.getTable());
                i.putExtra("name", clickedContact.getName());
                i.putExtra("phone", clickedContact.getPhone());
                i.putExtra("email", clickedContact.getEmail());
                i.putExtra("address", clickedContact.getAddress());
                startActivityForResult(i, 1);
            }
        });
// If add button is clicked, bring up AddNewContact screen and offer to make a new one.
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddNewContact.class);
                startActivityForResult(i, 1);
            }
        });
    }

    private void UpdateList() {
        String temp = String.valueOf(spinner.getSelectedItem());
        if(temp.equals(tableAll))
            contacts = dbHandler.getAllContacts();
        else
            contacts = dbHandler.getTableContacts(temp);
        listAdapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, contacts);
        listView.setAdapter(listAdapter);
    }

    // If user clicks button on either menu
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //listView.setAdapter(aAdapter);
        if (resultCode == Activity.RESULT_OK){
            String temp = data.getStringExtra("call");
// Activates if returning from AddNewContact
            if(temp.equals("add")) {
                String tTable = data.getStringExtra("table");
                String tName = data.getStringExtra("name");
                String tPhone = data.getStringExtra("phone");
                String tEmail = data.getStringExtra("email");
                String tAddress = data.getStringExtra("address");
                dbHandler.createContact(tTable, tName, tPhone, tEmail, tAddress);
// Activates if returning from ContactDetails
            } else if (temp.equals("remove")) {
                dbHandler.deleteContact(clickedContact);
            }
        }
        UpdateList();
    }

    @Override
    protected void onResume() {
//        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
//        datasource.close();
        super.onPause();
    }

    public void PreLoad() {
        dbHandler.createContact("Friends", "James Sullivan", "9844441122", "jsully@scare.net", "338 scare st");
        dbHandler.createContact("Friends", "Abominable Snowman", "1126822295", "abomy@google.com", "553 water way");
        dbHandler.createContact("Friends", "Boo", "1220547387", "boo@google.com", "that door");
        dbHandler.createContact("Family", "Mom", "9843339666", "theone@scream.com", "666 run away ave");
        dbHandler.createContact("Family", "Celia", "9840033563", "snakeeyes@boo.org", "392 west woods fork");
        dbHandler.createContact("CoWorkers", "Henry Waternoose", "2248547773", "hwaternoose@scare.net", "312 fallen oak dr.");
        dbHandler.createContact("CoWorkers", "Randall Boggs", "8899467735", "rboggs@boo.org", "");
        dbHandler.createContact("CoWorkers", "Roz", "2244395693", "roz@shhh.net", "993 no sanctuary ave");
        dbHandler.createContact("Other", "Fungus", "2249930654", "fun@ahhhh.org", "746 lost child dr");
        dbHandler.createContact("Other", "Rex", "8893348732", "rex@rex.net", "228 night ave");
        dbHandler.createContact("Other", "Flint", "6777219834", "flint@flooze.org", "693 terror ln");
        UpdateList();
    }

} 