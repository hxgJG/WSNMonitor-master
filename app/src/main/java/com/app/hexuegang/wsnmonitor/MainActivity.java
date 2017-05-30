package com.app.hexuegang.wsnmonitor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.app.hexuegang.wsnmonitor.databinding.ActivityMainBinding;
import com.app.hexuegang.wsnmonitor.view.adapter.BaseFragmentPagerAdapter;
import com.app.hexuegang.wsnmonitor.view.fragment.AirIndexFragment;
import com.app.hexuegang.wsnmonitor.view.fragment.ComprehensiveFragment;
import com.app.hexuegang.wsnmonitor.view.fragment.NoiseIndexFragment;
import com.app.hexuegang.wsnmonitor.view.fragment.SoilIndexFragment;
import com.app.hexuegang.wsnmonitor.view.fragment.WaterIndexFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        List<Fragment> list = new ArrayList<>();
        list.add(getFragment(new WaterIndexFragment(), 0));
        list.add(getFragment(new AirIndexFragment(), 0));
        list.add(getFragment(new SoilIndexFragment(), 0));
        list.add(getFragment(new NoiseIndexFragment(), 0));
        list.add(getFragment(new ComprehensiveFragment(), 0));

        bindingView.viewpager.setAdapter(new BaseFragmentPagerAdapter(getSupportFragmentManager(), list));
        bindingView.radiogroup.setViewPager(bindingView.viewpager);

        doListener();
    }

    private void doListener() {
        bindingView.viewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
//                ToastUtils.toast(MainActivity.this,"onPageSelected");
            }
        });
    }

    private Fragment getFragment(Fragment fragment, int index){
        Bundle bundle = new Bundle();
        bundle.putInt("type", index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onClick(View view) {}

}
