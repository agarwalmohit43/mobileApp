package mantoo.dbcent.mantoo.Activites;

import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mantoo.dbcent.mantoo.CustomAdapters.CustomerAdapter;
import mantoo.dbcent.mantoo.Fragments.Checkout;
import mantoo.dbcent.mantoo.Fragments.Customer;
import mantoo.dbcent.mantoo.Fragments.Payments;
import mantoo.dbcent.mantoo.Fragments.Product;
import mantoo.dbcent.mantoo.Fragments.Sells;
import mantoo.dbcent.mantoo.Fragments.Transaction;
import mantoo.dbcent.mantoo.Fragments.UpdateCustomer;
import mantoo.dbcent.mantoo.Information.TransactionsInformation;
import mantoo.dbcent.mantoo.R;
import mantoo.dbcent.mantoo.SQLiteFiles.CustomerData;
import mantoo.dbcent.mantoo.SQLiteFiles.FirmData;
import mantoo.dbcent.mantoo.SQLiteFiles.InventoryData;
import mantoo.dbcent.mantoo.SQLiteFiles.SchemaDefinition;
import mantoo.dbcent.mantoo.SQLiteFiles.TransactionsData;
import mantoo.dbcent.mantoo.SQLiteFiles.UserData;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Context mContext;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    TextView mCustomer, mProduct, mPayment, mSells;

    SchemaDefinition schemaDefinitionObj;
    CustomerData customerDataObj;
    InventoryData inventoryDataObj;
    UserData userDataObj;
    FirmData firmDataObj;
    TransactionsData transactionsDataObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Database objects
        customerDataObj = new CustomerData(this);
        inventoryDataObj = new InventoryData(this);
        userDataObj = new UserData(this);
        firmDataObj = new FirmData(this);
        transactionsDataObj =new TransactionsData(this);

        init(savedInstanceState);

        TransactionsInformation obj= new TransactionsInformation();
       //obj=transactionsDataObj.getTransactionDataWithStatus();
    }

    private void createSchema() {

        schemaDefinitionObj = new SchemaDefinition(this);
        SQLiteDatabase sqLiteDatabaseObj = schemaDefinitionObj.getWritableDatabase();


    }

    private void init(Bundle savedInstanceState) {
        bindResources();
        setUpToolbar();
        setUpNavigationDrawerMenu();
        byDefaultLoading();
        //createSchema();


    }

    private void byDefaultLoading() {

        Fragment mfragment = new Customer();
        FragmentTransaction mfragmentTransaction = getSupportFragmentManager().beginTransaction();

        mfragmentTransaction.add(R.id.content_main, mfragment);

        mfragmentTransaction.commit();

        mNavigationView.setCheckedItem(R.id.navigation_Customer);


    }

    private void bindResources() {
        mContext = this;
        mToolbar = (Toolbar) findViewById(R.id.activity_main_appbar);

        mNavigationView = (NavigationView) findViewById(R.id.navigation_navigationView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawerLayout);


        mCustomer = (TextView) MenuItemCompat.getActionView(mNavigationView.getMenu().findItem(R.id.navigation_Customer));
        mPayment = (TextView) MenuItemCompat.getActionView(mNavigationView.getMenu().findItem(R.id.navigation_Payments));
        mSells = (TextView) MenuItemCompat.getActionView(mNavigationView.getMenu().findItem(R.id.navigation_Sells));
        mProduct = (TextView) MenuItemCompat.getActionView(mNavigationView.getMenu().findItem(R.id.navigation_Product));
    }

    private void setUpNavigationDrawerMenu() {
        mNavigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                mToolbar,
                R.string.drawer_open,
                R.string.drawer_close);

        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


        initializeCountDrawer();

    }

    public void initializeCountDrawer() {


        mCustomer.setGravity(Gravity.CENTER_VERTICAL);
        mCustomer.setTypeface(null, Typeface.BOLD);
        //mCustomer.setBackgroundResource(R.drawable.mohit);
        mCustomer.setTextColor(getResources().getColor(R.color.colorAccent));
        mCustomer.setText(customerDataObj.getPartyList().size() + "");

        mPayment.setGravity(Gravity.CENTER_VERTICAL);
        mPayment.setTypeface(null, Typeface.BOLD);
        //mPayment.setBackgroundResource(R.drawable.mohit);
        mPayment.setTextColor(getResources().getColor(R.color.colorAccent));
        mPayment.setText("18");

        mSells.setGravity(Gravity.CENTER_VERTICAL);
        mSells.setTypeface(null, Typeface.BOLD);
        //mSells.setBackgroundResource(R.drawable.mohit);
        mSells.setTextColor(getResources().getColor(R.color.colorAccent));
        mSells.setText("55");

        mProduct.setGravity(Gravity.CENTER_VERTICAL);
        mProduct.setTypeface(null, Typeface.BOLD);
        //mProduct.setBackgroundResource(R.drawable.mohit);
        mProduct.setTextColor(getResources().getColor(R.color.colorAccent));
        mProduct.setText(inventoryDataObj.getInventoryList().size() + "");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        String itemName = (String) item.getTitle();

        Toast.makeText(MainActivity.this, itemName + " Clicked", Toast.LENGTH_SHORT).show();

        displaySelectedFragment(item.getItemId());


        //showDrawer();
       /* switch (item.getItemId()) {
            case R.id.navigation_Customer:

                break;
            case R.id.navigation_Product:

                break;

            case R.id.navigation_Payments:

                break;

            case R.id.navigation_Sells:

                break;

        }*/

        closeDrawer();

        return true;
    }

    private void displaySelectedFragment(int id) {
        Fragment fragment = null;

        switch (id) {
            case R.id.navigation_Customer:

                fragment = new Customer();
                break;
            case R.id.navigation_Product:
                fragment = new Product();
                break;

            case R.id.navigation_Payments:
                fragment = new Payments();
                break;

            case R.id.navigation_Sells:
                fragment = new Sells();
                break;
            case R.id.navigation_Transaction:
                fragment = new Transaction();
                break;
        }

        if (fragment != null) {
            FragmentTransaction mfragmentTransaction = getSupportFragmentManager().beginTransaction();
            mfragmentTransaction.replace(R.id.content_main, fragment);
            mfragmentTransaction.addToBackStack(null);
            mfragmentTransaction.commit();
        }
    }

    private void showDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    private void closeDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Mantoo");
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
            closeDrawer();
        else if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.check_update) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", "Mohit");
            customerDataObj.updateParty(contentValues, "c9bc4c11-9196-496c-bfd6-90bfdd7ff675");
        }

        return super.onOptionsItemSelected(item);
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


}
