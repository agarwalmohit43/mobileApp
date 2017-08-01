package mantoo.dbcent.mantoo.SQLiteFiles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import mantoo.dbcent.mantoo.Extra.Message;
import mantoo.dbcent.mantoo.Information.TransactionEntryInfo;
import mantoo.dbcent.mantoo.Information.TransactionsInformation;
import mantoo.dbcent.mantoo.Interface.TransactionEntries;

/**
 * Created by dbcent91 on 1/8/17.
 */

public class TransactionEntryData implements TransactionEntries{


    private SQLiteDatabase sqLiteDatabaseObj;
    private SchemaDefinition schemaDefinitionObj;
    private TransactionsData transactionsDataObj;

    private Context context;

    public TransactionEntryData(Context context){
        this.context = context;
        schemaDefinitionObj = new SchemaDefinition(context);
        sqLiteDatabaseObj = schemaDefinitionObj.getWritableDatabase();
        transactionsDataObj = new TransactionsData(context);
    }


    @Override
    public void addTxnEntry(ContentValues contentValues) {

        sqLiteDatabaseObj.beginTransaction();
        try
        {
            sqLiteDatabaseObj.insert("txnEntry",null,contentValues);
            sqLiteDatabaseObj.setTransactionSuccessful();
            Message.message(context, "Item addedd to Cart Successfully");

        }catch (Exception e)
        {
            Log.d("Transactions",""+e);
            Message.message(context, "Unabel to update");

        }finally
        {
            sqLiteDatabaseObj.endTransaction();
        }
    }

    @Override
    public ArrayList<TransactionEntryInfo> getTxnEntry() {

        TransactionsInformation transactionsInformationObj = new TransactionsInformation();
        transactionsInformationObj = transactionsDataObj.getTransactionDataWithStatus();

        ArrayList<TransactionEntryInfo> transactionEntryInfosList = new ArrayList<>();

        String[] columns = {"id","transactionsId","inventoryId","quantity","rate","mrp","discount","tax","amount","comment","createdAt"};

        Cursor cursor = sqLiteDatabaseObj.query("txnEntry",columns,"transactionsId = '"+ transactionsInformationObj.getId()+"'",null,null,null,null);

        while (cursor.moveToNext()){
            TransactionEntryInfo obj = new TransactionEntryInfo();
            obj.setTxnEntryId(cursor.getString(0));
            obj.setTransactionsId(cursor.getString(1));
            obj.setInventoryId(cursor.getString(2));
            obj.setQuantity(cursor.getInt(3));
            obj.setRate(cursor.getDouble(4));
            obj.setMrp(cursor.getDouble(5));
            obj.setDiscount(cursor.getDouble(6));
            obj.setTax(cursor.getDouble(7));
            obj.setAmount(cursor.getDouble(8));
            obj.setComment(cursor.getString(9));
            obj.setMillisecond(cursor.getLong(10));

            transactionEntryInfosList.add(obj);

        }


        return transactionEntryInfosList;
    }


}
