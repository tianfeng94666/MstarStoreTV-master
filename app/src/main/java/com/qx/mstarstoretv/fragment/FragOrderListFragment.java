package com.qx.mstarstoretv.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.activity.ConfirmOrderActivity;
import com.qx.mstarstoretv.activity.CustomMadeActivity;
import com.qx.mstarstoretv.activity.FinishTableLessActivity;
import com.qx.mstarstoretv.activity.ProductionListActivity;
import com.qx.mstarstoretv.adapter.BaseViewHolder;
import com.qx.mstarstoretv.adapter.CommonAdapter;
import com.qx.mstarstoretv.adapter.SendingListAdater;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.base.BaseFragment;
import com.qx.mstarstoretv.json.OrderWaitResult;
import com.qx.mstarstoretv.json.SendingResult;
import com.qx.mstarstoretv.net.ImageLoadOptions;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;
import com.qx.mstarstoretv.utils.UIUtils;
import com.qx.mstarstoretv.viewutils.CustomGridView;
import com.qx.mstarstoretv.viewutils.GridViewWithHeaderAndFooter;
import com.qx.mstarstoretv.viewutils.LoadingWaitDialog;
import com.qx.mstarstoretv.viewutils.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
@SuppressLint("ValidFragment")
public class FragOrderListFragment extends BaseFragment implements PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener {
    private List<OrderWaitResult.DataBean.OrderListBean.ListBean> listData;
    private BaseAdapter adapter;
    private PullToRefreshView oullRefreshView;
    private static final int PULL_REFRESH = 1;
    private static final int PULL_LOAD = 2;
    private int pullStatus;
    private int cpage = 1;
    private int tempCurpage;
    private int fragType;
    private int listCount = 0;
    private boolean isflag;
    LinearLayout spContent;
    private int imgContainerWidth;
    private final int CHECKING_CODE = 1;
    private final int PRODUCTING_CODE = 2;
    private final int SENDING_CODE = 3;
    private final int FINISHED_CODE = 4;
    private List<SendingResult.DataBean.OrderListBean> sendinglist =new ArrayList<>();
    private String tokenKey;
    private GridViewWithHeaderAndFooter gridview;
    private LoadingWaitDialog dialog;
    JsonObject jsonResult;
    public FragOrderListFragment(int fragType) {
        this.fragType = fragType;
    }

    public FragOrderListFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_list_layout, null);
        initView(view);
        cpage = 1;
