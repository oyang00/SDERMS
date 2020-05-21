package com.ouyang.valid.aspect;


import com.ouyang.base.constant.CustomConstant;
import com.ouyang.base.utils.ValueUtils;
import com.ouyang.base.utils.WrapUtils;
import com.ouyang.base.wrap.WrapMapper;
import com.ouyang.base.wrap.WrapParams;
import com.ouyang.valid.bean.Method;
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
 * @date 2019/11/15 11:16
 * @Description TODO
 * ParamValidation 注解切面处理
 */
@Aspect
@Component
public class ParamValidationActionAspect {

    @Pointcut("@annotation(com.ouyang.valid.aspect.ParamValidation)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object actionAround(ProceedingJoinPoint pjp) throws Throwable {
        // 获取当前正在执行的类
        Class<?> classTarget = pjp.getTarget().getClass();
        // 获取当前正在执行的方法
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        // 获取注解参数
        Param[] authParams = classTarget.getMethod(signature.getName(), signature.getParameterTypes()).getAnnotation(ParamValidation.class).value();
        // 获取 request
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        // 获取传入参数
        WrapParams wrapParams = WrapUtils.paramToWrapParams(request);

        // 如果没有传入参数
        if (ValueUtils.valEmpty(wrapParams)) {
            return WrapMapper.error(CustomConstant.Wrap.CODE_PARAM_FAILURE, CustomConstant.Wrap.MSG_PARAM_EMPTY);
        }
        // 参数校验
        for (Param authParam : authParams) {
            // 参数 key
            String paramKey = authParam.value();
            // 参数 value
            Object paramValue = wrapParams.get(paramKey);
            if (ValueUtils.valEmpty(paramValue)) {
                return WrapMapper.error(CustomConstant.Wrap.CODE_PARAM_FAILURE, paramKey + CustomConstant.Wrap.MSG_PARAM_NULL);
            }
            String res = handleParam(paramValue, authParam.method());
            if (ValueUtils.valNotEmpty(res)) {
                return WrapMapper.error(CustomConstant.Wrap.CODE_PARAM_FAILURE, paramKey + res);
            }
        }

        return pjp.proceed(new Object[]{wrapParams});
    }

    // 处理参数
    private String handleParam(Object value, Method[] methods) {
        for (Method method : methods) {
            switch (method) {
                case EMAIL:
                    if (!String.valueOf(value).matches(CustomConstant.Regular.MAIL)) {
                        return CustomConstant.Wrap.MSG_PARAM_EMAIL;
                    }
                    break;
                case NUMBER:
                    if (!String.valueOf(value).matches(CustomConstant.Regular.NUMBER)) {
                        return CustomConstant.Wrap.MSG_PARAM_NUMBER;
                    }
                    break;
                case PHONE:
                    if (!String.valueOf(value).matches(CustomConstant.Regular.PHONE)) {
                        return CustomConstant.Wrap.MSG_PARAM_PHONE;
                    }
                    break;
            }
        }
        return null;
    }

}
