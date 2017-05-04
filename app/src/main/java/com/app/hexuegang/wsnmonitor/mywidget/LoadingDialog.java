package com.app.hexuegang.wsnmonitor.mywidget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.app.hexuegang.wsnmonitor.R;


/**
 * Created by hexuegang on 2017/1/20.
 */
public class LoadingDialog
{
    private Context context;
    private Dialog dialog;
    private RelativeLayout rLayout_bg;

    private DisplayMetrics displayMetrics;


    public LoadingDialog(Context context)
    {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        displayMetrics=new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
    }

    @SuppressLint("InflateParams")
    public LoadingDialog Builder()
    {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);

        rLayout_bg = (RelativeLayout) view.findViewById(R.id.rLayout_bg);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.MyDialogStyle);
        dialog.setContentView(view);


        // 调整dialog背景大小
//        rLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (displayMetrics.widthPixels * 0.85), WindowManager.LayoutParams.WRAP_CONTENT));

        return this;
    }


    public void show()
    {
        dialog.show();
    }

    public void dismiss()
    {
        dialog.dismiss();
    }
}


