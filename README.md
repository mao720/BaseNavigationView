# BaseNavigationView
BaseNavigationView，快速实现可配置的底部导航栏

导入项目：
  compile 'com.mjf:basenavigationview:1.0.2'

配置方法：

  1.在布局中引入BaseNavigationView

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
          
  2.编辑values/arrays文件
  
      <string-array name="navigation_tab_names">
          <item>@string/tab_one</item>
          <item>@string/tab_two</item>
          <item>@string/tab_three</item>
          <item>@string/tab_four</item>
      </string-array>
      <string-array name="navigation_tab_ics_dark">
          <item>@drawable/ic_tab_one_dark</item>
          <item>@drawable/ic_tab_two_dark</item>
          <item>@drawable/ic_tab_three_dark</item>
          <item>@drawable/ic_tab_four_dark</item>
      </string-array>
      <string-array name="navigation_tab_ics_light">
          <item>@drawable/ic_tab_one_light</item>
          <item>@drawable/ic_tab_two_light</item>
          <item>@drawable/ic_tab_three_light</item>
          <item>@drawable/ic_tab_four_light</item>
      </string-array>

API：

  1.changeTabIcPadding(int position, int left, int top, int right, int bottom)
    改变指定位置图标的padding值。
  2.setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener listener)
    设置图标点击监听
