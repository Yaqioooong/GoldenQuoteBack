package com.yaxingguo.goldenquote.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    // 哈希密码
    public static String hashPassword(String password) {
        // 生成盐值并哈希密码
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // 验证密码
    public static boolean checkPassword(String rawPassword, String hashedPassword) {
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }

    public static void main(String[] args) {
        String password = "123123";
        String hashedPassword = hashPassword(password);
        System.out.println("Hashed Password: " + hashedPassword);
    }
}