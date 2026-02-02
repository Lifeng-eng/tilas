package com.example.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    // Base64 编码的密钥：对应 "业务"
    private static final String SECRET = "5Lia5Yqh";

    /**
     * 生成 JWT 令牌
     *
     * @param claims       自定义声明（如用户名、角色等）
     * @param expirationMs 过期时间（毫秒）
     * @return JWT 字符串
     */
    public static String generateToken(Map<String, Object> claims, long expirationMs) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setClaims(claims)                    // 设置自定义数据
                .setIssuedAt(now)                     // 签发时间
                .setExpiration(expiration)            // 过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET) // 使用HS256和密钥签名
                .compact();
    }

    /**
     * 解析 JWT 令牌
     *
     * @param token JWT 字符串
     * @return 解析出的 Claims 对象
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)        // 使用密钥解析
                .parseClaimsJws(token)
                .getBody();
    }
}