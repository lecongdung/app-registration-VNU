package com.lecongdung.testvnu.fcm;

import android.content.SharedPreferences;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseService extends FirebaseInstanceIdService {
    private SharedPreferences sp;
    private String uid;

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        sp = getSharedPreferences("auth", MODE_PRIVATE);
        uid = sp.getString("uid", null);
        String tokenRefresh = FirebaseInstanceId.getInstance().getToken();
        if (uid != null) {
            updateToken(tokenRefresh);
        }
    }

    private void updateToken(String tokenRefresh) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("tokens");
        ref.child(uid).setValue(new Token(tokenRefresh, "employee"));
    }
}
