package com.google.youngandroid;

import android.content.Context;
import android.os.Handler;
import android.text.format.Formatter;
import androidx.core.view.InputDeviceCompat;
import androidx.fragment.app.FragmentTransaction;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.common.OptionList;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.Clock;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.OptionHelper;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.errors.StopBlocksExecution;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.AssetFetcher;
import com.google.appinventor.components.runtime.util.ComponentUtil;
import com.google.appinventor.components.runtime.util.Continuation;
import com.google.appinventor.components.runtime.util.ContinuationUtil;
import com.google.appinventor.components.runtime.util.CsvUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import com.google.appinventor.components.runtime.util.JavaStringUtils;
import com.google.appinventor.components.runtime.util.PropertyUtil;
import com.google.appinventor.components.runtime.util.RetValManager;
import com.google.appinventor.components.runtime.util.TypeUtil;
import com.google.appinventor.components.runtime.util.YailDictionary;
import com.google.appinventor.components.runtime.util.YailList;
import com.google.appinventor.components.runtime.util.YailNumberToString;
import com.google.appinventor.components.runtime.util.YailObject;
import gnu.bytecode.ClassType;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.Arithmetic;
import gnu.kawa.functions.BitwiseOp;
import gnu.kawa.functions.CallCC;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.functions.LispEscapeFormat;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.functions.NumberCompare;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.SlotSet;
import gnu.kawa.servlet.HttpRequestContext;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.DFloNum;
import gnu.math.IntFraction;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RealNum;
import gnu.text.Char;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kawa.Telnet;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxForms;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.ports;
import kawa.lib.strings;
import kawa.lib.thread;
import kawa.standard.Scheme;
import kawa.standard.expt;
import kawa.standard.syntax_case;

/* compiled from: runtime4411419262522357548.scm */
/* loaded from: classes.dex */
public class runtime extends ModuleBody implements Runnable {
    public static final ModuleMethod $Pcset$Mnand$Mncoerce$Mnproperty$Ex;
    public static final ModuleMethod $Pcset$Mnsubform$Mnlayout$Mnproperty$Ex;
    public static Object $Stalpha$Mnopaque$St;
    public static Object $Stcolor$Mnalpha$Mnposition$St;
    public static Object $Stcolor$Mnblue$Mnposition$St;
    public static Object $Stcolor$Mngreen$Mnposition$St;
    public static Object $Stcolor$Mnred$Mnposition$St;
    public static Boolean $Stdebug$St;
    public static final ModuleMethod $Stformat$Mninexact$St;
    public static Object $Stinit$Mnthunk$Mnenvironment$St;
    public static String $Stjava$Mnexception$Mnmessage$St;
    public static final Macro $Stlist$Mnfor$Mnruntime$St;
    public static Object $Stmax$Mncolor$Mncomponent$St;
    public static Object $Stnon$Mncoercible$Mnvalue$St;
    public static IntNum $Stnum$Mnconnections$St;
    public static DFloNum $Stpi$St;
    public static Random $Strandom$Mnnumber$Mngenerator$St;
    public static IntNum $Strepl$Mnport$St;
    public static String $Strepl$Mnserver$Mnaddress$St;
    public static Boolean $Strun$Mntelnet$Mnrepl$St;
    public static Object $Sttest$Mnenvironment$St;
    public static Object $Sttest$Mnglobal$Mnvar$Mnenvironment$St;
    public static Boolean $Sttesting$St;
    public static String $Stthe$Mnempty$Mnstring$Mnprinted$Mnrep$St;
    public static Object $Stthe$Mnnull$Mnvalue$Mnprinted$Mnrep$St;
    public static Object $Stthe$Mnnull$Mnvalue$St;
    public static Object $Stthis$Mnform$St;
    public static Object $Stthis$Mnis$Mnthe$Mnrepl$St;
    public static Object $Stui$Mnhandler$St;
    public static final ModuleMethod $Styail$Mnbreak$St;
    public static SimpleSymbol $Styail$Mnlist$St;
    public static final runtime $instance;
    public static final Class AssetFetcher;
    public static final Class ContinuationUtil;
    public static final Class CsvUtil;
    public static final Class Double;
    public static Object ERROR_DIVISION_BY_ZERO;
    public static final Class Float;
    public static final Class Integer;
    public static final Class JavaCollection;
    public static final Class JavaIterator;
    public static final Class JavaMap;
    public static final Class JavaStringUtils;
    public static final Class KawaEnvironment;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SyntaxPattern Lit100;
    static final SyntaxTemplate Lit101;
    static final SimpleSymbol Lit102;
    static final SyntaxPattern Lit103;
    static final SyntaxTemplate Lit104;
    static final SimpleSymbol Lit105;
    static final SyntaxRules Lit106;
    static final SimpleSymbol Lit107;
    static final SyntaxRules Lit108;
    static final SimpleSymbol Lit109;
    static final SimpleSymbol Lit11;
    static final SyntaxPattern Lit110;
    static final SyntaxTemplate Lit111;
    static final SyntaxTemplate Lit112;
    static final SyntaxTemplate Lit113;
    static final SimpleSymbol Lit114;
    static final SyntaxTemplate Lit115;
    static final SyntaxTemplate Lit116;
    static final SyntaxTemplate Lit117;
    static final SimpleSymbol Lit118;
    static final SyntaxPattern Lit119;
    static final SimpleSymbol Lit12;
    static final SyntaxTemplate Lit120;
    static final SyntaxTemplate Lit121;
    static final SimpleSymbol Lit122;
    static final SyntaxTemplate Lit123;
    static final SyntaxTemplate Lit124;
    static final SyntaxTemplate Lit125;
    static final SyntaxTemplate Lit126;
    static final SimpleSymbol Lit127;
    static final SyntaxRules Lit128;
    static final SimpleSymbol Lit129;
    static final SimpleSymbol Lit13;
    static final SyntaxRules Lit130;
    static final SimpleSymbol Lit131;
    static final SimpleSymbol Lit132;
    static final SimpleSymbol Lit133;
    static final SimpleSymbol Lit134;
    static final SimpleSymbol Lit135;
    static final SimpleSymbol Lit136;
    static final SimpleSymbol Lit137;
    static final SimpleSymbol Lit138;
    static final SimpleSymbol Lit139;
    static final SimpleSymbol Lit14;
    static final PairWithPosition Lit140;
    static final PairWithPosition Lit141;
    static final PairWithPosition Lit142;
    static final PairWithPosition Lit143;
    static final PairWithPosition Lit144;
    static final PairWithPosition Lit145;
    static final PairWithPosition Lit146;
    static final SimpleSymbol Lit147;
    static final SimpleSymbol Lit148;
    static final PairWithPosition Lit149;
    static final SimpleSymbol Lit15;
    static final PairWithPosition Lit150;
    static final PairWithPosition Lit151;
    static final PairWithPosition Lit152;
    static final PairWithPosition Lit153;
    static final SimpleSymbol Lit154;
    static final PairWithPosition Lit155;
    static final PairWithPosition Lit156;
    static final PairWithPosition Lit157;
    static final PairWithPosition Lit158;
    static final PairWithPosition Lit159;
    static final SimpleSymbol Lit16;
    static final PairWithPosition Lit160;
    static final PairWithPosition Lit161;
    static final PairWithPosition Lit162;
    static final PairWithPosition Lit163;
    static final PairWithPosition Lit164;
    static final PairWithPosition Lit165;
    static final SimpleSymbol Lit166;
    static final SyntaxRules Lit167;
    static final SimpleSymbol Lit168;
    static final SyntaxRules Lit169;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit170;
    static final SyntaxRules Lit171;
    static final SimpleSymbol Lit172;
    static final SyntaxRules Lit173;
    static final SimpleSymbol Lit174;
    static final SyntaxRules Lit175;
    static final SimpleSymbol Lit176;
    static final SyntaxRules Lit177;
    static final SimpleSymbol Lit178;
    static final SyntaxRules Lit179;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit180;
    static final SyntaxRules Lit181;
    static final SimpleSymbol Lit182;
    static final SyntaxRules Lit183;
    static final SimpleSymbol Lit184;
    static final SyntaxRules Lit185;
    static final SimpleSymbol Lit186;
    static final SimpleSymbol Lit187;
    static final SimpleSymbol Lit188;
    static final SimpleSymbol Lit189;
    static final DFloNum Lit19;
    static final SimpleSymbol Lit190;
    static final SimpleSymbol Lit191;
    static final SimpleSymbol Lit192;
    static final SimpleSymbol Lit193;
    static final SimpleSymbol Lit194;
    static final SimpleSymbol Lit195;
    static final SimpleSymbol Lit196;
    static final SimpleSymbol Lit197;
    static final SimpleSymbol Lit198;
    static final SimpleSymbol Lit199;
    static final PairWithPosition Lit2;
    static final DFloNum Lit20;
    static final SimpleSymbol Lit200;
    static final SimpleSymbol Lit201;
    static final SimpleSymbol Lit202;
    static final SimpleSymbol Lit203;
    static final SimpleSymbol Lit204;
    static final SimpleSymbol Lit205;
    static final SimpleSymbol Lit206;
    static final SimpleSymbol Lit207;
    static final SimpleSymbol Lit208;
    static final SimpleSymbol Lit209;
    static final DFloNum Lit21;
    static final SimpleSymbol Lit210;
    static final SimpleSymbol Lit211;
    static final SimpleSymbol Lit212;
    static final SimpleSymbol Lit213;
    static final SimpleSymbol Lit214;
    static final SimpleSymbol Lit215;
    static final SimpleSymbol Lit216;
    static final SimpleSymbol Lit217;
    static final SimpleSymbol Lit218;
    static final SimpleSymbol Lit219;
    static final DFloNum Lit22;
    static final SimpleSymbol Lit220;
    static final SyntaxRules Lit221;
    static final SimpleSymbol Lit222;
    static final SimpleSymbol Lit223;
    static final SimpleSymbol Lit224;
    static final SimpleSymbol Lit225;
    static final SimpleSymbol Lit226;
    static final SimpleSymbol Lit227;
    static final SimpleSymbol Lit228;
    static final SimpleSymbol Lit229;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit230;
    static final SimpleSymbol Lit231;
    static final SimpleSymbol Lit232;
    static final SimpleSymbol Lit233;
    static final SimpleSymbol Lit234;
    static final SimpleSymbol Lit235;
    static final SimpleSymbol Lit236;
    static final SimpleSymbol Lit237;
    static final SimpleSymbol Lit238;
    static final SimpleSymbol Lit239;
    static final IntNum Lit24;
    static final SimpleSymbol Lit240;
    static final SimpleSymbol Lit241;
    static final SimpleSymbol Lit242;
    static final SimpleSymbol Lit243;
    static final SimpleSymbol Lit244;
    static final SimpleSymbol Lit245;
    static final SimpleSymbol Lit246;
    static final SimpleSymbol Lit247;
    static final SimpleSymbol Lit248;
    static final SimpleSymbol Lit249;
    static final IntNum Lit25;
    static final SimpleSymbol Lit250;
    static final SimpleSymbol Lit251;
    static final SimpleSymbol Lit252;
    static final SimpleSymbol Lit253;
    static final SimpleSymbol Lit254;
    static final SimpleSymbol Lit255;
    static final SimpleSymbol Lit256;
    static final SimpleSymbol Lit257;
    static final SimpleSymbol Lit258;
    static final SimpleSymbol Lit259;
    static final IntNum Lit26;
    static final SimpleSymbol Lit260;
    static final SimpleSymbol Lit261;
    static final SimpleSymbol Lit262;
    static final SimpleSymbol Lit263;
    static final SimpleSymbol Lit264;
    static final SimpleSymbol Lit265;
    static final SimpleSymbol Lit266;
    static final SimpleSymbol Lit267;
    static final SimpleSymbol Lit268;
    static final SimpleSymbol Lit269;
    static final IntNum Lit27;
    static final SimpleSymbol Lit270;
    static final SimpleSymbol Lit271;
    static final SimpleSymbol Lit272;
    static final SimpleSymbol Lit273;
    static final SimpleSymbol Lit274;
    static final SimpleSymbol Lit275;
    static final SimpleSymbol Lit276;
    static final SimpleSymbol Lit277;
    static final SimpleSymbol Lit278;
    static final SimpleSymbol Lit279;
    static final DFloNum Lit28;
    static final SimpleSymbol Lit280;
    static final SimpleSymbol Lit281;
    static final SimpleSymbol Lit282;
    static final SimpleSymbol Lit283;
    static final SimpleSymbol Lit284;
    static final SimpleSymbol Lit285;
    static final SimpleSymbol Lit286;
    static final SimpleSymbol Lit287;
    static final SimpleSymbol Lit288;
    static final SimpleSymbol Lit289;
    static final IntNum Lit29;
    static final SimpleSymbol Lit290;
    static final SimpleSymbol Lit291;
    static final SimpleSymbol Lit292;
    static final SimpleSymbol Lit293;
    static final SimpleSymbol Lit294;
    static final SimpleSymbol Lit295;
    static final SimpleSymbol Lit296;
    static final SimpleSymbol Lit297;
    static final SimpleSymbol Lit298;
    static final SimpleSymbol Lit299;
    static final SimpleSymbol Lit3;
    static final DFloNum Lit30;
    static final SimpleSymbol Lit300;
    static final SimpleSymbol Lit301;
    static final SimpleSymbol Lit302;
    static final SimpleSymbol Lit303;
    static final SimpleSymbol Lit304;
    static final SimpleSymbol Lit305;
    static final SimpleSymbol Lit306;
    static final SimpleSymbol Lit307;
    static final SimpleSymbol Lit308;
    static final SimpleSymbol Lit309;
    static final DFloNum Lit31;
    static final SimpleSymbol Lit310;
    static final SimpleSymbol Lit311;
    static final SimpleSymbol Lit312;
    static final SimpleSymbol Lit313;
    static final SimpleSymbol Lit314;
    static final SimpleSymbol Lit315;
    static final SimpleSymbol Lit316;
    static final SimpleSymbol Lit317;
    static final SimpleSymbol Lit318;
    static final SimpleSymbol Lit319;
    static final IntNum Lit32;
    static final SimpleSymbol Lit320;
    static final SimpleSymbol Lit321;
    static final SimpleSymbol Lit322;
    static final SimpleSymbol Lit323;
    static final SimpleSymbol Lit324;
    static final SimpleSymbol Lit325;
    static final SimpleSymbol Lit326;
    static final SimpleSymbol Lit327;
    static final SimpleSymbol Lit328;
    static final SimpleSymbol Lit329;
    static final IntNum Lit33;
    static final SimpleSymbol Lit330;
    static final SimpleSymbol Lit331;
    static final SimpleSymbol Lit332;
    static final SimpleSymbol Lit333;
    static final SimpleSymbol Lit334;
    static final SimpleSymbol Lit335;
    static final SimpleSymbol Lit336;
    static final SimpleSymbol Lit337;
    static final SimpleSymbol Lit338;
    static final SimpleSymbol Lit339;
    static final IntNum Lit34;
    static final SimpleSymbol Lit340;
    static final SimpleSymbol Lit341;
    static final SimpleSymbol Lit342;
    static final SimpleSymbol Lit343;
    static final SimpleSymbol Lit344;
    static final SimpleSymbol Lit345;
    static final SimpleSymbol Lit346;
    static final SimpleSymbol Lit347;
    static final SimpleSymbol Lit348;
    static final SimpleSymbol Lit349;
    static final IntNum Lit35;
    static final SimpleSymbol Lit350;
    static final SimpleSymbol Lit351;
    static final SimpleSymbol Lit352;
    static final SimpleSymbol Lit353;
    static final SimpleSymbol Lit354;
    static final SimpleSymbol Lit355;
    static final SimpleSymbol Lit356;
    static final SimpleSymbol Lit357;
    static final SimpleSymbol Lit358;
    static final SimpleSymbol Lit359;
    static final Char Lit36;
    static final SimpleSymbol Lit360;
    static final SimpleSymbol Lit361;
    static final SimpleSymbol Lit362;
    static final SimpleSymbol Lit363;
    static final SimpleSymbol Lit364;
    static final SimpleSymbol Lit365;
    static final SimpleSymbol Lit366;
    static final SimpleSymbol Lit367;
    static final SimpleSymbol Lit368;
    static final SimpleSymbol Lit369;
    static final Char Lit37;
    static final SimpleSymbol Lit370;
    static final SimpleSymbol Lit371;
    static final SimpleSymbol Lit372;
    static final SimpleSymbol Lit373;
    static final SimpleSymbol Lit374;
    static final SimpleSymbol Lit375;
    static final SimpleSymbol Lit376;
    static final SimpleSymbol Lit377;
    static final SimpleSymbol Lit378;
    static final SimpleSymbol Lit379;
    static final Char Lit38;
    static final SimpleSymbol Lit380;
    static final SimpleSymbol Lit381;
    static final SimpleSymbol Lit382;
    static final SimpleSymbol Lit383;
    static final SimpleSymbol Lit384;
    static final SimpleSymbol Lit385;
    static final SimpleSymbol Lit386;
    static final SimpleSymbol Lit387;
    static final SimpleSymbol Lit388;
    static final SimpleSymbol Lit389;
    static final Char Lit39;
    static final SimpleSymbol Lit390;
    static final SimpleSymbol Lit391;
    static final SimpleSymbol Lit392;
    static final SimpleSymbol Lit393;
    static final SimpleSymbol Lit394;
    static final SimpleSymbol Lit395;
    static final SimpleSymbol Lit396;
    static final SimpleSymbol Lit397;
    static final SimpleSymbol Lit398;
    static final SimpleSymbol Lit399;
    static final Class Lit4;
    static final DFloNum Lit40;
    static final SimpleSymbol Lit400;
    static final SimpleSymbol Lit401;
    static final SimpleSymbol Lit402;
    static final SimpleSymbol Lit403;
    static final SimpleSymbol Lit404;
    static final SimpleSymbol Lit405;
    static final SimpleSymbol Lit406;
    static final SimpleSymbol Lit407;
    static final SyntaxRules Lit408;
    static final SimpleSymbol Lit409;
    static final IntFraction Lit41;
    static final SimpleSymbol Lit410;
    static final SimpleSymbol Lit411;
    static final SimpleSymbol Lit412;
    static final SimpleSymbol Lit413;
    static final SimpleSymbol Lit414;
    static final SimpleSymbol Lit415;
    static final SimpleSymbol Lit416;
    static final SimpleSymbol Lit417;
    static final SimpleSymbol Lit418;
    static final SimpleSymbol Lit419;
    static final IntFraction Lit42;
    static final SimpleSymbol Lit420;
    static final SimpleSymbol Lit421;
    static final SimpleSymbol Lit422;
    static final SimpleSymbol Lit423;
    static final SimpleSymbol Lit424;
    static final SimpleSymbol Lit425;
    static final SimpleSymbol Lit426;
    static final SimpleSymbol Lit427;
    static final SimpleSymbol Lit428;
    static final SimpleSymbol Lit429;
    static final SimpleSymbol Lit43;
    static final SimpleSymbol Lit430;
    static final SimpleSymbol Lit431;
    static final SimpleSymbol Lit432;
    static final SimpleSymbol Lit433;
    static final SimpleSymbol Lit434;
    static final SimpleSymbol Lit435;
    static final SimpleSymbol Lit436;
    static final SimpleSymbol Lit437;
    static final SimpleSymbol Lit438;
    static final SimpleSymbol Lit439;
    static final SimpleSymbol Lit44;
    static final SimpleSymbol Lit440;
    static final SimpleSymbol Lit441;
    static final SimpleSymbol Lit442;
    static final SimpleSymbol Lit443;
    static final SimpleSymbol Lit444;
    static final SimpleSymbol Lit445;
    static final SimpleSymbol Lit446;
    static final SimpleSymbol Lit447;
    static final SimpleSymbol Lit448;
    static final SimpleSymbol Lit449;
    static final PairWithPosition Lit45;
    static final SimpleSymbol Lit450;
    static final SimpleSymbol Lit451;
    static final SimpleSymbol Lit452;
    static final SimpleSymbol Lit453;
    static final SimpleSymbol Lit454;
    static final SimpleSymbol Lit455;
    static final SimpleSymbol Lit456;
    static final SimpleSymbol Lit457;
    static final SimpleSymbol Lit458;
    static final SimpleSymbol Lit459;
    static final SimpleSymbol Lit46;
    static final SimpleSymbol Lit460;
    static final SimpleSymbol Lit461;
    static final SimpleSymbol Lit462;
    static final SimpleSymbol Lit463;
    static final SimpleSymbol Lit464;
    static final SimpleSymbol Lit465;
    static final SimpleSymbol Lit466;
    static final SimpleSymbol Lit467;
    static final SimpleSymbol Lit468;
    static final SimpleSymbol Lit469;
    static final IntNum Lit47;
    static final SimpleSymbol Lit470;
    static final SimpleSymbol Lit471;
    static final SimpleSymbol Lit472;
    static final SimpleSymbol Lit473;
    static final SimpleSymbol Lit474;
    static final SimpleSymbol Lit475;
    static final SimpleSymbol Lit476;
    static final SimpleSymbol Lit477;
    static final SimpleSymbol Lit478;
    static final SimpleSymbol Lit479;
    static final IntNum Lit48;
    static final SimpleSymbol Lit480;
    static final SimpleSymbol Lit481;
    static final SimpleSymbol Lit482;
    static final SimpleSymbol Lit483;
    static final SimpleSymbol Lit484;
    static final SimpleSymbol Lit485;
    static final SimpleSymbol Lit486;
    static final SimpleSymbol Lit487;
    static final SimpleSymbol Lit488;
    static final SimpleSymbol Lit489;
    static final SimpleSymbol Lit49;
    static final SimpleSymbol Lit490;
    static final SimpleSymbol Lit491;
    static final SimpleSymbol Lit492;
    static final SimpleSymbol Lit493;
    static final SimpleSymbol Lit494;
    static final SimpleSymbol Lit495;
    static final SimpleSymbol Lit496;
    static final SimpleSymbol Lit497;
    static final SimpleSymbol Lit498;
    static final SimpleSymbol Lit499;
    static final SimpleSymbol Lit5;
    static final IntNum Lit50;
    static final SimpleSymbol Lit500;
    static final SimpleSymbol Lit501;
    static final SimpleSymbol Lit502;
    static final SimpleSymbol Lit503;
    static final SimpleSymbol Lit504;
    static final SimpleSymbol Lit505;
    static final SimpleSymbol Lit506;
    static final SimpleSymbol Lit507;
    static final SimpleSymbol Lit508;
    static final SimpleSymbol Lit509;
    static final IntNum Lit51;
    static final SimpleSymbol Lit510;
    static final SimpleSymbol Lit511;
    static final SimpleSymbol Lit512;
    static final SimpleSymbol Lit513;
    static final SimpleSymbol Lit514;
    static final SimpleSymbol Lit515;
    static final SimpleSymbol Lit516;
    static final SimpleSymbol Lit517;
    static final SimpleSymbol Lit518;
    static final SimpleSymbol Lit519;
    static final IntNum Lit52;
    static final SimpleSymbol Lit520;
    static final SimpleSymbol Lit521;
    static final SimpleSymbol Lit522;
    static final SimpleSymbol Lit523;
    static final SimpleSymbol Lit524;
    static final SimpleSymbol Lit525;
    static final SimpleSymbol Lit526;
    static final SimpleSymbol Lit527;
    static final SimpleSymbol Lit528;
    static final SimpleSymbol Lit529;
    static final IntNum Lit53;
    static final SimpleSymbol Lit530;
    static final SimpleSymbol Lit531;
    static final SimpleSymbol Lit532;
    static final SimpleSymbol Lit533;
    static final SimpleSymbol Lit534;
    static final SimpleSymbol Lit535;
    static final SimpleSymbol Lit536;
    static final SimpleSymbol Lit537;
    static final SimpleSymbol Lit538;
    static final SimpleSymbol Lit539;
    static final IntNum Lit54;
    static final SimpleSymbol Lit540;
    static final SimpleSymbol Lit55;
    static final SimpleSymbol Lit56;
    static final SimpleSymbol Lit57;
    static final SimpleSymbol Lit58;
    static final SimpleSymbol Lit59;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit60;
    static final SyntaxPattern Lit61;
    static final SyntaxTemplate Lit62;
    static final SimpleSymbol Lit63;
    static final SyntaxRules Lit64;
    static final SimpleSymbol Lit65;
    static final SimpleSymbol Lit66;
    static final SimpleSymbol Lit67;
    static final SimpleSymbol Lit68;
    static final SimpleSymbol Lit69;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit70;
    static final SyntaxRules Lit71;
    static final SimpleSymbol Lit72;
    static final SyntaxRules Lit73;
    static final SimpleSymbol Lit74;
    static final SimpleSymbol Lit75;
    static final SimpleSymbol Lit76;
    static final SimpleSymbol Lit77;
    static final SimpleSymbol Lit78;
    static final SimpleSymbol Lit79;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit80;
    static final SyntaxRules Lit81;
    static final SimpleSymbol Lit82;
    static final SyntaxRules Lit83;
    static final SimpleSymbol Lit84;
    static final SyntaxRules Lit85;
    static final SimpleSymbol Lit86;
    static final SyntaxRules Lit87;
    static final SimpleSymbol Lit88;
    static final SyntaxRules Lit89;
    static final SimpleSymbol Lit9;
    static final SimpleSymbol Lit90;
    static final SyntaxRules Lit91;
    static final SimpleSymbol Lit92;
    static final SyntaxRules Lit93;
    static final SimpleSymbol Lit94;
    static final SyntaxRules Lit95;
    static final SimpleSymbol Lit96;
    static final SyntaxRules Lit97;
    static final SimpleSymbol Lit98;
    static final SimpleSymbol Lit99;
    public static final Class Long;
    public static final Class Matcher;
    public static final Class Pattern;
    public static final Class PermissionException;
    public static final Class Short;
    public static final ClassType SimpleForm;
    public static final Class StopBlocksExecution;
    public static final Class String;
    public static final Class TypeUtil;
    public static final Class YailDictionary;
    public static final Class YailList;
    public static final Class YailNumberToString;
    public static final Class YailRuntimeError;
    public static final ModuleMethod acos$Mndegrees;
    public static final Macro add$Mncomponent;
    public static final ModuleMethod add$Mncomponent$Mnwithin$Mnrepl;
    public static final ModuleMethod add$Mnglobal$Mnvar$Mnto$Mncurrent$Mnform$Mnenvironment;
    public static final ModuleMethod add$Mninit$Mnthunk;
    public static final ModuleMethod add$Mnto$Mncurrent$Mnform$Mnenvironment;
    public static final ModuleMethod all$Mncoercible$Qu;
    public static final ModuleMethod alternate$Mnnumber$Mn$Grstring$Mnbinary;
    public static final Macro and$Mndelayed;
    public static final ModuleMethod android$Mnlog;
    public static final ModuleMethod appinventor$Mnnumber$Mn$Grstring;
    public static final ModuleMethod array$Mn$Grlist;
    public static final ModuleMethod as$Mnnumber;
    public static final ModuleMethod asin$Mndegrees;
    public static final ModuleMethod atan$Mndegrees;
    public static final ModuleMethod atan2$Mndegrees;
    public static final ModuleMethod avg;
    public static final ModuleMethod back;
    public static final ModuleMethod boolean$Mn$Grstring;
    public static final ModuleMethod boolean$Mneq$Qu;
    public static final ModuleMethod boolean$Mnleq$Qu;
    public static final ModuleMethod boolean$Mnlt$Qu;
    public static final ModuleMethod but$Mnlast;
    public static final ModuleMethod call$MnInitialize$Mnof$Mncomponents;
    public static final ModuleMethod call$Mncomponent$Mnmethod;
    public static final ModuleMethod call$Mncomponent$Mnmethod$Mnwith$Mnblocking$Mncontinuation;
    public static final ModuleMethod call$Mncomponent$Mnmethod$Mnwith$Mncontinuation;
    public static final ModuleMethod call$Mncomponent$Mntype$Mnmethod;
    public static final ModuleMethod call$Mncomponent$Mntype$Mnmethod$Mnwith$Mnblocking$Mncontinuation;
    public static final ModuleMethod call$Mncomponent$Mntype$Mnmethod$Mnwith$Mncontinuation;
    public static final ModuleMethod call$Mnwith$Mncoerced$Mnargs;
    public static final ModuleMethod call$Mnyail$Mnprimitive;
    public static final ModuleMethod clarify;
    public static final ModuleMethod clarify1;
    public static final ModuleMethod clear$Mncurrent$Mnform;
    public static final ModuleMethod clear$Mninit$Mnthunks;
    public static Object clip$Mnto$Mnjava$Mnint$Mnrange;
    public static final ModuleMethod close$Mnapplication;
    public static final ModuleMethod close$Mnscreen;
    public static final ModuleMethod close$Mnscreen$Mnwith$Mnplain$Mntext;
    public static final ModuleMethod close$Mnscreen$Mnwith$Mnvalue;
    public static final ModuleMethod coerce$Mnarg;
    public static final ModuleMethod coerce$Mnargs;
    public static final ModuleMethod coerce$Mnto$Mnboolean;
    public static final ModuleMethod coerce$Mnto$Mncomponent;
    public static final ModuleMethod coerce$Mnto$Mncomponent$Mnand$Mnverify;
    public static final ModuleMethod coerce$Mnto$Mncomponent$Mnof$Mntype;
    public static final ModuleMethod coerce$Mnto$Mndictionary;
    public static final ModuleMethod coerce$Mnto$Mnenum;
    public static final ModuleMethod coerce$Mnto$Mninstant;
    public static final ModuleMethod coerce$Mnto$Mnkey;
    public static final ModuleMethod coerce$Mnto$Mnnumber;
    public static final ModuleMethod coerce$Mnto$Mnnumber$Mnlist;
    public static final ModuleMethod coerce$Mnto$Mnpair;
    public static final ModuleMethod coerce$Mnto$Mnstring;
    public static final ModuleMethod coerce$Mnto$Mntext;
    public static final ModuleMethod coerce$Mnto$Mnyail$Mnlist;
    public static final ModuleMethod component$Mneq$Qu;
    public static final ModuleMethod component$Mnleq$Qu;
    public static final ModuleMethod component$Mnlt$Qu;
    public static final ModuleMethod convert$Mnto$Mnstrings$Mnfor$Mncsv;
    public static final ModuleMethod cos$Mndegrees;
    public static final Macro def;
    public static final Macro define$Mnevent;
    public static final Macro define$Mnevent$Mnhelper;
    public static final Macro define$Mnform;
    public static final Macro define$Mnform$Mninternal;
    public static final Macro define$Mngeneric$Mnevent;
    public static final Macro define$Mnrepl$Mnform;
    public static final ModuleMethod degrees$Mn$Grradians;
    public static final ModuleMethod degrees$Mn$Grradians$Mninternal;
    public static final ModuleMethod delete$Mnfrom$Mncurrent$Mnform$Mnenvironment;
    public static final Macro do$Mnafter$Mnform$Mncreation;
    public static final ModuleMethod drop;
    public static final ModuleMethod enum$Mntype$Qu;
    public static final ModuleMethod enum$Qu;
    public static final Class errorMessages;
    public static final ModuleMethod filter$Mntype$Mnin$Mncurrent$Mnform$Mnenvironment;
    public static final Macro filter_nondest;
    public static final Macro foreach;
    public static final Macro foreach$Mnwith$Mnbreak;
    public static final ModuleMethod format$Mnas$Mndecimal;
    public static final Macro forrange;
    public static final Macro forrange$Mnwith$Mnbreak;
    public static final ModuleMethod front;
    public static final Macro gen$Mnevent$Mnname;
    public static final Macro gen$Mngeneric$Mnevent$Mnname;
    public static final Macro gen$Mnsimple$Mncomponent$Mntype;
    public static final ModuleMethod generate$Mnruntime$Mntype$Mnerror;
    public static final Macro get$Mnall$Mncomponents;
    public static final Macro get$Mncomponent;
    public static final ModuleMethod get$Mndisplay$Mnrepresentation;
    public static final ModuleMethod get$Mninit$Mnthunk;
    public static Object get$Mnjson$Mndisplay$Mnrepresentation;
    public static Object get$Mnoriginal$Mndisplay$Mnrepresentation;
    public static final ModuleMethod get$Mnplain$Mnstart$Mntext;
    public static final ModuleMethod get$Mnproperty;
    public static final ModuleMethod get$Mnproperty$Mnand$Mncheck;
    public static final ModuleMethod get$Mnserver$Mnaddress$Mnfrom$Mnwifi;
    public static final ModuleMethod get$Mnstart$Mnvalue;
    public static final Macro get$Mnvar;
    public static final ModuleMethod gm;
    static Numeric highest;
    public static final ModuleMethod in$Mnui;
    public static final ModuleMethod indexof;
    public static final ModuleMethod init$Mnruntime;
    public static final ModuleMethod insert$Mnyail$Mnlist$Mnheader;
    public static final ModuleMethod internal$Mnbinary$Mnconvert;
    public static final ModuleMethod is$Mnbase10$Qu;
    public static final ModuleMethod is$Mnbinary$Qu;
    public static final ModuleMethod is$Mncoercible$Qu;
    public static final ModuleMethod is$Mneq$Qu;
    public static final ModuleMethod is$Mnhexadecimal$Qu;
    public static final ModuleMethod is$Mnleq$Qu;
    public static final ModuleMethod is$Mnlt$Qu;
    public static final ModuleMethod is$Mnnumber$Qu;
    public static final ModuleMethod java$Mncollection$Mn$Grkawa$Mnlist;
    public static final ModuleMethod java$Mncollection$Mn$Gryail$Mnlist;
    public static final ModuleMethod java$Mnmap$Mn$Gryail$Mndictionary;
    public static final ModuleMethod join$Mnstrings;
    public static final ModuleMethod kawa$Mnlist$Mn$Gryail$Mnlist;
    static final ModuleMethod lambda$Fn11;
    static final ModuleMethod lambda$Fn15;
    static final ModuleMethod lambda$Fn8;
    public static final Macro lexical$Mnvalue;
    public static final ModuleMethod list$Mneq$Qu;
    public static final ModuleMethod list$Mnleq$Qu;
    public static final ModuleMethod list$Mnlt$Qu;
    public static final ModuleMethod list$Mnmax;
    public static final ModuleMethod list$Mnmin;
    public static final ModuleMethod list$Mnnumber$Mnonly;
    static final Location loc$component;
    static final Location loc$non$Mncoercible$Mnvalue;
    static final Location loc$possible$Mncomponent;
    public static final ModuleMethod lookup$Mncomponent;
    public static final ModuleMethod lookup$Mnglobal$Mnvar$Mnin$Mncurrent$Mnform$Mnenvironment;
    public static final ModuleMethod lookup$Mnin$Mncurrent$Mnform$Mnenvironment;
    static Numeric lowest;
    public static final ModuleMethod make$Mncolor;
    public static final ModuleMethod make$Mndictionary$Mnpair;
    public static final ModuleMethod make$Mndisjunct;
    public static final ModuleMethod make$Mnexact$Mnyail$Mninteger;
    public static final ModuleMethod make$Mnyail$Mndictionary;
    public static final ModuleMethod make$Mnyail$Mnlist;
    public static final Macro map_nondest;
    public static final ModuleMethod math$Mnconvert$Mnbin$Mndec;
    public static final ModuleMethod math$Mnconvert$Mndec$Mnbin;
    public static final ModuleMethod math$Mnconvert$Mndec$Mnhex;
    public static final ModuleMethod math$Mnconvert$Mnhex$Mndec;
    public static final Macro maxcomparator$Mnnondest;
    public static final ModuleMethod maxl;
    public static final ModuleMethod mean;
    public static final ModuleMethod merge;
    public static final ModuleMethod merge$Mnkey;
    public static final ModuleMethod mergesort;
    public static final ModuleMethod mergesort$Mnkey;
    public static final Macro mincomparator$Mnnondest;
    public static final ModuleMethod minl;
    public static final ModuleMethod mode;
    public static final ModuleMethod open$Mnanother$Mnscreen;
    public static final ModuleMethod open$Mnanother$Mnscreen$Mnwith$Mnstart$Mnvalue;
    public static final Macro or$Mndelayed;
    public static final ModuleMethod padded$Mnstring$Mn$Grnumber;
    public static final ModuleMethod pair$Mnok$Qu;
    public static final ModuleMethod patched$Mnnumber$Mn$Grstring$Mnbinary;
    public static final ModuleMethod process$Mnand$Mndelayed;
    public static final ModuleMethod process$Mnor$Mndelayed;
    public static final Macro process$Mnrepl$Mninput;
    public static final ModuleMethod radians$Mn$Grdegrees;
    public static final ModuleMethod radians$Mn$Grdegrees$Mninternal;
    public static final ModuleMethod random$Mnfraction;
    public static final ModuleMethod random$Mninteger;
    public static final ModuleMethod random$Mnset$Mnseed;
    public static final Macro reduceovereach;
    public static final ModuleMethod remove$Mncomponent;
    public static final ModuleMethod rename$Mncomponent;
    public static final ModuleMethod rename$Mnin$Mncurrent$Mnform$Mnenvironment;
    public static final ModuleMethod reset$Mncurrent$Mnform$Mnenvironment;
    public static final ModuleMethod sample$Mnstd$Mndev;
    public static final ModuleMethod sanitize$Mnatomic;
    public static final ModuleMethod sanitize$Mncomponent$Mndata;
    public static final ModuleMethod sanitize$Mnreturn$Mnvalue;
    public static final ModuleMethod send$Mnto$Mnblock;
    public static final ModuleMethod set$Mnand$Mncoerce$Mnproperty$Ex;
    public static final ModuleMethod set$Mnand$Mncoerce$Mnproperty$Mnand$Mncheck$Ex;
    public static final ModuleMethod set$Mnform$Mnname;
    public static final Macro set$Mnlexical$Ex;
    public static final ModuleMethod set$Mnthis$Mnform;
    public static final Macro set$Mnvar$Ex;
    public static final ModuleMethod set$Mnyail$Mnlist$Mncontents$Ex;
    public static final ModuleMethod show$Mnarglist$Mnno$Mnparens;
    public static final ModuleMethod signal$Mnruntime$Mnerror;
    public static final ModuleMethod signal$Mnruntime$Mnform$Mnerror;
    public static final String simple$Mncomponent$Mnpackage$Mnname;
    public static final ModuleMethod sin$Mndegrees;
    public static final Macro sortcomparator_nondest;
    public static final Macro sortkey_nondest;
    public static final ModuleMethod split$Mncolor;
    public static final ModuleMethod std$Mndev;
    public static final ModuleMethod std$Mnerr;
    public static final ModuleMethod string$Mncontains;
    public static final ModuleMethod string$Mncontains$Mnall;
    public static final ModuleMethod string$Mncontains$Mnany;
    public static final ModuleMethod string$Mnempty$Qu;
    public static final ModuleMethod string$Mnreplace;
    public static final ModuleMethod string$Mnreplace$Mnall;
    public static final ModuleMethod string$Mnreplace$Mnmappings$Mndictionary;
    public static final ModuleMethod string$Mnreplace$Mnmappings$Mnearliest$Mnoccurrence;
    public static final ModuleMethod string$Mnreplace$Mnmappings$Mnlongest$Mnstring;
    public static final ModuleMethod string$Mnreverse;
    public static final ModuleMethod string$Mnsplit;
    public static final ModuleMethod string$Mnsplit$Mnat$Mnany;
    public static final ModuleMethod string$Mnsplit$Mnat$Mnfirst;
    public static final ModuleMethod string$Mnsplit$Mnat$Mnfirst$Mnof$Mnany;
    public static final ModuleMethod string$Mnsplit$Mnat$Mnspaces;
    public static final ModuleMethod string$Mnstarts$Mnat;
    public static final ModuleMethod string$Mnsubstring;
    public static final ModuleMethod string$Mnto$Mnlower$Mncase;
    public static final ModuleMethod string$Mnto$Mnupper$Mncase;
    public static final ModuleMethod string$Mntrim;
    public static final ModuleMethod sum$Mnmean$Mnsquare$Mndiff;
    public static final ModuleMethod symbol$Mnappend;
    public static final ModuleMethod take;
    public static final ModuleMethod tan$Mndegrees;
    public static final ModuleMethod text$Mndeobfuscate;
    public static final ModuleMethod type$Mn$Grclass;
    public static final ModuleMethod type$Mnlt$Qu;
    public static final ModuleMethod typeof;
    public static PairWithPosition typeordering;
    public static final ModuleMethod unicode$Mnstring$Mn$Grlist;
    public static final Macro use$Mnjson$Mnformat;

    /* renamed from: while, reason: not valid java name */
    public static final Macro f0while;
    public static final Macro while$Mnwith$Mnbreak;
    public static final ModuleMethod yail$Mnalist$Mnlookup;
    public static final ModuleMethod yail$Mnatomic$Mnequal$Qu;
    public static final ModuleMethod yail$Mnceiling;
    public static final ModuleMethod yail$Mndictionary$Mnalist$Mnto$Mndict;
    public static final ModuleMethod yail$Mndictionary$Mncombine$Mndicts;
    public static final ModuleMethod yail$Mndictionary$Mncopy;
    public static final ModuleMethod yail$Mndictionary$Mndelete$Mnpair;
    public static final ModuleMethod yail$Mndictionary$Mndict$Mnto$Mnalist;
    public static final ModuleMethod yail$Mndictionary$Mnget$Mnkeys;
    public static final ModuleMethod yail$Mndictionary$Mnget$Mnvalues;
    public static final ModuleMethod yail$Mndictionary$Mnis$Mnkey$Mnin;
    public static final ModuleMethod yail$Mndictionary$Mnlength;
    public static final ModuleMethod yail$Mndictionary$Mnlookup;
    public static final ModuleMethod yail$Mndictionary$Mnrecursive$Mnlookup;
    public static final ModuleMethod yail$Mndictionary$Mnrecursive$Mnset;
    public static final ModuleMethod yail$Mndictionary$Mnset$Mnpair;
    public static final ModuleMethod yail$Mndictionary$Mnwalk;
    public static final ModuleMethod yail$Mndictionary$Qu;
    public static final ModuleMethod yail$Mndivide;
    public static final ModuleMethod yail$Mnequal$Qu;
    public static final ModuleMethod yail$Mnfloor;
    public static final ModuleMethod yail$Mnfor$Mneach;
    public static final ModuleMethod yail$Mnfor$Mnrange;
    public static final ModuleMethod yail$Mnfor$Mnrange$Mnwith$Mnnumeric$Mnchecked$Mnargs;
    public static final ModuleMethod yail$Mnlist$Mn$Grkawa$Mnlist;
    public static final ModuleMethod yail$Mnlist$Mnadd$Mnto$Mnlist$Ex;
    public static final ModuleMethod yail$Mnlist$Mnappend$Ex;
    public static final ModuleMethod yail$Mnlist$Mnbut$Mnfirst;
    public static final ModuleMethod yail$Mnlist$Mnbut$Mnlast;
    public static final ModuleMethod yail$Mnlist$Mncandidate$Qu;
    public static final ModuleMethod yail$Mnlist$Mncontents;
    public static final ModuleMethod yail$Mnlist$Mncopy;
    public static final ModuleMethod yail$Mnlist$Mnempty$Qu;
    public static final ModuleMethod yail$Mnlist$Mnfilter;
    public static final ModuleMethod yail$Mnlist$Mnfrom$Mncsv$Mnrow;
    public static final ModuleMethod yail$Mnlist$Mnfrom$Mncsv$Mntable;
    public static final ModuleMethod yail$Mnlist$Mnget$Mnitem;
    public static final ModuleMethod yail$Mnlist$Mnindex;
    public static final ModuleMethod yail$Mnlist$Mninsert$Mnitem$Ex;
    public static final ModuleMethod yail$Mnlist$Mnjoin$Mnwith$Mnseparator;
    public static final ModuleMethod yail$Mnlist$Mnlength;
    public static final ModuleMethod yail$Mnlist$Mnmap;
    public static final ModuleMethod yail$Mnlist$Mnmax$Mncomparator;
    public static final ModuleMethod yail$Mnlist$Mnmember$Qu;
    public static final ModuleMethod yail$Mnlist$Mnmin$Mncomparator;
    public static final ModuleMethod yail$Mnlist$Mnnecessary;
    public static final ModuleMethod yail$Mnlist$Mnpick$Mnrandom;
    public static final ModuleMethod yail$Mnlist$Mnreduce;
    public static final ModuleMethod yail$Mnlist$Mnremove$Mnitem$Ex;
    public static final ModuleMethod yail$Mnlist$Mnreverse;
    public static final ModuleMethod yail$Mnlist$Mnset$Mnitem$Ex;
    public static final ModuleMethod yail$Mnlist$Mnslice;
    public static final ModuleMethod yail$Mnlist$Mnsort;
    public static final ModuleMethod yail$Mnlist$Mnsort$Mncomparator;
    public static final ModuleMethod yail$Mnlist$Mnsort$Mnkey;
    public static final ModuleMethod yail$Mnlist$Mnto$Mncsv$Mnrow;
    public static final ModuleMethod yail$Mnlist$Mnto$Mncsv$Mntable;
    public static final ModuleMethod yail$Mnlist$Qu;
    public static final ModuleMethod yail$Mnmul;
    public static final ModuleMethod yail$Mnnot;
    public static final ModuleMethod yail$Mnnot$Mnequal$Qu;
    public static final ModuleMethod yail$Mnnumber$Mnrange;
    public static final ModuleMethod yail$Mnround;

    public runtime() {
        ModuleInfo.register(this);
    }

    public static Object lookupGlobalVarInCurrentFormEnvironment(Symbol symbol) {
        return lookupGlobalVarInCurrentFormEnvironment(symbol, Boolean.FALSE);
    }

    public static Object lookupInCurrentFormEnvironment(Symbol symbol) {
        return lookupInCurrentFormEnvironment(symbol, Boolean.FALSE);
    }

    @Override // gnu.expr.ModuleBody
    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
        $Stdebug$St = Boolean.FALSE;
        $Stthis$Mnis$Mnthe$Mnrepl$St = Boolean.FALSE;
        $Sttesting$St = Boolean.FALSE;
        $Stinit$Mnthunk$Mnenvironment$St = Environment.make("init-thunk-environment");
        $Sttest$Mnenvironment$St = Environment.make("test-env");
        $Sttest$Mnglobal$Mnvar$Mnenvironment$St = Environment.make("test-global-var-env");
        $Stthe$Mnnull$Mnvalue$St = null;
        $Stthe$Mnnull$Mnvalue$Mnprinted$Mnrep$St = "*nothing*";
        $Stthe$Mnempty$Mnstring$Mnprinted$Mnrep$St = "*empty-string*";
        $Stnon$Mncoercible$Mnvalue$St = Lit2;
        $Stjava$Mnexception$Mnmessage$St = "An internal system error occurred: ";
        get$Mnoriginal$Mndisplay$Mnrepresentation = lambda$Fn8;
        get$Mnjson$Mndisplay$Mnrepresentation = lambda$Fn11;
        $Strandom$Mnnumber$Mngenerator$St = new Random();
        AddOp addOp = AddOp.$Mn;
        Numeric expt = expt.expt(Lit26, Lit27);
        IntNum intNum = Lit24;
        Object apply2 = addOp.apply2(expt, intNum);
        try {
            highest = (Numeric) apply2;
            Object apply1 = AddOp.$Mn.apply1(highest);
            try {
                lowest = (Numeric) apply1;
                clip$Mnto$Mnjava$Mnint$Mnrange = lambda$Fn15;
                ERROR_DIVISION_BY_ZERO = Integer.valueOf(ErrorMessages.ERROR_DIVISION_BY_ZERO);
                $Stpi$St = Lit28;
                $Styail$Mnlist$St = Lit43;
                typeordering = Lit45;
                IntNum intNum2 = Lit47;
                $Stmax$Mncolor$Mncomponent$St = numbers.exact(intNum2);
                $Stcolor$Mnalpha$Mnposition$St = numbers.exact(Lit50);
                $Stcolor$Mnred$Mnposition$St = numbers.exact(Lit51);
                $Stcolor$Mngreen$Mnposition$St = numbers.exact(Lit48);
                $Stcolor$Mnblue$Mnposition$St = numbers.exact(Lit25);
                $Stalpha$Mnopaque$St = numbers.exact(intNum2);
                $Strun$Mntelnet$Mnrepl$St = Boolean.TRUE;
                $Stnum$Mnconnections$St = intNum;
                $Strepl$Mnserver$Mnaddress$St = "NONE";
                $Strepl$Mnport$St = Lit54;
                $Stui$Mnhandler$St = null;
                $Stthis$Mnform$St = null;
            } catch (ClassCastException e) {
                throw new WrongType(e, "lowest", -2, apply1);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "highest", -2, apply2);
        }
    }

    public static void androidLog(Object message) {
    }

    @Override // gnu.expr.ModuleBody
    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 15:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 16:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 20:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 22:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 25:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 29:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 30:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 31:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 32:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 34:
                if (!(obj instanceof Symbol)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 36:
                if (!(obj instanceof Symbol)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 37:
                if (!(obj instanceof Symbol)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 40:
                if (!(obj instanceof Symbol)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 44:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 54:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 56:
                if (!(obj instanceof Collection)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 57:
                if (!(obj instanceof Collection)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 58:
                if (!(obj instanceof Map)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 59:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 62:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 67:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 70:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 71:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 72:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 74:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 75:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 76:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 78:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 79:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 80:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 81:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 82:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 83:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 84:
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
            case 92:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 93:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 94:
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
            case 99:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 103:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 104:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 105:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 106:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 109:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 111:
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
            case 117:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 118:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 119:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 120:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 122:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 123:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 124:
                if (!(obj instanceof CharSequence)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 125:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 127:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 128:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 129:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 130:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 131:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 132:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 133:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 134:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 135:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 136:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 137:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 138:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 139:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 140:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 141:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 142:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 143:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 144:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 146:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 147:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 148:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 149:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 150:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 151:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 153:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 154:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 155:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 156:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 158:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 159:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case ComponentConstants.TEXTBOX_PREFERRED_WIDTH /* 160 */:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 161:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 162:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 163:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 164:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 165:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 174:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 179:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK /* 190 */:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 199:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 204:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 209:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 210:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 211:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 219:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case YaVersion.YOUNG_ANDROID_VERSION /* 229 */:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 230:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 232:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 233:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 234:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 235:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 237:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 238:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 239:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 248:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 250:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case Telnet.WONT /* 252 */:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case InputDeviceCompat.SOURCE_KEYBOARD /* 257 */:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 258:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 259:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 262:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 265:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 267:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 272:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 273:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 277:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 278:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod, obj, callContext);
        }
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("add-to-components").readResolve();
        Lit540 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("init-components").readResolve();
        Lit539 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("init-global-variables").readResolve();
        Lit538 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("components").readResolve();
        Lit537 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("create-components").readResolve();
        Lit536 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("reverse").readResolve();
        Lit535 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("*the-null-value*").readResolve();
        Lit534 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("register-events").readResolve();
        Lit533 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("symbols").readResolve();
        Lit532 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("symbol->string").readResolve();
        Lit531 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("field").readResolve();
        Lit530 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("cadddr").readResolve();
        Lit529 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("caddr").readResolve();
        Lit528 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("component-descriptors").readResolve();
        Lit527 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("component-object").readResolve();
        Lit526 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = (SimpleSymbol) new SimpleSymbol("component-container").readResolve();
        Lit525 = simpleSymbol16;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("cadr").readResolve();
        Lit524 = simpleSymbol17;
        SimpleSymbol simpleSymbol18 = (SimpleSymbol) new SimpleSymbol("component-info").readResolve();
        Lit523 = simpleSymbol18;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("var-val-pairs").readResolve();
        Lit522 = simpleSymbol19;
        SimpleSymbol simpleSymbol20 = (SimpleSymbol) new SimpleSymbol("add-to-global-var-environment").readResolve();
        Lit521 = simpleSymbol20;
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol("var-val").readResolve();
        Lit520 = simpleSymbol21;
        SimpleSymbol simpleSymbol22 = (SimpleSymbol) new SimpleSymbol("car").readResolve();
        Lit519 = simpleSymbol22;
        SimpleSymbol simpleSymbol23 = (SimpleSymbol) new SimpleSymbol("for-each").readResolve();
        Lit518 = simpleSymbol23;
        SimpleSymbol simpleSymbol24 = (SimpleSymbol) new SimpleSymbol("events").readResolve();
        Lit517 = simpleSymbol24;
        SimpleSymbol simpleSymbol25 = (SimpleSymbol) new SimpleSymbol("event-info").readResolve();
        Lit516 = simpleSymbol25;
        SimpleSymbol simpleSymbol26 = (SimpleSymbol) new SimpleSymbol("registerEventForDelegation").readResolve();
        Lit515 = simpleSymbol26;
        SimpleSymbol simpleSymbol27 = (SimpleSymbol) new SimpleSymbol("SimpleEventDispatcher").readResolve();
        Lit514 = simpleSymbol27;
        SimpleSymbol simpleSymbol28 = (SimpleSymbol) new SimpleSymbol("define-alias").readResolve();
        Lit513 = simpleSymbol28;
        SimpleSymbol simpleSymbol29 = (SimpleSymbol) new SimpleSymbol("componentName").readResolve();
        Lit512 = simpleSymbol29;
        SimpleSymbol simpleSymbol30 = (SimpleSymbol) new SimpleSymbol("lookup-handler").readResolve();
        Lit511 = simpleSymbol30;
        SimpleSymbol simpleSymbol31 = (SimpleSymbol) new SimpleSymbol("java.lang.Throwable").readResolve();
        Lit510 = simpleSymbol31;
        SimpleSymbol simpleSymbol32 = (SimpleSymbol) new SimpleSymbol("getPermissionNeeded").readResolve();
        Lit509 = simpleSymbol32;
        SimpleSymbol simpleSymbol33 = (SimpleSymbol) new SimpleSymbol("PermissionDenied").readResolve();
        Lit508 = simpleSymbol33;
        SimpleSymbol simpleSymbol34 = (SimpleSymbol) new SimpleSymbol("equal?").readResolve();
        Lit507 = simpleSymbol34;
        SimpleSymbol simpleSymbol35 = (SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.errors.PermissionException").readResolve();
        Lit506 = simpleSymbol35;
        SimpleSymbol simpleSymbol36 = (SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.errors.StopBlocksExecution").readResolve();
        Lit505 = simpleSymbol36;
        SimpleSymbol simpleSymbol37 = (SimpleSymbol) new SimpleSymbol("notAlreadyHandled").readResolve();
        Lit504 = simpleSymbol37;
        SimpleSymbol simpleSymbol38 = (SimpleSymbol) new SimpleSymbol("apply").readResolve();
        Lit503 = simpleSymbol38;
        SimpleSymbol simpleSymbol39 = (SimpleSymbol) new SimpleSymbol("try-catch").readResolve();
        Lit502 = simpleSymbol39;
        SimpleSymbol simpleSymbol40 = (SimpleSymbol) new SimpleSymbol("handler-symbol").readResolve();
        Lit501 = simpleSymbol40;
        SimpleSymbol simpleSymbol41 = (SimpleSymbol) new SimpleSymbol("get-simple-name").readResolve();
        Lit500 = simpleSymbol41;
        SimpleSymbol simpleSymbol42 = (SimpleSymbol) new SimpleSymbol("string-append").readResolve();
        Lit499 = simpleSymbol42;
        SimpleSymbol simpleSymbol43 = (SimpleSymbol) new SimpleSymbol("string->symbol").readResolve();
        Lit498 = simpleSymbol43;
        SimpleSymbol simpleSymbol44 = (SimpleSymbol) new SimpleSymbol("void").readResolve();
        Lit497 = simpleSymbol44;
        SimpleSymbol simpleSymbol45 = (SimpleSymbol) new SimpleSymbol("java.lang.Object[]").readResolve();
        Lit496 = simpleSymbol45;
        SimpleSymbol simpleSymbol46 = (SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.Component").readResolve();
        Lit495 = simpleSymbol46;
        SimpleSymbol simpleSymbol47 = (SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.HandlesEventDispatching").readResolve();
        Lit494 = simpleSymbol47;
        SimpleSymbol simpleSymbol48 = (SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.EventDispatcher").readResolve();
        Lit493 = simpleSymbol48;
        SimpleSymbol simpleSymbol49 = (SimpleSymbol) new SimpleSymbol("printStackTrace").readResolve();
        Lit492 = simpleSymbol49;
        SimpleSymbol simpleSymbol50 = (SimpleSymbol) new SimpleSymbol("process-exception").readResolve();
        Lit491 = simpleSymbol50;
        SimpleSymbol simpleSymbol51 = (SimpleSymbol) new SimpleSymbol("and").readResolve();
        Lit490 = simpleSymbol51;
        SimpleSymbol simpleSymbol52 = (SimpleSymbol) new SimpleSymbol("exception").readResolve();
        Lit489 = simpleSymbol52;
        SimpleSymbol simpleSymbol53 = (SimpleSymbol) new SimpleSymbol("args").readResolve();
        Lit488 = simpleSymbol53;
        SimpleSymbol simpleSymbol54 = (SimpleSymbol) new SimpleSymbol("handler").readResolve();
        Lit487 = simpleSymbol54;
        SimpleSymbol simpleSymbol55 = (SimpleSymbol) new SimpleSymbol("eventName").readResolve();
        Lit486 = simpleSymbol55;
        SimpleSymbol simpleSymbol56 = (SimpleSymbol) new SimpleSymbol("componentObject").readResolve();
        Lit485 = simpleSymbol56;
        SimpleSymbol simpleSymbol57 = (SimpleSymbol) new SimpleSymbol("lookup-in-form-environment").readResolve();
        Lit484 = simpleSymbol57;
        SimpleSymbol simpleSymbol58 = (SimpleSymbol) new SimpleSymbol("eq?").readResolve();
        Lit483 = simpleSymbol58;
        SimpleSymbol simpleSymbol59 = (SimpleSymbol) new SimpleSymbol("registeredObject").readResolve();
        Lit482 = simpleSymbol59;
        SimpleSymbol simpleSymbol60 = (SimpleSymbol) new SimpleSymbol("is-bound-in-form-environment").readResolve();
        Lit481 = simpleSymbol60;
        SimpleSymbol simpleSymbol61 = (SimpleSymbol) new SimpleSymbol("registeredComponentName").readResolve();
        Lit480 = simpleSymbol61;
        SimpleSymbol simpleSymbol62 = (SimpleSymbol) new SimpleSymbol("java.lang.String").readResolve();
        Lit479 = simpleSymbol62;
        SimpleSymbol simpleSymbol63 = (SimpleSymbol) new SimpleSymbol("as").readResolve();
        Lit478 = simpleSymbol63;
        SimpleSymbol simpleSymbol64 = (SimpleSymbol) new SimpleSymbol("YailRuntimeError").readResolve();
        Lit477 = simpleSymbol64;
        SimpleSymbol simpleSymbol65 = (SimpleSymbol) new SimpleSymbol(GetNamedPart.INSTANCEOF_METHOD_NAME).readResolve();
        Lit476 = simpleSymbol65;
        SimpleSymbol simpleSymbol66 = (SimpleSymbol) new SimpleSymbol("getMessage").readResolve();
        Lit475 = simpleSymbol66;
        SimpleSymbol simpleSymbol67 = (SimpleSymbol) new SimpleSymbol("send-error").readResolve();
        Lit474 = simpleSymbol67;
        SimpleSymbol simpleSymbol68 = (SimpleSymbol) new SimpleSymbol("ex").readResolve();
        Lit473 = simpleSymbol68;
        SimpleSymbol simpleSymbol69 = (SimpleSymbol) new SimpleSymbol("this").readResolve();
        Lit472 = simpleSymbol69;
        SimpleSymbol simpleSymbol70 = (SimpleSymbol) new SimpleSymbol("when").readResolve();
        Lit471 = simpleSymbol70;
        SimpleSymbol simpleSymbol71 = (SimpleSymbol) new SimpleSymbol("error").readResolve();
        Lit470 = simpleSymbol71;
        SimpleSymbol simpleSymbol72 = (SimpleSymbol) new SimpleSymbol("thunk").readResolve();
        Lit469 = simpleSymbol72;
        SimpleSymbol simpleSymbol73 = (SimpleSymbol) new SimpleSymbol("form-do-after-creation").readResolve();
        Lit468 = simpleSymbol73;
        SimpleSymbol simpleSymbol74 = (SimpleSymbol) new SimpleSymbol("add-to-form-do-after-creation").readResolve();
        Lit467 = simpleSymbol74;
        SimpleSymbol simpleSymbol75 = (SimpleSymbol) new SimpleSymbol("val-thunk").readResolve();
        Lit466 = simpleSymbol75;
        SimpleSymbol simpleSymbol76 = (SimpleSymbol) new SimpleSymbol("var").readResolve();
        Lit465 = simpleSymbol76;
        SimpleSymbol simpleSymbol77 = (SimpleSymbol) new SimpleSymbol("global-vars-to-create").readResolve();
        Lit464 = simpleSymbol77;
        SimpleSymbol simpleSymbol78 = (SimpleSymbol) new SimpleSymbol("init-thunk").readResolve();
        Lit463 = simpleSymbol78;
        SimpleSymbol simpleSymbol79 = (SimpleSymbol) new SimpleSymbol("component-type").readResolve();
        Lit462 = simpleSymbol79;
        SimpleSymbol simpleSymbol80 = (SimpleSymbol) new SimpleSymbol("container-name").readResolve();
        Lit461 = simpleSymbol80;
        SimpleSymbol simpleSymbol81 = (SimpleSymbol) new SimpleSymbol("components-to-create").readResolve();
        Lit460 = simpleSymbol81;
        SimpleSymbol simpleSymbol82 = (SimpleSymbol) new SimpleSymbol("set!").readResolve();
        Lit459 = simpleSymbol82;
        SimpleSymbol simpleSymbol83 = (SimpleSymbol) new SimpleSymbol("event-name").readResolve();
        Lit458 = simpleSymbol83;
        SimpleSymbol simpleSymbol84 = (SimpleSymbol) new SimpleSymbol("component-name").readResolve();
        Lit457 = simpleSymbol84;
        SimpleSymbol simpleSymbol85 = (SimpleSymbol) new SimpleSymbol("cons").readResolve();
        Lit456 = simpleSymbol85;
        SimpleSymbol simpleSymbol86 = (SimpleSymbol) new SimpleSymbol("events-to-register").readResolve();
        Lit455 = simpleSymbol86;
        SimpleSymbol simpleSymbol87 = (SimpleSymbol) new SimpleSymbol("add-to-events").readResolve();
        Lit454 = simpleSymbol87;
        SimpleSymbol simpleSymbol88 = (SimpleSymbol) new SimpleSymbol("gnu.lists.LList").readResolve();
        Lit453 = simpleSymbol88;
        SimpleSymbol simpleSymbol89 = (SimpleSymbol) new SimpleSymbol("global-var-environment").readResolve();
        Lit452 = simpleSymbol89;
        SimpleSymbol simpleSymbol90 = (SimpleSymbol) new SimpleSymbol("format").readResolve();
        Lit451 = simpleSymbol90;
        SimpleSymbol simpleSymbol91 = (SimpleSymbol) new SimpleSymbol("make").readResolve();
        Lit450 = simpleSymbol91;
        SimpleSymbol simpleSymbol92 = (SimpleSymbol) new SimpleSymbol("isBound").readResolve();
        Lit449 = simpleSymbol92;
        SimpleSymbol simpleSymbol93 = (SimpleSymbol) new SimpleSymbol("default-value").readResolve();
        Lit448 = simpleSymbol93;
        SimpleSymbol simpleSymbol94 = (SimpleSymbol) new SimpleSymbol("gnu.mapping.Symbol").readResolve();
        Lit447 = simpleSymbol94;
        SimpleSymbol simpleSymbol95 = (SimpleSymbol) new SimpleSymbol("form-environment").readResolve();
        Lit446 = simpleSymbol95;
        SimpleSymbol simpleSymbol96 = (SimpleSymbol) new SimpleSymbol("name").readResolve();
        Lit445 = simpleSymbol96;
        SimpleSymbol simpleSymbol97 = (SimpleSymbol) new SimpleSymbol("android-log-form").readResolve();
        Lit444 = simpleSymbol97;
        SimpleSymbol simpleSymbol98 = (SimpleSymbol) new SimpleSymbol("add-to-form-environment").readResolve();
        Lit443 = simpleSymbol98;
        SimpleSymbol simpleSymbol99 = (SimpleSymbol) new SimpleSymbol("gnu.mapping.Environment").readResolve();
        Lit442 = simpleSymbol99;
        SimpleSymbol simpleSymbol100 = (SimpleSymbol) new SimpleSymbol("message").readResolve();
        Lit441 = simpleSymbol100;
        SimpleSymbol simpleSymbol101 = (SimpleSymbol) new SimpleSymbol("*debug-form*").readResolve();
        Lit440 = simpleSymbol101;
        SimpleSymbol simpleSymbol102 = (SimpleSymbol) new SimpleSymbol("icicle").readResolve();
        Lit439 = simpleSymbol102;
        SimpleSymbol simpleSymbol103 = (SimpleSymbol) new SimpleSymbol("onCreate").readResolve();
        Lit438 = simpleSymbol103;
        SimpleSymbol simpleSymbol104 = (SimpleSymbol) new SimpleSymbol("::").readResolve();
        Lit437 = simpleSymbol104;
        SimpleSymbol simpleSymbol105 = (SimpleSymbol) new SimpleSymbol("object").readResolve();
        Lit436 = simpleSymbol105;
        SimpleSymbol simpleSymbol106 = (SimpleSymbol) new SimpleSymbol("*").readResolve();
        Lit435 = simpleSymbol106;
        SimpleSymbol simpleSymbol107 = (SimpleSymbol) new SimpleSymbol("define").readResolve();
        Lit434 = simpleSymbol107;
        SimpleSymbol simpleSymbol108 = (SimpleSymbol) new SimpleSymbol("add-to-global-vars").readResolve();
        Lit433 = simpleSymbol108;
        SimpleSymbol simpleSymbol109 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quote_sym).readResolve();
        Lit432 = simpleSymbol109;
        SimpleSymbol simpleSymbol110 = (SimpleSymbol) new SimpleSymbol("*this-is-the-repl*").readResolve();
        Lit431 = simpleSymbol110;
        SimpleSymbol simpleSymbol111 = (SimpleSymbol) new SimpleSymbol("delay").readResolve();
        Lit430 = simpleSymbol111;
        SimpleSymbol simpleSymbol112 = (SimpleSymbol) new SimpleSymbol("proc").readResolve();
        Lit429 = simpleSymbol112;
        SimpleSymbol simpleSymbol113 = (SimpleSymbol) new SimpleSymbol("*yail-loop*").readResolve();
        Lit428 = simpleSymbol113;
        SimpleSymbol simpleSymbol114 = (SimpleSymbol) new SimpleSymbol("begin").readResolve();
        Lit427 = simpleSymbol114;
        SimpleSymbol simpleSymbol115 = (SimpleSymbol) new SimpleSymbol("let").readResolve();
        Lit426 = simpleSymbol115;
        SimpleSymbol simpleSymbol116 = (SimpleSymbol) new SimpleSymbol("lambda").readResolve();
        Lit425 = simpleSymbol116;
        SimpleSymbol simpleSymbol117 = (SimpleSymbol) new SimpleSymbol("call-with-current-continuation").readResolve();
        Lit424 = simpleSymbol117;
        SimpleSymbol simpleSymbol118 = (SimpleSymbol) new SimpleSymbol("loop").readResolve();
        Lit423 = simpleSymbol118;
        SimpleSymbol simpleSymbol119 = (SimpleSymbol) new SimpleSymbol("if").readResolve();
        Lit422 = simpleSymbol119;
        SimpleSymbol simpleSymbol120 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quasiquote_sym).readResolve();
        Lit421 = simpleSymbol120;
        SimpleSymbol simpleSymbol121 = (SimpleSymbol) new SimpleSymbol("$lookup$").readResolve();
        Lit420 = simpleSymbol121;
        SimpleSymbol simpleSymbol122 = (SimpleSymbol) new SimpleSymbol("_").readResolve();
        Lit419 = simpleSymbol122;
        SimpleSymbol simpleSymbol123 = (SimpleSymbol) new SimpleSymbol("clarify1").readResolve();
        Lit418 = simpleSymbol123;
        SimpleSymbol simpleSymbol124 = (SimpleSymbol) new SimpleSymbol("clarify").readResolve();
        Lit417 = simpleSymbol124;
        SimpleSymbol simpleSymbol125 = (SimpleSymbol) new SimpleSymbol("set-this-form").readResolve();
        Lit416 = simpleSymbol125;
        SimpleSymbol simpleSymbol126 = (SimpleSymbol) new SimpleSymbol("init-runtime").readResolve();
        Lit415 = simpleSymbol126;
        SimpleSymbol simpleSymbol127 = (SimpleSymbol) new SimpleSymbol("rename-component").readResolve();
        Lit414 = simpleSymbol127;
        SimpleSymbol simpleSymbol128 = (SimpleSymbol) new SimpleSymbol("remove-component").readResolve();
        Lit413 = simpleSymbol128;
        SimpleSymbol simpleSymbol129 = (SimpleSymbol) new SimpleSymbol("set-form-name").readResolve();
        Lit412 = simpleSymbol129;
        SimpleSymbol simpleSymbol130 = (SimpleSymbol) new SimpleSymbol("clear-current-form").readResolve();
        Lit411 = simpleSymbol130;
        SimpleSymbol simpleSymbol131 = (SimpleSymbol) new SimpleSymbol("send-to-block").readResolve();
        Lit410 = simpleSymbol131;
        SimpleSymbol simpleSymbol132 = (SimpleSymbol) new SimpleSymbol("in-ui").readResolve();
        Lit409 = simpleSymbol132;
        SyntaxRules syntaxRules = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\b\u000b", new Object[]{simpleSymbol132, simpleSymbol111}, 0)}, 2);
        Lit408 = syntaxRules;
        SimpleSymbol simpleSymbol133 = (SimpleSymbol) new SimpleSymbol("process-repl-input").readResolve();
        Lit407 = simpleSymbol133;
        SimpleSymbol simpleSymbol134 = (SimpleSymbol) new SimpleSymbol("get-server-address-from-wifi").readResolve();
        Lit406 = simpleSymbol134;
        SimpleSymbol simpleSymbol135 = (SimpleSymbol) new SimpleSymbol("close-screen-with-plain-text").readResolve();
        Lit405 = simpleSymbol135;
        SimpleSymbol simpleSymbol136 = (SimpleSymbol) new SimpleSymbol("get-plain-start-text").readResolve();
        Lit404 = simpleSymbol136;
        SimpleSymbol simpleSymbol137 = (SimpleSymbol) new SimpleSymbol("close-screen-with-value").readResolve();
        Lit403 = simpleSymbol137;
        SimpleSymbol simpleSymbol138 = (SimpleSymbol) new SimpleSymbol("get-start-value").readResolve();
        Lit402 = simpleSymbol138;
        SimpleSymbol simpleSymbol139 = (SimpleSymbol) new SimpleSymbol("open-another-screen-with-start-value").readResolve();
        Lit401 = simpleSymbol139;
        SimpleSymbol simpleSymbol140 = (SimpleSymbol) new SimpleSymbol("open-another-screen").readResolve();
        Lit400 = simpleSymbol140;
        SimpleSymbol simpleSymbol141 = (SimpleSymbol) new SimpleSymbol("close-application").readResolve();
        Lit399 = simpleSymbol141;
        SimpleSymbol simpleSymbol142 = (SimpleSymbol) new SimpleSymbol("close-screen").readResolve();
        Lit398 = simpleSymbol142;
        SimpleSymbol simpleSymbol143 = (SimpleSymbol) new SimpleSymbol("split-color").readResolve();
        Lit397 = simpleSymbol143;
        SimpleSymbol simpleSymbol144 = (SimpleSymbol) new SimpleSymbol("make-color").readResolve();
        Lit396 = simpleSymbol144;
        SimpleSymbol simpleSymbol145 = (SimpleSymbol) new SimpleSymbol("make-exact-yail-integer").readResolve();
        Lit395 = simpleSymbol145;
        SimpleSymbol simpleSymbol146 = (SimpleSymbol) new SimpleSymbol("string-replace-mappings-earliest-occurrence").readResolve();
        Lit394 = simpleSymbol146;
        SimpleSymbol simpleSymbol147 = (SimpleSymbol) new SimpleSymbol("string-replace-mappings-longest-string").readResolve();
        Lit393 = simpleSymbol147;
        SimpleSymbol simpleSymbol148 = (SimpleSymbol) new SimpleSymbol("string-replace-mappings-dictionary").readResolve();
        Lit392 = simpleSymbol148;
        SimpleSymbol simpleSymbol149 = (SimpleSymbol) new SimpleSymbol("text-deobfuscate").readResolve();
        Lit391 = simpleSymbol149;
        SimpleSymbol simpleSymbol150 = (SimpleSymbol) new SimpleSymbol("string-empty?").readResolve();
        Lit390 = simpleSymbol150;
        SimpleSymbol simpleSymbol151 = (SimpleSymbol) new SimpleSymbol("string-replace-all").readResolve();
        Lit389 = simpleSymbol151;
        SimpleSymbol simpleSymbol152 = (SimpleSymbol) new SimpleSymbol("string-trim").readResolve();
        Lit388 = simpleSymbol152;
        SimpleSymbol simpleSymbol153 = (SimpleSymbol) new SimpleSymbol("string-substring").readResolve();
        Lit387 = simpleSymbol153;
        SimpleSymbol simpleSymbol154 = (SimpleSymbol) new SimpleSymbol("string-split-at-spaces").readResolve();
        Lit386 = simpleSymbol154;
        SimpleSymbol simpleSymbol155 = (SimpleSymbol) new SimpleSymbol("string-split-at-any").readResolve();
        Lit385 = simpleSymbol155;
        SimpleSymbol simpleSymbol156 = (SimpleSymbol) new SimpleSymbol("string-split").readResolve();
        Lit384 = simpleSymbol156;
        SimpleSymbol simpleSymbol157 = (SimpleSymbol) new SimpleSymbol("string-split-at-first-of-any").readResolve();
        Lit383 = simpleSymbol157;
        SimpleSymbol simpleSymbol158 = (SimpleSymbol) new SimpleSymbol("string-split-at-first").readResolve();
        Lit382 = simpleSymbol158;
        SimpleSymbol simpleSymbol159 = (SimpleSymbol) new SimpleSymbol("string-contains-all").readResolve();
        Lit381 = simpleSymbol159;
        SimpleSymbol simpleSymbol160 = (SimpleSymbol) new SimpleSymbol("string-contains-any").readResolve();
        Lit380 = simpleSymbol160;
        SimpleSymbol simpleSymbol161 = (SimpleSymbol) new SimpleSymbol("string-contains").readResolve();
        Lit379 = simpleSymbol161;
        SimpleSymbol simpleSymbol162 = (SimpleSymbol) new SimpleSymbol("string-starts-at").readResolve();
        Lit378 = simpleSymbol162;
        SimpleSymbol simpleSymbol163 = (SimpleSymbol) new SimpleSymbol("array->list").readResolve();
        Lit377 = simpleSymbol163;
        SimpleSymbol simpleSymbol164 = (SimpleSymbol) new SimpleSymbol("make-disjunct").readResolve();
        Lit376 = simpleSymbol164;
        SimpleSymbol simpleSymbol165 = (SimpleSymbol) new SimpleSymbol("yail-dictionary?").readResolve();
        Lit375 = simpleSymbol165;
        SimpleSymbol simpleSymbol166 = (SimpleSymbol) new SimpleSymbol("yail-dictionary-combine-dicts").readResolve();
        Lit374 = simpleSymbol166;
        SimpleSymbol simpleSymbol167 = (SimpleSymbol) new SimpleSymbol("yail-dictionary-copy").readResolve();
        Lit373 = simpleSymbol167;
        SimpleSymbol simpleSymbol168 = (SimpleSymbol) new SimpleSymbol("yail-dictionary-dict-to-alist").readResolve();
        Lit372 = simpleSymbol168;
        SimpleSymbol simpleSymbol169 = (SimpleSymbol) new SimpleSymbol("yail-dictionary-alist-to-dict").readResolve();
        Lit371 = simpleSymbol169;
        SimpleSymbol simpleSymbol170 = (SimpleSymbol) new SimpleSymbol("yail-dictionary-length").readResolve();
        Lit370 = simpleSymbol170;
        SimpleSymbol simpleSymbol171 = (SimpleSymbol) new SimpleSymbol("yail-dictionary-is-key-in").readResolve();
        Lit369 = simpleSymbol171;
        SimpleSymbol simpleSymbol172 = (SimpleSymbol) new SimpleSymbol("yail-dictionary-get-values").readResolve();
        Lit368 = simpleSymbol172;
        SimpleSymbol simpleSymbol173 = (SimpleSymbol) new SimpleSymbol("yail-dictionary-get-keys").readResolve();
        Lit367 = simpleSymbol173;
        SimpleSymbol simpleSymbol174 = (SimpleSymbol) new SimpleSymbol("yail-dictionary-recursive-set").readResolve();
        Lit366 = simpleSymbol174;
        SimpleSymbol simpleSymbol175 = (SimpleSymbol) new SimpleSymbol("yail-dictionary-walk").readResolve();
        Lit365 = simpleSymbol175;
        SimpleSymbol simpleSymbol176 = (SimpleSymbol) new SimpleSymbol("yail-dictionary-recursive-lookup").readResolve();
        Lit364 = simpleSymbol176;
        SimpleSymbol simpleSymbol177 = (SimpleSymbol) new SimpleSymbol("yail-dictionary-lookup").readResolve();
        Lit363 = simpleSymbol177;
        SimpleSymbol simpleSymbol178 = (SimpleSymbol) new SimpleSymbol("yail-dictionary-delete-pair").readResolve();
        Lit362 = simpleSymbol178;
        SimpleSymbol simpleSymbol179 = (SimpleSymbol) new SimpleSymbol("yail-dictionary-set-pair").readResolve();
        Lit361 = simpleSymbol179;
        SimpleSymbol simpleSymbol180 = (SimpleSymbol) new SimpleSymbol("make-dictionary-pair").readResolve();
        Lit360 = simpleSymbol180;
        SimpleSymbol simpleSymbol181 = (SimpleSymbol) new SimpleSymbol("make-yail-dictionary").readResolve();
        Lit359 = simpleSymbol181;
        SimpleSymbol simpleSymbol182 = (SimpleSymbol) new SimpleSymbol("yail-list-join-with-separator").readResolve();
        Lit358 = simpleSymbol182;
        SimpleSymbol simpleSymbol183 = (SimpleSymbol) new SimpleSymbol("pair-ok?").readResolve();
        Lit357 = simpleSymbol183;
        SimpleSymbol simpleSymbol184 = (SimpleSymbol) new SimpleSymbol("yail-alist-lookup").readResolve();
        Lit356 = simpleSymbol184;
        SimpleSymbol simpleSymbol185 = (SimpleSymbol) new SimpleSymbol("yail-number-range").readResolve();
        Lit355 = simpleSymbol185;
        SimpleSymbol simpleSymbol186 = (SimpleSymbol) new SimpleSymbol("yail-for-range-with-numeric-checked-args").readResolve();
        Lit354 = simpleSymbol186;
        SimpleSymbol simpleSymbol187 = (SimpleSymbol) new SimpleSymbol("yail-for-range").readResolve();
        Lit353 = simpleSymbol187;
        SimpleSymbol simpleSymbol188 = (SimpleSymbol) new SimpleSymbol("yail-list-slice").readResolve();
        Lit352 = simpleSymbol188;
        SimpleSymbol simpleSymbol189 = (SimpleSymbol) new SimpleSymbol("back").readResolve();
        Lit351 = simpleSymbol189;
        SimpleSymbol simpleSymbol190 = (SimpleSymbol) new SimpleSymbol("front").readResolve();
        Lit350 = simpleSymbol190;
        SimpleSymbol simpleSymbol191 = (SimpleSymbol) new SimpleSymbol("yail-list-but-last").readResolve();
        Lit349 = simpleSymbol191;
        SimpleSymbol simpleSymbol192 = (SimpleSymbol) new SimpleSymbol("but-last").readResolve();
        Lit348 = simpleSymbol192;
        SimpleSymbol simpleSymbol193 = (SimpleSymbol) new SimpleSymbol("yail-list-but-first").readResolve();
        Lit347 = simpleSymbol193;
        SimpleSymbol simpleSymbol194 = (SimpleSymbol) new SimpleSymbol("yail-list-max-comparator").readResolve();
        Lit346 = simpleSymbol194;
        SimpleSymbol simpleSymbol195 = (SimpleSymbol) new SimpleSymbol("list-max").readResolve();
        Lit345 = simpleSymbol195;
        SimpleSymbol simpleSymbol196 = (SimpleSymbol) new SimpleSymbol("yail-list-min-comparator").readResolve();
        Lit344 = simpleSymbol196;
        SimpleSymbol simpleSymbol197 = (SimpleSymbol) new SimpleSymbol("list-min").readResolve();
        Lit343 = simpleSymbol197;
        SimpleSymbol simpleSymbol198 = (SimpleSymbol) new SimpleSymbol("list-number-only").readResolve();
        Lit342 = simpleSymbol198;
        SimpleSymbol simpleSymbol199 = (SimpleSymbol) new SimpleSymbol("yail-list-sort-key").readResolve();
        Lit341 = simpleSymbol199;
        SimpleSymbol simpleSymbol200 = (SimpleSymbol) new SimpleSymbol("mergesort-key").readResolve();
        Lit340 = simpleSymbol200;
        SimpleSymbol simpleSymbol201 = (SimpleSymbol) new SimpleSymbol("merge-key").readResolve();
        Lit339 = simpleSymbol201;
        SimpleSymbol simpleSymbol202 = (SimpleSymbol) new SimpleSymbol("yail-list-sort-comparator").readResolve();
        Lit338 = simpleSymbol202;
        SimpleSymbol simpleSymbol203 = (SimpleSymbol) new SimpleSymbol("yail-list-sort").readResolve();
        Lit337 = simpleSymbol203;
        SimpleSymbol simpleSymbol204 = (SimpleSymbol) new SimpleSymbol("mergesort").readResolve();
        Lit336 = simpleSymbol204;
        SimpleSymbol simpleSymbol205 = (SimpleSymbol) new SimpleSymbol("merge").readResolve();
        Lit335 = simpleSymbol205;
        SimpleSymbol simpleSymbol206 = (SimpleSymbol) new SimpleSymbol("drop").readResolve();
        Lit334 = simpleSymbol206;
        SimpleSymbol simpleSymbol207 = (SimpleSymbol) new SimpleSymbol("take").readResolve();
        Lit333 = simpleSymbol207;
        SimpleSymbol simpleSymbol208 = (SimpleSymbol) new SimpleSymbol("component-leq?").readResolve();
        Lit332 = simpleSymbol208;
        SimpleSymbol simpleSymbol209 = (SimpleSymbol) new SimpleSymbol("component-eq?").readResolve();
        Lit331 = simpleSymbol209;
        SimpleSymbol simpleSymbol210 = (SimpleSymbol) new SimpleSymbol("component-lt?").readResolve();
        Lit330 = simpleSymbol210;
        SimpleSymbol simpleSymbol211 = (SimpleSymbol) new SimpleSymbol("list-leq?").readResolve();
        Lit329 = simpleSymbol211;
        SimpleSymbol simpleSymbol212 = (SimpleSymbol) new SimpleSymbol("yail-list-necessary").readResolve();
        Lit328 = simpleSymbol212;
        SimpleSymbol simpleSymbol213 = (SimpleSymbol) new SimpleSymbol("list-eq?").readResolve();
        Lit327 = simpleSymbol213;
        SimpleSymbol simpleSymbol214 = (SimpleSymbol) new SimpleSymbol("list-lt?").readResolve();
        Lit326 = simpleSymbol214;
        SimpleSymbol simpleSymbol215 = (SimpleSymbol) new SimpleSymbol("boolean-leq?").readResolve();
        Lit325 = simpleSymbol215;
        SimpleSymbol simpleSymbol216 = (SimpleSymbol) new SimpleSymbol("boolean-eq?").readResolve();
        Lit324 = simpleSymbol216;
        SimpleSymbol simpleSymbol217 = (SimpleSymbol) new SimpleSymbol("boolean-lt?").readResolve();
        Lit323 = simpleSymbol217;
        SimpleSymbol simpleSymbol218 = (SimpleSymbol) new SimpleSymbol("is-leq?").readResolve();
        Lit322 = simpleSymbol218;
        SimpleSymbol simpleSymbol219 = (SimpleSymbol) new SimpleSymbol("is-eq?").readResolve();
        Lit321 = simpleSymbol219;
        SimpleSymbol simpleSymbol220 = (SimpleSymbol) new SimpleSymbol("is-lt?").readResolve();
        Lit320 = simpleSymbol220;
        SimpleSymbol simpleSymbol221 = (SimpleSymbol) new SimpleSymbol("type-lt?").readResolve();
        Lit319 = simpleSymbol221;
        SimpleSymbol simpleSymbol222 = (SimpleSymbol) new SimpleSymbol("indexof").readResolve();
        Lit318 = simpleSymbol222;
        SimpleSymbol simpleSymbol223 = (SimpleSymbol) new SimpleSymbol("typeof").readResolve();
        Lit317 = simpleSymbol223;
        SimpleSymbol simpleSymbol224 = (SimpleSymbol) new SimpleSymbol("yail-list-reduce").readResolve();
        Lit316 = simpleSymbol224;
        SimpleSymbol simpleSymbol225 = (SimpleSymbol) new SimpleSymbol("yail-list-filter").readResolve();
        Lit315 = simpleSymbol225;
        SimpleSymbol simpleSymbol226 = (SimpleSymbol) new SimpleSymbol("yail-list-map").readResolve();
        Lit314 = simpleSymbol226;
        SimpleSymbol simpleSymbol227 = (SimpleSymbol) new SimpleSymbol("yail-for-each").readResolve();
        Lit313 = simpleSymbol227;
        SimpleSymbol simpleSymbol228 = (SimpleSymbol) new SimpleSymbol("yail-list-pick-random").readResolve();
        Lit312 = simpleSymbol228;
        SimpleSymbol simpleSymbol229 = (SimpleSymbol) new SimpleSymbol("yail-list-member?").readResolve();
        Lit311 = simpleSymbol229;
        SimpleSymbol simpleSymbol230 = (SimpleSymbol) new SimpleSymbol("yail-list-add-to-list!").readResolve();
        Lit310 = simpleSymbol230;
        SimpleSymbol simpleSymbol231 = (SimpleSymbol) new SimpleSymbol("yail-list-append!").readResolve();
        Lit309 = simpleSymbol231;
        SimpleSymbol simpleSymbol232 = (SimpleSymbol) new SimpleSymbol("yail-list-insert-item!").readResolve();
        Lit308 = simpleSymbol232;
        SimpleSymbol simpleSymbol233 = (SimpleSymbol) new SimpleSymbol("yail-list-remove-item!").readResolve();
        Lit307 = simpleSymbol233;
        SimpleSymbol simpleSymbol234 = (SimpleSymbol) new SimpleSymbol("yail-list-set-item!").readResolve();
        Lit306 = simpleSymbol234;
        SimpleSymbol simpleSymbol235 = (SimpleSymbol) new SimpleSymbol("yail-list-get-item").readResolve();
        Lit305 = simpleSymbol235;
        SimpleSymbol simpleSymbol236 = (SimpleSymbol) new SimpleSymbol("yail-list-index").readResolve();
        Lit304 = simpleSymbol236;
        SimpleSymbol simpleSymbol237 = (SimpleSymbol) new SimpleSymbol("yail-list-length").readResolve();
        Lit303 = simpleSymbol237;
        SimpleSymbol simpleSymbol238 = (SimpleSymbol) new SimpleSymbol("yail-list-from-csv-row").readResolve();
        Lit302 = simpleSymbol238;
        SimpleSymbol simpleSymbol239 = (SimpleSymbol) new SimpleSymbol("yail-list-from-csv-table").readResolve();
        Lit301 = simpleSymbol239;
        SimpleSymbol simpleSymbol240 = (SimpleSymbol) new SimpleSymbol("convert-to-strings-for-csv").readResolve();
        Lit300 = simpleSymbol240;
        SimpleSymbol simpleSymbol241 = (SimpleSymbol) new SimpleSymbol("yail-list-to-csv-row").readResolve();
        Lit299 = simpleSymbol241;
        SimpleSymbol simpleSymbol242 = (SimpleSymbol) new SimpleSymbol("yail-list-to-csv-table").readResolve();
        Lit298 = simpleSymbol242;
        SimpleSymbol simpleSymbol243 = (SimpleSymbol) new SimpleSymbol("yail-list-reverse").readResolve();
        Lit297 = simpleSymbol243;
        SimpleSymbol simpleSymbol244 = (SimpleSymbol) new SimpleSymbol("yail-list-copy").readResolve();
        Lit296 = simpleSymbol244;
        SimpleSymbol simpleSymbol245 = (SimpleSymbol) new SimpleSymbol("make-yail-list").readResolve();
        Lit295 = simpleSymbol245;
        SimpleSymbol simpleSymbol246 = (SimpleSymbol) new SimpleSymbol("yail-list-empty?").readResolve();
        Lit294 = simpleSymbol246;
        SimpleSymbol simpleSymbol247 = (SimpleSymbol) new SimpleSymbol("yail-list->kawa-list").readResolve();
        Lit293 = simpleSymbol247;
        SimpleSymbol simpleSymbol248 = (SimpleSymbol) new SimpleSymbol("kawa-list->yail-list").readResolve();
        Lit292 = simpleSymbol248;
        SimpleSymbol simpleSymbol249 = (SimpleSymbol) new SimpleSymbol("insert-yail-list-header").readResolve();
        Lit291 = simpleSymbol249;
        SimpleSymbol simpleSymbol250 = (SimpleSymbol) new SimpleSymbol("set-yail-list-contents!").readResolve();
        Lit290 = simpleSymbol250;
        SimpleSymbol simpleSymbol251 = (SimpleSymbol) new SimpleSymbol("yail-list-contents").readResolve();
        Lit289 = simpleSymbol251;
        SimpleSymbol simpleSymbol252 = (SimpleSymbol) new SimpleSymbol("yail-list-candidate?").readResolve();
        Lit288 = simpleSymbol252;
        SimpleSymbol simpleSymbol253 = (SimpleSymbol) new SimpleSymbol("yail-list?").readResolve();
        Lit287 = simpleSymbol253;
        SimpleSymbol simpleSymbol254 = (SimpleSymbol) new SimpleSymbol("std-err").readResolve();
        Lit286 = simpleSymbol254;
        SimpleSymbol simpleSymbol255 = (SimpleSymbol) new SimpleSymbol("sample-std-dev").readResolve();
        Lit285 = simpleSymbol255;
        SimpleSymbol simpleSymbol256 = (SimpleSymbol) new SimpleSymbol("std-dev").readResolve();
        Lit284 = simpleSymbol256;
        SimpleSymbol simpleSymbol257 = (SimpleSymbol) new SimpleSymbol("sum-mean-square-diff").readResolve();
        Lit283 = simpleSymbol257;
        SimpleSymbol simpleSymbol258 = (SimpleSymbol) new SimpleSymbol("mean").readResolve();
        Lit282 = simpleSymbol258;
        SimpleSymbol simpleSymbol259 = (SimpleSymbol) new SimpleSymbol("minl").readResolve();
        Lit281 = simpleSymbol259;
        SimpleSymbol simpleSymbol260 = (SimpleSymbol) new SimpleSymbol("maxl").readResolve();
        Lit280 = simpleSymbol260;
        SimpleSymbol simpleSymbol261 = (SimpleSymbol) new SimpleSymbol("mode").readResolve();
        Lit279 = simpleSymbol261;
        SimpleSymbol simpleSymbol262 = (SimpleSymbol) new SimpleSymbol("gm").readResolve();
        Lit278 = simpleSymbol262;
        SimpleSymbol simpleSymbol263 = (SimpleSymbol) new SimpleSymbol("yail-mul").readResolve();
        Lit277 = simpleSymbol263;
        SimpleSymbol simpleSymbol264 = (SimpleSymbol) new SimpleSymbol("avg").readResolve();
        Lit276 = simpleSymbol264;
        SimpleSymbol simpleSymbol265 = (SimpleSymbol) new SimpleSymbol("internal-binary-convert").readResolve();
        Lit275 = simpleSymbol265;
        SimpleSymbol simpleSymbol266 = (SimpleSymbol) new SimpleSymbol("alternate-number->string-binary").readResolve();
        Lit274 = simpleSymbol266;
        SimpleSymbol simpleSymbol267 = (SimpleSymbol) new SimpleSymbol("patched-number->string-binary").readResolve();
        Lit273 = simpleSymbol267;
        SimpleSymbol simpleSymbol268 = (SimpleSymbol) new SimpleSymbol("math-convert-dec-bin").readResolve();
        Lit272 = simpleSymbol268;
        SimpleSymbol simpleSymbol269 = (SimpleSymbol) new SimpleSymbol("math-convert-bin-dec").readResolve();
        Lit271 = simpleSymbol269;
        SimpleSymbol simpleSymbol270 = (SimpleSymbol) new SimpleSymbol("math-convert-hex-dec").readResolve();
        Lit270 = simpleSymbol270;
        SimpleSymbol simpleSymbol271 = (SimpleSymbol) new SimpleSymbol("math-convert-dec-hex").readResolve();
        Lit269 = simpleSymbol271;
        SimpleSymbol simpleSymbol272 = (SimpleSymbol) new SimpleSymbol("is-binary?").readResolve();
        Lit268 = simpleSymbol272;
        SimpleSymbol simpleSymbol273 = (SimpleSymbol) new SimpleSymbol("is-hexadecimal?").readResolve();
        Lit267 = simpleSymbol273;
        SimpleSymbol simpleSymbol274 = (SimpleSymbol) new SimpleSymbol("is-base10?").readResolve();
        Lit266 = simpleSymbol274;
        SimpleSymbol simpleSymbol275 = (SimpleSymbol) new SimpleSymbol("is-number?").readResolve();
        Lit265 = simpleSymbol275;
        SimpleSymbol simpleSymbol276 = (SimpleSymbol) new SimpleSymbol("format-as-decimal").readResolve();
        Lit264 = simpleSymbol276;
        SimpleSymbol simpleSymbol277 = (SimpleSymbol) new SimpleSymbol("string-reverse").readResolve();
        Lit263 = simpleSymbol277;
        SimpleSymbol simpleSymbol278 = (SimpleSymbol) new SimpleSymbol("unicode-string->list").readResolve();
        Lit262 = simpleSymbol278;
        SimpleSymbol simpleSymbol279 = (SimpleSymbol) new SimpleSymbol("string-to-lower-case").readResolve();
        Lit261 = simpleSymbol279;
        SimpleSymbol simpleSymbol280 = (SimpleSymbol) new SimpleSymbol("string-to-upper-case").readResolve();
        Lit260 = simpleSymbol280;
        SimpleSymbol simpleSymbol281 = (SimpleSymbol) new SimpleSymbol("atan2-degrees").readResolve();
        Lit259 = simpleSymbol281;
        SimpleSymbol simpleSymbol282 = (SimpleSymbol) new SimpleSymbol("atan-degrees").readResolve();
        Lit258 = simpleSymbol282;
        SimpleSymbol simpleSymbol283 = (SimpleSymbol) new SimpleSymbol("acos-degrees").readResolve();
        Lit257 = simpleSymbol283;
        SimpleSymbol simpleSymbol284 = (SimpleSymbol) new SimpleSymbol("asin-degrees").readResolve();
        Lit256 = simpleSymbol284;
        SimpleSymbol simpleSymbol285 = (SimpleSymbol) new SimpleSymbol("tan-degrees").readResolve();
        Lit255 = simpleSymbol285;
        SimpleSymbol simpleSymbol286 = (SimpleSymbol) new SimpleSymbol("cos-degrees").readResolve();
        Lit254 = simpleSymbol286;
        SimpleSymbol simpleSymbol287 = (SimpleSymbol) new SimpleSymbol("sin-degrees").readResolve();
        Lit253 = simpleSymbol287;
        SimpleSymbol simpleSymbol288 = (SimpleSymbol) new SimpleSymbol("radians->degrees").readResolve();
        Lit252 = simpleSymbol288;
        SimpleSymbol simpleSymbol289 = (SimpleSymbol) new SimpleSymbol("degrees->radians").readResolve();
        Lit251 = simpleSymbol289;
        SimpleSymbol simpleSymbol290 = (SimpleSymbol) new SimpleSymbol("radians->degrees-internal").readResolve();
        Lit250 = simpleSymbol290;
        SimpleSymbol simpleSymbol291 = (SimpleSymbol) new SimpleSymbol("degrees->radians-internal").readResolve();
        Lit249 = simpleSymbol291;
        SimpleSymbol simpleSymbol292 = (SimpleSymbol) new SimpleSymbol("yail-divide").readResolve();
        Lit248 = simpleSymbol292;
        SimpleSymbol simpleSymbol293 = (SimpleSymbol) new SimpleSymbol("random-integer").readResolve();
        Lit247 = simpleSymbol293;
        SimpleSymbol simpleSymbol294 = (SimpleSymbol) new SimpleSymbol("random-fraction").readResolve();
        Lit246 = simpleSymbol294;
        SimpleSymbol simpleSymbol295 = (SimpleSymbol) new SimpleSymbol("random-set-seed").readResolve();
        Lit245 = simpleSymbol295;
        SimpleSymbol simpleSymbol296 = (SimpleSymbol) new SimpleSymbol("yail-round").readResolve();
        Lit244 = simpleSymbol296;
        SimpleSymbol simpleSymbol297 = (SimpleSymbol) new SimpleSymbol("yail-ceiling").readResolve();
        Lit243 = simpleSymbol297;
        SimpleSymbol simpleSymbol298 = (SimpleSymbol) new SimpleSymbol("yail-floor").readResolve();
        Lit242 = simpleSymbol298;
        SimpleSymbol simpleSymbol299 = (SimpleSymbol) new SimpleSymbol("process-or-delayed").readResolve();
        Lit241 = simpleSymbol299;
        SimpleSymbol simpleSymbol300 = (SimpleSymbol) new SimpleSymbol("process-and-delayed").readResolve();
        Lit240 = simpleSymbol300;
        SimpleSymbol simpleSymbol301 = (SimpleSymbol) new SimpleSymbol("yail-not-equal?").readResolve();
        Lit239 = simpleSymbol301;
        SimpleSymbol simpleSymbol302 = (SimpleSymbol) new SimpleSymbol("as-number").readResolve();
        Lit238 = simpleSymbol302;
        SimpleSymbol simpleSymbol303 = (SimpleSymbol) new SimpleSymbol("yail-atomic-equal?").readResolve();
        Lit237 = simpleSymbol303;
        SimpleSymbol simpleSymbol304 = (SimpleSymbol) new SimpleSymbol("yail-equal?").readResolve();
        Lit236 = simpleSymbol304;
        SimpleSymbol simpleSymbol305 = (SimpleSymbol) new SimpleSymbol("appinventor-number->string").readResolve();
        Lit235 = simpleSymbol305;
        SimpleSymbol simpleSymbol306 = (SimpleSymbol) new SimpleSymbol("*format-inexact*").readResolve();
        Lit234 = simpleSymbol306;
        SimpleSymbol simpleSymbol307 = (SimpleSymbol) new SimpleSymbol("padded-string->number").readResolve();
        Lit233 = simpleSymbol307;
        SimpleSymbol simpleSymbol308 = (SimpleSymbol) new SimpleSymbol("boolean->string").readResolve();
        Lit232 = simpleSymbol308;
        SimpleSymbol simpleSymbol309 = (SimpleSymbol) new SimpleSymbol("all-coercible?").readResolve();
        Lit231 = simpleSymbol309;
        SimpleSymbol simpleSymbol310 = (SimpleSymbol) new SimpleSymbol("is-coercible?").readResolve();
        Lit230 = simpleSymbol310;
        SimpleSymbol simpleSymbol311 = (SimpleSymbol) new SimpleSymbol("coerce-to-boolean").readResolve();
        Lit229 = simpleSymbol311;
        SimpleSymbol simpleSymbol312 = (SimpleSymbol) new SimpleSymbol("coerce-to-dictionary").readResolve();
        Lit228 = simpleSymbol312;
        SimpleSymbol simpleSymbol313 = (SimpleSymbol) new SimpleSymbol("coerce-to-pair").readResolve();
        Lit227 = simpleSymbol313;
        SimpleSymbol simpleSymbol314 = (SimpleSymbol) new SimpleSymbol("coerce-to-yail-list").readResolve();
        Lit226 = simpleSymbol314;
        SimpleSymbol simpleSymbol315 = (SimpleSymbol) new SimpleSymbol("string-replace").readResolve();
        Lit225 = simpleSymbol315;
        SimpleSymbol simpleSymbol316 = (SimpleSymbol) new SimpleSymbol("join-strings").readResolve();
        Lit224 = simpleSymbol316;
        SimpleSymbol simpleSymbol317 = (SimpleSymbol) new SimpleSymbol("get-display-representation").readResolve();
        Lit223 = simpleSymbol317;
        SimpleSymbol simpleSymbol318 = (SimpleSymbol) new SimpleSymbol("coerce-to-string").readResolve();
        Lit222 = simpleSymbol318;
        SyntaxRules syntaxRules2 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\b", new Object[0], 0), "", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol119, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("*testing*").readResolve(), PairWithPosition.make(Boolean.TRUE, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol106, Pair.make(Pair.make(simpleSymbol120, Pair.make((SimpleSymbol) new SimpleSymbol("ShowListsAsJson").readResolve(), LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 6541323), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make((SimpleSymbol) new SimpleSymbol("SimpleForm").readResolve(), Pair.make(Pair.make(simpleSymbol120, Pair.make((SimpleSymbol) new SimpleSymbol("getActiveForm").readResolve(), LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 6541342), LList.Empty, "/tmp/runtime4411419262522357548.scm", 6541341), LList.Empty, "/tmp/runtime4411419262522357548.scm", 6541341), "/tmp/runtime4411419262522357548.scm", 6541322), LList.Empty, "/tmp/runtime4411419262522357548.scm", 6541322), "/tmp/runtime4411419262522357548.scm", 6537236), "/tmp/runtime4411419262522357548.scm", 6537226), "/tmp/runtime4411419262522357548.scm", 6537222)}, 0)}, 0);
        Lit221 = syntaxRules2;
        SimpleSymbol simpleSymbol319 = (SimpleSymbol) new SimpleSymbol("use-json-format").readResolve();
        Lit220 = simpleSymbol319;
        SimpleSymbol simpleSymbol320 = (SimpleSymbol) new SimpleSymbol("coerce-to-key").readResolve();
        Lit219 = simpleSymbol320;
        SimpleSymbol simpleSymbol321 = (SimpleSymbol) new SimpleSymbol("coerce-to-number").readResolve();
        Lit218 = simpleSymbol321;
        SimpleSymbol simpleSymbol322 = (SimpleSymbol) new SimpleSymbol("type->class").readResolve();
        Lit217 = simpleSymbol322;
        SimpleSymbol simpleSymbol323 = (SimpleSymbol) new SimpleSymbol("coerce-to-component-of-type").readResolve();
        Lit216 = simpleSymbol323;
        SimpleSymbol simpleSymbol324 = (SimpleSymbol) new SimpleSymbol("coerce-to-component").readResolve();
        Lit215 = simpleSymbol324;
        SimpleSymbol simpleSymbol325 = (SimpleSymbol) new SimpleSymbol("coerce-to-instant").readResolve();
        Lit214 = simpleSymbol325;
        SimpleSymbol simpleSymbol326 = (SimpleSymbol) new SimpleSymbol("coerce-to-text").readResolve();
        Lit213 = simpleSymbol326;
        SimpleSymbol simpleSymbol327 = (SimpleSymbol) new SimpleSymbol("coerce-to-enum").readResolve();
        Lit212 = simpleSymbol327;
        SimpleSymbol simpleSymbol328 = (SimpleSymbol) new SimpleSymbol("enum?").readResolve();
        Lit211 = simpleSymbol328;
        SimpleSymbol simpleSymbol329 = (SimpleSymbol) new SimpleSymbol("enum-type?").readResolve();
        Lit210 = simpleSymbol329;
        SimpleSymbol simpleSymbol330 = (SimpleSymbol) new SimpleSymbol("coerce-to-number-list").readResolve();
        Lit209 = simpleSymbol330;
        SimpleSymbol simpleSymbol331 = (SimpleSymbol) new SimpleSymbol("coerce-arg").readResolve();
        Lit208 = simpleSymbol331;
        SimpleSymbol simpleSymbol332 = (SimpleSymbol) new SimpleSymbol("coerce-args").readResolve();
        Lit207 = simpleSymbol332;
        SimpleSymbol simpleSymbol333 = (SimpleSymbol) new SimpleSymbol("show-arglist-no-parens").readResolve();
        Lit206 = simpleSymbol333;
        SimpleSymbol simpleSymbol334 = (SimpleSymbol) new SimpleSymbol("generate-runtime-type-error").readResolve();
        Lit205 = simpleSymbol334;
        SimpleSymbol simpleSymbol335 = (SimpleSymbol) new SimpleSymbol("%set-subform-layout-property!").readResolve();
        Lit204 = simpleSymbol335;
        SimpleSymbol simpleSymbol336 = (SimpleSymbol) new SimpleSymbol("%set-and-coerce-property!").readResolve();
        Lit203 = simpleSymbol336;
        SimpleSymbol simpleSymbol337 = (SimpleSymbol) new SimpleSymbol("call-with-coerced-args").readResolve();
        Lit202 = simpleSymbol337;
        SimpleSymbol simpleSymbol338 = (SimpleSymbol) new SimpleSymbol("yail-not").readResolve();
        Lit201 = simpleSymbol338;
        SimpleSymbol simpleSymbol339 = (SimpleSymbol) new SimpleSymbol("signal-runtime-form-error").readResolve();
        Lit200 = simpleSymbol339;
        SimpleSymbol simpleSymbol340 = (SimpleSymbol) new SimpleSymbol("signal-runtime-error").readResolve();
        Lit199 = simpleSymbol340;
        SimpleSymbol simpleSymbol341 = (SimpleSymbol) new SimpleSymbol("sanitize-atomic").readResolve();
        Lit198 = simpleSymbol341;
        SimpleSymbol simpleSymbol342 = (SimpleSymbol) new SimpleSymbol("java-map->yail-dictionary").readResolve();
        Lit197 = simpleSymbol342;
        SimpleSymbol simpleSymbol343 = (SimpleSymbol) new SimpleSymbol("java-collection->kawa-list").readResolve();
        Lit196 = simpleSymbol343;
        SimpleSymbol simpleSymbol344 = (SimpleSymbol) new SimpleSymbol("java-collection->yail-list").readResolve();
        Lit195 = simpleSymbol344;
        SimpleSymbol simpleSymbol345 = (SimpleSymbol) new SimpleSymbol("sanitize-return-value").readResolve();
        Lit194 = simpleSymbol345;
        SimpleSymbol simpleSymbol346 = (SimpleSymbol) new SimpleSymbol("sanitize-component-data").readResolve();
        Lit193 = simpleSymbol346;
        SimpleSymbol simpleSymbol347 = (SimpleSymbol) new SimpleSymbol("call-yail-primitive").readResolve();
        Lit192 = simpleSymbol347;
        SimpleSymbol simpleSymbol348 = (SimpleSymbol) new SimpleSymbol("call-component-type-method-with-blocking-continuation").readResolve();
        Lit191 = simpleSymbol348;
        SimpleSymbol simpleSymbol349 = (SimpleSymbol) new SimpleSymbol("call-component-type-method-with-continuation").readResolve();
        Lit190 = simpleSymbol349;
        SimpleSymbol simpleSymbol350 = (SimpleSymbol) new SimpleSymbol("call-component-type-method").readResolve();
        Lit189 = simpleSymbol350;
        SimpleSymbol simpleSymbol351 = (SimpleSymbol) new SimpleSymbol("call-component-method-with-blocking-continuation").readResolve();
        Lit188 = simpleSymbol351;
        SimpleSymbol simpleSymbol352 = (SimpleSymbol) new SimpleSymbol("call-component-method-with-continuation").readResolve();
        Lit187 = simpleSymbol352;
        SimpleSymbol simpleSymbol353 = (SimpleSymbol) new SimpleSymbol("call-component-method").readResolve();
        Lit186 = simpleSymbol353;
        SyntaxRules syntaxRules3 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\r\u0017\u0010\b\b", new Object[0], 3), "\u0001\u0001\u0003", "\u0011\u0018\u0004\b\u0011\u0018\f\u0011\b\u0003\b\u0011\u0018\u0014\u0011\u0018\u001c\t\u0010\b\u0011\u0018$\t\u000bA\u0011\u0018,\u0011\u0015\u0013\u00184\u0018<", new Object[]{simpleSymbol117, simpleSymbol116, simpleSymbol115, simpleSymbol118, simpleSymbol119, simpleSymbol114, PairWithPosition.make(PairWithPosition.make(simpleSymbol118, LList.Empty, "/tmp/runtime4411419262522357548.scm", 4149251), LList.Empty, "/tmp/runtime4411419262522357548.scm", 4149251), PairWithPosition.make(simpleSymbol7, LList.Empty, "/tmp/runtime4411419262522357548.scm", 4153352)}, 1)}, 3);
        Lit185 = syntaxRules3;
        SimpleSymbol simpleSymbol354 = (SimpleSymbol) new SimpleSymbol("while-with-break").readResolve();
        Lit184 = simpleSymbol354;
        SyntaxRules syntaxRules4 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\f\u001f\f'\f/\b", new Object[0], 6), "\u0001\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004\b\u0011\u0018\f\u0011\b\u0003\b\u0011\u0018\u0014A\u0011\u0018\f\u0011\b\u000b\b\u0013\t\u001b\t#\b+", new Object[]{simpleSymbol117, simpleSymbol116, simpleSymbol187}, 0)}, 6);
        Lit183 = syntaxRules4;
        SimpleSymbol simpleSymbol355 = (SimpleSymbol) new SimpleSymbol("forrange-with-break").readResolve();
        Lit182 = simpleSymbol355;
        SyntaxRules syntaxRules5 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3), "\u0001\u0001\u0001", "\u0011\u0018\u0004A\u0011\u0018\f\u0011\b\u0003\b\u000b\b\u0013", new Object[]{simpleSymbol199, simpleSymbol116}, 0)}, 3);
        Lit181 = syntaxRules5;
        SimpleSymbol simpleSymbol356 = (SimpleSymbol) new SimpleSymbol("sortkey_nondest").readResolve();
        Lit180 = simpleSymbol356;
        SyntaxRules syntaxRules6 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\f\u001f\b", new Object[0], 4), "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f!\t\u0003\b\u000b\b\u0013\b\u001b", new Object[]{simpleSymbol194, simpleSymbol116}, 0)}, 4);
        Lit179 = syntaxRules6;
        SimpleSymbol simpleSymbol357 = (SimpleSymbol) new SimpleSymbol("maxcomparator-nondest").readResolve();
        Lit178 = simpleSymbol357;
        SyntaxRules syntaxRules7 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\f\u001f\b", new Object[0], 4), "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f!\t\u0003\b\u000b\b\u0013\b\u001b", new Object[]{simpleSymbol196, simpleSymbol116}, 0)}, 4);
        Lit177 = syntaxRules7;
        SimpleSymbol simpleSymbol358 = (SimpleSymbol) new SimpleSymbol("mincomparator-nondest").readResolve();
        Lit176 = simpleSymbol358;
        SyntaxRules syntaxRules8 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\f\u001f\b", new Object[0], 4), "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f!\t\u0003\b\u000b\b\u0013\b\u001b", new Object[]{simpleSymbol202, simpleSymbol116}, 0)}, 4);
        Lit175 = syntaxRules8;
        SimpleSymbol simpleSymbol359 = (SimpleSymbol) new SimpleSymbol("sortcomparator_nondest").readResolve();
        Lit174 = simpleSymbol359;
        SyntaxRules syntaxRules9 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\f\u001f\f'\b", new Object[0], 5), "\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004\t\u0003Q\u0011\u0018\f!\t\u000b\b\u0013\b\u001b\b#", new Object[]{simpleSymbol224, simpleSymbol116}, 0)}, 5);
        Lit173 = syntaxRules9;
        SimpleSymbol simpleSymbol360 = (SimpleSymbol) new SimpleSymbol("reduceovereach").readResolve();
        Lit172 = simpleSymbol360;
        SyntaxRules syntaxRules10 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3), "\u0001\u0001\u0001", "\u0011\u0018\u0004A\u0011\u0018\f\u0011\b\u0003\b\u000b\b\u0013", new Object[]{simpleSymbol225, simpleSymbol116}, 0)}, 3);
        Lit171 = syntaxRules10;
        SimpleSymbol simpleSymbol361 = (SimpleSymbol) new SimpleSymbol("filter_nondest").readResolve();
        Lit170 = simpleSymbol361;
        SyntaxRules syntaxRules11 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3), "\u0001\u0001\u0001", "\u0011\u0018\u0004A\u0011\u0018\f\u0011\b\u0003\b\u000b\b\u0013", new Object[]{simpleSymbol226, simpleSymbol116}, 0)}, 3);
        Lit169 = syntaxRules11;
        SimpleSymbol simpleSymbol362 = (SimpleSymbol) new SimpleSymbol("map_nondest").readResolve();
        Lit168 = simpleSymbol362;
        SyntaxRules syntaxRules12 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\f\u001f\b", new Object[0], 4), "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004\b\u0011\u0018\f\u0011\b\u0003\b\u0011\u0018\u0014i\b\u0011\u0018\u001c\b\u0011\u0018\f\u0011\b\u000b\b\u0013\b\u0011\u0018$\u0011\u0018\u001c\b\u001b", new Object[]{simpleSymbol117, simpleSymbol116, simpleSymbol115, simpleSymbol112, simpleSymbol227}, 0)}, 4);
        Lit167 = syntaxRules12;
        SimpleSymbol simpleSymbol363 = (SimpleSymbol) new SimpleSymbol("foreach-with-break").readResolve();
        Lit166 = simpleSymbol363;
        SimpleSymbol simpleSymbol364 = (SimpleSymbol) new SimpleSymbol("cont").readResolve();
        Lit49 = simpleSymbol364;
        Lit165 = PairWithPosition.make(PairWithPosition.make(simpleSymbol117, PairWithPosition.make(simpleSymbol364, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3821606), "/tmp/runtime4411419262522357548.scm", 3821574), LList.Empty, "/tmp/runtime4411419262522357548.scm", 3821574);
        Lit164 = PairWithPosition.make(null, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3817496);
        Lit163 = PairWithPosition.make(PairWithPosition.make(simpleSymbol113, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3813407), LList.Empty, "/tmp/runtime4411419262522357548.scm", 3813407);
        Lit162 = PairWithPosition.make(simpleSymbol114, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3809311);
        Lit161 = PairWithPosition.make(simpleSymbol114, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3809304);
        Lit160 = PairWithPosition.make(simpleSymbol119, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3805204);
        Lit159 = PairWithPosition.make(simpleSymbol115, PairWithPosition.make(simpleSymbol113, PairWithPosition.make(LList.Empty, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3801123), "/tmp/runtime4411419262522357548.scm", 3801111), "/tmp/runtime4411419262522357548.scm", 3801106);
        SimpleSymbol simpleSymbol365 = (SimpleSymbol) new SimpleSymbol("*yail-break*").readResolve();
        Lit147 = simpleSymbol365;
        Lit158 = PairWithPosition.make(simpleSymbol365, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3797016);
        Lit157 = PairWithPosition.make(simpleSymbol116, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3797008);
        Lit156 = PairWithPosition.make(simpleSymbol364, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3797002);
        Lit155 = PairWithPosition.make(simpleSymbol115, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3796996);
        SimpleSymbol simpleSymbol366 = (SimpleSymbol) new SimpleSymbol("while").readResolve();
        Lit154 = simpleSymbol366;
        Lit153 = PairWithPosition.make(simpleSymbol116, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3743767);
        Lit152 = PairWithPosition.make(simpleSymbol187, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3743751);
        Lit151 = PairWithPosition.make(simpleSymbol365, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3739661);
        Lit150 = PairWithPosition.make(simpleSymbol116, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3739653);
        Lit149 = PairWithPosition.make(simpleSymbol117, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3735556);
        SimpleSymbol simpleSymbol367 = (SimpleSymbol) new SimpleSymbol("forrange").readResolve();
        Lit148 = simpleSymbol367;
        Lit146 = PairWithPosition.make(simpleSymbol227, PairWithPosition.make(simpleSymbol112, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3665944), "/tmp/runtime4411419262522357548.scm", 3665929);
        Lit145 = PairWithPosition.make(simpleSymbol116, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3661843);
        Lit144 = PairWithPosition.make(simpleSymbol112, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3661837);
        Lit143 = PairWithPosition.make(simpleSymbol115, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3661831);
        Lit142 = PairWithPosition.make(simpleSymbol365, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3657741);
        Lit141 = PairWithPosition.make(simpleSymbol116, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3657733);
        Lit140 = PairWithPosition.make(simpleSymbol117, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3653636);
        SimpleSymbol simpleSymbol368 = (SimpleSymbol) new SimpleSymbol("foreach").readResolve();
        Lit139 = simpleSymbol368;
        SimpleSymbol simpleSymbol369 = (SimpleSymbol) new SimpleSymbol("reset-current-form-environment").readResolve();
        Lit138 = simpleSymbol369;
        SimpleSymbol simpleSymbol370 = (SimpleSymbol) new SimpleSymbol("lookup-global-var-in-current-form-environment").readResolve();
        Lit137 = simpleSymbol370;
        SimpleSymbol simpleSymbol371 = (SimpleSymbol) new SimpleSymbol("add-global-var-to-current-form-environment").readResolve();
        Lit136 = simpleSymbol371;
        SimpleSymbol simpleSymbol372 = (SimpleSymbol) new SimpleSymbol("rename-in-current-form-environment").readResolve();
        Lit135 = simpleSymbol372;
        SimpleSymbol simpleSymbol373 = (SimpleSymbol) new SimpleSymbol("delete-from-current-form-environment").readResolve();
        Lit134 = simpleSymbol373;
        SimpleSymbol simpleSymbol374 = (SimpleSymbol) new SimpleSymbol("filter-type-in-current-form-environment").readResolve();
        Lit133 = simpleSymbol374;
        SimpleSymbol simpleSymbol375 = (SimpleSymbol) new SimpleSymbol("lookup-in-current-form-environment").readResolve();
        Lit132 = simpleSymbol375;
        SimpleSymbol simpleSymbol376 = (SimpleSymbol) new SimpleSymbol("add-to-current-form-environment").readResolve();
        Lit131 = simpleSymbol376;
        SyntaxRules syntaxRules13 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\r\u0007\u0000\b\b", new Object[0], 1), "\u0003", "\u0011\u0018\u0004\u0011\u0018\f1\u0011\u0018\u0014\b\u0005\u0003\b\u0011\u0018\u001c\b\u0011\u0018$\b\u0011\u0018\u0014\b\u0005\u0003", new Object[]{simpleSymbol119, simpleSymbol110, simpleSymbol114, simpleSymbol74, simpleSymbol111}, 1)}, 1);
        Lit130 = syntaxRules13;
        SimpleSymbol simpleSymbol377 = (SimpleSymbol) new SimpleSymbol("do-after-form-creation").readResolve();
        Lit129 = simpleSymbol377;
        SyntaxRules syntaxRules14 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018<\f\u0007\r\u000f\b\b\b\r\u0017\u0010\b\b", new Object[0], 3), "\u0001\u0003\u0003", "\u0011\u0018\u0004\b\u0011\u0018\f\u0011\u0018\u0014¡\u0011\u0018\u001c)\u0011\u0018$\b\u0003\b\u0011\u0018,\u0019\b\r\u000b\b\u0015\u0013\b\u0011\u00184)\u0011\u0018$\b\u0003\b\u0011\u0018,\t\u0010\b\u0011\u0018,\u0019\b\r\u000b\b\u0015\u0013", new Object[]{simpleSymbol114, simpleSymbol119, simpleSymbol110, simpleSymbol371, simpleSymbol109, simpleSymbol116, simpleSymbol108}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\b\u0011\u0018\f\u0011\u0018\u0014Y\u0011\u0018\u001c)\u0011\u0018$\b\u0003\b\u000b\b\u0011\u0018,)\u0011\u0018$\b\u0003\b\u0011\u00184\t\u0010\b\u000b", new Object[]{simpleSymbol114, simpleSymbol119, simpleSymbol110, simpleSymbol371, simpleSymbol109, simpleSymbol108, simpleSymbol116}, 0)}, 3);
        Lit128 = syntaxRules14;
        SimpleSymbol simpleSymbol378 = (SimpleSymbol) new SimpleSymbol("def").readResolve();
        Lit127 = simpleSymbol378;
        Lit126 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0000", "\u0010", new Object[0], 0);
        Lit125 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0000", "\t\u001b\b\"", new Object[0], 0);
        Lit124 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0000", "\u0013", new Object[0], 0);
        Lit123 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0000", "\u000b", new Object[0], 0);
        SimpleSymbol simpleSymbol379 = (SimpleSymbol) new SimpleSymbol("any$").readResolve();
        Lit122 = simpleSymbol379;
        SimpleSymbol simpleSymbol380 = (SimpleSymbol) new SimpleSymbol("define-event-helper").readResolve();
        Lit105 = simpleSymbol380;
        Lit121 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0000", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol380, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3092492)}, 0);
        Lit120 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0000", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol114, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3088394)}, 0);
        Lit119 = new SyntaxPattern("\f\u0007\f\u000f\f\u0017\f\u001f#", new Object[0], 5);
        SimpleSymbol simpleSymbol381 = (SimpleSymbol) new SimpleSymbol("define-generic-event").readResolve();
        Lit118 = simpleSymbol381;
        Lit117 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0000", "\b\u0011\u0018\u0004\u0011\u0018\f\u0091\u0011\u0018\u0014\u0011\u0018\u001c)\u0011\u0018$\b\u000b\b\u0011\u0018$\b\u0013\b\u0011\u0018,)\u0011\u0018$\b\u000b\b\u0011\u0018$\b\u0013", new Object[]{simpleSymbol119, simpleSymbol110, PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol48, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol26, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 3043345), PairWithPosition.make(simpleSymbol63, PairWithPosition.make(simpleSymbol47, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("*this-form*").readResolve(), LList.Empty, "/tmp/runtime4411419262522357548.scm", 3047511), "/tmp/runtime4411419262522357548.scm", 3047445), "/tmp/runtime4411419262522357548.scm", 3047441), simpleSymbol109, simpleSymbol87}, 0);
        Lit116 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0000", "\t\u001b\b\"", new Object[0], 0);
        Lit115 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0000", "\u0013", new Object[0], 0);
        SimpleSymbol simpleSymbol382 = (SimpleSymbol) new SimpleSymbol("$").readResolve();
        Lit114 = simpleSymbol382;
        Lit113 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0000", "\u000b", new Object[0], 0);
        Lit112 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0000", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol380, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3018764)}, 0);
        Lit111 = new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0000", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol114, LList.Empty, "/tmp/runtime4411419262522357548.scm", 3014666)}, 0);
        Lit110 = new SyntaxPattern("\f\u0007\f\u000f\f\u0017\f\u001f#", new Object[0], 5);
        SimpleSymbol simpleSymbol383 = (SimpleSymbol) new SimpleSymbol("define-event").readResolve();
        Lit109 = simpleSymbol383;
        SyntaxPattern syntaxPattern = new SyntaxPattern("\f\u0018\r\u0007\u0000\b\b", new Object[0], 1);
        SimpleSymbol simpleSymbol384 = (SimpleSymbol) new SimpleSymbol("list").readResolve();
        Lit8 = simpleSymbol384;
        SyntaxRules syntaxRules15 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(syntaxPattern, "\u0003", "\u0011\u0018\u0004\b\u0005\u0003", new Object[]{simpleSymbol384}, 1)}, 1);
        Lit108 = syntaxRules15;
        SimpleSymbol simpleSymbol385 = (SimpleSymbol) new SimpleSymbol("*list-for-runtime*").readResolve();
        Lit107 = simpleSymbol385;
        SyntaxRules syntaxRules16 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007,\r\u000f\b\b\b,\r\u0017\u0010\b\b\b", new Object[0], 3), "\u0001\u0003\u0003", "\u0011\u0018\u0004Ù\u0011\u0018\f)\t\u0003\b\r\u000b\b\u0011\u0018\u0014Q\b\r\t\u000b\b\u0011\u0018\u001c\b\u000b\b\u0015\u0013\b\u0011\u0018$\u0011\u0018,Y\u0011\u00184)\u0011\u0018<\b\u0003\b\u0003\b\u0011\u0018D)\u0011\u0018<\b\u0003\b\u0003", new Object[]{simpleSymbol114, simpleSymbol107, simpleSymbol115, simpleSymbol346, simpleSymbol119, simpleSymbol110, simpleSymbol376, simpleSymbol109, simpleSymbol98}, 1)}, 3);
        Lit106 = syntaxRules16;
        SimpleSymbol simpleSymbol386 = (SimpleSymbol) new SimpleSymbol("symbol-append").readResolve();
        Lit98 = simpleSymbol386;
        Lit104 = new SyntaxTemplate("\u0001\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\f\t\u000b\u0011\u0018\u0014\b\u0013", new Object[]{simpleSymbol386, PairWithPosition.make(simpleSymbol109, PairWithPosition.make(simpleSymbol379, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2785332), "/tmp/runtime4411419262522357548.scm", 2785332), PairWithPosition.make(simpleSymbol109, PairWithPosition.make(simpleSymbol382, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2785353), "/tmp/runtime4411419262522357548.scm", 2785353)}, 0);
        Lit103 = new SyntaxPattern("\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
        SimpleSymbol simpleSymbol387 = (SimpleSymbol) new SimpleSymbol("gen-generic-event-name").readResolve();
        Lit102 = simpleSymbol387;
        Lit101 = new SyntaxTemplate("\u0001\u0001\u0001", "\u0011\u0018\u0004\t\u000b\u0011\u0018\f\b\u0013", new Object[]{simpleSymbol386, PairWithPosition.make(simpleSymbol109, PairWithPosition.make(simpleSymbol382, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2752579), "/tmp/runtime4411419262522357548.scm", 2752579)}, 0);
        Lit100 = new SyntaxPattern("\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
        SimpleSymbol simpleSymbol388 = (SimpleSymbol) new SimpleSymbol("gen-event-name").readResolve();
        Lit99 = simpleSymbol388;
        Object[] objArr = {simpleSymbol122};
        SyntaxPattern syntaxPattern2 = new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\f\u001f\f'\b", new Object[0], 5);
        PairWithPosition make = PairWithPosition.make(simpleSymbol98, PairWithPosition.make(simpleSymbol96, PairWithPosition.make(simpleSymbol104, PairWithPosition.make(simpleSymbol94, PairWithPosition.make(simpleSymbol105, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1380420), "/tmp/runtime4411419262522357548.scm", 1380401), "/tmp/runtime4411419262522357548.scm", 1380398), "/tmp/runtime4411419262522357548.scm", 1380393), "/tmp/runtime4411419262522357548.scm", 1380368);
        PairWithPosition make2 = PairWithPosition.make(simpleSymbol97, PairWithPosition.make(PairWithPosition.make(simpleSymbol90, PairWithPosition.make(Boolean.FALSE, PairWithPosition.make("Adding ~A to env ~A with value ~A", PairWithPosition.make(simpleSymbol96, PairWithPosition.make(simpleSymbol95, PairWithPosition.make(simpleSymbol105, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1384545), "/tmp/runtime4411419262522357548.scm", 1384528), "/tmp/runtime4411419262522357548.scm", 1384523), "/tmp/runtime4411419262522357548.scm", 1384487), "/tmp/runtime4411419262522357548.scm", 1384484), "/tmp/runtime4411419262522357548.scm", 1384476), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1384476), "/tmp/runtime4411419262522357548.scm", 1384458);
        SimpleSymbol simpleSymbol389 = (SimpleSymbol) new SimpleSymbol("put").readResolve();
        Lit0 = simpleSymbol389;
        PairWithPosition make3 = PairWithPosition.make(simpleSymbol57, PairWithPosition.make(simpleSymbol96, PairWithPosition.make(simpleSymbol104, PairWithPosition.make(simpleSymbol94, PairWithPosition.make(Special.optional, PairWithPosition.make(PairWithPosition.make(simpleSymbol93, PairWithPosition.make(Boolean.FALSE, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1396833), "/tmp/runtime4411419262522357548.scm", 1396818), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1396818), "/tmp/runtime4411419262522357548.scm", 1396807), "/tmp/runtime4411419262522357548.scm", 1396788), "/tmp/runtime4411419262522357548.scm", 1396785), "/tmp/runtime4411419262522357548.scm", 1396780), "/tmp/runtime4411419262522357548.scm", 1396752);
        PairWithPosition make4 = PairWithPosition.make(simpleSymbol51, PairWithPosition.make(PairWithPosition.make((SimpleSymbol) new SimpleSymbol("not").readResolve(), PairWithPosition.make(PairWithPosition.make(simpleSymbol58, PairWithPosition.make(simpleSymbol95, PairWithPosition.make(null, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1400878), "/tmp/runtime4411419262522357548.scm", 1400861), "/tmp/runtime4411419262522357548.scm", 1400856), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1400856), "/tmp/runtime4411419262522357548.scm", 1400851), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol99, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol92, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1404948), PairWithPosition.make(simpleSymbol95, PairWithPosition.make(simpleSymbol96, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1404997), "/tmp/runtime4411419262522357548.scm", 1404980), "/tmp/runtime4411419262522357548.scm", 1404947), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1404947), "/tmp/runtime4411419262522357548.scm", 1400851), "/tmp/runtime4411419262522357548.scm", 1400846);
        SimpleSymbol simpleSymbol390 = (SimpleSymbol) new SimpleSymbol("get").readResolve();
        Lit1 = simpleSymbol390;
        PairWithPosition make5 = PairWithPosition.make((SimpleSymbol) new SimpleSymbol("dispatchEvent").readResolve(), PairWithPosition.make(simpleSymbol56, PairWithPosition.make(simpleSymbol104, PairWithPosition.make(simpleSymbol46, PairWithPosition.make(simpleSymbol61, PairWithPosition.make(simpleSymbol104, PairWithPosition.make(simpleSymbol62, PairWithPosition.make(simpleSymbol55, PairWithPosition.make(simpleSymbol104, PairWithPosition.make(simpleSymbol62, PairWithPosition.make(simpleSymbol53, PairWithPosition.make(simpleSymbol104, PairWithPosition.make(simpleSymbol45, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1769511), "/tmp/runtime4411419262522357548.scm", 1769508), "/tmp/runtime4411419262522357548.scm", 1769503), "/tmp/runtime4411419262522357548.scm", 1765420), "/tmp/runtime4411419262522357548.scm", 1765417), "/tmp/runtime4411419262522357548.scm", 1765407), "/tmp/runtime4411419262522357548.scm", 1761338), "/tmp/runtime4411419262522357548.scm", 1761335), "/tmp/runtime4411419262522357548.scm", 1761311), "/tmp/runtime4411419262522357548.scm", 1757234), "/tmp/runtime4411419262522357548.scm", 1757231), "/tmp/runtime4411419262522357548.scm", 1757215), "/tmp/runtime4411419262522357548.scm", 1757200);
        SimpleSymbol simpleSymbol391 = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN).readResolve();
        Lit7 = simpleSymbol391;
        PairWithPosition make6 = PairWithPosition.make(PairWithPosition.make(simpleSymbol59, PairWithPosition.make(PairWithPosition.make(simpleSymbol43, PairWithPosition.make(simpleSymbol61, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1794100), "/tmp/runtime4411419262522357548.scm", 1794084), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1794084), "/tmp/runtime4411419262522357548.scm", 1794066), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1794065);
        PairWithPosition make7 = PairWithPosition.make(simpleSymbol60, PairWithPosition.make(simpleSymbol59, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1798196), "/tmp/runtime4411419262522357548.scm", 1798166);
        PairWithPosition make8 = PairWithPosition.make(simpleSymbol58, PairWithPosition.make(PairWithPosition.make(simpleSymbol57, PairWithPosition.make(simpleSymbol59, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1802299), "/tmp/runtime4411419262522357548.scm", 1802271), PairWithPosition.make(simpleSymbol56, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1802317), "/tmp/runtime4411419262522357548.scm", 1802271), "/tmp/runtime4411419262522357548.scm", 1802266);
        PairWithPosition make9 = PairWithPosition.make(PairWithPosition.make(simpleSymbol54, PairWithPosition.make(PairWithPosition.make(simpleSymbol30, PairWithPosition.make(simpleSymbol61, PairWithPosition.make(simpleSymbol55, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1806416), "/tmp/runtime4411419262522357548.scm", 1806392), "/tmp/runtime4411419262522357548.scm", 1806376), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1806376), "/tmp/runtime4411419262522357548.scm", 1806367), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1806366);
        SimpleSymbol simpleSymbol392 = (SimpleSymbol) new SimpleSymbol("makeList").readResolve();
        Lit44 = simpleSymbol392;
        PairWithPosition make10 = PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol88, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol392, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1843252);
        IntNum make11 = IntNum.make(0);
        Lit25 = make11;
        SyntaxRules syntaxRules17 = new SyntaxRules(objArr, new SyntaxRule[]{new SyntaxRule(syntaxPattern2, "\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004)\u0011\u0018\f\b\u0013)\u0011\u0018\u0014\b\u0003)\u0011\u0018\u001c\b\u000b\u0011\u0018$\u0011\u0018,Ñ\u0011\u00184\u0011\u0018<\u0011\u0018D\u0011\u0018L)\u0011\u0018T\b#\b\u0011\u0018\\\t\u0013\u0018d\u0011\u0018l\u0011\u0018tÑ\u0011\u00184\u0011\u0018|\u0011\u0018D\u0011\u0018\u0084\b\u0011\u0018\u008c\b\u0011\u0018\u0094\b\u0011\u0018\u009c\b\u000b\u0011\u0018¤\u0011\u0018¬\u0011\u0018´ā\u0011\u00184\u0011\u0018¼\u0011\u0018D\u0011\u0018\u0084\b\u0011\u0018Ä\b\u0011\u0018ÌI\u0011\u0018\u0094\b\u0011\u0018\u009c\b\u000b\u0018Ô\u0011\u0018Üa\u0011\u00184\t\u000b\u0011\u0018D\t\u0003\u0018ä\u0091\u0011\u00184\u0011\u0018ì\u0011\u0018D\u0011\u0018ô\b\u0011\u0018\u009c\b\u000b\u0011\u0018ü\u0011\u0018Ą\u0011\u0018Č\u0011\u0018Ĕ\u0011\u0018Ĝ\u0011\u0018Ĥ\u0011\u0018Ĭ\u0011\u0018Ĵ\u0011\u0018ļ\u0089\u0011\u00184\u0011\u0018ń\u0011\u0018Ō\b\u0011\u0018Ŕ\t\u001b\u0018Ŝ\u0011\u0018Ť\u0011\u0018Ŭ\u0011\u0018Ŵ\b\u0011\u00184\u0011\u0018ż\u0011\u0018D\u0011\u0018L\u0011\u0018Ƅ\u0011\u0018ƌ\u0011\u0018Ɣ\u0011\u0018Ɯ\u0011\u0018Ƥ\u0011\u0018Ƭ\u0011\u0018ƴ9\u0011\u0018Ƽ\t\u000b\u0018ǄY\u0011\u0018ǌ)\u0011\u0018\u009c\b\u000b\u0018ǔ\u0018ǜ", new Object[]{simpleSymbol114, (SimpleSymbol) new SimpleSymbol("module-extends").readResolve(), (SimpleSymbol) new SimpleSymbol("module-name").readResolve(), (SimpleSymbol) new SimpleSymbol("module-static").readResolve(), PairWithPosition.make((SimpleSymbol) new SimpleSymbol("require").readResolve(), PairWithPosition.make((SimpleSymbol) new SimpleSymbol("<com.google.youngandroid.runtime>").readResolve(), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1294353), "/tmp/runtime4411419262522357548.scm", 1294344), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(PairWithPosition.make(simpleSymbol41, PairWithPosition.make(simpleSymbol105, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1302561), "/tmp/runtime4411419262522357548.scm", 1302544), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol106, Pair.make(Pair.make(simpleSymbol120, Pair.make((SimpleSymbol) new SimpleSymbol("getSimpleName").readResolve(), LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1306635), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol106, Pair.make(Pair.make(simpleSymbol120, Pair.make((SimpleSymbol) new SimpleSymbol("getClass").readResolve(), LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1306652), PairWithPosition.make(simpleSymbol105, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1306663), "/tmp/runtime4411419262522357548.scm", 1306651), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1306651), "/tmp/runtime4411419262522357548.scm", 1306634), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1306634), "/tmp/runtime4411419262522357548.scm", 1302544), "/tmp/runtime4411419262522357548.scm", 1302536), simpleSymbol107, PairWithPosition.make(simpleSymbol103, PairWithPosition.make(simpleSymbol102, PairWithPosition.make(simpleSymbol104, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("android.os.Bundle").readResolve(), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1314852), "/tmp/runtime4411419262522357548.scm", 1314849), "/tmp/runtime4411419262522357548.scm", 1314842), "/tmp/runtime4411419262522357548.scm", 1314832), simpleSymbol104, simpleSymbol44, PairWithPosition.make(simpleSymbol121, Pair.make((SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.AppInventorCompatActivity").readResolve(), Pair.make(Pair.make(simpleSymbol120, Pair.make((SimpleSymbol) new SimpleSymbol("setClassicModeFromYail").readResolve(), LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1323019), (SimpleSymbol) new SimpleSymbol("invoke-special").readResolve(), PairWithPosition.make(PairWithPosition.make(simpleSymbol69, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1327144), PairWithPosition.make(PairWithPosition.make(simpleSymbol109, PairWithPosition.make(simpleSymbol103, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1327152), "/tmp/runtime4411419262522357548.scm", 1327152), PairWithPosition.make(simpleSymbol102, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1327161), "/tmp/runtime4411419262522357548.scm", 1327151), "/tmp/runtime4411419262522357548.scm", 1327144), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(simpleSymbol101, PairWithPosition.make(Boolean.FALSE, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1335325), "/tmp/runtime4411419262522357548.scm", 1335312), "/tmp/runtime4411419262522357548.scm", 1335304), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(PairWithPosition.make(simpleSymbol97, PairWithPosition.make(simpleSymbol100, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1343522), "/tmp/runtime4411419262522357548.scm", 1343504), PairWithPosition.make(PairWithPosition.make(simpleSymbol70, PairWithPosition.make(simpleSymbol101, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make((SimpleSymbol) new SimpleSymbol("android.util.Log").readResolve(), Pair.make(Pair.make(simpleSymbol120, Pair.make((SimpleSymbol) new SimpleSymbol("i").readResolve(), LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1347614), PairWithPosition.make("YAIL", PairWithPosition.make(simpleSymbol100, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1347640), "/tmp/runtime4411419262522357548.scm", 1347633), "/tmp/runtime4411419262522357548.scm", 1347613), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1347613), "/tmp/runtime4411419262522357548.scm", 1347600), "/tmp/runtime4411419262522357548.scm", 1347594), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1347594), "/tmp/runtime4411419262522357548.scm", 1343504), "/tmp/runtime4411419262522357548.scm", 1343496), simpleSymbol95, simpleSymbol99, PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol99, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol91, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1372171), simpleSymbol10, simpleSymbol109, PairWithPosition.make(simpleSymbol107, PairWithPosition.make(make, PairWithPosition.make(make2, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol99, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol389, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1388555), PairWithPosition.make(simpleSymbol95, PairWithPosition.make(simpleSymbol96, PairWithPosition.make(simpleSymbol105, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1388605), "/tmp/runtime4411419262522357548.scm", 1388600), "/tmp/runtime4411419262522357548.scm", 1388583), "/tmp/runtime4411419262522357548.scm", 1388554), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1388554), "/tmp/runtime4411419262522357548.scm", 1384458), "/tmp/runtime4411419262522357548.scm", 1380368), "/tmp/runtime4411419262522357548.scm", 1380360), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(make3, PairWithPosition.make(PairWithPosition.make(simpleSymbol119, PairWithPosition.make(make4, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol99, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol390, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1409039), PairWithPosition.make(simpleSymbol95, PairWithPosition.make(simpleSymbol96, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1409084), "/tmp/runtime4411419262522357548.scm", 1409067), "/tmp/runtime4411419262522357548.scm", 1409038), PairWithPosition.make(simpleSymbol93, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1413134), "/tmp/runtime4411419262522357548.scm", 1409038), "/tmp/runtime4411419262522357548.scm", 1400846), "/tmp/runtime4411419262522357548.scm", 1400842), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1400842), "/tmp/runtime4411419262522357548.scm", 1396752), "/tmp/runtime4411419262522357548.scm", 1396744), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(PairWithPosition.make(simpleSymbol60, PairWithPosition.make(simpleSymbol96, PairWithPosition.make(simpleSymbol104, PairWithPosition.make(simpleSymbol94, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1421366), "/tmp/runtime4411419262522357548.scm", 1421363), "/tmp/runtime4411419262522357548.scm", 1421358), "/tmp/runtime4411419262522357548.scm", 1421328), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol99, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol92, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1425419), PairWithPosition.make(simpleSymbol95, PairWithPosition.make(simpleSymbol96, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1425468), "/tmp/runtime4411419262522357548.scm", 1425451), "/tmp/runtime4411419262522357548.scm", 1425418), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1425418), "/tmp/runtime4411419262522357548.scm", 1421328), "/tmp/runtime4411419262522357548.scm", 1421320), simpleSymbol89, PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol99, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol91, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1437707), simpleSymbol42, PairWithPosition.make("-global-vars", LList.Empty, "/tmp/runtime4411419262522357548.scm", 1445929), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(PairWithPosition.make(simpleSymbol20, PairWithPosition.make(simpleSymbol96, PairWithPosition.make(simpleSymbol104, PairWithPosition.make(simpleSymbol94, PairWithPosition.make(simpleSymbol105, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1454154), "/tmp/runtime4411419262522357548.scm", 1454135), "/tmp/runtime4411419262522357548.scm", 1454132), "/tmp/runtime4411419262522357548.scm", 1454127), "/tmp/runtime4411419262522357548.scm", 1454096), PairWithPosition.make(PairWithPosition.make(simpleSymbol97, PairWithPosition.make(PairWithPosition.make(simpleSymbol90, PairWithPosition.make(Boolean.FALSE, PairWithPosition.make("Adding ~A to env ~A with value ~A", PairWithPosition.make(simpleSymbol96, PairWithPosition.make(simpleSymbol89, PairWithPosition.make(simpleSymbol105, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1458279), "/tmp/runtime4411419262522357548.scm", 1458256), "/tmp/runtime4411419262522357548.scm", 1458251), "/tmp/runtime4411419262522357548.scm", 1458215), "/tmp/runtime4411419262522357548.scm", 1458212), "/tmp/runtime4411419262522357548.scm", 1458204), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1458204), "/tmp/runtime4411419262522357548.scm", 1458186), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol99, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol389, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1462283), PairWithPosition.make(simpleSymbol89, PairWithPosition.make(simpleSymbol96, PairWithPosition.make(simpleSymbol105, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1462339), "/tmp/runtime4411419262522357548.scm", 1462334), "/tmp/runtime4411419262522357548.scm", 1462311), "/tmp/runtime4411419262522357548.scm", 1462282), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1462282), "/tmp/runtime4411419262522357548.scm", 1458186), "/tmp/runtime4411419262522357548.scm", 1454096), "/tmp/runtime4411419262522357548.scm", 1454088), PairWithPosition.make(null, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1478696), (SimpleSymbol) new SimpleSymbol("form-name-symbol").readResolve(), simpleSymbol94, PairWithPosition.make(simpleSymbol107, PairWithPosition.make(simpleSymbol86, PairWithPosition.make(simpleSymbol104, PairWithPosition.make(simpleSymbol88, PairWithPosition.make(PairWithPosition.make(simpleSymbol109, PairWithPosition.make(LList.Empty, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1503288), "/tmp/runtime4411419262522357548.scm", 1503288), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1503287), "/tmp/runtime4411419262522357548.scm", 1503271), "/tmp/runtime4411419262522357548.scm", 1503268), "/tmp/runtime4411419262522357548.scm", 1503248), "/tmp/runtime4411419262522357548.scm", 1503240), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(simpleSymbol81, PairWithPosition.make(simpleSymbol104, PairWithPosition.make(simpleSymbol88, PairWithPosition.make(PairWithPosition.make(simpleSymbol109, PairWithPosition.make(LList.Empty, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1523770), "/tmp/runtime4411419262522357548.scm", 1523770), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1523769), "/tmp/runtime4411419262522357548.scm", 1523753), "/tmp/runtime4411419262522357548.scm", 1523750), "/tmp/runtime4411419262522357548.scm", 1523728), "/tmp/runtime4411419262522357548.scm", 1523720), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(PairWithPosition.make(simpleSymbol87, PairWithPosition.make(simpleSymbol84, PairWithPosition.make(simpleSymbol83, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1540142), "/tmp/runtime4411419262522357548.scm", 1540127), "/tmp/runtime4411419262522357548.scm", 1540112), PairWithPosition.make(PairWithPosition.make(simpleSymbol82, PairWithPosition.make(simpleSymbol86, PairWithPosition.make(PairWithPosition.make(simpleSymbol85, PairWithPosition.make(PairWithPosition.make(simpleSymbol85, PairWithPosition.make(simpleSymbol84, PairWithPosition.make(simpleSymbol83, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1548331), "/tmp/runtime4411419262522357548.scm", 1548316), "/tmp/runtime4411419262522357548.scm", 1548310), PairWithPosition.make(simpleSymbol86, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1552406), "/tmp/runtime4411419262522357548.scm", 1548310), "/tmp/runtime4411419262522357548.scm", 1548304), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1548304), "/tmp/runtime4411419262522357548.scm", 1544208), "/tmp/runtime4411419262522357548.scm", 1544202), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1544202), "/tmp/runtime4411419262522357548.scm", 1540112), "/tmp/runtime4411419262522357548.scm", 1540104), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(PairWithPosition.make(simpleSymbol, PairWithPosition.make(simpleSymbol80, PairWithPosition.make(simpleSymbol79, PairWithPosition.make(simpleSymbol84, PairWithPosition.make(simpleSymbol78, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1568848), "/tmp/runtime4411419262522357548.scm", 1568833), "/tmp/runtime4411419262522357548.scm", 1568818), "/tmp/runtime4411419262522357548.scm", 1568803), "/tmp/runtime4411419262522357548.scm", 1568784), PairWithPosition.make(PairWithPosition.make(simpleSymbol82, PairWithPosition.make(simpleSymbol81, PairWithPosition.make(PairWithPosition.make(simpleSymbol85, PairWithPosition.make(PairWithPosition.make(simpleSymbol384, PairWithPosition.make(simpleSymbol80, PairWithPosition.make(simpleSymbol79, PairWithPosition.make(simpleSymbol84, PairWithPosition.make(simpleSymbol78, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1577033), "/tmp/runtime4411419262522357548.scm", 1577018), "/tmp/runtime4411419262522357548.scm", 1577003), "/tmp/runtime4411419262522357548.scm", 1576988), "/tmp/runtime4411419262522357548.scm", 1576982), PairWithPosition.make(simpleSymbol81, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1581078), "/tmp/runtime4411419262522357548.scm", 1576982), "/tmp/runtime4411419262522357548.scm", 1576976), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1576976), "/tmp/runtime4411419262522357548.scm", 1572880), "/tmp/runtime4411419262522357548.scm", 1572874), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1572874), "/tmp/runtime4411419262522357548.scm", 1568784), "/tmp/runtime4411419262522357548.scm", 1568776), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(simpleSymbol77, PairWithPosition.make(simpleSymbol104, PairWithPosition.make(simpleSymbol88, PairWithPosition.make(PairWithPosition.make(simpleSymbol109, PairWithPosition.make(LList.Empty, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1593403), "/tmp/runtime4411419262522357548.scm", 1593403), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1593402), "/tmp/runtime4411419262522357548.scm", 1593386), "/tmp/runtime4411419262522357548.scm", 1593383), "/tmp/runtime4411419262522357548.scm", 1593360), "/tmp/runtime4411419262522357548.scm", 1593352), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(PairWithPosition.make(simpleSymbol108, PairWithPosition.make(simpleSymbol76, PairWithPosition.make(simpleSymbol75, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1605672), "/tmp/runtime4411419262522357548.scm", 1605668), "/tmp/runtime4411419262522357548.scm", 1605648), PairWithPosition.make(PairWithPosition.make(simpleSymbol82, PairWithPosition.make(simpleSymbol77, PairWithPosition.make(PairWithPosition.make(simpleSymbol85, PairWithPosition.make(PairWithPosition.make(simpleSymbol384, PairWithPosition.make(simpleSymbol76, PairWithPosition.make(simpleSymbol75, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1613856), "/tmp/runtime4411419262522357548.scm", 1613852), "/tmp/runtime4411419262522357548.scm", 1613846), PairWithPosition.make(simpleSymbol77, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1617942), "/tmp/runtime4411419262522357548.scm", 1613846), "/tmp/runtime4411419262522357548.scm", 1613840), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1613840), "/tmp/runtime4411419262522357548.scm", 1609744), "/tmp/runtime4411419262522357548.scm", 1609738), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1609738), "/tmp/runtime4411419262522357548.scm", 1605648), "/tmp/runtime4411419262522357548.scm", 1605640), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(simpleSymbol73, PairWithPosition.make(simpleSymbol104, PairWithPosition.make(simpleSymbol88, PairWithPosition.make(PairWithPosition.make(simpleSymbol109, PairWithPosition.make(LList.Empty, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1638460), "/tmp/runtime4411419262522357548.scm", 1638460), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1638459), "/tmp/runtime4411419262522357548.scm", 1638443), "/tmp/runtime4411419262522357548.scm", 1638440), "/tmp/runtime4411419262522357548.scm", 1638416), "/tmp/runtime4411419262522357548.scm", 1638408), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(PairWithPosition.make(simpleSymbol74, PairWithPosition.make(simpleSymbol72, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1646639), "/tmp/runtime4411419262522357548.scm", 1646608), PairWithPosition.make(PairWithPosition.make(simpleSymbol82, PairWithPosition.make(simpleSymbol73, PairWithPosition.make(PairWithPosition.make(simpleSymbol85, PairWithPosition.make(simpleSymbol72, PairWithPosition.make(simpleSymbol73, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1658902), "/tmp/runtime4411419262522357548.scm", 1654806), "/tmp/runtime4411419262522357548.scm", 1654800), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1654800), "/tmp/runtime4411419262522357548.scm", 1650704), "/tmp/runtime4411419262522357548.scm", 1650698), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1650698), "/tmp/runtime4411419262522357548.scm", 1646608), "/tmp/runtime4411419262522357548.scm", 1646600), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(PairWithPosition.make(simpleSymbol67, PairWithPosition.make(simpleSymbol71, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1667100), "/tmp/runtime4411419262522357548.scm", 1667088), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make((SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.util.RetValManager").readResolve(), Pair.make(Pair.make(simpleSymbol120, Pair.make((SimpleSymbol) new SimpleSymbol("sendError").readResolve(), LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1671179), PairWithPosition.make(simpleSymbol71, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1671250), "/tmp/runtime4411419262522357548.scm", 1671178), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1671178), "/tmp/runtime4411419262522357548.scm", 1667088), "/tmp/runtime4411419262522357548.scm", 1667080), PairWithPosition.make(simpleSymbol50, PairWithPosition.make(simpleSymbol68, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1679395), "/tmp/runtime4411419262522357548.scm", 1679376), PairWithPosition.make(simpleSymbol28, PairWithPosition.make(simpleSymbol64, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("<com.google.appinventor.components.runtime.errors.YailRuntimeError>").readResolve(), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1683497), "/tmp/runtime4411419262522357548.scm", 1683480), "/tmp/runtime4411419262522357548.scm", 1683466), simpleSymbol119, PairWithPosition.make(PairWithPosition.make(simpleSymbol70, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(PairWithPosition.make(simpleSymbol69, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1703957), Pair.make(Pair.make(simpleSymbol120, Pair.make((SimpleSymbol) new SimpleSymbol("toastAllowed").readResolve(), LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1703957), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1703956), PairWithPosition.make(PairWithPosition.make(simpleSymbol115, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol100, PairWithPosition.make(PairWithPosition.make(simpleSymbol119, PairWithPosition.make(PairWithPosition.make(simpleSymbol65, PairWithPosition.make(simpleSymbol68, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("java.lang.Error").readResolve(), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1708085), "/tmp/runtime4411419262522357548.scm", 1708082), "/tmp/runtime4411419262522357548.scm", 1708071), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol68, Pair.make(Pair.make(simpleSymbol120, Pair.make((SimpleSymbol) new SimpleSymbol("toString").readResolve(), LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1708103), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1708102), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol68, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol66, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1708117), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1708116), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1708116), "/tmp/runtime4411419262522357548.scm", 1708102), "/tmp/runtime4411419262522357548.scm", 1708071), "/tmp/runtime4411419262522357548.scm", 1708067), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1708067), "/tmp/runtime4411419262522357548.scm", 1708058), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1708057), PairWithPosition.make(PairWithPosition.make(simpleSymbol67, PairWithPosition.make(simpleSymbol100, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1712162), "/tmp/runtime4411419262522357548.scm", 1712150), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make((SimpleSymbol) new SimpleSymbol("android.widget.Toast").readResolve(), Pair.make(Pair.make(simpleSymbol120, Pair.make((SimpleSymbol) new SimpleSymbol("makeText").readResolve(), LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1716248), PairWithPosition.make(PairWithPosition.make(simpleSymbol69, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1716278), PairWithPosition.make(simpleSymbol100, PairWithPosition.make(IntNum.make(5), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1716293), "/tmp/runtime4411419262522357548.scm", 1716285), "/tmp/runtime4411419262522357548.scm", 1716278), "/tmp/runtime4411419262522357548.scm", 1716247), Pair.make(Pair.make(simpleSymbol120, Pair.make((SimpleSymbol) new SimpleSymbol("show").readResolve(), LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1716247), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1716246), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1716246), "/tmp/runtime4411419262522357548.scm", 1712150), "/tmp/runtime4411419262522357548.scm", 1708057), "/tmp/runtime4411419262522357548.scm", 1708052), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1708052), "/tmp/runtime4411419262522357548.scm", 1703956), "/tmp/runtime4411419262522357548.scm", 1703950), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make((SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.util.RuntimeErrorAlert").readResolve(), Pair.make(Pair.make(simpleSymbol120, Pair.make((SimpleSymbol) new SimpleSymbol("alert").readResolve(), LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1724431), PairWithPosition.make(PairWithPosition.make(simpleSymbol69, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1728527), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol68, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol66, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1732624), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1732623), PairWithPosition.make(PairWithPosition.make(simpleSymbol119, PairWithPosition.make(PairWithPosition.make(simpleSymbol65, PairWithPosition.make(simpleSymbol68, PairWithPosition.make(simpleSymbol64, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1736737), "/tmp/runtime4411419262522357548.scm", 1736734), "/tmp/runtime4411419262522357548.scm", 1736723), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(PairWithPosition.make(simpleSymbol63, PairWithPosition.make(simpleSymbol64, PairWithPosition.make(simpleSymbol68, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1736777), "/tmp/runtime4411419262522357548.scm", 1736760), "/tmp/runtime4411419262522357548.scm", 1736756), Pair.make(Pair.make(simpleSymbol120, Pair.make((SimpleSymbol) new SimpleSymbol("getErrorType").readResolve(), LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1736756), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1736755), PairWithPosition.make("Runtime Error", LList.Empty, "/tmp/runtime4411419262522357548.scm", 1736795), "/tmp/runtime4411419262522357548.scm", 1736755), "/tmp/runtime4411419262522357548.scm", 1736723), "/tmp/runtime4411419262522357548.scm", 1736719), PairWithPosition.make("End Application", LList.Empty, "/tmp/runtime4411419262522357548.scm", 1740815), "/tmp/runtime4411419262522357548.scm", 1736719), "/tmp/runtime4411419262522357548.scm", 1732623), "/tmp/runtime4411419262522357548.scm", 1728527), "/tmp/runtime4411419262522357548.scm", 1724430), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1724430), "/tmp/runtime4411419262522357548.scm", 1703950), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(make5, PairWithPosition.make(simpleSymbol104, PairWithPosition.make(simpleSymbol391, PairWithPosition.make(PairWithPosition.make(simpleSymbol115, PairWithPosition.make(make6, PairWithPosition.make(PairWithPosition.make(simpleSymbol119, PairWithPosition.make(make7, PairWithPosition.make(PairWithPosition.make(simpleSymbol119, PairWithPosition.make(make8, PairWithPosition.make(PairWithPosition.make(simpleSymbol115, PairWithPosition.make(make9, PairWithPosition.make(PairWithPosition.make(simpleSymbol39, PairWithPosition.make(PairWithPosition.make(simpleSymbol114, PairWithPosition.make(PairWithPosition.make(simpleSymbol38, PairWithPosition.make(simpleSymbol54, PairWithPosition.make(PairWithPosition.make(make10, PairWithPosition.make(simpleSymbol53, PairWithPosition.make(make11, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1843282), "/tmp/runtime4411419262522357548.scm", 1843277), "/tmp/runtime4411419262522357548.scm", 1843251), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1843251), "/tmp/runtime4411419262522357548.scm", 1843243), "/tmp/runtime4411419262522357548.scm", 1843236), PairWithPosition.make(Boolean.TRUE, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1847332), "/tmp/runtime4411419262522357548.scm", 1843236), "/tmp/runtime4411419262522357548.scm", 1839138), PairWithPosition.make(PairWithPosition.make(simpleSymbol52, PairWithPosition.make(simpleSymbol36, PairWithPosition.make(PairWithPosition.make((SimpleSymbol) new SimpleSymbol("throw").readResolve(), PairWithPosition.make(simpleSymbol52, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1855531), "/tmp/runtime4411419262522357548.scm", 1855524), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1855524), "/tmp/runtime4411419262522357548.scm", 1851437), "/tmp/runtime4411419262522357548.scm", 1851426), PairWithPosition.make(PairWithPosition.make(simpleSymbol52, PairWithPosition.make(simpleSymbol35, PairWithPosition.make(PairWithPosition.make(simpleSymbol114, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol52, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol49, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1892390), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1892389), PairWithPosition.make(PairWithPosition.make(simpleSymbol119, PairWithPosition.make(PairWithPosition.make(simpleSymbol51, PairWithPosition.make(PairWithPosition.make(simpleSymbol58, PairWithPosition.make(PairWithPosition.make(simpleSymbol69, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1908787), PairWithPosition.make(simpleSymbol56, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1908794), "/tmp/runtime4411419262522357548.scm", 1908787), "/tmp/runtime4411419262522357548.scm", 1908782), PairWithPosition.make(PairWithPosition.make(simpleSymbol34, PairWithPosition.make(simpleSymbol55, PairWithPosition.make("PermissionNeeded", LList.Empty, "/tmp/runtime4411419262522357548.scm", 1912896), "/tmp/runtime4411419262522357548.scm", 1912886), "/tmp/runtime4411419262522357548.scm", 1912878), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1912878), "/tmp/runtime4411419262522357548.scm", 1908782), "/tmp/runtime4411419262522357548.scm", 1908777), PairWithPosition.make(PairWithPosition.make(simpleSymbol50, PairWithPosition.make(simpleSymbol52, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1929276), "/tmp/runtime4411419262522357548.scm", 1929257), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(PairWithPosition.make(simpleSymbol69, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1933354), Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol33, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1933354), PairWithPosition.make(simpleSymbol56, PairWithPosition.make(simpleSymbol55, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol52, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol32, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1937475), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1937474), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1937474), "/tmp/runtime4411419262522357548.scm", 1933394), "/tmp/runtime4411419262522357548.scm", 1933378), "/tmp/runtime4411419262522357548.scm", 1933353), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1933353), "/tmp/runtime4411419262522357548.scm", 1929257), "/tmp/runtime4411419262522357548.scm", 1908777), "/tmp/runtime4411419262522357548.scm", 1908773), PairWithPosition.make(Boolean.FALSE, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1941541), "/tmp/runtime4411419262522357548.scm", 1908773), "/tmp/runtime4411419262522357548.scm", 1892389), "/tmp/runtime4411419262522357548.scm", 1888291), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1888291), "/tmp/runtime4411419262522357548.scm", 1884205), "/tmp/runtime4411419262522357548.scm", 1884194), PairWithPosition.make(PairWithPosition.make(simpleSymbol52, PairWithPosition.make(simpleSymbol31, PairWithPosition.make(PairWithPosition.make(simpleSymbol114, PairWithPosition.make(PairWithPosition.make(simpleSymbol97, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol52, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol66, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1953848), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1953847), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1953847), "/tmp/runtime4411419262522357548.scm", 1953829), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol52, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol49, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1962022), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1962021), PairWithPosition.make(PairWithPosition.make(simpleSymbol50, PairWithPosition.make(simpleSymbol52, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1966136), "/tmp/runtime4411419262522357548.scm", 1966117), PairWithPosition.make(Boolean.FALSE, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1970213), "/tmp/runtime4411419262522357548.scm", 1966117), "/tmp/runtime4411419262522357548.scm", 1962021), "/tmp/runtime4411419262522357548.scm", 1953829), "/tmp/runtime4411419262522357548.scm", 1949731), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1949731), "/tmp/runtime4411419262522357548.scm", 1945645), "/tmp/runtime4411419262522357548.scm", 1945634), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1945634), "/tmp/runtime4411419262522357548.scm", 1884194), "/tmp/runtime4411419262522357548.scm", 1851426), "/tmp/runtime4411419262522357548.scm", 1839138), "/tmp/runtime4411419262522357548.scm", 1835041), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1835041), "/tmp/runtime4411419262522357548.scm", 1806366), "/tmp/runtime4411419262522357548.scm", 1806361), PairWithPosition.make(Boolean.FALSE, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1974297), "/tmp/runtime4411419262522357548.scm", 1806361), "/tmp/runtime4411419262522357548.scm", 1802266), "/tmp/runtime4411419262522357548.scm", 1802262), PairWithPosition.make(PairWithPosition.make(simpleSymbol114, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol48, Pair.make(Pair.make(simpleSymbol120, Pair.make((SimpleSymbol) new SimpleSymbol("unregisterEventForDelegation").readResolve(), LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 1986585), PairWithPosition.make(PairWithPosition.make(simpleSymbol63, PairWithPosition.make(simpleSymbol47, PairWithPosition.make(PairWithPosition.make(simpleSymbol69, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1990752), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1990752), "/tmp/runtime4411419262522357548.scm", 1990686), "/tmp/runtime4411419262522357548.scm", 1990682), PairWithPosition.make(simpleSymbol61, PairWithPosition.make(simpleSymbol55, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1994802), "/tmp/runtime4411419262522357548.scm", 1994778), "/tmp/runtime4411419262522357548.scm", 1990682), "/tmp/runtime4411419262522357548.scm", 1986584), PairWithPosition.make(Boolean.FALSE, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1998872), "/tmp/runtime4411419262522357548.scm", 1986584), "/tmp/runtime4411419262522357548.scm", 1982486), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1982486), "/tmp/runtime4411419262522357548.scm", 1802262), "/tmp/runtime4411419262522357548.scm", 1798166), "/tmp/runtime4411419262522357548.scm", 1798162), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1798162), "/tmp/runtime4411419262522357548.scm", 1794065), "/tmp/runtime4411419262522357548.scm", 1794060), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1794060), "/tmp/runtime4411419262522357548.scm", 1769534), "/tmp/runtime4411419262522357548.scm", 1769531), "/tmp/runtime4411419262522357548.scm", 1757200), "/tmp/runtime4411419262522357548.scm", 1757192), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(PairWithPosition.make((SimpleSymbol) new SimpleSymbol("dispatchGenericEvent").readResolve(), PairWithPosition.make(simpleSymbol56, PairWithPosition.make(simpleSymbol104, PairWithPosition.make(simpleSymbol46, PairWithPosition.make(simpleSymbol55, PairWithPosition.make(simpleSymbol104, PairWithPosition.make(simpleSymbol62, PairWithPosition.make(simpleSymbol37, PairWithPosition.make(simpleSymbol104, PairWithPosition.make(simpleSymbol391, PairWithPosition.make(simpleSymbol53, PairWithPosition.make(simpleSymbol104, PairWithPosition.make(simpleSymbol45, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2019374), "/tmp/runtime4411419262522357548.scm", 2019371), "/tmp/runtime4411419262522357548.scm", 2019366), "/tmp/runtime4411419262522357548.scm", 2015291), "/tmp/runtime4411419262522357548.scm", 2015288), "/tmp/runtime4411419262522357548.scm", 2015270), "/tmp/runtime4411419262522357548.scm", 2011187), "/tmp/runtime4411419262522357548.scm", 2011184), "/tmp/runtime4411419262522357548.scm", 2011174), "/tmp/runtime4411419262522357548.scm", 2007097), "/tmp/runtime4411419262522357548.scm", 2007094), "/tmp/runtime4411419262522357548.scm", 2007078), "/tmp/runtime4411419262522357548.scm", 2007056), PairWithPosition.make(simpleSymbol104, PairWithPosition.make(simpleSymbol44, PairWithPosition.make(PairWithPosition.make((SimpleSymbol) new SimpleSymbol("let*").readResolve(), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol40, PairWithPosition.make(PairWithPosition.make(simpleSymbol43, PairWithPosition.make(PairWithPosition.make(simpleSymbol42, PairWithPosition.make("any$", PairWithPosition.make(PairWithPosition.make(simpleSymbol41, PairWithPosition.make(simpleSymbol56, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2056280), "/tmp/runtime4411419262522357548.scm", 2056263), PairWithPosition.make("$", PairWithPosition.make(simpleSymbol55, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2056301), "/tmp/runtime4411419262522357548.scm", 2056297), "/tmp/runtime4411419262522357548.scm", 2056263), "/tmp/runtime4411419262522357548.scm", 2056256), "/tmp/runtime4411419262522357548.scm", 2056241), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2056241), "/tmp/runtime4411419262522357548.scm", 2056225), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2056225), "/tmp/runtime4411419262522357548.scm", 2056209), PairWithPosition.make(PairWithPosition.make(simpleSymbol54, PairWithPosition.make(PairWithPosition.make(simpleSymbol57, PairWithPosition.make(simpleSymbol40, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2060342), "/tmp/runtime4411419262522357548.scm", 2060314), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2060314), "/tmp/runtime4411419262522357548.scm", 2060305), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2060305), "/tmp/runtime4411419262522357548.scm", 2056208), PairWithPosition.make(PairWithPosition.make(simpleSymbol119, PairWithPosition.make(simpleSymbol54, PairWithPosition.make(PairWithPosition.make(simpleSymbol39, PairWithPosition.make(PairWithPosition.make(simpleSymbol114, PairWithPosition.make(PairWithPosition.make(simpleSymbol38, PairWithPosition.make(simpleSymbol54, PairWithPosition.make(PairWithPosition.make(simpleSymbol85, PairWithPosition.make(simpleSymbol56, PairWithPosition.make(PairWithPosition.make(simpleSymbol85, PairWithPosition.make(simpleSymbol37, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol88, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol392, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 2076753), PairWithPosition.make(simpleSymbol53, PairWithPosition.make(make11, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2076783), "/tmp/runtime4411419262522357548.scm", 2076778), "/tmp/runtime4411419262522357548.scm", 2076752), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2076752), "/tmp/runtime4411419262522357548.scm", 2076734), "/tmp/runtime4411419262522357548.scm", 2076728), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2076728), "/tmp/runtime4411419262522357548.scm", 2076712), "/tmp/runtime4411419262522357548.scm", 2076706), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2076706), "/tmp/runtime4411419262522357548.scm", 2076698), "/tmp/runtime4411419262522357548.scm", 2076691), PairWithPosition.make(Boolean.TRUE, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2080787), "/tmp/runtime4411419262522357548.scm", 2076691), "/tmp/runtime4411419262522357548.scm", 2072593), PairWithPosition.make(PairWithPosition.make(simpleSymbol52, PairWithPosition.make(simpleSymbol36, PairWithPosition.make(Boolean.FALSE, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2088979), "/tmp/runtime4411419262522357548.scm", 2084892), "/tmp/runtime4411419262522357548.scm", 2084881), PairWithPosition.make(PairWithPosition.make(simpleSymbol52, PairWithPosition.make(simpleSymbol35, PairWithPosition.make(PairWithPosition.make(simpleSymbol114, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol52, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol49, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 2101269), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2101268), PairWithPosition.make(PairWithPosition.make(simpleSymbol119, PairWithPosition.make(PairWithPosition.make(simpleSymbol51, PairWithPosition.make(PairWithPosition.make(simpleSymbol58, PairWithPosition.make(PairWithPosition.make(simpleSymbol69, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2117666), PairWithPosition.make(simpleSymbol56, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2117673), "/tmp/runtime4411419262522357548.scm", 2117666), "/tmp/runtime4411419262522357548.scm", 2117661), PairWithPosition.make(PairWithPosition.make(simpleSymbol34, PairWithPosition.make(simpleSymbol55, PairWithPosition.make("PermissionNeeded", LList.Empty, "/tmp/runtime4411419262522357548.scm", 2121775), "/tmp/runtime4411419262522357548.scm", 2121765), "/tmp/runtime4411419262522357548.scm", 2121757), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2121757), "/tmp/runtime4411419262522357548.scm", 2117661), "/tmp/runtime4411419262522357548.scm", 2117656), PairWithPosition.make(PairWithPosition.make(simpleSymbol50, PairWithPosition.make(simpleSymbol52, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2138155), "/tmp/runtime4411419262522357548.scm", 2138136), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(PairWithPosition.make(simpleSymbol69, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2142233), Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol33, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 2142233), PairWithPosition.make(simpleSymbol56, PairWithPosition.make(simpleSymbol55, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol52, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol32, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 2146330), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2146329), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2146329), "/tmp/runtime4411419262522357548.scm", 2142273), "/tmp/runtime4411419262522357548.scm", 2142257), "/tmp/runtime4411419262522357548.scm", 2142232), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2142232), "/tmp/runtime4411419262522357548.scm", 2138136), "/tmp/runtime4411419262522357548.scm", 2117656), "/tmp/runtime4411419262522357548.scm", 2117652), PairWithPosition.make(Boolean.FALSE, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2150420), "/tmp/runtime4411419262522357548.scm", 2117652), "/tmp/runtime4411419262522357548.scm", 2101268), "/tmp/runtime4411419262522357548.scm", 2097170), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2097170), "/tmp/runtime4411419262522357548.scm", 2093084), "/tmp/runtime4411419262522357548.scm", 2093073), PairWithPosition.make(PairWithPosition.make(simpleSymbol52, PairWithPosition.make(simpleSymbol31, PairWithPosition.make(PairWithPosition.make(simpleSymbol114, PairWithPosition.make(PairWithPosition.make(simpleSymbol97, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol52, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol66, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 2162727), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2162726), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2162726), "/tmp/runtime4411419262522357548.scm", 2162708), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol52, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol49, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 2170901), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2170900), PairWithPosition.make(PairWithPosition.make(simpleSymbol50, PairWithPosition.make(simpleSymbol52, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2175015), "/tmp/runtime4411419262522357548.scm", 2174996), PairWithPosition.make(Boolean.FALSE, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2179092), "/tmp/runtime4411419262522357548.scm", 2174996), "/tmp/runtime4411419262522357548.scm", 2170900), "/tmp/runtime4411419262522357548.scm", 2162708), "/tmp/runtime4411419262522357548.scm", 2158610), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2158610), "/tmp/runtime4411419262522357548.scm", 2154524), "/tmp/runtime4411419262522357548.scm", 2154513), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2154513), "/tmp/runtime4411419262522357548.scm", 2093073), "/tmp/runtime4411419262522357548.scm", 2084881), "/tmp/runtime4411419262522357548.scm", 2072593), "/tmp/runtime4411419262522357548.scm", 2068496), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2068496), "/tmp/runtime4411419262522357548.scm", 2064400), "/tmp/runtime4411419262522357548.scm", 2064396), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2064396), "/tmp/runtime4411419262522357548.scm", 2056208), "/tmp/runtime4411419262522357548.scm", 2056202), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2056202), "/tmp/runtime4411419262522357548.scm", 2019397), "/tmp/runtime4411419262522357548.scm", 2019394), "/tmp/runtime4411419262522357548.scm", 2007056), "/tmp/runtime4411419262522357548.scm", 2007048), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(PairWithPosition.make(simpleSymbol30, PairWithPosition.make(simpleSymbol29, PairWithPosition.make(simpleSymbol55, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2187310), "/tmp/runtime4411419262522357548.scm", 2187296), "/tmp/runtime4411419262522357548.scm", 2187280), PairWithPosition.make(PairWithPosition.make(simpleSymbol57, PairWithPosition.make(PairWithPosition.make(simpleSymbol43, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol48, Pair.make(Pair.make(simpleSymbol120, Pair.make((SimpleSymbol) new SimpleSymbol("makeFullEventName").readResolve(), LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 2199565), PairWithPosition.make(simpleSymbol29, PairWithPosition.make(simpleSymbol55, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2203675), "/tmp/runtime4411419262522357548.scm", 2203661), "/tmp/runtime4411419262522357548.scm", 2199564), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2199564), "/tmp/runtime4411419262522357548.scm", 2195467), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2195467), "/tmp/runtime4411419262522357548.scm", 2191370), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2191370), "/tmp/runtime4411419262522357548.scm", 2187280), "/tmp/runtime4411419262522357548.scm", 2187272), PairWithPosition.make((SimpleSymbol) new SimpleSymbol("$define").readResolve(), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2220048), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(PairWithPosition.make(simpleSymbol8, PairWithPosition.make(simpleSymbol24, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2232355), "/tmp/runtime4411419262522357548.scm", 2232338), PairWithPosition.make(PairWithPosition.make(simpleSymbol28, PairWithPosition.make(simpleSymbol27, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("<com.google.appinventor.components.runtime.EventDispatcher>").readResolve(), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2240526), "/tmp/runtime4411419262522357548.scm", 2236442), "/tmp/runtime4411419262522357548.scm", 2236428), PairWithPosition.make(PairWithPosition.make(simpleSymbol23, PairWithPosition.make(PairWithPosition.make(simpleSymbol116, PairWithPosition.make(PairWithPosition.make(simpleSymbol25, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2244638), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol27, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol26, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 2252825), PairWithPosition.make(PairWithPosition.make(simpleSymbol63, PairWithPosition.make(simpleSymbol47, PairWithPosition.make(PairWithPosition.make(simpleSymbol69, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2256991), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2256991), "/tmp/runtime4411419262522357548.scm", 2256925), "/tmp/runtime4411419262522357548.scm", 2256921), PairWithPosition.make(PairWithPosition.make(simpleSymbol22, PairWithPosition.make(simpleSymbol25, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2261022), "/tmp/runtime4411419262522357548.scm", 2261017), PairWithPosition.make(PairWithPosition.make((SimpleSymbol) new SimpleSymbol("cdr").readResolve(), PairWithPosition.make(simpleSymbol25, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2265118), "/tmp/runtime4411419262522357548.scm", 2265113), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2265113), "/tmp/runtime4411419262522357548.scm", 2261017), "/tmp/runtime4411419262522357548.scm", 2256921), "/tmp/runtime4411419262522357548.scm", 2252824), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2252824), "/tmp/runtime4411419262522357548.scm", 2244638), "/tmp/runtime4411419262522357548.scm", 2244630), PairWithPosition.make(simpleSymbol24, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2269206), "/tmp/runtime4411419262522357548.scm", 2244630), "/tmp/runtime4411419262522357548.scm", 2244620), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2244620), "/tmp/runtime4411419262522357548.scm", 2236428), "/tmp/runtime4411419262522357548.scm", 2232338), "/tmp/runtime4411419262522357548.scm", 2232330), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(PairWithPosition.make(simpleSymbol3, PairWithPosition.make(simpleSymbol19, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2281513), "/tmp/runtime4411419262522357548.scm", 2281490), PairWithPosition.make(PairWithPosition.make(simpleSymbol23, PairWithPosition.make(PairWithPosition.make(simpleSymbol116, PairWithPosition.make(PairWithPosition.make(simpleSymbol21, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2289694), PairWithPosition.make(PairWithPosition.make(simpleSymbol115, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol76, PairWithPosition.make(PairWithPosition.make(simpleSymbol22, PairWithPosition.make(simpleSymbol21, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2293800), "/tmp/runtime4411419262522357548.scm", 2293795), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2293795), "/tmp/runtime4411419262522357548.scm", 2293790), PairWithPosition.make(PairWithPosition.make(simpleSymbol75, PairWithPosition.make(PairWithPosition.make(simpleSymbol17, PairWithPosition.make(simpleSymbol21, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2297903), "/tmp/runtime4411419262522357548.scm", 2297897), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2297897), "/tmp/runtime4411419262522357548.scm", 2297886), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2297886), "/tmp/runtime4411419262522357548.scm", 2293789), PairWithPosition.make(PairWithPosition.make(simpleSymbol20, PairWithPosition.make(simpleSymbol76, PairWithPosition.make(PairWithPosition.make(simpleSymbol75, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2302013), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2302013), "/tmp/runtime4411419262522357548.scm", 2302009), "/tmp/runtime4411419262522357548.scm", 2301978), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2301978), "/tmp/runtime4411419262522357548.scm", 2293789), "/tmp/runtime4411419262522357548.scm", 2293784), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2293784), "/tmp/runtime4411419262522357548.scm", 2289694), "/tmp/runtime4411419262522357548.scm", 2289686), PairWithPosition.make(simpleSymbol19, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2306070), "/tmp/runtime4411419262522357548.scm", 2289686), "/tmp/runtime4411419262522357548.scm", 2289676), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2289676), "/tmp/runtime4411419262522357548.scm", 2281490), "/tmp/runtime4411419262522357548.scm", 2281482), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(PairWithPosition.make(simpleSymbol5, PairWithPosition.make(simpleSymbol14, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2318373), "/tmp/runtime4411419262522357548.scm", 2318354), PairWithPosition.make(PairWithPosition.make(simpleSymbol23, PairWithPosition.make(PairWithPosition.make(simpleSymbol116, PairWithPosition.make(PairWithPosition.make(simpleSymbol18, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2322462), PairWithPosition.make(PairWithPosition.make(simpleSymbol115, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol84, PairWithPosition.make(PairWithPosition.make(simpleSymbol13, PairWithPosition.make(simpleSymbol18, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2326581), "/tmp/runtime4411419262522357548.scm", 2326574), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2326574), "/tmp/runtime4411419262522357548.scm", 2326558), PairWithPosition.make(PairWithPosition.make(simpleSymbol78, PairWithPosition.make(PairWithPosition.make(simpleSymbol12, PairWithPosition.make(simpleSymbol18, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2330674), "/tmp/runtime4411419262522357548.scm", 2330666), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2330666), "/tmp/runtime4411419262522357548.scm", 2330654), PairWithPosition.make(PairWithPosition.make(simpleSymbol79, PairWithPosition.make(PairWithPosition.make(simpleSymbol17, PairWithPosition.make(simpleSymbol18, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2334772), "/tmp/runtime4411419262522357548.scm", 2334766), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2334766), "/tmp/runtime4411419262522357548.scm", 2334750), PairWithPosition.make(PairWithPosition.make(simpleSymbol16, PairWithPosition.make(PairWithPosition.make(simpleSymbol57, PairWithPosition.make(PairWithPosition.make(simpleSymbol22, PairWithPosition.make(simpleSymbol18, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2338900), "/tmp/runtime4411419262522357548.scm", 2338895), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2338895), "/tmp/runtime4411419262522357548.scm", 2338867), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2338867), "/tmp/runtime4411419262522357548.scm", 2338846), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2338846), "/tmp/runtime4411419262522357548.scm", 2334750), "/tmp/runtime4411419262522357548.scm", 2330654), "/tmp/runtime4411419262522357548.scm", 2326557), PairWithPosition.make(PairWithPosition.make(simpleSymbol115, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol15, PairWithPosition.make(PairWithPosition.make(simpleSymbol91, PairWithPosition.make(simpleSymbol79, PairWithPosition.make(simpleSymbol16, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2355271), "/tmp/runtime4411419262522357548.scm", 2355256), "/tmp/runtime4411419262522357548.scm", 2355250), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2355250), "/tmp/runtime4411419262522357548.scm", 2355232), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2355231), PairWithPosition.make(PairWithPosition.make(simpleSymbol82, PairWithPosition.make(PairWithPosition.make(simpleSymbol11, PairWithPosition.make(PairWithPosition.make(simpleSymbol69, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2363433), PairWithPosition.make(simpleSymbol84, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2363440), "/tmp/runtime4411419262522357548.scm", 2363433), "/tmp/runtime4411419262522357548.scm", 2363426), PairWithPosition.make(simpleSymbol15, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2363456), "/tmp/runtime4411419262522357548.scm", 2363426), "/tmp/runtime4411419262522357548.scm", 2363420), PairWithPosition.make(PairWithPosition.make(simpleSymbol98, PairWithPosition.make(simpleSymbol84, PairWithPosition.make(simpleSymbol15, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2375748), "/tmp/runtime4411419262522357548.scm", 2375733), "/tmp/runtime4411419262522357548.scm", 2375708), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2375708), "/tmp/runtime4411419262522357548.scm", 2363420), "/tmp/runtime4411419262522357548.scm", 2355231), "/tmp/runtime4411419262522357548.scm", 2355226), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2355226), "/tmp/runtime4411419262522357548.scm", 2326557), "/tmp/runtime4411419262522357548.scm", 2326552), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2326552), "/tmp/runtime4411419262522357548.scm", 2322462), "/tmp/runtime4411419262522357548.scm", 2322454), PairWithPosition.make(simpleSymbol14, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2379798), "/tmp/runtime4411419262522357548.scm", 2322454), "/tmp/runtime4411419262522357548.scm", 2322444), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2322444), "/tmp/runtime4411419262522357548.scm", 2318354), "/tmp/runtime4411419262522357548.scm", 2318346), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol14, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2392099), "/tmp/runtime4411419262522357548.scm", 2392082), PairWithPosition.make(PairWithPosition.make(simpleSymbol23, PairWithPosition.make(PairWithPosition.make(simpleSymbol116, PairWithPosition.make(PairWithPosition.make(simpleSymbol18, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2400286), PairWithPosition.make(PairWithPosition.make(simpleSymbol115, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol84, PairWithPosition.make(PairWithPosition.make(simpleSymbol13, PairWithPosition.make(simpleSymbol18, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2404405), "/tmp/runtime4411419262522357548.scm", 2404398), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2404398), "/tmp/runtime4411419262522357548.scm", 2404382), PairWithPosition.make(PairWithPosition.make(simpleSymbol78, PairWithPosition.make(PairWithPosition.make(simpleSymbol12, PairWithPosition.make(simpleSymbol18, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2408498), "/tmp/runtime4411419262522357548.scm", 2408490), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2408490), "/tmp/runtime4411419262522357548.scm", 2408478), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2408478), "/tmp/runtime4411419262522357548.scm", 2404381), PairWithPosition.make(PairWithPosition.make(simpleSymbol70, PairWithPosition.make(simpleSymbol78, PairWithPosition.make(PairWithPosition.make(simpleSymbol78, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2416683), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2416683), "/tmp/runtime4411419262522357548.scm", 2416672), "/tmp/runtime4411419262522357548.scm", 2416666), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2416666), "/tmp/runtime4411419262522357548.scm", 2404381), "/tmp/runtime4411419262522357548.scm", 2404376), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2404376), "/tmp/runtime4411419262522357548.scm", 2400286), "/tmp/runtime4411419262522357548.scm", 2400278), PairWithPosition.make(simpleSymbol14, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2420758), "/tmp/runtime4411419262522357548.scm", 2400278), "/tmp/runtime4411419262522357548.scm", 2400268), PairWithPosition.make(PairWithPosition.make(simpleSymbol23, PairWithPosition.make(PairWithPosition.make(simpleSymbol116, PairWithPosition.make(PairWithPosition.make(simpleSymbol18, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2428958), PairWithPosition.make(PairWithPosition.make(simpleSymbol115, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol84, PairWithPosition.make(PairWithPosition.make(simpleSymbol13, PairWithPosition.make(simpleSymbol18, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2433077), "/tmp/runtime4411419262522357548.scm", 2433070), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2433070), "/tmp/runtime4411419262522357548.scm", 2433054), PairWithPosition.make(PairWithPosition.make(simpleSymbol78, PairWithPosition.make(PairWithPosition.make(simpleSymbol12, PairWithPosition.make(simpleSymbol18, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2437170), "/tmp/runtime4411419262522357548.scm", 2437162), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2437162), "/tmp/runtime4411419262522357548.scm", 2437150), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2437150), "/tmp/runtime4411419262522357548.scm", 2433053), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(PairWithPosition.make(simpleSymbol69, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2445339), Pair.make(Pair.make(simpleSymbol120, Pair.make((SimpleSymbol) new SimpleSymbol("callInitialize").readResolve(), LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 2445339), PairWithPosition.make(PairWithPosition.make(simpleSymbol11, PairWithPosition.make(PairWithPosition.make(simpleSymbol69, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2445368), PairWithPosition.make(simpleSymbol84, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2445375), "/tmp/runtime4411419262522357548.scm", 2445368), "/tmp/runtime4411419262522357548.scm", 2445361), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2445361), "/tmp/runtime4411419262522357548.scm", 2445338), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2445338), "/tmp/runtime4411419262522357548.scm", 2433053), "/tmp/runtime4411419262522357548.scm", 2433048), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2433048), "/tmp/runtime4411419262522357548.scm", 2428958), "/tmp/runtime4411419262522357548.scm", 2428950), PairWithPosition.make(simpleSymbol14, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2449430), "/tmp/runtime4411419262522357548.scm", 2428950), "/tmp/runtime4411419262522357548.scm", 2428940), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2428940), "/tmp/runtime4411419262522357548.scm", 2400268), "/tmp/runtime4411419262522357548.scm", 2392082), "/tmp/runtime4411419262522357548.scm", 2392074), PairWithPosition.make(simpleSymbol107, PairWithPosition.make(PairWithPosition.make(simpleSymbol386, simpleSymbol9, "/tmp/runtime4411419262522357548.scm", 2461714), PairWithPosition.make(PairWithPosition.make(simpleSymbol43, PairWithPosition.make(PairWithPosition.make(simpleSymbol38, PairWithPosition.make(simpleSymbol42, PairWithPosition.make(PairWithPosition.make((SimpleSymbol) new SimpleSymbol("map").readResolve(), PairWithPosition.make(simpleSymbol10, PairWithPosition.make(simpleSymbol9, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2474024), "/tmp/runtime4411419262522357548.scm", 2474009), "/tmp/runtime4411419262522357548.scm", 2474004), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2474004), "/tmp/runtime4411419262522357548.scm", 2469908), "/tmp/runtime4411419262522357548.scm", 2469901), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2469901), "/tmp/runtime4411419262522357548.scm", 2465804), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2465804), "/tmp/runtime4411419262522357548.scm", 2461714), "/tmp/runtime4411419262522357548.scm", 2461706), PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make((SimpleSymbol) new SimpleSymbol("gnu.expr.Language").readResolve(), Pair.make(Pair.make(simpleSymbol120, Pair.make((SimpleSymbol) new SimpleSymbol("setDefaults").readResolve(), LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 2494475), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make((SimpleSymbol) new SimpleSymbol("kawa.standard.Scheme").readResolve(), Pair.make(Pair.make(simpleSymbol120, Pair.make((SimpleSymbol) new SimpleSymbol("getInstance").readResolve(), LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 2494506), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2494505), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2494505), "/tmp/runtime4411419262522357548.scm", 2494474), PairWithPosition.make(simpleSymbol39, PairWithPosition.make(PairWithPosition.make((SimpleSymbol) new SimpleSymbol("invoke").readResolve(), PairWithPosition.make(PairWithPosition.make(simpleSymbol69, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2531347), PairWithPosition.make(PairWithPosition.make(simpleSymbol109, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("run").readResolve(), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2531355), "/tmp/runtime4411419262522357548.scm", 2531355), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2531354), "/tmp/runtime4411419262522357548.scm", 2531347), "/tmp/runtime4411419262522357548.scm", 2531339), PairWithPosition.make(PairWithPosition.make(simpleSymbol52, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("java.lang.Exception").readResolve(), PairWithPosition.make(PairWithPosition.make(simpleSymbol97, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol121, Pair.make(simpleSymbol52, Pair.make(Pair.make(simpleSymbol120, Pair.make(simpleSymbol66, LList.Empty)), LList.Empty)), "/tmp/runtime4411419262522357548.scm", 2539551), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2539550), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2539550), "/tmp/runtime4411419262522357548.scm", 2539532), PairWithPosition.make(PairWithPosition.make(simpleSymbol50, PairWithPosition.make(simpleSymbol52, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2543647), "/tmp/runtime4411419262522357548.scm", 2543628), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2543628), "/tmp/runtime4411419262522357548.scm", 2539532), "/tmp/runtime4411419262522357548.scm", 2535446), "/tmp/runtime4411419262522357548.scm", 2535435), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2535435), "/tmp/runtime4411419262522357548.scm", 2531339), "/tmp/runtime4411419262522357548.scm", 2527242), simpleSymbol82, PairWithPosition.make(PairWithPosition.make(simpleSymbol69, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2547738), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2547738), simpleSymbol98, PairWithPosition.make(PairWithPosition.make(simpleSymbol69, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2555950), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2555950), PairWithPosition.make(PairWithPosition.make(simpleSymbol8, PairWithPosition.make(simpleSymbol86, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2564123), "/tmp/runtime4411419262522357548.scm", 2564106), PairWithPosition.make(PairWithPosition.make(simpleSymbol39, PairWithPosition.make(PairWithPosition.make(simpleSymbol115, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(simpleSymbol4, PairWithPosition.make(PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol81, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2576422), "/tmp/runtime4411419262522357548.scm", 2576413), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2576413), "/tmp/runtime4411419262522357548.scm", 2576401), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2576400), PairWithPosition.make(PairWithPosition.make(simpleSymbol108, PairWithPosition.make(PairWithPosition.make(simpleSymbol109, PairWithPosition.make(simpleSymbol7, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2596898), "/tmp/runtime4411419262522357548.scm", 2596898), PairWithPosition.make(PairWithPosition.make(simpleSymbol116, PairWithPosition.make(LList.Empty, PairWithPosition.make(null, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2596926), "/tmp/runtime4411419262522357548.scm", 2596923), "/tmp/runtime4411419262522357548.scm", 2596915), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2596915), "/tmp/runtime4411419262522357548.scm", 2596897), "/tmp/runtime4411419262522357548.scm", 2596877), PairWithPosition.make(PairWithPosition.make(simpleSymbol23, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("force").readResolve(), PairWithPosition.make(PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol73, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2613286), "/tmp/runtime4411419262522357548.scm", 2613277), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2613277), "/tmp/runtime4411419262522357548.scm", 2613271), "/tmp/runtime4411419262522357548.scm", 2613261), PairWithPosition.make(PairWithPosition.make(simpleSymbol5, PairWithPosition.make(simpleSymbol4, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2617376), "/tmp/runtime4411419262522357548.scm", 2617357), PairWithPosition.make(PairWithPosition.make(simpleSymbol3, PairWithPosition.make(PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol77, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2641965), "/tmp/runtime4411419262522357548.scm", 2641956), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2641956), "/tmp/runtime4411419262522357548.scm", 2641933), PairWithPosition.make(PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol4, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2670622), "/tmp/runtime4411419262522357548.scm", 2670605), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2670605), "/tmp/runtime4411419262522357548.scm", 2641933), "/tmp/runtime4411419262522357548.scm", 2617357), "/tmp/runtime4411419262522357548.scm", 2613261), "/tmp/runtime4411419262522357548.scm", 2596877), "/tmp/runtime4411419262522357548.scm", 2576400), "/tmp/runtime4411419262522357548.scm", 2576395), PairWithPosition.make(PairWithPosition.make(simpleSymbol52, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.errors.YailRuntimeError").readResolve(), PairWithPosition.make(PairWithPosition.make(simpleSymbol50, PairWithPosition.make(simpleSymbol52, LList.Empty, "/tmp/runtime4411419262522357548.scm", 2682921), "/tmp/runtime4411419262522357548.scm", 2682902), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2682902), "/tmp/runtime4411419262522357548.scm", 2674710), "/tmp/runtime4411419262522357548.scm", 2674699), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2674699), "/tmp/runtime4411419262522357548.scm", 2576395), "/tmp/runtime4411419262522357548.scm", 2572298), LList.Empty, "/tmp/runtime4411419262522357548.scm", 2572298), "/tmp/runtime4411419262522357548.scm", 2564106)}, 0)}, 5);
        Lit97 = syntaxRules17;
        SimpleSymbol simpleSymbol393 = (SimpleSymbol) new SimpleSymbol("define-form-internal").readResolve();
        Lit96 = simpleSymbol393;
        SyntaxRules syntaxRules18 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\t\u000b\u0018\f", new Object[]{simpleSymbol393, PairWithPosition.make(PairWithPosition.make(simpleSymbol109, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.ReplForm").readResolve(), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1257522), "/tmp/runtime4411419262522357548.scm", 1257522), PairWithPosition.make(Boolean.TRUE, PairWithPosition.make(Boolean.FALSE, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1257576), "/tmp/runtime4411419262522357548.scm", 1257573), "/tmp/runtime4411419262522357548.scm", 1257521)}, 0)}, 2);
        Lit95 = syntaxRules18;
        SimpleSymbol simpleSymbol394 = (SimpleSymbol) new SimpleSymbol("define-repl-form").readResolve();
        Lit94 = simpleSymbol394;
        SyntaxPattern syntaxPattern3 = new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        SimpleSymbol simpleSymbol395 = (SimpleSymbol) new SimpleSymbol("com.google.appinventor.components.runtime.Form").readResolve();
        Lit17 = simpleSymbol395;
        SyntaxRules syntaxRules19 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(syntaxPattern3, "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\t\u000b\u0018\f", new Object[]{simpleSymbol393, PairWithPosition.make(PairWithPosition.make(simpleSymbol109, PairWithPosition.make(simpleSymbol395, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1228850), "/tmp/runtime4411419262522357548.scm", 1228850), PairWithPosition.make(Boolean.FALSE, PairWithPosition.make(Boolean.TRUE, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1228900), "/tmp/runtime4411419262522357548.scm", 1228897), "/tmp/runtime4411419262522357548.scm", 1228849)}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3), "\u0001\u0001\u0001", "\u0011\u0018\u0004\t\u0003\t\u000b\u0011\u0018\f\u0011\u0018\u0014\b\u0013", new Object[]{simpleSymbol393, PairWithPosition.make(simpleSymbol109, PairWithPosition.make(simpleSymbol395, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1237042), "/tmp/runtime4411419262522357548.scm", 1237042), Boolean.FALSE}, 0)}, 3);
        Lit93 = syntaxRules19;
        SimpleSymbol simpleSymbol396 = (SimpleSymbol) new SimpleSymbol("define-form").readResolve();
        Lit92 = simpleSymbol396;
        SyntaxRules syntaxRules20 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\r\u0007\u0000\b\b", new Object[0], 1), "\u0003", "\u0011\u0018\u0004\b\u0005\u0011\u0018\f\t\u0010\b\u0003", new Object[]{simpleSymbol299, simpleSymbol116}, 1)}, 1);
        Lit91 = syntaxRules20;
        SimpleSymbol simpleSymbol397 = (SimpleSymbol) new SimpleSymbol("or-delayed").readResolve();
        Lit90 = simpleSymbol397;
        SyntaxRules syntaxRules21 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\r\u0007\u0000\b\b", new Object[0], 1), "\u0003", "\u0011\u0018\u0004\b\u0005\u0011\u0018\f\t\u0010\b\u0003", new Object[]{simpleSymbol300, simpleSymbol116}, 1)}, 1);
        Lit89 = syntaxRules21;
        SimpleSymbol simpleSymbol398 = (SimpleSymbol) new SimpleSymbol("and-delayed").readResolve();
        Lit88 = simpleSymbol398;
        SyntaxRules syntaxRules22 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\b\u000b", new Object[]{simpleSymbol82}, 0)}, 2);
        Lit87 = syntaxRules22;
        SimpleSymbol simpleSymbol399 = (SimpleSymbol) new SimpleSymbol("set-lexical!").readResolve();
        Lit86 = simpleSymbol399;
        SyntaxRules syntaxRules23 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u00049\u0011\u0018\f\t\u0003\u0018\u0014Á\u0011\u0018\u001c\u0091\u0011\u0018$\u0011\u0018,I\u0011\u00184\b\u0011\u0018<\b\u0003\u0018D\u0018L\b\u0003", new Object[]{simpleSymbol119, simpleSymbol65, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("<java.lang.Package>").readResolve(), LList.Empty, "/tmp/runtime4411419262522357548.scm", 1081374), simpleSymbol340, simpleSymbol42, "The variable ", simpleSymbol317, simpleSymbol120, PairWithPosition.make(" is not bound in the current context", LList.Empty, "/tmp/runtime4411419262522357548.scm", 1093658), PairWithPosition.make("Unbound Variable", LList.Empty, "/tmp/runtime4411419262522357548.scm", 1097739)}, 0)}, 1);
        Lit85 = syntaxRules23;
        SimpleSymbol simpleSymbol400 = (SimpleSymbol) new SimpleSymbol("lexical-value").readResolve();
        Lit84 = simpleSymbol400;
        SyntaxRules syntaxRules24 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2), "\u0001\u0001", "\u0011\u0018\u0004)\u0011\u0018\f\b\u0003\b\u000b", new Object[]{simpleSymbol371, simpleSymbol109}, 0)}, 2);
        Lit83 = syntaxRules24;
        SimpleSymbol simpleSymbol401 = (SimpleSymbol) new SimpleSymbol("set-var!").readResolve();
        Lit82 = simpleSymbol401;
        SyntaxRules syntaxRules25 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004)\u0011\u0018\f\b\u0003\u0018\u0014", new Object[]{simpleSymbol370, simpleSymbol109, PairWithPosition.make(simpleSymbol7, LList.Empty, "/tmp/runtime4411419262522357548.scm", 1015871)}, 0)}, 1);
        Lit81 = syntaxRules25;
        SimpleSymbol simpleSymbol402 = (SimpleSymbol) new SimpleSymbol("get-var").readResolve();
        Lit80 = simpleSymbol402;
        SimpleSymbol simpleSymbol403 = (SimpleSymbol) new SimpleSymbol("set-and-coerce-property-and-check!").readResolve();
        Lit79 = simpleSymbol403;
        SimpleSymbol simpleSymbol404 = (SimpleSymbol) new SimpleSymbol("get-property-and-check").readResolve();
        Lit78 = simpleSymbol404;
        SimpleSymbol simpleSymbol405 = (SimpleSymbol) new SimpleSymbol("coerce-to-component-and-verify").readResolve();
        Lit77 = simpleSymbol405;
        SimpleSymbol simpleSymbol406 = (SimpleSymbol) new SimpleSymbol("get-property").readResolve();
        Lit76 = simpleSymbol406;
        SimpleSymbol simpleSymbol407 = (SimpleSymbol) new SimpleSymbol("set-and-coerce-property!").readResolve();
        Lit75 = simpleSymbol407;
        SimpleSymbol simpleSymbol408 = (SimpleSymbol) new SimpleSymbol("lookup-component").readResolve();
        Lit74 = simpleSymbol408;
        SyntaxRules syntaxRules26 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\b\u0011\u0018\f\b\u0003", new Object[]{simpleSymbol374, simpleSymbol109}, 0)}, 1);
        Lit73 = syntaxRules26;
        SimpleSymbol simpleSymbol409 = (SimpleSymbol) new SimpleSymbol("get-all-components").readResolve();
        Lit72 = simpleSymbol409;
        SyntaxRules syntaxRules27 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\b\u0011\u0018\f\b\u0003", new Object[]{simpleSymbol375, simpleSymbol109}, 0)}, 1);
        Lit71 = syntaxRules27;
        SimpleSymbol simpleSymbol410 = (SimpleSymbol) new SimpleSymbol("get-component").readResolve();
        Lit70 = simpleSymbol410;
        SimpleSymbol simpleSymbol411 = (SimpleSymbol) new SimpleSymbol("clear-init-thunks").readResolve();
        Lit69 = simpleSymbol411;
        SimpleSymbol simpleSymbol412 = (SimpleSymbol) new SimpleSymbol("get-init-thunk").readResolve();
        Lit68 = simpleSymbol412;
        SimpleSymbol simpleSymbol413 = (SimpleSymbol) new SimpleSymbol("add-init-thunk").readResolve();
        Lit67 = simpleSymbol413;
        SimpleSymbol simpleSymbol414 = (SimpleSymbol) new SimpleSymbol("call-Initialize-of-components").readResolve();
        Lit66 = simpleSymbol414;
        SimpleSymbol simpleSymbol415 = (SimpleSymbol) new SimpleSymbol("add-component-within-repl").readResolve();
        Lit65 = simpleSymbol415;
        SyntaxPattern syntaxPattern4 = new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
        SimpleSymbol simpleSymbol416 = (SimpleSymbol) new SimpleSymbol("gen-simple-component-type").readResolve();
        Lit60 = simpleSymbol416;
        SyntaxRules syntaxRules28 = new SyntaxRules(new Object[]{simpleSymbol122}, new SyntaxRule[]{new SyntaxRule(syntaxPattern4, "\u0001\u0001\u0001", "\u0011\u0018\u0004\u0081\u0011\u0018\f\t\u0013\u0011\u0018\u0014)\u0011\u0018\u001c\b\u000b\u0018$\b\u0011\u0018,\u0011\u00184¹\u0011\u0018<)\u0011\u0018D\b\u0003)\u0011\u0018\u001c\b\u000b)\u0011\u0018D\b\u0013\u0018L\b\u0011\u0018T)\u0011\u0018D\b\u0003)\u0011\u0018\u001c\b\u000b)\u0011\u0018D\b\u0013\u0018\\", new Object[]{simpleSymbol114, simpleSymbol107, simpleSymbol104, simpleSymbol416, PairWithPosition.make(null, LList.Empty, "/tmp/runtime4411419262522357548.scm", 241741), simpleSymbol119, simpleSymbol110, simpleSymbol415, simpleSymbol109, PairWithPosition.make(Boolean.FALSE, LList.Empty, "/tmp/runtime4411419262522357548.scm", 262183), simpleSymbol, PairWithPosition.make(Boolean.FALSE, LList.Empty, "/tmp/runtime4411419262522357548.scm", 278559)}, 0), new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\r\u001f\u0018\b\b", new Object[0], 4), "\u0001\u0001\u0001\u0003", "\u0011\u0018\u0004\u0081\u0011\u0018\f\t\u0013\u0011\u0018\u0014)\u0011\u0018\u001c\b\u000b\u0018$\b\u0011\u0018,\u0011\u00184ñ\u0011\u0018<)\u0011\u0018D\b\u0003)\u0011\u0018\u001c\b\u000b)\u0011\u0018D\b\u0013\b\u0011\u0018L\t\u0010\b\u001d\u001b\b\u0011\u0018T)\u0011\u0018D\b\u0003)\u0011\u0018\u001c\b\u000b)\u0011\u0018D\b\u0013\b\u0011\u0018L\t\u0010\b\u001d\u001b", new Object[]{simpleSymbol114, simpleSymbol107, simpleSymbol104, simpleSymbol416, PairWithPosition.make(null, LList.Empty, "/tmp/runtime4411419262522357548.scm", 290893), simpleSymbol119, simpleSymbol110, simpleSymbol415, simpleSymbol109, simpleSymbol116, simpleSymbol}, 1)}, 4);
        Lit64 = syntaxRules28;
        SimpleSymbol simpleSymbol417 = (SimpleSymbol) new SimpleSymbol("add-component").readResolve();
        Lit63 = simpleSymbol417;
        Lit62 = new SyntaxTemplate("\u0001\u0001", "\u000b", new Object[0], 0);
        Lit61 = new SyntaxPattern("\f\u0007\f\u000f\b", new Object[0], 2);
        SimpleSymbol simpleSymbol418 = (SimpleSymbol) new SimpleSymbol("android-log").readResolve();
        Lit59 = simpleSymbol418;
        SimpleSymbol simpleSymbol419 = (SimpleSymbol) new SimpleSymbol("non-coercible-value").readResolve();
        Lit58 = simpleSymbol419;
        SimpleSymbol simpleSymbol420 = (SimpleSymbol) new SimpleSymbol("possible-component").readResolve();
        Lit57 = simpleSymbol420;
        Lit56 = (SimpleSymbol) new SimpleSymbol("post").readResolve();
        Lit55 = (SimpleSymbol) new SimpleSymbol("getDhcpInfo").readResolve();
        Lit54 = IntNum.make(9999);
        Lit53 = IntNum.make(4);
        Lit52 = IntNum.make(3);
        Lit51 = IntNum.make(16);
        Lit50 = IntNum.make(24);
        Lit48 = IntNum.make(8);
        Lit47 = IntNum.make(255);
        Lit46 = (SimpleSymbol) new SimpleSymbol("setValueForKeyPath").readResolve();
        SimpleSymbol simpleSymbol421 = (SimpleSymbol) new SimpleSymbol("number").readResolve();
        Lit5 = simpleSymbol421;
        SimpleSymbol simpleSymbol422 = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_TEXT).readResolve();
        Lit6 = simpleSymbol422;
        SimpleSymbol simpleSymbol423 = (SimpleSymbol) new SimpleSymbol("component").readResolve();
        Lit11 = simpleSymbol423;
        Lit45 = PairWithPosition.make(simpleSymbol391, PairWithPosition.make(simpleSymbol421, PairWithPosition.make(simpleSymbol422, PairWithPosition.make(simpleSymbol384, PairWithPosition.make(simpleSymbol423, LList.Empty, "/tmp/runtime4411419262522357548.scm", 11178033), "/tmp/runtime4411419262522357548.scm", 11178028), "/tmp/runtime4411419262522357548.scm", 11178023), "/tmp/runtime4411419262522357548.scm", 11178016), "/tmp/runtime4411419262522357548.scm", 11178007);
        Lit43 = (SimpleSymbol) new SimpleSymbol("*list*").readResolve();
        IntNum make12 = IntNum.make(1);
        Lit24 = make12;
        Lit42 = new IntFraction(make12, make11);
        IntNum make13 = IntNum.make(-1);
        Lit34 = make13;
        Lit41 = new IntFraction(make13, make11);
        Lit40 = DFloNum.make(1.0E18d);
        Lit39 = Char.make(57343);
        Lit38 = Char.make(55296);
        Lit37 = Char.make(57343);
        Lit36 = Char.make(55296);
        Lit35 = IntNum.make(45);
        Lit33 = IntNum.make(90);
        Lit32 = IntNum.make(360);
        Lit31 = DFloNum.make(6.2831853d);
        Lit30 = DFloNum.make(6.2831853d);
        Lit29 = IntNum.make(180);
        Lit28 = DFloNum.make(3.14159265d);
        Lit27 = IntNum.make(30);
        Lit26 = IntNum.make(2);
        Lit23 = (SimpleSymbol) new SimpleSymbol("toYailDictionary").readResolve();
        Lit22 = DFloNum.make(Double.NEGATIVE_INFINITY);
        Lit21 = DFloNum.make(Double.POSITIVE_INFINITY);
        Lit20 = DFloNum.make(Double.NEGATIVE_INFINITY);
        Lit19 = DFloNum.make(Double.POSITIVE_INFINITY);
        Lit18 = (SimpleSymbol) new SimpleSymbol("toUnderlyingValue").readResolve();
        Lit16 = (SimpleSymbol) new SimpleSymbol("Screen").readResolve();
        Lit15 = (SimpleSymbol) new SimpleSymbol("any").readResolve();
        Lit14 = (SimpleSymbol) new SimpleSymbol("dictionary").readResolve();
        Lit13 = (SimpleSymbol) new SimpleSymbol("key").readResolve();
        Lit12 = (SimpleSymbol) new SimpleSymbol("pair").readResolve();
        Lit10 = (SimpleSymbol) new SimpleSymbol("InstantInTime").readResolve();
        Lit9 = (SimpleSymbol) new SimpleSymbol("list-of-number").readResolve();
        Lit4 = Object.class;
        Lit3 = (SimpleSymbol) new SimpleSymbol("remove").readResolve();
        Lit2 = PairWithPosition.make((SimpleSymbol) new SimpleSymbol("non-coercible").readResolve(), LList.Empty, "/tmp/runtime4411419262522357548.scm", 4390944);
        AssetFetcher = AssetFetcher.class;
        errorMessages = ErrorMessages.class;
        JavaMap = Map.class;
        JavaIterator = Iterator.class;
        JavaCollection = Collection.class;
        YailNumberToString = YailNumberToString.class;
        YailDictionary = YailDictionary.class;
        YailList = YailList.class;
        JavaStringUtils = JavaStringUtils.class;
        YailRuntimeError = YailRuntimeError.class;
        StopBlocksExecution = StopBlocksExecution.class;
        PermissionException = PermissionException.class;
        CsvUtil = CsvUtil.class;
        ContinuationUtil = ContinuationUtil.class;
        Matcher = Matcher.class;
        Pattern = Pattern.class;
        String = String.class;
        Short = Short.class;
        Long = Long.class;
        KawaEnvironment = Environment.class;
        Integer = Integer.class;
        Float = Float.class;
        Double = Double.class;
        TypeUtil = TypeUtil.class;
        SimpleForm = ClassType.make("com.google.appinventor.components.runtime.Form");
        runtime runtimeVar = new runtime();
        $instance = runtimeVar;
        loc$possible$Mncomponent = ThreadLocation.getInstance(simpleSymbol420, null);
        loc$component = ThreadLocation.getInstance(simpleSymbol423, null);
        loc$non$Mncoercible$Mnvalue = ThreadLocation.getInstance(simpleSymbol419, null);
        android$Mnlog = new ModuleMethod(runtimeVar, 15, simpleSymbol418, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        simple$Mncomponent$Mnpackage$Mnname = "com.google.appinventor.components.runtime";
        ModuleMethod moduleMethod = new ModuleMethod(runtimeVar, 16, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:40");
        gen$Mnsimple$Mncomponent$Mntype = Macro.make(simpleSymbol416, moduleMethod, runtimeVar);
        add$Mncomponent = Macro.make(simpleSymbol417, syntaxRules28, runtimeVar);
        add$Mncomponent$Mnwithin$Mnrepl = new ModuleMethod(runtimeVar, 17, simpleSymbol415, 16388);
        call$MnInitialize$Mnof$Mncomponents = new ModuleMethod(runtimeVar, 18, simpleSymbol414, -4096);
        add$Mninit$Mnthunk = new ModuleMethod(runtimeVar, 19, simpleSymbol413, 8194);
        get$Mninit$Mnthunk = new ModuleMethod(runtimeVar, 20, simpleSymbol412, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        clear$Mninit$Mnthunks = new ModuleMethod(runtimeVar, 21, simpleSymbol411, 0);
        get$Mncomponent = Macro.make(simpleSymbol410, syntaxRules27, runtimeVar);
        get$Mnall$Mncomponents = Macro.make(simpleSymbol409, syntaxRules26, runtimeVar);
        lookup$Mncomponent = new ModuleMethod(runtimeVar, 22, simpleSymbol408, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        set$Mnand$Mncoerce$Mnproperty$Ex = new ModuleMethod(runtimeVar, 23, simpleSymbol407, 16388);
        get$Mnproperty = new ModuleMethod(runtimeVar, 24, simpleSymbol406, 8194);
        coerce$Mnto$Mncomponent$Mnand$Mnverify = new ModuleMethod(runtimeVar, 25, simpleSymbol405, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        get$Mnproperty$Mnand$Mncheck = new ModuleMethod(runtimeVar, 26, simpleSymbol404, 12291);
        set$Mnand$Mncoerce$Mnproperty$Mnand$Mncheck$Ex = new ModuleMethod(runtimeVar, 27, simpleSymbol403, 20485);
        get$Mnvar = Macro.make(simpleSymbol402, syntaxRules25, runtimeVar);
        set$Mnvar$Ex = Macro.make(simpleSymbol401, syntaxRules24, runtimeVar);
        lexical$Mnvalue = Macro.make(simpleSymbol400, syntaxRules23, runtimeVar);
        set$Mnlexical$Ex = Macro.make(simpleSymbol399, syntaxRules22, runtimeVar);
        and$Mndelayed = Macro.make(simpleSymbol398, syntaxRules21, runtimeVar);
        or$Mndelayed = Macro.make(simpleSymbol397, syntaxRules20, runtimeVar);
        define$Mnform = Macro.make(simpleSymbol396, syntaxRules19, runtimeVar);
        define$Mnrepl$Mnform = Macro.make(simpleSymbol394, syntaxRules18, runtimeVar);
        define$Mnform$Mninternal = Macro.make(simpleSymbol393, syntaxRules17, runtimeVar);
        symbol$Mnappend = new ModuleMethod(runtimeVar, 28, simpleSymbol386, -4096);
        ModuleMethod moduleMethod2 = new ModuleMethod(runtimeVar, 29, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod2.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:669");
        gen$Mnevent$Mnname = Macro.make(simpleSymbol388, moduleMethod2, runtimeVar);
        ModuleMethod moduleMethod3 = new ModuleMethod(runtimeVar, 30, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod3.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:677");
        gen$Mngeneric$Mnevent$Mnname = Macro.make(simpleSymbol387, moduleMethod3, runtimeVar);
        define$Mnevent$Mnhelper = Macro.make(simpleSymbol380, syntaxRules16, runtimeVar);
        $Stlist$Mnfor$Mnruntime$St = Macro.make(simpleSymbol385, syntaxRules15, runtimeVar);
        ModuleMethod moduleMethod4 = new ModuleMethod(runtimeVar, 31, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod4.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:733");
        define$Mnevent = Macro.make(simpleSymbol383, moduleMethod4, runtimeVar);
        ModuleMethod moduleMethod5 = new ModuleMethod(runtimeVar, 32, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod5.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:751");
        define$Mngeneric$Mnevent = Macro.make(simpleSymbol381, moduleMethod5, runtimeVar);
        def = Macro.make(simpleSymbol378, syntaxRules14, runtimeVar);
        do$Mnafter$Mnform$Mncreation = Macro.make(simpleSymbol377, syntaxRules13, runtimeVar);
        add$Mnto$Mncurrent$Mnform$Mnenvironment = new ModuleMethod(runtimeVar, 33, simpleSymbol376, 8194);
        lookup$Mnin$Mncurrent$Mnform$Mnenvironment = new ModuleMethod(runtimeVar, 34, simpleSymbol375, 8193);
        filter$Mntype$Mnin$Mncurrent$Mnform$Mnenvironment = new ModuleMethod(runtimeVar, 36, simpleSymbol374, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        delete$Mnfrom$Mncurrent$Mnform$Mnenvironment = new ModuleMethod(runtimeVar, 37, simpleSymbol373, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        rename$Mnin$Mncurrent$Mnform$Mnenvironment = new ModuleMethod(runtimeVar, 38, simpleSymbol372, 8194);
        add$Mnglobal$Mnvar$Mnto$Mncurrent$Mnform$Mnenvironment = new ModuleMethod(runtimeVar, 39, simpleSymbol371, 8194);
        lookup$Mnglobal$Mnvar$Mnin$Mncurrent$Mnform$Mnenvironment = new ModuleMethod(runtimeVar, 40, simpleSymbol370, 8193);
        reset$Mncurrent$Mnform$Mnenvironment = new ModuleMethod(runtimeVar, 42, simpleSymbol369, 0);
        foreach = Macro.makeNonHygienic(simpleSymbol368, new ModuleMethod(runtimeVar, 43, null, 12291), runtimeVar);
        $Styail$Mnbreak$St = new ModuleMethod(runtimeVar, 44, simpleSymbol365, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        forrange = Macro.makeNonHygienic(simpleSymbol367, new ModuleMethod(runtimeVar, 45, null, 20485), runtimeVar);
        f0while = Macro.makeNonHygienic(simpleSymbol366, new ModuleMethod(runtimeVar, 46, null, -4094), runtimeVar);
        foreach$Mnwith$Mnbreak = Macro.make(simpleSymbol363, syntaxRules12, runtimeVar);
        map_nondest = Macro.make(simpleSymbol362, syntaxRules11, runtimeVar);
        filter_nondest = Macro.make(simpleSymbol361, syntaxRules10, runtimeVar);
        reduceovereach = Macro.make(simpleSymbol360, syntaxRules9, runtimeVar);
        sortcomparator_nondest = Macro.make(simpleSymbol359, syntaxRules8, runtimeVar);
        mincomparator$Mnnondest = Macro.make(simpleSymbol358, syntaxRules7, runtimeVar);
        maxcomparator$Mnnondest = Macro.make(simpleSymbol357, syntaxRules6, runtimeVar);
        sortkey_nondest = Macro.make(simpleSymbol356, syntaxRules5, runtimeVar);
        forrange$Mnwith$Mnbreak = Macro.make(simpleSymbol355, syntaxRules4, runtimeVar);
        while$Mnwith$Mnbreak = Macro.make(simpleSymbol354, syntaxRules3, runtimeVar);
        call$Mncomponent$Mnmethod = new ModuleMethod(runtimeVar, 47, simpleSymbol353, 16388);
        call$Mncomponent$Mnmethod$Mnwith$Mncontinuation = new ModuleMethod(runtimeVar, 48, simpleSymbol352, 20485);
        call$Mncomponent$Mnmethod$Mnwith$Mnblocking$Mncontinuation = new ModuleMethod(runtimeVar, 49, simpleSymbol351, 16388);
        call$Mncomponent$Mntype$Mnmethod = new ModuleMethod(runtimeVar, 50, simpleSymbol350, 20485);
        call$Mncomponent$Mntype$Mnmethod$Mnwith$Mncontinuation = new ModuleMethod(runtimeVar, 51, simpleSymbol349, 20485);
        call$Mncomponent$Mntype$Mnmethod$Mnwith$Mnblocking$Mncontinuation = new ModuleMethod(runtimeVar, 52, simpleSymbol348, 16388);
        call$Mnyail$Mnprimitive = new ModuleMethod(runtimeVar, 53, simpleSymbol347, 16388);
        sanitize$Mncomponent$Mndata = new ModuleMethod(runtimeVar, 54, simpleSymbol346, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        sanitize$Mnreturn$Mnvalue = new ModuleMethod(runtimeVar, 55, simpleSymbol345, 12291);
        java$Mncollection$Mn$Gryail$Mnlist = new ModuleMethod(runtimeVar, 56, simpleSymbol344, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        java$Mncollection$Mn$Grkawa$Mnlist = new ModuleMethod(runtimeVar, 57, simpleSymbol343, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        java$Mnmap$Mn$Gryail$Mndictionary = new ModuleMethod(runtimeVar, 58, simpleSymbol342, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        sanitize$Mnatomic = new ModuleMethod(runtimeVar, 59, simpleSymbol341, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        signal$Mnruntime$Mnerror = new ModuleMethod(runtimeVar, 60, simpleSymbol340, 8194);
        signal$Mnruntime$Mnform$Mnerror = new ModuleMethod(runtimeVar, 61, simpleSymbol339, 12291);
        yail$Mnnot = new ModuleMethod(runtimeVar, 62, simpleSymbol338, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        call$Mnwith$Mncoerced$Mnargs = new ModuleMethod(runtimeVar, 63, simpleSymbol337, 16388);
        $Pcset$Mnand$Mncoerce$Mnproperty$Ex = new ModuleMethod(runtimeVar, 64, simpleSymbol336, 16388);
        $Pcset$Mnsubform$Mnlayout$Mnproperty$Ex = new ModuleMethod(runtimeVar, 65, simpleSymbol335, 12291);
        generate$Mnruntime$Mntype$Mnerror = new ModuleMethod(runtimeVar, 66, simpleSymbol334, 8194);
        show$Mnarglist$Mnno$Mnparens = new ModuleMethod(runtimeVar, 67, simpleSymbol333, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        coerce$Mnargs = new ModuleMethod(runtimeVar, 68, simpleSymbol332, 12291);
        coerce$Mnarg = new ModuleMethod(runtimeVar, 69, simpleSymbol331, 8194);
        coerce$Mnto$Mnnumber$Mnlist = new ModuleMethod(runtimeVar, 70, simpleSymbol330, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        enum$Mntype$Qu = new ModuleMethod(runtimeVar, 71, simpleSymbol329, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        enum$Qu = new ModuleMethod(runtimeVar, 72, simpleSymbol328, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        coerce$Mnto$Mnenum = new ModuleMethod(runtimeVar, 73, simpleSymbol327, 8194);
        coerce$Mnto$Mntext = new ModuleMethod(runtimeVar, 74, simpleSymbol326, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        coerce$Mnto$Mninstant = new ModuleMethod(runtimeVar, 75, simpleSymbol325, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        coerce$Mnto$Mncomponent = new ModuleMethod(runtimeVar, 76, simpleSymbol324, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        coerce$Mnto$Mncomponent$Mnof$Mntype = new ModuleMethod(runtimeVar, 77, simpleSymbol323, 8194);
        type$Mn$Grclass = new ModuleMethod(runtimeVar, 78, simpleSymbol322, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        coerce$Mnto$Mnnumber = new ModuleMethod(runtimeVar, 79, simpleSymbol321, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        coerce$Mnto$Mnkey = new ModuleMethod(runtimeVar, 80, simpleSymbol320, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        use$Mnjson$Mnformat = Macro.make(simpleSymbol319, syntaxRules2, runtimeVar);
        coerce$Mnto$Mnstring = new ModuleMethod(runtimeVar, 81, simpleSymbol318, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod6 = new ModuleMethod(runtimeVar, 82, simpleSymbol317, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod6.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:1625");
        get$Mndisplay$Mnrepresentation = moduleMethod6;
        ModuleMethod moduleMethod7 = new ModuleMethod(runtimeVar, 83, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod7.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:1635");
        lambda$Fn8 = moduleMethod7;
        ModuleMethod moduleMethod8 = new ModuleMethod(runtimeVar, 84, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod8.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:1658");
        lambda$Fn11 = moduleMethod8;
        join$Mnstrings = new ModuleMethod(runtimeVar, 85, simpleSymbol316, 8194);
        string$Mnreplace = new ModuleMethod(runtimeVar, 86, simpleSymbol315, 8194);
        coerce$Mnto$Mnyail$Mnlist = new ModuleMethod(runtimeVar, 87, simpleSymbol314, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        coerce$Mnto$Mnpair = new ModuleMethod(runtimeVar, 88, simpleSymbol313, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        coerce$Mnto$Mndictionary = new ModuleMethod(runtimeVar, 89, simpleSymbol312, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        coerce$Mnto$Mnboolean = new ModuleMethod(runtimeVar, 90, simpleSymbol311, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        is$Mncoercible$Qu = new ModuleMethod(runtimeVar, 91, simpleSymbol310, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        all$Mncoercible$Qu = new ModuleMethod(runtimeVar, 92, simpleSymbol309, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        boolean$Mn$Grstring = new ModuleMethod(runtimeVar, 93, simpleSymbol308, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        padded$Mnstring$Mn$Grnumber = new ModuleMethod(runtimeVar, 94, simpleSymbol307, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        $Stformat$Mninexact$St = new ModuleMethod(runtimeVar, 95, simpleSymbol306, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        appinventor$Mnnumber$Mn$Grstring = new ModuleMethod(runtimeVar, 96, simpleSymbol305, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnequal$Qu = new ModuleMethod(runtimeVar, 97, simpleSymbol304, 8194);
        yail$Mnatomic$Mnequal$Qu = new ModuleMethod(runtimeVar, 98, simpleSymbol303, 8194);
        as$Mnnumber = new ModuleMethod(runtimeVar, 99, simpleSymbol302, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnnot$Mnequal$Qu = new ModuleMethod(runtimeVar, 100, simpleSymbol301, 8194);
        process$Mnand$Mndelayed = new ModuleMethod(runtimeVar, 101, simpleSymbol300, -4096);
        process$Mnor$Mndelayed = new ModuleMethod(runtimeVar, 102, simpleSymbol299, -4096);
        yail$Mnfloor = new ModuleMethod(runtimeVar, 103, simpleSymbol298, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnceiling = new ModuleMethod(runtimeVar, 104, simpleSymbol297, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnround = new ModuleMethod(runtimeVar, 105, simpleSymbol296, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        random$Mnset$Mnseed = new ModuleMethod(runtimeVar, 106, simpleSymbol295, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        random$Mnfraction = new ModuleMethod(runtimeVar, 107, simpleSymbol294, 0);
        random$Mninteger = new ModuleMethod(runtimeVar, 108, simpleSymbol293, 8194);
        ModuleMethod moduleMethod9 = new ModuleMethod(runtimeVar, 109, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod9.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:1963");
        lambda$Fn15 = moduleMethod9;
        yail$Mndivide = new ModuleMethod(runtimeVar, 110, simpleSymbol292, 8194);
        degrees$Mn$Grradians$Mninternal = new ModuleMethod(runtimeVar, 111, simpleSymbol291, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        radians$Mn$Grdegrees$Mninternal = new ModuleMethod(runtimeVar, 112, simpleSymbol290, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        degrees$Mn$Grradians = new ModuleMethod(runtimeVar, 113, simpleSymbol289, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        radians$Mn$Grdegrees = new ModuleMethod(runtimeVar, 114, simpleSymbol288, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        sin$Mndegrees = new ModuleMethod(runtimeVar, 115, simpleSymbol287, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cos$Mndegrees = new ModuleMethod(runtimeVar, 116, simpleSymbol286, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        tan$Mndegrees = new ModuleMethod(runtimeVar, 117, simpleSymbol285, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        asin$Mndegrees = new ModuleMethod(runtimeVar, 118, simpleSymbol284, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        acos$Mndegrees = new ModuleMethod(runtimeVar, 119, simpleSymbol283, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        atan$Mndegrees = new ModuleMethod(runtimeVar, 120, simpleSymbol282, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        atan2$Mndegrees = new ModuleMethod(runtimeVar, 121, simpleSymbol281, 8194);
        string$Mnto$Mnupper$Mncase = new ModuleMethod(runtimeVar, 122, simpleSymbol280, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnto$Mnlower$Mncase = new ModuleMethod(runtimeVar, 123, simpleSymbol279, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        unicode$Mnstring$Mn$Grlist = new ModuleMethod(runtimeVar, 124, simpleSymbol278, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnreverse = new ModuleMethod(runtimeVar, 125, simpleSymbol277, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        format$Mnas$Mndecimal = new ModuleMethod(runtimeVar, 126, simpleSymbol276, 8194);
        is$Mnnumber$Qu = new ModuleMethod(runtimeVar, 127, simpleSymbol275, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        is$Mnbase10$Qu = new ModuleMethod(runtimeVar, 128, simpleSymbol274, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        is$Mnhexadecimal$Qu = new ModuleMethod(runtimeVar, 129, simpleSymbol273, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        is$Mnbinary$Qu = new ModuleMethod(runtimeVar, 130, simpleSymbol272, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        math$Mnconvert$Mndec$Mnhex = new ModuleMethod(runtimeVar, 131, simpleSymbol271, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        math$Mnconvert$Mnhex$Mndec = new ModuleMethod(runtimeVar, 132, simpleSymbol270, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        math$Mnconvert$Mnbin$Mndec = new ModuleMethod(runtimeVar, 133, simpleSymbol269, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        math$Mnconvert$Mndec$Mnbin = new ModuleMethod(runtimeVar, 134, simpleSymbol268, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        patched$Mnnumber$Mn$Grstring$Mnbinary = new ModuleMethod(runtimeVar, 135, simpleSymbol267, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        alternate$Mnnumber$Mn$Grstring$Mnbinary = new ModuleMethod(runtimeVar, 136, simpleSymbol266, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        internal$Mnbinary$Mnconvert = new ModuleMethod(runtimeVar, 137, simpleSymbol265, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        avg = new ModuleMethod(runtimeVar, 138, simpleSymbol264, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnmul = new ModuleMethod(runtimeVar, 139, simpleSymbol263, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        gm = new ModuleMethod(runtimeVar, 140, simpleSymbol262, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        mode = new ModuleMethod(runtimeVar, 141, simpleSymbol261, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        maxl = new ModuleMethod(runtimeVar, 142, simpleSymbol260, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        minl = new ModuleMethod(runtimeVar, 143, simpleSymbol259, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        mean = new ModuleMethod(runtimeVar, 144, simpleSymbol258, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        sum$Mnmean$Mnsquare$Mndiff = new ModuleMethod(runtimeVar, 145, simpleSymbol257, 8194);
        std$Mndev = new ModuleMethod(runtimeVar, 146, simpleSymbol256, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        sample$Mnstd$Mndev = new ModuleMethod(runtimeVar, 147, simpleSymbol255, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        std$Mnerr = new ModuleMethod(runtimeVar, 148, simpleSymbol254, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnlist$Qu = new ModuleMethod(runtimeVar, 149, simpleSymbol253, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnlist$Mncandidate$Qu = new ModuleMethod(runtimeVar, 150, simpleSymbol252, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnlist$Mncontents = new ModuleMethod(runtimeVar, 151, simpleSymbol251, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        set$Mnyail$Mnlist$Mncontents$Ex = new ModuleMethod(runtimeVar, 152, simpleSymbol250, 8194);
        insert$Mnyail$Mnlist$Mnheader = new ModuleMethod(runtimeVar, 153, simpleSymbol249, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        kawa$Mnlist$Mn$Gryail$Mnlist = new ModuleMethod(runtimeVar, 154, simpleSymbol248, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnlist$Mn$Grkawa$Mnlist = new ModuleMethod(runtimeVar, 155, simpleSymbol247, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnlist$Mnempty$Qu = new ModuleMethod(runtimeVar, 156, simpleSymbol246, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mnyail$Mnlist = new ModuleMethod(runtimeVar, 157, simpleSymbol245, -4096);
        yail$Mnlist$Mncopy = new ModuleMethod(runtimeVar, 158, simpleSymbol244, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnlist$Mnreverse = new ModuleMethod(runtimeVar, 159, simpleSymbol243, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnlist$Mnto$Mncsv$Mntable = new ModuleMethod(runtimeVar, ComponentConstants.TEXTBOX_PREFERRED_WIDTH, simpleSymbol242, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnlist$Mnto$Mncsv$Mnrow = new ModuleMethod(runtimeVar, 161, simpleSymbol241, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        convert$Mnto$Mnstrings$Mnfor$Mncsv = new ModuleMethod(runtimeVar, 162, simpleSymbol240, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnlist$Mnfrom$Mncsv$Mntable = new ModuleMethod(runtimeVar, 163, simpleSymbol239, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnlist$Mnfrom$Mncsv$Mnrow = new ModuleMethod(runtimeVar, 164, simpleSymbol238, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnlist$Mnlength = new ModuleMethod(runtimeVar, 165, simpleSymbol237, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnlist$Mnindex = new ModuleMethod(runtimeVar, 166, simpleSymbol236, 8194);
        yail$Mnlist$Mnget$Mnitem = new ModuleMethod(runtimeVar, 167, simpleSymbol235, 8194);
        yail$Mnlist$Mnset$Mnitem$Ex = new ModuleMethod(runtimeVar, 168, simpleSymbol234, 12291);
        yail$Mnlist$Mnremove$Mnitem$Ex = new ModuleMethod(runtimeVar, 169, simpleSymbol233, 8194);
        yail$Mnlist$Mninsert$Mnitem$Ex = new ModuleMethod(runtimeVar, 170, simpleSymbol232, 12291);
        yail$Mnlist$Mnappend$Ex = new ModuleMethod(runtimeVar, 171, simpleSymbol231, 8194);
        yail$Mnlist$Mnadd$Mnto$Mnlist$Ex = new ModuleMethod(runtimeVar, 172, simpleSymbol230, -4095);
        yail$Mnlist$Mnmember$Qu = new ModuleMethod(runtimeVar, 173, simpleSymbol229, 8194);
        yail$Mnlist$Mnpick$Mnrandom = new ModuleMethod(runtimeVar, 174, simpleSymbol228, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnfor$Mneach = new ModuleMethod(runtimeVar, 175, simpleSymbol227, 8194);
        yail$Mnlist$Mnmap = new ModuleMethod(runtimeVar, 176, simpleSymbol226, 8194);
        yail$Mnlist$Mnfilter = new ModuleMethod(runtimeVar, 177, simpleSymbol225, 8194);
        yail$Mnlist$Mnreduce = new ModuleMethod(runtimeVar, 178, simpleSymbol224, 12291);
        typeof = new ModuleMethod(runtimeVar, 179, simpleSymbol223, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        indexof = new ModuleMethod(runtimeVar, 180, simpleSymbol222, 8194);
        type$Mnlt$Qu = new ModuleMethod(runtimeVar, 181, simpleSymbol221, 8194);
        is$Mnlt$Qu = new ModuleMethod(runtimeVar, 182, simpleSymbol220, 8194);
        is$Mneq$Qu = new ModuleMethod(runtimeVar, 183, simpleSymbol219, 8194);
        is$Mnleq$Qu = new ModuleMethod(runtimeVar, 184, simpleSymbol218, 8194);
        boolean$Mnlt$Qu = new ModuleMethod(runtimeVar, 185, simpleSymbol217, 8194);
        boolean$Mneq$Qu = new ModuleMethod(runtimeVar, 186, simpleSymbol216, 8194);
        boolean$Mnleq$Qu = new ModuleMethod(runtimeVar, 187, simpleSymbol215, 8194);
        list$Mnlt$Qu = new ModuleMethod(runtimeVar, 188, simpleSymbol214, 8194);
        list$Mneq$Qu = new ModuleMethod(runtimeVar, FullScreenVideoUtil.FULLSCREEN_VIDEO_DIALOG_FLAG, simpleSymbol213, 8194);
        yail$Mnlist$Mnnecessary = new ModuleMethod(runtimeVar, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK, simpleSymbol212, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mnleq$Qu = new ModuleMethod(runtimeVar, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PLAY, simpleSymbol211, 8194);
        component$Mnlt$Qu = new ModuleMethod(runtimeVar, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE, simpleSymbol210, 8194);
        component$Mneq$Qu = new ModuleMethod(runtimeVar, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_STOP, simpleSymbol209, 8194);
        component$Mnleq$Qu = new ModuleMethod(runtimeVar, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SOURCE, simpleSymbol208, 8194);
        take = new ModuleMethod(runtimeVar, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN, simpleSymbol207, 8194);
        drop = new ModuleMethod(runtimeVar, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION, simpleSymbol206, 8194);
        merge = new ModuleMethod(runtimeVar, 197, simpleSymbol205, 12291);
        mergesort = new ModuleMethod(runtimeVar, 198, simpleSymbol204, 8194);
        yail$Mnlist$Mnsort = new ModuleMethod(runtimeVar, 199, simpleSymbol203, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnlist$Mnsort$Mncomparator = new ModuleMethod(runtimeVar, HttpRequestContext.HTTP_OK, simpleSymbol202, 8194);
        merge$Mnkey = new ModuleMethod(runtimeVar, ErrorMessages.ERROR_CAMERA_NO_IMAGE_RETURNED, simpleSymbol201, 16388);
        mergesort$Mnkey = new ModuleMethod(runtimeVar, ErrorMessages.ERROR_NO_CAMERA_PERMISSION, simpleSymbol200, 12291);
        yail$Mnlist$Mnsort$Mnkey = new ModuleMethod(runtimeVar, 203, simpleSymbol199, 8194);
        list$Mnnumber$Mnonly = new ModuleMethod(runtimeVar, 204, simpleSymbol198, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mnmin = new ModuleMethod(runtimeVar, 205, simpleSymbol197, 12291);
        yail$Mnlist$Mnmin$Mncomparator = new ModuleMethod(runtimeVar, 206, simpleSymbol196, 8194);
        list$Mnmax = new ModuleMethod(runtimeVar, 207, simpleSymbol195, 12291);
        yail$Mnlist$Mnmax$Mncomparator = new ModuleMethod(runtimeVar, 208, simpleSymbol194, 8194);
        yail$Mnlist$Mnbut$Mnfirst = new ModuleMethod(runtimeVar, 209, simpleSymbol193, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        but$Mnlast = new ModuleMethod(runtimeVar, 210, simpleSymbol192, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnlist$Mnbut$Mnlast = new ModuleMethod(runtimeVar, 211, simpleSymbol191, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        front = new ModuleMethod(runtimeVar, 212, simpleSymbol190, 8194);
        back = new ModuleMethod(runtimeVar, 213, simpleSymbol189, 12291);
        yail$Mnlist$Mnslice = new ModuleMethod(runtimeVar, 214, simpleSymbol188, 12291);
        yail$Mnfor$Mnrange = new ModuleMethod(runtimeVar, 215, simpleSymbol187, 16388);
        yail$Mnfor$Mnrange$Mnwith$Mnnumeric$Mnchecked$Mnargs = new ModuleMethod(runtimeVar, 216, simpleSymbol186, 16388);
        yail$Mnnumber$Mnrange = new ModuleMethod(runtimeVar, 217, simpleSymbol185, 8194);
        yail$Mnalist$Mnlookup = new ModuleMethod(runtimeVar, 218, simpleSymbol184, 12291);
        pair$Mnok$Qu = new ModuleMethod(runtimeVar, 219, simpleSymbol183, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mnlist$Mnjoin$Mnwith$Mnseparator = new ModuleMethod(runtimeVar, 220, simpleSymbol182, 8194);
        make$Mnyail$Mndictionary = new ModuleMethod(runtimeVar, 221, simpleSymbol181, -4096);
        make$Mndictionary$Mnpair = new ModuleMethod(runtimeVar, 222, simpleSymbol180, 8194);
        yail$Mndictionary$Mnset$Mnpair = new ModuleMethod(runtimeVar, 223, simpleSymbol179, 12291);
        yail$Mndictionary$Mndelete$Mnpair = new ModuleMethod(runtimeVar, 224, simpleSymbol178, 8194);
        yail$Mndictionary$Mnlookup = new ModuleMethod(runtimeVar, 225, simpleSymbol177, 12291);
        yail$Mndictionary$Mnrecursive$Mnlookup = new ModuleMethod(runtimeVar, 226, simpleSymbol176, 12291);
        yail$Mndictionary$Mnwalk = new ModuleMethod(runtimeVar, 227, simpleSymbol175, 8194);
        yail$Mndictionary$Mnrecursive$Mnset = new ModuleMethod(runtimeVar, 228, simpleSymbol174, 12291);
        yail$Mndictionary$Mnget$Mnkeys = new ModuleMethod(runtimeVar, YaVersion.YOUNG_ANDROID_VERSION, simpleSymbol173, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mndictionary$Mnget$Mnvalues = new ModuleMethod(runtimeVar, 230, simpleSymbol172, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mndictionary$Mnis$Mnkey$Mnin = new ModuleMethod(runtimeVar, 231, simpleSymbol171, 8194);
        yail$Mndictionary$Mnlength = new ModuleMethod(runtimeVar, 232, simpleSymbol170, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mndictionary$Mnalist$Mnto$Mndict = new ModuleMethod(runtimeVar, 233, simpleSymbol169, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mndictionary$Mndict$Mnto$Mnalist = new ModuleMethod(runtimeVar, 234, simpleSymbol168, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mndictionary$Mncopy = new ModuleMethod(runtimeVar, 235, simpleSymbol167, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        yail$Mndictionary$Mncombine$Mndicts = new ModuleMethod(runtimeVar, 236, simpleSymbol166, 8194);
        yail$Mndictionary$Qu = new ModuleMethod(runtimeVar, 237, simpleSymbol165, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mndisjunct = new ModuleMethod(runtimeVar, 238, simpleSymbol164, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        array$Mn$Grlist = new ModuleMethod(runtimeVar, 239, simpleSymbol163, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnstarts$Mnat = new ModuleMethod(runtimeVar, 240, simpleSymbol162, 8194);
        string$Mncontains = new ModuleMethod(runtimeVar, LispEscapeFormat.ESCAPE_NORMAL, simpleSymbol161, 8194);
        string$Mncontains$Mnany = new ModuleMethod(runtimeVar, LispEscapeFormat.ESCAPE_ALL, simpleSymbol160, 8194);
        string$Mncontains$Mnall = new ModuleMethod(runtimeVar, 243, simpleSymbol159, 8194);
        string$Mnsplit$Mnat$Mnfirst = new ModuleMethod(runtimeVar, 244, simpleSymbol158, 8194);
        string$Mnsplit$Mnat$Mnfirst$Mnof$Mnany = new ModuleMethod(runtimeVar, 245, simpleSymbol157, 8194);
        string$Mnsplit = new ModuleMethod(runtimeVar, 246, simpleSymbol156, 8194);
        string$Mnsplit$Mnat$Mnany = new ModuleMethod(runtimeVar, 247, simpleSymbol155, 8194);
        string$Mnsplit$Mnat$Mnspaces = new ModuleMethod(runtimeVar, 248, simpleSymbol154, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnsubstring = new ModuleMethod(runtimeVar, 249, simpleSymbol153, 12291);
        string$Mntrim = new ModuleMethod(runtimeVar, 250, simpleSymbol152, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnreplace$Mnall = new ModuleMethod(runtimeVar, Telnet.WILL, simpleSymbol151, 12291);
        string$Mnempty$Qu = new ModuleMethod(runtimeVar, Telnet.WONT, simpleSymbol150, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        text$Mndeobfuscate = new ModuleMethod(runtimeVar, Telnet.DO, simpleSymbol149, 8194);
        string$Mnreplace$Mnmappings$Mndictionary = new ModuleMethod(runtimeVar, Telnet.DONT, simpleSymbol148, 8194);
        string$Mnreplace$Mnmappings$Mnlongest$Mnstring = new ModuleMethod(runtimeVar, 255, simpleSymbol147, 8194);
        string$Mnreplace$Mnmappings$Mnearliest$Mnoccurrence = new ModuleMethod(runtimeVar, 256, simpleSymbol146, 8194);
        make$Mnexact$Mnyail$Mninteger = new ModuleMethod(runtimeVar, InputDeviceCompat.SOURCE_KEYBOARD, simpleSymbol145, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mncolor = new ModuleMethod(runtimeVar, 258, simpleSymbol144, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        split$Mncolor = new ModuleMethod(runtimeVar, 259, simpleSymbol143, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        close$Mnscreen = new ModuleMethod(runtimeVar, 260, simpleSymbol142, 0);
        close$Mnapplication = new ModuleMethod(runtimeVar, 261, simpleSymbol141, 0);
        open$Mnanother$Mnscreen = new ModuleMethod(runtimeVar, 262, simpleSymbol140, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        open$Mnanother$Mnscreen$Mnwith$Mnstart$Mnvalue = new ModuleMethod(runtimeVar, 263, simpleSymbol139, 8194);
        get$Mnstart$Mnvalue = new ModuleMethod(runtimeVar, 264, simpleSymbol138, 0);
        close$Mnscreen$Mnwith$Mnvalue = new ModuleMethod(runtimeVar, 265, simpleSymbol137, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        get$Mnplain$Mnstart$Mntext = new ModuleMethod(runtimeVar, 266, simpleSymbol136, 0);
        close$Mnscreen$Mnwith$Mnplain$Mntext = new ModuleMethod(runtimeVar, 267, simpleSymbol135, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        get$Mnserver$Mnaddress$Mnfrom$Mnwifi = new ModuleMethod(runtimeVar, 268, simpleSymbol134, 0);
        process$Mnrepl$Mninput = Macro.make(simpleSymbol133, syntaxRules, runtimeVar);
        in$Mnui = new ModuleMethod(runtimeVar, 269, simpleSymbol132, 8194);
        send$Mnto$Mnblock = new ModuleMethod(runtimeVar, 270, simpleSymbol131, 8194);
        clear$Mncurrent$Mnform = new ModuleMethod(runtimeVar, 271, simpleSymbol130, 0);
        set$Mnform$Mnname = new ModuleMethod(runtimeVar, 272, simpleSymbol129, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        remove$Mncomponent = new ModuleMethod(runtimeVar, 273, simpleSymbol128, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        rename$Mncomponent = new ModuleMethod(runtimeVar, 274, simpleSymbol127, 8194);
        init$Mnruntime = new ModuleMethod(runtimeVar, 275, simpleSymbol126, 0);
        set$Mnthis$Mnform = new ModuleMethod(runtimeVar, 276, simpleSymbol125, 0);
        clarify = new ModuleMethod(runtimeVar, 277, simpleSymbol124, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        clarify1 = new ModuleMethod(runtimeVar, 278, simpleSymbol123, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    }

    static Object lambda21(Object stx) {
        Object[] allocVars = SyntaxPattern.allocVars(2, null);
        if (Lit61.match(stx, allocVars, 0)) {
            Object[] objArr = new Object[3];
            objArr[0] = "";
            objArr[1] = "";
            Object execute = Lit62.execute(allocVars, TemplateScope.make());
            try {
                objArr[2] = ((Symbol) execute).toString();
                return SyntaxForms.makeWithTemplate(stx, strings.stringAppend(objArr));
            } catch (ClassCastException e) {
                throw new WrongType(e, "symbol->string", 1, execute);
            }
        }
        return syntax_case.error("syntax-case", stx);
    }

    public static Object addComponentWithinRepl(Object container$Mnname, Object component$Mntype, Object componentName, Object initPropsThunk) {
        frame frameVar = new frame();
        frameVar.component$Mnname = componentName;
        frameVar.init$Mnprops$Mnthunk = initPropsThunk;
        try {
            Object lookupInCurrentFormEnvironment = lookupInCurrentFormEnvironment((Symbol) container$Mnname);
            try {
                ComponentContainer container = (ComponentContainer) lookupInCurrentFormEnvironment;
                Object obj = frameVar.component$Mnname;
                try {
                    frameVar.existing$Mncomponent = lookupInCurrentFormEnvironment((Symbol) obj);
                    frameVar.component$Mnto$Mnadd = Invoke.make.apply2(component$Mntype, container);
                    Object obj2 = frameVar.component$Mnname;
                    try {
                        addToCurrentFormEnvironment((Symbol) obj2, frameVar.component$Mnto$Mnadd);
                        return addInitThunk(frameVar.component$Mnname, frameVar.lambda$Fn1);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "add-to-current-form-environment", 0, obj2);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "lookup-in-current-form-environment", 0, obj);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "container", -2, lookupInCurrentFormEnvironment);
            }
        } catch (ClassCastException e4) {
            throw new WrongType(e4, "lookup-in-current-form-environment", 0, container$Mnname);
        }
    }

    @Override // gnu.expr.ModuleBody
    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 17:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case 23:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case 47:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case 49:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case 52:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case 53:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case 63:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case 64:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case ErrorMessages.ERROR_CAMERA_NO_IMAGE_RETURNED /* 201 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case 215:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            case 216:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            default:
                return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
        }
    }

    /* compiled from: runtime4411419262522357548.scm */
    /* loaded from: classes.dex */
    public class frame extends ModuleBody {
        Object component$Mnname;
        Object component$Mnto$Mnadd;
        Object existing$Mncomponent;
        Object init$Mnprops$Mnthunk;
        final ModuleMethod lambda$Fn1;

        public frame() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 0);
            moduleMethod.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:99");
            this.lambda$Fn1 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 1 ? lambda1() : super.apply0(moduleMethod);
        }

        Object lambda1() {
            if (this.init$Mnprops$Mnthunk != Boolean.FALSE) {
                Scheme.applyToArgs.apply1(this.init$Mnprops$Mnthunk);
            }
            if (this.existing$Mncomponent == Boolean.FALSE) {
                return Values.empty;
            }
            runtime.androidLog(Format.formatToString(0, "Copying component properties for ~A", this.component$Mnname));
            Object obj = this.existing$Mncomponent;
            try {
                Component component = (Component) obj;
                Object obj2 = this.component$Mnto$Mnadd;
                try {
                    return PropertyUtil.copyComponentProperties(component, (Component) obj2);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "com.google.appinventor.components.runtime.util.PropertyUtil.copyComponentProperties(com.google.appinventor.components.runtime.Component,com.google.appinventor.components.runtime.Component)", 2, obj2);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "com.google.appinventor.components.runtime.util.PropertyUtil.copyComponentProperties(com.google.appinventor.components.runtime.Component,com.google.appinventor.components.runtime.Component)", 1, obj);
            }
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

    public static Object call$MnInitializeOfComponents$V(Object[] argsArray) {
        LList component$Mnnames = LList.makeList(argsArray, 0);
        Object arg0 = component$Mnnames;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                Object init$Mnthunk = getInitThunk(arg02.getCar());
                if (init$Mnthunk != Boolean.FALSE) {
                    Scheme.applyToArgs.apply1(init$Mnthunk);
                }
                arg0 = arg02.getCdr();
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        Object arg03 = component$Mnnames;
        while (arg03 != LList.Empty) {
            try {
                Pair arg04 = (Pair) arg03;
                Object component$Mnname = arg04.getCar();
                try {
                    ((Form) $Stthis$Mnform$St).callInitialize(lookupInCurrentFormEnvironment((Symbol) component$Mnname));
                    arg03 = arg04.getCdr();
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "lookup-in-current-form-environment", 0, component$Mnname);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "arg0", -2, arg03);
            }
        }
        return Values.empty;
    }

    @Override // gnu.expr.ModuleBody
    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 18:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 27:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 28:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 45:
            case 46:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 48:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 50:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 51:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 101:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 102:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 157:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 172:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 221:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod, objArr, callContext);
        }
    }

    public static Object addInitThunk(Object component$Mnname, Object thunk) {
        return Invoke.invokeStatic.applyN(new Object[]{KawaEnvironment, Lit0, $Stinit$Mnthunk$Mnenvironment$St, component$Mnname, thunk});
    }

    @Override // gnu.expr.ModuleBody
    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 19:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 24:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 33:
                if (!(obj instanceof Symbol)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 34:
                if (!(obj instanceof Symbol)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 38:
                if (!(obj instanceof Symbol)) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (!(obj2 instanceof Symbol)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 39:
                if (!(obj instanceof Symbol)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 40:
                if (!(obj instanceof Symbol)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 60:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 66:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 69:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 73:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 77:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 85:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 86:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 97:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 98:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 100:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 108:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 110:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 121:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 126:
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
            case 152:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 166:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 167:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 169:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 171:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 173:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 175:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 176:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 177:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 180:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 181:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 182:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 183:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 184:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 185:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 186:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 187:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 188:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_DIALOG_FLAG /* 189 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PLAY /* 191 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE /* 192 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_STOP /* 193 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SOURCE /* 194 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN /* 195 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION /* 196 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 198:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case HttpRequestContext.HTTP_OK /* 200 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 203:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 206:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 208:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 212:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 217:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 220:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 222:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 224:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 227:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 231:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 236:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 240:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case LispEscapeFormat.ESCAPE_NORMAL /* 241 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case LispEscapeFormat.ESCAPE_ALL /* 242 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 243:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 244:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 245:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 246:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 247:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case Telnet.DO /* 253 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case Telnet.DONT /* 254 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 255:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 256:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 263:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 269:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 270:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 274:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod, obj, obj2, callContext);
        }
    }

    public static Object getInitThunk(Object component$Mnname) {
        Object obj = $Stinit$Mnthunk$Mnenvironment$St;
        try {
            try {
                boolean x = ((Environment) obj).isBound((Symbol) component$Mnname);
                if (x) {
                    return Invoke.invokeStatic.apply4(KawaEnvironment, Lit1, $Stinit$Mnthunk$Mnenvironment$St, component$Mnname);
                }
                return x ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException e) {
                throw new WrongType(e, "gnu.mapping.Environment.isBound(gnu.mapping.Symbol)", 2, component$Mnname);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "gnu.mapping.Environment.isBound(gnu.mapping.Symbol)", 1, obj);
        }
    }

    public static void clearInitThunks() {
        $Stinit$Mnthunk$Mnenvironment$St = Environment.make("init-thunk-environment");
    }

    @Override // gnu.expr.ModuleBody
    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 21:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 42:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 107:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 260:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 261:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 264:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 266:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 268:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 271:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 275:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 276:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            default:
                return super.match0(moduleMethod, callContext);
        }
    }

    public static Object lookupComponent(Object comp$Mnname) {
        try {
            Object verified = lookupInCurrentFormEnvironment((Symbol) comp$Mnname, Boolean.FALSE);
            return verified != Boolean.FALSE ? verified : Lit2;
        } catch (ClassCastException e) {
            throw new WrongType(e, "lookup-in-current-form-environment", 0, comp$Mnname);
        }
    }

    public static Object setAndCoerceProperty$Ex(Object component, Object prop$Mnsym, Object property$Mnvalue, Object property$Mntype) {
        return $PcSetAndCoerceProperty$Ex(coerceToComponentAndVerify(component), prop$Mnsym, property$Mnvalue, property$Mntype);
    }

    public static Object getProperty$1(Object component, Object prop$Mnname) {
        Object component2 = coerceToComponentAndVerify(component);
        return sanitizeReturnValue(component2, prop$Mnname, Invoke.invoke.apply2(component2, prop$Mnname));
    }

    public static Object coerceToComponentAndVerify(Object possible$Mncomponent) {
        Object component = coerceToComponent(possible$Mncomponent);
        return !(component instanceof Component) ? signalRuntimeError(strings.stringAppend("Cannot find the component: ", getDisplayRepresentation(possible$Mncomponent)), "Problem with application") : component;
    }

    public static Object getPropertyAndCheck(Object possible$Mncomponent, Object component$Mntype, Object prop$Mnname) {
        Object component = coerceToComponentOfType(possible$Mncomponent, component$Mntype);
        return !(component instanceof Component) ? signalRuntimeError(Format.formatToString(0, "Property getter was expecting a ~A component but got a ~A instead.", component$Mntype, possible$Mncomponent.getClass().getSimpleName()), "Problem with application") : sanitizeReturnValue(component, prop$Mnname, Invoke.invoke.apply2(component, prop$Mnname));
    }

    @Override // gnu.expr.ModuleBody
    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 26:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 43:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 55:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 61:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 65:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 68:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 168:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 170:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 178:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 197:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case ErrorMessages.ERROR_NO_CAMERA_PERMISSION /* 202 */:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 205:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 207:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 213:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 214:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 218:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 223:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 225:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 226:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 228:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 249:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case Telnet.WILL /* 251 */:
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

    public static Object setAndCoercePropertyAndCheck$Ex(Object possible$Mncomponent, Object comp$Mntype, Object prop$Mnsym, Object property$Mnvalue, Object property$Mntype) {
        Object component = coerceToComponentOfType(possible$Mncomponent, comp$Mntype);
        return !(component instanceof Component) ? signalRuntimeError(Format.formatToString(0, "Property setter was expecting a ~A component but got a ~A instead.", comp$Mntype, possible$Mncomponent.getClass().getSimpleName()), "Problem with application") : $PcSetAndCoerceProperty$Ex(component, prop$Mnsym, property$Mnvalue, property$Mntype);
    }

    public static SimpleSymbol symbolAppend$V(Object[] argsArray) {
        Object symbols = LList.makeList(argsArray, 0);
        Apply apply = Scheme.apply;
        ModuleMethod moduleMethod = strings.string$Mnappend;
        Object result = LList.Empty;
        Object arg0 = symbols;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                Object cdr = arg02.getCdr();
                Object car = arg02.getCar();
                try {
                    result = Pair.make(((Symbol) car).toString(), result);
                    arg0 = cdr;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "symbol->string", 1, car);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "arg0", -2, arg0);
            }
        }
        Object apply2 = apply.apply2(moduleMethod, LList.reverseInPlace(result));
        try {
            return misc.string$To$Symbol((CharSequence) apply2);
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "string->symbol", 1, apply2);
        }
    }

    static Object lambda22(Object stx) {
        Object[] allocVars = SyntaxPattern.allocVars(3, null);
        if (!Lit100.match(stx, allocVars, 0)) {
            return syntax_case.error("syntax-case", stx);
        }
        return SyntaxForms.makeWithTemplate(stx, Lit101.execute(allocVars, TemplateScope.make()));
    }

    static Object lambda23(Object stx) {
        Object[] allocVars = SyntaxPattern.allocVars(3, null);
        if (!Lit103.match(stx, allocVars, 0)) {
            return syntax_case.error("syntax-case", stx);
        }
        return SyntaxForms.makeWithTemplate(stx, Lit104.execute(allocVars, TemplateScope.make()));
    }

    static Object lambda24(Object stx) {
        Object consX;
        Object[] allocVars = SyntaxPattern.allocVars(5, null);
        if (!Lit110.match(stx, allocVars, 0)) {
            return syntax_case.error("syntax-case", stx);
        }
        TemplateScope make = TemplateScope.make();
        consX = LList.consX(new Object[]{symbolAppend$V(new Object[]{Lit113.execute(allocVars, make), Lit114, Lit115.execute(allocVars, make)}), Lit116.execute(allocVars, make)});
        return Quote.append$V(new Object[]{Lit111.execute(allocVars, make), Pair.make(Quote.append$V(new Object[]{Lit112.execute(allocVars, make), consX}), Lit117.execute(allocVars, make))});
    }

    static Object lambda25(Object stx) {
        Object consX;
        Object[] allocVars = SyntaxPattern.allocVars(5, null);
        if (!Lit119.match(stx, allocVars, 0)) {
            return syntax_case.error("syntax-case", stx);
        }
        TemplateScope make = TemplateScope.make();
        consX = LList.consX(new Object[]{symbolAppend$V(new Object[]{Lit122, Lit123.execute(allocVars, make), Lit114, Lit124.execute(allocVars, make)}), Lit125.execute(allocVars, make)});
        return Quote.append$V(new Object[]{Lit120.execute(allocVars, make), Pair.make(Quote.append$V(new Object[]{Lit121.execute(allocVars, make), consX}), Lit126.execute(allocVars, make))});
    }

    @Override // gnu.expr.ModuleBody
    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 15:
                androidLog(obj);
                return Values.empty;
            case 16:
                return lambda21(obj);
            case 20:
                return getInitThunk(obj);
            case 22:
                return lookupComponent(obj);
            case 25:
                return coerceToComponentAndVerify(obj);
            case 29:
                return lambda22(obj);
            case 30:
                return lambda23(obj);
            case 31:
                return lambda24(obj);
            case 32:
                return lambda25(obj);
            case 34:
                try {
                    return lookupInCurrentFormEnvironment((Symbol) obj);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "lookup-in-current-form-environment", 1, obj);
                }
            case 36:
                try {
                    return filterTypeInCurrentFormEnvironment((Symbol) obj);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "filter-type-in-current-form-environment", 1, obj);
                }
            case 37:
                try {
                    return deleteFromCurrentFormEnvironment((Symbol) obj);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "delete-from-current-form-environment", 1, obj);
                }
            case 40:
                try {
                    return lookupGlobalVarInCurrentFormEnvironment((Symbol) obj);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "lookup-global-var-in-current-form-environment", 1, obj);
                }
            case 44:
                return $StYailBreak$St(obj);
            case 54:
                return sanitizeComponentData(obj);
            case 56:
                try {
                    return javaCollection$To$YailList((Collection) obj);
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "java-collection->yail-list", 1, obj);
                }
            case 57:
                try {
                    return javaCollection$To$KawaList((Collection) obj);
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "java-collection->kawa-list", 1, obj);
                }
            case 58:
                try {
                    return javaMap$To$YailDictionary((Map) obj);
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "java-map->yail-dictionary", 1, obj);
                }
            case 59:
                return sanitizeAtomic(obj);
            case 62:
                return yailNot(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 67:
                return showArglistNoParens(obj);
            case 70:
                return coerceToNumberList(obj);
            case 71:
                return isEnumType(obj);
            case 72:
                return isEnum(obj);
            case 74:
                return coerceToText(obj);
            case 75:
                return coerceToInstant(obj);
            case 76:
                return coerceToComponent(obj);
            case 78:
                return type$To$Class(obj);
            case 79:
                return coerceToNumber(obj);
            case 80:
                return coerceToKey(obj);
            case 81:
                return coerceToString(obj);
            case 82:
                return getDisplayRepresentation(obj);
            case 83:
                return lambda8(obj);
            case 84:
                return lambda11(obj);
            case 87:
                return coerceToYailList(obj);
            case 88:
                return coerceToPair(obj);
            case 89:
                return coerceToDictionary(obj);
            case 90:
                return coerceToBoolean(obj);
            case 91:
                return isIsCoercible(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 92:
                return isAllCoercible(obj);
            case 93:
                return boolean$To$String(obj);
            case 94:
                return paddedString$To$Number(obj);
            case 95:
                return $StFormatInexact$St(obj);
            case 96:
                return appinventorNumber$To$String(obj);
            case 99:
                return asNumber(obj);
            case 103:
                return yailFloor(obj);
            case 104:
                return yailCeiling(obj);
            case 105:
                return yailRound(obj);
            case 106:
                return randomSetSeed(obj);
            case 109:
                return lambda15(obj);
            case 111:
                return degrees$To$RadiansInternal(obj);
            case 112:
                return radians$To$DegreesInternal(obj);
            case 113:
                return degrees$To$Radians(obj);
            case 114:
                return radians$To$Degrees(obj);
            case 115:
                return sinDegrees(obj);
            case 116:
                return cosDegrees(obj);
            case 117:
                return tanDegrees(obj);
            case 118:
                return asinDegrees(obj);
            case 119:
                return acosDegrees(obj);
            case 120:
                return atanDegrees(obj);
            case 122:
                return stringToUpperCase(obj);
            case 123:
                return stringToLowerCase(obj);
            case 124:
                try {
                    return unicodeString$To$List((CharSequence) obj);
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "unicode-string->list", 1, obj);
                }
            case 125:
                return stringReverse(obj);
            case 127:
                return isIsNumber(obj);
            case 128:
                return isIsBase10(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 129:
                return isIsHexadecimal(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 130:
                return isIsBinary(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 131:
                return mathConvertDecHex(obj);
            case 132:
                return mathConvertHexDec(obj);
            case 133:
                return mathConvertBinDec(obj);
            case 134:
                return mathConvertDecBin(obj);
            case 135:
                return patchedNumber$To$StringBinary(obj);
            case 136:
                return alternateNumber$To$StringBinary(obj);
            case 137:
                return internalBinaryConvert(obj);
            case 138:
                return avg(obj);
            case 139:
                return yailMul(obj);
            case 140:
                return gm(obj);
            case 141:
                return mode(obj);
            case 142:
                return maxl(obj);
            case 143:
                return minl(obj);
            case 144:
                return mean(obj);
            case 146:
                return stdDev(obj);
            case 147:
                return sampleStdDev(obj);
            case 148:
                return stdErr(obj);
            case 149:
                return isYailList(obj);
            case 150:
                return isYailListCandidate(obj);
            case 151:
                return yailListContents(obj);
            case 153:
                return insertYailListHeader(obj);
            case 154:
                return kawaList$To$YailList(obj);
            case 155:
                return yailList$To$KawaList(obj);
            case 156:
                return isYailListEmpty(obj);
            case 158:
                return yailListCopy(obj);
            case 159:
                return yailListReverse(obj);
            case ComponentConstants.TEXTBOX_PREFERRED_WIDTH /* 160 */:
                return yailListToCsvTable(obj);
            case 161:
                return yailListToCsvRow(obj);
            case 162:
                return convertToStringsForCsv(obj);
            case 163:
                return yailListFromCsvTable(obj);
            case 164:
                return yailListFromCsvRow(obj);
            case 165:
                return Integer.valueOf(yailListLength(obj));
            case 174:
                return yailListPickRandom(obj);
            case 179:
                return typeof(obj);
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK /* 190 */:
                return yailListNecessary(obj);
            case 199:
                return yailListSort(obj);
            case 204:
                return listNumberOnly(obj);
            case 209:
                return yailListButFirst(obj);
            case 210:
                return butLast(obj);
            case 211:
                return yailListButLast(obj);
            case 219:
                return isPairOk(obj);
            case YaVersion.YOUNG_ANDROID_VERSION /* 229 */:
                return yailDictionaryGetKeys(obj);
            case 230:
                return yailDictionaryGetValues(obj);
            case 232:
                return Integer.valueOf(yailDictionaryLength(obj));
            case 233:
                return yailDictionaryAlistToDict(obj);
            case 234:
                return yailDictionaryDictToAlist(obj);
            case 235:
                return yailDictionaryCopy(obj);
            case 237:
                return isYailDictionary(obj);
            case 238:
                return makeDisjunct(obj);
            case 239:
                return array$To$List(obj);
            case 248:
                return stringSplitAtSpaces(obj);
            case 250:
                return stringTrim(obj);
            case Telnet.WONT /* 252 */:
                return isStringEmpty(obj);
            case InputDeviceCompat.SOURCE_KEYBOARD /* 257 */:
                return makeExactYailInteger(obj);
            case 258:
                return makeColor(obj);
            case 259:
                return splitColor(obj);
            case 262:
                openAnotherScreen(obj);
                return Values.empty;
            case 265:
                closeScreenWithValue(obj);
                return Values.empty;
            case 267:
                closeScreenWithPlainText(obj);
                return Values.empty;
            case 272:
                return setFormName(obj);
            case 273:
                return removeComponent(obj);
            case 277:
                return clarify(obj);
            case 278:
                return clarify1(obj);
            default:
                return super.apply1(moduleMethod, obj);
        }
    }

    public static Object addToCurrentFormEnvironment(Symbol name, Object object) {
        return $Stthis$Mnform$St != null ? Invoke.invokeStatic.applyN(new Object[]{KawaEnvironment, Lit0, SlotGet.getSlotValue(false, $Stthis$Mnform$St, "form-environment", "form$Mnenvironment", "getFormEnvironment", "isFormEnvironment", Scheme.instance), name, object}) : Invoke.invokeStatic.applyN(new Object[]{KawaEnvironment, Lit0, $Sttest$Mnenvironment$St, name, object});
    }

    public static Object lookupInCurrentFormEnvironment(Symbol name, Object default$Mnvalue) {
        Object obj = $Stthis$Mnform$St;
        Object env = obj != null ? SlotGet.getSlotValue(false, obj, "form-environment", "form$Mnenvironment", "getFormEnvironment", "isFormEnvironment", Scheme.instance) : $Sttest$Mnenvironment$St;
        try {
            if (!((Environment) env).isBound(name)) {
                return default$Mnvalue;
            }
            return Invoke.invokeStatic.apply4(KawaEnvironment, Lit1, env, name);
        } catch (ClassCastException e) {
            throw new WrongType(e, "gnu.mapping.Environment.isBound(gnu.mapping.Symbol)", 1, env);
        }
    }

    public static Object filterTypeInCurrentFormEnvironment(Symbol type) {
        Object obj = $Stthis$Mnform$St;
        Object env = obj != null ? SlotGet.getSlotValue(false, obj, "form-environment", "form$Mnenvironment", "getFormEnvironment", "isFormEnvironment", Scheme.instance) : $Sttest$Mnenvironment$St;
        try {
            return sanitizeComponentData(ComponentUtil.filterComponentsOfType((Environment) env, type == null ? null : type.toString()));
        } catch (ClassCastException e) {
            throw new WrongType(e, "com.google.appinventor.components.runtime.util.ComponentUtil.filterComponentsOfType(gnu.mapping.Environment,java.lang.String)", 1, env);
        }
    }

    public static Object deleteFromCurrentFormEnvironment(Symbol name) {
        if ($Stthis$Mnform$St != null) {
            return Invoke.invokeStatic.apply4(KawaEnvironment, Lit3, SlotGet.getSlotValue(false, $Stthis$Mnform$St, "form-environment", "form$Mnenvironment", "getFormEnvironment", "isFormEnvironment", Scheme.instance), name);
        }
        return Invoke.invokeStatic.apply4(KawaEnvironment, Lit3, $Sttest$Mnenvironment$St, name);
    }

    public static Object renameInCurrentFormEnvironment(Symbol old$Mnname, Symbol new$Mnname) {
        if (Scheme.isEqv.apply2(old$Mnname, new$Mnname) != Boolean.FALSE) {
            return Values.empty;
        }
        Object old$Mnvalue = lookupInCurrentFormEnvironment(old$Mnname);
        if ($Stthis$Mnform$St != null) {
            Invoke.invokeStatic.applyN(new Object[]{KawaEnvironment, Lit0, SlotGet.getSlotValue(false, $Stthis$Mnform$St, "form-environment", "form$Mnenvironment", "getFormEnvironment", "isFormEnvironment", Scheme.instance), new$Mnname, old$Mnvalue});
        } else {
            Invoke.invokeStatic.applyN(new Object[]{KawaEnvironment, Lit0, $Sttest$Mnenvironment$St, new$Mnname, old$Mnvalue});
        }
        return deleteFromCurrentFormEnvironment(old$Mnname);
    }

    public static Object addGlobalVarToCurrentFormEnvironment(Symbol name, Object object) {
        if ($Stthis$Mnform$St != null) {
            Invoke.invokeStatic.applyN(new Object[]{KawaEnvironment, Lit0, SlotGet.getSlotValue(false, $Stthis$Mnform$St, "global-var-environment", "global$Mnvar$Mnenvironment", "getGlobalVarEnvironment", "isGlobalVarEnvironment", Scheme.instance), name, object});
            return null;
        }
        Invoke.invokeStatic.applyN(new Object[]{KawaEnvironment, Lit0, $Sttest$Mnglobal$Mnvar$Mnenvironment$St, name, object});
        return null;
    }

    public static Object lookupGlobalVarInCurrentFormEnvironment(Symbol name, Object default$Mnvalue) {
        Object obj = $Stthis$Mnform$St;
        Object env = obj != null ? SlotGet.getSlotValue(false, obj, "global-var-environment", "global$Mnvar$Mnenvironment", "getGlobalVarEnvironment", "isGlobalVarEnvironment", Scheme.instance) : $Sttest$Mnglobal$Mnvar$Mnenvironment$St;
        try {
            if (!((Environment) env).isBound(name)) {
                return default$Mnvalue;
            }
            return Invoke.invokeStatic.apply4(KawaEnvironment, Lit1, env, name);
        } catch (ClassCastException e) {
            throw new WrongType(e, "gnu.mapping.Environment.isBound(gnu.mapping.Symbol)", 1, env);
        }
    }

    public static void resetCurrentFormEnvironment() {
        Object obj = $Stthis$Mnform$St;
        if (obj != null) {
            Object form$Mnname = SlotGet.getSlotValue(false, obj, "form-name-symbol", "form$Mnname$Mnsymbol", "getFormNameSymbol", "isFormNameSymbol", Scheme.instance);
            try {
                SlotSet.set$Mnfield$Ex.apply3($Stthis$Mnform$St, "form-environment", Environment.make(((Symbol) form$Mnname).toString()));
                try {
                    addToCurrentFormEnvironment((Symbol) form$Mnname, $Stthis$Mnform$St);
                    SlotSet slotSet = SlotSet.set$Mnfield$Ex;
                    Object obj2 = $Stthis$Mnform$St;
                    Object[] objArr = new Object[2];
                    try {
                        objArr[0] = ((Symbol) form$Mnname).toString();
                        objArr[1] = "-global-vars";
                        FString stringAppend = strings.stringAppend(objArr);
                        slotSet.apply3(obj2, "global-var-environment", Environment.make(stringAppend == null ? null : stringAppend.toString()));
                        return;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "symbol->string", 1, form$Mnname);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "add-to-current-form-environment", 0, form$Mnname);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "symbol->string", 1, form$Mnname);
            }
        }
        $Sttest$Mnenvironment$St = Environment.make("test-env");
        Invoke.invoke.apply3(Environment.getCurrent(), "addParent", $Sttest$Mnenvironment$St);
        $Sttest$Mnglobal$Mnvar$Mnenvironment$St = Environment.make("test-global-var-env");
    }

    static Object lambda26(Object arg$Mnname, Object bodyform, Object list$Mnof$Mnargs) {
        Object consX;
        Object consX2;
        Object consX3;
        PairWithPosition pairWithPosition = Lit142;
        consX = LList.consX(new Object[]{arg$Mnname, LList.Empty});
        consX2 = LList.consX(new Object[]{bodyform, LList.Empty});
        Pair make = Pair.make(Quote.append$V(new Object[]{Lit144, Pair.make(Quote.append$V(new Object[]{Lit145, Pair.make(consX, consX2)}), LList.Empty)}), LList.Empty);
        consX3 = LList.consX(new Object[]{list$Mnof$Mnargs, LList.Empty});
        return Quote.append$V(new Object[]{Lit140, Pair.make(Quote.append$V(new Object[]{Lit141, Pair.make(pairWithPosition, Pair.make(Quote.append$V(new Object[]{Lit143, Pair.make(make, Pair.make(Quote.append$V(new Object[]{Lit146, consX3}), LList.Empty))}), LList.Empty))}), LList.Empty)});
    }

    public static Object $StYailBreak$St(Object ignore) {
        return signalRuntimeError("Break should be run only from within a loop", "Bad use of Break");
    }

    static Object lambda27(Object lambda$Mnarg$Mnname, Object body$Mnform, Object start, Object end, Object step) {
        Object consX;
        Object consX2;
        Object consX3;
        Object consX4;
        Object consX5;
        PairWithPosition pairWithPosition = Lit151;
        consX = LList.consX(new Object[]{lambda$Mnarg$Mnname, LList.Empty});
        consX2 = LList.consX(new Object[]{body$Mnform, LList.Empty});
        Object append$V = Quote.append$V(new Object[]{Lit153, Pair.make(consX, consX2)});
        consX3 = LList.consX(new Object[]{step, LList.Empty});
        consX4 = LList.consX(new Object[]{end, consX3});
        consX5 = LList.consX(new Object[]{start, consX4});
        return Quote.append$V(new Object[]{Lit149, Pair.make(Quote.append$V(new Object[]{Lit150, Pair.make(pairWithPosition, Pair.make(Quote.append$V(new Object[]{Lit152, Pair.make(append$V, consX5)}), LList.Empty))}), LList.Empty)});
    }

    static Object lambda28$V(Object condition, Object body, Object[] argsArray) {
        Object consX;
        Object consX2;
        LList rest = LList.makeList(argsArray, 0);
        PairWithPosition pairWithPosition = Lit158;
        consX = LList.consX(new Object[]{body, rest});
        consX2 = LList.consX(new Object[]{condition, Pair.make(Quote.append$V(new Object[]{Lit161, Pair.make(Quote.append$V(new Object[]{Lit162, consX}), Lit163)}), Lit164)});
        return Quote.append$V(new Object[]{Lit155, Pair.make(Pair.make(Quote.append$V(new Object[]{Lit156, Pair.make(Quote.append$V(new Object[]{Lit157, Pair.make(pairWithPosition, Pair.make(Quote.append$V(new Object[]{Lit159, Pair.make(Quote.append$V(new Object[]{Lit160, consX2}), LList.Empty)}), LList.Empty))}), LList.Empty)}), LList.Empty), Lit165)});
    }

    public static Object callComponentMethod(Object component$Mnname, Object method$Mnname, Object arglist, Object typelist) {
        Object result;
        Object consX;
        Object consX2;
        Object coerced$Mnargs = coerceArgs(method$Mnname, arglist, typelist);
        try {
            Object component = lookupInCurrentFormEnvironment((Symbol) component$Mnname);
            if (isAllCoercible(coerced$Mnargs) != Boolean.FALSE) {
                try {
                    Apply apply = Scheme.apply;
                    Invoke invoke = Invoke.invoke;
                    consX = LList.consX(new Object[]{method$Mnname, Quote.append$V(new Object[]{coerced$Mnargs, LList.Empty})});
                    consX2 = LList.consX(new Object[]{component, consX});
                    result = apply.apply2(invoke, consX2);
                } catch (PermissionException exception) {
                    result = Invoke.invoke.applyN(new Object[]{Form.getActiveForm(), "dispatchPermissionDeniedEvent", component, method$Mnname, exception});
                }
            } else {
                result = generateRuntimeTypeError(method$Mnname, arglist);
            }
            return sanitizeReturnValue(component, method$Mnname, result);
        } catch (ClassCastException e) {
            throw new WrongType(e, "lookup-in-current-form-environment", 0, component$Mnname);
        }
    }

    public static Object callComponentMethodWithContinuation(Object component$Mnname, Object methodName, Object arglist, Object typelist, Object k) {
        Object consX;
        Object consX2;
        Object consX3;
        frame0 frame0Var = new frame0();
        frame0Var.method$Mnname = methodName;
        frame0Var.k = k;
        Object coerced$Mnargs = coerceArgs(frame0Var.method$Mnname, arglist, typelist);
        try {
            frame0Var.component = lookupInCurrentFormEnvironment((Symbol) component$Mnname);
            Continuation continuation = ContinuationUtil.wrap(frame0Var.lambda$Fn2, Lit4);
            if (isAllCoercible(coerced$Mnargs) != Boolean.FALSE) {
                try {
                    Apply apply = Scheme.apply;
                    Invoke invoke = Invoke.invoke;
                    Object[] objArr = new Object[2];
                    objArr[0] = frame0Var.component;
                    Object[] objArr2 = new Object[2];
                    try {
                        objArr2[0] = frame0Var.method$Mnname;
                        consX = LList.consX(new Object[]{continuation, LList.Empty});
                        objArr2[1] = Quote.append$V(new Object[]{coerced$Mnargs, consX});
                        consX2 = LList.consX(objArr2);
                        objArr[1] = consX2;
                        consX3 = LList.consX(objArr);
                        return apply.apply2(invoke, consX3);
                    } catch (PermissionException e) {
                        exception = e;
                        return Invoke.invoke.applyN(new Object[]{Form.getActiveForm(), "dispatchPermissionDeniedEvent", frame0Var.component, frame0Var.method$Mnname, exception});
                    }
                } catch (PermissionException e2) {
                    exception = e2;
                }
            } else {
                return generateRuntimeTypeError(frame0Var.method$Mnname, arglist);
            }
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "lookup-in-current-form-environment", 0, component$Mnname);
        }
    }

    /* compiled from: runtime4411419262522357548.scm */
    /* loaded from: classes.dex */
    public class frame0 extends ModuleBody {
        Object component;
        Object k;
        final ModuleMethod lambda$Fn2;
        Object method$Mnname;

        public frame0() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 2, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:1131");
            this.lambda$Fn2 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 2 ? lambda2(obj) : super.apply1(moduleMethod, obj);
        }

        Object lambda2(Object v) {
            return Scheme.applyToArgs.apply2(this.k, runtime.sanitizeReturnValue(this.component, this.method$Mnname, v));
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

    public static Object callComponentMethodWithBlockingContinuation(Object component$Mnname, Object method$Mnname, Object arglist, Object typelist) {
        frame1 frame1Var = new frame1();
        frame1Var.result = Boolean.FALSE;
        callComponentMethodWithContinuation(component$Mnname, method$Mnname, arglist, typelist, frame1Var.lambda$Fn3);
        return frame1Var.result;
    }

    /* compiled from: runtime4411419262522357548.scm */
    /* loaded from: classes.dex */
    public class frame1 extends ModuleBody {
        final ModuleMethod lambda$Fn3;
        Object result;

        public frame1() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 3, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:1152");
            this.lambda$Fn3 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector != 3) {
                return super.apply1(moduleMethod, obj);
            }
            lambda3(obj);
            return Values.empty;
        }

        void lambda3(Object v) {
            this.result = v;
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
    }

    public static Object callComponentTypeMethod(Object possible$Mncomponent, Object component$Mntype, Object method$Mnname, Object arglist, Object typelist) {
        Object generateRuntimeTypeError;
        Object consX;
        Object consX2;
        Object coerced$Mnargs = coerceArgs(method$Mnname, arglist, lists.cdr.apply1(typelist));
        Object component$Mnvalue = coerceToComponentOfType(possible$Mncomponent, component$Mntype);
        if (!(component$Mnvalue instanceof Component)) {
            Object result = generateRuntimeTypeError(method$Mnname, LList.list1(getDisplayRepresentation(possible$Mncomponent)));
            return result;
        }
        if (isAllCoercible(coerced$Mnargs) != Boolean.FALSE) {
            Apply apply = Scheme.apply;
            Invoke invoke = Invoke.invoke;
            consX = LList.consX(new Object[]{method$Mnname, Quote.append$V(new Object[]{coerced$Mnargs, LList.Empty})});
            consX2 = LList.consX(new Object[]{component$Mnvalue, consX});
            generateRuntimeTypeError = apply.apply2(invoke, consX2);
        } else {
            generateRuntimeTypeError = generateRuntimeTypeError(method$Mnname, arglist);
        }
        Object result2 = generateRuntimeTypeError;
        return sanitizeReturnValue(component$Mnvalue, method$Mnname, result2);
    }

    public static Object callComponentTypeMethodWithContinuation(Object component$Mntype, Object methodName, Object arglist, Object typelist, Object k) {
        Apply apply;
        Invoke invoke;
        Object[] objArr;
        Object consX;
        Object consX2;
        Object consX3;
        frame2 frame2Var = new frame2();
        frame2Var.method$Mnname = methodName;
        frame2Var.k = k;
        Object coerced$Mnargs = coerceArgs(frame2Var.method$Mnname, arglist, lists.cdr.apply1(typelist));
        try {
            frame2Var.component$Mnvalue = coerceToComponentOfType(loc$possible$Mncomponent.get(), component$Mntype);
            Continuation continuation = ContinuationUtil.wrap(frame2Var.lambda$Fn4, Lit4);
            if (isAllCoercible(coerced$Mnargs) == Boolean.FALSE) {
                return generateRuntimeTypeError(frame2Var.method$Mnname, arglist);
            }
            try {
                apply = Scheme.apply;
                invoke = Invoke.invoke;
                objArr = new Object[2];
            } catch (PermissionException e) {
                e = e;
            }
            try {
                objArr[0] = frame2Var.component$Mnvalue;
                consX = LList.consX(new Object[]{continuation, LList.Empty});
                consX2 = LList.consX(new Object[]{frame2Var.method$Mnname, Quote.append$V(new Object[]{coerced$Mnargs, consX})});
                objArr[1] = consX2;
                consX3 = LList.consX(objArr);
                return apply.apply2(invoke, consX3);
            } catch (PermissionException e2) {
                e = e2;
                PermissionException exception = e;
                Invoke invoke2 = Invoke.invoke;
                Object[] objArr2 = new Object[5];
                objArr2[0] = Form.getActiveForm();
                objArr2[1] = "dispatchPermissionDeniedEvent";
                try {
                    objArr2[2] = loc$component.get();
                    objArr2[3] = frame2Var.method$Mnname;
                    objArr2[4] = exception;
                    return invoke2.applyN(objArr2);
                } catch (UnboundLocationException e3) {
                    e3.setLine("/tmp/runtime4411419262522357548.scm", 1200, 72);
                    throw e3;
                }
            }
        } catch (UnboundLocationException e4) {
            e4.setLine("/tmp/runtime4411419262522357548.scm", 1192, 56);
            throw e4;
        }
    }

    /* compiled from: runtime4411419262522357548.scm */
    /* loaded from: classes.dex */
    public class frame2 extends ModuleBody {
        Object component$Mnvalue;
        Object k;
        final ModuleMethod lambda$Fn4;
        Object method$Mnname;

        public frame2() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 4, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:1194");
            this.lambda$Fn4 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 4 ? lambda4(obj) : super.apply1(moduleMethod, obj);
        }

        Object lambda4(Object v) {
            return Scheme.applyToArgs.apply2(this.k, runtime.sanitizeReturnValue(this.component$Mnvalue, this.method$Mnname, v));
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 4) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object callComponentTypeMethodWithBlockingContinuation(Object component$Mntype, Object method$Mnname, Object arglist, Object typelist) {
        frame3 frame3Var = new frame3();
        frame3Var.result = Boolean.FALSE;
        callComponentTypeMethodWithContinuation(component$Mntype, method$Mnname, arglist, typelist, frame3Var.lambda$Fn5);
        return frame3Var.result;
    }

    /* compiled from: runtime4411419262522357548.scm */
    /* loaded from: classes.dex */
    public class frame3 extends ModuleBody {
        final ModuleMethod lambda$Fn5;
        Object result;

        public frame3() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 5, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:1211");
            this.lambda$Fn5 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector != 5) {
                return super.apply1(moduleMethod, obj);
            }
            lambda5(obj);
            return Values.empty;
        }

        void lambda5(Object v) {
            this.result = v;
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 5) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object callYailPrimitive(Object prim, Object arglist, Object typelist, Object codeblocks$Mnname) {
        Object coerced$Mnargs = coerceArgs(codeblocks$Mnname, arglist, typelist);
        if (isAllCoercible(coerced$Mnargs) != Boolean.FALSE) {
            return Scheme.apply.apply2(prim, coerced$Mnargs);
        }
        return generateRuntimeTypeError(codeblocks$Mnname, arglist);
    }

    public static Object sanitizeComponentData(Object data) {
        if (!strings.isString(data) && isYailDictionary(data) == Boolean.FALSE) {
            if (data instanceof Map) {
                try {
                    return javaMap$To$YailDictionary((Map) data);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "java-map->yail-dictionary", 0, data);
                }
            }
            if (isYailList(data) == Boolean.FALSE) {
                if (lists.isList(data)) {
                    return kawaList$To$YailList(data);
                }
                if (data instanceof Collection) {
                    try {
                        return javaCollection$To$YailList((Collection) data);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "java-collection->yail-list", 0, data);
                    }
                }
                return sanitizeAtomic(data);
            }
        }
        return data;
    }

    public static Object sanitizeReturnValue(Object component, Object func$Mnname, Object value) {
        if (isEnum(value) != Boolean.FALSE) {
            return value;
        }
        Object value2 = OptionHelper.optionListFromValue(component, func$Mnname == null ? null : func$Mnname.toString(), value);
        return isEnum(value2) != Boolean.FALSE ? value2 : sanitizeComponentData(value2);
    }

    public static Object javaCollection$To$YailList(Collection collection) {
        return kawaList$To$YailList(javaCollection$To$KawaList(collection));
    }

    public static Object javaCollection$To$KawaList(Collection collection) {
        Iterator iterator = collection.iterator();
        Object result = LList.Empty;
        while (iterator.hasNext()) {
            result = lists.cons(sanitizeComponentData(iterator.next()), result);
        }
        try {
            return LList.reverseInPlace((LList) result);
        } catch (ClassCastException e) {
            throw new WrongType(e, "reverse!", 1, result);
        }
    }

    public static Object javaMap$To$YailDictionary(Map jMap) {
        YailDictionary dict = new YailDictionary();
        for (Object key : jMap.keySet()) {
            dict.put(key, sanitizeComponentData(jMap.get(key)));
        }
        return dict;
    }

    public static Object sanitizeAtomic(Object arg) {
        if (arg == null || Values.empty == arg) {
            return null;
        }
        if (!numbers.isNumber(arg)) {
            return arg;
        }
        Numeric numericarg = Arithmetic.asNumeric(arg);
        return numericarg;
    }

    public static Object signalRuntimeError(Object message, Object error$Mntype) {
        throw new YailRuntimeError(message == null ? null : message.toString(), error$Mntype != null ? error$Mntype.toString() : null);
    }

    public static Object signalRuntimeFormError(Object function$Mnname, Object error$Mnnumber, Object message) {
        return Invoke.invoke.applyN(new Object[]{$Stthis$Mnform$St, "runtimeFormErrorOccurredEvent", function$Mnname, error$Mnnumber, message});
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean yailNot(Object foo) {
        return ((foo != Boolean.FALSE ? 1 : 0) + 1) & 1;
    }

    public static Object callWithCoercedArgs(Object func, Object arglist, Object typelist, Object codeblocks$Mnname) {
        Object coerced$Mnargs = coerceArgs(codeblocks$Mnname, arglist, typelist);
        if (isAllCoercible(coerced$Mnargs) != Boolean.FALSE) {
            return Scheme.apply.apply2(func, coerced$Mnargs);
        }
        return generateRuntimeTypeError(codeblocks$Mnname, arglist);
    }

    public static Object $PcSetAndCoerceProperty$Ex(Object comp, Object prop$Mnname, Object property$Mnvalue, Object property$Mntype) {
        androidLog(Format.formatToString(0, "coercing for setting property ~A -- value ~A to type ~A", prop$Mnname, property$Mnvalue, property$Mntype));
        Object coerced$Mnarg = coerceArg(property$Mnvalue, property$Mntype);
        androidLog(Format.formatToString(0, "coerced property value was: ~A ", coerced$Mnarg));
        if (isAllCoercible(LList.list1(coerced$Mnarg)) != Boolean.FALSE) {
            try {
                return Invoke.invoke.apply3(comp, prop$Mnname, coerced$Mnarg);
            } catch (PermissionException exception) {
                return Invoke.invoke.applyN(new Object[]{Form.getActiveForm(), "dispatchPermissionDeniedEvent", comp, prop$Mnname, exception});
            }
        }
        return generateRuntimeTypeError(prop$Mnname, LList.list1(property$Mnvalue));
    }

    public static Object $PcSetSubformLayoutProperty$Ex(Object layout, Object prop$Mnname, Object value) {
        return Invoke.invoke.apply3(layout, prop$Mnname, value);
    }

    public static Object generateRuntimeTypeError(Object proc$Mnname, Object arglist) {
        androidLog(Format.formatToString(0, "arglist is: ~A ", arglist));
        Object string$Mnname = coerceToString(proc$Mnname);
        Object[] objArr = new Object[4];
        objArr[0] = "The operation ";
        objArr[1] = string$Mnname;
        Object[] objArr2 = new Object[2];
        objArr2[0] = " cannot accept the argument~P: ";
        try {
            objArr2[1] = Integer.valueOf(lists.length((LList) arglist));
            objArr[2] = Format.formatToString(0, objArr2);
            objArr[3] = showArglistNoParens(arglist);
            return signalRuntimeError(strings.stringAppend(objArr), strings.stringAppend("Bad arguments to ", string$Mnname));
        } catch (ClassCastException e) {
            throw new WrongType(e, PropertyTypeConstants.PROPERTY_TYPE_LENGTH, 1, arglist);
        }
    }

    public static Object showArglistNoParens(Object args) {
        Object result = LList.Empty;
        Object arg0 = args;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                Object arg03 = arg02.getCdr();
                result = Pair.make(getDisplayRepresentation(arg02.getCar()), result);
                arg0 = arg03;
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        LList elements = LList.reverseInPlace(result);
        Object result2 = LList.Empty;
        Object arg04 = elements;
        while (arg04 != LList.Empty) {
            try {
                Pair arg05 = (Pair) arg04;
                arg04 = arg05.getCdr();
                Object s = arg05.getCar();
                result2 = Pair.make(strings.stringAppend("[", s, "]"), result2);
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "arg0", -2, arg04);
            }
        }
        Object rest$Mnelements = LList.reverseInPlace(result2);
        Object result3 = "";
        while (!lists.isNull(rest$Mnelements)) {
            Object stringAppend = strings.stringAppend(result3, ", ", lists.car.apply1(rest$Mnelements));
            rest$Mnelements = lists.cdr.apply1(rest$Mnelements);
            result3 = stringAppend;
        }
        return result3;
    }

    public static Object coerceArgs(Object procedure$Mnname, Object arglist, Object typelist) {
        if (lists.isNull(typelist)) {
            return lists.isNull(arglist) ? arglist : signalRuntimeError(strings.stringAppend("The procedure ", procedure$Mnname, " expects no arguments, but it was called with the arguments: ", showArglistNoParens(arglist)), strings.stringAppend("Wrong number of arguments for", procedure$Mnname));
        }
        try {
            try {
                if (lists.length((LList) arglist) != lists.length((LList) typelist)) {
                    return signalRuntimeError(strings.stringAppend("The arguments ", showArglistNoParens(arglist), " are the wrong number of arguments for ", getDisplayRepresentation(procedure$Mnname)), strings.stringAppend("Wrong number of arguments for", getDisplayRepresentation(procedure$Mnname)));
                }
                Object result = LList.Empty;
                Object arg0 = arglist;
                Object arg1 = typelist;
                while (arg0 != LList.Empty && arg1 != LList.Empty) {
                    try {
                        Pair arg02 = (Pair) arg0;
                        try {
                            Pair arg12 = (Pair) arg1;
                            arg0 = arg02.getCdr();
                            arg1 = arg12.getCdr();
                            result = Pair.make(coerceArg(arg02.getCar(), arg12.getCar()), result);
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "arg1", -2, arg1);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "arg0", -2, arg0);
                    }
                }
                return LList.reverseInPlace(result);
            } catch (ClassCastException e3) {
                throw new WrongType(e3, PropertyTypeConstants.PROPERTY_TYPE_LENGTH, 1, typelist);
            }
        } catch (ClassCastException e4) {
            throw new WrongType(e4, PropertyTypeConstants.PROPERTY_TYPE_LENGTH, 1, arglist);
        }
    }

    public static Object coerceArg(Object arg, Object type) {
        Object arg2 = sanitizeAtomic(arg);
        if (IsEqual.apply(type, Lit5)) {
            return coerceToNumber(arg2);
        }
        if (IsEqual.apply(type, Lit6)) {
            return coerceToText(arg2);
        }
        if (IsEqual.apply(type, Lit7)) {
            return coerceToBoolean(arg2);
        }
        if (IsEqual.apply(type, Lit8)) {
            return coerceToYailList(arg2);
        }
        if (IsEqual.apply(type, Lit9)) {
            return coerceToNumberList(arg2);
        }
        if (IsEqual.apply(type, Lit10)) {
            return coerceToInstant(arg2);
        }
        if (IsEqual.apply(type, Lit11)) {
            return coerceToComponent(arg2);
        }
        if (IsEqual.apply(type, Lit12)) {
            return coerceToPair(arg2);
        }
        if (IsEqual.apply(type, Lit13)) {
            return coerceToKey(arg2);
        }
        if (IsEqual.apply(type, Lit14)) {
            return coerceToDictionary(arg2);
        }
        if (IsEqual.apply(type, Lit15)) {
            return arg2;
        }
        if (isEnumType(type) != Boolean.FALSE) {
            return coerceToEnum(arg2, type);
        }
        return coerceToComponentOfType(arg2, type);
    }

    public static Object coerceToNumberList(Object l) {
        if (isYailList(l) == Boolean.FALSE) {
            return Lit2;
        }
        Object arg0 = yailListContents(l);
        Object result = LList.Empty;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                arg0 = arg02.getCdr();
                result = Pair.make(coerceToNumber(arg02.getCar()), result);
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        LList coerced = LList.reverseInPlace(result);
        if (isAllCoercible(coerced) == Boolean.FALSE) {
            try {
                return loc$non$Mncoercible$Mnvalue.get();
            } catch (UnboundLocationException e2) {
                e2.setLine("/tmp/runtime4411419262522357548.scm", 1500, 9);
                throw e2;
            }
        }
        return Scheme.apply.apply2(make$Mnyail$Mnlist, coerced);
    }

    public static Object isEnumType(Object type) {
        try {
            return stringContains(((Symbol) type).toString(), "Enum");
        } catch (ClassCastException e) {
            throw new WrongType(e, "symbol->string", 1, type);
        }
    }

    public static Object isEnum(Object arg) {
        return arg instanceof OptionList ? Boolean.TRUE : Boolean.FALSE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0033, code lost:
    
        if (r1.apply2(r2, gnu.lists.LList.list2(r7, kawa.lib.misc.string$To$Symbol((java.lang.CharSequence) r4))) != java.lang.Boolean.FALSE) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:?, code lost:
    
        return r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0051, code lost:
    
        r1 = com.google.appinventor.components.runtime.util.TypeUtil.castToEnum(r7, (gnu.mapping.Symbol) r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0056, code lost:
    
        if (r1 != null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005b, code lost:
    
        return com.google.youngandroid.runtime.Lit2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:?, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x005c, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0065, code lost:
    
        throw new gnu.mapping.WrongType(r7, "com.google.appinventor.components.runtime.util.TypeUtil.castToEnum(java.lang.Object,gnu.mapping.Symbol)", 2, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x004a, code lost:
    
        if (r0 != java.lang.Boolean.FALSE) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object coerceToEnum(java.lang.Object r7, java.lang.Object r8) {
        /*
            java.lang.String r0 = "coerce-to-enum"
            androidLog(r0)
            java.lang.Object r0 = isEnum(r7)
            r1 = 0
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            if (r0 == r1) goto L48
            gnu.kawa.functions.Apply r1 = kawa.standard.Scheme.apply
            gnu.kawa.reflect.InstanceOf r2 = kawa.standard.Scheme.instanceOf
            r3 = 1
            r4 = r8
            gnu.mapping.Symbol r4 = (gnu.mapping.Symbol) r4     // Catch: java.lang.ClassCastException -> L3f
            java.lang.String r4 = kawa.lib.misc.symbol$To$String(r4)
            java.lang.String r5 = "Enum"
            java.lang.String r6 = ""
            java.lang.Object r4 = stringReplaceAll(r4, r5, r6)
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch: java.lang.ClassCastException -> L36
            gnu.mapping.SimpleSymbol r3 = kawa.lib.misc.string$To$Symbol(r4)
            gnu.lists.Pair r3 = gnu.lists.LList.list2(r7, r3)
            java.lang.Object r1 = r1.apply2(r2, r3)
            java.lang.Boolean r2 = java.lang.Boolean.FALSE
            if (r1 == r2) goto L4e
            goto L4c
        L36:
            r7 = move-exception
            gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
            java.lang.String r0 = "string->symbol"
            r8.<init>(r7, r0, r3, r4)
            throw r8
        L3f:
            r7 = move-exception
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            java.lang.String r1 = "symbol->string"
            r0.<init>(r7, r1, r3, r8)
            throw r0
        L48:
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            if (r0 == r1) goto L4e
        L4c:
            r0 = r7
            goto L5b
        L4e:
            r1 = r8
            gnu.mapping.Symbol r1 = (gnu.mapping.Symbol) r1     // Catch: java.lang.ClassCastException -> L5c
            com.google.appinventor.components.common.OptionList r1 = com.google.appinventor.components.runtime.util.TypeUtil.castToEnum(r7, r1)
            r0 = r1
            if (r0 != 0) goto L5b
            gnu.lists.PairWithPosition r1 = com.google.youngandroid.runtime.Lit2
            r0 = r1
        L5b:
            return r0
        L5c:
            r7 = move-exception
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            java.lang.String r1 = "com.google.appinventor.components.runtime.util.TypeUtil.castToEnum(java.lang.Object,gnu.mapping.Symbol)"
            r2 = 2
            r0.<init>(r7, r1, r2, r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.youngandroid.runtime.coerceToEnum(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public static Object coerceToText(Object arg) {
        return arg == null ? Lit2 : coerceToString(arg);
    }

    public static Object coerceToInstant(Object arg) {
        if (arg instanceof Calendar) {
            return arg;
        }
        Object as$Mnmillis = coerceToNumber(arg);
        if (!numbers.isNumber(as$Mnmillis)) {
            return Lit2;
        }
        try {
            return Clock.MakeInstantFromMillis(((Number) as$Mnmillis).longValue());
        } catch (ClassCastException e) {
            throw new WrongType(e, "com.google.appinventor.components.runtime.Clock.MakeInstantFromMillis(long)", 1, as$Mnmillis);
        }
    }

    public static Object coerceToComponent(Object arg) {
        if (!strings.isString(arg)) {
            if (arg instanceof Component) {
                return arg;
            }
            return misc.isSymbol(arg) ? lookupComponent(arg) : Lit2;
        }
        if (strings.isString$Eq(arg, "")) {
            return null;
        }
        try {
            return lookupComponent(misc.string$To$Symbol((CharSequence) arg));
        } catch (ClassCastException e) {
            throw new WrongType(e, "string->symbol", 1, arg);
        }
    }

    public static Object coerceToComponentOfType(Object arg, Object type) {
        Object component = coerceToComponent(arg);
        Object component2 = Lit2;
        return (component == component2 || Scheme.apply.apply2(Scheme.instanceOf, LList.list2(arg, type$To$Class(type))) == Boolean.FALSE) ? component2 : component;
    }

    public static Object type$To$Class(Object type$Mnname) {
        return type$Mnname == Lit16 ? Lit17 : type$Mnname;
    }

    public static Object coerceToNumber(Object arg) {
        if (numbers.isNumber(arg)) {
            return arg;
        }
        if (strings.isString(arg)) {
            Object x = paddedString$To$Number(arg);
            return x != Boolean.FALSE ? x : Lit2;
        }
        if (isEnum(arg) == Boolean.FALSE) {
            return Lit2;
        }
        Object val = Scheme.applyToArgs.apply1(GetNamedPart.getNamedPart.apply2(arg, Lit18));
        return numbers.isNumber(val) ? val : Lit2;
    }

    public static Object coerceToKey(Object arg) {
        if (numbers.isNumber(arg)) {
            return coerceToNumber(arg);
        }
        if (strings.isString(arg)) {
            return coerceToString(arg);
        }
        return (isEnum(arg) == Boolean.FALSE && !(arg instanceof Component)) ? Lit2 : arg;
    }

    public static Object coerceToString(Object arg) {
        frame4 frame4Var = new frame4();
        frame4Var.arg = arg;
        if (frame4Var.arg == null) {
            return "*nothing*";
        }
        if (strings.isString(frame4Var.arg)) {
            return frame4Var.arg;
        }
        if (numbers.isNumber(frame4Var.arg)) {
            return appinventorNumber$To$String(frame4Var.arg);
        }
        if (misc.isBoolean(frame4Var.arg)) {
            return boolean$To$String(frame4Var.arg);
        }
        if (isYailList(frame4Var.arg) != Boolean.FALSE) {
            return coerceToString(yailList$To$KawaList(frame4Var.arg));
        }
        if (lists.isList(frame4Var.arg)) {
            if (Form.getActiveForm().ShowListsAsJson()) {
                Object arg0 = frame4Var.arg;
                Object result = LList.Empty;
                while (arg0 != LList.Empty) {
                    try {
                        Pair arg02 = (Pair) arg0;
                        arg0 = arg02.getCdr();
                        result = Pair.make(((Procedure) get$Mnjson$Mndisplay$Mnrepresentation).apply1(arg02.getCar()), result);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "arg0", -2, arg0);
                    }
                }
                LList pieces = LList.reverseInPlace(result);
                return strings.stringAppend("[", joinStrings(pieces, ", "), "]");
            }
            Object arg03 = frame4Var.arg;
            Object result2 = LList.Empty;
            while (arg03 != LList.Empty) {
                try {
                    Pair arg04 = (Pair) arg03;
                    arg03 = arg04.getCdr();
                    result2 = Pair.make(coerceToString(arg04.getCar()), result2);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "arg0", -2, arg03);
                }
            }
            frame4Var.pieces = LList.reverseInPlace(result2);
            return ports.callWithOutputString(frame4Var.lambda$Fn6);
        }
        if (isEnum(frame4Var.arg) != Boolean.FALSE) {
            Object val = Scheme.applyToArgs.apply1(GetNamedPart.getNamedPart.apply2(frame4Var.arg, Lit18));
            return strings.isString(val) ? val : Lit2;
        }
        return ports.callWithOutputString(frame4Var.lambda$Fn7);
    }

    /* compiled from: runtime4411419262522357548.scm */
    /* loaded from: classes.dex */
    public class frame4 extends ModuleBody {
        Object arg;
        final ModuleMethod lambda$Fn6;
        final ModuleMethod lambda$Fn7;
        LList pieces;

        public frame4() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 6, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:1610");
            this.lambda$Fn6 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 7, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod2.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:1616");
            this.lambda$Fn7 = moduleMethod2;
        }

        void lambda6(Object port) {
            ports.display(this.pieces, port);
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
                    lambda6(obj);
                    return Values.empty;
                case 7:
                    lambda7(obj);
                    return Values.empty;
                default:
                    return super.apply1(moduleMethod, obj);
            }
        }

        void lambda7(Object port) {
            ports.display(this.arg, port);
        }
    }

    public static Object getDisplayRepresentation(Object arg) {
        if (Form.getActiveForm().ShowListsAsJson()) {
            return ((Procedure) get$Mnjson$Mndisplay$Mnrepresentation).apply1(arg);
        }
        return ((Procedure) get$Mnoriginal$Mndisplay$Mnrepresentation).apply1(arg);
    }

    static Object lambda8(Object arg) {
        frame5 frame5Var = new frame5();
        frame5Var.arg = arg;
        if (Scheme.numEqu.apply2(frame5Var.arg, Lit19) != Boolean.FALSE) {
            return "+infinity";
        }
        if (Scheme.numEqu.apply2(frame5Var.arg, Lit20) != Boolean.FALSE) {
            return "-infinity";
        }
        if (frame5Var.arg == null) {
            return "*nothing*";
        }
        if (misc.isSymbol(frame5Var.arg)) {
            Object obj = frame5Var.arg;
            try {
                return ((Symbol) obj).toString();
            } catch (ClassCastException e) {
                throw new WrongType(e, "symbol->string", 1, obj);
            }
        }
        if (strings.isString(frame5Var.arg)) {
            if (strings.isString$Eq(frame5Var.arg, "")) {
                return "*empty-string*";
            }
            return frame5Var.arg;
        }
        if (numbers.isNumber(frame5Var.arg)) {
            return appinventorNumber$To$String(frame5Var.arg);
        }
        if (misc.isBoolean(frame5Var.arg)) {
            return boolean$To$String(frame5Var.arg);
        }
        if (isYailList(frame5Var.arg) != Boolean.FALSE) {
            return getDisplayRepresentation(yailList$To$KawaList(frame5Var.arg));
        }
        if (lists.isList(frame5Var.arg)) {
            Object arg0 = frame5Var.arg;
            Object result = LList.Empty;
            while (arg0 != LList.Empty) {
                try {
                    Pair arg02 = (Pair) arg0;
                    arg0 = arg02.getCdr();
                    result = Pair.make(getDisplayRepresentation(arg02.getCar()), result);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "arg0", -2, arg0);
                }
            }
            frame5Var.pieces = LList.reverseInPlace(result);
            return ports.callWithOutputString(frame5Var.lambda$Fn9);
        }
        return ports.callWithOutputString(frame5Var.lambda$Fn10);
    }

    /* compiled from: runtime4411419262522357548.scm */
    /* loaded from: classes.dex */
    public class frame5 extends ModuleBody {
        Object arg;
        final ModuleMethod lambda$Fn10;
        final ModuleMethod lambda$Fn9;
        LList pieces;

        public frame5() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 8, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:1650");
            this.lambda$Fn9 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 9, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod2.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:1651");
            this.lambda$Fn10 = moduleMethod2;
        }

        void lambda9(Object port) {
            ports.display(this.pieces, port);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 8:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 9:
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
                case 8:
                    lambda9(obj);
                    return Values.empty;
                case 9:
                    lambda10(obj);
                    return Values.empty;
                default:
                    return super.apply1(moduleMethod, obj);
            }
        }

        void lambda10(Object port) {
            ports.display(this.arg, port);
        }
    }

    static Object lambda11(Object arg) {
        frame6 frame6Var = new frame6();
        frame6Var.arg = arg;
        if (Scheme.numEqu.apply2(frame6Var.arg, Lit21) != Boolean.FALSE) {
            return "+infinity";
        }
        if (Scheme.numEqu.apply2(frame6Var.arg, Lit22) != Boolean.FALSE) {
            return "-infinity";
        }
        if (frame6Var.arg == null) {
            return "*nothing*";
        }
        if (misc.isSymbol(frame6Var.arg)) {
            Object obj = frame6Var.arg;
            try {
                return ((Symbol) obj).toString();
            } catch (ClassCastException e) {
                throw new WrongType(e, "symbol->string", 1, obj);
            }
        }
        if (strings.isString(frame6Var.arg)) {
            return strings.stringAppend("\"", frame6Var.arg, "\"");
        }
        if (numbers.isNumber(frame6Var.arg)) {
            return appinventorNumber$To$String(frame6Var.arg);
        }
        if (misc.isBoolean(frame6Var.arg)) {
            return boolean$To$String(frame6Var.arg);
        }
        if (isYailList(frame6Var.arg) != Boolean.FALSE) {
            return ((Procedure) get$Mnjson$Mndisplay$Mnrepresentation).apply1(yailList$To$KawaList(frame6Var.arg));
        }
        if (lists.isList(frame6Var.arg)) {
            Object arg0 = frame6Var.arg;
            Object result = LList.Empty;
            while (arg0 != LList.Empty) {
                try {
                    Pair arg02 = (Pair) arg0;
                    arg0 = arg02.getCdr();
                    result = Pair.make(((Procedure) get$Mnjson$Mndisplay$Mnrepresentation).apply1(arg02.getCar()), result);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "arg0", -2, arg0);
                }
            }
            LList pieces = LList.reverseInPlace(result);
            return strings.stringAppend("[", joinStrings(pieces, ", "), "]");
        }
        return ports.callWithOutputString(frame6Var.lambda$Fn12);
    }

    /* compiled from: runtime4411419262522357548.scm */
    /* loaded from: classes.dex */
    public class frame6 extends ModuleBody {
        Object arg;
        final ModuleMethod lambda$Fn12;

        public frame6() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 10, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:1671");
            this.lambda$Fn12 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector != 10) {
                return super.apply1(moduleMethod, obj);
            }
            lambda12(obj);
            return Values.empty;
        }

        void lambda12(Object port) {
            ports.display(this.arg, port);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 10) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object joinStrings(Object list$Mnof$Mnstrings, Object separator) {
        try {
            return JavaStringUtils.joinStrings((List) list$Mnof$Mnstrings, separator == null ? null : separator.toString());
        } catch (ClassCastException e) {
            throw new WrongType(e, "com.google.appinventor.components.runtime.util.JavaStringUtils.joinStrings(java.util.List,java.lang.String)", 1, list$Mnof$Mnstrings);
        }
    }

    public static Object stringReplace(Object original, Object replacement$Mntable) {
        return lists.isNull(replacement$Mntable) ? original : strings.isString$Eq(original, lists.caar.apply1(replacement$Mntable)) ? lists.cadar.apply1(replacement$Mntable) : stringReplace(original, lists.cdr.apply1(replacement$Mntable));
    }

    public static Object coerceToYailList(Object arg) {
        if (isYailList(arg) != Boolean.FALSE) {
            return arg;
        }
        return isYailDictionary(arg) != Boolean.FALSE ? yailDictionaryDictToAlist(arg) : Lit2;
    }

    public static Object coerceToPair(Object arg) {
        return coerceToYailList(arg);
    }

    public static Object coerceToDictionary(Object arg) {
        if (isYailDictionary(arg) != Boolean.FALSE) {
            return arg;
        }
        if (isYailList(arg) != Boolean.FALSE) {
            return yailDictionaryAlistToDict(arg);
        }
        try {
            return Scheme.applyToArgs.apply1(GetNamedPart.getNamedPart.apply2(arg, Lit23));
        } catch (Exception e) {
            return Scheme.applyToArgs.apply1(Lit2);
        }
    }

    public static Object coerceToBoolean(Object arg) {
        return misc.isBoolean(arg) ? arg : Lit2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isIsCoercible(Object x) {
        return ((x == Lit2 ? 1 : 0) + 1) & 1;
    }

    public static Object isAllCoercible(Object args) {
        if (lists.isNull(args)) {
            return Boolean.TRUE;
        }
        boolean x = isIsCoercible(lists.car.apply1(args));
        if (!x) {
            return x ? Boolean.TRUE : Boolean.FALSE;
        }
        return isAllCoercible(lists.cdr.apply1(args));
    }

    public static String boolean$To$String(Object b) {
        return b != Boolean.FALSE ? "true" : "false";
    }

    public static Object paddedString$To$Number(Object s) {
        return numbers.string$To$Number(s.toString().trim(), 10);
    }

    public static String $StFormatInexact$St(Object n) {
        try {
            return YailNumberToString.format(((Number) n).doubleValue());
        } catch (ClassCastException e) {
            throw new WrongType(e, "com.google.appinventor.components.runtime.util.YailNumberToString.format(double)", 1, n);
        }
    }

    /* compiled from: runtime4411419262522357548.scm */
    /* loaded from: classes.dex */
    public class frame7 extends ModuleBody {
        final ModuleMethod lambda$Fn13;
        final ModuleMethod lambda$Fn14;
        Object n;

        public frame7() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 11, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:1796");
            this.lambda$Fn13 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 12, null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod2.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:1804");
            this.lambda$Fn14 = moduleMethod2;
        }

        void lambda13(Object port) {
            ports.display(this.n, port);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
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
                default:
                    return super.match1(moduleMethod, obj, callContext);
            }
        }

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            switch (moduleMethod.selector) {
                case 11:
                    lambda13(obj);
                    return Values.empty;
                case 12:
                    lambda14(obj);
                    return Values.empty;
                default:
                    return super.apply1(moduleMethod, obj);
            }
        }

        void lambda14(Object port) {
            Object obj = this.n;
            try {
                ports.display(numbers.exact((Number) obj), port);
            } catch (ClassCastException e) {
                throw new WrongType(e, "exact", 1, obj);
            }
        }
    }

    public static Object appinventorNumber$To$String(Object n) {
        frame7 frame7Var = new frame7();
        frame7Var.n = n;
        if (!numbers.isReal(frame7Var.n)) {
            return ports.callWithOutputString(frame7Var.lambda$Fn13);
        }
        if (numbers.isInteger(frame7Var.n)) {
            return ports.callWithOutputString(frame7Var.lambda$Fn14);
        }
        if (numbers.isExact(frame7Var.n)) {
            Object obj = frame7Var.n;
            try {
                return appinventorNumber$To$String(Arithmetic.toInexact((Number) obj));
            } catch (ClassCastException e) {
                throw new WrongType(e, "exact->inexact", 1, obj);
            }
        }
        return $StFormatInexact$St(frame7Var.n);
    }

    public static Object isYailEqual(Object obj, Object obj2) {
        boolean isNull = lists.isNull(obj);
        if (!isNull ? !isNull : !lists.isNull(obj2)) {
            return Boolean.TRUE;
        }
        boolean isNull2 = lists.isNull(obj);
        if (!isNull2 ? !lists.isNull(obj2) : !isNull2) {
            return Boolean.FALSE;
        }
        int i = ((lists.isPair(obj) ? 1 : 0) + 1) & 1;
        if (i == 0 ? i != 0 : !lists.isPair(obj2)) {
            return isYailAtomicEqual(obj, obj2);
        }
        int i2 = ((lists.isPair(obj) ? 1 : 0) + 1) & 1;
        if (i2 == 0 ? !lists.isPair(obj2) : i2 != 0) {
            return Boolean.FALSE;
        }
        Object isYailEqual = isYailEqual(lists.car.apply1(obj), lists.car.apply1(obj2));
        if (isYailEqual == Boolean.FALSE) {
            return isYailEqual;
        }
        return isYailEqual(lists.cdr.apply1(obj), lists.cdr.apply1(obj2));
    }

    public static Object isYailAtomicEqual(Object obj, Object obj2) {
        if (IsEqual.apply(obj, obj2)) {
            return Boolean.TRUE;
        }
        Object isEnum = isEnum(obj);
        if (isEnum == Boolean.FALSE ? isEnum != Boolean.FALSE : isEnum(obj2) == Boolean.FALSE) {
            return IsEqual.apply(Scheme.applyToArgs.apply1(GetNamedPart.getNamedPart.apply2(obj, Lit18)), obj2) ? Boolean.TRUE : Boolean.FALSE;
        }
        Object isEnum2 = isEnum(obj);
        try {
            int i = ((isEnum2 != Boolean.FALSE ? 1 : 0) + 1) & 1;
            if (i == 0 ? i != 0 : isEnum(obj2) != Boolean.FALSE) {
                return IsEqual.apply(obj, Scheme.applyToArgs.apply1(GetNamedPart.getNamedPart.apply2(obj2, Lit18))) ? Boolean.TRUE : Boolean.FALSE;
            }
            Object asNumber = asNumber(obj);
            if (asNumber == Boolean.FALSE) {
                return asNumber;
            }
            Object asNumber2 = asNumber(obj2);
            return asNumber2 != Boolean.FALSE ? Scheme.numEqu.apply2(asNumber, asNumber2) : asNumber2;
        } catch (ClassCastException e) {
            throw new WrongType(e, "x", -2, isEnum2);
        }
    }

    public static Object asNumber(Object x) {
        Object nx = coerceToNumber(x);
        return nx == Lit2 ? Boolean.FALSE : nx;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isYailNotEqual(Object x1, Object x2) {
        return ((isYailEqual(x1, x2) != Boolean.FALSE ? 1 : 0) + 1) & 1;
    }

    public static Object processAndDelayed$V(Object[] argsArray) {
        LList delayed$Mnargs = LList.makeList(argsArray, 0);
        for (Object delayed$Mnargs2 = delayed$Mnargs; !lists.isNull(delayed$Mnargs2); delayed$Mnargs2 = lists.cdr.apply1(delayed$Mnargs2)) {
            Object conjunct = Scheme.applyToArgs.apply1(lists.car.apply1(delayed$Mnargs2));
            Object coerced$Mnconjunct = coerceToBoolean(conjunct);
            if (!isIsCoercible(coerced$Mnconjunct)) {
                return signalRuntimeError(strings.stringAppend("The AND operation cannot accept the argument ", getDisplayRepresentation(conjunct), " because it is neither true nor false"), strings.stringAppend("Bad argument to AND" instanceof Object[] ? (Object[]) "Bad argument to AND" : new Object[]{"Bad argument to AND"}));
            }
            if (coerced$Mnconjunct == Boolean.FALSE) {
                return coerced$Mnconjunct;
            }
        }
        return Boolean.TRUE;
    }

    public static Object processOrDelayed$V(Object[] argsArray) {
        LList delayed$Mnargs = LList.makeList(argsArray, 0);
        for (Object delayed$Mnargs2 = delayed$Mnargs; !lists.isNull(delayed$Mnargs2); delayed$Mnargs2 = lists.cdr.apply1(delayed$Mnargs2)) {
            Object disjunct = Scheme.applyToArgs.apply1(lists.car.apply1(delayed$Mnargs2));
            Object coerced$Mndisjunct = coerceToBoolean(disjunct);
            if (!isIsCoercible(coerced$Mndisjunct)) {
                return signalRuntimeError(strings.stringAppend("The OR operation cannot accept the argument ", getDisplayRepresentation(disjunct), " because it is neither true nor false"), strings.stringAppend("Bad argument to OR" instanceof Object[] ? (Object[]) "Bad argument to OR" : new Object[]{"Bad argument to OR"}));
            }
            if (coerced$Mndisjunct != Boolean.FALSE) {
                return coerced$Mndisjunct;
            }
        }
        return Boolean.FALSE;
    }

    public static Number yailFloor(Object x) {
        try {
            return Arithmetic.toExact(numbers.floor(LangObjType.coerceRealNum(x)));
        } catch (ClassCastException e) {
            throw new WrongType(e, "floor", 1, x);
        }
    }

    public static Number yailCeiling(Object x) {
        try {
            return Arithmetic.toExact(numbers.ceiling(LangObjType.coerceRealNum(x)));
        } catch (ClassCastException e) {
            throw new WrongType(e, "ceiling", 1, x);
        }
    }

    public static Number yailRound(Object x) {
        try {
            return Arithmetic.toExact(numbers.round(LangObjType.coerceRealNum(x)));
        } catch (ClassCastException e) {
            throw new WrongType(e, "round", 1, x);
        }
    }

    public static Object randomSetSeed(Object seed) {
        if (numbers.isNumber(seed)) {
            try {
                $Strandom$Mnnumber$Mngenerator$St.setSeed(((Number) seed).longValue());
                return Values.empty;
            } catch (ClassCastException e) {
                throw new WrongType(e, "java.util.Random.setSeed(long)", 2, seed);
            }
        }
        if (strings.isString(seed)) {
            return randomSetSeed(paddedString$To$Number(seed));
        }
        if (lists.isList(seed)) {
            return randomSetSeed(lists.car.apply1(seed));
        }
        if (Boolean.TRUE == seed) {
            return randomSetSeed(Lit24);
        }
        if (Boolean.FALSE == seed) {
            return randomSetSeed(Lit25);
        }
        return randomSetSeed(Lit25);
    }

    public static double randomFraction() {
        return $Strandom$Mnnumber$Mngenerator$St.nextDouble();
    }

    public static Object randomInteger(Object low, Object high) {
        try {
            Object low2 = numbers.ceiling(LangObjType.coerceRealNum(low));
            try {
                Object high2 = numbers.floor(LangObjType.coerceRealNum(high));
                while (Scheme.numGrt.apply2(low2, high2) != Boolean.FALSE) {
                    Object high3 = low2;
                    low2 = high2;
                    high2 = high3;
                }
                Object clow = ((Procedure) clip$Mnto$Mnjava$Mnint$Mnrange).apply1(low2);
                Object chigh = ((Procedure) clip$Mnto$Mnjava$Mnint$Mnrange).apply1(high2);
                AddOp addOp = AddOp.$Pl;
                Random random = $Strandom$Mnnumber$Mngenerator$St;
                Object apply2 = AddOp.$Pl.apply2(Lit24, AddOp.$Mn.apply2(chigh, clow));
                try {
                    Object apply22 = addOp.apply2(Integer.valueOf(random.nextInt(((Number) apply2).intValue())), clow);
                    try {
                        return Arithmetic.toExact((Number) apply22);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "inexact->exact", 1, apply22);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "java.util.Random.nextInt(int)", 2, apply2);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "floor", 1, high);
            }
        } catch (ClassCastException e4) {
            throw new WrongType(e4, "ceiling", 1, low);
        }
    }

    static Object lambda15(Object x) {
        return numbers.max(lowest, numbers.min(x, highest));
    }

    public static Object yailDivide(Object n, Object d) {
        NumberCompare numberCompare = Scheme.numEqu;
        IntNum intNum = Lit25;
        Object apply2 = numberCompare.apply2(d, intNum);
        try {
            boolean x = ((Boolean) apply2).booleanValue();
            if (!x ? x : Scheme.numEqu.apply2(n, intNum) != Boolean.FALSE) {
                signalRuntimeFormError("Division", ERROR_DIVISION_BY_ZERO, n);
                return n;
            }
            if (Scheme.numEqu.apply2(d, intNum) != Boolean.FALSE) {
                signalRuntimeFormError("Division", ERROR_DIVISION_BY_ZERO, n);
                Object apply22 = DivideOp.$Sl.apply2(n, d);
                try {
                    return Arithmetic.toInexact((Number) apply22);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "exact->inexact", 1, apply22);
                }
            }
            Object apply23 = DivideOp.$Sl.apply2(n, d);
            try {
                return Arithmetic.toInexact((Number) apply23);
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "exact->inexact", 1, apply23);
            }
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "x", -2, apply2);
        }
    }

    public static Object degrees$To$RadiansInternal(Object degrees) {
        return DivideOp.$Sl.apply2(MultiplyOp.$St.apply2(degrees, Lit28), Lit29);
    }

    public static Object radians$To$DegreesInternal(Object radians) {
        return DivideOp.$Sl.apply2(MultiplyOp.$St.apply2(radians, Lit29), Lit28);
    }

    public static Object degrees$To$Radians(Object degrees) {
        Object rads = DivideOp.modulo.apply2(degrees$To$RadiansInternal(degrees), Lit30);
        return Scheme.numGEq.apply2(rads, Lit28) != Boolean.FALSE ? AddOp.$Mn.apply2(rads, Lit31) : rads;
    }

    public static Object radians$To$Degrees(Object radians) {
        return DivideOp.modulo.apply2(radians$To$DegreesInternal(radians), Lit32);
    }

    public static Object sinDegrees(Object degrees) {
        NumberCompare numberCompare = Scheme.numEqu;
        DivideOp divideOp = DivideOp.modulo;
        IntNum intNum = Lit33;
        Object apply2 = divideOp.apply2(degrees, intNum);
        IntNum intNum2 = Lit25;
        if (numberCompare.apply2(apply2, intNum2) != Boolean.FALSE) {
            NumberCompare numberCompare2 = Scheme.numEqu;
            DivideOp divideOp2 = DivideOp.modulo;
            Object apply22 = DivideOp.$Sl.apply2(degrees, intNum);
            IntNum intNum3 = Lit26;
            return numberCompare2.apply2(divideOp2.apply2(apply22, intNum3), intNum2) != Boolean.FALSE ? intNum2 : Scheme.numEqu.apply2(DivideOp.modulo.apply2(DivideOp.$Sl.apply2(AddOp.$Mn.apply2(degrees, intNum), Lit29), intNum3), intNum2) != Boolean.FALSE ? Lit24 : Lit34;
        }
        Object degrees$To$RadiansInternal = degrees$To$RadiansInternal(degrees);
        try {
            return Double.valueOf(numbers.sin(((Number) degrees$To$RadiansInternal).doubleValue()));
        } catch (ClassCastException e) {
            throw new WrongType(e, "sin", 1, degrees$To$RadiansInternal);
        }
    }

    public static Object cosDegrees(Object degrees) {
        NumberCompare numberCompare = Scheme.numEqu;
        DivideOp divideOp = DivideOp.modulo;
        IntNum intNum = Lit33;
        Object apply2 = divideOp.apply2(degrees, intNum);
        IntNum intNum2 = Lit25;
        if (numberCompare.apply2(apply2, intNum2) != Boolean.FALSE) {
            NumberCompare numberCompare2 = Scheme.numEqu;
            DivideOp divideOp2 = DivideOp.modulo;
            Object apply22 = DivideOp.$Sl.apply2(degrees, intNum);
            IntNum intNum3 = Lit26;
            Object apply23 = divideOp2.apply2(apply22, intNum3);
            IntNum intNum4 = Lit24;
            return numberCompare2.apply2(apply23, intNum4) != Boolean.FALSE ? intNum2 : Scheme.numEqu.apply2(DivideOp.modulo.apply2(DivideOp.$Sl.apply2(degrees, Lit29), intNum3), intNum4) != Boolean.FALSE ? Lit34 : intNum4;
        }
        Object degrees$To$RadiansInternal = degrees$To$RadiansInternal(degrees);
        try {
            return Double.valueOf(numbers.cos(((Number) degrees$To$RadiansInternal).doubleValue()));
        } catch (ClassCastException e) {
            throw new WrongType(e, "cos", 1, degrees$To$RadiansInternal);
        }
    }

    public static Object tanDegrees(Object degrees) {
        NumberCompare numberCompare = Scheme.numEqu;
        Object apply2 = DivideOp.modulo.apply2(degrees, Lit29);
        IntNum intNum = Lit25;
        if (numberCompare.apply2(apply2, intNum) != Boolean.FALSE) {
            return intNum;
        }
        NumberCompare numberCompare2 = Scheme.numEqu;
        DivideOp divideOp = DivideOp.modulo;
        AddOp addOp = AddOp.$Mn;
        IntNum intNum2 = Lit35;
        Object apply22 = addOp.apply2(degrees, intNum2);
        IntNum intNum3 = Lit33;
        if (numberCompare2.apply2(divideOp.apply2(apply22, intNum3), intNum) != Boolean.FALSE) {
            return Scheme.numEqu.apply2(DivideOp.modulo.apply2(DivideOp.$Sl.apply2(AddOp.$Mn.apply2(degrees, intNum2), intNum3), Lit26), intNum) != Boolean.FALSE ? Lit24 : Lit34;
        }
        Object degrees$To$RadiansInternal = degrees$To$RadiansInternal(degrees);
        try {
            return Double.valueOf(numbers.tan(((Number) degrees$To$RadiansInternal).doubleValue()));
        } catch (ClassCastException e) {
            throw new WrongType(e, "tan", 1, degrees$To$RadiansInternal);
        }
    }

    public static Object asinDegrees(Object y) {
        try {
            return radians$To$DegreesInternal(Double.valueOf(numbers.asin(((Number) y).doubleValue())));
        } catch (ClassCastException e) {
            throw new WrongType(e, "asin", 1, y);
        }
    }

    public static Object acosDegrees(Object y) {
        try {
            return radians$To$DegreesInternal(Double.valueOf(numbers.acos(((Number) y).doubleValue())));
        } catch (ClassCastException e) {
            throw new WrongType(e, "acos", 1, y);
        }
    }

    public static Object atanDegrees(Object ratio) {
        return radians$To$DegreesInternal(numbers.atan.apply1(ratio));
    }

    public static Object atan2Degrees(Object y, Object x) {
        return radians$To$DegreesInternal(numbers.atan.apply2(y, x));
    }

    public static String stringToUpperCase(Object s) {
        return s.toString().toUpperCase();
    }

    public static String stringToLowerCase(Object s) {
        return s.toString().toLowerCase();
    }

    public static LList unicodeString$To$List(CharSequence str) {
        LList result = LList.Empty;
        int i = strings.stringLength(str);
        while (true) {
            i--;
            if (i >= 0) {
                boolean x = i >= 1;
                if (!x) {
                    if (x) {
                        Pair pair = new Pair(Char.make(strings.stringRef(str, i)), new Pair(Char.make(strings.stringRef(str, i - 1)), result));
                        i--;
                        result = pair;
                    } else {
                        result = new Pair(Char.make(strings.stringRef(str, i)), result);
                    }
                } else {
                    char c = strings.stringRef(str, i);
                    char c1 = strings.stringRef(str, i - 1);
                    boolean x2 = characters.isChar$Gr$Eq(Char.make(c), Lit36);
                    if (x2) {
                        boolean x3 = characters.isChar$Ls$Eq(Char.make(c), Lit37);
                        if (x3) {
                            boolean x4 = characters.isChar$Gr$Eq(Char.make(c1), Lit38);
                            if (x4) {
                                if (characters.isChar$Ls$Eq(Char.make(c1), Lit39)) {
                                    Pair pair2 = new Pair(Char.make(strings.stringRef(str, i)), new Pair(Char.make(strings.stringRef(str, i - 1)), result));
                                    i--;
                                    result = pair2;
                                } else {
                                    result = new Pair(Char.make(strings.stringRef(str, i)), result);
                                }
                            } else if (x4) {
                                Pair pair22 = new Pair(Char.make(strings.stringRef(str, i)), new Pair(Char.make(strings.stringRef(str, i - 1)), result));
                                i--;
                                result = pair22;
                            } else {
                                result = new Pair(Char.make(strings.stringRef(str, i)), result);
                            }
                        } else if (x3) {
                            Pair pair222 = new Pair(Char.make(strings.stringRef(str, i)), new Pair(Char.make(strings.stringRef(str, i - 1)), result));
                            i--;
                            result = pair222;
                        } else {
                            result = new Pair(Char.make(strings.stringRef(str, i)), result);
                        }
                    } else if (x2) {
                        Pair pair2222 = new Pair(Char.make(strings.stringRef(str, i)), new Pair(Char.make(strings.stringRef(str, i - 1)), result));
                        i--;
                        result = pair2222;
                    } else {
                        result = new Pair(Char.make(strings.stringRef(str, i)), result);
                    }
                }
            } else {
                return result;
            }
        }
    }

    public static CharSequence stringReverse(Object s) {
        try {
            return strings.list$To$String(lists.reverse(unicodeString$To$List((CharSequence) s)));
        } catch (ClassCastException e) {
            throw new WrongType(e, "unicode-string->list", 0, s);
        }
    }

    public static Object formatAsDecimal(Object number, Object places) {
        NumberCompare numberCompare = Scheme.numEqu;
        IntNum intNum = Lit25;
        if (numberCompare.apply2(places, intNum) != Boolean.FALSE) {
            return yailRound(number);
        }
        boolean x = numbers.isInteger(places);
        if (!x ? x : Scheme.numGrt.apply2(places, intNum) != Boolean.FALSE) {
            return signalRuntimeError(strings.stringAppend("format-as-decimal was called with ", getDisplayRepresentation(places), " as the number of decimal places.  This number must be a non-negative integer."), strings.stringAppend("Bad number of decimal places for format as decimal" instanceof Object[] ? (Object[]) "Bad number of decimal places for format as decimal" : new Object[]{"Bad number of decimal places for format as decimal"}));
        }
        return Format.formatToString(0, strings.stringAppend("~,", appinventorNumber$To$String(places), "f"), number);
    }

    public static Boolean isIsNumber(Object arg) {
        boolean x;
        boolean x2 = numbers.isNumber(arg);
        return (!x2 ? !(!(x = strings.isString(arg)) ? x : paddedString$To$Number(arg) != Boolean.FALSE) : x2) ? Boolean.FALSE : Boolean.TRUE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isIsBase10(Object arg) {
        try {
            boolean x = Pattern.matches("[0123456789]*", (CharSequence) arg);
            if (x) {
                return ((isStringEmpty(arg) != Boolean.FALSE ? 1 : 0) + 1) & 1;
            }
            return x;
        } catch (ClassCastException e) {
            throw new WrongType(e, "java.util.regex.Pattern.matches(java.lang.String,java.lang.CharSequence)", 2, arg);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isIsHexadecimal(Object arg) {
        try {
            boolean x = Pattern.matches("[0-9a-fA-F]*", (CharSequence) arg);
            if (x) {
                return ((isStringEmpty(arg) != Boolean.FALSE ? 1 : 0) + 1) & 1;
            }
            return x;
        } catch (ClassCastException e) {
            throw new WrongType(e, "java.util.regex.Pattern.matches(java.lang.String,java.lang.CharSequence)", 2, arg);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isIsBinary(Object arg) {
        try {
            boolean x = Pattern.matches("[01]*", (CharSequence) arg);
            if (x) {
                return ((isStringEmpty(arg) != Boolean.FALSE ? 1 : 0) + 1) & 1;
            }
            return x;
        } catch (ClassCastException e) {
            throw new WrongType(e, "java.util.regex.Pattern.matches(java.lang.String,java.lang.CharSequence)", 2, arg);
        }
    }

    public static Object mathConvertDecHex(Object x) {
        if (!isIsBase10(x)) {
            return signalRuntimeError(Format.formatToString(0, "Convert base 10 to hex: '~A' is not a positive integer", getDisplayRepresentation(x)), "Argument is not a positive integer");
        }
        try {
            Object string$To$Number = numbers.string$To$Number((CharSequence) x, 10);
            try {
                return stringToUpperCase(numbers.number$To$String((Number) string$To$Number, 16));
            } catch (ClassCastException e) {
                throw new WrongType(e, "number->string", 1, string$To$Number);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "string->number", 1, x);
        }
    }

    public static Object mathConvertHexDec(Object x) {
        return isIsHexadecimal(x) ? numbers.string$To$Number(stringToUpperCase(x), 16) : signalRuntimeError(Format.formatToString(0, "Convert hex to base 10: '~A' is not a hexadecimal number", getDisplayRepresentation(x)), "Invalid hexadecimal number");
    }

    public static Object mathConvertBinDec(Object x) {
        if (!isIsBinary(x)) {
            return signalRuntimeError(Format.formatToString(0, "Convert binary to base 10: '~A' is not a  binary number", getDisplayRepresentation(x)), "Invalid binary number");
        }
        try {
            return numbers.string$To$Number((CharSequence) x, 2);
        } catch (ClassCastException e) {
            throw new WrongType(e, "string->number", 1, x);
        }
    }

    public static Object mathConvertDecBin(Object x) {
        if (!isIsBase10(x)) {
            return signalRuntimeError(Format.formatToString(0, "Convert base 10 to binary: '~A' is not a positive integer", getDisplayRepresentation(x)), "Argument is not a positive integer");
        }
        try {
            return patchedNumber$To$StringBinary(numbers.string$To$Number((CharSequence) x, 10));
        } catch (ClassCastException e) {
            throw new WrongType(e, "string->number", 1, x);
        }
    }

    public static Object patchedNumber$To$StringBinary(Object x) {
        try {
            if (Scheme.numLss.apply2(numbers.abs((Number) x), Lit40) != Boolean.FALSE) {
                try {
                    return numbers.number$To$String((Number) x, 2);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "number->string", 1, x);
                }
            }
            return alternateNumber$To$StringBinary(x);
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "abs", 1, x);
        }
    }

    public static Object alternateNumber$To$StringBinary(Object x) {
        try {
            Number abs = numbers.abs((Number) x);
            try {
                RealNum clean$Mnx = numbers.floor(LangObjType.coerceRealNum(abs));
                Object converted$Mnclean$Mnx = internalBinaryConvert(clean$Mnx);
                return clean$Mnx.doubleValue() >= 0.0d ? converted$Mnclean$Mnx : strings.stringAppend("-", converted$Mnclean$Mnx);
            } catch (ClassCastException e) {
                throw new WrongType(e, "floor", 1, abs);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "abs", 1, x);
        }
    }

    public static Object internalBinaryConvert(Object x) {
        if (Scheme.numEqu.apply2(x, Lit25) != Boolean.FALSE) {
            return Component.TYPEFACE_DEFAULT;
        }
        if (Scheme.numEqu.apply2(x, Lit24) != Boolean.FALSE) {
            return Component.TYPEFACE_SANSSERIF;
        }
        DivideOp divideOp = DivideOp.quotient;
        IntNum intNum = Lit26;
        return strings.stringAppend(internalBinaryConvert(divideOp.apply2(x, intNum)), internalBinaryConvert(DivideOp.remainder.apply2(x, intNum)));
    }

    public static Object avg(Object l) {
        Object l$Mncontent = yailListContents(l);
        if (lists.isNull(l$Mncontent)) {
            return Lit25;
        }
        try {
            return yailDivide(Scheme.apply.apply2(AddOp.$Pl, l$Mncontent), Integer.valueOf(lists.length((LList) l$Mncontent)));
        } catch (ClassCastException e) {
            throw new WrongType(e, PropertyTypeConstants.PROPERTY_TYPE_LENGTH, 1, l$Mncontent);
        }
    }

    public static Object yailMul(Object yail$Mnlist$Mncontents2) {
        return lists.isNull(yail$Mnlist$Mncontents2) ? Lit25 : Scheme.apply.apply2(MultiplyOp.$St, yail$Mnlist$Mncontents2);
    }

    public static Object gm(Object l) {
        Object l$Mncontent = yailListContents(l);
        if (lists.isNull(l$Mncontent)) {
            return Lit25;
        }
        try {
            return expt.expt(yailMul(l$Mncontent), yailDivide(Lit24, Integer.valueOf(lists.length((LList) l$Mncontent))));
        } catch (ClassCastException e) {
            throw new WrongType(e, PropertyTypeConstants.PROPERTY_TYPE_LENGTH, 1, l$Mncontent);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x0066, code lost:
    
        if (r6 != false) goto L26;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object mode(java.lang.Object r9) {
        /*
            Method dump skipped, instructions count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.youngandroid.runtime.mode(java.lang.Object):java.lang.Object");
    }

    public static Object maxl(Object l) {
        Object l$Mncontent = yailListContents(l);
        return lists.isNull(l$Mncontent) ? Lit41 : Scheme.apply.apply2(numbers.max, l$Mncontent);
    }

    public static Object minl(Object l) {
        Object l$Mncontent = yailListContents(l);
        return lists.isNull(l$Mncontent) ? Lit42 : Scheme.apply.apply2(numbers.min, l$Mncontent);
    }

    public static Object mean(Object l$Mncontent) {
        try {
            return yailDivide(Scheme.apply.apply2(AddOp.$Pl, l$Mncontent), Integer.valueOf(lists.length((LList) l$Mncontent)));
        } catch (ClassCastException e) {
            throw new WrongType(e, PropertyTypeConstants.PROPERTY_TYPE_LENGTH, 1, l$Mncontent);
        }
    }

    public static Object sumMeanSquareDiff(Object lst, Object av) {
        return lists.isNull(lst) ? Lit25 : AddOp.$Pl.apply2(MultiplyOp.$St.apply2(AddOp.$Mn.apply2(lists.car.apply1(lst), av), AddOp.$Mn.apply2(lists.car.apply1(lst), av)), sumMeanSquareDiff(lists.cdr.apply1(lst), av));
    }

    public static Object stdDev(Object l) {
        Object lst = yailListContents(l);
        try {
            if (lists.length((LList) lst) <= 1) {
                return signalRuntimeError(Format.formatToString(0, "Select list item: Attempt to get item number ~A, of the list ~A.  The minimum valid item number is 2.", getDisplayRepresentation(lst)), "List smaller than 2");
            }
            try {
                return numbers.sqrt.apply1(yailDivide(sumMeanSquareDiff(lst, mean(lst)), Integer.valueOf(lists.length((LList) lst))));
            } catch (ClassCastException e) {
                throw new WrongType(e, PropertyTypeConstants.PROPERTY_TYPE_LENGTH, 1, lst);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, PropertyTypeConstants.PROPERTY_TYPE_LENGTH, 1, lst);
        }
    }

    public static Object sampleStdDev(Object lst) {
        try {
            return numbers.sqrt.apply1(yailDivide(sumMeanSquareDiff(lst, mean(lst)), Integer.valueOf(lists.length((LList) lst) - 1)));
        } catch (ClassCastException e) {
            throw new WrongType(e, PropertyTypeConstants.PROPERTY_TYPE_LENGTH, 1, lst);
        }
    }

    public static Object stdErr(Object l) {
        Object lst = yailListContents(l);
        try {
            if (lists.length((LList) lst) <= 1) {
                return signalRuntimeError(Format.formatToString(0, "Select list item: Attempt to get item number ~A, of the list ~A.  The minimum valid item number is 2.", getDisplayRepresentation(lst)), "List smaller than 2");
            }
            try {
                return yailDivide(sampleStdDev(lst), numbers.sqrt.apply1(Integer.valueOf(lists.length((LList) lst))));
            } catch (ClassCastException e) {
                throw new WrongType(e, PropertyTypeConstants.PROPERTY_TYPE_LENGTH, 1, lst);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, PropertyTypeConstants.PROPERTY_TYPE_LENGTH, 1, lst);
        }
    }

    public static Object isYailList(Object x) {
        Object x2 = isYailListCandidate(x);
        if (x2 != Boolean.FALSE) {
            return x instanceof YailList ? Boolean.TRUE : Boolean.FALSE;
        }
        return x2;
    }

    public static Object isYailListCandidate(Object x) {
        boolean x2 = lists.isPair(x);
        return x2 ? IsEqual.apply(lists.car.apply1(x), Lit43) ? Boolean.TRUE : Boolean.FALSE : x2 ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object yailListContents(Object yail$Mnlist) {
        return lists.cdr.apply1(yail$Mnlist);
    }

    public static void setYailListContents$Ex(Object yail$Mnlist, Object contents) {
        try {
            ((Pair) yail$Mnlist).setCdr(contents);
        } catch (ClassCastException e) {
            throw new WrongType(e, "set-cdr!", 1, yail$Mnlist);
        }
    }

    public static Object insertYailListHeader(Object x) {
        return Invoke.invokeStatic.apply3(YailList, Lit44, x);
    }

    public static Object kawaList$To$YailList(Object x) {
        if (lists.isNull(x)) {
            return new YailList();
        }
        if (!lists.isPair(x)) {
            return sanitizeAtomic(x);
        }
        if (isYailList(x) != Boolean.FALSE) {
            return x;
        }
        Object result = LList.Empty;
        Object arg0 = x;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                arg0 = arg02.getCdr();
                result = Pair.make(kawaList$To$YailList(arg02.getCar()), result);
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        return YailList.makeList((List) LList.reverseInPlace(result));
    }

    public static Object yailList$To$KawaList(Object data) {
        if (isYailList(data) == Boolean.FALSE) {
            return data;
        }
        Object arg0 = yailListContents(data);
        Object result = LList.Empty;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                arg0 = arg02.getCdr();
                result = Pair.make(yailList$To$KawaList(arg02.getCar()), result);
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        Object arg03 = LList.reverseInPlace(result);
        return arg03;
    }

    public static Object isYailListEmpty(Object x) {
        Object x2 = isYailList(x);
        if (x2 != Boolean.FALSE) {
            return lists.isNull(yailListContents(x)) ? Boolean.TRUE : Boolean.FALSE;
        }
        return x2;
    }

    public static YailList makeYailList$V(Object[] argsArray) {
        LList args = LList.makeList(argsArray, 0);
        return YailList.makeList((List) args);
    }

    public static Object yailListCopy(Object yl) {
        if (isYailListEmpty(yl) != Boolean.FALSE) {
            return new YailList();
        }
        if (!lists.isPair(yl)) {
            return yl;
        }
        Object arg0 = yailListContents(yl);
        Object result = LList.Empty;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                arg0 = arg02.getCdr();
                result = Pair.make(yailListCopy(arg02.getCar()), result);
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        return YailList.makeList((List) LList.reverseInPlace(result));
    }

    public static Object yailListReverse(Object yl) {
        if (isYailList(yl) == Boolean.FALSE) {
            return signalRuntimeError("Argument value to \"reverse list\" must be a list", "Expecting list");
        }
        Object yailListContents = yailListContents(yl);
        try {
            return insertYailListHeader(lists.reverse((LList) yailListContents));
        } catch (ClassCastException e) {
            throw new WrongType(e, "reverse", 1, yailListContents);
        }
    }

    public static Object yailListToCsvTable(Object yl) {
        if (isYailList(yl) == Boolean.FALSE) {
            return signalRuntimeError("Argument value to \"list to csv table\" must be a list", "Expecting list");
        }
        Apply apply = Scheme.apply;
        ModuleMethod moduleMethod = make$Mnyail$Mnlist;
        Object arg0 = yailListContents(yl);
        Object result = LList.Empty;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                arg0 = arg02.getCdr();
                result = Pair.make(convertToStringsForCsv(arg02.getCar()), result);
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        Object arg03 = LList.reverseInPlace(result);
        Object apply2 = apply.apply2(moduleMethod, arg03);
        try {
            return CsvUtil.toCsvTable((YailList) apply2);
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "com.google.appinventor.components.runtime.util.CsvUtil.toCsvTable(com.google.appinventor.components.runtime.util.YailList)", 1, apply2);
        }
    }

    public static Object yailListToCsvRow(Object yl) {
        if (isYailList(yl) == Boolean.FALSE) {
            return signalRuntimeError("Argument value to \"list to csv row\" must be a list", "Expecting list");
        }
        Object convertToStringsForCsv = convertToStringsForCsv(yl);
        try {
            return CsvUtil.toCsvRow((YailList) convertToStringsForCsv);
        } catch (ClassCastException e) {
            throw new WrongType(e, "com.google.appinventor.components.runtime.util.CsvUtil.toCsvRow(com.google.appinventor.components.runtime.util.YailList)", 1, convertToStringsForCsv);
        }
    }

    public static Object convertToStringsForCsv(Object yl) {
        if (isYailListEmpty(yl) != Boolean.FALSE) {
            return yl;
        }
        if (isYailList(yl) == Boolean.FALSE) {
            return makeYailList$V(new Object[]{yl});
        }
        Apply apply = Scheme.apply;
        ModuleMethod moduleMethod = make$Mnyail$Mnlist;
        Object arg0 = yailListContents(yl);
        Object result = LList.Empty;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                arg0 = arg02.getCdr();
                result = Pair.make(coerceToString(arg02.getCar()), result);
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        Object arg03 = LList.reverseInPlace(result);
        return apply.apply2(moduleMethod, arg03);
    }

    public static Object yailListFromCsvTable(Object str) {
        String obj;
        if (str == null) {
            obj = null;
        } else {
            try {
                obj = str.toString();
            } catch (Exception exception) {
                return signalRuntimeError("Cannot parse text argument to \"list from csv table\" as a CSV-formatted table", exception.getMessage());
            }
        }
        return CsvUtil.fromCsvTable(obj);
    }

    public static Object yailListFromCsvRow(Object str) {
        String obj;
        if (str == null) {
            obj = null;
        } else {
            try {
                obj = str.toString();
            } catch (Exception exception) {
                return signalRuntimeError("Cannot parse text argument to \"list from csv row\" as CSV-formatted row", exception.getMessage());
            }
        }
        return CsvUtil.fromCsvRow(obj);
    }

    public static int yailListLength(Object yail$Mnlist) {
        Object yailListContents = yailListContents(yail$Mnlist);
        try {
            return lists.length((LList) yailListContents);
        } catch (ClassCastException e) {
            throw new WrongType(e, PropertyTypeConstants.PROPERTY_TYPE_LENGTH, 1, yailListContents);
        }
    }

    public static Object yailListIndex(Object object, Object yail$Mnlist) {
        Object i = Lit24;
        for (Object list = yailListContents(yail$Mnlist); !lists.isNull(list); list = lists.cdr.apply1(list)) {
            if (isYailEqual(object, lists.car.apply1(list)) != Boolean.FALSE) {
                return i;
            }
            i = AddOp.$Pl.apply2(i, Lit24);
        }
        Object i2 = Lit25;
        return i2;
    }

    public static Object yailListGetItem(Object yail$Mnlist, Object index) {
        NumberCompare numberCompare = Scheme.numLss;
        IntNum intNum = Lit24;
        if (numberCompare.apply2(index, intNum) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Select list item: Attempt to get item number ~A, of the list ~A.  The minimum valid item number is 1.", index, getDisplayRepresentation(yail$Mnlist)), "List index smaller than 1");
        }
        int len = yailListLength(yail$Mnlist);
        if (Scheme.numGrt.apply2(index, Integer.valueOf(len)) != Boolean.FALSE) {
            return signalRuntimeError(Format.formatToString(0, "Select list item: Attempt to get item number ~A of a list of length ~A: ~A", index, Integer.valueOf(len), getDisplayRepresentation(yail$Mnlist)), "Select list item: List index too large");
        }
        Object yailListContents = yailListContents(yail$Mnlist);
        Object apply2 = AddOp.$Mn.apply2(index, intNum);
        try {
            return lists.listRef(yailListContents, ((Number) apply2).intValue());
        } catch (ClassCastException e) {
            throw new WrongType(e, "list-ref", 2, apply2);
        }
    }

    public static void yailListSetItem$Ex(Object yail$Mnlist, Object index, Object value) {
        NumberCompare numberCompare = Scheme.numLss;
        IntNum intNum = Lit24;
        if (numberCompare.apply2(index, intNum) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Replace list item: Attempt to replace item number ~A of the list ~A.  The minimum valid item number is 1.", index, getDisplayRepresentation(yail$Mnlist)), "List index smaller than 1");
        }
        int len = yailListLength(yail$Mnlist);
        if (Scheme.numGrt.apply2(index, Integer.valueOf(len)) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Replace list item: Attempt to replace item number ~A of a list of length ~A: ~A", index, Integer.valueOf(len), getDisplayRepresentation(yail$Mnlist)), "List index too large");
        }
        Object yailListContents = yailListContents(yail$Mnlist);
        Object apply2 = AddOp.$Mn.apply2(index, intNum);
        try {
            Object listTail = lists.listTail(yailListContents, ((Number) apply2).intValue());
            try {
                ((Pair) listTail).setCar(value);
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-car!", 1, listTail);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "list-tail", 2, apply2);
        }
    }

    public static void yailListRemoveItem$Ex(Object yail$Mnlist, Object index) {
        Object index2 = coerceToNumber(index);
        if (index2 == Lit2) {
            signalRuntimeError(Format.formatToString(0, "Remove list item: index -- ~A -- is not a number", getDisplayRepresentation(index)), "Bad list index");
        }
        if (isYailListEmpty(yail$Mnlist) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Remove list item: Attempt to remove item ~A of an empty list", getDisplayRepresentation(index)), "Invalid list operation");
        }
        NumberCompare numberCompare = Scheme.numLss;
        IntNum intNum = Lit24;
        if (numberCompare.apply2(index2, intNum) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Remove list item: Attempt to remove item ~A of the list ~A.  The minimum valid item number is 1.", index2, getDisplayRepresentation(yail$Mnlist)), "List index smaller than 1");
        }
        int len = yailListLength(yail$Mnlist);
        if (Scheme.numGrt.apply2(index2, Integer.valueOf(len)) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Remove list item: Attempt to remove item ~A of a list of length ~A: ~A", index2, Integer.valueOf(len), getDisplayRepresentation(yail$Mnlist)), "List index too large");
        }
        Object apply2 = AddOp.$Mn.apply2(index2, intNum);
        try {
            Object pair$Mnpointing$Mnto$Mndeletion = lists.listTail(yail$Mnlist, ((Number) apply2).intValue());
            try {
                ((Pair) pair$Mnpointing$Mnto$Mndeletion).setCdr(lists.cddr.apply1(pair$Mnpointing$Mnto$Mndeletion));
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-cdr!", 1, pair$Mnpointing$Mnto$Mndeletion);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "list-tail", 2, apply2);
        }
    }

    public static void yailListInsertItem$Ex(Object yail$Mnlist, Object index, Object item) {
        Object index2 = coerceToNumber(index);
        if (index2 == Lit2) {
            signalRuntimeError(Format.formatToString(0, "Insert list item: index (~A) is not a number", getDisplayRepresentation(index)), "Bad list index");
        }
        NumberCompare numberCompare = Scheme.numLss;
        IntNum intNum = Lit24;
        if (numberCompare.apply2(index2, intNum) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Insert list item: Attempt to insert item ~A into the list ~A.  The minimum valid item number is 1.", index2, getDisplayRepresentation(yail$Mnlist)), "List index smaller than 1");
        }
        int len$Pl1 = yailListLength(yail$Mnlist) + 1;
        if (Scheme.numGrt.apply2(index2, Integer.valueOf(len$Pl1)) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Insert list item: Attempt to insert item ~A into the list ~A.  The maximum valid item number is ~A.", index2, getDisplayRepresentation(yail$Mnlist), Integer.valueOf(len$Pl1)), "List index too large");
        }
        Object contents = yailListContents(yail$Mnlist);
        if (Scheme.numEqu.apply2(index2, intNum) != Boolean.FALSE) {
            setYailListContents$Ex(yail$Mnlist, lists.cons(item, contents));
            return;
        }
        Object apply2 = AddOp.$Mn.apply2(index2, Lit26);
        try {
            Object at$Mnitem = lists.listTail(contents, ((Number) apply2).intValue());
            try {
                ((Pair) at$Mnitem).setCdr(lists.cons(item, lists.cdr.apply1(at$Mnitem)));
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-cdr!", 1, at$Mnitem);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "list-tail", 2, apply2);
        }
    }

    public static void yailListAppend$Ex(Object yail$Mnlist$MnA, Object yail$Mnlist$MnB) {
        Object yailListContents = yailListContents(yail$Mnlist$MnA);
        try {
            Object listTail = lists.listTail(yail$Mnlist$MnA, lists.length((LList) yailListContents));
            try {
                ((Pair) listTail).setCdr(lambda16listCopy(yailListContents(yail$Mnlist$MnB)));
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-cdr!", 1, listTail);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, PropertyTypeConstants.PROPERTY_TYPE_LENGTH, 1, yailListContents);
        }
    }

    public static Object lambda16listCopy(Object l) {
        return lists.isNull(l) ? LList.Empty : lists.cons(lists.car.apply1(l), lambda16listCopy(lists.cdr.apply1(l)));
    }

    public static void yailListAddToList$Ex$V(Object yail$Mnlist, Object[] argsArray) {
        LList items = LList.makeList(argsArray, 0);
        yailListAppend$Ex(yail$Mnlist, Scheme.apply.apply2(make$Mnyail$Mnlist, items));
    }

    public static Boolean isYailListMember(Object object, Object yail$Mnlist) {
        Object result = lists.member(object, yailListContents(yail$Mnlist), yail$Mnequal$Qu);
        return result != Boolean.FALSE ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object yailListPickRandom(Object yail$Mnlist) {
        if (isYailListEmpty(yail$Mnlist) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Pick random item: Attempt to pick a random element from an empty list" instanceof Object[] ? (Object[]) "Pick random item: Attempt to pick a random element from an empty list" : new Object[]{"Pick random item: Attempt to pick a random element from an empty list"}), "Invalid list operation");
        }
        return yailListGetItem(yail$Mnlist, randomInteger(Lit24, Integer.valueOf(yailListLength(yail$Mnlist))));
    }

    public static Object yailForEach(Object proc, Object yail$Mnlist) {
        Object verified$Mnlist = coerceToYailList(yail$Mnlist);
        if (verified$Mnlist == Lit2) {
            return signalRuntimeError(Format.formatToString(0, "The second argument to foreach is not a list.  The second argument is: ~A", getDisplayRepresentation(yail$Mnlist)), "Bad list argument to foreach");
        }
        Object arg0 = yailListContents(verified$Mnlist);
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                Scheme.applyToArgs.apply2(proc, arg02.getCar());
                arg0 = arg02.getCdr();
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        return null;
    }

    public static Object yailListMap(Object proc, Object yail$Mnlist) {
        Object verified$Mnlist = coerceToYailList(yail$Mnlist);
        if (verified$Mnlist == Lit2) {
            return signalRuntimeError(Format.formatToString(0, "The second argument to map is not a list.  The second argument is: ~A", getDisplayRepresentation(yail$Mnlist)), "Bad list argument to map");
        }
        Object arg0 = yailListContents(verified$Mnlist);
        Object result = LList.Empty;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                arg0 = arg02.getCdr();
                result = Pair.make(Scheme.applyToArgs.apply2(proc, arg02.getCar()), result);
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        return kawaList$To$YailList(LList.reverseInPlace(result));
    }

    public static Object yailListFilter(Object pred, Object yail$Mnlist) {
        Object verified$Mnlist = coerceToYailList(yail$Mnlist);
        return verified$Mnlist == Lit2 ? signalRuntimeError(Format.formatToString(0, "The second argument to filter is not a list.  The second argument is: ~A", getDisplayRepresentation(yail$Mnlist)), "Bad list argument to filter") : kawaList$To$YailList(lambda17filter_(pred, yailListContents(verified$Mnlist)));
    }

    public static Object lambda17filter_(Object pred, Object lst) {
        return lists.isNull(lst) ? LList.Empty : Scheme.applyToArgs.apply2(pred, lists.car.apply1(lst)) != Boolean.FALSE ? lists.cons(lists.car.apply1(lst), lambda17filter_(pred, lists.cdr.apply1(lst))) : lambda17filter_(pred, lists.cdr.apply1(lst));
    }

    public static Object yailListReduce(Object ans, Object binop, Object yail$Mnlist) {
        Object verified$Mnlist = coerceToYailList(yail$Mnlist);
        if (verified$Mnlist == Lit2) {
            return signalRuntimeError(Format.formatToString(0, "The second argument to reduce is not a list.  The second argument is: ~A", getDisplayRepresentation(yail$Mnlist)), "Bad list argument to reduce");
        }
        Object accum = ans;
        for (Object lst = yailListContents(verified$Mnlist); !lists.isNull(lst); lst = lists.cdr.apply1(lst)) {
            accum = Scheme.applyToArgs.apply3(binop, accum, lists.car.apply1(lst));
        }
        Object lst2 = kawaList$To$YailList(accum);
        return lst2;
    }

    public static Object typeof(Object val) {
        return misc.isBoolean(val) ? Lit7 : numbers.isNumber(val) ? Lit5 : strings.isString(val) ? Lit6 : isYailList(val) != Boolean.FALSE ? Lit8 : val instanceof Component ? Lit11 : signalRuntimeError(Format.formatToString(0, "typeof called with unexpected value: ~A", getDisplayRepresentation(val)), "Bad arguement to typeof");
    }

    public static Object indexof(Object element, Object lst) {
        return yailListIndex(element, lst);
    }

    public static boolean isTypeLt(Object type1, Object type2) {
        NumberCompare numberCompare = Scheme.numLss;
        PairWithPosition pairWithPosition = Lit45;
        return ((Boolean) numberCompare.apply2(indexof(type1, pairWithPosition), indexof(type2, pairWithPosition))).booleanValue();
    }

    public static Object isIsLt(Object val1, Object val2) {
        Object isComponentLt;
        Object type1 = typeof(val1);
        Object type2 = typeof(val2);
        boolean x = isTypeLt(type1, type2);
        if (x) {
            return x ? Boolean.TRUE : Boolean.FALSE;
        }
        boolean x2 = type1 == type2;
        if (!x2) {
            return x2 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (type1 == Lit7) {
            isComponentLt = isBooleanLt(val1, val2);
        } else if (type1 == Lit5) {
            isComponentLt = Scheme.numLss.apply2(val1, val2);
        } else if (type1 == Lit6) {
            isComponentLt = strings.isString$Ls(val1, val2) ? Boolean.TRUE : Boolean.FALSE;
        } else if (type1 == Lit8) {
            isComponentLt = isListLt(val1, val2);
        } else {
            isComponentLt = type1 == Lit11 ? isComponentLt(val1, val2) : signalRuntimeError(Format.formatToString(0, "(islt? ~A ~A)", getDisplayRepresentation(val1), getDisplayRepresentation(val2)), "Shouldn't happen");
        }
        return isComponentLt;
    }

    public static Object isIsEq(Object val1, Object val2) {
        Object type1 = typeof(val1);
        Object type2 = typeof(val2);
        boolean x = type1 == type2;
        if (!x) {
            return x ? Boolean.TRUE : Boolean.FALSE;
        }
        if (type1 == Lit7) {
            return isBooleanEq(val1, val2);
        }
        if (type1 == Lit5) {
            return Scheme.numEqu.apply2(val1, val2);
        }
        if (type1 == Lit6) {
            return strings.isString$Eq(val1, val2) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (type1 == Lit8) {
            return isListEq(val1, val2);
        }
        return type1 == Lit11 ? isComponentEq(val1, val2) : signalRuntimeError(Format.formatToString(0, "(islt? ~A ~A)", getDisplayRepresentation(val1), getDisplayRepresentation(val2)), "Shouldn't happen");
    }

    public static Object isIsLeq(Object val1, Object val2) {
        Object isComponentLeq;
        Object type1 = typeof(val1);
        Object type2 = typeof(val2);
        boolean x = isTypeLt(type1, type2);
        if (x) {
            return x ? Boolean.TRUE : Boolean.FALSE;
        }
        boolean x2 = type1 == type2;
        if (!x2) {
            return x2 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (type1 == Lit7) {
            isComponentLeq = isBooleanLeq(val1, val2);
        } else if (type1 == Lit5) {
            isComponentLeq = Scheme.numLEq.apply2(val1, val2);
        } else if (type1 == Lit6) {
            isComponentLeq = strings.isString$Ls$Eq(val1, val2) ? Boolean.TRUE : Boolean.FALSE;
        } else if (type1 == Lit8) {
            isComponentLeq = isListLeq(val1, val2);
        } else {
            isComponentLeq = type1 == Lit11 ? isComponentLeq(val1, val2) : signalRuntimeError(Format.formatToString(0, "(isleq? ~A ~A)", getDisplayRepresentation(val1), getDisplayRepresentation(val2)), "Shouldn't happen");
        }
        return isComponentLeq;
    }

    public static Object isBooleanLt(Object val1, Object val2) {
        try {
            int i = ((val1 != Boolean.FALSE ? 1 : 0) + 1) & 1;
            return i != 0 ? val2 : i != 0 ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException e) {
            throw new WrongType(e, "x", -2, val1);
        }
    }

    public static Object isBooleanEq(Object val1, Object val2) {
        Object x = val1 != Boolean.FALSE ? val2 : val1;
        if (x != Boolean.FALSE) {
            return x;
        }
        try {
            int i = ((val1 != Boolean.FALSE ? 1 : 0) + 1) & 1;
            return i != 0 ? val2 != Boolean.FALSE ? Boolean.FALSE : Boolean.TRUE : i != 0 ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException e) {
            throw new WrongType(e, "x", -2, val1);
        }
    }

    public static Object isBooleanLeq(Object val1, Object val2) {
        return (val1 == Boolean.FALSE ? val1 == Boolean.FALSE : val2 != Boolean.FALSE) ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object isListLt(Object y1, Object y2) {
        Object lst1 = yailListContents(y1);
        Object lst2 = yailListContents(y2);
        while (!lists.isNull(lst1)) {
            if (lists.isNull(lst2)) {
                return Boolean.FALSE;
            }
            if (isIsLt(lists.car.apply1(lst1), lists.car.apply1(lst2)) != Boolean.FALSE) {
                return Boolean.TRUE;
            }
            if (isIsEq(lists.car.apply1(lst1), lists.car.apply1(lst2)) == Boolean.FALSE) {
                return Boolean.FALSE;
            }
            lst1 = lists.cdr.apply1(lst1);
            lst2 = lists.cdr.apply1(lst2);
        }
        return lists.isNull(lst2) ? Boolean.FALSE : Boolean.TRUE;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0033 A[LOOP:0: B:2:0x000c->B:8:0x0033, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0040 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object isListEq(java.lang.Object r5, java.lang.Object r6) {
        /*
            java.lang.Object r0 = yailListContents(r5)
            java.lang.Object r1 = yailListContents(r6)
            r2 = 0
        Lc:
            boolean r3 = kawa.lib.lists.isNull(r0)
            r2 = r3
            if (r2 == 0) goto L1a
            boolean r3 = kawa.lib.lists.isNull(r1)
            if (r3 == 0) goto L1f
            goto L1c
        L1a:
            if (r2 == 0) goto L1f
        L1c:
            java.lang.Boolean r2 = java.lang.Boolean.TRUE
            goto L42
        L1f:
            gnu.expr.GenericProc r3 = kawa.lib.lists.car
            java.lang.Object r3 = r3.apply1(r0)
            gnu.expr.GenericProc r4 = kawa.lib.lists.car
            java.lang.Object r4 = r4.apply1(r1)
            java.lang.Object r3 = isIsEq(r3, r4)
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            if (r3 == r4) goto L40
            gnu.expr.GenericProc r3 = kawa.lib.lists.cdr
            java.lang.Object r0 = r3.apply1(r0)
            gnu.expr.GenericProc r3 = kawa.lib.lists.cdr
            java.lang.Object r1 = r3.apply1(r1)
            goto Lc
        L40:
            java.lang.Boolean r2 = java.lang.Boolean.FALSE
        L42:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.youngandroid.runtime.isListEq(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public static Object yailListNecessary(Object y1) {
        return isYailList(y1) != Boolean.FALSE ? yailListContents(y1) : y1;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0023 A[EDGE_INSN: B:25:0x0023->B:26:0x0023 BREAK  A[LOOP:0: B:2:0x000c->B:14:0x005a], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object isListLeq(java.lang.Object r5, java.lang.Object r6) {
        /*
            java.lang.Object r0 = yailListNecessary(r5)
            java.lang.Object r1 = yailListNecessary(r6)
            r2 = 0
        Lc:
            boolean r3 = kawa.lib.lists.isNull(r0)
            r2 = r3
            if (r2 == 0) goto L1a
            boolean r3 = kawa.lib.lists.isNull(r1)
            if (r3 == 0) goto L1d
            goto L1c
        L1a:
            if (r2 == 0) goto L1d
        L1c:
            goto L23
        L1d:
            boolean r3 = kawa.lib.lists.isNull(r0)
            if (r3 == 0) goto L26
        L23:
            java.lang.Boolean r2 = java.lang.Boolean.TRUE
            goto L69
        L26:
            boolean r3 = kawa.lib.lists.isNull(r1)
            if (r3 == 0) goto L2f
            java.lang.Boolean r2 = java.lang.Boolean.FALSE
            goto L69
        L2f:
            gnu.expr.GenericProc r3 = kawa.lib.lists.car
            java.lang.Object r3 = r3.apply1(r0)
            gnu.expr.GenericProc r4 = kawa.lib.lists.car
            java.lang.Object r4 = r4.apply1(r1)
            java.lang.Object r3 = isIsLt(r3, r4)
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            if (r3 == r4) goto L46
            java.lang.Boolean r2 = java.lang.Boolean.TRUE
            goto L69
        L46:
            gnu.expr.GenericProc r3 = kawa.lib.lists.car
            java.lang.Object r3 = r3.apply1(r0)
            gnu.expr.GenericProc r4 = kawa.lib.lists.car
            java.lang.Object r4 = r4.apply1(r1)
            java.lang.Object r3 = isIsEq(r3, r4)
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            if (r3 == r4) goto L67
            gnu.expr.GenericProc r3 = kawa.lib.lists.cdr
            java.lang.Object r0 = r3.apply1(r0)
            gnu.expr.GenericProc r3 = kawa.lib.lists.cdr
            java.lang.Object r1 = r3.apply1(r1)
            goto Lc
        L67:
            java.lang.Boolean r2 = java.lang.Boolean.FALSE
        L69:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.youngandroid.runtime.isListLeq(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public static Object isComponentLt(Object comp1, Object comp2) {
        boolean x = strings.isString$Ls(comp1.getClass().getSimpleName(), comp2.getClass().getSimpleName());
        if (x) {
            return x ? Boolean.TRUE : Boolean.FALSE;
        }
        boolean x2 = strings.isString$Eq(comp1.getClass().getSimpleName(), comp2.getClass().getSimpleName());
        return x2 ? comp1.hashCode() < comp2.hashCode() ? Boolean.TRUE : Boolean.FALSE : x2 ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object isComponentEq(Object comp1, Object comp2) {
        boolean x = strings.isString$Eq(comp1.getClass().getSimpleName(), comp2.getClass().getSimpleName());
        return x ? comp1.hashCode() == comp2.hashCode() ? Boolean.TRUE : Boolean.FALSE : x ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object isComponentLeq(Object comp1, Object comp2) {
        boolean x = strings.isString$Ls(comp1.getClass().getSimpleName(), comp2.getClass().getSimpleName());
        if (x) {
            return x ? Boolean.TRUE : Boolean.FALSE;
        }
        boolean x2 = strings.isString$Eq(comp1.getClass().getSimpleName(), comp2.getClass().getSimpleName());
        return x2 ? comp1.hashCode() <= comp2.hashCode() ? Boolean.TRUE : Boolean.FALSE : x2 ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object take(Object n, Object xs) {
        Object zs = LList.Empty;
        Object n2 = n;
        Object xs2 = xs;
        while (true) {
            Object apply2 = Scheme.numEqu.apply2(n2, Lit25);
            try {
                boolean x = ((Boolean) apply2).booleanValue();
                if (x) {
                    if (!x) {
                        Object apply22 = AddOp.$Mn.apply2(n2, Lit24);
                        Object apply1 = lists.cdr.apply1(xs2);
                        zs = lists.cons(lists.car.apply1(xs2), zs);
                        xs2 = apply1;
                        n2 = apply22;
                    }
                    try {
                        return lists.reverse((LList) zs);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "reverse", 1, zs);
                    }
                }
                if (!lists.isNull(xs2)) {
                    Object apply222 = AddOp.$Mn.apply2(n2, Lit24);
                    Object apply12 = lists.cdr.apply1(xs2);
                    zs = lists.cons(lists.car.apply1(xs2), zs);
                    xs2 = apply12;
                    n2 = apply222;
                }
                return lists.reverse((LList) zs);
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "x", -2, apply2);
            }
        }
    }

    public static Object drop(Object n, Object xs) {
        Object apply2 = Scheme.numEqu.apply2(n, Lit25);
        try {
            boolean x = ((Boolean) apply2).booleanValue();
            return (!x ? lists.isNull(xs) : x) ? drop(AddOp.$Mn.apply2(n, Lit24), lists.cdr.apply1(xs)) : xs;
        } catch (ClassCastException e) {
            throw new WrongType(e, "x", -2, apply2);
        }
    }

    public static Object merge(Object lessthan$Qu, Object lst1, Object lst2) {
        if (lists.isNull(lst1)) {
            return lst2;
        }
        if (lists.isNull(lst2)) {
            return lst1;
        }
        return Scheme.applyToArgs.apply3(lessthan$Qu, lists.car.apply1(lst1), lists.car.apply1(lst2)) != Boolean.FALSE ? lists.cons(lists.car.apply1(lst1), merge(lessthan$Qu, lists.cdr.apply1(lst1), lst2)) : lists.cons(lists.car.apply1(lst2), merge(lessthan$Qu, lst1, lists.cdr.apply1(lst2)));
    }

    public static Object mergesort(Object lessthan$Qu, Object lst) {
        if (lists.isNull(lst) || lists.isNull(lists.cdr.apply1(lst))) {
            return lst;
        }
        try {
            try {
                return merge(lessthan$Qu, mergesort(lessthan$Qu, take(Integer.valueOf(lists.length((LList) lst) / 2), lst)), mergesort(lessthan$Qu, drop(Integer.valueOf(lists.length((LList) lst) / 2), lst)));
            } catch (ClassCastException e) {
                throw new WrongType(e, PropertyTypeConstants.PROPERTY_TYPE_LENGTH, 1, lst);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, PropertyTypeConstants.PROPERTY_TYPE_LENGTH, 1, lst);
        }
    }

    public static Object yailListSort(Object y1) {
        if (isYailListEmpty(y1) != Boolean.FALSE) {
            return new YailList();
        }
        if (!lists.isPair(y1)) {
            return y1;
        }
        return kawaList$To$YailList(mergesort(is$Mnleq$Qu, yailListContents(y1)));
    }

    public static Object yailListSortComparator(Object lessthan$Qu, Object y1) {
        if (isYailListEmpty(y1) != Boolean.FALSE) {
            return new YailList();
        }
        if (!lists.isPair(y1)) {
            return y1;
        }
        return kawaList$To$YailList(mergesort(lessthan$Qu, yailListContents(y1)));
    }

    public static Object mergeKey(Object lessthan$Qu, Object key, Object lst1, Object lst2) {
        if (lists.isNull(lst1)) {
            return lst2;
        }
        if (lists.isNull(lst2)) {
            return lst1;
        }
        return Scheme.applyToArgs.apply3(lessthan$Qu, Scheme.applyToArgs.apply2(key, lists.car.apply1(lst1)), Scheme.applyToArgs.apply2(key, lists.car.apply1(lst2))) != Boolean.FALSE ? lists.cons(lists.car.apply1(lst1), mergeKey(lessthan$Qu, key, lists.cdr.apply1(lst1), lst2)) : lists.cons(lists.car.apply1(lst2), mergeKey(lessthan$Qu, key, lst1, lists.cdr.apply1(lst2)));
    }

    public static Object mergesortKey(Object lessthan$Qu, Object key, Object lst) {
        if (lists.isNull(lst) || lists.isNull(lists.cdr.apply1(lst))) {
            return lst;
        }
        try {
            try {
                return mergeKey(lessthan$Qu, key, mergesortKey(lessthan$Qu, key, take(Integer.valueOf(lists.length((LList) lst) / 2), lst)), mergesortKey(lessthan$Qu, key, drop(Integer.valueOf(lists.length((LList) lst) / 2), lst)));
            } catch (ClassCastException e) {
                throw new WrongType(e, PropertyTypeConstants.PROPERTY_TYPE_LENGTH, 1, lst);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, PropertyTypeConstants.PROPERTY_TYPE_LENGTH, 1, lst);
        }
    }

    public static Object yailListSortKey(Object key, Object y1) {
        if (isYailListEmpty(y1) != Boolean.FALSE) {
            return new YailList();
        }
        if (!lists.isPair(y1)) {
            return y1;
        }
        return kawaList$To$YailList(mergesortKey(is$Mnleq$Qu, key, yailListContents(y1)));
    }

    public static Object listNumberOnly(Object lst) {
        return lists.isNull(lst) ? LList.Empty : numbers.isNumber(lists.car.apply1(lst)) ? lists.cons(lists.car.apply1(lst), listNumberOnly(lists.cdr.apply1(lst))) : listNumberOnly(lists.cdr.apply1(lst));
    }

    public static Object listMin(Object lessthan$Qu, Object min, Object lst) {
        if (lists.isNull(lst)) {
            return min;
        }
        return listMin(lessthan$Qu, Scheme.applyToArgs.apply3(lessthan$Qu, min, lists.car.apply1(lst)) != Boolean.FALSE ? min : lists.car.apply1(lst), lists.cdr.apply1(lst));
    }

    public static Object yailListMinComparator(Object lessthan$Qu, Object y1) {
        if (!lists.isPair(y1)) {
            return y1;
        }
        if (isYailListEmpty(y1) != Boolean.FALSE) {
            return makeYailList$V(new Object[0]);
        }
        return listMin(lessthan$Qu, lists.car.apply1(yailListContents(y1)), lists.cdr.apply1(yailListContents(y1)));
    }

    public static Object listMax(Object lessthan$Qu, Object max, Object lst) {
        Object obj;
        if (lists.isNull(lst)) {
            return max;
        }
        if (Scheme.applyToArgs.apply3(lessthan$Qu, max, lists.car.apply1(lst)) == Boolean.FALSE) {
            obj = max;
        } else {
            obj = lists.car.apply1(lst);
        }
        return listMax(lessthan$Qu, obj, lists.cdr.apply1(lst));
    }

    public static Object yailListMaxComparator(Object lessthan$Qu, Object y1) {
        if (!lists.isPair(y1)) {
            return y1;
        }
        if (isYailListEmpty(y1) != Boolean.FALSE) {
            return makeYailList$V(new Object[0]);
        }
        return listMax(lessthan$Qu, lists.car.apply1(yailListContents(y1)), lists.cdr.apply1(yailListContents(y1)));
    }

    public static Object yailListButFirst(Object yail$Mnlist) {
        Object contents = yailListContents(yail$Mnlist);
        if (lists.isNull(contents)) {
            return signalRuntimeError(Format.formatToString(0, "The list cannot be empty" instanceof Object[] ? (Object[]) "The list cannot be empty" : new Object[]{"The list cannot be empty"}), "Bad list argument to but-first");
        }
        return lists.isNull(lists.cdr.apply1(contents)) ? makeYailList$V(new Object[0]) : kawaList$To$YailList(lists.cdr.apply1(contents));
    }

    public static Object butLast(Object lst) {
        if (!lists.isNull(lst) && !lists.isNull(lists.cdr.apply1(lst))) {
            return lists.cons(lists.car.apply1(lst), butLast(lists.cdr.apply1(lst)));
        }
        return LList.Empty;
    }

    public static Object yailListButLast(Object yail$Mnlist) {
        Object contents = yailListContents(yail$Mnlist);
        if (lists.isNull(contents)) {
            return signalRuntimeError(Format.formatToString(0, "The list cannot be empty" instanceof Object[] ? (Object[]) "The list cannot be empty" : new Object[]{"The list cannot be empty"}), "Bad list argument to but-last");
        }
        return kawaList$To$YailList(butLast(yailListContents(yail$Mnlist)));
    }

    public static Object front(Object lst, Object n) {
        NumberCompare numberCompare = Scheme.numEqu;
        IntNum intNum = Lit24;
        return numberCompare.apply2(n, intNum) != Boolean.FALSE ? lst : front(lists.cdr.apply1(lst), AddOp.$Mn.apply2(n, intNum));
    }

    public static Object back(Object lst, Object n1, Object n2) {
        NumberCompare numberCompare = Scheme.numEqu;
        AddOp addOp = AddOp.$Mn;
        IntNum intNum = Lit24;
        return numberCompare.apply2(n1, addOp.apply2(n2, intNum)) != Boolean.FALSE ? LList.Empty : lists.cons(lists.car.apply1(lst), back(lists.cdr.apply1(lst), AddOp.$Pl.apply2(n1, intNum), n2));
    }

    public static Object yailListSlice(Object yail$Mnlist, Object index1, Object index2) {
        Object verified$Mnindex1 = coerceToNumber(index1);
        Object verified$Mnindex2 = coerceToNumber(index2);
        PairWithPosition pairWithPosition = Lit2;
        if (verified$Mnindex1 == pairWithPosition) {
            signalRuntimeError(Format.formatToString(0, "Insert list item: index (~A) is not a number", getDisplayRepresentation(verified$Mnindex1)), "Bad list verified-index1");
        }
        if (verified$Mnindex2 == pairWithPosition) {
            signalRuntimeError(Format.formatToString(0, "Insert list item: index (~A) is not a number", getDisplayRepresentation(verified$Mnindex2)), "Bad list verified-index2");
        }
        NumberCompare numberCompare = Scheme.numLss;
        IntNum intNum = Lit24;
        if (numberCompare.apply2(verified$Mnindex1, intNum) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Slice list: Attempt to slice list ~A at index ~A. The minimum valid index number is 1.", getDisplayRepresentation(yail$Mnlist), verified$Mnindex2), "List index smaller than 1");
        }
        int len$Pl1 = yailListLength(yail$Mnlist) + 1;
        if (Scheme.numGrt.apply2(verified$Mnindex2, Integer.valueOf(len$Pl1)) != Boolean.FALSE) {
            signalRuntimeError(Format.formatToString(0, "Slice list: Attempt to slice list ~A at index ~A.  The maximum valid index number is ~A.", getDisplayRepresentation(yail$Mnlist), verified$Mnindex2, Integer.valueOf(len$Pl1)), "List index too large");
        }
        return kawaList$To$YailList(take(AddOp.$Mn.apply2(verified$Mnindex2, verified$Mnindex1), drop(AddOp.$Mn.apply2(verified$Mnindex1, intNum), yailListContents(yail$Mnlist))));
    }

    public static Object yailForRange(Object proc, Object start, Object end, Object step) {
        Object nstart = coerceToNumber(start);
        Object nend = coerceToNumber(end);
        Object nstep = coerceToNumber(step);
        PairWithPosition pairWithPosition = Lit2;
        if (nstart == pairWithPosition) {
            signalRuntimeError(Format.formatToString(0, "For range: the start value -- ~A -- is not a number", getDisplayRepresentation(start)), "Bad start value");
        }
        if (nend == pairWithPosition) {
            signalRuntimeError(Format.formatToString(0, "For range: the end value -- ~A -- is not a number", getDisplayRepresentation(end)), "Bad end value");
        }
        if (nstep == pairWithPosition) {
            signalRuntimeError(Format.formatToString(0, "For range: the step value -- ~A -- is not a number", getDisplayRepresentation(step)), "Bad step value");
        }
        return yailForRangeWithNumericCheckedArgs(proc, nstart, nend, nstep);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0050, code lost:
    
        if (r1 != false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:?, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0079, code lost:
    
        if (r1 != false) goto L47;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b9 A[LOOP:0: B:26:0x00af->B:28:0x00b9, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object yailForRangeWithNumericCheckedArgs(java.lang.Object r6, java.lang.Object r7, java.lang.Object r8, java.lang.Object r9) {
        /*
            Method dump skipped, instructions count: 227
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.youngandroid.runtime.yailForRangeWithNumericCheckedArgs(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    @Override // gnu.expr.ModuleBody
    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        switch (moduleMethod.selector) {
            case 17:
                return addComponentWithinRepl(obj, obj2, obj3, obj4);
            case 23:
                return setAndCoerceProperty$Ex(obj, obj2, obj3, obj4);
            case 47:
                return callComponentMethod(obj, obj2, obj3, obj4);
            case 49:
                return callComponentMethodWithBlockingContinuation(obj, obj2, obj3, obj4);
            case 52:
                return callComponentTypeMethodWithBlockingContinuation(obj, obj2, obj3, obj4);
            case 53:
                return callYailPrimitive(obj, obj2, obj3, obj4);
            case 63:
                return callWithCoercedArgs(obj, obj2, obj3, obj4);
            case 64:
                return $PcSetAndCoerceProperty$Ex(obj, obj2, obj3, obj4);
            case ErrorMessages.ERROR_CAMERA_NO_IMAGE_RETURNED /* 201 */:
                return mergeKey(obj, obj2, obj3, obj4);
            case 215:
                return yailForRange(obj, obj2, obj3, obj4);
            case 216:
                return yailForRangeWithNumericCheckedArgs(obj, obj2, obj3, obj4);
            default:
                return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
        }
    }

    public static Object yailNumberRange(Object low, Object high) {
        try {
            try {
                return kawaList$To$YailList(lambda18loop(Arithmetic.toExact(numbers.ceiling(LangObjType.coerceRealNum(low))), Arithmetic.toExact(numbers.floor(LangObjType.coerceRealNum(high)))));
            } catch (ClassCastException e) {
                throw new WrongType(e, "floor", 1, high);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "ceiling", 1, low);
        }
    }

    public static Object lambda18loop(Object a, Object b) {
        return Scheme.numGrt.apply2(a, b) != Boolean.FALSE ? LList.Empty : lists.cons(a, lambda18loop(AddOp.$Pl.apply2(a, Lit24), b));
    }

    public static Object yailAlistLookup(Object key, Object yail$Mnlist$Mnof$Mnpairs, Object obj) {
        androidLog(Format.formatToString(0, "List alist lookup key is  ~A and table is ~A", key, yail$Mnlist$Mnof$Mnpairs));
        for (Object pairs$Mnto$Mncheck = yailListContents(yail$Mnlist$Mnof$Mnpairs); !lists.isNull(pairs$Mnto$Mncheck); pairs$Mnto$Mncheck = lists.cdr.apply1(pairs$Mnto$Mncheck)) {
            if (isPairOk(lists.car.apply1(pairs$Mnto$Mncheck)) == Boolean.FALSE) {
                return signalRuntimeError(Format.formatToString(0, "Lookup in pairs: the list ~A is not a well-formed list of pairs", getDisplayRepresentation(yail$Mnlist$Mnof$Mnpairs)), "Invalid list of pairs");
            }
            if (isYailEqual(key, lists.car.apply1(yailListContents(lists.car.apply1(pairs$Mnto$Mncheck)))) != Boolean.FALSE) {
                return lists.cadr.apply1(yailListContents(lists.car.apply1(pairs$Mnto$Mncheck)));
            }
        }
        return obj;
    }

    public static Object isPairOk(Object candidate$Mnpair) {
        Object x = isYailList(candidate$Mnpair);
        if (x == Boolean.FALSE) {
            return x;
        }
        Object yailListContents = yailListContents(candidate$Mnpair);
        try {
            return lists.length((LList) yailListContents) == 2 ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException e) {
            throw new WrongType(e, PropertyTypeConstants.PROPERTY_TYPE_LENGTH, 1, yailListContents);
        }
    }

    public static Object yailListJoinWithSeparator(Object yail$Mnlist, Object separator) {
        return joinStrings(yailListContents(yail$Mnlist), separator);
    }

    public static YailDictionary makeYailDictionary$V(Object[] argsArray) {
        LList pairs = LList.makeList(argsArray, 0);
        return YailDictionary.makeDictionary(pairs);
    }

    @Override // gnu.expr.ModuleBody
    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        switch (moduleMethod.selector) {
            case 18:
                return call$MnInitializeOfComponents$V(objArr);
            case 27:
                return setAndCoercePropertyAndCheck$Ex(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
            case 28:
                return symbolAppend$V(objArr);
            case 45:
                return lambda27(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
            case 46:
                Object obj = objArr[0];
                Object obj2 = objArr[1];
                int length = objArr.length - 2;
                Object[] objArr2 = new Object[length];
                while (true) {
                    length--;
                    if (length < 0) {
                        return lambda28$V(obj, obj2, objArr2);
                    }
                    objArr2[length] = objArr[length + 2];
                }
            case 48:
                return callComponentMethodWithContinuation(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
            case 50:
                return callComponentTypeMethod(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
            case 51:
                return callComponentTypeMethodWithContinuation(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
            case 101:
                return processAndDelayed$V(objArr);
            case 102:
                return processOrDelayed$V(objArr);
            case 157:
                return makeYailList$V(objArr);
            case 172:
                Object obj3 = objArr[0];
                int length2 = objArr.length - 1;
                Object[] objArr3 = new Object[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        yailListAddToList$Ex$V(obj3, objArr3);
                        return Values.empty;
                    }
                    objArr3[length2] = objArr[length2 + 1];
                }
            case 221:
                return makeYailDictionary$V(objArr);
            default:
                return super.applyN(moduleMethod, objArr);
        }
    }

    public static YailList makeDictionaryPair(Object key, Object value) {
        return makeYailList$V(new Object[]{key, value});
    }

    public static Object yailDictionarySetPair(Object key, Object yail$Mndictionary, Object value) {
        return ((YailDictionary) yail$Mndictionary).put(key, value);
    }

    public static Object yailDictionaryDeletePair(Object yail$Mndictionary, Object key) {
        return ((YailDictionary) yail$Mndictionary).remove(key);
    }

    public static Object yailDictionaryLookup(Object key, Object yail$Mndictionary, Object obj) {
        Object result;
        Object obj2;
        if (yail$Mndictionary instanceof YailList) {
            result = yailAlistLookup(key, yail$Mndictionary, obj);
        } else {
            result = yail$Mndictionary instanceof YailDictionary ? ((YailDictionary) yail$Mndictionary).get(key) : obj;
        }
        if (result != null) {
            return result;
        }
        if (isEnum(key) == Boolean.FALSE) {
            obj2 = obj;
        } else {
            obj2 = yailDictionaryLookup(sanitizeComponentData(Scheme.applyToArgs.apply1(GetNamedPart.getNamedPart.apply2(key, Lit18))), yail$Mndictionary, obj);
        }
        Object result2 = obj2;
        return result2;
    }

    public static Object yailDictionaryRecursiveLookup(Object keys, Object yail$Mndictionary, Object obj) {
        YailDictionary yailDictionary = (YailDictionary) yail$Mndictionary;
        Object yailListContents = yailListContents(keys);
        try {
            Object result = yailDictionary.getObjectAtKeyPath((List) yailListContents);
            return result == null ? obj : result;
        } catch (ClassCastException e) {
            throw new WrongType(e, "com.google.appinventor.components.runtime.util.YailDictionary.getObjectAtKeyPath(java.util.List)", 2, yailListContents);
        }
    }

    public static YailList yailDictionaryWalk(Object path, Object dict) {
        try {
            YailObject yailObject = (YailObject) dict;
            Object yailListContents = yailListContents(path);
            try {
                return YailList.makeList((List) YailDictionary.walkKeyPath(yailObject, (List) yailListContents));
            } catch (ClassCastException e) {
                throw new WrongType(e, "com.google.appinventor.components.runtime.util.YailDictionary.walkKeyPath(com.google.appinventor.components.runtime.util.YailObject,java.util.List)", 2, yailListContents);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "com.google.appinventor.components.runtime.util.YailDictionary.walkKeyPath(com.google.appinventor.components.runtime.util.YailObject,java.util.List)", 1, dict);
        }
    }

    public static Object yailDictionaryRecursiveSet(Object keys, Object yail$Mndictionary, Object value) {
        return Scheme.applyToArgs.apply3(GetNamedPart.getNamedPart.apply2(yail$Mndictionary, Lit46), yailListContents(keys), value);
    }

    public static YailList yailDictionaryGetKeys(Object yail$Mndictionary) {
        return YailList.makeList((Set) ((YailDictionary) yail$Mndictionary).keySet());
    }

    public static YailList yailDictionaryGetValues(Object yail$Mndictionary) {
        return YailList.makeList(((YailDictionary) yail$Mndictionary).values());
    }

    public static boolean yailDictionaryIsKeyIn(Object key, Object yail$Mndictionary) {
        return ((YailDictionary) yail$Mndictionary).containsKey(key);
    }

    public static int yailDictionaryLength(Object yail$Mndictionary) {
        return ((YailDictionary) yail$Mndictionary).size();
    }

    public static Object yailDictionaryAlistToDict(Object alist) {
        for (Object pairs$Mnto$Mncheck = yailListContents(alist); !lists.isNull(pairs$Mnto$Mncheck); pairs$Mnto$Mncheck = lists.cdr.apply1(pairs$Mnto$Mncheck)) {
            if (isPairOk(lists.car.apply1(pairs$Mnto$Mncheck)) == Boolean.FALSE) {
                signalRuntimeError(Format.formatToString(0, "List of pairs to dict: the list ~A is not a well-formed list of pairs", getDisplayRepresentation(alist)), "Invalid list of pairs");
                break;
            }
        }
        try {
            return YailDictionary.alistToDict((YailList) alist);
        } catch (ClassCastException e) {
            throw new WrongType(e, "com.google.appinventor.components.runtime.util.YailDictionary.alistToDict(com.google.appinventor.components.runtime.util.YailList)", 1, alist);
        }
    }

    public static Object yailDictionaryDictToAlist(Object dict) {
        try {
            return YailDictionary.dictToAlist((YailDictionary) dict);
        } catch (ClassCastException e) {
            throw new WrongType(e, "com.google.appinventor.components.runtime.util.YailDictionary.dictToAlist(com.google.appinventor.components.runtime.util.YailDictionary)", 1, dict);
        }
    }

    public static Object yailDictionaryCopy(Object yail$Mndictionary) {
        return ((YailDictionary) yail$Mndictionary).clone();
    }

    public static void yailDictionaryCombineDicts(Object first$Mndictionary, Object second$Mndictionary) {
        try {
            ((YailDictionary) first$Mndictionary).putAll((Map) second$Mndictionary);
        } catch (ClassCastException e) {
            throw new WrongType(e, "com.google.appinventor.components.runtime.util.YailDictionary.putAll(java.util.Map)", 2, second$Mndictionary);
        }
    }

    public static Object isYailDictionary(Object x) {
        return x instanceof YailDictionary ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object makeDisjunct(Object x) {
        if (lists.isNull(lists.cdr.apply1(x))) {
            Object apply1 = lists.car.apply1(x);
            return Pattern.quote(apply1 != null ? apply1.toString() : null);
        }
        Object[] objArr = new Object[2];
        Object apply12 = lists.car.apply1(x);
        objArr[0] = Pattern.quote(apply12 != null ? apply12.toString() : null);
        objArr[1] = strings.stringAppend("|", makeDisjunct(lists.cdr.apply1(x)));
        return strings.stringAppend(objArr);
    }

    public static Object array$To$List(Object arr) {
        try {
            return insertYailListHeader(LList.makeList((Object[]) arr, 0));
        } catch (ClassCastException e) {
            throw new WrongType(e, "gnu.lists.LList.makeList(java.lang.Object[],int)", 1, arr);
        }
    }

    public static int stringStartsAt(Object text, Object piece) {
        return text.toString().indexOf(piece.toString()) + 1;
    }

    public static Boolean stringContains(Object text, Object piece) {
        return stringStartsAt(text, piece) == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    public static Object stringContainsAny(Object text, Object piece$Mnlist) {
        for (Object piece$Mnlist2 = yailListContents(piece$Mnlist); !lists.isNull(piece$Mnlist2); piece$Mnlist2 = lists.cdr.apply1(piece$Mnlist2)) {
            Boolean x = stringContains(text, lists.car.apply1(piece$Mnlist2));
            if (x != Boolean.FALSE) {
                return x;
            }
        }
        return Boolean.FALSE;
    }

    public static Object stringContainsAll(Object text, Object piece$Mnlist) {
        for (Object piece$Mnlist2 = yailListContents(piece$Mnlist); !lists.isNull(piece$Mnlist2); piece$Mnlist2 = lists.cdr.apply1(piece$Mnlist2)) {
            Boolean x = stringContains(text, lists.car.apply1(piece$Mnlist2));
            if (x == Boolean.FALSE) {
                return x;
            }
        }
        return Boolean.TRUE;
    }

    public static Object stringSplitAtFirst(Object text, Object at) {
        return array$To$List(text.toString().split(Pattern.quote(at == null ? null : at.toString()), 2));
    }

    public static Object stringSplitAtFirstOfAny(Object text, Object at) {
        if (lists.isNull(yailListContents(at))) {
            return signalRuntimeError("split at first of any: The list of places to split at is empty.", "Invalid text operation");
        }
        String obj = text.toString();
        Object makeDisjunct = makeDisjunct(yailListContents(at));
        return array$To$List(obj.split(makeDisjunct == null ? null : makeDisjunct.toString(), 2));
    }

    public static YailList stringSplit(Object text, Object at) {
        return JavaStringUtils.split(text == null ? null : text.toString(), Pattern.quote(at != null ? at.toString() : null));
    }

    public static Object stringSplitAtAny(Object text, Object at) {
        if (lists.isNull(yailListContents(at))) {
            return signalRuntimeError("split at any: The list of places to split at is empty.", "Invalid text operation");
        }
        String obj = text.toString();
        Object makeDisjunct = makeDisjunct(yailListContents(at));
        return array$To$List(obj.split(makeDisjunct == null ? null : makeDisjunct.toString(), -1));
    }

    public static Object stringSplitAtSpaces(Object text) {
        return array$To$List(text.toString().trim().split("\\s+", -1));
    }

    public static Object stringSubstring(Object wholestring, Object start, Object length) {
        try {
            int len = strings.stringLength((CharSequence) wholestring);
            NumberCompare numberCompare = Scheme.numLss;
            IntNum intNum = Lit24;
            if (numberCompare.apply2(start, intNum) != Boolean.FALSE) {
                return signalRuntimeError(Format.formatToString(0, "Segment: Start is less than 1 (~A).", start), "Invalid text operation");
            }
            if (Scheme.numLss.apply2(length, Lit25) != Boolean.FALSE) {
                return signalRuntimeError(Format.formatToString(0, "Segment: Length is negative (~A).", length), "Invalid text operation");
            }
            if (Scheme.numGrt.apply2(AddOp.$Pl.apply2(AddOp.$Mn.apply2(start, intNum), length), Integer.valueOf(len)) != Boolean.FALSE) {
                return signalRuntimeError(Format.formatToString(0, "Segment: Start (~A) + length (~A) - 1 exceeds text length (~A).", start, length, Integer.valueOf(len)), "Invalid text operation");
            }
            try {
                CharSequence charSequence = (CharSequence) wholestring;
                Object apply2 = AddOp.$Mn.apply2(start, intNum);
                try {
                    int intValue = ((Number) apply2).intValue();
                    Object apply22 = AddOp.$Pl.apply2(AddOp.$Mn.apply2(start, intNum), length);
                    try {
                        return strings.substring(charSequence, intValue, ((Number) apply22).intValue());
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "substring", 3, apply22);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "substring", 2, apply2);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "substring", 1, wholestring);
            }
        } catch (ClassCastException e4) {
            throw new WrongType(e4, "string-length", 1, wholestring);
        }
    }

    public static String stringTrim(Object text) {
        return text.toString().trim();
    }

    public static Object stringReplaceAll(Object text, Object substring, Object replacement) {
        return text.toString().replaceAll(Pattern.quote(substring.toString()), Matcher.quoteReplacement(replacement.toString()));
    }

    @Override // gnu.expr.ModuleBody
    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        switch (moduleMethod.selector) {
            case 26:
                return getPropertyAndCheck(obj, obj2, obj3);
            case 43:
                return lambda26(obj, obj2, obj3);
            case 55:
                return sanitizeReturnValue(obj, obj2, obj3);
            case 61:
                return signalRuntimeFormError(obj, obj2, obj3);
            case 65:
                return $PcSetSubformLayoutProperty$Ex(obj, obj2, obj3);
            case 68:
                return coerceArgs(obj, obj2, obj3);
            case 168:
                yailListSetItem$Ex(obj, obj2, obj3);
                return Values.empty;
            case 170:
                yailListInsertItem$Ex(obj, obj2, obj3);
                return Values.empty;
            case 178:
                return yailListReduce(obj, obj2, obj3);
            case 197:
                return merge(obj, obj2, obj3);
            case ErrorMessages.ERROR_NO_CAMERA_PERMISSION /* 202 */:
                return mergesortKey(obj, obj2, obj3);
            case 205:
                return listMin(obj, obj2, obj3);
            case 207:
                return listMax(obj, obj2, obj3);
            case 213:
                return back(obj, obj2, obj3);
            case 214:
                return yailListSlice(obj, obj2, obj3);
            case 218:
                return yailAlistLookup(obj, obj2, obj3);
            case 223:
                return yailDictionarySetPair(obj, obj2, obj3);
            case 225:
                return yailDictionaryLookup(obj, obj2, obj3);
            case 226:
                return yailDictionaryRecursiveLookup(obj, obj2, obj3);
            case 228:
                return yailDictionaryRecursiveSet(obj, obj2, obj3);
            case 249:
                return stringSubstring(obj, obj2, obj3);
            case Telnet.WILL /* 251 */:
                return stringReplaceAll(obj, obj2, obj3);
            default:
                return super.apply3(moduleMethod, obj, obj2, obj3);
        }
    }

    public static Object isStringEmpty(Object text) {
        try {
            return strings.stringLength((CharSequence) text) == 0 ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException e) {
            throw new WrongType(e, "string-length", 1, text);
        }
    }

    public static Object textDeobfuscate(Object obj, Object obj2) {
        frame8 frame8Var = new frame8();
        frame8Var.text = obj;
        frame8Var.lc = obj2;
        ModuleMethod moduleMethod = frame8Var.cont$Fn16;
        CallCC.callcc.apply1(frame8Var.cont$Fn16);
        Object obj3 = Lit25;
        LList lList = LList.Empty;
        Object obj4 = frame8Var.text;
        try {
            Integer valueOf = Integer.valueOf(strings.stringLength((CharSequence) obj4));
            while (true) {
                NumberCompare numberCompare = Scheme.numGEq;
                Object obj5 = frame8Var.text;
                try {
                    if (numberCompare.apply2(obj3, Integer.valueOf(strings.stringLength((CharSequence) obj5))) != Boolean.FALSE) {
                        try {
                            break;
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "reverse", 1, lList);
                        }
                    }
                    Object obj6 = frame8Var.text;
                    try {
                        try {
                            int intValue = Char.make(strings.stringRef((CharSequence) obj6, ((Number) obj3).intValue())).intValue();
                            BitwiseOp bitwiseOp = BitwiseOp.and;
                            Object apply2 = BitwiseOp.xor.apply2(Integer.valueOf(intValue), AddOp.$Mn.apply2(valueOf, obj3));
                            IntNum intNum = Lit47;
                            Object apply22 = BitwiseOp.and.apply2(BitwiseOp.ior.apply2(BitwiseOp.ashiftl.apply2(BitwiseOp.and.apply2(BitwiseOp.xor.apply2(Integer.valueOf(intValue >> 8), obj3), intNum), Lit48), bitwiseOp.apply2(apply2, intNum)), intNum);
                            BitwiseOp bitwiseOp2 = BitwiseOp.and;
                            BitwiseOp bitwiseOp3 = BitwiseOp.xor;
                            Object obj7 = frame8Var.lc;
                            try {
                                try {
                                    lList = lists.cons(bitwiseOp2.apply2(bitwiseOp3.apply2(apply22, Integer.valueOf(Char.make(strings.stringRef((CharSequence) obj7, ((Number) obj3).intValue())).intValue())), intNum), lList);
                                    obj3 = AddOp.$Pl.apply2(Lit24, obj3);
                                } catch (ClassCastException e2) {
                                    throw new WrongType(e2, "string-ref", 2, obj3);
                                }
                            } catch (ClassCastException e3) {
                                throw new WrongType(e3, "string-ref", 1, obj7);
                            }
                        } catch (ClassCastException e4) {
                            throw new WrongType(e4, "string-ref", 2, obj3);
                        }
                    } catch (ClassCastException e5) {
                        throw new WrongType(e5, "string-ref", 1, obj6);
                    }
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "string-length", 1, obj5);
                }
            }
            Object reverse = lists.reverse(lList);
            Object obj8 = LList.Empty;
            while (reverse != LList.Empty) {
                try {
                    Pair pair = (Pair) reverse;
                    Object cdr = pair.getCdr();
                    Object car = pair.getCar();
                    try {
                        obj8 = Pair.make(Char.make(((Number) car).intValue()), obj8);
                        reverse = cdr;
                    } catch (ClassCastException e7) {
                        throw new WrongType(e7, "integer->char", 1, car);
                    }
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "arg0", -2, reverse);
                }
            }
            return strings.list$To$String(LList.reverseInPlace(obj8));
        } catch (ClassCastException e9) {
            throw new WrongType(e9, "string-length", 1, obj4);
        }
    }

    /* compiled from: runtime4411419262522357548.scm */
    /* loaded from: classes.dex */
    public class frame8 extends ModuleBody {
        final ModuleMethod cont$Fn16 = new ModuleMethod(this, 13, runtime.Lit49, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        Object lc;
        Object text;

        @Override // gnu.expr.ModuleBody
        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 13 ? lambda19cont(obj) : super.apply1(moduleMethod, obj);
        }

        @Override // gnu.expr.ModuleBody
        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 13) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }

        public Object lambda19cont(Object $Styail$Mnbreak$St) {
            while (true) {
                Object obj = this.lc;
                try {
                    int stringLength = strings.stringLength((CharSequence) obj);
                    Object obj2 = this.text;
                    try {
                        if (stringLength >= strings.stringLength((CharSequence) obj2)) {
                            return null;
                        }
                        Object obj3 = this.lc;
                        this.lc = strings.stringAppend(obj3, obj3);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "string-length", 1, obj2);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "string-length", 1, obj);
                }
            }
        }
    }

    public static String stringReplaceMappingsDictionary(Object text, Object mappings) {
        try {
            return JavaStringUtils.replaceAllMappingsDictionaryOrder(text == null ? null : text.toString(), (Map) mappings);
        } catch (ClassCastException e) {
            throw new WrongType(e, "com.google.appinventor.components.runtime.util.JavaStringUtils.replaceAllMappingsDictionaryOrder(java.lang.String,java.util.Map)", 2, mappings);
        }
    }

    public static String stringReplaceMappingsLongestString(Object text, Object mappings) {
        try {
            return JavaStringUtils.replaceAllMappingsLongestStringOrder(text == null ? null : text.toString(), (Map) mappings);
        } catch (ClassCastException e) {
            throw new WrongType(e, "com.google.appinventor.components.runtime.util.JavaStringUtils.replaceAllMappingsLongestStringOrder(java.lang.String,java.util.Map)", 2, mappings);
        }
    }

    public static String stringReplaceMappingsEarliestOccurrence(Object text, Object mappings) {
        try {
            return JavaStringUtils.replaceAllMappingsEarliestOccurrenceOrder(text == null ? null : text.toString(), (Map) mappings);
        } catch (ClassCastException e) {
            throw new WrongType(e, "com.google.appinventor.components.runtime.util.JavaStringUtils.replaceAllMappingsEarliestOccurrenceOrder(java.lang.String,java.util.Map)", 2, mappings);
        }
    }

    public static Number makeExactYailInteger(Object x) {
        Object coerceToNumber = coerceToNumber(x);
        try {
            return numbers.exact(numbers.round(LangObjType.coerceRealNum(coerceToNumber)));
        } catch (ClassCastException e) {
            throw new WrongType(e, "round", 1, coerceToNumber);
        }
    }

    public static Object makeColor(Object color$Mncomponents) {
        Number alpha;
        Number red = makeExactYailInteger(yailListGetItem(color$Mncomponents, Lit24));
        Number green = makeExactYailInteger(yailListGetItem(color$Mncomponents, Lit26));
        Number blue = makeExactYailInteger(yailListGetItem(color$Mncomponents, Lit52));
        if (yailListLength(color$Mncomponents) <= 3) {
            Object obj = $Stalpha$Mnopaque$St;
            try {
                alpha = (Number) obj;
            } catch (ClassCastException e) {
                throw new WrongType(e, "alpha", -2, obj);
            }
        } else {
            alpha = makeExactYailInteger(yailListGetItem(color$Mncomponents, Lit53));
        }
        return BitwiseOp.ior.apply2(BitwiseOp.ior.apply2(BitwiseOp.ior.apply2(BitwiseOp.ashiftl.apply2(BitwiseOp.and.apply2(alpha, $Stmax$Mncolor$Mncomponent$St), $Stcolor$Mnalpha$Mnposition$St), BitwiseOp.ashiftl.apply2(BitwiseOp.and.apply2(red, $Stmax$Mncolor$Mncomponent$St), $Stcolor$Mnred$Mnposition$St)), BitwiseOp.ashiftl.apply2(BitwiseOp.and.apply2(green, $Stmax$Mncolor$Mncomponent$St), $Stcolor$Mngreen$Mnposition$St)), BitwiseOp.ashiftl.apply2(BitwiseOp.and.apply2(blue, $Stmax$Mncolor$Mncomponent$St), $Stcolor$Mnblue$Mnposition$St));
    }

    public static Object splitColor(Object color) {
        Number intcolor = makeExactYailInteger(color);
        return kawaList$To$YailList(LList.list4(BitwiseOp.and.apply2(BitwiseOp.ashiftr.apply2(intcolor, $Stcolor$Mnred$Mnposition$St), $Stmax$Mncolor$Mncomponent$St), BitwiseOp.and.apply2(BitwiseOp.ashiftr.apply2(intcolor, $Stcolor$Mngreen$Mnposition$St), $Stmax$Mncolor$Mncomponent$St), BitwiseOp.and.apply2(BitwiseOp.ashiftr.apply2(intcolor, $Stcolor$Mnblue$Mnposition$St), $Stmax$Mncolor$Mncomponent$St), BitwiseOp.and.apply2(BitwiseOp.ashiftr.apply2(intcolor, $Stcolor$Mnalpha$Mnposition$St), $Stmax$Mncolor$Mncomponent$St)));
    }

    public static void closeScreen() {
        Form.finishActivity();
    }

    public static void closeApplication() {
        Form.finishApplication();
    }

    public static void openAnotherScreen(Object screen$Mnname) {
        Object coerceToString = coerceToString(screen$Mnname);
        Form.switchForm(coerceToString == null ? null : coerceToString.toString());
    }

    public static void openAnotherScreenWithStartValue(Object screen$Mnname, Object start$Mnvalue) {
        Object coerceToString = coerceToString(screen$Mnname);
        Form.switchFormWithStartValue(coerceToString == null ? null : coerceToString.toString(), start$Mnvalue);
    }

    public static Object getStartValue() {
        return sanitizeComponentData(Form.getStartValue());
    }

    public static void closeScreenWithValue(Object result) {
        Form.finishActivityWithResult(result);
    }

    public static String getPlainStartText() {
        return Form.getStartText();
    }

    public static void closeScreenWithPlainText(Object string) {
        Form.finishActivityWithTextResult(string == null ? null : string.toString());
    }

    public static String getServerAddressFromWifi() {
        Object slotValue = SlotGet.getSlotValue(false, Scheme.applyToArgs.apply1(GetNamedPart.getNamedPart.apply2(((Context) $Stthis$Mnform$St).getSystemService(Context.WIFI_SERVICE), Lit55)), "ipAddress", "ipAddress", "getIpAddress", "isIpAddress", Scheme.instance);
        try {
            return Formatter.formatIpAddress(((Number) slotValue).intValue());
        } catch (ClassCastException e) {
            throw new WrongType(e, "android.text.format.Formatter.formatIpAddress(int)", 1, slotValue);
        }
    }

    public static Object inUi(Object blockid, Object promise) {
        frame9 frame9Var = new frame9();
        frame9Var.blockid = blockid;
        frame9Var.promise = promise;
        $Stthis$Mnis$Mnthe$Mnrepl$St = Boolean.TRUE;
        return Scheme.applyToArgs.apply2(GetNamedPart.getNamedPart.apply2($Stui$Mnhandler$St, Lit56), thread.runnable(frame9Var.lambda$Fn17));
    }

    /* compiled from: runtime4411419262522357548.scm */
    /* loaded from: classes.dex */
    public class frame9 extends ModuleBody {
        Object blockid;
        final ModuleMethod lambda$Fn17;
        Object promise;

        public frame9() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 14, null, 0);
            moduleMethod.setProperty("source-location", "/tmp/runtime4411419262522357548.scm:3566");
            this.lambda$Fn17 = moduleMethod;
        }

        @Override // gnu.expr.ModuleBody
        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 14 ? lambda20() : super.apply0(moduleMethod);
        }

        Object lambda20() {
            Object list2;
            String message;
            Object obj = "NOK";
            Object obj2 = this.blockid;
            try {
                try {
                    list2 = LList.list2("OK", runtime.getDisplayRepresentation(misc.force(this.promise)));
                } catch (PermissionException exception) {
                    exception.printStackTrace();
                    list2 = LList.list2("NOK", strings.stringAppend("Failed due to missing permission: ", exception.getPermissionNeeded()));
                } catch (StopBlocksExecution e) {
                    list2 = LList.list2("OK", Boolean.FALSE);
                } catch (YailRuntimeError exception2) {
                    runtime.androidLog(exception2.getMessage());
                    list2 = LList.list2("NOK", exception2.getMessage());
                }
                obj = list2;
            } catch (Throwable exception3) {
                runtime.androidLog(exception3.getMessage());
                exception3.printStackTrace();
                if (exception3 instanceof Error) {
                    message = exception3.toString();
                } else {
                    message = exception3.getMessage();
                }
                list2 = LList.list2(obj, message);
            }
            return runtime.sendToBlock(obj2, list2);
        }

        @Override // gnu.expr.ModuleBody
        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 14) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }
    }

    public static Object sendToBlock(Object blockid, Object message) {
        Object good = lists.car.apply1(message);
        Object value = lists.cadr.apply1(message);
        RetValManager.appendReturnValue(blockid == null ? null : blockid.toString(), good == null ? null : good.toString(), value != null ? value.toString() : null);
        return Values.empty;
    }

    public static Object clearCurrentForm() {
        if ($Stthis$Mnform$St == null) {
            return Values.empty;
        }
        clearInitThunks();
        resetCurrentFormEnvironment();
        EventDispatcher.unregisterAllEventsForDelegation();
        return Invoke.invoke.apply2($Stthis$Mnform$St, "clear");
    }

    public static Object setFormName(Object form$Mnname) {
        return Invoke.invoke.apply3($Stthis$Mnform$St, "setFormName", form$Mnname);
    }

    public static Object removeComponent(Object component$Mnname) {
        try {
            SimpleSymbol component$Mnsymbol = misc.string$To$Symbol((CharSequence) component$Mnname);
            Object component$Mnobject = lookupInCurrentFormEnvironment(component$Mnsymbol);
            deleteFromCurrentFormEnvironment(component$Mnsymbol);
            return $Stthis$Mnform$St != null ? Invoke.invoke.apply3($Stthis$Mnform$St, "deleteComponent", component$Mnobject) : Values.empty;
        } catch (ClassCastException e) {
            throw new WrongType(e, "string->symbol", 1, component$Mnname);
        }
    }

    public static Object renameComponent(Object old$Mncomponent$Mnname, Object new$Mncomponent$Mnname) {
        try {
            try {
                return renameInCurrentFormEnvironment(misc.string$To$Symbol((CharSequence) old$Mncomponent$Mnname), misc.string$To$Symbol((CharSequence) new$Mncomponent$Mnname));
            } catch (ClassCastException e) {
                throw new WrongType(e, "string->symbol", 1, new$Mncomponent$Mnname);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "string->symbol", 1, old$Mncomponent$Mnname);
        }
    }

    @Override // gnu.expr.ModuleBody
    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 19:
                return addInitThunk(obj, obj2);
            case 24:
                return getProperty$1(obj, obj2);
            case 33:
                try {
                    return addToCurrentFormEnvironment((Symbol) obj, obj2);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "add-to-current-form-environment", 1, obj);
                }
            case 34:
                try {
                    return lookupInCurrentFormEnvironment((Symbol) obj, obj2);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "lookup-in-current-form-environment", 1, obj);
                }
            case 38:
                try {
                    try {
                        return renameInCurrentFormEnvironment((Symbol) obj, (Symbol) obj2);
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "rename-in-current-form-environment", 2, obj2);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "rename-in-current-form-environment", 1, obj);
                }
            case 39:
                try {
                    return addGlobalVarToCurrentFormEnvironment((Symbol) obj, obj2);
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "add-global-var-to-current-form-environment", 1, obj);
                }
            case 40:
                try {
                    return lookupGlobalVarInCurrentFormEnvironment((Symbol) obj, obj2);
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "lookup-global-var-in-current-form-environment", 1, obj);
                }
            case 60:
                return signalRuntimeError(obj, obj2);
            case 66:
                return generateRuntimeTypeError(obj, obj2);
            case 69:
                return coerceArg(obj, obj2);
            case 73:
                return coerceToEnum(obj, obj2);
            case 77:
                return coerceToComponentOfType(obj, obj2);
            case 85:
                return joinStrings(obj, obj2);
            case 86:
                return stringReplace(obj, obj2);
            case 97:
                return isYailEqual(obj, obj2);
            case 98:
                return isYailAtomicEqual(obj, obj2);
            case 100:
                return isYailNotEqual(obj, obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 108:
                return randomInteger(obj, obj2);
            case 110:
                return yailDivide(obj, obj2);
            case 121:
                return atan2Degrees(obj, obj2);
            case 126:
                return formatAsDecimal(obj, obj2);
            case 145:
                return sumMeanSquareDiff(obj, obj2);
            case 152:
                setYailListContents$Ex(obj, obj2);
                return Values.empty;
            case 166:
                return yailListIndex(obj, obj2);
            case 167:
                return yailListGetItem(obj, obj2);
            case 169:
                yailListRemoveItem$Ex(obj, obj2);
                return Values.empty;
            case 171:
                yailListAppend$Ex(obj, obj2);
                return Values.empty;
            case 173:
                return isYailListMember(obj, obj2);
            case 175:
                return yailForEach(obj, obj2);
            case 176:
                return yailListMap(obj, obj2);
            case 177:
                return yailListFilter(obj, obj2);
            case 180:
                return indexof(obj, obj2);
            case 181:
                return isTypeLt(obj, obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 182:
                return isIsLt(obj, obj2);
            case 183:
                return isIsEq(obj, obj2);
            case 184:
                return isIsLeq(obj, obj2);
            case 185:
                return isBooleanLt(obj, obj2);
            case 186:
                return isBooleanEq(obj, obj2);
            case 187:
                return isBooleanLeq(obj, obj2);
            case 188:
                return isListLt(obj, obj2);
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_DIALOG_FLAG /* 189 */:
                return isListEq(obj, obj2);
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PLAY /* 191 */:
                return isListLeq(obj, obj2);
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE /* 192 */:
                return isComponentLt(obj, obj2);
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_STOP /* 193 */:
                return isComponentEq(obj, obj2);
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SOURCE /* 194 */:
                return isComponentLeq(obj, obj2);
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN /* 195 */:
                return take(obj, obj2);
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION /* 196 */:
                return drop(obj, obj2);
            case 198:
                return mergesort(obj, obj2);
            case HttpRequestContext.HTTP_OK /* 200 */:
                return yailListSortComparator(obj, obj2);
            case 203:
                return yailListSortKey(obj, obj2);
            case 206:
                return yailListMinComparator(obj, obj2);
            case 208:
                return yailListMaxComparator(obj, obj2);
            case 212:
                return front(obj, obj2);
            case 217:
                return yailNumberRange(obj, obj2);
            case 220:
                return yailListJoinWithSeparator(obj, obj2);
            case 222:
                return makeDictionaryPair(obj, obj2);
            case 224:
                return yailDictionaryDeletePair(obj, obj2);
            case 227:
                return yailDictionaryWalk(obj, obj2);
            case 231:
                return yailDictionaryIsKeyIn(obj, obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 236:
                yailDictionaryCombineDicts(obj, obj2);
                return Values.empty;
            case 240:
                return Integer.valueOf(stringStartsAt(obj, obj2));
            case LispEscapeFormat.ESCAPE_NORMAL /* 241 */:
                return stringContains(obj, obj2);
            case LispEscapeFormat.ESCAPE_ALL /* 242 */:
                return stringContainsAny(obj, obj2);
            case 243:
                return stringContainsAll(obj, obj2);
            case 244:
                return stringSplitAtFirst(obj, obj2);
            case 245:
                return stringSplitAtFirstOfAny(obj, obj2);
            case 246:
                return stringSplit(obj, obj2);
            case 247:
                return stringSplitAtAny(obj, obj2);
            case Telnet.DO /* 253 */:
                return textDeobfuscate(obj, obj2);
            case Telnet.DONT /* 254 */:
                return stringReplaceMappingsDictionary(obj, obj2);
            case 255:
                return stringReplaceMappingsLongestString(obj, obj2);
            case 256:
                return stringReplaceMappingsEarliestOccurrence(obj, obj2);
            case 263:
                openAnotherScreenWithStartValue(obj, obj2);
                return Values.empty;
            case 269:
                return inUi(obj, obj2);
            case 270:
                return sendToBlock(obj, obj2);
            case 274:
                return renameComponent(obj, obj2);
            default:
                return super.apply2(moduleMethod, obj, obj2);
        }
    }

    public static void initRuntime() {
        setThisForm();
        $Stui$Mnhandler$St = new Handler();
    }

    public static void setThisForm() {
        $Stthis$Mnform$St = Form.getActiveForm();
    }

    @Override // gnu.expr.ModuleBody
    public Object apply0(ModuleMethod moduleMethod) {
        switch (moduleMethod.selector) {
            case 21:
                clearInitThunks();
                return Values.empty;
            case 42:
                resetCurrentFormEnvironment();
                return Values.empty;
            case 107:
                return Double.valueOf(randomFraction());
            case 260:
                closeScreen();
                return Values.empty;
            case 261:
                closeApplication();
                return Values.empty;
            case 264:
                return getStartValue();
            case 266:
                return getPlainStartText();
            case 268:
                return getServerAddressFromWifi();
            case 271:
                return clearCurrentForm();
            case 275:
                initRuntime();
                return Values.empty;
            case 276:
                setThisForm();
                return Values.empty;
            default:
                return super.apply0(moduleMethod);
        }
    }

    public static Object clarify(Object sl) {
        return clarify1(yailListContents(sl));
    }

    public static Object clarify1(Object sl) {
        String apply1;
        if (lists.isNull(sl)) {
            Object sp = LList.Empty;
            return sp;
        }
        if (IsEqual.apply(lists.car.apply1(sl), "")) {
            apply1 = "<empty>";
        } else {
            apply1 = IsEqual.apply(lists.car.apply1(sl), " ") ? "<space>" : lists.car.apply1(sl);
        }
        Object sp2 = apply1;
        return lists.cons(sp2, clarify1(lists.cdr.apply1(sl)));
    }
}
