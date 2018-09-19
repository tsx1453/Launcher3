package com.android.launcher3.custompage.weather;

import com.android.launcher3.R;

public class WeatherId2Icon {

    private int sunny = R.drawable.wic_clear_d;
    private int cloud = R.drawable.wic_cloudy_d;
    private int mist = R.drawable.wic_fog_mist;
    private int rain = R.drawable.wic_rain;
    private int snow = R.drawable.wic_snow;
    private int unknow = R.drawable.wic_unkown;
    private int wind = R.drawable.wic_wind;

    public int getPic(String code) {
        int id = unknow;
        try {
            id = Integer.valueOf(code);
        } catch (Exception ignore) {
            return id;
        }
        if (id == 100 || id == 201) {
            id = sunny;
        } else if (id > 100 && id < 200) {
            id = cloud;
        } else if (id == 200 || (id > 201 && id < 300)) {
            id = wind;
        } else if (id >= 300 && id < 400) {
            id = rain;
        } else if (id >= 400 && id < 500) {
            id = snow;
        } else if (id >= 500 && id < 600) {
            id = mist;
        }
        return id;
    }

}
