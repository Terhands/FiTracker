package com.terhands.fitracker.views.collapsinglist.listchild;

public class ViewState {

    private boolean isVisible;

    public ViewState(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public void toggleVisibility() {
        isVisible = !isVisible;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
