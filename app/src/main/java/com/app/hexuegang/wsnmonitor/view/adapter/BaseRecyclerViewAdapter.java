package com.app.hexuegang.wsnmonitor.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.hexuegang.wsnmonitor.publish.ITEM_TYPE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by HEXG on 2017/4/8.
 */

public abstract class BaseRecyclerViewAdapter<T extends BmobObject> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseRecyclerViewHolder> {

    protected ArrayList<T> datas = null;
    protected MyItemClickListener mListener;
    protected Context context;

    protected abstract BaseRecyclerViewAdapter.BaseRecyclerViewHolder getViewHolder(View view);
    protected abstract int setViewType();
    protected abstract int getItemLayoutId1();

    protected BaseRecyclerViewAdapter(Context context, ArrayList<T> datas) {
        this.datas = datas;
        this.context = context;
    }

    /**
     * 根据不同的条目类型加载不同的布局
     *
     * @param viewGroup
     * @param viewType  条目类型
     * @return
     */
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = null;
        if (viewType == ITEM_TYPE.ITEM_TYPE_ONE.ordinal()) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(getItemLayoutId1(), viewGroup, false);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_TWO.ordinal()) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(getItemLayoutId2(), viewGroup, false);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(getItemLayoutId0(), viewGroup, false);
        }
        return getViewHolder(view);
    }

    protected int getItemLayoutId0() {
        return getItemLayoutId1();
    }

    protected int getItemLayoutId2() {
        return 0;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewAdapter.BaseRecyclerViewHolder holder, final int position) {
        final T t = datas.get(position);
        holder.bindView(position, t);
        //单击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(t, position);
                }
            }
        });

        //长按事件
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mListener != null) {
                    mListener.onItemLongClick(t, position);
                }
                return true;
            }
        });
    }

    /**
     * @param position
     * @return 根据position返回条目的类型
     */
    @Override
    public int getItemViewType(int position) {
//        return (position == 0 || position == 3 || position == 8) ? ITEM_TYPE.ITEM_TYPE_ONE.ordinal() : ITEM_TYPE.ITEM_TYPE_TWO.ordinal();
//        return ITEM_TYPE.ITEM_TYPE_ONE.ordinal();
        return setViewType();
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }


    public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
        public BaseRecyclerViewHolder(View view) {
            super(view);
        }

        public void bindView(final int position, final T t) {
        }
    }

    /**
     * RecyclerView的条目监听事件接口
     *
     * @param <T>
     */
    public interface MyItemClickListener<T> {
        void onItemClick(final T t, int position);

        void onItemLongClick(final T t, int position);
    }

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mListener = listener;
    }

    /**
     * 设置Item长按监听
     *
     * @param listener
     */
    public void setOnItemLongClickListener(MyItemClickListener listener) {
        this.mListener = listener;
    }

    public void addTop(List<T> list)
    {
        if (datas != null) {
            datas.clear();
            if (list != null && list.size() > 0) {
                datas.addAll((Collection<? extends T>) list);
            }
            notifyDataSetChanged();
        }
    }

    public void addBottom(List<T> list)
    {
        if(datas != null && list!=null && list.size()>0)
        {
            datas.addAll(list);
            notifyDataSetChanged();
        }
    }
    public void clear()
    {
        if (datas != null) {
            datas.clear();
            notifyDataSetChanged();
        }
    }
}
