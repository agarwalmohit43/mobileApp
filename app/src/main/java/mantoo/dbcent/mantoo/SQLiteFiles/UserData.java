package mantoo.dbcent.mantoo.SQLiteFiles;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.UUID;

import mantoo.dbcent.mantoo.Extra.Message;
import mantoo.dbcent.mantoo.Interface.User;

/**
 * Created by dbcent91 on 28/7/17.
 */

public class UserData implements User {

    private SQLiteDatabase sqLiteDatabaseObj;
    private SchemaDefinition schemaDefinitionObj;

    private Context context;

    public UserData(Context context){
        this.context = context;
        schemaDefinitionObj = new SchemaDefinition(context);
        sqLiteDatabaseObj = schemaDefinitionObj.getWritableDatabase();
    }

    // ********* User (id -> d04679e5-6522-4474-b127-9d381bdbb8aa, name -> User -> 1) *********

    @Override
    public void addUser() {

       try{
           UUID userId = UUID.randomUUID();
           long millisecond = System.currentTimeMillis();

           ContentValues contentValues = new ContentValues();
           contentValues.put("id",userId.toString());
           contentValues.put("name","User -> 1");
           contentValues.put("email","user_1@dbcent.com");
           contentValues.put("firmId", "NULL");
           contentValues.put("createdAt", millisecond);
           contentValues.put("updatedAt", millisecond);

           Log.d("User",userId.toString());

           sqLiteDatabaseObj.insert("user",null,contentValues);

           Message.message(context,"User Data Inserted Successfully");
       }catch (Exception e){
           Message.message(context, "User Data Inserted -> Un-Successfull");
       }


    }
}
