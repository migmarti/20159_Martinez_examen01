package com.example.mmart.banpatito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int turnNumber = 1;
    CustomerAdapter customerAdapter;
    ArrayList<Customer> customers = new ArrayList<Customer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText txtName = (EditText) findViewById(R.id.editName);
        final EditText txtOperations = (EditText) findViewById(R.id.editOperations);
        Button btnAdd = (Button) findViewById(R.id.buttonAdd);
        Button btnQueue = (Button) findViewById(R.id.buttonQueue);
        ListView listView = (ListView) findViewById(R.id.listViewCustomer);
        customerAdapter = new CustomerAdapter(this);
        listView.setAdapter(customerAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString();
                int operations = Integer.parseInt(txtOperations.getText().toString());
                if (operations > 0) {
                    Customer customer = new Customer(name, operations);
                    customers.add(customer);
                    customerAdapter.add(customer);
                    customerAdapter.notifyDataSetChanged();
                }
            }
        });

        btnQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QueueActivity.class);
                intent.putExtra("Parcel", customers);
                startActivity(intent);
            }
        });
    }
}
