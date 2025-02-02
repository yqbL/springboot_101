package com.example.springboot_101.util;

import com.example.springboot_101.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "your_secret_key";

//    public static String generateToken(User user) {
//        return Jwts.builder()
//                .setSubject(user.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1天有效期
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();
//    }
}
