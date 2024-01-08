package com.karkar.springboot.log;

import com.karkar.springboot.enums.OperatorType;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface LogAnnotation {
    String module() default "";
    String operator() default "";
    OperatorType type() default OperatorType.SELECT;
}
