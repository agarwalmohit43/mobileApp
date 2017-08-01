package mantoo.dbcent.mantoo.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mantoo.dbcent.mantoo.CustomAdapters.TransactionAdapter;
import mantoo.dbcent.mantoo.Information.TransactionsInformation;
import mantoo.dbcent.mantoo.R;
import mantoo.dbcent.mantoo.SQLiteFiles.TransactionsData;

/**
 * Created by dbcent91 on 1/8/17.
 */

public class Transaction extends Fragment {


    RecyclerView recyclerView;

    Toolbar toolbar;

    TransactionAdapter mAdapter;
    TransactionsData transactionsDataObj;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transaction, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.transaction_recyclerView);

        transactionsDataObj = new TransactionsData(getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));





        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Transaction");

        mAdapter = new TransactionAdapter(getActivity(),transactionsDataObj.getTransactionData());

        recyclerView.setAdapter(mAdapter);
    }
}
