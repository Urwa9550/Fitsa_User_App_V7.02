package com.wasisoft.fitsa_user_app.Adaptors;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wasisoft.fitsa_user_app.Details_tr_nu;
import com.wasisoft.fitsa_user_app.Model.Exercise_items;
import com.wasisoft.fitsa_user_app.R;

import java.util.List;

public class Exercise_adaptor extends RecyclerView.Adapter<Exercise_adaptor.MyViewHolder> {

    private Context mContext;
    private List<Exercise_items> mExercise_items;

    public Exercise_adaptor(Context mContext, List<Exercise_items> mExercise_items) {
        this.mContext = mContext;
        this.mExercise_items = mExercise_items;
    }
/*i=position*/
    @NonNull
    @Override
    public Exercise_adaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.exercise_items,viewGroup, false);



       MyViewHolder viewHolder = new MyViewHolder(view);
         return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Exercise_adaptor.MyViewHolder myViewHolder, int i) {
        int imageID = mExercise_items.get(i).getmItemImages();
        String title = mExercise_items.get(i).getmItemName();
        String description = mExercise_items.get(i).getmItemDescription();
        Glide
                .with(mContext)
                .load(mContext.getDrawable(imageID)).into(myViewHolder.mImage);
                myViewHolder.mTitle.setText(title);
                myViewHolder.mDesc.setText(description);
    }

    @Override
    public int getItemCount() {
        return mExercise_items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mImage;
        private TextView mTitle;
        private TextView mDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.ex_title);
            mDesc = itemView.findViewById(R.id.ex_desc);
            mImage = itemView.findViewById(R.id.ex_imgv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, Details_tr_nu.class);
            mContext.startActivity(intent);
        }
    }

}
