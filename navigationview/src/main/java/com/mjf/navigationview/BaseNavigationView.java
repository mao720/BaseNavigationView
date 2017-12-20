package com.mjf.navigationview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoLayoutHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * D
 * Created by maojunfeng on 16/4/14.
 */
public class BaseNavigationView extends LinearLayout {

    List<View> tabs;
    int selectPosition;
    private boolean autoLayoutEnabled;
    private int tabSelectedColorId;
    private int tabUnselectedColorId;
    private String[] tabNames;
    private int[] tabIcsLight;
    private int[] tabIcsDark;
    private int tabTextSize = -1;
    private int tabIcPadding = -1;
    private int tabIcPaddingTop = -1;
    private int tabIcPaddingBottom = -1;
    private int tabTextPadding = -1;
    private int tabTextPaddingTop = -1;
    private int tabTextPaddingBottom = -1;


    private OnNavigationItemSelectedListener listener;

    public BaseNavigationView(Context context) {
        this(context, null);
    }

    public BaseNavigationView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseNavigationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.BaseNavigationView, defStyleAttr, 0);
        int indexCount = a.getIndexCount();
        //读取自定义控件的属性信息
        for (int i = 0; i < indexCount; i++) {
            int itemId = a.getIndex(i);
            if (itemId == R.styleable.BaseNavigationView_autoLayoutEnabled) {
                autoLayoutEnabled = a.getBoolean(itemId, false);
            } else if (itemId == R.styleable.BaseNavigationView_selectedColor) {
                tabSelectedColorId = a.getResourceId(itemId, 0);
            } else if (itemId == R.styleable.BaseNavigationView_unselectedColor) {
                tabUnselectedColorId = a.getResourceId(itemId, 0);
            } else if (itemId == R.styleable.BaseNavigationView_tabNames) {
                tabNames = Utils.getStringArray(this.getContext(), a.getResourceId(itemId, 0));
            } else if (itemId == R.styleable.BaseNavigationView_tabTextSize) {
                tabTextSize = a.getDimensionPixelSize(itemId, 0);
            } else if (itemId == R.styleable.BaseNavigationView_tabIcArraysSelected) {
                tabIcsLight = Utils.getResIdsArray(this.getContext(), a.getResourceId(itemId, 0));
            } else if (itemId == R.styleable.BaseNavigationView_tabIcArraysUnselected) {
                tabIcsDark = Utils.getResIdsArray(this.getContext(), a.getResourceId(itemId, 0));
            } else if (itemId == R.styleable.BaseNavigationView_tabIcPadding) {
                tabIcPadding = a.getDimensionPixelSize(itemId, 0);
            } else if (itemId == R.styleable.BaseNavigationView_tabIcPaddingTop) {
                tabIcPaddingTop = a.getDimensionPixelSize(itemId, 0);
            } else if (itemId == R.styleable.BaseNavigationView_tabIcPaddingBottom) {
                tabIcPaddingBottom = a.getDimensionPixelSize(itemId, 0);
            } else if (itemId == R.styleable.BaseNavigationView_tabTextPadding) {
                tabTextPadding = a.getDimensionPixelSize(itemId, 0);
            } else if (itemId == R.styleable.BaseNavigationView_tabTextPaddingTop) {
                tabTextPaddingTop = a.getDimensionPixelSize(itemId, 0);
            } else if (itemId == R.styleable.BaseNavigationView_tabTextPaddingBottom) {
                tabTextPaddingBottom = a.getDimensionPixelSize(itemId, 0);
            }
        }
        a.recycle();
        initView();
    }

    private void initView() {
        new AutoLayoutHelper(this);
        tabs = new ArrayList<>();
        if (tabNames == null || tabNames.length <= 0) {
            return;
        }
        tabSelectedColorId = tabSelectedColorId == 0 ? android.R.color.white : tabSelectedColorId;
        tabUnselectedColorId = tabUnselectedColorId == 0 ? android.R.color.black : tabUnselectedColorId;
        for (int i = 0; i < tabNames.length; i++) {
            LinearLayout tab = (LinearLayout) LayoutInflater
                    .from(this.getContext())
                    .inflate(R.layout.item_navigation_tab, null);
            tab.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    1));
            ImageView ivTabIc = tab.findViewById(R.id.iv_tab_ic);
            TextView tvTabName = tab.findViewById(R.id.tv_tab_name);
            //tab字体名称
            tvTabName.setText(tabNames[i]);
            //字体大小
            if (tabTextSize != -1) {
                tvTabName.setTextSize(TypedValue.COMPLEX_UNIT_PX, Utils.getAutoWidth(autoLayoutEnabled, tabTextSize));
            }
            //文字默认颜色
            tvTabName.setTextColor(getResources().getColor(tabUnselectedColorId));
            //文字padding
            if (tabTextPadding != -1) {
                int autoPadding = Utils.getAutoHeight(autoLayoutEnabled, tabTextPadding);
                tvTabName.setPadding(autoPadding, autoPadding, autoPadding, autoPadding);
            } else if (tabTextPaddingTop != -1 || tabTextPaddingBottom != -1) {
                tvTabName.setPadding(0, Utils.getAutoHeight(autoLayoutEnabled, tabTextPaddingTop),
                        0, Utils.getAutoHeight(autoLayoutEnabled, tabTextPaddingBottom));
            }
            //图标默认图片
            ivTabIc.setImageResource(tabIcsDark[i]);
            //图标padding
            if (tabIcPadding != -1) {
                int autoPadding = Utils.getAutoHeight(autoLayoutEnabled, tabIcPadding);
                ivTabIc.setPadding(autoPadding, autoPadding, autoPadding, autoPadding);
            } else if (tabIcPaddingTop != -1 || tabIcPaddingBottom != -1) {
                ivTabIc.setPadding(0, Utils.getAutoHeight(autoLayoutEnabled, tabIcPaddingTop),
                        0, Utils.getAutoHeight(autoLayoutEnabled, tabIcPaddingBottom));
            }
            final int position = i;
            //设置tab点击事件
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
                tvTabName.setTextColor(getResources().getColor(tabSelectedColorId));
            }
            //保存每一个tab
            tabs.add(tab);
            //将当前tab添加到界面
            this.addView(tab);
        }
    }

    public void changeSelectedTab(int position) {
        //外界设置的点击监听
        if (listener != null) {
            listener.onNavigationItemSelected(tabs.get(position), position);
        }
        //移除上一个选中的tab
        ImageView ivSelectTabIc = tabs.get(selectPosition).findViewById(R.id.iv_tab_ic);
        TextView tvSelectTabName = tabs.get(selectPosition).findViewById(R.id.tv_tab_name);
        ivSelectTabIc.setImageResource(tabIcsDark[selectPosition]);
        tvSelectTabName.setTextColor(getResources().getColor(tabUnselectedColorId));
        //将此次点击的tab选中
        ImageView ivTabIc = tabs.get(position).findViewById(R.id.iv_tab_ic);
        TextView tvTabName = tabs.get(position).findViewById(R.id.tv_tab_name);
        ivTabIc.setImageResource(tabIcsLight[position]);
        tvTabName.setTextColor(getResources().getColor(tabSelectedColorId));
        //记录选中的tab位置
        selectPosition = position;
    }

    public void changeTabIcPadding(int position, int left, int top, int right, int bottom) {
        tabs.get(position).findViewById(R.id.iv_tab_ic).setPadding(
                Utils.getAutoWidth(autoLayoutEnabled, left),
                Utils.getAutoHeight(autoLayoutEnabled, top),
                Utils.getAutoWidth(autoLayoutEnabled, right),
                Utils.getAutoHeight(autoLayoutEnabled, bottom));
    }

    public void setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener listener) {
        this.listener = listener;
    }

    public interface OnNavigationItemSelectedListener {
        void onNavigationItemSelected(View view, int index);
    }
}