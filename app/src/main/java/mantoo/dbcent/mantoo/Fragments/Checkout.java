package mantoo.dbcent.mantoo.Fragments;

import android.content.ContentValues;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import mantoo.dbcent.mantoo.CustomAdapters.CheckoutAdapter;
import mantoo.dbcent.mantoo.CustomAdapters.InventoryAdapter;
import mantoo.dbcent.mantoo.Extra.Message;
import mantoo.dbcent.mantoo.Information.CustomerInformation;
import mantoo.dbcent.mantoo.Information.TransactionEntryInfo;
import mantoo.dbcent.mantoo.R;
import mantoo.dbcent.mantoo.SQLiteFiles.CustomerData;
import mantoo.dbcent.mantoo.SQLiteFiles.TransactionEntryData;
import mantoo.dbcent.mantoo.SQLiteFiles.TransactionsData;

/**
 * Created by dbcent91 on 31/7/17.
 */

public class Checkout extends Fragment implements View.OnClickListener {


    TextView totalAmountDis;
    RecyclerView recyclerView;
    Toolbar toolbar;

    TransactionEntryData transactionEntryDataObj;
    CheckoutAdapter mCheckoutAdapter;

    Double totalAmount;

    Button submitCart;

    String userId;


    CustomerData customerDataObj;
    Map<String,CustomerInformation> customerInformationMap;



    TransactionsData transactionsDataobj;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_checkout, container, false);

        totalAmountDis =(TextView) rootView.findViewById(R.id.totalAmount_cart);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.checkout_recycler);
        submitCart = (Button) rootView.findViewById(R.id.cart_Submit);
        submitCart.setOnClickListener(this);


        customerDataObj = new CustomerData(getActivity());
        customerInformationMap = new HashMap<>();

        for(CustomerInformation obj:customerDataObj.getPartyList()){
            customerInformationMap.put(obj.getCustomerName(),obj);
        }

        transactionsDataobj = new TransactionsData(getActivity());

        transactionEntryDataObj = new TransactionEntryData(getActivity());

        totalAmount = 0.00;

        for(TransactionEntryInfo obj: transactionEntryDataObj.getTxnEntry()){
            totalAmount += obj.getAmount();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Checkout");





        mCheckoutAdapter = new CheckoutAdapter(getActivity(), transactionEntryDataObj.getTxnEntry());


        recyclerView.setAdapter(mCheckoutAdapter);

        totalAmountDis.setText("Total: "+String.format("%.2f",totalAmount)+"");
    }

    @Override
    public void onClick(View view) {


    //    String userId=customerInformationMap.get(getArguments().get("customerName")).getCustomerId();
      //  Message.message(getActivity(),userId);

        for(TransactionEntryInfo obj: transactionEntryDataObj.getTxnEntry()){
            long millisecond = System.currentTimeMillis();
            ContentValues contentValues = new ContentValues();

            contentValues.put("id",obj.getTransactionsId());
            contentValues.put("partyId",customerInformationMap.get(getArguments().get("customerName")).getCustomerId());
            contentValues.put("firmId","291e0f38-0056-4624-8962-5cbbc59e15fd");
            contentValues.put("Date",millisecond);
            contentValues.put("amount",totalAmount);
            contentValues.put("additionalDiscount",0);
            contentValues.put("status","submit");
            contentValues.put("comment","Data Submitted");
            contentValues.put("recordLocation","NULL");
            contentValues.put("createdAt",millisecond);
            contentValues.put("updatedAt",millisecond);


            transactionsDataobj.updateTransaction(contentValues,obj.getTransactionsId());
            Message.message(getActivity(),obj.getTransactionsId());

        }


        transactionsDataobj.createTransactions();



    }


}
