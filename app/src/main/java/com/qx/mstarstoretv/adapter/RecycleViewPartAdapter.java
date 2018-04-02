package com.qx.mstarstoretv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.json.ModelPartsBean;
import com.qx.mstarstoretv.net.ImageLoadOptions;
import com.qx.mstarstoretv.utils.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/12/8 0008.
 */

public class RecycleViewPartAdapter extends RecyclerView.Adapter<RecycleViewPartAdapter.ViewHolder> {
    private final Context context;
    private List<ModelPartsBean> list;
    private List<String> countList;
    private MyItemClickListener myItemClickListener;


    public RecycleViewPartAdapter(Context context ,List<ModelPartsBean> list, List<String> countList, MyItemClickListener myItemClickListener) {
        this.list = list;
        this.context =context;
        this.countList = countList;
        this.myItemClickListener = myItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_part, null);
        ViewHolder viewHolder = new ViewHolder(view, myItemClickListener);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //stone位置
        if (list.size() - 1 == position) {
            holder.mTvStone.setVisibility(View.VISIBLE);
            holder.mTvStone.setText(list.get(position).getTitle());
            return;
        } else {
            holder.mTvStone.setVisibility(View.GONE);
        }


        ImageLoader.getInstance().displayImage(list.get(position).getPicm(), holder.mProduct, ImageLoadOptions.getOptionsHight());

        if (StringUtils.isEmpty(list.get(position).getPid())) {
            holder.mTvName.setTextColor(context.getResources().getColor(R.color.text_color3));
        }else {
            holder.mTvName.setTextColor(context.getResources().getColor(R.color.text_color));
        }

        if (StringUtils.isEmpty(countList.get(position))) {
            holder.mTvName.setText(list.get(position).getTitle());
        } else {
            holder.mTvName.setText(list.get(position).getTitle()+"/"+countList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mProduct;

//        TextView mTvAmount;
        TextView mTvName;
        TextView mTvStone;
        View rootView;
        private MyItemClickListener mListener;

        public ViewHolder(View itemView, MyItemClickListener listener) {
            super(itemView);
            mProduct = (ImageView) itemView.findViewById(R.id.iv_item_part);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            mTvStone = (TextView) itemView.findViewById(R.id.tv_stone);
            rootView = itemView.findViewById(R.id.root_view);
            this.mListener = listener;
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(v, getPosition());
                    }
                }
            });
        }

    }


    public interface MyItemClickListener {
        public void onItemClick(View view, int postion);
    }

}
