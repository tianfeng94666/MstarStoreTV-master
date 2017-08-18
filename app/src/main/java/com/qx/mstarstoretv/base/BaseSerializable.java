package com.qx.mstarstoretv.base;

import android.content.Context;

import com.qx.mstarstoretv.utils.UIUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;



/**
 * 实体基类：实现序列化
 * @version 1.0PO
 * @created 2012-3-21
 */
public abstract class BaseSerializable implements Serializable {

	private static final long serialVersionUID = 4028753859512456811L;
	public final static String UTF8 = "UTF-8";
	public final static String NODE_ROOT = "LISTVIEW";
	
	protected Notice notice;

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}
	
	/**
	 * 判断缓存是否存在
	 * 
	 * @param cachefile
	 * @return
	 */
	private boolean isExistDataCache(String cachefile) {
		boolean exist = false;
		File data = UIUtils.getContext().getFileStreamPath(cachefile);
		if (data.exists())
			exist = true;
		return exist;
	}
	/**
	 * 读取对象
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public Serializable readObject(String file) {
		if (!isExistDataCache(file))
			return null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = UIUtils.getContext().openFileInput(file);
			ois = new ObjectInputStream(fis);
			return (Serializable) ois.readObject();
		} catch (FileNotFoundException e) {
		} catch (Exception e) {
			e.printStackTrace();
			// 反序列化失败 - 删除缓存文件
			if (e instanceof InvalidClassException) {
				File data = UIUtils.getContext().getFileStreamPath(file);
				data.delete();
			}
		} finally {
			try {
				ois.close();
			} catch (Exception e) {
			}
			try {
				fis.close();
			} catch (Exception e) {
			}
		}
		return null;
	}
	
	/**
	 * 保存对象
	 * 
	 * @param ser
	 * @param file
	 * @throws IOException
	 */
	public boolean saveObject(Serializable ser, String file) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = UIUtils.getContext().openFileOutput(file, Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(ser);
			oos.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				oos.close();
			} catch (Exception e) {
			}
			try {
				fos.close();
			} catch (Exception e) {
			}
		}
	}


	
	public class Notice implements Serializable {

		private static final long serialVersionUID = -605603539960259675L;
		public final static String UTF8 = "UTF-8";
		public final static String NODE_ROOT = "LISTVIEW";
		
		public final static int	TYPE_ATME = 1;
		public final static int	TYPE_MESSAGE = 2;
		public final static int	TYPE_COMMENT = 3;
		public final static int	TYPE_NEWFAN = 4;

		private int atmeCount;
		private int msgCount;
		private int reviewCount;
		private int newFansCount;
		
		public int getAtmeCount() {
			return atmeCount;
		}
		public void setAtmeCount(int atmeCount) {
			this.atmeCount = atmeCount;
		}
		public int getMsgCount() {
			return msgCount;
		}
		public void setMsgCount(int msgCount) {
			this.msgCount = msgCount;
		}
		public int getReviewCount() {
			return reviewCount;
		}
		public void setReviewCount(int reviewCount) {
			this.reviewCount = reviewCount;
		}
		public int getNewFansCount() {
			return newFansCount;
		}
		public void setNewFansCount(int newFansCount) {
			this.newFansCount = newFansCount;
		}	
		
		public Notice parse(InputStream inputStream)   {
			Notice notice = null;
	        return notice;       
		}
	}
}
