package mantoo.dbcent.mantoo.CustomAdapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mantoo.dbcent.mantoo.Animation.AnimationUtil;
import mantoo.dbcent.mantoo.Information.InventoryInformation;
import mantoo.dbcent.mantoo.Information.TransactionEntryInfo;
import mantoo.dbcent.mantoo.R;

/**
 * Created by dbcent91 on 26/7/17.
 */

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.MyViewHolder> {

    Context context;
    ArrayList<InventoryInformation> inventoryInformationsList;

    LayoutInflater inflater;

    int previousPostion = 0;

    public InventoryAdapter(Context context, ArrayList<InventoryInformation> inventoryList) {
        this.context = context;
        this.inventoryInformationsList = inventoryList;
        inflater = LayoutInflater.from(context);

    }



    @Override
    public InventoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.inventory_list_row, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {

        final int currentPostion = position;
        final InventoryInformation inventoryInformationObj = inventoryInformationsList.get(position);


        myViewHolder.mInventoryName.setText(inventoryInformationsList.get(position).getInventoryName());
        myViewHolder.mInventoryTax.setText("Tax:\t" + inventoryInformationsList.get(position).getTax() + "");
        myViewHolder.mInventoryGST.setText("GST:\t" + inventoryInformationsList.get(position).getGstRate() + "");
        myViewHolder.mInventoryRate.setText("Rate:\t" + inventoryInformationsList.get(position).getRate() + "");
        myViewHolder.mInventoryMRP.setText("MRP:\t" + inventoryInformationsList.get(position).getMrp() + "");
        myViewHolder.mInventoryPurPrice.setText("Purchase Price:\t" + inventoryInformationsList.get(position).getPurcahsePrice() + "");

        if (position > previousPostion) {// we are scrolling down

            AnimationUtil.animate(myViewHolder, true);

        } else {// we are scrolling up

            AnimationUtil.animate(myViewHolder, false);
        }
        previousPostion = position;

    }


    @Override
    public int getItemCount() {
        return inventoryInformationsList.size();
    }

    public void remove(int position) {
        inventoryInformationsList.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mInventoryName, mInventoryTax, mInventoryGST, mInventoryRate, mInventoryMRP, mInventoryPurPrice;


        public MyViewHolder(View itemView) {
            super(itemView);

            mInventoryName = (TextView) itemView.findViewById(R.id.inventory_Name_Display);
            mInventoryTax = (TextView) itemView.findViewById(R.id.inventory_Tax_Display);
            mInventoryGST = (TextView) itemView.findViewById(R.id.inventory_GST_Display);
            mInventoryRate = (TextView) itemView.findViewById(R.id.inventory_Rate_Display);
            mInventoryMRP = (TextView) itemView.findViewById(R.id.inventory_MRp_Display);
            mInventoryPurPrice = (TextView) itemView.findViewById(R.id.inventory_purPrice_Display);


        }
    }
}
