package com.qx.mstarstoretv.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.activity.ConfirmOrderActivity;
import com.qx.mstarstoretv.activity.CustomMadeActivity;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.base.BaseFragment;
import com.qx.mstarstoretv.json.OrderWaitResult;
import com.qx.mstarstoretv.net.ImageLoadOptions;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;
import com.qx.mstarstoretv.viewutils.CustomAdapter;
import com.qx.mstarstoretv.viewutils.CustomListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FragProductionFragment extends BaseFragment {

    List<OrderWaitResult.DataBean.OrderListBean.ListBean> listData = new ArrayList<>();
    ListViewAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_list_layout, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ListView listView = (ListView) view.findViewById(R.id.gridview);
        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(getActivity(), ConfirmOrderActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("type",2);
                bundle.putString("id",listData.get(i).getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
       // loadNetData();
    }

    private void loadNetData() {
        String url = AppURL.URL_ORDER_WAITCHECK + "tokenKey=" + BaseApplication.getToken();
        L.e("获取地址" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(getActivity(), url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("loadNetData  "+result);
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error=jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    OrderWaitResult orderWaitResult = new Gson().fromJson(result, OrderWaitResult.class);
                    if(orderWaitResult.getData()==null){
                        return;
                    }
                    OrderWaitResult.DataBean.OrderListBean orderList = orderWaitResult.getData().getOrderList();
                    listData = orderList.getList();
                    if(listData==null){
                        L.e("刷新");
                        listData=new ArrayList<>();
                    }
                    adapter.notifyDataSetChanged();
                }
                else if (error.equals("2")) {
                     loginToServer(CustomMadeActivity.class);
                } else {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    L.e(message);
                    ToastManager.showToastReal(message);
                }
            }

            @Override
            public void onFail(String fail) {

            }
        });


    }



    public class ListViewAdapter extends BaseAdapter {


        public ListViewAdapter() {
        }

        @Override
        public int getCount() {
            return listData==null? 0: listData.size();
        }

        @Override
        public Object getItem(int position) {
            return listData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.adapter_order_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            OrderWaitResult.DataBean.OrderListBean.ListBean listEntity = listData.get(position);
            viewHolder.tvOrderNumber.setText(listEntity.getOrderNum());
            viewHolder.idCusName.setText(listEntity.getCustomerName());
            viewHolder.idStartDate.setText(listEntity.getOrderDate());
            viewHolder.idEndDate.setText(listEntity.getModifyDate());
            viewHolder.idRemarks.setText(listEntity.getOtherInfo());
            viewHolder.tvTotalAmount.setText("参考总价 "+listEntity.getTotalPrice());
            viewHolder.idTvNeed.setText("定金 "+listEntity.getNeedPayPrice());
            CustomTypeListViewAdapter customListViewAdapter = new CustomTypeListViewAdapter(listEntity.getPics());
            viewHolder.customListView.setDividerHeight(10);
            viewHolder.customListView.setDividerWidth(10);
            viewHolder.customListView.setAdapter(customListViewAdapter);

            return convertView;
        }


        class ViewHolder {
            @Bind(R.id.tv_order_number)
            TextView tvOrderNumber;
            @Bind(R.id.tv_situation)
            TextView tvSituation;
            @Bind(R.id.id_cus_name)
            TextView idCusName;
            @Bind(R.id.id_start_date)
            TextView idStartDate;
            @Bind(R.id.id_end_date)
            TextView idEndDate;
            @Bind(R.id.id_remarks)
            TextView idRemarks;
            @Bind(R.id.inner_lny_container)
            LinearLayout innerLnyContainer;
            @Bind(R.id.tv_total_amount)
            TextView tvTotalAmount;
            @Bind(R.id.sexangleView)
            CustomListView customListView;
            @Bind(R.id.linear_container_view)
            LinearLayout linearContainerView;
            @Bind(R.id.tv_need_amount)
            TextView idTvNeed;


            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }


        public class CustomTypeListViewAdapter extends CustomAdapter {

            private List<String> pic;

            public CustomTypeListViewAdapter(List<String> pic) {
                this.pic = pic;
            }


            @Override
            public int getCount() {
                return pic.size();
            }

            @Override
            public String getItem(int position) {
                return pic.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView = new ImageView(getActivity());
                imageView.setBackgroundColor(Color.WHITE);
                int padding = UIUtils.dip2px(15);
                imageView.setPadding(padding, padding, padding, padding);
               // txt.setImageResource(getItem(position));
                ImageLoader.getInstance().displayImage(getItem(position), imageView, ImageLoadOptions.getOptions());
                imageView.setBackgroundResource(R.drawable.gv_selector);
                return imageView;
            }

        }
    }
}
