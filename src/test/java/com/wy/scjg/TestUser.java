package com.wy.scjg;

public class TestUser {
    public static void main(String[] args) {
        StringBuffer grzPaths = new StringBuffer();
        grzPaths.append("123,");
        String s = grzPaths.delete(1, grzPaths.length() - 1).toString();
        System.out.println(s);
    }
}
