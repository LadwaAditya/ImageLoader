package com.ladwa.aditya.image.ui.main;


import java.util.List;

import javax.inject.Inject;

import com.ladwa.aditya.image.data.DataManager;
import com.ladwa.aditya.image.injection.ConfigPersistent;
import com.ladwa.aditya.image.ui.base.BasePresenter;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void getPokemon(int limit) {
        checkViewAttached();
        getMvpView().showProgress(true);
        Subscription subs = mDataManager.getPokemonList(limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleSubscriber<List<String>>() {
                    @Override
                    public void onSuccess(List<String> pokemon) {
                        getMvpView().showProgress(false);
                        getMvpView().showPokemon(pokemon);
                    }

                    @Override
                    public void onError(Throwable error) {
                        getMvpView().showProgress(false);
                        getMvpView().showError(error);
                    }
                });
        addSubscription(subs);
    }

}