package stu.lcr.eztravel;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;

import java.util.ArrayList;
import java.util.List;

public class CityPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_picker);
        initCity();
    }

    private void initCity(){
        List<HotCity> hotCities = new ArrayList<>();
        hotCities.add(new HotCity("北京", "北京", "101010100"));
        hotCities.add(new HotCity("上海", "上海", "101020100"));
        hotCities.add(new HotCity("成都", "四川", "101270101"));

        CityPicker.from(this) //activity或者fragment
                .enableAnimation(true)	//启用动画效果，默认无
                .setLocatedCity(null)
                .setHotCities(hotCities)	//指定热门城市
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City data) {
                        Intent it = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("city", data.getName());
                        it.putExtras(bundle);
                        setResult(RESULT_OK, it);
                        finish();
                    }

                    @Override
                    public void onLocate() {

                    }

                    @Override
                    public void onCancel(){
                        finish();
                    }
                })
                .show();
    }
}
