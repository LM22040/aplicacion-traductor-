package gnu.mapping;

/* loaded from: classes.dex */
public class InheritingEnvironment extends SimpleEnvironment {
    int baseTimestamp;
    Environment[] inherited;
    Namespace[] namespaceMap;
    int numInherited;
    Object[] propertyMap;

    public InheritingEnvironment(String name, Environment parent) {
        super(name);
        addParent(parent);
        if (parent instanceof SimpleEnvironment) {
            SimpleEnvironment simpleEnvironment = (SimpleEnvironment) parent;
            int timestamp = simpleEnvironment.currentTimestamp + 1;
            simpleEnvironment.currentTimestamp = timestamp;
            this.baseTimestamp = timestamp;
            this.currentTimestamp = timestamp;
        }
    }

    public final int getNumParents() {
        return this.numInherited;
    }

    public final Environment getParent(int index) {
        return this.inherited[index];
    }

    public void addParent(Environment env) {
        int i = this.numInherited;
        if (i == 0) {
            this.inherited = new Environment[4];
        } else {
            Environment[] environmentArr = this.inherited;
            if (i <= environmentArr.length) {
                Environment[] newInherited = new Environment[i * 2];
                System.arraycopy(environmentArr, 0, newInherited, 0, i);
                this.inherited = newInherited;
            }
        }
        Environment[] environmentArr2 = this.inherited;
        int i2 = this.numInherited;
        environmentArr2[i2] = env;
        this.numInherited = i2 + 1;
    }

    public NamedLocation lookupInherited(Symbol name, Object property, int hash) {
        for (int i = 0; i < this.numInherited; i++) {
            Symbol sym = name;
            Object prop = property;
            Object[] objArr = this.namespaceMap;
            if (objArr != null && objArr.length > i * 2) {
                Object srcNamespace = objArr[i * 2];
                Object dstNamespace = objArr[(i * 2) + 1];
                if (srcNamespace != null || dstNamespace != null) {
                    if (name.getNamespace() != dstNamespace) {
                        continue;
                    } else {
                        sym = Symbol.make(srcNamespace, name.getName());
                    }
                }
            }
            Object[] objArr2 = this.propertyMap;
            if (objArr2 != null && objArr2.length > i * 2) {
                Object srcProperty = objArr2[i * 2];
                Object dstProperty = objArr2[(i * 2) + 1];
                if (srcProperty != null || dstProperty != null) {
                    if (property != dstProperty) {
                        continue;
                    } else {
                        prop = srcProperty;
                    }
                }
            }
            NamedLocation loc = this.inherited[i].lookup(sym, prop, hash);
            if (loc != null && loc.isBound() && (!(loc instanceof SharedLocation) || ((SharedLocation) loc).timestamp < this.baseTimestamp)) {
                return loc;
            }
        }
        return null;
    }

    @Override // gnu.mapping.SimpleEnvironment, gnu.mapping.Environment
    public NamedLocation lookup(Symbol name, Object property, int hash) {
        NamedLocation loc = super.lookup(name, property, hash);
        if (loc != null && loc.isBound()) {
            return loc;
        }
        return lookupInherited(name, property, hash);
    }

    @Override // gnu.mapping.SimpleEnvironment, gnu.mapping.Environment
    public synchronized NamedLocation getLocation(Symbol name, Object property, int hash, boolean create) {
        NamedLocation loc;
        NamedLocation loc2 = lookupDirect(name, property, hash);
        if (loc2 != null && (create || loc2.isBound())) {
            return loc2;
        }
        if ((this.flags & 32) != 0 && create) {
            loc = this.inherited[0].getLocation(name, property, hash, true);
        } else {
            loc = lookupInherited(name, property, hash);
        }
        if (loc != null) {
            if (!create) {
                return loc;
            }
            NamedLocation xloc = addUnboundLocation(name, property, hash);
            if ((1 & this.flags) == 0 && loc.isBound()) {
                redefineError(name, property, xloc);
            }
            xloc.base = loc;
            if (loc.value == IndirectableLocation.INDIRECT_FLUIDS) {
                xloc.value = loc.value;
            } else if ((this.flags & 16) != 0) {
                xloc.value = IndirectableLocation.DIRECT_ON_SET;
            } else {
                xloc.value = null;
            }
            if (xloc instanceof SharedLocation) {
                ((SharedLocation) xloc).timestamp = this.baseTimestamp;
            }
            return xloc;
        }
        return create ? addUnboundLocation(name, property, hash) : null;
    }

