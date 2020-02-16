package com.fairmentat.weather;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fairmentat.weather.data.WeatherData1;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Api mApi = Api.Instance.getApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button volgogradButton = findViewById(R.id.volgograd_button);
        volgogradButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mApi.getWeatherDataByCity("Volgograd", "340534cecc8b738d63106a1eee7beb3d", "metric")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<WeatherData1>() {
                            @Override
                            public void accept(WeatherData1 weatherData1) throws Exception {

                                //Toast.makeText(MapsActivity.this, weatherData1.getName() + " " + weatherData1.getMain().getTemp(), Toast.LENGTH_LONG).show();
                                LatLng latLng = new LatLng(weatherData1.getCoord().getLat(), weatherData1.getCoord().getLon());

                                mMap.addMarker(new MarkerOptions().position(latLng).title(weatherData1.getName() + ": " + weatherData1.getMain().getTemp())).showInfoWindow();
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));



                            }

                        });

            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);




    }
}
