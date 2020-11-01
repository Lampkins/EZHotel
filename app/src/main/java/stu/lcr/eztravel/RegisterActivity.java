package stu.lcr.eztravel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";

    private ProgressBar progressBar;
//    private EditText et_username;
//    private EditText et_password;
//    private EditText et_confirm_password;
//    private EditText et_phone;
//    private RadioButton gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Button btn_register = findViewById(R.id.btn_register);
        TextView tv_to_login = findViewById(R.id.tv_to_login);
        btn_register.setOnClickListener(this);
        tv_to_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                EditText et_phone = findViewById(R.id.et_phone);
                if (et_phone.getText().toString().matches("^1[34578]\\d{5,8}$")){
                    vPostRegister();
                }else
                    Toast.makeText(RegisterActivity.this, "手机格式错误", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_to_login:
                finish();
                break;
        }
    }

    private void vPostRegister() {
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        String url = Constants.basePath+"register";
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        // 放入注册信息
        StringRequest mStringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            response = new String(response.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.get("status").equals(201)) {
                                Log.d(TAG, "register success");
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(RegisterActivity.this, "注册成功，返回登录界面!", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(RegisterActivity.this, jsonObject.get("message").toString(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "register failure");
                Log.d(TAG, error.toString());
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(RegisterActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // 放入注册信息
                EditText et_username = findViewById(R.id.et_username);
                EditText et_password = findViewById(R.id.et_password);
                EditText et_phone = findViewById(R.id.et_phone);
                RadioGroup rg_gender = findViewById(R.id.rg_gender);
                RadioButton rb_gender = findViewById(rg_gender.getCheckedRadioButtonId());

                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                String phone = et_phone.getText().toString();
                String gender = rb_gender.getText().toString();

                params.put("username", username);
                params.put("password", password);
                params.put("phone", phone);
                params.put("gender", gender);
                return params;
            }
        };
        mRequestQueue.add(mStringRequest);
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
