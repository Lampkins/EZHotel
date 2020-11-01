package stu.lcr.eztravel;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
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

public class RecommendFragment extends Fragment {
    
    private static final String TAG = "RecommendActivity";

    private RecyclerView rv_recommend_list;
    private HotelInfoAdapter adapter;
    private List<HotelInfo> hotelList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommend,container,false);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        hotelList = new ArrayList<>();
        adapter = new HotelInfoAdapter(getActivity(), hotelList, R.layout.item_hotel_recommend);
        rv_recommend_list = getActivity().findViewById(R.id.rv_recommend_list);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        rv_recommend_list.setLayoutManager(mLayoutManager);
        rv_recommend_list.setItemAnimator(new DefaultItemAnimator());
        rv_recommend_list.setAdapter(adapter);
        rv_recommend_list.smoothScrollToPosition(0);
        volleyGet();

        RefreshLayout recommend_refresh = getActivity().findViewById(R.id.recommend_refresh);
        recommend_refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                volleyGet();
                refreshLayout.finishLoadMore(1000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                hotelList.clear();
                volleyGet();
                refreshLayout.finishRefresh(true);
            }
        });
    }

    private void volleyGet() {
        String url = Constants.basePath+"hotels?pageNum=0&pageSize=6&order=RAND()";
        // 1 创建RequestQueue对象
        RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "request success");
                        try {
                            Gson gson = new Gson();
                            Log.d(TAG, "onResponse1: "+response.get("status"));
                            JsonObject jsonObject = gson.fromJson(response.get("data").toString(), JsonObject.class);
                            List<HotelInfo> list = gson.fromJson(jsonObject.get("list").toString(), new TypeToken<List<HotelInfo>>() {}.getType());
                            hotelList.addAll(list);
                            adapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, error.toString());
                    }
                });
        mRequestQueue.add(mJsonObjectRequest);
    }
}
