package stu.lcr.eztravel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import stu.lcr.eztravel.adapter.CommentInfoAdapter;
import stu.lcr.eztravel.entity.CommentInfo;
import stu.lcr.eztravel.entity.HotelInfo;

public class HotelCommentsActivity extends AppCompatActivity {

    RefreshLayout comments_refresh;
    RecyclerView rv_comment_list;
    TextView none_comments;

    CommentInfoAdapter adapter;
    List<CommentInfo> commentList;
    HotelInfo hotelInfo;
    Integer nextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_comments);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            hotelInfo = (HotelInfo) bundle.get("hotelInfo");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle(hotelInfo.getHotelName());
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        init();
        vGetComments(hotelInfo.getHotelID(), 0);

        comments_refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (nextPage == 0)
                    Toast.makeText(HotelCommentsActivity.this, "已加载全部评论", Toast.LENGTH_SHORT).show();
                else
                    vGetComments(hotelInfo.getHotelID(), nextPage);
                refreshLayout.finishLoadMore(1000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                vGetComments(hotelInfo.getHotelID(), 0);
            }
        });
    }

    private void init() {
        comments_refresh = findViewById(R.id.comments_refresh);
        none_comments = findViewById(R.id.none_comments);
        commentList = new ArrayList<>();
        adapter = new CommentInfoAdapter(commentList);

        rv_comment_list = findViewById(R.id.rv_comment_list);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        rv_comment_list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv_comment_list.setLayoutManager(mLayoutManager);
        rv_comment_list.setAdapter(adapter);
    }

    private void vGetComments(Integer hotelID, Integer pageNum) {
        String url = Constants.basePath+"comments?pageNum="+pageNum+"&pageSize=10"+"&hotelID="+hotelID;
        // 1 创建RequestQueue对象
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Gson gson = new Gson();
                            JsonObject jsonObject = gson.fromJson(response.get("data").toString(), JsonObject.class);
                            if (response.get("status").equals(200) && !jsonObject.get("total").toString().equals("0")) {
                                nextPage = Integer.parseInt(jsonObject.get("nextPage").toString());

                                List<CommentInfo> list = gson.fromJson(jsonObject.get("list").toString(),
                                        new TypeToken<List<CommentInfo>>() {}.getType());
                                commentList.addAll(list);
                                adapter.notifyDataSetChanged();
                            }else
                                none_comments.setVisibility(View.VISIBLE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HotelCommentsActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
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
