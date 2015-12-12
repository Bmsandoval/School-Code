package com.example.sandman.project5_bs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sandman on 11/5/2014.
 */
public class AddNewContact extends ActionBarActivity {
    Spinner table;
    EditText name;
    EditText phone;
    EditText email;
    EditText address;
    Button addContact;
    ArrayList<String> tableOptions = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        table = (Spinner)findViewById(R.id.tableChooser);
        name = (EditText)findViewById(R.id.txtAddName);
        phone = (EditText)findViewById(R.id.txtAddPhone);
        email = (EditText)findViewById(R.id.txtAddEmail);
        address = (EditText)findViewById(R.id.txtAddAddress);
        addContact = (Button)findViewById(R.id.btnAddContact);

        ArrayAdapter<String> spinAdapter;
        String tableFriends = "Friends";
        String tableFamily = "Family";
        String tableCoWorkers = "CoWorkers";
        String tableOther = "Other";
        tableOptions.add(tableFriends);
        tableOptions.add(tableFamily);
        tableOptions.add(tableCoWorkers);
        tableOptions.add(tableOther);
// Create an ArrayAdapter using the string array and a default spinner layout
        spinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tableOptions);
// Specify the layout to use when the list of choices appears
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        table.setAdapter(spinAdapter);

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                returnIntent.putExtra("call", "add");
                returnIntent.putExtra("table", String.valueOf(table.getSelectedItem()));
                returnIntent.putExtra("name", name.getText().toString());
                returnIntent.putExtra("phone", phone.getText().toString());
                returnIntent.putExtra("email", email.getText().toString());
                returnIntent.putExtra("address", address.getText().toString());
                finish();
            }
        });
    }
}
