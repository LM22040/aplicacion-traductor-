package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.kawa.xml.MakeElement;
import gnu.kawa.xml.MakeText;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.xml.NamespaceBinding;
import kawa.lang.Syntax;
import kawa.lang.Translator;

/* loaded from: classes.dex */
public class MakeXmlElement extends Syntax {
    public static final MakeXmlElement makeXml;
    static final ClassType typeNamespace;

    static {
        MakeXmlElement makeXmlElement = new MakeXmlElement();
        makeXml = makeXmlElement;
        makeXmlElement.setName("$make-xml$");
        typeNamespace = ClassType.make("gnu.mapping.Namespace");
    }

    @Override // kawa.lang.Syntax
    public Expression rewriteForm(Pair form, Translator tr) {
        Namespace namespace;
        Object value;
        Pair pair1;
        Object namespaceList;
        Pair pair12 = (Pair) form.getCdr();
        Object namespaceList2 = pair12.getCar();
        Object obj = pair12.getCdr();
        NamespaceBinding saveBindings = tr.xmlElementNamespaces;
        NamespaceBinding nsBindings = saveBindings;
        boolean nsSeen = false;
        Object namespaceList3 = namespaceList2;
        while (namespaceList3 instanceof Pair) {
            if (!nsSeen) {
                tr.letStart();
                nsSeen = true;
            }
            Pair namespacePair = (Pair) namespaceList3;
            Pair namespaceNode = (Pair) namespacePair.getCar();
            String nsPrefix = (String) namespaceNode.getCar();
            String nsPrefix2 = nsPrefix.length() == 0 ? null : nsPrefix.intern();
            Object valueList = namespaceNode.getCdr();
            StringBuilder sbuf = new StringBuilder();
            while (valueList instanceof Pair) {
                Pair valuePair = valueList;
                Object valueForm = valuePair.getCar();
                if (LList.listLength(valueForm, false) != 2 || !(valueForm instanceof Pair) || ((Pair) valueForm).getCar() != MakeText.makeText) {
                    Expression valueExp = tr.rewrite_car(valuePair, false);
                    value = valueExp.valueIfConstant();
                } else {
                    value = ((Pair) ((Pair) valueForm).getCdr()).getCar();
                }
                if (value == null) {
                    Object savePos = tr.pushPositionOf(valuePair);
                    pair1 = pair12;
                    namespaceList = namespaceList3;
                    tr.error('e', "namespace URI must be literal");
                    tr.popPositionOf(savePos);
                } else {
                    pair1 = pair12;
                    namespaceList = namespaceList3;
                    sbuf.append(value);
                }
                valueList = valuePair.getCdr();
                pair12 = pair1;
                namespaceList3 = namespaceList;
            }
            Pair pair13 = pair12;
            String nsUri = sbuf.toString().intern();
            nsBindings = new NamespaceBinding(nsPrefix2, nsUri == "" ? null : nsUri, nsBindings);
            if (nsPrefix2 == null) {
                namespace = Namespace.valueOf(nsUri);
                nsPrefix2 = "[default-element-namespace]";
            } else {
                namespace = XmlNamespace.getInstance(nsPrefix2, nsUri);
            }
            Symbol nsSymbol = Namespace.EmptyNamespace.getSymbol(nsPrefix2);
            Declaration decl = tr.letVariable(nsSymbol, typeNamespace, new QuoteExp(namespace));
            decl.setFlag(2121728L);
            namespaceList3 = namespacePair.getCdr();
            pair12 = pair13;
        }
        MakeElement mkElement = new MakeElement();
        mkElement.setNamespaceNodes(nsBindings);
        tr.xmlElementNamespaces = nsBindings;
        if (nsSeen) {
            try {
                tr.letEnter();
            } catch (Throwable th) {
                th = th;
                tr.xmlElementNamespaces = saveBindings;
                throw th;
            }
        }
        try {
            Expression result = tr.rewrite(Translator.makePair(form, mkElement, obj));
            Expression letDone = nsSeen ? tr.letDone(result) : result;
            tr.xmlElementNamespaces = saveBindings;
            return letDone;
        } catch (Throwable th2) {
            th = th2;
            tr.xmlElementNamespaces = saveBindings;
            throw th;
        }
    }
}
