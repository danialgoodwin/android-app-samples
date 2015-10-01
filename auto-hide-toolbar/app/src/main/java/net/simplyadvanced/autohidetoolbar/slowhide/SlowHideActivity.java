package net.simplyadvanced.autohidetoolbar.slowhide;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import net.simplyadvanced.autohidetoolbar.R;
import net.simplyadvanced.autohidetoolbar.RecyclerBasicAdapter;
import net.simplyadvanced.util.ToolbarUtils;

import java.util.ArrayList;
import java.util.List;

// More info: http://mzgreen.github.io/2015/02/28/How-to-hideshow-Toolbar-when-list-is-scrolling%28part2%29/
public class SlowHideActivity extends ActionBarActivity {

    private LinearLayout mToolbarContainer;
    private int mToolbarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.AppThemeGreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slow_hide);

        mToolbarContainer = (LinearLayout) findViewById(R.id.toolbarContainer);
        initToolbar();
        initRecyclerView();
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle(getString(R.string.app_name));
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mToolbarHeight = ToolbarUtils.getToolbarHeight(this);
    }

    private void initRecyclerView() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        int paddingTop = ToolbarUtils.getToolbarHeight(this) + ToolbarUtils.getTabsHeight(this);
        recyclerView.setPadding(recyclerView.getPaddingLeft(), paddingTop, recyclerView.getPaddingRight(), recyclerView.getPaddingBottom());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerBasicAdapter recyclerAdapter = new RecyclerBasicAdapter(createItemList());
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView.setOnScrollListener(new HidingScrollListener3(this) {

            @Override
            public void onMoved(int distance) {
                mToolbarContainer.setTranslationY(-distance);
            }

            @Override
            public void onShow() {
                mToolbarContainer.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
            }

            @Override
            public void onHide() {
                mToolbarContainer.animate().translationY(-mToolbarHeight).setInterpolator(new AccelerateInterpolator(2)).start();
            }

        });
    }

    private List<String> createItemList() {
        List<String> itemList = new ArrayList<>();
        for(int i=0;i<20;i++) {
            itemList.add("Item "+i);
        }
        return itemList;
    }
}