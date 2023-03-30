package com.library.util;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    private static long time = 1000*60*60*2;//单位：毫秒
    private static String signature = "admin";

    public static String createToken(String username,String password){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                .claim("username",username)
                .claim("password",password)
                .setSubject("admin-test")//可以自定义
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        return jwtToken;
    }

    public static boolean checkToken(String token){
        if(token == null)return false;
        try{
            Jws<Claims> claimsJws =Jwts.parser().setSigningKey(signature)//通过signature签名进行解密
                    .parseClaimsJws(token);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static void parse(String token){
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImNoZW55aSIsInBhc3N3b3JkIjoiYnVnYW9zdW5pbWltYXhpeGl4aSIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE2NjA5NjU4NjQsImp0aSI6ImNjMzg4ZWFhLTFhZGEtNDVjZi1iMjc2LWIwMTI4MjU4NjRmMCJ9.Ui8HrH4WQ6kqu55Ya5HKE9RsWTIOstz9dQOryyo7gFE";
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature)//通过signature签名进行解密
                .parseClaimsJws(token);//解析token
        Claims claims = claimsJws.getBody();
        System.out.println("username:" + claims.get("username"));
//        System.out.println(claims.getId());
//        System.out.println(claims.getSubject());
//        System.out.println(claims.getExpiration());
    }

    public static String getUsername(String token){
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature)//通过signature签名进行解密
                .parseClaimsJws(token);//解析token
        Claims claims = claimsJws.getBody();
        return claims.get("username").toString();
    }

}
