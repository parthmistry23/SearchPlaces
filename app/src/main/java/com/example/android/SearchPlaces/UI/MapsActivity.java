package com.example.android.SearchPlaces.UI;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.SearchPlaces.Adapter.HorizontalAdapter;
import com.example.android.SearchPlaces.Network.ApiClient;
import com.example.android.SearchPlaces.POJO.Circle;
import com.example.android.SearchPlaces.POJO.DataList;
import com.example.android.SearchPlaces.POJO.Example;
import com.example.android.SearchPlaces.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button button;
    EditText searchItem;
    FloatingActionButton fab, fab1, fab2;
    Animation fabOpen, fabClose, fabForward, fabBackward;
    boolean isOpen = false;
    private double latitude = 39.0;
    private double longitude = -76.89;
    RecyclerView recyclerView;
    HorizontalAdapter adapter;
    List<Circle> items = new ArrayList<>();
    List<DataList> dataLists = new ArrayList<>();
//    RealmConfiguration realmConfiguration;
//    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        items = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new HorizontalAdapter(MapsActivity.this, items);
        recyclerView.setAdapter(adapter);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);

        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);

        fabForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        fabBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
                Intent intent = new Intent(MapsActivity.this, DataListActivity.class);
                intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) dataLists);
                startActivity(intent);
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
            }
        });

        searchItem = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String item = searchItem.getText().toString();
                    if (item != null) {
                        build_retrofit(item);
                    }
                }
            });
        }
    }

    private void animateFab() {
        if (isOpen) {
            fab.startAnimation(fabForward);
            fab1.startAnimation(fabClose);
            fab2.startAnimation(fabClose);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isOpen = false;
        } else {
            fab.startAnimation(fabBackward);
            fab1.startAnimation(fabOpen);
            fab2.startAnimation(fabOpen);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isOpen = true;
        }

    }

    public void build_retrofit(String item) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiClient service = retrofit.create(ApiClient.class);
        //LatLng location = new LatLng(39.0,-76.89);

        Call<Example> call = service.getCityResults(item, latitude + "," + longitude);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Response<Example> response, Retrofit retrofit) {

                try {
                    mMap.clear();
                    for (int i = 0; i < response.body().getResults().size(); i++) {

                        Double lat = response.body().getResults().get(i).getGeometry().getLocation().getLat();
                        Double lng = response.body().getResults().get(i).getGeometry().getLocation().getLng();
                        String placeName = response.body().getResults().get(i).getName();
                        String placeAddress = response.body().getResults().get(i).getFormatted_address();
                        Double rating = response.body().getResults().get(i).getRating();
                        String icon = response.body().getResults().get(i).getIcon();
                        MarkerOptions markerOptions = new MarkerOptions();
                        items.add(new Circle(placeName, rating));
                        dataLists.add(new DataList(placeName, rating, icon, placeAddress));
                        LatLng latLng = new LatLng(lat, lng);
                        // Position of Marker on Map
                        markerOptions.position(latLng);
                        // Adding Title to the Marker
                        markerOptions.title(placeName + " : " + placeAddress);
                        // Adding Marker to the Camera.
                        Marker m = mMap.addMarker(markerOptions);
                        mMap.setInfoWindowAdapter(new InfoWindowAdapter());
                        // Adding colour to the marker
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                        // move map camera
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
                    }

                    adapter.notifyDataSetChanged();

                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (mMap != null) {
            mMap.setInfoWindowAdapter(new InfoWindowAdapter());
        }
    }

    private class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            View v = getLayoutInflater().inflate(R.layout.custom_infowindow, null);
            TextView pName = (TextView) v.findViewById(R.id.placeName);
            //TextView pAddress= (TextView)v.findViewById(R.id.placeAddress);
            TextView lat = (TextView) v.findViewById(R.id.latitude);
            TextView lng = (TextView) v.findViewById(R.id.longitude);

            LatLng ll = marker.getPosition();
            pName.setText(marker.getTitle());
            lat.setText("Latitude: " + ll.latitude);
            lng.setText("Longitude: " + ll.longitude);

            return v;
        }
    }
}
