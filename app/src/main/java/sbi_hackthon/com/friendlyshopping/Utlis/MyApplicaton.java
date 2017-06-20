package sbi_hackthon.com.friendlyshopping.Utlis;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ramesh p on 11/06/2017.
 */

public class MyApplicaton extends Application {
    public static void setFirebaseAuth(FirebaseAuth firebaseAuth) {
        MyApplicaton.firebaseAuth = firebaseAuth;
    }

    private static FirebaseAuth firebaseAuth;

    public static void setFirebaseUser(FirebaseUser firebaseUser) {
        MyApplicaton.firebaseUser = firebaseUser;
    }

    private static FirebaseUser firebaseUser;

    public static FirebaseAuth getFirebaseAuth() {
        if (firebaseAuth == null){
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }

    public static FirebaseUser getFirebaseUser() {
        if (firebaseUser == null){
            firebaseUser = getFirebaseAuth().getCurrentUser();
        }
        return firebaseUser;
    }

    public static DatabaseReference getDatabaseReference() {
        if (databaseReference ==null){
            databaseReference = getFirebaseDatabase().getReference();
        }
        return databaseReference;
    }

    public static FirebaseDatabase getFirebaseDatabase() {
        if (firebaseDatabase == null){
            firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(false);
        }
        return firebaseDatabase;
    }

    private static DatabaseReference databaseReference;
    private static FirebaseDatabase firebaseDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        getFirebaseAuth();
        getFirebaseDatabase();
    }
}
