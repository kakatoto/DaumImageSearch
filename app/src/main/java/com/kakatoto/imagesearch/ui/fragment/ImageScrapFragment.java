package com.kakatoto.imagesearch.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kakatoto.imagesearch.R;
import com.kakatoto.imagesearch.ui.MainActivity;

import butterknife.ButterKnife;

/**
 * Created by hwoh on 2017. 6. 12..
 */

public class ImageScrapFragment extends Fragment{
    private final static String TAG = ImageScrapFragment.class.getSimpleName();

    public static ImageScrapFragment newInstance() {
        ImageScrapFragment fragment = new ImageScrapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_scrap, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
