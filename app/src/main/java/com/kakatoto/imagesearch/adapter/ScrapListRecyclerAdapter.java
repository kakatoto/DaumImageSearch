package com.kakatoto.imagesearch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kakatoto.imagesearch.R;
import com.kakatoto.imagesearch.model.ScrapBean;
import com.kakatoto.imagesearch.util.CommonUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ScrapListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = ScrapListRecyclerAdapter.class.getSimpleName();
    //Data
    private List<ScrapBean> itemList;
    private Context context;
    private OnItemSelectListener onItemSelectListener;


    public ScrapListRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setItemList(List<ScrapBean> itemList) {
        this.itemList = itemList;
    }

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_image, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int pos) {
        final ViewHolder holder = (ViewHolder) viewHolder;

        String thumbnailUrl = "";

        ScrapBean item = itemList.get(pos);

        if (!CommonUtil.isNull(item.getThumbnail()))
            thumbnailUrl = item.getThumbnail();


        Glide.with(context).load(thumbnailUrl)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.imgThumbnail);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelectListener.onItemSelect(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgThumbnail)
        ImageView imgThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemSelectListener{
        void onItemSelect(int pos);
    }
}
