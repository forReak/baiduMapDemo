package com.design.helpPlatform.util;


import java.security.MessageDigest;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Util {

    /**
     * 获得MD5加密密码的方法
     */
    public static String getMD5ofStr(String origString) {
        String origMD5 = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] result = md5.digest(origString.getBytes());
            origMD5 = byteArray2HexStr(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return origMD5;
    }

    /**
     * 密码验证方法
     */
    public static boolean verifyPassword(String inputStr, String MD5Code) {
        return getMD5ofStr(inputStr).equals(MD5Code);
    }

    /**
     * 处理字节数组得到MD5密码的方法
     */
    private static String byteArray2HexStr(byte[] bs) {
        StringBuffer sb = new StringBuffer();
        for (byte b : bs) {
            sb.append(byte2HexStr(b));
        }
        return sb.toString();
    }

    /**
     * 字节标准移位转十六进制方法
     */
    private static String byte2HexStr(byte b) {
        String hexStr = null;
        int n = b;
        if (n < 0) {
            //若需要自定义加密,请修改这个移位算法即可
            n = b & 0x7F + 127;
//            n = b & 0x7F + 128; 标准md5
        }
        hexStr = Integer.toHexString(n / 16) + Integer.toHexString(n % 16);
        return hexStr.toUpperCase();
    }

    public static void main(String[] args) {
        //加密
        System.out.println(getMD5ofStr("admin"));
        //验证
        System.out.println(verifyPassword("admin","21232F297A57A4A643884A0E4A801FC2"));
    }



    /**
     * 字符串判断为空
     *
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        // TODO Auto-generated method stub
        if (string == null || string.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断不为空
     *
     * @param object
     * @return
     */
    public static boolean isNotEmpty(Object object) {
        // TODO Auto-generated method stub
        if (object == null || object.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public static <T> boolean listIsEmpty(List<T> list){
        return list == null || list.size() == 0 || list.isEmpty();

    }

    // 判空
    public static boolean empty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String && (obj.equals("") || obj.equals("0"))) {
            return true;
        } else if (obj instanceof Number && ((Number) obj).doubleValue() == 0) {
            return true;
        } else if (obj instanceof Boolean && !((Boolean) obj)) {
            return true;
        } else if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        } else if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        } else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
            return true;
        }
        return false;
    }

    // List 转 String 且拆分
    public static <T> String listToString(List<T> list, String separator) {
        if (empty(list)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).toString()).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }
}
