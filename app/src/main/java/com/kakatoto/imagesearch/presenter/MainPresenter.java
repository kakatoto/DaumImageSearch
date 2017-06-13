package com.kakatoto.imagesearch.presenter;

import com.kakatoto.imagesearch.ApplicationClass;
import com.kakatoto.imagesearch.model.Suggest;
import com.kakatoto.imagesearch.presenter.impl.IMainContract;
import com.kakatoto.imagesearch.realm.repo.SuggestRepo;
import com.kakatoto.imagesearch.realm.repo.impl.ISuggestRepo;
import com.kakatoto.imagesearch.ui.MainActivity;

import javax.inject.Inject;

import io.realm.RealmResults;

/**
 * Created by hwoh on 2017. 6. 12..
 */

public class MainPresenter implements IMainContract.Presenter {
    private IMainContract.View view;

    @Inject
    SuggestRepo suggestRepo;

    public MainPresenter() {
        ApplicationClass.getInstance().getAppComponent().inject(this);
    }

    @Override
    public void attatch(IMainContract.View view) {
        this.view = view;
        this.view.setToolBar();
        this.view.setViewPager();
        this.view.setTabLayout();
        this.view.setSearchView();
    }

    @Override
    public void deatch() {
        this.view = null;
    }

    @Override
    public void addSuggest(String query) {
        suggestRepo.addSuggestObject(new Suggest(query), new ISuggestRepo.OnSaveSuggestCallBack() {
            @Override
            public void onSuccess() {
                getSuggest();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @Override
    public void getSuggest() {
        suggestRepo.getAllSuggest(new ISuggestRepo.OnGetSearchSuggestCallBack() {
            @Override
            public void onSuccess(RealmResults<Suggest> suggests) {
                String[] suggestArray = new String[suggests.size()];
                for(int i=0;i<suggests.size();i++){
                    suggestArray[i] = suggests.get(i).getSuggest();
                }

                view.setSuggest(suggestArray);
            }

            @Override
            public void onError(String message) {

            }
        });
    }
}
