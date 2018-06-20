package com.qx.mstarstoretv.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.Global;
import com.qx.mstarstoretv.json.StoneSearchInfoResult;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class StoneSearchResultAdapter extends BaseAdapter {
    Context context;
    private final boolean isShowPrice;
    List<StoneSearchInfoResult.DataBean.StoneBean.ListBean> list;
    ChooseItemInterface chooseItem;

    public StoneSearchResultAdapter(List<StoneSearchInfoResult.DataBean.StoneBean.ListBean> list, Context context, boolean isShowPrice) {
        this.context = context;
        this.list = list;
        chooseItem = (ChooseItemInterface) context;
        this.isShowPrice = isShowPrice;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        final StoneSearchInfoResult.DataBean.StoneBean.ListBean bean = list.get(position);
        if (view == null) {
            view = View.inflate(context, R.layout.item_stone_search_result, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if(Global.isShowPopup!=0){
            viewHolder.cbIscheckStone.setText("定制");
            viewHolder.cbIscheckStone.setTextColor(context.getResources().getColor(R.color.theme_color));
        }
        else {
            viewHolder.cbIscheckStone.setText("");
        }
        viewHolder.tvItemWeight.setText(bean.getWeight());
        viewHolder.tvItemShape.setText(bean.getShape());
        viewHolder.tvItemColor.setText(bean.getColor());
        viewHolder.tvItemPurity.setText(bean.getPurity());
        viewHolder.tvItemCut.setText(bean.getCut());
        viewHolder.tvItemPolish.setText(bean.getPolishing());
        viewHolder.tvItemSymmetric.setText(bean.getSymmetric());
        viewHolder.tvItemFluorescence.setText(bean.getFluorescence());
        viewHolder.tvItemCertauth.setText(bean.getCertAuth());
        viewHolder.cbIscheckStone.setChecked(bean.ischeck());
        viewHolder.tvItemPrice.setText(bean.getPrice());
        if (isShowPrice) {
            viewHolder.tvItemPrice.setVisibility(View.VISIBLE);
            viewHolder.tvItemQuotedPrice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chooseItem.quotedPrice(bean.getId());
                }
            });
            viewHolder.tvItemQuotedPrice.setTextColor(context.getResources().getColor(R.color.theme_color));
        } else {
            viewHolder.tvItemPrice.setVisibility(View.GONE);
            viewHolder.tvItemQuotedPrice.setTextColor(context.getResources().getColor(R.color.text_color));
        }


        if (bean.ischeck()) {
            setItemBackGroundColor(viewHolder, context.getResources().getDrawable(R.drawable.table_head_yello));
        } else {
            if(position%2==0){
                setItemBackGroundColor(viewHolder, context.getResources().getDrawable(R.drawable.table_head_gray));
            } else {
                setItemBackGroundColor(viewHolder, context.getResources().getDrawable(R.drawable.table_head));
            }
        }
        viewHolder.tvItemCerauthNumber.setText(bean.getCertCode());


        return view;
    }

    private void setItemBackGroundColor(ViewHolder viewHolder, Drawable drawable) {
        viewHolder.tvItemWeight.setBackground(drawable);
        viewHolder.tvItemShape.setBackground(drawable);
        viewHolder.tvItemColor.setBackground(drawable);
        viewHolder.tvItemPurity.setBackground(drawable);
        viewHolder.tvItemCut.setBackground(drawable);
        viewHolder.tvItemPolish.setBackground(drawable);
        viewHolder.tvItemSymmetric.setBackground(drawable);
        viewHolder.tvItemFluorescence.setBackground(drawable);
        viewHolder.tvItemCertauth.setBackground(drawable);
        viewHolder.tvItemPrice.setBackground(drawable);
        viewHolder.tvItemQuotedPrice.setBackground(drawable);
        viewHolder.tvItemCerauthNumber.setBackground(drawable);
        viewHolder.llCheck.setBackground(drawable);
    }

    public String getQuotedPriceId() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            StoneSearchInfoResult.DataBean.StoneBean.ListBean bean = list.get(i);
            if (bean.ischeck()) {
                if (sb.toString().equals("")) {
                    sb.append(bean.getId() + "");
                } else {
                    sb.append("," + bean.getId());
                }

            }
        }
        return sb.toString();
    }


    public interface ChooseItemInterface {
        void quotedPrice(String id);
    }



    class ViewHolder {
        @Bind(R.id.cb_ischeck_stone)
        CheckBox cbIscheckStone;
        @Bind(R.id.ll_check)
        LinearLayout llCheck;
        @Bind(R.id.tv_item_weight)
        TextView tvItemWeight;
        @Bind(R.id.tv_item_price)
        TextView tvItemPrice;
        @Bind(R.id.tv_item_shape)
        TextView tvItemShape;
        @Bind(R.id.tv_item_color)
        TextView tvItemColor;
        @Bind(R.id.tv_item_purity)
        TextView tvItemPurity;
        @Bind(R.id.tv_item_cut)
        TextView tvItemCut;
        @Bind(R.id.tv_item_polish)
        TextView tvItemPolish;
        @Bind(R.id.tv_item_symmetric)
        TextView tvItemSymmetric;
        @Bind(R.id.tv_item_fluorescence)
        TextView tvItemFluorescence;
        @Bind(R.id.tv_item_certauth)
        TextView tvItemCertauth;
        @Bind(R.id.tv_item_cerauth_number)
        TextView tvItemCerauthNumber;
        @Bind(R.id.tv_item_quoted_price)
        TextView tvItemQuotedPrice;
        @Bind(R.id.ll_item_stone)
        LinearLayout llItemStone;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
