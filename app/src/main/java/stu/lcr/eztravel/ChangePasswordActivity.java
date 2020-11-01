package stu.lcr.eztravel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import stu.lcr.eztravel.entity.UserInfo;

public class ChangePasswordActivity extends AppCompatActivity {

    private static final String TAG = "ChangePasswordActivity";
    EditText et_old_password;
    EditText et_new_password;
    EditText et_check_password;
    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("取消");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        et_old_password = findViewById(R.id.editText1);
        et_new_password = findViewById(R.id.editText2);
        et_check_password = findViewById(R.id.editText3);
        userInfo = (UserInfo) getIntent().getExtras().get("userInfo");
        Button btn_change_password = findViewById(R.id.btn_change_password);
        btn_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_new_password.getText().toString().equals(et_check_password.getText().toString()))
                    vPutUser();
                else
                    Toast.makeText(ChangePasswordActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            this.finish();
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private void vPutUser(){
        JSONObject result = new JSONObject();
        String url = Constants.basePath+"users?";
        url += "userID="+userInfo.getUserID();
        url += "&password="+et_old_password.getText().toString();
        url += "&newPassword="+et_new_password.getText().toString();
        Log.d(TAG, String.valueOf(userInfo.getUserID()));
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.get("status").toString().equals("201")){
                                Intent it = new Intent(ChangePasswordActivity.this,LoginActivity.class);
                                startActivity(it);
                                Toast.makeText(ChangePasswordActivity.this, "修改成功，返回登录", Toast.LENGTH_SHORT).show();
                            }else
                                Toast.makeText(ChangePasswordActivity.this, response.get("message").toString(), Toast.LENGTH_SHORT).show();
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
}
