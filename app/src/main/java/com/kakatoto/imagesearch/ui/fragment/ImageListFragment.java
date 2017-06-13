package com.kakatoto.imagesearch.ui.fragment;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.kakatoto.imagesearch.R;
import com.kakatoto.imagesearch.adapter.ImageListRecyclerAdapter;
import com.kakatoto.imagesearch.presenter.fragment.ImageListPresenter;
import com.kakatoto.imagesearch.presenter.fragment.impl.IImageListContract;
import com.kakatoto.imagesearch.ui.MainActivity;
import com.kakatoto.imagesearch.util.CommonUtil;
import com.kakatoto.imagesearch.util.view.recycler.EndlessRecyclerOnScrollListener;
import com.kakatoto.imagesearch.util.view.recycler.RecyclerDecGrid;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ImageListFragment extends Fragment implements IImageListContract.View {
    private ImageListPresenter presenter;

    @BindView(R.id.tag_group)
    TagView tagGroup;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.swipRefresh)
    SwipeRefreshLayout swipeRefreshLayout;


    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;

    public static ImageListFragment newInstance() {
        ImageListFragment fragment = new ImageListFragment();
        return fragment;
    }

    public ImageListPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(ImageListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_list, container, false);
        ButterKnife.bind(this, view);

        this.presenter = new ImageListPresenter(getActivity());
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
        ImageListRecyclerAdapter adapter = new ImageListRecyclerAdapter(getActivity());
        this.presenter.setAdapter(adapter);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        this.recyclerView.addItemDecoration(new RecyclerDecGrid(2, CommonUtil.convertDpToPx(getActivity(), 5), true));
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (!swipeRefreshLayout.isRefreshing())
                    presenter.onLoadMore();

            }
        });

        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                presenter.onRefesh();
            }
        });
    }

    @Override
    public void setTagLayout() {
        this.tagGroup.setOnTagDeleteListener(new TagView.OnTagDeleteListener() {
            @Override
            public void onTagDeleted(TagView tagView, Tag tag, int i) {
                tagView.remove(i);
                presenter.getList(true);
            }
        });
    }

    @Override
    public void addSearchTag(String query) {
        Tag tag = new Tag(query);
        tag.isDeletable = true;
        tagGroup.addTag(tag);
    }

    @Override
    public List getTagList() {
        return tagGroup.getTags();
    }


    @Override
    public void showScrapAlert(final int pos) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setMessage(getString(R.string.alert_save_message))
                .setPositiveButton(getString(R.string.alert_positive_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.onScrapImage(pos);
                    }
                })
                .setNegativeButton(getString(R.string.alert_negative_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create().show();
    }

    @Override
    public void showLoding() {
        avi.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoding() {
        avi.setVisibility(View.GONE);
    }
}
