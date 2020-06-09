package com.cymjoe.cmoudle.model;

import com.cymjoe.cmoudle.api.MainService;
import com.cymjoe.cmoudle.contract.MainContract;
import com.cymjoe.cmoudle.entity.TestDataEntity;
import com.cymjoe.moudle_base.constant.Constants;
import com.cymjoe.moudle_base.entity.BaseResp;
import com.cymjoe.moudle_base.utils.NetUtils;

import java.util.List;

import io.reactivex.Flowable;

public class MainModel implements MainContract.Model {
    @Override
    public Flowable<BaseResp<List<TestDataEntity>>> getData() {
        return NetUtils.getService(Constants.BASE_URL, MainService.class).getData();
    }
}
