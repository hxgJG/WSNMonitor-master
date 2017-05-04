package com.app.hexuegang.wsnmonitor.mywidget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.hexuegang.wsnmonitor.R;


/**
 * Created by hexuegang on 2017/1/20.
 */
public class MyAlertDialog
{
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView txt_title;
    private TextView txt_msg;
    private Button btn_neg;
    private Button btn_pos;
    private ImageView img_line;
    private DisplayMetrics displayMetrics;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;

    public MyAlertDialog(Context context)
    {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        displayMetrics=new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
    }

    @SuppressLint("InflateParams")
    public MyAlertDialog Builder()
    {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_alert, null);

        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setVisibility(View.GONE);
        txt_msg = (TextView) view.findViewById(R.id.txt_msg);
        txt_msg.setVisibility(View.GONE);
        btn_neg = (Button) view.findViewById(R.id.btn_neg);
        btn_neg.setVisibility(View.GONE);
        btn_pos = (Button) view.findViewById(R.id.btn_pos);
        btn_pos.setVisibility(View.GONE);
        img_line = (ImageView) view.findViewById(R.id.img_line);
        img_line.setVisibility(View.GONE);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.MyDialogStyle);
        dialog.setContentView(view);


        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (displayMetrics.widthPixels * 0.85), WindowManager.LayoutParams.WRAP_CONTENT));

        return this;
    }

    public MyAlertDialog setTitle(String title)
    {
        showTitle = true;
        if ("".equals(title))
        {
            txt_title.setText("提示");
        }
        else
        {
            txt_title.setText(title);
        }
        return this;
    }

    public MyAlertDialog setAlignCenter()
    {
        txt_msg.setGravity(Gravity.CENTER);
        return this;
    }

    public MyAlertDialog setTitle(int resid)
    {
        return this.setTitle(context.getResources().getString(resid));
    }

    public MyAlertDialog setMsg(String msg)
    {
        showMsg = true;
        if ("".equals(msg)) {
            txt_msg.setText("内容");
        } else {
            txt_msg.setText(msg);
        }
        return this;
    }

    public MyAlertDialog setMsg(int resid)
    {
        return this.setMsg(context.getResources().getString(resid));
    }

    public MyAlertDialog setCancelable(boolean cancel)
    {
        dialog.setCancelable(cancel);
        return this;
    }

    public MyAlertDialog setPositiveButton(String text, final View.OnClickListener listener) {
        showPosBtn = true;
        if ("".equals(text))
        {
            btn_pos.setText("确定");
        } else {
            btn_pos.setText(text);
        }
        btn_pos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(listener!=null)
                {
                    listener.onClick(v);
                }
                dialog.dismiss();
            }
        });
        return this;
    }

    public MyAlertDialog setNegativeButton(String text, final View.OnClickListener listener) {
        showNegBtn = true;
        if ("".equals(text))
        {
            btn_neg.setText("取消");
        }
        else
        {
            btn_neg.setText(text);
        }
        btn_neg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(listener!=null)
                {
                    listener.onClick(v);
                }
                dialog.dismiss();
            }
        });
        return this;
    }

    private void setLayout()
    {
        if (!showTitle && !showMsg)
        {
            txt_title.setText("提示");
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle)
        {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showMsg)
        {
            txt_msg.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && !showNegBtn)
        {
            btn_pos.setText("确定");
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.selector_alertdialog_single);
            btn_pos.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if (showPosBtn && showNegBtn)
        {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.selector_alertdialog_right);
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.selector_alertdialog_left);
            img_line.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.selector_alertdialog_single);
        }

        if (!showPosBtn && showNegBtn) {
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.selector_alertdialog_single);
        }
    }

    public void show()
    {
        setLayout();
        dialog.show();
    }

    public void dismiss()
    {
        dialog.dismiss();
    }
}


