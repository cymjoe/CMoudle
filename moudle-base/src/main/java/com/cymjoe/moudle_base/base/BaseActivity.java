package com.cymjoe.moudle_base.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.cymjoe.moudle_base.entity.Event;
import com.cymjoe.moudle_base.utils.EventUtils;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    public T presenter;

    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(getLayoutId());
        avoidLauncherAgain();
        if (isRegisterEventBus()) {
            EventUtils.register(this);
        }
        mContext = this;
        presenter = createPresenter();
        presenter.attachView(this);
        initView(savedInstanceState);
        initData();
    }

    protected boolean isRegisterEventBus() {
        return false;
    }

    protected abstract T createPresenter();

    protected abstract void initData();

    protected abstract void initView(Bundle bundle);

    protected abstract int getLayoutId();


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(Event event) {
        if (event != null) {
            receiveEvent(event);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBus(Event event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(Event event) {

    }

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    protected void receiveStickyEvent(Event event) {

    }

    /**
     * 绑定生命周期 防止MVP内存泄漏
     *
     * @param <T>
     * @return
     */
    @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from((LifecycleOwner) mContext, Lifecycle.Event.ON_DESTROY));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        if (isRegisterEventBus()) {
            EventUtils.unregister(this);
        }
    }

    /**
     * 避免从桌面启动程序后，会重新实例化入口类的activity
     */
    public void avoidLauncherAgain() {

        if (!this.isTaskRoot()) { // 判断当前activity是不是所在任务栈的根
            Intent intent = getIntent();
            if (intent != null) {
                String action = intent.getAction();
                if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                    finish();
                }
            }
        }
    }
    @Override
    public void showLoading() {

    }

    @Override
    public void hintLoading() {

    }
    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();

    }

}
