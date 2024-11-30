package com.easyapi.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DocApi {

    Class<?> value() default Null.class;

    Class<?> result() default Null.class;

    String stringResult() default "";

    String url() default "";

    String method() default "get";

    final class Null {

    }
}
