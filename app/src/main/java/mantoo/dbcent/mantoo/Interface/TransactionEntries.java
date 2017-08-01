package mantoo.dbcent.mantoo.Interface;

import android.content.ContentValues;

import java.util.ArrayList;

import mantoo.dbcent.mantoo.Information.TransactionEntryInfo;

/**
 * Created by dbcent91 on 1/8/17.
 */

public interface TransactionEntries {

    //add
    public void addTxnEntry(ContentValues contentValues);

    //read
    public ArrayList<TransactionEntryInfo> getTxnEntry();
}
