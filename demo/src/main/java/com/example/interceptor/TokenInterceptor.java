package com.example.interceptor;
import org.springframework.lang.NonNull;
import com.example.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        // 从请求头中获取token
        String token = request.getHeader("token");

        // 如果没有token，返回401未授权
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing token");
            return false;
        }

        // 如果token以Bearer开头，去掉前缀
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        try {
            // 使用 JwtUtil 解析token
            Claims claims = JwtUtil.parseToken(token);
            // 可以将解析出的信息存入request中供后续使用
            request.setAttribute("userClaims", claims);
            log.info("Token validated successfully for user: {}", claims.getSubject());
            return true;
        } catch (Exception e) {
            // token解析失败，返回401未授权
            log.warn("Invalid token: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");
            return false;
        }
    }
}
