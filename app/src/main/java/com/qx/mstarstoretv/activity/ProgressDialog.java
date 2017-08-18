package com.qx.mstarstoretv.activity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.adapter.BaseViewHolder;
import com.qx.mstarstoretv.adapter.CommonAdapter;
import com.qx.mstarstoretv.base.AppURL;
import com.qx.mstarstoretv.base.BaseApplication;
import com.qx.mstarstoretv.json.ProgressResult;
import com.qx.mstarstoretv.net.VolleyRequestUtils;
import com.qx.mstarstoretv.utils.L;
import com.qx.mstarstoretv.utils.StringUtils;
import com.qx.mstarstoretv.utils.ToastManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */
public class ProgressDialog {


    private final int type;
    ListView mListView;
    private PopupWindow mPopupWindow;
    private Context mContext;
    String mOrderNumber;
    ProgressResult.DataEntity.OrderInfoEntity mOrderInfo;
    List<ProgressResult.DataEntity.OrderlListEntity> morderlList=new ArrayList<>();
    public ProgressDialog(Context context,String orderNum,int type) {
        this.mContext = context;
        this.mOrderNumber=orderNum;
        this.type = type;
        initView();
        loadNetData();

    }

    //总进度
    int mFlowTotalCount;
    private void loadNetData() {
        String url=null;
        if(type==1){
             url= AppURL.URL_MODELPRODUCE_PROGRESS+"tokenKey="+ BaseApplication.getToken()+"&orderNum="+mOrderNumber;
        }else if(type==2){
             url= AppURL.URL_MODELPRODUCE_PROGRESS2+"tokenKey="+ BaseApplication.getToken()+"&orderNum="+mOrderNumber;
        }
        L.e("开启搜索"+url);
        // 进行登录请求
        VolleyRequestUtils.getInstance().getCookieRequest(mContext, url, new VolleyRequestUtils.HttpStringRequsetCallBack(){
            @Override
            public void onSuccess(String result) {
                L.e("loadNetData  "+result);
                JsonObject jsonResult = new Gson().fromJson(result, JsonObject.class);
                String error=jsonResult.get("error").getAsString();
                if (error.equals("0")) {
                    mLnyLoadingLayout.setVisibility(View.GONE);
                    ProgressResult progressResult = new Gson().fromJson(result, ProgressResult.class);
                    ProgressResult.DataEntity dataEntity = progressResult.getData();
                    if(dataEntity==null){
                        return;
                    }
                    mOrderInfo   = dataEntity.getOrderInfo();
                    mFlowTotalCount=dataEntity.getFlowTotalCount();
                    morderlList = dataEntity.getOrderlList();
                    if (morderlList!=null&&mOrderInfo!=null){
                        initDataView();
                    }

                   // mViewAdapter.setListData(orderlList);
                }else if (error.equals("2")) {
                    L.e("重新登录");
                    ToastManager.showToastReal(jsonResult.get("message").getAsString());
                }else {
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

    private void initDataView() {
        TextView tvOrderNumber = (TextView) mPopView.findViewById(R.id.id_order_num);
        TextView tvOrderDate = (TextView) mPopView.findViewById(R.id.id_order_date);
        TextView tvInvo = (TextView) mPopView.findViewById(R.id.id_tv_invo);
        tvOrderNumber.setText("订单编号："+mOrderInfo.getOrderNum());
        tvOrderDate.setText("下单日期："+mOrderInfo.getConfirmDate());
        tvInvo.setText(mOrderInfo.getOtherInfo());
        mViewAdapter.setListData(morderlList);
    }


    ListViewAdapter mViewAdapter;
    View mPopView;
    LinearLayout mLnyLoadingLayout;
    private void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPopView = inflater.inflate(R.layout.view_progress_dialog, null);
        mListView = (ListView) mPopView.findViewById(R.id.id_pd_lv);
        mLnyLoadingLayout = (LinearLayout) mPopView.findViewById(R.id.lny_loading_layout);
        mLnyLoadingLayout.setVisibility(View.VISIBLE);
        TextView tvTitle = (TextView) mPopView.findViewById(R.id.title_text);
        tvTitle.setText("查看进度");
        ImageView igBack = (ImageView) mPopView.findViewById(R.id.id_ig_back);
        igBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });


        mViewAdapter =new ListViewAdapter(morderlList,R.layout.item_progress);
        mListView.setAdapter(mViewAdapter);
        mPopupWindow = new PopupWindow(mPopView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());     //点击外部消失这句很重要
        // 点击外面的控件也可以使得PopUpWindow dimiss
        mPopupWindow.setOutsideTouchable(true);
    }

    public class ListViewAdapter extends CommonAdapter<ProgressResult.DataEntity.OrderlListEntity>{

        public ListViewAdapter(List<ProgressResult.DataEntity.OrderlListEntity> mDatas, int itemLayoutId) {
            super(mDatas, itemLayoutId);
        }

        @Override
        public void convert(int position, BaseViewHolder helper, ProgressResult.DataEntity.OrderlListEntity item) {
           // MyProgress progress = helper.getView(R.id.my_progress);
            LinearLayout linearlayout = helper.getView(R.id.id_list_porgress);
            helper.setText(R.id.product_name,item.getTitle());
            helper.setText(R.id.product_norms,item.getModelInfo());
            helper.setText(R.id.product_price,item.getNumber()+"件");
            helper.setImageByUrl(R.id.product_img,item.getPic());
            linearlayout.removeAllViews();
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(10,10,10,10);
            List<ProgressResult.DataEntity.OrderlListEntity.ProgressEntity> progressList=null;
            if (item!=null){
                progressList = item.getProgress();
            }
            if (progressList!=null){
                for (int i = 0; i < progressList.size(); i++) {
                    View view=getMyProgressView();
                    ProgressBar progress = (ProgressBar) view.findViewById(R.id.my_progress);
                    TextView textProgress = (TextView) view.findViewById(R.id.tv_progress);
                    progress.setMax(mFlowTotalCount);
                    if (StringUtils.isEmpty(progressList.get(i).getCurrentFlow())){
                        progress.setProgress(0);
                    }else {
                        progress.setProgress(Integer.valueOf(progressList.get(i).getCurrentFlow()));
                    }

                    textProgress.setText(progressList.get(i).getFlowInfo());
                    linearlayout.addView(view, lp);
                }
            }

        }

        public View getMyProgressView() {
            View inf_rel = LayoutInflater.from(mContext).inflate(R.layout.item_myprogress, null);
            return inf_rel;
        }

    }

    // 下拉式 弹出 pop菜单 parent 右下角
    public void showAsDropDown(RelativeLayout parent) {
        mPopupWindow.showAtLocation(parent, Gravity.TOP, 0, 0);
    }

}
