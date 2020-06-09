package com.cymjoe.moudle_base.base;

import android.annotation.SuppressLint;

import com.cymjoe.lib_http.RxScheduler;
import com.cymjoe.moudle_base.entity.BaseResp;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

import io.reactivex.Flowable;
import io.reactivex.FlowableConverter;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter<V extends BaseView> {
    protected V mView;

    /**
     * 绑定view
     *
     * @param mView
     */
    public void attachView(V mView) {
        this.mView = mView;
    }

    /**
     * 解绑view
     */
    public void detachView() {
        this.mView = null;
        System.gc();

    }

    /**
     * View是否绑定
     *
     * @return
     */
    public boolean isViewAttached() {
        return mView != null;
    }

    @SuppressLint("CheckResult")
    public Flowable subScribe(Flowable flowable) {
        flowable.compose(RxScheduler.Flo_io_main())
                .as(mView.bindAutoDispose());
        return flowable;
    }



}
