package com.mjf.navigationview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

public class AutoNavigationView extends AutoLinearLayout {
    List<View> selectTabs;
    int selectPosition;
    private int[] tabIcsDark;
    private int[] tabIcsLight;
    private OnNavigationItemSelectedListener listener;

    public AutoNavigationView(Context context) {
        super(context);
    }

    public AutoNavigationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoNavigationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        String[] tabNames = ArmsUtils.getStringArray(this.getContext(), R.array.navigation_tabs_name);
        tabIcsDark = ArmsUtils.getResIdsArray(this.getContext(), R.array.navigation_tabs_ic_dark);
        tabIcsLight = ArmsUtils.getResIdsArray(this.getContext(), R.array.navigation_tabs_ic_light);
        selectTabs = new ArrayList<>();
        for (int i = 0; i < tabNames.length; i++) {
            LinearLayout tab = (LinearLayout) LayoutInflater
                    .from(this.getContext())
                    .inflate(R.layout.item_navigation_tab, null);
            tab.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    1));
            selectTabs.add(tab);
            ImageView ivTabIc = tab.findViewById(R.id.iv_tab_ic);
            TextView tvTabName = tab.findViewById(R.id.tv_tab_name);
            tvTabName.setText(tabNames[i]);
            tvTabName.setTextSize(TypedValue.COMPLEX_UNIT_PX, AutoUtils.getPercentWidthSize(22));
            tvTabName.setPadding(0, 0, 0, AutoUtils.getPercentHeightSize(9));
            ivTabIc.setImageResource(tabIcsDark[i]);
            ivTabIc.setPadding(0, AutoUtils.getPercentHeightSize(10),
                    0, AutoUtils.getPercentHeightSize(10));
            final int position = i;
            tab.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeSelectedTab(position);
                }
            });
            //初始化，默认选择第一个条目
            if (i == 0) {
                selectPosition = 0;
                ivTabIc.setImageResource(tabIcsLight[i]);
                tvTabName.setSelected(true);
            }
            this.addView(tab);
        }
    }

    public void changeSelectedTab(int position) {
        if (listener != null) {
            listener.onNavigationItemSelected(selectTabs.get(position), position);
        }
        ImageView ivSelectTabIc = selectTabs.get(selectPosition).findViewById(R.id.iv_tab_ic);
        TextView tvSelectTabName = selectTabs.get(selectPosition).findViewById(R.id.tv_tab_name);
        ivSelectTabIc.setImageResource(tabIcsDark[selectPosition]);
        tvSelectTabName.setSelected(false);
        ImageView ivTabIc = selectTabs.get(position).findViewById(R.id.iv_tab_ic);
        TextView tvTabName = selectTabs.get(position).findViewById(R.id.tv_tab_name);
        ivTabIc.setImageResource(tabIcsLight[position]);
        tvTabName.setSelected(true);
        selectPosition = position;
    }

    public void setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener listener) {
        this.listener = listener;
    }

    public interface OnNavigationItemSelectedListener {
        void onNavigationItemSelected(View view, int index);
    }

}