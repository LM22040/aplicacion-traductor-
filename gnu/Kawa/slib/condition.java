package gnu.kawa.slib;

/* compiled from: conditions.scm */
/* loaded from: classes2.dex */
public class condition extends RuntimeException {
    public Object type$Mnfield$Mnalist;

    /* compiled from: conditions.scm */
    /* loaded from: classes2.dex */
    public class Mntype {
        public Object all$Mnfields;
        public Object fields;
        public Object name;
        public Object supertype;

        public Mntype(Object name, Object supertype, Object fields, Object all$Mnfields) {
            this.name = name;
            this.supertype = supertype;
            this.fields = fields;
            this.all$Mnfields = all$Mnfields;
        }

        public String toString() {
            StringBuffer sbuf = new StringBuffer((CharSequence) "#<condition-type ");
            sbuf.append(this.name);
            sbuf.append(">");
            return sbuf.toString();
        }
    }

    public condition(Object type$Mnfield$Mnalist) {
        this.type$Mnfield$Mnalist = type$Mnfield$Mnalist;
    }
}
