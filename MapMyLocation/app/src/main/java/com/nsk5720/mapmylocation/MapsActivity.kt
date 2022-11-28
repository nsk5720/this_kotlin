package com.nsk5720.mapmylocation

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nsk5720.mapmylocation.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var locationPermission: ActivityResultLauncher<Array<String>>

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locationPermission = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()) { result->
            if(result.all{it.value}){
                startProcess()
            } else{Toast.makeText(this,"권한 승인이 필요합니다.", Toast.LENGTH_LONG).show()}
        }

        locationPermission.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION)
        )
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        updateLocation()
    }

    @SuppressLint("MissingPermission")
    fun updateLocation() {
        val locationRequest = LocationRequest.create()
        locationRequest.run {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 1000
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult?.let {
                    for(location in it.locations) {
                        Log.d("Location", "${location.latitude} , ${location.longitude}")
                        setLastLocation(location)
                    }
                }
            }
        }
        //현재 위치를 검색하기 위해서 Fused LocationProviderClient를 생성해서 사용한다.
        // requestLocationUpdates()에 앞에서 생성한 2개와 함께 루퍼(스레드와 비슷) 정보를 넘겨준다.
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback,
            Looper.myLooper())
        //위 코드는 권한 처리가 필요한데 현재 코드에서는 확인할 수 없다.
        //따라서 메서드 상단에 해당 코드를 체크하지 않아도 된다는 의미로
        //@SuppressLint("MissingPermission") 애너테이션을 달아준다.
        //어노테이션은 사전적 의미로는 주석이라는 뜻을 가지고 있으며 컴퓨터 언어에선 컴파일러에게
        //정보를 제공하거나, 실행 시 특정 코드를 자동으로 생성해주도록 도와주는 역할을 하고 있습니다.
    }

    fun setLastLocation(lastLocation: Location) {
        val LATLNG = LatLng(lastLocation.latitude, lastLocation.longitude)

        val markerOptions = MarkerOptions()
            .position(LATLNG)
            .title("Here!")

        //카메라 위치를 현재 위치로 세팅하고 마커와 함께 지도에 반영한다.
        //마커를 지도에 반영하기 전에 mMap.p
        val cameraPosition = CameraPosition.Builder()
            .target(LATLNG)
            .zoom(15.0f)
            .build()

        mMap.clear()
        mMap.addMarker(markerOptions)
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    fun startProcess(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
}