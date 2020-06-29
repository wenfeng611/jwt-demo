package org.skytech.kefu.common.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.skytech.kefu.common.entity.User;
import org.skytech.kefu.common.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JWTProvideSerivce {


    private static final long EXPIRE_TIME = 60*60*1000;

    @Value("${jwt.secret}")
    public String secret;

    @Autowired
    private UserService userService;

    public String createJWTToken(String userid,String username){
        long now = System.currentTimeMillis();//当前时间
        long exp = now + EXPIRE_TIME;//过期时间为1分钟
        return Jwts.builder()
                .setId(userid)
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512,secret)
                .setExpiration(new Date(exp))
                .compact();

    }

    public boolean verifyJWTToken(String token){
        try {
            Claims claims =
                    Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return true;
        }catch (Exception e){
            log.error("Invalid JWT Toekn");
            throw new UnauthorizedException();
        }

    }

    public Authentication getAuthentication(String token) {
        Claims claims =
                Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        User user = userService.findOneById(claims.getId());
        return new UsernamePasswordAuthenticationToken(user,token);

    }
}