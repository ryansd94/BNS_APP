package Dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;

import java.util.ArrayList;
import java.util.List;

import Control.MyCustomViewPager;
import Control.MyTextView;
import fragment.Order_ProductListFragment;
import fragment.Order_ProductOrderFragment;
import info.androidhive.fontawesome.FontTextView;

public class OrderDialog_Phone extends DialogFragment {

    private TabLayout tabLayout;
    private MyCustomViewPager viewPager;
    private VotersPagerAdapter adapter;
    View mRootview;



    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRootview = inflater.inflate(R.layout.order_dialog_phone, container, false);
        FontTextView imgClosed=mRootview.findViewById(R.id.imgClosed);
        TextView txtTitle = mRootview.findViewById(R.id.txtTitle);
        Common._CONTROL_OrderDialogPhone=this;


        viewPager = (MyCustomViewPager) mRootview.findViewById(R.id.pager);
        tabLayout = (TabLayout) mRootview.findViewById(R.id.tabs);

        MyTextView.setDialogTextTitle(getActivity(), txtTitle);


        if (Common._ROOM_SELECTED_OBJ != null) {
            txtTitle.setText(Common._ROOM_SELECTED_OBJ.getTenBan() + "/" + Common._ROOM_SELECTED_OBJ.getAreaName());
        }



        imgClosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        adapter = new VotersPagerAdapter(getChildFragmentManager(), getArguments());
        adapter.setTitles(new String[]{getResources().getString(R.string.productList),
                getResources().getString(R.string.order)});

        viewPager.setAdapter(adapter);


        tabLayout.setupWithViewPager(viewPager);


        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            LinearLayout tab = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.tablayout_item_hor, null);
            // get child TextView and ImageView from this layout for the icon and label
            TextView tab_label = (TextView) tab.findViewById(R.id.nav_label);
            FontTextView tab_icon = (FontTextView) tab.findViewById(R.id.nav_icon);

            // set the label text by getting the actual string value by its id
            // by getting the actual resource value `getResources().getString(string_id)`
            String tabText=tabLayout.getTabAt(i).getText().toString();
            tab_label.setText(tabText);
            if(tabText== getResources().getString(R.string.productList))
                tab_icon.setText(R.string.fa_cube_solid);
            else if(tabText== getResources().getString(R.string.order))
                tab_icon.setText(R.string.fa_shopping_cart_solid);
            if (i != 0) {
                tab_label.setTextColor(getResources().getColor(R.color.color_tablayout));
                tab_icon.setTextColor(getResources().getColor(R.color.color_tablayout));
            } else {
                tab_label.setTextColor(getResources().getColor(R.color.color_tablayout_selected));
                tab_icon.setTextColor(getResources().getColor(R.color.color_tablayout_selected));
            }
            // finally publish this custom view to navigation tab
            tabLayout.getTabAt(i).setCustomView(tab);


        }
        //viewPager.setCurrentItem(1,true);
        //tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView tab_label = tab.getCustomView().findViewById(R.id.nav_label);
                FontTextView tab_icon = tab.getCustomView().findViewById(R.id.nav_icon);
                tab_label.setTextColor(getResources().getColor(R.color.color_tablayout_selected));
                tab_icon.setTextColor(getResources().getColor(R.color.color_tablayout_selected));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView tab_label = tab.getCustomView().findViewById(R.id.nav_label);
                FontTextView tab_icon = tab.getCustomView().findViewById(R.id.nav_icon);
                tab_label.setTextColor(getResources().getColor(R.color.color_tablayout));
                tab_icon.setTextColor(getResources().getColor(R.color.color_tablayout));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return mRootview;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getParentFragment().getFragmentManager());

        adapter.addFragment(new Order_ProductListFragment(), getResources().getString(R.string.productList));
        adapter.addFragment(new Order_ProductOrderFragment(), getResources().getString(R.string.order));

        viewPager.setAdapter(adapter);
    }


    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

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
        public CharSequence getPageTitle(int position) {

            return mFragmentTitleList.get(position);
        }

    }



    public class VotersPagerAdapter extends FragmentPagerAdapter {

        Bundle bundle;
        String [] titles;


        public VotersPagerAdapter(FragmentManager fm, Bundle bundle) {
            super(fm);
            this.bundle = bundle;
        }

        @Override
        public Fragment getItem(int num) {

            Fragment fragment;
            if (num == 0)
                fragment = new Order_ProductListFragment();
            else
                fragment = new Order_ProductOrderFragment();
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        public void setTitles(String[] titles) {
            this.titles = titles;
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_Light_NoActionBar);
        //hideSoftKeyboard();
    }

    public void hideSoftKeyboard() {
        if (mRootview != null) {

            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mRootview.getWindowToken(), 0);
        }
    }
}
