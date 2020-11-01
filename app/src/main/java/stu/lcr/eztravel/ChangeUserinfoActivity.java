package stu.lcr.eztravel;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import stu.lcr.eztravel.entity.UserInfo;

public class ChangeUserinfoActivity extends AppCompatActivity {

    private static final String TAG = "ChangeUserinfoActivity";

    HotelApplication application;
    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_userinfo);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("取消");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        application = (HotelApplication) getApplication();
        userInfo = application.getUserInfo();
        initUserInfo();
        Button btn_btn_finish = findViewById(R.id.btn_finish);
        btn_btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vPutUser();
            }
        });
    }

    private void initUserInfo() {
        EditText et = findViewById(R.id.et_username);
        String s = userInfo.getUsername();
        if (s!=null && !s.equals("null"))
            et.setText(s);
        et = findViewById(R.id.et_fullname);
        s = userInfo.getFullName();
        if (s!=null && !s.equals("null"))
            et.setText(s);
        et = findViewById(R.id.et_mail);
        s = userInfo.getMail();
        if (s!=null && !s.equals("null"))
            et.setText(s);
        et = findViewById(R.id.et_phone);
        s = userInfo.getPhone();
        if (s!=null && !s.equals("null"))
            et.setText(s);
        RadioGroup rg_gender = findViewById(R.id.rg_gender);
        rg_gender.clearCheck();
        RadioButton rb_gender = findViewById(R.id.rb_gender_male);
        if (userInfo.getGender().equals("女"))
            rb_gender = findViewById(R.id.rb_gender_female);
        else if (userInfo.getGender().equals("保密"))
            rb_gender = findViewById(R.id.rb_gender_secrecy);
        rb_gender.setChecked(true);
    }

    private void vPutUser(){
        String url = Constants.basePath+"users?";
        url += "userID="+userInfo.getUserID();
        EditText et = findViewById(R.id.et_username);
        url += "&username="+et.getText().toString();
        et = findViewById(R.id.et_fullname);
        url += "&fullName="+et.getText().toString();
        et = findViewById(R.id.et_mail);
        url += "&mail="+et.getText().toString();
        et = findViewById(R.id.et_phone);
        url += "&phone="+et.getText().toString();
        RadioGroup rg_gender = findViewById(R.id.rg_gender);
        RadioButton rb_gender = findViewById(rg_gender.getCheckedRadioButtonId());
        url += "&gender="+rb_gender.getText().toString();
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.get("status").toString().equals("201")){
                                UserInfo userInfo = new Gson().fromJson(response.get("data").toString(), UserInfo.class);
                                application.setUserInfo(userInfo);
                                Toast.makeText(ChangeUserinfoActivity.this, "完善成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }else
                                Toast.makeText(ChangeUserinfoActivity.this, response.get("message").toString(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "response failure");
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