    @Override // gnu.mapping.SimpleEnvironment, gnu.mapping.Environment
    public LocationEnumeration enumerateAllLocations() {
        LocationEnumeration it = new LocationEnumeration(this.table, 1 << this.log2Size);
        it.env = this;
        Environment[] environmentArr = this.inherited;
        if (environmentArr != null && environmentArr.length > 0) {
            it.inherited = environmentArr[0].enumerateAllLocations();
            it.index = 0;
        }
        return it;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0013, code lost:
    
        r6.prevLoc = null;
        r6.nextLoc = r6.inherited.nextLoc;
        r3 = r6.index + 1;
        r6.index = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0024, code lost:
    
        if (r3 != r5.numInherited) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0027, code lost:
    
        r6.inherited = null;
        r6.bindings = r5.table;
        r6.index = 1 << r5.log2Size;
     */
    @Override // gnu.mapping.SimpleEnvironment, gnu.mapping.Environment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean hasMoreElements(gnu.mapping.LocationEnumeration r6) {
        /*
            r5 = this;
            gnu.mapping.LocationEnumeration r0 = r6.inherited
            if (r0 == 0) goto L55
        L4:
            gnu.mapping.NamedLocation r0 = r6.nextLoc
        L6:
            gnu.mapping.LocationEnumeration r1 = r6.inherited
            r1.nextLoc = r0
            gnu.mapping.LocationEnumeration r1 = r6.inherited
            boolean r1 = r1.hasMoreElements()
            r2 = 1
            if (r1 != 0) goto L41
            r1 = 0
            r6.prevLoc = r1
            gnu.mapping.LocationEnumeration r3 = r6.inherited
            gnu.mapping.NamedLocation r3 = r3.nextLoc
            r6.nextLoc = r3
            int r3 = r6.index
            int r3 = r3 + r2
            r6.index = r3
            int r4 = r5.numInherited
            if (r3 != r4) goto L34
        L27:
            r6.inherited = r1
            gnu.mapping.NamedLocation[] r0 = r5.table
            r6.bindings = r0
            int r0 = r5.log2Size
            int r0 = r2 << r0
            r6.index = r0
            goto L55
        L34:
            gnu.mapping.Environment[] r1 = r5.inherited
            int r2 = r6.index
            r1 = r1[r2]
            gnu.mapping.LocationEnumeration r1 = r1.enumerateAllLocations()
            r6.inherited = r1
            goto L4
        L41:
            gnu.mapping.LocationEnumeration r1 = r6.inherited
            gnu.mapping.NamedLocation r0 = r1.nextLoc
            gnu.mapping.Symbol r1 = r0.name
            java.lang.Object r3 = r0.property
            gnu.mapping.Location r1 = r5.lookup(r1, r3)
            if (r1 != r0) goto L52
            r6.nextLoc = r0
            return r2
        L52:
            gnu.mapping.NamedLocation r0 = r0.next
            goto L6
        L55:
            boolean r0 = super.hasMoreElements(r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.InheritingEnvironment.hasMoreElements(gnu.mapping.LocationEnumeration):boolean");
    }

    @Override // gnu.mapping.SimpleEnvironment
    protected void toStringBase(StringBuffer sbuf) {
        sbuf.append(" baseTs:");
        sbuf.append(this.baseTimestamp);
        for (int i = 0; i < this.numInherited; i++) {
            sbuf.append(" base:");
            sbuf.append(this.inherited[i].toStringVerbose());
        }
    }
}
