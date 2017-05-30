package com.app.hexuegang.wsnmonitor.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.hexuegang.wsnmonitor.R;
import com.app.hexuegang.wsnmonitor.bean.ParameterAirYear;
import com.app.hexuegang.wsnmonitor.publish.ITEM_TYPE;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by HEXG on 2017/4/20.
 */

public class RecyclerViewAirAdapter extends BaseRecyclerViewAdapter<ParameterAirYear> {


    public RecyclerViewAirAdapter(Context context, ArrayList<ParameterAirYear> datas) {
        super(context, datas);
    }

    @Override
    protected BaseRecyclerViewHolder getViewHolder(View view) {
        return new ItemWaterViewHolder(view);
    }

    @Override
    protected int setViewType() {
        return ITEM_TYPE.ITEM_TYPE_ONE.ordinal();
    }


    @Override
    protected int getItemLayoutId1() {
        return R.layout.item_recyclerview1_img;
    }

    public class ItemWaterViewHolder extends BaseRecyclerViewHolder{

        private ImageView image;
        private TextView img_txt;
        private TextView txt_time;
        public ItemWaterViewHolder(View view) {
            super(view);
            image = (ImageView) itemView.findViewById(R.id.item_img);
            img_txt = (TextView) itemView.findViewById(R.id.img_txt);
            txt_time = (TextView) itemView.findViewById(R.id.txt_time);
        }

        @Override
        public void bindView(final int position, final ParameterAirYear pAir) {
            super.bindView(position, pAir);
            if (pAir.getImage_url()  != null){
                Glide.with(context).load(pAir.getImage_url()).dontAnimate().placeholder(R.mipmap.icon_water).into(image);
            }else {
                image.setImageResource(R.mipmap.icon_water);
            }
            img_txt.setText(pAir.getAir_year_name());
            txt_time.setText(pAir.getUpdatedAt());
        }
    }
}
