package androidx.core.util;

import androidx.core.util.Predicate;
import com.android.tools.r8.annotations.SynthesizedClassV2;

/* loaded from: classes2.dex */
public interface Predicate<T> {
    Predicate<T> and(Predicate<? super T> predicate);

    Predicate<T> negate();

    Predicate<T> or(Predicate<? super T> predicate);

    boolean test(T t);

    @SynthesizedClassV2(apiLevel = -2, kind = 10, versionHash = "3f190e5f9a80d0f7670193e4f99ebcadd7b320b2de7ca36da081fdb272840838")
    /* renamed from: androidx.core.util.Predicate$-CC, reason: invalid class name */
    /* loaded from: classes2.dex */
    public final /* synthetic */ class CC<T> {
        public static Predicate $default$and(final Predicate _this, final Predicate predicate) {
            predicate.getClass();
            return new Predicate() { // from class: androidx.core.util.Predicate$$ExternalSyntheticLambda4
                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate and(Predicate predicate2) {
                    return Predicate.CC.$default$and(this, predicate2);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate negate() {
                    return Predicate.CC.$default$negate(this);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate or(Predicate predicate2) {
                    return Predicate.CC.$default$or(this, predicate2);
                }

                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return Predicate.CC.$private$lambda$and$0(Predicate.this, predicate, obj);
                }
            };
        }

        public static /* synthetic */ boolean $private$lambda$and$0(Predicate _this, Predicate other, Object t) {
            return _this.test(t) && other.test(t);
        }

        public static Predicate $default$negate(final Predicate _this) {
            return new Predicate() { // from class: androidx.core.util.Predicate$$ExternalSyntheticLambda5
                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate.CC.$default$and(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate negate() {
                    return Predicate.CC.$default$negate(this);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate.CC.$default$or(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return Predicate.CC.$private$lambda$negate$1(Predicate.this, obj);
                }
            };
        }

        public static /* synthetic */ boolean $private$lambda$negate$1(Predicate _this, Object t) {
            return !_this.test(t);
        }

        public static Predicate $default$or(final Predicate _this, final Predicate predicate) {
            predicate.getClass();
            return new Predicate() { // from class: androidx.core.util.Predicate$$ExternalSyntheticLambda1
                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate and(Predicate predicate2) {
                    return Predicate.CC.$default$and(this, predicate2);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate negate() {
                    return Predicate.CC.$default$negate(this);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate or(Predicate predicate2) {
                    return Predicate.CC.$default$or(this, predicate2);
                }

                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return Predicate.CC.$private$lambda$or$2(Predicate.this, predicate, obj);
                }
            };
        }

        public static /* synthetic */ boolean $private$lambda$or$2(Predicate _this, Predicate other, Object t) {
            return _this.test(t) || other.test(t);
        }

        public static <T> Predicate<T> isEqual(final Object targetRef) {
            if (targetRef == null) {
                return new Predicate() { // from class: androidx.core.util.Predicate$$ExternalSyntheticLambda2
                    @Override // androidx.core.util.Predicate
                    public /* synthetic */ Predicate and(Predicate predicate) {
                        return Predicate.CC.$default$and(this, predicate);
                    }

                    @Override // androidx.core.util.Predicate
                    public /* synthetic */ Predicate negate() {
                        return Predicate.CC.$default$negate(this);
                    }

                    @Override // androidx.core.util.Predicate
                    public /* synthetic */ Predicate or(Predicate predicate) {
                        return Predicate.CC.$default$or(this, predicate);
                    }

                    @Override // androidx.core.util.Predicate
                    public final boolean test(Object obj) {
                        boolean m;
                        m = Predicate$$ExternalSyntheticBackport0.m(obj);
                        return m;
                    }
                };
            }
            return new Predicate() { // from class: androidx.core.util.Predicate$$ExternalSyntheticLambda3
                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate.CC.$default$and(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate negate() {
                    return Predicate.CC.$default$negate(this);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate.CC.$default$or(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    boolean equals;
                    equals = targetRef.equals(obj);
                    return equals;
                }
            };
        }

        public static <T> Predicate<T> not(Predicate<? super T> predicate) {
            predicate.getClass();
            return predicate.negate();
        }
    }
}
