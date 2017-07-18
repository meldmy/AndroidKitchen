package com.wroclaw.restoraunt.restoraunt.async;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.wroclaw.restoraunt.restoraunt.activity.MainActivity;
import com.wroclaw.restoraunt.restoraunt.entity.RestaurantStatus;
import com.wroclaw.restoraunt.restoraunt.json.RestaurantStatusCreator;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Dmytro Melnychuk
 */
public class ReceiveOrder extends AsyncTask<String, Void, RestaurantStatus> {

    private final HttpClient httpClient;
    private MainActivity activity;

    public ReceiveOrder(MainActivity activity) {
        this.activity = activity;
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
        httpClient = new DefaultHttpClient(httpParams);
    }

    @Override
    protected RestaurantStatus doInBackground(String... strings) {
        return receiveOrder();
    }

    private RestaurantStatus receiveOrder() {
        try {
            return tryToReceiveOrder();
        } catch (Exception e) {
            Log.d(getClass().getSimpleName(), "Some problem appear with receiving order");
        }
        return null;
    }

    private RestaurantStatus tryToReceiveOrder() throws IOException {
//        return invokeGet("http://192.168.43.251:8080/restaurantServer/service/order/full/1276");
        return invokeGet("http://10.0.2.2:8080/");

    }

    public RestaurantStatus invokeGet(String path) throws IOException {
        String originalResult = invoke(new HttpGet(path));
        return new RestaurantStatusCreator(originalResult).create();

    }

    protected String invoke(HttpRequestBase request) throws IOException {
        HttpResponse response = httpClient.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        if (!isOk(response, statusCode)) {
            throw new IOException();
        }
        return readContent(response);
    }

    private String readContent(HttpResponse response) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            response.getEntity().writeTo(out);
            return out.toString();
        } finally {
            out.close();
        }
    }

    private boolean isOk(HttpResponse response, int statusCode) throws IOException {
        if (statusCode != HttpStatus.SC_OK) {
            response.getEntity().consumeContent();
            return false;
        }
        return true;
    }

    @Override
    protected void onPreExecute() {
        showToast("Receiving restaurant status");
    }

    @Override
    protected void onPostExecute(RestaurantStatus restaurantStatus) {
        String orderName = new OrderNameCreator(restaurantStatus).create();
        String description = new OrderDescriptionCreator(restaurantStatus).create();
        activity.addOrder(orderName, description);

        startMainActivity();
    }

    private void startMainActivity() {
        activity.finish();
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    private void showToast(String message) {
        Toast.makeText(
                activity.getApplicationContext(),
                message,
                Toast.LENGTH_SHORT).show();
    }

}
