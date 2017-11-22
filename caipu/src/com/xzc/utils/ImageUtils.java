package com.xzc.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by Modificator on 2015/8/17.
 */
public class ImageUtils {

    public static ByteArrayInputStream getUploadImageStream(String path, float fileSize, int imagePixMaxSize) {
        int degree = getBitmapDegree(path);
        Bitmap degreeBitmap = getBitmapFromFile(new File(path), imagePixMaxSize);
        degreeBitmap = rotateBitmapByDegree(degreeBitmap, degree);
        return compressImage(degreeBitmap, fileSize);
    }

    public static Bitmap getUploadImageBitmap(String path, float fileSize, int imagePixMaxSize) {
        int degree = getBitmapDegree(path);
        Bitmap degreeBitmap = getBitmapFromFile(new File(path), imagePixMaxSize);
        degreeBitmap = rotateBitmapByDegree(degreeBitmap, degree);
        return BitmapFactory.decodeStream(compressImage(degreeBitmap, fileSize));
    }

    public static ByteArrayInputStream getUploadImageStream(String path) {
        return getUploadImageStream(path, 80, 1080);
    }

    public static Bitmap getUploadImageBitmap(String path) {
        return getUploadImageBitmap(path, 80, 1080);
    }

    public static ByteArrayInputStream compressImage(Bitmap image) {
        return compressImage(image, 80);
    }

    public static ByteArrayInputStream compressImage(Bitmap image, float size) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//����ѹ������������100��ʾ��ѹ������ѹ��������ݴ�ŵ�baos��
        int options = 100;
        while (baos.toByteArray().length / 1024f > size && options > 1) {  //ѭ���ж����ѹ����ͼƬ�Ƿ����100kb,���ڼ���ѹ��
            baos.reset();//����baos�����baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//����ѹ��options%����ѹ��������ݴ�ŵ�baos��
            options -= 5;//ÿ�ζ�����10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//��ѹ���������baos��ŵ�ByteArrayInputStream��
        //Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//��ByteArrayInputStream��������ͼƬ
        return isBm;
    }

    public static Bitmap getBitmapFromFile(File dst) {
        return getBitmapFromFile(dst, 1080);
    }

    public static Bitmap getBitmapFromFile(File dst, int maxSize) {
//        int maxSize = 1080;
        int width = maxSize;
        int height = maxSize;

        if (null != dst && dst.exists()) {
            BitmapFactory.Options opts = null;
            if (width > 0 && height > 0) {
                opts = new BitmapFactory.Options();
                //����inJustDecodeBoundsΪtrue��decodeFile��������ռ䣬��ʱ����ԭʼͼƬ�ĳ��ȺͿ��
                opts.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(dst.getPath(), opts);

                //region ��������
                if (Math.max(opts.outHeight, opts.outWidth) > maxSize) {
                    if (opts.outHeight > opts.outWidth) {
                        width = (int) (opts.outWidth * 1f * maxSize / opts.outHeight);
                    } else {
                        height = (int) (opts.outHeight * 1f * maxSize / opts.outWidth);
                    }

                }
                //endregion

                // ����ͼƬ���ű���
                final int minSideLength = Math.min(width, height);
                opts.inSampleSize = computeSampleSize(opts, minSideLength,
                        width * height);
                //����һ��Ҫ�������û�false����Ϊ֮ǰ���ǽ������ó���true
                opts.inJustDecodeBounds = false;
                opts.inInputShareable = true;
                opts.inPurgeable = true;
            }
            try {
                return BitmapFactory.decodeFile(dst.getPath(), opts);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static int computeSampleSize(BitmapFactory.Options options,
                                        int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }


    private static int computeInitialSampleSize(BitmapFactory.Options options,
                                                int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
                .sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math
                .floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    /**
     * ��ȡͼƬ����ת�ĽǶ�
     *
     * @param path ͼƬ����·��
     * @return ͼƬ����ת�Ƕ�
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // ��ָ��·���¶�ȡͼƬ������ȡ��EXIF��Ϣ
            ExifInterface exifInterface = new ExifInterface(path);
            // ��ȡͼƬ����ת��Ϣ
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * ��ͼƬ����ĳ���ǶȽ�����ת
     *
     * @param bm     ��Ҫ��ת��ͼƬ
     * @param degree ��ת�Ƕ�
     * @return ��ת���ͼƬ
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // ������ת�Ƕȣ�������ת����
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // ��ԭʼͼƬ������ת���������ת�����õ��µ�ͼƬ
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }

    public static Bitmap getVideoThumbnail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
            }
        }
        return bitmap;
    }
}


