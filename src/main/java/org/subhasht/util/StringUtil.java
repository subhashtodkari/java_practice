package org.subhasht.util;

public class StringUtil {

    public static void main(String[] args) {
        System.out.println(skipSpecialChars("Cut Off Trees for Golf Event") + "_" + 675);
    }

    public static String skipSpecialChars(String str) {
        StringBuilder stringBuilder = new StringBuilder(str.length());
        for(char c : str.toCharArray()) {
            if( (c >= 'a' && c <= 'z')
                    || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }
}
