package stu.lcr.eztravel.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.yinglan.scrolllayout.ScrollLayout;

import java.util.List;

import stu.lcr.eztravel.Constants;
import stu.lcr.eztravel.OrderActivity;
import stu.lcr.eztravel.R;
import stu.lcr.eztravel.entity.RoomType;

public class RoomTypeAdapter extends RecyclerView.Adapter<RoomTypeAdapter.MyViewHolder> {

    private Context mContext;
    private String hotelName;
    private Boolean isLogin;
    private ScrollLayout sl_rt_detail;
    private List<RoomType> roomTypeList;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_rt_name, tv_rt_detail, tv_rt_area, tv_rt_bedtype, tv_rt_maxperson, tv_rt_price;
        ImageView iv_rt_img;
        Button btn_rt_order;

        MyViewHolder(View view) {
            super(view);
            iv_rt_img = view.findViewById(R.id.iv_rt_img);
            tv_rt_name = view.findViewById(R.id.tv_rt_name);
            tv_rt_detail = view.findViewById(R.id.tv_rt_detail);
            tv_rt_area = view.findViewById(R.id.tv_rt_area);
            tv_rt_bedtype = view.findViewById(R.id.tv_rt_bedtype);
            tv_rt_maxperson = view.findViewById(R.id.tv_rt_maxperson);
            tv_rt_price = view.findViewById(R.id.tv_rt_price);
            btn_rt_order = view.findViewById(R.id.btn_rt_order);
        }
    }

    public RoomTypeAdapter(Context mContext, String hotelName, Boolean isLogin,
                           ScrollLayout sl_rt_detail, List<RoomType> roomTypeList) {
        this.mContext = mContext;
        this.hotelName = hotelName;
        this.isLogin = isLogin;
        this.roomTypeList = roomTypeList;
        this.sl_rt_detail = sl_rt_detail;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_room_type, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final RoomType roomType = roomTypeList.get(position);
        String imgPath = Constants.basePath + "room_type_imgs/" + roomType.getImgs();
        Glide.with(mContext).load(imgPath).into(holder.iv_rt_img);
        holder.tv_rt_name.setText(roomType.getRoomTypeName());
        holder.tv_rt_area.setText(roomType.getArea());
        holder.tv_rt_bedtype.setText(roomType.getBedType());
        holder.tv_rt_maxperson.setText(String.valueOf(roomType.getMaxPerson()));
        holder.tv_rt_price.setText(String.valueOf(roomType.getPrice()));
        onClickToDetail(holder.tv_rt_name, roomType);
        onClickToDetail(holder.tv_rt_detail, roomType);

        holder.btn_rt_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin){
                    Intent it = new Intent(mContext, OrderActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("hotelName", hotelName);
                    bundle.putSerializable("roomType", roomType);
                    it.putExtras(bundle);
                    mContext.startActivity(it);
                }else {
                    Toast.makeText(mContext, "未登录！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onClickToDetail(TextView tv, final RoomType roomType){
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显示房型详情
                sl_rt_detail.setMaxOffset(sl_rt_detail.getHeight());
                CoordinatorLayout.LayoutParams lp = new CoordinatorLayout.LayoutParams(sl_rt_detail.getLayoutParams());
                lp.setMargins(0, sl_rt_detail.getScreenHeight()-sl_rt_detail.getHeight(), 0, 0);
                sl_rt_detail.setLayoutParams(lp);
                sl_rt_detail.scrollToOpen();
                ImageView iv_rt_img2 = sl_rt_detail.findViewById(R.id.iv_rt_img);
                String imgPath = Constants.basePath + "room_type_imgs/" + roomType.getImgs();
                Glide.with(mContext).load(imgPath).into(iv_rt_img2);
                TextView textView = sl_rt_detail.findViewById(R.id.tv_rt_name);
                textView.setText(roomType.getRoomTypeName());
                textView = sl_rt_detail.findViewById(R.id.tv_rt_area);
                textView.setText(roomType.getArea());
                textView = sl_rt_detail.findViewById(R.id.tv_rt_maxperson);
                textView.setText(String.valueOf(roomType.getMaxPerson()));
                textView = sl_rt_detail.findViewById(R.id.tv_rt_bedtype);
                textView.setText(roomType.getBedType());
                textView = sl_rt_detail.findViewById(R.id.tv_rt_floor);
                textView.setText(roomType.getFloor());
                String other = roomType.getOther();
                JsonObject other_json = new Gson().fromJson(other, JsonObject.class);
                textView = sl_rt_detail.findViewById(R.id.tv_rt_facility);
                JsonArray jsonArray = other_json.get("客房设施").getAsJsonArray();
                StringBuilder s = new StringBuilder();
                for (int i=0; i<jsonArray.size(); i++) {
                    JsonElement j = jsonArray.get(i);
                    s.append(j.toString().replace("\"", ""));
                    if (i != (jsonArray.size()-1))
                        s.append("，");
                }
                textView.setText(s.toString());
                textView = sl_rt_detail.findViewById(R.id.tv_rt_shower);
                jsonArray = other_json.get("浴室").getAsJsonArray();
                s = new StringBuilder();
                for (int i=0; i<jsonArray.size(); i++) {
                    JsonElement j = jsonArray.get(i);
                    s.append(j.toString().replace("\"", ""));
                    if (i != (jsonArray.size()-1))
                        s.append("，");
                }
                textView.setText(s.toString());
                textView = sl_rt_detail.findViewById(R.id.tv_rt_fd);
                jsonArray = other_json.get("食品饮品").getAsJsonArray();
                s = new StringBuilder();
                for (int i=0; i<jsonArray.size(); i++) {
                    JsonElement j = jsonArray.get(i);
                    s.append(j.toString().replace("\"", ""));
                    if (i != (jsonArray.size()-1))
                        s.append("，");
                }
                textView.setText(s.toString());
                textView = sl_rt_detail.findViewById(R.id.tv_rt_media);
                jsonArray = other_json.get("媒体科技").getAsJsonArray();
                s = new StringBuilder();
                for (int i=0; i<jsonArray.size(); i++) {
                    JsonElement j = jsonArray.get(i);
                    s.append(j.toString().replace("\"", ""));
                    if (i != (jsonArray.size()-1))
                        s.append("，");
                }
                textView.setText(s.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return roomTypeList.size();
    }
}
