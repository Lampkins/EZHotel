package stu.lcr.eztravel.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import stu.lcr.eztravel.Constants;
import stu.lcr.eztravel.HotelDetailActivity;
import stu.lcr.eztravel.R;
import stu.lcr.eztravel.entity.HotelInfo;

public class HotelInfoAdapter extends RecyclerView.Adapter<HotelInfoAdapter.MyViewHolder> {

    private Context mContext;
    private List<HotelInfo> hotelList;
    private int item;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_sh_name, tv_sh_score, tv_sh_address, tv_sh_startingPrice, tv_city;
        ImageView iv_sh_img;
        RatingBar rating_grade;

        MyViewHolder(View view) {
            super(view);
            iv_sh_img = view.findViewById(R.id.iv_sh_img);
            tv_sh_name = view.findViewById(R.id.tv_sh_name);
            tv_sh_score = view.findViewById(R.id.tv_sh_score);
            rating_grade = view.findViewById(R.id.rating_grade);
            tv_sh_address = view.findViewById(R.id.tv_sh_address);
            tv_sh_startingPrice = view.findViewById(R.id.tv_sh_startingPrice);
            tv_city = view.findViewById(R.id.tv_city);
        }
    }

    public HotelInfoAdapter(Context mContext, List<HotelInfo> hotelList, int item) {
        this.mContext = mContext;
        this.hotelList = hotelList;
        this.item = item;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final HotelInfo hotelInfo = hotelList.get(position);
        String imgPath = Constants.basePath + "hotel_imgs/" + hotelInfo.getImgs();
//        Glide.with(mContext).load(imgPath).error(R.drawable.logo2).into(holder.iv_sh_img);
        Glide.with(mContext).load(imgPath).into(holder.iv_sh_img);
        holder.tv_sh_name.setText(hotelInfo.getHotelName());
        holder.tv_sh_score.setText(String.valueOf(hotelInfo.getScore()));
        holder.rating_grade.setRating(hotelInfo.getScore().floatValue());
        holder.tv_sh_address.setText(hotelInfo.getAddress());
        holder.tv_sh_startingPrice.setText(String.valueOf(hotelInfo.getStartingPrice()));
        holder.tv_city.setText(hotelInfo.getCity());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(mContext, HotelDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("hotelInfo", hotelInfo);
                it.putExtras(bundle);
                mContext.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }
}
