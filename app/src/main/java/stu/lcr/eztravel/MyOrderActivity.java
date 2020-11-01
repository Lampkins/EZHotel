package stu.lcr.eztravel;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
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
import com.google.gson.reflect.TypeToken;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import stu.lcr.eztravel.adapter.OrderInfoAdapter;
import stu.lcr.eztravel.entity.OrderInfo;

public class MyOrderActivity extends AppCompatActivity {

    private static final String TAG = "MyOrderActivity";

    RecyclerView rv_order_list;
    TextView none_order;
    OrderInfoAdapter adapter;
    List<OrderInfo> orderList;

    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("我的订单");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        userID = intent.getIntExtra("userID", 0);
        if (userID != 0)
            volleyGet(userID);
        none_order = findViewById(R.id.none_order);
        orderList = new ArrayList<>();
        adapter = new OrderInfoAdapter(this, orderList);
        rv_order_list = findViewById(R.id.rv_order_list);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        rv_order_list.setLayoutManager(mLayoutManager);
        rv_order_list.setItemAnimator(new DefaultItemAnimator());
        rv_order_list.setAdapter(adapter);

        RefreshLayout order_refresh = findViewById(R.id.order_refresh);
        order_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                orderList.clear();
                volleyGet(userID);
                refreshLayout.finishRefresh();
            }
        });
    }

    private void volleyGet(Integer userID) {
        String url = Constants.basePath+"bookingInfos?userID="+userID;
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "request success");
                        try {
                            Gson gson = new Gson();
                            Log.d(TAG, "onResponse1: "+response.get("status"));
                            if (response.get("status").equals(200) && !response.get("message").equals("订单为空")) {
                                List<OrderInfo> list = gson.fromJson(response.get("data").toString(),
                                        new TypeToken<List<OrderInfo>>() {}.getType());
                                orderList.addAll(list);
                                adapter.notifyDataSetChanged();
                            }else
                                none_order.setVisibility(View.VISIBLE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MyOrderActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, error.toString());
                    }
                });
        mRequestQueue.add(mJsonObjectRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        orderList.clear();
        volleyGet(userID);
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
