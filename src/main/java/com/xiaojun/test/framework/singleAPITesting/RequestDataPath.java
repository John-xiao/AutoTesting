package com.xiaojun.test.framework.singleAPITesting;

/**
 * Created by yongche on 17/6/14.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestDataPath {
    String path();

    boolean recursive() default false;
}
