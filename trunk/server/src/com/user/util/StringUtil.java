/**
 * $Id: StringUtil.java 5400 2010-01-29 03:20:39Z cheng.huang@XIAONEI.OPI.COM $
 * Copyright(C) 2009-2014 xiaonei.com/kaixin.com, All Rights Reserved.
 */
package com.user.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 字符串操作类
 * @author Administrator
 */
public class StringUtil {
    /** 随机数对象 */
    private static final Random random = new Random();
    /** 数字与字母字典 */
    private static final char[] LETTER_AND_DIGIT = ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
    /** 数字与字母字典长度 */
    private static final int LETTER_AND_DIGIT_LENGTH = LETTER_AND_DIGIT.length;
	/** 字符串默认值 */
	public static final String DEFAULT_STRING_VAL = "".intern();

    /**
     * 检测字符串是否为空字符串。
     * 字符串为空的标准：null或全部由空字符组成的字符串
     * @param input 待检测字符串
     * @return
     * <li>true：字符串是空字符串</li>
     * <li>false：字符串不是空字符串</li>
     */
    public static boolean isEmpty(String input) {
        return (input == null || input.trim().length() == 0);
    }

    /**
     * 将对象转换为字符串
     * @param input 待转换对象
     * @return 转换后的字符串
     * @see #getString(Object, String)
     * @see #getString(String)
     * @see #getString(String, String)
     */
    public static String getString(Object input) {
        return getString(input, DEFAULT_STRING_VAL);
    }
    /**
     * 将对象转换为字符串
     * @param input 待转换对象
     * @param defVal 对象转换为空字符串时的默认返回值
     * @return 转换后的字符串
     * @see #getString(String)
     * @see #getString(String, String)
     */
    public static String getString(Object input, String defVal) {
        return (input == null) ? defVal : getString(input.toString(), defVal);
    }
    /**
     * 转换字符串
     * @param input 待转换字符串
     * @return 转换后的字符串
     * @see #getString(String, String)
     */
    public static String getString(String input) {
        return getString(input, DEFAULT_STRING_VAL);
    }
    /**
     * 转换字符串
     * @param input 待转换字符串
     * @param defVal 默认转换值
     * @return 转换后的字符串
     * <li>字符串为null或全部由空白字符组成的字符串时，返回defVal参数指定的值</li>
     * <li>其他情况，返回去掉字符串两端空白字符后的字符串</li>
     */
    public static String getString(String input, String defVal) {
        return (isEmpty(input)) ? defVal : input.trim();
    }

    /**
     * 生成固定长度的随机数
     * @param len 随机数长度
     * @return 生成的随机数
     */
    public static String getRandomString(final int len) {
        if (len < 1) return "";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(LETTER_AND_DIGIT[random.nextInt(LETTER_AND_DIGIT_LENGTH)]);
        }
        return sb.toString();
    }
    
    /**
     * 对传入的字符串进行MD5加密
     * @param plainText
     * @return
     */
    public static String MD5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
		System.out.println(MD5("admin"));
	}

}

