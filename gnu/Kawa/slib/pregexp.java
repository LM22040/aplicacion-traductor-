package gnu.kawa.slib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.IsEqv;
import gnu.kawa.functions.NumberCompare;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.ports;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.append;

/* compiled from: pregexp.scm */
/* loaded from: classes2.dex */
public class pregexp extends ModuleBody {
    public static Char $Stpregexp$Mncomment$Mnchar$St;
    public static Object $Stpregexp$Mnnul$Mnchar$Mnint$St;
    public static Object $Stpregexp$Mnreturn$Mnchar$St;
    public static Object $Stpregexp$Mnspace$Mnsensitive$Qu$St;
    public static Object $Stpregexp$Mntab$Mnchar$St;
    public static IntNum $Stpregexp$Mnversion$St;
    public static final pregexp $instance;
    static final IntNum Lit0;
    static final Char Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit100;
    static final SimpleSymbol Lit101;
    static final SimpleSymbol Lit102;
    static final SimpleSymbol Lit103;
    static final SimpleSymbol Lit104;
    static final SimpleSymbol Lit105;
    static final PairWithPosition Lit106;
    static final SimpleSymbol Lit107;
    static final PairWithPosition Lit108;
    static final SimpleSymbol Lit109;
    static final Char Lit11;
    static final SimpleSymbol Lit110;
    static final SimpleSymbol Lit111;
    static final SimpleSymbol Lit112;
    static final Char Lit113;
    static final SimpleSymbol Lit114;
    static final SimpleSymbol Lit115;
    static final PairWithPosition Lit116;
    static final SimpleSymbol Lit117;
    static final SimpleSymbol Lit118;
    static final SimpleSymbol Lit119;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit120;
    static final SimpleSymbol Lit121;
    static final SimpleSymbol Lit122;
    static final SimpleSymbol Lit123;
    static final SimpleSymbol Lit124;
    static final SimpleSymbol Lit125;
    static final SimpleSymbol Lit126;
    static final SimpleSymbol Lit127;
    static final SimpleSymbol Lit128;
    static final SimpleSymbol Lit129;
    static final Char Lit13;
    static final SimpleSymbol Lit130;
    static final SimpleSymbol Lit131;
    static final SimpleSymbol Lit132;
    static final SimpleSymbol Lit133;
    static final SimpleSymbol Lit134;
    static final SimpleSymbol Lit135;
    static final SimpleSymbol Lit14;
    static final Char Lit15;
    static final IntNum Lit16;
    static final SimpleSymbol Lit17;
    static final Char Lit18;
    static final Char Lit19;
    static final Char Lit2;
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final Char Lit24;
    static final Char Lit25;
    static final SimpleSymbol Lit26;
    static final Char Lit27;
    static final SimpleSymbol Lit28;
    static final Char Lit29;
    static final Char Lit3;
    static final SimpleSymbol Lit30;
    static final Char Lit31;
    static final PairWithPosition Lit32;
    static final Char Lit33;
    static final Char Lit34;
    static final Char Lit35;
    static final SimpleSymbol Lit36;
    static final Char Lit37;
    static final PairWithPosition Lit38;
    static final Char Lit39;
    static final SimpleSymbol Lit4;
    static final Char Lit40;
    static final SimpleSymbol Lit41;
    static final Char Lit42;
    static final PairWithPosition Lit43;
    static final Char Lit44;
    static final SimpleSymbol Lit45;
    static final Char Lit46;
    static final Char Lit47;
    static final Char Lit48;
    static final PairWithPosition Lit49;
    static final SimpleSymbol Lit5;
    static final Char Lit50;
    static final PairWithPosition Lit51;
    static final Char Lit52;
    static final PairWithPosition Lit53;
    static final Char Lit54;
    static final PairWithPosition Lit55;
    static final PairWithPosition Lit56;
    static final SimpleSymbol Lit57;
    static final Char Lit58;
    static final Char Lit59;
    static final Char Lit6;
    static final SimpleSymbol Lit60;
    static final SimpleSymbol Lit61;
    static final Char Lit62;
    static final PairWithPosition Lit63;
    static final SimpleSymbol Lit64;
    static final Char Lit65;
    static final Char Lit66;
    static final Char Lit67;
    static final SimpleSymbol Lit68;
    static final SimpleSymbol Lit69;
    static final Char Lit7;
    static final SimpleSymbol Lit70;
    static final SimpleSymbol Lit71;
    static final SimpleSymbol Lit72;
    static final IntNum Lit73;
    static final SimpleSymbol Lit74;
    static final SimpleSymbol Lit75;
    static final SimpleSymbol Lit76;
    static final Char Lit77;
    static final Char Lit78;
    static final SimpleSymbol Lit79;
    static final IntNum Lit8;
    static final SimpleSymbol Lit80;
    static final SimpleSymbol Lit81;
    static final SimpleSymbol Lit82;
    static final SimpleSymbol Lit83;
    static final Char Lit84;
    static final SimpleSymbol Lit85;
    static final SimpleSymbol Lit86;
    static final SimpleSymbol Lit87;
    static final SimpleSymbol Lit88;
    static final SimpleSymbol Lit89;
    static final Char Lit9;
    static final SimpleSymbol Lit90;
    static final SimpleSymbol Lit91;
    static final SimpleSymbol Lit92;
    static final SimpleSymbol Lit93;
    static final SimpleSymbol Lit94;
    static final SimpleSymbol Lit95;
    static final Char Lit96;
    static final Char Lit97;
    static final Char Lit98;
    static final SimpleSymbol Lit99;
    static final ModuleMethod lambda$Fn1;
    static final ModuleMethod lambda$Fn10;
    static final ModuleMethod lambda$Fn6;
    static final ModuleMethod lambda$Fn7;
    static final ModuleMethod lambda$Fn8;
    static final ModuleMethod lambda$Fn9;
    public static final ModuleMethod pregexp;
    public static final ModuleMethod pregexp$Mnat$Mnword$Mnboundary$Qu;
    public static final ModuleMethod pregexp$Mnchar$Mnword$Qu;
    public static final ModuleMethod pregexp$Mncheck$Mnif$Mnin$Mnchar$Mnclass$Qu;
    public static final ModuleMethod pregexp$Mnerror;
    public static final ModuleMethod pregexp$Mninvert$Mnchar$Mnlist;
    public static final ModuleMethod pregexp$Mnlist$Mnref;
    public static final ModuleMethod pregexp$Mnmake$Mnbackref$Mnlist;
    public static final ModuleMethod pregexp$Mnmatch;
    public static final ModuleMethod pregexp$Mnmatch$Mnpositions;
    public static final ModuleMethod pregexp$Mnmatch$Mnpositions$Mnaux;
    public static final ModuleMethod pregexp$Mnquote;
    public static final ModuleMethod pregexp$Mnread$Mnbranch;
    public static final ModuleMethod pregexp$Mnread$Mnchar$Mnlist;
    public static final ModuleMethod pregexp$Mnread$Mncluster$Mntype;
    public static final ModuleMethod pregexp$Mnread$Mnescaped$Mnchar;
    public static final ModuleMethod pregexp$Mnread$Mnescaped$Mnnumber;
    public static final ModuleMethod pregexp$Mnread$Mnnums;
    public static final ModuleMethod pregexp$Mnread$Mnpattern;
    public static final ModuleMethod pregexp$Mnread$Mnpiece;
    public static final ModuleMethod pregexp$Mnread$Mnposix$Mnchar$Mnclass;
    public static final ModuleMethod pregexp$Mnread$Mnsubpattern;
    public static final ModuleMethod pregexp$Mnreplace;
    public static final ModuleMethod pregexp$Mnreplace$Mnaux;
    public static final ModuleMethod pregexp$Mnreplace$St;
    public static final ModuleMethod pregexp$Mnreverse$Ex;
    public static final ModuleMethod pregexp$Mnsplit;
    public static final ModuleMethod pregexp$Mnstring$Mnmatch;
    public static final ModuleMethod pregexp$Mnwrap$Mnquantifier$Mnif$Mnany;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("pregexp-quote").readResolve();
        Lit135 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("pregexp-replace*").readResolve();
        Lit134 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("pregexp-replace").readResolve();
        Lit133 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("pregexp-split").readResolve();
        Lit132 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("pregexp-match").readResolve();
        Lit131 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("pregexp").readResolve();
        Lit130 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("pregexp-replace-aux").readResolve();
        Lit129 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("pregexp-make-backref-list").readResolve();
        Lit128 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("pregexp-list-ref").readResolve();
        Lit127 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("pregexp-at-word-boundary?").readResolve();
        Lit126 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("pregexp-char-word?").readResolve();
        Lit125 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("pregexp-string-match").readResolve();
        Lit124 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("pregexp-invert-char-list").readResolve();
        Lit123 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("pregexp-read-escaped-char").readResolve();
        Lit122 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("pregexp-read-escaped-number").readResolve();
        Lit121 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = (SimpleSymbol) new SimpleSymbol("pregexp-read-branch").readResolve();
        Lit120 = simpleSymbol16;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("pregexp-read-pattern").readResolve();
        Lit119 = simpleSymbol17;
        SimpleSymbol simpleSymbol18 = (SimpleSymbol) new SimpleSymbol("pregexp-error").readResolve();
        Lit118 = simpleSymbol18;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("pregexp-reverse!").readResolve();
        Lit117 = simpleSymbol19;
        Char make = Char.make(92);
        Lit19 = make;
        Char make2 = Char.make(46);
        Lit13 = make2;
        Char make3 = Char.make(63);
        Lit47 = make3;
        Char make4 = Char.make(42);
        Lit65 = make4;
        Char make5 = Char.make(43);
        Lit66 = make5;
        Char make6 = Char.make(124);
        Lit7 = make6;
        Char make7 = Char.make(94);
        Lit9 = make7;
        Char make8 = Char.make(36);
        Lit11 = make8;
        Char make9 = Char.make(91);
        Lit15 = make9;
        Char make10 = Char.make(93);
        Lit46 = make10;
        Char make11 = Char.make(123);
        Lit67 = make11;
        Char make12 = Char.make(125);
        Lit78 = make12;
        Char make13 = Char.make(40);
        Lit18 = make13;
        Char make14 = Char.make(41);
        Lit6 = make14;
        Lit116 = PairWithPosition.make(make, PairWithPosition.make(make2, PairWithPosition.make(make3, PairWithPosition.make(make4, PairWithPosition.make(make5, PairWithPosition.make(make6, PairWithPosition.make(make7, PairWithPosition.make(make8, PairWithPosition.make(make9, PairWithPosition.make(make10, PairWithPosition.make(make11, PairWithPosition.make(make12, PairWithPosition.make(make13, PairWithPosition.make(make14, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3153977), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3153973), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3153969), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3153965), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3153961), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3153957), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149885), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149881), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149877), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149873), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149869), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149865), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149861), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149856);
        Lit115 = (SimpleSymbol) new SimpleSymbol("pattern-must-be-compiled-or-string-regexp").readResolve();
        SimpleSymbol simpleSymbol20 = (SimpleSymbol) new SimpleSymbol("pregexp-match-positions").readResolve();
        Lit114 = simpleSymbol20;
        Lit113 = Char.make(38);
        Lit112 = (SimpleSymbol) new SimpleSymbol("identity").readResolve();
        Lit111 = (SimpleSymbol) new SimpleSymbol("fk").readResolve();
        Lit110 = (SimpleSymbol) new SimpleSymbol("greedy-quantifier-operand-could-be-empty").readResolve();
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol(":no-backtrack").readResolve();
        Lit109 = simpleSymbol21;
        SimpleSymbol simpleSymbol22 = (SimpleSymbol) new SimpleSymbol(":between").readResolve();
        Lit68 = simpleSymbol22;
        Boolean bool = Boolean.FALSE;
        IntNum make15 = IntNum.make(0);
        Lit73 = make15;
        Boolean bool2 = Boolean.FALSE;
        SimpleSymbol simpleSymbol23 = (SimpleSymbol) new SimpleSymbol(":any").readResolve();
        Lit14 = simpleSymbol23;
        Lit108 = PairWithPosition.make(simpleSymbol22, PairWithPosition.make(bool, PairWithPosition.make(make15, PairWithPosition.make(bool2, PairWithPosition.make(simpleSymbol23, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2338881), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2338878), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2338876), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2338873), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2338863);
        SimpleSymbol simpleSymbol24 = (SimpleSymbol) new SimpleSymbol(":neg-lookbehind").readResolve();
        Lit107 = simpleSymbol24;
        Lit106 = PairWithPosition.make(simpleSymbol22, PairWithPosition.make(Boolean.FALSE, PairWithPosition.make(make15, PairWithPosition.make(Boolean.FALSE, PairWithPosition.make(simpleSymbol23, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2302017), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2302014), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2302012), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2302009), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2301999);
        SimpleSymbol simpleSymbol25 = (SimpleSymbol) new SimpleSymbol(":lookbehind").readResolve();
        Lit105 = simpleSymbol25;
        SimpleSymbol simpleSymbol26 = (SimpleSymbol) new SimpleSymbol(":neg-lookahead").readResolve();
        Lit104 = simpleSymbol26;
        SimpleSymbol simpleSymbol27 = (SimpleSymbol) new SimpleSymbol(":lookahead").readResolve();
        Lit103 = simpleSymbol27;
        Lit102 = (SimpleSymbol) new SimpleSymbol("non-existent-backref").readResolve();
        SimpleSymbol simpleSymbol28 = (SimpleSymbol) new SimpleSymbol("pregexp-match-positions-aux").readResolve();
        Lit101 = simpleSymbol28;
        SimpleSymbol simpleSymbol29 = (SimpleSymbol) new SimpleSymbol(":sub").readResolve();
        Lit100 = simpleSymbol29;
        SimpleSymbol simpleSymbol30 = (SimpleSymbol) new SimpleSymbol("pregexp-check-if-in-char-class?").readResolve();
        Lit99 = simpleSymbol30;
        Lit98 = Char.make(102);
        Lit97 = Char.make(101);
        Lit96 = Char.make(99);
        Lit95 = (SimpleSymbol) new SimpleSymbol(":xdigit").readResolve();
        Lit94 = (SimpleSymbol) new SimpleSymbol(":upper").readResolve();
        Lit93 = (SimpleSymbol) new SimpleSymbol(":punct").readResolve();
        Lit92 = (SimpleSymbol) new SimpleSymbol(":print").readResolve();
        Lit91 = (SimpleSymbol) new SimpleSymbol(":lower").readResolve();
        Lit90 = (SimpleSymbol) new SimpleSymbol(":graph").readResolve();
        Lit89 = (SimpleSymbol) new SimpleSymbol(":cntrl").readResolve();
        Lit88 = (SimpleSymbol) new SimpleSymbol(":blank").readResolve();
        Lit87 = (SimpleSymbol) new SimpleSymbol(":ascii").readResolve();
        Lit86 = (SimpleSymbol) new SimpleSymbol(":alpha").readResolve();
        Lit85 = (SimpleSymbol) new SimpleSymbol(":alnum").readResolve();
        Lit84 = Char.make(95);
        Lit83 = (SimpleSymbol) new SimpleSymbol(":char-range").readResolve();
        Lit82 = (SimpleSymbol) new SimpleSymbol(":one-of-chars").readResolve();
        Lit81 = (SimpleSymbol) new SimpleSymbol("character-class-ended-too-soon").readResolve();
        SimpleSymbol simpleSymbol31 = (SimpleSymbol) new SimpleSymbol("pregexp-read-char-list").readResolve();
        Lit80 = simpleSymbol31;
        Lit79 = (SimpleSymbol) new SimpleSymbol(":none-of-chars").readResolve();
        Lit77 = Char.make(44);
        SimpleSymbol simpleSymbol32 = (SimpleSymbol) new SimpleSymbol("pregexp-read-nums").readResolve();
        Lit76 = simpleSymbol32;
        Lit75 = (SimpleSymbol) new SimpleSymbol("left-brace-must-be-followed-by-number").readResolve();
        SimpleSymbol simpleSymbol33 = (SimpleSymbol) new SimpleSymbol("pregexp-wrap-quantifier-if-any").readResolve();
        Lit74 = simpleSymbol33;
        Lit72 = (SimpleSymbol) new SimpleSymbol("next-i").readResolve();
        Lit71 = (SimpleSymbol) new SimpleSymbol("at-most").readResolve();
        Lit70 = (SimpleSymbol) new SimpleSymbol("at-least").readResolve();
        Lit69 = (SimpleSymbol) new SimpleSymbol("minimal?").readResolve();
        SimpleSymbol simpleSymbol34 = (SimpleSymbol) new SimpleSymbol("pregexp-read-subpattern").readResolve();
        Lit64 = simpleSymbol34;
        Lit63 = PairWithPosition.make(simpleSymbol29, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 942102);
        Lit62 = Char.make(120);
        Lit61 = (SimpleSymbol) new SimpleSymbol(":case-insensitive").readResolve();
        Lit60 = (SimpleSymbol) new SimpleSymbol(":case-sensitive").readResolve();
        Lit59 = Char.make(105);
        Lit58 = Char.make(45);
        SimpleSymbol simpleSymbol35 = (SimpleSymbol) new SimpleSymbol("pregexp-read-cluster-type").readResolve();
        Lit57 = simpleSymbol35;
        Lit56 = PairWithPosition.make(simpleSymbol24, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 876575);
        Lit55 = PairWithPosition.make(simpleSymbol25, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 872479);
        Lit54 = Char.make(60);
        Lit53 = PairWithPosition.make(simpleSymbol21, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 860188);
        Lit52 = Char.make(62);
        Lit51 = PairWithPosition.make(simpleSymbol26, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 856092);
        Lit50 = Char.make(33);
        Lit49 = PairWithPosition.make(simpleSymbol27, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 851996);
        Lit48 = Char.make(61);
        SimpleSymbol simpleSymbol36 = (SimpleSymbol) new SimpleSymbol("pregexp-read-posix-char-class").readResolve();
        Lit45 = simpleSymbol36;
        Lit44 = Char.make(58);
        SimpleSymbol simpleSymbol37 = (SimpleSymbol) new SimpleSymbol(":neg-char").readResolve();
        Lit17 = simpleSymbol37;
        SimpleSymbol simpleSymbol38 = (SimpleSymbol) new SimpleSymbol(":word").readResolve();
        Lit41 = simpleSymbol38;
        Lit43 = PairWithPosition.make(simpleSymbol37, PairWithPosition.make(simpleSymbol38, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 696359), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 696348);
        Lit42 = Char.make(87);
        Lit40 = Char.make(119);
        Lit39 = Char.make(116);
        SimpleSymbol simpleSymbol39 = (SimpleSymbol) new SimpleSymbol(":space").readResolve();
        Lit36 = simpleSymbol39;
        Lit38 = PairWithPosition.make(simpleSymbol37, PairWithPosition.make(simpleSymbol39, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 684071), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 684060);
        Lit37 = Char.make(83);
        Lit35 = Char.make(115);
        Lit34 = Char.make(114);
        Lit33 = Char.make(110);
        SimpleSymbol simpleSymbol40 = (SimpleSymbol) new SimpleSymbol(":digit").readResolve();
        Lit30 = simpleSymbol40;
        Lit32 = PairWithPosition.make(simpleSymbol37, PairWithPosition.make(simpleSymbol40, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 667687), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 667676);
        Lit31 = Char.make(68);
        Lit29 = Char.make(100);
        Lit28 = (SimpleSymbol) new SimpleSymbol(":not-wbdry").readResolve();
        Lit27 = Char.make(66);
        Lit26 = (SimpleSymbol) new SimpleSymbol(":wbdry").readResolve();
        Lit25 = Char.make(98);
        Lit24 = Char.make(10);
        Lit23 = (SimpleSymbol) new SimpleSymbol(":empty").readResolve();
        Lit22 = (SimpleSymbol) new SimpleSymbol("backslash").readResolve();
        SimpleSymbol simpleSymbol41 = (SimpleSymbol) new SimpleSymbol("pregexp-read-piece").readResolve();
        Lit21 = simpleSymbol41;
        Lit20 = (SimpleSymbol) new SimpleSymbol(":backref").readResolve();
        Lit16 = IntNum.make(2);
        Lit12 = (SimpleSymbol) new SimpleSymbol(":eos").readResolve();
        Lit10 = (SimpleSymbol) new SimpleSymbol(":bos").readResolve();
        Lit8 = IntNum.make(1);
        Lit5 = (SimpleSymbol) new SimpleSymbol(":seq").readResolve();
        Lit4 = (SimpleSymbol) new SimpleSymbol(":or").readResolve();
        Lit3 = Char.make(32);
        Lit2 = Char.make(97);
        Lit1 = Char.make(59);
        Lit0 = IntNum.make(20050502);
        pregexp pregexpVar = new pregexp();
        $instance = pregexpVar;
        ModuleMethod moduleMethod = new ModuleMethod(pregexpVar, 16, simpleSymbol19, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:47");
        pregexp$Mnreverse$Ex = moduleMethod;
        ModuleMethod moduleMethod2 = new ModuleMethod(pregexpVar, 17, simpleSymbol18, -4096);
        moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:57");
        pregexp$Mnerror = moduleMethod2;
        ModuleMethod moduleMethod3 = new ModuleMethod(pregexpVar, 18, simpleSymbol17, 12291);
        moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:65");
        pregexp$Mnread$Mnpattern = moduleMethod3;
        ModuleMethod moduleMethod4 = new ModuleMethod(pregexpVar, 19, simpleSymbol16, 12291);
        moduleMethod4.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:79");
        pregexp$Mnread$Mnbranch = moduleMethod4;
        ModuleMethod moduleMethod5 = new ModuleMethod(pregexpVar, 20, simpleSymbol41, 12291);
        moduleMethod5.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:91");
        pregexp$Mnread$Mnpiece = moduleMethod5;
        ModuleMethod moduleMethod6 = new ModuleMethod(pregexpVar, 21, simpleSymbol15, 12291);
        moduleMethod6.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:138");
        pregexp$Mnread$Mnescaped$Mnnumber = moduleMethod6;
        ModuleMethod moduleMethod7 = new ModuleMethod(pregexpVar, 22, simpleSymbol14, 12291);
        moduleMethod7.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:155");
        pregexp$Mnread$Mnescaped$Mnchar = moduleMethod7;
        ModuleMethod moduleMethod8 = new ModuleMethod(pregexpVar, 23, simpleSymbol36, 12291);
        moduleMethod8.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:174");
        pregexp$Mnread$Mnposix$Mnchar$Mnclass = moduleMethod8;
        ModuleMethod moduleMethod9 = new ModuleMethod(pregexpVar, 24, simpleSymbol35, 12291);
        moduleMethod9.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:200");
        pregexp$Mnread$Mncluster$Mntype = moduleMethod9;
        ModuleMethod moduleMethod10 = new ModuleMethod(pregexpVar, 25, simpleSymbol34, 12291);
        moduleMethod10.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:233");
        pregexp$Mnread$Mnsubpattern = moduleMethod10;
        ModuleMethod moduleMethod11 = new ModuleMethod(pregexpVar, 26, simpleSymbol33, 12291);
        moduleMethod11.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:254");
        pregexp$Mnwrap$Mnquantifier$Mnif$Mnany = moduleMethod11;
        ModuleMethod moduleMethod12 = new ModuleMethod(pregexpVar, 27, simpleSymbol32, 12291);
        moduleMethod12.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:300");
        pregexp$Mnread$Mnnums = moduleMethod12;
        ModuleMethod moduleMethod13 = new ModuleMethod(pregexpVar, 28, simpleSymbol13, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod13.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:323");
        pregexp$Mninvert$Mnchar$Mnlist = moduleMethod13;
        ModuleMethod moduleMethod14 = new ModuleMethod(pregexpVar, 29, simpleSymbol31, 12291);
        moduleMethod14.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:330");
        pregexp$Mnread$Mnchar$Mnlist = moduleMethod14;
        ModuleMethod moduleMethod15 = new ModuleMethod(pregexpVar, 30, simpleSymbol12, 24582);
        moduleMethod15.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:368");
        pregexp$Mnstring$Mnmatch = moduleMethod15;
        ModuleMethod moduleMethod16 = new ModuleMethod(pregexpVar, 31, simpleSymbol11, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod16.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:379");
        pregexp$Mnchar$Mnword$Qu = moduleMethod16;
        ModuleMethod moduleMethod17 = new ModuleMethod(pregexpVar, 32, simpleSymbol10, 12291);
        moduleMethod17.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:387");
        pregexp$Mnat$Mnword$Mnboundary$Qu = moduleMethod17;
        ModuleMethod moduleMethod18 = new ModuleMethod(pregexpVar, 33, simpleSymbol30, 8194);
        moduleMethod18.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:399");
        pregexp$Mncheck$Mnif$Mnin$Mnchar$Mnclass$Qu = moduleMethod18;
        ModuleMethod moduleMethod19 = new ModuleMethod(pregexpVar, 34, simpleSymbol9, 8194);
        moduleMethod19.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:429");
        pregexp$Mnlist$Mnref = moduleMethod19;
        ModuleMethod moduleMethod20 = new ModuleMethod(pregexpVar, 35, simpleSymbol8, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod20.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:448");
        pregexp$Mnmake$Mnbackref$Mnlist = moduleMethod20;
        ModuleMethod moduleMethod21 = new ModuleMethod(pregexpVar, 36, null, 0);
        moduleMethod21.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:463");
        lambda$Fn1 = moduleMethod21;
        ModuleMethod moduleMethod22 = new ModuleMethod(pregexpVar, 37, null, 0);
        moduleMethod22.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:551");
        lambda$Fn6 = moduleMethod22;
        ModuleMethod moduleMethod23 = new ModuleMethod(pregexpVar, 38, null, 0);
        moduleMethod23.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:556");
        lambda$Fn7 = moduleMethod23;
        ModuleMethod moduleMethod24 = new ModuleMethod(pregexpVar, 39, null, 0);
        moduleMethod24.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:564");
        lambda$Fn8 = moduleMethod24;
        ModuleMethod moduleMethod25 = new ModuleMethod(pregexpVar, 40, null, 0);
        moduleMethod25.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:573");
        lambda$Fn9 = moduleMethod25;
        ModuleMethod moduleMethod26 = new ModuleMethod(pregexpVar, 41, null, 0);
        moduleMethod26.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:578");
        lambda$Fn10 = moduleMethod26;
        ModuleMethod moduleMethod27 = new ModuleMethod(pregexpVar, 42, simpleSymbol28, 24582);
        moduleMethod27.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:459");
        pregexp$Mnmatch$Mnpositions$Mnaux = moduleMethod27;
        ModuleMethod moduleMethod28 = new ModuleMethod(pregexpVar, 43, simpleSymbol7, 16388);
        moduleMethod28.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:639");
        pregexp$Mnreplace$Mnaux = moduleMethod28;
        ModuleMethod moduleMethod29 = new ModuleMethod(pregexpVar, 44, simpleSymbol6, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod29.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:665");
        pregexp = moduleMethod29;
        ModuleMethod moduleMethod30 = new ModuleMethod(pregexpVar, 45, simpleSymbol20, -4094);
        moduleMethod30.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:670");
        pregexp$Mnmatch$Mnpositions = moduleMethod30;
        ModuleMethod moduleMethod31 = new ModuleMethod(pregexpVar, 46, simpleSymbol5, -4094);
        moduleMethod31.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:690");
        pregexp$Mnmatch = moduleMethod31;
        ModuleMethod moduleMethod32 = new ModuleMethod(pregexpVar, 47, simpleSymbol4, 8194);
        moduleMethod32.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:700");
        pregexp$Mnsplit = moduleMethod32;
        ModuleMethod moduleMethod33 = new ModuleMethod(pregexpVar, 48, simpleSymbol3, 12291);
        moduleMethod33.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:723");
        pregexp$Mnreplace = moduleMethod33;
        ModuleMethod moduleMethod34 = new ModuleMethod(pregexpVar, 49, simpleSymbol2, 12291);
        moduleMethod34.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:736");
        pregexp$Mnreplace$St = moduleMethod34;
        ModuleMethod moduleMethod35 = new ModuleMethod(pregexpVar, 50, simpleSymbol, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod35.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:764");
        pregexp$Mnquote = moduleMethod35;
        pregexpVar.run();
    }

    public pregexp() {
        ModuleInfo.register(this);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
        $Stpregexp$Mnversion$St = Lit0;
        $Stpregexp$Mncomment$Mnchar$St = Lit1;
        Integer valueOf = Integer.valueOf(Lit2.intValue() - 97);
        $Stpregexp$Mnnul$Mnchar$Mnint$St = valueOf;
        $Stpregexp$Mnreturn$Mnchar$St = Char.make(valueOf.intValue() + 13);
        $Stpregexp$Mntab$Mnchar$St = Char.make(((Number) $Stpregexp$Mnnul$Mnchar$Mnint$St).intValue() + 9);
        $Stpregexp$Mnspace$Mnsensitive$Qu$St = Boolean.TRUE;
    }

    public static Object pregexpReverse$Ex(Object s) {
        Object r = LList.Empty;
        Object s2 = s;
        while (!lists.isNull(s2)) {
            Object d = lists.cdr.apply1(s2);
            try {
                ((Pair) s2).setCdr(r);
                r = s2;
                s2 = d;
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-cdr!", 1, s2);
            }
        }
        return r;
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 16:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 28:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 31:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 35:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 44:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 50:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod, obj, callContext);
        }
    }

    public static Object pregexpError$V(Object[] argsArray) {
        LList whatever = LList.makeList(argsArray, 0);
        ports.display("Error:");
        Object arg0 = whatever;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                Object x = arg02.getCar();
                ports.display(Lit3);
                ports.write(x);
                arg0 = arg02.getCdr();
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        ports.newline();
        return misc.error$V("pregexp-error", new Object[0]);
    }

    @Override // gnu.expr.ModuleBody
    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 17:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 30:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 42:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 45:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 46:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod, objArr, callContext);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0082 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object pregexpReadPattern(java.lang.Object r6, java.lang.Object r7, java.lang.Object r8) {
        /*
            gnu.kawa.functions.NumberCompare r0 = kawa.standard.Scheme.numGEq
            java.lang.Object r0 = r0.apply2(r7, r8)
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            if (r0 == r1) goto L1c
            gnu.mapping.SimpleSymbol r6 = gnu.kawa.slib.pregexp.Lit4
            gnu.mapping.SimpleSymbol r8 = gnu.kawa.slib.pregexp.Lit5
            gnu.lists.Pair r8 = gnu.lists.LList.list1(r8)
            gnu.lists.Pair r6 = gnu.lists.LList.list2(r6, r8)
            gnu.lists.Pair r6 = gnu.lists.LList.list2(r6, r7)
            goto L5d
        L1c:
            gnu.lists.LList r0 = gnu.lists.LList.Empty
        L1e:
            gnu.kawa.functions.NumberCompare r1 = kawa.standard.Scheme.numGEq
            java.lang.Object r1 = r1.apply2(r7, r8)
            r2 = r1
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch: java.lang.ClassCastException -> Lb3
            boolean r1 = r2.booleanValue()     // Catch: java.lang.ClassCastException -> Lb3
            r2 = 2
            r3 = 1
            java.lang.String r4 = "string-ref"
            if (r1 == 0) goto L34
            if (r1 == 0) goto L5e
        L33:
            goto L4f
        L34:
            r1 = r6
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch: java.lang.ClassCastException -> Lac
            r5 = r7
            java.lang.Number r5 = (java.lang.Number) r5     // Catch: java.lang.ClassCastException -> La5
            int r5 = r5.intValue()     // Catch: java.lang.ClassCastException -> La5
            char r1 = kawa.lib.strings.stringRef(r1, r5)
            gnu.text.Char r1 = gnu.text.Char.make(r1)
            gnu.text.Char r5 = gnu.kawa.slib.pregexp.Lit6
            boolean r1 = kawa.lib.characters.isChar$Eq(r1, r5)
            if (r1 == 0) goto L5e
            goto L33
        L4f:
            gnu.mapping.SimpleSymbol r6 = gnu.kawa.slib.pregexp.Lit4
            java.lang.Object r8 = pregexpReverse$Ex(r0)
            gnu.lists.Pair r6 = kawa.lib.lists.cons(r6, r8)
            gnu.lists.Pair r6 = gnu.lists.LList.list2(r6, r7)
        L5d:
            return r6
        L5e:
            r1 = r6
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch: java.lang.ClassCastException -> L9e
            r3 = r7
            java.lang.Number r3 = (java.lang.Number) r3     // Catch: java.lang.ClassCastException -> L97
            int r2 = r3.intValue()     // Catch: java.lang.ClassCastException -> L97
            char r1 = kawa.lib.strings.stringRef(r1, r2)
            gnu.text.Char r1 = gnu.text.Char.make(r1)
            gnu.text.Char r2 = gnu.kawa.slib.pregexp.Lit7
            boolean r1 = kawa.lib.characters.isChar$Eq(r1, r2)
            if (r1 == 0) goto L81
            gnu.kawa.functions.AddOp r1 = gnu.kawa.functions.AddOp.$Pl
            gnu.math.IntNum r2 = gnu.kawa.slib.pregexp.Lit8
            java.lang.Object r7 = r1.apply2(r7, r2)
            goto L82
        L81:
        L82:
            java.lang.Object r7 = pregexpReadBranch(r6, r7, r8)
            gnu.expr.GenericProc r1 = kawa.lib.lists.car
            java.lang.Object r1 = r1.apply1(r7)
            gnu.lists.Pair r0 = kawa.lib.lists.cons(r1, r0)
            gnu.expr.GenericProc r1 = kawa.lib.lists.cadr
            java.lang.Object r7 = r1.apply1(r7)
            goto L1e
        L97:
            r6 = move-exception
            gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
            r8.<init>(r6, r4, r2, r7)
            throw r8
        L9e:
            r7 = move-exception
            gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
            r8.<init>(r7, r4, r3, r6)
            throw r8
        La5:
            r6 = move-exception
            gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
            r8.<init>(r6, r4, r2, r7)
            throw r8
        Lac:
            r7 = move-exception
            gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
            r8.<init>(r7, r4, r3, r6)
            throw r8
        Lb3:
            r6 = move-exception
            gnu.mapping.WrongType r7 = new gnu.mapping.WrongType
            java.lang.String r8 = "x"
            r0 = -2
            r7.<init>(r6, r8, r0, r1)
            goto Lbe
        Lbd:
            throw r7
        Lbe:
            goto Lbd
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.pregexp.pregexpReadPattern(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    @Override // gnu.expr.ModuleBody
    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 18:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 19:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 20:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 21:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 22:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 23:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 24:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 25:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 26:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 27:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 29:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 32:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 48:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 49:
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

    public static Object pregexpReadBranch(Object obj, Object obj2, Object obj3) {
        Object obj4 = LList.Empty;
        while (Scheme.numGEq.apply2(obj2, obj3) == Boolean.FALSE) {
            try {
                try {
                    char stringRef = strings.stringRef((CharSequence) obj, ((Number) obj2).intValue());
                    boolean isChar$Eq = characters.isChar$Eq(Char.make(stringRef), Lit7);
                    if (isChar$Eq) {
                        if (isChar$Eq) {
                            return LList.list2(lists.cons(Lit5, pregexpReverse$Ex(obj4)), obj2);
                        }
                        Object pregexpReadPiece = pregexpReadPiece(obj, obj2, obj3);
                        obj4 = lists.cons(lists.car.apply1(pregexpReadPiece), obj4);
                        obj2 = lists.cadr.apply1(pregexpReadPiece);
                    } else {
                        if (characters.isChar$Eq(Char.make(stringRef), Lit6)) {
                            return LList.list2(lists.cons(Lit5, pregexpReverse$Ex(obj4)), obj2);
                        }
                        Object pregexpReadPiece2 = pregexpReadPiece(obj, obj2, obj3);
                        obj4 = lists.cons(lists.car.apply1(pregexpReadPiece2), obj4);
                        obj2 = lists.cadr.apply1(pregexpReadPiece2);
                    }
                } catch (ClassCastException e) {
                    throw new WrongType(e, "string-ref", 2, obj2);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "string-ref", 1, obj);
            }
        }
        return LList.list2(lists.cons(Lit5, pregexpReverse$Ex(obj4)), obj2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:116:?, code lost:
    
        return pregexpWrapQuantifierIfAny(gnu.lists.LList.list2(gnu.text.Char.make(r2), gnu.kawa.functions.AddOp.$Pl.apply2(r9, gnu.kawa.slib.pregexp.Lit8)), r8, r10);
     */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01d6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object pregexpReadPiece(java.lang.Object r8, java.lang.Object r9, java.lang.Object r10) {
        /*
            Method dump skipped, instructions count: 595
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.pregexp.pregexpReadPiece(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public static Object pregexpReadEscapedNumber(Object obj, Object obj2, Object obj3) {
        NumberCompare numberCompare = Scheme.numLss;
        AddOp addOp = AddOp.$Pl;
        IntNum intNum = Lit8;
        Object apply2 = numberCompare.apply2(addOp.apply2(obj2, intNum), obj3);
        try {
            boolean booleanValue = ((Boolean) apply2).booleanValue();
            if (!booleanValue) {
                return booleanValue ? Boolean.TRUE : Boolean.FALSE;
            }
            try {
                CharSequence charSequence = (CharSequence) obj;
                Object apply22 = AddOp.$Pl.apply2(obj2, intNum);
                try {
                    char stringRef = strings.stringRef(charSequence, ((Number) apply22).intValue());
                    boolean isCharNumeric = unicode.isCharNumeric(Char.make(stringRef));
                    if (!isCharNumeric) {
                        return isCharNumeric ? Boolean.TRUE : Boolean.FALSE;
                    }
                    Object apply23 = AddOp.$Pl.apply2(obj2, Lit16);
                    Pair list1 = LList.list1(Char.make(stringRef));
                    while (Scheme.numGEq.apply2(apply23, obj3) == Boolean.FALSE) {
                        try {
                            try {
                                char stringRef2 = strings.stringRef((CharSequence) obj, ((Number) apply23).intValue());
                                if (unicode.isCharNumeric(Char.make(stringRef2))) {
                                    apply23 = AddOp.$Pl.apply2(apply23, Lit8);
                                    list1 = lists.cons(Char.make(stringRef2), list1);
                                } else {
                                    Object pregexpReverse$Ex = pregexpReverse$Ex(list1);
                                    try {
                                        return LList.list2(numbers.string$To$Number(strings.list$To$String((LList) pregexpReverse$Ex), 10), apply23);
                                    } catch (ClassCastException e) {
                                        throw new WrongType(e, "list->string", 1, pregexpReverse$Ex);
                                    }
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "string-ref", 2, apply23);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "string-ref", 1, obj);
                        }
                    }
                    Object pregexpReverse$Ex2 = pregexpReverse$Ex(list1);
                    try {
                        return LList.list2(numbers.string$To$Number(strings.list$To$String((LList) pregexpReverse$Ex2), 10), apply23);
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "list->string", 1, pregexpReverse$Ex2);
                    }
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "string-ref", 2, apply22);
                }
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "string-ref", 1, obj);
            }
        } catch (ClassCastException e7) {
            throw new WrongType(e7, "x", -2, apply2);
        }
    }

    public static Object pregexpReadEscapedChar(Object obj, Object obj2, Object obj3) {
        NumberCompare numberCompare = Scheme.numLss;
        AddOp addOp = AddOp.$Pl;
        IntNum intNum = Lit8;
        Object apply2 = numberCompare.apply2(addOp.apply2(obj2, intNum), obj3);
        try {
            boolean booleanValue = ((Boolean) apply2).booleanValue();
            if (!booleanValue) {
                return booleanValue ? Boolean.TRUE : Boolean.FALSE;
            }
            try {
                CharSequence charSequence = (CharSequence) obj;
                Object apply22 = AddOp.$Pl.apply2(obj2, intNum);
                try {
                    char stringRef = strings.stringRef(charSequence, ((Number) apply22).intValue());
                    return Scheme.isEqv.apply2(Char.make(stringRef), Lit25) != Boolean.FALSE ? LList.list2(Lit26, AddOp.$Pl.apply2(obj2, Lit16)) : Scheme.isEqv.apply2(Char.make(stringRef), Lit27) != Boolean.FALSE ? LList.list2(Lit28, AddOp.$Pl.apply2(obj2, Lit16)) : Scheme.isEqv.apply2(Char.make(stringRef), Lit29) != Boolean.FALSE ? LList.list2(Lit30, AddOp.$Pl.apply2(obj2, Lit16)) : Scheme.isEqv.apply2(Char.make(stringRef), Lit31) != Boolean.FALSE ? LList.list2(Lit32, AddOp.$Pl.apply2(obj2, Lit16)) : Scheme.isEqv.apply2(Char.make(stringRef), Lit33) != Boolean.FALSE ? LList.list2(Lit24, AddOp.$Pl.apply2(obj2, Lit16)) : Scheme.isEqv.apply2(Char.make(stringRef), Lit34) != Boolean.FALSE ? LList.list2($Stpregexp$Mnreturn$Mnchar$St, AddOp.$Pl.apply2(obj2, Lit16)) : Scheme.isEqv.apply2(Char.make(stringRef), Lit35) != Boolean.FALSE ? LList.list2(Lit36, AddOp.$Pl.apply2(obj2, Lit16)) : Scheme.isEqv.apply2(Char.make(stringRef), Lit37) != Boolean.FALSE ? LList.list2(Lit38, AddOp.$Pl.apply2(obj2, Lit16)) : Scheme.isEqv.apply2(Char.make(stringRef), Lit39) != Boolean.FALSE ? LList.list2($Stpregexp$Mntab$Mnchar$St, AddOp.$Pl.apply2(obj2, Lit16)) : Scheme.isEqv.apply2(Char.make(stringRef), Lit40) != Boolean.FALSE ? LList.list2(Lit41, AddOp.$Pl.apply2(obj2, Lit16)) : Scheme.isEqv.apply2(Char.make(stringRef), Lit42) != Boolean.FALSE ? LList.list2(Lit43, AddOp.$Pl.apply2(obj2, Lit16)) : LList.list2(Char.make(stringRef), AddOp.$Pl.apply2(obj2, Lit16));
                } catch (ClassCastException e) {
                    throw new WrongType(e, "string-ref", 2, apply22);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "string-ref", 1, obj);
            }
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "x", -2, apply2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0086, code lost:
    
        if (r11 != false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00b3, code lost:
    
        r9 = pregexpReverse$Ex(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ba, code lost:
    
        r9 = kawa.lib.misc.string$To$Symbol(kawa.lib.strings.list$To$String((gnu.lists.LList) r9));
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00c4, code lost:
    
        if (r1 == java.lang.Boolean.FALSE) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c6, code lost:
    
        r9 = gnu.lists.LList.list2(gnu.kawa.slib.pregexp.Lit17, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:?, code lost:
    
        return gnu.lists.LList.list2(r9, gnu.kawa.functions.AddOp.$Pl.apply2(r10, gnu.kawa.slib.pregexp.Lit16));
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00db, code lost:
    
        r10 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00e3, code lost:
    
        throw new gnu.mapping.WrongType(r10, "list->string", 1, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:?, code lost:
    
        return pregexpError$V(new java.lang.Object[]{gnu.kawa.slib.pregexp.Lit45});
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a6, code lost:
    
        if (kawa.lib.characters.isChar$Eq(gnu.text.Char.make(kawa.lib.strings.stringRef(r9, ((java.lang.Number) r11).intValue())), gnu.kawa.slib.pregexp.Lit46) == false) goto L27;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object pregexpReadPosixCharClass(java.lang.Object r9, java.lang.Object r10, java.lang.Object r11) {
        /*
            Method dump skipped, instructions count: 279
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.pregexp.pregexpReadPosixCharClass(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public static Object pregexpReadClusterType(Object obj, Object obj2, Object obj3) {
        char stringRef;
        try {
            try {
                if (Scheme.isEqv.apply2(Char.make(strings.stringRef((CharSequence) obj, ((Number) obj2).intValue())), Lit47) == Boolean.FALSE) {
                    return LList.list2(Lit63, obj2);
                }
                AddOp addOp = AddOp.$Pl;
                IntNum intNum = Lit8;
                Object apply2 = addOp.apply2(obj2, intNum);
                try {
                    try {
                        char stringRef2 = strings.stringRef((CharSequence) obj, ((Number) apply2).intValue());
                        if (Scheme.isEqv.apply2(Char.make(stringRef2), Lit44) != Boolean.FALSE) {
                            return LList.list2(LList.Empty, AddOp.$Pl.apply2(apply2, intNum));
                        }
                        IsEqv isEqv = Scheme.isEqv;
                        Char make = Char.make(stringRef2);
                        Char r6 = Lit48;
                        if (isEqv.apply2(make, r6) != Boolean.FALSE) {
                            return LList.list2(Lit49, AddOp.$Pl.apply2(apply2, intNum));
                        }
                        IsEqv isEqv2 = Scheme.isEqv;
                        Char make2 = Char.make(stringRef2);
                        Char r7 = Lit50;
                        if (isEqv2.apply2(make2, r7) != Boolean.FALSE) {
                            return LList.list2(Lit51, AddOp.$Pl.apply2(apply2, intNum));
                        }
                        if (Scheme.isEqv.apply2(Char.make(stringRef2), Lit52) != Boolean.FALSE) {
                            return LList.list2(Lit53, AddOp.$Pl.apply2(apply2, intNum));
                        }
                        if (Scheme.isEqv.apply2(Char.make(stringRef2), Lit54) != Boolean.FALSE) {
                            try {
                                CharSequence charSequence = (CharSequence) obj;
                                Object apply22 = AddOp.$Pl.apply2(apply2, intNum);
                                try {
                                    char stringRef3 = strings.stringRef(charSequence, ((Number) apply22).intValue());
                                    return LList.list2(Scheme.isEqv.apply2(Char.make(stringRef3), r6) != Boolean.FALSE ? Lit55 : Scheme.isEqv.apply2(Char.make(stringRef3), r7) != Boolean.FALSE ? Lit56 : pregexpError$V(new Object[]{Lit57}), AddOp.$Pl.apply2(apply2, Lit16));
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "string-ref", 2, apply22);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "string-ref", 1, obj);
                            }
                        }
                        LList lList = LList.Empty;
                        Boolean bool = Boolean.FALSE;
                        while (true) {
                            try {
                                try {
                                    stringRef = strings.stringRef((CharSequence) obj, ((Number) apply2).intValue());
                                    if (Scheme.isEqv.apply2(Char.make(stringRef), Lit58) == Boolean.FALSE) {
                                        if (Scheme.isEqv.apply2(Char.make(stringRef), Lit59) == Boolean.FALSE) {
                                            if (Scheme.isEqv.apply2(Char.make(stringRef), Lit62) == Boolean.FALSE) {
                                                break;
                                            }
                                            $Stpregexp$Mnspace$Mnsensitive$Qu$St = bool;
                                            apply2 = AddOp.$Pl.apply2(apply2, Lit8);
                                            bool = Boolean.FALSE;
                                        } else {
                                            apply2 = AddOp.$Pl.apply2(apply2, Lit8);
                                            lList = lists.cons(bool != Boolean.FALSE ? Lit60 : Lit61, lList);
                                            bool = Boolean.FALSE;
                                        }
                                    } else {
                                        apply2 = AddOp.$Pl.apply2(apply2, Lit8);
                                        bool = Boolean.TRUE;
                                    }
                                } catch (ClassCastException e3) {
                                    throw new WrongType(e3, "string-ref", 2, apply2);
                                }
                            } catch (ClassCastException e4) {
                                throw new WrongType(e4, "string-ref", 1, obj);
                            }
                        }
                        return Scheme.isEqv.apply2(Char.make(stringRef), Lit44) != Boolean.FALSE ? LList.list2(lList, AddOp.$Pl.apply2(apply2, Lit8)) : pregexpError$V(new Object[]{Lit57});
                    } catch (ClassCastException e5) {
                        throw new WrongType(e5, "string-ref", 2, apply2);
                    }
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "string-ref", 1, obj);
                }
            } catch (ClassCastException e7) {
                throw new WrongType(e7, "string-ref", 2, obj2);
            }
        } catch (ClassCastException e8) {
            throw new WrongType(e8, "string-ref", 1, obj);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0083, code lost:
    
        r9 = kawa.lib.lists.cdr.apply1(r8);
        r0 = gnu.lists.LList.list2(kawa.lib.lists.car.apply1(r8), r0);
        r8 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:?, code lost:
    
        return gnu.lists.LList.list2(r0, gnu.kawa.functions.AddOp.$Pl.apply2(r8, gnu.kawa.slib.pregexp.Lit8));
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x00a0, code lost:
    
        return pregexpError$V(new java.lang.Object[]{gnu.kawa.slib.pregexp.Lit64});
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005a, code lost:
    
        if (kawa.lib.characters.isChar$Eq(gnu.text.Char.make(kawa.lib.strings.stringRef((java.lang.CharSequence) r12, ((java.lang.Number) r8).intValue())), gnu.kawa.slib.pregexp.Lit6) != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x006c, code lost:
    
        if (r8 != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x006e, code lost:
    
        r0 = r7;
        r8 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0074, code lost:
    
        if (kawa.lib.lists.isNull(r8) == false) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object pregexpReadSubpattern(java.lang.Object r12, java.lang.Object r13, java.lang.Object r14) {
        /*
            java.lang.String r0 = "string-ref"
            java.lang.Object r1 = gnu.kawa.slib.pregexp.$Stpregexp$Mnspace$Mnsensitive$Qu$St
            r2 = 0
            r3 = r2
            java.lang.Object r3 = pregexpReadClusterType(r12, r13, r14)
            r4 = r2
            gnu.expr.GenericProc r4 = kawa.lib.lists.car
            java.lang.Object r4 = r4.apply1(r3)
            r5 = r2
            gnu.expr.GenericProc r5 = kawa.lib.lists.cadr
            java.lang.Object r5 = r5.apply1(r3)
            r6 = r2
            java.lang.Object r6 = pregexpReadPattern(r12, r5, r14)
            r7 = r2
            gnu.kawa.slib.pregexp.$Stpregexp$Mnspace$Mnsensitive$Qu$St = r1
            gnu.expr.GenericProc r7 = kawa.lib.lists.car
            java.lang.Object r7 = r7.apply1(r6)
            gnu.expr.GenericProc r8 = kawa.lib.lists.cadr
            java.lang.Object r8 = r8.apply1(r6)
            r9 = r2
            r2 = r8
            gnu.kawa.functions.NumberCompare r8 = kawa.standard.Scheme.numLss
            java.lang.Object r8 = r8.apply2(r2, r14)
            r9 = r8
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.ClassCastException -> La1
            boolean r8 = r9.booleanValue()     // Catch: java.lang.ClassCastException -> La1
            r9 = 0
            r9 = 1
            if (r8 == 0) goto L6c
            r10 = r12
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch: java.lang.ClassCastException -> L65
            r11 = r2
            java.lang.Number r11 = (java.lang.Number) r11     // Catch: java.lang.ClassCastException -> L5d
            int r0 = r11.intValue()     // Catch: java.lang.ClassCastException -> L5d
            char r0 = kawa.lib.strings.stringRef(r10, r0)
            gnu.text.Char r0 = gnu.text.Char.make(r0)
            gnu.text.Char r10 = gnu.kawa.slib.pregexp.Lit6
            boolean r0 = kawa.lib.characters.isChar$Eq(r0, r10)
            if (r0 == 0) goto L95
            goto L6e
        L5d:
            r12 = move-exception
            gnu.mapping.WrongType r13 = new gnu.mapping.WrongType
            r14 = 2
            r13.<init>(r12, r0, r14, r2)
            throw r13
        L65:
            r13 = move-exception
            gnu.mapping.WrongType r14 = new gnu.mapping.WrongType
            r14.<init>(r13, r0, r9, r12)
            throw r14
        L6c:
            if (r8 == 0) goto L95
        L6e:
            r0 = r7
            r8 = r4
        L70:
            boolean r9 = kawa.lib.lists.isNull(r8)
            if (r9 == 0) goto L83
            gnu.kawa.functions.AddOp r8 = gnu.kawa.functions.AddOp.$Pl
            gnu.math.IntNum r9 = gnu.kawa.slib.pregexp.Lit8
            java.lang.Object r8 = r8.apply2(r2, r9)
            gnu.lists.Pair r0 = gnu.lists.LList.list2(r0, r8)
            goto La0
        L83:
            gnu.expr.GenericProc r9 = kawa.lib.lists.cdr
            java.lang.Object r9 = r9.apply1(r8)
            gnu.expr.GenericProc r10 = kawa.lib.lists.car
            java.lang.Object r10 = r10.apply1(r8)
            gnu.lists.Pair r0 = gnu.lists.LList.list2(r10, r0)
            r8 = r9
            goto L70
        L95:
            java.lang.Object[] r0 = new java.lang.Object[r9]
            r8 = 0
            gnu.mapping.SimpleSymbol r9 = gnu.kawa.slib.pregexp.Lit64
            r0[r8] = r9
            java.lang.Object r0 = pregexpError$V(r0)
        La0:
            return r0
        La1:
            r12 = move-exception
            gnu.mapping.WrongType r13 = new gnu.mapping.WrongType
            java.lang.String r14 = "x"
            r0 = -2
            r13.<init>(r12, r14, r0, r8)
            goto Lac
        Lab:
            throw r13
        Lac:
            goto Lab
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.pregexp.pregexpReadSubpattern(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01f7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x025c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object pregexpWrapQuantifierIfAny(java.lang.Object r10, java.lang.Object r11, java.lang.Object r12) {
        /*
            Method dump skipped, instructions count: 676
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.pregexp.pregexpWrapQuantifierIfAny(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0099  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object pregexpReadNums(java.lang.Object r10, java.lang.Object r11, java.lang.Object r12) {
        /*
            Method dump skipped, instructions count: 307
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.pregexp.pregexpReadNums(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public static Object pregexpInvertCharList(Object vv) {
        Object apply1 = lists.car.apply1(vv);
        try {
            ((Pair) apply1).setCar(Lit79);
            return vv;
        } catch (ClassCastException e) {
            throw new WrongType(e, "set-car!", 1, apply1);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x015f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x011d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object pregexpReadCharList(java.lang.Object r10, java.lang.Object r11, java.lang.Object r12) {
        /*
            Method dump skipped, instructions count: 524
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.pregexp.pregexpReadCharList(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public static Object pregexpStringMatch(Object s1, Object s, Object i, Object n, Object sk, Object fk) {
        try {
            int n1 = strings.stringLength((CharSequence) s1);
            if (Scheme.numGrt.apply2(Integer.valueOf(n1), n) != Boolean.FALSE) {
                return Scheme.applyToArgs.apply1(fk);
            }
            Object j = Lit73;
            Object k = i;
            while (Scheme.numGEq.apply2(j, Integer.valueOf(n1)) == Boolean.FALSE) {
                if (Scheme.numGEq.apply2(k, n) != Boolean.FALSE) {
                    return Scheme.applyToArgs.apply1(fk);
                }
                try {
                    try {
                        try {
                            try {
                                if (characters.isChar$Eq(Char.make(strings.stringRef((CharSequence) s1, ((Number) j).intValue())), Char.make(strings.stringRef((CharSequence) s, ((Number) k).intValue())))) {
                                    AddOp addOp = AddOp.$Pl;
                                    IntNum intNum = Lit8;
                                    j = addOp.apply2(j, intNum);
                                    k = AddOp.$Pl.apply2(k, intNum);
                                } else {
                                    return Scheme.applyToArgs.apply1(fk);
                                }
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "string-ref", 2, k);
                            }
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "string-ref", 1, s);
                        }
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "string-ref", 2, j);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "string-ref", 1, s1);
                }
            }
            return Scheme.applyToArgs.apply2(sk, k);
        } catch (ClassCastException e5) {
            throw new WrongType(e5, "string-length", 1, s1);
        }
    }

    public static boolean isPregexpCharWord(Object obj) {
        try {
            boolean isCharAlphabetic = unicode.isCharAlphabetic((Char) obj);
            if (isCharAlphabetic) {
                return isCharAlphabetic;
            }
            try {
                boolean isCharNumeric = unicode.isCharNumeric((Char) obj);
                if (isCharNumeric) {
                    return isCharNumeric;
                }
                try {
                    return characters.isChar$Eq((Char) obj, Lit84);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "char=?", 1, obj);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "char-numeric?", 1, obj);
            }
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "char-alphabetic?", 1, obj);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:?, code lost:
    
        return java.lang.Boolean.FALSE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002f, code lost:
    
        if (r9 != false) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0016, code lost:
    
        if (r2 != false) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:?, code lost:
    
        return java.lang.Boolean.TRUE;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object isPregexpAtWordBoundary(java.lang.Object r7, java.lang.Object r8, java.lang.Object r9) {
        /*
            Method dump skipped, instructions count: 197
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.pregexp.isPregexpAtWordBoundary(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:108:0x02a8, code lost:
    
        if (r12 != false) goto L201;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:?, code lost:
    
        return java.lang.Boolean.TRUE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:?, code lost:
    
        return java.lang.Boolean.FALSE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x02bb, code lost:
    
        if (r12 != false) goto L201;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x02fa, code lost:
    
        if (r12 != false) goto L228;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:?, code lost:
    
        return java.lang.Boolean.TRUE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:?, code lost:
    
        return java.lang.Boolean.FALSE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x030f, code lost:
    
        if (r12 != false) goto L228;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x031d, code lost:
    
        if (r12 != false) goto L228;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x032b, code lost:
    
        if (r12 != false) goto L228;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0339, code lost:
    
        if (r12 != false) goto L228;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0347, code lost:
    
        if (r12 != false) goto L228;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object isPregexpCheckIfInCharClass(java.lang.Object r11, java.lang.Object r12) {
        /*
            Method dump skipped, instructions count: 918
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.pregexp.isPregexpCheckIfInCharClass(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    @Override // gnu.expr.ModuleBody
    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 33:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 34:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 47:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod, obj, obj2, callContext);
        }
    }

    public static Object pregexpListRef(Object s, Object i) {
        Object k = Lit73;
        Object s2 = s;
        while (!lists.isNull(s2)) {
            if (Scheme.numEqu.apply2(k, i) != Boolean.FALSE) {
                return lists.car.apply1(s2);
            }
            s2 = lists.cdr.apply1(s2);
            k = AddOp.$Pl.apply2(k, Lit8);
        }
        return Boolean.FALSE;
    }

    public static Object pregexpMakeBackrefList(Object re) {
        return lambda1sub(re);
    }

    public static Object lambda1sub(Object re) {
        if (!lists.isPair(re)) {
            return LList.Empty;
        }
        Object car$Mnre = lists.car.apply1(re);
        Object sub$Mncdr$Mnre = lambda1sub(lists.cdr.apply1(re));
        return Scheme.isEqv.apply2(car$Mnre, Lit100) != Boolean.FALSE ? lists.cons(lists.cons(re, Boolean.FALSE), sub$Mncdr$Mnre) : append.append$V(new Object[]{lambda1sub(car$Mnre), sub$Mncdr$Mnre});
    }

    /* compiled from: pregexp.scm */
    /* loaded from: classes2.dex */
    public class frame extends ModuleBody {
        Object backrefs;
        Object case$Mnsensitive$Qu;
        Procedure identity;
        Object n;
        Object s;
        Object sn;
        Object start;

        public frame() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 15, pregexp.Lit112, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:460");
            this.identity = moduleMethod;
        }

        public static Object lambda2identity(Object x) {
            return x;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 15 ? lambda2identity(obj) : super.apply1(moduleMethod, obj);
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

        static Boolean lambda4() {
            return Boolean.FALSE;
        }

        /* JADX WARN: Code restructure failed: missing block: B:196:0x0568, code lost:
        
            r1.old = r8.case$Mnsensitive$Qu;
            r8.case$Mnsensitive$Qu = kawa.standard.Scheme.isEqv.apply2(kawa.lib.lists.car.apply1(r1.re$1), gnu.kawa.slib.pregexp.Lit60);
         */
        /* JADX WARN: Code restructure failed: missing block: B:197:?, code lost:
        
            return lambda3sub(kawa.lib.lists.cadr.apply1(r1.re$1), r1.i, r1.lambda$Fn11, r1.lambda$Fn12);
         */
        /* JADX WARN: Code restructure failed: missing block: B:259:0x0112, code lost:
        
            if (r8.case$Mnsensitive$Qu == java.lang.Boolean.FALSE) goto L40;
         */
        /* JADX WARN: Code restructure failed: missing block: B:260:0x0114, code lost:
        
            r9 = kawa.lib.characters.char$Eq$Qu;
         */
        /* JADX WARN: Code restructure failed: missing block: B:261:0x0119, code lost:
        
            r0 = r8.s;
         */
        /* JADX WARN: Code restructure failed: missing block: B:263:0x011b, code lost:
        
            r0 = (java.lang.CharSequence) r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:264:0x011d, code lost:
        
            r12 = r1.i;
         */
        /* JADX WARN: Code restructure failed: missing block: B:268:0x0136, code lost:
        
            if (r9.apply2(gnu.text.Char.make(kawa.lib.strings.stringRef(r0, ((java.lang.Number) r12).intValue())), r1.re$1) == java.lang.Boolean.FALSE) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:270:?, code lost:
        
            return kawa.standard.Scheme.applyToArgs.apply2(r1.sk, gnu.kawa.functions.AddOp.$Pl.apply2(r1.i, gnu.kawa.slib.pregexp.Lit8));
         */
        /* JADX WARN: Code restructure failed: missing block: B:272:?, code lost:
        
            return kawa.standard.Scheme.applyToArgs.apply1(r1.fk);
         */
        /* JADX WARN: Code restructure failed: missing block: B:274:0x0156, code lost:
        
            r9 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:276:0x015c, code lost:
        
            throw new gnu.mapping.WrongType(r9, "string-ref", 2, r12);
         */
        /* JADX WARN: Code restructure failed: missing block: B:278:0x015d, code lost:
        
            r9 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:280:0x0163, code lost:
        
            throw new gnu.mapping.WrongType(r9, "string-ref", 1, r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:281:0x0117, code lost:
        
            r9 = kawa.lib.rnrs.unicode.char$Mnci$Eq$Qu;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:169:0x059e  */
        /* JADX WARN: Removed duplicated region for block: B:194:0x0607  */
        /* JADX WARN: Removed duplicated region for block: B:200:0x0612  */
        /* JADX WARN: Removed duplicated region for block: B:236:0x01fa A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:48:0x01d2  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x027a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object lambda3sub(java.lang.Object r9, java.lang.Object r10, java.lang.Object r11, java.lang.Object r12) {
            /*
                Method dump skipped, instructions count: 1588
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.pregexp.frame.lambda3sub(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
        }
    }

    public static Object pregexpMatchPositionsAux(Object re, Object s, Object sn, Object start, Object n, Object i) {
        frame frameVar = new frame();
        frameVar.s = s;
        frameVar.sn = sn;
        frameVar.start = start;
        frameVar.n = n;
        Procedure procedure = frameVar.identity;
        Object pregexpMakeBackrefList = pregexpMakeBackrefList(re);
        frameVar.case$Mnsensitive$Qu = Boolean.TRUE;
        frameVar.backrefs = pregexpMakeBackrefList;
        frameVar.identity = procedure;
        frameVar.lambda3sub(re, i, frameVar.identity, lambda$Fn1);
        Object arg0 = frameVar.backrefs;
        Object result = LList.Empty;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                arg0 = arg02.getCdr();
                result = Pair.make(lists.cdr.apply1(arg02.getCar()), result);
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        LList backrefs = LList.reverseInPlace(result);
        Object x = lists.car.apply1(backrefs);
        return x != Boolean.FALSE ? backrefs : x;
    }

    @Override // gnu.expr.ModuleBody
    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 36:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 37:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 38:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 39:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 40:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 41:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            default:
                return super.match0(moduleMethod, callContext);
        }
    }

    /* compiled from: pregexp.scm */
    /* loaded from: classes2.dex */
    public class frame0 extends ModuleBody {
        boolean could$Mnloop$Mninfinitely$Qu;
        Object fk;
        Object i;
        final ModuleMethod lambda$Fn11;
        final ModuleMethod lambda$Fn12;
        final ModuleMethod lambda$Fn2;
        final ModuleMethod lambda$Fn3;
        final ModuleMethod lambda$Fn4;
        final ModuleMethod lambda$Fn5;
        boolean maximal$Qu;
        Object old;
        Object p;
        Object q;
        Object re;
        Object re$1;
        Object sk;
        frame staticLink;

        public frame0() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 9, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:513");
            this.lambda$Fn2 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 10, null, 0);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:514");
            this.lambda$Fn3 = moduleMethod2;
            ModuleMethod moduleMethod3 = new ModuleMethod(this, 11, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:541");
            this.lambda$Fn4 = moduleMethod3;
            ModuleMethod moduleMethod4 = new ModuleMethod(this, 12, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod4.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:545");
            this.lambda$Fn5 = moduleMethod4;
            ModuleMethod moduleMethod5 = new ModuleMethod(this, 13, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod5.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:587");
            this.lambda$Fn11 = moduleMethod5;
            ModuleMethod moduleMethod6 = new ModuleMethod(this, 14, null, 0);
            moduleMethod6.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:590");
            this.lambda$Fn12 = moduleMethod6;
        }

        public Object lambda5loupOneOfChars(Object chars) {
            frame1 frame1Var = new frame1();
            frame1Var.staticLink = this;
            frame1Var.chars = chars;
            return lists.isNull(frame1Var.chars) ? Scheme.applyToArgs.apply1(this.fk) : this.staticLink.lambda3sub(lists.car.apply1(frame1Var.chars), this.i, this.sk, frame1Var.lambda$Fn13);
        }

        Object lambda9(Object i1) {
            return Scheme.applyToArgs.apply1(this.fk);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 9:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 10:
                default:
                    return super.match1(moduleMethod, obj, callContext);
                case 11:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 12:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 13:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
            }
        }

        Object lambda10() {
            return Scheme.applyToArgs.apply2(this.sk, AddOp.$Pl.apply2(this.i, pregexp.Lit8));
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 10:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 14:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                default:
                    return super.match0(moduleMethod, callContext);
            }
        }

        public Object lambda6loupSeq(Object res, Object i) {
            frame2 frame2Var = new frame2();
            frame2Var.staticLink = this;
            frame2Var.res = res;
            return lists.isNull(frame2Var.res) ? Scheme.applyToArgs.apply2(this.sk, i) : this.staticLink.lambda3sub(lists.car.apply1(frame2Var.res), i, frame2Var.lambda$Fn14, this.fk);
        }

        public Object lambda7loupOr(Object res) {
            frame3 frame3Var = new frame3();
            frame3Var.staticLink = this;
            frame3Var.res = res;
            return lists.isNull(frame3Var.res) ? Scheme.applyToArgs.apply1(this.fk) : this.staticLink.lambda3sub(lists.car.apply1(frame3Var.res), this.i, frame3Var.lambda$Fn15, frame3Var.lambda$Fn16);
        }

        Object lambda11(Object i) {
            return Scheme.applyToArgs.apply2(this.sk, i);
        }

        Object lambda12(Object i1) {
            Object assv = lists.assv(this.re$1, this.staticLink.backrefs);
            try {
                ((Pair) assv).setCdr(lists.cons(this.i, i1));
                return Scheme.applyToArgs.apply2(this.sk, i1);
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-cdr!", 1, assv);
            }
        }

        static Boolean lambda13() {
            return Boolean.FALSE;
        }

        static Boolean lambda14() {
            return Boolean.FALSE;
        }

        static Boolean lambda15() {
            return Boolean.FALSE;
        }

        static Boolean lambda16() {
            return Boolean.FALSE;
        }

        static Boolean lambda17() {
            return Boolean.FALSE;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            switch (moduleMethod.selector) {
                case 9:
                    return lambda9(obj);
                case 10:
                default:
                    return super.apply1(moduleMethod, obj);
                case 11:
                    return lambda11(obj);
                case 12:
                    return lambda12(obj);
                case 13:
                    return lambda18(obj);
            }
        }

        Object lambda18(Object i1) {
            this.staticLink.case$Mnsensitive$Qu = this.old;
            return Scheme.applyToArgs.apply2(this.sk, i1);
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            switch (moduleMethod.selector) {
                case 10:
                    return lambda10();
                case 14:
                    return lambda19();
                default:
                    return super.apply0(moduleMethod);
            }
        }

        Object lambda19() {
            this.staticLink.case$Mnsensitive$Qu = this.old;
            return Scheme.applyToArgs.apply1(this.fk);
        }

        public Object lambda8loupP(Object k, Object i) {
            frame4 frame4Var = new frame4();
            frame4Var.staticLink = this;
            frame4Var.k = k;
            frame4Var.i = i;
            if (Scheme.numLss.apply2(frame4Var.k, this.p) != Boolean.FALSE) {
                return this.staticLink.lambda3sub(this.re, frame4Var.i, frame4Var.lambda$Fn17, this.fk);
            }
            frame4Var.q = this.q != Boolean.FALSE ? AddOp.$Mn.apply2(this.q, this.p) : this.q;
            return frame4Var.lambda24loupQ(pregexp.Lit73, frame4Var.i);
        }
    }

    /* compiled from: pregexp.scm */
    /* loaded from: classes2.dex */
    public class frame1 extends ModuleBody {
        Object chars;
        final ModuleMethod lambda$Fn13;
        frame0 staticLink;

        public frame1() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 0);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:508");
            this.lambda$Fn13 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 1 ? lambda20() : super.apply0(moduleMethod);
        }

        Object lambda20() {
            return this.staticLink.lambda5loupOneOfChars(lists.cdr.apply1(this.chars));
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
    }

    /* compiled from: pregexp.scm */
    /* loaded from: classes2.dex */
    public class frame2 extends ModuleBody {
        final ModuleMethod lambda$Fn14;
        Object res;
        frame0 staticLink;

        public frame2() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 2, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:519");
            this.lambda$Fn14 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 2 ? lambda21(obj) : super.apply1(moduleMethod, obj);
        }

        Object lambda21(Object i1) {
            return this.staticLink.lambda6loupSeq(lists.cdr.apply1(this.res), i1);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 2) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: pregexp.scm */
    /* loaded from: classes2.dex */
    public class frame3 extends ModuleBody {
        final ModuleMethod lambda$Fn15;
        final ModuleMethod lambda$Fn16;
        Object res;
        frame0 staticLink;

        public frame3() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 3, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:526");
            this.lambda$Fn15 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 4, null, 0);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:529");
            this.lambda$Fn16 = moduleMethod2;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 3 ? lambda22(obj) : super.apply1(moduleMethod, obj);
        }

        Object lambda22(Object i1) {
            Object x = Scheme.applyToArgs.apply2(this.staticLink.sk, i1);
            return x != Boolean.FALSE ? x : this.staticLink.lambda7loupOr(lists.cdr.apply1(this.res));
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 3) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 4 ? lambda23() : super.apply0(moduleMethod);
        }

        Object lambda23() {
            return this.staticLink.lambda7loupOr(lists.cdr.apply1(this.res));
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 4) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }
    }

    @Override // gnu.expr.ModuleBody
    public Object apply0(ModuleMethod moduleMethod) {
        switch (moduleMethod.selector) {
            case 36:
                return frame.lambda4();
            case 37:
                return frame0.lambda13();
            case 38:
                return frame0.lambda14();
            case 39:
                return frame0.lambda15();
            case 40:
                return frame0.lambda16();
            case 41:
                return frame0.lambda17();
            default:
                return super.apply0(moduleMethod);
        }
    }

    /* compiled from: pregexp.scm */
    /* loaded from: classes2.dex */
    public class frame4 extends ModuleBody {
        Object i;
        Object k;
        final ModuleMethod lambda$Fn17;
        Object q;
        frame0 staticLink;

        public frame4() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 8, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:602");
            this.lambda$Fn17 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 8 ? lambda25(obj) : super.apply1(moduleMethod, obj);
        }

        Object lambda25(Object i1) {
            if (!this.staticLink.could$Mnloop$Mninfinitely$Qu ? this.staticLink.could$Mnloop$Mninfinitely$Qu : Scheme.numEqu.apply2(i1, this.i) != Boolean.FALSE) {
                pregexp.pregexpError$V(new Object[]{pregexp.Lit101, pregexp.Lit110});
            }
            return this.staticLink.lambda8loupP(AddOp.$Pl.apply2(this.k, pregexp.Lit8), i1);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 8) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }

        public Object lambda24loupQ(Object k, Object i) {
            frame5 frame5Var = new frame5();
            frame5Var.staticLink = this;
            frame5Var.k = k;
            frame5Var.i = i;
            frame5Var.fk = frame5Var.fk;
            if (this.q == Boolean.FALSE ? this.q != Boolean.FALSE : Scheme.numGEq.apply2(frame5Var.k, this.q) != Boolean.FALSE) {
                return frame5Var.lambda26fk();
            }
            if (this.staticLink.maximal$Qu) {
                return this.staticLink.staticLink.lambda3sub(this.staticLink.re, frame5Var.i, frame5Var.lambda$Fn18, frame5Var.fk);
            }
            Object x = frame5Var.lambda26fk();
            return x != Boolean.FALSE ? x : this.staticLink.staticLink.lambda3sub(this.staticLink.re, frame5Var.i, frame5Var.lambda$Fn19, frame5Var.fk);
        }
    }

    /* compiled from: pregexp.scm */
    /* loaded from: classes2.dex */
    public class frame5 extends ModuleBody {
        Procedure fk;
        Object i;
        Object k;
        final ModuleMethod lambda$Fn18;
        final ModuleMethod lambda$Fn19;
        frame4 staticLink;

        public frame5() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 5, pregexp.Lit111, 0);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:612");
            this.fk = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 6, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:617");
            this.lambda$Fn18 = moduleMethod2;
            ModuleMethod moduleMethod3 = new ModuleMethod(this, 7, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:628");
            this.lambda$Fn19 = moduleMethod3;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 5 ? lambda26fk() : super.apply0(moduleMethod);
        }

        public Object lambda26fk() {
            return Scheme.applyToArgs.apply2(this.staticLink.staticLink.sk, this.i);
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

        Object lambda27(Object i1) {
            if (!this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu ? this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu : Scheme.numEqu.apply2(i1, this.i) != Boolean.FALSE) {
                pregexp.pregexpError$V(new Object[]{pregexp.Lit101, pregexp.Lit110});
            }
            Object x = this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), i1);
            return x != Boolean.FALSE ? x : lambda26fk();
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 6:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 7:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                default:
                    return super.match1(moduleMethod, obj, callContext);
            }
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            switch (moduleMethod.selector) {
                case 6:
                    return lambda27(obj);
                case 7:
                    return lambda28(obj);
                default:
                    return super.apply1(moduleMethod, obj);
            }
        }

        Object lambda28(Object i1) {
            return this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), i1);
        }
    }

    public static Object pregexpReplaceAux(Object obj, Object obj2, Object obj3, Object obj4) {
        Object obj5;
        Object obj6 = Lit73;
        Object obj7 = "";
        while (Scheme.numGEq.apply2(obj6, obj3) == Boolean.FALSE) {
            try {
                try {
                    char stringRef = strings.stringRef((CharSequence) obj2, ((Number) obj6).intValue());
                    if (characters.isChar$Eq(Char.make(stringRef), Lit19)) {
                        Object pregexpReadEscapedNumber = pregexpReadEscapedNumber(obj2, obj6, obj3);
                        if (pregexpReadEscapedNumber != Boolean.FALSE) {
                            obj5 = lists.car.apply1(pregexpReadEscapedNumber);
                        } else {
                            try {
                                CharSequence charSequence = (CharSequence) obj2;
                                Object apply2 = AddOp.$Pl.apply2(obj6, Lit8);
                                try {
                                    obj5 = characters.isChar$Eq(Char.make(strings.stringRef(charSequence, ((Number) apply2).intValue())), Lit113) ? Lit73 : Boolean.FALSE;
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "string-ref", 2, apply2);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "string-ref", 1, obj2);
                            }
                        }
                        if (pregexpReadEscapedNumber != Boolean.FALSE) {
                            obj6 = lists.cadr.apply1(pregexpReadEscapedNumber);
                        } else {
                            obj6 = obj5 != Boolean.FALSE ? AddOp.$Pl.apply2(obj6, Lit16) : AddOp.$Pl.apply2(obj6, Lit8);
                        }
                        if (obj5 == Boolean.FALSE) {
                            try {
                                try {
                                    char stringRef2 = strings.stringRef((CharSequence) obj2, ((Number) obj6).intValue());
                                    obj6 = AddOp.$Pl.apply2(obj6, Lit8);
                                    if (!characters.isChar$Eq(Char.make(stringRef2), Lit11)) {
                                        obj7 = strings.stringAppend(obj7, strings.$make$string$(Char.make(stringRef2)));
                                    }
                                } catch (ClassCastException e3) {
                                    throw new WrongType(e3, "string-ref", 2, obj6);
                                }
                            } catch (ClassCastException e4) {
                                throw new WrongType(e4, "string-ref", 1, obj2);
                            }
                        } else {
                            Object pregexpListRef = pregexpListRef(obj4, obj5);
                            if (pregexpListRef != Boolean.FALSE) {
                                Object[] objArr = new Object[2];
                                objArr[0] = obj7;
                                try {
                                    CharSequence charSequence2 = (CharSequence) obj;
                                    Object apply1 = lists.car.apply1(pregexpListRef);
                                    try {
                                        int intValue = ((Number) apply1).intValue();
                                        Object apply12 = lists.cdr.apply1(pregexpListRef);
                                        try {
                                            objArr[1] = strings.substring(charSequence2, intValue, ((Number) apply12).intValue());
                                            obj7 = strings.stringAppend(objArr);
                                        } catch (ClassCastException e5) {
                                            throw new WrongType(e5, "substring", 3, apply12);
                                        }
                                    } catch (ClassCastException e6) {
                                        throw new WrongType(e6, "substring", 2, apply1);
                                    }
                                } catch (ClassCastException e7) {
                                    throw new WrongType(e7, "substring", 1, obj);
                                }
                            } else {
                                continue;
                            }
                        }
                    } else {
                        obj6 = AddOp.$Pl.apply2(obj6, Lit8);
                        obj7 = strings.stringAppend(obj7, strings.$make$string$(Char.make(stringRef)));
                    }
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "string-ref", 2, obj6);
                }
            } catch (ClassCastException e9) {
                throw new WrongType(e9, "string-ref", 1, obj2);
            }
        }
        return obj7;
    }

    @Override // gnu.expr.ModuleBody
    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        return moduleMethod.selector == 43 ? pregexpReplaceAux(obj, obj2, obj3, obj4) : super.apply4(moduleMethod, obj, obj2, obj3, obj4);
    }

    @Override // gnu.expr.ModuleBody
    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        if (moduleMethod.selector != 43) {
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

    public static Pair pregexp(Object s) {
        $Stpregexp$Mnspace$Mnsensitive$Qu$St = Boolean.TRUE;
        try {
            return LList.list2(Lit100, lists.car.apply1(pregexpReadPattern(s, Lit73, Integer.valueOf(strings.stringLength((CharSequence) s)))));
        } catch (ClassCastException e) {
            throw new WrongType(e, "string-length", 1, s);
        }
    }

    public static Object pregexpMatchPositions$V(Object obj, Object obj2, Object[] objArr) {
        Object apply1;
        LList makeList = LList.makeList(objArr, 0);
        if (strings.isString(obj)) {
            obj = pregexp(obj);
        } else if (!lists.isPair(obj)) {
            pregexpError$V(new Object[]{Lit114, Lit115, obj});
        }
        try {
            int stringLength = strings.stringLength((CharSequence) obj2);
            if (lists.isNull(makeList)) {
                apply1 = Lit73;
            } else {
                apply1 = lists.car.apply1(makeList);
                Object apply12 = lists.cdr.apply1(makeList);
                try {
                    makeList = (LList) apply12;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "opt-args", -2, apply12);
                }
            }
            Object valueOf = lists.isNull(makeList) ? Integer.valueOf(stringLength) : lists.car.apply1(makeList);
            Object obj3 = apply1;
            while (true) {
                Object apply2 = Scheme.numLEq.apply2(obj3, valueOf);
                try {
                    boolean booleanValue = ((Boolean) apply2).booleanValue();
                    if (!booleanValue) {
                        return booleanValue ? Boolean.TRUE : Boolean.FALSE;
                    }
                    Object pregexpMatchPositionsAux = pregexpMatchPositionsAux(obj, obj2, Integer.valueOf(stringLength), apply1, valueOf, obj3);
                    if (pregexpMatchPositionsAux != Boolean.FALSE) {
                        return pregexpMatchPositionsAux;
                    }
                    obj3 = AddOp.$Pl.apply2(obj3, Lit8);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "x", -2, apply2);
                }
            }
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "string-length", 1, obj2);
        }
    }

    public static Object pregexpMatch$V(Object pat, Object str, Object[] argsArray) {
        LList opt$Mnargs = LList.makeList(argsArray, 0);
        Object ix$Mnprs = Scheme.apply.apply4(pregexp$Mnmatch$Mnpositions, pat, str, opt$Mnargs);
        if (ix$Mnprs == Boolean.FALSE) {
            return ix$Mnprs;
        }
        Object result = LList.Empty;
        Object arg0 = ix$Mnprs;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                Object cdr = arg02.getCdr();
                Object ix$Mnpr = arg02.getCar();
                if (ix$Mnpr != Boolean.FALSE) {
                    try {
                        CharSequence charSequence = (CharSequence) str;
                        Object apply1 = lists.car.apply1(ix$Mnpr);
                        try {
                            int intValue = ((Number) apply1).intValue();
                            Object apply12 = lists.cdr.apply1(ix$Mnpr);
                            try {
                                ix$Mnpr = strings.substring(charSequence, intValue, ((Number) apply12).intValue());
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "substring", 3, apply12);
                            }
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "substring", 2, apply1);
                        }
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "substring", 1, str);
                    }
                }
                result = Pair.make(ix$Mnpr, result);
                arg0 = cdr;
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "arg0", -2, arg0);
            }
        }
        return LList.reverseInPlace(result);
    }

    @Override // gnu.expr.ModuleBody
    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        switch (moduleMethod.selector) {
            case 17:
                return pregexpError$V(objArr);
            case 30:
                return pregexpStringMatch(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5]);
            case 42:
                return pregexpMatchPositionsAux(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5]);
            case 45:
                Object obj = objArr[0];
                Object obj2 = objArr[1];
                int length = objArr.length - 2;
                Object[] objArr2 = new Object[length];
                while (true) {
                    length--;
                    if (length < 0) {
                        return pregexpMatchPositions$V(obj, obj2, objArr2);
                    }
                    objArr2[length] = objArr[length + 2];
                }
            case 46:
                Object obj3 = objArr[0];
                Object obj4 = objArr[1];
                int length2 = objArr.length - 2;
                Object[] objArr3 = new Object[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        return pregexpMatch$V(obj3, obj4, objArr3);
                    }
                    objArr3[length2] = objArr[length2 + 2];
                }
            default:
                return super.applyN(moduleMethod, objArr);
        }
    }

    public static Object pregexpSplit(Object obj, Object obj2) {
        try {
            int stringLength = strings.stringLength((CharSequence) obj2);
            Object obj3 = Lit73;
            LList lList = LList.Empty;
            Boolean bool = Boolean.FALSE;
            while (Scheme.numGEq.apply2(obj3, Integer.valueOf(stringLength)) == Boolean.FALSE) {
                Object pregexpMatchPositions$V = pregexpMatchPositions$V(obj, obj2, new Object[]{obj3, Integer.valueOf(stringLength)});
                if (pregexpMatchPositions$V == Boolean.FALSE) {
                    Object valueOf = Integer.valueOf(stringLength);
                    try {
                        try {
                            lList = lists.cons(strings.substring((CharSequence) obj2, ((Number) obj3).intValue(), stringLength), lList);
                            bool = Boolean.FALSE;
                            obj3 = valueOf;
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "substring", 2, obj3);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "substring", 1, obj2);
                    }
                } else {
                    Object apply1 = lists.car.apply1(pregexpMatchPositions$V);
                    Object apply12 = lists.car.apply1(apply1);
                    Object apply13 = lists.cdr.apply1(apply1);
                    if (Scheme.numEqu.apply2(apply12, apply13) != Boolean.FALSE) {
                        AddOp addOp = AddOp.$Pl;
                        IntNum intNum = Lit8;
                        Object apply2 = addOp.apply2(apply13, intNum);
                        try {
                            CharSequence charSequence = (CharSequence) obj2;
                            try {
                                int intValue = ((Number) obj3).intValue();
                                Object apply22 = AddOp.$Pl.apply2(apply12, intNum);
                                try {
                                    lList = lists.cons(strings.substring(charSequence, intValue, ((Number) apply22).intValue()), lList);
                                    bool = Boolean.TRUE;
                                    obj3 = apply2;
                                } catch (ClassCastException e3) {
                                    throw new WrongType(e3, "substring", 3, apply22);
                                }
                            } catch (ClassCastException e4) {
                                throw new WrongType(e4, "substring", 2, obj3);
                            }
                        } catch (ClassCastException e5) {
                            throw new WrongType(e5, "substring", 1, obj2);
                        }
                    } else {
                        Object apply23 = Scheme.numEqu.apply2(apply12, obj3);
                        try {
                            boolean booleanValue = ((Boolean) apply23).booleanValue();
                            if (booleanValue) {
                                if (bool != Boolean.FALSE) {
                                    bool = Boolean.FALSE;
                                    obj3 = apply13;
                                }
                                try {
                                    try {
                                        try {
                                            lList = lists.cons(strings.substring((CharSequence) obj2, ((Number) obj3).intValue(), ((Number) apply12).intValue()), lList);
                                            bool = Boolean.FALSE;
                                            obj3 = apply13;
                                        } catch (ClassCastException e6) {
                                            throw new WrongType(e6, "substring", 3, apply12);
                                        }
                                    } catch (ClassCastException e7) {
                                        throw new WrongType(e7, "substring", 2, obj3);
                                    }
                                } catch (ClassCastException e8) {
                                    throw new WrongType(e8, "substring", 1, obj2);
                                }
                            } else {
                                if (booleanValue) {
                                    bool = Boolean.FALSE;
                                    obj3 = apply13;
                                }
                                lList = lists.cons(strings.substring((CharSequence) obj2, ((Number) obj3).intValue(), ((Number) apply12).intValue()), lList);
                                bool = Boolean.FALSE;
                                obj3 = apply13;
                            }
                        } catch (ClassCastException e9) {
                            throw new WrongType(e9, "x", -2, apply23);
                        }
                    }
                }
            }
            return pregexpReverse$Ex(lList);
        } catch (ClassCastException e10) {
            throw new WrongType(e10, "string-length", 1, obj2);
        }
    }

    @Override // gnu.expr.ModuleBody
    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 33:
                return isPregexpCheckIfInCharClass(obj, obj2);
            case 34:
                return pregexpListRef(obj, obj2);
            case 47:
                return pregexpSplit(obj, obj2);
            default:
                return super.apply2(moduleMethod, obj, obj2);
        }
    }

    public static Object pregexpReplace(Object pat, Object str, Object ins) {
        try {
            int n = strings.stringLength((CharSequence) str);
            Object pp = pregexpMatchPositions$V(pat, str, new Object[]{Lit73, Integer.valueOf(n)});
            if (pp == Boolean.FALSE) {
                return str;
            }
            try {
                int ins$Mnlen = strings.stringLength((CharSequence) ins);
                Object m$Mni = lists.caar.apply1(pp);
                Object m$Mnn = lists.cdar.apply1(pp);
                Object[] objArr = new Object[3];
                try {
                    try {
                        objArr[0] = strings.substring((CharSequence) str, 0, ((Number) m$Mni).intValue());
                        objArr[1] = pregexpReplaceAux(str, ins, Integer.valueOf(ins$Mnlen), pp);
                        try {
                            try {
                                objArr[2] = strings.substring((CharSequence) str, ((Number) m$Mnn).intValue(), n);
                                return strings.stringAppend(objArr);
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "substring", 2, m$Mnn);
                            }
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "substring", 1, str);
                        }
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "substring", 3, m$Mni);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "substring", 1, str);
                }
            } catch (ClassCastException e5) {
                throw new WrongType(e5, "string-length", 1, ins);
            }
        } catch (ClassCastException e6) {
            throw new WrongType(e6, "string-length", 1, str);
        }
    }

    public static Object pregexpReplace$St(Object obj, Object obj2, Object obj3) {
        if (strings.isString(obj)) {
            obj = pregexp(obj);
        }
        try {
            int stringLength = strings.stringLength((CharSequence) obj2);
            try {
                int stringLength2 = strings.stringLength((CharSequence) obj3);
                Object obj4 = Lit73;
                Object obj5 = "";
                while (Scheme.numGEq.apply2(obj4, Integer.valueOf(stringLength)) == Boolean.FALSE) {
                    Object pregexpMatchPositions$V = pregexpMatchPositions$V(obj, obj2, new Object[]{obj4, Integer.valueOf(stringLength)});
                    if (pregexpMatchPositions$V == Boolean.FALSE) {
                        if (Scheme.numEqu.apply2(obj4, Lit73) == Boolean.FALSE) {
                            Object[] objArr = new Object[2];
                            objArr[0] = obj5;
                            try {
                                try {
                                    objArr[1] = strings.substring((CharSequence) obj2, ((Number) obj4).intValue(), stringLength);
                                    obj2 = strings.stringAppend(objArr);
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "substring", 2, obj4);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "substring", 1, obj2);
                            }
                        }
                        return obj2;
                    }
                    Object apply1 = lists.cdar.apply1(pregexpMatchPositions$V);
                    Object[] objArr2 = new Object[3];
                    objArr2[0] = obj5;
                    try {
                        CharSequence charSequence = (CharSequence) obj2;
                        try {
                            int intValue = ((Number) obj4).intValue();
                            Object apply12 = lists.caar.apply1(pregexpMatchPositions$V);
                            try {
                                objArr2[1] = strings.substring(charSequence, intValue, ((Number) apply12).intValue());
                                objArr2[2] = pregexpReplaceAux(obj2, obj3, Integer.valueOf(stringLength2), pregexpMatchPositions$V);
                                obj5 = strings.stringAppend(objArr2);
                                obj4 = apply1;
                            } catch (ClassCastException e3) {
                                throw new WrongType(e3, "substring", 3, apply12);
                            }
                        } catch (ClassCastException e4) {
                            throw new WrongType(e4, "substring", 2, obj4);
                        }
                    } catch (ClassCastException e5) {
                        throw new WrongType(e5, "substring", 1, obj2);
                    }
                }
                return obj5;
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "string-length", 1, obj3);
            }
        } catch (ClassCastException e7) {
            throw new WrongType(e7, "string-length", 1, obj2);
        }
    }

    @Override // gnu.expr.ModuleBody
    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        switch (moduleMethod.selector) {
            case 18:
                return pregexpReadPattern(obj, obj2, obj3);
            case 19:
                return pregexpReadBranch(obj, obj2, obj3);
            case 20:
                return pregexpReadPiece(obj, obj2, obj3);
            case 21:
                return pregexpReadEscapedNumber(obj, obj2, obj3);
            case 22:
                return pregexpReadEscapedChar(obj, obj2, obj3);
            case 23:
                return pregexpReadPosixCharClass(obj, obj2, obj3);
            case 24:
                return pregexpReadClusterType(obj, obj2, obj3);
            case 25:
                return pregexpReadSubpattern(obj, obj2, obj3);
            case 26:
                return pregexpWrapQuantifierIfAny(obj, obj2, obj3);
            case 27:
                return pregexpReadNums(obj, obj2, obj3);
            case 29:
                return pregexpReadCharList(obj, obj2, obj3);
            case 32:
                return isPregexpAtWordBoundary(obj, obj2, obj3);
            case 48:
                return pregexpReplace(obj, obj2, obj3);
            case 49:
                return pregexpReplace$St(obj, obj2, obj3);
            default:
                return super.apply3(moduleMethod, obj, obj2, obj3);
        }
    }

    public static Object pregexpQuote(Object s) {
        Object cons;
        try {
            Object i = Integer.valueOf(strings.stringLength((CharSequence) s) - 1);
            Object r = LList.Empty;
            while (Scheme.numLss.apply2(i, Lit73) == Boolean.FALSE) {
                Object apply2 = AddOp.$Mn.apply2(i, Lit8);
                try {
                    try {
                        char c = strings.stringRef((CharSequence) s, ((Number) i).intValue());
                        if (lists.memv(Char.make(c), Lit116) != Boolean.FALSE) {
                            cons = lists.cons(Lit19, lists.cons(Char.make(c), r));
                        } else {
                            cons = lists.cons(Char.make(c), r);
                        }
                        r = cons;
                        i = apply2;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "string-ref", 2, i);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "string-ref", 1, s);
                }
            }
            try {
                return strings.list$To$String((LList) r);
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "list->string", 1, r);
            }
        } catch (ClassCastException e4) {
            throw new WrongType(e4, "string-length", 1, s);
        }
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 16:
                return pregexpReverse$Ex(obj);
            case 28:
                return pregexpInvertCharList(obj);
            case 31:
                return isPregexpCharWord(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 35:
                return pregexpMakeBackrefList(obj);
            case 44:
                return pregexp(obj);
            case 50:
                return pregexpQuote(obj);
            default:
                return super.apply1(moduleMethod, obj);
        }
    }
}
