package stu.lcr.eztravel.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import stu.lcr.eztravel.CommentActivity;
import stu.lcr.eztravel.Constants;
import stu.lcr.eztravel.HotelDetailActivity;
import stu.lcr.eztravel.R;
import stu.lcr.eztravel.entity.HotelInfo;
import stu.lcr.eztravel.entity.OrderInfo;

public class OrderInfoAdapter extends RecyclerView.Adapter<OrderInfoAdapter.MyViewHolder> {

    private Activity mActivity;
    private List<OrderInfo> orderList;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_hotel_name, tv_price, tv_address, tv_roomTypeName,
                tv_roomNum, tv_checkIn, tv_checkOut, tv_status;
        Button btn_rebook, btn_comment;

        MyViewHolder(View view) {
            super(view);
            tv_hotel_name = view.findViewById(R.id.tv_hotel_name);
            tv_price = view.findViewById(R.id.tv_price);
            tv_address = view.findViewById(R.id.tv_address);
            tv_roomTypeName = view.findViewById(R.id.tv_roomTypeName);
            tv_roomNum = view.findViewById(R.id.tv_roomNum);
            tv_checkIn = view.findViewById(R.id.tv_checkIn);
            tv_checkOut = view.findViewById(R.id.tv_checkOut);
            tv_status = view.findViewById(R.id.tv_status);
            btn_rebook = view.findViewById(R.id.btn_rebook);
            btn_comment = view.findViewById(R.id.btn_comment);
        }
    }

    public OrderInfoAdapter(Activity activity, List<OrderInfo> orderList) {
        this.mActivity = activity;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final OrderInfo orderInfo = orderList.get(position);
        holder.tv_hotel_name.setText(orderInfo.getHotelName());
        holder.tv_price.setText("￥" + orderInfo.getPrice());
        TextView tv_address = holder.tv_address;
        vGetHotel(orderInfo.getHotelID(), tv_address);
        holder.tv_roomTypeName.setText(String.valueOf(orderInfo.getRoomTypeName()));
        holder.tv_roomNum.setText(orderInfo.getRoomNum() + "间");
        holder.tv_checkIn.setText(orderInfo.getCheckIn());
        holder.tv_checkOut.setText(orderInfo.getCheckOut());
        if (orderInfo.getBookingStatus()==2)
            holder.tv_status.setText("待入住");
        else if (orderInfo.getBookingStatus()==3) {
            holder.tv_status.setText("待评价");
            holder.btn_comment.setVisibility(View.VISIBLE);
            holder.btn_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(mActivity, CommentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("orderInfo", orderInfo);
                    it.putExtras(bundle);
                    mActivity.startActivityForResult(it, 1);
                }
            });
        }
        else if (orderInfo.getBookingStatus()==4) {
            holder.tv_status.setText("已评价");
            holder.btn_comment.setVisibility(View.GONE);
        }
        holder.btn_rebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vGetHotel(orderInfo.getHotelID(), null);
            }
        });

    }

    private void vGetHotel(Integer hotelID, final TextView tv_address) {
        String url = Constants.basePath+"hotels/"+hotelID;
        RequestQueue mRequestQueue = Volley.newRequestQueue(mActivity);
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.get("status").equals(200)) {
                                HotelInfo hotelInfo = new Gson().fromJson(response.get("data").toString(), HotelInfo.class);
                                if (tv_address == null){
                                    Intent it = new Intent(mActivity, HotelDetailActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("hotelInfo", hotelInfo);
                                    it.putExtras(bundle);
                                    mActivity.startActivity(it);
                                }else
                                    tv_address.setText(hotelInfo.getAddress());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mActivity, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
        mRequestQueue.add(mJsonObjectRequest);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
