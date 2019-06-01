package com.wasisoft.fitsa_user_app.Adaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wasisoft.fitsa_user_app.Model.Exercise_items;
import com.wasisoft.fitsa_user_app.Model.Nutritionist_items;
import com.wasisoft.fitsa_user_app.Model.Trainer_items;
import com.wasisoft.fitsa_user_app.R;

import java.util.List;

public class Trainer_adaptor extends RecyclerView.Adapter<Trainer_adaptor.MyViewHolder> {

private Context mContext;
private List<Trainer_items> mTrainer_items;

public Trainer_adaptor(Context mContext, List<Trainer_items> mTrainer_items) {
        this.mContext = mContext;
        this.mTrainer_items = mTrainer_items;
        }
/*i=position*/
@NonNull
@Override
public Trainer_adaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.exercise_items,viewGroup, false);



    Trainer_adaptor.MyViewHolder viewHolder = new Trainer_adaptor.MyViewHolder(view);
        return viewHolder;
        }

@Override
public void onBindViewHolder(@NonNull Trainer_adaptor.MyViewHolder myViewHolder, int i) {
        int imageID = mTrainer_items.get(i).getmItemImages();
        String title = mTrainer_items.get(i).getmItemName();

        Glide
        .with(mContext)
        .load(mContext.getDrawable(imageID)).into(myViewHolder.mImage);
        myViewHolder.mTitle.setText(title);

        }

@Override
public int getItemCount() {
        return mTrainer_items.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder{

    private ImageView mImage;
    private TextView mTitle;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.nut_title);

        mImage = itemView.findViewById(R.id.nut_imgv);


    }
}
}