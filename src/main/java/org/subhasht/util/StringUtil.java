package org.subhasht.util;

public class StringUtil {

    public static void main(String[] args) {
        System.out.println(generateClassName("1034. Coloring A Border"));
    }

    public static String generateClassName(String str) {
        int firstDotIdx = str.indexOf(".");
        int problemNum = Integer.parseInt(str.substring(0, firstDotIdx));
        str = str.substring(firstDotIdx+1).trim();
        StringBuilder stringBuilder = new StringBuilder(str.length());
        boolean toUpper = true;
        for(char c : str.toCharArray()) {
            if( (c >= 'a' && c <= 'z')
                    || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
                if(toUpper) {
                    c = Character.toUpperCase(c);
                }
                stringBuilder.append(c);
            }
            toUpper = Character.isSpaceChar(c);
        }
        return stringBuilder + "_" + problemNum;
    }
}
