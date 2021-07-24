package com.skillbox.redisdemo;

import static java.lang.System.out;

public class Main {

    private static final int USER_PAID_NUM = 8;


    public static void main(String[] args) throws InterruptedException {
        MyRedisStorage redis = new MyRedisStorage();
        redis.init();
        System.out.println(redis.getUsers().size());



        while (true){
            int count = 0;
            for (int i = 0; i < 20; i++) {
                redis.logRegisteredUsers(i);
                Thread.sleep(1);
            }

            for (String user : redis.getUsers()) {
                if(count==4){
                    out.println(redis.getUsers().valueRange(USER_PAID_NUM, USER_PAID_NUM) + " оплатил платную услугу");
                    Thread.sleep(500);
                    out.println("На главной странице показываем " + redis.getUsers().valueRange(USER_PAID_NUM, USER_PAID_NUM));
                }
                if(count!=8) {
                    out.println("На главной странице показываем " + user);
                    Thread.sleep(500);
                }
                count++;
            }
        }

    }
}
