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
import com.app.hexuegang.wsnmonitor.bean.parameter_air_year;
import com.app.hexuegang.wsnmonitor.util.DateUtil;
import com.app.hexuegang.wsnmonitor.util.SystemUtils;
import com.app.hexuegang.wsnmonitor.view.adapter.RecyclerViewAirAdapter;
import com.app.hexuegang.wsnmonitor.view.fragment.activity.AirElementDetailActivity;
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

public class AirIndexFragment extends BaseFragment implements RecyclerViewAirAdapter.MyItemClickListener {
    private final static int QUERY_ITEM_LIMITS = 15;     // 查询结果条目限制个数
    private static final int REQUEST_CODE_POST_1 = 1;

    private RelativeLayout rl_2_root;
    private XRecyclerView xrecyclerview_2;
    private TextView mNoDataTv;
    private ProgressBar loading_prbar;
    private RecyclerViewAirAdapter adapter;
    private ArrayList<parameter_air_year> mList = new ArrayList<>();
    private Date mDate = new Date();
    private String lastParameterAirStr;
    private boolean isConnected;
    private Context context = AirIndexFragment.this.getContext();

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_2;
    }

    @Override
    public void init(View view) {
        rl_2_root = (RelativeLayout) view.findViewById(R.id.rl_2_root);
        xrecyclerview_2 = (XRecyclerView) view.findViewById(R.id.xrecyclerview_2);
        mNoDataTv = (TextView) view.findViewById(R.id.mNoDataTv);
        loading_prbar = (ProgressBar) view.findViewById(R.id.loading_prbar);
        isConnected = SystemUtils.checkNetworkConnection(getActivity());

        initRecyclerView();
        getData();

        doListener();
    }

    private void doListener() {
        xrecyclerview_2.setLoadingListener(new XRecyclerView.LoadingListener() {
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
        xrecyclerview_2.setLayoutManager(layoutManager);

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
        BmobQuery<parameter_air_year> waterBmobQuery = new BmobQuery<parameter_air_year>();
        waterBmobQuery.addWhereLessThanOrEqualTo("createdAt", new BmobDate(mDate));
        waterBmobQuery.order("-createdAt");  // 按时间降序排列
        waterBmobQuery.setLimit(QUERY_ITEM_LIMITS);  // 设定返回的查询条数
        // 设定查询缓存策略-CACHE_ELSE_NETWORK: 先从缓存读取数据, 如果没有, 再从网络获取.
        waterBmobQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
        waterBmobQuery.setMaxCacheAge(TimeUnit.DAYS.toMillis(7));    //此表示缓存一天
        waterBmobQuery.findObjects(new FindListener<parameter_air_year>() {
            @Override
            public void done(List<parameter_air_year> list, BmobException e) {
                if (e == null) {

                    mList.clear();

                    for (parameter_air_year airYear : list) {
                        mList.add(airYear);
                    }
                    // get the last item post time
                    if (!mList.isEmpty()) {
                        adapter = new RecyclerViewAirAdapter(mList);
                        xrecyclerview_2.setAdapter(adapter);
                        mNoDataTv.setVisibility(View.GONE);
                        adapter.setOnItemClickListener(AirIndexFragment.this);

                        parameter_air_year lastParameterAir = mList.get(mList.size() - 1);
                        lastParameterAirStr = lastParameterAir.getUpdatedAt();
                        Log.i("LOG", "lastParameterAir.getUserTimeStr() in generateData " +
                                lastParameterAirStr);
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
                    xrecyclerview_2.refreshComplete();
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
        Date newdate = DateUtil.string2Date(lastParameterAirStr);
        BmobQuery<parameter_air_year> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereLessThanOrEqualTo("createdAt", new BmobDate(newdate));
        bmobQuery.order("-createdAt");  // 按时间降序排列
        bmobQuery.setLimit(QUERY_ITEM_LIMITS);  // 设定返回的查询条数
        // 设定查询缓存策略-CACHE_ELSE_NETWORK: 先从网络读取数据, 如果没有, 再从缓存获取.
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
        bmobQuery.setMaxCacheAge(TimeUnit.DAYS.toMillis(7));    //此表示缓存一天
        bmobQuery.findObjects(new FindListener<parameter_air_year>() {
            @Override
            public void done(List<parameter_air_year> list, BmobException e) {
                if (e == null) {
                    if (list.size() == 0) {
                        SystemUtils.showHandlerToast(getActivity(), "没有更多内容了...");
                        Log.i("LOG", "list.size() in generateLoadMoreData >>> " + list.size());
                    } else {
                        for (parameter_air_year comUserPostInfo : list) {
                            mList.add(comUserPostInfo);
                        }

                        // 监听数据的变化, 上拉加载更多后处于当前可视的最后一个item位置
                        adapter.notifyDataSetChanged();
                        adapter.setOnItemClickListener(AirIndexFragment.this);

                        // get the last item post time
                        if (!mList.isEmpty()) {
                            parameter_air_year lastPostInfo = mList.get(mList.size() - 1);
                            lastParameterAirStr = lastPostInfo.getUpdatedAt();
                        }
                    }

                    loading_prbar.setVisibility(ProgressBar.GONE);
                } else {
                    loading_prbar.setVisibility(ProgressBar.GONE);
                }
                xrecyclerview_2.loadMoreComplete();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == -1) {
            parameter_air_year postData = (parameter_air_year) data.getSerializableExtra("newPostData");
            Log.d("SecondFragment", "postData >>> " + postData);

            switch (requestCode) {
                case REQUEST_CODE_POST_1:
                    mNoDataTv.setVisibility(View.GONE);

                    if (mList.isEmpty()) {
                        mList.add(postData);
                        adapter = new RecyclerViewAirAdapter(mList);
                        xrecyclerview_2.setAdapter(adapter);
                        adapter.setOnItemClickListener(AirIndexFragment.this);

                        // 如果是第一条数据，初始化所有
                        parameter_air_year lastParameterWater = mList.get(mList.size() - 1);
                        lastParameterAirStr = lastParameterWater.getUpdatedAt();
                    } else {
                        if (mList.isEmpty()){
                            mList = new ArrayList<>();
                        }
                        mList.add(0, postData);
                        adapter.notifyDataSetChanged();
                        adapter.setOnItemClickListener(AirIndexFragment.this);
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
        Intent intent = new Intent(AirIndexFragment.this.getActivity(), AirElementDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("air_element", mList.get(position));
        intent.putExtras(bundle);
        AirIndexFragment.this.getActivity().startActivity(intent);
    }
}