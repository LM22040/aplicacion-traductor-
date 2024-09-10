package gnu.text;

import com.google.appinventor.components.runtime.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/* loaded from: classes.dex */
public class Options {
    public static final int BOOLEAN_OPTION = 1;
    public static final int STRING_OPTION = 2;
    public static final String UNKNOWN = "unknown option name";
    OptionInfo first;
    HashMap<String, OptionInfo> infoTable;
    OptionInfo last;
    Options previous;
    HashMap<String, Object> valueTable;

    /* loaded from: classes.dex */
    public static final class OptionInfo {
        Object defaultValue;
        String documentation;
        String key;
        int kind;
        OptionInfo next;
    }

    public Options() {
    }

    public Options(Options previous) {
        this.previous = previous;
    }

    public OptionInfo add(String key, int kind, String documentation) {
        return add(key, kind, null, documentation);
    }

    public OptionInfo add(String key, int kind, Object defaultValue, String documentation) {
        HashMap<String, OptionInfo> hashMap = this.infoTable;
        if (hashMap == null) {
            this.infoTable = new HashMap<>();
        } else if (hashMap.get(key) != null) {
            throw new RuntimeException("duplicate option key: " + key);
        }
        OptionInfo info = new OptionInfo();
        info.key = key;
        info.kind = kind;
        info.defaultValue = defaultValue;
        info.documentation = documentation;
        if (this.first == null) {
            this.first = info;
        } else {
            this.last.next = info;
        }
        this.last = info;
        this.infoTable.put(key, info);
        return info;
    }

    static Object valueOf(OptionInfo info, String argument) {
        if ((info.kind & 1) != 0) {
            if (argument == null || argument.equals(Component.TYPEFACE_SANSSERIF) || argument.equals("on") || argument.equals("yes") || argument.equals("true")) {
                return Boolean.TRUE;
            }
            if (argument.equals(Component.TYPEFACE_DEFAULT) || argument.equals("off") || argument.equals("no") || argument.equals("false")) {
                return Boolean.FALSE;
            }
            return null;
        }
        return argument;
    }

    private void error(String message, SourceMessages messages) {
        if (messages == null) {
            throw new RuntimeException(message);
        }
        messages.error('e', message);
    }

    public void set(String key, Object value) {
        set(key, value, null);
    }

    public void set(String key, Object value, SourceMessages messages) {
        OptionInfo info = getInfo(key);
        if (info == null) {
            error("invalid option key: " + key, messages);
            return;
        }
        if ((info.kind & 1) != 0) {
            if (value instanceof String) {
                value = valueOf(info, (String) value);
            }
            if (!(value instanceof Boolean)) {
                error("value for option " + key + " must be boolean or yes/no/true/false/on/off/1/0", messages);
                return;
            }
        } else if (value == null) {
            value = "";
        }
        if (this.valueTable == null) {
            this.valueTable = new HashMap<>();
        }
        this.valueTable.put(key, value);
    }

    public void reset(String key, Object oldValue) {
        if (this.valueTable == null) {
            this.valueTable = new HashMap<>();
        }
        if (oldValue == null) {
            this.valueTable.remove(key);
        } else {
            this.valueTable.put(key, oldValue);
        }
    }

    public String set(String key, String argument) {
        OptionInfo info = getInfo(key);
        if (info == null) {
            return UNKNOWN;
        }
        Object value = valueOf(info, argument);
        if (value == null && (info.kind & 1) != 0) {
            return "value of option " + key + " must be yes/no/true/false/on/off/1/0";
        }
        if (this.valueTable == null) {
            this.valueTable = new HashMap<>();
        }
        this.valueTable.put(key, value);
        return null;
    }

    public OptionInfo getInfo(String key) {
        Options options;
        HashMap<String, OptionInfo> hashMap = this.infoTable;
        Object info = hashMap == null ? null : (OptionInfo) hashMap.get(key);
        if (info == null && (options = this.previous) != null) {
            info = options.getInfo(key);
        }
        return (OptionInfo) info;
    }

