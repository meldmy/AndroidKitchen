package com.wroclaw.restoraunt.restoraunt.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.wroclaw.restoraunt.restoraunt.OrdersDAO;
import com.wroclaw.restoraunt.restoraunt.R;
import com.wroclaw.restoraunt.restoraunt.async.ReceiveOrderAction;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String DELETED_ORDER = "deletedOrder";
    private static OrdersDAO orders = new OrdersDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        if (b != null && getIntent().hasExtra(DELETED_ORDER)) {
            removeOrder(b);
        }

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addNavigationMenu(toolbar);


        addReceiveOrdersButton();
    }

    private boolean isVerticalOrientation() {
        View detailsFrame = findViewById(R.id.order_description);
        return detailsFrame == null || detailsFrame.getVisibility() != View.VISIBLE;
    }

    private void removeOrder(Bundle b) {
        removeOrder(b.getInt(DELETED_ORDER));
        getIntent().removeExtra(DELETED_ORDER);
        b.getStringArrayList("descriptions");
    }

    private void addReceiveOrdersButton() {
        retrieveReceiveOrdersButton()
                .setOnClickListener(new ReceiveOrderAction(this));
    }

    private View retrieveReceiveOrdersButton() {
        return isVerticalOrientation()
                ? findViewById(R.id.orderButton)
                : findViewById(R.id.receiveOrderButton);
    }

    private void addNavigationMenu(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        /*int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public List<String> getOrderNames() {
        return orders.getOrderNames();
    }

    public List<String> getDescriptions() {
        return orders.getDescriptions();
    }

    public void addOrder(String orderName, String description) {
        orders.addOrder(orderName, description);
    }

    public void removeOrder(int orderIndex) {
        orders.removeOrder(orderIndex);
    }
}
