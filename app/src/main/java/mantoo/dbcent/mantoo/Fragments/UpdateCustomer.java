package mantoo.dbcent.mantoo.Fragments;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import mantoo.dbcent.mantoo.R;
import mantoo.dbcent.mantoo.SQLiteFiles.CustomerData;


public class UpdateCustomer extends Fragment implements View.OnClickListener {

    CustomerData customerDataObj;

    Button UpdateCustomer;

    EditText customerName, customerAddress, customerContact, customerBalance;

    String customerId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_update_customer, container, false);

        customerDataObj = new CustomerData(getActivity());

        UpdateCustomer = (Button) rootView.findViewById(R.id.customerUpdate_button);
        UpdateCustomer.setOnClickListener(this);

        customerId = getArguments().getString("customerId", "Internal Error -> Wrong key");

        customerName = (EditText) rootView.findViewById(R.id.customerName_editText);
        customerAddress = (EditText) rootView.findViewById(R.id.customerAddress_editText);
        customerContact = (EditText) rootView.findViewById(R.id.customerContact_editText);
        customerBalance = (EditText) rootView.findViewById(R.id.customerBalance_editText);


        customerName.setText(getArguments().getString("customerName", "Internal Error -> Wrong key"));
        customerAddress.setText(getArguments().getString("customerAddress", "Internal Error -> Wrong key"));
        customerContact.setText(getArguments().getString("customerContact", "Internal Error -> Wrong key"));
        customerBalance.setText(getArguments().getString("customerBalance", "Internal Error -> Wrong key"));


        return rootView;
    }

    @Override
    public void onClick(View view) {

        ContentValues contentValues = new ContentValues();

        contentValues.put("name", customerName.getText().toString());
        contentValues.put("address", customerAddress.getText().toString());
        contentValues.put("phoneNumber", customerContact.getText().toString());
        contentValues.put("dueAmount", Double.parseDouble(customerBalance.getText().toString()));


        customerDataObj.updateParty(contentValues, customerId);

        //Toast.makeText(getActivity(),customerName.getText().toString(),Toast.LENGTH_SHORT).show();
    }


}
