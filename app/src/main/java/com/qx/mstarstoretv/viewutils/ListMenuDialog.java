package com.qx.mstarstoretv.viewutils;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qx.mstarstoretv.R;
import com.qx.mstarstoretv.json.ModeListResult;

import java.util.List;

public class ListMenuDialog {
	private Context context;
	private PopupWindow popupWindow;
	ListView lv;
	List<ModeListResult.DataEntity.CustomList> mCustomList;

	public ListMenuDialog(Context context,List<ModeListResult.DataEntity.CustomList> customList) {
		this.context = context;
		this.mCustomList=customList;
		View view = LayoutInflater.from(context).inflate(R.layout.dialog_order_menu, null);
		popupWindow = new PopupWindow(view, LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景（很神奇的）//设置监听
		popupWindow.setBackgroundDrawable(new BitmapDrawable());

//		popupWindow.setTouchInterceptor(new View.OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				if(event.getAction() ==MotionEvent.ACTION_OUTSIDE){
//					//如果焦点不在popupWindow上，且点击了外面，不再往下dispatch事件：
//					//不做任何响应,不 dismiss popupWindow
//					return false;
//				}
//				//否则default，往下dispatch事件:关掉popupWindow，
//				return false;
//			}
//		});
		initView(view);
	}



	private void initView(View view) {
		lv= (ListView) view.findViewById(R.id.id_lv_memu);
		ListViewAdapter listViewAdapter =new ListViewAdapter();
		lv.setAdapter(listViewAdapter);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (onListMenuCloseClick!=null){
					onListMenuCloseClick.onSelect(mCustomList.get(position));
					dismiss();
				}
			}
		});
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
		ColorDrawable dw = new ColorDrawable(0xffffffff);//0xb0000000
		popupWindow.setBackgroundDrawable(dw);//半透明颜色
		popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {
				dismiss();
			}
		});
		popupWindow.update();
	}

	// 隐藏菜单
	public void dismiss() {
		if (onListMenuCloseClick!=null){
			popupWindow.dismiss();
			onListMenuCloseClick.onClose();
		}

	}

	public void setOnListMenuSelectCloseClick(OnListMenuSelectCloseClick onListMenuCloseClick) {
		this.onListMenuCloseClick = onListMenuCloseClick;
	}

	OnListMenuSelectCloseClick onListMenuCloseClick;
	public interface OnListMenuSelectCloseClick{
		void onClose();
		void onSelect(ModeListResult.DataEntity.CustomList select);
	}

	public class ListViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mCustomList.size();
		}

		@Override
		public Object getItem(int position) {
			return mCustomList.get(position);
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
				convertView = LayoutInflater.from(context).inflate(R.layout.adapter_order_menu, parent, false);
				viewHolder.textView = (TextView) convertView.findViewById(R.id.id_tv_item);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

			viewHolder.textView.setText(mCustomList.get(position).getTitle() + "");
			return  convertView;
		}


		public class ViewHolder{
			TextView textView;
		}
	}
}
