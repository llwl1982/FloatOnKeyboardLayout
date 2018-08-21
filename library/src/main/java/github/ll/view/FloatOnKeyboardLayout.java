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

    private Context mContext;
    private int mOldKeyboardHeight = -1;
    private int mNowKeyboardHeight = -1;
    private int mScreenHeight = 0;
    private View mAnchor;
    private int mMarginKeyboard = 0;
    private int mAnchorOriginBottom;
    private OnKeyboardPopupListener mListener;

    public FloatOnKeyboardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        setSoftKeyboardListener();
    }

    public void setAnchor(View anchorView) {
        mAnchor = anchorView;
    }

    /**
     * set margin of anchor and soft keyboard
     * @param margin
     */
    public void setMarginKeyboard(int margin) {
        mMarginKeyboard = margin;
    }

    public void setPopupListener(OnKeyboardPopupListener listener) {
        mListener = listener;
    }

    private void setSoftKeyboardListener() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // first time
                initAnchorPosition();

                Rect r = new Rect();

                ((Activity) mContext).getWindow().getDecorView().getWindowVisibleDisplayFrame(r);

                if (mScreenHeight == 0) {
                    mScreenHeight = r.bottom;
                }

                mNowKeyboardHeight = mScreenHeight - r.bottom;

                if (mOldKeyboardHeight != -1 && mNowKeyboardHeight != mOldKeyboardHeight) {
                    handleKeyboardHeightChanged();
                }

                mOldKeyboardHeight = mNowKeyboardHeight;
            }

            private void handleKeyboardHeightChanged() {
                if (mNowKeyboardHeight > 0) {
                    keyboardPopup();

                    if (mListener != null) {
                        mListener.onKeyboardPopup(true);
                    }
                } else {
                    keyboardClose();

                    if (mListener != null) {
                        mListener.onKeyboardPopup(false);
                    }
                }
            }

            private void keyboardClose() {
                setChildViewPosition(0);
            }

            private void keyboardPopup() {
                if (mAnchor == null) {
                    setChildViewPosition(mNowKeyboardHeight);
                } else {
                    boolean isCover = (mScreenHeight - mNowKeyboardHeight < mAnchorOriginBottom + mMarginKeyboard);

                    if (isCover) {
                        int offsetY = mAnchorOriginBottom - (mScreenHeight - mNowKeyboardHeight) + mMarginKeyboard;
                        setChildViewPosition(offsetY);
                    }
                }
            }

            private void initAnchorPosition() {
                if (mOldKeyboardHeight == -1 && mAnchor != null) {
                    int[] location = new int[2];
                    mAnchor.getLocationInWindow(location);
                    mAnchorOriginBottom = location[1] + mAnchor.getHeight();
                }
            }

            private void setChildViewPosition(int offsetY) {
                scrollTo(0, offsetY);
            }
        });
    }

    public interface OnKeyboardPopupListener {
        void onKeyboardPopup(boolean isPop);
    }
}
