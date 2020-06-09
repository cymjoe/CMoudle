package com.cymjoe.cmoudle.presenter;

import android.annotation.SuppressLint;

import com.cymjoe.cmoudle.contract.MainContract;
import com.cymjoe.cmoudle.entity.TestDataEntity;
import com.cymjoe.lib_http.RxScheduler;
import com.cymjoe.moudle_base.base.CConsumer;
import com.cymjoe.cmoudle.model.MainModel;
import com.cymjoe.moudle_base.base.APIException;
import com.cymjoe.moudle_base.base.BasePresenter;

import java.util.List;

import io.reactivex.functions.Consumer;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    MainContract.Model model = new MainModel();

    @SuppressLint("CheckResult")
    @Override
    public void getData() {
        if (!isViewAttached()) {
            return;
        }
        model.getData().compose(RxScheduler.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new CConsumer<List<TestDataEntity>>() {
                    @Override
                    public void onSuccess(List<TestDataEntity> entity) {
                        mView.onSuccess(entity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onFail(throwable.getLocalizedMessage());
                    }
                });

    }


}
