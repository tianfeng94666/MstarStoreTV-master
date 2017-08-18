package com.qx.mstarstoretv.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.json.SearchResultResult;
import com.qx.mstarstoretv.viewutils.CustomGridView;
import com.qx.mstarstoretv.viewutils.MyLinearLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class SearchResultAdapter extends BaseAdapter {
    Context context;
    private List<SearchResultResult.DataBean.OrderListBean> list;

    public SearchResultAdapter(Context context, List<SearchResultResult.DataBean.OrderListBean> list) {
        this.list = list;
        this.context = context;
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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        SearchResultResult.DataBean.OrderListBean bean = list.get(position);
        if (view == null) {
            view = View.inflate(context, R.layout.item_search_results, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvOrderNumber.setText(bean.getOrderNum()+"");
        viewHolder.tvCustomerName.setText(bean.getCustomerName());
        viewHolder.tvStartDate.setText(bean.getOrderDate());
        viewHolder.tvQuality.setText(bean.getPurityName());
        viewHolder.tvGoldPrice.setText(bean.getGoldPrice()+"");
        viewHolder.tvAmount.setText(bean.getNumber()+"");

        return view;
    }

     class ViewHolder {
        @Bind(R.id.specifiction_content)
        MyLinearLayout specifictionContent;
        @Bind(R.id.tv_situation)
        TextView tvSituation;
        @Bind(R.id.tv_order_number)
        TextView tvOrderNumber;
        @Bind(R.id.tv1)
        TextView tv1;
        @Bind(R.id.tv_customer_name)
        TextView tvCustomerName;
        @Bind(R.id.tv2)
        TextView tv2;
        @Bind(R.id.tv_start_date)
        TextView tvStartDate;
        @Bind(R.id.tv_quality_tv)
        TextView tvQualityTv;
        @Bind(R.id.tv_quality)
        TextView tvQuality;
        @Bind(R.id.tv_gold_price_tv)
        TextView tvGoldPriceTv;
        @Bind(R.id.tv_gold_price)
        TextView tvGoldPrice;
        @Bind(R.id.tv_amount_tv)
        TextView tvAmountTv;
        @Bind(R.id.tv_amount)
        TextView tvAmount;
        @Bind(R.id.inner_lny_container)
        LinearLayout innerLnyContainer;
        @Bind(R.id.img_container)
        CustomGridView imgContainer;
        @Bind(R.id.linear_container_view)
        LinearLayout linearContainerView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
