package gnu.kawa.slib;

import androidx.fragment.app.FragmentTransaction;
import com.google.appinventor.components.common.ComponentConstants;
import gnu.expr.GenericProc;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.Map;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.math.Numeric;
import kawa.lang.Continuation;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.standard.Scheme;
import kawa.standard.append;
import kawa.standard.call_with_values;

/* compiled from: srfi1.scm */
/* loaded from: classes2.dex */
public class srfi1 extends ModuleBody {
    public static final Macro $Pcevery;
    public static final int $Pcprovide$Pclist$Mnlib;
    public static final int $Pcprovide$Pcsrfi$Mn1;
    public static final srfi1 $instance;
    static final IntNum Lit0;
    static final IntNum Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit100;
    static final SimpleSymbol Lit101;
    static final SimpleSymbol Lit102;
    static final SimpleSymbol Lit103;
    static final SimpleSymbol Lit104;
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
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit29;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit30;
    static final SimpleSymbol Lit31;
    static final SimpleSymbol Lit32;
    static final SimpleSymbol Lit33;
    static final SimpleSymbol Lit34;
    static final SimpleSymbol Lit35;
    static final SimpleSymbol Lit36;
    static final SimpleSymbol Lit37;
    static final SimpleSymbol Lit38;
    static final SimpleSymbol Lit39;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit40;
    static final SimpleSymbol Lit41;
    static final SimpleSymbol Lit42;
    static final SimpleSymbol Lit43;
    static final SimpleSymbol Lit44;
    static final SimpleSymbol Lit45;
    static final SimpleSymbol Lit46;
    static final SimpleSymbol Lit47;
    static final SimpleSymbol Lit48;
    static final SimpleSymbol Lit49;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit50;
    static final SimpleSymbol Lit51;
    static final SimpleSymbol Lit52;
    static final SimpleSymbol Lit53;
    static final SimpleSymbol Lit54;
    static final SimpleSymbol Lit55;
    static final SimpleSymbol Lit56;
    static final SimpleSymbol Lit57;
    static final SimpleSymbol Lit58;
    static final SimpleSymbol Lit59;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit60;
    static final SimpleSymbol Lit61;
    static final SimpleSymbol Lit62;
    static final SimpleSymbol Lit63;
    static final SimpleSymbol Lit64;
    static final SimpleSymbol Lit65;
    static final SimpleSymbol Lit66;
    static final SimpleSymbol Lit67;
    static final SimpleSymbol Lit68;
    static final SimpleSymbol Lit69;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit70;
    static final SimpleSymbol Lit71;
    static final SimpleSymbol Lit72;
    static final SimpleSymbol Lit73;
    static final SimpleSymbol Lit74;
    static final SimpleSymbol Lit75;
    static final SimpleSymbol Lit76;
    static final SimpleSymbol Lit77;
    static final SimpleSymbol Lit78;
    static final SimpleSymbol Lit79;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit80;
    static final SimpleSymbol Lit81;
    static final SimpleSymbol Lit82;
    static final SimpleSymbol Lit83;
    static final SimpleSymbol Lit84;
    static final SyntaxRules Lit85;
    static final SimpleSymbol Lit86;
    static final SimpleSymbol Lit87;
    static final SimpleSymbol Lit88;
    static final SimpleSymbol Lit89;
    static final SimpleSymbol Lit9;
    static final SimpleSymbol Lit90;
    static final SimpleSymbol Lit91;
    static final SimpleSymbol Lit92;
    static final SimpleSymbol Lit93;
    static final SimpleSymbol Lit94;
    static final SimpleSymbol Lit95;
    static final SimpleSymbol Lit96;
    static final SimpleSymbol Lit97;
    static final SimpleSymbol Lit98;
    static final SimpleSymbol Lit99;
    public static final ModuleMethod alist$Mncons;
    public static final ModuleMethod alist$Mncopy;
    public static final ModuleMethod alist$Mndelete;
    public static final ModuleMethod alist$Mndelete$Ex;
    public static final ModuleMethod any;
    public static final ModuleMethod append$Ex;
    public static final ModuleMethod append$Mnmap;
    public static final ModuleMethod append$Mnmap$Ex;
    public static final ModuleMethod append$Mnreverse;
    public static final ModuleMethod append$Mnreverse$Ex;

