package com.example.mmart.banpatito;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mmart.banpatito.Utils.CustomerHelper;

/**
 * Created by MMART on 9/8/2017.
 */
public class CustomerAdapter extends ArrayAdapter<Customer> {

    CustomerHelper customerDB;
    EditText txtDate;

    public CustomerAdapter(Context context, EditText txtDate) {
        super(context, R.layout.customer_row, R.id.textCustomer);
        this.txtDate = txtDate;
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
        Button btnVisit = (Button) objectView.findViewById(R.id.btnVisit);
        TextView txtNumber = (TextView) objectView.findViewById(R.id.textNumber);
        TextView txtCustomer = (TextView) objectView.findViewById(R.id.textCustomer);
        TextView txtOperations = (TextView) objectView.findViewById(R.id.textOperations);
        TextView txtId = (TextView) objectView.findViewById(R.id.textID);
        final TextView txtVisits = (TextView) objectView.findViewById(R.id.textVisits);

        final Customer customer = this.getItem(position);
        txtNumber.setText((position + 1) + " - ");
        txtCustomer.setText(customer.getName());
        txtOperations.setText("# of Operations: " + customer.getOperations() + " ");
        txtId.setText("Customer Number: " + customer.getId());
        txtVisits.setText("Visits: " + customer.printVisits());

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

        btnVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = txtDate.getText().toString();
                if (value.length() > 0) {
                    customer.addVisit(value);
                    txtVisits.setText("Visits: " + customer.printVisits());
                }
            }
        });

        return objectView;
    }
    public CustomerHelper getDatabase() {
        return customerDB;
    }
}
