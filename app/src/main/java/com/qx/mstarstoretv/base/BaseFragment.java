package com.qx.mstarstoretv.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.activity.LoginActivity;
import com.qx.mstarstoretv.utils.SpUtils;
import com.qx.mstarstoretv.viewutils.LoadingWaitDialog;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpTaskHandler;


/**
 * @action:
 * @author: YangShao
 * @date: 2015/12/29 @time: 9:00
 */
public class BaseFragment extends Fragment implements HttpCycleContext {
    private FragmentManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            if (manager == null) {
                manager = getActivity().getSupportFragmentManager();
                manager.popBackStackImmediate(null, 1);
            }

        }
    }

    private LoadingWaitDialog loadingDialog;

    protected void baseShowWatLoading() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingWaitDialog(getActivity(), getString(R.string.pull_to_refresh_footer_refreshing_label));
            loadingDialog.show();
        }
    }

    public void baseHideWatLoading() {
        if (loadingDialog == null) return;
        if (loadingDialog != null || loadingDialog.isShowing()) {
            loadingDialog.cancel();
            loadingDialog = null;
        }
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(getActivity(), pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    /*跳转到登录页面  登录成功回调到刚刚页面*/
    public void loginToServer(Class<?> jumpTo) {
        BaseApplication.spUtils.saveString(SpUtils.key_tokenKey, "");
        Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
        if (jumpTo == null)
            getActivity().startActivityForResult(loginIntent, 2);
        else {
            loginIntent.putExtra(LoginActivity.JUMP_TO_ACTIVITY, jumpTo);
            getActivity().startActivity(loginIntent);
        }
    }


    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();

    @Override
    public String getHttpTaskKey() {
        return HTTP_TASK_KEY;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        HttpTaskHandler.getInstance().removeTask(HTTP_TASK_KEY);
    }
}
