package com.qx.mstarstoretv.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.base.BaseFragment;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class FinishFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =View.inflate(getActivity(),R.layout.frag_list_layout,null);
        return view;
    }
}
