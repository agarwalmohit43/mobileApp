package mantoo.dbcent.mantoo.Fragments;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import mantoo.dbcent.mantoo.Extra.Message;
import mantoo.dbcent.mantoo.Information.CustomerInformation;
import mantoo.dbcent.mantoo.Information.InventoryInformation;
import mantoo.dbcent.mantoo.Information.TransactionsInformation;
import mantoo.dbcent.mantoo.R;
import mantoo.dbcent.mantoo.SQLiteFiles.CustomerData;
import mantoo.dbcent.mantoo.SQLiteFiles.InventoryData;
import mantoo.dbcent.mantoo.SQLiteFiles.TransactionEntryData;
import mantoo.dbcent.mantoo.SQLiteFiles.TransactionsData;

/**
 * Created by dbcent91 on 21/7/17.
 */

public class Sells extends Fragment implements TextWatcher, View.OnClickListener {

    AutoCompleteTextView customerTextView, inventoryTextView;

    TransactionEntryData transactionEntryDataObj;

    CustomerData customerDataObj;
    Map<String,CustomerInformation> customerInformationMap;

    InventoryData inventoryDataObj;
    Map<String,InventoryInformation> inventoryInformationMap;

    EditText qty,rate,discount,tax,comment;

    private String transactionId;
    TransactionsData transactionsDataObj;
    TransactionsInformation transactionsInformationObj;


    Button addToCart;


    Fragment mfragment;
    FragmentTransaction mfragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sells, container, false);

        setHasOptionsMenu(true);

        mfragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        transactionsDataObj = new TransactionsData(getActivity());
        transactionsInformationObj = transactionsDataObj.getTransactionDataWithStatus();
        transactionId = transactionsInformationObj.getId();

        transactionEntryDataObj = new TransactionEntryData(getActivity());

        //Log.d("Transactions",transactionId);

        addToCart = (Button) rootView.findViewById(R.id.addToCart_sellsButton);
        addToCart.setOnClickListener(this);

        customerTextView = (AutoCompleteTextView) rootView.findViewById(R.id.partyAutoCompleteSells);
        inventoryTextView = (AutoCompleteTextView) rootView.findViewById(R.id.productAutoCompleteSells);

        customerDataObj = new CustomerData(getActivity());
        customerInformationMap = new HashMap<>();

        for(CustomerInformation obj:customerDataObj.getPartyList()){
            customerInformationMap.put(obj.getCustomerName(),obj);
        }

        inventoryDataObj = new InventoryData(getActivity());
        inventoryInformationMap = new HashMap<>();

        for(InventoryInformation obj: inventoryDataObj.getInventoryList()){
            inventoryInformationMap.put(obj.getInventoryName(),obj);
        }


        qty = (EditText) rootView.findViewById(R.id.qty_sells_editext);
        rate = (EditText) rootView.findViewById(R.id.rate_sells_editext);
        discount = (EditText) rootView.findViewById(R.id.dis_sells_editext);
        tax = (EditText) rootView.findViewById(R.id.tax_sells_editext);
        comment = (EditText) rootView.findViewById(R.id.comment_Sell);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Sells");

        ArrayAdapter partyAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,customerInformationMap.keySet().toArray());
        ArrayAdapter productAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,inventoryInformationMap.keySet().toArray());

        customerTextView.setAdapter(partyAdapter);
        customerTextView.setThreshold(1);

        customerTextView.addTextChangedListener(this);

        inventoryTextView.setAdapter(productAdapter);
        inventoryTextView.setThreshold(1);

        inventoryTextView.addTextChangedListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        if(inventoryInformationMap.containsKey(inventoryTextView.getText().toString())){
           // Message.message(getActivity(), customerInformationMap.get(customerTextView.getText().toString()).getCustomerName());

            rate.setText(inventoryInformationMap.get(inventoryTextView.getText().toString()).getRate().toString());
            discount.setText(inventoryInformationMap.get(inventoryTextView.getText().toString()).getDiscount().toString());
            tax.setText(inventoryInformationMap.get(inventoryTextView.getText().toString()).getGstRate().toString());

        }else{
           // Message.message(getActivity(),"Not Found");
        }




    }

    @Override
    public void onClick(View view) {



            int quantity = Integer.parseInt(qty.getText().toString());
            Double rates=Double.parseDouble(rate.getText().toString());
            Double discounts=Double.parseDouble(discount.getText().toString());
            Double taxes=Double.parseDouble(tax.getText().toString());

            Double netAmount= ((rates*quantity)*((100 - discounts)/100))*((taxes+100)/100);

            UUID txnEntryId = UUID.randomUUID();
            long millisecond = System.currentTimeMillis();

            ContentValues contentValues = new ContentValues();

            contentValues.put("id",txnEntryId.toString());
            contentValues.put("transactionsId",transactionId);
            contentValues.put("inventoryId",inventoryInformationMap.get(inventoryTextView.getText().toString()).getId());
            contentValues.put("quantity",quantity);
            contentValues.put("rate",rates);
            contentValues.put("mrp",inventoryInformationMap.get(inventoryTextView.getText().toString()).getMrp());
            contentValues.put("discount",discounts);
            contentValues.put("tax",taxes);
            contentValues.put("amount",netAmount);
            contentValues.put("comment",comment.getText().toString());
            contentValues.put("createdAt",millisecond);

            transactionEntryDataObj.addTxnEntry(contentValues);
            Log.d("Transactions",comment.getText().toString());


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.cart_sell, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.cart_submit_menu){

            Bundle sendinfo = new Bundle();
            sendinfo.putString("customerName", customerTextView.getText().toString());



            mfragment = new Checkout();
            mfragment.setArguments(sendinfo);

            mfragmentTransaction.replace(R.id.content_main, mfragment);
            mfragmentTransaction.addToBackStack(null);
            mfragmentTransaction.commit();

        }

        return super.onOptionsItemSelected(item);
    }
}


