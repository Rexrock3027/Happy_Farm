package com.city2farmer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

//import androidx.fragment.app.Fragment;

public class H1201_F_Map extends Fragment implements OnMapReadyCallback {

    private View mMap;
    private GoogleMap gMap;
    private MapView mMapView;
    private FragmentManager mFragmentMgr;
    private H1201_F_Map mMap2;
    private H1201_F_Select mSelect;
    private TextView bottombg;
    private H1201 mMain;
    private double latitude, longitude;
    private String shopName, telPhone, address;
    private LatLng location;
    private H1301 h1301;
    private ListView lsitview;
    private LinearLayout listlnlay, lay02,  listlnlay1, listlnlay2;
    private TextView shop00, address00, phone00;
    private Button callphone00;
    private float down_x, down_y;
    private float move_x, move_y;
    private ImageView list_img;
    private Animation slid_up, slid_down;
    private Handler handler = new Handler();;
    private boolean currentItem;
    private BottomSheetBehavior mBottomSheet;
    private CoordinatorLayout bottonsheet;
    private NestedScrollView mnestedscroll;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mMap = inflater.inflate(R.layout.h1201_map, container, false);
//        mMap是H1201_F_Map
        mMapView = (MapView) mMap.findViewById(R.id.h1201_mapView);
//        lsitview = (ListView) mMap.findViewById(R.id.h1201_maplistView);
        listlnlay = (LinearLayout) mMap.findViewById(R.id.h1201_map_lnlay1);
//            Log.e("test", "viewHolder.listlnlay=>"+viewHolder.listlnlay);
        shop00 = (TextView) mMap.findViewById(R.id.h1201_list_t001);
        address00 = (TextView) mMap.findViewById(R.id.h1201_list_t002);
        phone00 = (TextView) mMap.findViewById(R.id.h1201_list_t003);
        callphone00 = (Button) mMap.findViewById(R.id.h1201_list_b001);
        listlnlay1 =(LinearLayout) mMap.findViewById(R.id.h1201_map_lnlay1);
        listlnlay2 =(LinearLayout) mMap.findViewById(R.id.h1201_map_lnlay2);
        list_img = (ImageView) mMap.findViewById(R.id.h1201_list_up);
        mnestedscroll = (NestedScrollView) mMap.findViewById(R.id.h1201_nest);
        mBottomSheet = BottomSheetBehavior.from(mnestedscroll);
//        getActivity()是H1201_main
        lay02 = (LinearLayout) getActivity().findViewById(R.id.h1201_lay02);
        bottombg = (TextView) getActivity().findViewById(R.id.h1201_bottombg);
        lay02.setVisibility(View.GONE);
        bottombg.setVisibility(View.GONE);

        listlnlay2.setVisibility(View.GONE);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);

        Bundle bundle = new Bundle();
        latitude = this.getArguments().getDouble("lat");
        longitude = this.getArguments().getDouble("lon");
        shopName = this.getArguments().getString("shop");
        address = this.getArguments().getString("addr");
        telPhone = this.getArguments().getString("phone");

        return mMap;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //================使用別人的APP================================
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        //======================================================
        super.onCreate(savedInstanceState);
//        一定要加setHasOptionsMenu()来讓fragment可以添加或認知item到menu
        setHasOptionsMenu(true);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        currentItem = false;

        mFragmentMgr = getFragmentManager();
        mMap2 = new H1201_F_Map();
        mSelect = new H1201_F_Select();
        mMain = new H1201();
        h1301 = new H1301();
        int d = 0;
        shop00.setText(shopName);
        address00.setText(address);
        phone00.setText(telPhone);

        slid_up = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_slide_up);
        slid_down = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_slide_down);

//        listlnlay1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(currentItem==false){
//                    currentItem = true;
//                    listlnlay.startAnimation(slid_up);
////                    listlnlay2.setVisibility(View.VISIBLE);
//                    list_img.setImageResource(R.drawable.ic_group_collapse_05);
//                }else if(currentItem==true){
//                    currentItem = false;
//                    listlnlay.startAnimation(slid_down);
//                    list_img.setImageResource(R.drawable.ic_group_expand_05);
//
////                    listlnlay2.startAnimation(slid_down);
////                           handler.postDelayed(new Runnable() {
////                                @Override
////                                public void run() {
////                                    listlnlay2.setVisibility(View.GONE);
////                                }
////                            },820);
//                }
//            }
//        });

        mBottomSheet.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState){
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        list_img.setImageResource(R.drawable.ic_group_expand_05);
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:

                        break;
                    case BottomSheetBehavior.STATE_SETTLING:

                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        list_img.setImageResource(R.drawable.ic_group_collapse_05);
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

//        listlnlay1.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        down_x = event.getX();
//                        down_y = event.getY();
//
//                        Log.e("test", "down_x=>"+move_x + "down_y=>"+ move_y);
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        move_x = event.getX() - down_x;
//                        move_y = event.getY() - down_y;
//
//                        if(move_y < -192.0 && currentItem==false){
//                            currentItem = true;
//                            listlnlay.startAnimation(slid_up);
//                            listlnlay2.setVisibility(View.VISIBLE);
//                            list_img.setImageResource(R.drawable.ic_group_collapse_05);
//                        }else if(move_y > 195 && currentItem == true){
//                            currentItem = false;
//                            listlnlay.startAnimation(slid_down);
//                            list_img.setImageResource(R.drawable.ic_group_expand_05);
//
////                    listlnlay2.startAnimation(slid_down);
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    listlnlay2.setVisibility(View.GONE);
//                                }
//                            },815);
//                        }
////                        y-195
//                        Log.e("test", "move_x=>"+move_x + "move_y=>"+ move_y);
//                        break;
//                    case MotionEvent.ACTION_UP:
//
//                        break;
//
//                }
//                return false;
//            }
//        });

        callphone00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri uri = Uri.parse("tel:"+telPhone);
                intent.setData(uri);
                startActivity(intent);
            }
        });

////        listdata();
//        ArrayList<HashMap<String, String>> datas = new ArrayList<HashMap<String,String>>();
//        HashMap<String, String> item = new HashMap<String, String>();
//        item.put("shop", shopName);
//        item.put("address", address);
//        item.put("phone", telPhone);
//
//        datas.add(item);
//
//        Log.e("test", "datas=>"+datas);
//        OneExpandAdapter adapter = new OneExpandAdapter(this.getActivity(), datas);
//        Log.e("test", "list=>"+lsitview);
//        lsitview.setAdapter(adapter);



    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

//        lat緯度 lng經度
        location = new LatLng(latitude,longitude);
        Marker gMap_mark = gMap.addMarker(new MarkerOptions().position(location).title(shopName).visible(true));
//        自動呼叫mark
        gMap_mark.showInfoWindow();

//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        int mapzoom = 15;
//        int mapzoom = 0;
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, mapzoom));
    }
    private void updateLocationUI() {
        if (gMap == null) {
            return;
        }
        Log.e("h1301.location=", String.valueOf(h1301.locationPermissionGranted));
        try {
            if (h1301.locationPermissionGranted) {
                gMap.setMyLocationEnabled(true);
                gMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                gMap.setMyLocationEnabled(false);
                gMap.getUiSettings().setMyLocationButtonEnabled(false);
//                lastKnownLocation = null;
                h1301.checkRequiredPermission(getActivity());

            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        mMapView.onLowMemory();
        super.onLowMemory();
    }

    //======================menu==============================
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
//                mFragmentMgr.beginTransaction()
//                        .replace(R.id.h1201_framelayout, mSelect)
//                     .commit();
//                }
                ((H1201)getActivity()).BackToFragment();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
