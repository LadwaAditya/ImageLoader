package com.ladwa.aditya.image.ui.chooseimage;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.ladwa.aditya.image.R;
import com.ladwa.aditya.image.ui.base.BaseActivity;

import butterknife.BindView;

public class ChooseImageActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private final String TAB_TITLE_ONE = "ALBUMS";
    private final String TAB_TITLE_TWO = "INSTAGRAM";
    private final String TAB_TITLE_THREE = "FACEBOOK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabLayout.addTab(tabLayout.newTab().setText(TAB_TITLE_ONE));
        tabLayout.addTab(tabLayout.newTab().setText(TAB_TITLE_TWO));
        tabLayout.addTab(tabLayout.newTab().setText(TAB_TITLE_THREE));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(this);


        mViewPager.setAdapter(new ChooseImagePagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_choose_image;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    public class ChooseImagePagerAdapter extends FragmentPagerAdapter {
        private final Integer TOTAL_TABS = 3;


        public ChooseImagePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return AlbumFragment.newInstant();
                case 1:
                    return InstagramFragment.newInstant();
                case 2:
                    return FacebookFragment.newInstant();
            }
            return null;
        }

        @Override
        public int getCount() {
            return TOTAL_TABS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return TAB_TITLE_ONE;
                case 1:
                    return TAB_TITLE_TWO;
                case 2:
                    return TAB_TITLE_THREE;
            }
            return super.getPageTitle(position);
        }
    }
}
