package cn.hx.hxand.base;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import cn.hx.hxand.R;
import cn.hx.hxand.layout.SwipeBackLayout;

/**
 * Created by huangxiang on 16/5/15.
 */
public class BaseActivity extends AppCompatActivity {
    private SwipeBackLayout swipeBackLayout;
    private ImageView ivShadow;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(getContainer());
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        swipeBackLayout.addView(view);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(getContainer());
        swipeBackLayout.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(getContainer());
        swipeBackLayout.addView(view, params);
    }

    private View getContainer() {
        RelativeLayout container = new RelativeLayout(this);
        swipeBackLayout = new SwipeBackLayout(this);
        swipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.LEFT);
        ivShadow = new ImageView(this);
        ivShadow.setBackgroundColor(Color.argb(128, 0, 0, 0));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        container.addView(ivShadow, params);
        container.addView(swipeBackLayout);
        swipeBackLayout.setOnSwipeBackListener(new SwipeBackLayout.SwipeBackListener() {
            @Override
            public void onViewPositionChanged(float fractionAnchor, float fractionScreen) {
                ivShadow.setAlpha(1 - fractionScreen);
            }
        });
        return container;
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return swipeBackLayout;
    }
}
