package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;
import java.util.List;

/* compiled from: Setter.java */
/* loaded from: classes.dex */
class SetList extends Procedure2 {
    Type elementType;
    List list;

    public SetList(List list) {
        this.list = list;
    }

    @Override // gnu.mapping.Procedure2, gnu.mapping.Procedure
    public Object apply2(Object index, Object value) {
        this.list.set(((Number) index).intValue(), value);
        return Values.empty;
    }
}
