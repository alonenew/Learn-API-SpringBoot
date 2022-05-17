package com.demo2.demo2.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo2.demo2.constant.Authority;
import com.demo2.demo2.constant.StatusCode;
import com.demo2.demo2.model.common.ErrorResponse;
import com.demo2.demo2.security.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwtToken = request.getHeader(Authority.AUTHORIZATION);
                if ((jwtToken != null) && (jwtToken.startsWith(Authority.BEARER))) {
                    jwtToken = jwtToken.substring(7);
                    try {
                        jwtUtil.validateToken(jwtToken);
                    } catch (Exception e) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        ErrorResponse<Object> errorResponse = new ErrorResponse<Object>(StatusCode.ERR_CODE_401, StatusCode.ERR_DESC_401);
                        String errorResponseJson = objectMapper.writeValueAsString(errorResponse);
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(errorResponseJson);
                        return;
                    }
                     
                    String username = jwtUtil.getUsernameFromJwt(jwtToken);
                    if((username != null) && (SecurityContextHolder.getContext().getAuthentication() == null)){
                        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                        authorities.add(new SimpleGrantedAuthority(Authority.USER));
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,"(protected)", authorities);
                        SecurityContextHolder.getContext()
                                             .setAuthentication(authentication);
                    }
                }

                filterChain.doFilter(request, response);
    }

}
