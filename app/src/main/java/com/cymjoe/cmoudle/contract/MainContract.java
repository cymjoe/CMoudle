package com.cymjoe.cmoudle.contract;

import com.cymjoe.cmoudle.entity.TestDataEntity;
import com.cymjoe.moudle_base.base.BaseView;
import com.cymjoe.moudle_base.entity.BaseResp;

import java.util.List;

import io.reactivex.Flowable;

public interface MainContract {
    interface Model {

        Flowable<BaseResp<List<TestDataEntity>>> getData();
    }

    interface View extends BaseView {

        void onSuccess(List<TestDataEntity> entity);

        void onFail(String error);
    }

    interface Presenter {
        void getData();
    }
}
