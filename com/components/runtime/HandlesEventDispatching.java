package com.google.appinventor.components.runtime;

/* loaded from: classes.dex */
public interface HandlesEventDispatching {
    boolean canDispatchEvent(Component component, String str);

    void dispatchErrorOccurredEvent(Component component, String str, int i, Object... objArr);

    boolean dispatchEvent(Component component, String str, String str2, Object[] objArr);

    void dispatchGenericEvent(Component component, String str, boolean z, Object[] objArr);
}
