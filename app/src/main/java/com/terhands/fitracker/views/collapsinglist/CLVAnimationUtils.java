package com.terhands.fitracker.views.collapsinglist;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Source is from stack overflow
 * https://stackoverflow.com/questions/4946295/android-expand-collapse-animation/13381228#13381228
 */
public class CLVAnimationUtils {

    public static void expand(final View v, int durationMs) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        int duration1dpPerMs = (int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density);
        a.setDuration(durationMs > 0 ? durationMs : duration1dpPerMs);
        v.startAnimation(a);
    }

    public static void collapse(final View v, int durationMs) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        int duration1dpPerMs = (int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density);
        a.setDuration(durationMs > 0 ? durationMs : duration1dpPerMs);
        v.startAnimation(a);
    }
}
