package net.simplyadvanced.autohidetoolbar.slowhide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import net.simplyadvanced.util.ToolbarUtils;

public abstract class HidingScrollListener2 extends RecyclerView.OnScrollListener {

    private int mToolbarOffset = 0;
    private int mToolbarHeight;

    public HidingScrollListener2(Context context) {
        mToolbarHeight = ToolbarUtils.getToolbarHeight(context);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        clipToolbarOffset();
        onMoved(mToolbarOffset);

        if ((mToolbarOffset < mToolbarHeight && dy > 0) || (mToolbarOffset > 0 && dy < 0)) {
            mToolbarOffset += dy;
        }
    }

    private void clipToolbarOffset() {
        if (mToolbarOffset > mToolbarHeight) {
            mToolbarOffset = mToolbarHeight;
        } else if (mToolbarOffset < 0) {
            mToolbarOffset = 0;
        }
    }

    public abstract void onMoved(int distance);

}