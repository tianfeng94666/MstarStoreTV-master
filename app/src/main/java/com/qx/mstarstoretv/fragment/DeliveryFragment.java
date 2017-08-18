package com.qx.mstarstoretv.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.activity.SearchOrderMainActivity;
import com.qx.mstarstoretv.adapter.FinishTableLessAdapter;
import com.qx.mstarstoretv.base.BaseFragment;
import com.qx.mstarstoretv.json.SearchOrderMainResult;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class DeliveryFragment extends BaseFragment {


    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView ivRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.lv_sending_tables)
    ListView lvSendingTables;
    private String orderNumber;
    private FinishTableLessAdapter finishTableLessAdapter;
    private SearchOrderMainResult.DataBean.OrderSendedBean sendBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.activity_finish_table_less, null);
        ButterKnife.bind(this, view);
        init();
        getDate();
        return view;
    }


    private void init() {
        idRelTitle.setVisibility(View.GONE);
    }

    private void getDate() {
        sendBean = ((SearchOrderMainActivity) getActivity()).getOrderSendedBean();
        if (sendBean != null&&sendBean.getRecList()!=null) {
            finishTableLessAdapter = new FinishTableLessAdapter(getActivity(), sendBean.getRecList(),2+"");
            lvSendingTables.setAdapter(finishTableLessAdapter);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
