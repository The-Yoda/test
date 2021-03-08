package com.paypal.test;

public class Test1 {
    public static void main(String s[]) throws Exception {
        System.out.println("Updating db from file");
        DBUpdate updater = new DBUpdate();
        updater.updateDB();
        System.out.println("DB updated from file");
    }
}