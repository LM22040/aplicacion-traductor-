package com.google.appinventor.components.runtime.util;

import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.WebViewer;

/* loaded from: classes.dex */
public class HoneycombWebViewClient extends FroyoWebViewClient<WebViewer> {
    private static final String ASSET_PREFIX = "file:///appinventor_asset/";
    private static final String TAG = HoneycombWebViewClient.class.getSimpleName();

    public HoneycombWebViewClient(boolean followLinks, boolean ignoreErrors, Form form, WebViewer component) {
        super(followLinks, ignoreErrors, form, component);
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        if (url.startsWith("http://localhost/") || url.startsWith(ASSET_PREFIX)) {
            return handleAppRequest(url);
        }
        return super.shouldInterceptRequest(view, url);
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        Log.d(TAG, "scheme = " + request.getUrl().getScheme());
        if ("localhost".equals(request.getUrl().getAuthority()) || request.getUrl().toString().startsWith(ASSET_PREFIX)) {
            return handleAppRequest(request.getUrl().toString());
        }
        return super.shouldInterceptRequest(view, request);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x008d A[Catch: IOException -> 0x00a2, TryCatch #0 {IOException -> 0x00a2, blocks: (B:6:0x0025, B:8:0x0074, B:10:0x007c, B:14:0x0089, B:16:0x008d, B:19:0x009c), top: B:5:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x009c A[Catch: IOException -> 0x00a2, TRY_LEAVE, TryCatch #0 {IOException -> 0x00a2, blocks: (B:6:0x0025, B:8:0x0074, B:10:0x007c, B:14:0x0089, B:16:0x008d, B:19:0x009c), top: B:5:0x0025 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected android.webkit.WebResourceResponse handleAppRequest(java.lang.String r17) {
        /*
            r16 = this;
            r1 = r17
            java.lang.String r2 = "utf-8"
            java.lang.String r0 = "file:///appinventor_asset/"
            boolean r3 = r1.startsWith(r0)
            if (r3 == 0) goto L16
            int r0 = r0.length()
            java.lang.String r0 = r1.substring(r0)
            r3 = r0
            goto L23
        L16:
            java.lang.String r0 = "//localhost/"
            int r0 = r1.indexOf(r0)
            int r0 = r0 + 12
            java.lang.String r0 = r1.substring(r0)
            r3 = r0
        L23:
            r4 = 21
            java.lang.String r0 = com.google.appinventor.components.runtime.util.HoneycombWebViewClient.TAG     // Catch: java.io.IOException -> La2
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.io.IOException -> La2
            r5.<init>()     // Catch: java.io.IOException -> La2
            java.lang.String r6 = "webviewer requested path = "
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch: java.io.IOException -> La2
            java.lang.StringBuilder r5 = r5.append(r3)     // Catch: java.io.IOException -> La2
            java.lang.String r5 = r5.toString()     // Catch: java.io.IOException -> La2
            android.util.Log.i(r0, r5)     // Catch: java.io.IOException -> La2
            com.google.appinventor.components.runtime.Form r5 = r16.getForm()     // Catch: java.io.IOException -> La2
            java.io.InputStream r5 = r5.openAsset(r3)     // Catch: java.io.IOException -> La2
            java.util.HashMap r6 = new java.util.HashMap     // Catch: java.io.IOException -> La2
            r6.<init>()     // Catch: java.io.IOException -> La2
            r13 = r6
            java.lang.String r6 = "Access-Control-Allow-Origin"
            java.lang.String r7 = "localhost"
            r13.put(r6, r7)     // Catch: java.io.IOException -> La2
            java.net.FileNameMap r6 = java.net.URLConnection.getFileNameMap()     // Catch: java.io.IOException -> La2
            java.lang.String r6 = r6.getContentTypeFor(r3)     // Catch: java.io.IOException -> La2
            r14 = r6
            r6 = r2
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.io.IOException -> La2
            r7.<init>()     // Catch: java.io.IOException -> La2
            java.lang.String r8 = "Mime type = "
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch: java.io.IOException -> La2
            java.lang.StringBuilder r7 = r7.append(r14)     // Catch: java.io.IOException -> La2
            java.lang.String r7 = r7.toString()     // Catch: java.io.IOException -> La2
            android.util.Log.i(r0, r7)     // Catch: java.io.IOException -> La2
            if (r14 == 0) goto L87
            java.lang.String r0 = "text/"
            boolean r0 = r14.startsWith(r0)     // Catch: java.io.IOException -> La2
            if (r0 != 0) goto L85
            java.lang.String r0 = "application/javascript"
            boolean r0 = r14.equals(r0)     // Catch: java.io.IOException -> La2
            if (r0 != 0) goto L85
            goto L87
        L85:
            r0 = r6
            goto L89
        L87:
            r6 = 0
            r0 = r6
        L89:
            int r6 = android.os.Build.VERSION.SDK_INT     // Catch: java.io.IOException -> La2
            if (r6 < r4) goto L9c
            android.webkit.WebResourceResponse r15 = new android.webkit.WebResourceResponse     // Catch: java.io.IOException -> La2
            r9 = 200(0xc8, float:2.8E-43)
            java.lang.String r10 = "OK"
            r6 = r15
            r7 = r14
            r8 = r0
            r11 = r13
            r12 = r5
            r6.<init>(r7, r8, r9, r10, r11, r12)     // Catch: java.io.IOException -> La2
            return r15
        L9c:
            android.webkit.WebResourceResponse r6 = new android.webkit.WebResourceResponse     // Catch: java.io.IOException -> La2
            r6.<init>(r14, r0, r5)     // Catch: java.io.IOException -> La2
            return r6
        La2:
            r0 = move-exception
            java.io.ByteArrayInputStream r5 = new java.io.ByteArrayInputStream
            java.lang.String r6 = "404 Not Found"
            byte[] r6 = r6.getBytes()
            r5.<init>(r6)
            int r6 = android.os.Build.VERSION.SDK_INT
            if (r6 < r4) goto Lc3
            android.webkit.WebResourceResponse r2 = new android.webkit.WebResourceResponse
            java.lang.String r8 = "text/plain"
            java.lang.String r9 = "utf-8"
            r10 = 404(0x194, float:5.66E-43)
            java.lang.String r11 = "Not Found"
            r12 = 0
            r7 = r2
            r13 = r5
            r7.<init>(r8, r9, r10, r11, r12, r13)
            return r2
        Lc3:
            android.webkit.WebResourceResponse r4 = new android.webkit.WebResourceResponse
            java.lang.String r6 = "text/plain"
            r4.<init>(r6, r2, r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.HoneycombWebViewClient.handleAppRequest(java.lang.String):android.webkit.WebResourceResponse");
    }
}
