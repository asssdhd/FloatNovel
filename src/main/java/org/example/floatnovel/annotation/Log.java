package org.example.floatnovel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    /** 操作模块 */
    String module();

    /** 操作类型 */
    String operation();

    /** 操作说明 */
    String description() default "";
}
