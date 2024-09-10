package com.google.appinventor.components.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface UsesNativeLibraries {
    String libraries() default "";

    String v7aLibraries() default "";

    String v8aLibraries() default "";

    String x86_64Libraries() default "";
}
