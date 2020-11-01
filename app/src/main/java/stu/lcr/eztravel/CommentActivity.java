package stu.lcr.eztravel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import stu.lcr.eztravel.entity.OrderInfo;

public class CommentActivity extends AppCompatActivity {

    private static final String TAG = "CommentActivity";

    String username;
    OrderInfo orderInfo;
    TextView tv_back, tv_submit;
    RatingBar ratingBar;
    EditText et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            orderInfo = (OrderInfo) bundle.get("orderInfo");
        HotelApplication application = (HotelApplication) getApplication();
        username = application.getUserInfo().getUsername();

        init();
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(CommentActivity.this)
                        .setTitle("确认退出？")
                        .setMessage("退出后评价将不会被保存")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        }).show();
            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vPostComment();
            }
        });
    }

    private void init() {
        tv_back = findViewById(R.id.tv_back);
        tv_submit = findViewById(R.id.tv_submit);
        ratingBar = findViewById(R.id.ratingBar);
        et_content = findViewById(R.id.et_content);

        // 初始化订单信息
        TextView tmp = findViewById(R.id.tv_hotel_name);
        tmp.setText(orderInfo.getHotelName());
        tmp = findViewById(R.id.tv_price);
        tmp.setText("￥" + orderInfo.getPrice());
        tmp = findViewById(R.id.tv_roomTypeName);
        tmp.setText(String.valueOf(orderInfo.getRoomTypeName()));
        tmp = findViewById(R.id.tv_roomNum);
        tmp.setText(orderInfo.getRoomNum() + "间");
        tmp = findViewById(R.id.tv_checkIn);
        tmp.setText(orderInfo.getCheckIn());
        tmp = findViewById(R.id.tv_checkOut);
        tmp.setText(orderInfo.getCheckOut());
    }

    private void vPostComment() {
        String url = Constants.basePath+"comments";
        JSONObject json = new JSONObject();
        try {
            json.put("bookingID", orderInfo.getBookingID());
            json.put("hotelID", orderInfo.getHotelID());
            json.put("userID", orderInfo.getUserID());
            json.put("userName", username);
            json.put("roomTypeName", orderInfo.getRoomTypeName());
            json.put("score", ratingBar.getRating());
            json.put("content", et_content.getText().toString().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.get("status").equals(201)) {
                                Log.d(TAG, "onResponse: "+response.toString());
                                Toast.makeText(CommentActivity.this, "评价成功", Toast.LENGTH_SHORT).show();
                                Intent it = new Intent();
                                setResult(RESULT_OK, it);
                                finish();
                            }else
                                Toast.makeText(CommentActivity.this, "评价失败", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "response failure");
                        Toast.makeText(CommentActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
        mRequestQueue.add(mJsonObjectRequest);
    }
}
