package com.ouyang.login.aspect;


import com.ouyang.base.constant.CustomConstant;
import com.ouyang.base.utils.ValueUtils;
import com.ouyang.base.wrap.WrapMapper;
import com.ouyang.login.storage.Token;
import com.ouyang.login.utils.TokenUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2019
 * @date 2020/3/12 22:14
 * @Description TODO
 * NeedLogin 注解切面处理
 */
@Aspect
@Component
public class NeedLoginActionAspect {

    @Pointcut("@annotation(com.ouyang.login.aspect.NeedLogin)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object actionAround(ProceedingJoinPoint pjp) throws Throwable {
        // 获取当前正在执行的类
        Class<?> classTarget = pjp.getTarget().getClass();
        // 获取当前正在执行的方法
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        // 获取 request
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 获取注解参数
        String tokenCode = classTarget.getMethod(signature.getName(), signature.getParameterTypes()).getAnnotation(NeedLogin.class).value();
        long level = classTarget.getMethod(signature.getName(), signature.getParameterTypes()).getAnnotation(NeedLogin.class).level();

        // 调用方法验证 request 的 Authorization
        Token token = TokenUtil.checkToken(request.getHeader("authorization"));
        if (ValueUtils.valEmpty(token)) {
            return WrapMapper.error(CustomConstant.Wrap.CODE_TOKEN_FAILURE, CustomConstant.Wrap.MSG_TOKEN_FAILED);
        }
        if (level != -1 && level != token.level) {
            return WrapMapper.error(CustomConstant.Wrap.CODE_LEVEL_FAILURE, CustomConstant.Wrap.MSG_LEVEL_FAILED);
        }

        request.setAttribute(tokenCode + "_loginToken", token);
        return pjp.proceed();
    }
}
