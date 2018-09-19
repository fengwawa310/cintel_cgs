package com.controller.sys;

/**
 * Created by Mr.mu on 2018/5/28.
 */

import java.lang.annotation.*;
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    String module()  default "";
    String methods()  default "";
}