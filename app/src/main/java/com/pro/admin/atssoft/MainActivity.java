package com.pro.admin.atssoft;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.pro.admin.atssoft.APIResult.CF_Overview;
import com.pro.admin.atssoft.APIResult.Common;

import java.util.ArrayList;
import java.util.List;

import Control.MyCustomViewPager;
import Dialog.AreaDialog;
import Dialog.BranchDialog;
import fragment.CashierFragment;
import fragment.KitchenFragment;
import fragment.OrtherFragment;
import fragment.OverviewFragment;
import fragment.ReportFragment;
import info.androidhive.fontawesome.FontTextView;

public class MainActivity extends AppCompatActivity {


    SessionManager mSessionManager;
    private TabLayout tabLayout;
    private MyCustomViewPager viewPager;
    ViewPagerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSessionManager = new SessionManager(this);
        if (mSessionManager.getScreenSetting() == 1)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        /*
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorRed)));
        */

/*
        if (!Common._USER_PERMISSION.contains("kitchenpage")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.CONFIG_ORIENTATION);
        }
        */

        FontTextView imgSetting = (FontTextView) findViewById(R.id.action_setting);
        FontTextView imgNotify = (FontTextView) findViewById(R.id.action_notify);
        FontTextView imgBranch = (FontTextView) findViewById(R.id.action_branch);
        TextView txtUser = (TextView) findViewById(R.id.txtUser);
        if (Common._USER_NAME != null && Common._USER_NAME.length() > 0)
            txtUser.setText(Common._USER_NAME);
        else
            txtUser.setText(Common._USER_USERNAME);

        imgNotify.setOnTouchListener(onIconTouch);
        imgSetting.setOnTouchListener(onIconTouch);
        imgBranch.setOnTouchListener(onIconTouch);
        /*
        Common._NOTIFY_PRODUCT_DONE_BUTTON = (TextView) findViewById(R.id.notif_count);
        Common._NOTIFY_PRODUCT_DONE_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
        Common._NOTIFY_PRODUCT_DONE_BUTTON.setText(String.valueOf(Common._NOTIFY_PRODUCT_DONE_COUNT + ""));
        if (Common._NOTIFY_PRODUCT_DONE_COUNT > 0) {
            Common._NOTIFY_PRODUCT_DONE_BUTTON.setVisibility(View.VISIBLE);
        } else
            Common._NOTIFY_PRODUCT_DONE_BUTTON.setVisibility(View.GONE);

         */
        Intent intent = getIntent();
        int notify = intent.getIntExtra("notify", 0);

        mSessionManager = new SessionManager(this);
        mSessionManager.checkLogin();


