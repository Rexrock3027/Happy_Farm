package com.city2farmer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

//import androidx.fragment.app.Fragment;

public class H1201_F_Select extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String DB_File = "happyfarm.db", DB_TABLE = "HA1200";
    private SQLiteDatabase myHappyfarmDB;
    private HappyfarmDB hf12;
    private int DBversion = HappyfarmDB.VERSION;
    private ImageButton t013;
    private Intent intent = new Intent();
    private Spinner s001, s002;
    private String counties, asdt;
    private TextView tv, t014;
    private ProgressBar p001;
    private Handler handler = new Handler();
    private LocationManager manager;
    private ArrayList<Map<String, Object>> mLsit;
    private ListView listview;
    private Uri uri;
    private Intent it = new Intent();
    private CardView cardview;
    private String[] spinner1 = null;
    private String TABLE_l0103 = "l0103";
    private int bb;
    private ArrayList<Map<String, Object>> spinner2;
    private Geocoder geocoder;
    private String bestgps;
    private Location currentLocation;
    // 更新位置頻率的條件
    int minTime = 120000; // 毫秒
    float minDistance = 5; // 公尺
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 101;
    private TextView total;
    View mSelect;
    private H1201_F_Map mMap;
    private H1201_F_Select mselect;
    private FragmentManager mFragmentMgr;
    private LinearLayout lay02;
    private TextView bottombg;
    private H1201 mMain;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //================使用別人的APP================================
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        //======================================================
        super.onCreate(savedInstanceState);
        //一定要加setHasOptionsMenu()来讓fragment可以添加或認知item到menu
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mSelect = inflater.inflate(R.layout.h1201, container, false);

        lay02 = (LinearLayout) getActivity().findViewById(R.id.h1201_lay02);
        bottombg = (TextView) getActivity().findViewById(R.id.h1201_bottombg);

        lay02.setVisibility(View.VISIBLE);
        bottombg.setVisibility(View.VISIBLE);
//        setupViewCompoent();
        return mSelect;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        hf12 = new HappyfarmDB(getActivity(), DB_File, null, DBversion);
        s001 = (Spinner) mSelect.findViewById(R.id.h1201_s001);
        s002 = (Spinner) mSelect.findViewById(R.id.h1201_s002);
        p001 = (ProgressBar) mSelect.findViewById(R.id.h1201_p001);
        listview = (ListView) mSelect.findViewById(R.id.h1201_list01);
        total = mSelect.findViewById(R.id.h1201_total);
        mMap = new H1201_F_Map();
        mselect = new H1201_F_Select();
        mMain = new H1201();

// 建立Geocoder物件
        geocoder = new Geocoder(getActivity(), Locale.TAIWAN);


//        縣市spinner
        List<String> spinner1 = hf12.getAll_l0103();
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.h1201_counties, android.R.layout.simple_spinner_item);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinner1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s001.setAdapter(adapter);
//        縣市spinner監聽
        s001.setOnItemSelectedListener(this);

