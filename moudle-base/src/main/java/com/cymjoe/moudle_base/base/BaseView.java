package com.cymjoe.moudle_base.base;

import com.uber.autodispose.AutoDisposeConverter;

public interface BaseView {
    //显示加载框
    void showLoading();

    //隐藏加载框
    void hintLoading();

    //显示错误弹窗
    void showErrorView(String error);

    //显示空视图
    void showEmptyView(String empty);

    //展示toast
    void showToast(String msg);
    /**
     * 绑定Android生命周期 防止RxJava内存泄漏
     *
     * @param <T>
     * @return
     */
    <T> AutoDisposeConverter<T> bindAutoDispose();
}
