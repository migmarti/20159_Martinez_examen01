package com.example.mmart.banpatito;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.mmart.banpatito.Utils.CustomerHelper;

/**
 * Created by MMART on 9/8/2017.
 */
public class CustomerAdapter extends ArrayAdapter<Customer> {

    CustomerHelper customerDB;

    public CustomerAdapter(Context context) {
        super(context, R.layout.customer_row, R.id.textCustomer);
        customerDB = new CustomerHelper(context);
        customerDB.open();
        addAll(customerDB.getAllCustomers());
        customerDB.close();
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View objectView = super.getView(position, convertView, parent);

        Button btnDelete = (Button) objectView.findViewById(R.id.btnDelete);
        TextView txtNumber = (TextView) objectView.findViewById(R.id.textNumber);
        TextView txtCustomer = (TextView) objectView.findViewById(R.id.textCustomer);
        TextView txtOperations = (TextView) objectView.findViewById(R.id.textOperations);

        final Customer customer = this.getItem(position);
        txtNumber.setText((position + 1) + " | ");
        txtCustomer.setText(customer.getName() + " - ");
        txtOperations.setText(customer.getOperations() + "");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerDB.open();
                customerDB.deleteCustomer(customer.getId());
                customerDB.close();
                remove(customer);
                notifyDataSetChanged();
            }
        });
        return objectView;
    }
    public CustomerHelper getDatabase() {
        return customerDB;
    }
}
