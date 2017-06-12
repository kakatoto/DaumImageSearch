package com.kakatoto.imagesearch.ui.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kakatoto.imagesearch.R;
import com.kakatoto.imagesearch.presenter.fragment.ImageListPresenter;
import com.kakatoto.imagesearch.presenter.fragment.impl.IImageListContract;
import com.kakatoto.imagesearch.ui.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by darong on 2017. 6. 12..
 */

public class ImageListFragment extends Fragment implements IImageListContract.View{
    private final static String TAG = ImageListFragment.class.getSimpleName();

    private ImageListPresenter presenter;

    public static ImageListFragment newInstance() {
        ImageListFragment fragment = new ImageListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_list, container, false);
        ButterKnife.bind(this, view);

        this.presenter = new ImageListPresenter();
        this.presenter.attatch(this);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.deatch();
    }

    @OnClick(R.id.button)
    public void onClick(){
        this.presenter.reqeustImageList("");
    }
}
