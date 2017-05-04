package com.app.hexuegang.wsnmonitor.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.hexuegang.wsnmonitor.R;
import com.app.hexuegang.wsnmonitor.bean.Item1Bean;

import java.util.ArrayList;

/**
 * Created by HEXG on 2017/4/8.
 */

public class RecyclerView1Adapter extends RecyclerView.Adapter {

    public ArrayList<Item1Bean> datas = null;

    private enum ITEM_TYPE {
        ITEM_TYPE_ONE,
        ITEM_TYPE_TWO
    }

    public RecyclerView1Adapter(ArrayList<Item1Bean> datas) {
        this.datas = datas;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_ONE.ordinal()) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview1_img, viewGroup, false);
            return new ItemOneViewHolder(view);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_TWO.ordinal()) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview1_txt, viewGroup, false);
            return new ItemTwoViewHolder(view);
        } else
            return null;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemOneViewHolder) {
            ((ItemOneViewHolder) viewHolder).image.setImageResource(R.mipmap.ic_launcher);
            ((ItemOneViewHolder) viewHolder).img_txt.setText(datas.get(position).getTxtStr());
        } else if (viewHolder instanceof ItemTwoViewHolder) {
            ((ItemTwoViewHolder) viewHolder).title.setText(datas.get(position).getTxtStr());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getType()%2 == 0 ? ITEM_TYPE.ITEM_TYPE_ONE.ordinal() : ITEM_TYPE.ITEM_TYPE_TWO.ordinal();
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ItemOneViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView img_txt;

        public ItemOneViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.item_img);
            img_txt = (TextView) itemView.findViewById(R.id.img_txt);
        }
    }

    public class ItemTwoViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public ItemTwoViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_txt);
        }
    }
}
