package com.app.hexuegang.wsnmonitor.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.app.hexuegang.wsnmonitor.bean.ParameterNoise;
import com.app.hexuegang.wsnmonitor.publish.MyConstants;
import com.app.hexuegang.wsnmonitor.publish.MyMethods;
import com.app.hexuegang.wsnmonitor.view.adapter.RecyclerViewNoiseAdapter;
import com.app.hexuegang.wsnmonitor.view.fragment.activity.NoiseElementDetailActivity;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


/**
 * Created by HEXG on 2017/4/8.
 */

public class NoiseIndexFragment extends BaseIndexFragment<ParameterNoise> {

    private Date mDate = new Date();

    @Override
    protected void initAdapter() {
        adapter = new RecyclerViewNoiseAdapter(this.getActivity(), datas);
    }

    @Override
    protected void initUI() {
        setTitle("土壤环境的评价参数");
    }

    @Override
    public void getData(final int mode) {
        Log.i("LOG", "mDate in generateData >>> " + mDate);
        BmobQuery<ParameterNoise> waterBmobQuery = new BmobQuery<ParameterNoise>();
        waterBmobQuery.addWhereLessThanOrEqualTo("createdAt", new BmobDate(mDate));
        waterBmobQuery.order("-createdAt");  // 按时间降序排列
        waterBmobQuery.setLimit(MyConstants.QUERY_ITEM_LIMITS);  // 设定返回的查询条数
        // 设定查询缓存策略-CACHE_ELSE_NETWORK: 先从缓存读取数据, 如果没有, 再从网络获取.
        waterBmobQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
        waterBmobQuery.setMaxCacheAge(TimeUnit.DAYS.toMillis(7));    //此表示缓存一天
        waterBmobQuery.findObjects(new FindListener<ParameterNoise>() {
            @Override
            public void done(List<ParameterNoise> list, BmobException e) {
                if (e == null) {
                    if (!list.isEmpty()) {
                        adapter.addTop(list);
                        noDataTvVisibility(View.GONE);

                        ParameterNoise lastParameterWater = datas.get(datas.size() - 1);
                        lastParameterStr = lastParameterWater.getUpdatedAt();

                        MyMethods.noiseComputingMethod(list);

                    } else {
                        noDataTvSetText("暂无数据!");
                        noDataTvVisibility(View.VISIBLE);
                    }

                    progressBarVisible(ProgressBar.GONE);
                } else {
                    progressBarVisible(ProgressBar.GONE);
                    noDataTvSetText("加载数据出错");
                    noDataTvVisibility(View.VISIBLE);
                }

                if (mode == 1){
                    refreshComplete();
                }
            }
        });
    }

    @Override
    public void loadMoreData() {
        loadMoreComplete();
    }

    @Override
    public void onItemClick(ParameterNoise ParameterNoise, int position) {
        Intent intent = new Intent(NoiseIndexFragment.this.getActivity(), NoiseElementDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("noise_element", datas.get(position));
        intent.putExtras(bundle);
        NoiseIndexFragment.this.getActivity().startActivity(intent);
    }
}
