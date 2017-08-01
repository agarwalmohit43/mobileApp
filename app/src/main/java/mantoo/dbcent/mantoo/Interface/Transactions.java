package mantoo.dbcent.mantoo.Interface;

import android.content.ContentValues;

import java.util.ArrayList;

import mantoo.dbcent.mantoo.Information.TransactionsInformation;

/**
 * Created by dbcent91 on 31/7/17.
 */

public interface Transactions {

    //add
    public void createTransactions();




    //update
    public void updateTransaction(ContentValues contentValues, String transactionId);


    public void updateTransaction();

    //delete

    //read
        public ArrayList<TransactionsInformation> getTransactionData();
        public TransactionsInformation getTransactionDataWithStatus();

}
