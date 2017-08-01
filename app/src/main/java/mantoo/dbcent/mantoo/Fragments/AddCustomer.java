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

import mantoo.dbcent.mantoo.Activites.MainActivity;
import mantoo.dbcent.mantoo.R;
import mantoo.dbcent.mantoo.SQLiteFiles.CustomerData;

/**
 * Created by dbcent91 on 26/7/17.
 */

public class AddCustomer extends Fragment implements View.OnClickListener {

    CustomerData customerDataObj;

    Button addCustomer;

    EditText customerName, customerAddress, customerContact, customerBalance;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_customer, container, false);

        customerDataObj = new CustomerData(getActivity());

        addCustomer = (Button) rootView.findViewById(R.id.customerUpdate_button_add);
        addCustomer.setOnClickListener(this);


        customerName = (EditText) rootView.findViewById(R.id.customerName_editText_add);
        customerAddress = (EditText) rootView.findViewById(R.id.customerAddress_editText_add);
        customerContact = (EditText) rootView.findViewById(R.id.customerContact_editText_add);
        customerBalance = (EditText) rootView.findViewById(R.id.customerBalance_editText_add);

        return rootView;
    }


    @Override
    public void onClick(View view) {

        ContentValues contentValues = new ContentValues();

        contentValues.put("name", customerName.getText().toString());
        contentValues.put("address", customerAddress.getText().toString());
        contentValues.put("phoneNumber", customerContact.getText().toString());
        contentValues.put("dueAmount", Double.parseDouble(customerBalance.getText().toString()));

        customerDataObj.addCustomer(contentValues);
        ((MainActivity) getActivity()).initializeCountDrawer();
    }
}
