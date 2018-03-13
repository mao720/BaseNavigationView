# BaseNavigationView
BaseNavigationView，快速实现可配置的底部导航栏

导入项目：
  compile 'com.mjf:basenavigationview:1.0.2'
配置方法：
  1.在布局中引入BaseNavigationView
    `<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.mjf.sample.MainActivity">

      <TextView
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:text="Hello World!"
          app:layout_constraintBottom_toBottomOf="parent"
          app:layout_constraintLeft_toLeftOf="parent"
          app:layout_constraintRight_toRightOf="parent"
          app:layout_constraintTop_toTopOf="parent" />

      <com.mjf.navigationview.BaseNavigationView
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:background="@android:color/darker_gray"
          app:autoLayoutEnabled="true"
          app:selectedColor="@color/colorAccent"
          app:unselectedColor="@color/colorPrimary"
          app:tabNames="@array/navigation_tab_names"
          app:tabTextSize="20px"
          app:tabIcArraysSelected="@array/navigation_tab_ics_light"
          app:tabIcArraysUnselected="@array/navigation_tab_ics_dark"
          app:tabIcPadding="10px"
          app:tabTextPaddingBottom="10px"
          app:layout_constraintBottom_toBottomOf="parent" />
    </android.support.constraint.ConstraintLayout>`
API：
  1.changeTabIcPadding(int position, int left, int top, int right, int bottom)
    改变指定位置图标的padding值。
  2.setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener listener)
    设置图标点击监听
