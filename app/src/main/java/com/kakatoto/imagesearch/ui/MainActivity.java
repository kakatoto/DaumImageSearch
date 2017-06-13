package com.kakatoto.imagesearch.ui;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.kakatoto.imagesearch.R;
import com.kakatoto.imagesearch.presenter.MainPresenter;
import com.kakatoto.imagesearch.presenter.fragment.ImageListPresenter;
import com.kakatoto.imagesearch.presenter.fragment.ScrapListPresenter;
import com.kakatoto.imagesearch.presenter.fragment.impl.IImageListContract;
import com.kakatoto.imagesearch.presenter.impl.IMainContract;
import com.kakatoto.imagesearch.ui.fragment.ImageListFragment;
import com.kakatoto.imagesearch.ui.fragment.ScrapListFragment;
import com.kakatoto.imagesearch.util.CommonUtil;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements IMainContract.View, IImageListContract.ImageScrapListener {
    private MainPresenter presenter;

    @BindView(R.id.container)
    ViewPager viewPager;

    @BindView(R.id.searchView)
    MaterialSearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.presenter = new MainPresenter();
        this.presenter.attatch(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.presenter.deatch();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        this.searchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setToolBar() {
        Toolbar toolbar = ButterKnife.findById(this, R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void setSearchView() {
        this.searchView.setVoiceSearch(false);
        this.searchView.setEllipsize(true);
        this.searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!CommonUtil.isNull(query)) {
                    presenter.addSuggest(query);
                    viewPager.setCurrentItem(0);
                    ImageListFragment fragment = (ImageListFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.container + ":" + 0);
                    ImageListPresenter ImagePresenter = fragment.getPresenter();
                    ImagePresenter.setQuery(query);
                    ImagePresenter.getList(true);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        this.presenter.getSuggest();
    }


    @Override
    public void setSuggest(String[] suggest) {
        this.searchView.setSuggestions(suggest);
    }

    @Override
    public void setViewPager() {
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager());
        this.viewPager.setAdapter(adapter);
    }

    @Override
    public void setTabLayout() {
        TabLayout tabLayout = ButterKnife.findById(this, R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
        final int PAGE_COUNT = 2;

        public FragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ImageListFragment.newInstance();
                case 1:
                    return ScrapListFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.tab_name1);
                case 1:
                    return getString(R.string.tab_name2);
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        if (this.searchView.isSearchOpen()) {
            this. searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onImageScrap() {
        this.viewPager.setCurrentItem(1);
        ScrapListFragment fragment = (ScrapListFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.container + ":" + 1);
        ScrapListPresenter ImagePresenter = fragment.getPresenter();
        ImagePresenter.reqeustScrapList();
    }
}
