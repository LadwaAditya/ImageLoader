package com.ladwa.aditya.image.ui.detail;

import com.ladwa.aditya.image.data.model.Pokemon;
import com.ladwa.aditya.image.data.model.Statistic;
import com.ladwa.aditya.image.ui.base.MvpView;

public interface DetailMvpView extends MvpView {

    void showPokemon(Pokemon pokemon);

    void showStat(Statistic statistic);

    void showProgress(boolean show);

    void showError(Throwable error);

}