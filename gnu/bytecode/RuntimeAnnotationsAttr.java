package gnu.bytecode;

/* loaded from: classes.dex */
public class RuntimeAnnotationsAttr extends MiscAttr {
    int numEntries;

    public RuntimeAnnotationsAttr(String name, byte[] data, AttrContainer container) {
        super(name, data, 0, data.length);
        addToFrontOf(container);
        this.numEntries = u2(0);
    }

    @Override // gnu.bytecode.MiscAttr, gnu.bytecode.Attribute
    public void print(ClassTypeWriter dst) {
        dst.print("Attribute \"");
        dst.print(getName());
        dst.print("\", length:");
        dst.print(getLength());
        dst.print(", number of entries: ");
        dst.println(this.numEntries);
        int saveOffset = this.offset;
        this.offset = saveOffset + 2;
        for (int i = 0; i < this.numEntries; i++) {
            printAnnotation(2, dst);
        }
        this.offset = saveOffset;
    }

    public void printAnnotation(int indentation, ClassTypeWriter dst) {
        int type_index = u2();
        dst.printSpaces(indentation);
        dst.printOptionalIndex(type_index);
        dst.print('@');
        dst.printContantUtf8AsClass(type_index);
        int num_element_value_pairs = u2();
        dst.println();
        int indentation2 = indentation + 2;
        for (int i = 0; i < num_element_value_pairs; i++) {
            int element_name_index = u2();
            dst.printSpaces(indentation2);
            dst.printOptionalIndex(element_name_index);
            dst.printConstantTersely(element_name_index, 1);
            dst.print(" => ");
            printAnnotationElementValue(indentation2, dst);
            dst.println();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:11:0x0028. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00c1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void printAnnotationElementValue(int r11, gnu.bytecode.ClassTypeWriter r12) {
        /*
            Method dump skipped, instructions count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.RuntimeAnnotationsAttr.printAnnotationElementValue(int, gnu.bytecode.ClassTypeWriter):void");
    }
}
