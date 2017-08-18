package com.qx.mstarstoretv.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.json.SendingResult;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class SendingListAdater extends BaseAdapter {
    List<SendingResult.DataBean.OrderListBean> sendinglist;
    private Context context;

    public SendingListAdater(Context context, List<SendingResult.DataBean.OrderListBean> sendinglist) {
        this.context = context;
        this.sendinglist = sendinglist;
    }

    @Override
    public int getCount() {
        return sendinglist.size();
    }

    @Override
    public Object getItem(int i) {
        return sendinglist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        final SendingResult.DataBean.OrderListBean orderListBean = sendinglist.get(i);
        if (view == null) {
            view = View.inflate(context, R.layout.item_sending, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvItemSendingNumber.setText("订单编号："+orderListBean.getOrderNum());
        viewHolder.tvItemCustomerName.setText("客户名称："+orderListBean.getCustomerName());
        viewHolder.tvItemDate.setText("下单日期："+orderListBean.getOrderDate().substring(0,10));
        viewHolder.tvItemQuality.setText("成色："+orderListBean.getPurityName());//成色
        viewHolder.tvItemAmount.setText("订单件数："+orderListBean.getNumber());//订单数
        viewHolder.tvItemDeliveryAmount.setText("已出库："+orderListBean.getMoNum());//已出库
        viewHolder.tvItemFinishAmount.setText("已结算："+orderListBean.getRecNum());
        viewHolder.tvItemFinish.setText("查看结算单（"+orderListBean.getRecBillNum()+")");
//        viewHolder.tvItemFinish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, FinishTableLessActivity.class);
//                intent.putExtra("orderNumber",orderListBean.getOrderNum());
//                intent.putExtra("type","1");
//                context.startActivity(intent);
//            }
//        });
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.tv_item_sending_number)
        TextView tvItemSendingNumber;
        @Bind(R.id.tv_item_customer_name)
        TextView tvItemCustomerName;
        @Bind(R.id.tv_item_date)
        TextView tvItemDate;
        @Bind(R.id.tv_item_quality)
        TextView tvItemQuality;
        @Bind(R.id.tv_item_amount)
        TextView tvItemAmount;
        @Bind(R.id.tv_item_delivery_amount)
        TextView tvItemDeliveryAmount;
        @Bind(R.id.tv_item_finish_amount)
        TextView tvItemFinishAmount;
        @Bind(R.id.tv_item_finish)
        TextView tvItemFinish;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
