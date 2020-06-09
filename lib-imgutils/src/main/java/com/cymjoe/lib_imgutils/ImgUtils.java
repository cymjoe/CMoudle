package com.cymjoe.lib_imgutils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class ImgUtils {

    /**
     * 加载网络图片
     *
     * @param img
     * @param imgPath
     * @param context
     * @return
     */
    public static void loadHttpImg(ImageView img, String imgPath, Context context) {
        Glide.with(context)
                .load(imgPath)
                .into(img);

    }

    /**
     * 加载圆形网络图片
     *
     * @param img
     * @param imgPath
     * @param context
     * @return
     */
    public static void loadCircleImg(ImageView img, String imgPath, Context context) {
        Glide.with(context)
                .load(imgPath)
                .apply(RequestOptions.circleCropTransform())
                .into(img);
    }

    /**
     * 加载圆角网络图片
     *
     * @param img
     * @param imgPath
     * @param context
     * @return
     */
    public static void loadRoundImg(ImageView img, String imgPath, Context context, int radius) {
        RoundedCorners roundedCorners = new RoundedCorners(radius);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);

        Glide.with(context)
                .load(imgPath)
                .apply(options)
                .into(img);
    }

    /**
     * @param img
     * @param imgPath
     * @param context
     * @param level   设置模糊度(在0.0到25.0之间)，默认”25";"4":图片缩放比例,默认“1”
     */
    public static void blurImg(final ImageView img, String imgPath, Context context, int level,int scale) {
        Glide.with(context).load(imgPath)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(level, scale)))//
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        img.setImageDrawable(resource);
                    }
                });
    }

    /**
     * 压缩图片(采样率压缩)
     *
     * @param inSampleSize 可以根据需求计算出合理的inSampleSize
     */
    public static boolean compress(String compressPath, String imgPath, int inSampleSize) {
        File originFile = new File(imgPath);
        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置此参数是仅仅读取图片的宽高到options中，不会将整张图片读到内存中，防止oom
        options.inJustDecodeBounds = true;
        options.inSampleSize = inSampleSize;
        Bitmap resultBitmap = BitmapFactory.decodeFile(originFile.getAbsolutePath(), options);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        resultBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        try {
            FileOutputStream fos = new FileOutputStream(new File(compressPath));
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 压缩到指定大小
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image, int max) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = max;
        while (baos.toByteArray().length / 1024 > max) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }

    /**
     * 保存bitmap到指定目录
     *
     * @param bmp
     * @param mContext
     * @param galleryPath 保存的路径
     * @param saveImgName 要保存的文件名字
     * @return
     */
    public static String saveImageToDCIM(Bitmap bmp, Context mContext, String galleryPath, String saveImgName) {
        File appDir = new File(galleryPath);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        String fileName = saveImgName + ".png";
        File file = new File(appDir, fileName);
        // 声明输出流
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片
            bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outStream != null) {
                    outStream.flush();
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //通知相册更新
        MediaStore.Images.Media.insertImage(mContext.getContentResolver(),
                bmp, fileName, null);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        mContext.sendBroadcast(intent);
        return galleryPath + "/" + fileName;
    }
}
