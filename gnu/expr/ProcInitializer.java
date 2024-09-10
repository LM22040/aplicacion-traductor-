package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.mapping.Environment;
import gnu.mapping.PropertySet;
import gnu.mapping.Symbol;

/* loaded from: classes.dex */
public class ProcInitializer extends Initializer {
    LambdaExp proc;

    public ProcInitializer(LambdaExp lexp, Compilation comp, Field field) {
        this.field = field;
        this.proc = lexp;
        LambdaExp heapLambda = field.getStaticFlag() ? comp.getModule() : lexp.getOwningLambda();
        if ((heapLambda instanceof ModuleExp) && comp.isStatic()) {
            this.next = comp.clinitChain;
            comp.clinitChain = this;
        } else {
            this.next = heapLambda.initChain;
            heapLambda.initChain = this;
        }
    }

    public static void emitLoadModuleMethod(LambdaExp proc, Compilation comp) {
        String initName;
        LambdaExp lambdaExp = proc;
        Compilation compilation = comp;
        Declaration pdecl = lambdaExp.nameDecl;
        Object pname = pdecl == null ? proc.getName() : pdecl.getSymbol();
        ModuleMethod oldproc = null;
        if (compilation.immediate && pname != null && pdecl != null) {
            Environment env = Environment.getCurrent();
            Symbol sym = pname instanceof Symbol ? (Symbol) pname : Symbol.make("", pname.toString().intern());
            Object property = comp.getLanguage().getEnvPropertyFor(lambdaExp.nameDecl);
            Object old = env.get(sym, property, null);
            if (old instanceof ModuleMethod) {
                oldproc = (ModuleMethod) old;
            }
        }
        CodeAttr code = comp.getCode();
        ClassType procClass = Compilation.typeModuleMethod;
        int i = 1;
        if (oldproc == null) {
            code.emitNew(procClass);
            code.emitDup(1);
            initName = "<init>";
        } else {
            compilation.compileConstant(oldproc, Target.pushValue(procClass));
            initName = "init";
        }
        Method initModuleMethod = procClass.getDeclaredMethod(initName, 4);
        LambdaExp owning = proc.getNeedsClosureEnv() ? proc.getOwningLambda() : comp.getModule();
        if ((owning instanceof ClassExp) && owning.staticLinkField != null) {
            code.emitLoad(code.getCurrentScope().getVariable(1));
        } else if (!(owning instanceof ModuleExp) || (compilation.moduleClass == compilation.mainClass && !compilation.method.getStaticFlag())) {
            code.emitPushThis();
        } else {
            if (compilation.moduleInstanceVar == null) {
                compilation.moduleInstanceVar = code.locals.current_scope.addVariable(code, compilation.moduleClass, "$instance");
                if (compilation.moduleClass != compilation.mainClass && !comp.isStatic()) {
                    code.emitNew(compilation.moduleClass);
                    code.emitDup(compilation.moduleClass);
                    code.emitInvokeSpecial(compilation.moduleClass.constructor);
                    compilation.moduleInstanceMainField = compilation.moduleClass.addField("$main", compilation.mainClass, 0);
                    code.emitDup(compilation.moduleClass);
                    code.emitPushThis();
                    code.emitPutField(compilation.moduleInstanceMainField);
                } else {
                    code.emitGetStatic(compilation.moduleInstanceMainField);
                }
                code.emitStore(compilation.moduleInstanceVar);
            }
            code.emitLoad(compilation.moduleInstanceVar);
        }
        code.emitPushInt(proc.getSelectorValue(comp));
        compilation.compileConstant(pname, Target.pushObject);
        code.emitPushInt(lambdaExp.min_args | ((lambdaExp.keywords == null ? lambdaExp.max_args : -1) << 12));
        code.emitInvoke(initModuleMethod);
        if (lambdaExp.properties != null) {
            int len = lambdaExp.properties.length;
            int i2 = 0;
            while (i2 < len) {
                Object key = lambdaExp.properties[i2];
                if (key != null && key != PropertySet.nameKey) {
                    Object val = lambdaExp.properties[i2 + 1];
                    code.emitDup(i);
                    compilation.compileConstant(key);
                    Target target = Target.pushObject;
                    if (val instanceof Expression) {
                        ((Expression) val).compile(compilation, target);
                    } else {
                        compilation.compileConstant(val, target);
                    }
                    Method m = ClassType.make("gnu.mapping.PropertySet").getDeclaredMethod("setProperty", 2);
                    code.emitInvokeVirtual(m);
                }
                i2 += 2;
                i = 1;
                lambdaExp = proc;
                compilation = comp;
            }
        }
    }

    @Override // gnu.expr.Initializer
    public void emit(Compilation comp) {
        CodeAttr code = comp.getCode();
        if (!this.field.getStaticFlag()) {
            code.emitPushThis();
        }
        emitLoadModuleMethod(this.proc, comp);
        if (this.field.getStaticFlag()) {
            code.emitPutStatic(this.field);
        } else {
            code.emitPutField(this.field);
        }
    }

    @Override // gnu.expr.Initializer
    public void reportError(String message, Compilation comp) {
        String saveFile = comp.getFileName();
        int saveLine = comp.getLineNumber();
        int saveColumn = comp.getColumnNumber();
        comp.setLocation(this.proc);
        String name = this.proc.getName();
        StringBuffer sbuf = new StringBuffer(message);
        if (name == null) {
            sbuf.append("unnamed procedure");
        } else {
            sbuf.append("procedure ");
            sbuf.append(name);
        }
        comp.error('e', sbuf.toString());
        comp.setLine(saveFile, saveLine, saveColumn);
    }
}
