package com.choliy.igor.sunshine.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.choliy.igor.sunshine.R;
import com.choliy.igor.sunshine.SettingsFragment;

/**
 * SettingsActivity is responsible for displaying the {@link SettingsFragment}.
 * It is also responsible for orchestrating proper navigation when the up button is clicked.
 * When the up button is clicked from the SettingsActivity, we want to navigate to the Activity
 * that the user came from to get to the SettingsActivity.
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        /* Set setDisplayHomeAsUpEnabled to true on the support ActionBar */
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}