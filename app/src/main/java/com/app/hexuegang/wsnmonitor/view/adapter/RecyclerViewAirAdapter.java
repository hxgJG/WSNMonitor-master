package com.app.hexuegang.wsnmonitor.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.hexuegang.wsnmonitor.R;
import com.app.hexuegang.wsnmonitor.bean.parameter_air_year;
import com.app.hexuegang.wsnmonitor.bean.parameter_air_year;

import java.util.ArrayList;

/**
 * Created by HEXG on 2017/4/8.
 */

public class RecyclerViewAirAdapter extends RecyclerView.Adapter {

    public ArrayList<parameter_air_year> datas = null;
    private MyItemClickListener mListener;

    private enum ITEM_TYPE {
        ITEM_TYPE_ONE,
        ITEM_TYPE_TWO
    }

    public RecyclerViewAirAdapter(ArrayList<parameter_air_year> datas) {
        this.datas = datas;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_ONE.ordinal()) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview1_img, viewGroup, false);
            return new ItemOneViewHolder(view, mListener);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_TWO.ordinal()) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview1_txt, viewGroup, false);
            return new ItemTwoViewHolder(view, mListener);
        } else
            return null;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemOneViewHolder) {
            ((ItemOneViewHolder) viewHolder).image.setImageResource(getResId());
            ((ItemOneViewHolder) viewHolder).img_txt.setText(datas.get(position).getAir_element_name());
            ((ItemOneViewHolder) viewHolder).txt_time.setText(datas.get(position).getUpdatedAt());
        } else if (viewHolder instanceof ItemTwoViewHolder) {
            ((ItemTwoViewHolder) viewHolder).title.setText(datas.get(position).getAir_element_name());
        }
    }

    private int getResId(){
        int resId = 0;
        int r = (int) (Math.random()*100+1);
        if(r%2 == 0)
        {
            resId = R.mipmap.icon_air;
        }
        else {
            resId = R.mipmap.icon_air;
        }
        return resId;
    }

    @Override
    public int getItemViewType(int position) {
//        return (position == 0 || position == 3 || position == 8) ? ITEM_TYPE.ITEM_TYPE_ONE.ordinal() : ITEM_TYPE.ITEM_TYPE_TWO.ordinal();
        return ITEM_TYPE.ITEM_TYPE_ONE.ordinal();
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ItemOneViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView img_txt;
        TextView txt_time;
        private MyItemClickListener mListener;

        public ItemOneViewHolder(View itemView, MyItemClickListener mListener) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.item_img);
            img_txt = (TextView) itemView.findViewById(R.id.img_txt);
            txt_time = (TextView) itemView.findViewById(R.id.txt_time);
            itemView.setOnClickListener(this);
            this.mListener = mListener;
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onItemClick(v,getPosition()-1);
            }
        }
    }

    public class ItemTwoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        private MyItemClickListener mListener;

        public ItemTwoViewHolder(View itemView, MyItemClickListener mListener) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_txt);
            itemView.setOnClickListener(this);
            this.mListener = mListener;
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onItemClick(v,getPosition()-1);
            }
        }
    }

    public interface MyItemClickListener {
        public void onItemClick(View view, int position);
    }

    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mListener = listener;
    }
}
