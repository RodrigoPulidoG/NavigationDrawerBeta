package com.example.rodpro.navigationdrawer;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView tvCoordenadas,tvCoordenadasL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        tvCoordenadas = findViewById(R.id.tv_Coordenadas);
        tvCoordenadasL = findViewById(R.id.tv_CoordenadasL);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        putMarker();

        // Add a marker in COSMIC and move the camera
        LatLng cosmic = new LatLng(4.713402, -74.065246);
        mMap.addMarker(new MarkerOptions().position(cosmic).title("Marker in COSMIC"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cosmic, 14));


        // Quitar marker con un click
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getApplicationContext(), "marker removed", Toast.LENGTH_SHORT).show();
                marker.remove();
                return false;
            }
        });

        // Agregar marcador con click sostenido
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                tvCoordenadasL.setText(latLng.toString());
                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_name))
                        .anchor(0.5f, 1.5f)
                        .position(latLng));
                saveMarkerPreferences(latLng);
                getPositionMarker(latLng);


            }
        });



    }

    private void getPositionMarker(LatLng latLng) {
        tvCoordenadas.setText(latLng.toString());

    }

    private void saveMarkerPreferences(LatLng coordenadas) {
        SharedPreferences preferences = getSharedPreferences("Posicion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        double lat = coordenadas.latitude;
        double lng = coordenadas.longitude;

        float latitud = (float) lat;
        float longitud = (float) lng;

        editor.putFloat("markerlat",latitud);
        editor.putFloat("markerlng",longitud);
        editor.commit();

    }

    private void putMarker() {
        SharedPreferences preferences = getSharedPreferences("Posicion", Context.MODE_PRIVATE);
        Float lati = preferences.getFloat("markerlat",0);
        Float lngi = preferences.getFloat("markerlng",0);
        LatLng posicion = new LatLng(lati,lngi);
        tvCoordenadas.setText(posicion.toString());

        //mMap.addMarker(new MarkerOptions().position(posicion).title("Marker Saved"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_name))
                .anchor(0.5f, 1.5f).position(posicion));

    }


}
