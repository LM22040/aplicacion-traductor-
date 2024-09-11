package kawa.lib.rnrs;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.util.AbstractHashTable;
import gnu.kawa.util.HashNode;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.lib.kawa.hashtable;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.Scheme;

/* compiled from: hashtables.scm */
/* loaded from: classes.dex */
public class hashtables extends ModuleBody {
    public static final hashtables $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod equal$Mnhash;
    static final ModuleMethod hash$Mnby$Mnidentity;
    static final ModuleMethod hash$Mnfor$Mneqv;
    public static final ModuleMethod hashtable$Mnclear$Ex;
    public static final ModuleMethod hashtable$Mncontains$Qu;
    public static final ModuleMethod hashtable$Mncopy;
    public static final ModuleMethod hashtable$Mndelete$Ex;
    public static final ModuleMethod hashtable$Mnentries;
    public static final ModuleMethod hashtable$Mnequivalence$Mnfunction;
    public static final ModuleMethod hashtable$Mnhash$Mnfunction;
    public static final ModuleMethod hashtable$Mnkeys;
    public static final ModuleMethod hashtable$Mnmutable$Qu;
    public static final ModuleMethod hashtable$Mnref;
    public static final ModuleMethod hashtable$Mnset$Ex;
    public static final ModuleMethod hashtable$Mnsize;
    public static final ModuleMethod hashtable$Mnupdate$Ex;
    public static final ModuleMethod hashtable$Qu;
    public static final ModuleMethod make$Mneq$Mnhashtable;
    public static final ModuleMethod make$Mneqv$Mnhashtable;
    public static final ModuleMethod make$Mnhashtable;
    public static final ModuleMethod string$Mnci$Mnhash;
    public static final ModuleMethod string$Mnhash;
    public static final ModuleMethod symbol$Mnhash;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("symbol-hash").readResolve();
        Lit22 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("string-ci-hash").readResolve();
        Lit21 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("string-hash").readResolve();
        Lit20 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("equal-hash").readResolve();
        Lit19 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("hashtable-mutable?").readResolve();
        Lit18 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("hashtable-hash-function").readResolve();
        Lit17 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("hashtable-equivalence-function").readResolve();
        Lit16 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("hashtable-entries").readResolve();
        Lit15 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("hashtable-keys").readResolve();
        Lit14 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("hashtable-clear!").readResolve();
        Lit13 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("hashtable-copy").readResolve();
        Lit12 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("hashtable-update!").readResolve();
        Lit11 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("hashtable-contains?").readResolve();
        Lit10 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("hashtable-delete!").readResolve();
        Lit9 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("hashtable-set!").readResolve();
        Lit8 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = (SimpleSymbol) new SimpleSymbol("hashtable-ref").readResolve();
        Lit7 = simpleSymbol16;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("hashtable-size").readResolve();
        Lit6 = simpleSymbol17;
        SimpleSymbol simpleSymbol18 = (SimpleSymbol) new SimpleSymbol("hashtable?").readResolve();
        Lit5 = simpleSymbol18;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("make-hashtable").readResolve();
        Lit4 = simpleSymbol19;
        SimpleSymbol simpleSymbol20 = (SimpleSymbol) new SimpleSymbol("make-eqv-hashtable").readResolve();
        Lit3 = simpleSymbol20;
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol("make-eq-hashtable").readResolve();
        Lit2 = simpleSymbol21;
        SimpleSymbol simpleSymbol22 = (SimpleSymbol) new SimpleSymbol("hash-for-eqv").readResolve();
        Lit1 = simpleSymbol22;
        SimpleSymbol simpleSymbol23 = (SimpleSymbol) new SimpleSymbol("hash-by-identity").readResolve();
        Lit0 = simpleSymbol23;
        hashtables hashtablesVar = new hashtables();
        $instance = hashtablesVar;
        hash$Mnby$Mnidentity = new ModuleMethod(hashtablesVar, 1, simpleSymbol23, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hash$Mnfor$Mneqv = new ModuleMethod(hashtablesVar, 2, simpleSymbol22, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mneq$Mnhashtable = new ModuleMethod(hashtablesVar, 3, simpleSymbol21, 4096);
        make$Mneqv$Mnhashtable = new ModuleMethod(hashtablesVar, 5, simpleSymbol20, 4096);
        make$Mnhashtable = new ModuleMethod(hashtablesVar, 7, simpleSymbol19, 12290);
        hashtable$Qu = new ModuleMethod(hashtablesVar, 9, simpleSymbol18, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Mnsize = new ModuleMethod(hashtablesVar, 10, simpleSymbol17, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Mnref = new ModuleMethod(hashtablesVar, 11, simpleSymbol16, 12291);
        hashtable$Mnset$Ex = new ModuleMethod(hashtablesVar, 12, simpleSymbol15, 12291);
        hashtable$Mndelete$Ex = new ModuleMethod(hashtablesVar, 13, simpleSymbol14, 8194);
        hashtable$Mncontains$Qu = new ModuleMethod(hashtablesVar, 14, simpleSymbol13, 8194);
        hashtable$Mnupdate$Ex = new ModuleMethod(hashtablesVar, 15, simpleSymbol12, 16388);
        hashtable$Mncopy = new ModuleMethod(hashtablesVar, 16, simpleSymbol11, 8193);
        hashtable$Mnclear$Ex = new ModuleMethod(hashtablesVar, 18, simpleSymbol10, 8193);
        hashtable$Mnkeys = new ModuleMethod(hashtablesVar, 20, simpleSymbol9, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Mnentries = new ModuleMethod(hashtablesVar, 21, simpleSymbol8, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Mnequivalence$Mnfunction = new ModuleMethod(hashtablesVar, 22, simpleSymbol7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Mnhash$Mnfunction = new ModuleMethod(hashtablesVar, 23, simpleSymbol6, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Mnmutable$Qu = new ModuleMethod(hashtablesVar, 24, simpleSymbol5, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        equal$Mnhash = new ModuleMethod(hashtablesVar, 25, simpleSymbol4, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnhash = new ModuleMethod(hashtablesVar, 26, simpleSymbol3, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnci$Mnhash = new ModuleMethod(hashtablesVar, 27, simpleSymbol2, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        symbol$Mnhash = new ModuleMethod(hashtablesVar, 28, simpleSymbol, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtablesVar.run();
    }

    public hashtables() {
        ModuleInfo.register(this);
    }

    public static hashtable.HashTable hashtableCopy(hashtable.HashTable hashTable) {
        return hashtableCopy(hashTable, false);
    }

    public static hashtable.HashTable makeEqHashtable() {
        return makeEqHashtable(AbstractHashTable.DEFAULT_INITIAL_SIZE);
    }

    public static hashtable.HashTable makeEqvHashtable() {
        return makeEqvHashtable(AbstractHashTable.DEFAULT_INITIAL_SIZE);
    }

    public static hashtable.HashTable makeHashtable(Procedure procedure, Procedure procedure2) {
        return makeHashtable(procedure, procedure2, AbstractHashTable.DEFAULT_INITIAL_SIZE);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
    }

    static int hashByIdentity(Object obj) {
        return System.identityHashCode(obj);
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 1:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 2:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 3:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 4:
            case 6:
            case 7:
            case 8:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 17:
            case 19:
            default:
                return super.match1(moduleMethod, obj, callContext);
            case 5:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 9:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 10:
                if (!(obj instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 16:
                if (!(obj instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 18:
                if (!(obj instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 20:
                if (!(obj instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 21:
                if (!(obj instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 22:
                if (!(obj instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 23:
                if (!(obj instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 24:
                if (!(obj instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 25:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 26:
                if (!(obj instanceof CharSequence)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 27:
                if (!(obj instanceof CharSequence)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 28:
                if (!(obj instanceof Symbol)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
        }
    }

    static int hashForEqv(Object obj) {
        return obj.hashCode();
    }

    public static hashtable.HashTable makeEqHashtable(int k) {
        return new hashtable.HashTable(Scheme.isEq, hash$Mnby$Mnidentity, AbstractHashTable.DEFAULT_INITIAL_SIZE);
    }

    @Override // gnu.expr.ModuleBody
    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 3:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 4:
            default:
                return super.match0(moduleMethod, callContext);
            case 5:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
        }
    }

    public static hashtable.HashTable makeEqvHashtable(int k) {
        return new hashtable.HashTable(Scheme.isEqv, hash$Mnfor$Mneqv, AbstractHashTable.DEFAULT_INITIAL_SIZE);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply0(ModuleMethod moduleMethod) {
        switch (moduleMethod.selector) {
            case 3:
                return makeEqHashtable();
            case 4:
            default:
                return super.apply0(moduleMethod);
            case 5:
                return makeEqvHashtable();
        }
    }

    public static hashtable.HashTable makeHashtable(Procedure comparison, Procedure hash, int size) {
        return new hashtable.HashTable(comparison, hash, size);
    }

    @Override // gnu.expr.ModuleBody
    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 7:
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (!(obj2 instanceof Procedure)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 13:
                if (!(obj instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 14:
                if (!(obj instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 16:
                if (!(obj instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 18:
                if (!(obj instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod, obj, obj2, callContext);
        }
    }

    @Override // gnu.expr.ModuleBody
    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 7:
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (!(obj2 instanceof Procedure)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 11:
                if (!(obj instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 12:
                if (!(obj instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            default:
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
        }
    }

    public static boolean isHashtable(Object obj) {
        return obj instanceof hashtable.HashTable;
    }

    public static int hashtableSize(hashtable.HashTable ht) {
        return ht.size();
    }

    public static Object hashtableRef(hashtable.HashTable ht, Object key, Object obj) {
        HashNode node = ht.getNode(key);
        return node == null ? obj : node.getValue();
    }

    public static void hashtableSet$Ex(hashtable.HashTable ht, Object key, Object value) {
        hashtable.hashtableCheckMutable(ht);
        ht.put(key, value);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        switch (moduleMethod.selector) {
            case 7:
                try {
                    try {
                        try {
                            return makeHashtable((Procedure) obj, (Procedure) obj2, ((Number) obj3).intValue());
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "make-hashtable", 3, obj3);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "make-hashtable", 2, obj2);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "make-hashtable", 1, obj);
                }
            case 11:
                try {
                    return hashtableRef((hashtable.HashTable) obj, obj2, obj3);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "hashtable-ref", 1, obj);
                }
            case 12:
                try {
                    hashtableSet$Ex((hashtable.HashTable) obj, obj2, obj3);
                    return Values.empty;
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "hashtable-set!", 1, obj);
                }
            default:
                return super.apply3(moduleMethod, obj, obj2, obj3);
        }
    }

    public static void hashtableDelete$Ex(hashtable.HashTable ht, Object key) {
        hashtable.hashtableCheckMutable(ht);
        ht.remove(key);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isHashtableContains(hashtable.HashTable ht, Object key) {
        return ((ht.getNode(key) == null ? 1 : 0) + 1) & 1;
    }

    public static Object hashtableUpdate$Ex(hashtable.HashTable ht, Object key, Procedure proc, Object obj) {
        hashtable.hashtableCheckMutable(ht);
        HashNode node = ht.getNode(key);
        if (node == null) {
            hashtableSet$Ex(ht, key, proc.apply1(obj));
            return Values.empty;
        }
        return node.setValue(proc.apply1(node.getValue()));
    }

    @Override // gnu.expr.ModuleBody
    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        if (moduleMethod.selector != 15) {
            return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
        }
        try {
            try {
                return hashtableUpdate$Ex((hashtable.HashTable) obj, obj2, (Procedure) obj3, obj4);
            } catch (ClassCastException e) {
                throw new WrongType(e, "hashtable-update!", 3, obj3);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "hashtable-update!", 1, obj);
        }
    }

    @Override // gnu.expr.ModuleBody
    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        if (moduleMethod.selector != 15) {
            return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
        }
        if (!(obj instanceof hashtable.HashTable)) {
            return -786431;
        }
        callContext.value1 = obj;
        callContext.value2 = obj2;
        if (!(obj3 instanceof Procedure)) {
            return -786429;
        }
        callContext.value3 = obj3;
        callContext.value4 = obj4;
        callContext.proc = moduleMethod;
        callContext.pc = 4;
        return 0;
    }

    public static hashtable.HashTable hashtableCopy(hashtable.HashTable ht, boolean mutable) {
        return new hashtable.HashTable(ht, mutable);
    }

    public static void hashtableClear$Ex(hashtable.HashTable ht, int k) {
        hashtable.hashtableCheckMutable(ht);
        ht.clear();
    }

    @Override // gnu.expr.ModuleBody
    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 7:
                try {
                    try {
                        return makeHashtable((Procedure) obj, (Procedure) obj2);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "make-hashtable", 2, obj2);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "make-hashtable", 1, obj);
                }
            case 13:
                try {
                    hashtableDelete$Ex((hashtable.HashTable) obj, obj2);
                    return Values.empty;
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "hashtable-delete!", 1, obj);
                }
            case 14:
                try {
                    return isHashtableContains((hashtable.HashTable) obj, obj2) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "hashtable-contains?", 1, obj);
                }
            case 16:
                try {
                    try {
                        return hashtableCopy((hashtable.HashTable) obj, obj2 != Boolean.FALSE);
                    } catch (ClassCastException e5) {
                        throw new WrongType(e5, "hashtable-copy", 2, obj2);
                    }
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "hashtable-copy", 1, obj);
                }
            case 18:
                try {
                    try {
                        hashtableClear$Ex((hashtable.HashTable) obj, ((Number) obj2).intValue());
                        return Values.empty;
                    } catch (ClassCastException e7) {
                        throw new WrongType(e7, "hashtable-clear!", 2, obj2);
                    }
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "hashtable-clear!", 1, obj);
                }
            default:
                return super.apply2(moduleMethod, obj, obj2);
        }
    }

    public static FVector hashtableKeys(hashtable.HashTable ht) {
        return ht.keysVector();
    }

    public static Object hashtableEntries(hashtable.HashTable ht) {
        Pair pair = ht.entriesVectorPair();
        return misc.values(lists.car.apply1(pair), lists.cdr.apply1(pair));
    }

    public static Procedure hashtableEquivalenceFunction(hashtable.HashTable ht) {
        return (Procedure) ht.equivalenceFunction.apply1(ht);
    }

    public static Object hashtableHashFunction(hashtable.HashTable ht) {
        Object hasher = ht.hashFunction.apply1(ht);
        Object x = Scheme.isEqv.apply2(hasher, hash$Mnby$Mnidentity);
        if (x != Boolean.FALSE) {
            if (x == Boolean.FALSE) {
                return hasher;
            }
        } else if (Scheme.isEqv.apply2(hasher, hash$Mnfor$Mneqv) == Boolean.FALSE) {
            return hasher;
        }
        return Boolean.FALSE;
    }

    public static Object isHashtableMutable(hashtable.HashTable ht) {
        return Scheme.applyToArgs.apply1(ht.mutable ? Boolean.TRUE : Boolean.FALSE);
    }

    public static int equalHash(Object key) {
        return key.hashCode();
    }

    public static int stringHash(CharSequence s) {
        return s.hashCode();
    }

    public static int stringCiHash(CharSequence s) {
        return s.toString().toLowerCase().hashCode();
    }

    public static int symbolHash(Symbol s) {
        return s.hashCode();
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 1:
                return Integer.valueOf(hashByIdentity(obj));
            case 2:
                return Integer.valueOf(hashForEqv(obj));
            case 3:
                try {
                    return makeEqHashtable(((Number) obj).intValue());
                } catch (ClassCastException e) {
                    throw new WrongType(e, "make-eq-hashtable", 1, obj);
                }
            case 4:
            case 6:
            case 7:
            case 8:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 17:
            case 19:
            default:
                return super.apply1(moduleMethod, obj);
            case 5:
                try {
                    return makeEqvHashtable(((Number) obj).intValue());
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "make-eqv-hashtable", 1, obj);
                }
            case 9:
                return isHashtable(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 10:
                try {
                    return Integer.valueOf(hashtableSize((hashtable.HashTable) obj));
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "hashtable-size", 1, obj);
                }
            case 16:
                try {
                    return hashtableCopy((hashtable.HashTable) obj);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "hashtable-copy", 1, obj);
                }
            case 18:
                try {
                    hashtableClear$Ex((hashtable.HashTable) obj, 64);
                    return Values.empty;
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "hashtable-clear!", 1, obj);
                }
            case 20:
                try {
                    return hashtableKeys((hashtable.HashTable) obj);
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "hashtable-keys", 1, obj);
                }
            case 21:
                try {
                    return hashtableEntries((hashtable.HashTable) obj);
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "hashtable-entries", 1, obj);
                }
            case 22:
                try {
                    return hashtableEquivalenceFunction((hashtable.HashTable) obj);
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "hashtable-equivalence-function", 1, obj);
                }
            case 23:
                try {
                    return hashtableHashFunction((hashtable.HashTable) obj);
                } catch (ClassCastException e9) {
                    throw new WrongType(e9, "hashtable-hash-function", 1, obj);
                }
            case 24:
                try {
                    return isHashtableMutable((hashtable.HashTable) obj);
                } catch (ClassCastException e10) {
                    throw new WrongType(e10, "hashtable-mutable?", 1, obj);
                }
            case 25:
                return Integer.valueOf(equalHash(obj));
            case 26:
                try {
                    return Integer.valueOf(stringHash((CharSequence) obj));
                } catch (ClassCastException e11) {
                    throw new WrongType(e11, "string-hash", 1, obj);
                }
            case 27:
                try {
                    return Integer.valueOf(stringCiHash((CharSequence) obj));
                } catch (ClassCastException e12) {
                    throw new WrongType(e12, "string-ci-hash", 1, obj);
                }
            case 28:
                try {
                    return Integer.valueOf(symbolHash((Symbol) obj));
                } catch (ClassCastException e13) {
                    throw new WrongType(e13, "symbol-hash", 1, obj);
                }
        }
    }
}
