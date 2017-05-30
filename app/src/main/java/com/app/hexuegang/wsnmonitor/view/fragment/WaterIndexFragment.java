package com.app.hexuegang.wsnmonitor.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.app.hexuegang.wsnmonitor.bean.ParameterWater;
import com.app.hexuegang.wsnmonitor.publish.MyConstants;
import com.app.hexuegang.wsnmonitor.publish.MyMethods;
import com.app.hexuegang.wsnmonitor.view.adapter.RecyclerViewWaterAdapter;
import com.app.hexuegang.wsnmonitor.view.fragment.activity.WaterElementDetailActivity;

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

public class WaterIndexFragment extends BaseIndexFragment<ParameterWater> {

    private Date mDate = new Date();

    @Override
    protected void initAdapter() {
        adapter = new RecyclerViewWaterAdapter(this.getActivity(), datas);
    }

    @Override
    protected void initUI() {
        setTitle("水环境的评价参数");
    }

    @Override
    public void getData(final int mode) {
        Log.i("LOG", "mDate in generateData >>> " + mDate);
        BmobQuery<ParameterWater> waterBmobQuery = new BmobQuery<ParameterWater>();
        waterBmobQuery.addWhereLessThanOrEqualTo("createdAt", new BmobDate(mDate));
        waterBmobQuery.order("-createdAt");  // 按时间降序排列
        waterBmobQuery.setLimit(MyConstants.QUERY_ITEM_LIMITS);  // 设定返回的查询条数
        // 设定查询缓存策略-CACHE_ELSE_NETWORK: 先从缓存读取数据, 如果没有, 再从网络获取.
        waterBmobQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
        waterBmobQuery.setMaxCacheAge(TimeUnit.DAYS.toMillis(7));    //此表示缓存一天
        waterBmobQuery.findObjects(new FindListener<ParameterWater>() {
            @Override
            public void done(List<ParameterWater> list, BmobException e) {
                if (e == null) {
                    if (!list.isEmpty()) {
                        adapter.addTop(list);
                        noDataTvVisibility(View.GONE);

                        ParameterWater lastParameterWater = datas.get(datas.size() - 1);
                        lastParameterStr = lastParameterWater.getUpdatedAt();

                        MyMethods.waterComputingMethod(list);

                    } else {
                        noDataTvSetText("暂无数据!");
                        noDataTvVisibility(View.VISIBLE);
                    }

                    progressBarVisible(ProgressBar.GONE);
                } else {
                    progressBarVisible(ProgressBar.GONE);
                    noDataTvSetText("加载数据出错");
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
//        Date newdate = DateUtil.string2Date(lastParameterStr);
//        BmobQuery<ParameterWater> bmobQuery = new BmobQuery<>();
//        bmobQuery.addWhereLessThanOrEqualTo("createdAt", new BmobDate(newdate));
//        bmobQuery.order("-createdAt");  // 按时间降序排列
//        bmobQuery.setLimit(MyConstants.QUERY_ITEM_LIMITS);  // 设定返回的查询条数
//        // 设定查询缓存策略-CACHE_ELSE_NETWORK: 先从网络读取数据, 如果没有, 再从缓存获取.
//        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
//        bmobQuery.setMaxCacheAge(TimeUnit.DAYS.toMillis(7));    //此表示缓存一天
//        bmobQuery.findObjects(new FindListener<ParameterWater>() {
//            @Override
//            public void done(List<ParameterWater> list, BmobException e) {
//                if (e == null) {
//                    if (list.size() == 0) {
//                        SystemUtils.showHandlerToast(getActivity(), "没有更多内容了...");
//                    } else {
//                        adapter.addBottom(list);
//
//                        // get the last item post time
//                        if (!list.isEmpty()) {
//                            ParameterWater lastPostInfo = list.get(list.size() - 1);
//                            lastParameterStr = lastPostInfo.getUpdatedAt();
//                        }
//                    }
//
//                    progressBarVisible(ProgressBar.GONE);
//                } else {
//                    progressBarVisible(ProgressBar.GONE);
//                }
//                loadMoreComplete();
//            }
//        });
    }

    @Override
    public void onItemClick(ParameterWater ParameterWater, int position) {
        Intent intent = new Intent(WaterIndexFragment.this.getActivity(), WaterElementDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("water_element", datas.get(position));
        intent.putExtras(bundle);
        WaterIndexFragment.this.getActivity().startActivity(intent);
    }


}
