package com.app.hexuegang.wsnmonitor.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.hexuegang.wsnmonitor.R;
import com.app.hexuegang.wsnmonitor.publish.MyConstants;
import com.app.hexuegang.wsnmonitor.util.SystemUtils;
import com.app.hexuegang.wsnmonitor.view.adapter.BaseRecyclerViewAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by HEXG on 2017/4/19.
 */

public abstract class BaseIndexFragment<T extends BmobObject> extends BaseFragment implements BaseRecyclerViewAdapter.MyItemClickListener<T> {

    private RelativeLayout rl_1_root;
    private XRecyclerView xrecycler_view;
    private TextView mNoDataTv;
    private TextView tv_title;
    private ProgressBar loading_prbar;
    private boolean isConnected;
    protected BaseRecyclerViewAdapter<T> adapter;
    protected ArrayList<T> datas = new ArrayList<T>();

    protected String lastParameterStr;


    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_base_index;
    }

    @Override
    public void init(View view) {
        rl_1_root = (RelativeLayout) view.findViewById(R.id.rl_1_root);
        xrecycler_view = (XRecyclerView) view.findViewById(R.id.xrecycler_view);
        mNoDataTv = (TextView) view.findViewById(R.id.mNoDataTv);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        loading_prbar = (ProgressBar) view.findViewById(R.id.loading_prbar);
        isConnected = SystemUtils.checkNetworkConnection(getActivity());

        initUI();
        initRecyclerView();
        initAdapter();
        doListener();
        initData();
    }

    protected void initUI() {}

    protected abstract void initAdapter();

    private void doListener() {

        adapter.setOnItemClickListener(this);

        setAdapter();

        xrecycler_view.setLoadingListener(new XRecyclerView.LoadingListener() {
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
        xrecycler_view.setLayoutManager(layoutManager);
    }

    private void initData(){
        if (isConnected) {
            mNoDataTv.setVisibility(View.GONE);
        } else {
            SystemUtils.noNetworkAlert(getActivity());
            loading_prbar.setVisibility(ProgressBar.GONE);
            return;
        }

        getData(MyConstants.GET_DATA_MODE_FIRST);
    }

    /**
     * 获取数据
     * @param mode 获取数据的方式
     */
    public void getData(int mode) {}

    /**
     * 设置标题
     */
    public void setTitle(String title){
        tv_title.setText(title);
    }

    /**
     * 下拉刷新
     */
    private void refreshData(){
        getData(MyConstants.GET_DATA_MODE_REFRESH);
    }

    /**
     * 加载更多
     */
    public void loadMoreData(){}

    /**
     * 单击事件
     * @param t
     * @param position
     */
    @Override
    public void onItemClick(T t, int position) {}

    /**
     * 长按事件
     * @param t
     * @param position
     */
    @Override
    public void onItemLongClick(T t, int position) {}

    /**
     * 下拉刷新完成
     */
    protected void refreshComplete(){
        if (xrecycler_view != null){
            xrecycler_view.refreshComplete();
        }
    }

    /**
     * 加载更多完成
     */
    protected void loadMoreComplete(){
        if (xrecycler_view != null){
            xrecycler_view.loadMoreComplete();
        }
    }

    /**
     * 隐藏/显示没有数据页面
     */
    protected void noDataTvVisibility(int visibility){
        if (mNoDataTv != null){
            mNoDataTv.setVisibility(visibility);
        }
    }

    /**
     * 设置没有数据页面显示样式
     */
    protected void noDataTvSetText(String s){
        if (mNoDataTv != null){
            mNoDataTv.setText(s);
        }
    }

    /**
     * 显示/隐藏ProgressBar
     */
    protected void progressBarVisible(int visibility){
        if (loading_prbar != null){
            loading_prbar.setVisibility(visibility);
        }
    }

    /**
     * 设置Adapter
     */
    private void setAdapter(){
        if (adapter != null && xrecycler_view != null){
            xrecycler_view.setAdapter(adapter);
        }
    }


}
