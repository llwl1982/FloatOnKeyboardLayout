package github.ll.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

/**
 * Created by Liliang
 * Email: 53127822@qq.com
 */

public class FloatOnKeyboardLayout extends RelativeLayout {

    private static final int ID_CHILD = R.id.id_autolayout;

    private Context mContext;
    private int mOldKeyboardHeight = -1;
    private int mNowKeyboardHeight = -1;
    protected int mScreenHeight = 0;

    public FloatOnKeyboardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        setSoftKeyboardListener();
    }

        @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        int childSum = this.getChildCount();
        if(childSum > 1) {
            throw new IllegalStateException("can host only one direct child");
        } else {
            super.addView(child, index, params);
            android.widget.RelativeLayout.LayoutParams paramsChild;
            if(childSum == 0) {
                if(child.getId() < 0) {
                    child.setId(ID_CHILD);
                }

                paramsChild = (android.widget.RelativeLayout.LayoutParams)child.getLayoutParams();
                paramsChild.addRule(12);
                child.setLayoutParams(paramsChild);
            } else if(childSum == 1) {
                paramsChild = (android.widget.RelativeLayout.LayoutParams)child.getLayoutParams();
                paramsChild.addRule(2, ID_CHILD);
                child.setLayoutParams(paramsChild);
            }

        }
    }

    private void setSoftKeyboardListener() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();

                ((Activity) mContext).getWindow().getDecorView().getWindowVisibleDisplayFrame(r);

                if (mScreenHeight == 0) {
                    mScreenHeight = r.bottom;
                }
                mNowKeyboardHeight = mScreenHeight - r.bottom;

                if (mOldKeyboardHeight != -1 && mNowKeyboardHeight != mOldKeyboardHeight) {
                    if (mNowKeyboardHeight > 0) {
                        setChildViewPosition(mNowKeyboardHeight);
                    } else {
                        setChildViewPosition(mNowKeyboardHeight);
                    }
                }
                mOldKeyboardHeight = mNowKeyboardHeight;

            }
        });
    }

    private void setChildViewPosition(int keyboardHeight) {
        scrollTo(0, keyboardHeight);
    }
}
