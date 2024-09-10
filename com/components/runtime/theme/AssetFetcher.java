package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.ReplForm;
import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class AssetFetcher {
    private static Context context = ReplForm.getActiveForm();
    private static HashDatabase db = new HashDatabase(context);
    private static final String LOG_TAG = AssetFetcher.class.getSimpleName();
    private static ExecutorService background = Executors.newSingleThreadExecutor();
    private static volatile boolean inError = false;
    private static final Object semaphore = new Object();

    private AssetFetcher() {
    }

    public static void fetchAssets(final String cookieValue, final String projectId, final String uri, final String asset) {
        background.submit(new Runnable() { // from class: com.google.appinventor.components.runtime.util.AssetFetcher.1
            @Override // java.lang.Runnable
            public void run() {
                String fileName = uri + "/ode/download/file/" + projectId + "/" + asset;
                if (AssetFetcher.getFile(fileName, cookieValue, asset, 0) != null) {
                    RetValManager.assetTransferred(asset);
                }
            }
        });
    }

    public static void upgradeCompanion(final String cookieValue, final String inputUri) {
        background.submit(new Runnable() { // from class: com.google.appinventor.components.runtime.util.AssetFetcher.2
            @Override // java.lang.Runnable
            public void run() {
                String[] parts = inputUri.split("/", 0);
                String asset = parts[parts.length - 1];
                File assetFile = AssetFetcher.getFile(inputUri, cookieValue, asset, 0);
                if (assetFile != null) {
                    try {
                        Form form = Form.getActiveForm();
                        Intent intent = new Intent("android.intent.action.VIEW");
                        Uri packageuri = NougatUtil.getPackageUri(form, assetFile);
                        intent.setDataAndType(packageuri, "application/vnd.android.package-archive");
                        intent.setFlags(1);
                        form.startActivity(intent);
                    } catch (Exception e) {
                        Log.e(AssetFetcher.LOG_TAG, "ERROR_UNABLE_TO_GET", e);
                        RetValManager.sendError("Unable to Install new Companion Package.");
                    }
                }
            }
        });
    }

    public static void loadExtensions(String jsonString) {
        String str = LOG_TAG;
        Log.d(str, "loadExtensions called jsonString = " + jsonString);
        try {
            ReplForm form = (ReplForm) Form.getActiveForm();
            JSONArray array = new JSONArray(jsonString);
            List<String> extensionsToLoad = new ArrayList<>();
            if (array.length() == 0) {
                Log.d(str, "loadExtensions: No Extensions");
                RetValManager.extensionsLoaded();
                return;
            }
            for (int i = 0; i < array.length(); i++) {
                String extensionName = array.optString(i);
                if (extensionName != null) {
                    Log.d(LOG_TAG, "loadExtensions, extensionName = " + extensionName);
                    extensionsToLoad.add(extensionName);
                } else {
                    Log.e(LOG_TAG, "extensionName was null");
                    return;
                }
            }
            try {
                form.loadComponents(extensionsToLoad);
                RetValManager.extensionsLoaded();
            } catch (Exception e) {
                Log.e(LOG_TAG, "Error in form.loadComponents", e);
            }
        } catch (JSONException e2) {
            Log.e(LOG_TAG, "JSON Exception parsing extension string", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:129:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008f A[Catch: Exception -> 0x02c0, TRY_LEAVE, TryCatch #0 {Exception -> 0x02c0, blocks: (B:19:0x0056, B:22:0x006c, B:25:0x008f, B:34:0x00b9, B:42:0x00fa, B:120:0x0103), top: B:18:0x0056 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0238 A[Catch: Exception -> 0x027f, TryCatch #1 {Exception -> 0x027f, blocks: (B:105:0x0189, B:85:0x022c, B:107:0x01a2, B:108:0x01c0, B:77:0x01e7, B:79:0x01ec, B:83:0x020d, B:66:0x0233, B:68:0x0238, B:70:0x025c, B:71:0x027a, B:72:0x027e), top: B:57:0x016a }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x029b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.io.File getFile(final java.lang.String r21, java.lang.String r22, java.lang.String r23, int r24) {
        /*
            Method dump skipped, instructions count: 738
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.AssetFetcher.getFile(java.lang.String, java.lang.String, java.lang.String, int):java.io.File");
    }

    private static String byteArray2Hex(byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", Byte.valueOf(b));
        }
        return formatter.toString();
    }
}
