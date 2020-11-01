package stu.lcr.eztravel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import stu.lcr.eztravel.entity.UserInfo;


public class MineFragment extends Fragment {

    private static final String TAG = "MineFragment";

    private UserInfo userInfo;
    private HotelApplication application;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        application = (HotelApplication) getActivity().getApplication();
        userInfo = application.getUserInfo();
        TextView tv_nickname = getActivity().findViewById(R.id.tv_nickname);
        TextView textView11 = getActivity().findViewById(R.id.textView11);
        TextView tv_order = getActivity().findViewById(R.id.tv_order);
        TextView tv_logout = getActivity().findViewById(R.id.tv_logout);
        TextView tv_change_password = getActivity().findViewById(R.id.tv_change_password);
        TextView tv_change_userinfo = getActivity().findViewById(R.id.tv_change_personal_message);
        if (userInfo != null) {// 已登录
            tv_nickname.setText(userInfo.getUsername());
            textView11.setText(userInfo.getMemberLevel());
            tv_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(getActivity(), MyOrderActivity.class);
                    it.putExtra("userID", userInfo.getUserID());
                    it.putExtra("username", userInfo.getUsername());
                    startActivity(it);
                }
            });
            tv_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder bdr = new AlertDialog.Builder(getActivity()); //创建 Builder 对象
                    bdr.setCancelable(false) // 禁用返回键关闭对话框
                            .setIcon(android.R.drawable.ic_menu_edit) // 采用内建的图标
                            .setTitle("确定退出登录吗？") // 设置对话框的标题
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    application.setUserInfo(null);
                                    Log.d(TAG, "onClick: logout");
                                    onStart();
                                }
                            })  // 加入肯定按钮
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }) // 加入否定
                            .show(); // 显示对话框
                }
            });
            tv_change_password.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it_change_password = new Intent(getActivity(), ChangePasswordActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("userInfo", userInfo);
                    it_change_password.putExtras(bundle);
                    startActivity(it_change_password);
                }
            });
            tv_change_userinfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(getActivity(), ChangeUserinfoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("userInfo", userInfo);
                    it.putExtras(bundle);
                    startActivity(it);
                }
            });
        }else{// 未登录
            tv_nickname.setText("登录/注册");
            textView11.setText("非会员");
            tv_nickname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            });
            tv_order.setOnClickListener(null);
            tv_logout.setOnClickListener(null);
            tv_change_password.setOnClickListener(null);
            tv_change_userinfo.setOnClickListener(null);
        }
    }


}
