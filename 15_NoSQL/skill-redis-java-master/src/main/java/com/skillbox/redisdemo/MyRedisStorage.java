package com.skillbox.redisdemo;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import static java.lang.System.out;

public class MyRedisStorage {
    private RedissonClient redisson;

    private RKeys rKeys;

    private RScoredSortedSet<String> users;

    public RScoredSortedSet<String> getUsers() {
        return users;
    }

    private final static String KEY = "USERS";


    public void listKey(){
        Iterable<String> keys = rKeys.getKeys();
        for (String key : keys) {
            System.out.println("KEY: " + key + ", TYPE: " + rKeys.getType(key));
        }
    }

    public void init(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try{
            redisson = Redisson.create(config);
        }catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        rKeys = redisson.getKeys();
        users = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    void shutdown() {
        redisson.shutdown();
    }

    void logRegisteredUsers(int user_id)
    {
        //ZADD ONLINE_USERS
        users.add(Double.parseDouble(String.valueOf(user_id)), "user " + user_id);
    }


}