    public Object get(String key, Object defaultValue) {
        OptionInfo info = getInfo(key);
        if (info == null) {
            throw new RuntimeException("invalid option key: " + key);
        }
        return get(info, defaultValue);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0021, code lost:
    
        if (r1.defaultValue == null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0023, code lost:
    
        r6 = r1.defaultValue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0025, code lost:
    
        r0 = r0.previous;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object get(gnu.text.Options.OptionInfo r5, java.lang.Object r6) {
        /*
            r4 = this;
            r0 = r4
        L1:
            if (r0 == 0) goto L28
            r1 = r5
        L4:
            java.util.HashMap<java.lang.String, java.lang.Object> r2 = r0.valueTable
            if (r2 != 0) goto La
            r2 = 0
            goto L10
        La:
            java.lang.String r3 = r1.key
            java.lang.Object r2 = r2.get(r3)
        L10:
            if (r2 == 0) goto L13
            return r2
        L13:
            java.lang.Object r3 = r1.defaultValue
            boolean r3 = r3 instanceof gnu.text.Options.OptionInfo
            if (r3 == 0) goto L1f
            java.lang.Object r3 = r1.defaultValue
            r1 = r3
            gnu.text.Options$OptionInfo r1 = (gnu.text.Options.OptionInfo) r1
            goto L4
        L1f:
            java.lang.Object r3 = r1.defaultValue
            if (r3 == 0) goto L25
            java.lang.Object r6 = r1.defaultValue
        L25:
            gnu.text.Options r0 = r0.previous
            goto L1
        L28:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.Options.get(gnu.text.Options$OptionInfo, java.lang.Object):java.lang.Object");
    }

    public Object get(OptionInfo key) {
        return get(key, (Object) null);
    }

    public Object getLocal(String key) {
        HashMap<String, Object> hashMap = this.valueTable;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(key);
    }

    public boolean getBoolean(String key) {
        return ((Boolean) get(key, Boolean.FALSE)).booleanValue();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        Boolean defaultObject = defaultValue ? Boolean.TRUE : Boolean.FALSE;
        return ((Boolean) get(key, defaultObject)).booleanValue();
    }

    public boolean getBoolean(OptionInfo key, boolean defaultValue) {
        Boolean defaultObject = defaultValue ? Boolean.TRUE : Boolean.FALSE;
        return ((Boolean) get(key, defaultObject)).booleanValue();
    }

    public boolean getBoolean(OptionInfo key) {
        Object value = get(key, (Object) null);
        if (value == null) {
            return false;
        }
        return ((Boolean) value).booleanValue();
    }

    public void pushOptionValues(Vector options) {
        int len = options.size();
        int i = 0;
        while (i < len) {
            int i2 = i + 1;
            String key = (String) options.elementAt(i);
            Object newValue = options.elementAt(i2);
            int i3 = i2 + 1;
            options.setElementAt(newValue, i2);
            set(key, options.elementAt(i3));
            i = i3 + 1;
        }
    }

    public void popOptionValues(Vector options) {
        int i = options.size();
        while (true) {
            i -= 3;
            if (i >= 0) {
                String key = (String) options.elementAt(i);
                Object oldValue = options.elementAt(i + 1);
                options.setElementAt(null, i + 1);
                reset(key, oldValue);
            } else {
                return;
            }
        }
    }

    public ArrayList<String> keys() {
        ArrayList<String> allKeys = new ArrayList<>();
        for (Options options = this; options != null; options = options.previous) {
            HashMap<String, OptionInfo> hashMap = options.infoTable;
            if (hashMap != null) {
                for (String k : hashMap.keySet()) {
                    if (!allKeys.contains(k)) {
                        allKeys.add(k);
                    }
                }
            }
        }
        return allKeys;
    }

    public String getDoc(String key) {
        OptionInfo info = getInfo(key);
        if (key == null) {
            return null;
        }
        return info.documentation;
    }
}
