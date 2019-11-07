package com.venn.aop;

import com.venn.domain.annotion.DecryptField;
import com.venn.domain.enums.ErrorCodeMsgEnum;
import com.venn.exception.WalletException;
import com.venn.utils.RsaUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * 处理前端加密数据解密切面
 * <p>
 *
 * @author : venn
 * @date : 2019/11/7
 **/
@Component
@Aspect
@Order(2)
public class DecryptAspect {

    private static Logger logger = LoggerFactory.getLogger(DecryptAspect.class);

    private static final String PRI_KEY = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAocIWrqKm6YxQB9a2\n"
            + "eONIxpJg8GGo5Y4r0V3ifVeE1JkZJp1iD3wP1yBhzTDmXStla64WyoLwB/om6SwR\n"
            + "uZwwBwIDAQABAkBg82OE6BgCgwa0rAxSCGfmHHXdnasNa1j38718QqhqgyRCMawh\n"
            + "lqNv+o5yPX3xN/9So6120cs1AONqfqQLS0SBAiEA1fHBrpie2DGiti0b0bgGw0Yc\n"
            + "LLvr7j2z4XTqJHfpMt0CIQDBji9XkzWcix80qolZKdlXhPa/3FVMneDBQtEbzb9m\n"
            + "MwIgCtaMcUPaCCm7jG8Mkbs43HuYwctjUFZf3nQFyIMqlSECIB5H7VYpHLER/t7R\n"
            + "c010w6Dyl1vqz5l99aSmnGpaJQCLAiBUbznE9WlnD1ZZXwvMIgkEZb3vwJtjebXe\n"
            + "BYxGTJSmaA==";

    /**
     * 定义一个切入点
     */
    @Pointcut("execution(* com.venn.service..*.*(..))")
    public void annotationPointCut() {
    }

    @Around("annotationPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        logger.info("reqVO:{}", joinPoint);
        try {
            for (Object arg : joinPoint.getArgs()) {
                handleDecrypt(arg);
            }
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new WalletException(ErrorCodeMsgEnum.AOP_RSA_DECRYPT_FAIL, e);
        }
    }

    private void handleDecrypt(Object arg) {
        if (Objects.isNull(arg)) {
            return;
        }
        if (arg.getClass().isPrimitive()) {
            return;
        }
        Field[] fields = arg.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(DecryptField.class)) {
                field.setAccessible(true);
                try {
                    String encryptValue = (String) field.get(arg);
                    String plaintextValue = StringUtils.isNotEmpty(encryptValue) ? RsaUtil.decode(PRI_KEY, encryptValue) : null;
                    field.set(arg, plaintextValue);
                } catch (Exception e) {
                    throw new WalletException(ErrorCodeMsgEnum.AOP_RSA_DECRYPT_FAIL, e);
                }
            }
        }
    }
}
