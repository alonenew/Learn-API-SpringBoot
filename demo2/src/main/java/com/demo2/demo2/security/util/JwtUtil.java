package com.demo2.demo2.security.util;

import java.util.Date;

import com.demo2.demo2.constant.StatusCode;
import com.demo2.demo2.exception.BaseException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {


    @Value("${app.jwt.secrete}")
    private String secrete;
    @Value("${app.jwt.expired}")
    private String expired;

    public String generateToken(String username) {
        Date expDate = new Date(System.currentTimeMillis()+ 1000 * 60 * Integer.parseInt(expired));
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(expDate)
            .signWith(SignatureAlgorithm.HS256, secrete)
            .compact();   
    }

    public void validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secrete).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new BaseException(HttpStatus.UNAUTHORIZED, StatusCode.ERR_CODE_401, StatusCode.ERR_DESC_401);
        }catch(Exception e){
            throw new BaseException(HttpStatus.UNAUTHORIZED, StatusCode.ERR_CODE_401, StatusCode.ERR_DESC_401);
        }
    }

    public String getUsernameFromJwt(String token){
        Claims claims =  Jwts.parser()
                            .setSigningKey(secrete)
                            .parseClaimsJws(token)
                            .getBody();
            return claims.getSubject();
    }

}
