package com.app.hexuegang.wsnmonitor.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.hexuegang.wsnmonitor.R;
import com.app.hexuegang.wsnmonitor.bean.parameter_water;
import com.app.hexuegang.wsnmonitor.util.DateUtil;
import com.app.hexuegang.wsnmonitor.util.SystemUtils;
import com.app.hexuegang.wsnmonitor.view.adapter.RecyclerViewWaterAdapter;
import com.app.hexuegang.wsnmonitor.view.fragment.activity.WaterElementDetailActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
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

public class WaterIndexFragment extends BaseFragment implements RecyclerViewWaterAdapter.MyItemClickListener {

    private final static int QUERY_ITEM_LIMITS = 15;     // 查询结果条目限制个数
    private static final int REQUEST_CODE_POST_1 = 1;

    private RelativeLayout rl_1_root;
    private XRecyclerView xrecyclerview_1;
    private TextView mNoDataTv;
    private ProgressBar loading_prbar;
    private RecyclerViewWaterAdapter adapter;
    private ArrayList<parameter_water> mList = new ArrayList<>();
    private Date mDate = new Date();
    private String lastParameterWaterStr;
    private boolean isConnected;
    private Context context = WaterIndexFragment.this.getContext();

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_water_index;
    }

    @Override
    public void init(View view) {
        rl_1_root = (RelativeLayout) view.findViewById(R.id.rl_1_root);
        xrecyclerview_1 = (XRecyclerView) view.findViewById(R.id.xrecyclerview_1);
        mNoDataTv = (TextView) view.findViewById(R.id.mNoDataTv);
        loading_prbar = (ProgressBar) view.findViewById(R.id.loading_prbar);
        isConnected = SystemUtils.checkNetworkConnection(getActivity());

        initRecyclerView();
        getData();

        doListener();
    }

    private void doListener() {
        xrecyclerview_1.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //refresh data here
                refreshData();
            }

            @Override
            public void onLoadMore() {
                // load more data here
                loadMoreData();
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrecyclerview_1.setLayoutManager(layoutManager);

    }

    private void getData(final int type){
        if (isConnected) {
            mNoDataTv.setVisibility(View.GONE);
        } else {
            SystemUtils.noNetworkAlert(getActivity());
            loading_prbar.setVisibility(ProgressBar.GONE);
            return;
        }

        Log.i("LOG", "mDate in generateData >>> " + mDate);
        BmobQuery<parameter_water> waterBmobQuery = new BmobQuery<parameter_water>();
        waterBmobQuery.addWhereLessThanOrEqualTo("createdAt", new BmobDate(mDate));
        waterBmobQuery.order("-createdAt");  // 按时间降序排列
        waterBmobQuery.setLimit(QUERY_ITEM_LIMITS);  // 设定返回的查询条数
        // 设定查询缓存策略-CACHE_ELSE_NETWORK: 先从缓存读取数据, 如果没有, 再从网络获取.
        waterBmobQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
        waterBmobQuery.setMaxCacheAge(TimeUnit.DAYS.toMillis(7));    //此表示缓存一天
        waterBmobQuery.findObjects(new FindListener<parameter_water>() {
            @Override
            public void done(List<parameter_water> list, BmobException e) {
                if (e == null) {

                    mList.clear();

                    for (parameter_water parameterWater : list) {
                        mList.add(parameterWater);
                    }
                    // get the last item post time
                    if (!mList.isEmpty()) {
                        adapter = new RecyclerViewWaterAdapter(mList);
                        xrecyclerview_1.setAdapter(adapter);
                        mNoDataTv.setVisibility(View.GONE);
                        adapter.setOnItemClickListener(WaterIndexFragment.this);

                        parameter_water lastParameterWater = mList.get(mList.size() - 1);
                        lastParameterWaterStr = lastParameterWater.getUpdatedAt();
                        Log.i("LOG", "lastParameterWater.getUserTimeStr() in generateData " +
                                lastParameterWaterStr);
                    } else {
                        mNoDataTv.setText("暂无数据!");
                        mNoDataTv.setVisibility(View.VISIBLE);
                    }

                    loading_prbar.setVisibility(ProgressBar.GONE);
                } else {
                    loading_prbar.setVisibility(ProgressBar.GONE);
                    mNoDataTv.setText("加载数据出错");
                    mNoDataTv.setVisibility(View.VISIBLE);
                }

                if (type == 1){
                    xrecyclerview_1.refreshComplete();
                }
            }
        });
    }

    private void getData() {
        getData(0);
    }

    /**
     * 下拉刷新
     */
    private void refreshData(){
        getData(1);
    }

    /**
     * 加载更多
     */
    private void loadMoreData(){
        Date newdate = DateUtil.string2Date(lastParameterWaterStr);
        BmobQuery<parameter_water> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereLessThanOrEqualTo("createdAt", new BmobDate(newdate));
        bmobQuery.order("-createdAt");  // 按时间降序排列
        bmobQuery.setLimit(QUERY_ITEM_LIMITS);  // 设定返回的查询条数
        // 设定查询缓存策略-CACHE_ELSE_NETWORK: 先从网络读取数据, 如果没有, 再从缓存获取.
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
        bmobQuery.setMaxCacheAge(TimeUnit.DAYS.toMillis(7));    //此表示缓存一天
        bmobQuery.findObjects(new FindListener<parameter_water>() {
            @Override
            public void done(List<parameter_water> list, BmobException e) {
                if (e == null) {
                    if (list.size() == 0) {
                        SystemUtils.showHandlerToast(getActivity(), "没有更多内容了...");
                        Log.i("LOG", "list.size() in generateLoadMoreData >>> " + list.size());
                    } else {
                        for (parameter_water comUserPostInfo : list) {
                            mList.add(comUserPostInfo);
                        }

                        // 监听数据的变化, 上拉加载更多后处于当前可视的最后一个item位置
                        adapter.notifyDataSetChanged();
                        adapter.setOnItemClickListener(WaterIndexFragment.this);

                        // get the last item post time
                        if (!mList.isEmpty()) {
                            parameter_water lastPostInfo = mList.get(mList.size() - 1);
                            lastParameterWaterStr = lastPostInfo.getUpdatedAt();
                        }
                    }

                    loading_prbar.setVisibility(ProgressBar.GONE);
                } else {
                    loading_prbar.setVisibility(ProgressBar.GONE);
                }
                xrecyclerview_1.loadMoreComplete();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == -1) {
            parameter_water postData = (parameter_water) data.getSerializableExtra("newPostData");
            Log.d("SecondFragment", "postData >>> " + postData);

            switch (requestCode) {
                case REQUEST_CODE_POST_1:
                    mNoDataTv.setVisibility(View.GONE);

                    if (mList.isEmpty()) {
                        mList.add(postData);
                        adapter = new RecyclerViewWaterAdapter(mList);
                        xrecyclerview_1.setAdapter(adapter);
                        adapter.setOnItemClickListener(WaterIndexFragment.this);

                        // 如果是第一条数据，初始化所有
                        parameter_water lastParameterWater = mList.get(mList.size() - 1);
                        lastParameterWaterStr = lastParameterWater.getUpdatedAt();
                    } else {
                        if (mList.isEmpty()){
                            mList = new ArrayList<>();
                        }
                        mList.add(0, postData);
                        adapter.notifyDataSetChanged();
                        adapter.setOnItemClickListener(WaterIndexFragment.this);
                    }

                    break;
            }
        }
    }

    /**
     * 当联网后加载数据
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isConnected=SystemUtils.checkNetworkConnection(context);
        if (isConnected && mList.isEmpty()){
            getData();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(WaterIndexFragment.this.getActivity(), WaterElementDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("water_element", mList.get(position));
        intent.putExtras(bundle);
        WaterIndexFragment.this.getActivity().startActivity(intent);
    }
}