    /* renamed from: break, reason: not valid java name */
    public static final ModuleMethod f1break;
    public static final ModuleMethod break$Ex;
    public static final ModuleMethod car$Plcdr;
    public static final ModuleMethod circular$Mnlist;
    public static final ModuleMethod circular$Mnlist$Qu;
    public static final ModuleMethod concatenate;
    public static final ModuleMethod concatenate$Ex;
    public static final ModuleMethod cons$St;
    public static final ModuleMethod count;
    public static final ModuleMethod delete;
    public static final ModuleMethod delete$Ex;
    public static final ModuleMethod delete$Mnduplicates;
    public static final ModuleMethod delete$Mnduplicates$Ex;
    public static final ModuleMethod dotted$Mnlist$Qu;
    public static final ModuleMethod drop;
    public static final ModuleMethod drop$Mnright;
    public static final ModuleMethod drop$Mnright$Ex;
    public static final ModuleMethod drop$Mnwhile;
    public static final ModuleMethod eighth;
    public static final ModuleMethod every;
    public static final ModuleMethod fifth;
    public static final ModuleMethod filter;
    public static final ModuleMethod filter$Ex;
    public static final ModuleMethod filter$Mnmap;
    public static final ModuleMethod find;
    public static final ModuleMethod find$Mntail;
    public static GenericProc first;
    public static final ModuleMethod fold;
    public static final ModuleMethod fold$Mnright;
    public static GenericProc fourth;
    public static final ModuleMethod iota;
    static final ModuleMethod lambda$Fn64;
    static final ModuleMethod lambda$Fn78;
    public static final ModuleMethod last;
    public static final ModuleMethod last$Mnpair;
    public static final ModuleMethod length$Pl;
    public static final ModuleMethod list$Eq;
    public static final ModuleMethod list$Mncopy;
    public static final ModuleMethod list$Mnindex;
    public static final ModuleMethod list$Mntabulate;
    public static final ModuleMethod lset$Eq;
    public static final ModuleMethod lset$Ls$Eq;
    public static final ModuleMethod lset$Mnadjoin;
    public static final ModuleMethod lset$Mndiff$Plintersection;
    public static final ModuleMethod lset$Mndiff$Plintersection$Ex;
    public static final ModuleMethod lset$Mndifference;
    public static final ModuleMethod lset$Mndifference$Ex;
    public static final ModuleMethod lset$Mnintersection;
    public static final ModuleMethod lset$Mnintersection$Ex;
    public static final ModuleMethod lset$Mnunion;
    public static final ModuleMethod lset$Mnunion$Ex;
    public static final ModuleMethod lset$Mnxor;
    public static final ModuleMethod lset$Mnxor$Ex;
    public static final ModuleMethod make$Mnlist;
    public static final ModuleMethod map$Ex;
    public static Map map$Mnin$Mnorder;
    public static final ModuleMethod ninth;
    public static final ModuleMethod not$Mnpair$Qu;
    public static final ModuleMethod null$Mnlist$Qu;
    public static final ModuleMethod pair$Mnfold;
    public static final ModuleMethod pair$Mnfold$Mnright;
    public static final ModuleMethod pair$Mnfor$Mneach;
    public static final ModuleMethod partition;
    public static final ModuleMethod partition$Ex;
    public static final ModuleMethod proper$Mnlist$Qu;
    public static final ModuleMethod reduce;
    public static final ModuleMethod reduce$Mnright;
    public static final ModuleMethod remove;
    public static final ModuleMethod remove$Ex;
    public static GenericProc second;
    public static final ModuleMethod seventh;
    public static final ModuleMethod sixth;
    public static final ModuleMethod span;
    public static final ModuleMethod span$Ex;
    public static final ModuleMethod split$Mnat;
    public static final ModuleMethod split$Mnat$Ex;
    public static final ModuleMethod take;
    public static final ModuleMethod take$Ex;
    public static final ModuleMethod take$Mnright;
    public static final ModuleMethod take$Mnwhile;
    public static final ModuleMethod take$Mnwhile$Ex;
    public static final ModuleMethod tenth;
    public static GenericProc third;
    public static final ModuleMethod unfold;
    public static final ModuleMethod unfold$Mnright;
    public static final ModuleMethod unzip1;
    public static final ModuleMethod unzip2;
    public static final ModuleMethod unzip3;
    public static final ModuleMethod unzip4;
    public static final ModuleMethod unzip5;
    public static final ModuleMethod xcons;
    public static final ModuleMethod zip;

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame62 extends ModuleBody {
        Object cars$Mnfinal;
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("cdr").readResolve();
        Lit104 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("car").readResolve();
        Lit103 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("lp").readResolve();
        Lit102 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("head").readResolve();
        Lit101 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("tail").readResolve();
        Lit100 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("lset-diff+intersection!").readResolve();
        Lit99 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("lset-diff+intersection").readResolve();
        Lit98 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("lset-xor!").readResolve();
        Lit97 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("lset-xor").readResolve();
        Lit96 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("lset-difference!").readResolve();
        Lit95 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("lset-difference").readResolve();
        Lit94 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("lset-intersection!").readResolve();
        Lit93 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("lset-intersection").readResolve();
        Lit92 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("lset-union!").readResolve();
        Lit91 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("lset-union").readResolve();
        Lit90 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = (SimpleSymbol) new SimpleSymbol("lset-adjoin").readResolve();
        Lit89 = simpleSymbol16;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("lset=").readResolve();
        Lit88 = simpleSymbol17;
        SimpleSymbol simpleSymbol18 = (SimpleSymbol) new SimpleSymbol("lset<=").readResolve();
        Lit87 = simpleSymbol18;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("list-index").readResolve();
        Lit86 = simpleSymbol19;
        SimpleSymbol simpleSymbol20 = (SimpleSymbol) new SimpleSymbol("%every").readResolve();
        Lit84 = simpleSymbol20;
        SyntaxPattern syntaxPattern = new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol("null-list?").readResolve();
        Lit14 = simpleSymbol21;
        SyntaxRules syntaxRules = new SyntaxRules(new Object[]{simpleSymbol20}, new SyntaxRule[]{new SyntaxRule(syntaxPattern, "\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\f¡I\u0011\u0018\u0014\b\u0011\u0018\u001c\b\u000b\b\u0011\u0018$\b\u0011\u0018,\b\u000b\b\u0011\u00184\u0011\u0018<!\t\u0003\u0018D\u0018L", new Object[]{(SimpleSymbol) new SimpleSymbol("let").readResolve(), simpleSymbol3, simpleSymbol4, simpleSymbol2, simpleSymbol5, simpleSymbol, (SimpleSymbol) new SimpleSymbol("and").readResolve(), PairWithPosition.make(simpleSymbol21, PairWithPosition.make(simpleSymbol5, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722136), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722124), PairWithPosition.make(simpleSymbol4, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722148), PairWithPosition.make(PairWithPosition.make(simpleSymbol3, PairWithPosition.make(PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol5, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722163), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722158), PairWithPosition.make(PairWithPosition.make(simpleSymbol, PairWithPosition.make(simpleSymbol5, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722174), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722169), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722169), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722158), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722154), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722154)}, 0)}, 2);
        Lit85 = syntaxRules;
        SimpleSymbol simpleSymbol22 = (SimpleSymbol) new SimpleSymbol("every").readResolve();
        Lit83 = simpleSymbol22;
        SimpleSymbol simpleSymbol23 = (SimpleSymbol) new SimpleSymbol("any").readResolve();
        Lit82 = simpleSymbol23;
        SimpleSymbol simpleSymbol24 = (SimpleSymbol) new SimpleSymbol("break!").readResolve();
        Lit81 = simpleSymbol24;
        SimpleSymbol simpleSymbol25 = (SimpleSymbol) new SimpleSymbol("break").readResolve();
        Lit80 = simpleSymbol25;
        SimpleSymbol simpleSymbol26 = (SimpleSymbol) new SimpleSymbol("span!").readResolve();
        Lit79 = simpleSymbol26;
        SimpleSymbol simpleSymbol27 = (SimpleSymbol) new SimpleSymbol("span").readResolve();
        Lit78 = simpleSymbol27;
        SimpleSymbol simpleSymbol28 = (SimpleSymbol) new SimpleSymbol("take-while!").readResolve();
        Lit77 = simpleSymbol28;
        SimpleSymbol simpleSymbol29 = (SimpleSymbol) new SimpleSymbol("drop-while").readResolve();
        Lit76 = simpleSymbol29;
        SimpleSymbol simpleSymbol30 = (SimpleSymbol) new SimpleSymbol("take-while").readResolve();
        Lit75 = simpleSymbol30;
        SimpleSymbol simpleSymbol31 = (SimpleSymbol) new SimpleSymbol("find-tail").readResolve();
        Lit74 = simpleSymbol31;
        SimpleSymbol simpleSymbol32 = (SimpleSymbol) new SimpleSymbol("find").readResolve();
        Lit73 = simpleSymbol32;
        SimpleSymbol simpleSymbol33 = (SimpleSymbol) new SimpleSymbol("alist-delete!").readResolve();
        Lit72 = simpleSymbol33;
        SimpleSymbol simpleSymbol34 = (SimpleSymbol) new SimpleSymbol("alist-delete").readResolve();
        Lit71 = simpleSymbol34;
        SimpleSymbol simpleSymbol35 = (SimpleSymbol) new SimpleSymbol("alist-copy").readResolve();
        Lit70 = simpleSymbol35;
        SimpleSymbol simpleSymbol36 = (SimpleSymbol) new SimpleSymbol("alist-cons").readResolve();
        Lit69 = simpleSymbol36;
        SimpleSymbol simpleSymbol37 = (SimpleSymbol) new SimpleSymbol("delete-duplicates!").readResolve();
        Lit68 = simpleSymbol37;
        SimpleSymbol simpleSymbol38 = (SimpleSymbol) new SimpleSymbol("delete-duplicates").readResolve();
        Lit67 = simpleSymbol38;
        SimpleSymbol simpleSymbol39 = (SimpleSymbol) new SimpleSymbol("delete!").readResolve();
        Lit66 = simpleSymbol39;
        SimpleSymbol simpleSymbol40 = (SimpleSymbol) new SimpleSymbol("delete").readResolve();
        Lit65 = simpleSymbol40;
        SimpleSymbol simpleSymbol41 = (SimpleSymbol) new SimpleSymbol("remove!").readResolve();
        Lit64 = simpleSymbol41;
        SimpleSymbol simpleSymbol42 = (SimpleSymbol) new SimpleSymbol("remove").readResolve();
        Lit63 = simpleSymbol42;
        SimpleSymbol simpleSymbol43 = (SimpleSymbol) new SimpleSymbol("partition!").readResolve();
        Lit62 = simpleSymbol43;
        SimpleSymbol simpleSymbol44 = (SimpleSymbol) new SimpleSymbol("partition").readResolve();
        Lit61 = simpleSymbol44;
        SimpleSymbol simpleSymbol45 = (SimpleSymbol) new SimpleSymbol("filter!").readResolve();
        Lit60 = simpleSymbol45;
        SimpleSymbol simpleSymbol46 = (SimpleSymbol) new SimpleSymbol("filter").readResolve();
        Lit59 = simpleSymbol46;
        SimpleSymbol simpleSymbol47 = (SimpleSymbol) new SimpleSymbol("filter-map").readResolve();
        Lit58 = simpleSymbol47;
        SimpleSymbol simpleSymbol48 = (SimpleSymbol) new SimpleSymbol("map!").readResolve();
        Lit57 = simpleSymbol48;
        SimpleSymbol simpleSymbol49 = (SimpleSymbol) new SimpleSymbol("pair-for-each").readResolve();
        Lit56 = simpleSymbol49;
        SimpleSymbol simpleSymbol50 = (SimpleSymbol) new SimpleSymbol("append-map!").readResolve();
        Lit55 = simpleSymbol50;
        SimpleSymbol simpleSymbol51 = (SimpleSymbol) new SimpleSymbol("append-map").readResolve();
        Lit54 = simpleSymbol51;
        SimpleSymbol simpleSymbol52 = (SimpleSymbol) new SimpleSymbol("reduce-right").readResolve();
        Lit53 = simpleSymbol52;
        SimpleSymbol simpleSymbol53 = (SimpleSymbol) new SimpleSymbol("reduce").readResolve();
        Lit52 = simpleSymbol53;
        SimpleSymbol simpleSymbol54 = (SimpleSymbol) new SimpleSymbol("pair-fold").readResolve();
        Lit51 = simpleSymbol54;
        SimpleSymbol simpleSymbol55 = (SimpleSymbol) new SimpleSymbol("pair-fold-right").readResolve();
        Lit50 = simpleSymbol55;
        SimpleSymbol simpleSymbol56 = (SimpleSymbol) new SimpleSymbol("fold-right").readResolve();
        Lit49 = simpleSymbol56;
        SimpleSymbol simpleSymbol57 = (SimpleSymbol) new SimpleSymbol("fold").readResolve();
        Lit48 = simpleSymbol57;
        SimpleSymbol simpleSymbol58 = (SimpleSymbol) new SimpleSymbol("unfold").readResolve();
        Lit47 = simpleSymbol58;
        SimpleSymbol simpleSymbol59 = (SimpleSymbol) new SimpleSymbol("unfold-right").readResolve();
        Lit46 = simpleSymbol59;
        SimpleSymbol simpleSymbol60 = (SimpleSymbol) new SimpleSymbol("count").readResolve();
        Lit45 = simpleSymbol60;
        SimpleSymbol simpleSymbol61 = (SimpleSymbol) new SimpleSymbol("concatenate!").readResolve();
        Lit44 = simpleSymbol61;
        SimpleSymbol simpleSymbol62 = (SimpleSymbol) new SimpleSymbol("concatenate").readResolve();
        Lit43 = simpleSymbol62;
        SimpleSymbol simpleSymbol63 = (SimpleSymbol) new SimpleSymbol("append-reverse!").readResolve();
        Lit42 = simpleSymbol63;
        SimpleSymbol simpleSymbol64 = (SimpleSymbol) new SimpleSymbol("append-reverse").readResolve();
        Lit41 = simpleSymbol64;
        SimpleSymbol simpleSymbol65 = (SimpleSymbol) new SimpleSymbol("append!").readResolve();
        Lit40 = simpleSymbol65;
        SimpleSymbol simpleSymbol66 = (SimpleSymbol) new SimpleSymbol("unzip5").readResolve();
        Lit39 = simpleSymbol66;
        SimpleSymbol simpleSymbol67 = (SimpleSymbol) new SimpleSymbol("unzip4").readResolve();
        Lit38 = simpleSymbol67;
        SimpleSymbol simpleSymbol68 = (SimpleSymbol) new SimpleSymbol("unzip3").readResolve();
        Lit37 = simpleSymbol68;
        SimpleSymbol simpleSymbol69 = (SimpleSymbol) new SimpleSymbol("unzip2").readResolve();
        Lit36 = simpleSymbol69;
        SimpleSymbol simpleSymbol70 = (SimpleSymbol) new SimpleSymbol("unzip1").readResolve();
        Lit35 = simpleSymbol70;
        SimpleSymbol simpleSymbol71 = (SimpleSymbol) new SimpleSymbol("last-pair").readResolve();
        Lit34 = simpleSymbol71;
        SimpleSymbol simpleSymbol72 = (SimpleSymbol) new SimpleSymbol("last").readResolve();
        Lit33 = simpleSymbol72;
        SimpleSymbol simpleSymbol73 = (SimpleSymbol) new SimpleSymbol("split-at!").readResolve();
        Lit32 = simpleSymbol73;
        SimpleSymbol simpleSymbol74 = (SimpleSymbol) new SimpleSymbol("split-at").readResolve();
        Lit31 = simpleSymbol74;
        SimpleSymbol simpleSymbol75 = (SimpleSymbol) new SimpleSymbol("drop-right!").readResolve();
        Lit30 = simpleSymbol75;
        SimpleSymbol simpleSymbol76 = (SimpleSymbol) new SimpleSymbol("drop-right").readResolve();
        Lit29 = simpleSymbol76;
        SimpleSymbol simpleSymbol77 = (SimpleSymbol) new SimpleSymbol("take-right").readResolve();
        Lit28 = simpleSymbol77;
        SimpleSymbol simpleSymbol78 = (SimpleSymbol) new SimpleSymbol("take!").readResolve();
        Lit27 = simpleSymbol78;
        SimpleSymbol simpleSymbol79 = (SimpleSymbol) new SimpleSymbol("drop").readResolve();
        Lit26 = simpleSymbol79;
        SimpleSymbol simpleSymbol80 = (SimpleSymbol) new SimpleSymbol("take").readResolve();
        Lit25 = simpleSymbol80;
        SimpleSymbol simpleSymbol81 = (SimpleSymbol) new SimpleSymbol("car+cdr").readResolve();
        Lit24 = simpleSymbol81;
        SimpleSymbol simpleSymbol82 = (SimpleSymbol) new SimpleSymbol("tenth").readResolve();
        Lit23 = simpleSymbol82;
        SimpleSymbol simpleSymbol83 = (SimpleSymbol) new SimpleSymbol("ninth").readResolve();
        Lit22 = simpleSymbol83;
        SimpleSymbol simpleSymbol84 = (SimpleSymbol) new SimpleSymbol("eighth").readResolve();
        Lit21 = simpleSymbol84;
        SimpleSymbol simpleSymbol85 = (SimpleSymbol) new SimpleSymbol("seventh").readResolve();
        Lit20 = simpleSymbol85;
        SimpleSymbol simpleSymbol86 = (SimpleSymbol) new SimpleSymbol("sixth").readResolve();
        Lit19 = simpleSymbol86;
        SimpleSymbol simpleSymbol87 = (SimpleSymbol) new SimpleSymbol("fifth").readResolve();
        Lit18 = simpleSymbol87;
        SimpleSymbol simpleSymbol88 = (SimpleSymbol) new SimpleSymbol("zip").readResolve();
        Lit17 = simpleSymbol88;
        SimpleSymbol simpleSymbol89 = (SimpleSymbol) new SimpleSymbol("length+").readResolve();
        Lit16 = simpleSymbol89;
        SimpleSymbol simpleSymbol90 = (SimpleSymbol) new SimpleSymbol("list=").readResolve();
        Lit15 = simpleSymbol90;
        SimpleSymbol simpleSymbol91 = (SimpleSymbol) new SimpleSymbol("not-pair?").readResolve();
        Lit13 = simpleSymbol91;
        SimpleSymbol simpleSymbol92 = (SimpleSymbol) new SimpleSymbol("circular-list?").readResolve();
        Lit12 = simpleSymbol92;
        SimpleSymbol simpleSymbol93 = (SimpleSymbol) new SimpleSymbol("dotted-list?").readResolve();
        Lit11 = simpleSymbol93;
        SimpleSymbol simpleSymbol94 = (SimpleSymbol) new SimpleSymbol("proper-list?").readResolve();
        Lit10 = simpleSymbol94;
        SimpleSymbol simpleSymbol95 = (SimpleSymbol) new SimpleSymbol("circular-list").readResolve();
        Lit9 = simpleSymbol95;
        SimpleSymbol simpleSymbol96 = (SimpleSymbol) new SimpleSymbol("iota").readResolve();
        Lit8 = simpleSymbol96;
        SimpleSymbol simpleSymbol97 = (SimpleSymbol) new SimpleSymbol("list-copy").readResolve();
        Lit7 = simpleSymbol97;
        SimpleSymbol simpleSymbol98 = (SimpleSymbol) new SimpleSymbol("cons*").readResolve();
        Lit6 = simpleSymbol98;
        SimpleSymbol simpleSymbol99 = (SimpleSymbol) new SimpleSymbol("list-tabulate").readResolve();
        Lit5 = simpleSymbol99;
        SimpleSymbol simpleSymbol100 = (SimpleSymbol) new SimpleSymbol("make-list").readResolve();
        Lit4 = simpleSymbol100;
        SimpleSymbol simpleSymbol101 = (SimpleSymbol) new SimpleSymbol("xcons").readResolve();
        Lit3 = simpleSymbol101;
        Lit2 = (SimpleSymbol) new SimpleSymbol("tmp").readResolve();
        Lit1 = IntNum.make(1);
        Lit0 = IntNum.make(0);
        srfi1 srfi1Var = new srfi1();
        $instance = srfi1Var;
        $Pcprovide$Pcsrfi$Mn1 = 123;
        $Pcprovide$Pclist$Mnlib = 123;
        xcons = new ModuleMethod(srfi1Var, 78, simpleSymbol101, 8194);
        make$Mnlist = new ModuleMethod(srfi1Var, 79, simpleSymbol100, -4095);
        list$Mntabulate = new ModuleMethod(srfi1Var, 80, simpleSymbol99, 8194);
        cons$St = new ModuleMethod(srfi1Var, 81, simpleSymbol98, -4096);
        list$Mncopy = new ModuleMethod(srfi1Var, 82, simpleSymbol97, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        iota = new ModuleMethod(srfi1Var, 83, simpleSymbol96, 12289);
        circular$Mnlist = new ModuleMethod(srfi1Var, 86, simpleSymbol95, -4095);
        proper$Mnlist$Qu = new ModuleMethod(srfi1Var, 87, simpleSymbol94, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        dotted$Mnlist$Qu = new ModuleMethod(srfi1Var, 88, simpleSymbol93, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        circular$Mnlist$Qu = new ModuleMethod(srfi1Var, 89, simpleSymbol92, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        not$Mnpair$Qu = new ModuleMethod(srfi1Var, 90, simpleSymbol91, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        null$Mnlist$Qu = new ModuleMethod(srfi1Var, 91, simpleSymbol21, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Eq = new ModuleMethod(srfi1Var, 92, simpleSymbol90, -4095);
        length$Pl = new ModuleMethod(srfi1Var, 93, simpleSymbol89, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        zip = new ModuleMethod(srfi1Var, 94, simpleSymbol88, -4095);
        fifth = new ModuleMethod(srfi1Var, 95, simpleSymbol87, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        sixth = new ModuleMethod(srfi1Var, 96, simpleSymbol86, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        seventh = new ModuleMethod(srfi1Var, 97, simpleSymbol85, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        eighth = new ModuleMethod(srfi1Var, 98, simpleSymbol84, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ninth = new ModuleMethod(srfi1Var, 99, simpleSymbol83, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        tenth = new ModuleMethod(srfi1Var, 100, simpleSymbol82, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        car$Plcdr = new ModuleMethod(srfi1Var, 101, simpleSymbol81, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        take = new ModuleMethod(srfi1Var, 102, simpleSymbol80, 8194);
        drop = new ModuleMethod(srfi1Var, 103, simpleSymbol79, 8194);
        take$Ex = new ModuleMethod(srfi1Var, 104, simpleSymbol78, 8194);
        take$Mnright = new ModuleMethod(srfi1Var, 105, simpleSymbol77, 8194);
        drop$Mnright = new ModuleMethod(srfi1Var, 106, simpleSymbol76, 8194);
        drop$Mnright$Ex = new ModuleMethod(srfi1Var, 107, simpleSymbol75, 8194);
        split$Mnat = new ModuleMethod(srfi1Var, 108, simpleSymbol74, 8194);
        split$Mnat$Ex = new ModuleMethod(srfi1Var, 109, simpleSymbol73, 8194);
        last = new ModuleMethod(srfi1Var, 110, simpleSymbol72, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        last$Mnpair = new ModuleMethod(srfi1Var, 111, simpleSymbol71, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        unzip1 = new ModuleMethod(srfi1Var, 112, simpleSymbol70, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        unzip2 = new ModuleMethod(srfi1Var, 113, simpleSymbol69, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        unzip3 = new ModuleMethod(srfi1Var, 114, simpleSymbol68, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        unzip4 = new ModuleMethod(srfi1Var, 115, simpleSymbol67, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        unzip5 = new ModuleMethod(srfi1Var, 116, simpleSymbol66, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        append$Ex = new ModuleMethod(srfi1Var, 117, simpleSymbol65, -4096);
        append$Mnreverse = new ModuleMethod(srfi1Var, 118, simpleSymbol64, 8194);
        append$Mnreverse$Ex = new ModuleMethod(srfi1Var, 119, simpleSymbol63, 8194);
        concatenate = new ModuleMethod(srfi1Var, 120, simpleSymbol62, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        concatenate$Ex = new ModuleMethod(srfi1Var, 121, simpleSymbol61, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        count = new ModuleMethod(srfi1Var, 122, simpleSymbol60, -4094);
        unfold$Mnright = new ModuleMethod(srfi1Var, 123, simpleSymbol59, 20484);
        unfold = new ModuleMethod(srfi1Var, 125, simpleSymbol58, -4092);
        fold = new ModuleMethod(srfi1Var, 126, simpleSymbol57, -4093);
        fold$Mnright = new ModuleMethod(srfi1Var, 127, simpleSymbol56, -4093);
        pair$Mnfold$Mnright = new ModuleMethod(srfi1Var, 128, simpleSymbol55, -4093);
        pair$Mnfold = new ModuleMethod(srfi1Var, 129, simpleSymbol54, -4093);
        reduce = new ModuleMethod(srfi1Var, 130, simpleSymbol53, 12291);
        reduce$Mnright = new ModuleMethod(srfi1Var, 131, simpleSymbol52, 12291);
        append$Mnmap = new ModuleMethod(srfi1Var, 132, simpleSymbol51, -4094);
        append$Mnmap$Ex = new ModuleMethod(srfi1Var, 133, simpleSymbol50, -4094);
        pair$Mnfor$Mneach = new ModuleMethod(srfi1Var, 134, simpleSymbol49, -4094);
        map$Ex = new ModuleMethod(srfi1Var, 135, simpleSymbol48, -4094);
        filter$Mnmap = new ModuleMethod(srfi1Var, 136, simpleSymbol47, -4094);
        filter = new ModuleMethod(srfi1Var, 137, simpleSymbol46, 8194);
        filter$Ex = new ModuleMethod(srfi1Var, 138, simpleSymbol45, 8194);
        partition = new ModuleMethod(srfi1Var, 139, simpleSymbol44, 8194);
        partition$Ex = new ModuleMethod(srfi1Var, 140, simpleSymbol43, 8194);
        remove = new ModuleMethod(srfi1Var, 141, simpleSymbol42, 8194);
        remove$Ex = new ModuleMethod(srfi1Var, 142, simpleSymbol41, 8194);
        delete = new ModuleMethod(srfi1Var, 143, simpleSymbol40, 12290);
        delete$Ex = new ModuleMethod(srfi1Var, 145, simpleSymbol39, 12290);
        delete$Mnduplicates = new ModuleMethod(srfi1Var, 147, simpleSymbol38, 8193);
        delete$Mnduplicates$Ex = new ModuleMethod(srfi1Var, 149, simpleSymbol37, 8193);
        alist$Mncons = new ModuleMethod(srfi1Var, 151, simpleSymbol36, 12291);
        alist$Mncopy = new ModuleMethod(srfi1Var, 152, simpleSymbol35, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        alist$Mndelete = new ModuleMethod(srfi1Var, 153, simpleSymbol34, 12290);
        alist$Mndelete$Ex = new ModuleMethod(srfi1Var, 155, simpleSymbol33, 12290);
        find = new ModuleMethod(srfi1Var, 157, simpleSymbol32, 8194);
        find$Mntail = new ModuleMethod(srfi1Var, 158, simpleSymbol31, 8194);
        take$Mnwhile = new ModuleMethod(srfi1Var, 159, simpleSymbol30, 8194);
        drop$Mnwhile = new ModuleMethod(srfi1Var, ComponentConstants.TEXTBOX_PREFERRED_WIDTH, simpleSymbol29, 8194);
        take$Mnwhile$Ex = new ModuleMethod(srfi1Var, 161, simpleSymbol28, 8194);
        span = new ModuleMethod(srfi1Var, 162, simpleSymbol27, 8194);
        span$Ex = new ModuleMethod(srfi1Var, 163, simpleSymbol26, 8194);
        f1break = new ModuleMethod(srfi1Var, 164, simpleSymbol25, 8194);
        break$Ex = new ModuleMethod(srfi1Var, 165, simpleSymbol24, 8194);
        any = new ModuleMethod(srfi1Var, 166, simpleSymbol23, -4094);
        every = new ModuleMethod(srfi1Var, 167, simpleSymbol22, -4094);
        $Pcevery = Macro.make(simpleSymbol20, syntaxRules, srfi1Var);
        list$Mnindex = new ModuleMethod(srfi1Var, 168, simpleSymbol19, -4094);
        lset$Ls$Eq = new ModuleMethod(srfi1Var, 169, simpleSymbol18, -4095);
        lset$Eq = new ModuleMethod(srfi1Var, 170, simpleSymbol17, -4095);
        lset$Mnadjoin = new ModuleMethod(srfi1Var, 171, simpleSymbol16, -4094);
        lset$Mnunion = new ModuleMethod(srfi1Var, 172, simpleSymbol15, -4095);
        lset$Mnunion$Ex = new ModuleMethod(srfi1Var, 173, simpleSymbol14, -4095);
        lset$Mnintersection = new ModuleMethod(srfi1Var, 174, simpleSymbol13, -4094);
        lset$Mnintersection$Ex = new ModuleMethod(srfi1Var, 175, simpleSymbol12, -4094);
        lset$Mndifference = new ModuleMethod(srfi1Var, 176, simpleSymbol11, -4094);
        lset$Mndifference$Ex = new ModuleMethod(srfi1Var, 177, simpleSymbol10, -4094);
        lset$Mnxor = new ModuleMethod(srfi1Var, 178, simpleSymbol9, -4095);
        lset$Mnxor$Ex = new ModuleMethod(srfi1Var, 179, simpleSymbol8, -4095);
        lset$Mndiff$Plintersection = new ModuleMethod(srfi1Var, 180, simpleSymbol7, -4094);
        lset$Mndiff$Plintersection$Ex = new ModuleMethod(srfi1Var, 181, simpleSymbol6, -4094);
        lambda$Fn64 = new ModuleMethod(srfi1Var, 182, null, 8194);
        lambda$Fn78 = new ModuleMethod(srfi1Var, 183, null, 8194);
        srfi1Var.run();
    }

    public srfi1() {
        ModuleInfo.register(this);
    }

    public static Object alistDelete(Object obj, Object obj2) {
        return alistDelete(obj, obj2, Scheme.isEqual);
    }

    public static Object alistDelete$Ex(Object obj, Object obj2) {
        return alistDelete$Ex(obj, obj2, Scheme.isEqual);
    }

    public static Object delete(Object obj, Object obj2) {
        return delete(obj, obj2, Scheme.isEqual);
    }

    public static Object delete$Ex(Object obj, Object obj2) {
        return delete$Ex(obj, obj2, Scheme.isEqual);
    }

    public static Object deleteDuplicates(Object obj) {
        return deleteDuplicates(obj, Scheme.isEqual);
    }

    public static Object deleteDuplicates$Ex(Object obj) {
        return deleteDuplicates$Ex(obj, Scheme.isEqual);
    }

    public static Object iota(IntNum intNum) {
        return iota(intNum, Lit0, Lit1);
    }

    public static Object iota(IntNum intNum, Numeric numeric) {
        return iota(intNum, numeric, Lit1);
    }

    public static Object unfoldRight(Procedure procedure, Procedure procedure2, Procedure procedure3, Object obj) {
        return unfoldRight(procedure, procedure2, procedure3, obj, LList.Empty);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
        first = lists.car;
        second = lists.cadr;
        third = lists.caddr;
        fourth = lists.cadddr;
        map$Mnin$Mnorder = Scheme.map;
    }

    public static Pair xcons(Object d, Object a) {
        return lists.cons(a, d);
    }

    @Override // gnu.expr.ModuleBody
    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 78:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 80:
                callContext.value1 = obj;
                if (!(obj2 instanceof Procedure)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 83:
                if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (Numeric.asNumericOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 102:
                callContext.value1 = obj;
                if (IntNum.asIntNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 103:
                callContext.value1 = obj;
                if (IntNum.asIntNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 104:
                callContext.value1 = obj;
                if (IntNum.asIntNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 105:
                callContext.value1 = obj;
                if (IntNum.asIntNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 106:
                callContext.value1 = obj;
                if (IntNum.asIntNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 107:
                callContext.value1 = obj;
                if (IntNum.asIntNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 108:
                callContext.value1 = obj;
                if (IntNum.asIntNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 109:
                callContext.value1 = obj;
                if (IntNum.asIntNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 118:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 119:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 137:
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 138:
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 139:
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 140:
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 141:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 142:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 143:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 145:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 147:
                callContext.value1 = obj;
                if (!(obj2 instanceof Procedure)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 149:
                callContext.value1 = obj;
                if (!(obj2 instanceof Procedure)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 153:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 155:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 157:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 158:
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 159:
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case ComponentConstants.TEXTBOX_PREFERRED_WIDTH /* 160 */:
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 161:
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 162:
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 163:
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 164:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 165:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 182:
            case 183:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod, obj, obj2, callContext);
        }
    }

    public static Object makeList$V(Object obj, Object[] objArr) {
        Object apply1;
        LList makeList = LList.makeList(objArr, 0);
        int i = ((numbers.isInteger(obj) ? 1 : 0) + 1) & 1;
        if (i == 0 ? Scheme.numLss.apply2(obj, Lit0) != Boolean.FALSE : i != 0) {
            misc.error$V("make-list arg#1 must be a non-negative integer", new Object[0]);
        }
        if (lists.isNull(makeList)) {
            apply1 = Boolean.FALSE;
        } else {
            apply1 = lists.isNull(lists.cdr.apply1(makeList)) ? lists.car.apply1(makeList) : misc.error$V("Too many arguments to MAKE-LIST", new Object[]{lists.cons(obj, makeList)});
        }
        Object obj2 = LList.Empty;
        while (Scheme.numLEq.apply2(obj, Lit0) == Boolean.FALSE) {
            obj = AddOp.$Mn.apply2(obj, Lit1);
            obj2 = lists.cons(apply1, obj2);
        }
        return obj2;
    }

    @Override // gnu.expr.ModuleBody
    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 79:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 81:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 86:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 92:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 94:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 117:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 122:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 123:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 125:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 126:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 127:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 128:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 129:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 132:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 133:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 134:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 135:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 136:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 166:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 167:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 168:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 169:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 170:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 171:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 172:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 173:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 174:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 175:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 176:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 177:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 178:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 179:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 180:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 181:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod, objArr, callContext);
        }
    }

    public static Object listTabulate(Object obj, Procedure procedure) {
        int i = ((numbers.isInteger(obj) ? 1 : 0) + 1) & 1;
        if (i == 0 ? Scheme.numLss.apply2(obj, Lit0) != Boolean.FALSE : i != 0) {
            misc.error$V("list-tabulate arg#1 must be a non-negative integer", new Object[0]);
        }
        Object apply2 = AddOp.$Mn.apply2(obj, Lit1);
        Object obj2 = LList.Empty;
        while (Scheme.numLss.apply2(apply2, Lit0) == Boolean.FALSE) {
            Object apply22 = AddOp.$Mn.apply2(apply2, Lit1);
            obj2 = lists.cons(procedure.apply1(apply2), obj2);
            apply2 = apply22;
        }
        return obj2;
    }

    public static LList listCopy(LList lis) {
        LList result = LList.Empty;
        LList prev = LList.Empty;
        for (LList lis2 = lis; lists.isPair(lis2); lis2 = (LList) lists.cdr.apply1(lis2)) {
            Pair p = lists.cons(lists.car.apply1(lis2), LList.Empty);
            if (prev != LList.Empty) {
                try {
                    ((Pair) prev).setCdr(p);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "set-cdr!", 1, prev);
                }
            } else {
                result = p;
            }
            prev = p;
        }
        return result;
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 82:
                if (!(obj instanceof LList)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 83:
                if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 87:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 88:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 89:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 90:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 91:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 93:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 95:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 96:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 97:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 98:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 99:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 100:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 101:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 110:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 111:
                if (!(obj instanceof Pair)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 112:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 113:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 114:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 115:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 116:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 120:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 121:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 147:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 149:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 152:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod, obj, callContext);
        }
    }

    public static Object iota(IntNum count2, Numeric start, Numeric step) {
        if (IntNum.compare(count2, 0L) < 0) {
            misc.error$V("Negative step count", new Object[]{iota, count2});
        }
        Object apply2 = AddOp.$Pl.apply2(start, MultiplyOp.$St.apply2(IntNum.add(count2, -1), step));
        try {
            Object last$Mnval = (Numeric) apply2;
            Object ans = LList.Empty;
            Object count3 = count2;
            Object val = last$Mnval;
            while (Scheme.numLEq.apply2(count3, Lit0) == Boolean.FALSE) {
                Object apply22 = AddOp.$Mn.apply2(count3, Lit1);
                Object apply23 = AddOp.$Mn.apply2(val, step);
                ans = lists.cons(val, ans);
                val = apply23;
                count3 = apply22;
            }
            return ans;
        } catch (ClassCastException e) {
            throw new WrongType(e, "last-val", -2, apply2);
        }
    }

    @Override // gnu.expr.ModuleBody
    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 83:
                if (IntNum.asIntNumOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (Numeric.asNumericOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                if (Numeric.asNumericOrNull(obj3) == null) {
                    return -786429;
                }
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 130:
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 131:
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 143:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 145:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 151:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 153:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 155:
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

    public static Pair circularList$V(Object val1, Object[] argsArray) {
        LList vals = LList.makeList(argsArray, 0);
        Pair ans = lists.cons(val1, vals);
        Object lastPair = lastPair(ans);
        try {
            ((Pair) lastPair).setCdr(ans);
            return ans;
        } catch (ClassCastException e) {
            throw new WrongType(e, "set-cdr!", 1, lastPair);
        }
    }

    public static Object isProperList(Object x) {
        Object lag = x;
        Object x2 = x;
        while (lists.isPair(x2)) {
            Object x3 = lists.cdr.apply1(x2);
            if (!lists.isPair(x3)) {
                return lists.isNull(x3) ? Boolean.TRUE : Boolean.FALSE;
            }
            Object x4 = lists.cdr.apply1(x3);
            Object lag2 = lists.cdr.apply1(lag);
            int i = 1 & ((x4 == lag2 ? 1 : 0) + 1);
            if (i == 0) {
                return i != 0 ? Boolean.TRUE : Boolean.FALSE;
            }
            x2 = x4;
            lag = lag2;
        }
        return lists.isNull(x2) ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object isDottedList(Object x) {
        Object lag = x;
        Object x2 = x;
        while (lists.isPair(x2)) {
            Object x3 = lists.cdr.apply1(x2);
            if (!lists.isPair(x3)) {
                return lists.isNull(x3) ? Boolean.FALSE : Boolean.TRUE;
            }
            Object x4 = lists.cdr.apply1(x3);
            Object lag2 = lists.cdr.apply1(lag);
            int i = 1 & ((x4 == lag2 ? 1 : 0) + 1);
            if (i == 0) {
                return i != 0 ? Boolean.TRUE : Boolean.FALSE;
            }
            x2 = x4;
            lag = lag2;
        }
        return lists.isNull(x2) ? Boolean.FALSE : Boolean.TRUE;
    }

    public static Object isCircularList(Object x) {
        Object lag = x;
        Object x2 = x;
        while (true) {
            boolean x3 = lists.isPair(x2);
            if (!x3) {
                return x3 ? Boolean.TRUE : Boolean.FALSE;
            }
            Object x4 = lists.cdr.apply1(x2);
            boolean x5 = lists.isPair(x4);
            if (!x5) {
                return x5 ? Boolean.TRUE : Boolean.FALSE;
            }
            Object x6 = lists.cdr.apply1(x4);
            Object lag2 = lists.cdr.apply1(lag);
            boolean x7 = x6 == lag2;
            if (x7) {
                return x7 ? Boolean.TRUE : Boolean.FALSE;
            }
            x2 = x6;
            lag = lag2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isNotPair(Object obj) {
        return ((lists.isPair(obj) ? 1 : 0) + 1) & 1;
    }

    public static Object isNullList(Object l) {
        return l instanceof Pair ? Boolean.FALSE : l == LList.Empty ? Boolean.TRUE : misc.error$V("null-list?: argument out of domain", new Object[]{l});
    }

    public static Object list$Eq$V(Object obj, Object[] objArr) {
        LList makeList = LList.makeList(objArr, 0);
        boolean isNull = lists.isNull(makeList);
        if (isNull) {
            return isNull ? Boolean.TRUE : Boolean.FALSE;
        }
        Object apply1 = lists.car.apply1(makeList);
        Object apply12 = lists.cdr.apply1(makeList);
        while (true) {
            boolean isNull2 = lists.isNull(apply12);
            if (isNull2) {
                return isNull2 ? Boolean.TRUE : Boolean.FALSE;
            }
            Object apply13 = lists.car.apply1(apply12);
            apply12 = lists.cdr.apply1(apply12);
            if (apply1 == apply13) {
                apply1 = apply13;
            } else {
                while (isNullList(apply1) == Boolean.FALSE) {
                    Object isNullList = isNullList(apply13);
                    try {
                        int i = ((isNullList != Boolean.FALSE ? 1 : 0) + 1) & 1;
                        if (i == 0) {
                            return i != 0 ? Boolean.TRUE : Boolean.FALSE;
                        }
                        Object apply3 = Scheme.applyToArgs.apply3(obj, lists.car.apply1(apply1), lists.car.apply1(apply13));
                        if (apply3 == Boolean.FALSE) {
                            return apply3;
                        }
                        apply1 = lists.cdr.apply1(apply1);
                        apply13 = lists.cdr.apply1(apply13);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "x", -2, isNullList);
                    }
                }
                Object isNullList2 = isNullList(apply13);
                if (isNullList2 == Boolean.FALSE) {
                    return isNullList2;
                }
                apply1 = apply13;
            }
        }
    }

    public static Object length$Pl(Object x) {
        Object len = Lit0;
        Object lag = x;
        Object x2 = x;
        while (lists.isPair(x2)) {
            Object x3 = lists.cdr.apply1(x2);
            AddOp addOp = AddOp.$Pl;
            IntNum intNum = Lit1;
            Object len2 = addOp.apply2(len, intNum);
            if (lists.isPair(x3)) {
                Object x4 = lists.cdr.apply1(x3);
                Object lag2 = lists.cdr.apply1(lag);
                Object len3 = AddOp.$Pl.apply2(len2, intNum);
                int i = 1 & ((x4 == lag2 ? 1 : 0) + 1);
                if (i != 0) {
                    x2 = x4;
                    len = len3;
                    lag = lag2;
                } else {
                    len2 = i != 0 ? Boolean.TRUE : Boolean.FALSE;
                }
            }
            return len2;
        }
        return len;
    }

    public static Object zip$V(Object list1, Object[] argsArray) {
        LList more$Mnlists = LList.makeList(argsArray, 0);
        return Scheme.apply.apply4(Scheme.map, LangObjType.listType, list1, more$Mnlists);
    }

    public static Object fifth(Object x) {
        return lists.car.apply1(lists.cddddr.apply1(x));
    }

    public static Object sixth(Object x) {
        return lists.cadr.apply1(lists.cddddr.apply1(x));
    }

    public static Object seventh(Object x) {
        return lists.caddr.apply1(lists.cddddr.apply1(x));
    }

    public static Object eighth(Object x) {
        return lists.cadddr.apply1(lists.cddddr.apply1(x));
    }

    public static Object ninth(Object x) {
        return lists.car.apply1(lists.cddddr.apply1(lists.cddddr.apply1(x)));
    }

    public static Object tenth(Object x) {
        return lists.cadr.apply1(lists.cddddr.apply1(lists.cddddr.apply1(x)));
    }

    public static Object car$PlCdr(Object pair) {
        return misc.values(lists.car.apply1(pair), lists.cdr.apply1(pair));
    }

    public static Object take(Object lis, IntNum k) {
        Object res = LList.Empty;
        Object lis2 = lis;
        Object k2 = k;
        while (!numbers.isZero((Number) k2)) {
            try {
                Object lis3 = lists.cdr.apply1(lis2);
                k2 = AddOp.$Mn.apply2(k2, Lit1);
                res = lists.cons(lists.car.apply1(lis2), res);
                lis2 = lis3;
            } catch (ClassCastException e) {
                throw new WrongType(e, "zero?", 1, k2);
            }
        }
        try {
            return LList.reverseInPlace((LList) res);
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "reverse!", 1, res);
        }
    }

    public static Object drop(Object lis, IntNum k) {
        Object lis2 = lis;
        for (Object k2 = k; !numbers.isZero((Number) k2); k2 = AddOp.$Mn.apply2(k2, Lit1)) {
            try {
                lis2 = lists.cdr.apply1(lis2);
            } catch (ClassCastException e) {
                throw new WrongType(e, "zero?", 1, k2);
            }
        }
        return lis2;
    }

    public static Object take$Ex(Object lis, IntNum k) {
        if (numbers.isZero(k)) {
            return LList.Empty;
        }
        Object drop2 = drop(lis, IntNum.add(k, -1));
        try {
            ((Pair) drop2).setCdr(LList.Empty);
            return lis;
        } catch (ClassCastException e) {
            throw new WrongType(e, "set-cdr!", 1, drop2);
        }
    }

    public static Object takeRight(Object lis, IntNum k) {
        Object lag = lis;
        for (Object lead = drop(lis, k); lists.isPair(lead); lead = lists.cdr.apply1(lead)) {
            lag = lists.cdr.apply1(lag);
        }
        return lag;
    }

    public static Object dropRight(Object lis, IntNum k) {
        return lambda1recur(lis, drop(lis, k));
    }

    public static Object lambda1recur(Object lag, Object lead) {
        return lists.isPair(lead) ? lists.cons(lists.car.apply1(lag), lambda1recur(lists.cdr.apply1(lag), lists.cdr.apply1(lead))) : LList.Empty;
    }

    public static Object dropRight$Ex(Object lis, IntNum k) {
        Object lead = drop(lis, k);
        if (lists.isPair(lead)) {
            Object lag = lis;
            for (Object lead2 = lists.cdr.apply1(lead); lists.isPair(lead2); lead2 = lists.cdr.apply1(lead2)) {
                lag = lists.cdr.apply1(lag);
            }
            try {
                ((Pair) lag).setCdr(LList.Empty);
                return lis;
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-cdr!", 1, lag);
            }
        }
        return LList.Empty;
    }

    public static Object splitAt(Object x, IntNum k) {
        Object prefix = LList.Empty;
        Object suffix = x;
        for (Object k2 = k; !numbers.isZero((Number) k2); k2 = AddOp.$Mn.apply2(k2, Lit1)) {
            try {
                prefix = lists.cons(lists.car.apply1(suffix), prefix);
                suffix = lists.cdr.apply1(suffix);
            } catch (ClassCastException e) {
                throw new WrongType(e, "zero?", 1, k2);
            }
        }
        Object[] objArr = new Object[2];
        try {
            objArr[0] = LList.reverseInPlace((LList) prefix);
            objArr[1] = suffix;
            return misc.values(objArr);
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "reverse!", 1, prefix);
        }
    }

    public static Object splitAt$Ex(Object x, IntNum k) {
        if (numbers.isZero(k)) {
            return misc.values(LList.Empty, x);
        }
        Object prev = drop(x, IntNum.add(k, -1));
        Object suffix = lists.cdr.apply1(prev);
        try {
            ((Pair) prev).setCdr(LList.Empty);
            return misc.values(x, suffix);
        } catch (ClassCastException e) {
            throw new WrongType(e, "set-cdr!", 1, prev);
        }
    }

    public static Object last(Object lis) {
        try {
            return lists.car.apply1(lastPair((Pair) lis));
        } catch (ClassCastException e) {
            throw new WrongType(e, "last-pair", 0, lis);
        }
    }

    public static Object lastPair(Pair lis) {
        Object lis2 = lis;
        while (true) {
            Object tail = lists.cdr.apply1(lis2);
            if (!lists.isPair(tail)) {
                return lis2;
            }
            lis2 = tail;
        }
    }

    public static LList unzip1(Object lis) {
        Object result = LList.Empty;
        Object arg0 = lis;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                arg0 = arg02.getCdr();
                result = Pair.make(lists.car.apply1(arg02.getCar()), result);
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        return LList.reverseInPlace(result);
    }

    public static Object unzip2(Object lis) {
        new frame();
        return frame.lambda2recur(lis);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame extends ModuleBody {
        public static Object lambda2recur(Object lis) {
            frame0 frame0Var = new frame0();
            frame0Var.lis = lis;
            if (srfi1.isNullList(frame0Var.lis) != Boolean.FALSE) {
                return misc.values(frame0Var.lis, frame0Var.lis);
            }
            frame0Var.elt = lists.car.apply1(frame0Var.lis);
            return call_with_values.callWithValues(frame0Var.lambda$Fn1, frame0Var.lambda$Fn2);
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame0 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn1 = new ModuleMethod(this, 1, null, 0);
        final ModuleMethod lambda$Fn2;
        Object lis;

        public frame0() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 2, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:627");
            this.lambda$Fn2 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 1 ? lambda3() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 1) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 2 ? lambda4(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda3() {
            return frame.lambda2recur(lists.cdr.apply1(this.lis));
        }

        Object lambda4(Object a, Object b) {
            return misc.values(lists.cons(lists.car.apply1(this.elt), a), lists.cons(lists.cadr.apply1(this.elt), b));
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 2) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Object unzip3(Object lis) {
        new frame1();
        return frame1.lambda5recur(lis);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame1 extends ModuleBody {
        public static Object lambda5recur(Object lis) {
            frame2 frame2Var = new frame2();
            frame2Var.lis = lis;
            if (srfi1.isNullList(frame2Var.lis) != Boolean.FALSE) {
                return misc.values(frame2Var.lis, frame2Var.lis, frame2Var.lis);
            }
            frame2Var.elt = lists.car.apply1(frame2Var.lis);
            return call_with_values.callWithValues(frame2Var.lambda$Fn3, frame2Var.lambda$Fn4);
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame2 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn3 = new ModuleMethod(this, 3, null, 0);
        final ModuleMethod lambda$Fn4;
        Object lis;

        public frame2() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, 12291);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:635");
            this.lambda$Fn4 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 3 ? lambda6() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 3) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 4 ? lambda7(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        Object lambda6() {
            return frame1.lambda5recur(lists.cdr.apply1(this.lis));
        }

        Object lambda7(Object a, Object b, Object c) {
            return misc.values(lists.cons(lists.car.apply1(this.elt), a), lists.cons(lists.cadr.apply1(this.elt), b), lists.cons(lists.caddr.apply1(this.elt), c));
        }

        @Override // gnu.expr.ModuleBody
        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 4) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }
    }

    public static Object unzip4(Object lis) {
        new frame3();
        return frame3.lambda8recur(lis);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame3 extends ModuleBody {
        public static Object lambda8recur(Object lis) {
            frame4 frame4Var = new frame4();
            frame4Var.lis = lis;
            if (srfi1.isNullList(frame4Var.lis) != Boolean.FALSE) {
                return misc.values(frame4Var.lis, frame4Var.lis, frame4Var.lis, frame4Var.lis);
            }
            frame4Var.elt = lists.car.apply1(frame4Var.lis);
            return call_with_values.callWithValues(frame4Var.lambda$Fn5, frame4Var.lambda$Fn6);
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame4 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn5 = new ModuleMethod(this, 5, null, 0);
        final ModuleMethod lambda$Fn6;
        Object lis;

        public frame4() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, 16388);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:644");
            this.lambda$Fn6 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 5 ? lambda9() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 5) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
            return moduleMethod.selector == 6 ? lambda10(obj, obj2, obj3, obj4) : super.apply4(moduleMethod, obj, obj2, obj3, obj4);
        }

        Object lambda10(Object a, Object b, Object c, Object d) {
            return misc.values(lists.cons(lists.car.apply1(this.elt), a), lists.cons(lists.cadr.apply1(this.elt), b), lists.cons(lists.caddr.apply1(this.elt), c), lists.cons(lists.cadddr.apply1(this.elt), d));
        }

        Object lambda9() {
            return frame3.lambda8recur(lists.cdr.apply1(this.lis));
        }

        @Override // gnu.expr.ModuleBody
        public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
            if (moduleMethod.selector != 6) {
                return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        }
    }

    public static Object unzip5(Object lis) {
        new frame5();
        return frame5.lambda11recur(lis);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame5 extends ModuleBody {
        public static Object lambda11recur(Object lis) {
            frame6 frame6Var = new frame6();
            frame6Var.lis = lis;
            if (srfi1.isNullList(frame6Var.lis) != Boolean.FALSE) {
                return misc.values(frame6Var.lis, frame6Var.lis, frame6Var.lis, frame6Var.lis, frame6Var.lis);
            }
            frame6Var.elt = lists.car.apply1(frame6Var.lis);
            return call_with_values.callWithValues(frame6Var.lambda$Fn7, frame6Var.lambda$Fn8);
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame6 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn7 = new ModuleMethod(this, 7, null, 0);
        final ModuleMethod lambda$Fn8;
        Object lis;

        public frame6() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 8, null, 20485);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:654");
            this.lambda$Fn8 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 7 ? lambda12() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 7) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            return moduleMethod.selector == 8 ? lambda13(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]) : super.applyN(moduleMethod, objArr);
        }

        Object lambda12() {
            return frame5.lambda11recur(lists.cdr.apply1(this.lis));
        }

        Object lambda13(Object a, Object b, Object c, Object d, Object e) {
            return misc.values(lists.cons(lists.car.apply1(this.elt), a), lists.cons(lists.cadr.apply1(this.elt), b), lists.cons(lists.caddr.apply1(this.elt), c), lists.cons(lists.cadddr.apply1(this.elt), d), lists.cons(lists.car.apply1(lists.cddddr.apply1(this.elt)), e));
        }

        @Override // gnu.expr.ModuleBody
        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            if (moduleMethod.selector != 8) {
                return super.matchN(moduleMethod, objArr, callContext);
            }
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        }
    }

    public static Object append$Ex$V(Object[] argsArray) {
        Object lastPair;
        Object lists = LList.makeList(argsArray, 0);
        Object prev = LList.Empty;
        Object lists2 = lists;
        while (lists.isPair(lists2)) {
            Object first2 = lists.car.apply1(lists2);
            Object rest = lists.cdr.apply1(lists2);
            if (lists.isPair(first2)) {
                try {
                    Object tail$Mncons = lastPair((Pair) first2);
                    Object rest2 = rest;
                    Object tail$Mncons2 = tail$Mncons;
                    while (lists.isPair(rest2)) {
                        Object next = lists.car.apply1(rest2);
                        rest2 = lists.cdr.apply1(rest2);
                        try {
                            ((Pair) tail$Mncons2).setCdr(next);
                            if (lists.isPair(next)) {
                                try {
                                    lastPair = lastPair((Pair) next);
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "last-pair", 0, next);
                                }
                            } else {
                                lastPair = tail$Mncons2;
                            }
                            tail$Mncons2 = lastPair;
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "set-cdr!", 1, tail$Mncons2);
                        }
                    }
                    return first2;
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "last-pair", 0, first2);
                }
            }
            prev = first2;
            lists2 = rest;
        }
        return prev;
    }

    public static Object appendReverse(Object rev$Mnhead, Object tail) {
        Object rev$Mnhead2 = rev$Mnhead;
        Object tail2 = tail;
        while (isNullList(rev$Mnhead2) == Boolean.FALSE) {
            Object rev$Mnhead3 = lists.cdr.apply1(rev$Mnhead2);
            tail2 = lists.cons(lists.car.apply1(rev$Mnhead2), tail2);
            rev$Mnhead2 = rev$Mnhead3;
        }
        return tail2;
    }

    public static Object appendReverse$Ex(Object rev$Mnhead, Object tail) {
        Object rev$Mnhead2 = rev$Mnhead;
        Object tail2 = tail;
        while (isNullList(rev$Mnhead2) == Boolean.FALSE) {
            Object next$Mnrev = lists.cdr.apply1(rev$Mnhead2);
            try {
                ((Pair) rev$Mnhead2).setCdr(tail2);
                tail2 = rev$Mnhead2;
                rev$Mnhead2 = next$Mnrev;
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-cdr!", 1, rev$Mnhead2);
            }
        }
        return tail2;
    }

    public static Object concatenate(Object lists) {
        return reduceRight(append.append, LList.Empty, lists);
    }

    public static Object concatenate$Ex(Object lists) {
        return reduceRight(append$Ex, LList.Empty, lists);
    }

    static Object $PcCdrs(Object lists) {
        Continuation abort = new Continuation(CallContext.getInstance());
        try {
            frame55 frame55Var = new frame55();
            frame55Var.abort = abort;
            Object lambda74recur = frame55Var.lambda74recur(lists);
            abort.invoked = true;
            return lambda74recur;
        } catch (Throwable th) {
            return Continuation.handleException(th, abort);
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame55 extends ModuleBody {
        Continuation abort;

        public Object lambda74recur(Object lists) {
            if (!lists.isPair(lists)) {
                return LList.Empty;
            }
            Object lis = lists.car.apply1(lists);
            return srfi1.isNullList(lis) != Boolean.FALSE ? this.abort.apply1(LList.Empty) : lists.cons(lists.cdr.apply1(lis), lambda74recur(lists.cdr.apply1(lists)));
        }
    }

    static Object $PcCars$Pl(Object lists, Object lastElt) {
        frame56 frame56Var = new frame56();
        frame56Var.last$Mnelt = lastElt;
        return frame56Var.lambda75recur(lists);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame56 extends ModuleBody {
        Object last$Mnelt;

        public Object lambda75recur(Object lists) {
            return lists.isPair(lists) ? lists.cons(lists.caar.apply1(lists), lambda75recur(lists.cdr.apply1(lists))) : LList.list1(this.last$Mnelt);
        }
    }

    static Object $PcCars$PlCdrs(Object lists) {
        Continuation abort = new Continuation(CallContext.getInstance());
        try {
            frame57 frame57Var = new frame57();
            frame57Var.abort = abort;
            Object lambda76recur = frame57Var.lambda76recur(lists);
            abort.invoked = true;
            return lambda76recur;
        } catch (Throwable th) {
            return Continuation.handleException(th, abort);
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame57 extends ModuleBody {
        Continuation abort;

        public Object lambda76recur(Object lists) {
            frame58 frame58Var = new frame58();
            frame58Var.staticLink = this;
            frame58Var.lists = lists;
            return lists.isPair(frame58Var.lists) ? call_with_values.callWithValues(frame58Var.lambda$Fn57, frame58Var.lambda$Fn58) : misc.values(LList.Empty, LList.Empty);
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame58 extends ModuleBody {
        final ModuleMethod lambda$Fn57 = new ModuleMethod(this, 61, null, 0);
        final ModuleMethod lambda$Fn58;
        Object lists;
        frame57 staticLink;

        public frame58() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 62, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:762");
            this.lambda$Fn58 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 61 ? lambda77() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 61) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 62 ? lambda78(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda77() {
            return srfi1.car$PlCdr(this.lists);
        }

        Object lambda78(Object list, Object otherLists) {
            frame59 frame59Var = new frame59();
            frame59Var.staticLink = this;
            frame59Var.list = list;
            frame59Var.other$Mnlists = otherLists;
            return srfi1.isNullList(frame59Var.list) != Boolean.FALSE ? this.staticLink.abort.apply2(LList.Empty, LList.Empty) : call_with_values.callWithValues(frame59Var.lambda$Fn59, frame59Var.lambda$Fn60);
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 62) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame59 extends ModuleBody {
        final ModuleMethod lambda$Fn59 = new ModuleMethod(this, 59, null, 0);
        final ModuleMethod lambda$Fn60;
        Object list;
        Object other$Mnlists;
        frame58 staticLink;

        public frame59() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 60, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:764");
            this.lambda$Fn60 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 59 ? lambda79() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 59) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 60 ? lambda80(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda79() {
            return srfi1.car$PlCdr(this.list);
        }

        Object lambda80(Object a, Object d) {
            frame60 frame60Var = new frame60();
            frame60Var.staticLink = this;
            frame60Var.a = a;
            frame60Var.d = d;
            return call_with_values.callWithValues(frame60Var.lambda$Fn61, frame60Var.lambda$Fn62);
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 60) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame60 extends ModuleBody {
        Object a;
        Object d;
        final ModuleMethod lambda$Fn61 = new ModuleMethod(this, 57, null, 0);
        final ModuleMethod lambda$Fn62;
        frame59 staticLink;

        public frame60() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 58, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:765");
            this.lambda$Fn62 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 57 ? lambda81() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 57) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 58 ? lambda82(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda81() {
            return this.staticLink.staticLink.staticLink.lambda76recur(this.staticLink.other$Mnlists);
        }

        Object lambda82(Object cars, Object cdrs) {
            return misc.values(lists.cons(this.a, cars), lists.cons(this.d, cdrs));
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 58) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame61 extends ModuleBody {
        final ModuleMethod lambda$Fn63 = new ModuleMethod(this, 63, null, 0);
        Object lists;

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 63 ? lambda83() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 63) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        static Pair lambda84(Object x, Object x2) {
            return lists.cons(x, x2);
        }

        Object lambda83() {
            return srfi1.$PcCars$PlCdrs(this.lists);
        }
    }

    static Object $PcCars$PlCdrs$SlPair(Object lists) {
        frame61 frame61Var = new frame61();
        frame61Var.lists = lists;
        return call_with_values.callWithValues(frame61Var.lambda$Fn63, lambda$Fn64);
    }

    static Object $PcCars$PlCdrs$Pl(Object lists, Object carsFinal) {
        frame62 closureEnv = new frame62();
        closureEnv.cars$Mnfinal = carsFinal;
        Continuation abort = new Continuation(CallContext.getInstance());
        try {
            frame63 frame63Var = new frame63();
            frame63Var.staticLink = closureEnv;
            frame63Var.abort = abort;
            Object lambda85recur = frame63Var.lambda85recur(lists);
            abort.invoked = true;
            return lambda85recur;
        } catch (Throwable th) {
            return Continuation.handleException(th, abort);
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame63 extends ModuleBody {
        Continuation abort;
        frame62 staticLink;

        public Object lambda85recur(Object lists) {
            frame64 frame64Var = new frame64();
            frame64Var.staticLink = this;
            frame64Var.lists = lists;
            return lists.isPair(frame64Var.lists) ? call_with_values.callWithValues(frame64Var.lambda$Fn65, frame64Var.lambda$Fn66) : misc.values(LList.list1(this.staticLink.cars$Mnfinal), LList.Empty);
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame64 extends ModuleBody {
        final ModuleMethod lambda$Fn65 = new ModuleMethod(this, 68, null, 0);
        final ModuleMethod lambda$Fn66;
        Object lists;
        frame63 staticLink;

        public frame64() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 69, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:783");
            this.lambda$Fn66 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 68 ? lambda86() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 68) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 69 ? lambda87(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda86() {
            return srfi1.car$PlCdr(this.lists);
        }

        Object lambda87(Object list, Object otherLists) {
            frame65 frame65Var = new frame65();
            frame65Var.staticLink = this;
            frame65Var.list = list;
            frame65Var.other$Mnlists = otherLists;
            return srfi1.isNullList(frame65Var.list) != Boolean.FALSE ? this.staticLink.abort.apply2(LList.Empty, LList.Empty) : call_with_values.callWithValues(frame65Var.lambda$Fn67, frame65Var.lambda$Fn68);
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 69) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame65 extends ModuleBody {
        final ModuleMethod lambda$Fn67 = new ModuleMethod(this, 66, null, 0);
        final ModuleMethod lambda$Fn68;
        Object list;
        Object other$Mnlists;
        frame64 staticLink;

        public frame65() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 67, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:785");
            this.lambda$Fn68 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 66 ? lambda88() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 66) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 67 ? lambda89(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda88() {
            return srfi1.car$PlCdr(this.list);
        }

        Object lambda89(Object a, Object d) {
            frame66 frame66Var = new frame66();
            frame66Var.staticLink = this;
            frame66Var.a = a;
            frame66Var.d = d;
            return call_with_values.callWithValues(frame66Var.lambda$Fn69, frame66Var.lambda$Fn70);
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 67) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame66 extends ModuleBody {
        Object a;
        Object d;
        final ModuleMethod lambda$Fn69 = new ModuleMethod(this, 64, null, 0);
        final ModuleMethod lambda$Fn70;
        frame65 staticLink;

        public frame66() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 65, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:786");
            this.lambda$Fn70 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 64 ? lambda90() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 64) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 65 ? lambda91(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda90() {
            return this.staticLink.staticLink.staticLink.lambda85recur(this.staticLink.other$Mnlists);
        }

        Object lambda91(Object cars, Object cdrs) {
            return misc.values(lists.cons(this.a, cars), lists.cons(this.d, cdrs));
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 65) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    static Object $PcCars$PlCdrs$SlNoTest(Object lists) {
        new frame67();
        return frame67.lambda92recur(lists);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame67 extends ModuleBody {
        public static Object lambda92recur(Object lists) {
            frame68 frame68Var = new frame68();
            frame68Var.lists = lists;
            return lists.isPair(frame68Var.lists) ? call_with_values.callWithValues(frame68Var.lambda$Fn71, frame68Var.lambda$Fn72) : misc.values(LList.Empty, LList.Empty);
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame68 extends ModuleBody {
        final ModuleMethod lambda$Fn71 = new ModuleMethod(this, 74, null, 0);
        final ModuleMethod lambda$Fn72;
        Object lists;

        public frame68() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 75, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:794");
            this.lambda$Fn72 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 74 ? lambda93() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 74) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 75 ? lambda94(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda93() {
            return srfi1.car$PlCdr(this.lists);
        }

        Object lambda94(Object list, Object otherLists) {
            frame69 frame69Var = new frame69();
            frame69Var.staticLink = this;
            frame69Var.list = list;
            frame69Var.other$Mnlists = otherLists;
            return call_with_values.callWithValues(frame69Var.lambda$Fn73, frame69Var.lambda$Fn74);
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 75) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame69 extends ModuleBody {
        final ModuleMethod lambda$Fn73 = new ModuleMethod(this, 72, null, 0);
        final ModuleMethod lambda$Fn74;
        Object list;
        Object other$Mnlists;
        frame68 staticLink;

        public frame69() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 73, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:795");
            this.lambda$Fn74 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 72 ? lambda95() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 72) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 73 ? lambda96(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda95() {
            return srfi1.car$PlCdr(this.list);
        }

        Object lambda96(Object a, Object d) {
            frame70 frame70Var = new frame70();
            frame70Var.staticLink = this;
            frame70Var.a = a;
            frame70Var.d = d;
            return call_with_values.callWithValues(frame70Var.lambda$Fn75, frame70Var.lambda$Fn76);
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 73) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame70 extends ModuleBody {
        Object a;
        Object d;
        final ModuleMethod lambda$Fn75 = new ModuleMethod(this, 70, null, 0);
        final ModuleMethod lambda$Fn76;
        frame69 staticLink;

        public frame70() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 71, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:796");
            this.lambda$Fn76 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 70 ? lambda97() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 70) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 71 ? lambda98(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda97() {
            return frame67.lambda92recur(this.staticLink.other$Mnlists);
        }

        Object lambda98(Object cars, Object cdrs) {
            return misc.values(lists.cons(this.a, cars), lists.cons(this.d, cdrs));
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 71) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame71 extends ModuleBody {
        final ModuleMethod lambda$Fn77 = new ModuleMethod(this, 76, null, 0);
        Object lists;

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 76 ? lambda99() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 76) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        static Pair lambda100(Object x, Object x2) {
            return lists.cons(x, x2);
        }

        Object lambda99() {
            return srfi1.$PcCars$PlCdrs$SlNoTest(this.lists);
        }
    }

    static Object $PcCars$PlCdrs$SlNoTest$SlPair(Object lists) {
        frame71 frame71Var = new frame71();
        frame71Var.lists = lists;
        return call_with_values.callWithValues(frame71Var.lambda$Fn77, lambda$Fn78);
    }

    public static Object count$V(Procedure pred, Object list1, Object[] argsArray) {
        Object i;
        Object obj;
        Object lists = LList.makeList(argsArray, 0);
        if (lists.isPair(lists)) {
            i = Lit0;
            Object list12 = list1;
            Object lists2 = lists;
            while (isNullList(list12) == Boolean.FALSE) {
                Object split = $PcCars$PlCdrs$SlPair(lists2);
                Object a$Mns = lists.car.apply1(split);
                Object d$Mns = lists.cdr.apply1(split);
                if (lists.isNull(a$Mns)) {
                    break;
                }
                Object apply1 = lists.cdr.apply1(list12);
                if (Scheme.apply.apply3(pred, lists.car.apply1(list12), a$Mns) == Boolean.FALSE) {
                    obj = i;
                } else {
                    obj = AddOp.$Pl.apply2(i, Lit1);
                }
                i = obj;
                lists2 = d$Mns;
                list12 = apply1;
            }
        } else {
            i = Lit0;
            Object lis = list1;
            while (isNullList(lis) == Boolean.FALSE) {
                Object apply12 = lists.cdr.apply1(lis);
                i = pred.apply1(lists.car.apply1(lis)) != Boolean.FALSE ? AddOp.$Pl.apply2(i, Lit1) : i;
                lis = apply12;
            }
        }
        return i;
    }

    public static Object unfoldRight(Procedure p, Procedure f, Procedure g, Object seed, Object maybe$Mntail) {
        Object ans = maybe$Mntail;
        Object seed2 = seed;
        while (p.apply1(seed2) == Boolean.FALSE) {
            Object apply1 = g.apply1(seed2);
            ans = lists.cons(f.apply1(seed2), ans);
            seed2 = apply1;
        }
        return ans;
    }

    @Override // gnu.expr.ModuleBody
    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        if (moduleMethod.selector != 123) {
            return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
        }
        try {
            try {
                try {
                    return unfoldRight((Procedure) obj, (Procedure) obj2, (Procedure) obj3, obj4);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "unfold-right", 3, obj3);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "unfold-right", 2, obj2);
            }
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "unfold-right", 1, obj);
        }
    }

    @Override // gnu.expr.ModuleBody
    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        if (moduleMethod.selector != 123) {
            return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
        }
        if (!(obj instanceof Procedure)) {
            return -786431;
        }
        callContext.value1 = obj;
        if (!(obj2 instanceof Procedure)) {
            return -786430;
        }
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

    public static Object unfold$V(Procedure p, Procedure f, Procedure g, Object seed, Object[] argsArray) {
        LList maybe$Mntail$Mngen = LList.makeList(argsArray, 0);
        if (lists.isPair(maybe$Mntail$Mngen)) {
            Object tail$Mngen = lists.car.apply1(maybe$Mntail$Mngen);
            if (lists.isPair(lists.cdr.apply1(maybe$Mntail$Mngen))) {
                Object res = Scheme.apply.applyN(new Object[]{misc.error, "Too many arguments", unfold, p, f, g, seed, maybe$Mntail$Mngen});
                return res;
            }
            Object res2 = LList.Empty;
            Object seed2 = seed;
            while (p.apply1(seed2) == Boolean.FALSE) {
                Object seed3 = g.apply1(seed2);
                res2 = lists.cons(f.apply1(seed2), res2);
                seed2 = seed3;
            }
            return appendReverse$Ex(res2, Scheme.applyToArgs.apply2(tail$Mngen, seed2));
        }
        Object res3 = LList.Empty;
        Object seed4 = seed;
        while (p.apply1(seed4) == Boolean.FALSE) {
            Object seed5 = g.apply1(seed4);
            res3 = lists.cons(f.apply1(seed4), res3);
            seed4 = seed5;
        }
        try {
            return LList.reverseInPlace((LList) res3);
        } catch (ClassCastException e) {
            throw new WrongType(e, "reverse!", 1, res3);
        }
    }

    public static Object fold$V(Procedure kons, Object knil, Object lis1, Object[] argsArray) {
        frame7 frame7Var = new frame7();
        frame7Var.kons = kons;
        LList lists = LList.makeList(argsArray, 0);
        if (lists.isPair(lists)) {
            return frame7Var.lambda14lp(lists.cons(lis1, lists), knil);
        }
        Object lis = lis1;
        Object ans = knil;
        while (isNullList(lis) == Boolean.FALSE) {
            Object lis2 = lists.cdr.apply1(lis);
            ans = frame7Var.kons.apply2(lists.car.apply1(lis), ans);
            lis = lis2;
        }
        return ans;
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame7 extends ModuleBody {
        Procedure kons;

        public Object lambda14lp(Object lists, Object ans) {
            frame8 frame8Var = new frame8();
            frame8Var.staticLink = this;
            frame8Var.lists = lists;
            frame8Var.ans = ans;
            return call_with_values.callWithValues(frame8Var.lambda$Fn9, frame8Var.lambda$Fn10);
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame8 extends ModuleBody {
        Object ans;
        final ModuleMethod lambda$Fn10;
        final ModuleMethod lambda$Fn9 = new ModuleMethod(this, 9, null, 0);
        Object lists;
        frame7 staticLink;

        public frame8() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 10, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:859");
            this.lambda$Fn10 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 9 ? lambda15() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 9) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 10 ? lambda16(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda15() {
            return srfi1.$PcCars$PlCdrs$Pl(this.lists, this.ans);
        }

        Object lambda16(Object cars$Plans, Object cdrs) {
            return lists.isNull(cars$Plans) ? this.ans : this.staticLink.lambda14lp(cdrs, Scheme.apply.apply2(this.staticLink.kons, cars$Plans));
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 10) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Object foldRight$V(Procedure kons, Object knil, Object lis1, Object[] argsArray) {
        frame9 frame9Var = new frame9();
        frame9Var.kons = kons;
        frame9Var.knil = knil;
        LList lists = LList.makeList(argsArray, 0);
        if (lists.isPair(lists)) {
            return frame9Var.lambda17recur(lists.cons(lis1, lists));
        }
        return frame9Var.lambda18recur(lis1);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame9 extends ModuleBody {
        Object knil;
        Procedure kons;

        public Object lambda17recur(Object lists) {
            Object cdrs = srfi1.$PcCdrs(lists);
            return lists.isNull(cdrs) ? this.knil : Scheme.apply.apply2(this.kons, srfi1.$PcCars$Pl(lists, lambda17recur(cdrs)));
        }

        public Object lambda18recur(Object lis) {
            if (srfi1.isNullList(lis) != Boolean.FALSE) {
                Object head = this.knil;
                return head;
            }
            Object head2 = lists.car.apply1(lis);
            return this.kons.apply2(head2, lambda18recur(lists.cdr.apply1(lis)));
        }
    }

    public static Object pairFoldRight$V(Procedure f, Object zero, Object lis1, Object[] argsArray) {
        frame10 frame10Var = new frame10();
        frame10Var.f = f;
        frame10Var.zero = zero;
        LList lists = LList.makeList(argsArray, 0);
        if (lists.isPair(lists)) {
            return frame10Var.lambda19recur(lists.cons(lis1, lists));
        }
        return frame10Var.lambda20recur(lis1);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame10 extends ModuleBody {
        Procedure f;
        Object zero;

        public Object lambda19recur(Object lists) {
            Object cdrs = srfi1.$PcCdrs(lists);
            return lists.isNull(cdrs) ? this.zero : Scheme.apply.apply2(this.f, srfi1.append$Ex$V(new Object[]{lists, LList.list1(lambda19recur(cdrs))}));
        }

        public Object lambda20recur(Object lis) {
            return srfi1.isNullList(lis) != Boolean.FALSE ? this.zero : this.f.apply2(lis, lambda20recur(lists.cdr.apply1(lis)));
        }
    }

    /* JADX WARN: Incorrect condition in loop: B:12:0x003f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object pairFold$V(gnu.mapping.Procedure r9, java.lang.Object r10, java.lang.Object r11, java.lang.Object[] r12) {
        /*
            r0 = 0
            r1 = 0
            gnu.lists.LList r0 = gnu.lists.LList.makeList(r12, r1)
            boolean r2 = kawa.lib.lists.isPair(r0)
            r3 = 0
            if (r2 == 0) goto L36
            gnu.lists.Pair r2 = kawa.lib.lists.cons(r11, r0)
            r4 = r10
        L12:
            java.lang.Object r5 = $PcCdrs(r2)
            r3 = r5
            boolean r5 = kawa.lib.lists.isNull(r3)
            if (r5 == 0) goto L1e
            goto L41
        L1e:
            gnu.kawa.functions.Apply r5 = kawa.standard.Scheme.apply
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r6[r1] = r2
            r7 = 1
            gnu.lists.Pair r8 = gnu.lists.LList.list1(r4)
            r6[r7] = r8
            java.lang.Object r6 = append$Ex$V(r6)
            java.lang.Object r4 = r5.apply2(r9, r6)
            r2 = r3
            goto L12
        L36:
            r1 = r11
            r2 = r10
            r4 = r2
        L39:
            java.lang.Object r2 = isNullList(r1)
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            if (r2 == r5) goto L42
        L41:
            return r4
        L42:
            gnu.expr.GenericProc r2 = kawa.lib.lists.cdr
            java.lang.Object r2 = r2.apply1(r1)
            r3 = r2
            java.lang.Object r4 = r9.apply2(r1, r4)
            r1 = r3
            goto L39
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi1.pairFold$V(gnu.mapping.Procedure, java.lang.Object, java.lang.Object, java.lang.Object[]):java.lang.Object");
    }

    public static Object reduce(Procedure f, Object ridentity, Object lis) {
        return isNullList(lis) != Boolean.FALSE ? ridentity : fold$V(f, lists.car.apply1(lis), lists.cdr.apply1(lis), new Object[0]);
    }

    public static Object reduceRight(Procedure f, Object ridentity, Object lis) {
        frame11 frame11Var = new frame11();
        frame11Var.f = f;
        return isNullList(lis) != Boolean.FALSE ? ridentity : frame11Var.lambda21recur(lists.car.apply1(lis), lists.cdr.apply1(lis));
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame11 extends ModuleBody {
        Procedure f;

        public Object lambda21recur(Object head, Object lis) {
            if (!lists.isPair(lis)) {
                return head;
            }
            return this.f.apply2(head, lambda21recur(lists.car.apply1(lis), lists.cdr.apply1(lis)));
        }
    }

    public static Object appendMap$V(Object f, Object lis1, Object[] argsArray) {
        LList lists = LList.makeList(argsArray, 0);
        if (lists.isPair(lists)) {
            return Scheme.apply.apply2(append.append, Scheme.apply.apply4(Scheme.map, f, lis1, lists));
        }
        Apply apply = Scheme.apply;
        append appendVar = append.append;
        Object result = LList.Empty;
        Object arg0 = lis1;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                arg0 = arg02.getCdr();
                result = Pair.make(Scheme.applyToArgs.apply2(f, arg02.getCar()), result);
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        return apply.apply2(appendVar, LList.reverseInPlace(result));
    }

    public static Object appendMap$Ex$V(Object f, Object lis1, Object[] argsArray) {
        LList lists = LList.makeList(argsArray, 0);
        if (lists.isPair(lists)) {
            return Scheme.apply.apply2(append$Ex, Scheme.apply.apply4(Scheme.map, f, lis1, lists));
        }
        Apply apply = Scheme.apply;
        ModuleMethod moduleMethod = append$Ex;
        Object result = LList.Empty;
        Object arg0 = lis1;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                arg0 = arg02.getCdr();
                result = Pair.make(Scheme.applyToArgs.apply2(f, arg02.getCar()), result);
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        return apply.apply2(moduleMethod, LList.reverseInPlace(result));
    }

    public static Object pairForEach$V(Procedure proc, Object lis1, Object[] argsArray) {
        LList lists = LList.makeList(argsArray, 0);
        if (lists.isPair(lists)) {
            Object lists2 = lists.cons(lis1, lists);
            while (true) {
                Object tails = $PcCdrs(lists2);
                if (!lists.isPair(tails)) {
                    return Values.empty;
                }
                Scheme.apply.apply2(proc, lists2);
                lists2 = tails;
            }
        } else {
            Object lis = lis1;
            while (isNullList(lis) == Boolean.FALSE) {
                Object tail = lists.cdr.apply1(lis);
                proc.apply1(lis);
                lis = tail;
            }
            Object tail2 = Values.empty;
            return tail2;
        }
    }

    public static Object map$Ex$V(Procedure f, Object lis1, Object[] argsArray) {
        frame12 frame12Var = new frame12();
        frame12Var.f = f;
        Object lists = LList.makeList(argsArray, 0);
        if (lists.isPair(lists)) {
            Object lists2 = lists;
            Object lis12 = lis1;
            while (isNullList(lis12) == Boolean.FALSE) {
                Object split = $PcCars$PlCdrs$SlNoTest$SlPair(lists2);
                Object heads = lists.car.apply1(split);
                Object tails = lists.cdr.apply1(split);
                try {
                    ((Pair) lis12).setCar(Scheme.apply.apply3(frame12Var.f, lists.car.apply1(lis12), heads));
                    lis12 = lists.cdr.apply1(lis12);
                    lists2 = tails;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "set-car!", 1, lis12);
                }
            }
        } else {
            pairForEach$V(frame12Var.lambda$Fn11, lis1, new Object[0]);
        }
        return lis1;
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame12 extends ModuleBody {
        Procedure f;
        final ModuleMethod lambda$Fn11;

        public frame12() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 11, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:961");
            this.lambda$Fn11 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector != 11) {
                return super.apply1(moduleMethod, obj);
            }
            lambda22(obj);
            return Values.empty;
        }

        void lambda22(Object pair) {
            try {
                ((Pair) pair).setCar(this.f.apply1(lists.car.apply1(pair)));
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-car!", 1, pair);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 11) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object filterMap$V(Procedure f, Object lis1, Object[] argsArray) {
        frame13 frame13Var = new frame13();
        frame13Var.f = f;
        LList lists = LList.makeList(argsArray, 0);
        if (lists.isPair(lists)) {
            return frame13Var.lambda23recur(lists.cons(lis1, lists), LList.Empty);
        }
        Object res = LList.Empty;
        Object lis = lis1;
        while (isNullList(lis) == Boolean.FALSE) {
            Object head = frame13Var.f.apply1(lists.car.apply1(lis));
            Object tail = lists.cdr.apply1(lis);
            if (head != Boolean.FALSE) {
                res = lists.cons(head, res);
                lis = tail;
            } else {
                lis = tail;
            }
        }
        try {
            return LList.reverseInPlace((LList) res);
        } catch (ClassCastException e) {
            throw new WrongType(e, "reverse!", 1, res);
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame13 extends ModuleBody {
        Procedure f;

        public Object lambda23recur(Object lists, Object res) {
            frame14 frame14Var = new frame14();
            frame14Var.staticLink = this;
            frame14Var.lists = lists;
            frame14Var.res = res;
            return call_with_values.callWithValues(frame14Var.lambda$Fn12, frame14Var.lambda$Fn13);
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame14 extends ModuleBody {
        final ModuleMethod lambda$Fn12 = new ModuleMethod(this, 12, null, 0);
        final ModuleMethod lambda$Fn13;
        Object lists;
        Object res;
        frame13 staticLink;

        public frame14() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 13, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:969");
            this.lambda$Fn13 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 12 ? lambda24() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 12) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 13 ? lambda25(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda24() {
            return srfi1.$PcCars$PlCdrs(this.lists);
        }

        Object lambda25(Object cars, Object cdrs) {
            if (srfi1.isNotPair(cars)) {
                Object obj = this.res;
                try {
                    return LList.reverseInPlace((LList) obj);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "reverse!", 1, obj);
                }
            }
            Object head = Scheme.apply.apply2(this.staticLink.f, cars);
            if (head != Boolean.FALSE) {
                return this.staticLink.lambda23recur(cdrs, lists.cons(head, this.res));
            }
            return this.staticLink.lambda23recur(cdrs, this.res);
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 13) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Object filter(Procedure pred, Object lis) {
        Object res = LList.Empty;
        Object lis2 = lis;
        while (isNullList(lis2) == Boolean.FALSE) {
            Object head = lists.car.apply1(lis2);
            Object tail = lists.cdr.apply1(lis2);
            if (pred.apply1(head) != Boolean.FALSE) {
                res = lists.cons(head, res);
                lis2 = tail;
            } else {
                lis2 = tail;
            }
        }
        try {
            return LList.reverseInPlace((LList) res);
        } catch (ClassCastException e) {
            throw new WrongType(e, "reverse!", 1, res);
        }
    }

    public static Object filter$Ex(Procedure pred, Object lis) {
        Object ans = lis;
        while (true) {
            if (isNullList(ans) != Boolean.FALSE) {
                break;
            }
            if (pred.apply1(lists.car.apply1(ans)) == Boolean.FALSE) {
                ans = lists.cdr.apply1(ans);
            } else {
                Object lis2 = lists.cdr.apply1(ans);
                Object prev = ans;
                loop1: while (true) {
                    if (!lists.isPair(lis2)) {
                        break;
                    }
                    if (pred.apply1(lists.car.apply1(lis2)) != Boolean.FALSE) {
                        prev = lis2;
                        lis2 = lists.cdr.apply1(lis2);
                    } else {
                        Object prev2 = prev;
                        Object lis3 = lists.cdr.apply1(lis2);
                        while (lists.isPair(lis3)) {
                            if (pred.apply1(lists.car.apply1(lis3)) != Boolean.FALSE) {
                                try {
                                    ((Pair) prev2).setCdr(lis3);
                                    lis2 = lists.cdr.apply1(lis3);
                                    prev = lis3;
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "set-cdr!", 1, prev2);
                                }
                            } else {
                                lis3 = lists.cdr.apply1(lis3);
                            }
                        }
                        try {
                            ((Pair) prev2).setCdr(lis3);
                            break loop1;
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "set-cdr!", 1, prev2);
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static Object partition(Procedure pred, Object lis) {
        Object in = LList.Empty;
        Object out = LList.Empty;
        Object lis2 = lis;
        while (isNullList(lis2) == Boolean.FALSE) {
            Object head = lists.car.apply1(lis2);
            Object tail = lists.cdr.apply1(lis2);
            if (pred.apply1(head) != Boolean.FALSE) {
                in = lists.cons(head, in);
                lis2 = tail;
            } else {
                out = lists.cons(head, out);
                lis2 = tail;
            }
        }
        Object[] objArr = new Object[2];
        try {
            objArr[0] = LList.reverseInPlace((LList) in);
            try {
                objArr[1] = LList.reverseInPlace((LList) out);
                return misc.values(objArr);
            } catch (ClassCastException e) {
                throw new WrongType(e, "reverse!", 1, out);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "reverse!", 1, in);
        }
    }

    public static Object partition$Ex(Procedure pred, Object lis) {
        SimpleSymbol simpleSymbol = Lit2;
        Pair in$Mnhead = lists.cons(simpleSymbol, LList.Empty);
        Pair out$Mnhead = lists.cons(simpleSymbol, LList.Empty);
        Object in = in$Mnhead;
        Object lis2 = lis;
        Object out = out$Mnhead;
        while (!isNotPair(lis2)) {
            if (pred.apply1(lists.car.apply1(lis2)) != Boolean.FALSE) {
                try {
                    ((Pair) in).setCdr(lis2);
                    in = lis2;
                    lis2 = lists.cdr.apply1(lis2);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "set-cdr!", 1, in);
                }
            } else {
                try {
                    ((Pair) out).setCdr(lis2);
                    out = lis2;
                    lis2 = lists.cdr.apply1(lis2);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "set-cdr!", 1, out);
                }
            }
        }
        try {
            ((Pair) in).setCdr(LList.Empty);
            try {
                ((Pair) out).setCdr(LList.Empty);
                return misc.values(lists.cdr.apply1(in$Mnhead), lists.cdr.apply1(out$Mnhead));
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "set-cdr!", 1, out);
            }
        } catch (ClassCastException e4) {
            throw new WrongType(e4, "set-cdr!", 1, in);
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame15 extends ModuleBody {
        final ModuleMethod lambda$Fn14;
        Object pred;

        public frame15() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 14, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1199");
            this.lambda$Fn14 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 14 ? lambda26(obj) ? Boolean.TRUE : Boolean.FALSE : super.apply1(moduleMethod, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        boolean lambda26(Object x) {
            return ((Scheme.applyToArgs.apply2(this.pred, x) != Boolean.FALSE ? 1 : 0) + 1) & 1;
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 14) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame16 extends ModuleBody {
        final ModuleMethod lambda$Fn15;
        Object pred;

        public frame16() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 15, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1200");
            this.lambda$Fn15 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 15 ? lambda27(obj) ? Boolean.TRUE : Boolean.FALSE : super.apply1(moduleMethod, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        boolean lambda27(Object x) {
            return ((Scheme.applyToArgs.apply2(this.pred, x) != Boolean.FALSE ? 1 : 0) + 1) & 1;
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 15) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object remove(Object pred, Object l) {
        frame15 frame15Var = new frame15();
        frame15Var.pred = pred;
        return filter(frame15Var.lambda$Fn14, l);
    }

    public static Object remove$Ex(Object pred, Object l) {
        frame16 frame16Var = new frame16();
        frame16Var.pred = pred;
        return filter$Ex(frame16Var.lambda$Fn15, l);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame17 extends ModuleBody {
        final ModuleMethod lambda$Fn16;
        Object maybe$Mn$Eq;
        Object x;

        public frame17() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 16, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1222");
            this.lambda$Fn16 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 16 ? lambda28(obj) ? Boolean.TRUE : Boolean.FALSE : super.apply1(moduleMethod, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        boolean lambda28(Object y) {
            return ((Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.x, y) != Boolean.FALSE ? 1 : 0) + 1) & 1;
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 16) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object delete(Object x, Object lis, Object maybe$Mn$Eq) {
        frame17 frame17Var = new frame17();
        frame17Var.x = x;
        frame17Var.maybe$Mn$Eq = maybe$Mn$Eq;
        return filter(frame17Var.lambda$Fn16, lis);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame18 extends ModuleBody {
        final ModuleMethod lambda$Fn17;
        Object maybe$Mn$Eq;
        Object x;

        public frame18() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 17, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1225");
            this.lambda$Fn17 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 17 ? lambda29(obj) ? Boolean.TRUE : Boolean.FALSE : super.apply1(moduleMethod, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        boolean lambda29(Object y) {
            return ((Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.x, y) != Boolean.FALSE ? 1 : 0) + 1) & 1;
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 17) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object delete$Ex(Object x, Object lis, Object maybe$Mn$Eq) {
        frame18 frame18Var = new frame18();
        frame18Var.x = x;
        frame18Var.maybe$Mn$Eq = maybe$Mn$Eq;
        return filter$Ex(frame18Var.lambda$Fn17, lis);
    }

    public static Object deleteDuplicates(Object lis, Procedure maybe$Mn$Eq) {
        frame19 frame19Var = new frame19();
        frame19Var.maybe$Mn$Eq = maybe$Mn$Eq;
        return frame19Var.lambda30recur(lis);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame19 extends ModuleBody {
        Procedure maybe$Mn$Eq;

        public Object lambda30recur(Object lis) {
            if (srfi1.isNullList(lis) == Boolean.FALSE) {
                Object x = lists.car.apply1(lis);
                Object tail = lists.cdr.apply1(lis);
                Object new$Mntail = lambda30recur(srfi1.delete(x, tail, this.maybe$Mn$Eq));
                if (tail != new$Mntail) {
                    return lists.cons(x, new$Mntail);
                }
            }
            return lis;
        }
    }

    public static Object deleteDuplicates$Ex(Object lis, Procedure maybe$Mn$Eq) {
        frame20 frame20Var = new frame20();
        frame20Var.maybe$Mn$Eq = maybe$Mn$Eq;
        return frame20Var.lambda31recur(lis);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame20 extends ModuleBody {
        Procedure maybe$Mn$Eq;

        public Object lambda31recur(Object lis) {
            if (srfi1.isNullList(lis) == Boolean.FALSE) {
                Object x = lists.car.apply1(lis);
                Object tail = lists.cdr.apply1(lis);
                Object new$Mntail = lambda31recur(srfi1.delete$Ex(x, tail, this.maybe$Mn$Eq));
                if (tail != new$Mntail) {
                    return lists.cons(x, new$Mntail);
                }
            }
            return lis;
        }
    }

    public static Pair alistCons(Object key, Object datum, Object alist) {
        return lists.cons(lists.cons(key, datum), alist);
    }

    public static LList alistCopy(Object alist) {
        Object result = LList.Empty;
        Object arg0 = alist;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                Object cdr = arg02.getCdr();
                Object elt = arg02.getCar();
                result = Pair.make(lists.cons(lists.car.apply1(elt), lists.cdr.apply1(elt)), result);
                arg0 = cdr;
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        return LList.reverseInPlace(result);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 82:
                try {
                    return listCopy((LList) obj);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "list-copy", 1, obj);
                }
            case 83:
                try {
                    return iota(LangObjType.coerceIntNum(obj));
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "iota", 1, obj);
                }
            case 87:
                return isProperList(obj);
            case 88:
                return isDottedList(obj);
            case 89:
                return isCircularList(obj);
            case 90:
                return isNotPair(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 91:
                return isNullList(obj);
            case 93:
                return length$Pl(obj);
            case 95:
                return fifth(obj);
            case 96:
                return sixth(obj);
            case 97:
                return seventh(obj);
            case 98:
                return eighth(obj);
            case 99:
                return ninth(obj);
            case 100:
                return tenth(obj);
            case 101:
                return car$PlCdr(obj);
            case 110:
                return last(obj);
            case 111:
                try {
                    return lastPair((Pair) obj);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "last-pair", 1, obj);
                }
            case 112:
                return unzip1(obj);
            case 113:
                return unzip2(obj);
            case 114:
                return unzip3(obj);
            case 115:
                return unzip4(obj);
            case 116:
                return unzip5(obj);
            case 120:
                return concatenate(obj);
            case 121:
                return concatenate$Ex(obj);
            case 147:
                return deleteDuplicates(obj);
            case 149:
                return deleteDuplicates$Ex(obj);
            case 152:
                return alistCopy(obj);
            default:
                return super.apply1(moduleMethod, obj);
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame21 extends ModuleBody {
        Object key;
        final ModuleMethod lambda$Fn18;
        Object maybe$Mn$Eq;

        public frame21() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 18, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1280");
            this.lambda$Fn18 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 18 ? lambda32(obj) ? Boolean.TRUE : Boolean.FALSE : super.apply1(moduleMethod, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        boolean lambda32(Object elt) {
            return ((Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.key, lists.car.apply1(elt)) != Boolean.FALSE ? 1 : 0) + 1) & 1;
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 18) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object alistDelete(Object key, Object alist, Object maybe$Mn$Eq) {
        frame21 frame21Var = new frame21();
        frame21Var.key = key;
        frame21Var.maybe$Mn$Eq = maybe$Mn$Eq;
        return filter(frame21Var.lambda$Fn18, alist);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame22 extends ModuleBody {
        Object key;
        final ModuleMethod lambda$Fn19;
        Object maybe$Mn$Eq;

        public frame22() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 19, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1283");
            this.lambda$Fn19 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 19 ? lambda33(obj) ? Boolean.TRUE : Boolean.FALSE : super.apply1(moduleMethod, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        boolean lambda33(Object elt) {
            return ((Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.key, lists.car.apply1(elt)) != Boolean.FALSE ? 1 : 0) + 1) & 1;
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 19) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object alistDelete$Ex(Object key, Object alist, Object maybe$Mn$Eq) {
        frame22 frame22Var = new frame22();
        frame22Var.key = key;
        frame22Var.maybe$Mn$Eq = maybe$Mn$Eq;
        return filter$Ex(frame22Var.lambda$Fn19, alist);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        switch (moduleMethod.selector) {
            case 83:
                try {
                    try {
                        try {
                            return iota(LangObjType.coerceIntNum(obj), LangObjType.coerceNumeric(obj2), LangObjType.coerceNumeric(obj3));
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "iota", 3, obj3);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "iota", 2, obj2);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "iota", 1, obj);
                }
            case 130:
                try {
                    return reduce((Procedure) obj, obj2, obj3);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "reduce", 1, obj);
                }
            case 131:
                try {
                    return reduceRight((Procedure) obj, obj2, obj3);
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "reduce-right", 1, obj);
                }
            case 143:
                return delete(obj, obj2, obj3);
            case 145:
                return delete$Ex(obj, obj2, obj3);
            case 151:
                return alistCons(obj, obj2, obj3);
            case 153:
                return alistDelete(obj, obj2, obj3);
            case 155:
                return alistDelete$Ex(obj, obj2, obj3);
            default:
                return super.apply3(moduleMethod, obj, obj2, obj3);
        }
    }

    public static Object find(Object pred, Object list) {
        try {
            Object temp = findTail((Procedure) pred, list);
            return temp != Boolean.FALSE ? lists.car.apply1(temp) : Boolean.FALSE;
        } catch (ClassCastException e) {
            throw new WrongType(e, "find-tail", 0, pred);
        }
    }

    public static Object findTail(Procedure pred, Object list) {
        Object list2 = list;
        while (true) {
            Object isNullList = isNullList(list2);
            try {
                int i = ((isNullList != Boolean.FALSE ? 1 : 0) + 1) & 1;
                if (i == 0) {
                    Object list3 = i != 0 ? Boolean.TRUE : Boolean.FALSE;
                    return list3;
                }
                if (pred.apply1(lists.car.apply1(list2)) != Boolean.FALSE) {
                    return list2;
                }
                list2 = lists.cdr.apply1(list2);
            } catch (ClassCastException e) {
                throw new WrongType(e, "x", -2, isNullList);
            }
        }
    }

    public static Object takeWhile(Procedure pred, Object lis) {
        frame23 frame23Var = new frame23();
        frame23Var.pred = pred;
        return frame23Var.lambda34recur(lis);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame23 extends ModuleBody {
        Procedure pred;

        public Object lambda34recur(Object lis) {
            if (srfi1.isNullList(lis) != Boolean.FALSE) {
                return LList.Empty;
            }
            Object x = lists.car.apply1(lis);
            return this.pred.apply1(x) != Boolean.FALSE ? lists.cons(x, lambda34recur(lists.cdr.apply1(lis))) : LList.Empty;
        }
    }

    public static Object dropWhile(Procedure pred, Object lis) {
        for (Object lis2 = lis; isNullList(lis2) == Boolean.FALSE; lis2 = lists.cdr.apply1(lis2)) {
            if (pred.apply1(lists.car.apply1(lis2)) == Boolean.FALSE) {
                return lis2;
            }
        }
        Object lis3 = LList.Empty;
        return lis3;
    }

    public static Object takeWhile$Ex(Procedure pred, Object lis) {
        Object x = isNullList(lis);
        if (x == Boolean.FALSE ? pred.apply1(lists.car.apply1(lis)) == Boolean.FALSE : x != Boolean.FALSE) {
            return LList.Empty;
        }
        Object rest = lists.cdr.apply1(lis);
        Object prev = lis;
        while (true) {
            if (!lists.isPair(rest)) {
                break;
            }
            if (pred.apply1(lists.car.apply1(rest)) == Boolean.FALSE) {
                try {
                    Object rest2 = prev;
                    ((Pair) rest2).setCdr(LList.Empty);
                    break;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "set-cdr!", 1, prev);
                }
            }
            prev = rest;
            rest = lists.cdr.apply1(rest);
        }
        return lis;
    }

    public static Object span(Procedure pred, Object lis) {
        Object res = LList.Empty;
        Object lis2 = lis;
        while (isNullList(lis2) == Boolean.FALSE) {
            Object head = lists.car.apply1(lis2);
            if (pred.apply1(head) != Boolean.FALSE) {
                lis2 = lists.cdr.apply1(lis2);
                res = lists.cons(head, res);
            } else {
                Object[] objArr = new Object[2];
                try {
                    objArr[0] = LList.reverseInPlace((LList) res);
                    objArr[1] = lis2;
                    return misc.values(objArr);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "reverse!", 1, res);
                }
            }
        }
        Object[] objArr2 = new Object[2];
        try {
            objArr2[0] = LList.reverseInPlace((LList) res);
            objArr2[1] = lis2;
            return misc.values(objArr2);
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "reverse!", 1, res);
        }
    }

    public static Object span$Ex(Procedure pred, Object lis) {
        Object x = isNullList(lis);
        if (x == Boolean.FALSE ? pred.apply1(lists.car.apply1(lis)) == Boolean.FALSE : x != Boolean.FALSE) {
            Object suffix = misc.values(LList.Empty, lis);
            return suffix;
        }
        Object rest = lists.cdr.apply1(lis);
        Object prev = lis;
        while (true) {
            if (isNullList(rest) != Boolean.FALSE) {
                break;
            }
            if (pred.apply1(lists.car.apply1(rest)) == Boolean.FALSE) {
                try {
                    Object rest2 = prev;
                    ((Pair) rest2).setCdr(LList.Empty);
                    break;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "set-cdr!", 1, prev);
                }
            }
            prev = rest;
            rest = lists.cdr.apply1(rest);
        }
        Object suffix2 = rest;
        return misc.values(lis, suffix2);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame24 extends ModuleBody {
        final ModuleMethod lambda$Fn20;
        Object pred;

        public frame24() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 20, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1343");
            this.lambda$Fn20 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 20 ? lambda35(obj) ? Boolean.TRUE : Boolean.FALSE : super.apply1(moduleMethod, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        boolean lambda35(Object x) {
            return ((Scheme.applyToArgs.apply2(this.pred, x) != Boolean.FALSE ? 1 : 0) + 1) & 1;
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 20) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame25 extends ModuleBody {
        final ModuleMethod lambda$Fn21;
        Object pred;

        public frame25() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 21, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1344");
            this.lambda$Fn21 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 21 ? lambda36(obj) ? Boolean.TRUE : Boolean.FALSE : super.apply1(moduleMethod, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        boolean lambda36(Object x) {
            return ((Scheme.applyToArgs.apply2(this.pred, x) != Boolean.FALSE ? 1 : 0) + 1) & 1;
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 21) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* renamed from: break, reason: not valid java name */
    public static Object m1168break(Object pred, Object lis) {
        frame24 frame24Var = new frame24();
        frame24Var.pred = pred;
        return span(frame24Var.lambda$Fn20, lis);
    }

    public static Object break$Ex(Object pred, Object lis) {
        frame25 frame25Var = new frame25();
        frame25Var.pred = pred;
        return span$Ex(frame25Var.lambda$Fn21, lis);
    }

    @Override // gnu.expr.ModuleBody
    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 78:
                return xcons(obj, obj2);
            case 80:
                try {
                    return listTabulate(obj, (Procedure) obj2);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "list-tabulate", 2, obj2);
                }
            case 83:
                try {
                    try {
                        return iota(LangObjType.coerceIntNum(obj), LangObjType.coerceNumeric(obj2));
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "iota", 2, obj2);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "iota", 1, obj);
                }
            case 102:
                try {
                    return take(obj, LangObjType.coerceIntNum(obj2));
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "take", 2, obj2);
                }
            case 103:
                try {
                    return drop(obj, LangObjType.coerceIntNum(obj2));
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "drop", 2, obj2);
                }
            case 104:
                try {
                    return take$Ex(obj, LangObjType.coerceIntNum(obj2));
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "take!", 2, obj2);
                }
            case 105:
                try {
                    return takeRight(obj, LangObjType.coerceIntNum(obj2));
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "take-right", 2, obj2);
                }
            case 106:
                try {
                    return dropRight(obj, LangObjType.coerceIntNum(obj2));
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "drop-right", 2, obj2);
                }
            case 107:
                try {
                    return dropRight$Ex(obj, LangObjType.coerceIntNum(obj2));
                } catch (ClassCastException e9) {
                    throw new WrongType(e9, "drop-right!", 2, obj2);
                }
            case 108:
                try {
                    return splitAt(obj, LangObjType.coerceIntNum(obj2));
                } catch (ClassCastException e10) {
                    throw new WrongType(e10, "split-at", 2, obj2);
                }
            case 109:
                try {
                    return splitAt$Ex(obj, LangObjType.coerceIntNum(obj2));
                } catch (ClassCastException e11) {
                    throw new WrongType(e11, "split-at!", 2, obj2);
                }
            case 118:
                return appendReverse(obj, obj2);
            case 119:
                return appendReverse$Ex(obj, obj2);
            case 137:
                try {
                    return filter((Procedure) obj, obj2);
                } catch (ClassCastException e12) {
                    throw new WrongType(e12, "filter", 1, obj);
                }
            case 138:
                try {
                    return filter$Ex((Procedure) obj, obj2);
                } catch (ClassCastException e13) {
                    throw new WrongType(e13, "filter!", 1, obj);
                }
            case 139:
                try {
                    return partition((Procedure) obj, obj2);
                } catch (ClassCastException e14) {
                    throw new WrongType(e14, "partition", 1, obj);
                }
            case 140:
                try {
                    return partition$Ex((Procedure) obj, obj2);
                } catch (ClassCastException e15) {
                    throw new WrongType(e15, "partition!", 1, obj);
                }
            case 141:
                return remove(obj, obj2);
            case 142:
                return remove$Ex(obj, obj2);
            case 143:
                return delete(obj, obj2);
            case 145:
                return delete$Ex(obj, obj2);
            case 147:
                try {
                    return deleteDuplicates(obj, (Procedure) obj2);
                } catch (ClassCastException e16) {
                    throw new WrongType(e16, "delete-duplicates", 2, obj2);
                }
            case 149:
                try {
                    return deleteDuplicates$Ex(obj, (Procedure) obj2);
                } catch (ClassCastException e17) {
                    throw new WrongType(e17, "delete-duplicates!", 2, obj2);
                }
            case 153:
                return alistDelete(obj, obj2);
            case 155:
                return alistDelete$Ex(obj, obj2);
            case 157:
                return find(obj, obj2);
            case 158:
                try {
                    return findTail((Procedure) obj, obj2);
                } catch (ClassCastException e18) {
                    throw new WrongType(e18, "find-tail", 1, obj);
                }
            case 159:
                try {
                    return takeWhile((Procedure) obj, obj2);
                } catch (ClassCastException e19) {
                    throw new WrongType(e19, "take-while", 1, obj);
                }
            case ComponentConstants.TEXTBOX_PREFERRED_WIDTH /* 160 */:
                try {
                    return dropWhile((Procedure) obj, obj2);
                } catch (ClassCastException e20) {
                    throw new WrongType(e20, "drop-while", 1, obj);
                }
            case 161:
                try {
                    return takeWhile$Ex((Procedure) obj, obj2);
                } catch (ClassCastException e21) {
                    throw new WrongType(e21, "take-while!", 1, obj);
                }
            case 162:
                try {
                    return span((Procedure) obj, obj2);
                } catch (ClassCastException e22) {
                    throw new WrongType(e22, "span", 1, obj);
                }
            case 163:
                try {
                    return span$Ex((Procedure) obj, obj2);
                } catch (ClassCastException e23) {
                    throw new WrongType(e23, "span!", 1, obj);
                }
            case 164:
                return m1168break(obj, obj2);
            case 165:
                return break$Ex(obj, obj2);
            case 182:
                return frame61.lambda84(obj, obj2);
            case 183:
                return frame71.lambda100(obj, obj2);
            default:
                return super.apply2(moduleMethod, obj, obj2);
        }
    }

    public static Object any$V(Procedure procedure, Object obj, Object[] objArr) {
        frame26 frame26Var = new frame26();
        frame26Var.pred = procedure;
        frame26Var.lis1 = obj;
        frame26Var.lists = LList.makeList(objArr, 0);
        if (lists.isPair(frame26Var.lists)) {
            return call_with_values.callWithValues(frame26Var.lambda$Fn22, frame26Var.lambda$Fn23);
        }
        Object isNullList = isNullList(frame26Var.lis1);
        try {
            int i = ((isNullList != Boolean.FALSE ? 1 : 0) + 1) & 1;
            if (i == 0) {
                return i != 0 ? Boolean.TRUE : Boolean.FALSE;
            }
            Object apply1 = lists.car.apply1(frame26Var.lis1);
            for (Object apply12 = lists.cdr.apply1(frame26Var.lis1); isNullList(apply12) == Boolean.FALSE; apply12 = lists.cdr.apply1(apply12)) {
                Object apply13 = frame26Var.pred.apply1(apply1);
                if (apply13 != Boolean.FALSE) {
                    return apply13;
                }
                apply1 = lists.car.apply1(apply12);
            }
            return frame26Var.pred.apply1(apply1);
        } catch (ClassCastException e) {
            throw new WrongType(e, "x", -2, isNullList);
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame26 extends ModuleBody {
        final ModuleMethod lambda$Fn22 = new ModuleMethod(this, 22, null, 0);
        final ModuleMethod lambda$Fn23;
        Object lis1;
        LList lists;
        Procedure pred;

        public frame26() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 23, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1350");
            this.lambda$Fn23 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 22 ? lambda37() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 22) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 23 ? lambda38(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda37() {
            return srfi1.$PcCars$PlCdrs(lists.cons(this.lis1, this.lists));
        }

        Object lambda38(Object heads, Object tails) {
            boolean x = lists.isPair(heads);
            if (!x) {
                return x ? Boolean.TRUE : Boolean.FALSE;
            }
            Object heads2 = heads;
            Object tails2 = tails;
            while (true) {
                Object split = srfi1.$PcCars$PlCdrs$SlPair(tails2);
                Object next$Mnheads = lists.car.apply1(split);
                Object next$Mntails = lists.cdr.apply1(split);
                if (lists.isPair(next$Mnheads)) {
                    Object x2 = Scheme.apply.apply2(this.pred, heads2);
                    if (x2 != Boolean.FALSE) {
                        return x2;
                    }
                    tails2 = next$Mntails;
                    heads2 = next$Mnheads;
                } else {
                    return Scheme.apply.apply2(this.pred, heads2);
                }
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 23) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Object every$V(Procedure pred, Object lis1, Object[] argsArray) {
        frame27 frame27Var = new frame27();
        frame27Var.pred = pred;
        frame27Var.lis1 = lis1;
        frame27Var.lists = LList.makeList(argsArray, 0);
        if (lists.isPair(frame27Var.lists)) {
            return call_with_values.callWithValues(frame27Var.lambda$Fn24, frame27Var.lambda$Fn25);
        }
        Object x = isNullList(frame27Var.lis1);
        if (x != Boolean.FALSE) {
            return x;
        }
        Object head = lists.car.apply1(frame27Var.lis1);
        for (Object tail = lists.cdr.apply1(frame27Var.lis1); isNullList(tail) == Boolean.FALSE; tail = lists.cdr.apply1(tail)) {
            Object x2 = frame27Var.pred.apply1(head);
            if (x2 == Boolean.FALSE) {
                return x2;
            }
            head = lists.car.apply1(tail);
        }
        return frame27Var.pred.apply1(head);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame27 extends ModuleBody {
        final ModuleMethod lambda$Fn24 = new ModuleMethod(this, 26, null, 0);
        final ModuleMethod lambda$Fn25;
        Object lis1;
        LList lists;
        Procedure pred;

        public frame27() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 27, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1378");
            this.lambda$Fn25 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 26 ? lambda39() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 26) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 27 ? lambda40(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda39() {
            return srfi1.$PcCars$PlCdrs(lists.cons(this.lis1, this.lists));
        }

        Object lambda40(Object obj, Object obj2) {
            frame28 frame28Var = new frame28();
            frame28Var.staticLink = this;
            int i = ((lists.isPair(obj) ? 1 : 0) + 1) & 1;
            if (i != 0) {
                return i != 0 ? Boolean.TRUE : Boolean.FALSE;
            }
            return frame28Var.lambda41lp(obj, obj2);
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 27) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame28 extends ModuleBody {
        frame27 staticLink;

        public Object lambda41lp(Object heads, Object tails) {
            frame29 frame29Var = new frame29();
            frame29Var.staticLink = this;
            frame29Var.heads = heads;
            frame29Var.tails = tails;
            return call_with_values.callWithValues(frame29Var.lambda$Fn26, frame29Var.lambda$Fn27);
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame29 extends ModuleBody {
        Object heads;
        final ModuleMethod lambda$Fn26 = new ModuleMethod(this, 24, null, 0);
        final ModuleMethod lambda$Fn27;
        frame28 staticLink;
        Object tails;

        public frame29() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 25, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1381");
            this.lambda$Fn27 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 24 ? lambda42() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 24) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 25 ? lambda43(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda42() {
            return srfi1.$PcCars$PlCdrs(this.tails);
        }

        Object lambda43(Object next$Mnheads, Object next$Mntails) {
            if (lists.isPair(next$Mnheads)) {
                Object x = Scheme.apply.apply2(this.staticLink.staticLink.pred, this.heads);
                return x != Boolean.FALSE ? this.staticLink.lambda41lp(next$Mnheads, next$Mntails) : x;
            }
            return Scheme.apply.apply2(this.staticLink.staticLink.pred, this.heads);
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 25) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Object listIndex$V(Procedure pred, Object lis1, Object[] argsArray) {
        frame30 frame30Var = new frame30();
        frame30Var.pred = pred;
        LList lists = LList.makeList(argsArray, 0);
        if (lists.isPair(lists)) {
            return frame30Var.lambda44lp(lists.cons(lis1, lists), Lit0);
        }
        Object n = Lit0;
        Object lis = lis1;
        while (true) {
            Object isNullList = isNullList(lis);
            try {
                int i = ((isNullList != Boolean.FALSE ? 1 : 0) + 1) & 1;
                if (i == 0) {
                    return i != 0 ? Boolean.TRUE : Boolean.FALSE;
                }
                if (frame30Var.pred.apply1(lists.car.apply1(lis)) != Boolean.FALSE) {
                    return n;
                }
                lis = lists.cdr.apply1(lis);
                n = AddOp.$Pl.apply2(n, Lit1);
            } catch (ClassCastException e) {
                throw new WrongType(e, "x", -2, isNullList);
            }
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame30 extends ModuleBody {
        Procedure pred;

        public Object lambda44lp(Object lists, Object n) {
            frame31 frame31Var = new frame31();
            frame31Var.staticLink = this;
            frame31Var.lists = lists;
            frame31Var.n = n;
            return call_with_values.callWithValues(frame31Var.lambda$Fn28, frame31Var.lambda$Fn29);
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame31 extends ModuleBody {
        final ModuleMethod lambda$Fn28 = new ModuleMethod(this, 28, null, 0);
        final ModuleMethod lambda$Fn29;
        Object lists;
        Object n;
        frame30 staticLink;

        public frame31() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 29, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1404");
            this.lambda$Fn29 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 28 ? lambda45() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 28) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 29 ? lambda46(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda45() {
            return srfi1.$PcCars$PlCdrs(this.lists);
        }

        Object lambda46(Object heads, Object tails) {
            boolean x = lists.isPair(heads);
            return x ? Scheme.apply.apply2(this.staticLink.pred, heads) != Boolean.FALSE ? this.n : this.staticLink.lambda44lp(tails, AddOp.$Pl.apply2(this.n, srfi1.Lit1)) : x ? Boolean.TRUE : Boolean.FALSE;
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 29) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame72 extends ModuleBody {
        Object $Eq;
        final ModuleMethod lambda$Fn79;
        Object lis2;

        public frame72() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 77, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1443");
            this.lambda$Fn79 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 77 ? lambda101(obj) : super.apply1(moduleMethod, obj);
        }

        Object lambda101(Object x) {
            Object obj = this.lis2;
            Object obj2 = this.$Eq;
            try {
                return lists.member(x, obj, (Procedure) obj2);
            } catch (ClassCastException e) {
                throw new WrongType(e, "member", 3, obj2);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 77) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    static Object $PcLset2$Ls$Eq(Object $Eq, Object lis1, Object lis2) {
        frame72 frame72Var = new frame72();
        frame72Var.$Eq = $Eq;
        frame72Var.lis2 = lis2;
        return every$V(frame72Var.lambda$Fn79, lis1, new Object[0]);
    }

    public static Object lset$Ls$Eq$V(Procedure procedure, Object[] objArr) {
        LList makeList = LList.makeList(objArr, 0);
        int i = ((lists.isPair(makeList) ? 1 : 0) + 1) & 1;
        if (i != 0) {
            return i != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        Object apply1 = lists.car.apply1(makeList);
        Object apply12 = lists.cdr.apply1(makeList);
        while (true) {
            int i2 = ((lists.isPair(apply12) ? 1 : 0) + 1) & 1;
            if (i2 != 0) {
                return i2 != 0 ? Boolean.TRUE : Boolean.FALSE;
            }
            Object apply13 = lists.car.apply1(apply12);
            apply12 = lists.cdr.apply1(apply12);
            boolean z = apply13 == apply1;
            Object $PcLset2$Ls$Eq = z ? z ? Boolean.TRUE : Boolean.FALSE : $PcLset2$Ls$Eq(procedure, apply1, apply13);
            if ($PcLset2$Ls$Eq == Boolean.FALSE) {
                return $PcLset2$Ls$Eq;
            }
            apply1 = apply13;
        }
    }

    public static Object lset$Eq$V(Procedure procedure, Object[] objArr) {
        Object $PcLset2$Ls$Eq;
        LList makeList = LList.makeList(objArr, 0);
        int i = ((lists.isPair(makeList) ? 1 : 0) + 1) & 1;
        if (i != 0) {
            return i != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        Object apply1 = lists.car.apply1(makeList);
        Object apply12 = lists.cdr.apply1(makeList);
        while (true) {
            int i2 = ((lists.isPair(apply12) ? 1 : 0) + 1) & 1;
            if (i2 != 0) {
                return i2 != 0 ? Boolean.TRUE : Boolean.FALSE;
            }
            Object apply13 = lists.car.apply1(apply12);
            apply12 = lists.cdr.apply1(apply12);
            boolean z = apply1 == apply13;
            if (z) {
                $PcLset2$Ls$Eq = z ? Boolean.TRUE : Boolean.FALSE;
            } else {
                Object $PcLset2$Ls$Eq2 = $PcLset2$Ls$Eq(procedure, apply1, apply13);
                $PcLset2$Ls$Eq = $PcLset2$Ls$Eq2 != Boolean.FALSE ? $PcLset2$Ls$Eq(procedure, apply13, apply1) : $PcLset2$Ls$Eq2;
            }
            if ($PcLset2$Ls$Eq == Boolean.FALSE) {
                return $PcLset2$Ls$Eq;
            }
            apply1 = apply13;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame32 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn30;

        public frame32() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 30, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1466");
            this.lambda$Fn30 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 30 ? lambda47(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda47(Object elt, Object ans) {
            return lists.member(elt, ans, this.$Eq) != Boolean.FALSE ? ans : lists.cons(elt, ans);
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 30) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Object lsetAdjoin$V(Procedure $Eq, Object lis, Object[] argsArray) {
        frame32 frame32Var = new frame32();
        frame32Var.$Eq = $Eq;
        LList elts = LList.makeList(argsArray, 0);
        return fold$V(frame32Var.lambda$Fn30, lis, elts, new Object[0]);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame33 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn31;
        final ModuleMethod lambda$Fn32;

        public frame33() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 32, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1476");
            this.lambda$Fn32 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 33, null, 8194);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1471");
            this.lambda$Fn31 = moduleMethod2;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            switch (moduleMethod.selector) {
                case 32:
                    return lambda49(obj, obj2);
                case 33:
                    return lambda48(obj, obj2);
                default:
                    return super.apply2(moduleMethod, obj, obj2);
            }
        }

        Object lambda48(Object lis, Object ans) {
            if (!lists.isNull(lis)) {
                if (lists.isNull(ans)) {
                    return lis;
                }
                if (lis != ans) {
                    return srfi1.fold$V(this.lambda$Fn32, ans, lis, new Object[0]);
                }
            }
            return ans;
        }

        Object lambda49(Object elt, Object ans) {
            frame34 frame34Var = new frame34();
            frame34Var.staticLink = this;
            frame34Var.elt = elt;
            return srfi1.any$V(frame34Var.lambda$Fn33, ans, new Object[0]) != Boolean.FALSE ? ans : lists.cons(frame34Var.elt, ans);
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 32:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 33:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                default:
                    return super.match2(moduleMethod, obj, obj2, callContext);
            }
        }
    }

    public static Object lsetUnion$V(Procedure $Eq, Object[] argsArray) {
        frame33 frame33Var = new frame33();
        frame33Var.$Eq = $Eq;
        LList lists = LList.makeList(argsArray, 0);
        return reduce(frame33Var.lambda$Fn31, LList.Empty, lists);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame34 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn33;
        frame33 staticLink;

        public frame34() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 31, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1476");
            this.lambda$Fn33 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 31 ? lambda50(obj) : super.apply1(moduleMethod, obj);
        }

        Object lambda50(Object x) {
            return this.staticLink.$Eq.apply2(x, this.elt);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 31) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame35 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn34;
        final ModuleMethod lambda$Fn35;

        public frame35() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 35, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1488");
            this.lambda$Fn35 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 36, null, 8194);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1483");
            this.lambda$Fn34 = moduleMethod2;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            switch (moduleMethod.selector) {
                case 35:
                    return lambda52(obj, obj2);
                case 36:
                    return lambda51(obj, obj2);
                default:
                    return super.apply2(moduleMethod, obj, obj2);
            }
        }

        Object lambda51(Object lis, Object ans) {
            if (!lists.isNull(lis)) {
                if (lists.isNull(ans)) {
                    return lis;
                }
                if (lis != ans) {
                    return srfi1.pairFold$V(this.lambda$Fn35, ans, lis, new Object[0]);
                }
            }
            return ans;
        }

        Object lambda52(Object pair, Object ans) {
            frame36 frame36Var = new frame36();
            frame36Var.staticLink = this;
            frame36Var.elt = lists.car.apply1(pair);
            if (srfi1.any$V(frame36Var.lambda$Fn36, ans, new Object[0]) != Boolean.FALSE) {
                return ans;
            }
            try {
                ((Pair) pair).setCdr(ans);
                return pair;
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-cdr!", 1, pair);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 35:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 36:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                default:
                    return super.match2(moduleMethod, obj, obj2, callContext);
            }
        }
    }

    public static Object lsetUnion$Ex$V(Procedure $Eq, Object[] argsArray) {
        frame35 frame35Var = new frame35();
        frame35Var.$Eq = $Eq;
        LList lists = LList.makeList(argsArray, 0);
        return reduce(frame35Var.lambda$Fn34, LList.Empty, lists);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame36 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn36;
        frame35 staticLink;

        public frame36() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 34, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1490");
            this.lambda$Fn36 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 34 ? lambda53(obj) : super.apply1(moduleMethod, obj);
        }

        Object lambda53(Object x) {
            return this.staticLink.$Eq.apply2(x, this.elt);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 34) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object lsetIntersection$V(Procedure $Eq, Object lis1, Object[] argsArray) {
        frame37 frame37Var = new frame37();
        frame37Var.$Eq = $Eq;
        LList lists = LList.makeList(argsArray, 0);
        frame37Var.lists = delete(lis1, lists, Scheme.isEq);
        if (any$V(null$Mnlist$Qu, frame37Var.lists, new Object[0]) != Boolean.FALSE) {
            return LList.Empty;
        }
        if (lists.isNull(frame37Var.lists)) {
            return lis1;
        }
        return filter(frame37Var.lambda$Fn37, lis1);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame37 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn37;
        Object lists;

        public frame37() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 38, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1501");
            this.lambda$Fn37 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 38 ? lambda54(obj) : super.apply1(moduleMethod, obj);
        }

        Object lambda54(Object x) {
            frame38 frame38Var = new frame38();
            frame38Var.staticLink = this;
            frame38Var.x = x;
            return srfi1.every$V(frame38Var.lambda$Fn38, this.lists, new Object[0]);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 38) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame38 extends ModuleBody {
        final ModuleMethod lambda$Fn38;
        frame37 staticLink;
        Object x;

        public frame38() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 37, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1502");
            this.lambda$Fn38 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 37 ? lambda55(obj) : super.apply1(moduleMethod, obj);
        }

        Object lambda55(Object lis) {
            return lists.member(this.x, lis, this.staticLink.$Eq);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 37) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object lsetIntersection$Ex$V(Procedure $Eq, Object lis1, Object[] argsArray) {
        frame39 frame39Var = new frame39();
        frame39Var.$Eq = $Eq;
        LList lists = LList.makeList(argsArray, 0);
        frame39Var.lists = delete(lis1, lists, Scheme.isEq);
        if (any$V(null$Mnlist$Qu, frame39Var.lists, new Object[0]) != Boolean.FALSE) {
            return LList.Empty;
        }
        if (lists.isNull(frame39Var.lists)) {
            return lis1;
        }
        return filter$Ex(frame39Var.lambda$Fn39, lis1);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame39 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn39;
        Object lists;

        public frame39() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 40, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1509");
            this.lambda$Fn39 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 40 ? lambda56(obj) : super.apply1(moduleMethod, obj);
        }

        Object lambda56(Object x) {
            frame40 frame40Var = new frame40();
            frame40Var.staticLink = this;
            frame40Var.x = x;
            return srfi1.every$V(frame40Var.lambda$Fn40, this.lists, new Object[0]);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 40) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame40 extends ModuleBody {
        final ModuleMethod lambda$Fn40;
        frame39 staticLink;
        Object x;

        public frame40() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 39, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1510");
            this.lambda$Fn40 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 39 ? lambda57(obj) : super.apply1(moduleMethod, obj);
        }

        Object lambda57(Object lis) {
            return lists.member(this.x, lis, this.staticLink.$Eq);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 39) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object lsetDifference$V(Procedure $Eq, Object lis1, Object[] argsArray) {
        frame41 frame41Var = new frame41();
        frame41Var.$Eq = $Eq;
        LList lists = LList.makeList(argsArray, 0);
        frame41Var.lists = filter(lists.pair$Qu, lists);
        return lists.isNull(frame41Var.lists) ? lis1 : lists.memq(lis1, frame41Var.lists) != Boolean.FALSE ? LList.Empty : filter(frame41Var.lambda$Fn41, lis1);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame41 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn41;
        Object lists;

        public frame41() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 42, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1518");
            this.lambda$Fn41 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 42 ? lambda58(obj) : super.apply1(moduleMethod, obj);
        }

        Object lambda58(Object x) {
            frame42 frame42Var = new frame42();
            frame42Var.staticLink = this;
            frame42Var.x = x;
            return srfi1.every$V(frame42Var.lambda$Fn42, this.lists, new Object[0]);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 42) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame42 extends ModuleBody {
        final ModuleMethod lambda$Fn42;
        frame41 staticLink;
        Object x;

        public frame42() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 41, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1519");
            this.lambda$Fn42 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 41 ? lambda59(obj) ? Boolean.TRUE : Boolean.FALSE : super.apply1(moduleMethod, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        boolean lambda59(Object lis) {
            return ((lists.member(this.x, lis, this.staticLink.$Eq) != Boolean.FALSE ? 1 : 0) + 1) & 1;
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 41) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object lsetDifference$Ex$V(Procedure $Eq, Object lis1, Object[] argsArray) {
        frame43 frame43Var = new frame43();
        frame43Var.$Eq = $Eq;
        LList lists = LList.makeList(argsArray, 0);
        frame43Var.lists = filter(lists.pair$Qu, lists);
        return lists.isNull(frame43Var.lists) ? lis1 : lists.memq(lis1, frame43Var.lists) != Boolean.FALSE ? LList.Empty : filter$Ex(frame43Var.lambda$Fn43, lis1);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame43 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn43;
        Object lists;

        public frame43() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 44, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1527");
            this.lambda$Fn43 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 44 ? lambda60(obj) : super.apply1(moduleMethod, obj);
        }

        Object lambda60(Object x) {
            frame44 frame44Var = new frame44();
            frame44Var.staticLink = this;
            frame44Var.x = x;
            return srfi1.every$V(frame44Var.lambda$Fn44, this.lists, new Object[0]);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 44) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame44 extends ModuleBody {
        final ModuleMethod lambda$Fn44;
        frame43 staticLink;
        Object x;

        public frame44() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 43, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1528");
            this.lambda$Fn44 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 43 ? lambda61(obj) ? Boolean.TRUE : Boolean.FALSE : super.apply1(moduleMethod, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        boolean lambda61(Object lis) {
            return ((lists.member(this.x, lis, this.staticLink.$Eq) != Boolean.FALSE ? 1 : 0) + 1) & 1;
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 43) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame45 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn45;

        public frame45() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 48, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1534");
            this.lambda$Fn45 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 48 ? lambda62(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda62(Object b, Object a) {
            frame46 frame46Var = new frame46();
            frame46Var.staticLink = this;
            frame46Var.b = b;
            frame46Var.a = a;
            return call_with_values.callWithValues(frame46Var.lambda$Fn46, frame46Var.lambda$Fn47);
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 48) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Object lsetXor$V(Procedure $Eq, Object[] argsArray) {
        frame45 frame45Var = new frame45();
        frame45Var.$Eq = $Eq;
        LList lists = LList.makeList(argsArray, 0);
        return reduce(frame45Var.lambda$Fn45, LList.Empty, lists);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame46 extends ModuleBody {
        Object a;
        Object b;
        final ModuleMethod lambda$Fn46 = new ModuleMethod(this, 46, null, 0);
        final ModuleMethod lambda$Fn47;
        frame45 staticLink;

        public frame46() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 47, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1544");
            this.lambda$Fn47 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 46 ? lambda63() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 46) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 47 ? lambda64(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda63() {
            return srfi1.lsetDiff$PlIntersection$V(this.staticLink.$Eq, this.a, new Object[]{this.b});
        }

        Object lambda64(Object a$Mnb, Object aIntB) {
            frame47 frame47Var = new frame47();
            frame47Var.staticLink = this;
            frame47Var.a$Mnint$Mnb = aIntB;
            return lists.isNull(a$Mnb) ? srfi1.lsetDifference$V(this.staticLink.$Eq, this.b, new Object[]{this.a}) : lists.isNull(frame47Var.a$Mnint$Mnb) ? append.append$V(new Object[]{this.b, this.a}) : srfi1.fold$V(frame47Var.lambda$Fn48, a$Mnb, this.b, new Object[0]);
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 47) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame47 extends ModuleBody {
        Object a$Mnint$Mnb;
        final ModuleMethod lambda$Fn48;
        frame46 staticLink;

        public frame47() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 45, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1547");
            this.lambda$Fn48 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 45 ? lambda65(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda65(Object xb, Object ans) {
            return lists.member(xb, this.a$Mnint$Mnb, this.staticLink.staticLink.$Eq) != Boolean.FALSE ? ans : lists.cons(xb, ans);
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 45) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame48 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn49;

        public frame48() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 52, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1555");
            this.lambda$Fn49 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 52 ? lambda66(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda66(Object b, Object a) {
            frame49 frame49Var = new frame49();
            frame49Var.staticLink = this;
            frame49Var.b = b;
            frame49Var.a = a;
            return call_with_values.callWithValues(frame49Var.lambda$Fn50, frame49Var.lambda$Fn51);
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 52) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Object lsetXor$Ex$V(Procedure $Eq, Object[] argsArray) {
        frame48 frame48Var = new frame48();
        frame48Var.$Eq = $Eq;
        LList lists = LList.makeList(argsArray, 0);
        return reduce(frame48Var.lambda$Fn49, LList.Empty, lists);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame49 extends ModuleBody {
        Object a;
        Object b;
        final ModuleMethod lambda$Fn50 = new ModuleMethod(this, 50, null, 0);
        final ModuleMethod lambda$Fn51;
        frame48 staticLink;

        public frame49() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 51, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1565");
            this.lambda$Fn51 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 50 ? lambda67() : super.apply0(moduleMethod);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 50) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 51 ? lambda68(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda67() {
            return srfi1.lsetDiff$PlIntersection$Ex$V(this.staticLink.$Eq, this.a, new Object[]{this.b});
        }

        Object lambda68(Object a$Mnb, Object aIntB) {
            frame50 frame50Var = new frame50();
            frame50Var.staticLink = this;
            frame50Var.a$Mnint$Mnb = aIntB;
            return lists.isNull(a$Mnb) ? srfi1.lsetDifference$Ex$V(this.staticLink.$Eq, this.b, new Object[]{this.a}) : lists.isNull(frame50Var.a$Mnint$Mnb) ? srfi1.append$Ex$V(new Object[]{this.b, this.a}) : srfi1.pairFold$V(frame50Var.lambda$Fn52, a$Mnb, this.b, new Object[0]);
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 51) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame50 extends ModuleBody {
        Object a$Mnint$Mnb;
        final ModuleMethod lambda$Fn52;
        frame49 staticLink;

        public frame50() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 49, null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1568");
            this.lambda$Fn52 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 49 ? lambda69(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        Object lambda69(Object b$Mnpair, Object ans) {
            if (lists.member(lists.car.apply1(b$Mnpair), this.a$Mnint$Mnb, this.staticLink.staticLink.$Eq) != Boolean.FALSE) {
                return ans;
            }
            try {
                ((Pair) b$Mnpair).setCdr(ans);
                return b$Mnpair;
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-cdr!", 1, b$Mnpair);
            }
        }

        @Override // gnu.expr.ModuleBody
        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 49) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Object lsetDiff$PlIntersection$V(Procedure $Eq, Object lis1, Object[] argsArray) {
        frame51 frame51Var = new frame51();
        frame51Var.$Eq = $Eq;
        frame51Var.lists = LList.makeList(argsArray, 0);
        return every$V(null$Mnlist$Qu, frame51Var.lists, new Object[0]) != Boolean.FALSE ? misc.values(lis1, LList.Empty) : lists.memq(lis1, frame51Var.lists) != Boolean.FALSE ? misc.values(LList.Empty, lis1) : partition(frame51Var.lambda$Fn53, lis1);
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame51 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn53;
        LList lists;

        public frame51() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 54, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1579");
            this.lambda$Fn53 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 54 ? lambda70(obj) ? Boolean.TRUE : Boolean.FALSE : super.apply1(moduleMethod, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        boolean lambda70(Object elt) {
            frame52 frame52Var = new frame52();
            frame52Var.staticLink = this;
            frame52Var.elt = elt;
            return ((srfi1.any$V(frame52Var.lambda$Fn54, this.lists, new Object[0]) != Boolean.FALSE ? 1 : 0) + 1) & 1;
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 54) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame52 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn54;
        frame51 staticLink;

        public frame52() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 53, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1580");
            this.lambda$Fn54 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 53 ? lambda71(obj) : super.apply1(moduleMethod, obj);
        }

        Object lambda71(Object lis) {
            return lists.member(this.elt, lis, this.staticLink.$Eq);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 53) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object lsetDiff$PlIntersection$Ex$V(Procedure $Eq, Object lis1, Object[] argsArray) {
        frame53 frame53Var = new frame53();
        frame53Var.$Eq = $Eq;
        frame53Var.lists = LList.makeList(argsArray, 0);
        return every$V(null$Mnlist$Qu, frame53Var.lists, new Object[0]) != Boolean.FALSE ? misc.values(lis1, LList.Empty) : lists.memq(lis1, frame53Var.lists) != Boolean.FALSE ? misc.values(LList.Empty, lis1) : partition$Ex(frame53Var.lambda$Fn55, lis1);
    }

    @Override // gnu.expr.ModuleBody
    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        switch (moduleMethod.selector) {
            case 79:
                Object obj = objArr[0];
                int length = objArr.length - 1;
                Object[] objArr2 = new Object[length];
                while (true) {
                    length--;
                    if (length < 0) {
                        return makeList$V(obj, objArr2);
                    }
                    objArr2[length] = objArr[length + 1];
                }
            case 81:
                return LList.consX(objArr);
            case 86:
                Object obj2 = objArr[0];
                int length2 = objArr.length - 1;
                Object[] objArr3 = new Object[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        return circularList$V(obj2, objArr3);
                    }
                    objArr3[length2] = objArr[length2 + 1];
                }
            case 92:
                Object obj3 = objArr[0];
                int length3 = objArr.length - 1;
                Object[] objArr4 = new Object[length3];
                while (true) {
                    length3--;
                    if (length3 < 0) {
                        return list$Eq$V(obj3, objArr4);
                    }
                    objArr4[length3] = objArr[length3 + 1];
                }
            case 94:
                Object obj4 = objArr[0];
                int length4 = objArr.length - 1;
                Object[] objArr5 = new Object[length4];
                while (true) {
                    length4--;
                    if (length4 < 0) {
                        return zip$V(obj4, objArr5);
                    }
                    objArr5[length4] = objArr[length4 + 1];
                }
            case 117:
                return append$Ex$V(objArr);
            case 122:
                Object obj5 = objArr[0];
                try {
                    Procedure procedure = (Procedure) obj5;
                    Object obj6 = objArr[1];
                    int length5 = objArr.length - 2;
                    Object[] objArr6 = new Object[length5];
                    while (true) {
                        length5--;
                        if (length5 < 0) {
                            return count$V(procedure, obj6, objArr6);
                        }
                        objArr6[length5] = objArr[length5 + 2];
                    }
                } catch (ClassCastException e) {
                    throw new WrongType(e, "count", 1, obj5);
                }
            case 123:
                int length6 = objArr.length - 4;
                Object obj7 = objArr[0];
                try {
                    Procedure procedure2 = (Procedure) obj7;
                    Object obj8 = objArr[1];
                    try {
                        Procedure procedure3 = (Procedure) obj8;
                        Object obj9 = objArr[2];
                        try {
                            Procedure procedure4 = (Procedure) obj9;
                            Object obj10 = objArr[3];
                            return length6 <= 0 ? unfoldRight(procedure2, procedure3, procedure4, obj10) : unfoldRight(procedure2, procedure3, procedure4, obj10, objArr[4]);
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "unfold-right", 3, obj9);
                        }
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "unfold-right", 2, obj8);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "unfold-right", 1, obj7);
                }
            case 125:
                Object obj11 = objArr[0];
                try {
                    Procedure procedure5 = (Procedure) obj11;
                    Object obj12 = objArr[1];
                    try {
                        Procedure procedure6 = (Procedure) obj12;
                        Object obj13 = objArr[2];
                        try {
                            Procedure procedure7 = (Procedure) obj13;
                            Object obj14 = objArr[3];
                            int length7 = objArr.length - 4;
                            Object[] objArr7 = new Object[length7];
                            while (true) {
                                length7--;
                                if (length7 < 0) {
                                    return unfold$V(procedure5, procedure6, procedure7, obj14, objArr7);
                                }
                                objArr7[length7] = objArr[length7 + 4];
                            }
                        } catch (ClassCastException e5) {
                            throw new WrongType(e5, "unfold", 3, obj13);
                        }
                    } catch (ClassCastException e6) {
                        throw new WrongType(e6, "unfold", 2, obj12);
                    }
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "unfold", 1, obj11);
                }
            case 126:
                Object obj15 = objArr[0];
                try {
                    Procedure procedure8 = (Procedure) obj15;
                    Object obj16 = objArr[1];
                    Object obj17 = objArr[2];
                    int length8 = objArr.length - 3;
                    Object[] objArr8 = new Object[length8];
                    while (true) {
                        length8--;
                        if (length8 < 0) {
                            return fold$V(procedure8, obj16, obj17, objArr8);
                        }
                        objArr8[length8] = objArr[length8 + 3];
                    }
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "fold", 1, obj15);
                }
            case 127:
                Object obj18 = objArr[0];
                try {
                    Procedure procedure9 = (Procedure) obj18;
                    Object obj19 = objArr[1];
                    Object obj20 = objArr[2];
                    int length9 = objArr.length - 3;
                    Object[] objArr9 = new Object[length9];
                    while (true) {
                        length9--;
                        if (length9 < 0) {
                            return foldRight$V(procedure9, obj19, obj20, objArr9);
                        }
                        objArr9[length9] = objArr[length9 + 3];
                    }
                } catch (ClassCastException e9) {
                    throw new WrongType(e9, "fold-right", 1, obj18);
                }
            case 128:
                Object obj21 = objArr[0];
                try {
                    Procedure procedure10 = (Procedure) obj21;
                    Object obj22 = objArr[1];
                    Object obj23 = objArr[2];
                    int length10 = objArr.length - 3;
                    Object[] objArr10 = new Object[length10];
                    while (true) {
                        length10--;
                        if (length10 < 0) {
                            return pairFoldRight$V(procedure10, obj22, obj23, objArr10);
                        }
                        objArr10[length10] = objArr[length10 + 3];
                    }
                } catch (ClassCastException e10) {
                    throw new WrongType(e10, "pair-fold-right", 1, obj21);
                }
            case 129:
                Object obj24 = objArr[0];
                try {
                    Procedure procedure11 = (Procedure) obj24;
                    Object obj25 = objArr[1];
                    Object obj26 = objArr[2];
                    int length11 = objArr.length - 3;
                    Object[] objArr11 = new Object[length11];
                    while (true) {
                        length11--;
                        if (length11 < 0) {
                            return pairFold$V(procedure11, obj25, obj26, objArr11);
                        }
                        objArr11[length11] = objArr[length11 + 3];
                    }
                } catch (ClassCastException e11) {
                    throw new WrongType(e11, "pair-fold", 1, obj24);
                }
            case 132:
                Object obj27 = objArr[0];
                Object obj28 = objArr[1];
                int length12 = objArr.length - 2;
                Object[] objArr12 = new Object[length12];
                while (true) {
                    length12--;
                    if (length12 < 0) {
                        return appendMap$V(obj27, obj28, objArr12);
                    }
                    objArr12[length12] = objArr[length12 + 2];
                }
            case 133:
                Object obj29 = objArr[0];
                Object obj30 = objArr[1];
                int length13 = objArr.length - 2;
                Object[] objArr13 = new Object[length13];
                while (true) {
                    length13--;
                    if (length13 < 0) {
                        return appendMap$Ex$V(obj29, obj30, objArr13);
                    }
                    objArr13[length13] = objArr[length13 + 2];
                }
            case 134:
                Object obj31 = objArr[0];
                try {
                    Procedure procedure12 = (Procedure) obj31;
                    Object obj32 = objArr[1];
                    int length14 = objArr.length - 2;
                    Object[] objArr14 = new Object[length14];
                    while (true) {
                        length14--;
                        if (length14 < 0) {
                            return pairForEach$V(procedure12, obj32, objArr14);
                        }
                        objArr14[length14] = objArr[length14 + 2];
                    }
                } catch (ClassCastException e12) {
                    throw new WrongType(e12, "pair-for-each", 1, obj31);
                }
            case 135:
                Object obj33 = objArr[0];
                try {
                    Procedure procedure13 = (Procedure) obj33;
                    Object obj34 = objArr[1];
                    int length15 = objArr.length - 2;
                    Object[] objArr15 = new Object[length15];
                    while (true) {
                        length15--;
                        if (length15 < 0) {
                            return map$Ex$V(procedure13, obj34, objArr15);
                        }
                        objArr15[length15] = objArr[length15 + 2];
                    }
                } catch (ClassCastException e13) {
                    throw new WrongType(e13, "map!", 1, obj33);
                }
            case 136:
                Object obj35 = objArr[0];
                try {
                    Procedure procedure14 = (Procedure) obj35;
                    Object obj36 = objArr[1];
                    int length16 = objArr.length - 2;
                    Object[] objArr16 = new Object[length16];
                    while (true) {
                        length16--;
                        if (length16 < 0) {
                            return filterMap$V(procedure14, obj36, objArr16);
                        }
                        objArr16[length16] = objArr[length16 + 2];
                    }
                } catch (ClassCastException e14) {
                    throw new WrongType(e14, "filter-map", 1, obj35);
                }
            case 166:
                Object obj37 = objArr[0];
                try {
                    Procedure procedure15 = (Procedure) obj37;
                    Object obj38 = objArr[1];
                    int length17 = objArr.length - 2;
                    Object[] objArr17 = new Object[length17];
                    while (true) {
                        length17--;
                        if (length17 < 0) {
                            return any$V(procedure15, obj38, objArr17);
                        }
                        objArr17[length17] = objArr[length17 + 2];
                    }
                } catch (ClassCastException e15) {
                    throw new WrongType(e15, "any", 1, obj37);
                }
            case 167:
                Object obj39 = objArr[0];
                try {
                    Procedure procedure16 = (Procedure) obj39;
                    Object obj40 = objArr[1];
                    int length18 = objArr.length - 2;
                    Object[] objArr18 = new Object[length18];
                    while (true) {
                        length18--;
                        if (length18 < 0) {
                            return every$V(procedure16, obj40, objArr18);
                        }
                        objArr18[length18] = objArr[length18 + 2];
                    }
                } catch (ClassCastException e16) {
                    throw new WrongType(e16, "every", 1, obj39);
                }
            case 168:
                Object obj41 = objArr[0];
                try {
                    Procedure procedure17 = (Procedure) obj41;
                    Object obj42 = objArr[1];
                    int length19 = objArr.length - 2;
                    Object[] objArr19 = new Object[length19];
                    while (true) {
                        length19--;
                        if (length19 < 0) {
                            return listIndex$V(procedure17, obj42, objArr19);
                        }
                        objArr19[length19] = objArr[length19 + 2];
                    }
                } catch (ClassCastException e17) {
                    throw new WrongType(e17, "list-index", 1, obj41);
                }
            case 169:
                Object obj43 = objArr[0];
                try {
                    Procedure procedure18 = (Procedure) obj43;
                    int length20 = objArr.length - 1;
                    Object[] objArr20 = new Object[length20];
                    while (true) {
                        length20--;
                        if (length20 < 0) {
                            return lset$Ls$Eq$V(procedure18, objArr20);
                        }
                        objArr20[length20] = objArr[length20 + 1];
                    }
                } catch (ClassCastException e18) {
                    throw new WrongType(e18, "lset<=", 1, obj43);
                }
            case 170:
                Object obj44 = objArr[0];
                try {
                    Procedure procedure19 = (Procedure) obj44;
                    int length21 = objArr.length - 1;
                    Object[] objArr21 = new Object[length21];
                    while (true) {
                        length21--;
                        if (length21 < 0) {
                            return lset$Eq$V(procedure19, objArr21);
                        }
                        objArr21[length21] = objArr[length21 + 1];
                    }
                } catch (ClassCastException e19) {
                    throw new WrongType(e19, "lset=", 1, obj44);
                }
            case 171:
                Object obj45 = objArr[0];
                try {
                    Procedure procedure20 = (Procedure) obj45;
                    Object obj46 = objArr[1];
                    int length22 = objArr.length - 2;
                    Object[] objArr22 = new Object[length22];
                    while (true) {
                        length22--;
                        if (length22 < 0) {
                            return lsetAdjoin$V(procedure20, obj46, objArr22);
                        }
                        objArr22[length22] = objArr[length22 + 2];
                    }
                } catch (ClassCastException e20) {
                    throw new WrongType(e20, "lset-adjoin", 1, obj45);
                }
            case 172:
                Object obj47 = objArr[0];
                try {
                    Procedure procedure21 = (Procedure) obj47;
                    int length23 = objArr.length - 1;
                    Object[] objArr23 = new Object[length23];
                    while (true) {
                        length23--;
                        if (length23 < 0) {
                            return lsetUnion$V(procedure21, objArr23);
                        }
                        objArr23[length23] = objArr[length23 + 1];
                    }
                } catch (ClassCastException e21) {
                    throw new WrongType(e21, "lset-union", 1, obj47);
                }
            case 173:
                Object obj48 = objArr[0];
                try {
                    Procedure procedure22 = (Procedure) obj48;
                    int length24 = objArr.length - 1;
                    Object[] objArr24 = new Object[length24];
                    while (true) {
                        length24--;
                        if (length24 < 0) {
                            return lsetUnion$Ex$V(procedure22, objArr24);
                        }
                        objArr24[length24] = objArr[length24 + 1];
                    }
                } catch (ClassCastException e22) {
                    throw new WrongType(e22, "lset-union!", 1, obj48);
                }
            case 174:
                Object obj49 = objArr[0];
                try {
                    Procedure procedure23 = (Procedure) obj49;
                    Object obj50 = objArr[1];
                    int length25 = objArr.length - 2;
                    Object[] objArr25 = new Object[length25];
                    while (true) {
                        length25--;
                        if (length25 < 0) {
                            return lsetIntersection$V(procedure23, obj50, objArr25);
                        }
                        objArr25[length25] = objArr[length25 + 2];
                    }
                } catch (ClassCastException e23) {
                    throw new WrongType(e23, "lset-intersection", 1, obj49);
                }
            case 175:
                Object obj51 = objArr[0];
                try {
                    Procedure procedure24 = (Procedure) obj51;
                    Object obj52 = objArr[1];
                    int length26 = objArr.length - 2;
                    Object[] objArr26 = new Object[length26];
                    while (true) {
                        length26--;
                        if (length26 < 0) {
                            return lsetIntersection$Ex$V(procedure24, obj52, objArr26);
                        }
                        objArr26[length26] = objArr[length26 + 2];
                    }
                } catch (ClassCastException e24) {
                    throw new WrongType(e24, "lset-intersection!", 1, obj51);
                }
            case 176:
                Object obj53 = objArr[0];
                try {
                    Procedure procedure25 = (Procedure) obj53;
                    Object obj54 = objArr[1];
                    int length27 = objArr.length - 2;
                    Object[] objArr27 = new Object[length27];
                    while (true) {
                        length27--;
                        if (length27 < 0) {
                            return lsetDifference$V(procedure25, obj54, objArr27);
                        }
                        objArr27[length27] = objArr[length27 + 2];
                    }
                } catch (ClassCastException e25) {
                    throw new WrongType(e25, "lset-difference", 1, obj53);
                }
            case 177:
                Object obj55 = objArr[0];
                try {
                    Procedure procedure26 = (Procedure) obj55;
                    Object obj56 = objArr[1];
                    int length28 = objArr.length - 2;
                    Object[] objArr28 = new Object[length28];
                    while (true) {
                        length28--;
                        if (length28 < 0) {
                            return lsetDifference$Ex$V(procedure26, obj56, objArr28);
                        }
                        objArr28[length28] = objArr[length28 + 2];
                    }
                } catch (ClassCastException e26) {
                    throw new WrongType(e26, "lset-difference!", 1, obj55);
                }
            case 178:
                Object obj57 = objArr[0];
                try {
                    Procedure procedure27 = (Procedure) obj57;
                    int length29 = objArr.length - 1;
                    Object[] objArr29 = new Object[length29];
                    while (true) {
                        length29--;
                        if (length29 < 0) {
                            return lsetXor$V(procedure27, objArr29);
                        }
                        objArr29[length29] = objArr[length29 + 1];
                    }
                } catch (ClassCastException e27) {
                    throw new WrongType(e27, "lset-xor", 1, obj57);
                }
            case 179:
                Object obj58 = objArr[0];
                try {
                    Procedure procedure28 = (Procedure) obj58;
                    int length30 = objArr.length - 1;
                    Object[] objArr30 = new Object[length30];
                    while (true) {
                        length30--;
                        if (length30 < 0) {
                            return lsetXor$Ex$V(procedure28, objArr30);
                        }
                        objArr30[length30] = objArr[length30 + 1];
                    }
                } catch (ClassCastException e28) {
                    throw new WrongType(e28, "lset-xor!", 1, obj58);
                }
            case 180:
                Object obj59 = objArr[0];
                try {
                    Procedure procedure29 = (Procedure) obj59;
                    Object obj60 = objArr[1];
                    int length31 = objArr.length - 2;
                    Object[] objArr31 = new Object[length31];
                    while (true) {
                        length31--;
                        if (length31 < 0) {
                            return lsetDiff$PlIntersection$V(procedure29, obj60, objArr31);
                        }
                        objArr31[length31] = objArr[length31 + 2];
                    }
                } catch (ClassCastException e29) {
                    throw new WrongType(e29, "lset-diff+intersection", 1, obj59);
                }
            case 181:
                Object obj61 = objArr[0];
                try {
                    Procedure procedure30 = (Procedure) obj61;
                    Object obj62 = objArr[1];
                    int length32 = objArr.length - 2;
                    Object[] objArr32 = new Object[length32];
                    while (true) {
                        length32--;
                        if (length32 < 0) {
                            return lsetDiff$PlIntersection$Ex$V(procedure30, obj62, objArr32);
                        }
                        objArr32[length32] = objArr[length32 + 2];
                    }
                } catch (ClassCastException e30) {
                    throw new WrongType(e30, "lset-diff+intersection!", 1, obj61);
                }
            default:
                return super.applyN(moduleMethod, objArr);
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame53 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn55;
        LList lists;

        public frame53() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 56, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1587");
            this.lambda$Fn55 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 56 ? lambda72(obj) ? Boolean.TRUE : Boolean.FALSE : super.apply1(moduleMethod, obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        boolean lambda72(Object elt) {
            frame54 frame54Var = new frame54();
            frame54Var.staticLink = this;
            frame54Var.elt = elt;
            return ((srfi1.any$V(frame54Var.lambda$Fn56, this.lists, new Object[0]) != Boolean.FALSE ? 1 : 0) + 1) & 1;
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 56) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    /* loaded from: classes2.dex */
    public class frame54 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn56;
        frame53 staticLink;

        public frame54() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 55, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1588");
            this.lambda$Fn56 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 55 ? lambda73(obj) : super.apply1(moduleMethod, obj);
        }

        Object lambda73(Object lis) {
            return lists.member(this.elt, lis, this.staticLink.$Eq);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 55) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }
}
