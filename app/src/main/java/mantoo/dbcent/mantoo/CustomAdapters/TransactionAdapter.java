package mantoo.dbcent.mantoo.CustomAdapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mantoo.dbcent.mantoo.Information.CustomerInformation;
import mantoo.dbcent.mantoo.Information.TransactionsInformation;
import mantoo.dbcent.mantoo.R;
import mantoo.dbcent.mantoo.SQLiteFiles.CustomerData;

/**
 * Created by dbcent91 on 1/8/17.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {

    Context context;
    ArrayList<TransactionsInformation> transactionsInformationsList;
    LayoutInflater inflater;

    CustomerData customerDataObj;
    Map<String,CustomerInformation> customerInformationMap;

    public TransactionAdapter(Context context, ArrayList<TransactionsInformation> transactionDataList) {

        this.context = context;
        this.transactionsInformationsList = transactionDataList;
        this.inflater = LayoutInflater.from(context);


        customerDataObj = new CustomerData(context);
        customerInformationMap = new HashMap<>();

        for(CustomerInformation obj:customerDataObj.getPartyList()){
            customerInformationMap.put(obj.getCustomerId(),obj);
        }

    }

    @Override
    public TransactionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row_transaction, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        Log.d("Transactions","viewholder");
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Log.d("Transactions","onbind");

        holder.txnNumber.setText(transactionsInformationsList.get(position).getTxnNumber());
        holder.partyName.setText(customerInformationMap.get(transactionsInformationsList.get(position).getPartyId()).getCustomerName());
        holder.amount.setText(transactionsInformationsList.get(position).getAmount()+"");
        holder.comment.setText(transactionsInformationsList.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return transactionsInformationsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        TextView txnNumber, partyName, amount, comment;
        public MyViewHolder(View itemView) {
            super(itemView);

            Log.d("Transactions","viewholderclass");


            txnNumber = (TextView) itemView.findViewById(R.id.txnNumber_transaction);
            partyName = (TextView) itemView.findViewById(R.id.party_transaction);
            amount = (TextView) itemView.findViewById(R.id.amount_transaction);
            comment = (TextView) itemView.findViewById(R.id.comment_transaction);
        }
    }
}
