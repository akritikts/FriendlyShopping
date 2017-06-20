package sbi_hackthon.com.friendlyshopping;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import sbi_hackthon.com.friendlyshopping.Adapters.ProductListAdapter;
import sbi_hackthon.com.friendlyshopping.DataObjects.PoductData;
import sbi_hackthon.com.friendlyshopping.Utlis.MyApplicaton;

public class ProductListActivity extends AppCompatActivity {

    private static final String TAG = "ProductList";
    Context context;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;

    List<PoductData> poductDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        context = ProductListActivity.this;
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
        poductDatas = new ArrayList<>();
    }

    void addOnClickListeners(){
    }

    void fetchDataFromFirebase(){
        if(databaseReference != null) {
            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != null) {
                        poductDatas = new ArrayList<>();
                        Map data = (Map) dataSnapshot.getValue();
                        Iterator iterator = data.keySet().iterator();
                        while (iterator.hasNext()) {
                            String key = (String) iterator.next();
                            Map data1 = (Map) data.get(key);
                            Long id = (Long) data1.get("id");
                            String name = (String) data1.get("name");
                            String description = (String) data1.get("description");
                            String image = (String) data1.get("image");
                            PoductData poductData = new PoductData(id, name, description, image);
                            poductDatas.add(poductData);
                        }

                        if (poductDatas.size() != 0){
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
        ProductListAdapter productListAdapter = new ProductListAdapter(context, poductDatas);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvBusinessList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(productListAdapter);
        productListAdapter.notifyDataSetChanged();
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
