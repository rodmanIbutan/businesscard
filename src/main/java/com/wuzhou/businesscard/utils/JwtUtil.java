package com.wuzhou.businesscard.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "e33e58b6cace41249287454159472c0a";
    /**
     * 生成Jwt
     * @param claims 保存的内容
     * @return jwt令牌
     */
    public static String generateJwt(String claims) {
        return Jwts.builder()
                .claim("id",claims)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 365 * 24 * 60 * 60 *1000L))
                .compact();
    }
    /**
     * 解析jwt令牌
     * @param jwt jwt令牌
     * @return 保存的内容
     */
    public static Claims parseJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
