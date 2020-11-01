package stu.lcr.eztravel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import stu.lcr.eztravel.adapter.HotelInfoAdapter;
import stu.lcr.eztravel.entity.HotelInfo;

public class HotelSearchResultActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "HotelSearchResult";

    RecyclerView rv_search_list;
    EditText et_sh_city;
    TextView et_sh_name, none_result;
    Button btn_search;
    HotelInfoAdapter adapter;
    List<HotelInfo> hotelList;
    Integer nextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_search_result);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("搜索结果");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        init();

        // 填充搜索信息
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            et_sh_city.setText(bundle.getString("city"));
            et_sh_name.setText(bundle.getString("hotelName"));
            volleyGet(bundle, 0);
        }
        btn_search.setOnClickListener(this);
        et_sh_city.setOnClickListener(this);

        // 刷新与加载更多
        RefreshLayout search_refresh = findViewById(R.id.search_refresh);
        search_refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (nextPage == 0)
                    Toast.makeText(HotelSearchResultActivity.this, "已加载全部", Toast.LENGTH_SHORT).show();
                else
                    volleyGet(bundle, nextPage);
                refreshLayout.finishLoadMore(1000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                hotelList.clear();
                volleyGet(bundle, 0);
                refreshLayout.finishRefresh();
            }
        });
    }

    private void init() {
        hotelList = new ArrayList<>();
        adapter = new HotelInfoAdapter(this, hotelList, R.layout.item_hotel_search);
        et_sh_city = findViewById(R.id.et_sh_city);
        et_sh_name = findViewById(R.id.et_sh_name);
        btn_search = findViewById(R.id.btn_search);
        none_result = findViewById(R.id.none_result);

        rv_search_list = findViewById(R.id.rv_search_list);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        rv_search_list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv_search_list.setLayoutManager(mLayoutManager);
        rv_search_list.setAdapter(adapter);
    }

    private void volleyGet(Bundle bundle, Integer pageNum) {
        String url = Constants.basePath+"hotels?pageNum="+pageNum+"&pageSize=5";
        if (bundle.getString("city")!=null)
            url += "&city="+ bundle.getString("city");
        if (bundle.getString("hotelName")!=null)
            url += "&hotelName="+ bundle.getString("hotelName");
        if (bundle.getString("lowestPrice")!=null)
            url += "&startingPrice="+ bundle.getString("lowestPrice");
        if (bundle.getString("highestPrice")!=null)
            url += "&highestPrice="+ bundle.getString("highestPrice");
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
                                JsonObject jsonObject = gson.fromJson(response.get("data").toString(), JsonObject.class);
                                nextPage = Integer.parseInt(jsonObject.get("nextPage").toString());
                                List<HotelInfo> list = gson.fromJson(jsonObject.get("list").toString(),
                                        new TypeToken<List<HotelInfo>>() {}.getType());
                                hotelList.addAll(list);
                                adapter.notifyDataSetChanged();
                            }else
                                none_result.setVisibility(View.VISIBLE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HotelSearchResultActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, error.toString());
                    }
                });
        mRequestQueue.add(mJsonObjectRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                none_result.setVisibility(View.GONE);
                hotelList.clear();
                Bundle bundle = new Bundle();
                bundle.putString("city", et_sh_city.getText().toString());
                bundle.putString("hotelName", et_sh_name.getText().toString());
                volleyGet(bundle, 0);
                break;
            case R.id.et_sh_city:
                Intent it = new Intent(HotelSearchResultActivity.this, CityPickerActivity.class);
                it.putExtra("hotelName", et_sh_name.getText().toString());
                startActivityForResult(it, 2);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                String city = data.getStringExtra("city");
                et_sh_city.setText(city);
            }
        }
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
