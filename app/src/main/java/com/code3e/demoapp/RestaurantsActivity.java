package com.code3e.demoapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.code3e.demoapp.adapters.RestaurantListViewAdapter;
import com.code3e.demoapp.models.Restaurante;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private Location location;
    private List<Restaurante> listaRestaurantes;

    private RestaurantListViewAdapter listViewAdapter;
    private ListView restaurantesListView;

    private final String TAG="RestaurantsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listaRestaurantes = new ArrayList<Restaurante>();

        setContentView(R.layout.activity_restaurants);

        restaurantesListView = (ListView)findViewById(R.id.restaurantsListView);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if(location == null) {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        if(location != null) {
            Log.d(TAG, "onCreate: "+ location.getLatitude()+", "+location.getLongitude());
            downloadInfo(location);
        } else {
            Log.w(TAG, "onCreate: Location no disponible");
            Toast.makeText(this,"Ubicación no disponible",Toast.LENGTH_SHORT);
        }

        restaurantesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RestaurantsActivity.this, RestaurantDetailActivity.class);
                intent.putExtra("restaurantId",listaRestaurantes.get(position).getId());

                startActivity(intent);
            }
        });
    }

    private void downloadInfo(Location loc) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://api.sindelantal.mx/1/places/search.json?latitude="+loc.getLatitude()+"&longitude="+loc.getLongitude()+"&accuracy=0";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d(TAG, "onResponse: "+response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray results = jsonObject.getJSONArray("results");

                            for(int i = 0; i < results.length(); i++){
                                JSONObject object = results.getJSONObject(i);
                                Restaurante restaurante = new Restaurante(object);
                                listaRestaurantes.add(restaurante);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        listViewAdapter = new RestaurantListViewAdapter(RestaurantsActivity.this, R.layout.restaurant_layout, listaRestaurantes);
                        restaurantesListView.setAdapter(listViewAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: ", error);
                Toast.makeText(getBaseContext(),"No ha sido posible contactar a sindelantal: "+error.getMessage(),Toast.LENGTH_SHORT);
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}
