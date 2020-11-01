package stu.lcr.eztravel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yinglan.scrolllayout.ScrollLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import stu.lcr.eztravel.adapter.RoomTypeAdapter;
import stu.lcr.eztravel.entity.HotelInfo;
import stu.lcr.eztravel.entity.RoomType;

public class HotelDetailActivity extends AppCompatActivity {
    private static final String TAG = "HotelDetailActivity";

    Context mContext;
    RecyclerView rv_rt_list;
    RoomTypeAdapter adapter;
    ImageView iv_hd_img;
    TextView tv_hd_address, tv_hd_score, tv_hd_phone, tv_introduction, tv_show_comments;

    ScrollLayout sl_rt_detail;

    HotelInfo hotelInfo;
    List<RoomType> roomTypeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            hotelInfo = (HotelInfo) bundle.get("hotelInfo");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(hotelInfo.getHotelName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
        initHotelInfo();
        vGetRoomTypes(hotelInfo.getHotelID());

        tv_introduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maxLines = tv_introduction.getMaxLines();
                if (maxLines < 5)
                    tv_introduction.setMaxLines(50);
                else
                    tv_introduction.setMaxLines(3);
            }
        });
        tv_show_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotelDetailActivity.this, HotelCommentsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void init() {
        mContext = this;
        roomTypeList = new ArrayList<>();
        sl_rt_detail = findViewById(R.id.sl_rt_detail);
        final HotelApplication application = (HotelApplication) getApplication();
        adapter = new RoomTypeAdapter(this, hotelInfo.getHotelName(),
                application.getUserInfo()!=null, sl_rt_detail, roomTypeList);
        iv_hd_img = findViewById(R.id.iv_hd_img);
        tv_hd_address = findViewById(R.id.tv_hd_address);
        tv_hd_score = findViewById(R.id.tv_hd_score);
        tv_hd_phone = findViewById(R.id.tv_hd_phone);
        tv_introduction = findViewById(R.id.tv_introduction);
        tv_show_comments = findViewById(R.id.tv_show_comments);
        rv_rt_list = findViewById(R.id.rv_rt_list);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        rv_rt_list.setLayoutManager(mLayoutManager);
        rv_rt_list.setItemAnimator(new DefaultItemAnimator());
        rv_rt_list.setAdapter(adapter);
    }

    private void initHotelInfo() {
        String imgPath = Constants.basePath + "hotel_imgs/" + hotelInfo.getImgs();
        Glide.with(mContext).load(imgPath).into(iv_hd_img);
        Log.d(TAG, "initHotelInfo: " + hotelInfo.getHotelID()+hotelInfo.getHotelName());
        tv_hd_address.setText(hotelInfo.getAddress());
        tv_hd_score.setText(String.valueOf(hotelInfo.getScore()));
        tv_hd_phone.setText(hotelInfo.getPhone());
        tv_introduction.setText("　　"+hotelInfo.getIntroduction());
    }

    private void vGetRoomTypes(Integer hotelID) {
        String url = Constants.basePath+"roomTypes?hotelID="+hotelID;
        // 1 创建RequestQueue对象
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "request success");
                        try {
                            Gson gson = new Gson();
                            Log.d(TAG, "onResponse1: "+response.get("status"));
                            if (response.get("status").equals(200)) {
                                List<RoomType> list = gson.fromJson(response.get("data").toString(),
                                        new TypeToken<List<RoomType>>() {}.getType());
                                roomTypeList.addAll(list);
                                adapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, "网络错误", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, error.toString());
                    }
                });
        mRequestQueue.add(mJsonObjectRequest);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            this.finish();
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
