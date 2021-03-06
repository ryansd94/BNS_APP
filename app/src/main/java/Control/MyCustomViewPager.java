package Control;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyCustomViewPager extends ViewPager {
    public MyCustomViewPager(@NonNull Context context) {
        super(context);
    }

    private boolean enabled;
    public MyCustomViewPager(Context context, AttributeSet attrs){
        super(context, attrs);
        this.enabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(this.enabled){
            return super.onTouchEvent(event);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event){
        if(this.enabled){
            return super.onInterceptTouchEvent(event);
        }
        return false;
    }

    public void setPagingEnabled(boolean enabled){
        this.enabled = enabled;
    }
}
