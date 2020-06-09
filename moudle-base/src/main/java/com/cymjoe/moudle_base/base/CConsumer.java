package com.cymjoe.moudle_base.base;


import android.annotation.SuppressLint;

import com.cymjoe.moudle_base.base.APIException;
import com.cymjoe.moudle_base.entity.BaseResp;

import io.reactivex.functions.Consumer;
@SuppressLint("CheckResult")
public abstract class CConsumer<T> implements io.reactivex.functions.Consumer<BaseResp<T>> {
    @Override
    public void accept(BaseResp<T> tBaseResp) throws Exception {
        try {
            if (tBaseResp.getCode() == 1) {
                onSuccess(tBaseResp.getData());
            } else {
                throw new APIException(tBaseResp.getCode(), tBaseResp.getMsg());
            }
        } catch (Exception e) {
            throw new APIException(500, "加载失败");
        }
    }
    public abstract void onSuccess(T entity);
}
