package com.example.sandman.project5_bs;

/**
 * Created by Sandman on 11/5/2014.
 */
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ContactDetails extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details);
        TextView name = (TextView)findViewById(R.id.viewName);
        TextView phone = (TextView)findViewById(R.id.viewPhone);
        TextView email = (TextView)findViewById(R.id.viewEmail);
        TextView address = (TextView)findViewById(R.id.viewAddress);
        Button delBtn = (Button)findViewById(R.id.btnDelContact);
        Intent i = getIntent();
        final String _id = i.getStringExtra("table");
        final String iName = i.getStringExtra("name");
        final String iPhone = i.getStringExtra("phone");
        final String iEmail = i.getStringExtra("email");
        final String iAdd = i.getStringExtra("address");

        name.setText(iName);
        phone.setText(iPhone);
        email.setText(iEmail);
        address.setText(iAdd);

        // Check for delete button clicked
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                returnIntent.putExtra("call", "remove");
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
}
