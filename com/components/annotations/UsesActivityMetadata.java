package com.google.appinventor.components.annotations;

import com.google.appinventor.components.annotations.androidmanifest.MetaDataElement;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface UsesActivityMetadata {
    MetaDataElement[] metaDataElements();
}