//        行政區spinner監聽
        s002.setOnItemSelectedListener(s002ON);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        counties = parent.getSelectedItem().toString();
        List<String> spinner2 = hf12.getAll_l0104(counties);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinner2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s002.setAdapter(adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private AdapterView.OnItemSelectedListener s002ON = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            asdt = parent.getSelectedItem().toString();
//            ArrayList<Map<String, Object>>
            spinner2 = hf12.getAll_l0105(counties, asdt);

            SimpleAdapter adapter = new SimpleAdapter(
                    getActivity(),
                    spinner2,
                    R.layout.h1201_cardview,
                    new String[]{"shopname", "address", "telphone"},
                    new int[]{R.id.h1201_shopname, R.id.h1201_address, R.id.h1201_telphone});

            listview.setAdapter(adapter);
            listview.setOnItemClickListener(listON);
            int size = spinner2.size();

//            total.setTextColor(Color.rgb(252, 146, 12));
           String str_size = Integer.toString(size);
//-----------------------------文字顏色-------------------------------
            SpannableStringBuilder sp_size = new SpannableStringBuilder(str_size);
            sp_size.setSpan(new AbsoluteSizeSpan(60),0, str_size.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            sp_size.setSpan(new ForegroundColorSpan(Color.rgb(252, 118, 12)),0, str_size.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            sp_size.setSpan(new StyleSpan(Typeface.BOLD),0, str_size.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            sp_size.insert(0, getString(R.string.h1201_total01));
            sp_size.append(getString(R.string.h1201_total02));

           total.setText(sp_size);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private AdapterView.OnItemClickListener listON = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Map<String, Object> mm = spinner2.get(position);
            String shopName = mm.get("shopname").toString();
            String addressName = mm.get("address").toString();
            String telPhone = mm.get("telphone").toString();
            // 將地址轉換成經緯度座標
            try {
                // 取得經緯度座標清單的List物件
                List<Address> listGPSAddress = geocoder.getFromLocationName(addressName, 1);
// 有找到經緯度座標
                if (listGPSAddress != null) {
                    double latitude = listGPSAddress.get(0).getLatitude();
                    double longitude = listGPSAddress.get(0).getLongitude();
//                    String uri = String.format("geo:%f,%f?z=18", latitude, longitude);
//// 建立Intent物件
//                    Intent geoMap = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//                    startActivity(geoMap); // 啟動活動


                    Bundle bundle = new Bundle();
                    bundle.putDouble("lat", latitude);
                    bundle.putDouble("lon", longitude);
                    bundle.putString("shop", shopName);
                    bundle.putString("addr", addressName);
                    bundle.putString("phone", telPhone);

                    mselect.setArguments(bundle);
                    mFragmentMgr = getFragmentManager();
                    ((H1201)getActivity()).setMap(bundle);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
//            mFragmentMgr = getFragmentManager();
//            mFragmentMgr.beginTransaction()
////                    .setCustomAnimations(R.animator.fade_in, R.animator.fade_out)
//                    .replace(R.id.h1201_framelayout, mMap)
////                    .addToBackStack(null)
//                    .commit();
//            if(mMap.isAdded()){
//                mFragmentMgr.beginTransaction()
////                   .setCustomAnimations(R.animator.fade_in, R.animator.fade_out)
//                        .show(mMap)
//                        .hide(mselect)
//                        .addToBackStack(null)
//                        .commit();
//                Toast.makeText(getActivity(),"show",Toast.LENGTH_SHORT).show();
//            }else{
//                mFragmentMgr.beginTransaction()
////                    .setCustomAnimations(R.animator.fade_in, R.animator.fade_out)
//                        .add(R.id.h1201_framelayout, mMap)
//                        .show(mMap)
//                        .hide(mselect)
//                        .addToBackStack(null)
//                        .commit();
//                Toast.makeText(getActivity(),"add",Toast.LENGTH_SHORT).show();
//            }

//            ((H1201_main)getActivity()).setMap();
        }
    };

    private LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            currentLocation = location;
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    public void onStart() {
//        mselect.onStart();
        super.onStart();
    }

    @Override
    public void onPause() {
//        mselect.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
//        mselect.onResume();
        super.onResume();
    }

    @Override
    public void onStop() {
//        mselect.onStop();
        super.onStop();
    }

    @Override
    public void onDestroy() {
//        mselect.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                getActivity().finish();
//                Toast.makeText(getActivity(),"test",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
//    private void setupViewCompoent() {
//        hf12 = new HappyfarmDB(getActivity(), DB_File, null, DBversion);
//        s001 = (Spinner) mSelect.findViewById(R.id.h1201_s001);
//        s002 = (Spinner) mSelect.findViewById(R.id.h1201_s002);
//        p001 = (ProgressBar) mSelect.findViewById(R.id.h1201_p001);
//        listview = (ListView) mSelect.findViewById(R.id.h1201_list01);
//        total = mSelect.findViewById(R.id.h1201_total);
//        mMap = new H1201_F_Map();
//        mselect = new H1201_F_Select();
//        mMain = new H1201();
//
//// 建立Geocoder物件
//        geocoder = new Geocoder(getActivity(), Locale.TAIWAN);
////        loading執行2秒
////        handler.postDelayed(loading, 1000);
//
////-----------json-----------------------------
////        try {
////            // myHappyfarmDB.delete(DB_TABLE,null,null);
////            String Task_opendata
////                    = new TransTask().execute("https://data.coa.gov.tw/Service/OpenData/DataFileService.aspx?UnitId=104").get();
////
////            List<Map<String, Object>> jsonList;
////            jsonList = new ArrayList<Map<String, Object>>();
////
////            JSONArray jsonArray = new JSONArray(Task_opendata);
////
////            for (int i = 0; i < jsonArray.length(); i++) {
////                JSONObject jsonObject = jsonArray.getJSONObject(i);
////                Map<String, Object> item = new HashMap<String, Object>();
////
////                String company = jsonObject.getString("公司名稱");
////                String counties = jsonObject.getString("縣市");
////                String asdt = jsonObject.getString("行政區");
////                String address = jsonObject.getString("地址");
////                String phone = jsonObject.getString("電話");
////
////                ContentValues newrow = new ContentValues();
////                newrow.put("l0102", company);
////                newrow.put("l0103", counties);
////                newrow.put("l0104", asdt);
////                newrow.put("l0105", address);
////                newrow.put("l0106", phone);
////                myHappyfarmDB.insert(DB_TABLE, null, newrow);
////            }
////
////        } catch (JSONException e) {
////            e.printStackTrace();
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        } catch (ExecutionException e) {
////            e.printStackTrace();
////        }
////        縣市spinner
//        List<String> spinner1 = hf12.getAll_l0103();
//        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.h1201_counties, android.R.layout.simple_spinner_item);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinner1);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        s001.setAdapter(adapter);
//        s001.setOnItemSelectedListener(this);
//
////        行政區spinner的監聽
//        s002.setOnItemSelectedListener(s002ON);
//    }
//
//    private Runnable loading = new Runnable() {
//        @Override
//        public void run() {
//            try {
////                時間到後ProgressBar隱藏
//                p001.setVisibility(View.GONE);
//                listview.setVisibility(View.VISIBLE);
//            } catch (Exception e) {
//
//            }
//        }
//    };

}