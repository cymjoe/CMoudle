package com.cymjoe.cmoudle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cymjoe.cmoudle.contract.MainContract;
import com.cymjoe.cmoudle.entity.TestDataEntity;
import com.cymjoe.cmoudle.presenter.MainPresenter;
import com.cymjoe.lib_imgutils.ImgUtils;
import com.cymjoe.moudle_base.base.BaseActivity;
import com.cymjoe.moudle_base.base.BasePresenter;
import com.uber.autodispose.AutoDisposeConverter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.FlowableConverter;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }


    @Override
    protected void initData() {
        presenter.getData();
    }

    @Override
    protected void initView(Bundle bundle) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(List<TestDataEntity> entity) {
        Log.d("PPP", entity.get(0).toString());
    }

    @Override
    public void onFail(String error) {
        showToast(error);
    }


    @Override
    public void showErrorView(String error) {

    }

    @Override
    public void showEmptyView(String empty) {

    }


}
