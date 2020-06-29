package org.skytech.kefu.customer.exception;


import lombok.extern.slf4j.Slf4j;
import org.skytech.kefu.common.exception.BusinessException;
import org.skytech.kefu.common.exception.UnauthorizedException;
import org.skytech.kefu.common.model.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleUnKnownException(Exception e) {
        log.error("Returning HTTP 500 INTERNAL_SERVER_ERROR", e);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseResult handleBusinessException(BusinessException businessException) {
        log.error("Returning HTTP 400 Bad Request cause by business exception : {}", businessException.getMessage());
        ResponseResult response = null;
        response = ResponseResult.error(businessException.getCode(),businessException.getMessage());
        return response;
    }


    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseResult handleUnauthorizedException() {
        log.error("Returning HTTP 401 Bad Request cause by Unauthorized ");
        return ResponseResult.error(401,"Unauthorized");
    }


}
