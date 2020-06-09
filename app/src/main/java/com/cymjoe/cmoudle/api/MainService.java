package com.cymjoe.cmoudle.api;

import com.cymjoe.cmoudle.entity.TestDataEntity;
import com.cymjoe.moudle_base.entity.BaseResp;
import com.cymjoe.moudle_base.constant.Constants;

import org.intellij.lang.annotations.Flow;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MainService {
    @GET(Constants.DATA)
    Flowable<BaseResp<List<TestDataEntity>>> getData();
}
