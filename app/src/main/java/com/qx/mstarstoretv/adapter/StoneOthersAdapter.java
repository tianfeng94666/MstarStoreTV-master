package com.qx.mstarstoretv.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.json.StoneSearchResult;
import com.qx.mstarstoretv.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class StoneOthersAdapter extends BaseAdapter {
    private Context context;
    private StoneSearchResult.DataBean dataBean;
    List<List<String>> list;
    List<String> titleList;
    List<boolean[]> isCheckList;

    public StoneOthersAdapter(Context context, StoneSearchResult.DataBean data) {
        this.context = context;
        this.dataBean = data;
        list = new ArrayList<>();
        titleList = new ArrayList<>();
        isCheckList = new ArrayList<>();
        list.add(0, data.getCut().getValues());
        list.add(1, data.getPolishing().getValues());
        list.add(2, data.getSymmetric().getValues());
        list.add(3, data.getFluorescence().getValues());
        titleList.add(0, data.getCut().getTitle());
        titleList.add(1, data.getPolishing().getTitle());
        titleList.add(2, data.getSymmetric().getTitle());
        titleList.add(3, data.getFluorescence().getTitle());
        isCheckList.add(0, new boolean[data.getCut().getValues().size()]);
        isCheckList.add(1, new boolean[data.getPolishing().getValues().size()]);
        isCheckList.add(2, new boolean[data.getSymmetric().getValues().size()]);
        isCheckList.add(3, new boolean[data.getFluorescence().getValues().size()]);
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
        final List<String> item = list.get(position);
        final boolean[] ischecks = isCheckList.get(position);
        if (view == null) {
            view = View.inflate(context, R.layout.item_stone_others, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.llItem.removeAllViews();
        for (int i = 0; i < item.size(); i++) {
            TextView textView = addTExt(item.get(i), ischecks[i]);
            textView.setTag(i + "");
            viewHolder.llItem.addView(textView, i);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ischecks[Integer.parseInt(v.getTag().toString())] = !ischecks[Integer.parseInt(v.getTag().toString())];
                    notifyDataSetChanged();
                }
            });
        }
        viewHolder.tvTitle.setText(titleList.get(position));
        return view;
    }

    public TextView addTExt(String string, boolean isCheck) {
        TextView tv = new TextView(context);
        tv.setText(string);
        tv.setTextColor(context.getResources().getColor(R.color.text_color));
        tv.setTextSize(UIUtils.sp2px(24));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(UIUtils.dip2px(160), UIUtils.dip2px(65),1);
        params.setMargins(0, UIUtils.dip2px(10), UIUtils.dip2px(10), UIUtils.dip2px(10));
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
        if (isCheck) {
            tv.setBackgroundResource(R.drawable.corners_red_bg);
            tv.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            tv.setBackgroundResource(R.drawable.corners_white_bg);
            tv.setTextColor(context.getResources().getColor(R.color.text_color));
        }

        return tv;
    }

    public String getChooseResult(int j) {
        StringBuilder sb = new StringBuilder();
        boolean[] ischeck = isCheckList.get(j);
        List<String> listString = list.get(j);
        for (int i = 0; i < ischeck.length; i++) {
            if (ischeck[i]) {
                if (sb.toString().equals("")) {
                    sb.append(listString.get(i));
                } else {
                    sb.append("," + listString.get(i));
                }
            }
        }
        return sb.toString();
    }

    class ViewHolder {
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.ll_item)
        LinearLayout llItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
