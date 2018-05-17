package com.mjf.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mjf.navigationview.BaseNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BaseNavigationView bnvMain = findViewById(R.id.bnv_main);
        bnvMain.setTextColor(R.color.colorPrimary, R.color.colorAccent);
        bnvMain.setTabIcons(R.array.navigation_tab_ics_dark, R.array.navigation_tab_ics_light);
    }
}
