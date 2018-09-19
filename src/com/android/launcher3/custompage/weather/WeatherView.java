package com.android.launcher3.custompage.weather;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.custompage.bean.WeatherBean;

public class WeatherView extends ConstraintLayout {

    private boolean hasSuccessed = false;

    public WeatherView(Context context) {
        super(context);
        initView();
    }

    public WeatherView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WeatherView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        inflate(getContext(), R.layout.weather_widget, this);
        new WeatherManager().getWeather(new WeatherManager.WeatherGetListener() {
            @Override
            public void onSuccess(WeatherBean bean) {
                hasSuccessed = true;
                ((ImageView) findViewById(R.id.pic)).setImageResource(new WeatherId2Icon().getPic(bean.getHeWeather6().get(0).getNow().getCond_code()));
                ((TextView) findViewById(R.id.description)).setText(bean.getHeWeather6().get(0).getNow().getCond_txt());
                ((TextView) findViewById(R.id.temp)).setText(bean.getHeWeather6().get(0).getNow().getTmp());
            }

            @Override
            public void onFailed(String s) {
                if (!hasSuccessed) {
                    ((ImageView) findViewById(R.id.pic)).setImageResource(R.drawable.wic_unkown);
                }
            }
        });
        int pd = Utilities.pxFromDp(5, getContext().getResources().getDisplayMetrics());
        setPadding(pd,pd,pd,pd);
    }
}
