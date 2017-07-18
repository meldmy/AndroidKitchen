package com.wroclaw.restoraunt.restoraunt.async;

import android.support.design.widget.Snackbar;
import android.view.View;
import com.wroclaw.restoraunt.restoraunt.activity.MainActivity;

/**
 * @author Dmytro Melnychuk
 */
public class ReceiveOrderAction implements View.OnClickListener {

    private static final String RESTAURANT_STATUS = "Receiving restaurant status";
    private MainActivity activity;

    public ReceiveOrderAction(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        new ReceiveOrder(activity).execute();
        Snackbar.make(view, RESTAURANT_STATUS, Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
    }
}
