package sbi_hackthon.com.friendlyshopping.Utlis;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sbi_hackthon.com.friendlyshopping.Adapters.BillListAdapter;
import sbi_hackthon.com.friendlyshopping.Adapters.ProductListAdapter;
import sbi_hackthon.com.friendlyshopping.DataObjects.BillData;
import sbi_hackthon.com.friendlyshopping.DataObjects.BusinessData;
import sbi_hackthon.com.friendlyshopping.DataObjects.PoductData;
import sbi_hackthon.com.friendlyshopping.GoogleLoginActivity;
import sbi_hackthon.com.friendlyshopping.R;

public class BillActivity extends AppCompatActivity {
    private static final String TAG = "BillList";
    Context context;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;

    List<BillData> billDatas;
    long bussId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        context = getApplicationContext();
        bussId = getIntent().getLongExtra("BUSID",201);
        initialize();
        fetchDataFromFirebase();
    }
    void initialize(){
        initializeFirebase();
        initializeData();
        initializeUIElements();
        addOnClickListeners();
    }

    void initializeUIElements(){

    }
    void initializeFirebase(){
        firebaseAuth = MyApplicaton.getFirebaseAuth();
        firebaseUser = MyApplicaton.getFirebaseUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        if (firebaseUser == null){
            Intent intent = new Intent(context, GoogleLoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
    void initializeData(){
        billDatas = new ArrayList<>();
    }

    void addOnClickListeners(){
    }
    void fetchDataFromFirebase(){
        if(databaseReference != null) {
            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != null) {
                        billDatas = new ArrayList<>();
                        Map data = (Map) dataSnapshot.getValue();
                        Iterator iterator = data.keySet().iterator();
                        while (iterator.hasNext()) {
                            String key = (String) iterator.next();
                            Map data1 = (Map) data.get(key);
                            long id = (long)data1.get("busid");
                            String name = (String) data1.get("customerName");
                            String message = (String) data1.get("message");
                            String amount = (String) data1.get("amount");
                            String date = (String) data1.get("dateAdded");
                            BillData billData = new BillData(id,name,amount,message,date);
                            if (billData.getId()==bussId)
                            billDatas.add(billData);
                        }

                        if (billDatas.size() != 0){
                            populateList();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            databaseReference.child("Products").addListenerForSingleValueEvent(valueEventListener);
        }
    }
    void populateList(){
        BillListAdapter billListAdapter = new BillListAdapter(context, billDatas);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.bill_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(billListAdapter);
        billListAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.notification:
                return true;
            case R.id.logout:
                if (firebaseAuth != null){
                    MyApplicaton.getFirebaseAuth().signOut();
                    MyApplicaton.setFirebaseAuth(null);
                    MyApplicaton.setFirebaseUser(null);
                    Intent intent = new Intent(this, GoogleLoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
