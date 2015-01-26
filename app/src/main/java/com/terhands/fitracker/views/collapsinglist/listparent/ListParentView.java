package com.terhands.fitracker.views.collapsinglist.listparent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ListParentView extends Button {

    private List<OnClickListener> listeners = new ArrayList<>();

    public ListParentView(Context context) {
        super(context);
    }

    public ListParentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setOnClickListener(OnClickListener onClicked) {
        listeners.add(onClicked);

        super.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                for(OnClickListener listener : listeners) {
                    listener.onClick(v);
                }
            }
        });
    }
}
