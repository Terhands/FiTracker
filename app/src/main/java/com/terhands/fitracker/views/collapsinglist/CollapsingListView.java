package com.terhands.fitracker.views.collapsinglist;

import android.view.View;
import android.view.ViewGroup;

import com.terhands.fitracker.views.collapsinglist.listchild.ListChildView;
import com.terhands.fitracker.views.collapsinglist.listparent.ListParentView;

/**
 * Attach to a parent view and a child view to have clicks of the parent toggle the child view
 */
public class CollapsingListView {

    private final ListParentView parent;
    private final ListChildView child;

    public CollapsingListView(ListParentView parent, ListChildView child, boolean isChildVisible) {
        this.parent = parent;
        this.child = child;

        init(isChildVisible);
    }

    public CollapsingListView(ViewGroup container, ListParentView parent, ListChildView child, boolean isChildVisible) {
        this.parent = parent;
        this.child = child;

        container.addView(parent);
        container.addView(child);

        init(isChildVisible);
    }

    private void init(boolean isChildVisible) {
        child.setVisibility(isChildVisible);
        parent.setOnClickListener(onParentClicked);
    }

    private View.OnClickListener onParentClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            child.toggleVisibility();
        }
    };
}
