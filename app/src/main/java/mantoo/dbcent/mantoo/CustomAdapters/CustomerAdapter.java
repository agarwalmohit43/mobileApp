package mantoo.dbcent.mantoo.CustomAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mantoo.dbcent.mantoo.Animation.AnimationUtil;
import mantoo.dbcent.mantoo.Information.CustomerInformation;
import mantoo.dbcent.mantoo.R;

/**
 * Created by dbcent91 on 24/7/17.
 */

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {

    Context context;
    ArrayList<CustomerInformation> customerInformationsData;

    LayoutInflater inflater;

    int previousPostion = 0;

    public CustomerAdapter(Context context, ArrayList<CustomerInformation> partyList) {
        this.context = context;
        this.customerInformationsData = partyList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public CustomerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.customer_list_row, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {

        final int currentPostion = position;
        final CustomerInformation customerInformationObj = customerInformationsData.get(position);


        myViewHolder.mCustomerName.setText(customerInformationsData.get(position).getCustomerName());
        myViewHolder.mCustomerAddress.setText(customerInformationsData.get(position).getCustomerAddress());
        myViewHolder.mCustomerBalance.setText(customerInformationsData.get(position).getCustomerBalance() + "");
        myViewHolder.mCustomerContact.setText(customerInformationsData.get(position).getCustomerContact());

        if (position > previousPostion) {// we are scrolling down

            AnimationUtil.animate(myViewHolder, true);

        } else {// we are scrolling up

            AnimationUtil.animate(myViewHolder, false);
        }
        previousPostion = position;


    }

    @Override
    public int getItemCount() {
        return customerInformationsData.size();
    }

    public void remove(int position) {
        customerInformationsData.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mCustomerName, mCustomerAddress, mCustomerContact, mCustomerBalance;


        public MyViewHolder(View itemView) {
            super(itemView);

            mCustomerAddress = (TextView) itemView.findViewById(R.id.customerAddress);
            mCustomerBalance = (TextView) itemView.findViewById(R.id.customerBalance);
            mCustomerContact = (TextView) itemView.findViewById(R.id.customerContact);
            mCustomerName = (TextView) itemView.findViewById(R.id.customerName);
        }
    }


    public void setFilter(ArrayList<CustomerInformation> newList) {
        customerInformationsData = new ArrayList<>();
        customerInformationsData.addAll(newList);
        notifyDataSetChanged();
    }
}
