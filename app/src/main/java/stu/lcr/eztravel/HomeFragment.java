package stu.lcr.eztravel;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import stu.lcr.eztravel.adapter.HomeBannerAdapter;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeActivity";

    private TextView et_city;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // 首页轮播
        List<Integer> banner_list = new ArrayList<>();
        banner_list.add(R.drawable.banner1);
        banner_list.add(R.drawable.banner2);
        banner_list.add(R.drawable.banner3);
        Banner home_banner = getActivity().findViewById(R.id.home_banner);
        home_banner.addBannerLifecycleObserver(this) //添加生命周期观察者
                .setAdapter(new HomeBannerAdapter(banner_list))
                .setIndicator(new CircleIndicator(getActivity()))
                .start();

        View cardView_home = getActivity().findViewById(R.id.cardView_home);//找到你要设透明背景的layout 的id
        cardView_home.getBackground().setAlpha(210);

        et_city = getActivity().findViewById(R.id.et_city);
        final TextView et_hotel_name = getActivity().findViewById(R.id.et_hotel_name);
        final Spinner spinner_lowest_price = getActivity().findViewById(R.id.spinner_lowest_price);
        final Spinner spinner_highest_price = getActivity().findViewById(R.id.spinner_highest_price);
        Button btn_find = getActivity().findViewById(R.id.btn_find);
        et_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), CityPickerActivity.class);
                it.putExtra("hotelName", et_hotel_name.getText().toString());
                startActivityForResult(it, 1);
            }
        });
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), HotelSearchResultActivity.class);
                String city = et_city.getText().toString();
                String hotelName = et_hotel_name.getText().toString();
                String lowestPrice = spinner_lowest_price.getSelectedItem()+"";
                String highestPrice = spinner_highest_price.getSelectedItem()+"";
                Log.d(TAG, "onClick: "+city+hotelName+lowestPrice+" "+highestPrice);
                Bundle bundle = new Bundle();
                bundle.putString("city", city);
                bundle.putString("hotelName", hotelName);
                bundle.putString("lowestPrice", lowestPrice);
                bundle.putString("highestPrice", highestPrice);
                it.putExtras(bundle);
                startActivity(it);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == -1) {
                assert data != null;
                String city = data.getStringExtra("city");
                et_city.setText(city);
            }
        }
    }
}
