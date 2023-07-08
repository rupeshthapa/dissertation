package com.example.driversdoor;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class home extends AppCompatActivity {
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // Initialize the osmdroid configuration
        Configuration.getInstance().setUserAgentValue(getPackageName());
        Configuration.getInstance().setUserAgentValue("OSMDroid");

        // Get the MapView from the layout
        mapView = findViewById(R.id.mapView);

        // Set the tile source to Mapnik (OpenStreetMap)
        mapView.setTileSource(TileSourceFactory.MAPNIK);

        // Set the initial map position and zoom level
        mapView.getController().setCenter(new GeoPoint(0, 0)); // Set your desired initial position
        mapView.getController().setZoom(10); // Set your desired initial zoom level
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume(); // Call the onResume() method of the MapView
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause(); // Call the onPause() method of the MapView
    }
}
