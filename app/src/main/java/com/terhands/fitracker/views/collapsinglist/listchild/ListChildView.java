package com.terhands.fitracker.views.collapsinglist.listchild;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.terhands.fitracker.views.collapsinglist.CLVAnimationUtils;

/**
 * Defaults to being in an expanded state
 */
public class ListChildView extends LinearLayout {

    private ViewState viewState;

    public ListChildView(Context context) {
        super(context);

        init();
    }

    public ListChildView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        viewState = new ViewState(true);
    }

    public void setVisibility(boolean isVisible) {
        viewState = new ViewState(isVisible);
        if(isVisible) {
            setVisibility(View.VISIBLE);
        } else {
            setVisibility(View.GONE);
        }
    }

    public void toggleVisibility() {
        viewState.toggleVisibility();

        if(viewState.isVisible()) {
            CLVAnimationUtils.expand(this);
        } else {
            CLVAnimationUtils.collapse(this);
        }
    }
}
