package com.qx.mstarstoretv.viewutils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.utils.L;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TitlePopMenu {
	private Context context;
	private PopupWindow popupWindow;
	private CustomListView customListView;
	CustomListViewAdapter customListViewAdapter;
	LinearLayout normsLayout;
	List<String> typese=new ArrayList<>();


	public TitlePopMenu(Context context) {
		this.context = context;
		initData();
		View view = LayoutInflater.from(context).inflate(R.layout.titlepopmenu, null);
		popupWindow = new PopupWindow(view, LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景（很神奇的）
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		initView(view);
	}

	private void initData() {
		typese.add("推荐款");
		typese.add("畅销款");
		typese.add("最新款");
		typese.add("全部");
		typese.add("推荐款");
		typese.add("畅销款");
		typese.add("最新款");
		typese.add("全部");

		types.add("我  们");
		types.add("我  们");
		types.add("我  们");
		types.add("我  们");
		types.add("我  们");
		types.add("推 荐 款");
		types.add("畅 销 款");
		types.add("最 新 款");
		types.add("全 部");
	}

	private void initView(View view) {
		listData=getData();
		normsLayout = (LinearLayout) view.findViewById(R.id.norms_layout);
		customListView= (CustomListView) view.findViewById(R.id.sexangleView);
		ImageView close= (ImageView) view.findViewById(R.id.id_ig_close);
		close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		Button confirm= (Button) view.findViewById(R.id.id_btn_confirm);
		confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		customListViewAdapter = new CustomListViewAdapter();
		customListView.setDividerHeight(10);
		customListView.setDividerWidth(10);
		customListView.setAdapter(customListViewAdapter);
		LoadingGoodsLayout();
	}

	// 下拉式 弹出 pop菜单 parent 右下角
	public void showAsDropDown(View parent) {
		popupWindow.showAsDropDown(parent,
				10,
				// 保证尺寸是根据屏幕像素密度来的
				20);
		// 使其聚集
		popupWindow.setFocusable(true);
		// 设置允许在外点击消失
		popupWindow.setOutsideTouchable(true);
		// 刷新状态
		popupWindow.update();
	}

	// 隐藏菜单
	public void dismiss() {
		popupWindow.dismiss();
	}


	int size=15;
	private RadioGroup radioGroups;// 存储
	private int[] checkedIds;
	boolean isflag = false;
	LayoutInflater inflater;
	private int InitId = 0x1000;// 初始id
	@SuppressLint("NewApi")
	private void LoadingGoodsLayout() {
		normsLayout.removeAllViews();
		size=typese.size();
		radioGroups = new RadioGroup(context);
		if (checkedIds == null) {
			isflag = true;
			checkedIds = new int[size];
		}
		if (inflater == null)
			inflater = LayoutInflater.from(context);
		View inf_rel = inflater.inflate(R.layout.select_types, null);
		LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		ViewGroup.LayoutParams radiolp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		CustomRadioGroup normsGroup = (CustomRadioGroup) inf_rel.findViewById(R.id.goods_norms_group);
		normsGroup.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
		for (int j = 0; j < size; j++) {
			RadioButton view = new RadioButton(context);
			if (j == 0) {
				view.setTag(j);
			}
			view.setId(++InitId);
			view.setText(typese.get(j)+"");
			view.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
			view.setGravity(Gravity.CENTER);
			view.setPadding(20, 20, 20, 20);
			view.setButtonDrawable(R.drawable.radio_style);  //设置样式
			//view.setBackgroundResource(R.drawable.radio_style1.xml);  //设置背景
			view.setLayoutParams(radiolp);
			normsGroup.addView(view);
			if (view.getId() == checkedIds[j]) {
				L.e("radioButton0:" + view.getId());
				view.setChecked(true);
			}
		}
		normsLayout.addView(inf_rel, llparams);
	}

	private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
			String selected = radioButton.getText().toString();
			L.e("TAG"+"radioButton:" +selected);
		}
	}
	/**
	 * 数据封装
	 * @return
	 */
	private List<HashMap<String, Object>> getData()
	{
		List<HashMap<String, Object>> list = new ArrayList<>();
		for(int i=0;i<types.size();i++)
		{
			HashMap<String, Object> map = new HashMap<>();
			map.put("radioid", i);
			list.add(map);
		}
		return list;
	}

	private List<String> types=new ArrayList<>();
	private List<HashMap<String, Object>> listData;
	Map<Integer, Boolean> isCheckMap =  new HashMap<Integer, Boolean>();
	public class CustomListViewAdapter extends CustomAdapter {

		@Override
		public int getCount() {
			return types.size();
		}

		@Override
		public Object getItem(int position) {
			return types.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			if (convertView == null) {
				viewHolder = new ViewHolder();
				convertView = LayoutInflater.from(context).inflate(R.layout.adapter_cb_layout, parent, false);
				viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.id_cb_type);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

			viewHolder.checkBox.setText(types.get(position) + "");
			viewHolder.checkBox.setTag(listData.get(position).get("radioid").toString());
			viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					int radiaoId = Integer.parseInt(buttonView.getTag().toString());
					if(isChecked)
					{
						//将选中的放入hashmap中
						isCheckMap.put(radiaoId, isChecked);
						L.e(types.get(radiaoId)+"被选中");
					}
					else
					{
						//取消选中的则剔除
						isCheckMap.remove(radiaoId);
					}
				}
			});
			return  convertView;
		}


		public class ViewHolder{
			CheckBox checkBox;
		}
	}
}
