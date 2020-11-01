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
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import stu.lcr.eztravel.entity.UserInfo;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        TextView tv_register = findViewById(R.id.tv_to_register);
        TextView tv_to_password = findViewById(R.id.tv_to_password);
        tv_to_password.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                EditText et_username = findViewById(R.id.et_username);
                EditText et_password = findViewById(R.id.et_password);
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                if (username.equals("") || password.equals(""))
                    Toast.makeText(this, "输入为空！", Toast.LENGTH_SHORT).show();
                else
                    volleyGet(username, password);
                break;
            case R.id.tv_to_register:
                Intent it1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(it1);
                break;
            case R.id.tv_to_password:
                Intent it2 = new Intent(LoginActivity.this, ChangePasswordActivity.class);
                startActivity(it2);
                break;
          }
    }

    private void volleyGet(final String username, final String password) {
        String url = Constants.basePath+"signIn?username="+username+"&password="+password;
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.get("status").equals(200)){
                                UserInfo userInfo = new Gson().fromJson(response.get("data").toString(), UserInfo.class);
                                final HotelApplication application = (HotelApplication) getApplication();
                                application.setUserInfo(userInfo);
                                Intent it_login = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(it_login);
                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            }else
                                Toast.makeText(LoginActivity.this, response.get("message").toString(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, error.toString());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("username", username);
                        params.put("password",password);
                        return params;
                    }
                };
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
