package gnu.mapping;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Hashtable;

/* loaded from: classes.dex */
public class Namespace implements Externalizable, HasNamedParts {
    int log2Size;
    private int mask;
    String name;
    int num_bindings;
    protected String prefix;
    protected SymbolRef[] table;
    protected static final Hashtable nsTable = new Hashtable(50);
    public static final Namespace EmptyNamespace = valueOf("");

    public final String getName() {
        return this.name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final String getPrefix() {
        return this.prefix;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Namespace() {
        this(64);
    }

    protected Namespace(int capacity) {
        this.prefix = "";
        this.log2Size = 4;
        while (true) {
            int i = this.log2Size;
            if (capacity > (1 << i)) {
                this.log2Size = i + 1;
            } else {
                int capacity2 = 1 << i;
                this.table = new SymbolRef[capacity2];
                this.mask = capacity2 - 1;
                return;
            }
        }
    }

    public static Namespace create(int capacity) {
        return new Namespace(capacity);
    }

    public static Namespace create() {
        return new Namespace(64);
    }

    public static Namespace getDefault() {
        return EmptyNamespace;
    }

    public static Symbol getDefaultSymbol(String name) {
        return EmptyNamespace.getSymbol(name);
    }

    public static Namespace valueOf() {
        return EmptyNamespace;
    }

    public static Namespace valueOf(String name) {
        if (name == null) {
            name = "";
        }
        Hashtable hashtable = nsTable;
        synchronized (hashtable) {
            Namespace ns = (Namespace) hashtable.get(name);
            if (ns != null) {
                return ns;
            }
            Namespace ns2 = new Namespace();
            ns2.setName(name.intern());
            hashtable.put(name, ns2);
            return ns2;
        }
    }

    public static Namespace valueOf(String uri, String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return valueOf(uri);
        }
        String xname = prefix + " -> " + uri;
        Hashtable hashtable = nsTable;
        synchronized (hashtable) {
            Object old = hashtable.get(xname);
            if (old instanceof Namespace) {
                return (Namespace) old;
            }
            Namespace ns = new Namespace();
            ns.setName(uri.intern());
            ns.prefix = prefix.intern();
            hashtable.put(xname, ns);
            return ns;
        }
    }

    public static Namespace valueOf(String uri, SimpleSymbol prefix) {
        return valueOf(uri, prefix == null ? null : prefix.getName());
    }

    public static Namespace makeUnknownNamespace(String prefix) {
        String uri;
        if (prefix == null || prefix == "") {
            uri = "";
        } else {
            uri = "http://kawa.gnu.org/unknown-namespace/" + prefix;
        }
        return valueOf(uri, prefix);
    }

    public Object get(String key) {
        return Environment.getCurrent().get(getSymbol(key));
    }

    public boolean isConstant(String key) {
        return false;
    }

    public Symbol getSymbol(String key) {
        return lookup(key, key.hashCode(), true);
    }

    public Symbol lookup(String key) {
        return lookup(key, key.hashCode(), false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Symbol lookupInternal(String key, int hash) {
        int index = this.mask & hash;
        SymbolRef prev = null;
        SymbolRef ref = this.table[index];
        while (ref != null) {
            SymbolRef next = ref.next;
            Symbol sym = ref.getSymbol();
            if (sym == null) {
                if (prev == null) {
                    this.table[index] = next;
                } else {
                    prev.next = next;
                }
                this.num_bindings--;
            } else {
                if (sym.getLocalPart().equals(key)) {
                    return sym;
                }
                prev = ref;
            }
            ref = next;
        }
        return null;
    }

    public Symbol add(Symbol sym, int hash) {
        int index = this.mask & hash;
        SymbolRef ref = new SymbolRef(sym, this);
        sym.namespace = this;
        ref.next = this.table[index];
        SymbolRef[] symbolRefArr = this.table;
        symbolRefArr[index] = ref;
        int i = this.num_bindings + 1;
        this.num_bindings = i;
        if (i >= symbolRefArr.length) {
            rehash();
        }
        return sym;
    }

    public Symbol lookup(String key, int hash, boolean create) {
        Symbol sym;
        synchronized (this) {
            Symbol sym2 = lookupInternal(key, hash);
            if (sym2 != null) {
                return sym2;
            }
            if (!create) {
                return null;
            }
            if (this == EmptyNamespace) {
                sym = new SimpleSymbol(key);
            } else {
                sym = new Symbol(this, key);
            }
            return add(sym, hash);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0022, code lost:
    
        r9.table[r2] = r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean remove(gnu.mapping.Symbol r10) {
        /*
            r9 = this;
            monitor-enter(r9)
            java.lang.String r0 = r10.getLocalPart()     // Catch: java.lang.Throwable -> L38
            int r1 = r0.hashCode()     // Catch: java.lang.Throwable -> L38
            int r2 = r9.mask     // Catch: java.lang.Throwable -> L38
            r2 = r2 & r1
            r3 = 0
            gnu.mapping.SymbolRef[] r4 = r9.table     // Catch: java.lang.Throwable -> L38
            r4 = r4[r2]     // Catch: java.lang.Throwable -> L38
        L11:
            if (r4 == 0) goto L35
            gnu.mapping.SymbolRef r5 = r4.next     // Catch: java.lang.Throwable -> L38
            gnu.mapping.Symbol r6 = r4.getSymbol()     // Catch: java.lang.Throwable -> L38
            if (r6 == 0) goto L20
            if (r6 != r10) goto L1e
            goto L20
        L1e:
            r3 = r4
            goto L33
        L20:
            if (r3 != 0) goto L27
            gnu.mapping.SymbolRef[] r7 = r9.table     // Catch: java.lang.Throwable -> L38
            r7[r2] = r5     // Catch: java.lang.Throwable -> L38
            goto L29
        L27:
            r3.next = r5     // Catch: java.lang.Throwable -> L38
        L29:
            int r7 = r9.num_bindings     // Catch: java.lang.Throwable -> L38
            r8 = 1
            int r7 = r7 - r8
            r9.num_bindings = r7     // Catch: java.lang.Throwable -> L38
            if (r6 == 0) goto L33
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L38
            return r8
        L33:
            r4 = r5
            goto L11
        L35:
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L38
            r5 = 0
            return r5
        L38:
            r0 = move-exception
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L38
            goto L3c
        L3b:
            throw r0
        L3c:
            goto L3b
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.Namespace.remove(gnu.mapping.Symbol):boolean");
    }

    protected void rehash() {
        int oldCapacity = this.table.length;
        int newCapacity = oldCapacity * 2;
        int newMask = newCapacity - 1;
        int countInserted = 0;
        SymbolRef[] oldTable = this.table;
        SymbolRef[] newTable = new SymbolRef[newCapacity];
        int i = oldCapacity;
        while (true) {
            i--;
            if (i >= 0) {
                SymbolRef ref = oldTable[i];
                while (ref != null) {
                    SymbolRef next = ref.next;
                    Symbol sym = ref.getSymbol();
                    if (sym != null) {
                        String key = sym.getName();
                        int hash = key.hashCode();
                        int index = hash & newMask;
                        countInserted++;
                        ref.next = newTable[index];
                        newTable[index] = ref;
                    }
                    ref = next;
                }
            } else {
                this.table = newTable;
                this.log2Size++;
                this.mask = newMask;
                this.num_bindings = countInserted;
                return;
            }
        }
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getName());
        out.writeObject(this.prefix);
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = ((String) in.readObject()).intern();
        this.prefix = (String) in.readObject();
    }

    public Object readResolve() throws ObjectStreamException {
        String name = getName();
        if (name != null) {
            String str = this.prefix;
            String xname = (str == null || str.length() == 0) ? name : this.prefix + " -> " + name;
            Hashtable hashtable = nsTable;
            Namespace ns = (Namespace) hashtable.get(xname);
            if (ns != null) {
                return ns;
            }
            hashtable.put(xname, this);
        }
        return this;
    }

    public String toString() {
        StringBuilder sbuf = new StringBuilder("#,(namespace \"");
        sbuf.append(this.name);
        sbuf.append('\"');
        String str = this.prefix;
        if (str != null && str != "") {
            sbuf.append(' ');
            sbuf.append(this.prefix);
        }
        sbuf.append(')');
        return sbuf.toString();
    }
}
