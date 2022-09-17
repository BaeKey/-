package com.shiguang;

/**
 * @author: ShiGuang
 * @create: 2022-09-17 11:11
 * @description:
 */
import java.util.regex.Pattern;

final public class Patterns {

    public static final Pattern NO4 = Pattern.compile("[0-35-9]{11}");
    public static final Pattern AABB = Pattern.compile("\\d{7}(\\d)\\1{1}((?!\\1)\\d)\\2{1}");
    public static final Pattern AAA = Pattern.compile("(\\d)\\1{2,}");
    public static final Pattern LOVE = Pattern.compile("\\d{8}(521|520)");
    public static final Pattern ABCDABCD = Pattern.compile("\\d{3}(\\d)(\\d)(\\d)(\\d)\\1\\2\\3\\4");
    public static final Pattern ABCDDCBA = Pattern.compile("\\d{3}(\\d)(\\d)(\\d)(\\d)\\4\\3\\2\\1");
    public static final Pattern AABBCC = Pattern.compile("(\\d)\\1((?!\\1)\\d)\\2((?!\\2)(?!\\1)\\d)\\3");
    public static final Pattern ABCD = Pattern.compile("(?:0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){3}\\d");
    public static final Pattern ABCDE = Pattern.compile("(?:0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){4}\\d");
    public static final Pattern ONE5S = Pattern.compile("^(?=\\d*(\\d)\\d*(?:\\1\\d*){4})\\d{11}$");
}
