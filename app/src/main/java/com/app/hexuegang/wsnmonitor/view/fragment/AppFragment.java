package com.app.hexuegang.wsnmonitor.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;


public abstract class AppFragment extends Fragment implements OnClickListener
{
    protected View fragmentRootView;

    protected abstract View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle);

    protected void initData() {}
    protected void initWidget(View parentView) {}
    protected void widgetClick(View v) {}
    protected void onMenuClick() {}

    protected boolean checkLogin()
    {
        return false;
    }
    protected boolean needEventBus(){return false;}


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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
    	if(fragmentRootView==null)
    	{

		    fragmentRootView = inflaterView(inflater, container, savedInstanceState);
            initData();
	        initWidget(fragmentRootView);
            registerEventBus();
    	}
    	ViewGroup parent = (ViewGroup) fragmentRootView.getParent();
        if (parent != null)
        {
            parent.removeView(fragmentRootView);
        }
        return fragmentRootView;


    }


    public void onPause()
    {
        super.onPause();
    }

    public void onResume()
    {
        super.onResume();
    }

    public void onDestroy()
    {
        this.unRegisterEventBus();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }




	protected <T extends View> T bindView(int id) {
        return (T) fragmentRootView.findViewById(id);
    }

    protected <T extends View> T bindView(int id, boolean click)
    {
       
		T view = (T) fragmentRootView.findViewById(id);
        if (click)
        {
            view.setOnClickListener(this);
        }
        return view;
    }




    public void onLogin()
    {

    }
    public void onLogout()
    {

    }

}