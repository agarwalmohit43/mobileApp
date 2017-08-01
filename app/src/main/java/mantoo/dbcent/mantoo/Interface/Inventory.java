package mantoo.dbcent.mantoo.Interface;

import android.content.ContentValues;

import java.util.ArrayList;

import mantoo.dbcent.mantoo.Information.CustomerInformation;
import mantoo.dbcent.mantoo.Information.InventoryInformation;

/**
 * Created by dbcent91 on 26/7/17.
 */

public interface Inventory {
    //Create
    public void addInventoryValues();

    public void addInventory(ContentValues contentValues);


    //Read
    public ArrayList<InventoryInformation> getInventoryList();


    //Update
    public void updateInventory(ContentValues contentValues, String partyId);

    //Delete
}
