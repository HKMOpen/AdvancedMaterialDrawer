package de.madcyph3r.materialnavigationdrawer.menu.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import de.madcyph3r.materialnavigationdrawer.R;

/**
 * Created by marc on 15.07.2015.
 */
public abstract class MaterialMenuItem {

    private boolean bottom;

    public boolean isBottom() {
        return bottom;
    }

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }


    public abstract View getView();

    protected View inflatView(@LayoutRes final int res, Context ctx) {
        return LayoutInflater.from(ctx).inflate(res, null, false);
    }


    protected int getItemLayout(TypedArray values, int defaultResId) {
        int resId = values.getResourceId(R.styleable.MaterialSection_section_item_layout, -1);

        if (resId == -1) {
            Log.d("not", "got -1");
            return defaultResId;
        } else {
            Log.d("not", "resId > -1");
            return resId;
        }
    }

}
