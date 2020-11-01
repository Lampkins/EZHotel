package stu.lcr.eztravel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import stu.lcr.eztravel.R;
import stu.lcr.eztravel.entity.CommentInfo;

public class CommentInfoAdapter extends RecyclerView.Adapter<CommentInfoAdapter.MyViewHolder> {

    private List<CommentInfo> commentList;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_username, tv_content, tv_time, tv_room_type;
        RatingBar rb_rating;

        MyViewHolder(View view) {
            super(view);
            tv_username = view.findViewById(R.id.tv_username);
            tv_content = view.findViewById(R.id.tv_content);
            tv_time = view.findViewById(R.id.tv_time);
            tv_room_type = view.findViewById(R.id.tv_room_type);
            rb_rating = view.findViewById(R.id.rb_rating);
        }
    }

    public CommentInfoAdapter(List<CommentInfo> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hotel_comment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final CommentInfo commentInfo = commentList.get(position);
        holder.tv_username.setText(commentInfo.getUserName());
        holder.tv_content.setText(commentInfo.getContent());
        String time = commentInfo.getCreateTime();
        holder.tv_time.setText(time.split(" ")[0]);
        holder.tv_room_type.setText(commentInfo.getRoomTypeName());
        holder.rb_rating.setRating(commentInfo.getScore().floatValue());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }
}
