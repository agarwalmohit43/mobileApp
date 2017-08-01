package mantoo.dbcent.mantoo.SQLiteFiles;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import mantoo.dbcent.mantoo.Extra.Message;

/**
 * Created by dbcent91 on 21/7/17.
 */

public class SchemaDefinition extends SQLiteOpenHelper {
    private Context context;

    private static final String DATABASE_NAME="mantoo";
    private static final int DATABASE_VERSION=17;

   // private static final String CREATE_TABLE="CREATE TABLE parties (id TEXT PRIMARY KEY NOT NULL,mantooId TEXT NOT NULL,name TEXT NOT NULL UNIQUE,address TEXT,phoneNumber TEXT,dueAmount DECIMAL(10,5) NOT NULL DEFAULT 0,createdAt INTEGER,updatedAt INTEGER);";
    //private static final String DROP_TABLE="DROP TABLE  IF EXISTS parties";

//    private static final String CREATE_TABLE_PARTIES = "CREATE TABLE parties (id TEXT PRIMARY KEY NOT NULL,mantooId TEXT NOT NULL,name TEXT NOT NULL UNIQUE,address TEXT,phoneNumber TEXT,dueAmount DECIMAL(10,5) NOT NULL DEFAULT 0,createdAt INTEGER,updatedAt INTEGER);";
//    private static final String CREATE_TABLE_INVENTORY = "CREATE TABLE inventory (id TEXT PRIMARY KEY NOT NULL,mantooId TEXT,name TEXT NOT NULL UNIQUE,firmId TEXT, mantooProductid TEXT, tax DECIMAL(10,5) NOT NULL, gstRate DECIMAL(10,5) NOT NULL,rate DECIMAL(10,5) NOT NULL,mrp DECIMAL(10,5) NOT NULL, purcahsePrice DECIMAL(10,5) NOT NULL,createdAt INTEGER,updatedAt INTEGER);";
//    private static final String CREATE_TABLE_USER = "CREATE TABLE user (id TEXT PRIMARY KEY NOT NULL, name TEXT NOT NULL, email TEXT NOT NULL,firmId TEXT, createdAt INTEGER,updatedAt INTEGER)";
 //   private static final String CREATE_TABLE_FIRM = "CREATE TABLE firm (id TEXT PRIMARY KEY NOT NULL, name TEXT NOT NULL, mantooId TEXT, userId TEXT NOT NULL, createdAt INTEGER,updatedAt INTEGER, FOREIGN KEY (userId) REFERENCES user(id))";
/*
    private static final String CREATE_TABLE_TRANSACTION_ENTRY = "CREATE TABLE txnEntry (\n" +
            "    id            TEXT    PRIMARY KEY\n" +
            "                          NOT NULL\n" +
            "                          UNIQUE,\n" +
            "    transactionsId TEXT    NOT NULL,\n" +
            "    inventoryId   TEXT    NOT NULL,\n" +
            "    quantity      INTEGER DEFAULT (0) \n" +
            "                          NOT NULL,\n" +
            "    rate          DECIMAL NOT NULL\n" +
            "                          DEFAULT (0),\n" +
            "    mrp           DECIMAL DEFAULT (0) \n" +
            "                          NOT NULL,\n" +
            "    discount      DECIMAL NOT NULL\n" +
            "                          DEFAULT (0),\n" +
            "    tax           DECIMAL NOT NULL\n" +
            "                          DEFAULT (0),\n" +
            "    amount        DECIMAL NOT NULL\n" +
            "                          DEFAULT (0),\n" +
            "    comment       TEXT    DEFAULT Nothing,\n" +
            "    createdAt     INTEGER NOT NULL,\n" +
            "    updatedAt     INTEGER\n" +
            ",FOREIGN KEY (transactionsId) REFERENCES transactions(id), FOREIGN KEY (inventoryId) REFERENCES inventory(id));";
*/


   /* private static final String CREATE_TABLE_TRANSACTION = "CREATE TABLE transactions (\n" +
            "    id                 TEXT    PRIMARY KEY\n" +
            "                               NOT NULL,\n" +
            "    txnNumber          TEXT    NOT NULL\n" +
            "                               DEFAULT TXN001,\n" +
            "    partyId            TEXT    NOT NULL,\n" +
            "    firmId             TEXT    NOT NULL,\n" +
            "    Date               INTEGER NOT NULL,\n" +
            "    amount             DECIMAL NOT NULL\n" +
            "                               DEFAULT (0),\n" +
            "    additionalDiscount DECIMAL DEFAULT (0),\n" +
            "    status             TEXT    DEFAULT cart,\n" +
            "    comment            TEXT    DEFAULT Nothing\n" +
            "                               NOT NULL,\n" +
            "    recordLocation     TEXT    CONSTRAINT recordLocationxn_foreign REFERENCES recordLocation (id),\n" +
            "    createdAt          INTEGER NOT NULL,\n" +
            "    updatedAt          INTEGER, FOREIGN KEY (partyId) REFERENCES parties(id));";*/







  //  private static final String ALTER_TABLE_INVENTORY="ALTER TABLE inventory ADD COLUMN discount DECIMAL(10,5) DEFAULT 0";
    private static final String ALTER_TABLE_Transactions="ALTER TABLE transactions MODIFY COLUMN txnNumber INTEGER";

//    private static final String DROP_TABLE_PARTIES = "DROP TABLE  IF EXISTS parties";
//    private static final String DROP_TABLE_INVENTORY = "DROP TABLE  IF EXISTS inventory";
//    private static final String DROP_TABLE_USER = "DROP TABLE  IF EXISTS user";
//    private static final String DROP_TABLE_FIRM = "DROP TABLE  IF EXISTS firm";
 //   private static final String DROP_TABLE_TRANSACTION = "DROP TABLE  IF EXISTS transactions";
//    private static final String DROP_TABLE_TRANSACTION_ENTRY = "DROP TABLE  IF EXISTS txnEntry";


     public SchemaDefinition(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
        //Toast.makeText(context,"Constructor Called of insertdata",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        Message.message(context,"onCreate Called");

        try{
           // sqLiteDatabase.execSQL(CREATE_TABLE_TRANSACTION_ENTRY);

            Message.message(context,"Table Created Transaction_Entry");


        }catch (Exception e){
            //e.printStackTrace();
            Message.message(context,""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Message.message(context,"onUpgrade Called");
        try{
           sqLiteDatabase.execSQL(ALTER_TABLE_Transactions);
            Message.message(context,"Transaction Table Altered");
           // Message.message(context," Table Droped \n onCreate Will be called Now");
             //onCreate(sqLiteDatabase);

            Log.d("Transactions","Altered");
        }catch (Exception e){
            Message.message(context,""+e);
            Log.d("Transactions", ""+e);
        }
    }
}
