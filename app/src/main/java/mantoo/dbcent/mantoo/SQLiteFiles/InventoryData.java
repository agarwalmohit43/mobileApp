package mantoo.dbcent.mantoo.SQLiteFiles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

import mantoo.dbcent.mantoo.Extra.Message;
import mantoo.dbcent.mantoo.Information.CustomerInformation;
import mantoo.dbcent.mantoo.Information.InventoryInformation;
import mantoo.dbcent.mantoo.Interface.Inventory;

/**
 * Created by dbcent91 on 26/7/17.
 */

public class InventoryData implements Inventory {

    private SQLiteDatabase sqLiteDatabaseObj;
    private SchemaDefinition schemaDefinitionObj;

    private Context context;

    public InventoryData(Context context){
        this.context = context;
        schemaDefinitionObj = new SchemaDefinition(context);
        sqLiteDatabaseObj = schemaDefinitionObj.getWritableDatabase();
    }

    @Override
    public void addInventoryValues() {

        sqLiteDatabaseObj.beginTransaction();

        try {
            String name = "Inventory number -> ";
            for (int i = 1; i <= 2000; i++) {

                ContentValues contentValues = new ContentValues();

                UUID inventoryId = UUID.randomUUID();
                long millisecond = System.currentTimeMillis();


                contentValues.put("id", inventoryId.toString());
                contentValues.put("mantooId", "NULL");
                contentValues.put("name", name + i);
                contentValues.put("firmId", "NULL");
                contentValues.put("mantooProductid", "NULL");
                contentValues.put("tax", 14.5);
                contentValues.put("gstRate", 28.5);
                contentValues.put("rate", 200);
                contentValues.put("mrp", 250);
                contentValues.put("purcahsePrice", 150);
                contentValues.put("createdAt", millisecond);
                contentValues.put("updatedAt", millisecond);

                Log.d("Inventory", name + i);
                sqLiteDatabaseObj.insert("inventory", null, contentValues);
            }
            sqLiteDatabaseObj.setTransactionSuccessful();
            Message.message(context, "Successfull");
        } catch (Exception e) {
            Message.message(context, "Un-Successfull");
        } finally {
            sqLiteDatabaseObj.endTransaction();
        }

    }

   /* public void addDiscount() {

        sqLiteDatabaseObj.beginTransaction();

        try {

                ContentValues contentValues = new ContentValues();

                UUID inventoryId = UUID.randomUUID();
                long millisecond = System.currentTimeMillis();


                contentValues.put("updatedAt", millisecond);

                sqLiteDatabaseObj.insert("inventory", null, contentValues);
            }
            sqLiteDatabaseObj.setTransactionSuccessful();
            Message.message(context, "Successfull");
        } catch (Exception e) {
            Message.message(context, "Un-Successfull");
        } finally {
            sqLiteDatabaseObj.endTransaction();
        }

    }*/

    @Override
    public void addInventory(ContentValues contentValues) {




    }

    @Override
    public ArrayList<InventoryInformation> getInventoryList() {

        ArrayList<InventoryInformation> inventoryInformationsList = new ArrayList<>();

        String[] columns={"id","name","tax","gstRate","rate","mrp","purcahsePrice","discount"};
        Cursor cursor = sqLiteDatabaseObj.query("inventory", columns, null, null, null, null, null);

        while(cursor.moveToNext()){

            InventoryInformation obj=new InventoryInformation();
            obj.setId(cursor.getString(0));
            obj.setInventoryName(cursor.getString(1));
            obj.setTax(cursor.getDouble(2));
            obj.setGstRate(cursor.getDouble(3));
            obj.setRate(cursor.getDouble(4));
            obj.setMrp(cursor.getDouble(5));
            obj.setPurcahsePrice(cursor.getDouble(6));
            obj.setDiscount(cursor.getDouble(7));

            Log.d("Inventory",cursor.getString(0));
            inventoryInformationsList.add(obj);

        }
        return inventoryInformationsList;
    }



    @Override
    public void updateInventory(ContentValues contentValues, String partyId) {

    }
}
