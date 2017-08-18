package com.qx.mstarstoretv.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yangshao on 2015/10/10.
 */
public class BimpUtils {
    public static BimpUtils bimpUtils;

    public static BimpUtils getInstace() {
        if (bimpUtils == null) {
            bimpUtils = new BimpUtils();
        }
        return bimpUtils;
    }
    private BimpUtils(){

    }


    /**
     *  查询图片路径
     * @param context
     * @param uri
     * @return
     */
    public static String getRealPath(Context context, Uri uri) {
        if (uri == null) return null;
        String data = null;
        String scheme = uri.getScheme();
        if (scheme == null){
            data = uri.getPath();
        }else if(ContentResolver.SCHEME_FILE.equals(scheme)){
            data = uri.getPath();
        }else if(ContentResolver.SCHEME_CONTENT.equals(scheme)){
            Cursor cursor = context.getContentResolver().query(uri,new String[]{MediaStore.Images.ImageColumns.DATA},null,null,null);
            if (cursor != null && cursor.moveToFirst()){
                int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                if (index > -1){
                    data = cursor.getString(index);
                }
            }
            cursor.close();
        }
        return data;
    }


//    public String getCompressImagePaht(String path) {
//        Bitmap bmp = null;
//        String dir = null;
//        try {
//            //压缩图片
//            BufferedInputStream in = new BufferedInputStream(new FileInputStream(
//                    new File(path)));
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inJustDecodeBounds = true;
//            BitmapFactory.decodeStream(in, null, options);
//            in.close();
//            int i = 0;
//            while (true) {
//                if ((options.outWidth >> i <= 1000)
//                        && (options.outHeight >> i <= 1000)) {
//                    in = new BufferedInputStream(
//                            new FileInputStream(new File(path)));
//                    options.inSampleSize = (int) Math.pow(2.0D, i);
//                    options.inJustDecodeBounds = false;
//                    bmp = BitmapFactory.decodeStream(in, null, options);
//                    break;
//                }
//                i += 1;
//            }
//
//            //裁剪图片
//            int width = bmp.getWidth();
//            int height = bmp.getHeight();
//            if (width > height) {
//                bmp = Bitmap.createBitmap(bmp, (width - height) / 2, 0, height, height);
//            } else if (width < height) {
//                bmp = Bitmap.createBitmap(bmp, 0, (height - width) / 2, width, width);
//            }
//
//            //写入缓存 文件
//            dir = MyApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator
//                    + System.currentTimeMillis() + ".jpg";
//            File file = new File(dir);
//            FileOutputStream fos = new FileOutputStream(file);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//            fos.write(baos.toByteArray());
//            fos.flush();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        bmp.recycle();
//        bmp=null;
//        return dir;
//    }

    public static List<Bitmap> bmp = new ArrayList<Bitmap>();

	//图片sd地址  上传服务器时把图片调用下面方法压缩后 保存到临时文件夹 图片压缩后小于100KB，失真度不明显
	public static List<String> drr = new ArrayList<String>();

    //压缩图片
    public Bitmap compressImage(Bitmap image, int size) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 80, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > size) { // 循环判断如果压缩后图片是否大于1000kb,大于继续压缩
            options -= 10;// 每次都减少10
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * @param b Bitmap
     * @return 图片存储的位置
     * @throws
     */
    public static String saveImg(Bitmap b, String name)  {
        String path = getSDPath().getPath();
        File mediaFile = new File(path + File.separator + name + ".jpg");
        if (mediaFile.exists()) {
            mediaFile.delete();
        }
        if (!new File(path).exists()) {
            new File(path).mkdirs();
        }
        try {
            mediaFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(mediaFile);
            b.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            b.recycle();
            b = null;
            System.gc();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mediaFile.getPath();
    }


    /**
     * 获取sd卡路径
     *
     * @return
     */
    private static File getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            // 这里可以修改为你的路径
            sdDir = new File(Environment.getExternalStorageDirectory()
                    + "/DCIM/Cache");
        }
        return sdDir;
    }


        /**
         * 清空文件
         */
        public void clearAll(){
            File file= getSDPath();
            if (file.exists()) {
                file.delete();
            }
        }

    	public static Bitmap revitionImageSize(String path) throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				new File(path)));
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(in, null, options);
		in.close();
		int i = 0;
		Bitmap bitmap = null;
		while (true) {
			if ((options.outWidth >> i <= 1000)
					&& (options.outHeight >> i <= 1000)) {
				in = new BufferedInputStream(
						new FileInputStream(new File(path)));
				options.inSampleSize = (int) Math.pow(2.0D, i);
				options.inJustDecodeBounds = false;
				bitmap = BitmapFactory.decodeStream(in, null, options);
				break;
			}
			i += 1;
		}
		return bitmap;
	}


    public String path = "/sdcard/myHead/";// sd路径
    public void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public String savebitmap(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d("file:::",
                    "Error creating media file, check storage permissions: ");// e.getMessage());
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
            return pictureFile.getAbsolutePath();
        } catch (FileNotFoundException e) {
            L.d("file:::", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("file:::", "Error accessing file: " + e.getMessage());
        }
        return pictureFile.getAbsolutePath();
    }

    public File getOutputMediaFile(){
        //   + getApplicationContext().getPackageName()
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + "/Files");
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName="MI_"+ timeStamp +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }


    Bitmap bitmap =null;
    public void returnBitMap(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL urls=null;
                try{
                    urls=new URL(url);
                    HttpURLConnection connection= (HttpURLConnection) urls.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream is = connection.getInputStream();
                    bitmap= BitmapFactory.decodeStream(is);
                    is.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public static Bitmap compressBitmapFromSrc(String path, int len) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        int be = (w / len + h / len) / 2;
        if (be == 0) be = 1;
        newOpts.inSampleSize = be;
        bitmap = BitmapFactory.decodeFile(path, newOpts);
        return compressImage(bitmap);
    }

    //压缩图片
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 80, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 600) { // 循环判断如果压缩后图片是否大于1000kb,大于继续压缩
            options -= 10;// 每次都减少10
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

}
