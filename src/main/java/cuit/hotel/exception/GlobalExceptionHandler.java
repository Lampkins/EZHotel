package cuit.hotel.exception;

import cn.hutool.json.JSONUtil;
import cuit.hotel.common.Result;
import cuit.hotel.common.ResultUtils;
import cuit.hotel.common.StatusEnum;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 400
     * @param e Exception
     */
    @ExceptionHandler({HttpMessageNotReadableException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            SQLException.class,
            InternalAuthenticationServiceException.class,})
    @ResponseBody
    public Result hadlerHttpMessageNotReadableException(Exception e) {
        e.printStackTrace();
        return ResultUtils.failure(StatusEnum.ERROR, e.getMessage());
    }

    /**
     * 500
     * @param e Exception
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result defaultErrorHandler(Exception e) {
        return ResultUtils.failure(StatusEnum.SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result accessDeniedExceptionHandler(Exception e) {
        e.printStackTrace();
        return ResultUtils.failure(StatusEnum.NO_PERMISSION);
    }
}
