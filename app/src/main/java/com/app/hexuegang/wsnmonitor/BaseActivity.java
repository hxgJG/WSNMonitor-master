package com.app.hexuegang.wsnmonitor;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.app.hexuegang.wsnmonitor.databinding.ActivityBaseBinding;
import com.app.hexuegang.wsnmonitor.util.CommonUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by hexuegang on 2017/1/19.
 */

public abstract class BaseActivity<SV extends ViewDataBinding> extends AppCompatActivity{

    protected SV bindingView;
    private ActivityBaseBinding activityBaseBinding;
    private Toolbar tool_bar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        activityBaseBinding = DataBindingUtil.inflate(LayoutInflater.from(BaseActivity.this), R.layout.activity_base, null, false);
        bindingView = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, null, false);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(layoutParams);
        RelativeLayout mContainer = (RelativeLayout) activityBaseBinding.getRoot().findViewById(R.id.container);
        tool_bar = (Toolbar) activityBaseBinding.getRoot().findViewById(R.id.tool_bar);
        mContainer.addView(bindingView.getRoot());
        getWindow().setContentView(activityBaseBinding.getRoot());

        //设置透明状态栏
        CommonUtils.setColor(BaseActivity.this, getResources().getColor(R.color.colorAccent), 0);
    }

    public void setToolBar(boolean isShow){
        if (tool_bar != null && isShow)
        {
            tool_bar.setVisibility(View.VISIBLE);
        }else {
            tool_bar.setVisibility(View.GONE);
        }
    }

    public void setToolBarTitle(String title){
        tool_bar.setTitle(title);

    }

    public void setNavigationIcon(int resId){
        setSupportActionBar(tool_bar);
        tool_bar.setNavigationIcon(resId);
    }

    public void setNavigationIconClick(final Activity activity, boolean clickable){
        if (clickable)
        {
            tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.finish();
                }
            });
        }
    }

//    protected abstract int getContentViewLayout();
//    protected abstract void init();

    protected void registerEventBus()
    {
        if(needEventBus())
        {
            EventBus.getDefault().register(this);
        }
    }
    protected void unRegisterEventBus()
    {
        if(needEventBus())
        {
            EventBus.getDefault().unregister(this);
        }
    }

    protected boolean needEventBus()
    {
        return false;
    }
}
