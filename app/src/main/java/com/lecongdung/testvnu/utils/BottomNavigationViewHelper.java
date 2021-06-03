package com.lecongdung.testvnu.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.view.AccountActivity;
import com.lecongdung.testvnu.view.HomeActivity;
import com.lecongdung.testvnu.view.MyTestActivity;


public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";
    
    public static void setupBottomNavigationView(BottomNavigationView bottomNavigationView){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
    }

    public static void enableNavigation(final AppCompatActivity context, final BottomNavigationView view){

        view.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.menu_home:
                    if (view.getSelectedItemId() != R.id.menu_home) {
                        Intent intentLocation = new Intent(context, HomeActivity.class); //ACTIVITY_NUMBER = 0
                        context.startActivity(intentLocation);
                        context.finish();
                        break;
                    } else {
                        break;
                    }
                case R.id.menu_my_test:
                    if (view.getSelectedItemId() != R.id.menu_my_test) {
                        Intent intentRides = new Intent(context, MyTestActivity.class); //ACTIVITY_NUMBER = 2
                        context.startActivity(intentRides);
                        context.finish();
                        break;
                    } else {
                        break;
                    }
                case R.id.menu_account:
                    if (view.getSelectedItemId() != R.id.menu_account) {
                        Intent intentAccount = new Intent(context, AccountActivity.class); //ACTIVITY_NUMBER = 4
                        context.startActivity(intentAccount);
                        context.finish();
                        break;
                    } else {
                        break;
                    }
            }

            return false;
        });
    }

    public static void addBadge(final Context context, BottomNavigationView bottomNavigationView, int reminderLength){
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(2);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;
        View badge = LayoutInflater.from(context).inflate(R.layout.util_navigation_notification, itemView, true);
        TextView textView = (TextView) badge.findViewById(R.id.notificationsCount);
        textView.setText(String.valueOf(reminderLength));
    }

    public static void removeBadge(BottomNavigationView navigationView, int index) {
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) navigationView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(index);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;
        itemView.removeViewAt(itemView.getChildCount()-1);
    }
}
