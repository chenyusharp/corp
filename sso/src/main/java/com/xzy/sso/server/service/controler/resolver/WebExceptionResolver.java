package com.xzy.sso.server.service.controler.resolver;

import com.xzy.sso.core.entity.ReturnT;
import com.xzy.sso.core.exception.XzySsoException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * Date: 2023/7/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Component
public class WebExceptionResolver implements HandlerExceptionResolver {

    private static transient Logger logger = LoggerFactory.getLogger(WebExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        boolean isJson = false;
        HandlerMethod method = (HandlerMethod) handler;
        final ResponseBody responseBody = method.getMethodAnnotation(ResponseBody.class);
        if (responseBody != null) {
            isJson = true;
        }
        ReturnT<String> errorResult = null;
        if (ex instanceof XzySsoException) {
            errorResult = new ReturnT<>(ReturnT.FAIL_CODE, ex.getMessage());
        } else {
            errorResult = new ReturnT<>(ReturnT.FAIL_CODE, ex.toString().replaceAll("\n", "<br/>"));
        }
        ModelAndView mv = new ModelAndView();
        if (isJson) {
            try {
                response.setContentType("application/json;charset=utf-8");

                response.getWriter().print("{\"code:\":" + errorResult.getCode() + ",\"msg\":\"" + errorResult.getMsg() + "\"}");
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
            return mv;
        } else {
            mv.addObject("exceptionMsg", errorResult.getMsg());
            mv.setViewName("/common/common.exception");
            return mv;
        }
    }
}