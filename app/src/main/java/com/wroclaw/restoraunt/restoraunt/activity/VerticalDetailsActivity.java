package com.wroclaw.restoraunt.restoraunt.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.wroclaw.restoraunt.restoraunt.R;
import com.wroclaw.restoraunt.restoraunt.fragment.DetailsFragment;

/**
 * @author Dmytro Melnychuk
 */
public class VerticalDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            fillDetailsInfo();
        }
        setContentView(R.layout.details_layout);
    }

    private void fillDetailsInfo() {
        DetailsFragment details = new DetailsFragment();
        details.setArguments(getIntent().getExtras());
        addContext(details);
    }

    private void addContext(DetailsFragment details) {
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, details)
                .commit();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        intent.putExtras(getIntent().getExtras());
        startActivity(intent);
    }

}
