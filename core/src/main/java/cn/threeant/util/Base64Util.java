package cn.threeant.util;

import java.util.Base64;

public class Base64Util {

    public static String decode(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }

}
