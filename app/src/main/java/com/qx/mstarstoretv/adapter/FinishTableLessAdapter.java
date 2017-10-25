package com.qx.mstarstoretv.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.activity.FinishTableMoreActivity;
import com.qx.mstarstoretv.base.Global;
import com.qx.mstarstoretv.json.RecListBean;
import com.qx.mstarstoretv.utils.ToastManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class FinishTableLessAdapter extends BaseAdapter {
    private final String type;
    Context context;
    List<RecListBean> list;

    public FinishTableLessAdapter(Context context, List<RecListBean> list,String type) {
        this.context = context;
        this.list = list;
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
        final RecListBean recListBean = list.get(i);
        if (view == null) {
            view = View.inflate(context, R.layout.item_finish_table_one, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvFinishNumber.setText("结算单号：" + recListBean.getRecNum());
        viewHolder.tvFinishCustomerName.setText("客户名称：" + recListBean.getCustomerName());
        viewHolder.tvFinishDate.setText("下单日期：" + recListBean.getRecDate());
        viewHolder.tvFinishQuality.setText("成色：" + recListBean.getPurityName());
        viewHolder.tvFinishAmount.setText("数量：" + recListBean.getNumber());
        viewHolder.tvFinisShSumMoney.setText("¥" + recListBean.getTotalPrice());
        if ("1".equals(Global.isMainAccount)&&1==Global.isShowCost) {
            viewHolder.tvFinisShSumMoney.setVisibility(View.VISIBLE);
            viewHolder.tvFinishSumMoneyTv.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tvFinisShSumMoney.setVisibility(View.GONE);
            viewHolder.tvFinishSumMoneyTv.setVisibility(View.GONE);
        }
        FinishTableLessTwoAdapter finishTableLessTwoAdapter = new FinishTableLessTwoAdapter(context, recListBean.getMoList(),type);
        viewHolder.lvSendingTables.setAdapter(finishTableLessTwoAdapter);
        viewHolder.rlGotoFinishMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(1==Global.isShowCost){
                    Intent intent = new Intent(context, FinishTableMoreActivity.class);
                    intent.putExtra("recNumber", recListBean.getRecNum() + "");
                    intent.putExtra("type", type);
                    ((Activity) context).startActivity(intent);
                }else {
                    ToastManager.showToastReal("未显示价钱，无权限查看");
                }
            }
        });
        return view;

    }

    class ViewHolder {
        @Bind(R.id.rl_goto_finish_more)
        RelativeLayout rlGotoFinishMore;
        @Bind(R.id.tv_finish_number)
        TextView tvFinishNumber;
        @Bind(R.id.ib_right)
        ImageView ivRight;
        @Bind(R.id.tv_finish_customer_name)
        TextView tvFinishCustomerName;
        @Bind(R.id.tv_number)
        TextView tvNumber;
        @Bind(R.id.tv_finish_date)
        TextView tvFinishDate;
        @Bind(R.id.tv_finish_quality)
        TextView tvFinishQuality;
        @Bind(R.id.tv_finish_amount)
        TextView tvFinishAmount;
        @Bind(R.id.tv_finish_sum_money_tv)
        TextView tvFinishSumMoneyTv;
        @Bind(R.id.tv_finisSh_sum_money)
        TextView tvFinisShSumMoney;
        @Bind(R.id.lv_sending_tables)
        ListView lvSendingTables;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
