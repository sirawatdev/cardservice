package com.hackathon.cardservice.interceptor;

import com.hackathon.cardservice.constants.Response;
import com.hackathon.cardservice.exception.CardException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CardServiceInterceptor implements HandlerInterceptor {
    private static final Logger log = LogManager.getLogger(CardServiceInterceptor.class.getName());


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        if(request.getRequestURI().contains("/api/error")){
            if(response.getStatus() == 405){
                throw new CardException(Response.WRONG_METHOD, HttpStatus.METHOD_NOT_ALLOWED);
            }
            else if(response.getStatus() == 404){
                throw new CardException(Response.PATH_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
            else if(response.getStatus() == 500){
                throw new CardException(Response.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        log.info("Card Interceptor: preHandle");

        return true;


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        log.info("Card Interceptor: postHandle");
    }
}
