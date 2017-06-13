package com.kakatoto.imagesearch.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kakatoto.imagesearch.R;
import com.kakatoto.imagesearch.adapter.ScrapListRecyclerAdapter;
import com.kakatoto.imagesearch.presenter.fragment.ImageListPresenter;
import com.kakatoto.imagesearch.presenter.fragment.ScrapListPresenter;
import com.kakatoto.imagesearch.presenter.fragment.impl.IScrapListContract;
import com.kakatoto.imagesearch.util.CommonUtil;
import com.kakatoto.imagesearch.util.view.recycler.RecyclerDecGrid;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;



public class ScrapListFragment extends Fragment implements IScrapListContract.View {
    private ScrapListPresenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;

    public static ScrapListFragment newInstance() {
        ScrapListFragment fragment = new ScrapListFragment();
        return fragment;
    }
    public ScrapListPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_scrap, container, false);
        ButterKnife.bind(this, view);
        this.presenter = new ScrapListPresenter();
        this.presenter.attatch(this);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.deatch();
    }

    @Override
    public void setRecycler() {
        ScrapListRecyclerAdapter adapter = new ScrapListRecyclerAdapter(getActivity());
        this.presenter.setAdapter(adapter);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        this.recyclerView.addItemDecoration(new RecyclerDecGrid(2, CommonUtil.convertDpToPx(getActivity(), 5), true));
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setAdapter(adapter);
    }

    @Override
    public void showDeleteScrap(final int pos) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setMessage(getString(R.string.alert_delete_message))
                .setPositiveButton(getString(R.string.alert_positive_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.onDeleteScrap(pos);
                    }
                })
                .setNegativeButton(getString(R.string.alert_negative_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create().show();
    }
}
