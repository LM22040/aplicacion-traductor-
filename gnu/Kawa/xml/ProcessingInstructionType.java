package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.TypeValue;
import gnu.lists.AbstractSequence;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/* loaded from: classes2.dex */
public class ProcessingInstructionType extends NodeType implements TypeValue, Externalizable {
    static final Method coerceMethod;
    static final Method coerceOrNullMethod;
    public static final ProcessingInstructionType piNodeTest = new ProcessingInstructionType(null);
    public static final ClassType typeProcessingInstructionType;
    String target;

    static {
        ClassType make = ClassType.make("gnu.kawa.xml.ProcessingInstructionType");
        typeProcessingInstructionType = make;
        coerceMethod = make.getDeclaredMethod("coerce", 2);
        coerceOrNullMethod = make.getDeclaredMethod("coerceOrNull", 2);
    }

    public ProcessingInstructionType(String target) {
        super(target == null ? "processing-instruction()" : "processing-instruction(" + target + ")");
        this.target = target;
    }

    public static ProcessingInstructionType getInstance(String target) {
        return target == null ? piNodeTest : new ProcessingInstructionType(target);
    }

    @Override // gnu.kawa.xml.NodeType, gnu.bytecode.ObjectType, gnu.bytecode.Type
    public Type getImplementationType() {
        return ClassType.make("gnu.kawa.xml.KProcessingInstruction");
    }

    @Override // gnu.kawa.xml.NodeType, gnu.bytecode.ObjectType, gnu.bytecode.Type
    public void emitCoerceFromObject(CodeAttr code) {
        code.emitPushString(this.target);
        code.emitInvokeStatic(coerceMethod);
    }

    @Override // gnu.kawa.xml.NodeType, gnu.bytecode.ObjectType, gnu.bytecode.Type
    public Object coerceFromObject(Object obj) {
        return coerce(obj, this.target);
    }

    @Override // gnu.kawa.xml.NodeType, gnu.lists.ItemPredicate
    public boolean isInstancePos(AbstractSequence seq, int ipos) {
        int kind = seq.getNextKind(ipos);
        if (kind == 37) {
            String str = this.target;
            return str == null || str.equals(seq.getNextTypeObject(ipos));
        }
        if (kind == 32) {
            return isInstance(seq.getPosNext(ipos));
        }
        return false;
    }

    @Override // gnu.kawa.xml.NodeType, gnu.bytecode.ObjectType, gnu.bytecode.Type
    public boolean isInstance(Object obj) {
        return coerceOrNull(obj, this.target) != null;
    }

    public static KProcessingInstruction coerceOrNull(Object obj, String target) {
        KProcessingInstruction pos = (KProcessingInstruction) NodeType.coerceOrNull(obj, 32);
        if (pos == null || !(target == null || target.equals(pos.getTarget()))) {
            return null;
        }
        return pos;
    }

    public static KProcessingInstruction coerce(Object obj, String target) {
        KProcessingInstruction pos = coerceOrNull(obj, target);
        if (pos == null) {
            throw new ClassCastException();
        }
        return pos;
    }

    @Override // gnu.kawa.xml.NodeType
    protected void emitCoerceOrNullMethod(Variable incoming, Compilation comp) {
        CodeAttr code = comp.getCode();
        if (incoming != null) {
            code.emitLoad(incoming);
        }
        code.emitPushString(this.target);
        code.emitInvokeStatic(coerceOrNullMethod);
    }

    @Override // gnu.kawa.xml.NodeType, java.io.Externalizable
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.target);
    }

    @Override // gnu.kawa.xml.NodeType, java.io.Externalizable
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.target = (String) in.readObject();
    }

    @Override // gnu.kawa.xml.NodeType, gnu.bytecode.Type
    public String toString() {
        StringBuilder append = new StringBuilder().append("ProcessingInstructionType ");
        String str = this.target;
        if (str == null) {
            str = "*";
        }
        return append.append(str).toString();
    }
}
