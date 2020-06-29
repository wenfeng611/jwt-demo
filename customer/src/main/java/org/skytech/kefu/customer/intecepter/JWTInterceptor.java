package org.skytech.kefu.customer.intecepter;

import org.skytech.kefu.common.exception.UnauthorizedException;
import org.skytech.kefu.common.service.JWTProvideSerivce;
import org.skytech.kefu.customer.core.JWTConfigurer;
import org.skytech.kefu.common.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTInterceptor extends HandlerInterceptorAdapter {


    private  JWTProvideSerivce jwtProvideSerivce;

    public JWTInterceptor(JWTProvideSerivce jwtProvideSerivce) {
        this.jwtProvideSerivce = jwtProvideSerivce;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String jwt = resolveToken(request);
        if (!StringUtils.hasText(jwt)) throw new UnauthorizedException();

        if (jwtProvideSerivce.verifyJWTToken(jwt)) {
            Authentication authentication = jwtProvideSerivce.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }


    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(JWTConfigurer.AUTHORIZATION_HEADER);
        if (!StringUtils.hasText(bearerToken)) {
            String[] parameterValues = request.getParameterValues(JWTConfigurer.AUTHORIZATION_HEADER);
            if (parameterValues != null && parameterValues.length > 0) {
                bearerToken = parameterValues[0];
            }
        }
        if (StringUtils.hasText(bearerToken)) {
            return bearerToken;
        }
        return null;
    }
}
