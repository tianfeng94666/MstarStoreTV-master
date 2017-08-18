package com.qx.mstarstoretv.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.activity.DeliveryTableActivity;
import com.qx.mstarstoretv.json.RecListBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class FinishTableLessTwoAdapter extends BaseAdapter {
    private final String type;
    private Context context;
    private List<RecListBean.MoListBean> list;

    public FinishTableLessTwoAdapter(Context context, List<RecListBean.MoListBean> list,String type) {
        this.list = list;
        this.context = context;
        this.type = type;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        final RecListBean.MoListBean bean = list.get(i);
        if (view == null) {
            view = View.inflate(context, R.layout.item_finished_less, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvItemFinishDeliveryNumber.setText("出库单号"+bean.getMoNum());
        viewHolder.tvItemFinishDeliveryDate.setText("出库日期:"+bean.getMoDate());
        viewHolder.tvItemFinishDeliveryPrice.setText("价格："+bean.getTotalPrice()+"");
        viewHolder.tvItemFinishDeliveryAmount.setText("数量："+bean.getNumber());
        viewHolder.rlGotoDeliveryTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DeliveryTableActivity.class);
                intent.putExtra("momNumber",bean.getMoNum()+"");
                intent.putExtra("type",type);
                ( (Activity)context).startActivity(intent);
            }
        });
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.rl_goto_delivery_table)
        RelativeLayout rlGotoDeliveryTable;
        @Bind(R.id.tv_item_finish_delivery_number)
        TextView tvItemFinishDeliveryNumber;
        @Bind(R.id.tv_item_finish_delivery_date)
        TextView tvItemFinishDeliveryDate;
        @Bind(R.id.tv_item_finish_delivery_price)
        TextView tvItemFinishDeliveryPrice;
        @Bind(R.id.tv_item_finish_delivery_amount)
        TextView tvItemFinishDeliveryAmount;
        @Bind(R.id.iv_item_goto_delivery)
        ImageView ivItemGotoDelivery;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
