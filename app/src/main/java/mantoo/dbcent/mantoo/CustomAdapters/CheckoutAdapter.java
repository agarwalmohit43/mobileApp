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
import mantoo.dbcent.mantoo.Information.InventoryInformation;
import mantoo.dbcent.mantoo.Information.TransactionEntryInfo;
import mantoo.dbcent.mantoo.R;
import mantoo.dbcent.mantoo.SQLiteFiles.InventoryData;

/**
 * Created by dbcent91 on 1/8/17.
 */

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.MyViewHolder> {

    Context context;
    ArrayList<TransactionEntryInfo> transactionEntryInfosList;

    InventoryData inventoryDataObj;
    Map<String,InventoryInformation> inventoryInformationMap;

    LayoutInflater inflater;

    public CheckoutAdapter(Context context, ArrayList<TransactionEntryInfo> txnEntry) {

        this.context = context;
        this.transactionEntryInfosList = txnEntry;
        inflater = LayoutInflater.from(context);

        inventoryDataObj = new InventoryData(context);
        inventoryInformationMap = new HashMap<>();

        for(InventoryInformation obj: inventoryDataObj.getInventoryList()){
            inventoryInformationMap.put(obj.getId(),obj);
        }
    }


    @Override
    public CheckoutAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.checkout_row, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        String inventoryId = transactionEntryInfosList.get(position).getInventoryId();
        holder.itemName.setText(inventoryInformationMap.get(transactionEntryInfosList.get(position).getInventoryId()).getInventoryName());
        holder.qty.setText(transactionEntryInfosList.get(position).getQuantity()+"");
        holder.rate.setText(transactionEntryInfosList.get(position).getRate()+"");
        holder.netAmount.setText(transactionEntryInfosList.get(position).getAmount()+"");
       // Log.d("Transactions",inventoryInformationMap.get(transactionEntryInfosList.get(position).getInventoryId()).getInventoryName());
    }



    @Override
    public int getItemCount() {
        return transactionEntryInfosList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView itemName, qty, rate, netAmount;


        public MyViewHolder(View itemView) {
            super(itemView);

            itemName = (TextView) itemView.findViewById(R.id.itemName_checkout);
            qty = (TextView) itemView.findViewById(R.id.qty_checkout);
            rate = (TextView) itemView.findViewById(R.id.rate_checkout);
            netAmount = (TextView) itemView.findViewById(R.id.netAmount_checkout);
        }
    }
}
