package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class ElementsUtil {
    public static YailList elementsFromString(String itemString) {
        YailList items = new YailList();
        if (itemString.length() > 0) {
            YailList items2 = YailList.makeList(itemString.split(" *, *"));
            return items2;
        }
        return items;
    }

    public static List<String> elementsStrings(YailList itemList, String componentName) {
        Object[] objects = itemList.toStringArray();
        for (Object obj : objects) {
            if (!(obj instanceof String)) {
                throw new YailRuntimeError("Items passed to " + componentName + " must be Strings", "Error");
            }
        }
        String[] strings = (String[]) objects;
        List<String> ans = new ArrayList<>(Arrays.asList(strings));
        return ans;
    }

    public static List<String> elementsListFromString(String itemString) {
        if (itemString.length() > 0) {
            String[] words = itemString.split(" *, *");
            List<String> items = new ArrayList<>(Arrays.asList(words));
            return items;
        }
        List<String> items2 = new ArrayList<>();
        return items2;
    }

    public static YailList makeYailListFromList(List<String> stringItems) {
        if (stringItems == null || stringItems.size() == 0) {
            return YailList.makeEmptyList();
        }
        return YailList.makeList((List) stringItems);
    }

    public static int selectionIndexInStringList(int index, List<String> items) {
        if (index < 1 || index > items.size()) {
            return 0;
        }
        return index;
    }

    public static String setSelectionFromIndexInStringList(int index, List<String> items) {
        if (index < 1 || index > items.size()) {
            return "";
        }
        return items.get(index - 1);
    }

    public static int setSelectedIndexFromValueInStringList(String value, List<String> items) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equals(value)) {
                return i + 1;
            }
        }
        return 0;
    }

    public static YailList elements(YailList itemList, String componentName) {
        Object[] objects = itemList.toStringArray();
        for (Object obj : objects) {
            if (!(obj instanceof String)) {
                throw new YailRuntimeError("Items passed to " + componentName + " must be Strings", "Error");
            }
        }
        return itemList;
    }

    public static int selectionIndex(int index, YailList items) {
        if (index <= 0 || index > items.size()) {
            return 0;
        }
        return index;
    }

    public static String setSelectionFromIndex(int index, YailList items) {
        if (index == 0 || index > items.size()) {
            return "";
        }
        return items.getString(index - 1);
    }

    public static int setSelectedIndexFromValue(String value, YailList items) {
        for (int i = 0; i < items.size(); i++) {
            if (items.getString(i).equals(value)) {
                return i + 1;
            }
        }
        return 0;
    }

    public static String toStringEmptyIfNull(Object o) {
        return o == null ? "" : o.toString();
    }
}