        viewPager = (MyCustomViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        viewPager.setPagingEnabled(false);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition() ==0) {

                    CF_Overview.getBillWailtingPayment(MainActivity.this, Common._USER_TOKEN, Common.getStrBranchSelected());

                    CF_Overview.getPaidBill(MainActivity.this, Common._USER_TOKEN, Common.getStrBranchSelected());

                    CF_Overview.getRoomEmptyAndUsedt(MainActivity.this, Common._USER_TOKEN, Common.getStrBranchSelected());

                    CF_Overview.getRevenueToday(MainActivity.this, Common._USER_TOKEN, Common.getStrBranchSelected(), Common._CHART_DOANHTHU, Common._CHART_SOLUONGKHACH);
                }

                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            LinearLayout tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tablayout_item_ver, null);
            // get child TextView and ImageView from this layout for the icon and label
            TextView tab_label = (TextView) tab.findViewById(R.id.nav_label);
            ImageView tab_icon = (ImageView) tab.findViewById(R.id.nav_icon);
            // set the label text by getting the actual string value by its id
            // by getting the actual resource value `getResources().getString(string_id)`
            String tabText = tabLayout.getTabAt(i).getText().toString();
            tab_label.setText(tabText);
            if (tabText == getResources().getString(R.string.overview))
                tab_icon.setImageResource(R.mipmap.home);
            else if (tabText == getResources().getString(R.string.cashier)) {
                tab_icon.setImageResource(R.mipmap.cashier);
                Common._NOTIFY_PRODUCT_DONE_BUTTON = (TextView) tab.findViewById(R.id.nav_number);
                if (Common._NOTIFY_PRODUCT_DONE_COUNT <= 9)
                    Common._NOTIFY_PRODUCT_DONE_BUTTON.setText(String.valueOf(Common._NOTIFY_PRODUCT_DONE_COUNT + ""));
                else
                    Common._NOTIFY_PRODUCT_DONE_BUTTON.setText(String.valueOf(Common._NOTIFY_PRODUCT_DONE_COUNT + "+"));
                if (Common._NOTIFY_PRODUCT_DONE_COUNT > 0) {
                    Common._NOTIFY_PRODUCT_DONE_BUTTON.setVisibility(View.VISIBLE);
                } else
                    Common._NOTIFY_PRODUCT_DONE_BUTTON.setVisibility(View.GONE);
            } else if (tabText == getResources().getString(R.string.kitchen))
                tab_icon.setImageResource(R.mipmap.kitchen);
            else if (tabText == getResources().getString(R.string.report))
                tab_icon.setImageResource(R.mipmap.report);
            else if (tabText == getResources().getString(R.string.orther))
                tab_icon.setImageResource(R.mipmap.menu);
            if (i != 0) {
                tab_label.setTextColor(getResources().getColor(R.color.color_tablayout));

                tab_icon.setColorFilter(getResources().getColor(R.color.color_tablayout));
            } else {
                tab_label.setTextColor(getResources().getColor(R.color.color_tablayout_selected));
                tab_icon.setColorFilter(getResources().getColor(R.color.color_tablayout_selected));
            }
            // finally publish this custom view to navigation tab
            tabLayout.getTabAt(i).setCustomView(tab);
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView tab_label = tab.getCustomView().findViewById(R.id.nav_label);
                ImageView tab_icon = tab.getCustomView().findViewById(R.id.nav_icon);
                tab_label.setTextColor(getResources().getColor(R.color.color_tablayout_selected));
                tab_icon.setColorFilter(getResources().getColor(R.color.color_tablayout_selected));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView tab_label = tab.getCustomView().findViewById(R.id.nav_label);
                ImageView tab_icon = tab.getCustomView().findViewById(R.id.nav_icon);
                tab_label.setTextColor(getResources().getColor(R.color.color_tablayout));
                tab_icon.setColorFilter(getResources().getColor(R.color.color_tablayout));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        imgBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BranchDialog areaDialog = new BranchDialog();
                areaDialog.show(getSupportFragmentManager(), "");
            }
        });

        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.inflate(R.menu.menu_main);
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.logout:
                                mSessionManager.logout();
                                return true;
                            case R.id.exit:
                                android.os.Process.killProcess(android.os.Process.myPid());
                                finish();
                                System.exit(0);
                                return true;
                        }
                        return true;
                    }
                });
            }
        });

        if (notify == 1)
            viewPager.setCurrentItem(1);

    }


    private View.OnTouchListener onIconTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            FontTextView img = (FontTextView) v;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    img.setTextColor(getResources().getColor(R.color.color_icon_click));
                    return true; // if you want to handle the touch event
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    img.setTextColor(getResources().getColor(R.color.color_while));
                    return true; // if you want to handle the touch event
            }
            return false;
        }
    };


    private void setupViewPager(ViewPager viewPager) {
         adapter = new ViewPagerAdapter(getSupportFragmentManager());

        if (Common._USER_ADMIN) {
            adapter.addFragment(new OverviewFragment(), getResources().getString(R.string.overview));
            adapter.addFragment(new CashierFragment(), getResources().getString(R.string.cashier));
            if (!Common._MSG_KhongSuDungTinhNangBep)
                if (Common._USER_PERMISSION.contains("kitchenpage") && Common._USER_LINCESES.contains("kitchenpage"))
                    adapter.addFragment(new KitchenFragment(), getResources().getString(R.string.kitchen));
            adapter.addFragment(new ReportFragment(), getResources().getString(R.string.report));
            adapter.addFragment(new OrtherFragment(), getResources().getString(R.string.orther));
        } else {
            if (Common._USER_PERMISSION.contains("overviewpage"))
                adapter.addFragment(new OverviewFragment(), getResources().getString(R.string.overview));
            if (Common._USER_PERMISSION.contains("orderpage") && Common._USER_LINCESES.contains("orderpage"))
                adapter.addFragment(new CashierFragment(), getResources().getString(R.string.cashier));
            if (!Common._MSG_KhongSuDungTinhNangBep)
                if (Common._USER_PERMISSION.contains("kitchenpage") && Common._USER_LINCESES.contains("kitchenpage"))
                    adapter.addFragment(new KitchenFragment(), getResources().getString(R.string.kitchen));
            if ((Common._USER_PERMISSION.contains("todaychartpage")
                    || Common._USER_PERMISSION.contains("salesdetailchartpage")) &&
                    (Common._USER_LINCESES.contains("todaychartpage")
                            || Common._USER_LINCESES.contains("salesdetailchartpage")))
                adapter.addFragment(new ReportFragment(), getResources().getString(R.string.report));

        }
        viewPager.setAdapter(adapter);
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
        @Override
        public CharSequence getPageTitle(int position) {

            return mFragmentTitleList.get(position);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout:
                mSessionManager.logout();
                return true;
            case R.id.exit:
                android.os.Process.killProcess(android.os.Process.myPid());
                finish();
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