//        loadNetData();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            if(jsonResult==null){
                loadNetData();
            }
        } else {
            //相当于Fragment的onPause
        }
    }

    private void initView(View view) {
    gridview = (GridViewWithHeaderAndFooter) view.findViewById(R.id.gridview);
        oullRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_refresh_view);
        spContent = (LinearLayout) view.findViewById(R.id.specifiction_content);

        oullRefreshView.setOnHeaderRefreshListener(this);
        oullRefreshView.setOnFooterRefreshListener(this);
        listData = new ArrayList<>();
        switch (fragType) {
            case CHECKING_CODE:
                adapter = new ListViewAdapter();
                gridview.setAdapter(adapter);
                break;
            case PRODUCTING_CODE:
                adapter = new ListViewAdapter();
                gridview.setAdapter(adapter);
                break;
            case SENDING_CODE:
                if(sendinglist!=null){
                    adapter = new SendingListAdater(getActivity(), sendinglist);
                    gridview.setAdapter(adapter);
                }
                break;

        }

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                L.e("gridview.setOnItemClickListener");
                Intent intent = null;
                Bundle bundle = null;
                switch (fragType) {
                    case CHECKING_CODE:
                        intent = new Intent(getActivity(), ConfirmOrderActivity.class);
                        bundle = new Bundle();
                        bundle.putInt("type", 2);
                        bundle.putString("itemId", listData.get(i).getId());
                        intent.putExtras(bundle);
                        startActivityForResult(intent, 11);
                        break;
                    case PRODUCTING_CODE:
                        intent = new Intent(getActivity(), ProductionListActivity.class);
                        bundle = new Bundle();
                        bundle.putInt("type", fragType);
                        bundle.putString("orderNum", listData.get(i).getOrderNum());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case SENDING_CODE:
                        intent = new Intent(getActivity(), FinishTableLessActivity.class);
                        intent.putExtra("orderNumber",sendinglist.get(i).getOrderNum());
                        intent.putExtra("type","1");
                        startActivity(intent);
                        break;

                }

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 11) {
            loadNetData();
        }
    }



    public void showWatiNetDialog() {
        dialog = new LoadingWaitDialog(getActivity());
        dialog.show();
    }
    public void dismissWatiNetDialog() {
        if (dialog != null) {
            dialog.cancel();
            dialog.dismiss();
            dialog = null;
        }
    }
    private void loadNetData() {
        showWatiNetDialog();
        String url = "";
        tokenKey = BaseApplication.getToken();
        switch (fragType) {
            case CHECKING_CODE:
                url = AppURL.URL_ORDER_WAITCHECK + "tokenKey=" +tokenKey  + "&cpage=" + cpage;
                break;
            case PRODUCTING_CODE:
                url = AppURL.URL_ORDER_MODEL_LIST + "tokenKey=" + tokenKey + "&cpage=" + cpage;
                // ModelOrderProduceListPage?tokenKey=10b588002228fa805231a59bb7976bf4&cpage=2
                break;
            case SENDING_CODE:
                url = AppURL.URL_CODE_SENDING + "tokenKey=" + tokenKey+ "&cpage=" + cpage;
                break;
            case FINISHED_CODE:
                url="";
                dismissWatiNetDialog();
                break;
        }

        if (StringUtils.isEmpty(url)) {
            return;
        }
        L.e("获取地址" + url);
        VolleyRequestUtils.getInstance().getCookieRequest(getActivity(), url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                dismissWatiNetDialog();
                L.e("loadNetData  " + result);
                 jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error = jsonResult.get("error").getAsString();
                //待审核及生产中 返回结果
                OrderWaitResult orderWaitResult;
                OrderWaitResult.DataBean.OrderListBean orderList = null;
                List<OrderWaitResult.DataBean.OrderListBean.ListBean> list = null;
                //已发货
                SendingResult sendingResult;
                SendingResult.DataBean.OrderListBean orderListBean = null;

                if (error.equals("0")) {
                    switch (fragType) {
                        case CHECKING_CODE:
                            orderWaitResult = new Gson().fromJson(result, OrderWaitResult.class);
                            if(orderWaitResult.getData()==null){
                                break;
                            }
                           OrderWaitResult.DataBean.StatusCountBean statusCountBean = orderWaitResult.getData().getStatusCount();
                            setBadge(statusCountBean);
                            orderList = orderWaitResult.getData().getOrderList();

                            list = orderList.getList();
                            if(list!=null){
                                listCount = Integer.valueOf(orderList.getList_count());
                            }
                            break;
                        case PRODUCTING_CODE:
                            orderWaitResult = new Gson().fromJson(result, OrderWaitResult.class);
                            orderList = orderWaitResult.getData().getOrderList();
                            list = orderList.getList();
                            if(list!=null){
                                listCount = Integer.valueOf(orderList.getList_count());
                            }
                            break;
                        case SENDING_CODE:
                            sendingResult = new Gson().fromJson(result, SendingResult.class);
                            if(sendingResult.getData()!=null){
                                List<SendingResult.DataBean.OrderListBean> templist = sendingResult.getData().getOrderList();
                                if(templist !=null){
                                    sendinglist.addAll(templist);
                                }
                            }else {
                                break;
                            }
                            adapter = new SendingListAdater(getActivity(), sendinglist);
                            gridview.setAdapter(adapter);
                            if(sendinglist!=null){
                                listCount = Integer.valueOf(sendingResult.getData().getList_count());
                            }
                            break;
                    }

                    if (pullStatus != PULL_LOAD) {
                        listData.clear();
                    }
                    if (list != null) {
                        listData.addAll(list);
                    }
                    endNetRequest();

                } else if (error.equals("2")) {
                    loginToServer(CustomMadeActivity.class);
                } else {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    ToastManager.showToastWhendebug(message);
                    L.e(message);
                }
            }

            @Override
            public void onFail(String fail) {
                dismissWatiNetDialog();
            }

        });
    }

    private void setBadge( OrderWaitResult.DataBean.StatusCountBean statusCountBean) {
        if(statusCountBean!=null){
                    ((CustomMadeActivity)getActivity()).onFragOrderCount(statusCountBean);
        }
    }

    OnOderNumberChange onOderNumberChange;

    public interface OnOderNumberChange {
        void onFragOrderCount(OrderWaitResult.DataBean.StatusCountBean statusCountBean);

        void onFragProduCount(int deliverNum);
    }

    public void setOnOderNumberChange(OnOderNumberChange onOderNumberChange) {
        this.onOderNumberChange = onOderNumberChange;
    }


    public void endNetRequest() {
        if(adapter!=null){
            adapter.notifyDataSetChanged();
        }
        tempCurpage = cpage;
        if (pullStatus == PULL_LOAD) {
            oullRefreshView.onFooterRefreshComplete();
        }
        if (pullStatus == PULL_REFRESH) {
            oullRefreshView.onHeaderRefreshComplete();
        }
        pullStatus = 0;
    }

    @Override
    public void onFooterRefresh(PullToRefreshView pullToFootRefreshView) {
        L.e("listCount" + listCount + "adapter.getCount " + adapter.getCount());
        if (listCount > adapter.getCount()) {
            tempCurpage = cpage;
            cpage++;
            pullStatus = PULL_LOAD;
            loadNetData();
        } else {
            ToastManager.showToastReal("没有更多数据");
            pullToFootRefreshView.onFooterRefreshComplete();
        }

    }

    @Override
    public void onHeaderRefresh(PullToRefreshView pullToFootRefreshView) {
        tempCurpage = cpage;
        cpage = 1;
        pullStatus = PULL_REFRESH;
        listData.clear();
        sendinglist.clear();
      loadNetData();

    }


    public class ListViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return listData == null ? 0 : listData.size();
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
            final ViewHolder viewHolder;
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
            viewHolder.tvOrderState.setText(listEntity.getOrderStatusTitle());

            if (fragType == 1) {
                //修改日期
                viewHolder.idEndDate.setText(listEntity.getModifyDate());
            } else if (fragType == 2) {
                //审核日期
                viewHolder.idEndDate.setText(listEntity.getModifyDate());
            }
            viewHolder.idRemarks.setText(listEntity.getOtherInfo());
            viewHolder.tvTotalAmount.setText("¥" + listEntity.getTotalPrice());
            viewHolder.idTvNeed.setText("定金 " + listEntity.getNeedPayPrice());



            viewHolder.layImages.removeAllViews();
            if (listEntity.getPics() != null && listEntity.getPics().size() != 0) {
                addMenuLayout(listEntity.getPics(), viewHolder.layImages);
            }

