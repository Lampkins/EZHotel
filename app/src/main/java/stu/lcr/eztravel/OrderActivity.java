package stu.lcr.eztravel;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import stu.lcr.eztravel.entity.RoomType;
import stu.lcr.eztravel.entity.UserInfo;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "OrderActivity";

    Calendar c = Calendar.getInstance();
    TextView tv_roomNum, tv_checkIn, tv_checkOut, tv_price;
    EditText et_fullName, et_phone;
    ImageView iv_sub, iv_add;
    Button btn_pay;

    UserInfo userInfo;
    RoomType roomType;
    String hotelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("订单");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        // 初始化组件
        iv_sub = findViewById(R.id.iv_sub);
        tv_roomNum = findViewById(R.id.tv_roomNum);
        iv_add = findViewById(R.id.iv_add);
        et_fullName = findViewById(R.id.et_fullName);
        et_phone = findViewById(R.id.et_phone);
        tv_checkIn = findViewById(R.id.tv_checkIn);
        tv_checkOut = findViewById(R.id.tv_checkOut);
        tv_price = findViewById(R.id.tv_price);
        btn_pay = findViewById(R.id.btn_pay);

        // 获取用户信息
        HotelApplication application = (HotelApplication) getApplication();
        userInfo = application.getUserInfo();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            roomType = (RoomType) bundle.get("roomType");
            hotelName = bundle.getString("hotelName");
        }

        // 初始化订单页面
        initOrder();

        iv_sub.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        tv_checkIn.setOnClickListener(this);
        tv_checkOut.setOnClickListener(this);
        btn_pay.setOnClickListener(this);

    }

    private void initOrder() {
        Log.d(TAG, "initOrder: "+userInfo.toString());
        TextView tv = findViewById(R.id.tv_hotelName);
        tv.setText(hotelName);
        tv = findViewById(R.id.tv_roomTypeName);
        tv.setText(roomType.getRoomTypeName());
        tv = findViewById(R.id.tv_price_of_day);
        tv.setText(roomType.getPrice()+"元");
        EditText et = findViewById(R.id.et_fullName);
        if (userInfo.getFullName()!=null && !userInfo.getFullName().equals("null"))
            et.setText(userInfo.getFullName());
        et = findViewById(R.id.et_phone);
        if (userInfo.getFullName()!=null && !userInfo.getPhone().equals("null"))
            et.setText(userInfo.getPhone());
        tv = findViewById(R.id.tv_price);
        tv.setText(String.valueOf(roomType.getPrice()));
    }

    @Override
    public void onClick(View v) {
        // 获取房间数量和两个日期
        int num = Integer.parseInt(tv_roomNum.getText().toString());
        // 点击事件
        if (v == iv_sub){
            if (num > 1) {
                tv_roomNum.setText(String.valueOf(num - 1));
                updTotal();
            }
        }else if (v == iv_add){
            tv_roomNum.setText(String.valueOf(num+1));
            updTotal();
        }else if (v == tv_checkIn){
              new DatePickerDialog(this,
                      new DatePickerDialog.OnDateSetListener() {
                          @SuppressLint("SetTextI18n")
                          @Override
                          public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                              tv_checkIn.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
                              updTotal();
                          }
                      },    // 由活动对象监听事件
                      c.get(Calendar.YEAR),   //由Calendar对象获取当前的公元年
                      c.get(Calendar.MONTH),  //获取当前月 (从 0 算起)
                      c.get(Calendar.DAY_OF_MONTH)) //获取当前日
                    .show();  //显示出来
        }else if (v == tv_checkOut){
            new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            tv_checkOut.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
                            updTotal();
                        }
                    },    // 由活动对象监听事件
                    c.get(Calendar.YEAR),   //由Calendar对象获取当前的公元年
                    c.get(Calendar.MONTH),  //获取当前月 (从 0 算起)
                    c.get(Calendar.DAY_OF_MONTH)) //获取当前日
                    .show();  //显示出来
        }else if (v == btn_pay){
            new AlertDialog.Builder(OrderActivity.this)
                    .setMessage("确认支付？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (!et_fullName.getText().toString().isEmpty() &&
                                    !et_phone.getText().toString().isEmpty() &&
                                    !tv_checkIn.getText().toString().isEmpty() &&
                                    !tv_checkOut.getText().toString().isEmpty()) {
                                if (et_phone.getText().toString().matches("^1[34578]\\d{5,8}$"))
                                    vPostOrder();
                                else
                                    Toast.makeText(OrderActivity.this, "无效的手机号！", Toast.LENGTH_SHORT).show();
                            }else
                                Toast.makeText(OrderActivity.this, "入住信息不完整！", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }

    }

    // 更新总价格
    private void updTotal() {
        int num = Integer.parseInt(tv_roomNum.getText().toString());
        long betweenDay = 1;
        String in = tv_checkIn.getText().toString();
        String out = tv_checkOut.getText().toString();
        if (!in.equals("") && !out.equals(""))
            betweenDay = DateUtil.between(DateUtil.parse(in, "yyyy/MM/dd"), DateUtil.parse(out, "yyyy/MM/dd"), DateUnit.DAY);
        Double p = roomType.getPrice() * num * betweenDay;
        tv_price.setText(String.valueOf(p));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            this.finish();
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private void vPostOrder(){
        String url = Constants.basePath+"bookingInfos";
        JSONObject json = new JSONObject();
        try {
            json.put("userID", userInfo.getUserID());
            json.put("hotelID", roomType.getHotelID());
            json.put("roomTypeID", roomType.getRoomTypeID());
            json.put("hotelName", hotelName);
            json.put("roomTypeName", roomType.getRoomTypeName());
            json.put("fullName", et_fullName.getText().toString());
            json.put("phone", et_phone.getText().toString());
            json.put("checkIn", tv_checkIn.getText().toString());
            json.put("checkOut", tv_checkOut.getText().toString());
            TextView et = findViewById(R.id.tv_roomNum);
            json.put("roomNum", Integer.parseInt(et.getText().toString()));
            TextView tv = findViewById(R.id.tv_price);
            json.put("price", Double.parseDouble(tv.getText().toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // 1 创建RequestQueue对象
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.get("status").equals(200)) {
                                Log.d(TAG, "onResponse: "+response.toString());
                                Toast.makeText(OrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                                Intent it = new Intent(OrderActivity.this, MyOrderActivity.class);
                                it.putExtra("userID", userInfo.getUserID());
                                startActivity(it);
                            }else
                                Toast.makeText(OrderActivity.this, "请完善订单信息", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "response failure");
                        Toast.makeText(OrderActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
        mRequestQueue.add(mJsonObjectRequest);
    }
}
