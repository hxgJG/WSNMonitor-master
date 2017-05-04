package com.app.hexuegang.wsnmonitor.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by HEXG on 2017/4/8.
 */

public abstract class BaseFragment<T> extends Fragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);
        init(view);
        return view;
    }

    public abstract int getFragmentLayout();

    public void init(View view){}

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    public void widgetClick(View v) {

    }

}