//
//
//          if ( viewHolder.customListViewAdapter==null){
//              viewHolder.customListViewAdapter = new CustomTypeListViewAdapter(listEntity.getPicsEntity(), viewHolder.customListView);
//          }
//            viewHolder.customListView.setAdapter(viewHolder.customListViewAdapter);
//            viewHolder.customListView.setClickable(false);
//            viewHolder.customListView.setPressed(false);
//            viewHolder.customListView.setEnabled(false);
            return convertView;
        }


        private void addMenuLayout(final List<String> images, LinearLayout layout) {
            for (int i = 0; i < images.size(); i++) {
                final LinearLayout ll = new LinearLayout(getActivity());
                ll.setPadding(16, 14, 16, 14);
                ll.setGravity(Gravity.CENTER_HORIZONTAL);
                ll.setOrientation(LinearLayout.VERTICAL);
                final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(UIUtils.dip2px(45), UIUtils.dip2px(45));
                final ImageView img = new ImageView(getActivity()) {
                    @Override
                    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                        int widthSize = (UIUtils.getWindowWidth() - 40 * 3 + 20) / 4;
                        setMeasuredDimension(UIUtils.dip2px(60), UIUtils.dip2px(60));
                    }
                };
                ImageLoader.getInstance().displayImage(images.get(i), img, ImageLoadOptions.getOptions());
                img.setBackgroundResource(R.drawable.gv_selector);
                if (StringUtils.isEmpty(images.get(i))) {
                    L.e(images.get(i));
                    break;
                }
                ll.addView(img, lp);
                layout.addView(ll);
            }
        }


        public ImageView getImageView(String pic) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundColor(Color.WHITE);
            int padding = UIUtils.dip2px(15);
            imageView.setPadding(padding, padding, padding, padding);
            // txt.setImageResource(getItem(position));
            ImageLoader.getInstance().displayImage(pic, imageView, ImageLoadOptions.getOptions());
            imageView.setBackgroundResource(R.drawable.gv_selector);
            imageView.setClickable(false);
            imageView.setFocusable(false);
            return imageView;
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
            @Bind(R.id.img_container)
            CustomGridView customListView;
            @Bind(R.id.linear_container_view)
            LinearLayout linearContainerView;
            @Bind(R.id.tv_need_amount)
            TextView idTvNeed;
            @Bind(R.id.id_lay_images)
            LinearLayout layImages;
            @Bind(R.id.tv_order_state)
            TextView tvOrderState;

            CustomTypeListViewAdapter customListViewAdapter;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }


        public class CustomTypeListViewAdapter extends BaseAdapter {

            private List<String> imgFiles;
            private CustomGridView mCustomGridView;

            public CustomTypeListViewAdapter(List<String> pic, CustomGridView customGridView) {
                this.imgFiles = pic;
                this.mCustomGridView = customGridView;
            }


            @Override
            public int getCount() {
                return imgFiles.size();
            }

            @Override
            public String getItem(int position) {
                return imgFiles.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            int width;

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    int horizontalSpace = mCustomGridView.getHorizontalSpacing();
                    int colums = mCustomGridView.getNumColumns();
                    width = (imgContainerWidth - colums * horizontalSpace) / colums;
                    ImageView img = new ImageView(getActivity()) {
                        @Override
                        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                            setMeasuredDimension(width, width);
                        }
                    };
                    convertView = img;
                }
                if (imgFiles.size() != 0)
                    mCustomGridView.setPadding(0, 15, 0, 36);
                else
                    mCustomGridView.setPadding(0, 15, 0, 0);
                ImageLoader.getInstance().displayImage(imgFiles.get(position), (ImageView) convertView, ImageLoadOptions.getOptions());
                //convertView.setDrawingCacheEnabled(false);
                return convertView;
            }

        }

        public class CustomTypeListViewAdapter1 extends CommonAdapter {

            public CustomTypeListViewAdapter1(List<String> mDatas, int itemLayoutId) {
                super(mDatas, itemLayoutId);
            }

            @Override
            public void convert(int position, BaseViewHolder helper, Object item) {
                //TextView textView = helper.getView(R.id.tv_total_amount);
                //view.setText(item);
                helper.setText(R.id.id_tv_title, "");
            }

        }
    }
}
