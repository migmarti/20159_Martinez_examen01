package com.example.mmart.banpatito;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mmart.banpatito.Utils.CustomerHelper;

public class MainActivity extends AppCompatActivity {

    int turnNumber = 1;
    CustomerAdapter customerAdapter;
    CustomerHelper customerDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText txtName = (EditText) findViewById(R.id.editName);
        final EditText txtOperations = (EditText) findViewById(R.id.editOperations);
        Button btnAdd = (Button) findViewById(R.id.buttonAdd);
        Button btnQueue = (Button) findViewById(R.id.buttonQueue);
        Button btnClear = (Button) findViewById(R.id.buttonClear);
        ListView listView = (ListView) findViewById(R.id.listViewCustomer);
        customerAdapter = new CustomerAdapter(this);
        listView.setAdapter(customerAdapter);
        customerDB = customerAdapter.getDatabase();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString();
                int operations = Integer.parseInt(txtOperations.getText().toString());
                if (operations > 0) {
                    customerDB.open();
                    Customer customer = customerDB.addCustomer(name, operations, 0);
                    customerDB.close();
                    customerAdapter.add(customer);
                    customerAdapter.notifyDataSetChanged();
                }
            }
        });

        btnQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QueueActivity.class);
                customerDB.open();
                intent.putExtra("Parcel", customerDB.getAllCustomers());
                customerDB.close();
                startActivity(intent);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerDB.open();
                customerDB.deleteAllCustomers();
                customerDB.close();
                customerAdapter.clear();
                customerAdapter.notifyDataSetChanged();
            }
        });
    }


}
