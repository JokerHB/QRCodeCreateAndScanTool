package com.wang.qrcodecreateandscantool.util;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

/**
 * Android�ͻ��˶�ά�����ɵĹ�����
 * <p/>
 * Created by wang on 2015/11/12.
 */
public class QRCodeCreateUtil {

    /**
     * ����һ��û��logo�Ķ�ά��Bitmap
     *
     * @param context   ��ά������
     * @param widthPix  ��ά����
     * @param heightPix ��ά��߶�
     * @return Bitmap
     */
    public static Bitmap createNoLogoQRCode(String context, int widthPix, int heightPix) {
        Bitmap bitmap = null;
        try {
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            // ָ������ȼ�
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            // ָ�������ʽ
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(context, BarcodeFormat.QR_CODE, widthPix, heightPix, hints);
            int[] pixels = new int[widthPix * heightPix];
            // �������ﰴ�ն�ά����㷨��������ɶ�ά���ͼƬ��
            // ����forѭ����ͼƬ����ɨ��Ľ��
            for (int y = 0; y < heightPix; y++) {
                for (int x = 0; x < widthPix; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * widthPix + x] = 0xff000000;
                    } else {
                        pixels[y * widthPix + x] = 0xffffffff;
                    }
                }
            }
            bitmap = Bitmap.createBitmap(widthPix, heightPix, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, widthPix, 0, 0, widthPix, heightPix);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
