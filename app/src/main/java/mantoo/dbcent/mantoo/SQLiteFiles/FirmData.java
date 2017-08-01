package mantoo.dbcent.mantoo.SQLiteFiles;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.UUID;

import mantoo.dbcent.mantoo.Extra.Message;
import mantoo.dbcent.mantoo.Interface.Firm;

/**
 * Created by dbcent91 on 28/7/17.
 */

public class FirmData implements Firm {

    private SQLiteDatabase sqLiteDatabaseObj;
    private SchemaDefinition schemaDefinitionObj;

    private Context context;

    public FirmData(Context context){
        this.context = context;
        schemaDefinitionObj = new SchemaDefinition(context);
        sqLiteDatabaseObj = schemaDefinitionObj.getWritableDatabase();
    }

    // ********* Firm (id -> 291e0f38-0056-4624-8962-5cbbc59e15fd,name -> Firm -> 1) *********

    @Override
    public void addFirm() {


        try{
            UUID firmId = UUID.randomUUID();
            long millisecond = System.currentTimeMillis();

            ContentValues contentValues = new ContentValues();
            contentValues.put("id",firmId.toString());
            contentValues.put("name","Firm -> 1");
            contentValues.put("mantooId","NULL");
            contentValues.put("userId","d04679e5-6522-4474-b127-9d381bdbb8aa");
            contentValues.put("createdAt", millisecond);
            contentValues.put("updatedAt", millisecond);

            Log.d("Firm",firmId.toString());

            sqLiteDatabaseObj.insert("firm",null,contentValues);

            Message.message(context,"Firm Data Inserted Successfully");
        }catch (Exception e){
            Message.message(context, "Firm Data Inserted -> Un-Successfull");
        }

    }
}
