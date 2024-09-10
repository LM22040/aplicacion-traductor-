package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.SeqPosition;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;
import gnu.mapping.WrongType;

/* loaded from: classes2.dex */
public class NodeCompare extends Procedure2 {
    static final int RESULT_EQU = 0;
    static final int RESULT_GRT = 1;
    static final int RESULT_LSS = -1;
    static final int TRUE_IF_EQU = 8;
    static final int TRUE_IF_GRT = 16;
    static final int TRUE_IF_LSS = 4;
    int flags;
    public static final NodeCompare $Eq = make("is", 8);
    public static final NodeCompare $Ne = make("isnot", 20);
    public static final NodeCompare $Gr = make(">>", 16);
    public static final NodeCompare $Ls = make("<<", 4);

    public static NodeCompare make(String name, int flags) {
        NodeCompare proc = new NodeCompare();
        proc.setName(name);
        proc.flags = flags;
        return proc;
    }

    @Override // gnu.mapping.Procedure2, gnu.mapping.Procedure
    public Object apply2(Object obj, Object obj2) {
        int pos;
        AbstractSequence abstractSequence;
        int pos2;
        AbstractSequence abstractSequence2;
        int stableCompare;
        if (obj == null || obj2 == null) {
            return null;
        }
        if (obj == Values.empty) {
            return obj;
        }
        if (obj2 == Values.empty) {
            return obj2;
        }
        if (obj instanceof AbstractSequence) {
            abstractSequence = (AbstractSequence) obj;
            pos = abstractSequence.startPos();
        } else {
            try {
                SeqPosition seqPosition = (SeqPosition) obj;
                AbstractSequence abstractSequence3 = seqPosition.sequence;
                pos = seqPosition.getPos();
                abstractSequence = abstractSequence3;
            } catch (ClassCastException e) {
                throw WrongType.make(e, this, 1, obj);
            }
        }
        if (obj2 instanceof AbstractSequence) {
            abstractSequence2 = (AbstractSequence) obj2;
            pos2 = abstractSequence2.startPos();
        } else {
            try {
                SeqPosition seqPosition2 = (SeqPosition) obj2;
                AbstractSequence abstractSequence4 = seqPosition2.sequence;
                pos2 = seqPosition2.getPos();
                abstractSequence2 = abstractSequence4;
            } catch (ClassCastException e2) {
                throw WrongType.make(e2, this, 2, obj2);
            }
        }
        if (abstractSequence == abstractSequence2) {
            stableCompare = abstractSequence.compare(pos, pos2);
        } else {
            if (this == $Eq) {
                return Boolean.FALSE;
            }
            if (this == $Ne) {
                return Boolean.TRUE;
            }
            stableCompare = abstractSequence.stableCompare(abstractSequence2);
        }
        if (((1 << (stableCompare + 3)) & this.flags) != 0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
