package mantoo.dbcent.mantoo.Fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.ArrayList;

import mantoo.dbcent.mantoo.Activites.MainActivity;
import mantoo.dbcent.mantoo.CustomAdapters.CustomerAdapter;
import mantoo.dbcent.mantoo.Extra.RecyclerTouchListener;
import mantoo.dbcent.mantoo.Information.CustomerInformation;
import mantoo.dbcent.mantoo.R;
import mantoo.dbcent.mantoo.SQLiteFiles.CustomerData;

/**
 * Created by dbcent91 on 21/7/17.
 */

public class Customer extends Fragment implements SearchView.OnQueryTextListener, View.OnClickListener {


    CustomerData customerDataObj;

    RecyclerView recyclerView;

    Toolbar toolbar;

    CustomerAdapter madapter;

    FloatingActionButton addCustoemr;

    Fragment mfragment;
    FragmentTransaction mfragmentTransaction;

    private static final int TIME_TO_AUTOMATICALLY_DISMISS_ITEM = 3000;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_customer, container, false);

        mfragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        customerDataObj = new CustomerData(getActivity());

        recyclerView = (RecyclerView) rootView.findViewById(R.id.customer_recyclerView);

        madapter = new CustomerAdapter(getActivity(), customerDataObj.getPartyList());

        recyclerView.setAdapter(madapter);

        addCustoemr = (FloatingActionButton) rootView.findViewById(R.id.addCustomer_FloatingBtn);
        addCustoemr.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Toast.makeText(getActivity(), "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Toast.makeText(getActivity(), "on Swiped ", Toast.LENGTH_SHORT).show();
                //Remove swiped item from list and notify the RecyclerView
            }
        };

        // Then set callback for recyclerView with below statements:

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Customer");





        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new MainActivity.ClickListener() {

            @Override
            public void onClick(View view, int position) {

                CustomerInformation customerInformation = customerDataObj.getPartyList().get(position);


                //Toast.makeText(getActivity(), customerInformation.getCustomerName() + " is selected!", Toast.LENGTH_SHORT).show();


                Bundle sendinfo = new Bundle();
                sendinfo.putString("customerId", customerInformation.getCustomerId());
                sendinfo.putString("customerName", customerInformation.getCustomerName());
                sendinfo.putString("customerContact", customerInformation.getCustomerContact());
                sendinfo.putString("customerAddress", customerInformation.getCustomerAddress());
                sendinfo.putString("customerBalance", customerInformation.getCustomerBalance() + "");


                mfragment = new UpdateCustomer();
                mfragment.setArguments(sendinfo);

                mfragmentTransaction.add(R.id.content_main, mfragment);
                mfragmentTransaction.addToBackStack(null);
                mfragmentTransaction.commit();


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        newText = newText.toLowerCase();
        ArrayList<CustomerInformation> customerInformations = new ArrayList<CustomerInformation>();

        for (CustomerInformation customerInformation : customerDataObj.getPartyList()) {

            String customerName = customerInformation.getCustomerName().toLowerCase();
            if (customerName.contains(newText)) {
                customerInformations.add(customerInformation);
            }
        }

        madapter.setFilter(customerInformations);
        return true;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.addCustomer_FloatingBtn) {
           /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/

            mfragment = new AddCustomer();
            mfragmentTransaction.replace(R.id.content_main, mfragment);
            mfragmentTransaction.addToBackStack(null);
            mfragmentTransaction.commit();


        }
    }
}
